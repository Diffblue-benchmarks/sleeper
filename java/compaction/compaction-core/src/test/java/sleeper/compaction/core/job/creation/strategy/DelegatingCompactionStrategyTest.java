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
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobFactory;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.compaction.core.job.creation.strategy.impl.CompactionStrategyTestBase;
import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.CONFIG_BUCKET;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.instance.CommonProperty.FILE_SYSTEM;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.table.TableProperty.TABLE_NAME;

public class DelegatingCompactionStrategyTest extends CompactionStrategyTestBase {

    private TestLeafPartitionCompactionStrategy leafStrategy;

    @BeforeEach
    void setUp() {
        leafStrategy = new TestLeafPartitionCompactionStrategy();
        instanceProperties.set(FILE_SYSTEM, "file://");
        instanceProperties.set(CONFIG_BUCKET, "bucket");
        instanceProperties.set(DATA_BUCKET, "databucket");
        tableProperties.set(TABLE_NAME, "table");
        tableProperties.set(TABLE_ID, "table-id");
    }

    @Test
    public void shouldCreateStrategyWithDefaultShouldCreateJobsStrategy() {
        // Given
        strategy = new DelegatingCompactionStrategy(leafStrategy);
        PartitionTree partitionTree = new PartitionsBuilder(DEFAULT_SCHEMA)
                .singlePartition("root")
                .buildTree();
        FileReferenceFactory factory = FileReferenceFactory.from(partitionTree);
        List<FileReference> fileReferences = List.of(
                factory.rootFile("file1", 100L),
                factory.rootFile("file2", 200L));

        // When
        List<CompactionJob> jobs = createCompactionJobs(fileReferences, partitionTree.getAllPartitions());

        // Then
        assertThat(jobs).hasSize(2);
        assertThat(leafStrategy.initCalled).isTrue();
    }

    @Test
    public void shouldCreateStrategyWithCustomShouldCreateJobsStrategy() {
        // Given
        TestShouldCreateJobsStrategy shouldCreateJobsStrategy = new TestShouldCreateJobsStrategy(1);
        strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
        PartitionTree partitionTree = new PartitionsBuilder(DEFAULT_SCHEMA)
                .singlePartition("root")
                .buildTree();
        FileReferenceFactory factory = FileReferenceFactory.from(partitionTree);
        List<FileReference> fileReferences = List.of(
                factory.rootFile("file1", 100L),
                factory.rootFile("file2", 200L));

        // When
        List<CompactionJob> jobs = createCompactionJobs(fileReferences, partitionTree.getAllPartitions());

        // Then
        assertThat(jobs).hasSize(1);
        assertThat(shouldCreateJobsStrategy.initCalled).isTrue();
    }

    @Test
    public void shouldCallInitOnBothStrategies() {
        // Given
        TestShouldCreateJobsStrategy shouldCreateJobsStrategy = new TestShouldCreateJobsStrategy(10);
        strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
        PartitionTree partitionTree = new PartitionsBuilder(DEFAULT_SCHEMA)
                .singlePartition("root")
                .buildTree();
        FileReferenceFactory factory = FileReferenceFactory.from(partitionTree);
        List<FileReference> fileReferences = List.of(factory.rootFile("file1", 100L));

        // When
        createCompactionJobs(fileReferences, partitionTree.getAllPartitions());

        // Then
        assertThat(leafStrategy.initCalled).isTrue();
        assertThat(shouldCreateJobsStrategy.initCalled).isTrue();
    }

    @Test
    public void shouldCreateJobsForAllLeafPartitions() {
        // Given
        strategy = new DelegatingCompactionStrategy(leafStrategy);
        PartitionTree partitionTree = new PartitionsBuilder(DEFAULT_SCHEMA)
                .rootFirst("root")
                .splitToNewChildren("root", "left", "right", 100L)
                .buildTree();
        FileReferenceFactory factory = FileReferenceFactory.from(partitionTree);
        List<FileReference> fileReferences = List.of(
                factory.partitionFile("left", "file1", 100L),
                factory.partitionFile("right", "file2", 200L));

        // When
        List<CompactionJob> jobs = createCompactionJobs(fileReferences, partitionTree.getAllPartitions());

        // Then
        assertThat(jobs).hasSize(2);
    }

    @Test
    public void shouldReturnEmptyListWhenMaxJobsIsZero() {
        // Given
        TestShouldCreateJobsStrategy shouldCreateJobsStrategy = new TestShouldCreateJobsStrategy(0);
        strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
        PartitionTree partitionTree = new PartitionsBuilder(DEFAULT_SCHEMA)
                .singlePartition("root")
                .buildTree();
        FileReferenceFactory factory = FileReferenceFactory.from(partitionTree);
        List<FileReference> fileReferences = List.of(
                factory.rootFile("file1", 100L),
                factory.rootFile("file2", 200L));

        // When
        List<CompactionJob> jobs = createCompactionJobs(fileReferences, partitionTree.getAllPartitions());

        // Then
        assertThat(jobs).isEmpty();
    }

    @Test
    public void shouldTrimJobsWhenExceedingMaxLimit() {
        // Given
        TestShouldCreateJobsStrategy shouldCreateJobsStrategy = new TestShouldCreateJobsStrategy(2);
        strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
        PartitionTree partitionTree = new PartitionsBuilder(DEFAULT_SCHEMA)
                .singlePartition("root")
                .buildTree();
        FileReferenceFactory factory = FileReferenceFactory.from(partitionTree);
        List<FileReference> fileReferences = List.of(
                factory.rootFile("file1", 100L),
                factory.rootFile("file2", 200L),
                factory.rootFile("file3", 300L),
                factory.rootFile("file4", 400L));

        // When
        List<CompactionJob> jobs = createCompactionJobs(fileReferences, partitionTree.getAllPartitions());

        // Then
        assertThat(jobs).hasSize(2);
    }

    @Test
    public void shouldNotTrimJobsWhenWithinMaxLimit() {
        // Given
        TestShouldCreateJobsStrategy shouldCreateJobsStrategy = new TestShouldCreateJobsStrategy(10);
        strategy = new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
        PartitionTree partitionTree = new PartitionsBuilder(DEFAULT_SCHEMA)
                .singlePartition("root")
                .buildTree();
        FileReferenceFactory factory = FileReferenceFactory.from(partitionTree);
        List<FileReference> fileReferences = List.of(
                factory.rootFile("file1", 100L),
                factory.rootFile("file2", 200L));

        // When
        List<CompactionJob> jobs = createCompactionJobs(fileReferences, partitionTree.getAllPartitions());

        // Then
        assertThat(jobs).hasSize(2);
    }

    private static class TestLeafPartitionCompactionStrategy implements LeafPartitionCompactionStrategy {
        private boolean initCalled = false;
        private InstanceProperties instanceProperties;
        private TableProperties tableProperties;
        private CompactionJobFactory factory;

        @Override
        public void init(InstanceProperties instanceProperties, TableProperties tableProperties, CompactionJobFactory factory) {
            this.initCalled = true;
            this.instanceProperties = instanceProperties;
            this.tableProperties = tableProperties;
            this.factory = factory;
        }

        @Override
        public List<CompactionJob> createJobsForLeafPartition(FilesInPartition filesInPartition) {
            List<CompactionJob> jobs = new ArrayList<>();
            for (FileReference file : filesInPartition.getFilesWithNoJobIdInAscendingOrder()) {
                jobs.add(factory.createCompactionJob(List.of(file), filesInPartition.getPartitionId()));
            }
            return jobs;
        }
    }

    private static class TestShouldCreateJobsStrategy implements ShouldCreateJobsStrategy {
        private final long maxJobs;
        private boolean initCalled = false;

        TestShouldCreateJobsStrategy(long maxJobs) {
            this.maxJobs = maxJobs;
        }

        @Override
        public void init(InstanceProperties instanceProperties, TableProperties tableProperties) {
            this.initCalled = true;
        }

        @Override
        public long maxCompactionJobsToCreate(FilesInPartition filesInPartition) {
            return maxJobs;
        }
    }
}
