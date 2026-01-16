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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.ecs.EcsClient;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobCommitterOrSendToLambda;
import sleeper.compaction.core.job.commit.CompactionCommitMessageSerDe;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.commit.StateStoreCommitRequestSerDe;
import sleeper.core.statestore.testutils.FixedStateStoreProvider;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.localstack.test.LocalStackTestBase;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_COMMIT_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.STATESTORE_COMMITTER_QUEUE_URL;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_ECS_LAUNCHTYPE;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_ASYNC_BATCHING;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_COMMIT_ASYNC;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;

/**
 * Unit tests for ECSCompactionTaskRunner.
 */
class ECSCompactionTaskRunnerClaudeTest extends LocalStackTestBase {

    private InstanceProperties instanceProperties;
    private TableProperties tableProperties;
    private Schema schema;
    private InMemoryCompactionJobTracker jobTracker;
    private StateStore stateStore;

    @BeforeEach
    void setUp() {
        instanceProperties = createTestInstanceProperties();
        schema = Schema.builder()
                .rowKeyFields(new Field("key", new StringType()))
                .build();
        tableProperties = createTestTableProperties(instanceProperties, schema);
        jobTracker = new InMemoryCompactionJobTracker();
        stateStore = mock(StateStore.class);
    }

    private CompactionJob createJob() {
        return CompactionJob.builder()
                .tableId(tableProperties.get(TABLE_ID))
                .jobId("test-job-" + UUID.randomUUID())
                .inputFiles(List.of("input1.parquet", "input2.parquet"))
                .outputFile("output.parquet")
                .partitionId("root")
                .build();
    }

    private CompactionJobFinishedEvent createFinishedEvent(CompactionJob job) {
        Instant startTime = Instant.now().minusSeconds(10);
        Instant finishTime = Instant.now();
        return CompactionJobFinishedEvent.builder()
                .jobId(job.getId())
                .tableId(job.getTableId())
                .taskId("test-task")
                .jobRunId("test-job-run")
                .summary(new JobRunSummary(
                        new RecordsProcessed(100L, 100L),
                        startTime, finishTime, Duration.between(startTime, finishTime)))
                .build();
    }

    @DisplayName("logEC2Metadata")
    @Nested
    class LogEC2MetadataTests {

        @Test
        void shouldNotLogWhenLaunchTypeIsFargate() {
            // Given
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "FARGATE");
            EcsClient ecsClient = mock(EcsClient.class);

            // When/Then - Should not throw, just return without doing anything
            ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, ecsClient);
        }

        @Test
        void shouldNotLogWhenLaunchTypeIsFargateWithLowerCase() {
            // Given
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "fargate");
            EcsClient ecsClient = mock(EcsClient.class);

            // When/Then - Should not throw
            ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, ecsClient);
        }

        @Test
        void shouldHandleNullEcsClientWhenLaunchTypeIsEC2() {
            // Given
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "EC2");

            // When/Then - Should not throw, and should warn about null client
            ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, null);
        }

        @Test
        void shouldHandleEC2LaunchTypeWithMockedClient() {
            // Given
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "EC2");
            EcsClient ecsClient = mock(EcsClient.class);

            // When/Then - Should not throw, metadata retrieval will fail gracefully
            // since environment variables for ECS metadata are not set in test environment
            ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, ecsClient);
        }

        @Test
        void shouldHandleEC2LaunchTypeLowerCase() {
            // Given
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "ec2");
            EcsClient ecsClient = mock(EcsClient.class);

            // When/Then - Should not throw
            ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, ecsClient);
        }
    }

    @DisplayName("committerOrSendToLambda with async batching")
    @Nested
    class CommitterOrSendToLambdaAsyncBatchingTests {

        @Test
        void shouldSendToBatchedQueueWhenAsyncCommitAndBatchingEnabled() {
            // Given
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "true");
            tableProperties.set(COMPACTION_JOB_ASYNC_BATCHING, "true");
            String compactionCommitQueueUrl = createStandardQueue();
            instanceProperties.set(COMPACTION_COMMIT_QUEUE_URL, compactionCommitQueueUrl);

            CompactionJob job = createJob();
            CompactionJobFinishedEvent finishedEvent = createFinishedEvent(job);

            CompactionJobCommitterOrSendToLambda committer = ECSCompactionTaskRunner.committerOrSendToLambda(
                    new FixedTablePropertiesProvider(tableProperties),
                    new FixedStateStoreProvider(tableProperties, stateStore),
                    jobTracker,
                    instanceProperties,
                    sqsClient);

            // When
            committer.commit(job, finishedEvent);

            // Then
            List<Message> messages = sqsClient.receiveMessage(new ReceiveMessageRequest()
                    .withQueueUrl(compactionCommitQueueUrl)
                    .withMaxNumberOfMessages(10)).getMessages();
            assertThat(messages).hasSize(1);

            CompactionCommitMessageSerDe serDe = new CompactionCommitMessageSerDe();
            var messageHandle = serDe.fromJsonWithCallbackOnFail(messages.get(0).getBody(), () -> { });
            assertThat(messageHandle.tableId()).isEqualTo(tableProperties.getStatus().getTableUniqueId());
        }

        @Test
        void shouldSendToStateStoreCommitterQueueWhenAsyncCommitButNoBatching() {
            // Given
            tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "true");
            tableProperties.set(COMPACTION_JOB_ASYNC_BATCHING, "false");
            String stateStoreQueueUrl = createFifoQueueGetUrl();
            instanceProperties.set(STATESTORE_COMMITTER_QUEUE_URL, stateStoreQueueUrl);

            CompactionJob job = createJob();
            CompactionJobFinishedEvent finishedEvent = createFinishedEvent(job);

            CompactionJobCommitterOrSendToLambda committer = ECSCompactionTaskRunner.committerOrSendToLambda(
                    new FixedTablePropertiesProvider(tableProperties),
                    new FixedStateStoreProvider(tableProperties, stateStore),
                    jobTracker,
                    instanceProperties,
                    sqsClient);

            // When
            committer.commit(job, finishedEvent);

            // Then
            List<Message> messages = sqsClient.receiveMessage(new ReceiveMessageRequest()
                    .withQueueUrl(stateStoreQueueUrl)
                    .withMaxNumberOfMessages(10)).getMessages();
            assertThat(messages).hasSize(1);

            StateStoreCommitRequestSerDe serDe = new StateStoreCommitRequestSerDe(
                    new FixedTablePropertiesProvider(tableProperties));
            StateStoreCommitRequest request = serDe.fromJson(messages.get(0).getBody());
            assertThat(request.getTableId()).isEqualTo(tableProperties.getStatus().getTableUniqueId());
        }

        private String createStandardQueue() {
            return sqsClient.createQueue(new CreateQueueRequest()
                    .withQueueName(UUID.randomUUID().toString())).getQueueUrl();
        }
    }

    // Note: The main() method is not directly testable because:
    // 1. It calls System.exit() which would terminate the JVM
    // 2. It requires actual AWS infrastructure (S3 bucket with instance properties, DynamoDB, SQS)
    // 3. It instantiates and runs a full CompactionTask that would need real compaction jobs
    // The main method's argument validation behavior (requiring exactly 1 arg) and its orchestration
    // of AWS clients is tested implicitly through integration tests and manual testing.
}
