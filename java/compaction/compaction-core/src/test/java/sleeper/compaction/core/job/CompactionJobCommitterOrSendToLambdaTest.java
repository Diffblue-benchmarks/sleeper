/*
 * Copyright 2022-2024 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sleeper.compaction.core.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.commit.CompactionCommitMessage;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogsPerTable;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.RecordsProcessed;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_ASYNC_BATCHING;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_COMMIT_ASYNC;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

public class CompactionJobCommitterOrSendToLambdaTest {
    private static final String TEST_TABLE_ID = "test-table-id";
    private static final Instant TEST_TIME = Instant.parse("2024-03-17T10:00:00Z");
    private static final String TEST_TASK_ID = "test-task-id";
    private static final String TEST_JOB_RUN_ID = "test-job-run-id";

    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final Schema schema = schemaWithKey("key");
    private final InMemoryTransactionLogsPerTable transactionLogs = new InMemoryTransactionLogsPerTable();
    private final StateStoreProvider stateStoreProvider = InMemoryTransactionLogStateStore.createProvider(instanceProperties, transactionLogs);
    private final TableProperties tableProperties = createTableProperties();
    private final StateStore stateStore = stateStoreProvider.getStateStore(tableProperties);
    private final CompactionJobTracker jobTracker = new InMemoryCompactionJobTracker();
    private final List<StateStoreCommitRequest> stateStoreCommitQueue = new ArrayList<>();
    private final List<CompactionCommitMessage> batcherCommitQueue = new ArrayList<>();
    private FileReferenceFactory factory;

    @BeforeEach
    void setUp() throws Exception {
        update(stateStore).initialise(schema);
        factory = FileReferenceFactory.from(stateStore);
    }

    private TableProperties createTableProperties() {
        TableProperties table = createTestTableProperties(instanceProperties, schema);
        table.set(TABLE_ID, TEST_TABLE_ID);
        return table;
    }

    private TablePropertiesProvider tablePropertiesProvider() {
        return new FixedTablePropertiesProvider(List.of(tableProperties));
    }

    private CompactionJob createJob(String jobId) throws Exception {
        String inputFile = UUID.randomUUID().toString();
        String outputFile = UUID.randomUUID().toString();
        CompactionJob job = CompactionJob.builder()
                .tableId(TEST_TABLE_ID)
                .jobId(jobId)
                .partitionId("root")
                .inputFiles(List.of(inputFile))
                .outputFile(outputFile)
                .build();
        update(stateStore).addFile(factory.rootFile(inputFile, 100L));
        update(stateStore).assignJobIds(List.of(job.createAssignJobIdRequest()));
        return job;
    }

    private CompactionJobFinishedEvent createFinishedEvent(CompactionJob job, RecordsProcessed records) {
        JobRunSummary summary = new JobRunSummary(records, TEST_TIME, TEST_TIME.plusSeconds(10));
        return job.finishedEventBuilder(summary)
                .taskId(TEST_TASK_ID)
                .jobRunId(TEST_JOB_RUN_ID)
                .build();
    }

    @Nested
    @DisplayName("Constructor tests")
    class ConstructorTests {

        @Test
        void shouldCreateInstanceWithFiveParameterConstructor() {
            // Given / When
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    tablePropertiesProvider(),
                    stateStoreProvider,
                    jobTracker,
                    stateStoreCommitQueue::add,
                    batcherCommitQueue::add);

            // Then
            assertThat(committer).isNotNull();
        }

        @Test
        void shouldCreateInstanceWithSixParameterConstructor() {
            // Given / When
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    tablePropertiesProvider(),
                    stateStoreProvider,
                    jobTracker,
                    stateStoreCommitQueue::add,
                    batcherCommitQueue::add,
                    () -> TEST_TIME);

            // Then
            assertThat(committer).isNotNull();
        }
    }

    @Nested
    @DisplayName("Commit with async batching")
    class AsyncBatchingCommit {

        @BeforeEach
        void setUpAsync() {
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "true");
            tableProperties.set(COMPACTION_JOB_ASYNC_BATCHING, "true");
        }

        @Test
        void shouldSendJobToBatcherQueueWhenAsyncBatchingEnabled() throws Exception {
            // Given
            CompactionJob job = createJob("job1");
            RecordsProcessed records = new RecordsProcessed(100L, 100L);
            CompactionJobFinishedEvent finishedEvent = createFinishedEvent(job, records);
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    tablePropertiesProvider(),
                    stateStoreProvider,
                    jobTracker,
                    stateStoreCommitQueue::add,
                    batcherCommitQueue::add,
                    () -> TEST_TIME);

            // When
            committer.commit(job, finishedEvent);

            // Then
            assertThat(batcherCommitQueue).hasSize(1);
            assertThat(stateStoreCommitQueue).isEmpty();
            CompactionCommitMessage message = batcherCommitQueue.get(0);
            assertThat(message.tableId()).isEqualTo(TEST_TABLE_ID);
            assertThat(message.request().getJobId()).isEqualTo("job1");
        }

        @Test
        void shouldTrackJobAsFinishedWhenSendingToBatcher() throws Exception {
            // Given
            CompactionJob job = createJob("job2");
            RecordsProcessed records = new RecordsProcessed(200L, 200L);
            CompactionJobFinishedEvent finishedEvent = createFinishedEvent(job, records);
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    tablePropertiesProvider(),
                    stateStoreProvider,
                    jobTracker,
                    stateStoreCommitQueue::add,
                    batcherCommitQueue::add,
                    () -> TEST_TIME);

            // When
            committer.commit(job, finishedEvent);

            // Then
            assertThat(jobTracker.getAllJobs(TEST_TABLE_ID)).hasSize(1);
        }
    }

    @Nested
    @DisplayName("Commit with async no batching")
    class AsyncNoBatchingCommit {

        @BeforeEach
        void setUpAsync() {
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "true");
            tableProperties.set(COMPACTION_JOB_ASYNC_BATCHING, "false");
        }

        @Test
        void shouldSendJobToStateStoreCommitQueueWhenAsyncNoBatchingEnabled() throws Exception {
            // Given
            CompactionJob job = createJob("job3");
            RecordsProcessed records = new RecordsProcessed(150L, 150L);
            CompactionJobFinishedEvent finishedEvent = createFinishedEvent(job, records);
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    tablePropertiesProvider(),
                    stateStoreProvider,
                    jobTracker,
                    stateStoreCommitQueue::add,
                    batcherCommitQueue::add,
                    () -> TEST_TIME);

            // When
            committer.commit(job, finishedEvent);

            // Then
            assertThat(stateStoreCommitQueue).hasSize(1);
            assertThat(batcherCommitQueue).isEmpty();
            StateStoreCommitRequest request = stateStoreCommitQueue.get(0);
            assertThat(request.getTableId()).isEqualTo(TEST_TABLE_ID);
        }

        @Test
        void shouldTrackJobAsFinishedWhenSendingToStateStoreQueue() throws Exception {
            // Given
            CompactionJob job = createJob("job4");
            RecordsProcessed records = new RecordsProcessed(250L, 250L);
            CompactionJobFinishedEvent finishedEvent = createFinishedEvent(job, records);
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    tablePropertiesProvider(),
                    stateStoreProvider,
                    jobTracker,
                    stateStoreCommitQueue::add,
                    batcherCommitQueue::add,
                    () -> TEST_TIME);

            // When
            committer.commit(job, finishedEvent);

            // Then
            assertThat(jobTracker.getAllJobs(TEST_TABLE_ID)).hasSize(1);
        }
    }

    @Nested
    @DisplayName("Commit synchronously")
    class SynchronousCommit {

        @BeforeEach
        void setUpSynchronous() {
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "false");
        }

        @Test
        void shouldCommitJobSynchronouslyWhenAsyncCommitDisabled() throws Exception {
            // Given
            CompactionJob job = createJob("job5");
            RecordsProcessed records = new RecordsProcessed(300L, 300L);
            CompactionJobFinishedEvent finishedEvent = createFinishedEvent(job, records);
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    tablePropertiesProvider(),
                    stateStoreProvider,
                    jobTracker,
                    stateStoreCommitQueue::add,
                    batcherCommitQueue::add,
                    () -> TEST_TIME);

            // When
            committer.commit(job, finishedEvent);

            // Then
            assertThat(stateStoreCommitQueue).isEmpty();
            assertThat(batcherCommitQueue).isEmpty();
        }

        @Test
        void shouldTrackJobAsCommittedWhenCommittingSynchronously() throws Exception {
            // Given
            CompactionJob job = createJob("job6");
            RecordsProcessed records = new RecordsProcessed(400L, 400L);
            CompactionJobFinishedEvent finishedEvent = createFinishedEvent(job, records);
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    tablePropertiesProvider(),
                    stateStoreProvider,
                    jobTracker,
                    stateStoreCommitQueue::add,
                    batcherCommitQueue::add,
                    () -> TEST_TIME);

            // When
            committer.commit(job, finishedEvent);

            // Then
            assertThat(jobTracker.getAllJobs(TEST_TABLE_ID)).hasSize(1);
        }

        @Test
        void shouldUpdateStateStoreWhenCommittingSynchronously() throws Exception {
            // Given
            CompactionJob job = createJob("job7");
            RecordsProcessed records = new RecordsProcessed(500L, 500L);
            CompactionJobFinishedEvent finishedEvent = createFinishedEvent(job, records);
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    tablePropertiesProvider(),
                    stateStoreProvider,
                    jobTracker,
                    stateStoreCommitQueue::add,
                    batcherCommitQueue::add,
                    () -> TEST_TIME);

            // When
            committer.commit(job, finishedEvent);

            // Then
            assertThat(stateStore.getFileReferences()).hasSize(1);
            assertThat(stateStore.getFileReferences().get(0).getFilename()).isEqualTo(job.getOutputFile());
        }
    }
}
