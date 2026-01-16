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
package sleeper.compaction.core.job.dispatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.core.tracker.compaction.job.update.CompactionJobCreatedEvent;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_SEND_RETRY_DELAY_SECS;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_SEND_TIMEOUT_SECS;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

public class CompactionJobDispatcherClaudeTest {

    private static final Instant DEFAULT_FILE_UPDATE_TIME = FilesReportTestHelper.DEFAULT_UPDATE_TIME;
    private static final Instant DEFAULT_TIME = Instant.parse("2024-06-01T10:00:00Z");
    private static final String DATA_BUCKET_NAME = "test-data-bucket";

    private final Schema schema = schemaWithKey("key", new StringType());
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final InMemoryTransactionLogs transactionLogs = new InMemoryTransactionLogs();
    private final List<CompactionJob> sentJobs = new ArrayList<>();
    private final List<CompactionJobCreatedEvent> trackedCreatedEvents = new ArrayList<>();
    private final List<ReturnedRequest> returnedRequests = new ArrayList<>();
    private final List<CompactionJobDispatchRequest> deadLetterRequests = new ArrayList<>();

    private PartitionTree partitions;
    private FileReferenceFactory fileFactory;
    private TableProperties tableProperties;
    private StateStore stateStore;
    private Instant currentTime = DEFAULT_TIME;

    @BeforeEach
    void setUp() {
        instanceProperties.set(DATA_BUCKET, DATA_BUCKET_NAME);
        partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
        fileFactory = FileReferenceFactory.fromUpdatedAt(partitions, DEFAULT_FILE_UPDATE_TIME);
        tableProperties = createTestTableProperties(instanceProperties, schema);
        stateStore = createStateStore(tableProperties);
    }

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Test
        void shouldCreateDispatcherWithAllDependencies() {
            // When
            CompactionJobDispatcher dispatcher = createDispatcher();

            // Then
            assertThat(dispatcher).isNotNull();
        }
    }

    @Nested
    @DisplayName("dispatch - files assigned")
    class DispatchFilesAssigned {

        @Test
        void shouldSendJobsWhenFilesAreAssigned() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));
            update(stateStore).assignJobId("job-1", "root", List.of("file1.parquet", "file2.parquet"));

            CompactionJob job = createJob("job-1", List.of("file1.parquet", "file2.parquet"));
            CompactionJobDispatchRequest request = createRequestForBatch(List.of(job));

            CompactionJobDispatcher dispatcher = createDispatcher(List.of(job));

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(sentJobs).containsExactly(job);
            assertThat(returnedRequests).isEmpty();
            assertThat(deadLetterRequests).isEmpty();
        }

        @Test
        void shouldTrackJobCreatedWhenSent() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));
            update(stateStore).assignJobId("job-1", "root", List.of("file1.parquet", "file2.parquet"));

            CompactionJob job = createJob("job-1", List.of("file1.parquet", "file2.parquet"));
            CompactionJobDispatchRequest request = createRequestForBatch(List.of(job));

            CompactionJobDispatcher dispatcher = createDispatcher(List.of(job));

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(trackedCreatedEvents).hasSize(1);
            assertThat(trackedCreatedEvents.get(0).getJobId()).isEqualTo("job-1");
        }

        @Test
        void shouldSendMultipleJobsInSingleBatch() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            FileReference file3 = fileFactory.rootFile("file3.parquet", 300L);
            FileReference file4 = fileFactory.rootFile("file4.parquet", 400L);
            update(stateStore).addFiles(List.of(file1, file2, file3, file4));
            update(stateStore).assignJobId("job-1", "root", List.of("file1.parquet", "file2.parquet"));
            update(stateStore).assignJobId("job-2", "root", List.of("file3.parquet", "file4.parquet"));

            CompactionJob job1 = createJob("job-1", List.of("file1.parquet", "file2.parquet"));
            CompactionJob job2 = createJob("job-2", List.of("file3.parquet", "file4.parquet"));
            CompactionJobDispatchRequest request = createRequestForBatch(List.of(job1, job2));

            CompactionJobDispatcher dispatcher = createDispatcherWithBatchSize(List.of(job1, job2), 10);

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(sentJobs).containsExactly(job1, job2);
            assertThat(trackedCreatedEvents).hasSize(2);
        }

        @Test
        void shouldSplitJobsIntoBatchesWhenExceedingSendBatchSize() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            FileReference file3 = fileFactory.rootFile("file3.parquet", 300L);
            FileReference file4 = fileFactory.rootFile("file4.parquet", 400L);
            update(stateStore).addFiles(List.of(file1, file2, file3, file4));
            update(stateStore).assignJobId("job-1", "root", List.of("file1.parquet", "file2.parquet"));
            update(stateStore).assignJobId("job-2", "root", List.of("file3.parquet", "file4.parquet"));

            CompactionJob job1 = createJob("job-1", List.of("file1.parquet", "file2.parquet"));
            CompactionJob job2 = createJob("job-2", List.of("file3.parquet", "file4.parquet"));
            CompactionJobDispatchRequest request = createRequestForBatch(List.of(job1, job2));

            // Track send batch calls
            List<List<CompactionJob>> sendBatches = new ArrayList<>();
            CompactionJobDispatcher dispatcher = createDispatcherWithCustomSendJobs(
                    List.of(job1, job2), 1, jobs -> {
                        sendBatches.add(new ArrayList<>(jobs));
                        sentJobs.addAll(jobs);
                    });

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(sendBatches).hasSize(2);
            assertThat(sendBatches.get(0)).containsExactly(job1);
            assertThat(sendBatches.get(1)).containsExactly(job2);
        }
    }

    @Nested
    @DisplayName("dispatch - files not assigned")
    class DispatchFilesNotAssigned {

        @Test
        void shouldReturnToQueueWhenFilesNotAssigned() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));
            // Files not assigned to any job

            CompactionJob job = createJob("job-1", List.of("file1.parquet", "file2.parquet"));
            CompactionJobDispatchRequest request = createRequestForBatch(List.of(job));

            tableProperties.setNumber(COMPACTION_JOB_SEND_TIMEOUT_SECS, 300);
            tableProperties.setNumber(COMPACTION_JOB_SEND_RETRY_DELAY_SECS, 10);
            CompactionJobDispatcher dispatcher = createDispatcher(List.of(job));

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(sentJobs).isEmpty();
            assertThat(returnedRequests).hasSize(1);
            assertThat(returnedRequests.get(0).request()).isEqualTo(request);
            assertThat(returnedRequests.get(0).delaySeconds()).isEqualTo(10);
            assertThat(deadLetterRequests).isEmpty();
        }

        @Test
        void shouldSendToDeadLetterWhenExpired() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));
            // Files not assigned to any job

            CompactionJob job = createJob("job-1", List.of("file1.parquet", "file2.parquet"));
            Instant createTime = Instant.parse("2024-06-01T09:00:00Z");
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-1", createTime);

            tableProperties.setNumber(COMPACTION_JOB_SEND_TIMEOUT_SECS, 300); // 5 minutes
            // Current time is 1 hour after create time (expired)
            currentTime = Instant.parse("2024-06-01T10:00:00Z");

            CompactionJobDispatcher dispatcher = createDispatcher(List.of(job));

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(sentJobs).isEmpty();
            assertThat(returnedRequests).isEmpty();
            assertThat(deadLetterRequests).containsExactly(request);
        }

        @Test
        void shouldReturnToQueueWhenNotExpired() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));
            // Files not assigned to any job

            CompactionJob job = createJob("job-1", List.of("file1.parquet", "file2.parquet"));
            Instant createTime = Instant.parse("2024-06-01T09:55:00Z");
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-1", createTime);

            tableProperties.setNumber(COMPACTION_JOB_SEND_TIMEOUT_SECS, 300); // 5 minutes
            tableProperties.setNumber(COMPACTION_JOB_SEND_RETRY_DELAY_SECS, 20);
            // Current time is 5 minutes after create time (not expired yet)
            currentTime = Instant.parse("2024-06-01T10:00:00Z");

            CompactionJobDispatcher dispatcher = createDispatcher(List.of(job));

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(sentJobs).isEmpty();
            assertThat(returnedRequests).hasSize(1);
            assertThat(returnedRequests.get(0).delaySeconds()).isEqualTo(20);
            assertThat(deadLetterRequests).isEmpty();
        }

        @Test
        void shouldExpireAtExactTimeout() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            update(stateStore).addFiles(List.of(file1));

            CompactionJob job = createJob("job-1", List.of("file1.parquet"));
            Instant createTime = Instant.parse("2024-06-01T09:55:00Z");
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-1", createTime);

            tableProperties.setNumber(COMPACTION_JOB_SEND_TIMEOUT_SECS, 300); // 5 minutes
            // Current time is exactly 5 minutes and 1 second after create time (expired)
            currentTime = Instant.parse("2024-06-01T10:00:01Z");

            CompactionJobDispatcher dispatcher = createDispatcher(List.of(job));

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(deadLetterRequests).containsExactly(request);
        }
    }

    @Nested
    @DisplayName("dispatch - state store exceptions")
    class DispatchStateStoreExceptions {

        @Test
        void shouldSendToDeadLetterWhenFileNotFound() throws Exception {
            // Given - files exist but job references a non-existent file
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            update(stateStore).addFiles(List.of(file1));
            update(stateStore).assignJobId("job-1", "root", List.of("file1.parquet"));

            // Job references a file that doesn't exist
            CompactionJob job = createJob("job-1", List.of("file1.parquet", "non-existent.parquet"));
            CompactionJobDispatchRequest request = createRequestForBatch(List.of(job));

            CompactionJobDispatcher dispatcher = createDispatcher(List.of(job));

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(sentJobs).isEmpty();
            assertThat(returnedRequests).isEmpty();
            assertThat(deadLetterRequests).containsExactly(request);
        }

        @Test
        void shouldSendToDeadLetterWhenFileAssignedToDifferentJob() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));
            // Files assigned to a different job
            update(stateStore).assignJobId("other-job", "root", List.of("file1.parquet", "file2.parquet"));

            CompactionJob job = createJob("job-1", List.of("file1.parquet", "file2.parquet"));
            CompactionJobDispatchRequest request = createRequestForBatch(List.of(job));

            CompactionJobDispatcher dispatcher = createDispatcher(List.of(job));

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(sentJobs).isEmpty();
            assertThat(returnedRequests).isEmpty();
            assertThat(deadLetterRequests).containsExactly(request);
        }
    }

    @Nested
    @DisplayName("dispatch - partial assignment")
    class DispatchPartialAssignment {

        @Test
        void shouldReturnToQueueWhenOnlyOneFileAssigned() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));
            // Only first file assigned
            update(stateStore).assignJobId("job-1", "root", List.of("file1.parquet"));

            CompactionJob job = createJob("job-1", List.of("file1.parquet", "file2.parquet"));
            CompactionJobDispatchRequest request = createRequestForBatch(List.of(job));

            tableProperties.setNumber(COMPACTION_JOB_SEND_TIMEOUT_SECS, 300);
            tableProperties.setNumber(COMPACTION_JOB_SEND_RETRY_DELAY_SECS, 15);
            CompactionJobDispatcher dispatcher = createDispatcher(List.of(job));

            // When
            dispatcher.dispatch(request);

            // Then
            assertThat(sentJobs).isEmpty();
            assertThat(returnedRequests).hasSize(1);
            assertThat(deadLetterRequests).isEmpty();
        }
    }

    private StateStore createStateStore(TableProperties tableProperties) {
        StateStore store = InMemoryTransactionLogStateStore.create(tableProperties, transactionLogs);
        update(store).initialise(partitions.getAllPartitions());
        store.fixFileUpdateTime(DEFAULT_FILE_UPDATE_TIME);
        return store;
    }

    private CompactionJobDispatcher createDispatcher() {
        return createDispatcher(List.of());
    }

    private CompactionJobDispatcher createDispatcher(List<CompactionJob> jobsToRead) {
        return createDispatcherWithBatchSize(jobsToRead, 10);
    }

    private CompactionJobDispatcher createDispatcherWithBatchSize(List<CompactionJob> jobsToRead, int sendBatchSize) {
        return createDispatcherWithCustomSendJobs(jobsToRead, sendBatchSize, sentJobs::addAll);
    }

    private CompactionJobDispatcher createDispatcherWithCustomSendJobs(
            List<CompactionJob> jobsToRead, int sendBatchSize, CompactionJobDispatcher.SendJobs sendJobs) {
        FixedTablePropertiesProvider tablePropertiesProvider = new FixedTablePropertiesProvider(tableProperties);
        StateStoreProvider stateStoreProvider = new StateStoreProvider(1, props -> stateStore);

        CompactionJobTracker tracker = new CompactionJobTracker() {
            @Override
            public void jobCreated(CompactionJobCreatedEvent event) {
                trackedCreatedEvents.add(event);
            }
        };

        CompactionJobDispatcher.ReadBatch readBatch = (bucketName, key) -> jobsToRead;

        CompactionJobDispatcher.ReturnRequestToPendingQueue returnToPendingQueue = (request, delaySeconds) -> {
            returnedRequests.add(new ReturnedRequest(request, delaySeconds));
        };

        CompactionJobDispatcher.SendDeadLetter sendDeadLetter = deadLetterRequests::add;

        return new CompactionJobDispatcher(
                instanceProperties,
                tablePropertiesProvider,
                stateStoreProvider,
                tracker,
                readBatch,
                sendJobs,
                sendBatchSize,
                returnToPendingQueue,
                sendDeadLetter,
                () -> currentTime);
    }

    private CompactionJob createJob(String jobId, List<String> inputFiles) {
        return CompactionJob.builder()
                .tableId(tableProperties.get(TABLE_ID))
                .jobId(jobId)
                .inputFiles(inputFiles)
                .outputFile("output-" + jobId + ".parquet")
                .partitionId("root")
                .build();
    }

    private CompactionJobDispatchRequest createRequestForBatch(List<CompactionJob> jobs) {
        return CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-1", currentTime);
    }

    private record ReturnedRequest(CompactionJobDispatchRequest request, int delaySeconds) {
    }
}
