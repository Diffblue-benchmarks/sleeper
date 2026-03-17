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
import sleeper.compaction.core.job.CompactionRunner;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.validation.CompactionMethod;
import sleeper.core.schema.Schema;
import sleeper.core.util.ObjectFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CompactionProperty.DEFAULT_COMPACTION_METHOD;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;

class DefaultCompactionRunnerFactoryTest {

    private static final Schema DEFAULT_SCHEMA = schemaWithKey("key");
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, DEFAULT_SCHEMA);

    @Nested
    @DisplayName("Create factory")
    class CreateFactory {

        @Test
        void shouldCreateFactoryWithObjectFactoryAndConfiguration() {
            // Given
            ObjectFactory objectFactory = ObjectFactory.noUserJars();
            Configuration configuration = new Configuration();

            // When
            DefaultCompactionRunnerFactory factory = new DefaultCompactionRunnerFactory(objectFactory, configuration);

            // Then
            assertThat(factory).isNotNull();
        }

        @Test
        void shouldCreateJavaCompactorWhenMethodIsJava() {
            // Given
            instanceProperties.set(DEFAULT_COMPACTION_METHOD, CompactionMethod.JAVA.toString());
            ObjectFactory objectFactory = ObjectFactory.noUserJars();
            Configuration configuration = new Configuration();
            DefaultCompactionRunnerFactory factory = new DefaultCompactionRunnerFactory(objectFactory, configuration);
            CompactionJob job = CompactionJob.builder()
                    .tableId("test-table")
                    .jobId("test-job")
                    .partitionId("root")
                    .inputFiles(java.util.List.of("file1.parquet"))
                    .outputFile("output.parquet")
                    .build();

            // When
            CompactionRunner compactor = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(compactor).isInstanceOf(JavaCompactionRunner.class);
        }

        @Test
        void shouldCreateRustCompactorWhenMethodIsDatafusion() {
            // Given
            instanceProperties.set(DEFAULT_COMPACTION_METHOD, CompactionMethod.DATAFUSION.toString());
            ObjectFactory objectFactory = ObjectFactory.noUserJars();
            Configuration configuration = new Configuration();
            DefaultCompactionRunnerFactory factory = new DefaultCompactionRunnerFactory(objectFactory, configuration);
            CompactionJob job = CompactionJob.builder()
                    .tableId("test-table")
                    .jobId("test-job")
                    .partitionId("root")
                    .inputFiles(java.util.List.of("file1.parquet"))
                    .outputFile("output.parquet")
                    .build();

            // When
            CompactionRunner compactor = factory.createCompactor(job, tableProperties);

            // Then
            assertThat(compactor).isInstanceOf(sleeper.compaction.rust.RustCompactionRunner.class);
        }
    }
}
