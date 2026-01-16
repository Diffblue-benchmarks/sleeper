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
package sleeper.compaction.core.job.dispatch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;

public class CompactionJobDispatchRequestClaudeTest {

    private final Schema schema = schemaWithKey("key", new StringType());
    private final InstanceProperties instanceProperties = createTestInstanceProperties();

    @Nested
    @DisplayName("forTableWithBatchIdAtTime factory method")
    class ForTableWithBatchIdAtTime {

        @Test
        void shouldCreateRequestWithCorrectTableId() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String expectedTableId = tableProperties.get(TABLE_ID);

            // When
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T10:00:00Z"));

            // Then
            assertThat(request.getTableId()).isEqualTo(expectedTableId);
        }

        @Test
        void shouldCreateRequestWithCorrectBatchKey() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String tableId = tableProperties.get(TABLE_ID);
            String batchId = "my-batch-id";

            // When
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, batchId, Instant.parse("2024-01-01T10:00:00Z"));

            // Then
            assertThat(request.getBatchKey()).isEqualTo(tableId + "/compactions/" + batchId + ".json");
        }

        @Test
        void shouldCreateRequestWithCorrectCreateTime() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-06-15T14:30:00Z");

            // When
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-456", createTime);

            // Then
            assertThat(request.getCreateTime()).isEqualTo(createTime);
        }

        @Test
        void shouldCreateDifferentBatchKeysForDifferentBatchIds() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-01-01T00:00:00Z");

            // When
            CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-aaa", createTime);
            CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-bbb", createTime);

            // Then
            assertThat(request1.getBatchKey()).isNotEqualTo(request2.getBatchKey());
        }

        @Test
        void shouldCreateDifferentTableIdsForDifferentTables() {
            // Given
            TableProperties tableProperties1 = createTestTableProperties(instanceProperties, schema);
            TableProperties tableProperties2 = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-01-01T00:00:00Z");

            // When
            CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties1, "batch-123", createTime);
            CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties2, "batch-123", createTime);

            // Then
            assertThat(request1.getTableId()).isNotEqualTo(request2.getTableId());
        }
    }

    @Nested
    @DisplayName("getTableId")
    class GetTableId {

        @Test
        void shouldReturnTableIdFromTableProperties() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String expectedTableId = tableProperties.get(TABLE_ID);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-id", Instant.now());

            // When
            String actualTableId = request.getTableId();

            // Then
            assertThat(actualTableId).isEqualTo(expectedTableId);
        }
    }

    @Nested
    @DisplayName("getBatchKey")
    class GetBatchKey {

        @Test
        void shouldReturnBatchKeyWithCorrectFormat() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String tableId = tableProperties.get(TABLE_ID);
            String batchId = "test-batch";
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, batchId, Instant.now());

            // When
            String batchKey = request.getBatchKey();

            // Then
            assertThat(batchKey).isEqualTo(tableId + "/compactions/" + batchId + ".json");
        }

        @Test
        void shouldIncludeJsonExtension() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch", Instant.now());

            // When
            String batchKey = request.getBatchKey();

            // Then
            assertThat(batchKey).endsWith(".json");
        }

        @Test
        void shouldIncludeCompactionsPathSegment() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch", Instant.now());

            // When
            String batchKey = request.getBatchKey();

            // Then
            assertThat(batchKey).contains("/compactions/");
        }
    }

    @Nested
    @DisplayName("getCreateTime")
    class GetCreateTime {

        @Test
        void shouldReturnExactCreateTime() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-03-20T08:45:30Z");
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch", createTime);

            // When
            Instant actualCreateTime = request.getCreateTime();

            // Then
            assertThat(actualCreateTime).isEqualTo(createTime);
        }

        @Test
        void shouldHandleEpochTime() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant epochTime = Instant.EPOCH;
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch", epochTime);

            // When
            Instant actualCreateTime = request.getCreateTime();

            // Then
            assertThat(actualCreateTime).isEqualTo(Instant.EPOCH);
        }

        @Test
        void shouldHandleFarFutureTime() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant futureTime = Instant.parse("2099-12-31T23:59:59Z");
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch", futureTime);

            // When
            Instant actualCreateTime = request.getCreateTime();

            // Then
            assertThat(actualCreateTime).isEqualTo(futureTime);
        }
    }

    @Nested
    @DisplayName("hashCode")
    class HashCode {

        @Test
        void shouldReturnSameHashCodeForEqualObjects() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-01-01T00:00:00Z");
            CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", createTime);
            CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", createTime);

            // When/Then
            assertThat(request1.hashCode()).isEqualTo(request2.hashCode());
        }

        @Test
        void shouldReturnConsistentHashCode() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T00:00:00Z"));

            // When
            int hashCode1 = request.hashCode();
            int hashCode2 = request.hashCode();

            // Then
            assertThat(hashCode1).isEqualTo(hashCode2);
        }

        @Test
        void shouldReturnDifferentHashCodeForDifferentTableIds() {
            // Given
            TableProperties tableProperties1 = createTestTableProperties(instanceProperties, schema);
            TableProperties tableProperties2 = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-01-01T00:00:00Z");
            CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties1, "batch-123", createTime);
            CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties2, "batch-123", createTime);

            // When/Then - different table IDs should generally produce different hash codes
            assertThat(request1.hashCode()).isNotEqualTo(request2.hashCode());
        }

        @Test
        void shouldReturnDifferentHashCodeForDifferentBatchKeys() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-01-01T00:00:00Z");
            CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-aaa", createTime);
            CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-bbb", createTime);

            // When/Then
            assertThat(request1.hashCode()).isNotEqualTo(request2.hashCode());
        }

        @Test
        void shouldReturnDifferentHashCodeForDifferentCreateTimes() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T00:00:00Z"));
            CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-02T00:00:00Z"));

            // When/Then
            assertThat(request1.hashCode()).isNotEqualTo(request2.hashCode());
        }
    }

    @Nested
    @DisplayName("equals")
    class Equals {

        @Test
        void shouldBeEqualToItself() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T00:00:00Z"));

            // When/Then
            assertThat(request).isEqualTo(request);
        }

        @Test
        void shouldBeEqualToObjectWithSameValues() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-01-01T00:00:00Z");
            CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", createTime);
            CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", createTime);

            // When/Then
            assertThat(request1).isEqualTo(request2);
            assertThat(request2).isEqualTo(request1);
        }

        @Test
        void shouldNotBeEqualToNull() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T00:00:00Z"));

            // When/Then
            assertThat(request).isNotEqualTo(null);
        }

        @Test
        void shouldNotBeEqualToDifferentType() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T00:00:00Z"));

            // When/Then
            assertThat(request).isNotEqualTo("not a request");
            assertThat(request).isNotEqualTo(42);
        }

        @Test
        void shouldNotBeEqualWhenTableIdDiffers() {
            // Given
            TableProperties tableProperties1 = createTestTableProperties(instanceProperties, schema);
            TableProperties tableProperties2 = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-01-01T00:00:00Z");
            CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties1, "batch-123", createTime);
            CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties2, "batch-123", createTime);

            // When/Then
            assertThat(request1).isNotEqualTo(request2);
        }

        @Test
        void shouldNotBeEqualWhenBatchKeyDiffers() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-01-01T00:00:00Z");
            CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-aaa", createTime);
            CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-bbb", createTime);

            // When/Then
            assertThat(request1).isNotEqualTo(request2);
        }

        @Test
        void shouldNotBeEqualWhenCreateTimeDiffers() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T00:00:00Z"));
            CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-02T00:00:00Z"));

            // When/Then
            assertThat(request1).isNotEqualTo(request2);
        }
    }

    @Nested
    @DisplayName("toString")
    class ToString {

        @Test
        void shouldContainClassName() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T00:00:00Z"));

            // When
            String toString = request.toString();

            // Then
            assertThat(toString).startsWith("CompactionJobDispatchRequest{");
        }

        @Test
        void shouldContainTableId() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String tableId = tableProperties.get(TABLE_ID);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T00:00:00Z"));

            // When
            String toString = request.toString();

            // Then
            assertThat(toString).contains("tableId=" + tableId);
        }

        @Test
        void shouldContainBatchKey() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T00:00:00Z"));
            String batchKey = request.getBatchKey();

            // When
            String toString = request.toString();

            // Then
            assertThat(toString).contains("batchKey=" + batchKey);
        }

        @Test
        void shouldContainCreateTime() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant createTime = Instant.parse("2024-06-15T14:30:00Z");
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", createTime);

            // When
            String toString = request.toString();

            // Then
            assertThat(toString).contains("createTime=" + createTime);
        }

        @Test
        void shouldHaveCorrectFormat() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String tableId = tableProperties.get(TABLE_ID);
            String batchId = "batch-format-test";
            Instant createTime = Instant.parse("2024-01-01T12:00:00Z");
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, batchId, createTime);
            String batchKey = request.getBatchKey();

            // When
            String toString = request.toString();

            // Then
            String expected = "CompactionJobDispatchRequest{tableId=" + tableId +
                    ", batchKey=" + batchKey + ", createTime=" + createTime + "}";
            assertThat(toString).isEqualTo(expected);
        }
    }
}
