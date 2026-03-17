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
package sleeper.compaction.job.execution;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobSerDe;
import sleeper.compaction.core.task.CompactionTask.MessageHandle;
import sleeper.core.properties.instance.InstanceProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_JOB_QUEUE_URL;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_JOB_FAILED_VISIBILITY_TIMEOUT_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_KEEP_ALIVE_PERIOD_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_QUEUE_VISIBILITY_TIMEOUT_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_WAIT_TIME_IN_SECONDS;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

@DisplayName("SqsCompactionQueueHandler")
public class SqsCompactionQueueHandlerTest {

    @Test
    void shouldReturnEmptyWhenNoMessagesReceived() throws IOException {
        // Given
        InstanceProperties instanceProperties = createTestInstanceProperties();
        instanceProperties.set(COMPACTION_JOB_QUEUE_URL, "test-queue-url");
        instanceProperties.setNumber(COMPACTION_TASK_WAIT_TIME_IN_SECONDS, 5);
        instanceProperties.setNumber(COMPACTION_KEEP_ALIVE_PERIOD_IN_SECONDS, 10);
        instanceProperties.setNumber(COMPACTION_QUEUE_VISIBILITY_TIMEOUT_IN_SECONDS, 60);
        instanceProperties.setNumber(COMPACTION_JOB_FAILED_VISIBILITY_TIMEOUT_IN_SECONDS, 30);

        TestSqsClient sqsClient = new TestSqsClient();
        SqsCompactionQueueHandler handler = new SqsCompactionQueueHandler(sqsClient, instanceProperties);

        // When
        Optional<MessageHandle> result = handler.receiveMessage();

        // Then
        assertThat(result).isEmpty();
        assertThat(sqsClient.lastReceiveRequest).isNotNull();
        assertThat(sqsClient.lastReceiveRequest.getQueueUrl()).isEqualTo("test-queue-url");
        assertThat(sqsClient.lastReceiveRequest.getMaxNumberOfMessages()).isEqualTo(1);
        assertThat(sqsClient.lastReceiveRequest.getWaitTimeSeconds()).isEqualTo(5);
    }

    @Test
    void shouldReturnMessageHandleWhenMessageReceived() throws IOException {
        // Given
        InstanceProperties instanceProperties = createTestInstanceProperties();
        instanceProperties.set(COMPACTION_JOB_QUEUE_URL, "test-queue-url");
        instanceProperties.setNumber(COMPACTION_TASK_WAIT_TIME_IN_SECONDS, 0);
        instanceProperties.setNumber(COMPACTION_KEEP_ALIVE_PERIOD_IN_SECONDS, 1);
        instanceProperties.setNumber(COMPACTION_QUEUE_VISIBILITY_TIMEOUT_IN_SECONDS, 60);
        instanceProperties.setNumber(COMPACTION_JOB_FAILED_VISIBILITY_TIMEOUT_IN_SECONDS, 30);

        CompactionJob job = CompactionJob.builder()
                .tableId("test-table")
                .jobId("test-job-1")
                .partitionId("root")
                .inputFiles(List.of("file1.parquet", "file2.parquet"))
                .outputFile("output.parquet")
                .build();
        String jobJson = new CompactionJobSerDe().toJson(job);

        TestSqsClient sqsClient = new TestSqsClient();
        sqsClient.addMessage(jobJson, "receipt-handle-123");
        SqsCompactionQueueHandler handler = new SqsCompactionQueueHandler(sqsClient, instanceProperties);

        // When
        Optional<MessageHandle> result = handler.receiveMessage();

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getJob()).isEqualTo(job);
    }

    @Test
    void shouldDeserializeJobCorrectly() throws IOException {
        // Given
        InstanceProperties instanceProperties = createTestInstanceProperties();
        instanceProperties.set(COMPACTION_JOB_QUEUE_URL, "test-queue-url");
        instanceProperties.setNumber(COMPACTION_TASK_WAIT_TIME_IN_SECONDS, 0);
        instanceProperties.setNumber(COMPACTION_KEEP_ALIVE_PERIOD_IN_SECONDS, 1);
        instanceProperties.setNumber(COMPACTION_QUEUE_VISIBILITY_TIMEOUT_IN_SECONDS, 60);
        instanceProperties.setNumber(COMPACTION_JOB_FAILED_VISIBILITY_TIMEOUT_IN_SECONDS, 30);

        CompactionJob job = CompactionJob.builder()
                .tableId("another-table")
                .jobId("job-456")
                .partitionId("partition-2")
                .inputFiles(List.of("input1.parquet", "input2.parquet", "input3.parquet"))
                .outputFile("merged.parquet")
                .build();
        String jobJson = new CompactionJobSerDe().toJson(job);

        TestSqsClient sqsClient = new TestSqsClient();
        sqsClient.addMessage(jobJson, "receipt-handle-456");
        SqsCompactionQueueHandler handler = new SqsCompactionQueueHandler(sqsClient, instanceProperties);

        // When
        Optional<MessageHandle> result = handler.receiveMessage();

        // Then
        assertThat(result).isPresent();
        CompactionJob receivedJob = result.get().getJob();
        assertThat(receivedJob.getId()).isEqualTo("job-456");
        assertThat(receivedJob.getTableId()).isEqualTo("another-table");
        assertThat(receivedJob.getPartitionId()).isEqualTo("partition-2");
        assertThat(receivedJob.getInputFiles()).containsExactly("input1.parquet", "input2.parquet", "input3.parquet");
        assertThat(receivedJob.getOutputFile()).isEqualTo("merged.parquet");
    }

    @Test
    void shouldCloseMessageHandleWithoutException() throws IOException {
        // Given
        InstanceProperties instanceProperties = createTestInstanceProperties();
        instanceProperties.set(COMPACTION_JOB_QUEUE_URL, "test-queue-url");
        instanceProperties.setNumber(COMPACTION_TASK_WAIT_TIME_IN_SECONDS, 0);
        instanceProperties.setNumber(COMPACTION_KEEP_ALIVE_PERIOD_IN_SECONDS, 1);
        instanceProperties.setNumber(COMPACTION_QUEUE_VISIBILITY_TIMEOUT_IN_SECONDS, 60);
        instanceProperties.setNumber(COMPACTION_JOB_FAILED_VISIBILITY_TIMEOUT_IN_SECONDS, 30);

        CompactionJob job = CompactionJob.builder()
                .tableId("test-table")
                .jobId("job-789")
                .partitionId("root")
                .inputFiles(List.of("file1.parquet"))
                .outputFile("output.parquet")
                .build();
        String jobJson = new CompactionJobSerDe().toJson(job);

        TestSqsClient sqsClient = new TestSqsClient();
        sqsClient.addMessage(jobJson, "receipt-handle-789");
        SqsCompactionQueueHandler handler = new SqsCompactionQueueHandler(sqsClient, instanceProperties);
        Optional<MessageHandle> result = handler.receiveMessage();

        // When
        result.get().close();

        // Then - no exception thrown
        assertThat(result).isPresent();
    }

    /**
     * Test double for AmazonSQS that doesn't require actual AWS infrastructure.
     */
    private static class TestSqsClient implements AmazonSQS {
        private final List<Message> messagesToReturn = new ArrayList<>();
        ReceiveMessageRequest lastReceiveRequest;

        void addMessage(String body, String receiptHandle) {
            Message message = new Message();
            message.setBody(body);
            message.setReceiptHandle(receiptHandle);
            messagesToReturn.add(message);
        }

        @Override
        public ReceiveMessageResult receiveMessage(ReceiveMessageRequest request) {
            lastReceiveRequest = request;
            ReceiveMessageResult result = new ReceiveMessageResult();
            if (!messagesToReturn.isEmpty()) {
                result.setMessages(List.of(messagesToReturn.remove(0)));
            } else {
                result.setMessages(List.of());
            }
            return result;
        }

        @Override
        public void setEndpoint(String endpoint) {
        }

        @Override
        public void setRegion(com.amazonaws.regions.Region region) {
        }

        @Override
        public com.amazonaws.services.sqs.model.ChangeMessageVisibilityResult changeMessageVisibility(
                String queueUrl, String receiptHandle, Integer visibilityTimeout) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ChangeMessageVisibilityResult changeMessageVisibility(
                com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(
                com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(
                String queueUrl, List<com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchRequestEntry> entries) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.CreateQueueResult createQueue(String queueName) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.CreateQueueResult createQueue(
                com.amazonaws.services.sqs.model.CreateQueueRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteMessageResult deleteMessage(String queueUrl, String receiptHandle) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteMessageResult deleteMessage(
                com.amazonaws.services.sqs.model.DeleteMessageRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteMessageBatchResult deleteMessageBatch(
                com.amazonaws.services.sqs.model.DeleteMessageBatchRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteMessageBatchResult deleteMessageBatch(
                String queueUrl, List<com.amazonaws.services.sqs.model.DeleteMessageBatchRequestEntry> entries) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteQueueResult deleteQueue(String queueUrl) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.DeleteQueueResult deleteQueue(
                com.amazonaws.services.sqs.model.DeleteQueueRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.GetQueueAttributesResult getQueueAttributes(String queueUrl, List<String> attributeNames) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.GetQueueAttributesResult getQueueAttributes(
                com.amazonaws.services.sqs.model.GetQueueAttributesRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.GetQueueUrlResult getQueueUrl(String queueName) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.GetQueueUrlResult getQueueUrl(
                com.amazonaws.services.sqs.model.GetQueueUrlRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesResult listDeadLetterSourceQueues(
                com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ListQueuesResult listQueues() {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ListQueuesResult listQueues(String queueNamePrefix) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ListQueuesResult listQueues(
                com.amazonaws.services.sqs.model.ListQueuesRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ListQueueTagsResult listQueueTags(String queueUrl) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ListQueueTagsResult listQueueTags(
                com.amazonaws.services.sqs.model.ListQueueTagsRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.PurgeQueueResult purgeQueue(
                com.amazonaws.services.sqs.model.PurgeQueueRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ReceiveMessageResult receiveMessage(String queueUrl) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.RemovePermissionResult removePermission(String queueUrl, String label) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.RemovePermissionResult removePermission(
                com.amazonaws.services.sqs.model.RemovePermissionRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.SendMessageResult sendMessage(String queueUrl, String messageBody) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.SendMessageResult sendMessage(
                com.amazonaws.services.sqs.model.SendMessageRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.SendMessageBatchResult sendMessageBatch(
                com.amazonaws.services.sqs.model.SendMessageBatchRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.SendMessageBatchResult sendMessageBatch(
                String queueUrl, List<com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry> entries) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.SetQueueAttributesResult setQueueAttributes(
                String queueUrl, java.util.Map<String, String> attributes) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.SetQueueAttributesResult setQueueAttributes(
                com.amazonaws.services.sqs.model.SetQueueAttributesRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.TagQueueResult tagQueue(String queueUrl, java.util.Map<String, String> tags) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.TagQueueResult tagQueue(
                com.amazonaws.services.sqs.model.TagQueueRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.UntagQueueResult untagQueue(String queueUrl, List<String> tagKeys) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.UntagQueueResult untagQueue(
                com.amazonaws.services.sqs.model.UntagQueueRequest request) {
            return null;
        }

        @Override
        public void shutdown() {
        }

        @Override
        public com.amazonaws.ResponseMetadata getCachedResponseMetadata(com.amazonaws.AmazonWebServiceRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.AddPermissionResult addPermission(
                com.amazonaws.services.sqs.model.AddPermissionRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.AddPermissionResult addPermission(
                String queueUrl, String label, List<String> aWSAccountIds, List<String> actions) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.CancelMessageMoveTaskResult cancelMessageMoveTask(
                com.amazonaws.services.sqs.model.CancelMessageMoveTaskRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.ListMessageMoveTasksResult listMessageMoveTasks(
                com.amazonaws.services.sqs.model.ListMessageMoveTasksRequest request) {
            return null;
        }

        @Override
        public com.amazonaws.services.sqs.model.StartMessageMoveTaskResult startMessageMoveTask(
                com.amazonaws.services.sqs.model.StartMessageMoveTaskRequest request) {
            return null;
        }

    }
}
