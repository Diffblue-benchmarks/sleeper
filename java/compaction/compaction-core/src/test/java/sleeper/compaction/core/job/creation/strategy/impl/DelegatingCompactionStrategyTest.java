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
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobFactory;
import sleeper.compaction.core.job.creation.strategy.DelegatingCompactionStrategy;
import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.CONFIG_BUCKET;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.instance.CommonProperty.FILE_SYSTEM;
import static sleeper.core.properties.table.TableProperty.COMPACTION_FILES_BATCH_SIZE;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.table.TableProperty.TABLE_NAME;

public class DelegatingCompactionStrategyTest extends CompactionStrategyTestBase {

    @BeforeEach
    void setUp() {
        instanceProperties.set(FILE_SYSTEM, "file://");
        instanceProperties.set(CONFIG_BUCKET, "bucket");
        instanceProperties.set(DATA_BUCKET, "databucket");
        tableProperties.set(TABLE_NAME, "table");
        tableProperties.set(TABLE_ID, "table-id");
        tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "2");
    }

    @Test
    void shouldReturnNoJobsWhenShouldCreateJobsStrategyDisallowsJobCreation() {
        // Given - a ShouldCreateJobsStrategy that returns 0 (no jobs allowed)
        strategy = new DelegatingCompactionStrategy(new BasicLeafStrategy(), filesInPartition -> 0);
        PartitionTree partitionTree = new PartitionsBuilder(DEFAULT_SCHEMA)
                .singlePartition("root")
                .buildTree();
        FileReferenceFactory factory = FileReferenceFactory.from(partitionTree);
        FileReference file1 = factory.rootFile("file1", 100L);
        FileReference file2 = factory.rootFile("file2", 200L);

        // When
        List<CompactionJob> compactionJobs = createCompactionJobs(List.of(file1, file2), partitionTree.getAllPartitions());

        // Then
        assertThat(compactionJobs).isEmpty();
    }

    @Test
    void shouldTrimJobsToMaxAllowedByStrategy() {
        // Given - a ShouldCreateJobsStrategy that allows only 1 job, but leaf strategy would create 2
        strategy = new DelegatingCompactionStrategy(new BasicLeafStrategy(), filesInPartition -> 1);
        PartitionTree partitionTree = new PartitionsBuilder(DEFAULT_SCHEMA)
                .singlePartition("root")
                .buildTree();
        FileReferenceFactory factory = FileReferenceFactory.from(partitionTree);
        FileReference file1 = factory.rootFile("file1", 100L);
        FileReference file2 = factory.rootFile("file2", 200L);
        FileReference file3 = factory.rootFile("file3", 300L);
        FileReference file4 = factory.rootFile("file4", 400L);
        CompactionJobFactory jobFactory = jobFactoryWithIncrementingJobIds();
        CompactionJobFactory assertionFactory = jobFactoryWithIncrementingJobIds();

        // When
        List<CompactionJob> compactionJobs = createCompactionJobs(jobFactory, List.of(file1, file2, file3, file4), partitionTree.getAllPartitions());

        // Then - only 1 job should be returned even though 2 could be created
        assertThat(compactionJobs).hasSize(1);
        assertThat(compactionJobs).containsExactly(
                assertionFactory.createCompactionJob("job1", List.of(file1, file2), "root"));
    }
}
