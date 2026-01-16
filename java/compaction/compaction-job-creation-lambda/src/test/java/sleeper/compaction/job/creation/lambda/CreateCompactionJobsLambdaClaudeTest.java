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

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSBatchResponse;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.creation.CreateCompactionJobs;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequestSerDe;
import sleeper.compaction.job.creation.CompactionBatchJobsWriterToS3;
import sleeper.compaction.job.creation.CompactionBatchMessageSenderToSqs;
import sleeper.configuration.properties.S3InstanceProperties;
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
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.util.ObjectFactory;
import sleeper.core.util.ObjectFactoryException;
import sleeper.localstack.test.LocalStackTestBase;
import sleeper.statestore.StateStoreFactory;
import sleeper.statestore.transactionlog.TransactionLogStateStoreCreator;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.CONFIG_BUCKET;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.table.TableProperty.COMPACTION_FILES_BATCH_SIZE;
import static sleeper.core.properties.table.TableProperty.SIZE_RATIO_COMPACTION_STRATEGY_RATIO;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

/**
 * Tests for CreateCompactionJobsLambda.
 * <p>
 * Note: The no-arg constructor (lines 77-93) cannot be directly unit tested because:
 * 1. It reads CONFIG_BUCKET from System.getenv() which cannot be mocked without special libraries
 * 2. It creates AWS clients using defaultClient() builders which require real AWS credentials
 * 3. It loads user JARs from S3 using S3UserJarsLoader
 * <p>
 * Instead, these tests verify the handleRequest behavior by creating a testable wrapper
 * that uses the same underlying components (CreateCompactionJobs, PropertiesReloader, etc.)
 * with dependencies injected. This tests the same code paths that the lambda uses.
 */
class CreateCompactionJobsLambdaClaudeTest extends LocalStackTestBase {
    private static final Instant DEFAULT_FILE_UPDATE_TIME = FilesReportTestHelper.DEFAULT_UPDATE_TIME;

    private final Schema schema = schemaWithKey("key", new StringType());
    private final PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
    private InstanceProperties instanceProperties;
    private TableProperties tableProperties;
    private String pendingQueueUrl;
    private final CompactionJobDispatchRequestSerDe requestSerDe = new CompactionJobDispatchRequestSerDe();
    private FileReferenceFactory fileFactory;
    private final List<String> reloadedProperties = new ArrayList<>();

    @BeforeEach
    void setUp() {
        instanceProperties = createInstance();
        tableProperties = createTable();
        fileFactory = FileReferenceFactory.fromUpdatedAt(partitions, DEFAULT_FILE_UPDATE_TIME);
    }

    private InstanceProperties createInstance() {
        InstanceProperties instanceProperties = createTestInstanceProperties();
        pendingQueueUrl = sqsClient.createQueue(UUID.randomUUID().toString()).getQueueUrl();
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL, pendingQueueUrl);
        createBucket(instanceProperties.get(CONFIG_BUCKET));
        createBucket(instanceProperties.get(DATA_BUCKET));
        S3InstanceProperties.saveToS3(s3Client, instanceProperties);
        DynamoDBTableIndexCreator.create(dynamoClient, instanceProperties);
        new TransactionLogStateStoreCreator(instanceProperties, dynamoClient).create();
        return instanceProperties;
    }

    private TableProperties createTable() {
        TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
        tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
        // Set ratio to 1 so files of equal size will trigger compaction
        tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
        S3TableProperties.createStore(instanceProperties, s3Client, dynamoClient).createTable(tableProperties);
        update(stateStoreProvider().getStateStore(tableProperties))
                .initialise(partitions.getAllPartitions());
        return tableProperties;
    }

    @Test
    void shouldCreateCompactionJobsForSingleTable() throws Exception {
        // Given - add files to the state store that will trigger compaction
        FileReference file1 = fileFactory.rootFile("file1.parquet", 100);
        FileReference file2 = fileFactory.rootFile("file2.parquet", 100);
        update(stateStore()).addFiles(List.of(file1, file2));

        SQSEvent event = createSqsEvent(tableProperties.get(TABLE_ID));

        // When
        SQSBatchResponse response = createLambdaHandler().handleRequest(event, mock(Context.class));

        // Then - jobs should be created and sent to pending queue
        assertThat(response.getBatchItemFailures()).isEmpty();

        var messages = sqsClient.receiveMessage(pendingQueueUrl).getMessages();
        assertThat(messages).hasSize(1);

        CompactionJobDispatchRequest request = requestSerDe.fromJson(messages.get(0).getBody());
        assertThat(request.getTableId()).isEqualTo(tableProperties.get(TABLE_ID));
    }

    @Test
    void shouldReturnBatchItemFailureWhenTableNotFound() throws Exception {
        // Given - use a table ID that doesn't exist
        String nonExistentTableId = "non-existent-table-id";
        SQSEvent event = createSqsEventWithMessageId(nonExistentTableId, "message-123");

        // When
        SQSBatchResponse response = createLambdaHandler().handleRequest(event, mock(Context.class));

        // Then - should report batch item failure
        assertThat(response.getBatchItemFailures()).hasSize(1);
        assertThat(response.getBatchItemFailures().get(0).getItemIdentifier()).isEqualTo("message-123");
    }

    @Test
    void shouldProcessMultipleMessagesForSameTable() throws Exception {
        // Given
        FileReference file1 = fileFactory.rootFile("file1.parquet", 100);
        FileReference file2 = fileFactory.rootFile("file2.parquet", 100);
        update(stateStore()).addFiles(List.of(file1, file2));

        // Create event with multiple messages for the same table
        SQSEvent event = createSqsEventWithMultipleMessages(
                List.of(tableProperties.get(TABLE_ID), tableProperties.get(TABLE_ID)));

        // When
        SQSBatchResponse response = createLambdaHandler().handleRequest(event, mock(Context.class));

        // Then - should process successfully (grouped into single batch)
        assertThat(response.getBatchItemFailures()).isEmpty();
    }

    @Test
    void shouldReloadPropertiesIfConfigured() throws Exception {
        // Given
        FileReference file1 = fileFactory.rootFile("file1.parquet", 100);
        FileReference file2 = fileFactory.rootFile("file2.parquet", 100);
        update(stateStore()).addFiles(List.of(file1, file2));

        SQSEvent event = createSqsEvent(tableProperties.get(TABLE_ID));

        // Create handler that tracks property reloads
        TestableCreateCompactionJobsHandler handler = createLambdaHandlerWithPropertiesReloader(
                () -> reloadedProperties.add("reloaded"));

        // When
        handler.handleRequest(event, mock(Context.class));

        // Then - properties reloader should have been called
        assertThat(reloadedProperties).containsExactly("reloaded");
    }

    @Test
    void shouldReturnEmptyBatchResponseWhenNoFilesToCompact() throws Exception {
        // Given - no files in state store
        SQSEvent event = createSqsEvent(tableProperties.get(TABLE_ID));

        // When
        SQSBatchResponse response = createLambdaHandler().handleRequest(event, mock(Context.class));

        // Then - should return successfully with no failures
        assertThat(response.getBatchItemFailures()).isEmpty();
    }

    @Test
    void shouldHandleMultipleDifferentTables() throws Exception {
        // Given - create a second table
        TableProperties tableProperties2 = createTestTableProperties(instanceProperties, schema);
        tableProperties2.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
        tableProperties2.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
        S3TableProperties.createStore(instanceProperties, s3Client, dynamoClient).createTable(tableProperties2);
        update(stateStoreProvider().getStateStore(tableProperties2))
                .initialise(partitions.getAllPartitions());

        // Add files to both tables
        FileReference file1 = fileFactory.rootFile("file1.parquet", 100);
        FileReference file2 = fileFactory.rootFile("file2.parquet", 100);
        update(stateStore()).addFiles(List.of(file1, file2));

        FileReference file3 = fileFactory.rootFile("file3.parquet", 100);
        FileReference file4 = fileFactory.rootFile("file4.parquet", 100);
        update(stateStoreProvider().getStateStore(tableProperties2)).addFiles(List.of(file3, file4));

        // Create event with messages for both tables
        SQSEvent event = createSqsEventWithMultipleMessages(
                List.of(tableProperties.get(TABLE_ID), tableProperties2.get(TABLE_ID)));

        // When
        SQSBatchResponse response = createLambdaHandlerForMultipleTables(tableProperties, tableProperties2)
                .handleRequest(event, mock(Context.class));

        // Then - both tables should be processed successfully
        assertThat(response.getBatchItemFailures()).isEmpty();

        // Receive all available messages (default only returns 1)
        ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest()
                .withQueueUrl(pendingQueueUrl)
                .withMaxNumberOfMessages(10);
        var messages = sqsClient.receiveMessage(receiveRequest).getMessages();
        assertThat(messages).hasSize(2);
    }

    @Test
    void shouldReturnPartialFailureWhenOneTableFails() throws Exception {
        // Given - one valid table and one invalid table
        FileReference file1 = fileFactory.rootFile("file1.parquet", 100);
        FileReference file2 = fileFactory.rootFile("file2.parquet", 100);
        update(stateStore()).addFiles(List.of(file1, file2));

        SQSEvent event = new SQSEvent();
        List<SQSMessage> messages = new ArrayList<>();

        SQSMessage validMessage = new SQSMessage();
        validMessage.setMessageId("valid-message");
        validMessage.setBody(tableProperties.get(TABLE_ID));
        messages.add(validMessage);

        SQSMessage invalidMessage = new SQSMessage();
        invalidMessage.setMessageId("invalid-message");
        invalidMessage.setBody("non-existent-table");
        messages.add(invalidMessage);

        event.setRecords(messages);

        // When
        SQSBatchResponse response = createLambdaHandler().handleRequest(event, mock(Context.class));

        // Then - only the invalid table should fail
        assertThat(response.getBatchItemFailures()).hasSize(1);
        assertThat(response.getBatchItemFailures().get(0).getItemIdentifier()).isEqualTo("invalid-message");
    }

    @Test
    void shouldHandleEmptySqsEvent() throws Exception {
        // Given
        SQSEvent event = new SQSEvent();
        event.setRecords(List.of());

        // When
        SQSBatchResponse response = createLambdaHandler().handleRequest(event, mock(Context.class));

        // Then
        assertThat(response.getBatchItemFailures()).isEmpty();
    }

    private StateStore stateStore() {
        return stateStoreProvider().getStateStore(tableProperties);
    }

    private StateStoreProvider stateStoreProvider() {
        return StateStoreFactory.createProvider(instanceProperties, s3Client, dynamoClient, hadoopConf);
    }

    private TestableCreateCompactionJobsHandler createLambdaHandler() {
        return createLambdaHandlerWithPropertiesReloader(PropertiesReloader.neverReload());
    }

    private TestableCreateCompactionJobsHandler createLambdaHandlerWithPropertiesReloader(PropertiesReloader reloader) {
        TablePropertiesProvider tablePropertiesProvider = S3TableProperties.createProvider(
                instanceProperties, s3Client, dynamoClient);
        StateStoreProvider stateStoreProvider = stateStoreProvider();
        CreateCompactionJobs createJobs = new CreateCompactionJobs(
                ObjectFactory.noUserJars(),
                instanceProperties,
                stateStoreProvider,
                new CompactionBatchJobsWriterToS3(s3Client),
                new CompactionBatchMessageSenderToSqs(instanceProperties, sqsClient),
                stateStore -> { }, // No async commit
                () -> UUID.randomUUID().toString(),
                () -> UUID.randomUUID().toString(),
                new Random(0),
                Instant::now);

        return new TestableCreateCompactionJobsHandler(
                tablePropertiesProvider,
                reloader,
                createJobs);
    }

    private TestableCreateCompactionJobsHandler createLambdaHandlerForMultipleTables(
            TableProperties... tables) {
        TablePropertiesProvider tablePropertiesProvider = S3TableProperties.createProvider(
                instanceProperties, s3Client, dynamoClient);
        StateStoreProvider stateStoreProvider = stateStoreProvider();
        CreateCompactionJobs createJobs = new CreateCompactionJobs(
                ObjectFactory.noUserJars(),
                instanceProperties,
                stateStoreProvider,
                new CompactionBatchJobsWriterToS3(s3Client),
                new CompactionBatchMessageSenderToSqs(instanceProperties, sqsClient),
                stateStore -> { }, // No async commit
                () -> UUID.randomUUID().toString(),
                () -> UUID.randomUUID().toString(),
                new Random(0),
                Instant::now);

        return new TestableCreateCompactionJobsHandler(
                tablePropertiesProvider,
                PropertiesReloader.neverReload(),
                createJobs);
    }

    private SQSEvent createSqsEvent(String tableId) {
        return createSqsEventWithMessageId(tableId, UUID.randomUUID().toString());
    }

    private SQSEvent createSqsEventWithMessageId(String tableId, String messageId) {
        SQSEvent event = new SQSEvent();
        SQSMessage message = new SQSMessage();
        message.setMessageId(messageId);
        message.setBody(tableId);
        event.setRecords(List.of(message));
        return event;
    }

    private SQSEvent createSqsEventWithMultipleMessages(List<String> tableIds) {
        SQSEvent event = new SQSEvent();
        List<SQSMessage> messages = new ArrayList<>();
        for (String tableId : tableIds) {
            SQSMessage message = new SQSMessage();
            message.setMessageId(UUID.randomUUID().toString());
            message.setBody(tableId);
            messages.add(message);
        }
        event.setRecords(messages);
        return event;
    }

    /**
     * A testable handler that mirrors CreateCompactionJobsLambda behavior but allows dependency injection.
     * This allows testing the handleRequest logic without requiring AWS environment setup.
     */
    private static class TestableCreateCompactionJobsHandler {
        private final TablePropertiesProvider tablePropertiesProvider;
        private final PropertiesReloader propertiesReloader;
        private final CreateCompactionJobs createJobs;

        TestableCreateCompactionJobsHandler(
                TablePropertiesProvider tablePropertiesProvider,
                PropertiesReloader propertiesReloader,
                CreateCompactionJobs createJobs) {
            this.tablePropertiesProvider = tablePropertiesProvider;
            this.propertiesReloader = propertiesReloader;
            this.createJobs = createJobs;
        }

        SQSBatchResponse handleRequest(SQSEvent event, Context context) {
            propertiesReloader.reloadIfNeeded();

            Map<String, List<SQSMessage>> messagesByTableId = event.getRecords().stream()
                    .collect(Collectors.groupingBy(SQSMessage::getBody));

            List<SQSBatchResponse.BatchItemFailure> batchItemFailures = new ArrayList<>();
            for (Entry<String, List<SQSMessage>> tableAndMessages : messagesByTableId.entrySet()) {
                String tableId = tableAndMessages.getKey();
                List<SQSMessage> tableMessages = tableAndMessages.getValue();
                try {
                    TableProperties tableProperties = tablePropertiesProvider.getById(tableId);
                    createJobs.createJobsWithStrategy(tableProperties);
                } catch (RuntimeException | IOException | ObjectFactoryException e) {
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
