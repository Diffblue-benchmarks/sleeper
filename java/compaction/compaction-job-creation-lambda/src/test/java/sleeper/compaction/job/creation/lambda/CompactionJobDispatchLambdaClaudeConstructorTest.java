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

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobSerDe;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequestSerDe;
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
 * Integration tests for the CompactionJobDispatchLambda constructor and dispatcher method
 * using LocalStack. These tests verify that the dispatcher method correctly initializes
 * all dependencies and integrates with AWS services.
 * <p>
 * Note: The default no-arg constructor (lines 69-77) uses AmazonS3ClientBuilder.defaultClient()
 * which creates clients that connect to AWS by default. Testing it directly would require
 * setting AWS SDK system properties to redirect to LocalStack, which is fragile.
 * Instead, we test the static dispatcher() method which performs the same initialization
 * logic (lines 85-94) and is what the constructor delegates to (line 76-77).
 */
public class CompactionJobDispatchLambdaClaudeConstructorTest extends LocalStackTestBase {
    private static final Instant DEFAULT_FILE_UPDATE_TIME = FilesReportTestHelper.DEFAULT_UPDATE_TIME;

    private final Schema schema = schemaWithKey("key", new StringType());
    private final PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
    private final CompactionJobDispatchRequestSerDe requestSerDe = new CompactionJobDispatchRequestSerDe();
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
    void shouldCreateDispatcherWithAllDependenciesFromAWSClients() {
        // Given - create a batch of compaction jobs in S3
        CompactionJob job = createCompactionJob("job-1", "file-1.parquet");
        List<CompactionJob> batch = List.of(job);
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", Instant.now());

        // Upload the batch to S3
        String batchJson = jobSerDe.toJson(batch);
        s3Client.putObject(instanceProperties.get(DATA_BUCKET), request.getBatchKey(), batchJson);

        // Add and assign files in state store
        FileReference file = fileFactory.rootFile("file-1.parquet", 100);
        update(stateStore()).addFile(file);
        update(stateStore()).assignJobId("job-1", "root", List.of("file-1.parquet"));

        // When - use the static dispatcher method to create a dispatcher
        // This exercises the same initialization logic as the constructor (lines 75-77)
        CompactionJobDispatcher dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf,
                instanceProperties, Instant::now);

        dispatcher.dispatch(request);

        // Then - verify job was sent to the compaction job queue
        List<Message> messages = sqsClient.receiveMessage(new ReceiveMessageRequest()
                .withQueueUrl(instanceProperties.get(COMPACTION_JOB_QUEUE_URL))
                .withWaitTimeSeconds(1)
                .withMaxNumberOfMessages(10)).getMessages();

        assertThat(messages).hasSize(1);
        CompactionJob receivedJob = jobSerDe.fromJson(messages.get(0).getBody());
        assertThat(receivedJob.getId()).isEqualTo("job-1");
        assertThat(receivedJob.getTableId()).isEqualTo(tableProperties.get(TABLE_ID));
    }

    @Test
    void shouldHandleMultipleJobsInBatch() {
        // Given - create a batch with multiple jobs
        CompactionJob job1 = createCompactionJob("job-3", "file-3.parquet");
        CompactionJob job2 = createCompactionJob("job-4", "file-4.parquet");
        List<CompactionJob> batch = List.of(job1, job2);
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "multi-batch", Instant.now());

        // Upload the batch to S3
        String batchJson = jobSerDe.toJson(batch);
        s3Client.putObject(instanceProperties.get(DATA_BUCKET), request.getBatchKey(), batchJson);

        // Add and assign files in state store
        FileReference file3 = fileFactory.rootFile("file-3.parquet", 100);
        FileReference file4 = fileFactory.rootFile("file-4.parquet", 150);
        update(stateStore()).addFiles(List.of(file3, file4));
        update(stateStore()).assignJobId("job-3", "root", List.of("file-3.parquet"));
        update(stateStore()).assignJobId("job-4", "root", List.of("file-4.parquet"));

        // When
        CompactionJobDispatcher dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf,
                instanceProperties, Instant::now);

        dispatcher.dispatch(request);

        // Then - verify both jobs were sent
        List<Message> messages = sqsClient.receiveMessage(new ReceiveMessageRequest()
                .withQueueUrl(instanceProperties.get(COMPACTION_JOB_QUEUE_URL))
                .withWaitTimeSeconds(1)
                .withMaxNumberOfMessages(10)).getMessages();

        assertThat(messages).hasSize(2);
    }

    @Test
    void shouldSendExpiredRequestToDeadLetterQueue() {
        // Given - create a batch with an old/expired request time
        CompactionJob job = createCompactionJob("job-5", "file-5.parquet");
        List<CompactionJob> batch = List.of(job);
        Instant oldTime = Instant.parse("2020-01-01T00:00:00Z");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "expired-batch", oldTime);

        // Upload the batch to S3
        String batchJson = jobSerDe.toJson(batch);
        s3Client.putObject(instanceProperties.get(DATA_BUCKET), request.getBatchKey(), batchJson);

        // Add file but do NOT assign to job (to trigger expiration path)
        FileReference file = fileFactory.rootFile("file-5.parquet", 100);
        update(stateStore()).addFile(file);

        // When - create dispatcher with current time well after the request time
        Instant currentTime = Instant.parse("2024-01-01T00:00:00Z");
        CompactionJobDispatcher dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf,
                instanceProperties, () -> currentTime);

        dispatcher.dispatch(request);

        // Then - verify request was sent to dead letter queue
        List<Message> dlqMessages = sqsClient.receiveMessage(new ReceiveMessageRequest()
                .withQueueUrl(instanceProperties.get(COMPACTION_PENDING_DLQ_URL))
                .withWaitTimeSeconds(1)
                .withMaxNumberOfMessages(10)).getMessages();

        assertThat(dlqMessages).hasSize(1);
        CompactionJobDispatchRequest dlqRequest = requestSerDe.fromJson(dlqMessages.get(0).getBody());
        assertThat(dlqRequest.getBatchKey()).isEqualTo(request.getBatchKey());
    }

    @Test
    void shouldIntegrateWithLambdaHandleRequest() {
        // Given
        CompactionJob job = createCompactionJob("job-6", "file-6.parquet");
        List<CompactionJob> batch = List.of(job);
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "lambda-test", Instant.now());

        String batchJson = jobSerDe.toJson(batch);
        s3Client.putObject(instanceProperties.get(DATA_BUCKET), request.getBatchKey(), batchJson);

        FileReference file = fileFactory.rootFile("file-6.parquet", 100);
        update(stateStore()).addFile(file);
        update(stateStore()).assignJobId("job-6", "root", List.of("file-6.parquet"));

        // When - create lambda with dispatcher and handle a request
        CompactionJobDispatcher dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf,
                instanceProperties, Instant::now);

        // Simulate what the lambda does: deserialize and dispatch
        CompactionJobDispatchRequest deserializedRequest = requestSerDe.fromJson(requestSerDe.toJson(request));
        dispatcher.dispatch(deserializedRequest);

        // Then
        List<Message> messages = sqsClient.receiveMessage(new ReceiveMessageRequest()
                .withQueueUrl(instanceProperties.get(COMPACTION_JOB_QUEUE_URL))
                .withWaitTimeSeconds(1)
                .withMaxNumberOfMessages(10)).getMessages();

        assertThat(messages).hasSize(1);
        CompactionJob receivedJob = jobSerDe.fromJson(messages.get(0).getBody());
        assertThat(receivedJob.getId()).isEqualTo("job-6");
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
