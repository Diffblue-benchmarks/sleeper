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

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobSerDe;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequestSerDe;
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
import sleeper.core.statestore.StateStoreProvider;
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
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_SEND_RETRY_DELAY_SECS;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.AssignJobIdRequest.assignJobOnPartitionToFiles;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

/**
 * Tests for the CompactionJobDispatchLambda constructor.
 * <p>
 * Note: The no-arg constructor (lines 69-77) cannot be directly unit tested because:
 * 1. It reads CONFIG_BUCKET from System.getenv() which cannot be mocked without special libraries
 * 2. It creates AWS clients using defaultClient() builders which require real AWS credentials
 * <p>
 * Instead, these tests verify the dispatcher factory method and end-to-end behavior
 * using LocalStack to simulate AWS services. This tests the same code paths that
 * the constructor uses, just with injected dependencies.
 */
class CompactionJobDispatchLambdaClaude_constructorTest extends LocalStackTestBase {
    private static final Instant DEFAULT_FILE_UPDATE_TIME = FilesReportTestHelper.DEFAULT_UPDATE_TIME;

    private final Schema schema = schemaWithKey("key", new StringType());
    private final PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
    private InstanceProperties instanceProperties;
    private TableProperties tableProperties;
    private final CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();
    private final CompactionJobDispatchRequestSerDe requestSerDe = new CompactionJobDispatchRequestSerDe();
    private FileReferenceFactory fileFactory;
    private String compactionJobQueueUrl;
    private String pendingQueueUrl;
    private String dlqUrl;

    @BeforeEach
    void setUp() {
        instanceProperties = createInstance();
        tableProperties = createTable();
        fileFactory = FileReferenceFactory.fromUpdatedAt(partitions, DEFAULT_FILE_UPDATE_TIME);
    }

    private InstanceProperties createInstance() {
        InstanceProperties instanceProperties = createTestInstanceProperties();

        compactionJobQueueUrl = sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl();
        pendingQueueUrl = sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl();
        dlqUrl = sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl();

        instanceProperties.set(COMPACTION_JOB_QUEUE_URL, compactionJobQueueUrl);
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL, pendingQueueUrl);
        instanceProperties.set(COMPACTION_PENDING_DLQ_URL, dlqUrl);

        createBucket(instanceProperties.get(CONFIG_BUCKET));
        createBucket(instanceProperties.get(DATA_BUCKET));
        S3InstanceProperties.saveToS3(s3Client, instanceProperties);
        DynamoDBTableIndexCreator.create(dynamoClient, instanceProperties);
        new TransactionLogStateStoreCreator(instanceProperties, dynamoClient).create();
        DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);
        return instanceProperties;
    }

    private TableProperties createTable() {
        TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
        tableProperties.set(COMPACTION_JOB_SEND_RETRY_DELAY_SECS, "0");
        S3TableProperties.createStore(instanceProperties, s3Client, dynamoClient).createTable(tableProperties);
        update(stateStoreProvider().getStateStore(tableProperties))
                .initialise(partitions.getAllPartitions());
        return tableProperties;
    }

    @Test
    void shouldCreateDispatcherFromStaticMethodWithLocalStackServices() {
        // Given - use the static dispatcher method which is called by the constructor
        var dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf, instanceProperties, Instant::now);

        // Then - dispatcher should be created successfully
        assertThat(dispatcher).isNotNull();
    }

    @Test
    void shouldDispatchJobUsingDispatcherCreatedFromStaticMethod() {
        // Given
        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);

        CompactionJob job = createCompactionJob(file.getFilename());
        update(stateStore()).assignJobIds(List.of(
                assignJobOnPartitionToFiles(job.getId(), file.getPartitionId(), List.of(file.getFilename()))));

        String batchKey = saveBatchToS3(List.of(job));
        CompactionJobDispatchRequest request = createRequest(batchKey);

        var dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf, instanceProperties, Instant::now);

        // When
        dispatcher.dispatch(request);

        // Then - job should be sent to the compaction job queue
        var messages = sqsClient.receiveMessage(compactionJobQueueUrl).getMessages();
        assertThat(messages).hasSize(1);
        CompactionJob receivedJob = compactionJobSerDe.fromJson(messages.get(0).getBody());
        assertThat(receivedJob.getId()).isEqualTo(job.getId());
    }

    @Test
    void shouldHandleRequestUsingDispatcherCreatedFromStaticMethod() {
        // Given
        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);

        CompactionJob job = createCompactionJob(file.getFilename());
        update(stateStore()).assignJobIds(List.of(
                assignJobOnPartitionToFiles(job.getId(), file.getPartitionId(), List.of(file.getFilename()))));

        String batchKey = saveBatchToS3(List.of(job));
        CompactionJobDispatchRequest request = createRequest(batchKey);

        var dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf, instanceProperties, Instant::now);

        // Create SQS event like handleRequest receives
        SQSEvent event = new SQSEvent();
        SQSEvent.SQSMessage message = new SQSEvent.SQSMessage();
        message.setBody(requestSerDe.toJson(request));
        event.setRecords(List.of(message));

        // When - simulate what handleRequest does
        event.getRecords().forEach(msg -> dispatcher.dispatch(requestSerDe.fromJson(msg.getBody())));

        // Then
        var messages = sqsClient.receiveMessage(compactionJobQueueUrl).getMessages();
        assertThat(messages).hasSize(1);
    }

    @Test
    void shouldReturnRequestToQueueWhenFilesNotAssigned() {
        // Given
        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);

        CompactionJob job = createCompactionJob(file.getFilename());
        // NOT assigning the job to the file

        String batchKey = saveBatchToS3(List.of(job));
        CompactionJobDispatchRequest request = createRequest(batchKey);

        var dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf, instanceProperties, Instant::now);

        // When
        dispatcher.dispatch(request);

        // Then - request should be returned to pending queue
        var messages = sqsClient.receiveMessage(pendingQueueUrl).getMessages();
        assertThat(messages).hasSize(1);
    }

    @Test
    void shouldSendExpiredRequestToDeadLetterQueue() {
        // Given
        FileReference file = fileFactory.rootFile("test.parquet", 100);
        update(stateStore()).addFile(file);

        CompactionJob job = createCompactionJob(file.getFilename());
        // NOT assigning - and create expired request

        String batchKey = saveBatchToS3(List.of(job));
        // Create request with time far in the past
        CompactionJobDispatchRequest request = createRequestAtTime(batchKey, Instant.parse("2020-01-01T00:00:00Z"));

        // Use current time supplier that returns time after expiry
        var dispatcher = CompactionJobDispatchLambda.dispatcher(
                s3Client, dynamoClient, sqsClient, hadoopConf, instanceProperties,
                () -> Instant.parse("2024-01-01T00:00:00Z"));

        // When
        dispatcher.dispatch(request);

        // Then - request should be sent to DLQ
        var messages = sqsClient.receiveMessage(dlqUrl).getMessages();
        assertThat(messages).hasSize(1);
    }

    private StateStore stateStore() {
        return stateStoreProvider().getStateStore(tableProperties);
    }

    private StateStoreProvider stateStoreProvider() {
        return StateStoreFactory.createProvider(instanceProperties, s3Client, dynamoClient, hadoopConf);
    }

    private CompactionJob createCompactionJob(String inputFile) {
        return CompactionJob.builder()
                .tableId(tableProperties.get(TABLE_ID))
                .jobId(UUID.randomUUID().toString())
                .inputFiles(List.of(inputFile))
                .outputFile("output-" + UUID.randomUUID() + ".parquet")
                .partitionId("root")
                .build();
    }

    private String saveBatchToS3(List<CompactionJob> batch) {
        String batchId = UUID.randomUUID().toString();
        String batchKey = tableProperties.get(TABLE_ID) + "/compactions/" + batchId + ".json";
        s3Client.putObject(instanceProperties.get(DATA_BUCKET), batchKey, compactionJobSerDe.toJson(batch));
        return batchKey;
    }

    private CompactionJobDispatchRequest createRequest(String batchKey) {
        return createRequestAtTime(batchKey, Instant.now());
    }

    private CompactionJobDispatchRequest createRequestAtTime(String batchKey, Instant createTime) {
        String batchId = batchKey.substring(batchKey.lastIndexOf("/") + 1, batchKey.lastIndexOf("."));
        return CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties, batchId, createTime);
    }
}
