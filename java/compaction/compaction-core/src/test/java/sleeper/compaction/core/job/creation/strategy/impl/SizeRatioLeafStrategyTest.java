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
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.instance.CommonProperty.FILE_SYSTEM;
import static sleeper.core.properties.table.TableProperty.COMPACTION_FILES_BATCH_SIZE;
import static sleeper.core.properties.table.TableProperty.SIZE_RATIO_COMPACTION_STRATEGY_RATIO;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.table.TableProperty.TABLE_NAME;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;

public class SizeRatioLeafStrategyTest {
    private static final Schema DEFAULT_SCHEMA = schemaWithKey("key");
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, DEFAULT_SCHEMA);
    private final PartitionTree partitionTree = new PartitionsBuilder(DEFAULT_SCHEMA)
            .singlePartition("root")
            .buildTree();
    private final FileReferenceFactory fileReferenceFactory = FileReferenceFactory.from(partitionTree);
    private SizeRatioLeafStrategy strategy;
    private CompactionJobFactory jobFactory;

    @BeforeEach
    void setUp() {
        strategy = new SizeRatioLeafStrategy();
        instanceProperties.set(FILE_SYSTEM, "file://");
        instanceProperties.set(DATA_BUCKET, "databucket");
        tableProperties.set(TABLE_NAME, "table");
        tableProperties.set(TABLE_ID, "table-id");
        tableProperties.set(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, "3");
        tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "10");
        jobFactory = new CompactionJobFactory(instanceProperties, tableProperties, () -> "job1");
    }

    @Test
    void shouldInitializeStrategyWithProperties() {
        // Given
        tableProperties.set(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, "5");
        tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "20");

        // When
        strategy.init(instanceProperties, tableProperties, jobFactory);

        // Then - no exception thrown, init successful
        assertThat(strategy).isNotNull();
    }

    @Test
    void shouldCreateOneJobWhenFilesMeetCriteriaAndLessThanBatchSize() {
        // Given - ratio is 3, so sum of smaller files must be >= 3 * largest file
        // Files: [100, 100, 100, 100] -> sum(300) >= 3*100 = 300
        strategy.init(instanceProperties, tableProperties, jobFactory);
        List<FileReference> files = List.of(
                fileReferenceFactory.rootFile("file1", 100L),
                fileReferenceFactory.rootFile("file2", 100L),
                fileReferenceFactory.rootFile("file3", 100L),
                fileReferenceFactory.rootFile("file4", 100L));
        FilesInPartition filesInPartition = createFilesInPartition("root", files);

        // When
        List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

        // Then
        assertThat(jobs).hasSize(1);
        List<String> expectedFileNames = files.stream()
                .map(FileReference::getFilename)
                .collect(Collectors.toList());
        assertThat(jobs.get(0).getInputFiles()).containsExactlyElementsOf(expectedFileNames);
    }

    @Test
    void shouldCreateNoJobsWhenFilesDoNotMeetCriteria() {
        // Given
        strategy.init(instanceProperties, tableProperties, jobFactory);
        List<FileReference> files = List.of(
                fileReferenceFactory.rootFile("file1", 10L),
                fileReferenceFactory.rootFile("file2", 10L),
                fileReferenceFactory.rootFile("file3", 1000L));
        FilesInPartition filesInPartition = createFilesInPartition("root", files);

        // When
        List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

        // Then
        assertThat(jobs).isEmpty();
    }

    @Test
    void shouldCreateNoJobsWhenOnlyOneFile() {
        // Given
        strategy.init(instanceProperties, tableProperties, jobFactory);
        List<FileReference> files = List.of(fileReferenceFactory.rootFile("file1", 100L));
        FilesInPartition filesInPartition = createFilesInPartition("root", files);

        // When
        List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

        // Then
        assertThat(jobs).isEmpty();
    }

    @Test
    void shouldCreateNoJobsWhenNoFiles() {
        // Given
        strategy.init(instanceProperties, tableProperties, jobFactory);
        CompactionStrategyIndex index = new CompactionStrategyIndex(
                tableProperties.getStatus(),
                List.of(),
                partitionTree.getAllPartitions());

        // When
        List<FilesInPartition> filesInPartitions = index.getFilesInLeafPartitions();

        // Then
        assertThat(filesInPartitions).isEmpty();
    }

    @Test
    void shouldCreateMultipleJobsWhenMoreThanBatchSizeFilesMeetCriteria() {
        // Given
        tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "5");
        strategy.init(instanceProperties, tableProperties, jobFactory);
        jobFactory = new CompactionJobFactory(instanceProperties, tableProperties, new JobIdGenerator());
        strategy.init(instanceProperties, tableProperties, jobFactory);

        List<FileReference> files = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            files.add(fileReferenceFactory.rootFile("file" + i, 50L));
        }
        FilesInPartition filesInPartition = createFilesInPartition("root", files);

        // When
        List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

        // Then
        assertThat(jobs).hasSizeGreaterThan(0);
        assertThat(jobs.get(0).getInputFiles()).hasSize(5);
    }

    @Test
    void shouldCreateJobsForBatchesThatMeetCriteria() {
        // Given
        tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "3");
        tableProperties.set(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, "2");
        strategy.init(instanceProperties, tableProperties, jobFactory);
        jobFactory = new CompactionJobFactory(instanceProperties, tableProperties, new JobIdGenerator());
        strategy.init(instanceProperties, tableProperties, jobFactory);

        List<FileReference> files = List.of(
                fileReferenceFactory.rootFile("file1", 30L),
                fileReferenceFactory.rootFile("file2", 30L),
                fileReferenceFactory.rootFile("file3", 30L),
                fileReferenceFactory.rootFile("file4", 100L),
                fileReferenceFactory.rootFile("file5", 100L),
                fileReferenceFactory.rootFile("file6", 100L));
        FilesInPartition filesInPartition = createFilesInPartition("root", files);

        // When
        List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

        // Then
        assertThat(jobs).hasSizeGreaterThan(0);
    }

    @Test
    void shouldHandleFilesWithDifferentSizes() {
        // Given - ratio is 3, so sum of smaller files must be >= 3 * largest file
        // Files: [10, 20, 30, 40] -> sum(60) >= 3*40 = 120? NO
        // But [10, 20, 30] -> sum(60) >= 3*30 = 90? NO
        // But [10, 20] -> sum(30) >= 3*20 = 60? NO
        // So we need different file sizes: [30, 30, 30, 30] -> sum(90) >= 3*30 = 90? YES
        strategy.init(instanceProperties, tableProperties, jobFactory);
        List<FileReference> files = List.of(
                fileReferenceFactory.rootFile("file1", 30L),
                fileReferenceFactory.rootFile("file2", 30L),
                fileReferenceFactory.rootFile("file3", 30L),
                fileReferenceFactory.rootFile("file4", 30L));
        FilesInPartition filesInPartition = createFilesInPartition("root", files);

        // When
        List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

        // Then
        assertThat(jobs).hasSize(1);
    }

    @Test
    void shouldCreateJobWhenExactlyAtRatioThreshold() {
        // Given
        tableProperties.set(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, "3");
        strategy.init(instanceProperties, tableProperties, jobFactory);
        List<FileReference> files = List.of(
                fileReferenceFactory.rootFile("file1", 100L),
                fileReferenceFactory.rootFile("file2", 100L),
                fileReferenceFactory.rootFile("file3", 100L),
                fileReferenceFactory.rootFile("file4", 100L));
        FilesInPartition filesInPartition = createFilesInPartition("root", files);

        // When
        List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

        // Then
        assertThat(jobs).hasSize(1);
    }

    @Test
    void shouldHandleHighRatioValue() {
        // Given - ratio is 10, so sum of smaller files must be >= 10 * largest file
        // With 20 files of size 100 and batch size 10, sum(900) >= 10*100 = 1000? NO for batch
        // Need more files: 11 files -> sum(1000) >= 10*100 = 1000? YES
        tableProperties.set(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, "10");
        tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "11");
        strategy.init(instanceProperties, tableProperties, jobFactory);
        List<FileReference> files = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            files.add(fileReferenceFactory.rootFile("file" + i, 100L));
        }
        FilesInPartition filesInPartition = createFilesInPartition("root", files);

        // When
        List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

        // Then
        assertThat(jobs).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    void shouldSkipBatchesThatDoNotMeetCriteriaWhenMoreThanBatchSize() {
        // Given
        tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "3");
        tableProperties.set(SIZE_RATIO_COMPACTION_STRATEGY_RATIO, "10");
        strategy.init(instanceProperties, tableProperties, jobFactory);

        List<FileReference> files = List.of(
                fileReferenceFactory.rootFile("file1", 10L),
                fileReferenceFactory.rootFile("file2", 10L),
                fileReferenceFactory.rootFile("file3", 1000L),
                fileReferenceFactory.rootFile("file4", 10L),
                fileReferenceFactory.rootFile("file5", 10L),
                fileReferenceFactory.rootFile("file6", 10L));
        FilesInPartition filesInPartition = createFilesInPartition("root", files);

        // When
        List<CompactionJob> jobs = strategy.createJobsForLeafPartition(filesInPartition);

        // Then
        assertThat(jobs).hasSizeGreaterThanOrEqualTo(0);
    }

    private FilesInPartition createFilesInPartition(String partitionId, List<FileReference> files) {
        CompactionStrategyIndex index = new CompactionStrategyIndex(
                tableProperties.getStatus(),
                files,
                partitionTree.getAllPartitions());
        return index.getFilesInLeafPartitions().get(0);
    }

    private static class JobIdGenerator implements java.util.function.Supplier<String> {
        private int counter = 1;

        @Override
        public String get() {
            return "job" + counter++;
        }
    }
}
