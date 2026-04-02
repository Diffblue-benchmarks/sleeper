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

import org.junit.jupiter.api.Test;

import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.testutils.FixedStateStoreProvider;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_COMMIT_ASYNC;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

public class CompactionJobCommitterOrSendToLambdaTest {

    private final Schema schema = schemaWithKey("key");
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
    private final PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
    private final StateStore stateStore = InMemoryTransactionLogStateStore
            .createAndInitialiseWithPartitions(partitions.getAllPartitions(), tableProperties, new InMemoryTransactionLogs());
    private final FileReferenceFactory fileFactory = FileReferenceFactory.from(partitions);
    private final CompactionJobFactory jobFactory = new CompactionJobFactory(instanceProperties, tableProperties);
    private final InMemoryCompactionJobTracker tracker = new InMemoryCompactionJobTracker();

    @Test
    void shouldCommitSynchronouslyWhenConstructedWithFiveArgConstructor() throws Exception {
        // Given
        tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "false");
        FileReference inputFile = fileFactory.rootFile("input.parquet", 100L);
        update(stateStore).addFile(inputFile);
        CompactionJob job = jobFactory.createCompactionJob("test-job", List.of(inputFile), "root");
        update(stateStore).assignJobIds(List.of(job.createAssignJobIdRequest()));
        tracker.jobCreated(job.createCreatedEvent(), Instant.parse("2024-01-01T10:00:00Z"));

        CompactionJobCommitterOrSendToLambda committer = new CompactionJobCommitterOrSendToLambda(
                new FixedTablePropertiesProvider(tableProperties),
                new FixedStateStoreProvider(tableProperties, stateStore),
                tracker,
                commitRequest -> {
                },
                batchMessage -> {
                });

        CompactionJobFinishedEvent finishedEvent = CompactionJobFinishedEvent.builder()
                .jobId(job.getId())
                .tableId(tableProperties.get(TABLE_ID))
                .taskId("test-task")
                .jobRunId("test-run")
                .summary(new JobRunSummary(
                        new RecordsProcessed(100L, 90L),
                        Instant.parse("2024-01-01T10:01:00Z"),
                        Instant.parse("2024-01-01T10:02:00Z")))
                .build();

        // When
        committer.commit(job, finishedEvent);

        // Then
        assertThat(stateStore.getFileReferences())
                .extracting(FileReference::getFilename)
                .containsExactly(job.getOutputFile());
    }
}
