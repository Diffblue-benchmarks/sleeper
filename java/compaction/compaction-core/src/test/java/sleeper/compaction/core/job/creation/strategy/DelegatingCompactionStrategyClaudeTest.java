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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobFactory;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.table.TableStatusTestHelper.uniqueIdAndName;

public class DelegatingCompactionStrategyClaudeTest {

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
    @DisplayName("Constructor with LeafPartitionCompactionStrategy only")
    class ConstructorWithLeafStrategyOnly {

        @Test
        void shouldCreateStrategyWithLeafStrategyOnly() {
            // Given
            LeafPartitionCompactionStrategy leafStrategy = createNoOpLeafStrategy();

            // When
            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy);

            // Then
            assertThat(strategy).isNotNull();
        }

        @Test
        void shouldUseDefaultShouldCreateJobsStrategyThatAlwaysReturnsMaxValue() {
            // Given
            AtomicBoolean leafStrategyInvoked = new AtomicBoolean(false);
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    leafStrategyInvoked.set(true);
                    return List.of();
                }
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(1);

            // When
            strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then - leaf strategy should have been invoked (default ShouldCreateJobsStrategy returns Long.MAX_VALUE)
            assertThat(leafStrategyInvoked.get()).isTrue();
        }
    }

    @Nested
    @DisplayName("Constructor with LeafPartitionCompactionStrategy and ShouldCreateJobsStrategy")
    class ConstructorWithBothStrategies {

        @Test
        void shouldCreateStrategyWithBothStrategies() {
            // Given
            LeafPartitionCompactionStrategy leafStrategy = createNoOpLeafStrategy();
            ShouldCreateJobsStrategy shouldCreateJobsStrategy = ShouldCreateJobsStrategy.yes();

            // When
            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);

            // Then
            assertThat(strategy).isNotNull();
        }

        @Test
        void shouldUseProvidedShouldCreateJobsStrategy() {
            // Given
            AtomicBoolean shouldCreateInvoked = new AtomicBoolean(false);
            LeafPartitionCompactionStrategy leafStrategy = createNoOpLeafStrategy();
            ShouldCreateJobsStrategy shouldCreateJobsStrategy = filesInPartition -> {
                shouldCreateInvoked.set(true);
                return Long.MAX_VALUE;
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(1);

            // When
            strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(shouldCreateInvoked.get()).isTrue();
        }

        @Test
        void shouldInitializeShouldCreateJobsStrategy() {
            // Given
            AtomicBoolean initInvoked = new AtomicBoolean(false);
            LeafPartitionCompactionStrategy leafStrategy = createNoOpLeafStrategy();
            ShouldCreateJobsStrategy shouldCreateJobsStrategy = new ShouldCreateJobsStrategy() {
                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties) {
                    initInvoked.set(true);
                }

                @Override
                public long maxCompactionJobsToCreate(FilesInPartition filesInPartition) {
                    return Long.MAX_VALUE;
                }
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(1);

            // When
            strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(initInvoked.get()).isTrue();
        }
    }

    @Nested
    @DisplayName("createCompactionJobs")
    class CreateCompactionJobs {

        @Test
        void shouldReturnEmptyListWhenNoFilesInIndex() {
            // Given
            LeafPartitionCompactionStrategy leafStrategy = createNoOpLeafStrategy();
            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createEmptyIndex();

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).isEmpty();
        }

        @Test
        void shouldInitializeLeafStrategyBeforeCreatingJobs() {
            // Given
            AtomicBoolean initInvoked = new AtomicBoolean(false);
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                    initInvoked.set(true);
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    return List.of();
                }
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(1);

            // When
            strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(initInvoked.get()).isTrue();
        }

        @Test
        void shouldDelegateToLeafStrategyForEachPartition() {
            // Given
            AtomicInteger invokeCount = new AtomicInteger(0);
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    invokeCount.incrementAndGet();
                    return List.of();
                }
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithMultiplePartitions(3);

            // When
            strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(invokeCount.get()).isEqualTo(3);
        }

        @Test
        void shouldReturnJobsCreatedByLeafStrategy() {
            // Given
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                private CompactionJobFactory factory;

                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                    this.factory = factory;
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    return List.of(factory.createCompactionJobWithFilenames(
                            "job-1",
                            List.of("file1.parquet", "file2.parquet"),
                            filesInPartition.getPartitionId()));
                }
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(2);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getId()).isEqualTo("job-1");
            assertThat(jobs.get(0).getInputFiles()).containsExactly("file1.parquet", "file2.parquet");
        }

        @Test
        void shouldAggregateJobsFromMultiplePartitions() {
            // Given
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                private CompactionJobFactory factory;
                private int jobCounter = 0;

                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                    this.factory = factory;
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    String jobId = "job-" + (jobCounter++);
                    return List.of(factory.createCompactionJobWithFilenames(
                            jobId,
                            List.of("file.parquet"),
                            filesInPartition.getPartitionId()));
                }
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithMultiplePartitions(3);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(3);
            assertThat(jobs)
                    .extracting(CompactionJob::getId)
                    .containsExactly("job-0", "job-1", "job-2");
        }

        @Test
        void shouldNotCreateJobsWhenShouldCreateJobsStrategyReturnsZero() {
            // Given
            AtomicBoolean leafStrategyInvoked = new AtomicBoolean(false);
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    leafStrategyInvoked.set(true);
                    return List.of();
                }
            };
            ShouldCreateJobsStrategy shouldCreateJobsStrategy = filesInPartition -> 0;

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(2);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).isEmpty();
            assertThat(leafStrategyInvoked.get()).isFalse();
        }

        @Test
        void shouldNotCreateJobsWhenShouldCreateJobsStrategyReturnsNegative() {
            // Given
            AtomicBoolean leafStrategyInvoked = new AtomicBoolean(false);
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    leafStrategyInvoked.set(true);
                    return List.of();
                }
            };
            ShouldCreateJobsStrategy shouldCreateJobsStrategy = filesInPartition -> -1;

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(2);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).isEmpty();
            assertThat(leafStrategyInvoked.get()).isFalse();
        }

        @Test
        void shouldLimitJobsToMaxWhenStrategyCreatesMoreThanAllowed() {
            // Given
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                private CompactionJobFactory factory;

                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                    this.factory = factory;
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    List<CompactionJob> jobs = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        jobs.add(factory.createCompactionJobWithFilenames(
                                "job-" + i,
                                List.of("file" + i + ".parquet"),
                                filesInPartition.getPartitionId()));
                    }
                    return jobs;
                }
            };
            ShouldCreateJobsStrategy shouldCreateJobsStrategy = filesInPartition -> 2;

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(5);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(2);
            assertThat(jobs)
                    .extracting(CompactionJob::getId)
                    .containsExactly("job-0", "job-1");
        }

        @Test
        void shouldAllowExactNumberOfJobsWhenLimitEqualsJobCount() {
            // Given
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                private CompactionJobFactory factory;

                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                    this.factory = factory;
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    List<CompactionJob> jobs = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        jobs.add(factory.createCompactionJobWithFilenames(
                                "job-" + i,
                                List.of("file" + i + ".parquet"),
                                filesInPartition.getPartitionId()));
                    }
                    return jobs;
                }
            };
            ShouldCreateJobsStrategy shouldCreateJobsStrategy = filesInPartition -> 3;

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(3);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(3);
        }

        @Test
        void shouldHandlePartitionsWithDifferentJobLimits() {
            // Given
            AtomicInteger partitionCounter = new AtomicInteger(0);
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                private CompactionJobFactory factory;

                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                    this.factory = factory;
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    List<CompactionJob> jobs = new ArrayList<>();
                    int partitionNum = partitionCounter.getAndIncrement();
                    for (int i = 0; i < 5; i++) {
                        jobs.add(factory.createCompactionJobWithFilenames(
                                "job-p" + partitionNum + "-" + i,
                                List.of("file.parquet"),
                                filesInPartition.getPartitionId()));
                    }
                    return jobs;
                }
            };

            AtomicInteger limitCounter = new AtomicInteger(0);
            ShouldCreateJobsStrategy shouldCreateJobsStrategy = filesInPartition -> {
                // First partition allows 1, second allows 2, third allows 3
                return limitCounter.incrementAndGet();
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithMultiplePartitions(3);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(jobs).hasSize(6); // 1 + 2 + 3
        }

        @Test
        void shouldPassCorrectPropertiesToLeafStrategyInit() {
            // Given
            List<InstanceProperties> capturedInstanceProps = new ArrayList<>();
            List<TableProperties> capturedTableProps = new ArrayList<>();
            List<CompactionJobFactory> capturedFactories = new ArrayList<>();

            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                    capturedInstanceProps.add(instanceProperties);
                    capturedTableProps.add(tableProperties);
                    capturedFactories.add(factory);
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    return List.of();
                }
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(1);

            // When
            strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(capturedInstanceProps).containsExactly(instanceProperties);
            assertThat(capturedTableProps).containsExactly(tableProperties);
            assertThat(capturedFactories).containsExactly(factory);
        }

        @Test
        void shouldPassCorrectPropertiesToShouldCreateJobsStrategyInit() {
            // Given
            List<InstanceProperties> capturedInstanceProps = new ArrayList<>();
            List<TableProperties> capturedTableProps = new ArrayList<>();

            LeafPartitionCompactionStrategy leafStrategy = createNoOpLeafStrategy();
            ShouldCreateJobsStrategy shouldCreateJobsStrategy = new ShouldCreateJobsStrategy() {
                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties) {
                    capturedInstanceProps.add(instanceProperties);
                    capturedTableProps.add(tableProperties);
                }

                @Override
                public long maxCompactionJobsToCreate(FilesInPartition filesInPartition) {
                    return Long.MAX_VALUE;
                }
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(1);

            // When
            strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(capturedInstanceProps).containsExactly(instanceProperties);
            assertThat(capturedTableProps).containsExactly(tableProperties);
        }

        @Test
        void shouldPassCorrectFilesInPartitionToLeafStrategy() {
            // Given
            List<FilesInPartition> capturedPartitions = new ArrayList<>();

            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    capturedPartitions.add(filesInPartition);
                    return List.of();
                }
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(2);

            // When
            strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(capturedPartitions).hasSize(1);
            assertThat(capturedPartitions.get(0).getPartitionId()).isEqualTo("root");
            assertThat(capturedPartitions.get(0).getFilesWithNoJobIdInAscendingOrder()).hasSize(2);
        }

        @Test
        void shouldPassCorrectFilesInPartitionToShouldCreateJobsStrategy() {
            // Given
            List<FilesInPartition> capturedPartitions = new ArrayList<>();

            LeafPartitionCompactionStrategy leafStrategy = createNoOpLeafStrategy();
            ShouldCreateJobsStrategy shouldCreateJobsStrategy = filesInPartition -> {
                capturedPartitions.add(filesInPartition);
                return Long.MAX_VALUE;
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(2);

            // When
            strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then
            assertThat(capturedPartitions).hasSize(1);
            assertThat(capturedPartitions.get(0).getPartitionId()).isEqualTo("root");
        }

        @Test
        void shouldReturnMutableList() {
            // Given
            LeafPartitionCompactionStrategy leafStrategy = new LeafPartitionCompactionStrategy() {
                private CompactionJobFactory factory;

                @Override
                public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
                    this.factory = factory;
                }

                @Override
                public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                    return List.of(factory.createCompactionJobWithFilenames(
                            "job-1",
                            List.of("file.parquet"),
                            filesInPartition.getPartitionId()));
                }
            };

            DelegatingCompactionStrategy strategy = new DelegatingCompactionStrategy(leafStrategy);
            CompactionJobFactory factory = createFactory();
            CompactionStrategyIndex index = createIndexWithFiles(1);

            // When
            List<CompactionJob> jobs = strategy.createCompactionJobs(instanceProperties, tableProperties, factory, index);

            // Then - list should be mutable (can add elements)
            CompactionJob additionalJob = factory.createCompactionJobWithFilenames("job-2", List.of("file2.parquet"), "root");
            jobs.add(additionalJob);
            assertThat(jobs).hasSize(2);
        }
    }

    private LeafPartitionCompactionStrategy createNoOpLeafStrategy() {
        return new LeafPartitionCompactionStrategy() {
            @Override
            public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
            }

            @Override
            public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
                return List.of();
            }
        };
    }

    private CompactionJobFactory createFactory() {
        return new CompactionJobFactory(instanceProperties, tableProperties, () -> "test-job-id");
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

    private CompactionStrategyIndex createIndexWithMultiplePartitions(int numPartitions) {
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
            files.add(fileReference("file-" + partitionId + ".parquet", partitionId, 100L));
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
