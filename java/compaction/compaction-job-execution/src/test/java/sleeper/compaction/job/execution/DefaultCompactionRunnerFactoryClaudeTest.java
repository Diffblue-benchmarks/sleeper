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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionRunner;
import sleeper.compaction.rust.RustCompactionRunner;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.validation.CompactionMethod;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.util.ObjectFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.table.TableProperty.COMPACTION_METHOD;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;

/**
 * Unit tests for the DefaultCompactionRunnerFactory class.
 */
class DefaultCompactionRunnerFactoryClaudeTest {

    private ObjectFactory objectFactory;
    private Configuration configuration;
    private InstanceProperties instanceProperties;
    private TableProperties tableProperties;
    private DefaultCompactionRunnerFactory factory;

    @BeforeEach
    void setUp() {
        objectFactory = ObjectFactory.noUserJars();
        configuration = new Configuration();
        instanceProperties = createTestInstanceProperties();
        Schema schema = Schema.builder()
                .rowKeyFields(new Field("key", new StringType()))
                .build();
        tableProperties = createTestTableProperties(instanceProperties, schema);
        factory = new DefaultCompactionRunnerFactory(objectFactory, configuration);
    }

    private CompactionJob createJob() {
        return createJobWithIterator(null);
    }

    private CompactionJob createJobWithIterator(String iteratorClassName) {
        return CompactionJob.builder()
                .tableId(tableProperties.get(sleeper.core.properties.table.TableProperty.TABLE_ID))
                .jobId("test-job-id")
                .inputFiles(List.of("file1.parquet", "file2.parquet"))
                .outputFile("output.parquet")
                .partitionId("partition-id")
                .iteratorClassName(iteratorClassName)
                .build();
    }

    @DisplayName("Create compactor with Java compaction method")
    @Nested
    class JavaCompactionMethod {

        @Test
        void shouldCreateJavaRunnerWhenMethodIsJava() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.JAVA.toString());
            CompactionJob job = createJob();

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner).isInstanceOf(JavaCompactionRunner.class);
        }

        @Test
        void shouldCreateJavaRunnerWithIteratorSupport() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.JAVA.toString());
            CompactionJob job = createJob();

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner.supportsIterators()).isTrue();
        }

        @Test
        void shouldCreateJavaRunnerWhenMethodIsJavaAndJobHasIterator() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.JAVA.toString());
            CompactionJob job = createJobWithIterator("com.example.SomeIterator");

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner).isInstanceOf(JavaCompactionRunner.class);
            assertThat(runner.supportsIterators()).isTrue();
        }

        @Test
        void shouldReturnJavaAsImplementationLanguage() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.JAVA.toString());
            CompactionJob job = createJob();

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner.implementationLanguage()).isEqualTo("Java");
        }
    }

    @DisplayName("Create compactor with DataFusion compaction method")
    @Nested
    class DataFusionCompactionMethod {

        @Test
        void shouldCreateRustRunnerWhenMethodIsDataFusion() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.DATAFUSION.toString());
            CompactionJob job = createJob();

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner).isInstanceOf(RustCompactionRunner.class);
        }

        @Test
        void shouldCreateRustRunnerWithNoIteratorSupport() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.DATAFUSION.toString());
            CompactionJob job = createJob();

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner.supportsIterators()).isFalse();
        }

        @Test
        void shouldReturnRustAsImplementationLanguage() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.DATAFUSION.toString());
            CompactionJob job = createJob();

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner.implementationLanguage()).isEqualTo("Rust");
        }
    }

    @DisplayName("Fallback to Java when iterator not supported")
    @Nested
    class FallbackToJavaRunner {

        @Test
        void shouldFallbackToJavaRunnerWhenDataFusionMethodAndJobHasIterator() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.DATAFUSION.toString());
            CompactionJob job = createJobWithIterator("com.example.SomeIterator");

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner).isInstanceOf(JavaCompactionRunner.class);
        }

        @Test
        void shouldNotFallbackWhenDataFusionMethodAndJobHasNoIterator() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.DATAFUSION.toString());
            CompactionJob job = createJob();

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner).isInstanceOf(RustCompactionRunner.class);
        }

        @Test
        void shouldNotFallbackWhenIteratorIsNull() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.DATAFUSION.toString());
            CompactionJob job = createJobWithIterator(null);

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner).isInstanceOf(RustCompactionRunner.class);
        }
    }

    @DisplayName("Default compaction method")
    @Nested
    class DefaultCompactionMethodTests {

        @Test
        void shouldUseDefaultCompactionMethodFromInstanceProperties() {
            // Given - COMPACTION_METHOD not explicitly set, will use default from instance properties
            CompactionJob job = createJob();

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then - Default is JAVA
            assertThat(runner).isInstanceOf(JavaCompactionRunner.class);
        }
    }

    @DisplayName("Runner properties")
    @Nested
    class RunnerProperties {

        @Test
        void shouldReturnNonHardwareAcceleratedForJavaRunner() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.JAVA.toString());
            CompactionJob job = createJob();

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner.isHardwareAccelerated()).isFalse();
        }

        @Test
        void shouldReturnNonHardwareAcceleratedForRustRunner() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.DATAFUSION.toString());
            CompactionJob job = createJob();

            // When
            CompactionRunner runner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner.isHardwareAccelerated()).isFalse();
        }
    }

    @DisplayName("Multiple calls to factory")
    @Nested
    class MultipleFactoryCalls {

        @Test
        void shouldCreateNewRunnerInstanceOnEachCall() {
            // Given
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.JAVA.toString());
            CompactionJob job = createJob();

            // When
            CompactionRunner runner1 = factory.createCompactor(job, tableProperties);
            CompactionRunner runner2 = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(runner1).isNotSameAs(runner2);
        }

        @Test
        void shouldCreateDifferentRunnerTypesBasedOnTableProperties() {
            // Given
            CompactionJob job = createJob();

            // When - First call with Java method
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.JAVA.toString());
            CompactionRunner javaRunner = factory.createCompactor(job, tableProperties);

            // Then - Second call with DataFusion method
            tableProperties.set(COMPACTION_METHOD, CompactionMethod.DATAFUSION.toString());
            CompactionRunner rustRunner = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(javaRunner).isInstanceOf(JavaCompactionRunner.class);
            assertThat(rustRunner).isInstanceOf(RustCompactionRunner.class);
        }
    }
}
