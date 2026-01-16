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
package sleeper.compaction.job.creation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobSerDe;
import sleeper.localstack.test.LocalStackTestBase;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CompactionBatchJobsWriterToS3ClaudeTest extends LocalStackTestBase {

    private static final String TEST_BUCKET = "test-compaction-bucket";
    private final CompactionJobSerDe serDe = new CompactionJobSerDe();
    private CompactionBatchJobsWriterToS3 writer;

    @BeforeEach
    void setUp() {
        s3Client.createBucket(TEST_BUCKET);
        writer = new CompactionBatchJobsWriterToS3(s3Client);
    }

    @Test
    void shouldWriteSingleJobToS3() {
        // Given
        String key = "jobs/" + UUID.randomUUID() + ".json";
        CompactionJob job = createTestJob("job-1", "table-1", "partition-1");

        // When
        writer.writeJobs(TEST_BUCKET, key, List.of(job));

        // Then
        String storedJson = s3Client.getObjectAsString(TEST_BUCKET, key);
        List<CompactionJob> retrievedJobs = serDe.batchFromJson(storedJson);
        assertThat(retrievedJobs).containsExactly(job);
    }

    @Test
    void shouldWriteMultipleJobsToS3() {
        // Given
        String key = "jobs/" + UUID.randomUUID() + ".json";
        CompactionJob job1 = createTestJob("job-1", "table-1", "partition-1");
        CompactionJob job2 = createTestJob("job-2", "table-1", "partition-2");
        CompactionJob job3 = createTestJob("job-3", "table-2", "partition-1");

        // When
        writer.writeJobs(TEST_BUCKET, key, List.of(job1, job2, job3));

        // Then
        String storedJson = s3Client.getObjectAsString(TEST_BUCKET, key);
        List<CompactionJob> retrievedJobs = serDe.batchFromJson(storedJson);
        assertThat(retrievedJobs).containsExactly(job1, job2, job3);
    }

    @Test
    void shouldWriteEmptyJobListToS3() {
        // Given
        String key = "jobs/" + UUID.randomUUID() + ".json";

        // When
        writer.writeJobs(TEST_BUCKET, key, Collections.emptyList());

        // Then
        String storedJson = s3Client.getObjectAsString(TEST_BUCKET, key);
        List<CompactionJob> retrievedJobs = serDe.batchFromJson(storedJson);
        assertThat(retrievedJobs).isEmpty();
    }

    @Test
    void shouldWriteJobWithIteratorConfiguration() {
        // Given
        String key = "jobs/" + UUID.randomUUID() + ".json";
        CompactionJob job = CompactionJob.builder()
                .tableId("table-1")
                .jobId("job-with-iterator")
                .partitionId("partition-1")
                .inputFiles(List.of("input1.parquet"))
                .outputFile("output.parquet")
                .iteratorClassName("com.example.CustomIterator")
                .iteratorConfig("{\"config\": \"value\"}")
                .build();

        // When
        writer.writeJobs(TEST_BUCKET, key, List.of(job));

        // Then
        String storedJson = s3Client.getObjectAsString(TEST_BUCKET, key);
        List<CompactionJob> retrievedJobs = serDe.batchFromJson(storedJson);
        assertThat(retrievedJobs).hasSize(1);
        assertThat(retrievedJobs.get(0).getIteratorClassName()).isEqualTo("com.example.CustomIterator");
        assertThat(retrievedJobs.get(0).getIteratorConfig()).isEqualTo("{\"config\": \"value\"}");
    }

    @Test
    void shouldWriteJobWithMultipleInputFiles() {
        // Given
        String key = "jobs/" + UUID.randomUUID() + ".json";
        CompactionJob job = CompactionJob.builder()
                .tableId("table-1")
                .jobId("job-multiple-inputs")
                .partitionId("partition-1")
                .inputFiles(List.of("file1.parquet", "file2.parquet", "file3.parquet", "file4.parquet"))
                .outputFile("output.parquet")
                .build();

        // When
        writer.writeJobs(TEST_BUCKET, key, List.of(job));

        // Then
        String storedJson = s3Client.getObjectAsString(TEST_BUCKET, key);
        List<CompactionJob> retrievedJobs = serDe.batchFromJson(storedJson);
        assertThat(retrievedJobs).hasSize(1);
        assertThat(retrievedJobs.get(0).getInputFiles())
                .containsExactly("file1.parquet", "file2.parquet", "file3.parquet", "file4.parquet");
    }

    @Test
    void shouldWriteToSpecifiedKey() {
        // Given
        String specificKey = "custom/path/to/compaction-jobs.json";
        CompactionJob job = createTestJob("job-1", "table-1", "partition-1");

        // When
        writer.writeJobs(TEST_BUCKET, specificKey, List.of(job));

        // Then
        assertThat(s3Client.doesObjectExist(TEST_BUCKET, specificKey)).isTrue();
    }

    @Test
    void shouldOverwriteExistingObjectAtSameKey() {
        // Given
        String key = "jobs/same-key.json";
        CompactionJob job1 = createTestJob("job-1", "table-1", "partition-1");
        CompactionJob job2 = createTestJob("job-2", "table-2", "partition-2");

        // When
        writer.writeJobs(TEST_BUCKET, key, List.of(job1));
        writer.writeJobs(TEST_BUCKET, key, List.of(job2));

        // Then
        String storedJson = s3Client.getObjectAsString(TEST_BUCKET, key);
        List<CompactionJob> retrievedJobs = serDe.batchFromJson(storedJson);
        assertThat(retrievedJobs).containsExactly(job2);
    }

    private CompactionJob createTestJob(String jobId, String tableId, String partitionId) {
        return CompactionJob.builder()
                .tableId(tableId)
                .jobId(jobId)
                .partitionId(partitionId)
                .inputFiles(List.of("input-" + jobId + ".parquet"))
                .outputFile("output-" + jobId + ".parquet")
                .build();
    }
}
