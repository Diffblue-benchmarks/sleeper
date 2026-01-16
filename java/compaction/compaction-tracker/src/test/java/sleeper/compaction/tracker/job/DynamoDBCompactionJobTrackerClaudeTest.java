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
package sleeper.compaction.tracker.job;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.tracker.CompactionTrackerException;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.job.update.CompactionJobCommittedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobCreatedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFailedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobStartedEvent;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.localstack.test.LocalStackTestBase;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_JOB_STATUS_TTL_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TRACKER_ENABLED;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

public class DynamoDBCompactionJobTrackerClaudeTest extends LocalStackTestBase {

    private InstanceProperties instanceProperties;
    private DynamoDBCompactionJobTracker tracker;

    @BeforeEach
    void setUp() {
        instanceProperties = createTestInstanceProperties();
        instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
        instanceProperties.set(COMPACTION_JOB_STATUS_TTL_IN_SECONDS, "3600");
        DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);
        tracker = DynamoDBCompactionJobTracker.stronglyConsistentReads(dynamoClient, instanceProperties);
    }

    @Nested
    @DisplayName("Static table name methods")
    class TableNameMethods {

        @Test
        void shouldGenerateCorrectJobUpdatesTableName() {
            // When
            String tableName = DynamoDBCompactionJobTracker.jobUpdatesTableName("test-instance");

            // Then
            assertThat(tableName).isEqualTo("sleeper-test-instance-compaction-job-updates");
        }

        @Test
        void shouldGenerateCorrectJobLookupTableName() {
            // When
            String tableName = DynamoDBCompactionJobTracker.jobLookupTableName("test-instance");

            // Then
            assertThat(tableName).isEqualTo("sleeper-test-instance-compaction-job-lookup");
        }

        @Test
        void shouldHandleEmptyInstanceId() {
            // When
            String updatesTableName = DynamoDBCompactionJobTracker.jobUpdatesTableName("");
            String lookupTableName = DynamoDBCompactionJobTracker.jobLookupTableName("");

            // Then
            assertThat(updatesTableName).isEqualTo("sleeper--compaction-job-updates");
            assertThat(lookupTableName).isEqualTo("sleeper--compaction-job-lookup");
        }
    }

    @Nested
    @DisplayName("Factory methods")
    class FactoryMethods {

        @Test
        void shouldCreateTrackerWithStronglyConsistentReads() {
            // When
            DynamoDBCompactionJobTracker result = DynamoDBCompactionJobTracker.stronglyConsistentReads(
                    dynamoClient, instanceProperties);

            // Then
            assertThat(result).isNotNull();
            assertThat(result).isInstanceOf(DynamoDBCompactionJobTracker.class);
        }

        @Test
        void shouldCreateTrackerWithEventuallyConsistentReads() {
            // When
            DynamoDBCompactionJobTracker result = DynamoDBCompactionJobTracker.eventuallyConsistentReads(
                    dynamoClient, instanceProperties);

            // Then
            assertThat(result).isNotNull();
            assertThat(result).isInstanceOf(DynamoDBCompactionJobTracker.class);
        }

        @Test
        void shouldCreateDifferentInstancesForDifferentConsistencyModes() {
            // When
            DynamoDBCompactionJobTracker strongTracker = DynamoDBCompactionJobTracker.stronglyConsistentReads(
                    dynamoClient, instanceProperties);
            DynamoDBCompactionJobTracker eventualTracker = DynamoDBCompactionJobTracker.eventuallyConsistentReads(
                    dynamoClient, instanceProperties);

            // Then
            assertThat(strongTracker).isNotSameAs(eventualTracker);
        }
    }

    @Nested
    @DisplayName("Constructor with custom time supplier")
    class ConstructorWithCustomTimeSupplier {

        @Test
        void shouldUseCustomTimeSupplierForExpiryCalculation() {
            // Given
            Instant fixedTime = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionJobTracker customTracker = new DynamoDBCompactionJobTracker(
                    dynamoClient, instanceProperties, true, () -> fixedTime);

            String jobId = "job-custom-time";
            String tableId = "table-1";
            CompactionJobCreatedEvent createdEvent = CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(2)
                    .build();

            // When
            customTracker.jobCreated(createdEvent);

            // Then
            Optional<CompactionJobStatus> job = customTracker.getJob(jobId);
            assertThat(job).isPresent();
            // TTL is 3600 seconds = 1 hour, so expiry should be 11:00:00Z
            assertThat(job.get().getExpiryDate()).isEqualTo(fixedTime.plusSeconds(3600));
        }
    }

    @Nested
    @DisplayName("jobCreated")
    class JobCreated {

        @Test
        void shouldStoreJobCreatedEvent() {
            // Given
            String jobId = "job-1";
            String tableId = "table-1";
            CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(3)
                    .build();

            // When
            tracker.jobCreated(event);

            // Then
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);
            assertThat(result).isPresent();
            assertThat(result.get().getJobId()).isEqualTo(jobId);
            assertThat(result.get().getPartitionId()).isEqualTo("partition-1");
            assertThat(result.get().getInputFilesCount()).isEqualTo(3);
        }

        @Test
        void shouldThrowCompactionTrackerExceptionOnDynamoDBFailure() {
            // Given
            AmazonDynamoDB mockDynamoDB = mock(AmazonDynamoDB.class);
            when(mockDynamoDB.putItem(any())).thenThrow(new AmazonDynamoDBException("Test error"));

            DynamoDBCompactionJobTracker failingTracker = new DynamoDBCompactionJobTracker(
                    mockDynamoDB, instanceProperties, true, Instant::now);

            CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
                    .jobId("job-fail")
                    .tableId("table-1")
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build();

            // When/Then
            assertThatThrownBy(() -> failingTracker.jobCreated(event))
                    .isInstanceOf(CompactionTrackerException.class)
                    .hasMessageContaining("Failed saving created event for job job-fail");
        }
    }

    @Nested
    @DisplayName("jobStarted")
    class JobStarted {

        @Test
        void shouldStoreJobStartedEvent() {
            // Given
            String jobId = "job-2";
            String tableId = "table-1";
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");

            // First create the job
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(2)
                    .build());

            CompactionJobStartedEvent event = CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(startTime)
                    .build();

            // When
            tracker.jobStarted(event);

            // Then
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);
            assertThat(result).isPresent();
            assertThat(result.get().isStarted()).isTrue();
        }

        @Test
        void shouldThrowCompactionTrackerExceptionOnDynamoDBFailure() {
            // Given
            AmazonDynamoDB mockDynamoDB = mock(AmazonDynamoDB.class);
            when(mockDynamoDB.putItem(any())).thenThrow(new AmazonDynamoDBException("Test error"));

            DynamoDBCompactionJobTracker failingTracker = new DynamoDBCompactionJobTracker(
                    mockDynamoDB, instanceProperties, true, Instant::now);

            CompactionJobStartedEvent event = CompactionJobStartedEvent.builder()
                    .jobId("job-fail")
                    .tableId("table-1")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(Instant.now())
                    .build();

            // When/Then
            assertThatThrownBy(() -> failingTracker.jobStarted(event))
                    .isInstanceOf(CompactionTrackerException.class)
                    .hasMessageContaining("Failed saving started event for job job-fail");
        }
    }

    @Nested
    @DisplayName("jobFinished")
    class JobFinished {

        @Test
        void shouldStoreJobFinishedEvent() {
            // Given
            String jobId = "job-3";
            String tableId = "table-1";
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:05:00Z");

            // First create and start the job
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(2)
                    .build());

            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(startTime)
                    .build());

            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(1000, 900),
                    startTime,
                    finishTime);

            CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(summary)
                    .build();

            // When
            tracker.jobFinished(event);

            // Then - after finish but before commit, the job is in UNCOMMITTED state (awaiting commit)
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);
            assertThat(result).isPresent();
            assertThat(result.get().getRunsAwaitingCommit()).isEqualTo(1);
        }

        @Test
        void shouldThrowCompactionTrackerExceptionOnDynamoDBFailure() {
            // Given
            AmazonDynamoDB mockDynamoDB = mock(AmazonDynamoDB.class);
            when(mockDynamoDB.putItem(any())).thenThrow(new AmazonDynamoDBException("Test error"));

            DynamoDBCompactionJobTracker failingTracker = new DynamoDBCompactionJobTracker(
                    mockDynamoDB, instanceProperties, true, Instant::now);

            Instant startTime = Instant.now();
            CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                    .jobId("job-fail")
                    .tableId("table-1")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(100, 100), startTime, startTime.plusSeconds(60)))
                    .build();

            // When/Then
            assertThatThrownBy(() -> failingTracker.jobFinished(event))
                    .isInstanceOf(CompactionTrackerException.class)
                    .hasMessageContaining("Failed saving finished event for job job-fail");
        }
    }

    @Nested
    @DisplayName("jobCommitted")
    class JobCommitted {

        @Test
        void shouldStoreJobCommittedEvent() {
            // Given
            String jobId = "job-4";
            String tableId = "table-1";
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:05:00Z");
            Instant commitTime = Instant.parse("2024-01-15T10:06:00Z");

            // Create full lifecycle
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(2)
                    .build());

            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(startTime)
                    .build());

            tracker.jobFinished(CompactionJobFinishedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(1000, 900), startTime, finishTime))
                    .build());

            CompactionJobCommittedEvent event = CompactionJobCommittedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .commitTime(commitTime)
                    .build();

            // When
            tracker.jobCommitted(event);

            // Then
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);
            assertThat(result).isPresent();
        }

        @Test
        void shouldThrowCompactionTrackerExceptionOnDynamoDBFailure() {
            // Given
            AmazonDynamoDB mockDynamoDB = mock(AmazonDynamoDB.class);
            when(mockDynamoDB.putItem(any())).thenThrow(new AmazonDynamoDBException("Test error"));

            DynamoDBCompactionJobTracker failingTracker = new DynamoDBCompactionJobTracker(
                    mockDynamoDB, instanceProperties, true, Instant::now);

            CompactionJobCommittedEvent event = CompactionJobCommittedEvent.builder()
                    .jobId("job-fail")
                    .tableId("table-1")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .commitTime(Instant.now())
                    .build();

            // When/Then
            assertThatThrownBy(() -> failingTracker.jobCommitted(event))
                    .isInstanceOf(CompactionTrackerException.class)
                    .hasMessageContaining("Failed saving committed event for job job-fail");
        }
    }

    @Nested
    @DisplayName("jobFailed")
    class JobFailed {

        @Test
        void shouldStoreJobFailedEvent() {
            // Given
            String jobId = "job-5";
            String tableId = "table-1";
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant failureTime = Instant.parse("2024-01-15T10:05:00Z");

            // Create and start job
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(2)
                    .build());

            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(startTime)
                    .build());

            CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureTime(failureTime)
                    .failureReasons(List.of("Something went wrong", "Root cause"))
                    .build();

            // When
            tracker.jobFailed(event);

            // Then
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);
            assertThat(result).isPresent();
            assertThat(result.get().isAnyRunFailed()).isTrue();
        }

        @Test
        void shouldThrowCompactionTrackerExceptionOnDynamoDBFailure() {
            // Given
            AmazonDynamoDB mockDynamoDB = mock(AmazonDynamoDB.class);
            when(mockDynamoDB.putItem(any())).thenThrow(new AmazonDynamoDBException("Test error"));

            DynamoDBCompactionJobTracker failingTracker = new DynamoDBCompactionJobTracker(
                    mockDynamoDB, instanceProperties, true, Instant::now);

            CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                    .jobId("job-fail")
                    .tableId("table-1")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureTime(Instant.now())
                    .failureReasons(List.of("Error"))
                    .build();

            // When/Then
            assertThatThrownBy(() -> failingTracker.jobFailed(event))
                    .isInstanceOf(CompactionTrackerException.class)
                    .hasMessageContaining("Failed saving failed event for job job-fail");
        }
    }

    @Nested
    @DisplayName("getJob")
    class GetJob {

        @Test
        void shouldReturnEmptyOptionalForNonExistentJob() {
            // When
            Optional<CompactionJobStatus> result = tracker.getJob("non-existent-job");

            // Then
            assertThat(result).isEmpty();
        }

        @Test
        void shouldReturnJobStatusForExistingJob() {
            // Given
            String jobId = "job-get";
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId("table-1")
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            // When
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);

            // Then
            assertThat(result).isPresent();
            assertThat(result.get().getJobId()).isEqualTo(jobId);
        }

        @Test
        void shouldReturnFullJobStatusWithAllUpdates() {
            // Given
            String jobId = "job-full";
            String tableId = "table-1";
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:05:00Z");
            Instant commitTime = Instant.parse("2024-01-15T10:06:00Z");

            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(5)
                    .build());

            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(startTime)
                    .build());

            tracker.jobFinished(CompactionJobFinishedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(5000, 4500), startTime, finishTime))
                    .build());

            tracker.jobCommitted(CompactionJobCommittedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .commitTime(commitTime)
                    .build());

            // When
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);

            // Then
            assertThat(result).isPresent();
            CompactionJobStatus status = result.get();
            assertThat(status.getJobId()).isEqualTo(jobId);
            assertThat(status.getPartitionId()).isEqualTo("partition-1");
            assertThat(status.getInputFilesCount()).isEqualTo(5);
            assertThat(status.isStarted()).isTrue();
            assertThat(status.isAnyRunSuccessful()).isTrue();
        }
    }

    @Nested
    @DisplayName("streamAllJobs")
    class StreamAllJobs {

        @Test
        void shouldReturnEmptyStreamForTableWithNoJobs() {
            // When
            List<CompactionJobStatus> result = tracker.streamAllJobs("empty-table").collect(Collectors.toList());

            // Then
            assertThat(result).isEmpty();
        }

        @Test
        void shouldReturnAllJobsForTable() {
            // Given
            String tableId = "table-stream";

            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId("job-stream-1")
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId("job-stream-2")
                    .tableId(tableId)
                    .partitionId("partition-2")
                    .inputFilesCount(2)
                    .build());

            // When
            List<CompactionJobStatus> result = tracker.streamAllJobs(tableId).collect(Collectors.toList());

            // Then
            assertThat(result).hasSize(2);
            assertThat(result).extracting(CompactionJobStatus::getJobId)
                    .containsExactlyInAnyOrder("job-stream-1", "job-stream-2");
        }

        @Test
        void shouldNotReturnJobsFromDifferentTable() {
            // Given
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId("job-table-a")
                    .tableId("table-a")
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId("job-table-b")
                    .tableId("table-b")
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            // When
            List<CompactionJobStatus> result = tracker.streamAllJobs("table-a").collect(Collectors.toList());

            // Then
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getJobId()).isEqualTo("job-table-a");
        }

        @Test
        void shouldReturnJobsWithFullStatusHistory() {
            // Given
            String jobId = "job-history";
            String tableId = "table-history";
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:05:00Z");
            Instant commitTime = Instant.parse("2024-01-15T10:06:00Z");

            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(3)
                    .build());

            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(startTime)
                    .build());

            tracker.jobFinished(CompactionJobFinishedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(1000, 900), startTime, finishTime))
                    .build());

            tracker.jobCommitted(CompactionJobCommittedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .commitTime(commitTime)
                    .build());

            // When
            List<CompactionJobStatus> result = tracker.streamAllJobs(tableId).collect(Collectors.toList());

            // Then
            assertThat(result).hasSize(1);
            CompactionJobStatus status = result.get(0);
            assertThat(status.getJobId()).isEqualTo(jobId);
            assertThat(status.isStarted()).isTrue();
            assertThat(status.isAnyRunSuccessful()).isTrue();
        }
    }

    @Nested
    @DisplayName("Full job lifecycle")
    class FullJobLifecycle {

        @Test
        void shouldTrackCompleteSuccessfulJobLifecycle() {
            // Given
            String jobId = "lifecycle-success";
            String tableId = "table-lifecycle";
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:10:00Z");
            Instant commitTime = Instant.parse("2024-01-15T10:11:00Z");

            // When - Create
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(10)
                    .build());

            Optional<CompactionJobStatus> afterCreate = tracker.getJob(jobId);
            assertThat(afterCreate).isPresent();
            assertThat(afterCreate.get().isStarted()).isFalse();

            // When - Start
            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(startTime)
                    .build());

            Optional<CompactionJobStatus> afterStart = tracker.getJob(jobId);
            assertThat(afterStart).isPresent();
            assertThat(afterStart.get().isStarted()).isTrue();
            assertThat(afterStart.get().isAnyRunInProgress()).isTrue();

            // When - Finish
            tracker.jobFinished(CompactionJobFinishedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(10000, 9000), startTime, finishTime))
                    .build());

            Optional<CompactionJobStatus> afterFinish = tracker.getJob(jobId);
            assertThat(afterFinish).isPresent();
            // After finish but before commit, the job is awaiting commit (UNCOMMITTED state)
            assertThat(afterFinish.get().getRunsAwaitingCommit()).isEqualTo(1);

            // When - Commit
            tracker.jobCommitted(CompactionJobCommittedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .commitTime(commitTime)
                    .build());

            Optional<CompactionJobStatus> afterCommit = tracker.getJob(jobId);
            assertThat(afterCommit).isPresent();
        }

        @Test
        void shouldTrackJobThatFailsAfterStarting() {
            // Given
            String jobId = "lifecycle-fail";
            String tableId = "table-lifecycle";
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant failureTime = Instant.parse("2024-01-15T10:05:00Z");

            // When - Create and start
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(5)
                    .build());

            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(startTime)
                    .build());

            // When - Fail
            tracker.jobFailed(CompactionJobFailedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureTime(failureTime)
                    .failureReasons(List.of("OutOfMemoryError", "Heap space exhausted"))
                    .build());

            // Then
            Optional<CompactionJobStatus> afterFail = tracker.getJob(jobId);
            assertThat(afterFail).isPresent();
            assertThat(afterFail.get().isAnyRunFailed()).isTrue();
            assertThat(afterFail.get().isAwaitingRetry()).isTrue();
        }
    }

    @Nested
    @DisplayName("Multiple jobs and runs")
    class MultipleJobsAndRuns {

        @Test
        void shouldHandleMultipleJobsForSameTable() {
            // Given
            String tableId = "multi-job-table";

            for (int i = 0; i < 5; i++) {
                tracker.jobCreated(CompactionJobCreatedEvent.builder()
                        .jobId("multi-job-" + i)
                        .tableId(tableId)
                        .partitionId("partition-" + i)
                        .inputFilesCount(i + 1)
                        .build());
            }

            // When
            List<CompactionJobStatus> result = tracker.streamAllJobs(tableId).collect(Collectors.toList());

            // Then
            assertThat(result).hasSize(5);
        }

        @Test
        void shouldHandleJobWithMultipleRuns() {
            // Given
            String jobId = "multi-run-job";
            String tableId = "table-multi-run";
            Instant firstStartTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant firstFailTime = Instant.parse("2024-01-15T10:05:00Z");
            Instant secondStartTime = Instant.parse("2024-01-15T10:10:00Z");
            Instant secondFinishTime = Instant.parse("2024-01-15T10:15:00Z");

            // Create job
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(3)
                    .build());

            // First run - fails
            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(firstStartTime)
                    .build());

            tracker.jobFailed(CompactionJobFailedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureTime(firstFailTime)
                    .failureReasons(List.of("Temporary error"))
                    .build());

            // Second run - succeeds
            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-2")
                    .jobRunId("run-2")
                    .startTime(secondStartTime)
                    .build());

            tracker.jobFinished(CompactionJobFinishedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-2")
                    .jobRunId("run-2")
                    .summary(new JobRunSummary(new RecordsProcessed(1000, 900), secondStartTime, secondFinishTime))
                    .build());

            Instant secondCommitTime = Instant.parse("2024-01-15T10:16:00Z");
            tracker.jobCommitted(CompactionJobCommittedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-2")
                    .jobRunId("run-2")
                    .commitTime(secondCommitTime)
                    .build());

            // Then
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);
            assertThat(result).isPresent();
            CompactionJobStatus status = result.get();
            assertThat(status.isMultipleRuns()).isTrue();
            assertThat(status.isAnyRunFailed()).isTrue();
            assertThat(status.isAnyRunSuccessful()).isTrue();
        }
    }

    @Nested
    @DisplayName("TTL and expiry")
    class TtlAndExpiry {

        @Test
        void shouldSetExpiryDateBasedOnTtlConfiguration() {
            // Given
            Instant fixedTime = Instant.parse("2024-01-15T10:00:00Z");
            int ttlSeconds = 7200; // 2 hours
            instanceProperties.set(COMPACTION_JOB_STATUS_TTL_IN_SECONDS, String.valueOf(ttlSeconds));
            DynamoDBCompactionJobTrackerCreator.tearDown(instanceProperties, dynamoClient);
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);

            DynamoDBCompactionJobTracker customTracker = new DynamoDBCompactionJobTracker(
                    dynamoClient, instanceProperties, true, () -> fixedTime);

            String jobId = "job-ttl";
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId("table-1")
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            // When
            customTracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId("job-ttl-2")
                    .tableId("table-1")
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            // Then
            Optional<CompactionJobStatus> result = customTracker.getJob("job-ttl-2");
            assertThat(result).isPresent();
            assertThat(result.get().getExpiryDate()).isEqualTo(fixedTime.plusSeconds(ttlSeconds));
        }
    }

    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {

        @Test
        void shouldHandleJobIdWithSpecialCharacters() {
            // Given
            String jobId = "job-with-special|chars_123";
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId("table-1")
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            // When
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);

            // Then
            assertThat(result).isPresent();
            assertThat(result.get().getJobId()).isEqualTo(jobId);
        }

        @Test
        void shouldHandleZeroInputFilesCount() {
            // Given
            String jobId = "job-zero-files";
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId("table-1")
                    .partitionId("partition-1")
                    .inputFilesCount(0)
                    .build());

            // When
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);

            // Then
            assertThat(result).isPresent();
            assertThat(result.get().getInputFilesCount()).isEqualTo(0);
        }

        @Test
        void shouldHandleVeryLongJobId() {
            // Given
            String jobId = "a".repeat(200);
            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId("table-1")
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            // When
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);

            // Then
            assertThat(result).isPresent();
            assertThat(result.get().getJobId()).isEqualTo(jobId);
        }

        @Test
        void shouldHandleJobWithZeroDurationRun() {
            // Given
            String jobId = "job-zero-duration";
            String tableId = "table-1";
            Instant time = Instant.parse("2024-01-15T10:00:00Z");

            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(time)
                    .build());

            // Finish at the same instant
            tracker.jobFinished(CompactionJobFinishedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(0, 0), time, Duration.ZERO))
                    .build());

            // When
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);

            // Then
            assertThat(result).isPresent();
        }

        @Test
        void shouldHandleEmptyFailureReasons() {
            // Given
            String jobId = "job-empty-reasons";
            String tableId = "table-1";
            Instant startTime = Instant.now();
            Instant failureTime = startTime.plusSeconds(60);

            tracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            tracker.jobStarted(CompactionJobStartedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(startTime)
                    .build());

            tracker.jobFailed(CompactionJobFailedEvent.builder()
                    .jobId(jobId)
                    .tableId(tableId)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureTime(failureTime)
                    .failureReasons(List.of())
                    .build());

            // When
            Optional<CompactionJobStatus> result = tracker.getJob(jobId);

            // Then
            assertThat(result).isPresent();
            assertThat(result.get().isAnyRunFailed()).isTrue();
        }
    }

    @Nested
    @DisplayName("Consistency modes")
    class ConsistencyModes {

        @Test
        void shouldReturnSameDataWithStronglyConsistentReads() {
            // Given
            String jobId = "job-strong";
            DynamoDBCompactionJobTracker strongTracker = DynamoDBCompactionJobTracker.stronglyConsistentReads(
                    dynamoClient, instanceProperties);

            strongTracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId("table-1")
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            // When
            Optional<CompactionJobStatus> result = strongTracker.getJob(jobId);

            // Then
            assertThat(result).isPresent();
            assertThat(result.get().getJobId()).isEqualTo(jobId);
        }

        @Test
        void shouldReturnSameDataWithEventuallyConsistentReads() {
            // Given
            String jobId = "job-eventual";
            DynamoDBCompactionJobTracker eventualTracker = DynamoDBCompactionJobTracker.eventuallyConsistentReads(
                    dynamoClient, instanceProperties);

            eventualTracker.jobCreated(CompactionJobCreatedEvent.builder()
                    .jobId(jobId)
                    .tableId("table-1")
                    .partitionId("partition-1")
                    .inputFilesCount(1)
                    .build());

            // When
            Optional<CompactionJobStatus> result = eventualTracker.getJob(jobId);

            // Then
            assertThat(result).isPresent();
            assertThat(result.get().getJobId()).isEqualTo(jobId);
        }
    }
}
