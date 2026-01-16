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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.AssignJobIdRequest;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.exception.FileReferenceAssignedToJobException;
import sleeper.core.statestore.exception.FileReferenceNotFoundException;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.core.statestore.transactionlog.transaction.impl.AssignJobIdsTransaction;
import sleeper.core.table.TableStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.AssignJobIdRequest.assignJobOnPartitionToFiles;
import static sleeper.core.statestore.FileReferenceTestData.withJobId;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

public class AssignJobIdToFilesClaudeTest {

    private static final Instant DEFAULT_FILE_UPDATE_TIME = FilesReportTestHelper.DEFAULT_UPDATE_TIME;
    private final Schema schema = schemaWithKey("key", new StringType());
    private final PartitionTree partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
    private final FileReferenceFactory fileFactory = FileReferenceFactory.fromUpdatedAt(partitions, DEFAULT_FILE_UPDATE_TIME);
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final InMemoryTransactionLogs transactionLogs = new InMemoryTransactionLogs();

    @Nested
    @DisplayName("synchronous method")
    class Synchronous {

        @Test
        void shouldAssignJobIdToSingleFile() throws Exception {
            // Given
            TableProperties tableProperties = createTableProperties();
            StateStore stateStore = createStateStore(tableProperties);
            FileReference file = fileFactory.rootFile("file.parquet", 100L);
            update(stateStore).addFile(file);
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.synchronous(stateStore);

            // When
            assignJobIdToFiles.assignJobIds(
                    List.of(assignJobOnPartitionToFiles("test-job", "root", List.of("file.parquet"))),
                    tableProperties.getStatus());

            // Then
            assertThat(stateStore.getFileReferences()).containsExactly(
                    withJobId("test-job", file));
        }

        @Test
        void shouldAssignJobIdToMultipleFiles() throws Exception {
            // Given
            TableProperties tableProperties = createTableProperties();
            StateStore stateStore = createStateStore(tableProperties);
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.synchronous(stateStore);

            // When
            assignJobIdToFiles.assignJobIds(
                    List.of(assignJobOnPartitionToFiles("test-job", "root", List.of("file1.parquet", "file2.parquet"))),
                    tableProperties.getStatus());

            // Then
            assertThat(stateStore.getFileReferences()).containsExactlyInAnyOrder(
                    withJobId("test-job", file1),
                    withJobId("test-job", file2));
        }

        @Test
        void shouldAssignJobIdsToFilesFromMultipleRequests() throws Exception {
            // Given
            TableProperties tableProperties = createTableProperties();
            StateStore stateStore = createStateStore(tableProperties);
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.synchronous(stateStore);

            // When
            assignJobIdToFiles.assignJobIds(
                    List.of(
                            assignJobOnPartitionToFiles("job-1", "root", List.of("file1.parquet")),
                            assignJobOnPartitionToFiles("job-2", "root", List.of("file2.parquet"))),
                    tableProperties.getStatus());

            // Then
            assertThat(stateStore.getFileReferences()).containsExactlyInAnyOrder(
                    withJobId("job-1", file1),
                    withJobId("job-2", file2));
        }

        @Test
        void shouldThrowExceptionWhenFileDoesNotExist() {
            // Given
            TableProperties tableProperties = createTableProperties();
            StateStore stateStore = createStateStore(tableProperties);
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.synchronous(stateStore);

            // When / Then
            assertThatThrownBy(() -> assignJobIdToFiles.assignJobIds(
                    List.of(assignJobOnPartitionToFiles("test-job", "root", List.of("nonexistent.parquet"))),
                    tableProperties.getStatus()))
                    .isInstanceOf(FileReferenceNotFoundException.class);
        }

        @Test
        void shouldThrowExceptionWhenFileIsAlreadyAssignedToJob() throws Exception {
            // Given
            TableProperties tableProperties = createTableProperties();
            StateStore stateStore = createStateStore(tableProperties);
            FileReference file = fileFactory.rootFile("file.parquet", 100L);
            update(stateStore).addFile(file);
            update(stateStore).assignJobIds(List.of(assignJobOnPartitionToFiles("existing-job", "root", List.of("file.parquet"))));
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.synchronous(stateStore);

            // When / Then
            assertThatThrownBy(() -> assignJobIdToFiles.assignJobIds(
                    List.of(assignJobOnPartitionToFiles("new-job", "root", List.of("file.parquet"))),
                    tableProperties.getStatus()))
                    .isInstanceOf(FileReferenceAssignedToJobException.class);
        }

        @Test
        void shouldHandleEmptyRequestsList() throws Exception {
            // Given
            TableProperties tableProperties = createTableProperties();
            StateStore stateStore = createStateStore(tableProperties);
            FileReference file = fileFactory.rootFile("file.parquet", 100L);
            update(stateStore).addFile(file);
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.synchronous(stateStore);

            // When
            assignJobIdToFiles.assignJobIds(List.of(), tableProperties.getStatus());

            // Then - file should remain unchanged with no job ID
            assertThat(stateStore.getFileReferences()).containsExactly(file);
        }

        @Test
        void shouldReturnFunctionalInterface() {
            // Given
            TableProperties tableProperties = createTableProperties();
            StateStore stateStore = createStateStore(tableProperties);

            // When
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.synchronous(stateStore);

            // Then
            assertThat(assignJobIdToFiles).isNotNull();
        }
    }

    @Nested
    @DisplayName("byQueue method")
    class ByQueue {

        private final List<StateStoreCommitRequest> sentRequests = new ArrayList<>();

        @Test
        void shouldSendCommitRequestToQueue() {
            // Given
            TableProperties tableProperties = createTableProperties();
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.byQueue(sentRequests::add);
            TableStatus tableStatus = tableProperties.getStatus();

            // When
            assignJobIdToFiles.assignJobIds(
                    List.of(assignJobOnPartitionToFiles("test-job", "root", List.of("file.parquet"))),
                    tableStatus);

            // Then
            assertThat(sentRequests).hasSize(1);
            StateStoreCommitRequest request = sentRequests.get(0);
            assertThat(request.getTableId()).isEqualTo(tableStatus.getTableUniqueId());
        }

        @Test
        void shouldSendCommitRequestWithCorrectTransactionType() {
            // Given
            TableProperties tableProperties = createTableProperties();
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.byQueue(sentRequests::add);

            // When
            assignJobIdToFiles.assignJobIds(
                    List.of(assignJobOnPartitionToFiles("test-job", "root", List.of("file.parquet"))),
                    tableProperties.getStatus());

            // Then
            assertThat(sentRequests.get(0).getTransactionType()).isEqualTo(TransactionType.ASSIGN_JOB_IDS);
        }

        @Test
        void shouldSendCommitRequestWithAssignJobIdsTransaction() {
            // Given
            TableProperties tableProperties = createTableProperties();
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.byQueue(sentRequests::add);

            // When
            assignJobIdToFiles.assignJobIds(
                    List.of(assignJobOnPartitionToFiles("test-job", "root", List.of("file.parquet"))),
                    tableProperties.getStatus());

            // Then
            Optional<AssignJobIdsTransaction> transaction = sentRequests.get(0).getTransactionIfHeld();
            assertThat(transaction).isPresent();
        }

        @Test
        void shouldIncludeAllRequestsInTransaction() {
            // Given
            TableProperties tableProperties = createTableProperties();
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.byQueue(sentRequests::add);
            List<AssignJobIdRequest> requests = List.of(
                    assignJobOnPartitionToFiles("job-1", "root", List.of("file1.parquet")),
                    assignJobOnPartitionToFiles("job-2", "root", List.of("file2.parquet")));

            // When
            assignJobIdToFiles.assignJobIds(requests, tableProperties.getStatus());

            // Then
            assertThat(sentRequests).hasSize(1);
            Optional<AssignJobIdsTransaction> transaction = sentRequests.get(0).getTransactionIfHeld();
            assertThat(transaction).isPresent();
            AssignJobIdsTransaction assignJobIdsTransaction = transaction.get();
            // The transaction should have the original requests
            assertThat(assignJobIdsTransaction).isEqualTo(new AssignJobIdsTransaction(requests));
        }

        @Test
        void shouldUseTableUniqueIdFromTableStatus() {
            // Given
            TableProperties tableProperties = createTableProperties();
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.byQueue(sentRequests::add);
            TableStatus tableStatus = tableProperties.getStatus();

            // When
            assignJobIdToFiles.assignJobIds(
                    List.of(assignJobOnPartitionToFiles("test-job", "root", List.of("file.parquet"))),
                    tableStatus);

            // Then
            assertThat(sentRequests.get(0).getTableId()).isEqualTo(tableStatus.getTableUniqueId());
        }

        @Test
        void shouldHandleEmptyRequestsList() {
            // Given
            TableProperties tableProperties = createTableProperties();
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.byQueue(sentRequests::add);

            // When
            assignJobIdToFiles.assignJobIds(List.of(), tableProperties.getStatus());

            // Then - should still send a request with empty transaction
            assertThat(sentRequests).hasSize(1);
        }

        @Test
        void shouldFilterOutRequestsWithEmptyFilenamesList() {
            // Given
            TableProperties tableProperties = createTableProperties();
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.byQueue(sentRequests::add);
            List<AssignJobIdRequest> requests = List.of(
                    assignJobOnPartitionToFiles("job-1", "root", List.of("file.parquet")),
                    assignJobOnPartitionToFiles("job-2", "root", List.of()));

            // When
            assignJobIdToFiles.assignJobIds(requests, tableProperties.getStatus());

            // Then - transaction should only contain the non-empty request
            Optional<AssignJobIdsTransaction> transaction = sentRequests.get(0).getTransactionIfHeld();
            assertThat(transaction).isPresent();
            // The AssignJobIdsTransaction constructor filters out requests with empty filenames
            AssignJobIdsTransaction expectedTransaction = new AssignJobIdsTransaction(List.of(
                    assignJobOnPartitionToFiles("job-1", "root", List.of("file.parquet"))));
            assertThat(transaction.get()).isEqualTo(expectedTransaction);
        }

        @Test
        void shouldReturnFunctionalInterface() {
            // When
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.byQueue(sentRequests::add);

            // Then
            assertThat(assignJobIdToFiles).isNotNull();
        }

        @Test
        void shouldSendMultipleFilesInSingleRequest() {
            // Given
            TableProperties tableProperties = createTableProperties();
            AssignJobIdToFiles assignJobIdToFiles = AssignJobIdToFiles.byQueue(sentRequests::add);

            // When
            assignJobIdToFiles.assignJobIds(
                    List.of(assignJobOnPartitionToFiles("test-job", "root",
                            List.of("file1.parquet", "file2.parquet", "file3.parquet"))),
                    tableProperties.getStatus());

            // Then
            assertThat(sentRequests).hasSize(1);
            Optional<AssignJobIdsTransaction> transaction = sentRequests.get(0).getTransactionIfHeld();
            assertThat(transaction).isPresent();
            AssignJobIdsTransaction expectedTransaction = new AssignJobIdsTransaction(List.of(
                    assignJobOnPartitionToFiles("test-job", "root",
                            List.of("file1.parquet", "file2.parquet", "file3.parquet"))));
            assertThat(transaction.get()).isEqualTo(expectedTransaction);
        }
    }

    private TableProperties createTableProperties() {
        return createTestTableProperties(instanceProperties, schema);
    }

    private StateStore createStateStore(TableProperties tableProperties) {
        StateStore stateStore = InMemoryTransactionLogStateStore.create(tableProperties, transactionLogs);
        update(stateStore).initialise(partitions.getAllPartitions());
        stateStore.fixFileUpdateTime(DEFAULT_FILE_UPDATE_TIME);
        return stateStore;
    }
}
