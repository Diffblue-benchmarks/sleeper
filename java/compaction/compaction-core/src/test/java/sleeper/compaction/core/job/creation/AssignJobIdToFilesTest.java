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
package sleeper.compaction.core.job.creation;

import org.junit.jupiter.api.Test;

import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.commit.StateStoreCommitRequestSender;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.statestore.transactionlog.transaction.impl.AssignJobIdsTransaction;
import sleeper.core.table.TableStatus;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.statestore.AssignJobIdRequest.assignJobOnPartitionToFiles;
import static sleeper.core.statestore.FileReferenceTestData.DEFAULT_UPDATE_TIME;
import static sleeper.core.statestore.FileReferenceTestData.withJobId;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

public class AssignJobIdToFilesTest {

    private final Schema schema = Schema.builder().rowKeyFields(new Field("key", new StringType())).build();
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
    private final StateStore stateStore = createStateStore();
    private final TableStatus tableStatus = TableStatus.uniqueIdAndName(tableProperties.get(sleeper.core.properties.table.TableProperty.TABLE_ID), "test-table", true);

    @Test
    void shouldAssignJobIdsSynchronously() throws Exception {
        // Given
        update(stateStore).initialise(new PartitionsBuilder(schema).singlePartition("root").buildList());
        FileReference file1 = fileFactory().rootFile("file1.parquet", 100L);
        FileReference file2 = fileFactory().rootFile("file2.parquet", 200L);
        update(stateStore).addFiles(List.of(file1, file2));
        AssignJobIdToFiles assignJobIds = AssignJobIdToFiles.synchronous(stateStore);

        // When
        assignJobIds.assignJobIds(
                List.of(assignJobOnPartitionToFiles("job-1", "root", List.of("file1.parquet", "file2.parquet"))),
                tableStatus);

        // Then
        assertThat(stateStore.getFileReferences()).containsExactly(
                withJobId("job-1", file1),
                withJobId("job-1", file2));
    }

    @Test
    void shouldAssignMultipleJobIdsSynchronously() throws Exception {
        // Given
        update(stateStore).initialise(new PartitionsBuilder(schema)
                .rootFirst("root")
                .splitToNewChildren("root", "L", "R", "m")
                .buildList());
        FileReference file1 = fileFactory().partitionFile("L", "file1.parquet", 100L);
        FileReference file2 = fileFactory().partitionFile("R", "file2.parquet", 200L);
        FileReference file3 = fileFactory().partitionFile("R", "file3.parquet", 300L);
        update(stateStore).addFiles(List.of(file1, file2, file3));
        AssignJobIdToFiles assignJobIds = AssignJobIdToFiles.synchronous(stateStore);

        // When
        assignJobIds.assignJobIds(
                List.of(
                        assignJobOnPartitionToFiles("job-1", "L", List.of("file1.parquet")),
                        assignJobOnPartitionToFiles("job-2", "R", List.of("file2.parquet", "file3.parquet"))),
                tableStatus);

        // Then
        assertThat(stateStore.getFileReferences()).containsExactly(
                withJobId("job-1", file1),
                withJobId("job-2", file2),
                withJobId("job-2", file3));
    }

    private FileReferenceFactory fileFactory() {
        return FileReferenceFactory.fromUpdatedAt(stateStore, DEFAULT_UPDATE_TIME);
    }

    private StateStore createStateStore() {
        StateStore stateStore = InMemoryTransactionLogStateStore.create(tableProperties, new InMemoryTransactionLogs());
        stateStore.fixFileUpdateTime(DEFAULT_UPDATE_TIME);
        return stateStore;
    }

    @Test
    void shouldSendJobIdAssignmentToQueue() {
        // Given
        List<StateStoreCommitRequest> sentRequests = new ArrayList<>();
        StateStoreCommitRequestSender queueSender = sentRequests::add;
        AssignJobIdToFiles assignJobIds = AssignJobIdToFiles.byQueue(queueSender);

        // When
        assignJobIds.assignJobIds(
                List.of(assignJobOnPartitionToFiles("job-1", "partition-1", List.of("file1.parquet", "file2.parquet"))),
                tableStatus);

        // Then
        assertThat(sentRequests).containsExactly(
                StateStoreCommitRequest.create(
                        tableProperties.get(sleeper.core.properties.table.TableProperty.TABLE_ID),
                        new AssignJobIdsTransaction(
                                List.of(assignJobOnPartitionToFiles("job-1", "partition-1", List.of("file1.parquet", "file2.parquet"))))));
    }

    @Test
    void shouldSendMultipleJobIdAssignmentsToQueue() {
        // Given
        List<StateStoreCommitRequest> sentRequests = new ArrayList<>();
        StateStoreCommitRequestSender queueSender = sentRequests::add;
        AssignJobIdToFiles assignJobIds = AssignJobIdToFiles.byQueue(queueSender);

        // When
        assignJobIds.assignJobIds(
                List.of(
                        assignJobOnPartitionToFiles("job-1", "partition-1", List.of("file1.parquet")),
                        assignJobOnPartitionToFiles("job-2", "partition-2", List.of("file2.parquet", "file3.parquet"))),
                tableStatus);

        // Then
        assertThat(sentRequests).containsExactly(
                StateStoreCommitRequest.create(
                        tableProperties.get(sleeper.core.properties.table.TableProperty.TABLE_ID),
                        new AssignJobIdsTransaction(
                                List.of(
                                        assignJobOnPartitionToFiles("job-1", "partition-1", List.of("file1.parquet")),
                                        assignJobOnPartitionToFiles("job-2", "partition-2", List.of("file2.parquet", "file3.parquet"))))));
    }
}
