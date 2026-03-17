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

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.compaction.core.job.creation.CreateJobsTestUtils.createInstanceProperties;
import static sleeper.compaction.core.job.creation.CreateJobsTestUtils.createSchema;
import static sleeper.compaction.core.job.creation.CreateJobsTestUtils.createTableProperties;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_QUEUE_URL;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;

public class CompactionBatchMessageSenderToSqsTest {

    @Test
    public void shouldConstructWithInstancePropertiesAndSqsClient() {
        // Given
        InstanceProperties instanceProperties = createInstanceProperties();
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL, "test-queue-url");
        FakeSqsClient sqsClient = new FakeSqsClient();

        // When
        CompactionBatchMessageSenderToSqs sender = new CompactionBatchMessageSenderToSqs(instanceProperties, sqsClient);

        // Then
        assertThat(sender).isNotNull();
    }

    @Test
    public void shouldSendMessageToCorrectQueue() {
        // Given
        InstanceProperties instanceProperties = createInstanceProperties();
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL, "test-queue-url");
        FakeSqsClient sqsClient = new FakeSqsClient();
        CompactionBatchMessageSenderToSqs sender = new CompactionBatchMessageSenderToSqs(instanceProperties, sqsClient);
        Schema schema = createSchema();
        TableProperties tableProperties = createTableProperties(schema, instanceProperties);
        tableProperties.set(TABLE_ID, "test-table");
        CompactionJobDispatchRequest dispatchRequest = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", Instant.parse("2024-11-18T12:00:00Z"));

        // When
        sender.sendMessage(dispatchRequest);

        // Then
        assertThat(sqsClient.sentMessages).hasSize(1);
        assertThat(sqsClient.sentMessages.get(0).getQueueUrl()).isEqualTo("test-queue-url");
        assertThat(sqsClient.sentMessages.get(0).getMessageBody()).contains("test-table");
        assertThat(sqsClient.sentMessages.get(0).getMessageBody()).contains("1731931200000");
    }

    @Test
    public void shouldSendMultipleMessagesToQueue() {
        // Given
        InstanceProperties instanceProperties = createInstanceProperties();
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL, "test-queue-url");
        FakeSqsClient sqsClient = new FakeSqsClient();
        CompactionBatchMessageSenderToSqs sender = new CompactionBatchMessageSenderToSqs(instanceProperties, sqsClient);
        Schema schema = createSchema();
        TableProperties tableProperties = createTableProperties(schema, instanceProperties);
        tableProperties.set(TABLE_ID, "test-table");
        CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-1", Instant.parse("2024-11-18T12:00:00Z"));
        CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-2", Instant.parse("2024-11-18T12:01:00Z"));

        // When
        sender.sendMessage(request1);
        sender.sendMessage(request2);

        // Then
        assertThat(sqsClient.sentMessages).hasSize(2);
        assertThat(sqsClient.sentMessages.get(0).getQueueUrl()).isEqualTo("test-queue-url");
        assertThat(sqsClient.sentMessages.get(1).getQueueUrl()).isEqualTo("test-queue-url");
    }

    private static class FakeSqsClient implements AmazonSQS {
        private final List<SendMessageRequest> sentMessages = new ArrayList<>();

        @Override
        public SendMessageResult sendMessage(String queueUrl, String messageBody) {
            sentMessages.add(new SendMessageRequest(queueUrl, messageBody));
            return new SendMessageResult().withMessageId("fake-message-id");
        }

        @Override
        public SendMessageResult sendMessage(SendMessageRequest sendMessageRequest) {
            sentMessages.add(sendMessageRequest);
            return new SendMessageResult().withMessageId("fake-message-id");
        }

        // All other methods throw UnsupportedOperationException
        @Override
        public void setEndpoint(String endpoint) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setRegion(com.amazonaws.regions.Region region) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.AddPermissionResult addPermission(com.amazonaws.services.sqs.model.AddPermissionRequest addPermissionRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.AddPermissionResult addPermission(String queueUrl, String label, java.util.List<String> aWSAccountIds, java.util.List<String> actions) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.CancelMessageMoveTaskResult cancelMessageMoveTask(com.amazonaws.services.sqs.model.CancelMessageMoveTaskRequest cancelMessageMoveTaskRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ChangeMessageVisibilityResult changeMessageVisibility(com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest changeMessageVisibilityRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ChangeMessageVisibilityResult changeMessageVisibility(String queueUrl, String receiptHandle, Integer visibilityTimeout) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchRequest changeMessageVisibilityBatchRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(String queueUrl, java.util.List<com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchRequestEntry> entries) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.CreateQueueResult createQueue(com.amazonaws.services.sqs.model.CreateQueueRequest createQueueRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.CreateQueueResult createQueue(String queueName) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteMessageResult deleteMessage(com.amazonaws.services.sqs.model.DeleteMessageRequest deleteMessageRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteMessageResult deleteMessage(String queueUrl, String receiptHandle) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteMessageBatchResult deleteMessageBatch(com.amazonaws.services.sqs.model.DeleteMessageBatchRequest deleteMessageBatchRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteMessageBatchResult deleteMessageBatch(String queueUrl, java.util.List<com.amazonaws.services.sqs.model.DeleteMessageBatchRequestEntry> entries) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteQueueResult deleteQueue(com.amazonaws.services.sqs.model.DeleteQueueRequest deleteQueueRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteQueueResult deleteQueue(String queueUrl) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.GetQueueAttributesResult getQueueAttributes(com.amazonaws.services.sqs.model.GetQueueAttributesRequest getQueueAttributesRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.GetQueueAttributesResult getQueueAttributes(String queueUrl, java.util.List<String> attributeNames) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.GetQueueUrlResult getQueueUrl(com.amazonaws.services.sqs.model.GetQueueUrlRequest getQueueUrlRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.GetQueueUrlResult getQueueUrl(String queueName) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesResult listDeadLetterSourceQueues(com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesRequest listDeadLetterSourceQueuesRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ListMessageMoveTasksResult listMessageMoveTasks(com.amazonaws.services.sqs.model.ListMessageMoveTasksRequest listMessageMoveTasksRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ListQueuesResult listQueues() {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ListQueuesResult listQueues(com.amazonaws.services.sqs.model.ListQueuesRequest listQueuesRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ListQueuesResult listQueues(String queueNamePrefix) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ListQueueTagsResult listQueueTags(com.amazonaws.services.sqs.model.ListQueueTagsRequest listQueueTagsRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ListQueueTagsResult listQueueTags(String queueUrl) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.PurgeQueueResult purgeQueue(com.amazonaws.services.sqs.model.PurgeQueueRequest purgeQueueRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ReceiveMessageResult receiveMessage(com.amazonaws.services.sqs.model.ReceiveMessageRequest receiveMessageRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.ReceiveMessageResult receiveMessage(String queueUrl) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.RemovePermissionResult removePermission(com.amazonaws.services.sqs.model.RemovePermissionRequest removePermissionRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.RemovePermissionResult removePermission(String queueUrl, String label) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.SendMessageBatchResult sendMessageBatch(com.amazonaws.services.sqs.model.SendMessageBatchRequest sendMessageBatchRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.SendMessageBatchResult sendMessageBatch(String queueUrl, java.util.List<com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry> entries) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.SetQueueAttributesResult setQueueAttributes(com.amazonaws.services.sqs.model.SetQueueAttributesRequest setQueueAttributesRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.SetQueueAttributesResult setQueueAttributes(String queueUrl, java.util.Map<String, String> attributes) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.StartMessageMoveTaskResult startMessageMoveTask(com.amazonaws.services.sqs.model.StartMessageMoveTaskRequest startMessageMoveTaskRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.TagQueueResult tagQueue(com.amazonaws.services.sqs.model.TagQueueRequest tagQueueRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.TagQueueResult tagQueue(String queueUrl, java.util.Map<String, String> tags) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.UntagQueueResult untagQueue(com.amazonaws.services.sqs.model.UntagQueueRequest untagQueueRequest) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.amazonaws.services.sqs.model.UntagQueueResult untagQueue(String queueUrl, java.util.List<String> tagKeys) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void shutdown() {
        }

        @Override
        public com.amazonaws.ResponseMetadata getCachedResponseMetadata(com.amazonaws.AmazonWebServiceRequest request) {
            throw new UnsupportedOperationException();
        }
    }
}
