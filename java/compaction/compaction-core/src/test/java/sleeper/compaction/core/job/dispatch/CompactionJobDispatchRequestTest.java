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

import org.junit.jupiter.api.Test;

import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTablePropertiesWithNoSchema;

public class CompactionJobDispatchRequestTest {

    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTablePropertiesWithNoSchema(instanceProperties);

    @Test
    void shouldCreateRequestForTableWithBatchIdAtTime() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "test-table");
        Instant createTime = Instant.parse("2024-11-18T12:01:00Z");

        // When
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", createTime);

        // Then
        assertThat(request.getTableId()).isEqualTo("test-table");
        assertThat(request.getBatchKey()).isEqualTo("test-table/compactions/test-batch.json");
        assertThat(request.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void shouldReturnTableId() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "my-table");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-123", Instant.parse("2024-11-18T12:01:00Z"));

        // When / Then
        assertThat(request.getTableId()).isEqualTo("my-table");
    }

    @Test
    void shouldReturnBatchKey() {
        // Given
        instanceProperties.set(DATA_BUCKET, "my-bucket");
        tableProperties.set(TABLE_ID, "table-456");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-789", Instant.parse("2024-11-18T12:01:00Z"));

        // When / Then
        assertThat(request.getBatchKey()).isEqualTo("table-456/compactions/batch-789.json");
    }

    @Test
    void shouldReturnCreateTime() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "test-table");
        Instant createTime = Instant.parse("2024-11-18T14:30:45Z");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-001", createTime);

        // When / Then
        assertThat(request.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void shouldBeEqualWhenSameObject() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "test-table");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", Instant.parse("2024-11-18T12:01:00Z"));

        // When / Then
        assertThat(request).isEqualTo(request);
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "test-table");
        Instant createTime = Instant.parse("2024-11-18T12:01:00Z");
        CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", createTime);
        CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", createTime);

        // When / Then
        assertThat(request1).isEqualTo(request2);
    }

    @Test
    void shouldNotBeEqualWhenDifferentTableId() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "table-1");
        Instant createTime = Instant.parse("2024-11-18T12:01:00Z");
        CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", createTime);

        tableProperties.set(TABLE_ID, "table-2");
        CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", createTime);

        // When / Then
        assertThat(request1).isNotEqualTo(request2);
    }

    @Test
    void shouldNotBeEqualWhenDifferentBatchKey() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "test-table");
        Instant createTime = Instant.parse("2024-11-18T12:01:00Z");
        CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-1", createTime);
        CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "batch-2", createTime);

        // When / Then
        assertThat(request1).isNotEqualTo(request2);
    }

    @Test
    void shouldNotBeEqualWhenDifferentCreateTime() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "test-table");
        CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", Instant.parse("2024-11-18T12:01:00Z"));
        CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", Instant.parse("2024-11-18T14:30:00Z"));

        // When / Then
        assertThat(request1).isNotEqualTo(request2);
    }

    @Test
    void shouldNotBeEqualWhenComparingWithNull() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "test-table");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", Instant.parse("2024-11-18T12:01:00Z"));

        // When / Then
        assertThat(request).isNotEqualTo(null);
    }

    @Test
    void shouldNotBeEqualWhenComparingWithDifferentType() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "test-table");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", Instant.parse("2024-11-18T12:01:00Z"));

        // When / Then
        assertThat(request).isNotEqualTo("not a CompactionJobDispatchRequest");
    }

    @Test
    void shouldHaveSameHashCodeWhenSameValues() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "test-table");
        Instant createTime = Instant.parse("2024-11-18T12:01:00Z");
        CompactionJobDispatchRequest request1 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", createTime);
        CompactionJobDispatchRequest request2 = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", createTime);

        // When / Then
        assertThat(request1.hashCode()).isEqualTo(request2.hashCode());
    }

    @Test
    void shouldProduceToStringWithAllFields() {
        // Given
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        tableProperties.set(TABLE_ID, "test-table");
        Instant createTime = Instant.parse("2024-11-18T12:01:00Z");
        CompactionJobDispatchRequest request = CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
                tableProperties, "test-batch", createTime);

        // When
        String result = request.toString();

        // Then
        assertThat(result).contains("CompactionJobDispatchRequest");
        assertThat(result).contains("tableId=test-table");
        assertThat(result).contains("batchKey=test-table/compactions/test-batch.json");
        assertThat(result).contains("createTime=2024-11-18T12:01:00Z");
    }
}
