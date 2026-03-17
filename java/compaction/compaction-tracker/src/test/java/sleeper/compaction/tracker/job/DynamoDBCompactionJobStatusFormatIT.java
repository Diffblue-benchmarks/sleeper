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

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.jupiter.api.Test;

import sleeper.core.statestore.AssignJobIdRequest;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.job.update.CompactionJobCommittedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobCreatedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFailedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobStartedEvent;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.dynamodb.tools.DynamoDBRecordBuilder;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.dynamodb.tools.DynamoDBAttributes.createNumberAttribute;
import static sleeper.dynamodb.tools.DynamoDBAttributes.createStringAttribute;

public class DynamoDBCompactionJobStatusFormatIT {

    @Test
    public void shouldStreamJobStatusFromDynamoDBItems() {
        // Given
        Instant updateTime = Instant.parse("2022-09-23T10:50:00.001Z");
        Map<String, AttributeValue> item = Map.of(
                "JobId", createStringAttribute("job-1"),
                "UpdateTime", createNumberAttribute(updateTime.toEpochMilli()),
                "UpdateType", createStringAttribute("created"),
                "PartitionId", createStringAttribute("partition-1"),
                "InputFilesCount", createNumberAttribute(3),
                "ExpiryDate", createNumberAttribute(updateTime.plusSeconds(604800).getEpochSecond()));

        // When
        List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat
                .streamJobStatuses(Stream.of(item))
                .toList();

        // Then
        assertThat(statuses).hasSize(1);
        assertThat(statuses.get(0).getJobId()).isEqualTo("job-1");
        assertThat(statuses.get(0).getPartitionId()).isEqualTo("partition-1");
        assertThat(statuses.get(0).getInputFilesCount()).isEqualTo(3);
    }

    @Test
    public void shouldStreamMultipleJobStatusesFromDynamoDBItems() {
        // Given
        Instant updateTime1 = Instant.parse("2022-09-23T10:50:00.001Z");
        Instant updateTime2 = Instant.parse("2022-09-23T10:51:00.001Z");
        Map<String, AttributeValue> item1 = Map.of(
                "JobId", createStringAttribute("job-1"),
                "UpdateTime", createNumberAttribute(updateTime1.toEpochMilli()),
                "UpdateType", createStringAttribute("created"),
                "PartitionId", createStringAttribute("partition-1"),
                "InputFilesCount", createNumberAttribute(2),
                "ExpiryDate", createNumberAttribute(updateTime1.plusSeconds(604800).getEpochSecond()));

        Map<String, AttributeValue> item2 = Map.of(
                "JobId", createStringAttribute("job-2"),
                "UpdateTime", createNumberAttribute(updateTime2.toEpochMilli()),
                "UpdateType", createStringAttribute("created"),
                "PartitionId", createStringAttribute("partition-2"),
                "InputFilesCount", createNumberAttribute(5),
                "ExpiryDate", createNumberAttribute(updateTime2.plusSeconds(604800).getEpochSecond()));

        // When
        List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat
                .streamJobStatuses(Stream.of(item1, item2))
                .toList();

        // Then
        assertThat(statuses).hasSize(2);
        assertThat(statuses)
                .extracting(CompactionJobStatus::getJobId)
                .containsExactlyInAnyOrder("job-1", "job-2");
        assertThat(statuses)
                .extracting(CompactionJobStatus::getPartitionId)
                .containsExactlyInAnyOrder("partition-1", "partition-2");
        assertThat(statuses)
                .extracting(CompactionJobStatus::getInputFilesCount)
                .containsExactlyInAnyOrder(2, 5);
    }

    @Test
    public void shouldStreamEmptyResultWhenNoItems() {
        // Given / When
        List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat
                .streamJobStatuses(Stream.empty())
                .toList();

        // Then
        assertThat(statuses).isEmpty();
    }

    @Test
    public void shouldGenerateJobUpdateIdWithCorrectFormat() {
        // Given
        String tableId = "test-table";
        String jobId = "test-job";
        Instant timeNow = Instant.parse("2024-03-17T10:00:00Z");
        Instant expiry = timeNow.plusSeconds(604800);

        // When
        DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat
                .jobUpdateBuilder(tableId, jobId, timeNow, expiry);
        Map<String, AttributeValue> record = builder.build();

        // Then
        String jobIdAndUpdate = record.get("JobIdAndUpdate").getS();
        assertThat(jobIdAndUpdate).startsWith("test-job|" + timeNow.toEpochMilli() + "|");

        // Extract the update ID part
        String[] parts = jobIdAndUpdate.split("\\|");
        assertThat(parts).hasSize(3);
        String updateId = parts[2];

        // Verify it's a valid hex string of 8 characters (4 bytes)
        assertThat(updateId).hasSize(8);
        assertThat(updateId).matches("[0-9a-f]{8}");
    }

    @Test
    public void shouldGenerateUniqueJobUpdateIds() {
        // Given
        String tableId = "test-table";
        String jobId = "test-job";
        Instant timeNow = Instant.parse("2024-03-17T10:00:00Z");
        Instant expiry = timeNow.plusSeconds(604800);
        Set<String> updateIds = new HashSet<>();

        // When
        for (int i = 0; i < 100; i++) {
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat
                    .jobUpdateBuilder(tableId, jobId, timeNow, expiry);
            Map<String, AttributeValue> record = builder.build();
            String jobIdAndUpdate = record.get("JobIdAndUpdate").getS();
            String[] parts = jobIdAndUpdate.split("\\|");
            String updateId = parts[2];
            updateIds.add(updateId);
        }

        // Then
        assertThat(updateIds).hasSizeGreaterThan(95);
    }

    @Test
    public void shouldIncludeGeneratedUpdateIdInJobUpdateBuilder() {
        // Given
        String tableId = "test-table";
        String jobId = "test-job";
        Instant timeNow = Instant.parse("2024-03-17T10:00:00Z");
        Instant expiry = timeNow.plusSeconds(604800);

        // When
        DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat
                .jobUpdateBuilder(tableId, jobId, timeNow, expiry);
        Map<String, AttributeValue> record = builder.build();

        // Then
        assertThat(record).containsKeys("TableId", "JobId", "JobIdAndUpdate", "UpdateTime", "ExpiryDate");
        assertThat(record.get("TableId").getS()).isEqualTo("test-table");
        assertThat(record.get("JobId").getS()).isEqualTo("test-job");
        assertThat(record.get("UpdateTime").getN()).isEqualTo(String.valueOf(timeNow.toEpochMilli()));
        assertThat(record.get("ExpiryDate").getN()).isEqualTo(String.valueOf(expiry.getEpochSecond()));
    }

    @Test
    public void shouldCreateJobFailedUpdateWithSingleFailureReason() {
        // Given
        Instant failureTime = Instant.parse("2024-03-17T11:30:00Z");
        CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                .jobId("job-1")
                .tableId("table-1")
                .taskId("task-1")
                .jobRunId("run-1")
                .failureTime(failureTime)
                .failureReasons(List.of("Connection timeout"))
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobFailedUpdate(event, builder);

        // Then
        assertThat(record).containsKeys("UpdateType", "TaskId", "JobRunId", "FinishTime", "FailureReasons");
        assertThat(record.get("UpdateType").getS()).isEqualTo("failed");
        assertThat(record.get("TaskId").getS()).isEqualTo("task-1");
        assertThat(record.get("JobRunId").getS()).isEqualTo("run-1");
        assertThat(record.get("FinishTime").getN()).isEqualTo(String.valueOf(failureTime.toEpochMilli()));
        assertThat(record.get("FailureReasons").getL())
                .hasSize(1)
                .extracting(AttributeValue::getS)
                .containsExactly("Connection timeout");
    }

    @Test
    public void shouldCreateJobFailedUpdateWithMultipleFailureReasons() {
        // Given
        Instant failureTime = Instant.parse("2024-03-17T12:45:00Z");
        CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                .jobId("job-2")
                .tableId("table-2")
                .taskId("task-2")
                .jobRunId("run-2")
                .failureTime(failureTime)
                .failureReasons(List.of("Network error", "Retry failed", "Max attempts exceeded"))
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobFailedUpdate(event, builder);

        // Then
        assertThat(record).containsKeys("UpdateType", "TaskId", "JobRunId", "FinishTime", "FailureReasons");
        assertThat(record.get("UpdateType").getS()).isEqualTo("failed");
        assertThat(record.get("TaskId").getS()).isEqualTo("task-2");
        assertThat(record.get("JobRunId").getS()).isEqualTo("run-2");
        assertThat(record.get("FinishTime").getN()).isEqualTo(String.valueOf(failureTime.toEpochMilli()));
        assertThat(record.get("FailureReasons").getL())
                .hasSize(3)
                .extracting(AttributeValue::getS)
                .containsExactly("Network error", "Retry failed", "Max attempts exceeded");
    }

    @Test
    public void shouldCreateJobFailedUpdateWithEmptyFailureReasons() {
        // Given
        Instant failureTime = Instant.parse("2024-03-17T13:00:00Z");
        CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                .jobId("job-3")
                .tableId("table-3")
                .taskId("task-3")
                .jobRunId("run-3")
                .failureTime(failureTime)
                .failureReasons(List.of())
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobFailedUpdate(event, builder);

        // Then
        assertThat(record).containsKeys("UpdateType", "TaskId", "JobRunId", "FinishTime", "FailureReasons");
        assertThat(record.get("UpdateType").getS()).isEqualTo("failed");
        assertThat(record.get("TaskId").getS()).isEqualTo("task-3");
        assertThat(record.get("JobRunId").getS()).isEqualTo("run-3");
        assertThat(record.get("FinishTime").getN()).isEqualTo(String.valueOf(failureTime.toEpochMilli()));
        assertThat(record.get("FailureReasons").getL()).isEmpty();
    }

    @Test
    public void shouldCreateJobCommittedUpdate() {
        // Given
        Instant commitTime = Instant.parse("2024-03-17T14:30:00Z");
        CompactionJobCommittedEvent event = CompactionJobCommittedEvent.builder()
                .jobId("job-1")
                .tableId("table-1")
                .taskId("task-1")
                .jobRunId("run-1")
                .commitTime(commitTime)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobCommittedUpdate(event, builder);

        // Then
        assertThat(record).containsKeys("UpdateType", "TaskId", "JobRunId", "CommitTime");
        assertThat(record.get("UpdateType").getS()).isEqualTo("committed");
        assertThat(record.get("TaskId").getS()).isEqualTo("task-1");
        assertThat(record.get("JobRunId").getS()).isEqualTo("run-1");
        assertThat(record.get("CommitTime").getN()).isEqualTo(String.valueOf(commitTime.toEpochMilli()));
    }

    @Test
    public void shouldCreateJobCommittedUpdateWithDifferentTaskAndRunIds() {
        // Given
        Instant commitTime = Instant.parse("2024-03-17T15:45:00Z");
        CompactionJobCommittedEvent event = CompactionJobCommittedEvent.builder()
                .jobId("job-2")
                .tableId("table-2")
                .taskId("task-abc")
                .jobRunId("run-xyz")
                .commitTime(commitTime)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobCommittedUpdate(event, builder);

        // Then
        assertThat(record.get("UpdateType").getS()).isEqualTo("committed");
        assertThat(record.get("TaskId").getS()).isEqualTo("task-abc");
        assertThat(record.get("JobRunId").getS()).isEqualTo("run-xyz");
        assertThat(record.get("CommitTime").getN()).isEqualTo(String.valueOf(commitTime.toEpochMilli()));
    }

    @Test
    public void shouldCreateJobCommittedUpdateWithCorrectCommitTimeConversion() {
        // Given
        Instant commitTime = Instant.parse("2024-03-17T16:00:00.123Z");
        CompactionJobCommittedEvent event = CompactionJobCommittedEvent.builder()
                .jobId("job-3")
                .tableId("table-3")
                .taskId("task-3")
                .jobRunId("run-3")
                .commitTime(commitTime)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobCommittedUpdate(event, builder);

        // Then
        long expectedEpochMilli = 1710691200123L;
        assertThat(commitTime.toEpochMilli()).isEqualTo(expectedEpochMilli);
        assertThat(record.get("CommitTime").getN()).isEqualTo(String.valueOf(expectedEpochMilli));
    }

    @Test
    public void shouldCreateJobFinishedUpdate() {
        // Given
        Instant startTime = Instant.parse("2024-03-17T10:00:00Z");
        Instant finishTime = Instant.parse("2024-03-17T10:30:00Z");
        RecordsProcessed recordsProcessed = new RecordsProcessed(1000L, 900L);
        JobRunSummary summary = new JobRunSummary(recordsProcessed, startTime, finishTime);
        CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                .jobId("job-1")
                .tableId("table-1")
                .taskId("task-1")
                .jobRunId("run-1")
                .summary(summary)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobFinishedUpdate(event, builder);

        // Then
        assertThat(record).containsKeys("UpdateType", "TaskId", "JobRunId", "FinishTime", "RecordsRead", "RecordsWritten");
        assertThat(record.get("UpdateType").getS()).isEqualTo("finished");
        assertThat(record.get("TaskId").getS()).isEqualTo("task-1");
        assertThat(record.get("JobRunId").getS()).isEqualTo("run-1");
        assertThat(record.get("FinishTime").getN()).isEqualTo(String.valueOf(finishTime.toEpochMilli()));
        assertThat(record.get("RecordsRead").getN()).isEqualTo("1000");
        assertThat(record.get("RecordsWritten").getN()).isEqualTo("900");
    }

    @Test
    public void shouldCreateJobFinishedUpdateWithDifferentTaskAndRunIds() {
        // Given
        Instant startTime = Instant.parse("2024-03-17T11:00:00Z");
        Instant finishTime = Instant.parse("2024-03-17T11:45:00Z");
        RecordsProcessed recordsProcessed = new RecordsProcessed(5000L, 4500L);
        JobRunSummary summary = new JobRunSummary(recordsProcessed, startTime, finishTime);
        CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                .jobId("job-2")
                .tableId("table-2")
                .taskId("task-abc")
                .jobRunId("run-xyz")
                .summary(summary)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobFinishedUpdate(event, builder);

        // Then
        assertThat(record.get("UpdateType").getS()).isEqualTo("finished");
        assertThat(record.get("TaskId").getS()).isEqualTo("task-abc");
        assertThat(record.get("JobRunId").getS()).isEqualTo("run-xyz");
        assertThat(record.get("FinishTime").getN()).isEqualTo(String.valueOf(finishTime.toEpochMilli()));
        assertThat(record.get("RecordsRead").getN()).isEqualTo("5000");
        assertThat(record.get("RecordsWritten").getN()).isEqualTo("4500");
    }

    @Test
    public void shouldCreateJobFinishedUpdateWithCorrectFinishTimeConversion() {
        // Given
        Instant startTime = Instant.parse("2024-03-17T12:00:00Z");
        Instant finishTime = Instant.parse("2024-03-17T12:30:00.456Z");
        RecordsProcessed recordsProcessed = new RecordsProcessed(2500L, 2400L);
        JobRunSummary summary = new JobRunSummary(recordsProcessed, startTime, finishTime);
        CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                .jobId("job-3")
                .tableId("table-3")
                .taskId("task-3")
                .jobRunId("run-3")
                .summary(summary)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobFinishedUpdate(event, builder);

        // Then
        long expectedEpochMilli = 1710678600456L;
        assertThat(finishTime.toEpochMilli()).isEqualTo(expectedEpochMilli);
        assertThat(record.get("FinishTime").getN()).isEqualTo(String.valueOf(expectedEpochMilli));
    }

    @Test
    public void shouldCreateJobFinishedUpdateWithZeroRecordsProcessed() {
        // Given
        Instant startTime = Instant.parse("2024-03-17T13:00:00Z");
        Instant finishTime = Instant.parse("2024-03-17T13:05:00Z");
        RecordsProcessed recordsProcessed = new RecordsProcessed(0L, 0L);
        JobRunSummary summary = new JobRunSummary(recordsProcessed, startTime, finishTime);
        CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                .jobId("job-4")
                .tableId("table-4")
                .taskId("task-4")
                .jobRunId("run-4")
                .summary(summary)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobFinishedUpdate(event, builder);

        // Then
        assertThat(record.get("UpdateType").getS()).isEqualTo("finished");
        assertThat(record.get("TaskId").getS()).isEqualTo("task-4");
        assertThat(record.get("JobRunId").getS()).isEqualTo("run-4");
        assertThat(record.get("FinishTime").getN()).isEqualTo(String.valueOf(finishTime.toEpochMilli()));
        assertThat(record.get("RecordsRead").getN()).isEqualTo("0");
        assertThat(record.get("RecordsWritten").getN()).isEqualTo("0");
    }

    @Test
    public void shouldCreateJobCreatedUpdate() {
        // Given
        CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
                .jobId("job-1")
                .tableId("table-1")
                .partitionId("partition-1")
                .inputFilesCount(5)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobCreated(event, builder);

        // Then
        assertThat(record).containsKeys("UpdateType", "PartitionId", "InputFilesCount");
        assertThat(record.get("UpdateType").getS()).isEqualTo("created");
        assertThat(record.get("PartitionId").getS()).isEqualTo("partition-1");
        assertThat(record.get("InputFilesCount").getN()).isEqualTo("5");
    }

    @Test
    public void shouldCreateJobCreatedUpdateWithDifferentPartitionId() {
        // Given
        CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
                .jobId("job-2")
                .tableId("table-2")
                .partitionId("partition-abc")
                .inputFilesCount(10)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobCreated(event, builder);

        // Then
        assertThat(record.get("UpdateType").getS()).isEqualTo("created");
        assertThat(record.get("PartitionId").getS()).isEqualTo("partition-abc");
        assertThat(record.get("InputFilesCount").getN()).isEqualTo("10");
    }

    @Test
    public void shouldCreateJobCreatedUpdateWithZeroInputFiles() {
        // Given
        CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
                .jobId("job-3")
                .tableId("table-3")
                .partitionId("partition-3")
                .inputFilesCount(0)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobCreated(event, builder);

        // Then
        assertThat(record.get("UpdateType").getS()).isEqualTo("created");
        assertThat(record.get("PartitionId").getS()).isEqualTo("partition-3");
        assertThat(record.get("InputFilesCount").getN()).isEqualTo("0");
    }

    @Test
    public void shouldCreateJobStartedUpdate() {
        // Given
        Instant startTime = Instant.parse("2024-03-17T09:00:00Z");
        CompactionJobStartedEvent event = CompactionJobStartedEvent.builder()
                .jobId("job-1")
                .tableId("table-1")
                .taskId("task-1")
                .jobRunId("run-1")
                .startTime(startTime)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobStartedUpdate(event, builder);

        // Then
        assertThat(record).containsKeys("UpdateType", "StartTime", "TaskId", "JobRunId");
        assertThat(record.get("UpdateType").getS()).isEqualTo("started");
        assertThat(record.get("StartTime").getN()).isEqualTo(String.valueOf(startTime.toEpochMilli()));
        assertThat(record.get("TaskId").getS()).isEqualTo("task-1");
        assertThat(record.get("JobRunId").getS()).isEqualTo("run-1");
    }

    @Test
    public void shouldCreateJobStartedUpdateWithDifferentTaskAndRunIds() {
        // Given
        Instant startTime = Instant.parse("2024-03-17T10:15:00Z");
        CompactionJobStartedEvent event = CompactionJobStartedEvent.builder()
                .jobId("job-2")
                .tableId("table-2")
                .taskId("task-abc")
                .jobRunId("run-xyz")
                .startTime(startTime)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobStartedUpdate(event, builder);

        // Then
        assertThat(record.get("UpdateType").getS()).isEqualTo("started");
        assertThat(record.get("TaskId").getS()).isEqualTo("task-abc");
        assertThat(record.get("JobRunId").getS()).isEqualTo("run-xyz");
        assertThat(record.get("StartTime").getN()).isEqualTo(String.valueOf(startTime.toEpochMilli()));
    }

    @Test
    public void shouldCreateJobStartedUpdateWithCorrectStartTimeConversion() {
        // Given
        Instant startTime = Instant.parse("2024-03-17T08:30:00.789Z");
        CompactionJobStartedEvent event = CompactionJobStartedEvent.builder()
                .jobId("job-3")
                .tableId("table-3")
                .taskId("task-3")
                .jobRunId("run-3")
                .startTime(startTime)
                .build();
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createJobStartedUpdate(event, builder);

        // Then
        long expectedEpochMilli = 1710664200789L;
        assertThat(startTime.toEpochMilli()).isEqualTo(expectedEpochMilli);
        assertThat(record.get("StartTime").getN()).isEqualTo(String.valueOf(expectedEpochMilli));
    }

    @Test
    public void shouldCreateFilesAssignedUpdate() {
        // Given
        AssignJobIdRequest request = AssignJobIdRequest.assignJobOnPartitionToFiles(
                "job-1",
                "partition-1",
                List.of("file1.parquet", "file2.parquet", "file3.parquet"));
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createFilesAssignedUpdate(request, builder);

        // Then
        assertThat(record).containsKeys("UpdateType", "PartitionId", "InputFilesCount");
        assertThat(record.get("UpdateType").getS()).isEqualTo("created");
        assertThat(record.get("PartitionId").getS()).isEqualTo("partition-1");
        assertThat(record.get("InputFilesCount").getN()).isEqualTo("3");
    }

    @Test
    public void shouldCreateFilesAssignedUpdateWithDifferentPartitionId() {
        // Given
        AssignJobIdRequest request = AssignJobIdRequest.assignJobOnPartitionToFiles(
                "job-2",
                "partition-xyz",
                List.of("file1.parquet", "file2.parquet"));
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createFilesAssignedUpdate(request, builder);

        // Then
        assertThat(record.get("UpdateType").getS()).isEqualTo("created");
        assertThat(record.get("PartitionId").getS()).isEqualTo("partition-xyz");
        assertThat(record.get("InputFilesCount").getN()).isEqualTo("2");
    }

    @Test
    public void shouldCreateFilesAssignedUpdateWithSingleFile() {
        // Given
        AssignJobIdRequest request = AssignJobIdRequest.assignJobOnPartitionToFiles(
                "job-3",
                "partition-3",
                List.of("file1.parquet"));
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createFilesAssignedUpdate(request, builder);

        // Then
        assertThat(record.get("UpdateType").getS()).isEqualTo("created");
        assertThat(record.get("PartitionId").getS()).isEqualTo("partition-3");
        assertThat(record.get("InputFilesCount").getN()).isEqualTo("1");
    }

    @Test
    public void shouldCreateFilesAssignedUpdateWithEmptyFileList() {
        // Given
        AssignJobIdRequest request = AssignJobIdRequest.assignJobOnPartitionToFiles(
                "job-4",
                "partition-4",
                List.of());
        DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

        // When
        Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat
                .createFilesAssignedUpdate(request, builder);

        // Then
        assertThat(record.get("UpdateType").getS()).isEqualTo("created");
        assertThat(record.get("PartitionId").getS()).isEqualTo("partition-4");
        assertThat(record.get("InputFilesCount").getN()).isEqualTo("0");
    }
}
