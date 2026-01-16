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
package sleeper.compaction.core.job.creation.strategy.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobFactory;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.FileReference;
import sleeper.core.table.TableStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.table.TableProperty.COMPACTION_FILES_BATCH_SIZE;
import static sleeper.core.properties.table.TableProperty.SIZE_RATIO_COMPACTION_STRATEGY_RATIO;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.table.TableStatusTestHelper.uniqueIdAndName;

@SuppressWarnings("checkstyle:typeName")
public class SizeRatioLeafStrategyClaude_createJobsForLeafPartitionTest {

    private static final Instant DEFAULT_UPDATE_TIME = Instant.parse("2024-06-01T10:00:00Z");
    private final Schema schema = schemaWithKey("key", new StringType());
    private final TableStatus tableStatus = uniqueIdAndName("test-table-id", "test-table");
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private TableProperties tableProperties;
    private SizeRatioLeafStrategy strategy;

    @BeforeEach
    void setUp() {
        tableProperties = createTestTableProperties(instanceProperties, schema);
        strategy = new SizeRatioLeafStrategy();
    }

    @Nested
    @DisplayName("Empty and no files scenarios")
    class EmptyAndNoFilesScenarios {

        @Test
        void shouldReturnEmptyListWhenNoFiles() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, List.of(), partitions.getAllPartitions());

            // When/Then - no leaf partitions with files means empty list
            assertThat(index.getFilesInLeafPartitions()).isEmpty();
        }

        @Test
        void shouldReturnEmptyListWhenOnlyOneFile() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - single file can never meet criteria (needs at least 2)
            assertThat(jobs).isEmpty();
        }
    }

    @Nested
    @DisplayName("Size ratio criteria")
    class SizeRatioCriteria {

        @Test
        void shouldCreateJobWhenTwoEqualFilesWithRatioOne() {
            // Given - ratio 1: sum of smaller (100) >= 1 * largest (100)
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(2);
        }

        @Test
        void shouldNotCreateJobWhenTwoEqualFilesWithRatioTwo() {
            // Given - ratio 2: sum of smaller (100) < 2 * largest (100) = 200
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 2);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldCreateJobWhenThreeFilesWithRatioTwo() {
            // Given - ratio 2: sum of smaller (100 + 100) = 200 >= 2 * largest (100) = 200
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 2);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldCreateJobWithOnlyFilesThatMeetCriteria() {
            // Given - ratio 3: need sum >= 3 * largest
            // Files: 100, 200, 300, 1000
            // All 4: sum of smaller (100+200+300) = 600 < 3 * 1000 = 3000 - NO
            // Remove largest: (100, 200, 300): sum (100+200) = 300 >= 3 * 300 = 900 - NO
            // Remove largest: (100, 200): sum (100) = 100 < 3 * 200 = 600 - NO
            // Actually let me recalculate with different numbers
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 3);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            // Files: 100, 100, 100, 100, 1000
            // All 5: sum (100+100+100+100) = 400 < 3 * 1000 = 3000 - NO
            // Remove 1000: (100, 100, 100, 100): sum (100+100+100) = 300 >= 3 * 100 = 300 - YES
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100, 1000);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - should only include the 4 files of size 100
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(4);
        }

        @Test
        void shouldNotCreateJobWhenNoCombinationMeetsCriteria() {
            // Given - ratio 10: need sum of smaller >= 10 * largest
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 10);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            // Files: 10, 100 - sum of smaller (10) < 10 * largest (100) = 1000
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(10, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldCreateJobWhenExactlyMeetingCriteria() {
            // Given - ratio 3: sum of smaller must be >= 3 * largest
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 3);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            // Files: 100, 100, 100, 100 - sum (300) == 3 * 100 = 300 - exactly meets
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(4);
        }
    }

    @Nested
    @DisplayName("Batch size handling")
    class BatchSizeHandling {

        @Test
        void shouldCreateSingleJobWhenFilesEqualBatchSize() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldCreateSingleJobWhenFilesLessThanBatchSize() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldCreateMultipleJobsWhenFilesExceedBatchSize() {
            // Given - 6 equal files with batch size 3
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - with ratio 1 and equal files, both batches of 3 should meet criteria
            assertThat(jobs).hasSize(2);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
            assertThat(jobs.get(1).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldHandleBatchSizeOfOne() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 1);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - batch size 1 can never meet criteria (single file doesn't meet)
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldHandleBatchSizeOfTwo() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - 4 files / 2 batch size = 2 jobs
            assertThat(jobs).hasSize(2);
            assertThat(jobs.get(0).getInputFiles()).hasSize(2);
            assertThat(jobs.get(1).getInputFiles()).hasSize(2);
        }

        @Test
        void shouldNotCreateJobForRemainingFilesThatDontMeetBatchSize() {
            // Given - 5 files with batch size 3, ratio 1
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - first batch of 3 files, remaining 2 files can still form a batch that meets criteria
            assertThat(jobs).hasSize(2);
        }
    }

    @Nested
    @DisplayName("Batching with varying file sizes")
    class BatchingWithVaryingFileSizes {

        @Test
        void shouldSkipBatchesThatDontMeetCriteria() {
            // Given - ratio 2 requires sum of smaller >= 2 * largest
            // With batch size 3: need sum of 2 smaller >= 2 * largest
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 2);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            strategy.init(instanceProperties, tableProperties, createFactory());

            // Files: 100, 100, 100 - sum of smaller (200) >= 2 * largest (100) = 200? YES
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - all 3 meet criteria with ratio 2 since sum (200) >= 2 * 100
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldCreateJobsForBatchesThatMeetCriteriaWithinLargerSet() {
            // Given - ratio 1, batch size 2
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());

            // Files: 10, 20, 30, 40 all meet criteria (since each pair will have sum of smaller >= 1 * largest)
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(10, 20, 30, 40);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - first pair (10, 20): 10 >= 1 * 20? NO for ratio criteria
            // Actually with ratio 1: smallest (10) >= 1 * largest (20)? 10 >= 20? NO
            // So (10, 20) doesn't meet, slide...
            // (20, 30): 20 >= 30? NO
            // (30, 40): 30 >= 40? NO
            // None meet! Let me recalculate...
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldCreateJobsWhenSmallerFilesPassCriteria() {
            // Given - ratio 1: sum of smaller >= largest
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());

            // With ratio 1 and 2-file batches: need file1 >= file2
            // Files: 100, 100, 100, 100 - all pairs meet (100 >= 100)
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(2);
        }
    }

    @Nested
    @DisplayName("Files with job IDs already assigned")
    class FilesWithJobIdsAlreadyAssigned {

        @Test
        void shouldIgnoreFilesWithJobIdAlreadyAssigned() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("file1.parquet", "root", 100L),
                    fileReference("file2.parquet", "root", 100L),
                    fileReferenceWithJobId("file3.parquet", "root", 100L, "existing-job"));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - only 2 files without job ID
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).containsExactly("file1.parquet", "file2.parquet");
        }

        @Test
        void shouldReturnEmptyWhenAllFilesHaveJobIds() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            // Include one file without job ID so partition is returned, plus files with job IDs
            List<FileReference> files = List.of(
                    fileReference("file0.parquet", "root", 100L),
                    fileReferenceWithJobId("file1.parquet", "root", 100L, "job1"),
                    fileReferenceWithJobId("file2.parquet", "root", 100L, "job2"));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - only 1 file without job ID, can't meet criteria
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldNotMeetCriteriaWhenTooFewFilesWithoutJobIds() {
            // Given - ratio 3 needs 4 equal files
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 3);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("file1.parquet", "root", 100L),
                    fileReference("file2.parquet", "root", 100L),
                    fileReferenceWithJobId("file3.parquet", "root", 100L, "job1"),
                    fileReferenceWithJobId("file4.parquet", "root", 100L, "job2"));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - only 2 files without job IDs, doesn't meet ratio 3 criteria
            assertThat(jobs).isEmpty();
        }
    }

    @Nested
    @DisplayName("Job properties")
    class JobProperties {

        @Test
        void shouldAssignCorrectPartitionIdToJob() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithIdAndSizes("custom-partition", 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getPartitionId()).isEqualTo("custom-partition");
        }

        @Test
        void shouldAssignCorrectTableIdToJob() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getTableId()).isEqualTo(tableProperties.getStatus().getTableUniqueId());
        }

        @Test
        void shouldCreateJobsWithUniqueIds() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(2);
            assertThat(jobs.get(0).getId()).isNotEqualTo(jobs.get(1).getId());
        }

        @Test
        void shouldIncludeCorrectInputFilesInJob() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("alpha.parquet", "root", 100L),
                    fileReference("beta.parquet", "root", 100L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).containsExactlyInAnyOrder("alpha.parquet", "beta.parquet");
        }
    }

    @Nested
    @DisplayName("File ordering")
    class FileOrdering {

        @Test
        void shouldProcessFilesInAscendingOrderByRecordCount() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("large.parquet", "root", 1000L),
                    fileReference("small.parquet", "root", 100L),
                    fileReference("medium.parquet", "root", 500L),
                    fileReference("tiny.parquet", "root", 50L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - files are sorted ascending, so first batch is smallest files
            // (50, 100): 50 >= 100? NO
            // (100, 500): 100 >= 500? NO
            // (500, 1000): 500 >= 1000? NO
            // None of the pairs meet criteria
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldCreateJobsWithFilesInCorrectOrder() {
            // Given - files that will meet criteria
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("c.parquet", "root", 100L),
                    fileReference("a.parquet", "root", 100L),
                    fileReference("b.parquet", "root", 100L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
        }
    }

    @Nested
    @DisplayName("Reusability")
    class Reusability {

        @Test
        void shouldBeReusableForMultiplePartitions() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition partition1 = createFilesInPartitionWithIdAndSizes("partition-1", 100, 100);
            FilesInPartition partition2 = createFilesInPartitionWithIdAndSizes("partition-2", 100, 100, 100);

            // When
            List<CompactionJob> jobs1 = strategy.createJobsForLeafPartition(partition1);
            List<CompactionJob> jobs2 = strategy.createJobsForLeafPartition(partition2);

            // Then
            assertThat(jobs1).hasSize(1);
            assertThat(jobs1.get(0).getPartitionId()).isEqualTo("partition-1");
            assertThat(jobs1.get(0).getInputFiles()).hasSize(2);

            assertThat(jobs2).hasSize(1);
            assertThat(jobs2.get(0).getPartitionId()).isEqualTo("partition-2");
            assertThat(jobs2.get(0).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldCreateIndependentJobsForDifferentPartitions() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            strategy.init(instanceProperties, tableProperties, createFactory());

            FilesInPartition partition1 = createFilesInPartitionWithIdAndSizes("partition-1", 100, 100);
            FilesInPartition partition2 = createFilesInPartitionWithIdAndSizes("partition-2", 100, 100);

            // When
            List<CompactionJob> jobs1 = strategy.createJobsForLeafPartition(partition1);
            List<CompactionJob> jobs2 = strategy.createJobsForLeafPartition(partition2);

            // Then - jobs should have different IDs
            assertThat(jobs1.get(0).getId()).isNotEqualTo(jobs2.get(0).getId());
        }
    }

    @Nested
    @DisplayName("Large file counts")
    class LargeFileCounts {

        @Test
        void shouldHandleManyFilesWithSmallBatchSize() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());

            // 10 equal files with batch size 2
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(
                    100, 100, 100, 100, 100, 100, 100, 100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - 5 batches of 2 files each
            assertThat(jobs).hasSize(5);
            for (CompactionJob job : jobs) {
                assertThat(job.getInputFiles()).hasSize(2);
            }
        }

        @Test
        void shouldHandleLargeBatchSize() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 100);
            strategy.init(instanceProperties, tableProperties, createFactory());

            // 10 files with batch size 100
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(
                    100, 100, 100, 100, 100, 100, 100, 100, 100, 100);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - single job with all files
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(10);
        }
    }

    private CompactionJobFactory createFactory() {
        AtomicInteger jobCounter = new AtomicInteger(0);
        return new CompactionJobFactory(instanceProperties, tableProperties, () -> "job-" + jobCounter.incrementAndGet());
    }

    private FilesInPartition createFilesInPartitionWithSizes(long... sizes) {
        return createFilesInPartitionWithIdAndSizes("root", sizes);
    }

    private FilesInPartition createFilesInPartitionWithIdAndSizes(String partitionId, long... sizes) {
        PartitionTree partitions = new PartitionsBuilder(schema).singlePartition(partitionId).buildTree();
        List<FileReference> files = new ArrayList<>();
        for (int i = 0; i < sizes.length; i++) {
            files.add(fileReference("file" + i + ".parquet", partitionId, sizes[i]));
        }
        CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
        return index.getFilesInLeafPartitions().get(0);
    }

    private FileReference fileReference(String filename, String partitionId, long numberOfRecords) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(partitionId)
                .numberOfRecords(numberOfRecords)
                .lastStateStoreUpdateTime(DEFAULT_UPDATE_TIME)
                .build();
    }

    private FileReference fileReferenceWithJobId(String filename, String partitionId, long numberOfRecords, String jobId) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(partitionId)
                .numberOfRecords(numberOfRecords)
                .lastStateStoreUpdateTime(DEFAULT_UPDATE_TIME)
                .jobId(jobId)
                .build();
    }
}
