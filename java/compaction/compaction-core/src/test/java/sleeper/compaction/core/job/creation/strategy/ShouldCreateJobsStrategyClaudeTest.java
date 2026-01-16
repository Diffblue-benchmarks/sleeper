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
package sleeper.compaction.core.job.creation.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.FileReference;
import sleeper.core.table.TableStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.table.TableStatusTestHelper.uniqueIdAndName;

class ShouldCreateJobsStrategyClaudeTest {

    private static final Instant DEFAULT_UPDATE_TIME = Instant.parse("2024-06-01T10:00:00Z");
    private final TableStatus tableStatus = uniqueIdAndName("test-table-id", "test-table");

    @Nested
    @DisplayName("Default init method")
    class DefaultInitMethod {

        @Test
        void shouldNotThrowWhenCalledWithValidProperties() {
            // Given
            ShouldCreateJobsStrategy strategy = filesInPartition -> Long.MAX_VALUE;
            InstanceProperties instanceProperties = createTestInstanceProperties();
            TableProperties tableProperties = createTestTableProperties(instanceProperties,
                    schemaWithKey("key", new StringType()));

            // When/Then
            assertThatCode(() -> strategy.init(instanceProperties, tableProperties))
                    .doesNotThrowAnyException();
        }

        @Test
        void shouldNotThrowWhenCalledWithNullInstanceProperties() {
            // Given
            ShouldCreateJobsStrategy strategy = filesInPartition -> Long.MAX_VALUE;
            InstanceProperties instanceProperties = createTestInstanceProperties();
            TableProperties tableProperties = createTestTableProperties(instanceProperties,
                    schemaWithKey("key", new StringType()));

            // When/Then
            assertThatCode(() -> strategy.init(null, tableProperties))
                    .doesNotThrowAnyException();
        }

        @Test
        void shouldNotThrowWhenCalledWithNullTableProperties() {
            // Given
            ShouldCreateJobsStrategy strategy = filesInPartition -> Long.MAX_VALUE;
            InstanceProperties instanceProperties = createTestInstanceProperties();

            // When/Then
            assertThatCode(() -> strategy.init(instanceProperties, null))
                    .doesNotThrowAnyException();
        }

        @Test
        void shouldNotThrowWhenCalledWithBothPropertiesNull() {
            // Given
            ShouldCreateJobsStrategy strategy = filesInPartition -> Long.MAX_VALUE;

            // When/Then
            assertThatCode(() -> strategy.init(null, null))
                    .doesNotThrowAnyException();
        }

        @Test
        void shouldBeAbleToCallInitMultipleTimes() {
            // Given
            ShouldCreateJobsStrategy strategy = filesInPartition -> Long.MAX_VALUE;
            InstanceProperties instanceProperties = createTestInstanceProperties();
            TableProperties tableProperties = createTestTableProperties(instanceProperties,
                    schemaWithKey("key", new StringType()));

            // When/Then
            assertThatCode(() -> {
                strategy.init(instanceProperties, tableProperties);
                strategy.init(instanceProperties, tableProperties);
                strategy.init(instanceProperties, tableProperties);
            }).doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("Static yes() factory method")
    class YesFactoryMethod {

        @Test
        void shouldReturnNonNullStrategy() {
            // When
            ShouldCreateJobsStrategy strategy = ShouldCreateJobsStrategy.yes();

            // Then
            assertThat(strategy).isNotNull();
        }

        @Test
        void shouldReturnMaxValueForEmptyPartition() {
            // Given
            ShouldCreateJobsStrategy strategy = ShouldCreateJobsStrategy.yes();
            FilesInPartition emptyPartition = new FilesInPartition(
                    tableStatus, "partition-1", new ArrayList<>(), new ArrayList<>());

            // When
            long result = strategy.maxCompactionJobsToCreate(emptyPartition);

            // Then
            assertThat(result).isEqualTo(Long.MAX_VALUE);
        }

        @Test
        void shouldReturnMaxValueForPartitionWithFilesWithoutJobId() {
            // Given
            ShouldCreateJobsStrategy strategy = ShouldCreateJobsStrategy.yes();
            List<FileReference> filesWithNoJobId = List.of(
                    createFileReference("file1.parquet", "partition-1", 100L),
                    createFileReference("file2.parquet", "partition-1", 200L));
            FilesInPartition partition = new FilesInPartition(
                    tableStatus, "partition-1", filesWithNoJobId, new ArrayList<>());

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then
            assertThat(result).isEqualTo(Long.MAX_VALUE);
        }

        @Test
        void shouldReturnMaxValueForPartitionWithFilesWithJobId() {
            // Given
            ShouldCreateJobsStrategy strategy = ShouldCreateJobsStrategy.yes();
            List<FileReference> filesWithJobId = List.of(
                    createFileReferenceWithJobId("file1.parquet", "partition-1", 100L, "job-1"),
                    createFileReferenceWithJobId("file2.parquet", "partition-1", 200L, "job-2"));
            FilesInPartition partition = new FilesInPartition(
                    tableStatus, "partition-1", new ArrayList<>(), filesWithJobId);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then
            assertThat(result).isEqualTo(Long.MAX_VALUE);
        }

        @Test
        void shouldReturnMaxValueForPartitionWithBothFileTypes() {
            // Given
            ShouldCreateJobsStrategy strategy = ShouldCreateJobsStrategy.yes();
            List<FileReference> filesWithNoJobId = List.of(
                    createFileReference("file1.parquet", "partition-1", 100L));
            List<FileReference> filesWithJobId = List.of(
                    createFileReferenceWithJobId("file2.parquet", "partition-1", 200L, "job-1"));
            FilesInPartition partition = new FilesInPartition(
                    tableStatus, "partition-1", filesWithNoJobId, filesWithJobId);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then
            assertThat(result).isEqualTo(Long.MAX_VALUE);
        }

        @Test
        void shouldReturnMaxValueRegardlessOfPartitionId() {
            // Given
            ShouldCreateJobsStrategy strategy = ShouldCreateJobsStrategy.yes();
            FilesInPartition partition1 = new FilesInPartition(
                    tableStatus, "partition-abc", new ArrayList<>(), new ArrayList<>());
            FilesInPartition partition2 = new FilesInPartition(
                    tableStatus, "partition-xyz", new ArrayList<>(), new ArrayList<>());

            // When/Then
            assertThat(strategy.maxCompactionJobsToCreate(partition1)).isEqualTo(Long.MAX_VALUE);
            assertThat(strategy.maxCompactionJobsToCreate(partition2)).isEqualTo(Long.MAX_VALUE);
        }

        @Test
        void shouldReturnSameResultOnMultipleCalls() {
            // Given
            ShouldCreateJobsStrategy strategy = ShouldCreateJobsStrategy.yes();
            FilesInPartition partition = new FilesInPartition(
                    tableStatus, "partition-1", new ArrayList<>(), new ArrayList<>());

            // When
            long result1 = strategy.maxCompactionJobsToCreate(partition);
            long result2 = strategy.maxCompactionJobsToCreate(partition);
            long result3 = strategy.maxCompactionJobsToCreate(partition);

            // Then
            assertThat(result1).isEqualTo(Long.MAX_VALUE);
            assertThat(result2).isEqualTo(Long.MAX_VALUE);
            assertThat(result3).isEqualTo(Long.MAX_VALUE);
        }

        @Test
        void shouldHaveDefaultInitThatDoesNotThrow() {
            // Given
            ShouldCreateJobsStrategy strategy = ShouldCreateJobsStrategy.yes();
            InstanceProperties instanceProperties = createTestInstanceProperties();
            TableProperties tableProperties = createTestTableProperties(instanceProperties,
                    schemaWithKey("key", new StringType()));

            // When/Then
            assertThatCode(() -> strategy.init(instanceProperties, tableProperties))
                    .doesNotThrowAnyException();
        }
    }

    private FileReference createFileReference(String filename, String partitionId, long numberOfRecords) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(partitionId)
                .numberOfRecords(numberOfRecords)
                .lastStateStoreUpdateTime(DEFAULT_UPDATE_TIME)
                .build();
    }

    private FileReference createFileReferenceWithJobId(String filename, String partitionId, long numberOfRecords, String jobId) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(partitionId)
                .numberOfRecords(numberOfRecords)
                .jobId(jobId)
                .lastStateStoreUpdateTime(DEFAULT_UPDATE_TIME)
                .build();
    }
}
