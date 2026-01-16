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

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

public class CompactionCommitMessageHandleClaudeTest {

    private static final String TABLE_ID = "test-table";
    private static final String PARTITION_ID = "root";

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Test
        void shouldCreateHandleWithAllFields() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("input.parquet"), "output.parquet");
            Runnable callback = () -> {
            };

            // When
            CompactionCommitMessageHandle handle = new CompactionCommitMessageHandle(TABLE_ID, request, callback);

            // Then
            assertThat(handle.tableId()).isEqualTo(TABLE_ID);
            assertThat(handle.request()).isSameAs(request);
            assertThat(handle.callbackOnFail()).isSameAs(callback);
        }

        @Test
        void shouldAllowNullTableId() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessageHandle handle = new CompactionCommitMessageHandle(null, request, () -> {
            });

            // Then
            assertThat(handle.tableId()).isNull();
        }

        @Test
        void shouldAllowNullRequest() {
            // When
            CompactionCommitMessageHandle handle = new CompactionCommitMessageHandle(TABLE_ID, null, () -> {
            });

            // Then
            assertThat(handle.request()).isNull();
        }

        @Test
        void shouldAllowNullCallback() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessageHandle handle = new CompactionCommitMessageHandle(TABLE_ID, request, null);

            // Then
            assertThat(handle.callbackOnFail()).isNull();
        }
    }

    @Nested
    @DisplayName("Accessor methods")
    class AccessorMethods {

        @Test
        void shouldReturnTableId() {
            // Given
            CompactionCommitMessageHandle handle = createHandle("table-123", "job-1");

            // Then
            assertThat(handle.tableId()).isEqualTo("table-123");
        }

        @Test
        void shouldReturnRequest() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("file1.parquet", "file2.parquet"), "output.parquet");
            CompactionCommitMessageHandle handle = new CompactionCommitMessageHandle(TABLE_ID, request, () -> {
            });

            // Then
            assertThat(handle.request()).isSameAs(request);
            assertThat(handle.request().getJobId()).isEqualTo("job-1");
            assertThat(handle.request().getInputFiles()).containsExactly("file1.parquet", "file2.parquet");
        }

        @Test
        void shouldReturnCallbackOnFail() {
            // Given
            AtomicBoolean callbackInvoked = new AtomicBoolean(false);
            Runnable callback = () -> callbackInvoked.set(true);
            CompactionCommitMessageHandle handle = new CompactionCommitMessageHandle(TABLE_ID, createRequest("job-1", List.of("input.parquet"), "output.parquet"), callback);

            // When
            handle.callbackOnFail().run();

            // Then
            assertThat(callbackInvoked.get()).isTrue();
        }
    }

    @Nested
    @DisplayName("toString")
    class ToString {

        @Test
        void shouldIncludeTableIdInToString() {
            // Given
            CompactionCommitMessageHandle handle = createHandle("my-table-id", "job-1");

            // When
            String result = handle.toString();

            // Then
            assertThat(result).contains("tableId=my-table-id");
        }

        @Test
        void shouldIncludeRequestInToString() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-123", List.of("input.parquet"), "output.parquet");
            CompactionCommitMessageHandle handle = new CompactionCommitMessageHandle(TABLE_ID, request, () -> {
            });

            // When
            String result = handle.toString();

            // Then
            assertThat(result).contains("request=");
        }

        @Test
        void shouldIncludeCallbackInfoInToString() {
            // Given
            CompactionCommitMessageHandle handle = createHandle(TABLE_ID, "job-1");

            // When
            String result = handle.toString();

            // Then
            assertThat(result).contains("callbackOnFail=");
        }

        @Test
        void shouldIncludeRecordNameInToString() {
            // Given
            CompactionCommitMessageHandle handle = createHandle(TABLE_ID, "job-1");

            // When
            String result = handle.toString();

            // Then
            assertThat(result).contains("CompactionCommitMessageHandle");
        }
    }

    @Nested
    @DisplayName("equals")
    class Equals {

        @Test
        void shouldBeEqualToItself() {
            // Given
            CompactionCommitMessageHandle handle = createHandle(TABLE_ID, "job-1");

            // Then
            assertThat(handle).isEqualTo(handle);
        }

        @Test
        void shouldBeEqualToHandleWithSameFieldValues() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("input.parquet"), "output.parquet");
            Runnable callback = () -> {
            };
            CompactionCommitMessageHandle handle1 = new CompactionCommitMessageHandle(TABLE_ID, request, callback);
            CompactionCommitMessageHandle handle2 = new CompactionCommitMessageHandle(TABLE_ID, request, callback);

            // Then
            assertThat(handle1).isEqualTo(handle2);
        }

        @Test
        void shouldNotBeEqualToHandleWithDifferentTableId() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("input.parquet"), "output.parquet");
            Runnable callback = () -> {
            };
            CompactionCommitMessageHandle handle1 = new CompactionCommitMessageHandle("table-1", request, callback);
            CompactionCommitMessageHandle handle2 = new CompactionCommitMessageHandle("table-2", request, callback);

            // Then
            assertThat(handle1).isNotEqualTo(handle2);
        }

        @Test
        void shouldNotBeEqualToHandleWithDifferentRequest() {
            // Given
            ReplaceFileReferencesRequest request1 = createRequest("job-1", List.of("input1.parquet"), "output1.parquet");
            ReplaceFileReferencesRequest request2 = createRequest("job-2", List.of("input2.parquet"), "output2.parquet");
            Runnable callback = () -> {
            };
            CompactionCommitMessageHandle handle1 = new CompactionCommitMessageHandle(TABLE_ID, request1, callback);
            CompactionCommitMessageHandle handle2 = new CompactionCommitMessageHandle(TABLE_ID, request2, callback);

            // Then
            assertThat(handle1).isNotEqualTo(handle2);
        }

        @Test
        void shouldNotBeEqualToHandleWithDifferentCallback() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("input.parquet"), "output.parquet");
            Runnable callback1 = () -> {
            };
            Runnable callback2 = () -> {
            };
            CompactionCommitMessageHandle handle1 = new CompactionCommitMessageHandle(TABLE_ID, request, callback1);
            CompactionCommitMessageHandle handle2 = new CompactionCommitMessageHandle(TABLE_ID, request, callback2);

            // Then
            assertThat(handle1).isNotEqualTo(handle2);
        }

        @Test
        void shouldNotBeEqualToNull() {
            // Given
            CompactionCommitMessageHandle handle = createHandle(TABLE_ID, "job-1");

            // Then
            assertThat(handle).isNotEqualTo(null);
        }

        @Test
        void shouldNotBeEqualToDifferentType() {
            // Given
            CompactionCommitMessageHandle handle = createHandle(TABLE_ID, "job-1");

            // Then
            assertThat(handle).isNotEqualTo("not a handle");
        }

        @Test
        void shouldBeEqualWhenBothHaveNullTableId() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("input.parquet"), "output.parquet");
            Runnable callback = () -> {
            };
            CompactionCommitMessageHandle handle1 = new CompactionCommitMessageHandle(null, request, callback);
            CompactionCommitMessageHandle handle2 = new CompactionCommitMessageHandle(null, request, callback);

            // Then
            assertThat(handle1).isEqualTo(handle2);
        }

        @Test
        void shouldNotBeEqualWhenOneHasNullTableId() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("input.parquet"), "output.parquet");
            Runnable callback = () -> {
            };
            CompactionCommitMessageHandle handle1 = new CompactionCommitMessageHandle(null, request, callback);
            CompactionCommitMessageHandle handle2 = new CompactionCommitMessageHandle(TABLE_ID, request, callback);

            // Then
            assertThat(handle1).isNotEqualTo(handle2);
        }
    }

    @Nested
    @DisplayName("hashCode")
    class HashCode {

        @Test
        void shouldHaveSameHashCodeForEqualObjects() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("input.parquet"), "output.parquet");
            Runnable callback = () -> {
            };
            CompactionCommitMessageHandle handle1 = new CompactionCommitMessageHandle(TABLE_ID, request, callback);
            CompactionCommitMessageHandle handle2 = new CompactionCommitMessageHandle(TABLE_ID, request, callback);

            // Then
            assertThat(handle1.hashCode()).isEqualTo(handle2.hashCode());
        }

        @Test
        void shouldHaveConsistentHashCode() {
            // Given
            CompactionCommitMessageHandle handle = createHandle(TABLE_ID, "job-1");

            // When
            int hashCode1 = handle.hashCode();
            int hashCode2 = handle.hashCode();

            // Then
            assertThat(hashCode1).isEqualTo(hashCode2);
        }

        @Test
        void shouldHaveDifferentHashCodeForDifferentTableIds() {
            // Given
            ReplaceFileReferencesRequest request = createRequest("job-1", List.of("input.parquet"), "output.parquet");
            Runnable callback = () -> {
            };
            CompactionCommitMessageHandle handle1 = new CompactionCommitMessageHandle("table-1", request, callback);
            CompactionCommitMessageHandle handle2 = new CompactionCommitMessageHandle("table-2", request, callback);

            // Then - different hash codes is likely but not guaranteed
            assertThat(handle1.hashCode()).isNotEqualTo(handle2.hashCode());
        }

        @Test
        void shouldHaveSameHashCodeWithNullFields() {
            // Given
            CompactionCommitMessageHandle handle1 = new CompactionCommitMessageHandle(null, null, null);
            CompactionCommitMessageHandle handle2 = new CompactionCommitMessageHandle(null, null, null);

            // Then
            assertThat(handle1.hashCode()).isEqualTo(handle2.hashCode());
        }
    }

    private CompactionCommitMessageHandle createHandle(String tableId, String jobId) {
        return new CompactionCommitMessageHandle(
                tableId,
                createRequest(jobId, List.of("input.parquet"), "output.parquet"),
                () -> {
                });
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
