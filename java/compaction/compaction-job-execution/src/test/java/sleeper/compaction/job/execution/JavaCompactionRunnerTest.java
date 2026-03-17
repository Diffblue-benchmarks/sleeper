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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.IteratorCreationException;
import sleeper.core.iterator.SortedRecordIterator;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.util.ObjectFactory;
import sleeper.core.util.ObjectFactoryException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JavaCompactionRunnerTest {

    @Test
    void shouldReturnImplementationLanguage() {
        // Given
        JavaCompactionRunner runner = new JavaCompactionRunner(ObjectFactory.noUserJars(), new Configuration());

        // When
        String language = runner.implementationLanguage();

        // Then
        assertThat(language).isEqualTo("Java");
    }

    @Test
    void shouldSupportIterators() {
        // Given
        JavaCompactionRunner runner = new JavaCompactionRunner(ObjectFactory.noUserJars(), new Configuration());

        // When
        boolean supportsIterators = runner.supportsIterators();

        // Then
        assertThat(supportsIterators).isTrue();
    }

    @Test
    void shouldNotBeHardwareAccelerated() {
        // Given
        JavaCompactionRunner runner = new JavaCompactionRunner(ObjectFactory.noUserJars(), new Configuration());

        // When
        boolean isHardwareAccelerated = runner.isHardwareAccelerated();

        // Then
        assertThat(isHardwareAccelerated).isFalse();
    }

    @Nested
    @DisplayName("Get merging iterator")
    class GetMergingIterator {

        private final Schema schema = Schema.builder()
                .rowKeyFields(new Field("key", new StringType()))
                .build();

        @Test
        void shouldReturnMergingIteratorWhenNoIteratorClassNameProvided() throws Exception {
            // Given
            ObjectFactory objectFactory = ObjectFactory.noUserJars();
            List<CloseableIterator<Record>> inputIterators = new ArrayList<>();
            CompactionJob job = CompactionJob.builder()
                    .tableId("test-table")
                    .jobId("test-job")
                    .partitionId("root")
                    .inputFiles(List.of("file1.parquet"))
                    .outputFile("output.parquet")
                    .build();

            // When
            CloseableIterator<Record> result = JavaCompactionRunner.getMergingIterator(
                    objectFactory, schema, job, inputIterators);

            // Then
            assertThat(result).isNotNull();
        }

        @Test
        void shouldApplyIteratorWhenIteratorClassNameProvided() throws Exception {
            // Given
            ObjectFactory objectFactory = ObjectFactory.noUserJars();
            List<CloseableIterator<Record>> inputIterators = new ArrayList<>();
            CompactionJob job = CompactionJob.builder()
                    .tableId("test-table")
                    .jobId("test-job")
                    .partitionId("root")
                    .inputFiles(List.of("file1.parquet"))
                    .outputFile("output.parquet")
                    .iteratorClassName(TestIterator.class.getName())
                    .iteratorConfig("test-config")
                    .build();

            // When
            CloseableIterator<Record> result = JavaCompactionRunner.getMergingIterator(
                    objectFactory, schema, job, inputIterators);

            // Then
            assertThat(result).isNotNull();
        }

        @Test
        void shouldThrowIteratorCreationExceptionWhenObjectFactoryFails() {
            // Given
            ObjectFactory objectFactory = new ObjectFactory(JavaCompactionRunnerTest.class.getClassLoader()) {
                @Override
                public <T> T getObject(String className, Class<T> baseClass) throws ObjectFactoryException {
                    throw new ObjectFactoryException("Failed to create object");
                }
            };
            List<CloseableIterator<Record>> inputIterators = new ArrayList<>();
            CompactionJob job = CompactionJob.builder()
                    .tableId("test-table")
                    .jobId("test-job")
                    .partitionId("root")
                    .inputFiles(List.of("file1.parquet"))
                    .outputFile("output.parquet")
                    .iteratorClassName("com.example.NonExistentIterator")
                    .iteratorConfig("test-config")
                    .build();

            // When / Then
            assertThatThrownBy(() -> JavaCompactionRunner.getMergingIterator(
                    objectFactory, schema, job, inputIterators))
                    .isInstanceOf(IteratorCreationException.class)
                    .hasMessageContaining("ObjectFactoryException creating iterator")
                    .hasMessageContaining("com.example.NonExistentIterator");
        }
    }

    public static class TestIterator implements SortedRecordIterator {
        @Override
        public void init(String configString, Schema schema) {
            // Test iterator initialization
        }

        @Override
        public List<String> getRequiredValueFields() {
            return Collections.emptyList();
        }

        @Override
        public CloseableIterator<Record> apply(CloseableIterator<Record> input) {
            return input;
        }
    }
}
