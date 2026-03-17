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

import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.core.statestore.FileReference;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.table.TableProperty.SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION;

public class SizeRatioShouldCreateJobsStrategyTest extends CompactionStrategyTestBase {

    private SizeRatioShouldCreateJobsStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new SizeRatioShouldCreateJobsStrategy();
    }

    @Test
    void shouldInitializeMaxConcurrentJobsPerPartitionFromTableProperties() {
        // Given
        tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 5);

        // When
        strategy.init(instanceProperties, tableProperties);
        FilesInPartition filesInPartition = createFilesInPartition(List.of());
        long maxJobs = strategy.maxCompactionJobsToCreate(filesInPartition);

        // Then
        assertThat(maxJobs).isEqualTo(5);
    }

    @Test
    void shouldReturnMaxJobsWhenNoCurrentCompactionJobs() {
        // Given
        tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 3);
        strategy.init(instanceProperties, tableProperties);
        FilesInPartition filesInPartition = createFilesInPartition(List.of());

        // When
        long maxJobs = strategy.maxCompactionJobsToCreate(filesInPartition);

        // Then
        assertThat(maxJobs).isEqualTo(3);
    }

    @Test
    void shouldReturnZeroWhenCurrentJobsEqualMaxLimit() {
        // Given
        tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 2);
        strategy.init(instanceProperties, tableProperties);
        List<FileReference> filesWithJobId = List.of(
                fileReferenceWithJobId("file1", "job1"),
                fileReferenceWithJobId("file2", "job2"));
        FilesInPartition filesInPartition = createFilesInPartition(filesWithJobId);

        // When
        long maxJobs = strategy.maxCompactionJobsToCreate(filesInPartition);

        // Then
        assertThat(maxJobs).isEqualTo(0);
    }

    @Test
    void shouldReturnZeroWhenCurrentJobsExceedMaxLimit() {
        // Given
        tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 2);
        strategy.init(instanceProperties, tableProperties);
        List<FileReference> filesWithJobId = List.of(
                fileReferenceWithJobId("file1", "job1"),
                fileReferenceWithJobId("file2", "job2"),
                fileReferenceWithJobId("file3", "job3"));
        FilesInPartition filesInPartition = createFilesInPartition(filesWithJobId);

        // When
        long maxJobs = strategy.maxCompactionJobsToCreate(filesInPartition);

        // Then
        assertThat(maxJobs).isEqualTo(0);
    }

    @Test
    void shouldReturnDifferenceWhenCurrentJobsLessThanMaxLimit() {
        // Given
        tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 5);
        strategy.init(instanceProperties, tableProperties);
        List<FileReference> filesWithJobId = List.of(
                fileReferenceWithJobId("file1", "job1"),
                fileReferenceWithJobId("file2", "job2"));
        FilesInPartition filesInPartition = createFilesInPartition(filesWithJobId);

        // When
        long maxJobs = strategy.maxCompactionJobsToCreate(filesInPartition);

        // Then
        assertThat(maxJobs).isEqualTo(3);
    }

    @Test
    void shouldCountDistinctJobIdsWhenMultipleFilesHaveSameJobId() {
        // Given
        tableProperties.setNumber(SIZE_RATIO_COMPACTION_STRATEGY_MAX_CONCURRENT_JOBS_PER_PARTITION, 5);
        strategy.init(instanceProperties, tableProperties);
        List<FileReference> filesWithJobId = List.of(
                fileReferenceWithJobId("file1", "job1"),
                fileReferenceWithJobId("file2", "job1"),
                fileReferenceWithJobId("file3", "job2"));
        FilesInPartition filesInPartition = createFilesInPartition(filesWithJobId);

        // When
        long maxJobs = strategy.maxCompactionJobsToCreate(filesInPartition);

        // Then
        assertThat(maxJobs).isEqualTo(3);
    }

    private FilesInPartition createFilesInPartition(List<FileReference> filesWithJobId) {
        // Need at least one file without jobId to create a FilesInPartition
        FileReference dummyFile = fileReferenceFactory.rootFile("dummy", 1L);
        List<FileReference> allFiles = new java.util.ArrayList<>();
        allFiles.add(dummyFile);
        allFiles.addAll(filesWithJobId);

        CompactionStrategyIndex index = new CompactionStrategyIndex(
                tableProperties.getStatus(),
                allFiles,
                partitionTree.getAllPartitions());
        return index.getFilesInLeafPartitions().get(0);
    }

    private FileReference fileReferenceWithJobId(String filename, String jobId) {
        return FileReference.builder()
                .filename(filename)
                .partitionId("root")
                .jobId(jobId)
                .numberOfRecords(100L)
                .build();
    }
}
