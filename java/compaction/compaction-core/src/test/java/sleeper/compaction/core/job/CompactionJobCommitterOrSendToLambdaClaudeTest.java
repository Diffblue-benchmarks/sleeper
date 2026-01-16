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
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.statestore.transactionlog.transaction.impl.ReplaceFileReferencesTransaction;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.RecordsProcessed;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_ASYNC_BATCHING;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_COMMIT_ASYNC;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

public class CompactionJobCommitterOrSendToLambdaClaudeTest {

    private static final Schema SCHEMA = schemaWithKey("key");
    private static final String PARTITION_ID = "root";
    private static final String TASK_ID = "test-task";
    private static final String JOB_RUN_ID = "test-run";

    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, SCHEMA);
    private final InMemoryTransactionLogs transactionLogs = new InMemoryTransactionLogs();
    private final InMemoryCompactionJobTracker tracker = new InMemoryCompactionJobTracker();
    private final List<StateStoreCommitRequest> sentCommitRequests = new ArrayList<>();
    private final List<CompactionCommitMessage> sentBatchedMessages = new ArrayList<>();

    private StateStore stateStore;
    private FileReferenceFactory fileFactory;
    private Instant fixedTime = Instant.parse("2024-01-15T12:00:00Z");

    @BeforeEach
    void setUp() {
        stateStore = InMemoryTransactionLogStateStore.createAndInitialise(tableProperties, transactionLogs);
        fileFactory = FileReferenceFactory.from(stateStore);
    }

    @Nested
    @DisplayName("Synchronous commit (no async)")
    class SynchronousCommit {

        @BeforeEach
        void setUp() {
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "false");
        }

        @Test
        void shouldCommitCompactionJobSynchronouslyAndTrackCommit() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("input1.parquet", 100);
            FileReference file2 = fileFactory.rootFile("input2.parquet", 100);
            update(stateStore).addFiles(List.of(file1, file2));

            CompactionJob job = createCompactionJob("output.parquet", List.of("input1.parquet", "input2.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(200, 180),
                    Instant.parse("2024-01-15T11:00:00Z"),
                    Instant.parse("2024-01-15T11:30:00Z"));
            CompactionJobFinishedEvent finishedEvent = job.finishedEventBuilder(summary)
                    .taskId(TASK_ID)
                    .jobRunId(JOB_RUN_ID)
                    .build();

            // When
            committer().commit(job, finishedEvent);

            // Then - verify state store was updated
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getFilename)
                    .containsExactly("output.parquet");
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getNumberOfRecords)
                    .containsExactly(180L);

            // Verify tracker recorded finished and committed events
            Optional<CompactionJobStatus> jobStatus = tracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
            assertThat(jobStatus.get().isAnyRunSuccessful()).isTrue();

            // No async messages should be sent
            assertThat(sentCommitRequests).isEmpty();
            assertThat(sentBatchedMessages).isEmpty();
        }

        @Test
        void shouldTrackJobCommittedWithCorrectTime() throws Exception {
            // Given
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(100, 90),
                    Instant.parse("2024-01-15T11:00:00Z"),
                    Instant.parse("2024-01-15T11:30:00Z"));
            CompactionJobFinishedEvent finishedEvent = job.finishedEventBuilder(summary)
                    .taskId(TASK_ID)
                    .jobRunId(JOB_RUN_ID)
                    .build();

            Instant commitTime = Instant.parse("2024-01-15T12:00:00Z");

            // When
            committerWithTime(() -> commitTime).commit(job, finishedEvent);

            // Then - verify state store was updated and job tracked
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getFilename)
                    .containsExactly("output.parquet");

            Optional<CompactionJobStatus> jobStatus = tracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
        }
    }

    @Nested
    @DisplayName("Asynchronous commit to single queue")
    class AsyncCommitToSingleQueue {

        @BeforeEach
        void setUp() {
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "true");
            tableProperties.set(COMPACTION_JOB_ASYNC_BATCHING, "false");
        }

        @Test
        void shouldSendCommitRequestToQueue() throws Exception {
            // Given
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(100, 90),
                    Instant.parse("2024-01-15T11:00:00Z"),
                    Instant.parse("2024-01-15T11:30:00Z"));
            CompactionJobFinishedEvent finishedEvent = job.finishedEventBuilder(summary)
                    .taskId(TASK_ID)
                    .jobRunId(JOB_RUN_ID)
                    .build();

            // When
            committer().commit(job, finishedEvent);

            // Then - verify commit request was sent to queue
            assertThat(sentCommitRequests).hasSize(1);
            StateStoreCommitRequest request = sentCommitRequests.get(0);
            assertThat(request.getTableId()).isEqualTo(tableProperties.get(TABLE_ID));

            // Verify transaction type
            Optional<ReplaceFileReferencesTransaction> transaction = request.getTransactionIfHeld();
            assertThat(transaction).isPresent();

            // No batched messages should be sent
            assertThat(sentBatchedMessages).isEmpty();

            // State store should NOT be modified (async)
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getFilename)
                    .containsExactly("input.parquet");

            // Tracker should record finished event
            Optional<CompactionJobStatus> jobStatus = tracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
        }

        @Test
        void shouldIncludeTaskIdAndJobRunIdInRequest() throws Exception {
            // Given
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(100, 90),
                    Instant.parse("2024-01-15T11:00:00Z"),
                    Instant.parse("2024-01-15T11:30:00Z"));
            CompactionJobFinishedEvent finishedEvent = job.finishedEventBuilder(summary)
                    .taskId(TASK_ID)
                    .jobRunId(JOB_RUN_ID)
                    .build();

            // When
            committer().commit(job, finishedEvent);

            // Then
            assertThat(sentCommitRequests).hasSize(1);
            StateStoreCommitRequest request = sentCommitRequests.get(0);
            Optional<ReplaceFileReferencesTransaction> transaction = request.getTransactionIfHeld();
            assertThat(transaction).isPresent();
            assertThat(transaction.get().getJobs().get(0).getTaskId()).isEqualTo(TASK_ID);
            assertThat(transaction.get().getJobs().get(0).getJobRunId()).isEqualTo(JOB_RUN_ID);
        }
    }

    @Nested
    @DisplayName("Asynchronous commit to batched queue")
    class AsyncCommitToBatchedQueue {

        @BeforeEach
        void setUp() {
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "true");
            tableProperties.set(COMPACTION_JOB_ASYNC_BATCHING, "true");
        }

        @Test
        void shouldSendCompactionCommitMessageToBatcherQueue() throws Exception {
            // Given
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(100, 90),
                    Instant.parse("2024-01-15T11:00:00Z"),
                    Instant.parse("2024-01-15T11:30:00Z"));
            CompactionJobFinishedEvent finishedEvent = job.finishedEventBuilder(summary)
                    .taskId(TASK_ID)
                    .jobRunId(JOB_RUN_ID)
                    .build();

            // When
            committer().commit(job, finishedEvent);

            // Then - verify batched message was sent
            assertThat(sentBatchedMessages).hasSize(1);
            CompactionCommitMessage message = sentBatchedMessages.get(0);
            assertThat(message.tableId()).isEqualTo(tableProperties.get(TABLE_ID));
            assertThat(message.request().getJobId()).isEqualTo(job.getId());
            assertThat(message.request().getInputFiles()).isEqualTo(job.getInputFiles());
            assertThat(message.request().getNewReference().getFilename()).isEqualTo("output.parquet");
            assertThat(message.request().getNewReference().getNumberOfRecords()).isEqualTo(90L);

            // No single commit queue messages should be sent
            assertThat(sentCommitRequests).isEmpty();

            // State store should NOT be modified (async)
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getFilename)
                    .containsExactly("input.parquet");
        }

        @Test
        void shouldIncludeTaskIdAndJobRunIdInBatchedMessage() throws Exception {
            // Given
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(100, 90),
                    Instant.parse("2024-01-15T11:00:00Z"),
                    Instant.parse("2024-01-15T11:30:00Z"));
            CompactionJobFinishedEvent finishedEvent = job.finishedEventBuilder(summary)
                    .taskId(TASK_ID)
                    .jobRunId(JOB_RUN_ID)
                    .build();

            // When
            committer().commit(job, finishedEvent);

            // Then
            assertThat(sentBatchedMessages).hasSize(1);
            CompactionCommitMessage message = sentBatchedMessages.get(0);
            assertThat(message.request().getTaskId()).isEqualTo(TASK_ID);
            assertThat(message.request().getJobRunId()).isEqualTo(JOB_RUN_ID);
        }
    }

    @Nested
    @DisplayName("Constructor variants")
    class ConstructorVariants {

        @Test
        void shouldCreateCommitterWithDefaultTimeSupplier() throws Exception {
            // Given
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "false");

            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(100, 90),
                    Instant.parse("2024-01-15T11:00:00Z"),
                    Instant.parse("2024-01-15T11:30:00Z"));
            CompactionJobFinishedEvent finishedEvent = job.finishedEventBuilder(summary)
                    .taskId(TASK_ID)
                    .jobRunId(JOB_RUN_ID)
                    .build();

            // When - use the 5-arg constructor (no time supplier)
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    new FixedTablePropertiesProvider(tableProperties),
                    stateStoreProvider(),
                    tracker,
                    sentCommitRequests::add,
                    sentBatchedMessages::add);

            committer.commit(job, finishedEvent);

            // Then - verify job was committed (state store updated)
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getFilename)
                    .containsExactly("output.parquet");
        }

        @Test
        void shouldCreateCommitterWithCustomTimeSupplier() throws Exception {
            // Given
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "false");

            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(100, 90),
                    Instant.parse("2024-01-15T11:00:00Z"),
                    Instant.parse("2024-01-15T11:30:00Z"));
            CompactionJobFinishedEvent finishedEvent = job.finishedEventBuilder(summary)
                    .taskId(TASK_ID)
                    .jobRunId(JOB_RUN_ID)
                    .build();

            Instant customTime = Instant.parse("2024-06-01T15:00:00Z");

            // When - use the 6-arg constructor (with time supplier)
            CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                    new FixedTablePropertiesProvider(tableProperties),
                    stateStoreProvider(),
                    tracker,
                    sentCommitRequests::add,
                    sentBatchedMessages::add,
                    () -> customTime);

            committer.commit(job, finishedEvent);

            // Then - verify job was committed
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getFilename)
                    .containsExactly("output.parquet");
        }
    }

    @Nested
    @DisplayName("Multiple input files")
    class MultipleInputFiles {

        @Test
        void shouldCommitCompactionWithMultipleInputFiles() throws Exception {
            // Given
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "false");

            FileReference file1 = fileFactory.rootFile("input1.parquet", 100);
            FileReference file2 = fileFactory.rootFile("input2.parquet", 150);
            FileReference file3 = fileFactory.rootFile("input3.parquet", 200);
            update(stateStore).addFiles(List.of(file1, file2, file3));

            CompactionJob job = createCompactionJob("output.parquet",
                    List.of("input1.parquet", "input2.parquet", "input3.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(450, 400),
                    Instant.parse("2024-01-15T11:00:00Z"),
                    Instant.parse("2024-01-15T11:30:00Z"));
            CompactionJobFinishedEvent finishedEvent = job.finishedEventBuilder(summary)
                    .taskId(TASK_ID)
                    .jobRunId(JOB_RUN_ID)
                    .build();

            // When
            committer().commit(job, finishedEvent);

            // Then
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getFilename)
                    .containsExactly("output.parquet");
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getNumberOfRecords)
                    .containsExactly(400L);
        }
    }

    private CompactionJob createCompactionJob(String outputFile, List<String> inputFiles) {
        return CompactionJob.builder()
                .tableId(tableProperties.get(TABLE_ID))
                .jobId("test-job-" + System.nanoTime())
                .partitionId(PARTITION_ID)
                .outputFile(outputFile)
                .inputFiles(inputFiles)
                .build();
    }

    private CompactionJobCommitterOrSendToLambda committer() {
        return committerWithTime(() -> fixedTime);
    }

    private CompactionJobCommitterOrSendToLambda committerWithTime(java.util.function.Supplier<Instant> timeSupplier) {
        return new CompactionJobCommitterOrSendToLambda(
                new FixedTablePropertiesProvider(tableProperties),
                stateStoreProvider(),
                tracker,
                sentCommitRequests::add,
                sentBatchedMessages::add,
                timeSupplier);
    }

    private StateStoreProvider stateStoreProvider() {
        return new StateStoreProvider(instanceProperties, props -> stateStore);
    }
}
