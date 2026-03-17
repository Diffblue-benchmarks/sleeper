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
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.table.TableStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.FileReferenceTestData.withJobId;

public class CompactionStrategyIndexTest {
    private final TableStatus tableStatus = TableStatus.uniqueIdAndName("test-table-id", "test-table", true);
    private final Schema schema = schemaWithKey("test");

    @Nested
    @DisplayName("Unassigned files")
    class UnassignedFiles {
        @Test
        void shouldIndexOneLeafPartitionWithMultipleFiles() {
            // Given
            PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                    .rootFirst("root");
            FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
            FileReference file1 = factory.rootFile("file1.parquet", 456L);
            FileReference file2 = factory.rootFile("file2.parquet", 789L);
            FileReference file3 = factory.rootFile("file3.parquet", 123L);
            List<FileReference> allFileReferences = List.of(file1, file2, file3);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, allFileReferences, partitionsBuilder.buildList());

            // Then
            assertThat(index.getFilesInLeafPartitions())
                    .containsExactly(unassignedFilesInPartition("root", List.of(file3, file1, file2)));
        }

        @Test
        void shouldIndexMultipleFilesWithSameNumberOfRecords() {
            // Given
            PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                    .rootFirst("root");
            FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
            FileReference file1 = factory.rootFile("file1.parquet", 100L);
            FileReference file2 = factory.rootFile("file2.parquet", 100L);
            FileReference file3 = factory.rootFile("file3.parquet", 100L);
            List<FileReference> allFileReferences = List.of(file1, file2, file3);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, allFileReferences, partitionsBuilder.buildList());

            // Then
            assertThat(index.getFilesInLeafPartitions())
                    .containsExactly(unassignedFilesInPartition("root", List.of(file1, file2, file3)));
        }

        @Test
        void shouldIndexMultipleLeafPartitionsWithMultipleFiles() {
            // Given
            PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                    .rootFirst("root")
                    .splitToNewChildren("root", "L", "R", 123L);
            FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
            FileReference file1 = factory.partitionFile("L", "file1.parquet", 120L);
            FileReference file2 = factory.partitionFile("R", "file2.parquet", 456L);
            FileReference file3 = factory.partitionFile("R", "file3.parquet", 789L);
            List<FileReference> allFileReferences = List.of(file1, file2, file3);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, allFileReferences, partitionsBuilder.buildList());

            // Then
            assertThat(index.getFilesInLeafPartitions())
                    .containsExactlyInAnyOrder(
                            unassignedFilesInPartition("L", List.of(file1)),
                            unassignedFilesInPartition("R", List.of(file2, file3)));
        }

        @Test
        void shouldSortFilesInAscendingOrderByRecordCount() {
            // Given
            PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                    .rootFirst("root");
            FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
            FileReference largeFile = factory.rootFile("large.parquet", 1000000L);
            FileReference mediumFile = factory.rootFile("medium.parquet", 50000L);
            FileReference smallFile = factory.rootFile("small.parquet", 100L);
            FileReference tinyFile = factory.rootFile("tiny.parquet", 1L);
            List<FileReference> allFileReferences = List.of(largeFile, mediumFile, smallFile, tinyFile);

            // When
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, allFileReferences, partitionsBuilder.buildList());

            // Then
            assertThat(index.getFilesInLeafPartitions())
                    .containsExactly(unassignedFilesInPartition("root", List.of(tinyFile, smallFile, mediumFile, largeFile)));
        }
    }

    @Test
    void shouldIgnoreLeafPartitionsWithNoFiles() {
        // Given
        PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                .rootFirst("root");

        // When
        CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, List.of(), partitionsBuilder.buildList());

        // Then
        assertThat(index.getFilesInLeafPartitions()).isEmpty();
    }

    @Test
    void shouldIgnoreLeafPartitionsWithOnlyAssignedFiles() {
        // Given
        PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                .rootFirst("root");
        FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
        FileReference file = withJobId("job1", factory.rootFile("file.parquet", 120L));

        // When
        CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, List.of(file), partitionsBuilder.buildList());

        // Then
        assertThat(index.getFilesInLeafPartitions()).isEmpty();
    }

    @Test
    void shouldIndexUnassignedFilesInLeafPartitions() {
        // Given
        PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                .rootFirst("root");
        FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
        FileReference file1 = factory.rootFile("file1.parquet", 120L);
        FileReference file2 = withJobId("job1", factory.rootFile("file2.parquet", 120L));

        // When
        CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, List.of(file1, file2), partitionsBuilder.buildList());

        // Then
        assertThat(index.getFilesInLeafPartitions()).containsExactly(
                new FilesInPartition(tableStatus, "root", List.of(file1), List.of(file2)));
    }

    @Test
    void shouldIgnoreFilesInNonLeafPartitions() {
        // Given
        PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                .rootFirst("root")
                .splitToNewChildren("root", "L", "R", 123L);
        FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
        FileReference file1 = factory.rootFile("file1.parquet", 120L);
        List<FileReference> allFileReferences = List.of(file1);

        // When
        CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, allFileReferences, partitionsBuilder.buildList());

        // Then
        assertThat(index.getFilesInLeafPartitions()).isEmpty();
    }

    private FilesInPartition unassignedFilesInPartition(String partitionId, List<FileReference> unassignedFiles) {
        return new FilesInPartition(tableStatus, partitionId, unassignedFiles, List.of());
    }

    @Nested
    @DisplayName("FilesInPartition methods")
    class FilesInPartitionMethods {
        @Test
        void shouldGetFilesWithJobId() {
            // Given
            PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                    .rootFirst("root");
            FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
            FileReference file1 = factory.rootFile("file1.parquet", 100L);
            FileReference file2 = withJobId("job1", factory.rootFile("file2.parquet", 200L));
            FilesInPartition filesInPartition = new FilesInPartition(tableStatus, "root", List.of(file1), List.of(file2));

            // When
            List<FileReference> filesWithJobId = filesInPartition.getFilesWithJobId();

            // Then
            assertThat(filesWithJobId).containsExactly(file2);
        }

        @Test
        void shouldGetFilesWithNoJobIdInAscendingOrder() {
            // Given
            PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                    .rootFirst("root");
            FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
            FileReference file1 = factory.rootFile("file1.parquet", 300L);
            FileReference file2 = factory.rootFile("file2.parquet", 100L);
            FilesInPartition filesInPartition = new FilesInPartition(tableStatus, "root", List.of(file2, file1), List.of());

            // When
            List<FileReference> filesWithNoJobId = filesInPartition.getFilesWithNoJobIdInAscendingOrder();

            // Then
            assertThat(filesWithNoJobId).containsExactly(file2, file1);
        }

        @Test
        void shouldGetPartitionId() {
            // Given
            FilesInPartition filesInPartition = new FilesInPartition(tableStatus, "test-partition", List.of(), List.of());

            // When
            String partitionId = filesInPartition.getPartitionId();

            // Then
            assertThat(partitionId).isEqualTo("test-partition");
        }

        @Test
        void shouldGetTableStatus() {
            // Given
            FilesInPartition filesInPartition = new FilesInPartition(tableStatus, "root", List.of(), List.of());

            // When
            TableStatus result = filesInPartition.getTableStatus();

            // Then
            assertThat(result).isEqualTo(tableStatus);
        }

        @Test
        void shouldGenerateConsistentHashCode() {
            // Given
            PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                    .rootFirst("root");
            FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
            FileReference file1 = factory.rootFile("file1.parquet", 100L);
            FileReference file2 = withJobId("job1", factory.rootFile("file2.parquet", 200L));
            FilesInPartition filesInPartition1 = new FilesInPartition(tableStatus, "root", List.of(file1), List.of(file2));
            FilesInPartition filesInPartition2 = new FilesInPartition(tableStatus, "root", List.of(file1), List.of(file2));

            // When
            int hashCode1 = filesInPartition1.hashCode();
            int hashCode2 = filesInPartition2.hashCode();

            // Then
            assertThat(hashCode1).isEqualTo(hashCode2);
        }

        @Test
        void shouldBeEqualWhenSameInstance() {
            // Given
            FilesInPartition filesInPartition = new FilesInPartition(tableStatus, "root", List.of(), List.of());

            // When / Then
            assertThat(filesInPartition).isEqualTo(filesInPartition);
        }

        @Test
        void shouldBeEqualWhenSameContent() {
            // Given
            PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                    .rootFirst("root");
            FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
            FileReference file1 = factory.rootFile("file1.parquet", 100L);
            FileReference file2 = withJobId("job1", factory.rootFile("file2.parquet", 200L));
            FilesInPartition filesInPartition1 = new FilesInPartition(tableStatus, "root", List.of(file1), List.of(file2));
            FilesInPartition filesInPartition2 = new FilesInPartition(tableStatus, "root", List.of(file1), List.of(file2));

            // When / Then
            assertThat(filesInPartition1).isEqualTo(filesInPartition2);
        }

        @Test
        void shouldNotBeEqualWhenDifferentPartitionId() {
            // Given
            FilesInPartition filesInPartition1 = new FilesInPartition(tableStatus, "root", List.of(), List.of());
            FilesInPartition filesInPartition2 = new FilesInPartition(tableStatus, "other", List.of(), List.of());

            // When / Then
            assertThat(filesInPartition1).isNotEqualTo(filesInPartition2);
        }

        @Test
        void shouldNotBeEqualWhenDifferentType() {
            // Given
            FilesInPartition filesInPartition = new FilesInPartition(tableStatus, "root", List.of(), List.of());
            String other = "not a FilesInPartition";

            // When / Then
            assertThat(filesInPartition).isNotEqualTo(other);
        }

        @Test
        void shouldGenerateToString() {
            // Given
            PartitionsBuilder partitionsBuilder = new PartitionsBuilder(schema)
                    .rootFirst("root");
            FileReferenceFactory factory = FileReferenceFactory.from(partitionsBuilder.buildTree());
            FileReference file1 = factory.rootFile("file1.parquet", 100L);
            FilesInPartition filesInPartition = new FilesInPartition(tableStatus, "root", List.of(file1), List.of());

            // When
            String result = filesInPartition.toString();

            // Then
            assertThat(result)
                    .contains("FilesInPartition")
                    .contains("root")
                    .contains("file1.parquet");
        }
    }
}
