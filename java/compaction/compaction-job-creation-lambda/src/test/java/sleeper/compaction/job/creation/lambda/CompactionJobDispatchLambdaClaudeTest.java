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
package sleeper.compaction.job.creation.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobSerDe;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatcher;
import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.testutils.FixedStateStoreProvider;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.statestore.StateStoreFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_JOB_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_DLQ_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_QUEUE_URL;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.AssignJobIdRequest.assignJobOnPartitionToFiles;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

class CompactionJobDispatchLambdaClaudeTest {
    private static final Instant DEFAULT_FILE_UPDATE_TIME = FilesReportTestHelper.DEFAULT_UPDATE_TIME;
    private static final Instant DEFAULT_CREATE_TIME = Instant.parse("2024-01-01T10:00:00Z");

    private final Schema schema = schemaWithKey("key", new StringType());
    private final PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
    private final InMemoryTransactionLogs transactionLogs = new InMemoryTransactionLogs();
    private final FileReferenceFactory fileFactory = FileReferenceFactory.fromUpdatedAt(partitions, DEFAULT_FILE_UPDATE_TIME);
    private final InMemoryCompactionJobTracker jobTracker = new InMemoryCompactionJobTracker();
    private final CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();
    private final List<CompactionJob> sentJobs = new ArrayList<>();
    private final List<CompactionJobDispatchRequest> returnedRequests = new ArrayList<>();
    private final List<Integer> returnedDelays = new ArrayList<>();
    private final List<CompactionJobDispatchRequest> deadLetters = new ArrayList<>();

    @BeforeEach
    void setUp() {
        instanceProperties.set(COMPACTION_JOB_QUEUE_URL, "test-compaction-job-queue");
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL, "test-pending-queue");
        instanceProperties.set(COMPACTION_PENDING_DLQ_URL, "test-dlq");
        update(stateStore()).initialise(partitions.getAllPartitions());
    }

    @Test
    void shouldDispatchJobWhenFilesAreAssigned() {
        // Given
        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);
        CompactionJob job = createCompactionJob(file.getFilename());
        assignJobToFile(job, file);
        CompactionJobDispatchRequest request = createRequest(job);

        // When
        dispatcher().dispatch(request);

        // Then
        assertThat(sentJobs).containsExactly(job);
        assertThat(returnedRequests).isEmpty();
        assertThat(deadLetters).isEmpty();
    }

    @Test
    void shouldReturnRequestToQueueWhenFilesNotYetAssigned() {
        // Given
        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);
        CompactionJob job = createCompactionJob(file.getFilename());
        // Deliberately NOT assigning the job to the file
        CompactionJobDispatchRequest request = createRequest(job);

        // When
        dispatcher().dispatch(request);

        // Then
        assertThat(sentJobs).isEmpty();
        assertThat(returnedRequests).containsExactly(request);
        assertThat(returnedDelays).containsExactly(30); // Default retry delay
        assertThat(deadLetters).isEmpty();
    }

    @Test
    void shouldSendToDeadLetterQueueWhenRequestExpired() {
        // Given
        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);
        CompactionJob job = createCompactionJob(file.getFilename());
        // Create a request that is already expired (created in the past)
        CompactionJobDispatchRequest request = createRequestAtTime(job, Instant.parse("2023-01-01T10:00:00Z"));
        // Set current time to after expiry
        Instant currentTime = Instant.parse("2024-01-01T10:00:00Z");

        // When
        dispatcher(currentTime).dispatch(request);

        // Then
        assertThat(sentJobs).isEmpty();
        assertThat(returnedRequests).isEmpty();
        assertThat(deadLetters).containsExactly(request);
    }

    @Test
    void shouldDispatchMultipleJobsInBatch() {
        // Given
        FileReference file1 = fileFactory.rootFile("test1.parquet", 100);
        FileReference file2 = fileFactory.rootFile("test2.parquet", 200);
        update(stateStore()).addFiles(List.of(file1, file2));
        CompactionJob job1 = createCompactionJobWithId("job-1", file1.getFilename());
        CompactionJob job2 = createCompactionJobWithId("job-2", file2.getFilename());
        assignJobToFile(job1, file1);
        assignJobToFile(job2, file2);
        List<CompactionJob> batch = List.of(job1, job2);
        CompactionJobDispatchRequest request = createRequestForBatch(batch);

        // When
        dispatcher().dispatch(request);

        // Then
        assertThat(sentJobs).containsExactly(job1, job2);
        assertThat(returnedRequests).isEmpty();
        assertThat(deadLetters).isEmpty();
    }

    @Test
    void shouldTrackJobCreatedEventWhenJobDispatched() {
        // Given
        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);
        CompactionJob job = createCompactionJob(file.getFilename());
        assignJobToFile(job, file);
        CompactionJobDispatchRequest request = createRequest(job);

        // When
        dispatcher().dispatch(request);

        // Then
        assertThat(jobTracker.getJob(job.getId()))
                .isPresent()
                .hasValueSatisfying(status ->
                    assertThat(status.getJobId()).isEqualTo(job.getId()));
    }

    @Test
    void shouldCreateDispatcherWithCorrectConfiguration() {
        // Given
        AmazonS3 s3 = mock(AmazonS3.class);
        AmazonDynamoDB dynamoDB = mock(AmazonDynamoDB.class);
        AmazonSQS sqs = mock(AmazonSQS.class);
        Configuration conf = new Configuration();
        Instant fixedTime = Instant.parse("2024-01-01T12:00:00Z");

        // When
        CompactionJobDispatcher dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3, dynamoDB, sqs, conf, instanceProperties, () -> fixedTime);

        // Then
        assertThat(dispatcher).isNotNull();
    }

    @Test
    void shouldSendJobsToSqsQueue() {
        // Given
        AmazonSQS sqs = mock(AmazonSQS.class);
        when(sqs.sendMessageBatch(any(SendMessageBatchRequest.class)))
                .thenReturn(new SendMessageBatchResult().withFailed(Collections.emptyList()));

        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);
        CompactionJob job = createCompactionJob(file.getFilename());
        assignJobToFile(job, file);
        CompactionJobDispatchRequest request = createRequest(job);

        // When
        dispatcherWithSqs(sqs).dispatch(request);

        // Then
        ArgumentCaptor<SendMessageBatchRequest> captor = ArgumentCaptor.forClass(SendMessageBatchRequest.class);
        verify(sqs).sendMessageBatch(captor.capture());
        SendMessageBatchRequest batchRequest = captor.getValue();
        assertThat(batchRequest.getQueueUrl()).isEqualTo("test-compaction-job-queue");
        assertThat(batchRequest.getEntries()).hasSize(1);
        assertThat(batchRequest.getEntries().get(0).getId()).isEqualTo(job.getId());
    }

    @Test
    void shouldReturnRequestToPendingQueueWithDelay() {
        // Given
        AmazonSQS sqs = mock(AmazonSQS.class);
        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);
        CompactionJob job = createCompactionJob(file.getFilename());
        // Not assigned - will return to queue
        CompactionJobDispatchRequest request = createRequest(job);

        // When
        dispatcherWithSqs(sqs).dispatch(request);

        // Then
        ArgumentCaptor<SendMessageRequest> captor = ArgumentCaptor.forClass(SendMessageRequest.class);
        verify(sqs).sendMessage(captor.capture());
        SendMessageRequest messageRequest = captor.getValue();
        assertThat(messageRequest.getQueueUrl()).isEqualTo("test-pending-queue");
        assertThat(messageRequest.getDelaySeconds()).isEqualTo(30);
    }

    @Test
    void shouldSendExpiredRequestToDeadLetterQueue() {
        // Given
        AmazonSQS sqs = mock(AmazonSQS.class);
        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);
        CompactionJob job = createCompactionJob(file.getFilename());
        // Not assigned and expired
        CompactionJobDispatchRequest request = createRequestAtTime(job, Instant.parse("2023-01-01T10:00:00Z"));
        Instant currentTime = Instant.parse("2024-01-01T10:00:00Z");

        // When
        dispatcherWithSqs(sqs, currentTime).dispatch(request);

        // Then
        ArgumentCaptor<SendMessageRequest> captor = ArgumentCaptor.forClass(SendMessageRequest.class);
        verify(sqs).sendMessage(captor.capture());
        SendMessageRequest messageRequest = captor.getValue();
        assertThat(messageRequest.getQueueUrl()).isEqualTo("test-dlq");
    }

    @Test
    void shouldSendJobsInBatchesOfTen() {
        // Given
        AmazonSQS sqs = mock(AmazonSQS.class);
        when(sqs.sendMessageBatch(any(SendMessageBatchRequest.class)))
                .thenReturn(new SendMessageBatchResult().withFailed(Collections.emptyList()));

        List<FileReference> files = new ArrayList<>();
        List<CompactionJob> jobs = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            FileReference file = fileFactory.rootFile("test" + i + ".parquet", 100);
            files.add(file);
            CompactionJob job = createCompactionJobWithId("job-" + i, file.getFilename());
            jobs.add(job);
        }
        update(stateStore()).addFiles(files);
        for (int i = 0; i < 15; i++) {
            assignJobToFile(jobs.get(i), files.get(i));
        }
        CompactionJobDispatchRequest request = createRequestForBatch(jobs);

        // When
        dispatcherWithSqs(sqs).dispatch(request);

        // Then - should be sent in 2 batches (10 + 5)
        verify(sqs, times(2)).sendMessageBatch(any(SendMessageBatchRequest.class));
    }

    private StateStore stateStore() {
        StateStore stateStore = StateStoreFactory.forCommitterProcess(true, tableProperties,
                transactionLogs.stateStoreBuilder(tableProperties.getStatus(), schema))
                .build();
        stateStore.fixFileUpdateTime(DEFAULT_FILE_UPDATE_TIME);
        return stateStore;
    }

    private CompactionJobDispatcher dispatcher() {
        return dispatcher(DEFAULT_CREATE_TIME);
    }

    private CompactionJobDispatcher dispatcher(Instant currentTime) {
        TablePropertiesProvider tablePropertiesProvider = new FixedTablePropertiesProvider(tableProperties);
        StateStoreProvider stateStoreProvider = new FixedStateStoreProvider(tableProperties, stateStore());
        return new CompactionJobDispatcher(
                instanceProperties,
                tablePropertiesProvider,
                stateStoreProvider,
                jobTracker,
                (bucketName, key) -> compactionJobSerDe.batchFromJson(batchStore.get(key)),
                sentJobs::addAll,
                10,
                (request, delaySeconds) -> {
                    returnedRequests.add(request);
                    returnedDelays.add(delaySeconds);
                },
                deadLetters::add,
                () -> currentTime);
    }

    private CompactionJobDispatcher dispatcherWithSqs(AmazonSQS sqs) {
        return dispatcherWithSqs(sqs, DEFAULT_CREATE_TIME);
    }

    private CompactionJobDispatcher dispatcherWithSqs(AmazonSQS sqs, Instant currentTime) {
        TablePropertiesProvider tablePropertiesProvider = new FixedTablePropertiesProvider(tableProperties);
        StateStoreProvider stateStoreProvider = new FixedStateStoreProvider(tableProperties, stateStore());
        return new CompactionJobDispatcher(
                instanceProperties,
                tablePropertiesProvider,
                stateStoreProvider,
                jobTracker,
                (bucketName, key) -> compactionJobSerDe.batchFromJson(batchStore.get(key)),
                createSendJobs(sqs),
                10,
                createReturnToQueue(sqs),
                createSendDeadLetter(sqs),
                () -> currentTime);
    }

    private CompactionJobDispatcher.SendJobs createSendJobs(AmazonSQS sqs) {
        return jobs -> {
            SendMessageBatchResult result = sqs.sendMessageBatch(new SendMessageBatchRequest()
                    .withQueueUrl(instanceProperties.get(COMPACTION_JOB_QUEUE_URL))
                    .withEntries(jobs.stream()
                            .map(job -> new SendMessageBatchRequestEntry(job.getId(), compactionJobSerDe.toJson(job)))
                            .toList()));
            if (!result.getFailed().isEmpty()) {
                throw new RuntimeException("Failed sending halfway through batch");
            }
        };
    }

    private CompactionJobDispatcher.ReturnRequestToPendingQueue createReturnToQueue(AmazonSQS sqs) {
        var serDe = new sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequestSerDe();
        return (request, delaySeconds) -> sqs.sendMessage(new SendMessageRequest()
                .withQueueUrl(instanceProperties.get(COMPACTION_PENDING_QUEUE_URL))
                .withMessageBody(serDe.toJson(request))
                .withDelaySeconds(delaySeconds));
    }

    private CompactionJobDispatcher.SendDeadLetter createSendDeadLetter(AmazonSQS sqs) {
        var serDe = new sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequestSerDe();
        return request -> sqs.sendMessage(new SendMessageRequest()
                .withQueueUrl(instanceProperties.get(COMPACTION_PENDING_DLQ_URL))
                .withMessageBody(serDe.toJson(request)));
    }

    private final java.util.Map<String, String> batchStore = new java.util.HashMap<>();

    private CompactionJob createCompactionJob(String inputFile) {
        return createCompactionJobWithId(java.util.UUID.randomUUID().toString(), inputFile);
    }

    private CompactionJob createCompactionJobWithId(String jobId, String inputFile) {
        return CompactionJob.builder()
                .tableId(tableProperties.get(TABLE_ID))
                .jobId(jobId)
                .inputFiles(List.of(inputFile))
                .outputFile("output-" + jobId + ".parquet")
                .partitionId("root")
                .build();
    }

    private void assignJobToFile(CompactionJob job, FileReference file) {
        update(stateStore()).assignJobIds(List.of(
                assignJobOnPartitionToFiles(job.getId(), file.getPartitionId(), List.of(file.getFilename()))));
    }

    private CompactionJobDispatchRequest createRequest(CompactionJob job) {
        return createRequestAtTime(job, DEFAULT_CREATE_TIME);
    }

    private CompactionJobDispatchRequest createRequestAtTime(CompactionJob job, Instant createTime) {
        return createRequestForBatchAtTime(List.of(job), createTime);
    }

    private CompactionJobDispatchRequest createRequestForBatch(List<CompactionJob> batch) {
        return createRequestForBatchAtTime(batch, DEFAULT_CREATE_TIME);
    }

    private CompactionJobDispatchRequest createRequestForBatchAtTime(List<CompactionJob> batch, Instant createTime) {
        String batchId = java.util.UUID.randomUUID().toString();
        String batchKey = tableProperties.get(TABLE_ID) + "/compactions/" + batchId + ".json";
        batchStore.put(batchKey, compactionJobSerDe.toJson(batch));
        return CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties, batchId, createTime);
    }
}
