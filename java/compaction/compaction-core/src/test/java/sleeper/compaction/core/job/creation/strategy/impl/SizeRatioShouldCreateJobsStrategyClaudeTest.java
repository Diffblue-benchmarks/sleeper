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

import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.compaction.core.job.creation.strategy.ShouldCreateJobsStrategy;
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

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.table.TableProperty.SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.table.TableStatusTestHelper.uniqueIdAndName;

class SizeRatioShouldCreateJobsStrategyClaudeTest {

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
            SizeRatioShouldCreateJobsStrategy strategy = new SizeRatioShouldCreateJobsStrategy();

            // Then
            assertThat(strategy).isNotNull();
        }

        @Test
        void shouldImplementShouldCreateJobsStrategy() {
            // When
            SizeRatioShouldCreateJobsStrategy strategy = new SizeRatioShouldCreateJobsStrategy();

            // Then
            assertThat(strategy).isInstanceOf(ShouldCreateJobsStrategy.class);
        }
    }

    @Nested
    @DisplayName("init method")
    class InitMethod {

        @Test
        void shouldReadMaxConcurrentJobsFromTableProperties() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 5);
            SizeRatioShouldCreateJobsStrategy strategy = new SizeRatioShouldCreateJobsStrategy();
            strategy.init(instanceProperties, tableProperties);

            // When - verify by checking maxCompactionJobsToCreate with no existing jobs
            FilesInPartition emptyPartition = createFilesInPartitionWithExistingJobs("root", 0, 0);
            long result = strategy.maxCompactionJobsToCreate(emptyPartition);

            // Then
            assertThat(result).isEqualTo(5);
        }

        @Test
        void shouldAllowReinitialization() {
            // Given
            SizeRatioShouldCreateJobsStrategy strategy = new SizeRatioShouldCreateJobsStrategy();

            // First init with max 3
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 3);
            strategy.init(instanceProperties, tableProperties);

            // Second init with max 7
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 7);
            strategy.init(instanceProperties, tableProperties);

            // When
            FilesInPartition emptyPartition = createFilesInPartitionWithExistingJobs("root", 0, 0);
            long result = strategy.maxCompactionJobsToCreate(emptyPartition);

            // Then - should use the latest configured value
            assertThat(result).isEqualTo(7);
        }

        @Test
        void shouldUseDefaultValueWhenPropertyNotExplicitlySet() {
            // Given - use default table properties without explicitly setting the property
            SizeRatioShouldCreateJobsStrategy strategy = new SizeRatioShouldCreateJobsStrategy();
            strategy.init(instanceProperties, tableProperties);

            // When
            FilesInPartition emptyPartition = createFilesInPartitionWithExistingJobs("root", 0, 0);
            long result = strategy.maxCompactionJobsToCreate(emptyPartition);

            // Then - default is Integer.MAX_VALUE
            assertThat(result).isEqualTo(Integer.MAX_VALUE);
        }
    }

    @Nested
    @DisplayName("maxCompactionJobsToCreate method")
    class MaxCompactionJobsToCreate {

        private SizeRatioShouldCreateJobsStrategy strategy;

        @BeforeEach
        void setUp() {
            strategy = new SizeRatioShouldCreateJobsStrategy();
        }

        @Test
        void shouldReturnMaxWhenNoExistingJobs() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 5);
            strategy.init(instanceProperties, tableProperties);
            FilesInPartition partition = createFilesInPartitionWithExistingJobs("root", 0, 0);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then
            assertThat(result).isEqualTo(5);
        }

        @Test
        void shouldReturnZeroWhenAtMaxConcurrentJobs() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 2);
            strategy.init(instanceProperties, tableProperties);
            // 2 existing jobs (at the limit)
            FilesInPartition partition = createFilesInPartitionWithExistingJobs("root", 2, 2);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then
            assertThat(result).isEqualTo(0);
        }

        @Test
        void shouldReturnZeroWhenExceedingMaxConcurrentJobs() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 2);
            strategy.init(instanceProperties, tableProperties);
            // 3 existing jobs (exceeds limit)
            FilesInPartition partition = createFilesInPartitionWithExistingJobs("root", 3, 3);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then
            assertThat(result).isEqualTo(0);
        }

        @Test
        void shouldReturnRemainingCapacityWhenBelowMax() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 5);
            strategy.init(instanceProperties, tableProperties);
            // 2 existing jobs
            FilesInPartition partition = createFilesInPartitionWithExistingJobs("root", 2, 2);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then - 5 max - 2 existing = 3 remaining
            assertThat(result).isEqualTo(3);
        }

        @Test
        void shouldReturnOneWhenOneSlotRemaining() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 3);
            strategy.init(instanceProperties, tableProperties);
            // 2 existing jobs
            FilesInPartition partition = createFilesInPartitionWithExistingJobs("root", 2, 2);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then
            assertThat(result).isEqualTo(1);
        }

        @Test
        void shouldCountUniqueJobIdsNotFileCount() {
            // Given - multiple files can have the same job ID
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 3);
            strategy.init(instanceProperties, tableProperties);
            // 6 files with only 2 unique job IDs
            FilesInPartition partition = createFilesInPartitionWithExistingJobs("root", 6, 2);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then - should count 2 unique jobs, leaving 1 slot
            assertThat(result).isEqualTo(1);
        }

        @Test
        void shouldCountMultipleFilesWithSameJobIdAsOneJob() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 5);
            strategy.init(instanceProperties, tableProperties);
            // Create partition with 4 files all sharing the same job ID, plus one without job ID
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReferenceWithJobId("file1.parquet", "root", 100L, "same-job-id"),
                    fileReferenceWithJobId("file2.parquet", "root", 200L, "same-job-id"),
                    fileReferenceWithJobId("file3.parquet", "root", 300L, "same-job-id"),
                    fileReferenceWithJobId("file4.parquet", "root", 400L, "same-job-id"),
                    fileReference("file5.parquet", "root", 500L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition partition = index.getFilesInLeafPartitions().get(0);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then - 4 files with same job ID = 1 job, so 5 - 1 = 4 remaining
            assertThat(result).isEqualTo(4);
        }

        @Test
        void shouldHandlePartitionWithOnlyFilesWithoutJobId() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 3);
            strategy.init(instanceProperties, tableProperties);
            // Files without job IDs (not yet assigned to any compaction job)
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("file1.parquet", "root", 100L),
                    fileReference("file2.parquet", "root", 200L),
                    fileReference("file3.parquet", "root", 300L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition partition = index.getFilesInLeafPartitions().get(0);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then - no existing jobs, so max of 3 can be created
            assertThat(result).isEqualTo(3);
        }

        @Test
        void shouldHandleMixedFilesWithAndWithoutJobIds() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 4);
            strategy.init(instanceProperties, tableProperties);
            // Mix of files with and without job IDs
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("file1.parquet", "root", 100L),
                    fileReference("file2.parquet", "root", 200L),
                    fileReferenceWithJobId("file3.parquet", "root", 300L, "job-1"),
                    fileReferenceWithJobId("file4.parquet", "root", 400L, "job-2"));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition partition = index.getFilesInLeafPartitions().get(0);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then - 2 existing jobs (job-1 and job-2), so 4 - 2 = 2 remaining
            assertThat(result).isEqualTo(2);
        }

        @Test
        void shouldHandleMaxConcurrentOfOne() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 1);
            strategy.init(instanceProperties, tableProperties);
            FilesInPartition emptyPartition = createFilesInPartitionWithExistingJobs("root", 0, 0);

            // When
            long result = strategy.maxCompactionJobsToCreate(emptyPartition);

            // Then
            assertThat(result).isEqualTo(1);
        }

        @Test
        void shouldReturnZeroWhenMaxConcurrentIsOneAndOneJobExists() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 1);
            strategy.init(instanceProperties, tableProperties);
            FilesInPartition partition = createFilesInPartitionWithExistingJobs("root", 1, 1);

            // When
            long result = strategy.maxCompactionJobsToCreate(partition);

            // Then
            assertThat(result).isEqualTo(0);
        }

        @Test
        void shouldBeCallableForDifferentPartitions() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 5);
            strategy.init(instanceProperties, tableProperties);
            FilesInPartition partition1 = createFilesInPartitionWithExistingJobs("partition-a", 1, 1);
            FilesInPartition partition2 = createFilesInPartitionWithExistingJobs("partition-b", 3, 3);

            // When
            long result1 = strategy.maxCompactionJobsToCreate(partition1);
            long result2 = strategy.maxCompactionJobsToCreate(partition2);

            // Then
            assertThat(result1).isEqualTo(4); // 5 - 1 = 4
            assertThat(result2).isEqualTo(2); // 5 - 3 = 2
        }

        @Test
        void shouldBeReusableForMultipleCalls() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 10);
            strategy.init(instanceProperties, tableProperties);
            FilesInPartition partition = createFilesInPartitionWithExistingJobs("root", 3, 3);

            // When
            long result1 = strategy.maxCompactionJobsToCreate(partition);
            long result2 = strategy.maxCompactionJobsToCreate(partition);
            long result3 = strategy.maxCompactionJobsToCreate(partition);

            // Then - should return same result each time
            assertThat(result1).isEqualTo(7);
            assertThat(result2).isEqualTo(7);
            assertThat(result3).isEqualTo(7);
        }
    }

    private FilesInPartition createFilesInPartitionWithExistingJobs(String partitionId, int numFilesWithJobId, int numUniqueJobIds) {
        PartitionTree partitions = new PartitionsBuilder(schema).singlePartition(partitionId).buildTree();
        List<FileReference> files = new ArrayList<>();

        // Create files with job IDs
        for (int i = 0; i < numFilesWithJobId; i++) {
            String jobId = "job-" + (i % numUniqueJobIds);
            files.add(fileReferenceWithJobId("file-with-job-" + i + ".parquet", partitionId, 100L * (i + 1), jobId));
        }

        // Add at least one file without job ID so the partition gets created
        files.add(fileReference("file-without-job.parquet", partitionId, 50L));

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
