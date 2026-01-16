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

public class CompactionJobDispatchRequestSerDeClaudeTest {

    private static final String BATCH_ID = "test-batch-123";
    private static final Instant CREATE_TIME = Instant.parse("2024-06-15T14:30:00Z");

    private final Schema schema = schemaWithKey("key", new StringType());
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final CompactionJobDispatchRequestSerDe serDe = new CompactionJobDispatchRequestSerDe();

    private CompactionJobDispatchRequest createRequest(TableProperties tableProperties, String batchId, Instant createTime) {
        return CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties, batchId, createTime);
    }

    private CompactionJobDispatchRequest createDefaultRequest() {
        TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
        return createRequest(tableProperties, BATCH_ID, CREATE_TIME);
    }

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Test
        void shouldCreateSerDeInstance() {
            // Given/When
            CompactionJobDispatchRequestSerDe serDe = new CompactionJobDispatchRequestSerDe();

            // Then
            assertThat(serDe).isNotNull();
        }
    }

    @Nested
    @DisplayName("toJson")
    class ToJson {

        @Test
        void shouldSerializeRequestWithTableId() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String expectedTableId = tableProperties.get(TABLE_ID);
            CompactionJobDispatchRequest request = createRequest(tableProperties, BATCH_ID, CREATE_TIME);

            // When
            String json = serDe.toJson(request);

            // Then
            assertThat(json).contains("\"tableId\":\"" + expectedTableId + "\"");
        }

        @Test
        void shouldSerializeRequestWithBatchKey() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = createRequest(tableProperties, BATCH_ID, CREATE_TIME);
            String expectedBatchKey = request.getBatchKey();

            // When
            String json = serDe.toJson(request);

            // Then
            assertThat(json).contains("\"batchKey\":\"" + expectedBatchKey + "\"");
        }

        @Test
        void shouldSerializeRequestWithCreateTime() {
            // Given
            CompactionJobDispatchRequest request = createDefaultRequest();

            // When
            String json = serDe.toJson(request);

            // Then
            // GsonConfig uses epoch milliseconds for Instant
            assertThat(json).contains("\"createTime\":");
        }

        @Test
        void shouldProduceCompactJson() {
            // Given
            CompactionJobDispatchRequest request = createDefaultRequest();

            // When
            String json = serDe.toJson(request);

            // Then - compact JSON should not have newlines
            assertThat(json).doesNotContain("\n");
        }

        @Test
        void shouldSerializeEpochTime() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request = createRequest(tableProperties, BATCH_ID, Instant.EPOCH);

            // When
            String json = serDe.toJson(request);

            // Then - epoch time should serialize as 0 milliseconds
            assertThat(json).contains("\"createTime\":0");
        }

        @Test
        void shouldSerializeMillisecondPrecision() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant timeWithMillis = Instant.parse("2024-01-01T12:00:00.123Z");
            CompactionJobDispatchRequest request = createRequest(tableProperties, BATCH_ID, timeWithMillis);

            // When
            String json = serDe.toJson(request);

            // Then
            assertThat(json).contains("\"createTime\":" + timeWithMillis.toEpochMilli());
        }
    }

    @Nested
    @DisplayName("toJsonPrettyPrint")
    class ToJsonPrettyPrint {

        @Test
        void shouldSerializeRequestWithPrettyPrinting() {
            // Given
            CompactionJobDispatchRequest request = createDefaultRequest();

            // When
            String json = serDe.toJsonPrettyPrint(request);

            // Then - pretty print should have newlines and indentation
            assertThat(json).contains("\n");
            assertThat(json).contains("  ");
        }

        @Test
        void shouldContainAllFieldsWhenPrettyPrinted() {
            // Given
            CompactionJobDispatchRequest request = createDefaultRequest();

            // When
            String json = serDe.toJsonPrettyPrint(request);

            // Then
            assertThat(json).contains("tableId");
            assertThat(json).contains("batchKey");
            assertThat(json).contains("createTime");
        }

        @Test
        void shouldBeDifferentFromCompactJson() {
            // Given
            CompactionJobDispatchRequest request = createDefaultRequest();

            // When
            String compactJson = serDe.toJson(request);
            String prettyJson = serDe.toJsonPrettyPrint(request);

            // Then
            assertThat(prettyJson).isNotEqualTo(compactJson);
            assertThat(prettyJson.length()).isGreaterThan(compactJson.length());
        }

        @Test
        void shouldSerializeTableIdWithPrettyPrinting() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String expectedTableId = tableProperties.get(TABLE_ID);
            CompactionJobDispatchRequest request = createRequest(tableProperties, BATCH_ID, CREATE_TIME);

            // When
            String json = serDe.toJsonPrettyPrint(request);

            // Then
            assertThat(json).contains("\"tableId\": \"" + expectedTableId + "\"");
        }
    }

    @Nested
    @DisplayName("fromJson")
    class FromJson {

        @Test
        void shouldDeserializeRequestWithCorrectTableId() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String expectedTableId = tableProperties.get(TABLE_ID);
            CompactionJobDispatchRequest originalRequest = createRequest(tableProperties, BATCH_ID, CREATE_TIME);
            String json = serDe.toJson(originalRequest);

            // When
            CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(json);

            // Then
            assertThat(deserializedRequest.getTableId()).isEqualTo(expectedTableId);
        }

        @Test
        void shouldDeserializeRequestWithCorrectBatchKey() {
            // Given
            CompactionJobDispatchRequest originalRequest = createDefaultRequest();
            String expectedBatchKey = originalRequest.getBatchKey();
            String json = serDe.toJson(originalRequest);

            // When
            CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(json);

            // Then
            assertThat(deserializedRequest.getBatchKey()).isEqualTo(expectedBatchKey);
        }

        @Test
        void shouldDeserializeRequestWithCorrectCreateTime() {
            // Given
            CompactionJobDispatchRequest originalRequest = createDefaultRequest();
            String json = serDe.toJson(originalRequest);

            // When
            CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(json);

            // Then
            assertThat(deserializedRequest.getCreateTime()).isEqualTo(CREATE_TIME);
        }

        @Test
        void shouldDeserializeFromPrettyPrintedJson() {
            // Given
            CompactionJobDispatchRequest originalRequest = createDefaultRequest();
            String prettyJson = serDe.toJsonPrettyPrint(originalRequest);

            // When
            CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(prettyJson);

            // Then
            assertThat(deserializedRequest).isEqualTo(originalRequest);
        }

        @Test
        void shouldDeserializeEpochTime() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest originalRequest = createRequest(tableProperties, BATCH_ID, Instant.EPOCH);
            String json = serDe.toJson(originalRequest);

            // When
            CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(json);

            // Then
            assertThat(deserializedRequest.getCreateTime()).isEqualTo(Instant.EPOCH);
        }

        @Test
        void shouldDeserializeFarFutureTime() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant futureTime = Instant.parse("2099-12-31T23:59:59Z");
            CompactionJobDispatchRequest originalRequest = createRequest(tableProperties, BATCH_ID, futureTime);
            String json = serDe.toJson(originalRequest);

            // When
            CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(json);

            // Then
            assertThat(deserializedRequest.getCreateTime()).isEqualTo(futureTime);
        }

        @Test
        void shouldDeserializeMillisecondPrecision() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant timeWithMillis = Instant.parse("2024-01-01T12:00:00.456Z");
            CompactionJobDispatchRequest originalRequest = createRequest(tableProperties, BATCH_ID, timeWithMillis);
            String json = serDe.toJson(originalRequest);

            // When
            CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(json);

            // Then
            assertThat(deserializedRequest.getCreateTime()).isEqualTo(timeWithMillis);
        }
    }

    @Nested
    @DisplayName("Round-trip serialization")
    class RoundTrip {

        @Test
        void shouldRoundTripThroughCompactJson() {
            // Given
            CompactionJobDispatchRequest originalRequest = createDefaultRequest();

            // When
            String json = serDe.toJson(originalRequest);
            CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(json);

            // Then
            assertThat(deserializedRequest).isEqualTo(originalRequest);
        }

        @Test
        void shouldRoundTripThroughPrettyPrintJson() {
            // Given
            CompactionJobDispatchRequest originalRequest = createDefaultRequest();

            // When
            String json = serDe.toJsonPrettyPrint(originalRequest);
            CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(json);

            // Then
            assertThat(deserializedRequest).isEqualTo(originalRequest);
        }

        @Test
        void shouldRoundTripWithDifferentBatchIds() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request1 = createRequest(tableProperties, "batch-aaa", CREATE_TIME);
            CompactionJobDispatchRequest request2 = createRequest(tableProperties, "batch-bbb", CREATE_TIME);

            // When
            String json1 = serDe.toJson(request1);
            String json2 = serDe.toJson(request2);
            CompactionJobDispatchRequest deserialized1 = serDe.fromJson(json1);
            CompactionJobDispatchRequest deserialized2 = serDe.fromJson(json2);

            // Then
            assertThat(deserialized1).isEqualTo(request1);
            assertThat(deserialized2).isEqualTo(request2);
            assertThat(deserialized1).isNotEqualTo(deserialized2);
        }

        @Test
        void shouldRoundTripWithDifferentTableIds() {
            // Given
            TableProperties tableProperties1 = createTestTableProperties(instanceProperties, schema);
            TableProperties tableProperties2 = createTestTableProperties(instanceProperties, schema);
            CompactionJobDispatchRequest request1 = createRequest(tableProperties1, BATCH_ID, CREATE_TIME);
            CompactionJobDispatchRequest request2 = createRequest(tableProperties2, BATCH_ID, CREATE_TIME);

            // When
            String json1 = serDe.toJson(request1);
            String json2 = serDe.toJson(request2);
            CompactionJobDispatchRequest deserialized1 = serDe.fromJson(json1);
            CompactionJobDispatchRequest deserialized2 = serDe.fromJson(json2);

            // Then
            assertThat(deserialized1).isEqualTo(request1);
            assertThat(deserialized2).isEqualTo(request2);
            assertThat(deserialized1.getTableId()).isNotEqualTo(deserialized2.getTableId());
        }

        @Test
        void shouldRoundTripWithDifferentCreateTimes() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            Instant time1 = Instant.parse("2024-01-01T00:00:00Z");
            Instant time2 = Instant.parse("2024-06-15T12:30:45Z");
            CompactionJobDispatchRequest request1 = createRequest(tableProperties, BATCH_ID, time1);
            CompactionJobDispatchRequest request2 = createRequest(tableProperties, BATCH_ID, time2);

            // When
            String json1 = serDe.toJson(request1);
            String json2 = serDe.toJson(request2);
            CompactionJobDispatchRequest deserialized1 = serDe.fromJson(json1);
            CompactionJobDispatchRequest deserialized2 = serDe.fromJson(json2);

            // Then
            assertThat(deserialized1.getCreateTime()).isEqualTo(time1);
            assertThat(deserialized2.getCreateTime()).isEqualTo(time2);
        }

        @Test
        void shouldPreserveAllFieldsAfterRoundTrip() {
            // Given
            TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
            String expectedTableId = tableProperties.get(TABLE_ID);
            String batchId = "complex-batch-id-with-dashes";
            Instant createTime = Instant.parse("2024-03-20T08:45:30.123Z");
            CompactionJobDispatchRequest originalRequest = createRequest(tableProperties, batchId, createTime);

            // When
            String json = serDe.toJson(originalRequest);
            CompactionJobDispatchRequest deserializedRequest = serDe.fromJson(json);

            // Then
            assertThat(deserializedRequest.getTableId()).isEqualTo(expectedTableId);
            assertThat(deserializedRequest.getBatchKey()).isEqualTo(originalRequest.getBatchKey());
            assertThat(deserializedRequest.getCreateTime()).isEqualTo(createTime);
        }

        @Test
        void shouldRoundTripMultipleTimes() {
            // Given
            CompactionJobDispatchRequest originalRequest = createDefaultRequest();

            // When - serialize and deserialize multiple times
            String json1 = serDe.toJson(originalRequest);
            CompactionJobDispatchRequest round1 = serDe.fromJson(json1);
            String json2 = serDe.toJson(round1);
            CompactionJobDispatchRequest round2 = serDe.fromJson(json2);
            String json3 = serDe.toJsonPrettyPrint(round2);
            CompactionJobDispatchRequest round3 = serDe.fromJson(json3);

            // Then
            assertThat(round1).isEqualTo(originalRequest);
            assertThat(round2).isEqualTo(originalRequest);
            assertThat(round3).isEqualTo(originalRequest);
        }
    }

    @Nested
    @DisplayName("JSON format consistency")
    class JsonFormatConsistency {

        @Test
        void shouldProduceDeterministicJson() {
            // Given
            CompactionJobDispatchRequest request = createDefaultRequest();

            // When
            String json1 = serDe.toJson(request);
            String json2 = serDe.toJson(request);

            // Then
            assertThat(json1).isEqualTo(json2);
        }

        @Test
        void shouldProduceDeterministicPrettyPrintJson() {
            // Given
            CompactionJobDispatchRequest request = createDefaultRequest();

            // When
            String json1 = serDe.toJsonPrettyPrint(request);
            String json2 = serDe.toJsonPrettyPrint(request);

            // Then
            assertThat(json1).isEqualTo(json2);
        }

        @Test
        void shouldDeserializeBothFormatsToSameObject() {
            // Given
            CompactionJobDispatchRequest originalRequest = createDefaultRequest();
            String compactJson = serDe.toJson(originalRequest);
            String prettyJson = serDe.toJsonPrettyPrint(originalRequest);

            // When
            CompactionJobDispatchRequest fromCompact = serDe.fromJson(compactJson);
            CompactionJobDispatchRequest fromPretty = serDe.fromJson(prettyJson);

            // Then
            assertThat(fromCompact).isEqualTo(fromPretty);
            assertThat(fromCompact).isEqualTo(originalRequest);
        }
    }
}
