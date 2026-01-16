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
import sleeper.compaction.core.job.creation.strategy.CompactionStrategy;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex;
import sleeper.compaction.core.job.creation.strategy.DelegatingCompactionStrategy;
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
import static sleeper.core.properties.table.TableProperty.SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION;
import static sleeper.core.properties.table.TableProperty.SIZE_RATIO_COMPACTION_STRATEGY_RATIO;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.table.TableStatusTestHelper.uniqueIdAndName;

public class SizeRatioCompactionStrategyClaudeTest {

    private static final Instant DEFAULT_UPDATE_TIME = Instant.parse("2024-06-01T10:00:00Z");
    private final Schema schema = schemaWithKey("key", new StringType());
    private final TableStatus tableStatus = uniqueIdAndName("test-table-id", "test-table");
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private TableProperties tableProperties;

    @BeforeEach
    void setUp() {
        tableProperties = createTestTableProperties(instanceProperties, schema);
    }

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Test
        void shouldCreateInstanceSuccessfully() {
            // When
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();

            // Then
            assertThat(strategy).isNotNull();
        }

        @Test
        void shouldExtendDelegatingCompactionStrategy() {
            // When
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();

            // Then
            assertThat(strategy).isInstanceOf(DelegatingCompactionStrategy.class);
        }

        @Test
        void shouldImplementCompactionStrategy() {
            // When
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();

            // Then
            assertThat(strategy).isInstanceOf(CompactionStrategy.class);
        }

        @Test
        void shouldBeUsableAfterConstruction() {
            // Given
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createEmptyIndex();

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).isEmpty();
        }
    }

    @Nested
    @DisplayName("Integration with SizeRatioLeafStrategy")
    class IntegrationWithSizeRatioLeafStrategy {

        @Test
        void shouldReturnEmptyListWhenNoFiles() {
            // Given
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createEmptyIndex();

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldNotCreateJobWhenSizeRatioCriteriaNotMet() {
            // Given - two files where sum of smaller is NOT >= ratio * largest
            // With default ratio of 3: small file (100) < 3 * large file (1000) = 3000
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("small.parquet", "root", 100L),
                    fileReference("large.parquet", "root", 1000L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldCreateJobWhenSizeRatioCriteriaMet() {
            // Given - files where sum of smaller files >= ratio * largest
            // With default ratio of 3: 100 + 100 + 100 + 100 = 400 >= 3 * 100 = 300
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("file1.parquet", "root", 100L),
                    fileReference("file2.parquet", "root", 100L),
                    fileReference("file3.parquet", "root", 100L),
                    fileReference("file4.parquet", "root", 100L),
                    fileReference("file5.parquet", "root", 100L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(5);
        }

        @Test
        void shouldCreateJobWhenSizeRatioCriteriaExactlyMet() {
            // Given - files where sum of smaller files == ratio * largest
            // With ratio of 3: 300 = 3 * 100 (exactly meets criteria)
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 3);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("file1.parquet", "root", 100L),
                    fileReference("file2.parquet", "root", 100L),
                    fileReference("file3.parquet", "root", 100L),
                    fileReference("largest.parquet", "root", 100L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
        }

        @Test
        void shouldRemoveLargestFileUntilCriteriaMet() {
            // Given - files where only a subset meet the criteria after removing large files
            // Files: 100, 100, 100, 500
            // With ratio 3: 100+100+100 = 300 < 3*500 = 1500, so remove largest
            // After removal: 100+100 = 200 < 3*100 = 300, so remove largest
            // After removal: 100 < 3*100 = 300, only 1 file so no job
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 3);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("small1.parquet", "root", 100L),
                    fileReference("small2.parquet", "root", 100L),
                    fileReference("small3.parquet", "root", 100L),
                    fileReference("large.parquet", "root", 500L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldBatchFilesWhenExceedingBatchSize() {
            // Given - many files meeting criteria, but exceeding batch size
            // With ratio 1 and batch size 3: each batch of 3 same-sized files will meet criteria
            // For batch of 3 equal files (100 each): sum of others (200) >= 1 * largest (100) = 100
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = new ArrayList<>();
            // All same size, with ratio 1 each batch of 3 will meet criteria
            for (int i = 0; i < 10; i++) {
                files.add(fileReference("file" + i + ".parquet", "root", 100L));
            }
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(3); // 10 files / 3 per batch = 3 complete batches
        }

        @Test
        void shouldUseConfiguredRatio() {
            // Given - lower ratio of 2 should be easier to meet
            // With ratio 2: 200 >= 2 * 100 = 200 (meets criteria)
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 2);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("file1.parquet", "root", 100L),
                    fileReference("file2.parquet", "root", 100L),
                    fileReference("largest.parquet", "root", 100L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
        }

        @Test
        void shouldNotCreateJobWithOnlyOneFile() {
            // Given - single file should never create a job
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("single.parquet", "root", 100L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldAssignCorrectPartitionIdToJob() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("file1.parquet", "root", 100L),
                    fileReference("file2.parquet", "root", 100L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getPartitionId()).isEqualTo("root");
        }
    }

    @Nested
    @DisplayName("Integration with SizeRatioShouldCreateJobsStrategy")
    class IntegrationWithSizeRatioShouldCreateJobsStrategy {

        @Test
        void shouldRespectMaxConcurrentJobsPerPartition() {
            // Given - files already have jobs assigned
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 2);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReferenceWithJob("file1.parquet", "root", 100L, "existing-job-1"),
                    fileReferenceWithJob("file2.parquet", "root", 100L, "existing-job-1"),
                    fileReferenceWithJob("file3.parquet", "root", 100L, "existing-job-2"),
                    fileReferenceWithJob("file4.parquet", "root", 100L, "existing-job-2"),
                    fileReference("new1.parquet", "root", 100L),
                    fileReference("new2.parquet", "root", 100L),
                    fileReference("new3.parquet", "root", 100L),
                    fileReference("new4.parquet", "root", 100L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then - 2 existing jobs, max 2, so no new jobs allowed
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldAllowJobsUpToMaxConcurrentLimit() {
            // Given - one existing job, max 2 concurrent
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 2);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReferenceWithJob("file1.parquet", "root", 100L, "existing-job-1"),
                    fileReferenceWithJob("file2.parquet", "root", 100L, "existing-job-1"),
                    fileReference("new1.parquet", "root", 100L),
                    fileReference("new2.parquet", "root", 100L),
                    fileReference("new3.parquet", "root", 100L),
                    fileReference("new4.parquet", "root", 100L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then - 1 existing job, max 2, so 1 new job allowed
            assertThat(jobs).hasSize(1);
        }

        @Test
        void shouldCreateMultipleJobsWhenNoConcurrentLimit() {
            // Given - high max concurrent limit
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 100);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                files.add(fileReference("file" + i + ".parquet", "root", 100L));
            }
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(3); // 6 files / 2 per batch = 3 jobs
        }
    }

    @Nested
    @DisplayName("Reusability")
    class Reusability {

        @Test
        void shouldBeReusableForMultipleCalls() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index1 = createIndexWithSameRecordCountFiles(2);
            CompactionStrategyIndex index2 = createIndexWithSameRecordCountFiles(4);

            // When
            List<CompactionJob> jobs1 = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index1);
            List<CompactionJob> jobs2 = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index2);

            // Then
            assertThat(jobs1).hasSize(1);
            assertThat(jobs2).hasSize(2);
        }

        @Test
        void shouldAllowReconfigurationBetweenCalls() {
            // Given
            SizeRatioCompactionStrategy strategy = new SizeRatioCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            // First call with strict ratio
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 10);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            CompactionStrategyIndex index1 = createIndexWithSameRecordCountFiles(5);
            List<CompactionJob> jobs1 = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index1);

            // Second call with lenient ratio
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            CompactionStrategyIndex index2 = createIndexWithSameRecordCountFiles(5);
            List<CompactionJob> jobs2 = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index2);

            // Then
            assertThat(jobs1).isEmpty(); // Strict ratio not met
            assertThat(jobs2).hasSize(1); // Lenient ratio met
        }
    }

    private CompactionJobFactory createFactory() {
        AtomicInteger jobCounter = new AtomicInteger(0);
        return new CompactionJobFactory(instanceProperties, tableProperties, () -> "job-" + jobCounter.incrementAndGet());
    }

    private CompactionStrategyIndex createEmptyIndex() {
        PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
        return new CompactionStrategyIndex(tableStatus, List.of(), partitions.getAllPartitions());
    }

    private CompactionStrategyIndex createIndexWithSameRecordCountFiles(int numFiles) {
        PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
        List<FileReference> files = new ArrayList<>();
        for (int i = 0; i < numFiles; i++) {
            files.add(fileReference("file" + i + ".parquet", "root", 100L));
        }
        return new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
    }

    private FileReference fileReference(String filename, String partitionId, long numberOfRecords) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(partitionId)
                .numberOfRecords(numberOfRecords)
                .lastStateStoreUpdateTime(DEFAULT_UPDATE_TIME)
                .build();
    }

    private FileReference fileReferenceWithJob(String filename, String partitionId, long numberOfRecords, String jobId) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(partitionId)
                .numberOfRecords(numberOfRecords)
                .lastStateStoreUpdateTime(DEFAULT_UPDATE_TIME)
                .jobId(jobId)
                .build();
    }
}
