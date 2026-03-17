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

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobSerDe;
import sleeper.compaction.core.task.CompactionTask.MessageHandle;
import sleeper.core.properties.instance.InstanceProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_JOB_QUEUE_URL;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_JOB_FAILED_VISIBILITY_TIMEOUT_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_KEEP_ALIVE_PERIOD_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_QUEUE_VISIBILITY_TIMEOUT_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_WAIT_TIME_IN_SECONDS;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

class SqsCompactionQueueHandlerIT {

    @Nested
    @DisplayName("Delete message from queue")
    class DeleteMessageFromQueue {

        @Test
        void shouldCallDeleteMessageWithCorrectParameters() throws Exception {
            // Given
            TestSqsClient sqsClient = new TestSqsClient();
            InstanceProperties instanceProperties = createInstanceProperties();
            SqsCompactionQueueHandler handler = new SqsCompactionQueueHandler(sqsClient, instanceProperties);

            CompactionJob job = CompactionJob.builder()
                    .tableId("test-table")
                    .jobId("test-job-1")
                    .partitionId("root")
                    .inputFiles(List.of("file1.parquet", "file2.parquet"))
                    .outputFile("output.parquet")
                    .build();
            String jobJson = new CompactionJobSerDe().toJson(job);

            sqsClient.addMessageToQueue(jobJson);
            Optional<MessageHandle> messageHandle = handler.receiveMessage();
            assertThat(messageHandle).isPresent();

            // When
            messageHandle.get().deleteFromQueue();

            // Then
            assertThat(sqsClient.getDeleteMessageCalls()).hasSize(1);
            TestSqsClient.DeleteMessageCall call = sqsClient.getDeleteMessageCalls().get(0);
            assertThat(call.queueUrl).isEqualTo("https://sqs.test-region.amazonaws.com/test-queue");
            assertThat(call.receiptHandle).isEqualTo("receipt-handle-0");
        }

        @Test
        void shouldWrapActionExceptionInRuntimeException() throws Exception {
            // Given
            TestSqsClient sqsClient = new TestSqsClient();
            sqsClient.setDeleteMessageShouldFail(true);
            InstanceProperties instanceProperties = createInstanceProperties();
            SqsCompactionQueueHandler handler = new SqsCompactionQueueHandler(sqsClient, instanceProperties);

            CompactionJob job = CompactionJob.builder()
                    .tableId("test-table")
                    .jobId("test-job-1")
                    .partitionId("root")
                    .inputFiles(List.of("file1.parquet", "file2.parquet"))
                    .outputFile("output.parquet")
                    .build();
            String jobJson = new CompactionJobSerDe().toJson(job);

            sqsClient.addMessageToQueue(jobJson);
            Optional<MessageHandle> messageHandle = handler.receiveMessage();
            assertThat(messageHandle).isPresent();

            // When / Then
            assertThat(messageHandle.get())
                    .extracting(handle -> {
                        try {
                            handle.deleteFromQueue();
                            return null;
                        } catch (RuntimeException e) {
                            return e;
                        }
                    })
                    .isInstanceOf(RuntimeException.class)
                    .extracting(Throwable::getCause)
                    .hasFieldOrPropertyWithValue("class", sleeper.job.common.action.ActionException.class);
        }
    }

    @Nested
    @DisplayName("Return message to queue")
    class ReturnMessageToQueue {

        @Test
        void shouldCallChangeMessageVisibilityWithFailedTimeout() throws Exception {
            // Given
            TestSqsClient sqsClient = new TestSqsClient();
            InstanceProperties instanceProperties = createInstanceProperties();
            SqsCompactionQueueHandler handler = new SqsCompactionQueueHandler(sqsClient, instanceProperties);

            CompactionJob job = CompactionJob.builder()
                    .tableId("test-table")
                    .jobId("test-job-1")
                    .partitionId("root")
                    .inputFiles(List.of("file1.parquet", "file2.parquet"))
                    .outputFile("output.parquet")
                    .build();
            String jobJson = new CompactionJobSerDe().toJson(job);

            sqsClient.addMessageToQueue(jobJson);
            Optional<MessageHandle> messageHandle = handler.receiveMessage();
            assertThat(messageHandle).isPresent();

            // When
            messageHandle.get().returnToQueue();

            // Then
            assertThat(sqsClient.getChangeVisibilityCalls()).hasSize(1);
            TestSqsClient.ChangeVisibilityCall call = sqsClient.getChangeVisibilityCalls().get(0);
            assertThat(call.queueUrl).isEqualTo("https://sqs.test-region.amazonaws.com/test-queue");
            assertThat(call.visibilityTimeout).isEqualTo(5);
        }
    }

    private static InstanceProperties createInstanceProperties() {
        InstanceProperties properties = createTestInstanceProperties();
        properties.set(COMPACTION_JOB_QUEUE_URL, "https://sqs.test-region.amazonaws.com/test-queue");
        properties.setNumber(COMPACTION_TASK_WAIT_TIME_IN_SECONDS, 1);
        properties.setNumber(COMPACTION_KEEP_ALIVE_PERIOD_IN_SECONDS, 10);
        properties.setNumber(COMPACTION_QUEUE_VISIBILITY_TIMEOUT_IN_SECONDS, 30);
        properties.setNumber(COMPACTION_JOB_FAILED_VISIBILITY_TIMEOUT_IN_SECONDS, 5);
        return properties;
    }

    private static class TestSqsClient implements AmazonSQS {
        private final List<String> queueMessages = new ArrayList<>();
        private final List<ChangeVisibilityCall> changeVisibilityCalls = new ArrayList<>();
        private final List<DeleteMessageCall> deleteMessageCalls = new ArrayList<>();
        private int messageCounter = 0;
        private boolean deleteMessageShouldFail = false;

        void addMessageToQueue(String messageBody) {
            queueMessages.add(messageBody);
        }

        void setDeleteMessageShouldFail(boolean shouldFail) {
            this.deleteMessageShouldFail = shouldFail;
        }

        List<ChangeVisibilityCall> getChangeVisibilityCalls() {
            return changeVisibilityCalls;
        }

        List<DeleteMessageCall> getDeleteMessageCalls() {
            return deleteMessageCalls;
        }

        @Override
        public ReceiveMessageResult receiveMessage(ReceiveMessageRequest request) {
            ReceiveMessageResult result = new ReceiveMessageResult();
            if (!queueMessages.isEmpty()) {
                String body = queueMessages.remove(0);
                Message message = new Message()
                        .withBody(body)
                        .withReceiptHandle("receipt-handle-" + messageCounter++);
                result.withMessages(message);
            }
            return result;
        }

        @Override
        public ChangeMessageVisibilityResult changeMessageVisibility(String queueUrl, String receiptHandle, Integer visibilityTimeout) {
            changeVisibilityCalls.add(new ChangeVisibilityCall(queueUrl, receiptHandle, visibilityTimeout));
            return new ChangeMessageVisibilityResult();
        }

        @Override
        public DeleteMessageResult deleteMessage(String queueUrl, String receiptHandle) {
            if (deleteMessageShouldFail) {
                throw new com.amazonaws.services.sqs.model.AmazonSQSException("Simulated SQS failure");
            }
            deleteMessageCalls.add(new DeleteMessageCall(queueUrl, receiptHandle));
            return new DeleteMessageResult();
        }

        @Override
        public ChangeMessageVisibilityResult changeMessageVisibility(ChangeMessageVisibilityRequest request) {
            return changeMessageVisibility(request.getQueueUrl(), request.getReceiptHandle(), request.getVisibilityTimeout());
        }

        static class ChangeVisibilityCall {
            final String queueUrl;
            final String receiptHandle;
            final int visibilityTimeout;

            ChangeVisibilityCall(String queueUrl, String receiptHandle, int visibilityTimeout) {
                this.queueUrl = queueUrl;
                this.receiptHandle = receiptHandle;
                this.visibilityTimeout = visibilityTimeout;
            }
        }

        static class DeleteMessageCall {
            final String queueUrl;
            final String receiptHandle;

            DeleteMessageCall(String queueUrl, String receiptHandle) {
                this.queueUrl = queueUrl;
                this.receiptHandle = receiptHandle;
            }
        }

        // All other methods throw UnsupportedOperationException as they are not needed for this test
        @Override public void setEndpoint(String endpoint) { throw new java.lang.UnsupportedOperationException(); }
        @Override public void setRegion(Region region) { throw new java.lang.UnsupportedOperationException(); }
        @Override public AddPermissionResult addPermission(AddPermissionRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public AddPermissionResult addPermission(String queueUrl, String label, List<String> aWSAccountIds, List<String> actions) { throw new java.lang.UnsupportedOperationException(); }
        @Override public ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(String queueUrl, List<ChangeMessageVisibilityBatchRequestEntry> entries) { throw new java.lang.UnsupportedOperationException(); }
        @Override public CreateQueueResult createQueue(CreateQueueRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public CreateQueueResult createQueue(String queueName) { throw new java.lang.UnsupportedOperationException(); }
        @Override public DeleteMessageResult deleteMessage(DeleteMessageRequest request) { return deleteMessage(request.getQueueUrl(), request.getReceiptHandle()); }
        @Override public DeleteMessageBatchResult deleteMessageBatch(DeleteMessageBatchRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public DeleteMessageBatchResult deleteMessageBatch(String queueUrl, List<DeleteMessageBatchRequestEntry> entries) { throw new java.lang.UnsupportedOperationException(); }
        @Override public DeleteQueueResult deleteQueue(DeleteQueueRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public DeleteQueueResult deleteQueue(String queueUrl) { throw new java.lang.UnsupportedOperationException(); }
        @Override public GetQueueAttributesResult getQueueAttributes(GetQueueAttributesRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public GetQueueAttributesResult getQueueAttributes(String queueUrl, List<String> attributeNames) { throw new java.lang.UnsupportedOperationException(); }
        @Override public GetQueueUrlResult getQueueUrl(GetQueueUrlRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public GetQueueUrlResult getQueueUrl(String queueName) { throw new java.lang.UnsupportedOperationException(); }
        @Override public ListDeadLetterSourceQueuesResult listDeadLetterSourceQueues(ListDeadLetterSourceQueuesRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public ListQueuesResult listQueues(ListQueuesRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public ListQueuesResult listQueues() { throw new java.lang.UnsupportedOperationException(); }
        @Override public ListQueuesResult listQueues(String queueNamePrefix) { throw new java.lang.UnsupportedOperationException(); }
        @Override public ListQueueTagsResult listQueueTags(ListQueueTagsRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public ListQueueTagsResult listQueueTags(String queueUrl) { throw new java.lang.UnsupportedOperationException(); }
        @Override public PurgeQueueResult purgeQueue(PurgeQueueRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public ReceiveMessageResult receiveMessage(String queueUrl) { throw new java.lang.UnsupportedOperationException(); }
        @Override public RemovePermissionResult removePermission(RemovePermissionRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public RemovePermissionResult removePermission(String queueUrl, String label) { throw new java.lang.UnsupportedOperationException(); }
        @Override public SendMessageResult sendMessage(SendMessageRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public SendMessageResult sendMessage(String queueUrl, String messageBody) { throw new java.lang.UnsupportedOperationException(); }
        @Override public SendMessageBatchResult sendMessageBatch(SendMessageBatchRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public SendMessageBatchResult sendMessageBatch(String queueUrl, List<SendMessageBatchRequestEntry> entries) { throw new java.lang.UnsupportedOperationException(); }
        @Override public SetQueueAttributesResult setQueueAttributes(SetQueueAttributesRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public SetQueueAttributesResult setQueueAttributes(String queueUrl, Map<String, String> attributes) { throw new java.lang.UnsupportedOperationException(); }
        @Override public TagQueueResult tagQueue(TagQueueRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public TagQueueResult tagQueue(String queueUrl, Map<String, String> tags) { throw new java.lang.UnsupportedOperationException(); }
        @Override public UntagQueueResult untagQueue(UntagQueueRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public UntagQueueResult untagQueue(String queueUrl, List<String> tagKeys) { throw new java.lang.UnsupportedOperationException(); }
        @Override public StartMessageMoveTaskResult startMessageMoveTask(StartMessageMoveTaskRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public CancelMessageMoveTaskResult cancelMessageMoveTask(CancelMessageMoveTaskRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public ListMessageMoveTasksResult listMessageMoveTasks(ListMessageMoveTasksRequest request) { throw new java.lang.UnsupportedOperationException(); }
        @Override public void shutdown() { throw new java.lang.UnsupportedOperationException(); }
        @Override public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest request) { throw new java.lang.UnsupportedOperationException(); }
    }
}
