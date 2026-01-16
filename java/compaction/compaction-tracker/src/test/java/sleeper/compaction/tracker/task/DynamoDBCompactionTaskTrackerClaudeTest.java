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
package sleeper.compaction.tracker.task;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.tracker.CompactionTrackerException;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.tracker.compaction.task.CompactionTaskFinishedStatus;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus;
import sleeper.localstack.test.LocalStackTestBase;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sleeper.core.properties.instance.CommonProperty.ID;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_STATUS_TTL_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TRACKER_ENABLED;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

public class DynamoDBCompactionTaskTrackerClaudeTest extends LocalStackTestBase {

    private InstanceProperties instanceProperties;
    private DynamoDBCompactionTaskTracker tracker;

    @BeforeEach
    void setUp() {
        instanceProperties = createTestInstanceProperties();
        instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
        instanceProperties.set(COMPACTION_TASK_STATUS_TTL_IN_SECONDS, "3600");
        DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);
        tracker = new DynamoDBCompactionTaskTracker(dynamoClient, instanceProperties);
    }

    @Nested
    @DisplayName("Constructor with default time supplier")
    class ConstructorWithDefaultTimeSupplier {

        @Test
        void shouldCreateTrackerUsingDefaultConstructor() {
            // When
            DynamoDBCompactionTaskTracker newTracker = new DynamoDBCompactionTaskTracker(
                    dynamoClient, instanceProperties);

            // Then
            assertThat(newTracker).isNotNull();
        }

        @Test
        void shouldStoreAndRetrieveTaskUsingDefaultConstructor() {
            // Given
            DynamoDBCompactionTaskTracker newTracker = new DynamoDBCompactionTaskTracker(
                    dynamoClient, instanceProperties);
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("default-constructor-task")
                    .startTime(startTime)
                    .build();

            // When
            newTracker.taskStarted(taskStatus);
            CompactionTaskStatus retrieved = newTracker.getTask("default-constructor-task");

            // Then
            assertThat(retrieved).isNotNull();
            assertThat(retrieved.getTaskId()).isEqualTo("default-constructor-task");
            assertThat(retrieved.getStartTime()).isEqualTo(startTime);
        }
    }

    @Nested
    @DisplayName("Constructor with custom time supplier")
    class ConstructorWithCustomTimeSupplier {

        @Test
        void shouldCreateTrackerWithCustomTimeSupplier() {
            // Given
            Instant fixedTime = Instant.parse("2024-01-15T10:00:00Z");

            // When
            DynamoDBCompactionTaskTracker customTracker = new DynamoDBCompactionTaskTracker(
                    dynamoClient, instanceProperties, () -> fixedTime);

            // Then
            assertThat(customTracker).isNotNull();
        }

        @Test
        void shouldUseCustomTimeSupplierForExpiryCalculation() {
            // Given
            Instant fixedTime = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionTaskTracker customTracker = new DynamoDBCompactionTaskTracker(
                    dynamoClient, instanceProperties, () -> fixedTime);
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("custom-time-task")
                    .startTime(Instant.parse("2024-01-15T09:00:00Z"))
                    .build();

            // When
            customTracker.taskStarted(taskStatus);
            CompactionTaskStatus retrieved = customTracker.getTask("custom-time-task");

            // Then
            assertThat(retrieved).isNotNull();
            // TTL is 3600 seconds = 1 hour, so expiry should be 11:00:00Z
            assertThat(retrieved.getExpiryDate()).isEqualTo(fixedTime.plusSeconds(3600));
        }
    }

    @Nested
    @DisplayName("taskStarted")
    class TaskStarted {

        @Test
        void shouldStoreTaskStartedStatus() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("task-1")
                    .startTime(startTime)
                    .build();

            // When
            tracker.taskStarted(taskStatus);

            // Then
            CompactionTaskStatus retrieved = tracker.getTask("task-1");
            assertThat(retrieved).isNotNull();
            assertThat(retrieved.getTaskId()).isEqualTo("task-1");
            assertThat(retrieved.getStartTime()).isEqualTo(startTime);
            assertThat(retrieved.isFinished()).isFalse();
        }

        @Test
        void shouldThrowCompactionTrackerExceptionOnDynamoDBFailure() {
            // Given
            AmazonDynamoDB mockDynamoDB = mock(AmazonDynamoDB.class);
            when(mockDynamoDB.putItem(any())).thenThrow(new AmazonDynamoDBException("Test error"));

            DynamoDBCompactionTaskTracker failingTracker = new DynamoDBCompactionTaskTracker(
                    mockDynamoDB, instanceProperties, Instant::now);

            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("fail-task")
                    .startTime(Instant.now())
                    .build();

            // When/Then
            assertThatThrownBy(() -> failingTracker.taskStarted(taskStatus))
                    .isInstanceOf(CompactionTrackerException.class)
                    .hasMessageContaining("Failed putItem in taskStarted for task fail-task");
        }

        @Test
        void shouldStoreMultipleTasksStarted() {
            // Given
            CompactionTaskStatus task1 = CompactionTaskStatus.builder()
                    .taskId("task-multi-1")
                    .startTime(Instant.parse("2024-01-15T10:00:00Z"))
                    .build();
            CompactionTaskStatus task2 = CompactionTaskStatus.builder()
                    .taskId("task-multi-2")
                    .startTime(Instant.parse("2024-01-15T11:00:00Z"))
                    .build();

            // When
            tracker.taskStarted(task1);
            tracker.taskStarted(task2);

            // Then
            assertThat(tracker.getTask("task-multi-1")).isNotNull();
            assertThat(tracker.getTask("task-multi-2")).isNotNull();
        }
    }

    @Nested
    @DisplayName("taskFinished")
    class TaskFinished {

        @Test
        void shouldStoreTaskFinishedStatus() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T11:00:00Z");
            CompactionTaskStatus startedTask = CompactionTaskStatus.builder()
                    .taskId("task-finish")
                    .startTime(startTime)
                    .build();
            CompactionTaskStatus finishedTask = CompactionTaskStatus.builder()
                    .taskId("task-finish")
                    .startTime(startTime)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(finishTime)
                            .timeSpentOnJobs(Duration.ofMinutes(30))
                            .totalJobRuns(5)
                            .totalRecordsRead(1000)
                            .totalRecordsWritten(900)
                            .recordsReadPerSecond(100.0)
                            .recordsWrittenPerSecond(90.0)
                            .build())
                    .build();

            // When
            tracker.taskStarted(startedTask);
            tracker.taskFinished(finishedTask);

            // Then
            CompactionTaskStatus retrieved = tracker.getTask("task-finish");
            assertThat(retrieved).isNotNull();
            assertThat(retrieved.isFinished()).isTrue();
            assertThat(retrieved.getFinishedStatus().getFinishTime()).isEqualTo(finishTime);
            assertThat(retrieved.getFinishedStatus().getTotalJobRuns()).isEqualTo(5);
            assertThat(retrieved.getFinishedStatus().getTotalRecordsRead()).isEqualTo(1000);
            assertThat(retrieved.getFinishedStatus().getTotalRecordsWritten()).isEqualTo(900);
        }

        @Test
        void shouldThrowCompactionTrackerExceptionOnDynamoDBFailure() {
            // Given
            AmazonDynamoDB mockDynamoDB = mock(AmazonDynamoDB.class);
            when(mockDynamoDB.putItem(any())).thenThrow(new AmazonDynamoDBException("Test error"));

            DynamoDBCompactionTaskTracker failingTracker = new DynamoDBCompactionTaskTracker(
                    mockDynamoDB, instanceProperties, Instant::now);

            Instant startTime = Instant.now();
            CompactionTaskStatus finishedTask = CompactionTaskStatus.builder()
                    .taskId("fail-finish-task")
                    .startTime(startTime)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(startTime.plusSeconds(3600))
                            .timeSpentOnJobs(Duration.ofMinutes(30))
                            .totalJobRuns(1)
                            .totalRecordsRead(100)
                            .totalRecordsWritten(100)
                            .recordsReadPerSecond(10.0)
                            .recordsWrittenPerSecond(10.0)
                            .build())
                    .build();

            // When/Then
            assertThatThrownBy(() -> failingTracker.taskFinished(finishedTask))
                    .isInstanceOf(CompactionTrackerException.class)
                    .hasMessageContaining("Failed putItem in taskFinished for task fail-finish-task");
        }

        @Test
        void shouldStoreFinishedTaskWithZeroRecordsProcessed() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:30:00Z");
            CompactionTaskStatus startedTask = CompactionTaskStatus.builder()
                    .taskId("task-zero-records")
                    .startTime(startTime)
                    .build();
            CompactionTaskStatus finishedTask = CompactionTaskStatus.builder()
                    .taskId("task-zero-records")
                    .startTime(startTime)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(finishTime)
                            .timeSpentOnJobs(Duration.ZERO)
                            .totalJobRuns(0)
                            .totalRecordsRead(0)
                            .totalRecordsWritten(0)
                            .recordsReadPerSecond(0.0)
                            .recordsWrittenPerSecond(0.0)
                            .build())
                    .build();

            // When
            tracker.taskStarted(startedTask);
            tracker.taskFinished(finishedTask);

            // Then
            CompactionTaskStatus retrieved = tracker.getTask("task-zero-records");
            assertThat(retrieved).isNotNull();
            assertThat(retrieved.isFinished()).isTrue();
            assertThat(retrieved.getFinishedStatus().getTotalJobRuns()).isZero();
            assertThat(retrieved.getFinishedStatus().getTotalRecordsRead()).isZero();
        }
    }

    @Nested
    @DisplayName("getTask")
    class GetTask {

        @Test
        void shouldReturnNullForNonExistentTask() {
            // When
            CompactionTaskStatus result = tracker.getTask("non-existent-task");

            // Then
            assertThat(result).isNull();
        }

        @Test
        void shouldReturnTaskStatusForExistingTask() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("existing-task")
                    .startTime(startTime)
                    .build();
            tracker.taskStarted(taskStatus);

            // When
            CompactionTaskStatus result = tracker.getTask("existing-task");

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getTaskId()).isEqualTo("existing-task");
            assertThat(result.getStartTime()).isEqualTo(startTime);
        }

        @Test
        void shouldReturnCombinedStatusForStartedAndFinishedTask() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T11:00:00Z");
            CompactionTaskStatus startedTask = CompactionTaskStatus.builder()
                    .taskId("combined-task")
                    .startTime(startTime)
                    .build();
            CompactionTaskStatus finishedTask = CompactionTaskStatus.builder()
                    .taskId("combined-task")
                    .startTime(startTime)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(finishTime)
                            .timeSpentOnJobs(Duration.ofMinutes(45))
                            .totalJobRuns(3)
                            .totalRecordsRead(5000)
                            .totalRecordsWritten(4500)
                            .recordsReadPerSecond(200.0)
                            .recordsWrittenPerSecond(180.0)
                            .build())
                    .build();

            tracker.taskStarted(startedTask);
            tracker.taskFinished(finishedTask);

            // When
            CompactionTaskStatus result = tracker.getTask("combined-task");

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getTaskId()).isEqualTo("combined-task");
            assertThat(result.getStartTime()).isEqualTo(startTime);
            assertThat(result.isFinished()).isTrue();
            assertThat(result.getFinishedStatus().getFinishTime()).isEqualTo(finishTime);
        }

        @Test
        void shouldHandleTaskIdWithSpecialCharacters() {
            // Given
            String taskId = "task-with-special|chars_123";
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId(taskId)
                    .startTime(Instant.now())
                    .build();
            tracker.taskStarted(taskStatus);

            // When
            CompactionTaskStatus result = tracker.getTask(taskId);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getTaskId()).isEqualTo(taskId);
        }
    }

    @Nested
    @DisplayName("getAllTasks")
    class GetAllTasks {

        @Test
        void shouldReturnEmptyListWhenNoTasks() {
            // When
            List<CompactionTaskStatus> result = tracker.getAllTasks();

            // Then
            assertThat(result).isEmpty();
        }

        @Test
        void shouldReturnAllTasks() {
            // Given
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("all-task-1")
                    .startTime(Instant.parse("2024-01-15T10:00:00Z"))
                    .build());
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("all-task-2")
                    .startTime(Instant.parse("2024-01-15T11:00:00Z"))
                    .build());
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("all-task-3")
                    .startTime(Instant.parse("2024-01-15T12:00:00Z"))
                    .build());

            // When
            List<CompactionTaskStatus> result = tracker.getAllTasks();

            // Then
            assertThat(result).hasSize(3);
            assertThat(result).extracting(CompactionTaskStatus::getTaskId)
                    .containsExactlyInAnyOrder("all-task-1", "all-task-2", "all-task-3");
        }

        @Test
        void shouldReturnTasksInBothStartedAndFinishedStates() {
            // Given
            CompactionTaskStatus startedTask = CompactionTaskStatus.builder()
                    .taskId("mixed-task-started")
                    .startTime(Instant.parse("2024-01-15T10:00:00Z"))
                    .build();
            CompactionTaskStatus finishedTaskStart = CompactionTaskStatus.builder()
                    .taskId("mixed-task-finished")
                    .startTime(Instant.parse("2024-01-15T09:00:00Z"))
                    .build();
            CompactionTaskStatus finishedTaskEnd = CompactionTaskStatus.builder()
                    .taskId("mixed-task-finished")
                    .startTime(Instant.parse("2024-01-15T09:00:00Z"))
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(Instant.parse("2024-01-15T09:30:00Z"))
                            .timeSpentOnJobs(Duration.ofMinutes(20))
                            .totalJobRuns(2)
                            .totalRecordsRead(500)
                            .totalRecordsWritten(450)
                            .recordsReadPerSecond(50.0)
                            .recordsWrittenPerSecond(45.0)
                            .build())
                    .build();

            tracker.taskStarted(startedTask);
            tracker.taskStarted(finishedTaskStart);
            tracker.taskFinished(finishedTaskEnd);

            // When
            List<CompactionTaskStatus> result = tracker.getAllTasks();

            // Then
            assertThat(result).hasSize(2);
            assertThat(result).extracting(CompactionTaskStatus::getTaskId)
                    .containsExactlyInAnyOrder("mixed-task-started", "mixed-task-finished");
        }
    }

    @Nested
    @DisplayName("getTasksInTimePeriod")
    class GetTasksInTimePeriod {

        @Test
        void shouldReturnEmptyListWhenNoTasksInPeriod() {
            // Given
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("outside-task")
                    .startTime(Instant.parse("2024-01-10T10:00:00Z"))
                    .build());

            // When - query for a different time period
            List<CompactionTaskStatus> result = tracker.getTasksInTimePeriod(
                    Instant.parse("2024-01-15T00:00:00Z"),
                    Instant.parse("2024-01-16T00:00:00Z"));

            // Then
            assertThat(result).isEmpty();
        }

        @Test
        void shouldReturnTasksWithinTimePeriod() {
            // Given
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("in-period-task")
                    .startTime(Instant.parse("2024-01-15T10:00:00Z"))
                    .build());

            // When
            List<CompactionTaskStatus> result = tracker.getTasksInTimePeriod(
                    Instant.parse("2024-01-15T00:00:00Z"),
                    Instant.parse("2024-01-16T00:00:00Z"));

            // Then
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getTaskId()).isEqualTo("in-period-task");
        }

        @Test
        void shouldReturnUnfinishedTaskStartedInPeriod() {
            // Given
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("unfinished-in-period")
                    .startTime(Instant.parse("2024-01-15T12:00:00Z"))
                    .build());

            // When
            List<CompactionTaskStatus> result = tracker.getTasksInTimePeriod(
                    Instant.parse("2024-01-15T00:00:00Z"),
                    Instant.parse("2024-01-16T00:00:00Z"));

            // Then
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getTaskId()).isEqualTo("unfinished-in-period");
            assertThat(result.get(0).isFinished()).isFalse();
        }

        @Test
        void shouldReturnFinishedTaskWithinTimePeriod() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T11:00:00Z");
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("finished-in-period")
                    .startTime(startTime)
                    .build());
            tracker.taskFinished(CompactionTaskStatus.builder()
                    .taskId("finished-in-period")
                    .startTime(startTime)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(finishTime)
                            .timeSpentOnJobs(Duration.ofMinutes(30))
                            .totalJobRuns(2)
                            .totalRecordsRead(1000)
                            .totalRecordsWritten(900)
                            .recordsReadPerSecond(50.0)
                            .recordsWrittenPerSecond(45.0)
                            .build())
                    .build());

            // When
            List<CompactionTaskStatus> result = tracker.getTasksInTimePeriod(
                    Instant.parse("2024-01-15T00:00:00Z"),
                    Instant.parse("2024-01-16T00:00:00Z"));

            // Then
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getTaskId()).isEqualTo("finished-in-period");
            assertThat(result.get(0).isFinished()).isTrue();
        }

        @Test
        void shouldFilterOutTasksOutsideTimePeriod() {
            // Given - a finished task outside the window (finished before query window start)
            Instant insideStartTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant outsideStartTime = Instant.parse("2024-01-10T10:00:00Z");
            Instant outsideFinishTime = Instant.parse("2024-01-10T12:00:00Z");

            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("inside-task")
                    .startTime(insideStartTime)
                    .build());

            // Mark the outside task as finished before the query window
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("before-task")
                    .startTime(outsideStartTime)
                    .build());
            tracker.taskFinished(CompactionTaskStatus.builder()
                    .taskId("before-task")
                    .startTime(outsideStartTime)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(outsideFinishTime)
                            .timeSpentOnJobs(Duration.ofMinutes(30))
                            .totalJobRuns(1)
                            .totalRecordsRead(100)
                            .totalRecordsWritten(90)
                            .recordsReadPerSecond(10.0)
                            .recordsWrittenPerSecond(9.0)
                            .build())
                    .build());

            // When
            List<CompactionTaskStatus> result = tracker.getTasksInTimePeriod(
                    Instant.parse("2024-01-15T00:00:00Z"),
                    Instant.parse("2024-01-16T00:00:00Z"));

            // Then
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getTaskId()).isEqualTo("inside-task");
        }
    }

    @Nested
    @DisplayName("getTasksInProgress")
    class GetTasksInProgress {

        @Test
        void shouldReturnEmptyListWhenNoTasksInProgress() {
            // When
            List<CompactionTaskStatus> result = tracker.getTasksInProgress();

            // Then
            assertThat(result).isEmpty();
        }

        @Test
        void shouldReturnOnlyUnfinishedTasks() {
            // Given
            Instant startTime1 = Instant.parse("2024-01-15T10:00:00Z");
            Instant startTime2 = Instant.parse("2024-01-15T11:00:00Z");
            Instant finishTime2 = Instant.parse("2024-01-15T11:30:00Z");

            // Task 1 - in progress
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("in-progress-task")
                    .startTime(startTime1)
                    .build());

            // Task 2 - finished
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("finished-task")
                    .startTime(startTime2)
                    .build());
            tracker.taskFinished(CompactionTaskStatus.builder()
                    .taskId("finished-task")
                    .startTime(startTime2)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(finishTime2)
                            .timeSpentOnJobs(Duration.ofMinutes(20))
                            .totalJobRuns(1)
                            .totalRecordsRead(500)
                            .totalRecordsWritten(450)
                            .recordsReadPerSecond(50.0)
                            .recordsWrittenPerSecond(45.0)
                            .build())
                    .build());

            // When
            List<CompactionTaskStatus> result = tracker.getTasksInProgress();

            // Then
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getTaskId()).isEqualTo("in-progress-task");
            assertThat(result.get(0).isFinished()).isFalse();
        }

        @Test
        void shouldReturnMultipleTasksInProgress() {
            // Given
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("progress-task-1")
                    .startTime(Instant.parse("2024-01-15T10:00:00Z"))
                    .build());
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("progress-task-2")
                    .startTime(Instant.parse("2024-01-15T11:00:00Z"))
                    .build());
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("progress-task-3")
                    .startTime(Instant.parse("2024-01-15T12:00:00Z"))
                    .build());

            // When
            List<CompactionTaskStatus> result = tracker.getTasksInProgress();

            // Then
            assertThat(result).hasSize(3);
            assertThat(result).extracting(CompactionTaskStatus::getTaskId)
                    .containsExactlyInAnyOrder("progress-task-1", "progress-task-2", "progress-task-3");
        }

        @Test
        void shouldReturnEmptyListWhenAllTasksAreFinished() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T11:00:00Z");
            tracker.taskStarted(CompactionTaskStatus.builder()
                    .taskId("all-finished-task")
                    .startTime(startTime)
                    .build());
            tracker.taskFinished(CompactionTaskStatus.builder()
                    .taskId("all-finished-task")
                    .startTime(startTime)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(finishTime)
                            .timeSpentOnJobs(Duration.ofMinutes(30))
                            .totalJobRuns(2)
                            .totalRecordsRead(1000)
                            .totalRecordsWritten(900)
                            .recordsReadPerSecond(50.0)
                            .recordsWrittenPerSecond(45.0)
                            .build())
                    .build());

            // When
            List<CompactionTaskStatus> result = tracker.getTasksInProgress();

            // Then
            assertThat(result).isEmpty();
        }
    }

    @Nested
    @DisplayName("taskStatusTableName")
    class TaskStatusTableName {

        @Test
        void shouldGenerateCorrectTableName() {
            // When
            String tableName = DynamoDBCompactionTaskTracker.taskStatusTableName("test-instance");

            // Then
            assertThat(tableName).isEqualTo("sleeper-test-instance-compaction-task-status");
        }

        @Test
        void shouldHandleEmptyInstanceId() {
            // When
            String tableName = DynamoDBCompactionTaskTracker.taskStatusTableName("");

            // Then
            assertThat(tableName).isEqualTo("sleeper--compaction-task-status");
        }

        @Test
        void shouldHandleInstanceIdWithSpecialCharacters() {
            // When
            String tableName = DynamoDBCompactionTaskTracker.taskStatusTableName("my-instance-123");

            // Then
            assertThat(tableName).isEqualTo("sleeper-my-instance-123-compaction-task-status");
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
            instanceProperties.set(COMPACTION_TASK_STATUS_TTL_IN_SECONDS, String.valueOf(ttlSeconds));

            // Need to recreate tracker with new TTL settings
            String newInstanceId = instanceProperties.get(ID) + "-ttl-test";
            InstanceProperties ttlProperties = createTestInstanceProperties();
            ttlProperties.set(ID, newInstanceId);
            ttlProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            ttlProperties.set(COMPACTION_TASK_STATUS_TTL_IN_SECONDS, String.valueOf(ttlSeconds));
            DynamoDBCompactionTaskTrackerCreator.create(ttlProperties, dynamoClient);

            DynamoDBCompactionTaskTracker ttlTracker = new DynamoDBCompactionTaskTracker(
                    dynamoClient, ttlProperties, () -> fixedTime);

            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("ttl-task")
                    .startTime(Instant.parse("2024-01-15T09:00:00Z"))
                    .build();

            // When
            ttlTracker.taskStarted(taskStatus);

            // Then
            CompactionTaskStatus retrieved = ttlTracker.getTask("ttl-task");
            assertThat(retrieved).isNotNull();
            assertThat(retrieved.getExpiryDate()).isEqualTo(fixedTime.plusSeconds(ttlSeconds));
        }
    }

    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {

        @Test
        void shouldHandleVeryLongTaskId() {
            // Given
            String longTaskId = "a".repeat(200);
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId(longTaskId)
                    .startTime(Instant.now())
                    .build();

            // When
            tracker.taskStarted(taskStatus);
            CompactionTaskStatus result = tracker.getTask(longTaskId);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getTaskId()).isEqualTo(longTaskId);
        }

        @Test
        void shouldHandleTaskWithVeryLargeDuration() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T00:00:00Z");
            Instant finishTime = Instant.parse("2024-01-20T00:00:00Z"); // 5 days later
            Duration timeSpentOnJobs = Duration.ofDays(4);
            CompactionTaskStatus startedTask = CompactionTaskStatus.builder()
                    .taskId("long-duration-task")
                    .startTime(startTime)
                    .build();
            CompactionTaskStatus finishedTask = CompactionTaskStatus.builder()
                    .taskId("long-duration-task")
                    .startTime(startTime)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(finishTime)
                            .timeSpentOnJobs(timeSpentOnJobs)
                            .totalJobRuns(100)
                            .totalRecordsRead(10000000)
                            .totalRecordsWritten(9000000)
                            .recordsReadPerSecond(1000.0)
                            .recordsWrittenPerSecond(900.0)
                            .build())
                    .build();

            tracker.taskStarted(startedTask);
            tracker.taskFinished(finishedTask);

            // When
            CompactionTaskStatus result = tracker.getTask("long-duration-task");

            // Then
            assertThat(result).isNotNull();
            assertThat(result.isFinished()).isTrue();
            assertThat(result.getFinishedStatus().getTimeSpentOnJobs()).isEqualTo(timeSpentOnJobs);
        }

        @Test
        void shouldHandleTaskWithLargeValues() {
            // Given - using large but valid values (not max values which can cause DynamoDB issues)
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T11:00:00Z");
            CompactionTaskStatus startedTask = CompactionTaskStatus.builder()
                    .taskId("large-values-task")
                    .startTime(startTime)
                    .build();
            CompactionTaskStatus finishedTask = CompactionTaskStatus.builder()
                    .taskId("large-values-task")
                    .startTime(startTime)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(finishTime)
                            .timeSpentOnJobs(Duration.ofMinutes(30))
                            .totalJobRuns(1000000)
                            .totalRecordsRead(1000000000000L)
                            .totalRecordsWritten(900000000000L)
                            .recordsReadPerSecond(1000000.5)
                            .recordsWrittenPerSecond(900000.5)
                            .build())
                    .build();

            tracker.taskStarted(startedTask);
            tracker.taskFinished(finishedTask);

            // When
            CompactionTaskStatus result = tracker.getTask("large-values-task");

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getFinishedStatus().getTotalJobRuns()).isEqualTo(1000000);
            assertThat(result.getFinishedStatus().getTotalRecordsRead()).isEqualTo(1000000000000L);
            assertThat(result.getFinishedStatus().getTotalRecordsWritten()).isEqualTo(900000000000L);
        }
    }

    @Nested
    @DisplayName("Full task lifecycle")
    class FullTaskLifecycle {

        @Test
        void shouldTrackCompleteTaskLifecycle() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T12:00:00Z");
            String taskId = "lifecycle-task";

            // When - Start task
            CompactionTaskStatus startedTask = CompactionTaskStatus.builder()
                    .taskId(taskId)
                    .startTime(startTime)
                    .build();
            tracker.taskStarted(startedTask);

            // Verify started state
            CompactionTaskStatus afterStart = tracker.getTask(taskId);
            assertThat(afterStart).isNotNull();
            assertThat(afterStart.isFinished()).isFalse();
            assertThat(tracker.getTasksInProgress()).hasSize(1);

            // When - Finish task
            CompactionTaskStatus finishedTask = CompactionTaskStatus.builder()
                    .taskId(taskId)
                    .startTime(startTime)
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(finishTime)
                            .timeSpentOnJobs(Duration.ofMinutes(90))
                            .totalJobRuns(10)
                            .totalRecordsRead(50000)
                            .totalRecordsWritten(45000)
                            .recordsReadPerSecond(500.0)
                            .recordsWrittenPerSecond(450.0)
                            .build())
                    .build();
            tracker.taskFinished(finishedTask);

            // Verify finished state
            CompactionTaskStatus afterFinish = tracker.getTask(taskId);
            assertThat(afterFinish).isNotNull();
            assertThat(afterFinish.isFinished()).isTrue();
            assertThat(afterFinish.getDuration()).isEqualTo(Duration.ofHours(2));
            assertThat(tracker.getTasksInProgress()).isEmpty();
        }

        @Test
        void shouldTrackMultipleConcurrentTasks() {
            // Given
            String[] taskIds = {"concurrent-1", "concurrent-2", "concurrent-3"};
            Instant[] startTimes = {
                    Instant.parse("2024-01-15T10:00:00Z"),
                    Instant.parse("2024-01-15T10:05:00Z"),
                    Instant.parse("2024-01-15T10:10:00Z")
            };

            // When - Start all tasks
            for (int i = 0; i < taskIds.length; i++) {
                tracker.taskStarted(CompactionTaskStatus.builder()
                        .taskId(taskIds[i])
                        .startTime(startTimes[i])
                        .build());
            }

            // Then - All should be in progress
            assertThat(tracker.getTasksInProgress()).hasSize(3);
            assertThat(tracker.getAllTasks()).hasSize(3);

            // When - Finish first task
            tracker.taskFinished(CompactionTaskStatus.builder()
                    .taskId(taskIds[0])
                    .startTime(startTimes[0])
                    .finishedStatus(CompactionTaskFinishedStatus.builder()
                            .finishTime(startTimes[0].plus(Duration.ofMinutes(30)))
                            .timeSpentOnJobs(Duration.ofMinutes(25))
                            .totalJobRuns(3)
                            .totalRecordsRead(1000)
                            .totalRecordsWritten(900)
                            .recordsReadPerSecond(40.0)
                            .recordsWrittenPerSecond(36.0)
                            .build())
                    .build());

            // Then - Two should be in progress
            assertThat(tracker.getTasksInProgress()).hasSize(2);
            assertThat(tracker.getAllTasks()).hasSize(3);
        }
    }
}
