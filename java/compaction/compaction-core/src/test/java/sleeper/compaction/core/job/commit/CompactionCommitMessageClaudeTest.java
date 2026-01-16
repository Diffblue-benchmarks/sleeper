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

import static org.assertj.core.api.Assertions.assertThat;

public class CompactionCommitMessageClaudeTest {

    private static final String TABLE_ID = "test-table-id";
    private static final String JOB_ID = "test-job-id";
    private static final String PARTITION_ID = "root";

    @Nested
    @DisplayName("Constructor and accessor methods")
    class ConstructorAndAccessors {

        @Test
        void shouldCreateMessageWithTableIdAndRequest() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // Then
            assertThat(message.tableId()).isEqualTo(TABLE_ID);
            assertThat(message.request()).isSameAs(request);
        }

        @Test
        void shouldAllowNullTableId() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessage message = new CompactionCommitMessage(null, request);

            // Then
            assertThat(message.tableId()).isNull();
            assertThat(message.request()).isSameAs(request);
        }

        @Test
        void shouldAllowNullRequest() {
            // When
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, null);

            // Then
            assertThat(message.tableId()).isEqualTo(TABLE_ID);
            assertThat(message.request()).isNull();
        }

        @Test
        void shouldAllowBothFieldsNull() {
            // When
            CompactionCommitMessage message = new CompactionCommitMessage(null, null);

            // Then
            assertThat(message.tableId()).isNull();
            assertThat(message.request()).isNull();
        }

        @Test
        void shouldReturnDifferentTableIds() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            String tableId1 = "table-1";
            String tableId2 = "table-2";

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(tableId1, request);
            CompactionCommitMessage message2 = new CompactionCommitMessage(tableId2, request);

            // Then
            assertThat(message1.tableId()).isEqualTo(tableId1);
            assertThat(message2.tableId()).isEqualTo(tableId2);
        }

        @Test
        void shouldReturnDifferentRequests() {
            // Given
            ReplaceFileReferencesRequest request1 = createRequest("job-1", List.of("input1.parquet"), "output1.parquet");
            ReplaceFileReferencesRequest request2 = createRequest("job-2", List.of("input2.parquet"), "output2.parquet");

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(TABLE_ID, request1);
            CompactionCommitMessage message2 = new CompactionCommitMessage(TABLE_ID, request2);

            // Then
            assertThat(message1.request()).isSameAs(request1);
            assertThat(message2.request()).isSameAs(request2);
        }
    }

    @Nested
    @DisplayName("equals method")
    class EqualsMethod {

        @Test
        void shouldBeEqualToItself() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // Then
            assertThat(message).isEqualTo(message);
        }

        @Test
        void shouldBeEqualWhenSameTableIdAndRequest() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(TABLE_ID, request);
            CompactionCommitMessage message2 = new CompactionCommitMessage(TABLE_ID, request);

            // Then
            assertThat(message1).isEqualTo(message2);
            assertThat(message2).isEqualTo(message1);
        }

        @Test
        void shouldBeEqualWhenEquivalentRequests() {
            // Given - two request objects with the same values
            ReplaceFileReferencesRequest request1 = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            ReplaceFileReferencesRequest request2 = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(TABLE_ID, request1);
            CompactionCommitMessage message2 = new CompactionCommitMessage(TABLE_ID, request2);

            // Then
            assertThat(message1).isEqualTo(message2);
        }

        @Test
        void shouldNotBeEqualWhenDifferentTableId() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage("table-1", request);
            CompactionCommitMessage message2 = new CompactionCommitMessage("table-2", request);

            // Then
            assertThat(message1).isNotEqualTo(message2);
        }

        @Test
        void shouldNotBeEqualWhenDifferentRequest() {
            // Given
            ReplaceFileReferencesRequest request1 = createRequest("job-1", List.of("input1.parquet"), "output1.parquet");
            ReplaceFileReferencesRequest request2 = createRequest("job-2", List.of("input2.parquet"), "output2.parquet");

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(TABLE_ID, request1);
            CompactionCommitMessage message2 = new CompactionCommitMessage(TABLE_ID, request2);

            // Then
            assertThat(message1).isNotEqualTo(message2);
        }

        @Test
        void shouldNotBeEqualToNull() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // Then
            assertThat(message).isNotEqualTo(null);
        }

        @Test
        void shouldNotBeEqualToDifferentType() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // Then
            assertThat(message).isNotEqualTo("not a CompactionCommitMessage");
            assertThat(message).isNotEqualTo(123);
        }

        @Test
        void shouldBeEqualWhenBothHaveNullTableId() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(null, request);
            CompactionCommitMessage message2 = new CompactionCommitMessage(null, request);

            // Then
            assertThat(message1).isEqualTo(message2);
        }

        @Test
        void shouldBeEqualWhenBothHaveNullRequest() {
            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(TABLE_ID, null);
            CompactionCommitMessage message2 = new CompactionCommitMessage(TABLE_ID, null);

            // Then
            assertThat(message1).isEqualTo(message2);
        }

        @Test
        void shouldNotBeEqualWhenOneHasNullTableId() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(null, request);
            CompactionCommitMessage message2 = new CompactionCommitMessage(TABLE_ID, request);

            // Then
            assertThat(message1).isNotEqualTo(message2);
        }

        @Test
        void shouldNotBeEqualWhenOneHasNullRequest() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(TABLE_ID, null);
            CompactionCommitMessage message2 = new CompactionCommitMessage(TABLE_ID, request);

            // Then
            assertThat(message1).isNotEqualTo(message2);
        }
    }

    @Nested
    @DisplayName("hashCode method")
    class HashCodeMethod {

        @Test
        void shouldHaveSameHashCodeWhenEqual() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(TABLE_ID, request);
            CompactionCommitMessage message2 = new CompactionCommitMessage(TABLE_ID, request);

            // Then
            assertThat(message1.hashCode()).isEqualTo(message2.hashCode());
        }

        @Test
        void shouldHaveSameHashCodeWhenEquivalentRequests() {
            // Given
            ReplaceFileReferencesRequest request1 = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            ReplaceFileReferencesRequest request2 = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");

            // When
            CompactionCommitMessage message1 = new CompactionCommitMessage(TABLE_ID, request1);
            CompactionCommitMessage message2 = new CompactionCommitMessage(TABLE_ID, request2);

            // Then
            assertThat(message1.hashCode()).isEqualTo(message2.hashCode());
        }

        @Test
        void shouldHaveSameHashCodeWhenCalledMultipleTimes() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            int hashCode1 = message.hashCode();
            int hashCode2 = message.hashCode();
            int hashCode3 = message.hashCode();

            // Then
            assertThat(hashCode1).isEqualTo(hashCode2).isEqualTo(hashCode3);
        }

        @Test
        void shouldHandleNullTableIdInHashCode() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message1 = new CompactionCommitMessage(null, request);
            CompactionCommitMessage message2 = new CompactionCommitMessage(null, request);

            // Then - should not throw and should be consistent
            assertThat(message1.hashCode()).isEqualTo(message2.hashCode());
        }

        @Test
        void shouldHandleNullRequestInHashCode() {
            // Given
            CompactionCommitMessage message1 = new CompactionCommitMessage(TABLE_ID, null);
            CompactionCommitMessage message2 = new CompactionCommitMessage(TABLE_ID, null);

            // Then - should not throw and should be consistent
            assertThat(message1.hashCode()).isEqualTo(message2.hashCode());
        }

        @Test
        void shouldHandleBothFieldsNullInHashCode() {
            // Given
            CompactionCommitMessage message1 = new CompactionCommitMessage(null, null);
            CompactionCommitMessage message2 = new CompactionCommitMessage(null, null);

            // Then - should not throw and should be consistent
            assertThat(message1.hashCode()).isEqualTo(message2.hashCode());
        }
    }

    @Nested
    @DisplayName("toString method")
    class ToStringMethod {

        @Test
        void shouldIncludeTableIdInToString() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String result = message.toString();

            // Then
            assertThat(result).contains(TABLE_ID);
        }

        @Test
        void shouldIncludeRequestDetailsInToString() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String result = message.toString();

            // Then
            assertThat(result).contains("request=");
        }

        @Test
        void shouldHandleNullTableIdInToString() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(null, request);

            // When
            String result = message.toString();

            // Then
            assertThat(result).contains("tableId=null");
        }

        @Test
        void shouldHandleNullRequestInToString() {
            // Given
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, null);

            // When
            String result = message.toString();

            // Then
            assertThat(result).contains("request=null");
        }

        @Test
        void shouldIncludeRecordNameInToString() {
            // Given
            ReplaceFileReferencesRequest request = createRequest(JOB_ID, List.of("input.parquet"), "output.parquet");
            CompactionCommitMessage message = new CompactionCommitMessage(TABLE_ID, request);

            // When
            String result = message.toString();

            // Then
            assertThat(result).contains("CompactionCommitMessage");
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
