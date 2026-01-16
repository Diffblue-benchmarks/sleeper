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
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.table.TableStatusTestHelper.uniqueIdAndName;

public class BasicCompactionStrategyClaudeTest {

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
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();

            // Then
            assertThat(strategy).isNotNull();
        }

        @Test
        void shouldExtendDelegatingCompactionStrategy() {
            // When
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();

            // Then
            assertThat(strategy).isInstanceOf(DelegatingCompactionStrategy.class);
        }

        @Test
        void shouldImplementCompactionStrategy() {
            // When
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();

            // Then
            assertThat(strategy).isInstanceOf(CompactionStrategy.class);
        }
    }

    @Nested
    @DisplayName("Integration with BasicLeafStrategy")
    class IntegrationWithBasicLeafStrategy {

        @Test
        void shouldReturnEmptyListWhenNoFiles() {
            // Given
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createEmptyIndex();

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldNotCreateJobsWhenFilesLessThanBatchSize() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 5);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(3);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldCreateOneJobWhenFilesEqualBatchSize() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(3);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldCreateOneJobWhenFilesExceedBatchSize() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 3);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(4);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(3);
        }

        @Test
        void shouldCreateMultipleJobsWhenFilesExceedTwoBatches() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(5);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(2);
            assertThat(jobs.get(0).getInputFiles()).hasSize(2);
            assertThat(jobs.get(1).getInputFiles()).hasSize(2);
        }

        @Test
        void shouldCreateJobsForMultiplePartitions() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithMultiplePartitionsAndFiles(2, 3);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(2); // One job per partition (3 files, batch size 2 = 1 job each)
        }

        @Test
        void shouldOrderFilesByRecordCountAscending() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();

            // Create files with different record counts
            PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
            List<FileReference> files = List.of(
                    fileReference("large.parquet", "root", 1000L),
                    fileReference("small.parquet", "root", 100L),
                    fileReference("medium.parquet", "root", 500L));
            CompactionStrategyIndex index = new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
            // Files should be ordered by record count ascending
            assertThat(jobs.get(0).getInputFiles())
                    .containsExactly("small.parquet", "medium.parquet");
        }

        @Test
        void shouldAssignCorrectPartitionIdToJob() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(2);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getPartitionId()).isEqualTo("root");
        }

        @Test
        void shouldUseTableIdInCompactionJob() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(2);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getTableId()).isEqualTo(tableProperties.getStatus().getTableUniqueId());
        }
    }

    @Nested
    @DisplayName("Batch size configuration")
    class BatchSizeConfiguration {

        @Test
        void shouldUseBatchSizeOfOne() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 1);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(3);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(3);
            for (CompactionJob job : jobs) {
                assertThat(job.getInputFiles()).hasSize(1);
            }
        }

        @Test
        void shouldUseLargeBatchSize() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 100);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(100);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(100);
        }

        @Test
        void shouldHandleDefaultBatchSize() {
            // Given - using default batch size (not explicitly set)
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            int defaultBatchSize = tableProperties.getInt(COMPACTION_FILES_BATCH_SIZE);
            CompactionStrategyIndex index = createIndexWithFiles(defaultBatchSize);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).hasSize(defaultBatchSize);
        }
    }

    @Nested
    @DisplayName("Reusability")
    class Reusability {

        @Test
        void shouldBeReusableForMultipleCalls() {
            // Given
            tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);
            BasicCompactionStrategy strategy = new BasicCompactionStrategy();
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index1 = createIndexWithFiles(2);
            CompactionStrategyIndex index2 = createIndexWithFiles(4);

            // When
            List<CompactionJob> jobs1 = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index1);
            List<CompactionJob> jobs2 = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index2);

            // Then
            assertThat(jobs1).hasSize(1);
            assertThat(jobs2).hasSize(2);
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

    private CompactionStrategyIndex createIndexWithFiles(int numFiles) {
        PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
        List<FileReference> files = new ArrayList<>();
        for (int i = 0; i < numFiles; i++) {
            files.add(fileReference("file" + i + ".parquet", "root", 100L * (i + 1)));
        }
        return new CompactionStrategyIndex(tableStatus, files, partitions.getAllPartitions());
    }

    private CompactionStrategyIndex createIndexWithMultiplePartitionsAndFiles(int numPartitions, int filesPerPartition) {
        PartitionsBuilder builder = new PartitionsBuilder(schema);
        if (numPartitions == 1) {
            builder.singlePartition("partition-0");
        } else {
            builder.rootFirst("root");
            String previousPartition = "root";
            for (int i = 0; i < numPartitions; i++) {
                String leftPartition = "partition-" + i;
                String rightPartition = (i == numPartitions - 1) ? "partition-final" : "split-" + i;
                builder.splitToNewChildren(previousPartition, leftPartition, rightPartition, "split" + i);
                if (i < numPartitions - 1) {
                    previousPartition = rightPartition;
                }
            }
        }
        PartitionTree partitions = builder.buildTree();

        List<FileReference> files = new ArrayList<>();
        List<String> leafPartitionIds = partitions.getAllPartitions().stream()
                .filter(p -> p.isLeafPartition())
                .map(p -> p.getId())
                .limit(numPartitions)
                .toList();

        for (String partitionId : leafPartitionIds) {
            for (int i = 0; i < filesPerPartition; i++) {
                files.add(fileReference("file-" + partitionId + "-" + i + ".parquet", partitionId, 100L * (i + 1)));
            }
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
}
