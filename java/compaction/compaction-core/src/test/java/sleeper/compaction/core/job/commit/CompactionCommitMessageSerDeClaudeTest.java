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
package sleeper.compaction.core.job.commit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.ReplaceFileReferencesRequest;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

public class CompactionCommitMessageSerDeClaudeTest {

    private static final String TABLE_ID = "test-table-id";
    private static final String JOB_ID = "test-job-id";
    private static final String PARTITION_ID = "root";

    private final CompactionCommitMessageSerDe serDe = new CompactionCommitMessageSerDe();

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Test
        void shouldCreateSerDeInstance() {
            // Given/When
            CompactionCommitMessageSerDe serDe = new CompactionCommitMessageSerDe();

            // Then
            assertThat(serDe).isNotNull();
        }
    }

    @Nested
    @DisplayName("toJson")
    class ToJson {

        @Test
        void shouldSerializeMessageWithTableIdAndRequest() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJson(message);

            // Then
            assertThat(json).contains("\"tableId\":\"test-table-id\"");
            assertThat(json).contains("\"jobId\":\"test-job-id\"");
            assertThat(json).contains("\"inputFiles\":[\"input.parquet\"]");
        }

        @Test
        void shouldSerializeMessageWithMultipleInputFiles() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input1.parquet", "input2.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJson(message);

            // Then
            assertThat(json).contains("\"input1.parquet\"");
            assertThat(json).contains("\"input2.parquet\"");
        }

        @Test
        void shouldSerializeFileReferenceFields() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJson(message);

            // Then
            assertThat(json).contains("\"filename\":\"output.parquet\"");
            assertThat(json).contains("\"partitionId\":\"root\"");
            assertThat(json).contains("\"numberOfRecords\":100");
        }

        @Test
        void shouldExcludeLastStateStoreUpdateTime() {
            // Given
            FileReference reference = FileReference.builder()
                    .filename("output.parquet")
                    .partitionId(PARTITION_ID)
                    .numberOfRecords(100L)
                    .countApproximate(false)
                    .onlyContainsDataForThisPartition(true)
                    .lastStateStoreUpdateTime(Instant.parse("2024-01-01T12:00:00Z"))
                    .build();
            ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                    .jobId(JOB_ID)
                    .inputFiles(List.of("input.parquet"))
                    .newReference(reference)
                    .build();
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJson(message);

            // Then
            assertThat(json).doesNotContain("lastStateStoreUpdateTime");
            assertThat(json).doesNotContain("2024-01-01");
        }

        @Test
        void shouldSerializeWithTaskIdAndJobRunId() {
            // Given
            ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                    .jobId(JOB_ID)
                    .taskId("task-123")
                    .jobRunId("run-456")
                    .inputFiles(List.of("input.parquet"))
                    .newReference(createFileReference("output.parquet", 100L))
                    .build();
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJson(message);

            // Then
            assertThat(json).contains("\"taskId\":\"task-123\"");
            assertThat(json).contains("\"jobRunId\":\"run-456\"");
        }

        @Test
        void shouldSerializeEmptyInputFilesList() {
            // Given
            ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                    .jobId(JOB_ID)
                    .inputFiles(List.of())
                    .newReference(createFileReference("output.parquet", 100L))
                    .build();
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJson(message);

            // Then
            assertThat(json).contains("\"inputFiles\":[]");
        }

        @Test
        void shouldProduceCompactJson() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJson(message);

            // Then - compact JSON should not have newlines
            assertThat(json).doesNotContain("\n");
        }
    }

    @Nested
    @DisplayName("toJsonPrettyPrint")
    class ToJsonPrettyPrint {

        @Test
        void shouldSerializeMessageWithPrettyPrinting() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJsonPrettyPrint(message);

            // Then - pretty print should have newlines and indentation
            assertThat(json).contains("\n");
            assertThat(json).contains("  ");
        }

        @Test
        void shouldContainAllFieldsWhenPrettyPrinted() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJsonPrettyPrint(message);

            // Then
            assertThat(json).contains("tableId");
            assertThat(json).contains("jobId");
            assertThat(json).contains("inputFiles");
            assertThat(json).contains("newReference");
        }

        @Test
        void shouldBeDifferentFromCompactJson() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String compactJson = serDe.toJson(message);
            String prettyJson = serDe.toJsonPrettyPrint(message);

            // Then
            assertThat(prettyJson).isNotEqualTo(compactJson);
            assertThat(prettyJson.length()).isGreaterThan(compactJson.length());
        }

        @Test
        void shouldExcludeLastStateStoreUpdateTimeWhenPrettyPrinted() {
            // Given
            FileReference reference = FileReference.builder()
                    .filename("output.parquet")
                    .partitionId(PARTITION_ID)
                    .numberOfRecords(100L)
                    .countApproximate(false)
                    .onlyContainsDataForThisPartition(true)
                    .lastStateStoreUpdateTime(Instant.parse("2024-06-15T10:30:00Z"))
                    .build();
            ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                    .jobId(JOB_ID)
                    .inputFiles(List.of("input.parquet"))
                    .newReference(reference)
                    .build();
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJsonPrettyPrint(message);

            // Then
            assertThat(json).doesNotContain("lastStateStoreUpdateTime");
        }
    }

    @Nested
    @DisplayName("fromJsonWithCallbackOnFail")
    class FromJsonWithCallbackOnFail {

        @Test
        void shouldDeserializeMessageAndCreateHandle() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);
            String json = serDe.toJson(message);
            Runnable callback = () -> {
            };

            // When
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, callback);

            // Then
            assertThat(handle.tableId()).isEqualTo(TABLE_ID);
            assertThat(handle.request().getJobId()).isEqualTo(JOB_ID);
            assertThat(handle.callbackOnFail()).isSameAs(callback);
        }

        @Test
        void shouldPreserveCallbackInHandle() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);
            String json = serDe.toJson(message);
            AtomicBoolean called = new AtomicBoolean(false);
            Runnable callback = () -> called.set(true);

            // When
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, callback);
            handle.callbackOnFail().run();

            // Then
            assertThat(called.get()).isTrue();
        }

        @Test
        void shouldDeserializeFromPrettyPrintedJson() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);
            String prettyJson = serDe.toJsonPrettyPrint(message);
            Runnable callback = () -> {
            };

            // When
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(prettyJson, callback);

            // Then
            assertThat(handle.tableId()).isEqualTo(TABLE_ID);
            assertThat(handle.request().getJobId()).isEqualTo(JOB_ID);
        }

        @Test
        void shouldDeserializeMultipleInputFiles() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("file1.parquet", "file2.parquet", "file3.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);
            String json = serDe.toJson(message);
            Runnable callback = () -> {
            };

            // When
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, callback);

            // Then
            assertThat(handle.request().getInputFiles()).containsExactly("file1.parquet", "file2.parquet", "file3.parquet");
        }

        @Test
        void shouldDeserializeWithTaskIdAndJobRunId() {
            // Given
            ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                    .jobId(JOB_ID)
                    .taskId("my-task")
                    .jobRunId("my-run")
                    .inputFiles(List.of("input.parquet"))
                    .newReference(createFileReference("output.parquet", 100L))
                    .build();
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);
            String json = serDe.toJson(message);
            Runnable callback = () -> {
            };

            // When
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, callback);

            // Then
            assertThat(handle.request().getTaskId()).isEqualTo("my-task");
            assertThat(handle.request().getJobRunId()).isEqualTo("my-run");
        }

        @Test
        void shouldDeserializeFileReferenceDetails() {
            // Given
            FileReference reference = FileReference.builder()
                    .filename("output.parquet")
                    .partitionId("partition-1")
                    .numberOfRecords(5000L)
                    .countApproximate(true)
                    .onlyContainsDataForThisPartition(false)
                    .build();
            ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                    .jobId(JOB_ID)
                    .inputFiles(List.of("input.parquet"))
                    .newReference(reference)
                    .build();
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);
            String json = serDe.toJson(message);
            Runnable callback = () -> {
            };

            // When
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, callback);

            // Then
            FileReference newRef = handle.request().getNewReference();
            assertThat(newRef.getFilename()).isEqualTo("output.parquet");
            assertThat(newRef.getPartitionId()).isEqualTo("partition-1");
            assertThat(newRef.getNumberOfRecords()).isEqualTo(5000L);
            assertThat(newRef.isCountApproximate()).isTrue();
            assertThat(newRef.onlyContainsDataForThisPartition()).isFalse();
        }

        @Test
        void shouldDeserializeEmptyInputFiles() {
            // Given
            ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                    .jobId(JOB_ID)
                    .inputFiles(List.of())
                    .newReference(createFileReference("output.parquet", 100L))
                    .build();
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);
            String json = serDe.toJson(message);
            Runnable callback = () -> {
            };

            // When
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, callback);

            // Then
            assertThat(handle.request().getInputFiles()).isEmpty();
        }

        @Test
        void shouldHandleNullCallback() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);
            String json = serDe.toJson(message);

            // When
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, null);

            // Then
            assertThat(handle.tableId()).isEqualTo(TABLE_ID);
            assertThat(handle.callbackOnFail()).isNull();
        }
    }

    @Nested
    @DisplayName("Round-trip serialization")
    class RoundTrip {

        @Test
        void shouldRoundTripThroughCompactJson() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input1.parquet", "input2.parquet"), "output.parquet");
            CompactionCommitMessage originalMessage = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJson(originalMessage);
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, () -> {
            });

            // Then
            assertThat(handle.tableId()).isEqualTo(originalMessage.tableId());
            assertThat(handle.request().getJobId()).isEqualTo(originalMessage.request().getJobId());
            assertThat(handle.request().getInputFiles()).isEqualTo(originalMessage.request().getInputFiles());
            assertThat(handle.request().getNewReference().getFilename())
                    .isEqualTo(originalMessage.request().getNewReference().getFilename());
        }

        @Test
        void shouldRoundTripThroughPrettyPrintJson() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input1.parquet", "input2.parquet"), "output.parquet");
            CompactionCommitMessage originalMessage = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJsonPrettyPrint(originalMessage);
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, () -> {
            });

            // Then
            assertThat(handle.tableId()).isEqualTo(originalMessage.tableId());
            assertThat(handle.request().getJobId()).isEqualTo(originalMessage.request().getJobId());
            assertThat(handle.request().getInputFiles()).isEqualTo(originalMessage.request().getInputFiles());
        }

        @Test
        void shouldRoundTripWithSpecialCharactersInTableId() {
            // Given
            String specialTableId = "table-with-special-chars-!@#$%";
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage originalMessage = new CompactionCommitMessage(specialTableId, request);

            // When
            String json = serDe.toJson(originalMessage);
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, () -> {
            });

            // Then
            assertThat(handle.tableId()).isEqualTo(specialTableId);
        }

        @Test
        void shouldRoundTripWithSpecialCharactersInFilenames() {
            // Given
            ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                    .jobId(JOB_ID)
                    .inputFiles(List.of("file with spaces.parquet", "file\twith\ttabs.parquet"))
                    .newReference(createFileReference("output \"quoted\".parquet", 100L))
                    .build();
            CompactionCommitMessage originalMessage = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String json = serDe.toJson(originalMessage);
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, () -> {
            });

            // Then
            assertThat(handle.request().getInputFiles())
                    .containsExactly("file with spaces.parquet", "file\twith\ttabs.parquet");
            assertThat(handle.request().getNewReference().getFilename())
                    .isEqualTo("output \"quoted\".parquet");
        }

        @Test
        void shouldRoundTripWithUnicodeCharacters() {
            // Given
            String unicodeTableId = "table-\u00e9\u00e8\u00ea";
            ReplaceFileReferencesRequest request = createRequest("job-\u00fc\u00f6\u00e4", List.of("input-\u4e2d\u6587.parquet"), "output.parquet");
            CompactionCommitMessage originalMessage = new CompactionCommitMessage(unicodeTableId, request);

            // When
            String json = serDe.toJson(originalMessage);
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, () -> {
            });

            // Then
            assertThat(handle.tableId()).isEqualTo(unicodeTableId);
            assertThat(handle.request().getJobId()).isEqualTo("job-\u00fc\u00f6\u00e4");
            assertThat(handle.request().getInputFiles()).containsExactly("input-\u4e2d\u6587.parquet");
        }

        @Test
        void shouldRoundTripWithFullyPopulatedRequest() {
            // Given
            FileReference reference = FileReference.builder()
                    .filename("output.parquet")
                    .partitionId("partition-abc")
                    .numberOfRecords(999999L)
                    .countApproximate(true)
                    .onlyContainsDataForThisPartition(false)
                    .jobId("assigned-job-id")
                    .build();
            ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                    .jobId("original-job-id")
                    .taskId("task-xyz")
                    .jobRunId("run-xyz")
                    .inputFiles(List.of("a.parquet", "b.parquet", "c.parquet"))
                    .newReference(reference)
                    .build();
            CompactionCommitMessage originalMessage = new CompactionCommitMessage("complex-table-id", request);

            // When
            String json = serDe.toJson(originalMessage);
            CompactionCommitMessageHandle handle = serDe.fromJsonWithCallbackOnFail(json, () -> {
            });

            // Then
            assertThat(handle.tableId()).isEqualTo("complex-table-id");
            assertThat(handle.request().getJobId()).isEqualTo("original-job-id");
            assertThat(handle.request().getTaskId()).isEqualTo("task-xyz");
            assertThat(handle.request().getJobRunId()).isEqualTo("run-xyz");
            assertThat(handle.request().getInputFiles()).containsExactly("a.parquet", "b.parquet", "c.parquet");

            FileReference newRef = handle.request().getNewReference();
            assertThat(newRef.getFilename()).isEqualTo("output.parquet");
            assertThat(newRef.getPartitionId()).isEqualTo("partition-abc");
            assertThat(newRef.getNumberOfRecords()).isEqualTo(999999L);
            assertThat(newRef.isCountApproximate()).isTrue();
            assertThat(newRef.onlyContainsDataForThisPartition()).isFalse();
            assertThat(newRef.getJobId()).isEqualTo("assigned-job-id");
        }
    }

    private ReplaceFileReferencesRequest createRequest(String jobId, List<String> inputFiles, String outputFile) {
        return ReplaceFileReferencesRequest.builder()
                .jobId(jobId)
                .inputFiles(inputFiles)
                .newReference(createFileReference(outputFile, 100L))
                .build();
    }

    private FileReference createFileReference(String filename, long records) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(PARTITION_ID)
                .numberOfRecords(records)
                .countApproximate(false)
                .onlyContainsDataForThisPartition(true)
                .build();
    }
}
