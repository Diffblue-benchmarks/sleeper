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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.statestore.AssignJobIdRequest;
import sleeper.core.tracker.compaction.job.query.CompactionJobCommittedStatus;
import sleeper.core.tracker.compaction.job.query.CompactionJobFinishedStatus;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sleeper.core.statestore.AssignJobIdRequest.assignJobOnPartitionToFiles;
import static sleeper.dynamodb.tools.DynamoDBAttributes.getInstantAttribute;
import static sleeper.dynamodb.tools.DynamoDBAttributes.getIntAttribute;
import static sleeper.dynamodb.tools.DynamoDBAttributes.getLongAttribute;
import static sleeper.dynamodb.tools.DynamoDBAttributes.getStringAttribute;
import static sleeper.dynamodb.tools.DynamoDBAttributes.getStringListAttribute;

class DynamoDBCompactionJobStatusFormatClaudeTest {

    private static final String TABLE_ID = "TableId";
    private static final String JOB_ID = "JobId";
    private static final String JOB_ID_AND_UPDATE = "JobIdAndUpdate";
    private static final String UPDATE_TIME = "UpdateTime";
    private static final String EXPIRY_DATE = "ExpiryDate";
    private static final String UPDATE_TYPE = "UpdateType";
    private static final String PARTITION_ID = "PartitionId";
    private static final String INPUT_FILES_COUNT = "InputFilesCount";
    private static final String START_TIME = "StartTime";
    private static final String FINISH_TIME = "FinishTime";
    private static final String COMMIT_TIME = "CommitTime";
    private static final String RECORDS_READ = "RecordsRead";
    private static final String RECORDS_WRITTEN = "RecordsWritten";
    private static final String FAILURE_REASONS = "FailureReasons";
    private static final String JOB_RUN_ID = "JobRunId";
    private static final String TASK_ID = "TaskId";

    @Nested
    @DisplayName("createFilesAssignedUpdate")
    class CreateFilesAssignedUpdate {

        @Test
        void shouldCreateRecordWithCorrectPartitionId() {
            // Given
            AssignJobIdRequest request = assignJobOnPartitionToFiles(
                    "test-job-id", "test-partition", List.of("file1.parquet", "file2.parquet"));
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(request, builder);

            // Then
            assertThat(getStringAttribute(record, PARTITION_ID)).isEqualTo("test-partition");
        }

        @Test
        void shouldCreateRecordWithInputFilesCountBasedOnFilenamesSize() {
            // Given
            AssignJobIdRequest request = assignJobOnPartitionToFiles(
                    "test-job-id", "test-partition", List.of("file1.parquet", "file2.parquet", "file3.parquet"));
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(request, builder);

            // Then
            assertThat(getIntAttribute(record, INPUT_FILES_COUNT, -1)).isEqualTo(3);
        }

        @Test
        void shouldCreateRecordWithCreatedUpdateType() {
            // Given
            AssignJobIdRequest request = assignJobOnPartitionToFiles(
                    "test-job-id", "root", List.of("file.parquet"));
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(request, builder);

            // Then
            assertThat(getStringAttribute(record, UPDATE_TYPE)).isEqualTo("created");
        }

        @Test
        void shouldPreserveExistingBuilderAttributesWhenCreatingRecord() {
            // Given
            AssignJobIdRequest request = assignJobOnPartitionToFiles(
                    "test-job-id", "test-partition", List.of("file.parquet"));
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder()
                    .string(TABLE_ID, "my-table")
                    .string(JOB_ID, "test-job-id");

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(request, builder);

            // Then
            assertThat(getStringAttribute(record, TABLE_ID)).isEqualTo("my-table");
            assertThat(getStringAttribute(record, JOB_ID)).isEqualTo("test-job-id");
        }
    }

    @Nested
    @DisplayName("createJobCreated")
    class CreateJobCreated {

        @Test
        void shouldCreateRecordWithCorrectPartitionId() {
            // Given
            CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .partitionId("partition-123")
                    .inputFilesCount(5)
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobCreated(event, builder);

            // Then
            assertThat(getStringAttribute(record, PARTITION_ID)).isEqualTo("partition-123");
        }

        @Test
        void shouldCreateRecordWithCorrectInputFilesCount() {
            // Given
            CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .partitionId("root")
                    .inputFilesCount(7)
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobCreated(event, builder);

            // Then
            assertThat(getIntAttribute(record, INPUT_FILES_COUNT, -1)).isEqualTo(7);
        }

        @Test
        void shouldCreateRecordWithCreatedUpdateType() {
            // Given
            CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .partitionId("root")
                    .inputFilesCount(1)
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobCreated(event, builder);

            // Then
            assertThat(getStringAttribute(record, UPDATE_TYPE)).isEqualTo("created");
        }
    }

    @Nested
    @DisplayName("createJobStartedUpdate")
    class CreateJobStartedUpdate {

        @Test
        void shouldCreateRecordWithStartTime() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:30:00Z");
            CompactionJobStartedEvent event = CompactionJobStartedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(startTime)
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(event, builder);

            // Then
            assertThat(getInstantAttribute(record, START_TIME)).isEqualTo(startTime);
        }

        @Test
        void shouldCreateRecordWithTaskId() {
            // Given
            CompactionJobStartedEvent event = CompactionJobStartedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("my-task-id")
                    .jobRunId("run-1")
                    .startTime(Instant.now())
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(event, builder);

            // Then
            assertThat(getStringAttribute(record, TASK_ID)).isEqualTo("my-task-id");
        }

        @Test
        void shouldCreateRecordWithJobRunId() {
            // Given
            CompactionJobStartedEvent event = CompactionJobStartedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("unique-run-id")
                    .startTime(Instant.now())
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(event, builder);

            // Then
            assertThat(getStringAttribute(record, JOB_RUN_ID)).isEqualTo("unique-run-id");
        }

        @Test
        void shouldCreateRecordWithStartedUpdateType() {
            // Given
            CompactionJobStartedEvent event = CompactionJobStartedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .startTime(Instant.now())
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(event, builder);

            // Then
            assertThat(getStringAttribute(record, UPDATE_TYPE)).isEqualTo("started");
        }
    }

    @Nested
    @DisplayName("createJobFinishedUpdate")
    class CreateJobFinishedUpdate {

        @Test
        void shouldCreateRecordWithFinishTime() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:30:00Z");
            CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(100, 100), startTime, finishTime))
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(event, builder);

            // Then
            assertThat(getInstantAttribute(record, FINISH_TIME)).isEqualTo(finishTime);
        }

        @Test
        void shouldCreateRecordWithRecordsRead() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:30:00Z");
            CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(5000, 4800), startTime, finishTime))
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(event, builder);

            // Then
            assertThat(getLongAttribute(record, RECORDS_READ, -1)).isEqualTo(5000);
        }

        @Test
        void shouldCreateRecordWithRecordsWritten() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:30:00Z");
            CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(5000, 4800), startTime, finishTime))
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(event, builder);

            // Then
            assertThat(getLongAttribute(record, RECORDS_WRITTEN, -1)).isEqualTo(4800);
        }

        @Test
        void shouldCreateRecordWithTaskIdAndJobRunId() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:30:00Z");
            CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-abc")
                    .jobRunId("run-xyz")
                    .summary(new JobRunSummary(new RecordsProcessed(100, 100), startTime, finishTime))
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(event, builder);

            // Then
            assertThat(getStringAttribute(record, TASK_ID)).isEqualTo("task-abc");
            assertThat(getStringAttribute(record, JOB_RUN_ID)).isEqualTo("run-xyz");
        }

        @Test
        void shouldCreateRecordWithFinishedUpdateType() {
            // Given
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:30:00Z");
            CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(100, 100), startTime, finishTime))
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(event, builder);

            // Then
            assertThat(getStringAttribute(record, UPDATE_TYPE)).isEqualTo("finished");
        }
    }

    @Nested
    @DisplayName("createJobCommittedUpdate")
    class CreateJobCommittedUpdate {

        @Test
        void shouldCreateRecordWithCommitTime() {
            // Given
            Instant commitTime = Instant.parse("2024-01-15T10:35:00Z");
            CompactionJobCommittedEvent event = CompactionJobCommittedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .commitTime(commitTime)
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(event, builder);

            // Then
            assertThat(getInstantAttribute(record, COMMIT_TIME)).isEqualTo(commitTime);
        }

        @Test
        void shouldCreateRecordWithTaskIdAndJobRunId() {
            // Given
            CompactionJobCommittedEvent event = CompactionJobCommittedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("commit-task")
                    .jobRunId("commit-run")
                    .commitTime(Instant.now())
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(event, builder);

            // Then
            assertThat(getStringAttribute(record, TASK_ID)).isEqualTo("commit-task");
            assertThat(getStringAttribute(record, JOB_RUN_ID)).isEqualTo("commit-run");
        }

        @Test
        void shouldCreateRecordWithCommittedUpdateType() {
            // Given
            CompactionJobCommittedEvent event = CompactionJobCommittedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .commitTime(Instant.now())
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(event, builder);

            // Then
            assertThat(getStringAttribute(record, UPDATE_TYPE)).isEqualTo("committed");
        }
    }

    @Nested
    @DisplayName("createJobFailedUpdate")
    class CreateJobFailedUpdate {

        @Test
        void shouldCreateRecordWithFailureTime() {
            // Given
            Instant failureTime = Instant.parse("2024-01-15T10:45:00Z");
            CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureTime(failureTime)
                    .failureReasons(List.of("Out of memory"))
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(event, builder);

            // Then
            assertThat(getInstantAttribute(record, FINISH_TIME)).isEqualTo(failureTime);
        }

        @Test
        void shouldCreateRecordWithSingleFailureReason() {
            // Given
            CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureTime(Instant.now())
                    .failureReasons(List.of("File not found"))
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(event, builder);

            // Then
            assertThat(getStringListAttribute(record, FAILURE_REASONS)).containsExactly("File not found");
        }

        @Test
        void shouldCreateRecordWithMultipleFailureReasons() {
            // Given
            CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureTime(Instant.now())
                    .failureReasons(List.of("Connection timeout", "Root cause: Network error"))
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(event, builder);

            // Then
            assertThat(getStringListAttribute(record, FAILURE_REASONS))
                    .containsExactly("Connection timeout", "Root cause: Network error");
        }

        @Test
        void shouldCreateRecordWithTaskIdAndJobRunId() {
            // Given
            CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("failed-task")
                    .jobRunId("failed-run")
                    .failureTime(Instant.now())
                    .failureReasons(List.of("Error"))
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(event, builder);

            // Then
            assertThat(getStringAttribute(record, TASK_ID)).isEqualTo("failed-task");
            assertThat(getStringAttribute(record, JOB_RUN_ID)).isEqualTo("failed-run");
        }

        @Test
        void shouldCreateRecordWithFailedUpdateType() {
            // Given
            CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureTime(Instant.now())
                    .failureReasons(List.of("Error"))
                    .build();
            DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

            // When
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(event, builder);

            // Then
            assertThat(getStringAttribute(record, UPDATE_TYPE)).isEqualTo("failed");
        }
    }

    @Nested
    @DisplayName("jobUpdateBuilder")
    class JobUpdateBuilder {

        @Test
        void shouldCreateBuilderWithTableId() {
            // Given/When
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    "my-table", "job-1", Instant.now(), Instant.now().plusSeconds(3600));
            Map<String, AttributeValue> record = builder.build();

            // Then
            assertThat(getStringAttribute(record, TABLE_ID)).isEqualTo("my-table");
        }

        @Test
        void shouldCreateBuilderWithJobId() {
            // Given/When
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    "table-1", "unique-job-id", Instant.now(), Instant.now().plusSeconds(3600));
            Map<String, AttributeValue> record = builder.build();

            // Then
            assertThat(getStringAttribute(record, JOB_ID)).isEqualTo("unique-job-id");
        }

        @Test
        void shouldCreateBuilderWithUpdateTime() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T12:00:00Z");

            // When
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    "table-1", "job-1", updateTime, Instant.now().plusSeconds(3600));
            Map<String, AttributeValue> record = builder.build();

            // Then
            assertThat(getInstantAttribute(record, UPDATE_TIME)).isEqualTo(updateTime);
        }

        @Test
        void shouldCreateBuilderWithExpiryDateInEpochSeconds() {
            // Given
            Instant expiry = Instant.parse("2024-02-15T12:00:00Z");

            // When
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    "table-1", "job-1", Instant.now(), expiry);
            Map<String, AttributeValue> record = builder.build();

            // Then
            assertThat(getInstantAttribute(record, EXPIRY_DATE, Instant::ofEpochSecond)).isEqualTo(expiry);
        }

        @Test
        void shouldCreateBuilderWithJobIdAndUpdateCompositeKey() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T12:00:00Z");

            // When
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    "table-1", "my-job", updateTime, Instant.now().plusSeconds(3600));
            Map<String, AttributeValue> record = builder.build();

            // Then
            String jobIdAndUpdate = getStringAttribute(record, JOB_ID_AND_UPDATE);
            assertThat(jobIdAndUpdate).startsWith("my-job|" + updateTime.toEpochMilli() + "|");
            // The last part is a random 8-character hex string
            String[] parts = jobIdAndUpdate.split("\\|");
            assertThat(parts).hasSize(3);
            assertThat(parts[2]).matches("[0-9a-f]{8}");
        }

        @Test
        void shouldCreateDifferentRandomSuffixesForDifferentCalls() {
            // Given
            Instant updateTime = Instant.now();
            Instant expiry = Instant.now().plusSeconds(3600);

            // When
            Map<String, AttributeValue> record1 = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    "table-1", "job-1", updateTime, expiry).build();
            Map<String, AttributeValue> record2 = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    "table-1", "job-1", updateTime, expiry).build();

            // Then
            String key1 = getStringAttribute(record1, JOB_ID_AND_UPDATE);
            String key2 = getStringAttribute(record2, JOB_ID_AND_UPDATE);
            assertThat(key1).isNotEqualTo(key2);
        }
    }

    @Nested
    @DisplayName("streamJobStatuses")
    class StreamJobStatuses {

        @Test
        void shouldDeserializeCreatedStatusFromDynamoDBItem() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "test-job")
                    .string(UPDATE_TYPE, "created")
                    .number(UPDATE_TIME, updateTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .string(PARTITION_ID, "root")
                    .number(INPUT_FILES_COUNT, 3)
                    .build();

            // When
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(item)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobStatus status = statuses.get(0);
            assertThat(status.getJobId()).isEqualTo("test-job");
            assertThat(status.getPartitionId()).isEqualTo("root");
            assertThat(status.getInputFilesCount()).isEqualTo(3);
            assertThat(status.getCreateUpdateTime()).isEqualTo(updateTime);
            assertThat(status.getExpiryDate()).isEqualTo(expiry);
        }

        @Test
        void shouldDeserializeStartedStatusFromDynamoDBItem() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:05:00Z");
            Instant startTime = Instant.parse("2024-01-15T10:05:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "test-job")
                    .string(UPDATE_TYPE, "started")
                    .number(UPDATE_TIME, updateTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .number(START_TIME, startTime.toEpochMilli())
                    .string(TASK_ID, "task-1")
                    .string(JOB_RUN_ID, "run-1")
                    .build();

            // When
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(item)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobStatus status = statuses.get(0);
            assertThat(status.isStarted()).isTrue();
            assertThat(status.getRunsLatestFirst()).hasSize(1);
            assertThat(status.getRunsLatestFirst().get(0).getStartTime()).isEqualTo(startTime);
        }

        @Test
        void shouldDeserializeFinishedStatusFromDynamoDBItem() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:30:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:30:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "test-job")
                    .string(UPDATE_TYPE, "finished")
                    .number(UPDATE_TIME, updateTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .number(FINISH_TIME, finishTime.toEpochMilli())
                    .number(RECORDS_READ, 1000)
                    .number(RECORDS_WRITTEN, 950)
                    .string(TASK_ID, "task-1")
                    .string(JOB_RUN_ID, "run-1")
                    .build();

            // When
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(item)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobStatus status = statuses.get(0);
            assertThat(status.getRunsLatestFirst()).hasSize(1);
            CompactionJobFinishedStatus finishedStatus = status.getRunsLatestFirst().get(0).getSuccessfulFinishedStatus().orElseThrow();
            assertThat(finishedStatus.getFinishTime()).isEqualTo(finishTime);
            assertThat(finishedStatus.getRecordsProcessed().getRecordsRead()).isEqualTo(1000);
            assertThat(finishedStatus.getRecordsProcessed().getRecordsWritten()).isEqualTo(950);
        }

        @Test
        void shouldDeserializeCommittedStatusFromDynamoDBItem() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:35:00Z");
            Instant commitTime = Instant.parse("2024-01-15T10:35:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "test-job")
                    .string(UPDATE_TYPE, "committed")
                    .number(UPDATE_TIME, updateTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .number(COMMIT_TIME, commitTime.toEpochMilli())
                    .string(TASK_ID, "task-1")
                    .string(JOB_RUN_ID, "run-1")
                    .build();

            // When
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(item)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobStatus status = statuses.get(0);
            assertThat(status.getRunsLatestFirst()).hasSize(1);
            CompactionJobCommittedStatus committedStatus = (CompactionJobCommittedStatus) status.getRunsLatestFirst().get(0).getCommittedStatus().orElseThrow();
            assertThat(committedStatus.getCommitTime()).isEqualTo(commitTime);
            assertThat(committedStatus.getUpdateTime()).isEqualTo(updateTime);
        }

        @Test
        void shouldDeserializeFailedStatusFromDynamoDBItem() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:20:00Z");
            Instant failureTime = Instant.parse("2024-01-15T10:20:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "test-job")
                    .string(UPDATE_TYPE, "failed")
                    .number(UPDATE_TIME, updateTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .number(FINISH_TIME, failureTime.toEpochMilli())
                    .list(FAILURE_REASONS, List.of(
                            new AttributeValue("Error 1"),
                            new AttributeValue("Error 2")))
                    .string(TASK_ID, "task-1")
                    .string(JOB_RUN_ID, "run-1")
                    .build();

            // When
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(item)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobStatus status = statuses.get(0);
            assertThat(status.getRunsLatestFirst()).hasSize(1);
            assertThat(status.isAnyRunFailed()).isTrue();
            assertThat(status.getRunsLatestFirst().get(0).getFinishTime()).isEqualTo(failureTime);
        }

        @Test
        void shouldGroupMultipleUpdatesForSameJob() {
            // Given
            Instant createdTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant startedTime = Instant.parse("2024-01-15T10:05:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> createdItem = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "test-job")
                    .string(UPDATE_TYPE, "created")
                    .number(UPDATE_TIME, createdTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .string(PARTITION_ID, "root")
                    .number(INPUT_FILES_COUNT, 2)
                    .build();
            Map<String, AttributeValue> startedItem = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "test-job")
                    .string(UPDATE_TYPE, "started")
                    .number(UPDATE_TIME, startedTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .number(START_TIME, startedTime.toEpochMilli())
                    .string(TASK_ID, "task-1")
                    .string(JOB_RUN_ID, "run-1")
                    .build();

            // When
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(createdItem, startedItem)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobStatus status = statuses.get(0);
            assertThat(status.getJobId()).isEqualTo("test-job");
            assertThat(status.getCreateUpdateTime()).isEqualTo(createdTime);
            assertThat(status.isStarted()).isTrue();
        }

        @Test
        void shouldHandleMultipleDifferentJobs() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> job1Item = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "job-1")
                    .string(UPDATE_TYPE, "created")
                    .number(UPDATE_TIME, updateTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .string(PARTITION_ID, "partition-1")
                    .number(INPUT_FILES_COUNT, 1)
                    .build();
            Map<String, AttributeValue> job2Item = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "job-2")
                    .string(UPDATE_TYPE, "created")
                    .number(UPDATE_TIME, updateTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .string(PARTITION_ID, "partition-2")
                    .number(INPUT_FILES_COUNT, 2)
                    .build();

            // When
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(job1Item, job2Item)).toList();

            // Then
            assertThat(statuses).hasSize(2);
            assertThat(statuses).extracting(CompactionJobStatus::getJobId)
                    .containsExactlyInAnyOrder("job-1", "job-2");
        }

        @Test
        void shouldThrowExceptionForUnrecognisedUpdateType() {
            // Given
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "test-job")
                    .string(UPDATE_TYPE, "unknown")
                    .number(UPDATE_TIME, Instant.now().toEpochMilli())
                    .number(EXPIRY_DATE, Instant.now().getEpochSecond())
                    .build();

            // When/Then
            assertThatThrownBy(() -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(item)).toList())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("unrecognised update type");
        }

        @Test
        void shouldHandleEmptyStream() {
            // When
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.empty()).toList();

            // Then
            assertThat(statuses).isEmpty();
        }

        @Test
        void shouldHandleDefaultValuesForMissingRecordsProcessedAttributes() {
            // Given - finished record without records read/written
            Instant updateTime = Instant.parse("2024-01-15T10:30:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:30:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "test-job")
                    .string(UPDATE_TYPE, "finished")
                    .number(UPDATE_TIME, updateTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .number(FINISH_TIME, finishTime.toEpochMilli())
                    .string(TASK_ID, "task-1")
                    .string(JOB_RUN_ID, "run-1")
                    .build();

            // When
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(item)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobFinishedStatus finishedStatus = statuses.get(0)
                    .getRunsLatestFirst().get(0).getSuccessfulFinishedStatus().orElseThrow();
            assertThat(finishedStatus.getRecordsProcessed().getRecordsRead()).isZero();
            assertThat(finishedStatus.getRecordsProcessed().getRecordsWritten()).isZero();
        }

        @Test
        void shouldHandleDefaultValueForMissingInputFilesCount() {
            // Given - created record without input files count
            Instant updateTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            Map<String, AttributeValue> item = new DynamoDBRecordBuilder()
                    .string(JOB_ID, "test-job")
                    .string(UPDATE_TYPE, "created")
                    .number(UPDATE_TIME, updateTime.toEpochMilli())
                    .number(EXPIRY_DATE, expiry.getEpochSecond())
                    .string(PARTITION_ID, "root")
                    .build();

            // When
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(item)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            assertThat(statuses.get(0).getInputFilesCount()).isZero();
        }
    }

    @Nested
    @DisplayName("Round-trip serialization and deserialization")
    class RoundTrip {

        @Test
        void shouldRoundTripJobCreatedEvent() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .partitionId("root")
                    .inputFilesCount(5)
                    .build();

            // When - create and deserialize
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    event.getTableId(), event.getJobId(), updateTime, expiry);
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobCreated(event, builder);
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(record)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobStatus status = statuses.get(0);
            assertThat(status.getJobId()).isEqualTo("test-job");
            assertThat(status.getPartitionId()).isEqualTo("root");
            assertThat(status.getInputFilesCount()).isEqualTo(5);
            assertThat(status.getCreateUpdateTime()).isEqualTo(updateTime);
        }

        @Test
        void shouldRoundTripFilesAssignedRequest() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            AssignJobIdRequest request = assignJobOnPartitionToFiles(
                    "test-job", "partition-abc", List.of("file1.parquet", "file2.parquet"));

            // When - create and deserialize
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    "test-table", request.getJobId(), updateTime, expiry);
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(request, builder);
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(record)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobStatus status = statuses.get(0);
            assertThat(status.getJobId()).isEqualTo("test-job");
            assertThat(status.getPartitionId()).isEqualTo("partition-abc");
            assertThat(status.getInputFilesCount()).isEqualTo(2);
        }

        @Test
        void shouldRoundTripJobStartedEvent() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:05:00Z");
            Instant startTime = Instant.parse("2024-01-15T10:05:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            CompactionJobStartedEvent event = CompactionJobStartedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-123")
                    .jobRunId("run-456")
                    .startTime(startTime)
                    .build();

            // When
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    event.getTableId(), event.getJobId(), updateTime, expiry);
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(event, builder);
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(record)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobStatus status = statuses.get(0);
            assertThat(status.isStarted()).isTrue();
            assertThat(status.getRunsLatestFirst().get(0).getStartTime()).isEqualTo(startTime);
        }

        @Test
        void shouldRoundTripJobFinishedEvent() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:30:00Z");
            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:30:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            CompactionJobFinishedEvent event = CompactionJobFinishedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .summary(new JobRunSummary(new RecordsProcessed(10000, 9500), startTime, finishTime))
                    .build();

            // When
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    event.getTableId(), event.getJobId(), updateTime, expiry);
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(event, builder);
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(record)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobFinishedStatus finishedStatus = statuses.get(0)
                    .getRunsLatestFirst().get(0).getSuccessfulFinishedStatus().orElseThrow();
            assertThat(finishedStatus.getFinishTime()).isEqualTo(finishTime);
            assertThat(finishedStatus.getRecordsProcessed().getRecordsRead()).isEqualTo(10000);
            assertThat(finishedStatus.getRecordsProcessed().getRecordsWritten()).isEqualTo(9500);
        }

        @Test
        void shouldRoundTripJobCommittedEvent() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:35:00Z");
            Instant commitTime = Instant.parse("2024-01-15T10:35:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            CompactionJobCommittedEvent event = CompactionJobCommittedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .commitTime(commitTime)
                    .build();

            // When
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    event.getTableId(), event.getJobId(), updateTime, expiry);
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(event, builder);
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(record)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobCommittedStatus committedStatus = (CompactionJobCommittedStatus) statuses.get(0)
                    .getRunsLatestFirst().get(0).getCommittedStatus().orElseThrow();
            assertThat(committedStatus.getCommitTime()).isEqualTo(commitTime);
        }

        @Test
        void shouldRoundTripJobFailedEvent() {
            // Given
            Instant updateTime = Instant.parse("2024-01-15T10:20:00Z");
            Instant failureTime = Instant.parse("2024-01-15T10:20:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            List<String> failureReasons = List.of("Connection timeout", "Caused by: Network error");
            CompactionJobFailedEvent event = CompactionJobFailedEvent.builder()
                    .jobId("test-job")
                    .tableId("test-table")
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureTime(failureTime)
                    .failureReasons(failureReasons)
                    .build();

            // When
            DynamoDBRecordBuilder builder = DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
                    event.getTableId(), event.getJobId(), updateTime, expiry);
            Map<String, AttributeValue> record = DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(event, builder);
            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(record)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            assertThat(statuses.get(0).isAnyRunFailed()).isTrue();
            assertThat(statuses.get(0).getRunsLatestFirst().get(0).getFinishTime()).isEqualTo(failureTime);
        }

        @Test
        void shouldRoundTripCompleteJobLifecycle() {
            // Given - a job that goes through create -> start -> finish -> commit
            Instant createTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant startTime = Instant.parse("2024-01-15T10:01:00Z");
            Instant finishTime = Instant.parse("2024-01-15T10:30:00Z");
            Instant commitTime = Instant.parse("2024-01-15T10:31:00Z");
            Instant expiry = Instant.parse("2024-02-15T10:00:00Z");
            String jobId = "lifecycle-test-job";
            String tableId = "test-table";
            String taskId = "task-1";
            String runId = "run-1";

            CompactionJobCreatedEvent createdEvent = CompactionJobCreatedEvent.builder()
                    .jobId(jobId).tableId(tableId).partitionId("root").inputFilesCount(3).build();
            CompactionJobStartedEvent startedEvent = CompactionJobStartedEvent.builder()
                    .jobId(jobId).tableId(tableId).taskId(taskId).jobRunId(runId).startTime(startTime).build();
            CompactionJobFinishedEvent finishedEvent = CompactionJobFinishedEvent.builder()
                    .jobId(jobId).tableId(tableId).taskId(taskId).jobRunId(runId)
                    .summary(new JobRunSummary(new RecordsProcessed(1000, 900), startTime, finishTime)).build();
            CompactionJobCommittedEvent committedEvent = CompactionJobCommittedEvent.builder()
                    .jobId(jobId).tableId(tableId).taskId(taskId).jobRunId(runId).commitTime(commitTime).build();

            // When
            Map<String, AttributeValue> createdRecord = DynamoDBCompactionJobStatusFormat.createJobCreated(
                    createdEvent, DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(tableId, jobId, createTime, expiry));
            Map<String, AttributeValue> startedRecord = DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(
                    startedEvent, DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(tableId, jobId, startTime, expiry));
            Map<String, AttributeValue> finishedRecord = DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(
                    finishedEvent, DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(tableId, jobId, finishTime, expiry));
            Map<String, AttributeValue> committedRecord = DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(
                    committedEvent, DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(tableId, jobId, commitTime, expiry));

            List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat.streamJobStatuses(
                    Stream.of(createdRecord, startedRecord, finishedRecord, committedRecord)).toList();

            // Then
            assertThat(statuses).hasSize(1);
            CompactionJobStatus status = statuses.get(0);
            assertThat(status.getJobId()).isEqualTo(jobId);
            assertThat(status.getPartitionId()).isEqualTo("root");
            assertThat(status.getInputFilesCount()).isEqualTo(3);
            assertThat(status.getCreateUpdateTime()).isEqualTo(createTime);
            assertThat(status.isStarted()).isTrue();
            assertThat(status.isAnyRunSuccessful()).isTrue();
        }
    }
}
