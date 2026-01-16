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
import sleeper.compaction.core.job.creation.strategy.LeafPartitionCompactionStrategy;
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
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.table.TableStatusTestHelper.uniqueIdAndName;

public class BasicLeafStrategyClaudeTest {

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
            BasicLeafStrategy strategy = new BasicLeafStrategy();

            // Then
            assertThat(strategy).isNotNull();
        }

        @Test
        void shouldImplementLeafPartitionCompactionStrategy() {
            // When
            BasicLeafStrategy strategy = new BasicLeafStrategy();

            // Then
            assertThat(strategy).isInstanceOf(LeafPartitionCompactionStrategy.class);
        }
    }

    @Nested
    @DisplayName("init method")
    class InitMethod {

        @Test
        void shouldInitializeWithBatchSizeFromTableProperties() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 5);
            BasicLeafStrategy strategy = new BasicLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // When
            strategy.init(instanceProperties, tableProperties, factory);

            // Then - verify by calling createJobsForLeafPartition and checking behavior
            FilesInPartition filesInPartition = createFilesInPartition(5);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(5);
        }

        @Test
        void shouldInitializeWithFactory() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            BasicLeafStrategy strategy = new BasicLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // When
            strategy.init(instanceProperties, tableProperties, factory);

            // Then - verify by calling createJobsForLeafPartition and checking a job is created
            FilesInPartition filesInPartition = createFilesInPartition(2);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getTableId()).isEqualTo(tableProperties.getStatus().getTableUniqueId());
        }

        @Test
        void shouldReinitializeWithDifferentBatchSize() {
            // Given
            BasicLeafStrategy strategy = new BasicLeafStrategy();
            CompactionJobFactory factory = createFactory();

            // First init with batch size 2
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, factory);

            // Then reinit with batch size 3
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            strategy.init(instanceProperties, tableProperties, factory);

            // When
            FilesInPartition filesInPartition = createFilesInPartition(5);
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - should use new batch size of 3, so only 1 job with 3 files
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
        }
    }

    @Nested
    @DisplayName("createJobsForLeafPartition")
    class CreateJobsForLeafPartition {

        private BasicLeafStrategy strategy;

        @BeforeEach
        void setUp() {
            strategy = new BasicLeafStrategy();
        }

        @Test
        void shouldReturnEmptyListWhenOnlyOneFile() {
            // Given - batch size requires 2 files but only 1 is available
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartition(1);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - not enough files to create a batch
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldReturnEmptyListWhenFilesLessThanBatchSize() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 5);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartition(3);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldCreateOneJobWhenFilesEqualBatchSize() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartition(3);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldCreateOneJobWhenFilesExceedBatchSizeButNotDouble() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartition(5);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - only one batch of 3 files, remainder (2) is not enough for another batch
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldCreateTwoJobsWhenFilesExactlyDoubleBatchSize() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartition(6);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(2);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
            assertThat(jobs.get(1).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldCreateMultipleJobsWhenFilesExceedMultipleBatches() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartition(7);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - 7 files with batch size 2 = 3 jobs (2+2+2), last file not included
            assertThat(jobs).hasSize(3);
            for (CompactionJob job : jobs) {
                assertThat(job.getInputFiles()).hasSize(2);
            }
        }

        @Test
        void shouldAssignCorrectPartitionIdToJobs() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartitionWithId("custom-partition", 4);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(2);
            assertThat(jobs).allMatch(job -> "custom-partition".equals(job.getPartitionId()));
        }

        @Test
        void shouldProcessFilesInAscendingOrderByRecordCount() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());

            // Files are sorted by record count ascending in FilesInPartition
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("large.parquet", "root", 1000L),
                    fileReference("small.parquet", "root", 100L),
                    fileReference("medium.parquet", "root", 500L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - first job should contain smallest files first
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles())
                    .containsExactly("small.parquet", "medium.parquet");
        }

        @Test
        void shouldUseBatchSizeOfOne() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 1);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartition(3);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(3);
            for (CompactionJob job : jobs) {
                assertThat(job.getInputFiles()).hasSize(1);
            }
        }

        @Test
        void shouldHandleLargeBatchSize() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 50);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartition(50);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(50);
        }

        @Test
        void shouldIgnoreFilesWithJobIdAlreadyAssigned() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());

            // Create files with a mix of having job IDs assigned or not
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("file1.parquet", "root", 100L),
                    fileReference("file2.parquet", "root", 200L),
                    fileReferenceWithJobId("file3.parquet", "root", 300L, "existing-job"));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
            FilesInPartition filesInPartition = index.getFilesInLeafPartitions().get(0);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then - only creates job for files without job ID
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles())
                    .containsExactly("file1.parquet", "file2.parquet");
        }

        @Test
        void shouldBeReusableForMultiplePartitions() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition partition1 = createFilesInPartitionWithId("partition-1", 2);
            FilesInPartition partition2 = createFilesInPartitionWithId("partition-2", 4);

            // When
            List<CompactionJob> jobs1 = strategy.createJobsForLeafPartition(partition1);
            List<CompactionJob> jobs2 = strategy.createJobsForLeafPartition(partition2);

            // Then
            assertThat(jobs1).hasSize(1);
            assertThat(jobs1.get(0).getPartitionId()).isEqualTo("partition-1");

            assertThat(jobs2).hasSize(2);
            assertThat(jobs2).allMatch(job -> "partition-2".equals(job.getPartitionId()));
        }

        @Test
        void shouldCreateJobsWithUniqueIds() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartition(4);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(2);
            assertThat(jobs.get(0).getId()).isNotEqualTo(jobs.get(1).getId());
        }

        @Test
        void shouldCreateJobWithCorrectTableId() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            strategy.init(instanceProperties, tableProperties, createFactory());
            FilesInPartition filesInPartition = createFilesInPartition(2);

            // When
            List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getTableId()).isEqualTo(tableProperties.getStatus().getTableUniqueId());
        }
    }

    private CompactionJobFactory createFactory() {
        AtomicInteger jobCounter = new AtomicInteger(0);
        return new CompactionJobFactory(instanceProperties, tableProperties, () -> "job-" + jobCounter.incrementAndGet());
    }

    private FilesInPartition createFilesInPartition(int numFiles) {
        return createFilesInPartitionWithId("root", numFiles);
    }

    private FilesInPartition createFilesInPartitionWithId(String partitionId, int numFiles) {
        PartitionTree partitions = new PartitionsBuilder(schema).singlePartition(partitionId).buildTree();
        List<FileReference> files = new ArrayList<>();
        for (int i = 0; i < numFiles; i++) {
            files.add(fileReference("file" + i + ".parquet", partitionId, 100L * (i + 1)));
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
