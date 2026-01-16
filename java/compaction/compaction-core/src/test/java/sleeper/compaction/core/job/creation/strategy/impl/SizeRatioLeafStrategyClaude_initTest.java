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
public class SizeRatioLeafStrategyClaude_initTest {

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
    @DisplayName("init method")
    class InitMethod {

        @Test
        void shouldInitializeWithRatioFromTableProperties() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 2);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // When
            strategy.init(instanceProperties, tableProperties, factory);

            // Then - verify ratio was set by using behavior: with ratio 2, sum of smaller files
            // (200) >= 2 * largest (100) should trigger compaction
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);
            assertThat(jobs).hasSize(1);
        }

        @Test
        void shouldInitializeWithBatchSizeFromTableProperties() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // When
            strategy.init(instanceProperties, tableProperties, factory);

            // Then - verify batch size by checking jobs are created with batch size 3
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100, 100, 100);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);
            // With 6 equal files and ratio 1, batch size 3 should create 2 jobs
            assertThat(jobs).hasSize(2);
        }

        @Test
        void shouldInitializeWithFactory() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // When
            strategy.init(instanceProperties, tableProperties, factory);

            // Then - verify factory is set by checking created jobs have correct table ID
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getTableId()).isEqualTo(tableProperties.getStatus().getTableUniqueId());
        }

        @Test
        void shouldReinitializeWithDifferentRatio() {
            // Given
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            CompactionJobFactory factory = createFactory();
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);

            // First init with ratio 10 (hard to meet)
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 10);
            strategy.init(instanceProperties, tableProperties, factory);

            // Files where sum of smaller (100) < 10 * largest (100) = 1000
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100);
            List<CompactionJob> jobsWithStrictRatio = strategy.createJobsForLeafPartition(filesInPartition);

            // When - reinit with ratio 1 (easy to meet)
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            strategy.init(instanceProperties, tableProperties, factory);

            // Same files now meet criteria: sum of smaller (100) >= 1 * largest (100) = 100
            List<CompactionJob> jobsWithLenientRatio = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobsWithStrictRatio).isEmpty();
            assertThat(jobsWithLenientRatio).hasSize(1);
        }

        @Test
        void shouldReinitializeWithDifferentBatchSize() {
            // Given
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            CompactionJobFactory factory = createFactory();
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);

            // First init with batch size 2
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, factory);

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100);
            List<CompactionJob> jobsWithSmallBatch = strategy.createJobsForLeafPartition(filesInPartition);

            // When - reinit with batch size 4
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 4);
            strategy.init(instanceProperties, tableProperties, factory);

            List<CompactionJob> jobsWithLargeBatch = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobsWithSmallBatch).hasSize(2);
            assertThat(jobsWithLargeBatch).hasSize(1);
        }

        @Test
        void shouldReinitializeWithDifferentFactory() {
            // Given
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);

            // First init with one factory
            CompactionJobFactory factory1 = createFactoryWithPrefix("first-");
            strategy.init(instanceProperties, tableProperties, factory1);

            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100);
            List<CompactionJob> jobsWithFactory1 = strategy.createJobsForLeafPartition(filesInPartition);

            // When - reinit with different factory
            CompactionJobFactory factory2 = createFactoryWithPrefix("second-");
            strategy.init(instanceProperties, tableProperties, factory2);

            List<CompactionJob> jobsWithFactory2 = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobsWithFactory1).hasSize(1);
            assertThat(jobsWithFactory1.get(0).getId()).startsWith("first-");
            assertThat(jobsWithFactory2).hasSize(1);
            assertThat(jobsWithFactory2.get(0).getId()).startsWith("second-");
        }

        @Test
        void shouldInitializeWithDefaultRatio() {
            // Given - using default ratio value (which is 3)
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // When
            strategy.init(instanceProperties, tableProperties, factory);

            // Then - with default ratio 3, need sum of smaller >= 3 * largest
            // 4 files of 100 each: 300 >= 3 * 100 = 300, so criteria met
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);
            assertThat(jobs).hasSize(1);
        }

        @Test
        void shouldNotCreateJobWhenCriteriaNotMetWithDefaultRatio() {
            // Given - using default ratio value (which is 3)
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // When
            strategy.init(instanceProperties, tableProperties, factory);

            // Then - with default ratio 3, 2 files of 100 each: 100 < 3 * 100 = 300, so criteria not met
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldUseRatioOfOneSuccessfully() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // When
            strategy.init(instanceProperties, tableProperties, factory);

            // Then - with ratio 1, even 2 equal files meet criteria: 100 >= 1 * 100
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);
            assertThat(jobs).hasSize(1);
        }

        @Test
        void shouldUseHighRatioSuccessfully() {
            // Given
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 100);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // When
            strategy.init(instanceProperties, tableProperties, factory);

            // Then - with ratio 100, need many small files to meet criteria
            // Sum of smaller files needs to be >= 100 * largest
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100, 100, 100, 100);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldIgnoreInstancePropertiesDuringInit() {
            // Given - the init method only uses tableProperties for ratio and batch size
            // but should accept instanceProperties without error
            tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, 1);
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 10);
            SizeRatioLeafStrategy strategy = new SizeRatioLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // When
            strategy.init(instanceProperties, tableProperties, factory);

            // Then - strategy should work correctly
            FilesInPartition filesInPartition = createFilesInPartitionWithSizes(100, 100);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);
            assertThat(jobs).hasSize(1);
        }
    }

    private CompactionJobFactory createFactory() {
        return createFactoryWithPrefix("job-");
    }

    private CompactionJobFactory createFactoryWithPrefix(String prefix) {
        AtomicInteger jobCounter = new AtomicInteger(0);
        return new CompactionJobFactory(instanceProperties, tableProperties, () -> prefix + jobCounter.incrementAndGet());
    }

    private FilesInPartition createFilesInPartitionWithSizes(long... sizes) {
        PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
        List<FileReference> files = new ArrayList<>();
        for (int i = 0; i < sizes.length; i++) {
            files.add(fileReference("file" + i + ".parquet", "root", sizes[i]));
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
}
