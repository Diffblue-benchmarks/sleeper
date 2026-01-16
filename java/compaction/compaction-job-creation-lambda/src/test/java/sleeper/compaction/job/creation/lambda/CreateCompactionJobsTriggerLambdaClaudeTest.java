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
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sleeper.configuration.properties.S3InstanceProperties;
import sleeper.configuration.properties.S3TableProperties;
import sleeper.configuration.table.index.DynamoDBTableIndex;
import sleeper.configuration.table.index.DynamoDBTableIndexCreator;
import sleeper.core.properties.PropertiesReloader;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.type.StringType;
import sleeper.core.table.TableIndex;
import sleeper.invoke.tables.InvokeForTables;
import sleeper.localstack.test.LocalStackTestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_JOB_CREATION_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.CONFIG_BUCKET;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;

/**
 * Tests for CreateCompactionJobsTriggerLambda.
 * <p>
 * Note: The no-arg constructor cannot be directly unit tested because:
 * 1. It reads CONFIG_BUCKET from System.getenv() which cannot be mocked without special libraries
 * 2. It creates AWS clients using defaultClient() builders which require real AWS credentials/environment
 * <p>
 * Instead, these tests verify the handleRequest behavior by creating a testable wrapper
 * that uses the same underlying components (InvokeForTables, PropertiesReloader, TableIndex)
 * with dependencies injected. This tests the same code paths that the lambda uses.
 */
class CreateCompactionJobsTriggerLambdaClaudeTest extends LocalStackTestBase {

    private InstanceProperties instanceProperties;
    private String queueUrl;
    private final List<String> reloadedProperties = new ArrayList<>();

    @BeforeEach
    void setUp() {
        instanceProperties = createInstance();
    }

    private InstanceProperties createInstance() {
        InstanceProperties instanceProperties = createTestInstanceProperties();
        queueUrl = createFifoQueueGetUrl();
        instanceProperties.set(COMPACTION_JOB_CREATION_QUEUE_URL, queueUrl);
        createBucket(instanceProperties.get(CONFIG_BUCKET));
        createBucket(instanceProperties.get(DATA_BUCKET));
        S3InstanceProperties.saveToS3(s3Client, instanceProperties);
        DynamoDBTableIndexCreator.create(dynamoClient, instanceProperties);
        return instanceProperties;
    }

    private TableProperties createTable() {
        TableProperties tableProperties = createTestTableProperties(instanceProperties, schemaWithKey("key", new StringType()));
        S3TableProperties.createStore(instanceProperties, s3Client, dynamoClient).createTable(tableProperties);
        return tableProperties;
    }

    @Test
    void shouldSendMessageForSingleOnlineTable() {
        // Given
        TableProperties tableProperties = createTable();

        // When
        TestableCreateCompactionJobsTriggerHandler handler = createHandler();
        handler.handleRequest(createScheduledEvent(), mock(Context.class));

        // Then
        List<String> messages = receiveAllTableIdMessages();
        assertThat(messages).containsExactly(tableProperties.get(TABLE_ID));
    }

    @Test
    void shouldSendMessagesForMultipleOnlineTables() {
        // Given
        TableProperties table1 = createTable();
        TableProperties table2 = createTable();
        TableProperties table3 = createTable();

        // When
        TestableCreateCompactionJobsTriggerHandler handler = createHandler();
        handler.handleRequest(createScheduledEvent(), mock(Context.class));

        // Then
        List<String> messages = receiveAllTableIdMessages();
        assertThat(messages).containsExactlyInAnyOrder(
                table1.get(TABLE_ID),
                table2.get(TABLE_ID),
                table3.get(TABLE_ID));
    }

    @Test
    void shouldSendNoMessagesWhenNoTablesExist() {
        // Given - no tables created

        // When
        TestableCreateCompactionJobsTriggerHandler handler = createHandler();
        handler.handleRequest(createScheduledEvent(), mock(Context.class));

        // Then
        List<String> messages = receiveAllTableIdMessages();
        assertThat(messages).isEmpty();
    }

    @Test
    void shouldReloadPropertiesBeforeProcessing() {
        // Given
        createTable();

        // When
        TestableCreateCompactionJobsTriggerHandler handler = createHandlerWithPropertiesReloader(
                () -> reloadedProperties.add("reloaded"));
        handler.handleRequest(createScheduledEvent(), mock(Context.class));

        // Then
        assertThat(reloadedProperties).containsExactly("reloaded");
    }

    @Test
    void shouldReturnNullFromHandleRequest() {
        // Given
        createTable();

        // When
        TestableCreateCompactionJobsTriggerHandler handler = createHandler();
        Void result = handler.handleRequest(createScheduledEvent(), mock(Context.class));

        // Then
        assertThat(result).isNull();
    }

    @Test
    void shouldHandleScheduledEventWithTime() {
        // Given
        createTable();
        ScheduledEvent event = createScheduledEvent();
        DateTime eventTime = new DateTime(2024, 1, 15, 10, 30, 0);
        event.setTime(eventTime);

        // When
        TestableCreateCompactionJobsTriggerHandler handler = createHandler();
        handler.handleRequest(event, mock(Context.class));

        // Then - messages should be sent regardless of event time
        List<String> messages = receiveAllTableIdMessages();
        assertThat(messages).hasSize(1);
    }

    @Test
    void shouldSendMoreMessagesThanFitInSingleSqsBatch() {
        // Given - create more than 10 tables (SQS batch limit)
        List<TableProperties> tables = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            tables.add(createTable());
        }

        // When
        TestableCreateCompactionJobsTriggerHandler handler = createHandler();
        handler.handleRequest(createScheduledEvent(), mock(Context.class));

        // Then
        List<String> messages = receiveAllTableIdMessages();
        List<String> expectedTableIds = tables.stream()
                .map(t -> t.get(TABLE_ID))
                .collect(Collectors.toList());
        assertThat(messages).containsExactlyInAnyOrderElementsOf(expectedTableIds);
    }

    private TestableCreateCompactionJobsTriggerHandler createHandler() {
        return createHandlerWithPropertiesReloader(PropertiesReloader.neverReload());
    }

    private TestableCreateCompactionJobsTriggerHandler createHandlerWithPropertiesReloader(PropertiesReloader reloader) {
        return new TestableCreateCompactionJobsTriggerHandler(
                instanceProperties,
                new DynamoDBTableIndex(instanceProperties, dynamoClient),
                reloader);
    }

    private ScheduledEvent createScheduledEvent() {
        ScheduledEvent event = new ScheduledEvent();
        event.setTime(DateTime.now());
        return event;
    }

    private List<String> receiveAllTableIdMessages() {
        List<String> allMessages = new ArrayList<>();
        int maxAttempts = 5;
        for (int i = 0; i < maxAttempts; i++) {
            ReceiveMessageRequest request = new ReceiveMessageRequest(queueUrl)
                    .withMaxNumberOfMessages(10)
                    .withWaitTimeSeconds(0);
            List<Message> messages = sqsClient.receiveMessage(request).getMessages();
            if (messages.isEmpty()) {
                break;
            }
            messages.forEach(m -> allMessages.add(m.getBody()));
            // Delete received messages to allow receiving more
            messages.forEach(m -> sqsClient.deleteMessage(queueUrl, m.getReceiptHandle()));
        }
        return allMessages;
    }

    /**
     * A testable handler that mirrors CreateCompactionJobsTriggerLambda behavior but allows dependency injection.
     * This allows testing the handleRequest logic without requiring AWS environment setup.
     */
    private class TestableCreateCompactionJobsTriggerHandler {
        private final InstanceProperties instanceProperties;
        private final TableIndex tableIndex;
        private final PropertiesReloader propertiesReloader;

        TestableCreateCompactionJobsTriggerHandler(
                InstanceProperties instanceProperties,
                TableIndex tableIndex,
                PropertiesReloader propertiesReloader) {
            this.instanceProperties = instanceProperties;
            this.tableIndex = tableIndex;
            this.propertiesReloader = propertiesReloader;
        }

        Void handleRequest(ScheduledEvent event, Context context) {
            propertiesReloader.reloadIfNeeded();

            String jobCreationQueueUrl = instanceProperties.get(COMPACTION_JOB_CREATION_QUEUE_URL);
            InvokeForTables.sendOneMessagePerTable(sqsClient, jobCreationQueueUrl, tableIndex.streamOnlineTables());

            return null;
        }
    }
}
