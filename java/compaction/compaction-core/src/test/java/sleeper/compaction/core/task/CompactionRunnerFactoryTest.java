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

package sleeper.compaction.core.task;

import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionRunner;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.tracker.job.run.RecordsProcessed;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;

public class CompactionRunnerFactoryTest {

    @Test
    void shouldCreateCompactorFromFactory() {
        // Given
        TableProperties tableProperties = createTestTableProperties(createTestInstanceProperties(), schemaWithKey("key"));
        CompactionJob job = CompactionJob.builder()
                .tableId("test-table")
                .jobId("test-job")
                .partitionId("partition-1")
                .inputFiles(java.util.List.of("file1.parquet", "file2.parquet"))
                .outputFile("output.parquet")
                .build();
        CompactionRunner expectedRunner = (j, t, p) -> new RecordsProcessed(100L, 100L);
        CompactionRunnerFactory factory = (j, t) -> expectedRunner;

        // When
        CompactionRunner actualRunner = factory.createCompactor(job, tableProperties);

        // Then
        assertThat(actualRunner).isEqualTo(expectedRunner);
    }

    @Test
    void shouldCreateDifferentCompactorsBasedOnJobProperties() {
        // Given
        TableProperties tableProperties = createTestTableProperties(createTestInstanceProperties(), schemaWithKey("key"));
        CompactionJob job1 = CompactionJob.builder()
                .tableId("table1")
                .jobId("job1")
                .partitionId("partition-1")
                .inputFiles(java.util.List.of("file1.parquet"))
                .outputFile("output1.parquet")
                .build();
        CompactionJob job2 = CompactionJob.builder()
                .tableId("table2")
                .jobId("job2")
                .partitionId("partition-2")
                .inputFiles(java.util.List.of("file2.parquet"))
                .outputFile("output2.parquet")
                .build();
        CompactionRunner runner1 = (j, t, p) -> new RecordsProcessed(100L, 100L);
        CompactionRunner runner2 = (j, t, p) -> new RecordsProcessed(200L, 200L);
        CompactionRunnerFactory factory = (j, t) -> j.getTableId().equals("table1") ? runner1 : runner2;

        // When
        CompactionRunner actualRunner1 = factory.createCompactor(job1, tableProperties);
        CompactionRunner actualRunner2 = factory.createCompactor(job2, tableProperties);

        // Then
        assertThat(actualRunner1).isEqualTo(runner1);
        assertThat(actualRunner2).isEqualTo(runner2);
    }

    @Test
    void shouldReturnSameRunnerInstanceFromFactory() {
        // Given
        TableProperties tableProperties = createTestTableProperties(createTestInstanceProperties(), schemaWithKey("key"));
        CompactionJob job = CompactionJob.builder()
                .tableId("test-table")
                .jobId("test-job")
                .partitionId("partition-1")
                .inputFiles(java.util.List.of("file1.parquet"))
                .outputFile("output.parquet")
                .build();
        CompactionRunner runner = (j, t, p) -> new RecordsProcessed(150L, 150L);
        CompactionRunnerFactory factory = (j, t) -> runner;

        // When
        CompactionRunner compactor1 = factory.createCompactor(job, tableProperties);
        CompactionRunner compactor2 = factory.createCompactor(job, tableProperties);

        // Then
        assertThat(compactor1).isSameAs(runner);
        assertThat(compactor2).isSameAs(runner);
    }
}
