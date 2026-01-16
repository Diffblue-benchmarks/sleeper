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

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.tracker.compaction.task.CompactionTaskFinishedStatus;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus;
import sleeper.dynamodb.tools.DynamoDBRecordBuilder;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.dynamodb.tools.DynamoDBAttributes.getDoubleAttribute;
import static sleeper.dynamodb.tools.DynamoDBAttributes.getInstantAttribute;
import static sleeper.dynamodb.tools.DynamoDBAttributes.getIntAttribute;
import static sleeper.dynamodb.tools.DynamoDBAttributes.getLongAttribute;
import static sleeper.dynamodb.tools.DynamoDBAttributes.getStringAttribute;

class DynamoDBCompactionTaskStatusFormatClaudeTest {

    private static final String TASK_ID = "TaskId";
    private static final String UPDATE_TYPE = "UpdateType";
    private static final String START_TIME = "StartTime";
    private static final String UPDATE_TIME = "UpdateTime";
    private static final String FINISH_TIME = "FinishTime";
    private static final String MILLIS_SPENT_ON_JOBS = "MillisecondsOnJobs";
    private static final String NUMBER_OF_JOBS = "NumberOfJobs";
    private static final String RECORDS_READ = "RecordsRead";
    private static final String RECORDS_WRITTEN = "RecordsWritten";
    private static final String READ_RATE = "ReadRate";
    private static final String WRITE_RATE = "WriteRate";
    private static final String EXPIRY_DATE = "ExpiryDate";

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Test
        void shouldCreateFormatWithTimeToLiveAndTimeSupplier() {
            // Given
            Instant fixedTime = Instant.parse("2024-01-15T10:00:00Z");
            int ttlSeconds = 3600;

            // When
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(
                    ttlSeconds, () -> fixedTime);

            // Then - verify by creating a record and checking expiry
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("test-task")
                    .startTime(Instant.parse("2024-01-15T09:00:00Z"))
                    .build();
            Map<String, AttributeValue> record = format.createTaskStartedRecord(taskStatus);
            long expiryEpochSeconds = getInstantAttribute(record, EXPIRY_DATE, Instant::ofEpochSecond).getEpochSecond();
            assertThat(expiryEpochSeconds).isEqualTo(fixedTime.getEpochSecond() + ttlSeconds);
        }

        @Test
        void shouldUseSuppliedTimeForUpdateTime() {
            // Given
            Instant fixedTime = Instant.parse("2024-01-15T12:00:00Z");

            // When
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(
                    3600, () -> fixedTime);
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("test-task")
                    .startTime(Instant.parse("2024-01-15T11:00:00Z"))
                    .build();
            Map<String, AttributeValue> record = format.createTaskStartedRecord(taskStatus);

            // Then
            assertThat(getInstantAttribute(record, UPDATE_TIME)).isEqualTo(fixedTime);
        }
    }

    @Nested
    @DisplayName("createTaskStartedRecord")
    class CreateTaskStartedRecord {

        @Test
        void shouldCreateRecordWithTaskId() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("my-task-123")
                    .startTime(Instant.parse("2024-01-15T09:30:00Z"))
                    .build();

            // When
            Map<String, AttributeValue> record = format.createTaskStartedRecord(taskStatus);

            // Then
            assertThat(getStringAttribute(record, TASK_ID)).isEqualTo("my-task-123");
        }

        @Test
        void shouldCreateRecordWithStartTimeAsEpochMillis() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            Instant startTime = Instant.parse("2024-01-15T09:30:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("test-task")
                    .startTime(startTime)
                    .build();

            // When
            Map<String, AttributeValue> record = format.createTaskStartedRecord(taskStatus);

            // Then
            assertThat(getInstantAttribute(record, START_TIME)).isEqualTo(startTime);
        }

        @Test
        void shouldCreateRecordWithStartedUpdateType() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("test-task")
                    .startTime(Instant.parse("2024-01-15T09:30:00Z"))
                    .build();

            // When
            Map<String, AttributeValue> record = format.createTaskStartedRecord(taskStatus);

            // Then
            assertThat(getStringAttribute(record, UPDATE_TYPE)).isEqualTo("started");
        }

        @Test
        void shouldCreateRecordWithUpdateTime() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("test-task")
                    .startTime(Instant.parse("2024-01-15T09:30:00Z"))
                    .build();

            // When
            Map<String, AttributeValue> record = format.createTaskStartedRecord(taskStatus);

            // Then
            assertThat(getInstantAttribute(record, UPDATE_TIME)).isEqualTo(now);
        }

        @Test
        void shouldCreateRecordWithExpiryDateBasedOnTTL() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            int ttlSeconds = 7200;
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(ttlSeconds, () -> now);
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("test-task")
                    .startTime(Instant.parse("2024-01-15T09:30:00Z"))
                    .build();

            // When
            Map<String, AttributeValue> record = format.createTaskStartedRecord(taskStatus);

            // Then
            Instant expectedExpiry = now.plusSeconds(ttlSeconds);
            assertThat(getInstantAttribute(record, EXPIRY_DATE, Instant::ofEpochSecond)).isEqualTo(expectedExpiry);
        }
    }

    @Nested
    @DisplayName("createTaskFinishedRecord")
    class CreateTaskFinishedRecord {

        @Test
        void shouldCreateRecordWithTaskId() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = createFinishedTaskStatus("finished-task-456",
                    Instant.parse("2024-01-15T08:00:00Z"),
                    Instant.parse("2024-01-15T09:30:00Z"));

            // When
            Map<String, AttributeValue> record = format.createTaskFinishedRecord(taskStatus);

            // Then
            assertThat(getStringAttribute(record, TASK_ID)).isEqualTo("finished-task-456");
        }

        @Test
        void shouldCreateRecordWithStartTime() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            Instant startTime = Instant.parse("2024-01-15T08:00:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = createFinishedTaskStatus("test-task", startTime,
                    Instant.parse("2024-01-15T09:30:00Z"));

            // When
            Map<String, AttributeValue> record = format.createTaskFinishedRecord(taskStatus);

            // Then
            assertThat(getInstantAttribute(record, START_TIME)).isEqualTo(startTime);
        }

        @Test
        void shouldCreateRecordWithFinishTime() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T09:30:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = createFinishedTaskStatus("test-task",
                    Instant.parse("2024-01-15T08:00:00Z"), finishTime);

            // When
            Map<String, AttributeValue> record = format.createTaskFinishedRecord(taskStatus);

            // Then
            assertThat(getInstantAttribute(record, FINISH_TIME)).isEqualTo(finishTime);
        }

        @Test
        void shouldCreateRecordWithFinishedUpdateType() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = createFinishedTaskStatus("test-task",
                    Instant.parse("2024-01-15T08:00:00Z"),
                    Instant.parse("2024-01-15T09:30:00Z"));

            // When
            Map<String, AttributeValue> record = format.createTaskFinishedRecord(taskStatus);

            // Then
            assertThat(getStringAttribute(record, UPDATE_TYPE)).isEqualTo("finished");
        }

        @Test
        void shouldCreateRecordWithTimeSpentOnJobs() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            Duration timeSpentOnJobs = Duration.ofMinutes(45);
            CompactionTaskStatus taskStatus = createFinishedTaskStatusWithDetails("test-task",
                    Instant.parse("2024-01-15T08:00:00Z"),
                    Instant.parse("2024-01-15T09:30:00Z"),
                    timeSpentOnJobs, 5, 1000, 950, 100.0, 95.0);

            // When
            Map<String, AttributeValue> record = format.createTaskFinishedRecord(taskStatus);

            // Then
            assertThat(getLongAttribute(record, MILLIS_SPENT_ON_JOBS, -1)).isEqualTo(timeSpentOnJobs.toMillis());
        }

        @Test
        void shouldCreateRecordWithNumberOfJobs() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = createFinishedTaskStatusWithDetails("test-task",
                    Instant.parse("2024-01-15T08:00:00Z"),
                    Instant.parse("2024-01-15T09:30:00Z"),
                    Duration.ofMinutes(30), 7, 1000, 950, 100.0, 95.0);

            // When
            Map<String, AttributeValue> record = format.createTaskFinishedRecord(taskStatus);

            // Then
            assertThat(getIntAttribute(record, NUMBER_OF_JOBS, -1)).isEqualTo(7);
        }

        @Test
        void shouldCreateRecordWithRecordsReadAndWritten() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = createFinishedTaskStatusWithDetails("test-task",
                    Instant.parse("2024-01-15T08:00:00Z"),
                    Instant.parse("2024-01-15T09:30:00Z"),
                    Duration.ofMinutes(30), 3, 50000, 48000, 100.0, 95.0);

            // When
            Map<String, AttributeValue> record = format.createTaskFinishedRecord(taskStatus);

            // Then
            assertThat(getLongAttribute(record, RECORDS_READ, -1)).isEqualTo(50000);
            assertThat(getLongAttribute(record, RECORDS_WRITTEN, -1)).isEqualTo(48000);
        }

        @Test
        void shouldCreateRecordWithReadAndWriteRates() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(3600, () -> now);
            CompactionTaskStatus taskStatus = createFinishedTaskStatusWithDetails("test-task",
                    Instant.parse("2024-01-15T08:00:00Z"),
                    Instant.parse("2024-01-15T09:30:00Z"),
                    Duration.ofMinutes(30), 3, 50000, 48000, 250.5, 240.3);

            // When
            Map<String, AttributeValue> record = format.createTaskFinishedRecord(taskStatus);

            // Then
            assertThat(getDoubleAttribute(record, READ_RATE, -1)).isEqualTo(250.5);
            assertThat(getDoubleAttribute(record, WRITE_RATE, -1)).isEqualTo(240.3);
        }

        @Test
        void shouldCreateRecordWithUpdateTimeAndExpiryDate() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            int ttlSeconds = 3600;
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(ttlSeconds, () -> now);
            CompactionTaskStatus taskStatus = createFinishedTaskStatus("test-task",
                    Instant.parse("2024-01-15T08:00:00Z"),
                    Instant.parse("2024-01-15T09:30:00Z"));

            // When
            Map<String, AttributeValue> record = format.createTaskFinishedRecord(taskStatus);

            // Then
            assertThat(getInstantAttribute(record, UPDATE_TIME)).isEqualTo(now);
            assertThat(getInstantAttribute(record, EXPIRY_DATE, Instant::ofEpochSecond))
                    .isEqualTo(now.plusSeconds(ttlSeconds));
        }
    }

    @Nested
    @DisplayName("streamTaskStatuses")
    class StreamTaskStatuses {

        @Test
        void shouldDeserializeStartedTaskFromDynamoDBItem() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "test-task")
                    .string(UPDATE_TYPE, "started")
                    .number(START_TIME, startTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();

            // When
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(item)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionTaskStatus status = statuses.get(0);
            assertThat(status.getTaskId()).isEqualTo("test-task");
            assertThat(status.getStartTime()).isEqualTo(startTime);
            assertThat(status.getExpiryDate()).isEqualTo(expiry);
            assertThat(status.isFinished()).isFalse();
        }

        @Test
        void shouldDeserializeFinishedTaskFromDynamoDBItem() {
            // Given
            Instant finishTime = Instant.parse("2024-01-15T11:30:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "test-task")
                    .string(UPDATE_TYPE, "finished")
                    .number(FINISH_TIME, finishTime.toEpochMilli())
                    .number(MILLIS_SPENT_ON_JOBS, Duration.ofMinutes(30).toMillis())
                    .number(NUMBER_OF_JOBS, 5)
                    .number(RECORDS_READ, 10000)
                    .number(RECORDS_WRITTEN, 9500)
                    .number(READ_RATE, 500.0)
                    .number(WRITE_RATE, 475.0)
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();

            // When
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(item)).toList();

            // Then
            assertThat(statuses).isEmpty();
        }

        @Test
        void shouldCombineStartedAndFinishedRecordsForSameTask() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T11:30:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> startedItem = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "test-task")
                    .string(UPDATE_TYPE, "started")
                    .number(START_TIME, startTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();
            Map<String, AttributeValue> finishedItem = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "test-task")
                    .string(UPDATE_TYPE, "finished")
                    .number(FINISH_TIME, finishTime.toEpochMilli())
                    .number(MILLIS_SPENT_ON_JOBS, Duration.ofMinutes(30).toMillis())
                    .number(NUMBER_OF_JOBS, 5)
                    .number(RECORDS_READ, 10000)
                    .number(RECORDS_WRITTEN, 9500)
                    .number(READ_RATE, 500.0)
                    .number(WRITE_RATE, 475.0)
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();

            // When
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(startedItem, finishedItem)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionTaskStatus status = statuses.get(0);
            assertThat(status.getTaskId()).isEqualTo("test-task");
            assertThat(status.getStartTime()).isEqualTo(startTime);
            assertThat(status.isFinished()).isTrue();
            assertThat(status.getFinishedStatus().getFinishTime()).isEqualTo(finishTime);
            assertThat(status.getFinishedStatus().getTotalJobRuns()).isEqualTo(5);
            assertThat(status.getFinishedStatus().getTotalRecordsRead()).isEqualTo(10000);
            assertThat(status.getFinishedStatus().getTotalRecordsWritten()).isEqualTo(9500);
        }

        @Test
        void shouldHandleMultipleDifferentTasks() {
            // Given
            Instant startTime1 = Instant.parse("2024-01-15T10:00:00Z");
            Instant startTime2 = Instant.parse("2024-01-15T11:00:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> task1Item = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "task-1")
                    .string(UPDATE_TYPE, "started")
                    .number(START_TIME, startTime1.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();
            Map<String, AttributeValue> task2Item = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "task-2")
                    .string(UPDATE_TYPE, "started")
                    .number(START_TIME, startTime2.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();

            // When
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(task1Item, task2Item)).toList();

            // Then
            assertThat(statuses).hasSize(2);
            assertThat(statuses).extracting(CompactionTaskStatus::getTaskId)
                    .containsExactlyInAnyOrder("task-1", "task-2");
        }

        @Test
        void shouldSortTasksByStartTimeDescending() {
            // Given
            Instant startTime1 = Instant.parse("2024-01-15T08:00:00Z");
            Instant startTime2 = Instant.parse("2024-01-15T10:00:00Z");
            Instant startTime3 = Instant.parse("2024-01-15T09:00:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> task1Item = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "task-1")
                    .string(UPDATE_TYPE, "started")
                    .number(START_TIME, startTime1.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();
            Map<String, AttributeValue> task2Item = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "task-2")
                    .string(UPDATE_TYPE, "started")
                    .number(START_TIME, startTime2.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();
            Map<String, AttributeValue> task3Item = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "task-3")
                    .string(UPDATE_TYPE, "started")
                    .number(START_TIME, startTime3.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();

            // When
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(task1Item, task2Item, task3Item)).toList();

            // Then
            assertThat(statuses).extracting(CompactionTaskStatus::getTaskId)
                    .containsExactly("task-2", "task-3", "task-1");
        }

        @Test
        void shouldHandleEmptyStream() {
            // When
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.empty()).toList();

            // Then
            assertThat(statuses).isEmpty();
        }

        @Test
        void shouldIgnoreUnrecognisedUpdateType() {
            // Given
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "test-task")
                    .string(UPDATE_TYPE, "unknown")
                    .number(START_TIME, Instant.now().toEpochMilli())
                    .number(EXPIRY_DATE, Instant.now().getEpochSecond())
                    .build();

            // When
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(item)).toList();

            // Then
            assertThat(statuses).isEmpty();
        }

        @Test
        void shouldHandleDefaultValuesForMissingFinishedAttributes() {
            // Given - finished record without optional fields, combined with started record
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T11:30:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> startedItem = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "test-task")
                    .string(UPDATE_TYPE, "started")
                    .number(START_TIME, startTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();
            Map<String, AttributeValue> finishedItem = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "test-task")
                    .string(UPDATE_TYPE, "finished")
                    .number(FINISH_TIME, finishTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();

            // When
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(startedItem, finishedItem)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionTaskFinishedStatus finishedStatus = statuses.get(0).getFinishedStatus();
            assertThat(finishedStatus.getTimeSpentOnJobs()).isEqualTo(Duration.ZERO);
            assertThat(finishedStatus.getTotalJobRuns()).isZero();
            assertThat(finishedStatus.getTotalRecordsRead()).isZero();
            assertThat(finishedStatus.getTotalRecordsWritten()).isZero();
            assertThat(finishedStatus.getRecordsReadPerSecond()).isZero();
            assertThat(finishedStatus.getRecordsWrittenPerSecond()).isZero();
        }

        @Test
        void shouldIgnoreFinishedRecordWithoutStartedRecord() {
            // Given - only finished record, no started record
            Instant finishTime = Instant.parse("2024-01-15T11:30:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> finishedItem = new DynamoDBRecordBuilder()
                    .string(TASK_ID, "orphan-task")
                    .string(UPDATE_TYPE, "finished")
                    .number(FINISH_TIME, finishTime.toEpochMilli())
                    .number(MILLIS_SPENT_ON_JOBS, 1000)
                    .number(NUMBER_OF_JOBS, 1)
                    .number(RECORDS_READ, 100)
                    .number(RECORDS_WRITTEN, 100)
                    .number(READ_RATE, 10.0)
                    .number(WRITE_RATE, 10.0)
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .build();

            // When
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(finishedItem)).toList();

            // Then
            assertThat(statuses).isEmpty();
        }
    }

    @Nested
    @DisplayName("Round-trip serialization and deserialization")
    class RoundTrip {

        @Test
        void shouldRoundTripStartedTask() {
            // Given
            Instant now = Instant.parse("2024-01-15T10:00:00Z");
            Instant startTime = Instant.parse("2024-01-15T09:30:00Z");
            int ttlSeconds = 3600;
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(ttlSeconds, () -> now);
            CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                    .taskId("roundtrip-task")
                    .startTime(startTime)
                    .build();

            // When - serialize
            Map<String, AttributeValue> record = format.createTaskStartedRecord(taskStatus);

            // Then - deserialize
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(record)).toList();

            assertThat(statuses).hasSize(1);
            CompactionTaskStatus result = statuses.get(0);
            assertThat(result.getTaskId()).isEqualTo("roundtrip-task");
            assertThat(result.getStartTime()).isEqualTo(startTime);
            assertThat(result.getExpiryDate()).isEqualTo(now.plusSeconds(ttlSeconds));
            assertThat(result.isFinished()).isFalse();
        }

        @Test
        void shouldRoundTripFinishedTask() {
            // Given
            Instant now = Instant.parse("2024-01-15T12:00:00Z");
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T11:30:00Z");
            int ttlSeconds = 3600;
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(ttlSeconds, () -> now);
            CompactionTaskStatus taskStatus = createFinishedTaskStatusWithDetails("roundtrip-finished",
                    startTime, finishTime,
                    Duration.ofMinutes(45), 8, 25000, 24000, 555.5, 533.3);

            // When - serialize both records
            Map<String, AttributeValue> startedRecord = format.createTaskStartedRecord(
                    CompactionTaskStatus.builder().taskId("roundtrip-finished").startTime(startTime).build());
            Map<String, AttributeValue> finishedRecord = format.createTaskFinishedRecord(taskStatus);

            // Then - deserialize
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(startedRecord, finishedRecord)).toList();

            assertThat(statuses).hasSize(1);
            CompactionTaskStatus result = statuses.get(0);
            assertThat(result.getTaskId()).isEqualTo("roundtrip-finished");
            assertThat(result.getStartTime()).isEqualTo(startTime);
            assertThat(result.isFinished()).isTrue();
            CompactionTaskFinishedStatus finished = result.getFinishedStatus();
            assertThat(finished.getFinishTime()).isEqualTo(finishTime);
            assertThat(finished.getTimeSpentOnJobs()).isEqualTo(Duration.ofMinutes(45));
            assertThat(finished.getTotalJobRuns()).isEqualTo(8);
            assertThat(finished.getTotalRecordsRead()).isEqualTo(25000);
            assertThat(finished.getTotalRecordsWritten()).isEqualTo(24000);
            assertThat(finished.getRecordsReadPerSecond()).isEqualTo(555.5);
            assertThat(finished.getRecordsWrittenPerSecond()).isEqualTo(533.3);
        }

        @Test
        void shouldRoundTripMultipleTasksInDifferentStates() {
            // Given
            Instant now = Instant.parse("2024-01-15T14:00:00Z");
            int ttlSeconds = 3600;
            DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(ttlSeconds, () -> now);

            CompactionTaskStatus startedTask = CompactionTaskStatus.builder()
                    .taskId("task-running")
                    .startTime(Instant.parse("2024-01-15T13:00:00Z"))
                    .build();
            CompactionTaskStatus finishedTask = createFinishedTaskStatusWithDetails("task-done",
                    Instant.parse("2024-01-15T11:00:00Z"),
                    Instant.parse("2024-01-15T12:30:00Z"),
                    Duration.ofMinutes(60), 10, 100000, 95000, 1000.0, 950.0);

            // When - serialize
            Map<String, AttributeValue> startedRecord = format.createTaskStartedRecord(startedTask);
            Map<String, AttributeValue> finishedTaskStartRecord = format.createTaskStartedRecord(
                    CompactionTaskStatus.builder()
                            .taskId("task-done")
                            .startTime(Instant.parse("2024-01-15T11:00:00Z"))
                            .build());
            Map<String, AttributeValue> finishedRecord = format.createTaskFinishedRecord(finishedTask);

            // Then - deserialize
            List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                    Stream.of(startedRecord, finishedTaskStartRecord, finishedRecord)).toList();

            assertThat(statuses).hasSize(2);
            assertThat(statuses).extracting(CompactionTaskStatus::getTaskId)
                    .containsExactly("task-running", "task-done");
            assertThat(statuses.get(0).isFinished()).isFalse();
            assertThat(statuses.get(1).isFinished()).isTrue();
        }
    }

    private CompactionTaskStatus createFinishedTaskStatus(String taskId, Instant startTime, Instant finishTime) {
        return createFinishedTaskStatusWithDetails(taskId, startTime, finishTime,
                Duration.ofMinutes(30), 3, 1000, 950, 100.0, 95.0);
    }

    private CompactionTaskStatus createFinishedTaskStatusWithDetails(
            String taskId, Instant startTime, Instant finishTime,
            Duration timeSpentOnJobs, int totalJobRuns,
            long recordsRead, long recordsWritten,
            double readRate, double writeRate) {
        return CompactionTaskStatus.builder()
                .taskId(taskId)
                .startTime(startTime)
                .finishedStatus(CompactionTaskFinishedStatus.builder()
                        .finishTime(finishTime)
                        .timeSpentOnJobs(timeSpentOnJobs)
                        .totalJobRuns(totalJobRuns)
                        .totalRecordsRead(recordsRead)
                        .totalRecordsWritten(recordsWritten)
                        .recordsReadPerSecond(readRate)
                        .recordsWrittenPerSecond(writeRate)
                        .build())
                .build();
    }
}
