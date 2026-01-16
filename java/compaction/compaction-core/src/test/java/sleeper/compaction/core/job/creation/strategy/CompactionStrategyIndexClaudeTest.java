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
import sleeper.core.partition.Partition;
import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.FileReference;
import sleeper.core.table.TableStatus;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.table.TableStatusTestHelper.uniqueIdAndName;

public class CompactionStrategyIndexClaudeTest {

    private static final Instant DEFAULT_UPDATE_TIME = Instant.parse("2024-06-01T10:00:00Z");
    private final Schema schema = schemaWithKey("key", new StringType());
    private final TableStatus tableStatus = uniqueIdAndName("test-table-id", "test-table");

    @Nested
    @DisplayName("Constructor and getFilesInLeafPartitions")
    class ConstructorAndGetFilesInLeafPartitions {

        @Test
        void shouldReturnEmptyListWhenNoFilesExist() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(),
                    partitions.getAllPartitions());

            // Then
            assertThat(index.getFilesInLeafPartitions()).isEmpty();
        }

        @Test
        void shouldReturnEmptyListWhenAllFilesHaveJobIds() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference fileWithJobId = fileReference("file1.parquet", "root", 100L, "job-1");

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileWithJobId),
                    partitions.getAllPartitions());

            // Then
            assertThat(index.getFilesInLeafPartitions()).isEmpty();
        }

        @Test
        void shouldReturnFilesInLeafPartitionWithoutJobIds() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file1 = fileReference("file1.parquet", "root", 100L);
            FileReference file2 = fileReference("file2.parquet", "root", 200L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file1, file2),
                    partitions.getAllPartitions());

            // Then
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getPartitionId()).isEqualTo("root");
            assertThat(result.get(0).getFilesWithNoJobIdInAscendingOrder())
                    .extracting(FileReference::getFilename)
                    .containsExactly("file1.parquet", "file2.parquet");
            assertThat(result.get(0).getFilesWithJobId()).isEmpty();
        }

        @Test
        void shouldSortFilesWithoutJobIdsByRecordCountAscending() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file1 = fileReference("file1.parquet", "root", 300L);
            FileReference file2 = fileReference("file2.parquet", "root", 100L);
            FileReference file3 = fileReference("file3.parquet", "root", 200L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file1, file2, file3),
                    partitions.getAllPartitions());

            // Then
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getFilesWithNoJobIdInAscendingOrder())
                    .extracting(FileReference::getFilename)
                    .containsExactly("file2.parquet", "file3.parquet", "file1.parquet");
        }

        @Test
        void shouldSeparateFilesWithAndWithoutJobIds() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference fileWithoutJobId = fileReference("file1.parquet", "root", 100L);
            FileReference fileWithJobId = fileReference("file2.parquet", "root", 200L, "job-1");

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileWithoutJobId, fileWithJobId),
                    partitions.getAllPartitions());

            // Then
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getFilesWithNoJobIdInAscendingOrder())
                    .extracting(FileReference::getFilename)
                    .containsExactly("file1.parquet");
            assertThat(result.get(0).getFilesWithJobId())
                    .extracting(FileReference::getFilename)
                    .containsExactly("file2.parquet");
        }

        @Test
        void shouldFilterOutFilesInNonLeafPartitions() {
            // Given - partition tree with a split (root is not a leaf)
            PartitionTree partitions = new PartitionsBuilder(schema)
                    .rootFirst("root")
                    .splitToNewChildren("root", "left", "right", "m")
                    .buildTree();

            FileReference fileInRoot = fileReference("file1.parquet", "root", 100L);
            FileReference fileInLeft = fileReference("file2.parquet", "left", 200L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileInRoot, fileInLeft),
                    partitions.getAllPartitions());

            // Then - only the file in the leaf partition (left) should be included
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getPartitionId()).isEqualTo("left");
            assertThat(result.get(0).getFilesWithNoJobIdInAscendingOrder())
                    .extracting(FileReference::getFilename)
                    .containsExactly("file2.parquet");
        }

        @Test
        void shouldHandleMultipleLeafPartitions() {
            // Given - partition tree with two leaf partitions
            PartitionTree partitions = new PartitionsBuilder(schema)
                    .rootFirst("root")
                    .splitToNewChildren("root", "left", "right", "m")
                    .buildTree();

            FileReference fileInLeft = fileReference("file1.parquet", "left", 100L);
            FileReference fileInRight = fileReference("file2.parquet", "right", 200L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileInLeft, fileInRight),
                    partitions.getAllPartitions());

            // Then
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(2);
            assertThat(result)
                    .extracting(FilesInPartition::getPartitionId)
                    .containsExactlyInAnyOrder("left", "right");
        }

        @Test
        void shouldHandleMultipleFilesInMultipleLeafPartitions() {
            // Given - partition tree with two leaf partitions
            PartitionTree partitions = new PartitionsBuilder(schema)
                    .rootFirst("root")
                    .splitToNewChildren("root", "left", "right", "m")
                    .buildTree();

            FileReference file1InLeft = fileReference("file1.parquet", "left", 100L);
            FileReference file2InLeft = fileReference("file2.parquet", "left", 200L);
            FileReference file1InRight = fileReference("file3.parquet", "right", 300L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file1InLeft, file2InLeft, file1InRight),
                    partitions.getAllPartitions());

            // Then
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(2);

            FilesInPartition leftPartitionFiles = result.stream()
                    .filter(f -> f.getPartitionId().equals("left"))
                    .findFirst().orElseThrow();
            assertThat(leftPartitionFiles.getFilesWithNoJobIdInAscendingOrder())
                    .extracting(FileReference::getFilename)
                    .containsExactly("file1.parquet", "file2.parquet");

            FilesInPartition rightPartitionFiles = result.stream()
                    .filter(f -> f.getPartitionId().equals("right"))
                    .findFirst().orElseThrow();
            assertThat(rightPartitionFiles.getFilesWithNoJobIdInAscendingOrder())
                    .extracting(FileReference::getFilename)
                    .containsExactly("file3.parquet");
        }

        @Test
        void shouldOnlyIncludePartitionsWithFilesWithoutJobIds() {
            // Given - partition tree with two leaf partitions
            PartitionTree partitions = new PartitionsBuilder(schema)
                    .rootFirst("root")
                    .splitToNewChildren("root", "left", "right", "m")
                    .buildTree();

            // Only files with job IDs in left partition
            FileReference fileWithJobIdInLeft = fileReference("file1.parquet", "left", 100L, "job-1");
            // Files without job IDs in right partition
            FileReference fileWithoutJobIdInRight = fileReference("file2.parquet", "right", 200L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileWithJobIdInLeft, fileWithoutJobIdInRight),
                    partitions.getAllPartitions());

            // Then - only right partition should be included
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getPartitionId()).isEqualTo("right");
        }

        @Test
        void shouldSetTableStatusOnFilesInPartition() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file = fileReference("file1.parquet", "root", 100L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file),
                    partitions.getAllPartitions());

            // Then
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getTableStatus()).isEqualTo(tableStatus);
        }

        @Test
        void shouldHandleEmptyPartitionList() {
            // Given - no partitions
            FileReference file = fileReference("file1.parquet", "root", 100L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file),
                    List.of());

            // Then - no files should be indexed since there are no leaf partitions
            assertThat(index.getFilesInLeafPartitions()).isEmpty();
        }

        @Test
        void shouldHandleDeeplyNestedPartitionTree() {
            // Given - deeply nested partition tree
            PartitionTree partitions = new PartitionsBuilder(schema)
                    .rootFirst("root")
                    .splitToNewChildren("root", "level1-left", "level1-right", "m")
                    .splitToNewChildren("level1-left", "level2-left", "level2-right", "f")
                    .buildTree();

            FileReference fileInLevel2Left = fileReference("file1.parquet", "level2-left", 100L);
            FileReference fileInLevel2Right = fileReference("file2.parquet", "level2-right", 200L);
            FileReference fileInLevel1Right = fileReference("file3.parquet", "level1-right", 300L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileInLevel2Left, fileInLevel2Right, fileInLevel1Right),
                    partitions.getAllPartitions());

            // Then - all three partitions are leaf partitions
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(3);
            assertThat(result)
                    .extracting(FilesInPartition::getPartitionId)
                    .containsExactlyInAnyOrder("level2-left", "level2-right", "level1-right");
        }

        @Test
        void shouldHandleMixedFilesWithAndWithoutJobIdsInSamePartition() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file1WithoutJobId = fileReference("file1.parquet", "root", 100L);
            FileReference file2WithJobId = fileReference("file2.parquet", "root", 200L, "job-1");
            FileReference file3WithoutJobId = fileReference("file3.parquet", "root", 300L);
            FileReference file4WithJobId = fileReference("file4.parquet", "root", 400L, "job-2");

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file1WithoutJobId, file2WithJobId, file3WithoutJobId, file4WithJobId),
                    partitions.getAllPartitions());

            // Then
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getFilesWithNoJobIdInAscendingOrder())
                    .extracting(FileReference::getFilename)
                    .containsExactly("file1.parquet", "file3.parquet");
            assertThat(result.get(0).getFilesWithJobId())
                    .extracting(FileReference::getFilename)
                    .containsExactlyInAnyOrder("file2.parquet", "file4.parquet");
        }

        @Test
        void shouldHandleFilesWithSameRecordCount() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file1 = fileReference("file1.parquet", "root", 100L);
            FileReference file2 = fileReference("file2.parquet", "root", 100L);
            FileReference file3 = fileReference("file3.parquet", "root", 100L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file1, file2, file3),
                    partitions.getAllPartitions());

            // Then - all files should be included, order is stable due to sorting
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getFilesWithNoJobIdInAscendingOrder())
                    .extracting(FileReference::getFilename)
                    .containsExactlyInAnyOrder("file1.parquet", "file2.parquet", "file3.parquet");
        }

        @Test
        void shouldFilterFilesByLeafPartitionIds() {
            // Given - file references for non-existent partitions
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference fileInRoot = fileReference("file1.parquet", "root", 100L);
            FileReference fileInNonExistent = fileReference("file2.parquet", "nonexistent", 200L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileInRoot, fileInNonExistent),
                    partitions.getAllPartitions());

            // Then - only the file in the actual leaf partition should be included
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getPartitionId()).isEqualTo("root");
            assertThat(result.get(0).getFilesWithNoJobIdInAscendingOrder())
                    .extracting(FileReference::getFilename)
                    .containsExactly("file1.parquet");
        }
    }

    @Nested
    @DisplayName("FilesInPartition")
    class FilesInPartitionTests {

        @Test
        void shouldHaveCorrectEqualsAndHashCode() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file1 = fileReference("file1.parquet", "root", 100L);
            FileReference file2 = fileReference("file2.parquet", "root", 200L, "job-1");

            // When
            CompactionStrategyIndex index1 = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file1, file2),
                    partitions.getAllPartitions());
            CompactionStrategyIndex index2 = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file1, file2),
                    partitions.getAllPartitions());

            // Then
            assertThat(index1.getFilesInLeafPartitions())
                    .isEqualTo(index2.getFilesInLeafPartitions());
            assertThat(index1.getFilesInLeafPartitions().get(0).hashCode())
                    .isEqualTo(index2.getFilesInLeafPartitions().get(0).hashCode());
        }

        @Test
        void shouldNotBeEqualWithDifferentPartitionIds() {
            // Given
            PartitionTree partitions1 = new PartitionsBuilder(schema).singlePartition("root1").buildTree();
            PartitionTree partitions2 = new PartitionsBuilder(schema).singlePartition("root2").buildTree();
            FileReference file1 = fileReference("file1.parquet", "root1", 100L);
            FileReference file2 = fileReference("file1.parquet", "root2", 100L);

            // When
            CompactionStrategyIndex index1 = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file1),
                    partitions1.getAllPartitions());
            CompactionStrategyIndex index2 = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file2),
                    partitions2.getAllPartitions());

            // Then
            assertThat(index1.getFilesInLeafPartitions())
                    .isNotEqualTo(index2.getFilesInLeafPartitions());
        }

        @Test
        void shouldNotBeEqualWithDifferentFilesWithNoJobId() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file1 = fileReference("file1.parquet", "root", 100L);
            FileReference file2 = fileReference("file2.parquet", "root", 200L);

            // When
            CompactionStrategyIndex index1 = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file1),
                    partitions.getAllPartitions());
            CompactionStrategyIndex index2 = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file2),
                    partitions.getAllPartitions());

            // Then
            assertThat(index1.getFilesInLeafPartitions())
                    .isNotEqualTo(index2.getFilesInLeafPartitions());
        }

        @Test
        void shouldNotBeEqualWithDifferentFilesWithJobId() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference fileWithoutJobId = fileReference("file1.parquet", "root", 100L);
            FileReference file1WithJobId = fileReference("file2.parquet", "root", 200L, "job-1");
            FileReference file2WithJobId = fileReference("file3.parquet", "root", 300L, "job-2");

            // When
            CompactionStrategyIndex index1 = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileWithoutJobId, file1WithJobId),
                    partitions.getAllPartitions());
            CompactionStrategyIndex index2 = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileWithoutJobId, file2WithJobId),
                    partitions.getAllPartitions());

            // Then
            assertThat(index1.getFilesInLeafPartitions())
                    .isNotEqualTo(index2.getFilesInLeafPartitions());
        }

        @Test
        void shouldNotBeEqualWithDifferentTableStatus() {
            // Given
            TableStatus tableStatus1 = uniqueIdAndName("table-1", "table-name-1");
            TableStatus tableStatus2 = uniqueIdAndName("table-2", "table-name-2");
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file = fileReference("file1.parquet", "root", 100L);

            // When
            CompactionStrategyIndex index1 = new CompactionStrategyIndex(
                    tableStatus1,
                    List.of(file),
                    partitions.getAllPartitions());
            CompactionStrategyIndex index2 = new CompactionStrategyIndex(
                    tableStatus2,
                    List.of(file),
                    partitions.getAllPartitions());

            // Then
            assertThat(index1.getFilesInLeafPartitions())
                    .isNotEqualTo(index2.getFilesInLeafPartitions());
        }

        @Test
        void shouldHaveCorrectToString() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file = fileReference("file1.parquet", "root", 100L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file),
                    partitions.getAllPartitions());

            // Then
            String toStringResult = index.getFilesInLeafPartitions().get(0).toString();
            assertThat(toStringResult).contains("FilesInPartition");
            assertThat(toStringResult).contains("partitionId=root");
            assertThat(toStringResult).contains("tableStatus=test-table");
        }

        @Test
        void shouldBeEqualToItself() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file = fileReference("file1.parquet", "root", 100L);
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file),
                    partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // Then - reflexive property of equals
            assertThat(filesInPartition.equals(filesInPartition)).isTrue();
        }

        @Test
        void shouldNotBeEqualToNull() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file = fileReference("file1.parquet", "root", 100L);
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file),
                    partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // Then
            assertThat(filesInPartition.equals(null)).isFalse();
        }

        @Test
        void shouldNotBeEqualToDifferentType() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference file = fileReference("file1.parquet", "root", 100L);
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(file),
                    partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // Then
            assertThat(filesInPartition.equals("a string")).isFalse();
        }

        @Test
        void shouldReturnCorrectAccessors() {
            // Given
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            FileReference fileWithoutJobId = fileReference("file1.parquet", "root", 100L);
            FileReference fileWithJobId = fileReference("file2.parquet", "root", 200L, "job-1");

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileWithoutJobId, fileWithJobId),
                    partitions.getAllPartitions());

            // Then
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);
            assertThat(filesInPartition.getPartitionId()).isEqualTo("root");
            assertThat(filesInPartition.getTableStatus()).isEqualTo(tableStatus);
            assertThat(filesInPartition.getFilesWithNoJobIdInAscendingOrder())
                    .containsExactly(fileWithoutJobId);
            assertThat(filesInPartition.getFilesWithJobId())
                    .containsExactly(fileWithJobId);
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {

        @Test
        void shouldHandlePartitionWithNoLeafPartitions() {
            // Given - a non-leaf partition that has been split
            List<Partition> partitions = new PartitionsBuilder(schema)
                    .rootFirst("root")
                    .splitToNewChildren("root", "left", "right", "m")
                    .buildList();

            // All files in non-leaf partition
            FileReference fileInRoot = fileReference("file1.parquet", "root", 100L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileInRoot),
                    partitions);

            // Then - root is no longer a leaf, so no files should be returned
            assertThat(index.getFilesInLeafPartitions()).isEmpty();
        }

        @Test
        void shouldPreserveOrderOfLeafPartitions() {
            // Given - partition tree with multiple leaf partitions
            PartitionTree partitions = new PartitionsBuilder(schema)
                    .rootFirst("root")
                    .splitToNewChildren("root", "left", "right", "m")
                    .buildTree();

            FileReference fileInLeft = fileReference("file1.parquet", "left", 100L);
            FileReference fileInRight = fileReference("file2.parquet", "right", 200L);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(
                    tableStatus,
                    List.of(fileInLeft, fileInRight),
                    partitions.getAllPartitions());

            // Then - order should be consistent (based on LinkedHashSet used internally)
            List<FilesInPartition> result = index.getFilesInLeafPartitions();
            assertThat(result).hasSize(2);
        }
    }

    private FileReference fileReference(String filename, String partitionId, long numberOfRecords) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(partitionId)
                .numberOfRecords(numberOfRecords)
                .lastStateStoreUpdateTime(DEFAULT_UPDATE_TIME)
                .build();
    }

    private FileReference fileReference(String filename, String partitionId, long numberOfRecords, String jobId) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(partitionId)
                .numberOfRecords(numberOfRecords)
                .jobId(jobId)
                .lastStateStoreUpdateTime(DEFAULT_UPDATE_TIME)
                .build();
    }
}
