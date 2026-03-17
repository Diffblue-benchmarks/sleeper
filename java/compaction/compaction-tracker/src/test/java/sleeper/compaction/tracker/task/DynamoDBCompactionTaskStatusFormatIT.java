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
import org.junit.jupiter.api.Test;

import sleeper.core.tracker.compaction.task.CompactionTaskFinishedStatus;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.RecordsProcessed;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.EXPIRY_DATE;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.FINISH_TIME;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.FINISHED;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.MILLIS_SPENT_ON_JOBS;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.NUMBER_OF_JOBS;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.READ_RATE;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.RECORDS_READ;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.RECORDS_WRITTEN;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.START_TIME;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.STARTED;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.TASK_ID;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.UPDATE_TIME;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE;
import static sleeper.compaction.tracker.task.DynamoDBCompactionTaskStatusFormat.WRITE_RATE;

public class DynamoDBCompactionTaskStatusFormatIT {

    private static final Instant DEFAULT_TIME_NOW = Instant.parse("2022-09-22T14:00:00.000Z");
    private static final int DEFAULT_TTL_SECONDS = 604800;

    @Test
    public void shouldCreateTaskStartedRecord() {
        // Given
        DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(
                DEFAULT_TTL_SECONDS, () -> DEFAULT_TIME_NOW);
        Instant startTime = Instant.parse("2022-09-22T12:30:00.000Z");
        CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                .taskId("test-task-1")
                .startTime(startTime)
                .build();

        // When
        Map<String, AttributeValue> record = format.createTaskStartedRecord(taskStatus);

        // Then
        assertThat(record).containsEntry(TASK_ID, new AttributeValue("test-task-1"));
        assertThat(record).containsEntry(UPDATE_TYPE, new AttributeValue(STARTED));
        assertThat(record).containsEntry(START_TIME, new AttributeValue().withN("" + startTime.toEpochMilli()));
        assertThat(record).containsEntry(UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()));
        assertThat(record).containsEntry(EXPIRY_DATE, new AttributeValue().withN("" + (DEFAULT_TIME_NOW.getEpochSecond() + DEFAULT_TTL_SECONDS)));
    }

    @Test
    public void shouldCreateTaskFinishedRecord() {
        // Given
        DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(
                DEFAULT_TTL_SECONDS, () -> DEFAULT_TIME_NOW);
        Instant startTime = Instant.parse("2022-09-22T12:30:00.000Z");
        Instant finishTime = Instant.parse("2022-09-22T16:30:00.000Z");
        Instant jobStartTime = Instant.parse("2022-09-22T14:00:00.000Z");
        Instant jobFinishTime = Instant.parse("2022-09-22T14:00:10.000Z");
        CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                .taskId("test-task-2")
                .startTime(startTime)
                .finished(finishTime, CompactionTaskFinishedStatus.builder()
                        .addJobSummary(new JobRunSummary(
                                new RecordsProcessed(4800L, 2400L),
                                jobStartTime, jobFinishTime)))
                .build();

        // When
        Map<String, AttributeValue> record = format.createTaskFinishedRecord(taskStatus);

        // Then
        assertThat(record).containsEntry(TASK_ID, new AttributeValue("test-task-2"));
        assertThat(record).containsEntry(UPDATE_TYPE, new AttributeValue(FINISHED));
        assertThat(record).containsEntry(START_TIME, new AttributeValue().withN("" + startTime.toEpochMilli()));
        assertThat(record).containsEntry(FINISH_TIME, new AttributeValue().withN("" + finishTime.toEpochMilli()));
        assertThat(record).containsEntry(UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()));
        assertThat(record).containsEntry(EXPIRY_DATE, new AttributeValue().withN("" + (DEFAULT_TIME_NOW.getEpochSecond() + DEFAULT_TTL_SECONDS)));
        assertThat(record).containsEntry(MILLIS_SPENT_ON_JOBS, new AttributeValue().withN("10000"));
        assertThat(record).containsEntry(NUMBER_OF_JOBS, new AttributeValue().withN("1"));
        assertThat(record).containsEntry(RECORDS_READ, new AttributeValue().withN("4800"));
        assertThat(record).containsEntry(RECORDS_WRITTEN, new AttributeValue().withN("2400"));
        assertThat(record).containsEntry(READ_RATE, new AttributeValue().withN("480.0"));
        assertThat(record).containsEntry(WRITE_RATE, new AttributeValue().withN("240.0"));
    }

    @Test
    public void shouldStreamTaskStatusesFromStartedRecord() {
        // Given
        Instant startTime = Instant.parse("2022-09-22T12:30:00.000Z");
        Instant expiryDate = Instant.parse("2022-09-29T12:30:00.000Z");
        Map<String, AttributeValue> startedRecord = Map.of(
                TASK_ID, new AttributeValue("test-task-3"),
                UPDATE_TYPE, new AttributeValue(STARTED),
                START_TIME, new AttributeValue().withN("" + startTime.toEpochMilli()),
                UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()),
                EXPIRY_DATE, new AttributeValue().withN("" + expiryDate.getEpochSecond()));

        // When
        List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                Stream.of(startedRecord)).toList();

        // Then
        assertThat(statuses).hasSize(1);
        assertThat(statuses.get(0).getTaskId()).isEqualTo("test-task-3");
        assertThat(statuses.get(0).getStartTime()).isEqualTo(startTime);
        assertThat(statuses.get(0).getExpiryDate()).isEqualTo(expiryDate);
        assertThat(statuses.get(0).isFinished()).isFalse();
    }

    @Test
    public void shouldStreamTaskStatusesFromStartedAndFinishedRecords() {
        // Given
        Instant startTime = Instant.parse("2022-09-22T12:30:00.000Z");
        Instant finishTime = Instant.parse("2022-09-22T16:30:00.000Z");
        Instant expiryDate = Instant.parse("2022-09-29T12:30:00.000Z");
        Map<String, AttributeValue> startedRecord = Map.of(
                TASK_ID, new AttributeValue("test-task-4"),
                UPDATE_TYPE, new AttributeValue(STARTED),
                START_TIME, new AttributeValue().withN("" + startTime.toEpochMilli()),
                UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()),
                EXPIRY_DATE, new AttributeValue().withN("" + expiryDate.getEpochSecond()));
        Map<String, AttributeValue> finishedRecord = new HashMap<>();
        finishedRecord.put(TASK_ID, new AttributeValue("test-task-4"));
        finishedRecord.put(UPDATE_TYPE, new AttributeValue(FINISHED));
        finishedRecord.put(START_TIME, new AttributeValue().withN("" + startTime.toEpochMilli()));
        finishedRecord.put(FINISH_TIME, new AttributeValue().withN("" + finishTime.toEpochMilli()));
        finishedRecord.put(UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()));
        finishedRecord.put(EXPIRY_DATE, new AttributeValue().withN("" + expiryDate.getEpochSecond()));
        finishedRecord.put(MILLIS_SPENT_ON_JOBS, new AttributeValue().withN("10000"));
        finishedRecord.put(NUMBER_OF_JOBS, new AttributeValue().withN("5"));
        finishedRecord.put(RECORDS_READ, new AttributeValue().withN("4800"));
        finishedRecord.put(RECORDS_WRITTEN, new AttributeValue().withN("2400"));
        finishedRecord.put(READ_RATE, new AttributeValue().withN("480.0"));
        finishedRecord.put(WRITE_RATE, new AttributeValue().withN("240.0"));

        // When
        List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                Stream.of(startedRecord, finishedRecord)).toList();

        // Then
        assertThat(statuses).hasSize(1);
        CompactionTaskStatus status = statuses.get(0);
        assertThat(status.getTaskId()).isEqualTo("test-task-4");
        assertThat(status.getStartTime()).isEqualTo(startTime);
        assertThat(status.getExpiryDate()).isEqualTo(expiryDate);
        assertThat(status.isFinished()).isTrue();
        assertThat(status.getFinishedStatus().getFinishTime()).isEqualTo(finishTime);
        assertThat(status.getFinishedStatus().getTimeSpentOnJobs()).isEqualTo(Duration.ofMillis(10000));
        assertThat(status.getFinishedStatus().getTotalJobRuns()).isEqualTo(5);
        assertThat(status.getFinishedStatus().getTotalRecordsRead()).isEqualTo(4800L);
        assertThat(status.getFinishedStatus().getTotalRecordsWritten()).isEqualTo(2400L);
        assertThat(status.getFinishedStatus().getRecordsReadPerSecond()).isEqualTo(480.0);
        assertThat(status.getFinishedStatus().getRecordsWrittenPerSecond()).isEqualTo(240.0);
    }

    @Test
    public void shouldStreamTaskStatusesWithMissingOptionalFields() {
        // Given
        Instant startTime = Instant.parse("2022-09-22T12:30:00.000Z");
        Instant finishTime = Instant.parse("2022-09-22T16:30:00.000Z");
        Instant expiryDate = Instant.parse("2022-09-29T12:30:00.000Z");
        Map<String, AttributeValue> startedRecord = Map.of(
                TASK_ID, new AttributeValue("test-task-5"),
                UPDATE_TYPE, new AttributeValue(STARTED),
                START_TIME, new AttributeValue().withN("" + startTime.toEpochMilli()),
                UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()),
                EXPIRY_DATE, new AttributeValue().withN("" + expiryDate.getEpochSecond()));
        Map<String, AttributeValue> finishedRecord = Map.of(
                TASK_ID, new AttributeValue("test-task-5"),
                UPDATE_TYPE, new AttributeValue(FINISHED),
                START_TIME, new AttributeValue().withN("" + startTime.toEpochMilli()),
                FINISH_TIME, new AttributeValue().withN("" + finishTime.toEpochMilli()),
                UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()),
                EXPIRY_DATE, new AttributeValue().withN("" + expiryDate.getEpochSecond()));

        // When
        List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                Stream.of(startedRecord, finishedRecord)).toList();

        // Then
        assertThat(statuses).hasSize(1);
        CompactionTaskStatus status = statuses.get(0);
        assertThat(status.getTaskId()).isEqualTo("test-task-5");
        assertThat(status.isFinished()).isTrue();
        assertThat(status.getFinishedStatus().getTimeSpentOnJobs()).isEqualTo(Duration.ofMillis(0));
        assertThat(status.getFinishedStatus().getTotalJobRuns()).isEqualTo(0);
        assertThat(status.getFinishedStatus().getTotalRecordsRead()).isEqualTo(0L);
        assertThat(status.getFinishedStatus().getTotalRecordsWritten()).isEqualTo(0L);
        assertThat(status.getFinishedStatus().getRecordsReadPerSecond()).isEqualTo(0.0);
        assertThat(status.getFinishedStatus().getRecordsWrittenPerSecond()).isEqualTo(0.0);
    }

    @Test
    public void shouldStreamTaskStatusesIgnoringUnrecognisedUpdateType() {
        // Given
        Instant startTime = Instant.parse("2022-09-22T12:30:00.000Z");
        Instant expiryDate = Instant.parse("2022-09-29T12:30:00.000Z");
        Map<String, AttributeValue> startedRecord = Map.of(
                TASK_ID, new AttributeValue("test-task-6"),
                UPDATE_TYPE, new AttributeValue(STARTED),
                START_TIME, new AttributeValue().withN("" + startTime.toEpochMilli()),
                UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()),
                EXPIRY_DATE, new AttributeValue().withN("" + expiryDate.getEpochSecond()));
        Map<String, AttributeValue> unrecognisedRecord = Map.of(
                TASK_ID, new AttributeValue("test-task-6"),
                UPDATE_TYPE, new AttributeValue("unknown"),
                UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()),
                EXPIRY_DATE, new AttributeValue().withN("" + expiryDate.getEpochSecond()));

        // When
        List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                Stream.of(startedRecord, unrecognisedRecord)).toList();

        // Then
        assertThat(statuses).hasSize(1);
        assertThat(statuses.get(0).getTaskId()).isEqualTo("test-task-6");
        assertThat(statuses.get(0).isFinished()).isFalse();
    }

    @Test
    public void shouldCreateRecordWithCustomTimeToLive() {
        // Given
        int customTtl = 86400;
        DynamoDBCompactionTaskStatusFormat format = new DynamoDBCompactionTaskStatusFormat(
                customTtl, () -> DEFAULT_TIME_NOW);
        Instant startTime = Instant.parse("2022-09-22T12:30:00.000Z");
        CompactionTaskStatus taskStatus = CompactionTaskStatus.builder()
                .taskId("test-task-7")
                .startTime(startTime)
                .build();

        // When
        Map<String, AttributeValue> record = format.createTaskStartedRecord(taskStatus);

        // Then
        assertThat(record).containsEntry(EXPIRY_DATE, new AttributeValue().withN("" + (DEFAULT_TIME_NOW.getEpochSecond() + customTtl)));
    }

    @Test
    public void shouldStreamMultipleTaskStatuses() {
        // Given
        Instant startTime1 = Instant.parse("2022-09-22T12:30:00.000Z");
        Instant startTime2 = Instant.parse("2022-09-22T13:30:00.000Z");
        Instant expiryDate = Instant.parse("2022-09-29T12:30:00.000Z");
        Map<String, AttributeValue> startedRecord1 = Map.of(
                TASK_ID, new AttributeValue("test-task-8"),
                UPDATE_TYPE, new AttributeValue(STARTED),
                START_TIME, new AttributeValue().withN("" + startTime1.toEpochMilli()),
                UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()),
                EXPIRY_DATE, new AttributeValue().withN("" + expiryDate.getEpochSecond()));
        Map<String, AttributeValue> startedRecord2 = Map.of(
                TASK_ID, new AttributeValue("test-task-9"),
                UPDATE_TYPE, new AttributeValue(STARTED),
                START_TIME, new AttributeValue().withN("" + startTime2.toEpochMilli()),
                UPDATE_TIME, new AttributeValue().withN("" + DEFAULT_TIME_NOW.toEpochMilli()),
                EXPIRY_DATE, new AttributeValue().withN("" + expiryDate.getEpochSecond()));

        // When
        List<CompactionTaskStatus> statuses = DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(
                Stream.of(startedRecord1, startedRecord2)).toList();

        // Then
        assertThat(statuses).hasSize(2);
        assertThat(statuses).extracting(CompactionTaskStatus::getTaskId)
                .containsExactlyInAnyOrder("test-task-8", "test-task-9");
    }
}
