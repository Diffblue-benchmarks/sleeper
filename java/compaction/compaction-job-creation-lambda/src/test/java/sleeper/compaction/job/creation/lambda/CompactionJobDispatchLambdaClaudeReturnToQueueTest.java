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

import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.QueueAttributeName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobSerDe;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatcher;
import sleeper.compaction.tracker.job.DynamoDBCompactionJobTrackerCreator;
import sleeper.configuration.properties.S3InstanceProperties;
import sleeper.configuration.properties.S3TableProperties;
import sleeper.configuration.table.index.DynamoDBTableIndexCreator;
import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.statestore.StateStore;
import sleeper.localstack.test.LocalStackTestBase;
import sleeper.statestore.StateStoreFactory;
import sleeper.statestore.transactionlog.TransactionLogStateStoreCreator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_JOB_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_DLQ_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.CONFIG_BUCKET;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TRACKER_ENABLED;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

/**
 * Integration tests for the returnToQueue lambda method (lines 114-120) of CompactionJobDispatchLambda.
 * These tests verify that when files are not yet assigned to jobs, the dispatcher returns the request
 * to the pending queue with an appropriate delay.
 */
public class CompactionJobDispatchLambdaClaudeReturnToQueueTest extends LocalStackTestBase {
    private static final Instant DEFAULT_FILE_UPDATE_TIME = FilesReportTestHelper.DEFAULT_UPDATE_TIME;

    private final Schema schema = schemaWithKey("key", new StringType());
    private final PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
    private final CompactionJobSerDe jobSerDe = new CompactionJobSerDe();
    private final FileReferenceFactory fileFactory = FileReferenceFactory.fromUpdatedAt(partitions, DEFAULT_FILE_UPDATE_TIME);

    @BeforeEach
    void setUp() {
        // Create S3 buckets
        createBucket(instanceProperties.get(CONFIG_BUCKET));
        createBucket(instanceProperties.get(DATA_BUCKET));

        // Create SQS queues
        instanceProperties.set(COMPACTION_JOB_QUEUE_URL,
                sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl());
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL,
                sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl());
        instanceProperties.set(COMPACTION_PENDING_DLQ_URL,
                sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl());

        // Enable compaction tracker
        instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");

        // Save instance properties to S3
        S3InstanceProperties.saveToS3(s3Client, instanceProperties);

        // Create DynamoDB tables
        DynamoDBTableIndexCreator.create(dynamoClient, instanceProperties);
        new TransactionLogStateStoreCreator(instanceProperties, dynamoClient).create();
        DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);

        // Create and save table properties
        S3TableProperties.createStore(instanceProperties, s3Client, dynamoClient).createTable(tableProperties);

        // Initialize state store with partitions
        update(stateStore()).initialise(partitions.getAllPartitions());
    }

    @Test
    void shouldReturnRequestToPendingQueueWhenFilesNotAssigned() {
        // Given - create a batch with jobs but do NOT assign files to the jobs
        CompactionJob job = createCompactionJob("job-1", "file-1.parquet");
        List<CompactionJob> batch = List.of(job);
        // Use current time so the request is NOT expired
        Instant requestTime = Instant.now();
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "return-to-queue-batch", requestTime);

        // Upload the batch to S3
        String batchJson = jobSerDe.toJson(batch);
        s3Client.putObject(instanceProperties.get(DATA_BUCKET), request.getBatchKey(), batchJson);

        // Add file to state store but do NOT assign it to the job
        FileReference file = fileFactory.rootFile("file-1.parquet", 100);
        update(stateStore()).addFile(file);
        // Note: NOT calling assignJobId - this triggers the returnToQueue path

        // When - dispatch the request
        CompactionJobDispatcher dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf,
                instanceProperties, Instant::now);

        dispatcher.dispatch(request);

        // Then - verify request was returned to the pending queue (not to compaction job queue)
        // Note: Messages sent with a delay won't be immediately visible, so we check the queue attributes
        // to confirm the message count includes delayed messages
        int pendingMessageCount = getMessageCount(instanceProperties.get(COMPACTION_PENDING_QUEUE_URL));
        int jobMessageCount = getMessageCount(instanceProperties.get(COMPACTION_JOB_QUEUE_URL));

        // The request should be in pending queue (including delayed), not job queue
        assertThat(pendingMessageCount).isEqualTo(1);
        assertThat(jobMessageCount).isEqualTo(0);
    }

    @Test
    void shouldReturnRequestToPendingQueueForMultipleJobsWithUnassignedFiles() {
        // Given - create a batch with multiple jobs, none with assigned files
        CompactionJob job1 = createCompactionJob("job-2", "file-2.parquet");
        CompactionJob job2 = createCompactionJob("job-3", "file-3.parquet");
        List<CompactionJob> batch = List.of(job1, job2);
        Instant requestTime = Instant.now();
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "multi-job-return-batch", requestTime);

        // Upload the batch to S3
        String batchJson = jobSerDe.toJson(batch);
        s3Client.putObject(instanceProperties.get(DATA_BUCKET), request.getBatchKey(), batchJson);

        // Add files to state store but do NOT assign them to jobs
        FileReference file2 = fileFactory.rootFile("file-2.parquet", 100);
        FileReference file3 = fileFactory.rootFile("file-3.parquet", 150);
        update(stateStore()).addFiles(List.of(file2, file3));

        // When
        CompactionJobDispatcher dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf,
                instanceProperties, Instant::now);

        dispatcher.dispatch(request);

        // Then - request should be in pending queue (delayed messages)
        int pendingMessageCount = getMessageCount(instanceProperties.get(COMPACTION_PENDING_QUEUE_URL));
        assertThat(pendingMessageCount).isEqualTo(1);
    }

    @Test
    void shouldReturnRequestToPendingQueueWhenOnlyPartialFilesAssigned() {
        // Given - create a batch with multiple jobs, only some files assigned
        CompactionJob job1 = createCompactionJob("job-4", "file-4.parquet");
        CompactionJob job2 = createCompactionJob("job-5", "file-5.parquet");
        List<CompactionJob> batch = List.of(job1, job2);
        Instant requestTime = Instant.now();
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "partial-assign-batch", requestTime);

        // Upload the batch to S3
        String batchJson = jobSerDe.toJson(batch);
        s3Client.putObject(instanceProperties.get(DATA_BUCKET), request.getBatchKey(), batchJson);

        // Add files to state store
        FileReference file4 = fileFactory.rootFile("file-4.parquet", 100);
        FileReference file5 = fileFactory.rootFile("file-5.parquet", 150);
        update(stateStore()).addFiles(List.of(file4, file5));

        // Only assign one file, not both
        update(stateStore()).assignJobId("job-4", "root", List.of("file-4.parquet"));
        // Note: file-5.parquet is NOT assigned

        // When
        CompactionJobDispatcher dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf,
                instanceProperties, Instant::now);

        dispatcher.dispatch(request);

        // Then - request should be in pending queue since not all jobs have files assigned
        int pendingMessageCount = getMessageCount(instanceProperties.get(COMPACTION_PENDING_QUEUE_URL));
        assertThat(pendingMessageCount).isEqualTo(1);
    }

    @Test
    void shouldPreserveRequestBodyWhenReturningToPendingQueue() {
        // Given
        CompactionJob job = createCompactionJob("job-6", "file-6.parquet");
        List<CompactionJob> batch = List.of(job);
        Instant requestTime = Instant.now();
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "preserve-body-batch", requestTime);

        String batchJson = jobSerDe.toJson(batch);
        s3Client.putObject(instanceProperties.get(DATA_BUCKET), request.getBatchKey(), batchJson);

        FileReference file = fileFactory.rootFile("file-6.parquet", 100);
        update(stateStore()).addFile(file);
        // Not assigning file to job

        // When
        CompactionJobDispatcher dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf,
                instanceProperties, Instant::now);

        dispatcher.dispatch(request);

        // Then - verify a message was sent to pending queue
        // Note: Can't read actual message content since it has a delay, but we verify count
        int pendingMessageCount = getMessageCount(instanceProperties.get(COMPACTION_PENDING_QUEUE_URL));
        int jobMessageCount = getMessageCount(instanceProperties.get(COMPACTION_JOB_QUEUE_URL));

        assertThat(pendingMessageCount).isEqualTo(1);
        assertThat(jobMessageCount).isEqualTo(0);
    }

    private int getMessageCount(String queueUrl) {
        return sqsClient.getQueueAttributes(new GetQueueAttributesRequest()
                        .withQueueUrl(queueUrl)
                        .withAttributeNames(
                                QueueAttributeName.ApproximateNumberOfMessages,
                                QueueAttributeName.ApproximateNumberOfMessagesDelayed,
                                QueueAttributeName.ApproximateNumberOfMessagesNotVisible))
                .getAttributes()
                .entrySet().stream()
                .mapToInt(e -> Integer.parseInt(e.getValue()))
                .sum();
    }

    private StateStore stateStore() {
        return StateStoreFactory.createProvider(instanceProperties, s3Client, dynamoClient, hadoopConf)
                .getStateStore(tableProperties);
    }

    private CompactionJob createCompactionJob(String jobId, String inputFile) {
        return CompactionJob.builder()
                .tableId(tableProperties.get(TABLE_ID))
                .jobId(jobId)
                .inputFiles(List.of(inputFile))
                .outputFile("output-" + jobId + ".parquet")
                .partitionId("root")
                .build();
    }
}
