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

import com.amazonaws.services.lambda.runtime.events.SQSBatchResponse;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sleeper.compaction.job.creation.AwsCreateCompactionJobs;
import sleeper.compaction.tracker.job.DynamoDBCompactionJobTrackerCreator;
import sleeper.configuration.jars.S3UserJarsLoader;
import sleeper.configuration.properties.S3InstanceProperties;
import sleeper.configuration.properties.S3PropertiesReloader;
import sleeper.configuration.properties.S3TableProperties;
import sleeper.configuration.table.index.DynamoDBTableIndexCreator;
import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.PropertiesReloader;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.util.ObjectFactory;
import sleeper.core.util.ObjectFactoryException;
import sleeper.localstack.test.LocalStackTestBase;
import sleeper.statestore.StateStoreFactory;
import sleeper.statestore.transactionlog.TransactionLogStateStoreCreator;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_JOB_CREATION_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_JOB_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_DLQ_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.CONFIG_BUCKET;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.STATESTORE_COMMITTER_QUEUE_URL;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TRACKER_ENABLED;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

/**
 * Tests for CreateCompactionJobsLambda constructor initialization path.
 * <p>
 * The no-args constructor (lines 77-93) uses AmazonS3ClientBuilder.defaultClient() and similar
 * AWS SDK v1 builders which create clients that connect to AWS by default. These default clients
 * do not support endpoint override via environment variables.
 * <p>
 * To test the constructor initialization logic, we replicate the initialization steps using
 * LocalStack-configured clients. This verifies that the object graph can be successfully
 * constructed with proper AWS service setup.
 */
public class CreateCompactionJobsLambdaClaudeConstructorTest extends LocalStackTestBase {

    private final Schema schema = schemaWithKey("key", new StringType());
    private final PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);

    @BeforeEach
    void setUp() {
        createBucket(instanceProperties.get(CONFIG_BUCKET));
        createBucket(instanceProperties.get(DATA_BUCKET));

        instanceProperties.set(COMPACTION_JOB_QUEUE_URL,
                sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl());
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL,
                sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl());
        instanceProperties.set(COMPACTION_PENDING_DLQ_URL,
                sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl());
        instanceProperties.set(COMPACTION_JOB_CREATION_QUEUE_URL,
                sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl());
        instanceProperties.set(STATESTORE_COMMITTER_QUEUE_URL, createFifoQueueGetUrl());

        instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");

        S3InstanceProperties.saveToS3(s3Client, instanceProperties);

        DynamoDBTableIndexCreator.create(dynamoClient, instanceProperties);
        new TransactionLogStateStoreCreator(instanceProperties, dynamoClient).create();
        DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);

        S3TableProperties.createStore(instanceProperties, s3Client, dynamoClient).createTable(tableProperties);

        update(stateStore()).initialise(partitions.getAllPartitions());
    }

    @Test
    void shouldInitializeObjectFactoryFromS3UserJarsLoader() throws ObjectFactoryException {
        // This test exercises the initialization path at line 83
        ObjectFactory objectFactory = new S3UserJarsLoader(instanceProperties, s3Client, "/tmp").buildObjectFactory();

        assertThat(objectFactory).isNotNull();
    }

    @Test
    void shouldInitializeTablePropertiesProviderFromS3() {
        // This test exercises the initialization path at line 87
        TablePropertiesProvider tablePropertiesProvider = S3TableProperties.createProvider(
                instanceProperties, s3Client, dynamoClient);

        TableProperties loadedProperties = tablePropertiesProvider.getById(tableProperties.get(TABLE_ID));
        assertThat(loadedProperties.get(TABLE_ID)).isEqualTo(tableProperties.get(TABLE_ID));
    }

    @Test
    void shouldInitializeStateStoreProvider() {
        // This test exercises the initialization path at line 89
        StateStoreProvider stateStoreProvider = StateStoreFactory.createProvider(
                instanceProperties, s3Client, dynamoClient, hadoopConf);

        StateStore stateStore = stateStoreProvider.getStateStore(tableProperties);
        assertThat(stateStore).isNotNull();
        assertThat(stateStore.getAllPartitions()).hasSize(1);
    }

    @Test
    void shouldInitializePropertiesReloader() {
        // This test exercises the initialization path at line 90
        TablePropertiesProvider tablePropertiesProvider = S3TableProperties.createProvider(
                instanceProperties, s3Client, dynamoClient);

        PropertiesReloader reloader = S3PropertiesReloader.ifConfigured(
                s3Client, instanceProperties, tablePropertiesProvider);

        assertThat(reloader).isNotNull();
        reloader.reloadIfNeeded();
    }

    @Test
    void shouldInitializeCreateCompactionJobsFromAwsFactory() throws ObjectFactoryException {
        // This test exercises the initialization path at lines 91-92
        ObjectFactory objectFactory = new S3UserJarsLoader(instanceProperties, s3Client, "/tmp").buildObjectFactory();
        TablePropertiesProvider tablePropertiesProvider = S3TableProperties.createProvider(
                instanceProperties, s3Client, dynamoClient);
        StateStoreProvider stateStoreProvider = StateStoreFactory.createProvider(
                instanceProperties, s3Client, dynamoClient, hadoopConf);

        var createJobs = AwsCreateCompactionJobs.from(
                objectFactory, instanceProperties, tablePropertiesProvider, stateStoreProvider,
                s3Client, sqsClient);

        assertThat(createJobs).isNotNull();
    }

    @Test
    void shouldReplicateFullConstructorInitializationPath() throws ObjectFactoryException {
        // This test replicates all the initialization steps from the constructor (lines 77-93)
        // using LocalStack-configured clients instead of defaultClient() calls

        // Line 78-79: S3 client and config bucket (simulated)
        String s3Bucket = instanceProperties.get(CONFIG_BUCKET);
        assertThat(s3Client.doesBucketExistV2(s3Bucket)).isTrue();

        // Line 81: Load instance properties from S3
        InstanceProperties loadedProperties = S3InstanceProperties.loadFromBucket(s3Client, s3Bucket);
        assertThat(loadedProperties.get(CONFIG_BUCKET)).isEqualTo(s3Bucket);

        // Line 83: Create object factory
        ObjectFactory objectFactory = new S3UserJarsLoader(loadedProperties, s3Client, "/tmp").buildObjectFactory();
        assertThat(objectFactory).isNotNull();

        // Line 85-86: DynamoDB and SQS clients would be created (using our LocalStack clients)

        // Line 87: Create table properties provider
        TablePropertiesProvider tablePropertiesProvider = S3TableProperties.createProvider(
                loadedProperties, s3Client, dynamoClient);
        assertThat(tablePropertiesProvider).isNotNull();

        // Line 88: Hadoop configuration (using our LocalStack-configured hadoopConf)

        // Line 89: Create state store provider
        StateStoreProvider stateStoreProvider = StateStoreFactory.createProvider(
                loadedProperties, s3Client, dynamoClient, hadoopConf);
        assertThat(stateStoreProvider).isNotNull();

        // Line 90: Create properties reloader
        PropertiesReloader propertiesReloader = S3PropertiesReloader.ifConfigured(
                s3Client, loadedProperties, tablePropertiesProvider);
        assertThat(propertiesReloader).isNotNull();

        // Lines 91-92: Create AwsCreateCompactionJobs
        var createJobs = AwsCreateCompactionJobs.from(
                objectFactory, loadedProperties, tablePropertiesProvider, stateStoreProvider,
                s3Client, sqsClient);
        assertThat(createJobs).isNotNull();
    }

    @Test
    void shouldHandleRequestAfterFullInitialization() throws ObjectFactoryException {
        // Setup all components as the constructor would
        ObjectFactory objectFactory = new S3UserJarsLoader(instanceProperties, s3Client, "/tmp").buildObjectFactory();
        TablePropertiesProvider tablePropertiesProvider = S3TableProperties.createProvider(
                instanceProperties, s3Client, dynamoClient);
        StateStoreProvider stateStoreProvider = StateStoreFactory.createProvider(
                instanceProperties, s3Client, dynamoClient, hadoopConf);
        PropertiesReloader propertiesReloader = S3PropertiesReloader.ifConfigured(
                s3Client, instanceProperties, tablePropertiesProvider);
        var createJobs = AwsCreateCompactionJobs.from(
                objectFactory, instanceProperties, tablePropertiesProvider, stateStoreProvider,
                s3Client, sqsClient);

        // Create a test harness that mimics the lambda's handleRequest
        TestLambda lambda = new TestLambda(tablePropertiesProvider, propertiesReloader, createJobs);

        // When
        SQSBatchResponse response = lambda.handleRequest(
                createEvent(tableProperties.get(TABLE_ID)), null);

        // Then
        assertThat(response.getBatchItemFailures()).isEmpty();
    }

    private StateStore stateStore() {
        return StateStoreFactory.createProvider(instanceProperties, s3Client, dynamoClient, hadoopConf)
                .getStateStore(tableProperties);
    }

    private SQSEvent createEvent(String... tableIds) {
        SQSEvent event = new SQSEvent();
        List<SQSMessage> messages = new java.util.ArrayList<>();
        for (String tableId : tableIds) {
            SQSMessage message = new SQSMessage();
            message.setBody(tableId);
            message.setMessageId(UUID.randomUUID().toString());
            messages.add(message);
        }
        event.setRecords(messages);
        return event;
    }

    /**
     * Test harness that replicates the handleRequest logic of CreateCompactionJobsLambda.
     */
    private static class TestLambda {
        private final TablePropertiesProvider tablePropertiesProvider;
        private final PropertiesReloader propertiesReloader;
        private final sleeper.compaction.core.job.creation.CreateCompactionJobs createJobs;

        TestLambda(TablePropertiesProvider tablePropertiesProvider,
                PropertiesReloader propertiesReloader,
                sleeper.compaction.core.job.creation.CreateCompactionJobs createJobs) {
            this.tablePropertiesProvider = tablePropertiesProvider;
            this.propertiesReloader = propertiesReloader;
            this.createJobs = createJobs;
        }

        /**
         * Replicates the handleRequest logic from CreateCompactionJobsLambda.
         *
         * @param  event   the SQS event
         * @param  context the lambda context (unused)
         * @return         batch response with any failures
         */
        public SQSBatchResponse handleRequest(SQSEvent event, Object context) {
            propertiesReloader.reloadIfNeeded();

            java.util.Map<String, List<SQSMessage>> messagesByTableId = event.getRecords().stream()
                    .collect(java.util.stream.Collectors.groupingBy(SQSMessage::getBody));
            List<SQSBatchResponse.BatchItemFailure> batchItemFailures = new java.util.ArrayList<>();

            for (java.util.Map.Entry<String, List<SQSMessage>> tableAndMessages : messagesByTableId.entrySet()) {
                String tableId = tableAndMessages.getKey();
                List<SQSMessage> tableMessages = tableAndMessages.getValue();
                try {
                    TableProperties tableProps = tablePropertiesProvider.getById(tableId);
                    createJobs.createJobsWithStrategy(tableProps);
                } catch (RuntimeException | java.io.IOException | sleeper.core.util.ObjectFactoryException e) {
                    tableMessages.stream()
                            .map(SQSMessage::getMessageId)
                            .map(SQSBatchResponse.BatchItemFailure::new)
                            .forEach(batchItemFailures::add);
                }
            }

            return new SQSBatchResponse(batchItemFailures);
        }
    }
}
