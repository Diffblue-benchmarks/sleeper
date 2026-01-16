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
package sleeper.compaction.core.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.FileReference;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.instance.CommonProperty.FILE_SYSTEM;
import static sleeper.core.properties.table.TableProperty.ITERATOR_CLASS_NAME;
import static sleeper.core.properties.table.TableProperty.ITERATOR_CONFIG;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;

public class CompactionJobFactoryClaudeTest {

    private static final Schema SCHEMA = schemaWithKey("key");
    private static final String PARTITION_ID = "root";

    private InstanceProperties instanceProperties;
    private TableProperties tableProperties;

    @BeforeEach
    void setUp() {
        instanceProperties = createTestInstanceProperties();
        tableProperties = createTestTableProperties(instanceProperties, SCHEMA);
    }

    @Nested
    @DisplayName("Constructor with default job ID supplier")
    class ConstructorWithDefaultJobIdSupplier {

        @Test
        void shouldCreateFactoryWithDefaultJobIdSupplier() {
            // Given/When
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);

            // Then
            assertThat(factory).isNotNull();
        }

        @Test
        void shouldGenerateUniqueJobIdsWhenUsingDefaultSupplier() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);
            FileReference file1 = createFileReference("file1.parquet", PARTITION_ID);
            FileReference file2 = createFileReference("file2.parquet", PARTITION_ID);

            // When
            CompactionJob job1 = factory.createCompactionJob(List.of(file1), PARTITION_ID);
            CompactionJob job2 = factory.createCompactionJob(List.of(file2), PARTITION_ID);

            // Then
            assertThat(job1.getId()).isNotEqualTo(job2.getId());
        }
    }

    @Nested
    @DisplayName("Constructor with custom job ID supplier")
    class ConstructorWithCustomJobIdSupplier {

        @Test
        void shouldCreateFactoryWithCustomJobIdSupplier() {
            // Given
            AtomicInteger counter = new AtomicInteger(0);

            // When
            CompactionJobFactory factory = new CompactionJobFactory(
                    instanceProperties, tableProperties,
                    () -> "job-" + counter.incrementAndGet());

            // Then
            assertThat(factory).isNotNull();
        }

        @Test
        void shouldUseCustomJobIdSupplierForJobCreation() {
            // Given
            AtomicInteger counter = new AtomicInteger(0);
            CompactionJobFactory factory = new CompactionJobFactory(
                    instanceProperties, tableProperties,
                    () -> "custom-job-" + counter.incrementAndGet());
            FileReference file = createFileReference("file.parquet", PARTITION_ID);

            // When
            CompactionJob job1 = factory.createCompactionJob(List.of(file), PARTITION_ID);
            CompactionJob job2 = factory.createCompactionJob(List.of(file), PARTITION_ID);

            // Then
            assertThat(job1.getId()).isEqualTo("custom-job-1");
            assertThat(job2.getId()).isEqualTo("custom-job-2");
        }
    }

    @Nested
    @DisplayName("getOutputFilePrefix")
    class GetOutputFilePrefix {

        @Test
        void shouldReturnOutputFilePrefixBasedOnInstanceAndTableProperties() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);
            String expectedPrefix = instanceProperties.get(FILE_SYSTEM)
                    + instanceProperties.get(DATA_BUCKET)
                    + "/" + tableProperties.get(TABLE_ID);

            // When
            String outputFilePrefix = factory.getOutputFilePrefix();

            // Then
            assertThat(outputFilePrefix).isEqualTo(expectedPrefix);
        }

        @Test
        void shouldReturnDifferentPrefixesForDifferentTables() {
            // Given
            TableProperties tableProperties2 = createTestTableProperties(instanceProperties, SCHEMA);
            CompactionJobFactory factory1 = new CompactionJobFactory(instanceProperties, tableProperties);
            CompactionJobFactory factory2 = new CompactionJobFactory(instanceProperties, tableProperties2);

            // When/Then
            assertThat(factory1.getOutputFilePrefix()).isNotEqualTo(factory2.getOutputFilePrefix());
        }
    }

    @Nested
    @DisplayName("createCompactionJob with FileReferences")
    class CreateCompactionJobWithFileReferences {

        @Test
        void shouldCreateCompactionJobWithSingleFile() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(
                    instanceProperties, tableProperties, () -> "test-job-id");
            FileReference file = createFileReference("input.parquet", PARTITION_ID);

            // When
            CompactionJob job = factory.createCompactionJob(List.of(file), PARTITION_ID);

            // Then
            assertThat(job.getTableId()).isEqualTo(tableProperties.get(TABLE_ID));
            assertThat(job.getId()).isEqualTo("test-job-id");
            assertThat(job.getPartitionId()).isEqualTo(PARTITION_ID);
            assertThat(job.getInputFiles()).containsExactly("input.parquet");
        }

        @Test
        void shouldCreateCompactionJobWithMultipleFiles() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(
                    instanceProperties, tableProperties, () -> "test-job-id");
            FileReference file1 = createFileReference("input1.parquet", PARTITION_ID);
            FileReference file2 = createFileReference("input2.parquet", PARTITION_ID);
            FileReference file3 = createFileReference("input3.parquet", PARTITION_ID);

            // When
            CompactionJob job = factory.createCompactionJob(List.of(file1, file2, file3), PARTITION_ID);

            // Then
            assertThat(job.getInputFiles()).containsExactly("input1.parquet", "input2.parquet", "input3.parquet");
        }

        @Test
        void shouldGenerateOutputFilePathWithPartitionAndJobId() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(
                    instanceProperties, tableProperties, () -> "my-job-id");
            FileReference file = createFileReference("input.parquet", PARTITION_ID);

            // When
            CompactionJob job = factory.createCompactionJob(List.of(file), PARTITION_ID);

            // Then
            String expectedOutputPath = instanceProperties.get(FILE_SYSTEM)
                    + instanceProperties.get(DATA_BUCKET)
                    + "/" + tableProperties.get(TABLE_ID)
                    + "/data/partition_" + PARTITION_ID
                    + "/my-job-id.parquet";
            assertThat(job.getOutputFile()).isEqualTo(expectedOutputPath);
        }

        @Test
        void shouldIncludeIteratorClassNameWhenSet() {
            // Given
            tableProperties.set(ITERATOR_CLASS_NAME, "com.example.TestIterator");
            CompactionJobFactory factory = new CompactionJobFactory(
                    instanceProperties, tableProperties, () -> "test-job-id");
            FileReference file = createFileReference("input.parquet", PARTITION_ID);

            // When
            CompactionJob job = factory.createCompactionJob(List.of(file), PARTITION_ID);

            // Then
            assertThat(job.getIteratorClassName()).isEqualTo("com.example.TestIterator");
        }

        @Test
        void shouldIncludeIteratorConfigWhenSet() {
            // Given
            tableProperties.set(ITERATOR_CONFIG, "iterator-config-value");
            CompactionJobFactory factory = new CompactionJobFactory(
                    instanceProperties, tableProperties, () -> "test-job-id");
            FileReference file = createFileReference("input.parquet", PARTITION_ID);

            // When
            CompactionJob job = factory.createCompactionJob(List.of(file), PARTITION_ID);

            // Then
            assertThat(job.getIteratorConfig()).isEqualTo("iterator-config-value");
        }

        @Test
        void shouldHaveNullIteratorFieldsWhenNotSet() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(
                    instanceProperties, tableProperties, () -> "test-job-id");
            FileReference file = createFileReference("input.parquet", PARTITION_ID);

            // When
            CompactionJob job = factory.createCompactionJob(List.of(file), PARTITION_ID);

            // Then
            assertThat(job.getIteratorClassName()).isNull();
            assertThat(job.getIteratorConfig()).isNull();
        }
    }

    @Nested
    @DisplayName("createCompactionJob with explicit jobId")
    class CreateCompactionJobWithExplicitJobId {

        @Test
        void shouldCreateCompactionJobWithExplicitJobId() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);
            FileReference file = createFileReference("input.parquet", PARTITION_ID);

            // When
            CompactionJob job = factory.createCompactionJob("explicit-job-id", List.of(file), PARTITION_ID);

            // Then
            assertThat(job.getId()).isEqualTo("explicit-job-id");
        }

        @Test
        void shouldUseExplicitJobIdInsteadOfSupplier() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(
                    instanceProperties, tableProperties, () -> "supplier-job-id");
            FileReference file = createFileReference("input.parquet", PARTITION_ID);

            // When
            CompactionJob job = factory.createCompactionJob("explicit-job-id", List.of(file), PARTITION_ID);

            // Then
            assertThat(job.getId()).isEqualTo("explicit-job-id");
        }

        @Test
        void shouldGenerateOutputFileWithExplicitJobId() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);
            FileReference file = createFileReference("input.parquet", PARTITION_ID);

            // When
            CompactionJob job = factory.createCompactionJob("my-explicit-id", List.of(file), PARTITION_ID);

            // Then
            assertThat(job.getOutputFile()).contains("my-explicit-id.parquet");
        }

        @Test
        void shouldThrowExceptionWhenFilePartitionDoesNotMatchProvided() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);
            FileReference file = createFileReference("input.parquet", "different-partition");

            // When/Then
            assertThatThrownBy(() -> factory.createCompactionJob("job-id", List.of(file), PARTITION_ID))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("partition")
                    .hasMessageContaining(PARTITION_ID)
                    .hasMessageContaining("different-partition");
        }

        @Test
        void shouldThrowExceptionWhenAnyFilePartitionDoesNotMatch() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);
            FileReference file1 = createFileReference("input1.parquet", PARTITION_ID);
            FileReference file2 = createFileReference("input2.parquet", "wrong-partition");

            // When/Then
            assertThatThrownBy(() -> factory.createCompactionJob("job-id", List.of(file1, file2), PARTITION_ID))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("wrong-partition");
        }

        @Test
        void shouldSucceedWhenAllFilesHaveMatchingPartition() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);
            FileReference file1 = createFileReference("input1.parquet", PARTITION_ID);
            FileReference file2 = createFileReference("input2.parquet", PARTITION_ID);

            // When
            CompactionJob job = factory.createCompactionJob("job-id", List.of(file1, file2), PARTITION_ID);

            // Then
            assertThat(job.getInputFiles()).containsExactly("input1.parquet", "input2.parquet");
            assertThat(job.getPartitionId()).isEqualTo(PARTITION_ID);
        }
    }

    @Nested
    @DisplayName("createCompactionJobWithFilenames")
    class CreateCompactionJobWithFilenames {

        @Test
        void shouldCreateCompactionJobFromFilenames() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);
            List<String> filenames = List.of("file1.parquet", "file2.parquet");

            // When
            CompactionJob job = factory.createCompactionJobWithFilenames("job-id", filenames, PARTITION_ID);

            // Then
            assertThat(job.getId()).isEqualTo("job-id");
            assertThat(job.getInputFiles()).isEqualTo(filenames);
            assertThat(job.getPartitionId()).isEqualTo(PARTITION_ID);
            assertThat(job.getTableId()).isEqualTo(tableProperties.get(TABLE_ID));
        }

        @Test
        void shouldCreateJobWithSingleFilename() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);

            // When
            CompactionJob job = factory.createCompactionJobWithFilenames("job-id", List.of("single.parquet"), PARTITION_ID);

            // Then
            assertThat(job.getInputFiles()).containsExactly("single.parquet");
        }

        @Test
        void shouldGenerateCorrectOutputFilePath() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);

            // When
            CompactionJob job = factory.createCompactionJobWithFilenames("output-job", List.of("input.parquet"), "my-partition");

            // Then
            String expectedOutputPath = instanceProperties.get(FILE_SYSTEM)
                    + instanceProperties.get(DATA_BUCKET)
                    + "/" + tableProperties.get(TABLE_ID)
                    + "/data/partition_my-partition"
                    + "/output-job.parquet";
            assertThat(job.getOutputFile()).isEqualTo(expectedOutputPath);
        }

        @Test
        void shouldIncludeIteratorSettingsFromTableProperties() {
            // Given
            tableProperties.set(ITERATOR_CLASS_NAME, "com.example.MyIterator");
            tableProperties.set(ITERATOR_CONFIG, "config-data");
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);

            // When
            CompactionJob job = factory.createCompactionJobWithFilenames("job-id", List.of("input.parquet"), PARTITION_ID);

            // Then
            assertThat(job.getIteratorClassName()).isEqualTo("com.example.MyIterator");
            assertThat(job.getIteratorConfig()).isEqualTo("config-data");
        }

        @Test
        void shouldPreserveFilenameOrderInJob() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);
            List<String> filenames = List.of("z.parquet", "a.parquet", "m.parquet");

            // When
            CompactionJob job = factory.createCompactionJobWithFilenames("job-id", filenames, PARTITION_ID);

            // Then
            assertThat(job.getInputFiles()).containsExactly("z.parquet", "a.parquet", "m.parquet");
        }
    }

    @Nested
    @DisplayName("Different partitions")
    class DifferentPartitions {

        @Test
        void shouldCreateJobsForDifferentPartitions() {
            // Given
            CompactionJobFactory factory = new CompactionJobFactory(instanceProperties, tableProperties);
            FileReference file1 = createFileReference("file1.parquet", "partition-a");
            FileReference file2 = createFileReference("file2.parquet", "partition-b");

            // When
            CompactionJob job1 = factory.createCompactionJob("job-1", List.of(file1), "partition-a");
            CompactionJob job2 = factory.createCompactionJob("job-2", List.of(file2), "partition-b");

            // Then
            assertThat(job1.getPartitionId()).isEqualTo("partition-a");
            assertThat(job2.getPartitionId()).isEqualTo("partition-b");
            assertThat(job1.getOutputFile()).contains("partition_partition-a");
            assertThat(job2.getOutputFile()).contains("partition_partition-b");
        }
    }

    private FileReference createFileReference(String filename, String partitionId) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(partitionId)
                .numberOfRecords(100L)
                .countApproximate(false)
                .onlyContainsDataForThisPartition(true)
                .build();
    }
}
