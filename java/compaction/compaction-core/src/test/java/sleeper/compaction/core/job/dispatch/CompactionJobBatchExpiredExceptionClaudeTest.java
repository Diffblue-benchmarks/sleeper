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

public class CompactionJobBatchExpiredExceptionClaudeTest {

    private final Schema schema = schemaWithKey("key", new StringType());
    private final InstanceProperties instanceProperties = createTestInstanceProperties();

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Test
        void shouldCreateExceptionWithCorrectMessage() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String batchId = "test-batch-id";
            Instant createTime = Instant.parse("2024-06-01T10:00:00Z");
            Instant expiryTime = Instant.parse("2024-06-01T10:30:00Z");
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, batchId, createTime);

            // When
            CompactionJobBatchExpiredException exception = new CompactionJobBatchExpiredException(request, expiryTime);

            // Then
            assertThat(exception.getMessage())
                    .contains("Dispatch request for table " + tableProperties.get(TABLE_ID))
                    .contains("expired at " + expiryTime)
                    .contains("batch key: " + request.getBatchKey());
        }

        @Test
        void shouldIncludeTableIdInMessage() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String tableId = tableProperties.get(TABLE_ID);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-123", Instant.parse("2024-01-01T00:00:00Z"));
            Instant expiryTime = Instant.parse("2024-01-01T01:00:00Z");

            // When
            CompactionJobBatchExpiredException exception = new CompactionJobBatchExpiredException(request, expiryTime);

            // Then
            assertThat(exception.getMessage()).contains(tableId);
        }

        @Test
        void shouldIncludeExpiryTimeInMessage() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-456", Instant.parse("2024-03-15T08:00:00Z"));
            Instant expiryTime = Instant.parse("2024-03-15T08:15:00Z");

            // When
            CompactionJobBatchExpiredException exception = new CompactionJobBatchExpiredException(request, expiryTime);

            // Then
            assertThat(exception.getMessage()).contains("2024-03-15T08:15:00Z");
        }

        @Test
        void shouldIncludeBatchKeyInMessage() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String batchId = "unique-batch-789";
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, batchId, Instant.parse("2024-07-20T14:00:00Z"));
            Instant expiryTime = Instant.parse("2024-07-20T14:30:00Z");

            // When
            CompactionJobBatchExpiredException exception = new CompactionJobBatchExpiredException(request, expiryTime);

            // Then
            assertThat(exception.getMessage()).contains(request.getBatchKey());
        }

        @Test
        void shouldBeRuntimeException() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-id", Instant.parse("2024-01-01T00:00:00Z"));
            Instant expiryTime = Instant.parse("2024-01-01T00:30:00Z");

            // When
            CompactionJobBatchExpiredException exception = new CompactionJobBatchExpiredException(request, expiryTime);

            // Then
            assertThat(exception).isInstanceOf(RuntimeException.class);
        }

        @Test
        void shouldBeThrowable() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-id", Instant.parse("2024-01-01T00:00:00Z"));
            Instant expiryTime = Instant.parse("2024-01-01T00:30:00Z");

            // When/Then
            org.junit.jupiter.api.Assertions.assertThrows(
                    CompactionJobBatchExpiredException.class,
                    () -> {
                        throw new CompactionJobBatchExpiredException(request, expiryTime);
                    });
        }

        @Test
        void shouldFormatMessageCorrectly() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String tableId = tableProperties.get(TABLE_ID);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-format-test", Instant.parse("2024-02-28T12:00:00Z"));
            Instant expiryTime = Instant.parse("2024-02-28T12:45:00Z");
            String batchKey = request.getBatchKey();

            // When
            CompactionJobBatchExpiredException exception = new CompactionJobBatchExpiredException(request, expiryTime);

            // Then
            String expectedMessage = "Dispatch request for table " + tableId + " expired at " + expiryTime + ", batch key: " + batchKey;
            assertThat(exception.getMessage()).isEqualTo(expectedMessage);
        }

        @Test
        void shouldHandleDifferentExpiryTimes() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                    tableProperties, "batch-id", Instant.parse("2024-01-01T00:00:00Z"));

            // When - test with various expiry times
            Instant earlyExpiry = Instant.parse("2020-01-01T00:00:00Z");
            Instant midExpiry = Instant.parse("2024-06-15T12:30:45Z");
            Instant lateExpiry = Instant.parse("2030-12-31T23:59:59Z");

            CompactionJobBatchExpiredException exception1 = new CompactionJobBatchExpiredException(request, earlyExpiry);
            CompactionJobBatchExpiredException exception2 = new CompactionJobBatchExpiredException(request, midExpiry);
            CompactionJobBatchExpiredException exception3 = new CompactionJobBatchExpiredException(request, lateExpiry);

            // Then
            assertThat(exception1.getMessage()).contains("2020-01-01T00:00:00Z");
            assertThat(exception2.getMessage()).contains("2024-06-15T12:30:45Z");
            assertThat(exception3.getMessage()).contains("2030-12-31T23:59:59Z");
        }
    }
}
