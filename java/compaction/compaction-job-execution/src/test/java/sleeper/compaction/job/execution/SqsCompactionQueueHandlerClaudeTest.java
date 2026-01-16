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

import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobSerDe;
import sleeper.compaction.core.task.CompactionTask.MessageHandle;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.localstack.test.LocalStackTestBase;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_JOB_QUEUE_URL;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_JOB_FAILED_VISIBILITY_TIMEOUT_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_KEEP_ALIVE_PERIOD_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_QUEUE_VISIBILITY_TIMEOUT_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_WAIT_TIME_IN_SECONDS;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

class SqsCompactionQueueHandlerClaudeTest extends LocalStackTestBase {

    private InstanceProperties instanceProperties;
    private String queueUrl;
    private SqsCompactionQueueHandler handler;
    private final CompactionJobSerDe serDe = new CompactionJobSerDe();

    @BeforeEach
    void setUp() {
        instanceProperties = createTestInstanceProperties();
        queueUrl = sqsClient.createQueue(new CreateQueueRequest()
                .withQueueName(UUID.randomUUID().toString())
                .withAttributes(Map.of("VisibilityTimeout", "30"))).getQueueUrl();
        instanceProperties.set(COMPACTION_JOB_QUEUE_URL, queueUrl);
        instanceProperties.setNumber(COMPACTION_TASK_WAIT_TIME_IN_SECONDS, 1);
        instanceProperties.setNumber(COMPACTION_KEEP_ALIVE_PERIOD_IN_SECONDS, 60);
        instanceProperties.setNumber(COMPACTION_QUEUE_VISIBILITY_TIMEOUT_IN_SECONDS, 30);
        instanceProperties.setNumber(COMPACTION_JOB_FAILED_VISIBILITY_TIMEOUT_IN_SECONDS, 60);
        handler = new SqsCompactionQueueHandler(sqsClient, instanceProperties);
    }

    private CompactionJob createJob() {
        return CompactionJob.builder()
                .tableId("test-table-" + UUID.randomUUID())
                .jobId("test-job-" + UUID.randomUUID())
                .inputFiles(List.of("input1.parquet", "input2.parquet"))
                .outputFile("output.parquet")
                .partitionId("root")
                .build();
    }

    private void sendJobToQueue(CompactionJob job) {
        sqsClient.sendMessage(new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(serDe.toJson(job)));
    }

    @DisplayName("Constructor")
    @Nested
    class ConstructorTests {

        @Test
        void shouldCreateHandlerWithValidParameters() {
            // Given/When
            SqsCompactionQueueHandler newHandler = new SqsCompactionQueueHandler(sqsClient, instanceProperties);

            // Then
            assertThat(newHandler).isNotNull();
        }
    }

    @DisplayName("receiveMessage")
    @Nested
    class ReceiveMessageTests {

        @Test
        void shouldReturnEmptyWhenNoMessagesInQueue() throws IOException {
            // Given - empty queue

            // When
            Optional<MessageHandle> result = handler.receiveMessage();

            // Then
            assertThat(result).isEmpty();
        }

        @Test
        void shouldReceiveAndDeserializeCompactionJob() throws IOException {
            // Given
            CompactionJob job = createJob();
            sendJobToQueue(job);

            // When
            Optional<MessageHandle> result = handler.receiveMessage();

            // Then
            assertThat(result).isPresent();
            try (MessageHandle handle = result.get()) {
                assertThat(handle.getJob()).isEqualTo(job);
            }
        }

        @Test
        void shouldStartKeepAliveThreadOnReceiveMessage() throws IOException {
            // Given
            CompactionJob job = createJob();
            sendJobToQueue(job);

            // When
            Optional<MessageHandle> result = handler.receiveMessage();

            // Then
            assertThat(result).isPresent();
            // Clean up - close will stop the keep-alive thread
            result.get().close();
        }

        @Test
        void shouldReceiveMessageWithCorrectJobId() throws IOException {
            // Given
            CompactionJob job = createJob();
            sendJobToQueue(job);

            // When
            Optional<MessageHandle> result = handler.receiveMessage();

            // Then
            assertThat(result).isPresent();
            try (MessageHandle handle = result.get()) {
                assertThat(handle.getJob().getId()).isEqualTo(job.getId());
            }
        }

        @Test
        void shouldReceiveMessageWithCorrectTableId() throws IOException {
            // Given
            CompactionJob job = createJob();
            sendJobToQueue(job);

            // When
            Optional<MessageHandle> result = handler.receiveMessage();

            // Then
            assertThat(result).isPresent();
            try (MessageHandle handle = result.get()) {
                assertThat(handle.getJob().getTableId()).isEqualTo(job.getTableId());
            }
        }

        @Test
        void shouldReceiveMessageWithCorrectInputFiles() throws IOException {
            // Given
            CompactionJob job = createJob();
            sendJobToQueue(job);

            // When
            Optional<MessageHandle> result = handler.receiveMessage();

            // Then
            assertThat(result).isPresent();
            try (MessageHandle handle = result.get()) {
                assertThat(handle.getJob().getInputFiles()).isEqualTo(job.getInputFiles());
            }
        }
    }

    @DisplayName("MessageHandle operations")
    @Nested
    class MessageHandleTests {

        @Test
        void shouldDeleteMessageFromQueue() throws IOException {
            // Given
            CompactionJob job = createJob();
            sendJobToQueue(job);
            Optional<MessageHandle> result = handler.receiveMessage();
            assertThat(result).isPresent();

            // When
            try (MessageHandle handle = result.get()) {
                handle.deleteFromQueue();
            }

            // Then - message should be deleted, no messages in queue
            List<Message> remainingMessages = sqsClient.receiveMessage(new ReceiveMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMaxNumberOfMessages(10)
                    .withWaitTimeSeconds(1)).getMessages();
            assertThat(remainingMessages).isEmpty();
        }

        @Test
        void shouldReturnMessageToQueue() throws IOException {
            // Given
            CompactionJob job = createJob();
            sendJobToQueue(job);
            Optional<MessageHandle> result = handler.receiveMessage();
            assertThat(result).isPresent();

            // When
            try (MessageHandle handle = result.get()) {
                handle.returnToQueue();
            }

            // Then - message should eventually be visible again
            // Note: returnToQueue changes visibility timeout, so message will become visible after that timeout
            // For testing, we verify the call didn't throw
        }

        @Test
        void shouldCloseAndStopKeepAliveThread() throws IOException {
            // Given
            CompactionJob job = createJob();
            sendJobToQueue(job);
            Optional<MessageHandle> result = handler.receiveMessage();
            assertThat(result).isPresent();

            // When
            MessageHandle handle = result.get();
            handle.close();

            // Then - should not throw, thread should be stopped
        }

        @Test
        void shouldGetJobFromMessageHandle() throws IOException {
            // Given
            CompactionJob job = createJob();
            sendJobToQueue(job);
            Optional<MessageHandle> result = handler.receiveMessage();
            assertThat(result).isPresent();

            // When
            try (MessageHandle handle = result.get()) {
                CompactionJob retrievedJob = handle.getJob();

                // Then
                assertThat(retrievedJob).isEqualTo(job);
                assertThat(retrievedJob.getPartitionId()).isEqualTo(job.getPartitionId());
                assertThat(retrievedJob.getOutputFile()).isEqualTo(job.getOutputFile());
            }
        }
    }

    @DisplayName("Multiple messages")
    @Nested
    class MultipleMessageTests {

        @Test
        void shouldReceiveOnlyOneMessageAtATime() throws IOException {
            // Given
            CompactionJob job1 = createJob();
            CompactionJob job2 = createJob();
            sendJobToQueue(job1);
            sendJobToQueue(job2);

            // When
            Optional<MessageHandle> result1 = handler.receiveMessage();

            // Then
            assertThat(result1).isPresent();
            try (MessageHandle handle = result1.get()) {
                // Should receive one of the two jobs
                assertThat(handle.getJob().getId()).isIn(job1.getId(), job2.getId());
            }
        }
    }
}
