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
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import sleeper.compaction.core.job.creation.CreateCompactionJobs;
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

import java.lang.reflect.Field;
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
 * Integration tests for the handleRequest method of CreateCompactionJobsLambda.
 * <p>
 * These tests use LocalStack to provide real AWS service implementations
 * and verify the handleRequest method's behavior with actual dependencies.
 * <p>
 * Since the production lambda's constructor uses defaultClient() which cannot
 * be redirected to LocalStack, these tests use reflection to inject the
 * required dependencies after creating an instance via Mockito's mock allocation.
 * This allows us to test the actual handleRequest method for code coverage.
 */
public class CreateCompactionJobsLambdaClaudeHandleRequestTest extends LocalStackTestBase {

    private final Schema schema = schemaWithKey("key", new StringType());
    private final PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);

    private TablePropertiesProvider tablePropertiesProvider;
    private StateStoreProvider stateStoreProvider;
    private PropertiesReloader propertiesReloader;
    private CreateCompactionJobs createJobs;

    @BeforeEach
    void setUp() throws ObjectFactoryException {
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

        // Initialize the dependencies that would be created in the lambda constructor
        ObjectFactory objectFactory = new S3UserJarsLoader(instanceProperties, s3Client, "/tmp").buildObjectFactory();
        tablePropertiesProvider = S3TableProperties.createProvider(instanceProperties, s3Client, dynamoClient);
        stateStoreProvider = StateStoreFactory.createProvider(instanceProperties, s3Client, dynamoClient, hadoopConf);
        propertiesReloader = S3PropertiesReloader.ifConfigured(s3Client, instanceProperties, tablePropertiesProvider);
        createJobs = AwsCreateCompactionJobs.from(
                objectFactory, instanceProperties, tablePropertiesProvider, stateStoreProvider,
                s3Client, sqsClient);
    }

    @Test
    void shouldProcessSingleMessageForTable() throws Exception {
        // Given
        CreateCompactionJobsLambda lambda = createLambdaWithDependencies(
                tablePropertiesProvider, propertiesReloader, createJobs);
        SQSEvent event = createEvent(tableProperties.get(TABLE_ID));

        // When
        SQSBatchResponse response = lambda.handleRequest(event, null);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getBatchItemFailures()).isEmpty();
    }

    @Test
    void shouldProcessMultipleMessagesForSameTable() throws Exception {
        // Given
        CreateCompactionJobsLambda lambda = createLambdaWithDependencies(
                tablePropertiesProvider, propertiesReloader, createJobs);
        String tableId = tableProperties.get(TABLE_ID);
        SQSEvent event = createEventWithMultipleMessages(tableId, tableId, tableId);

        // When
        SQSBatchResponse response = lambda.handleRequest(event, null);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getBatchItemFailures()).isEmpty();
    }

    @Test
    void shouldProcessMessagesForDifferentTables() throws Exception {
        // Given - create a second table
        TableProperties table2 = createTestTableProperties(instanceProperties, schema);
        S3TableProperties.createStore(instanceProperties, s3Client, dynamoClient).createTable(table2);
        update(StateStoreFactory.createProvider(instanceProperties, s3Client, dynamoClient, hadoopConf)
                .getStateStore(table2)).initialise(partitions.getAllPartitions());

        // Refresh table properties provider to pick up the new table
        tablePropertiesProvider = S3TableProperties.createProvider(instanceProperties, s3Client, dynamoClient);
        ObjectFactory objectFactory = new S3UserJarsLoader(instanceProperties, s3Client, "/tmp").buildObjectFactory();
        createJobs = AwsCreateCompactionJobs.from(
                objectFactory, instanceProperties, tablePropertiesProvider, stateStoreProvider,
                s3Client, sqsClient);

        CreateCompactionJobsLambda lambda = createLambdaWithDependencies(
                tablePropertiesProvider, propertiesReloader, createJobs);
        SQSEvent event = createEventWithMultipleMessages(
                tableProperties.get(TABLE_ID), table2.get(TABLE_ID));

        // When
        SQSBatchResponse response = lambda.handleRequest(event, null);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getBatchItemFailures()).isEmpty();
    }

    @Test
    void shouldReturnBatchFailuresWhenTableNotFound() throws Exception {
        // Given
        CreateCompactionJobsLambda lambda = createLambdaWithDependencies(
                tablePropertiesProvider, propertiesReloader, createJobs);
        String nonExistentTableId = "non-existent-table-id";
        SQSMessage message = createMessageWithBodyAndId("msg-1", nonExistentTableId);
        SQSEvent event = new SQSEvent();
        event.setRecords(List.of(message));

        // When
        SQSBatchResponse response = lambda.handleRequest(event, null);

        // Then - should return failure for non-existent table
        assertThat(response.getBatchItemFailures())
                .extracting(SQSBatchResponse.BatchItemFailure::getItemIdentifier)
                .containsExactly("msg-1");
    }

    @Test
    void shouldReturnPartialBatchFailuresWhenOneTableNotFound() throws Exception {
        // Given
        CreateCompactionJobsLambda lambda = createLambdaWithDependencies(
                tablePropertiesProvider, propertiesReloader, createJobs);

        SQSMessage successMessage = createMessageWithBodyAndId("msg-success", tableProperties.get(TABLE_ID));
        SQSMessage failingMessage = createMessageWithBodyAndId("msg-fail", "non-existent-table");
        SQSEvent event = new SQSEvent();
        event.setRecords(List.of(successMessage, failingMessage));

        // When
        SQSBatchResponse response = lambda.handleRequest(event, null);

        // Then - only the non-existent table's message should fail
        assertThat(response.getBatchItemFailures())
                .extracting(SQSBatchResponse.BatchItemFailure::getItemIdentifier)
                .containsExactly("msg-fail");
    }

    @Test
    void shouldHandleEmptyEvent() throws Exception {
        // Given
        CreateCompactionJobsLambda lambda = createLambdaWithDependencies(
                tablePropertiesProvider, propertiesReloader, createJobs);
        SQSEvent event = new SQSEvent();
        event.setRecords(List.of());

        // When
        SQSBatchResponse response = lambda.handleRequest(event, null);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getBatchItemFailures()).isEmpty();
    }

    @Test
    void shouldReloadPropertiesBeforeProcessing() throws Exception {
        // Given - use a reloader that tracks calls
        java.util.concurrent.atomic.AtomicInteger reloadCount = new java.util.concurrent.atomic.AtomicInteger(0);
        PropertiesReloader trackingReloader = reloadCount::incrementAndGet;

        CreateCompactionJobsLambda lambda = createLambdaWithDependencies(
                tablePropertiesProvider, trackingReloader, createJobs);
        SQSEvent event = createEvent(tableProperties.get(TABLE_ID));

        // When
        lambda.handleRequest(event, null);

        // Then
        assertThat(reloadCount.get()).isEqualTo(1);
    }

    @Test
    void shouldReturnEmptyBatchResponseOnSuccess() throws Exception {
        // Given
        CreateCompactionJobsLambda lambda = createLambdaWithDependencies(
                tablePropertiesProvider, propertiesReloader, createJobs);
        SQSEvent event = createEvent(tableProperties.get(TABLE_ID));

        // When
        SQSBatchResponse response = lambda.handleRequest(event, null);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getBatchItemFailures()).isNotNull();
        assertThat(response.getBatchItemFailures()).isEmpty();
    }

    @Test
    void shouldReturnMultipleBatchFailuresForMultipleMessagesToFailingTable() throws Exception {
        // Given
        CreateCompactionJobsLambda lambda = createLambdaWithDependencies(
                tablePropertiesProvider, propertiesReloader, createJobs);

        String nonExistentTableId = "non-existent-table";
        SQSMessage message1 = createMessageWithBodyAndId("msg-1", nonExistentTableId);
        SQSMessage message2 = createMessageWithBodyAndId("msg-2", nonExistentTableId);
        SQSMessage message3 = createMessageWithBodyAndId("msg-3", nonExistentTableId);
        SQSEvent event = new SQSEvent();
        event.setRecords(List.of(message1, message2, message3));

        // When
        SQSBatchResponse response = lambda.handleRequest(event, null);

        // Then - all messages for the non-existent table should be failures
        assertThat(response.getBatchItemFailures())
                .extracting(SQSBatchResponse.BatchItemFailure::getItemIdentifier)
                .containsExactlyInAnyOrder("msg-1", "msg-2", "msg-3");
    }

    private StateStore stateStore() {
        return StateStoreFactory.createProvider(instanceProperties, s3Client, dynamoClient, hadoopConf)
                .getStateStore(tableProperties);
    }

    /**
     * Creates a CreateCompactionJobsLambda instance with injected dependencies using reflection.
     * <p>
     * This is necessary because the production constructor uses defaultClient() methods
     * which cannot be redirected to LocalStack. We use Objenesis (via Mockito's internal
     * usage) to create an instance without calling the constructor, then inject the
     * dependencies via reflection.
     *
     * @param  tablePropertiesProvider the table properties provider
     * @param  propertiesReloader      the properties reloader
     * @param  createJobs              the compaction job creator
     * @return                         a lambda instance with the injected dependencies
     * @throws Exception               if reflection fails
     */
    private CreateCompactionJobsLambda createLambdaWithDependencies(
            TablePropertiesProvider tablePropertiesProvider,
            PropertiesReloader propertiesReloader,
            CreateCompactionJobs createJobs) throws Exception {
        // Create instance without calling constructor using Objenesis
        Objenesis objenesis = new ObjenesisStd();
        CreateCompactionJobsLambda lambda = objenesis.newInstance(CreateCompactionJobsLambda.class);

        // Use reflection to set final fields
        setField(lambda, "tablePropertiesProvider", tablePropertiesProvider);
        setField(lambda, "propertiesReloader", propertiesReloader);
        setField(lambda, "createJobs", createJobs);

        return lambda;
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = CreateCompactionJobsLambda.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    private SQSEvent createEvent(String tableId) {
        SQSEvent event = new SQSEvent();
        SQSMessage message = new SQSMessage();
        message.setBody(tableId);
        message.setMessageId(UUID.randomUUID().toString());
        event.setRecords(List.of(message));
        return event;
    }

    private SQSEvent createEventWithMultipleMessages(String... tableIds) {
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

    private SQSMessage createMessageWithBodyAndId(String messageId, String body) {
        SQSMessage message = new SQSMessage();
        message.setMessageId(messageId);
        message.setBody(body);
        return message;
    }
}
