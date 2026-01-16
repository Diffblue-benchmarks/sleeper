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
package sleeper.compaction.core.job;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.statestore.AssignJobIdRequest;
import sleeper.core.statestore.CheckFileAssignmentsRequest;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.ReplaceFileReferencesRequest;
import sleeper.core.tracker.compaction.job.update.CompactionJobCommittedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobCreatedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFailedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobStartedEvent;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.RecordsProcessed;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CompactionJobClaudeTest {

    private static final String TABLE_ID = "test-table-id";
    private static final String JOB_ID = "test-job-id";
    private static final String PARTITION_ID = "test-partition";
    private static final String OUTPUT_FILE = "output.parquet";
    private static final List<String> INPUT_FILES = List.of("input1.parquet", "input2.parquet");

    private CompactionJob.Builder validBuilder() {
        return CompactionJob.builder()
                .tableId(TABLE_ID)
                .jobId(JOB_ID)
                .partitionId(PARTITION_ID)
                .outputFile(OUTPUT_FILE)
                .inputFiles(INPUT_FILES);
    }

    @Nested
    @DisplayName("Builder and getters")
    class BuilderAndGetters {

        @Test
        void shouldBuildCompactionJobWithRequiredFields() {
            // Given/When
            CompactionJob job = validBuilder().build();

            // Then
            assertThat(job.getTableId()).isEqualTo(TABLE_ID);
            assertThat(job.getId()).isEqualTo(JOB_ID);
            assertThat(job.getPartitionId()).isEqualTo(PARTITION_ID);
            assertThat(job.getOutputFile()).isEqualTo(OUTPUT_FILE);
            assertThat(job.getInputFiles()).isEqualTo(INPUT_FILES);
        }

        @Test
        void shouldBuildCompactionJobWithOptionalIteratorFields() {
            // Given/When
            CompactionJob job = validBuilder()
                    .iteratorClassName("com.example.Iterator")
                    .iteratorConfig("config")
                    .build();

            // Then
            assertThat(job.getIteratorClassName()).isEqualTo("com.example.Iterator");
            assertThat(job.getIteratorConfig()).isEqualTo("config");
        }

        @Test
        void shouldReturnNullForOptionalFieldsWhenNotSet() {
            // Given/When
            CompactionJob job = validBuilder().build();

            // Then
            assertThat(job.getIteratorClassName()).isNull();
            assertThat(job.getIteratorConfig()).isNull();
        }

        @Test
        void shouldReturnBuilderFromStaticMethod() {
            // When
            CompactionJob.Builder builder = CompactionJob.builder();

            // Then
            assertThat(builder).isNotNull();
        }
    }

    @Nested
    @DisplayName("Validation")
    class Validation {

        @Test
        void shouldThrowExceptionWhenTableIdIsNull() {
            // Given
            CompactionJob.Builder builder = CompactionJob.builder()
                    .jobId(JOB_ID)
                    .partitionId(PARTITION_ID)
                    .outputFile(OUTPUT_FILE)
                    .inputFiles(INPUT_FILES);

            // When/Then
            assertThatThrownBy(builder::build)
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("tableId");
        }

        @Test
        void shouldThrowExceptionWhenJobIdIsNull() {
            // Given
            CompactionJob.Builder builder = CompactionJob.builder()
                    .tableId(TABLE_ID)
                    .partitionId(PARTITION_ID)
                    .outputFile(OUTPUT_FILE)
                    .inputFiles(INPUT_FILES);

            // When/Then
            assertThatThrownBy(builder::build)
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("jobId");
        }

        @Test
        void shouldThrowExceptionWhenInputFilesIsNull() {
            // Given
            CompactionJob.Builder builder = CompactionJob.builder()
                    .tableId(TABLE_ID)
                    .jobId(JOB_ID)
                    .partitionId(PARTITION_ID)
                    .outputFile(OUTPUT_FILE);

            // When/Then
            assertThatThrownBy(builder::build)
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("inputFiles");
        }

        @Test
        void shouldThrowExceptionWhenOutputFileIsNull() {
            // Given
            CompactionJob.Builder builder = CompactionJob.builder()
                    .tableId(TABLE_ID)
                    .jobId(JOB_ID)
                    .partitionId(PARTITION_ID)
                    .inputFiles(INPUT_FILES);

            // When/Then
            assertThatThrownBy(builder::build)
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("outputFile");
        }

        @Test
        void shouldThrowExceptionWhenPartitionIdIsNull() {
            // Given
            CompactionJob.Builder builder = CompactionJob.builder()
                    .tableId(TABLE_ID)
                    .jobId(JOB_ID)
                    .outputFile(OUTPUT_FILE)
                    .inputFiles(INPUT_FILES);

            // When/Then
            assertThatThrownBy(builder::build)
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("partitionId");
        }

        @Test
        void shouldThrowExceptionWhenInputFilesContainsDuplicates() {
            // Given
            CompactionJob.Builder builder = validBuilder()
                    .inputFiles(List.of("file1.parquet", "file1.parquet"));

            // When/Then
            assertThatThrownBy(builder::build)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Duplicate");
        }

        @Test
        void shouldAllowSingleInputFile() {
            // Given/When
            CompactionJob job = validBuilder()
                    .inputFiles(List.of("single.parquet"))
                    .build();

            // Then
            assertThat(job.getInputFiles()).containsExactly("single.parquet");
        }

        @Test
        void shouldAllowEmptyInputFilesList() {
            // Given/When
            CompactionJob job = validBuilder()
                    .inputFiles(List.of())
                    .build();

            // Then
            assertThat(job.getInputFiles()).isEmpty();
        }
    }

    @Nested
    @DisplayName("createInputFileAssignmentsCheck")
    class CreateInputFileAssignmentsCheck {

        @Test
        void shouldCreateCheckFileAssignmentsRequest() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            CheckFileAssignmentsRequest request = job.createInputFileAssignmentsCheck();

            // Then
            assertThat(request.getJobId()).isEqualTo(JOB_ID);
            assertThat(request.getFilenames()).isEqualTo(INPUT_FILES);
            assertThat(request.getPartitionId()).isEqualTo(PARTITION_ID);
        }

        @Test
        void shouldCreateRequestWithSingleInputFile() {
            // Given
            CompactionJob job = validBuilder()
                    .inputFiles(List.of("single.parquet"))
                    .build();

            // When
            CheckFileAssignmentsRequest request = job.createInputFileAssignmentsCheck();

            // Then
            assertThat(request.getFilenames()).containsExactly("single.parquet");
        }
    }

    @Nested
    @DisplayName("createAssignJobIdRequest")
    class CreateAssignJobIdRequest {

        @Test
        void shouldCreateAssignJobIdRequest() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            AssignJobIdRequest request = job.createAssignJobIdRequest();

            // Then
            assertThat(request.getJobId()).isEqualTo(JOB_ID);
            assertThat(request.getPartitionId()).isEqualTo(PARTITION_ID);
            assertThat(request.getFilenames()).isEqualTo(INPUT_FILES);
        }
    }

    @Nested
    @DisplayName("createCreatedEvent")
    class CreateCreatedEvent {

        @Test
        void shouldCreateCompactionJobCreatedEvent() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            CompactionJobCreatedEvent event = job.createCreatedEvent();

            // Then
            assertThat(event.getJobId()).isEqualTo(JOB_ID);
            assertThat(event.getTableId()).isEqualTo(TABLE_ID);
            assertThat(event.getPartitionId()).isEqualTo(PARTITION_ID);
            assertThat(event.getInputFilesCount()).isEqualTo(2);
        }

        @Test
        void shouldCreateEventWithCorrectInputFilesCount() {
            // Given
            CompactionJob job = validBuilder()
                    .inputFiles(List.of("file1.parquet", "file2.parquet", "file3.parquet"))
                    .build();

            // When
            CompactionJobCreatedEvent event = job.createCreatedEvent();

            // Then
            assertThat(event.getInputFilesCount()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("startedEventBuilder")
    class StartedEventBuilder {

        @Test
        void shouldCreateStartedEventBuilder() {
            // Given
            CompactionJob job = validBuilder().build();
            Instant startTime = Instant.parse("2024-01-01T10:00:00Z");

            // When
            CompactionJobStartedEvent.Builder builder = job.startedEventBuilder(startTime);
            CompactionJobStartedEvent event = builder
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .build();

            // Then
            assertThat(event.getJobId()).isEqualTo(JOB_ID);
            assertThat(event.getTableId()).isEqualTo(TABLE_ID);
            assertThat(event.getStartTime()).isEqualTo(startTime);
            assertThat(event.getTaskId()).isEqualTo("task-1");
            assertThat(event.getJobRunId()).isEqualTo("run-1");
        }
    }

    @Nested
    @DisplayName("finishedEventBuilder")
    class FinishedEventBuilder {

        @Test
        void shouldCreateFinishedEventBuilder() {
            // Given
            CompactionJob job = validBuilder().build();
            Instant startTime = Instant.parse("2024-01-01T10:00:00Z");
            Instant finishTime = Instant.parse("2024-01-01T10:30:00Z");
            JobRunSummary summary = new JobRunSummary(
                    new RecordsProcessed(1000, 950),
                    startTime, finishTime);

            // When
            CompactionJobFinishedEvent.Builder builder = job.finishedEventBuilder(summary);
            CompactionJobFinishedEvent event = builder
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .build();

            // Then
            assertThat(event.getJobId()).isEqualTo(JOB_ID);
            assertThat(event.getTableId()).isEqualTo(TABLE_ID);
            assertThat(event.getFinishTime()).isEqualTo(finishTime);
            assertThat(event.getRecordsProcessed().getRecordsRead()).isEqualTo(1000);
            assertThat(event.getRecordsProcessed().getRecordsWritten()).isEqualTo(950);
        }
    }

    @Nested
    @DisplayName("committedEventBuilder")
    class CommittedEventBuilder {

        @Test
        void shouldCreateCommittedEventBuilder() {
            // Given
            CompactionJob job = validBuilder().build();
            Instant commitTime = Instant.parse("2024-01-01T11:00:00Z");

            // When
            CompactionJobCommittedEvent.Builder builder = job.committedEventBuilder(commitTime);
            CompactionJobCommittedEvent event = builder
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .build();

            // Then
            assertThat(event.getJobId()).isEqualTo(JOB_ID);
            assertThat(event.getTableId()).isEqualTo(TABLE_ID);
            assertThat(event.getCommitTime()).isEqualTo(commitTime);
            assertThat(event.getTaskId()).isEqualTo("task-1");
            assertThat(event.getJobRunId()).isEqualTo("run-1");
        }
    }

    @Nested
    @DisplayName("failedEventBuilder")
    class FailedEventBuilder {

        @Test
        void shouldCreateFailedEventBuilder() {
            // Given
            CompactionJob job = validBuilder().build();
            Instant failureTime = Instant.parse("2024-01-01T10:15:00Z");

            // When
            CompactionJobFailedEvent.Builder builder = job.failedEventBuilder(failureTime);
            CompactionJobFailedEvent event = builder
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .failureReasons(List.of("Out of memory"))
                    .build();

            // Then
            assertThat(event.getJobId()).isEqualTo(JOB_ID);
            assertThat(event.getTableId()).isEqualTo(TABLE_ID);
            assertThat(event.getFailureTime()).isEqualTo(failureTime);
            assertThat(event.getTaskId()).isEqualTo("task-1");
            assertThat(event.getJobRunId()).isEqualTo("run-1");
            assertThat(event.getFailureReasons()).containsExactly("Out of memory");
        }
    }

    @Nested
    @DisplayName("createOutputFileReference")
    class CreateOutputFileReference {

        @Test
        void shouldCreateOutputFileReference() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            FileReference fileReference = job.createOutputFileReference(5000L);

            // Then
            assertThat(fileReference.getFilename()).isEqualTo(OUTPUT_FILE);
            assertThat(fileReference.getPartitionId()).isEqualTo(PARTITION_ID);
            assertThat(fileReference.getNumberOfRecords()).isEqualTo(5000L);
            assertThat(fileReference.isCountApproximate()).isFalse();
            assertThat(fileReference.onlyContainsDataForThisPartition()).isTrue();
        }

        @Test
        void shouldCreateOutputFileReferenceWithZeroRecords() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            FileReference fileReference = job.createOutputFileReference(0L);

            // Then
            assertThat(fileReference.getNumberOfRecords()).isEqualTo(0L);
        }
    }

    @Nested
    @DisplayName("replaceFileReferencesRequestBuilder")
    class ReplaceFileReferencesRequestBuilder {

        @Test
        void shouldCreateReplaceFileReferencesRequestBuilder() {
            // Given
            CompactionJob job = validBuilder().build();
            long recordsWritten = 3000L;

            // When
            ReplaceFileReferencesRequest.Builder builder = job.replaceFileReferencesRequestBuilder(recordsWritten);
            ReplaceFileReferencesRequest request = builder.build();

            // Then
            assertThat(request.getJobId()).isEqualTo(JOB_ID);
            assertThat(request.getInputFiles()).isEqualTo(INPUT_FILES);
            assertThat(request.getNewReference().getFilename()).isEqualTo(OUTPUT_FILE);
            assertThat(request.getNewReference().getPartitionId()).isEqualTo(PARTITION_ID);
            assertThat(request.getNewReference().getNumberOfRecords()).isEqualTo(recordsWritten);
        }

        @Test
        void shouldAllowAddingTaskIdAndJobRunId() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            ReplaceFileReferencesRequest request = job.replaceFileReferencesRequestBuilder(1000L)
                    .taskId("task-1")
                    .jobRunId("run-1")
                    .build();

            // Then
            assertThat(request.getTaskId()).isEqualTo("task-1");
            assertThat(request.getJobRunId()).isEqualTo("run-1");
        }
    }

    @Nested
    @DisplayName("equals, hashCode, and toString")
    class EqualsHashCodeToString {

        @Test
        void shouldBeEqualWhenAllFieldsAreEqual() {
            // Given
            CompactionJob job1 = validBuilder().build();
            CompactionJob job2 = validBuilder().build();

            // When/Then
            assertThat(job1).isEqualTo(job2);
            assertThat(job1.hashCode()).isEqualTo(job2.hashCode());
        }

        @Test
        void shouldNotBeEqualWhenTableIdDiffers() {
            // Given
            CompactionJob job1 = validBuilder().build();
            CompactionJob job2 = validBuilder().tableId("different-table").build();

            // When/Then
            assertThat(job1).isNotEqualTo(job2);
        }

        @Test
        void shouldNotBeEqualWhenJobIdDiffers() {
            // Given
            CompactionJob job1 = validBuilder().build();
            CompactionJob job2 = validBuilder().jobId("different-job").build();

            // When/Then
            assertThat(job1).isNotEqualTo(job2);
        }

        @Test
        void shouldNotBeEqualWhenInputFilesDiffer() {
            // Given
            CompactionJob job1 = validBuilder().build();
            CompactionJob job2 = validBuilder().inputFiles(List.of("different.parquet")).build();

            // When/Then
            assertThat(job1).isNotEqualTo(job2);
        }

        @Test
        void shouldNotBeEqualWhenOutputFileDiffers() {
            // Given
            CompactionJob job1 = validBuilder().build();
            CompactionJob job2 = validBuilder().outputFile("different-output.parquet").build();

            // When/Then
            assertThat(job1).isNotEqualTo(job2);
        }

        @Test
        void shouldNotBeEqualWhenPartitionIdDiffers() {
            // Given
            CompactionJob job1 = validBuilder().build();
            CompactionJob job2 = validBuilder().partitionId("different-partition").build();

            // When/Then
            assertThat(job1).isNotEqualTo(job2);
        }

        @Test
        void shouldNotBeEqualWhenIteratorClassNameDiffers() {
            // Given
            CompactionJob job1 = validBuilder().iteratorClassName("com.example.Iterator1").build();
            CompactionJob job2 = validBuilder().iteratorClassName("com.example.Iterator2").build();

            // When/Then
            assertThat(job1).isNotEqualTo(job2);
        }

        @Test
        void shouldNotBeEqualWhenIteratorConfigDiffers() {
            // Given
            CompactionJob job1 = validBuilder().iteratorConfig("config1").build();
            CompactionJob job2 = validBuilder().iteratorConfig("config2").build();

            // When/Then
            assertThat(job1).isNotEqualTo(job2);
        }

        @Test
        void shouldBeEqualToItself() {
            // Given
            CompactionJob job = validBuilder().build();

            // When/Then
            assertThat(job).isEqualTo(job);
        }

        @Test
        void shouldNotBeEqualToNull() {
            // Given
            CompactionJob job = validBuilder().build();

            // When/Then
            assertThat(job).isNotEqualTo(null);
        }

        @Test
        void shouldNotBeEqualToDifferentType() {
            // Given
            CompactionJob job = validBuilder().build();

            // When/Then
            assertThat(job).isNotEqualTo("not a compaction job");
        }

        @Test
        void shouldProduceReadableToString() {
            // Given
            CompactionJob job = validBuilder()
                    .iteratorClassName("com.example.Iterator")
                    .iteratorConfig("config")
                    .build();

            // When
            String str = job.toString();

            // Then
            assertThat(str).contains("tableId='" + TABLE_ID + "'");
            assertThat(str).contains("jobId='" + JOB_ID + "'");
            assertThat(str).contains("partitionId='" + PARTITION_ID + "'");
            assertThat(str).contains("outputFile='" + OUTPUT_FILE + "'");
            assertThat(str).contains("inputFiles=" + INPUT_FILES);
            assertThat(str).contains("iteratorClassName='com.example.Iterator'");
            assertThat(str).contains("iteratorConfig='config'");
        }

        @Test
        void shouldIncludeNullIteratorFieldsInToString() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            String str = job.toString();

            // Then
            assertThat(str).contains("iteratorClassName='null'");
            assertThat(str).contains("iteratorConfig='null'");
        }

        @Test
        void shouldHaveDifferentHashCodesForDifferentJobs() {
            // Given
            CompactionJob job1 = validBuilder().jobId("job-1").build();
            CompactionJob job2 = validBuilder().jobId("job-2").build();

            // When/Then
            assertThat(job1.hashCode()).isNotEqualTo(job2.hashCode());
        }
    }
}
