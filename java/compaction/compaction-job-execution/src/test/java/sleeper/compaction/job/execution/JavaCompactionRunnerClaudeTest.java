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
package sleeper.compaction.job.execution;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.ParquetWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.IteratorCreationException;
import sleeper.core.iterator.WrappedIterator;
import sleeper.core.partition.Partition;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.StringType;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.core.util.ObjectFactory;
import sleeper.parquet.record.ParquetRecordWriterFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;

/**
 * Unit tests for the JavaCompactionRunner class.
 */
class JavaCompactionRunnerClaudeTest {

    @TempDir
    private File tempDir;

    private ObjectFactory objectFactory;
    private Configuration configuration;
    private InstanceProperties instanceProperties;
    private TableProperties tableProperties;
    private Schema schema;
    private JavaCompactionRunner runner;

    @BeforeEach
    void setUp() {
        objectFactory = ObjectFactory.noUserJars();
        configuration = new Configuration();
        instanceProperties = createTestInstanceProperties();
        schema = Schema.builder()
                .rowKeyFields(new Field("key", new StringType()))
                .valueFields(new Field("value", new LongType()))
                .build();
        tableProperties = createTestTableProperties(instanceProperties, schema);
        runner = new JavaCompactionRunner(objectFactory, configuration);
    }

    private void writeRecordsToParquetFile(String filePath, List<Record> records) throws IOException {
        ParquetWriter<Record> writer = ParquetRecordWriterFactory.createParquetRecordWriter(
                new Path(filePath), tableProperties, configuration);
        for (Record record : records) {
            writer.write(record);
        }
        writer.close();
    }

    private Record createRecord(String key, long value) {
        Record record = new Record();
        record.put("key", key);
        record.put("value", value);
        return record;
    }

    private Partition createRootPartition() {
        return new PartitionsBuilder(schema).singlePartition("root").buildTree().getRootPartition();
    }

    private CompactionJob createJob(List<String> inputFiles, String outputFile) {
        return CompactionJob.builder()
                .tableId(tableProperties.get(TABLE_ID))
                .jobId("test-job-id")
                .inputFiles(inputFiles)
                .outputFile(outputFile)
                .partitionId("root")
                .build();
    }

    private CompactionJob createJobWithIterator(List<String> inputFiles, String outputFile, String iteratorClassName, String iteratorConfig) {
        return CompactionJob.builder()
                .tableId(tableProperties.get(TABLE_ID))
                .jobId("test-job-id")
                .inputFiles(inputFiles)
                .outputFile(outputFile)
                .partitionId("root")
                .iteratorClassName(iteratorClassName)
                .iteratorConfig(iteratorConfig)
                .build();
    }

    @DisplayName("Implementation details")
    @Nested
    class ImplementationDetails {

        @Test
        void shouldReturnTrueForSupportsIterators() {
            // When/Then
            assertThat(runner.supportsIterators()).isTrue();
        }

        @Test
        void shouldReturnJavaForImplementationLanguage() {
            // When/Then
            assertThat(runner.implementationLanguage()).isEqualTo("Java");
        }

        @Test
        void shouldReturnFalseForIsHardwareAccelerated() {
            // When/Then
            assertThat(runner.isHardwareAccelerated()).isFalse();
        }
    }

    @DisplayName("Constructor")
    @Nested
    class Constructor {

        @Test
        void shouldCreateRunnerWithObjectFactoryAndConfiguration() {
            // Given
            ObjectFactory factory = ObjectFactory.noUserJars();
            Configuration conf = new Configuration();

            // When
            JavaCompactionRunner newRunner = new JavaCompactionRunner(factory, conf);

            // Then
            assertThat(newRunner).isNotNull();
            assertThat(newRunner.supportsIterators()).isTrue();
        }
    }

    @DisplayName("Compact operation")
    @Nested
    class CompactOperation {

        @Test
        void shouldCompactSingleFileWithSingleRecord() throws IOException, IteratorCreationException {
            // Given
            String inputFile = new File(tempDir, "input1.parquet").getAbsolutePath();
            String outputFile = new File(tempDir, "output.parquet").getAbsolutePath();

            List<Record> records = List.of(createRecord("key1", 100L));
            writeRecordsToParquetFile(inputFile, records);

            CompactionJob job = createJob(List.of(inputFile), outputFile);
            Partition partition = createRootPartition();

            // When
            RecordsProcessed result = runner.compact(job, tableProperties, partition);

            // Then
            assertThat(result.getRecordsRead()).isEqualTo(1);
            assertThat(result.getRecordsWritten()).isEqualTo(1);
            assertThat(new File(outputFile)).exists();
        }

        @Test
        void shouldCompactMultipleFilesIntoOne() throws IOException, IteratorCreationException {
            // Given
            String inputFile1 = new File(tempDir, "input1.parquet").getAbsolutePath();
            String inputFile2 = new File(tempDir, "input2.parquet").getAbsolutePath();
            String outputFile = new File(tempDir, "output.parquet").getAbsolutePath();

            List<Record> records1 = List.of(createRecord("a", 10L), createRecord("c", 30L));
            List<Record> records2 = List.of(createRecord("b", 20L), createRecord("d", 40L));
            writeRecordsToParquetFile(inputFile1, records1);
            writeRecordsToParquetFile(inputFile2, records2);

            CompactionJob job = createJob(List.of(inputFile1, inputFile2), outputFile);
            Partition partition = createRootPartition();

            // When
            RecordsProcessed result = runner.compact(job, tableProperties, partition);

            // Then
            assertThat(result.getRecordsRead()).isEqualTo(4);
            assertThat(result.getRecordsWritten()).isEqualTo(4);
            assertThat(new File(outputFile)).exists();
        }

        @Test
        void shouldCompactEmptyFiles() throws IOException, IteratorCreationException {
            // Given
            String inputFile = new File(tempDir, "input1.parquet").getAbsolutePath();
            String outputFile = new File(tempDir, "output.parquet").getAbsolutePath();

            List<Record> emptyRecords = List.of();
            writeRecordsToParquetFile(inputFile, emptyRecords);

            CompactionJob job = createJob(List.of(inputFile), outputFile);
            Partition partition = createRootPartition();

            // When
            RecordsProcessed result = runner.compact(job, tableProperties, partition);

            // Then
            assertThat(result.getRecordsRead()).isEqualTo(0);
            assertThat(result.getRecordsWritten()).isEqualTo(0);
        }

        @Test
        void shouldCreateSketchesFile() throws IOException, IteratorCreationException {
            // Given
            String inputFile = new File(tempDir, "input1.parquet").getAbsolutePath();
            String outputFile = new File(tempDir, "output.parquet").getAbsolutePath();

            List<Record> records = List.of(createRecord("key1", 100L));
            writeRecordsToParquetFile(inputFile, records);

            CompactionJob job = createJob(List.of(inputFile), outputFile);
            Partition partition = createRootPartition();

            // When
            runner.compact(job, tableProperties, partition);

            // Then - sketches file is created with .sketches extension (replacing .parquet)
            File sketchesFile = new File(tempDir, "output.sketches");
            assertThat(sketchesFile).exists();
        }

        @Test
        void shouldOverwriteExistingOutputFile() throws IOException, IteratorCreationException {
            // Given
            String inputFile = new File(tempDir, "input1.parquet").getAbsolutePath();
            String outputFile = new File(tempDir, "output.parquet").getAbsolutePath();

            List<Record> records = List.of(createRecord("key1", 100L));
            writeRecordsToParquetFile(inputFile, records);

            // Create an existing output file
            List<Record> existingRecords = List.of(createRecord("oldkey", 999L));
            writeRecordsToParquetFile(outputFile, existingRecords);

            CompactionJob job = createJob(List.of(inputFile), outputFile);
            Partition partition = createRootPartition();

            // When
            RecordsProcessed result = runner.compact(job, tableProperties, partition);

            // Then - should overwrite existing file
            assertThat(result.getRecordsRead()).isEqualTo(1);
            assertThat(result.getRecordsWritten()).isEqualTo(1);
        }
    }

    @DisplayName("getMergingIterator method")
    @Nested
    class GetMergingIteratorMethod {

        @Test
        void shouldCreateMergingIteratorWithoutCustomIterator() throws IteratorCreationException {
            // Given
            List<Record> records1 = List.of(createRecord("a", 1L), createRecord("c", 3L));
            List<Record> records2 = List.of(createRecord("b", 2L));

            CloseableIterator<Record> iter1 = new WrappedIterator<>(records1.iterator());
            CloseableIterator<Record> iter2 = new WrappedIterator<>(records2.iterator());
            List<CloseableIterator<Record>> inputIterators = new ArrayList<>(Arrays.asList(iter1, iter2));

            CompactionJob job = createJob(List.of("file1", "file2"), "output");

            // When
            CloseableIterator<Record> mergingIterator = JavaCompactionRunner.getMergingIterator(
                    objectFactory, schema, job, inputIterators);

            // Then
            List<Record> result = new ArrayList<>();
            while (mergingIterator.hasNext()) {
                result.add(mergingIterator.next());
            }

            assertThat(result).hasSize(3);
            // Merging iterator should produce sorted output
            assertThat(result.get(0).get("key")).isEqualTo("a");
            assertThat(result.get(1).get("key")).isEqualTo("b");
            assertThat(result.get(2).get("key")).isEqualTo("c");
        }

        @Test
        void shouldCreateMergingIteratorWithCustomIterator() throws IteratorCreationException {
            // Given - Use AdditionIterator which aggregates values for same key
            Schema aggregationSchema = Schema.builder()
                    .rowKeyFields(new Field("key", new StringType()))
                    .valueFields(new Field("value", new LongType()))
                    .build();

            List<Record> records1 = List.of(
                    createAggRecord("a", 10L),
                    createAggRecord("a", 20L) // Same key, should be aggregated
            );

            CloseableIterator<Record> iter1 = new WrappedIterator<>(records1.iterator());
            List<CloseableIterator<Record>> inputIterators = new ArrayList<>(List.of(iter1));

            CompactionJob job = createJobWithIterator(
                    List.of("file1"), "output",
                    "sleeper.example.iterator.AdditionIterator", null);

            // When
            CloseableIterator<Record> mergingIterator = JavaCompactionRunner.getMergingIterator(
                    objectFactory, aggregationSchema, job, inputIterators);

            // Then
            List<Record> result = new ArrayList<>();
            while (mergingIterator.hasNext()) {
                result.add(mergingIterator.next());
            }

            assertThat(result).hasSize(1);
            assertThat(result.get(0).get("key")).isEqualTo("a");
            assertThat(result.get(0).get("value")).isEqualTo(30L); // 10 + 20
        }

        private Record createAggRecord(String key, long value) {
            Record record = new Record();
            record.put("key", key);
            record.put("value", value);
            return record;
        }

        @Test
        void shouldThrowIteratorCreationExceptionForInvalidIteratorClass() {
            // Given
            CloseableIterator<Record> iter1 = new WrappedIterator<>(List.<Record>of().iterator());
            List<CloseableIterator<Record>> inputIterators = new ArrayList<>(List.of(iter1));

            CompactionJob job = createJobWithIterator(
                    List.of("file1"), "output",
                    "com.nonexistent.InvalidIterator", null);

            // When/Then
            assertThatThrownBy(() -> JavaCompactionRunner.getMergingIterator(
                    objectFactory, schema, job, inputIterators))
                    .isInstanceOf(IteratorCreationException.class)
                    .hasMessageContaining("ObjectFactoryException creating iterator");
        }

        @Test
        void shouldHandleEmptyInputIterators() throws IteratorCreationException {
            // Given
            List<CloseableIterator<Record>> emptyInputIterators = new ArrayList<>();

            CompactionJob job = createJob(List.of(), "output");

            // When
            CloseableIterator<Record> mergingIterator = JavaCompactionRunner.getMergingIterator(
                    objectFactory, schema, job, emptyInputIterators);

            // Then
            assertThat(mergingIterator.hasNext()).isFalse();
        }
    }

    @DisplayName("Compact with custom iterator")
    @Nested
    class CompactWithCustomIterator {

        @Test
        void shouldCompactWithAdditionIterator() throws IOException, IteratorCreationException {
            // Given
            String inputFile = new File(tempDir, "input1.parquet").getAbsolutePath();
            String outputFile = new File(tempDir, "output.parquet").getAbsolutePath();

            // Records with same key should be aggregated
            List<Record> records = List.of(
                    createRecord("key1", 10L),
                    createRecord("key1", 20L),
                    createRecord("key2", 100L)
            );
            writeRecordsToParquetFile(inputFile, records);

            CompactionJob job = createJobWithIterator(
                    List.of(inputFile), outputFile,
                    "sleeper.example.iterator.AdditionIterator", null);
            Partition partition = createRootPartition();

            // When
            RecordsProcessed result = runner.compact(job, tableProperties, partition);

            // Then
            assertThat(result.getRecordsRead()).isEqualTo(3);
            assertThat(result.getRecordsWritten()).isEqualTo(2); // 2 unique keys after aggregation
        }
    }
}
