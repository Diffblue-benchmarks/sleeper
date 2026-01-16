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
package sleeper.compaction.job.creation;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.creation.CreateCompactionJobs;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequestSerDe;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.localstack.test.LocalStackTestBase;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_QUEUE_URL;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;

public class CompactionBatchMessageSenderToSqsClaudeTest extends LocalStackTestBase {

    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final Schema schema = schemaWithKey("key");
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
    private final CompactionJobDispatchRequestSerDe serDe = new CompactionJobDispatchRequestSerDe();
    private String queueUrl;

    @BeforeEach
    void setUp() {
        queueUrl = sqsClient.createQueue("compaction-pending-" + UUID.randomUUID()).getQueueUrl();
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL, queueUrl);
    }

    @Test
    void shouldCreateSenderImplementingBatchMessageSenderInterface() {
        // When
        CreateCompactionJobs.BatchMessageSender sender = new CompactionBatchMessageSenderToSqs(
                instanceProperties, sqsClient);

        // Then
        assertThat(sender).isInstanceOf(CreateCompactionJobs.BatchMessageSender.class);
    }

    @Test
    void shouldSendDispatchRequestToSqsQueue() {
        // Given
        CompactionBatchMessageSenderToSqs sender = new CompactionBatchMessageSenderToSqs(
                instanceProperties, sqsClient);
        Instant createTime = Instant.parse("2024-01-15T10:30:00Z");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-123", createTime);

        // When
        sender.sendMessage(request);

        // Then
        List<Message> messages = receiveMessages();
        assertThat(messages).hasSize(1);
        CompactionJobDispatchRequest receivedRequest = serDe.fromJson(messages.get(0).getBody());
        assertThat(receivedRequest).isEqualTo(request);
    }

    @Test
    void shouldSendRequestWithCorrectTableId() {
        // Given
        CompactionBatchMessageSenderToSqs sender = new CompactionBatchMessageSenderToSqs(
                instanceProperties, sqsClient);
        Instant createTime = Instant.parse("2024-01-15T10:30:00Z");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-456", createTime);

        // When
        sender.sendMessage(request);

        // Then
        List<Message> messages = receiveMessages();
        CompactionJobDispatchRequest receivedRequest = serDe.fromJson(messages.get(0).getBody());
        assertThat(receivedRequest.getTableId()).isEqualTo(tableProperties.get(sleeper.core.properties.table.TableProperty.TABLE_ID));
    }

    @Test
    void shouldSendRequestWithCorrectBatchKey() {
        // Given
        CompactionBatchMessageSenderToSqs sender = new CompactionBatchMessageSenderToSqs(
                instanceProperties, sqsClient);
        Instant createTime = Instant.parse("2024-01-15T10:30:00Z");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch-id", createTime);

        // When
        sender.sendMessage(request);

        // Then
        List<Message> messages = receiveMessages();
        CompactionJobDispatchRequest receivedRequest = serDe.fromJson(messages.get(0).getBody());
        assertThat(receivedRequest.getBatchKey()).contains("test-batch-id");
    }

    @Test
    void shouldSendRequestWithCorrectCreateTime() {
        // Given
        CompactionBatchMessageSenderToSqs sender = new CompactionBatchMessageSenderToSqs(
                instanceProperties, sqsClient);
        Instant createTime = Instant.parse("2024-06-20T14:45:30Z");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-time-test", createTime);

        // When
        sender.sendMessage(request);

        // Then
        List<Message> messages = receiveMessages();
        CompactionJobDispatchRequest receivedRequest = serDe.fromJson(messages.get(0).getBody());
        assertThat(receivedRequest.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void shouldSendMultipleMessagesIndependently() {
        // Given
        CompactionBatchMessageSenderToSqs sender = new CompactionBatchMessageSenderToSqs(
                instanceProperties, sqsClient);
        Instant time1 = Instant.parse("2024-01-15T10:00:00Z");
        Instant time2 = Instant.parse("2024-01-15T11:00:00Z");
        CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-1", time1);
        CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-2", time2);

        // When
        sender.sendMessage(request1);
        sender.sendMessage(request2);

        // Then
        List<Message> messages = receiveAllMessages();
        assertThat(messages).hasSize(2);
        List<CompactionJobDispatchRequest> receivedRequests = messages.stream()
                .map(m -> serDe.fromJson(m.getBody()))
                .toList();
        assertThat(receivedRequests).containsExactlyInAnyOrder(request1, request2);
    }

    @Test
    void shouldSendToConfiguredQueueUrl() {
        // Given - create a second queue and reconfigure properties
        String secondQueueUrl = sqsClient.createQueue("second-queue-" + UUID.randomUUID()).getQueueUrl();
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL, secondQueueUrl);
        CompactionBatchMessageSenderToSqs sender = new CompactionBatchMessageSenderToSqs(
                instanceProperties, sqsClient);
        Instant createTime = Instant.parse("2024-01-15T10:30:00Z");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-queue-test", createTime);

        // When
        sender.sendMessage(request);

        // Then - message should be in second queue, not original queue
        List<Message> messagesInOriginalQueue = receiveMessages();
        assertThat(messagesInOriginalQueue).isEmpty();

        List<Message> messagesInSecondQueue = sqsClient.receiveMessage(new ReceiveMessageRequest()
                .withQueueUrl(secondQueueUrl)
                .withMaxNumberOfMessages(10)
                .withWaitTimeSeconds(1)).getMessages();
        assertThat(messagesInSecondQueue).hasSize(1);
    }

    @Test
    void shouldSerializeDispatchRequestToValidJson() {
        // Given
        CompactionBatchMessageSenderToSqs sender = new CompactionBatchMessageSenderToSqs(
                instanceProperties, sqsClient);
        Instant createTime = Instant.parse("2024-01-15T10:30:00Z");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "json-test-batch", createTime);

        // When
        sender.sendMessage(request);

        // Then - verify the message body is valid JSON that can be deserialized
        List<Message> messages = receiveMessages();
        String messageBody = messages.get(0).getBody();
        CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(messageBody);
        assertThat(deserializedRequest.getTableId()).isEqualTo(request.getTableId());
        assertThat(deserializedRequest.getBatchKey()).isEqualTo(request.getBatchKey());
        assertThat(deserializedRequest.getCreateTime()).isEqualTo(request.getCreateTime());
    }

    private List<Message> receiveMessages() {
        return sqsClient.receiveMessage(new ReceiveMessageRequest()
                .withQueueUrl(queueUrl)
                .withMaxNumberOfMessages(10)
                .withWaitTimeSeconds(1)).getMessages();
    }

    private List<Message> receiveAllMessages() {
        return sqsClient.receiveMessage(new ReceiveMessageRequest()
                .withQueueUrl(queueUrl)
                .withMaxNumberOfMessages(10)
                .withWaitTimeSeconds(2)).getMessages();
    }
}
