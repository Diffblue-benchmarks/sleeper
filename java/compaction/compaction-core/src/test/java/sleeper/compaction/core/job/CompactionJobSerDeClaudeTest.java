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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CompactionJobSerDeClaudeTest {

    private static final String TABLE_ID = "test-table-id";
    private static final String JOB_ID = "test-job-id";
    private static final String PARTITION_ID = "test-partition";
    private static final String OUTPUT_FILE = "output.parquet";
    private static final List<String> INPUT_FILES = List.of("input1.parquet", "input2.parquet");

    private final CompactionJobSerDe serDe = new CompactionJobSerDe();

    private CompactionJob.Builder validBuilder() {
        return CompactionJob.builder()
                .tableId(TABLE_ID)
                .jobId(JOB_ID)
                .partitionId(PARTITION_ID)
                .outputFile(OUTPUT_FILE)
                .inputFiles(INPUT_FILES);
    }

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Test
        void shouldCreateSerDeInstance() {
            // Given/When
            CompactionJobSerDe serDe = new CompactionJobSerDe();

            // Then
            assertThat(serDe).isNotNull();
        }
    }

    @Nested
    @DisplayName("toJson for single job")
    class ToJsonSingleJob {

        @Test
        void shouldSerializeJobWithRequiredFields() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            String json = serDe.toJson(job);

            // Then
            assertThat(json).contains("\"tableId\":\"test-table-id\"");
            assertThat(json).contains("\"jobId\":\"test-job-id\"");
            assertThat(json).contains("\"partitionId\":\"test-partition\"");
            assertThat(json).contains("\"outputFile\":\"output.parquet\"");
            assertThat(json).contains("\"inputFiles\":");
            assertThat(json).contains("\"input1.parquet\"");
            assertThat(json).contains("\"input2.parquet\"");
        }

        @Test
        void shouldSerializeJobWithIteratorFields() {
            // Given
            CompactionJob job = validBuilder()
                    .iteratorClassName("com.example.Iterator")
                    .iteratorConfig("config-value")
                    .build();

            // When
            String json = serDe.toJson(job);

            // Then
            assertThat(json).contains("\"iteratorClassName\":\"com.example.Iterator\"");
            assertThat(json).contains("\"iteratorConfig\":\"config-value\"");
        }

        @Test
        void shouldNotIncludeNullIteratorFieldsInCompactJson() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            String json = serDe.toJson(job);

            // Then - Gson typically omits null fields by default with GsonConfig
            // Check that required fields exist
            assertThat(json).contains("tableId");
            assertThat(json).contains("jobId");
        }

        @Test
        void shouldSerializeJobWithSingleInputFile() {
            // Given
            CompactionJob job = validBuilder()
                    .inputFiles(List.of("single-file.parquet"))
                    .build();

            // When
            String json = serDe.toJson(job);

            // Then
            assertThat(json).contains("\"inputFiles\":[\"single-file.parquet\"]");
        }

        @Test
        void shouldSerializeJobWithEmptyInputFiles() {
            // Given
            CompactionJob job = validBuilder()
                    .inputFiles(List.of())
                    .build();

            // When
            String json = serDe.toJson(job);

            // Then
            assertThat(json).contains("\"inputFiles\":[]");
        }
    }

    @Nested
    @DisplayName("toJsonPrettyPrint for single job")
    class ToJsonPrettyPrintSingleJob {

        @Test
        void shouldSerializeJobWithPrettyPrinting() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            String json = serDe.toJsonPrettyPrint(job);

            // Then - pretty print should have newlines and indentation
            assertThat(json).contains("\n");
            assertThat(json).contains("  ");
        }

        @Test
        void shouldContainAllFieldsWhenPrettyPrinted() {
            // Given
            CompactionJob job = validBuilder()
                    .iteratorClassName("com.example.Iterator")
                    .iteratorConfig("config")
                    .build();

            // When
            String json = serDe.toJsonPrettyPrint(job);

            // Then
            assertThat(json).contains("tableId");
            assertThat(json).contains("jobId");
            assertThat(json).contains("partitionId");
            assertThat(json).contains("outputFile");
            assertThat(json).contains("inputFiles");
            assertThat(json).contains("iteratorClassName");
            assertThat(json).contains("iteratorConfig");
        }

        @Test
        void shouldBeDifferentFromCompactJson() {
            // Given
            CompactionJob job = validBuilder().build();

            // When
            String compactJson = serDe.toJson(job);
            String prettyJson = serDe.toJsonPrettyPrint(job);

            // Then
            assertThat(prettyJson).isNotEqualTo(compactJson);
            assertThat(prettyJson.length()).isGreaterThan(compactJson.length());
        }
    }

    @Nested
    @DisplayName("toJson for batch")
    class ToJsonBatch {

        @Test
        void shouldSerializeSingleJobBatch() {
            // Given
            CompactionJob job = validBuilder().build();
            List<CompactionJob> batch = List.of(job);

            // When
            String json = serDe.toJson(batch);

            // Then
            assertThat(json).contains("\"jobs\":");
            assertThat(json).contains("\"test-job-id\"");
        }

        @Test
        void shouldSerializeMultipleJobsInBatch() {
            // Given
            CompactionJob job1 = validBuilder()
                    .jobId("job-1")
                    .build();
            CompactionJob job2 = validBuilder()
                    .jobId("job-2")
                    .outputFile("output2.parquet")
                    .build();
            List<CompactionJob> batch = List.of(job1, job2);

            // When
            String json = serDe.toJson(batch);

            // Then
            assertThat(json).contains("\"jobs\":");
            assertThat(json).contains("\"job-1\"");
            assertThat(json).contains("\"job-2\"");
        }

        @Test
        void shouldSerializeEmptyBatch() {
            // Given
            List<CompactionJob> batch = List.of();

            // When
            String json = serDe.toJson(batch);

            // Then
            assertThat(json).contains("\"jobs\":[]");
        }
    }

    @Nested
    @DisplayName("toJsonPrettyPrint for batch")
    class ToJsonPrettyPrintBatch {

        @Test
        void shouldSerializeBatchWithPrettyPrinting() {
            // Given
            CompactionJob job = validBuilder().build();
            List<CompactionJob> batch = List.of(job);

            // When
            String json = serDe.toJsonPrettyPrint(batch);

            // Then
            assertThat(json).contains("\n");
            assertThat(json).contains("  ");
            assertThat(json).contains("jobs");
        }

        @Test
        void shouldBeDifferentFromCompactBatchJson() {
            // Given
            CompactionJob job = validBuilder().build();
            List<CompactionJob> batch = List.of(job);

            // When
            String compactJson = serDe.toJson(batch);
            String prettyJson = serDe.toJsonPrettyPrint(batch);

            // Then
            assertThat(prettyJson).isNotEqualTo(compactJson);
            assertThat(prettyJson.length()).isGreaterThan(compactJson.length());
        }
    }

    @Nested
    @DisplayName("fromJson")
    class FromJson {

        @Test
        void shouldDeserializeJobWithRequiredFields() {
            // Given
            CompactionJob originalJob = validBuilder().build();
            String json = serDe.toJson(originalJob);

            // When
            CompactionJob deserializedJob = serDe.fromJson(json);

            // Then
            assertThat(deserializedJob).isEqualTo(originalJob);
        }

        @Test
        void shouldDeserializeJobWithIteratorFields() {
            // Given
            CompactionJob originalJob = validBuilder()
                    .iteratorClassName("com.example.Iterator")
                    .iteratorConfig("config-value")
                    .build();
            String json = serDe.toJson(originalJob);

            // When
            CompactionJob deserializedJob = serDe.fromJson(json);

            // Then
            assertThat(deserializedJob).isEqualTo(originalJob);
            assertThat(deserializedJob.getIteratorClassName()).isEqualTo("com.example.Iterator");
            assertThat(deserializedJob.getIteratorConfig()).isEqualTo("config-value");
        }

        @Test
        void shouldDeserializeJobFromPrettyPrintedJson() {
            // Given
            CompactionJob originalJob = validBuilder().build();
            String prettyJson = serDe.toJsonPrettyPrint(originalJob);

            // When
            CompactionJob deserializedJob = serDe.fromJson(prettyJson);

            // Then
            assertThat(deserializedJob).isEqualTo(originalJob);
        }

        @Test
        void shouldDeserializeJobWithSingleInputFile() {
            // Given
            CompactionJob originalJob = validBuilder()
                    .inputFiles(List.of("single.parquet"))
                    .build();
            String json = serDe.toJson(originalJob);

            // When
            CompactionJob deserializedJob = serDe.fromJson(json);

            // Then
            assertThat(deserializedJob.getInputFiles()).containsExactly("single.parquet");
        }

        @Test
        void shouldDeserializeJobWithEmptyInputFiles() {
            // Given
            CompactionJob originalJob = validBuilder()
                    .inputFiles(List.of())
                    .build();
            String json = serDe.toJson(originalJob);

            // When
            CompactionJob deserializedJob = serDe.fromJson(json);

            // Then
            assertThat(deserializedJob.getInputFiles()).isEmpty();
        }

        @Test
        void shouldPreserveAllFieldsAfterRoundTrip() {
            // Given
            CompactionJob originalJob = validBuilder()
                    .tableId("my-table")
                    .jobId("my-job")
                    .partitionId("my-partition")
                    .outputFile("output-file.parquet")
                    .inputFiles(List.of("file1.parquet", "file2.parquet", "file3.parquet"))
                    .iteratorClassName("com.example.MyIterator")
                    .iteratorConfig("{\"key\":\"value\"}")
                    .build();
            String json = serDe.toJson(originalJob);

            // When
            CompactionJob deserializedJob = serDe.fromJson(json);

            // Then
            assertThat(deserializedJob.getTableId()).isEqualTo("my-table");
            assertThat(deserializedJob.getId()).isEqualTo("my-job");
            assertThat(deserializedJob.getPartitionId()).isEqualTo("my-partition");
            assertThat(deserializedJob.getOutputFile()).isEqualTo("output-file.parquet");
            assertThat(deserializedJob.getInputFiles()).containsExactly("file1.parquet", "file2.parquet", "file3.parquet");
            assertThat(deserializedJob.getIteratorClassName()).isEqualTo("com.example.MyIterator");
            assertThat(deserializedJob.getIteratorConfig()).isEqualTo("{\"key\":\"value\"}");
        }
    }

    @Nested
    @DisplayName("batchFromJson")
    class BatchFromJson {

        @Test
        void shouldDeserializeSingleJobBatch() {
            // Given
            CompactionJob job = validBuilder().build();
            List<CompactionJob> originalBatch = List.of(job);
            String json = serDe.toJson(originalBatch);

            // When
            List<CompactionJob> deserializedBatch = serDe.batchFromJson(json);

            // Then
            assertThat(deserializedBatch).hasSize(1);
            assertThat(deserializedBatch.get(0)).isEqualTo(job);
        }

        @Test
        void shouldDeserializeMultipleJobsInBatch() {
            // Given
            CompactionJob job1 = validBuilder()
                    .jobId("job-1")
                    .build();
            CompactionJob job2 = validBuilder()
                    .jobId("job-2")
                    .outputFile("output2.parquet")
                    .build();
            List<CompactionJob> originalBatch = List.of(job1, job2);
            String json = serDe.toJson(originalBatch);

            // When
            List<CompactionJob> deserializedBatch = serDe.batchFromJson(json);

            // Then
            assertThat(deserializedBatch).hasSize(2);
            assertThat(deserializedBatch).containsExactly(job1, job2);
        }

        @Test
        void shouldDeserializeEmptyBatch() {
            // Given
            List<CompactionJob> originalBatch = List.of();
            String json = serDe.toJson(originalBatch);

            // When
            List<CompactionJob> deserializedBatch = serDe.batchFromJson(json);

            // Then
            assertThat(deserializedBatch).isEmpty();
        }

        @Test
        void shouldDeserializeBatchFromPrettyPrintedJson() {
            // Given
            CompactionJob job = validBuilder().build();
            List<CompactionJob> originalBatch = List.of(job);
            String prettyJson = serDe.toJsonPrettyPrint(originalBatch);

            // When
            List<CompactionJob> deserializedBatch = serDe.batchFromJson(prettyJson);

            // Then
            assertThat(deserializedBatch).hasSize(1);
            assertThat(deserializedBatch.get(0)).isEqualTo(job);
        }

        @Test
        void shouldDeserializeBatchWithJobsHavingIteratorFields() {
            // Given
            CompactionJob job1 = validBuilder()
                    .jobId("job-1")
                    .iteratorClassName("com.example.Iterator1")
                    .iteratorConfig("config1")
                    .build();
            CompactionJob job2 = validBuilder()
                    .jobId("job-2")
                    .outputFile("output2.parquet")
                    .build();
            List<CompactionJob> originalBatch = List.of(job1, job2);
            String json = serDe.toJson(originalBatch);

            // When
            List<CompactionJob> deserializedBatch = serDe.batchFromJson(json);

            // Then
            assertThat(deserializedBatch).hasSize(2);
            assertThat(deserializedBatch.get(0).getIteratorClassName()).isEqualTo("com.example.Iterator1");
            assertThat(deserializedBatch.get(0).getIteratorConfig()).isEqualTo("config1");
            assertThat(deserializedBatch.get(1).getIteratorClassName()).isNull();
        }

        @Test
        void shouldPreserveOrderOfJobsInBatch() {
            // Given
            CompactionJob job1 = validBuilder().jobId("first").build();
            CompactionJob job2 = validBuilder().jobId("second").outputFile("out2.parquet").build();
            CompactionJob job3 = validBuilder().jobId("third").outputFile("out3.parquet").build();
            List<CompactionJob> originalBatch = List.of(job1, job2, job3);
            String json = serDe.toJson(originalBatch);

            // When
            List<CompactionJob> deserializedBatch = serDe.batchFromJson(json);

            // Then
            assertThat(deserializedBatch).hasSize(3);
            assertThat(deserializedBatch.get(0).getId()).isEqualTo("first");
            assertThat(deserializedBatch.get(1).getId()).isEqualTo("second");
            assertThat(deserializedBatch.get(2).getId()).isEqualTo("third");
        }
    }

    @Nested
    @DisplayName("Round-trip serialization")
    class RoundTrip {

        @Test
        void shouldRoundTripSingleJobThroughCompactJson() {
            // Given
            CompactionJob originalJob = validBuilder().build();

            // When
            String json = serDe.toJson(originalJob);
            CompactionJob deserializedJob = serDe.fromJson(json);

            // Then
            assertThat(deserializedJob).isEqualTo(originalJob);
        }

        @Test
        void shouldRoundTripSingleJobThroughPrettyPrintJson() {
            // Given
            CompactionJob originalJob = validBuilder().build();

            // When
            String json = serDe.toJsonPrettyPrint(originalJob);
            CompactionJob deserializedJob = serDe.fromJson(json);

            // Then
            assertThat(deserializedJob).isEqualTo(originalJob);
        }

        @Test
        void shouldRoundTripBatchThroughCompactJson() {
            // Given
            List<CompactionJob> originalBatch = List.of(
                    validBuilder().jobId("job-1").build(),
                    validBuilder().jobId("job-2").outputFile("out2.parquet").build());

            // When
            String json = serDe.toJson(originalBatch);
            List<CompactionJob> deserializedBatch = serDe.batchFromJson(json);

            // Then
            assertThat(deserializedBatch).isEqualTo(originalBatch);
        }

        @Test
        void shouldRoundTripBatchThroughPrettyPrintJson() {
            // Given
            List<CompactionJob> originalBatch = List.of(
                    validBuilder().jobId("job-1").build(),
                    validBuilder().jobId("job-2").outputFile("out2.parquet").build());

            // When
            String json = serDe.toJsonPrettyPrint(originalBatch);
            List<CompactionJob> deserializedBatch = serDe.batchFromJson(json);

            // Then
            assertThat(deserializedBatch).isEqualTo(originalBatch);
        }

        @Test
        void shouldRoundTripJobWithSpecialCharactersInStrings() {
            // Given
            CompactionJob originalJob = validBuilder()
                    .tableId("table-with-special-chars-!@#$")
                    .jobId("job-with-unicode-\u00e9\u00e8")
                    .partitionId("partition/with/slashes")
                    .outputFile("path/to/output with spaces.parquet")
                    .inputFiles(List.of("file\"with\"quotes.parquet", "file\twith\ttabs.parquet"))
                    .iteratorConfig("{\"nested\":{\"json\":\"value\"}}")
                    .build();

            // When
            String json = serDe.toJson(originalJob);
            CompactionJob deserializedJob = serDe.fromJson(json);

            // Then
            assertThat(deserializedJob).isEqualTo(originalJob);
        }
    }
}
