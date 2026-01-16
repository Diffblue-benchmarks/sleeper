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
package sleeper.compaction.core.job.commit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.ReplaceFileReferencesRequest;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.transactionlog.transaction.impl.ReplaceFileReferencesTransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class CompactionCommitBatcherClaudeTest {

    private static final String TABLE_ID_1 = "test-table-1";
    private static final String TABLE_ID_2 = "test-table-2";
    private static final String PARTITION_ID = "root";

    private final List<StateStoreCommitRequest> sentRequests = new ArrayList<>();

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Test
        void shouldCreateBatcherWithSender() {
            // When
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(sentRequests::add);

            // Then - batcher is created and can be used
            batcher.sendBatch(Collections.emptyList());
            assertThat(sentRequests).isEmpty();
        }
    }

    @Nested
    @DisplayName("Send batch with single request")
    class SingleRequest {

        @Test
        void shouldSendSingleRequestAsTransaction() {
            // Given
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(sentRequests::add);
            CompactionCommitMessageHandle handle = createHandle(TABLE_ID_1, "job-1", List.of("input1.parquet"), "output1.parquet");

            // When
            batcher.sendBatch(List.of(handle));

            // Then
            assertThat(sentRequests).hasSize(1);
            StateStoreCommitRequest request = sentRequests.get(0);
            assertThat(request.getTableId()).isEqualTo(TABLE_ID_1);

            Optional<ReplaceFileReferencesTransaction> transaction = request.getTransactionIfHeld();
            assertThat(transaction).isPresent();
            assertThat(transaction.get().getJobs()).hasSize(1);
            assertThat(transaction.get().getJobs().get(0).getJobId()).isEqualTo("job-1");
        }

        @Test
        void shouldIncludeInputFilesInTransaction() {
            // Given
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(sentRequests::add);
            CompactionCommitMessageHandle handle = createHandle(TABLE_ID_1, "job-1",
                    List.of("input1.parquet", "input2.parquet", "input3.parquet"), "output.parquet");

            // When
            batcher.sendBatch(List.of(handle));

            // Then
            Optional<ReplaceFileReferencesTransaction> transaction = sentRequests.get(0).getTransactionIfHeld();
            assertThat(transaction).isPresent();
            assertThat(transaction.get().getJobs().get(0).getInputFiles())
                    .containsExactly("input1.parquet", "input2.parquet", "input3.parquet");
        }

        @Test
        void shouldIncludeOutputFileReferenceInTransaction() {
            // Given
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(sentRequests::add);
            CompactionCommitMessageHandle handle = createHandle(TABLE_ID_1, "job-1",
                    List.of("input.parquet"), "output.parquet", 500L);

            // When
            batcher.sendBatch(List.of(handle));

            // Then
            Optional<ReplaceFileReferencesTransaction> transaction = sentRequests.get(0).getTransactionIfHeld();
            assertThat(transaction).isPresent();
            FileReference newRef = transaction.get().getJobs().get(0).getNewReference();
            assertThat(newRef.getFilename()).isEqualTo("output.parquet");
            assertThat(newRef.getNumberOfRecords()).isEqualTo(500L);
            assertThat(newRef.getPartitionId()).isEqualTo(PARTITION_ID);
        }
    }

    @Nested
    @DisplayName("Batch multiple requests for same table")
    class BatchSameTable {

        @Test
        void shouldCombineMultipleRequestsForSameTableIntoSingleTransaction() {
            // Given
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(sentRequests::add);
            CompactionCommitMessageHandle handle1 = createHandle(TABLE_ID_1, "job-1", List.of("input1.parquet"), "output1.parquet");
            CompactionCommitMessageHandle handle2 = createHandle(TABLE_ID_1, "job-2", List.of("input2.parquet"), "output2.parquet");
            CompactionCommitMessageHandle handle3 = createHandle(TABLE_ID_1, "job-3", List.of("input3.parquet"), "output3.parquet");

            // When
            batcher.sendBatch(List.of(handle1, handle2, handle3));

            // Then - should send a single request for all three jobs
            assertThat(sentRequests).hasSize(1);
            StateStoreCommitRequest request = sentRequests.get(0);
            assertThat(request.getTableId()).isEqualTo(TABLE_ID_1);

            Optional<ReplaceFileReferencesTransaction> transaction = request.getTransactionIfHeld();
            assertThat(transaction).isPresent();
            assertThat(transaction.get().getJobs()).hasSize(3);
            assertThat(transaction.get().getJobs())
                    .extracting(ReplaceFileReferencesRequest::getJobId)
                    .containsExactlyInAnyOrder("job-1", "job-2", "job-3");
        }

        @Test
        void shouldPreserveAllJobDetailsInBatchedTransaction() {
            // Given
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(sentRequests::add);
            CompactionCommitMessageHandle handle1 = createHandle(TABLE_ID_1, "job-1",
                    List.of("a1.parquet", "a2.parquet"), "outputA.parquet", 100L);
            CompactionCommitMessageHandle handle2 = createHandle(TABLE_ID_1, "job-2",
                    List.of("b1.parquet"), "outputB.parquet", 200L);

            // When
            batcher.sendBatch(List.of(handle1, handle2));

            // Then
            Optional<ReplaceFileReferencesTransaction> transaction = sentRequests.get(0).getTransactionIfHeld();
            assertThat(transaction).isPresent();

            List<ReplaceFileReferencesRequest> jobs = transaction.get().getJobs();
            ReplaceFileReferencesRequest job1 = jobs.stream().filter(j -> j.getJobId().equals("job-1")).findFirst().orElseThrow();
            ReplaceFileReferencesRequest job2 = jobs.stream().filter(j -> j.getJobId().equals("job-2")).findFirst().orElseThrow();

            assertThat(job1.getInputFiles()).containsExactly("a1.parquet", "a2.parquet");
            assertThat(job1.getNewReference().getFilename()).isEqualTo("outputA.parquet");
            assertThat(job1.getNewReference().getNumberOfRecords()).isEqualTo(100L);

            assertThat(job2.getInputFiles()).containsExactly("b1.parquet");
            assertThat(job2.getNewReference().getFilename()).isEqualTo("outputB.parquet");
            assertThat(job2.getNewReference().getNumberOfRecords()).isEqualTo(200L);
        }
    }

    @Nested
    @DisplayName("Group requests by table")
    class GroupByTable {

        @Test
        void shouldSendSeparateTransactionsForDifferentTables() {
            // Given
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(sentRequests::add);
            CompactionCommitMessageHandle handleTable1 = createHandle(TABLE_ID_1, "job-1", List.of("input1.parquet"), "output1.parquet");
            CompactionCommitMessageHandle handleTable2 = createHandle(TABLE_ID_2, "job-2", List.of("input2.parquet"), "output2.parquet");

            // When
            batcher.sendBatch(List.of(handleTable1, handleTable2));

            // Then - should send two separate requests, one for each table
            assertThat(sentRequests).hasSize(2);
            assertThat(sentRequests)
                    .extracting(StateStoreCommitRequest::getTableId)
                    .containsExactlyInAnyOrder(TABLE_ID_1, TABLE_ID_2);

            // Each request should have exactly one job
            for (StateStoreCommitRequest request : sentRequests) {
                Optional<ReplaceFileReferencesTransaction> transaction = request.getTransactionIfHeld();
                assertThat(transaction).isPresent();
                assertThat(transaction.get().getJobs()).hasSize(1);
            }
        }

        @Test
        void shouldGroupMultipleRequestsByTableId() {
            // Given
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(sentRequests::add);
            CompactionCommitMessageHandle handleTable1Job1 = createHandle(TABLE_ID_1, "job-1", List.of("input1.parquet"), "output1.parquet");
            CompactionCommitMessageHandle handleTable2Job1 = createHandle(TABLE_ID_2, "job-2", List.of("input2.parquet"), "output2.parquet");
            CompactionCommitMessageHandle handleTable1Job2 = createHandle(TABLE_ID_1, "job-3", List.of("input3.parquet"), "output3.parquet");
            CompactionCommitMessageHandle handleTable2Job2 = createHandle(TABLE_ID_2, "job-4", List.of("input4.parquet"), "output4.parquet");

            // When
            batcher.sendBatch(List.of(handleTable1Job1, handleTable2Job1, handleTable1Job2, handleTable2Job2));

            // Then - should send two requests
            assertThat(sentRequests).hasSize(2);

            // Find request for table 1
            StateStoreCommitRequest requestTable1 = sentRequests.stream()
                    .filter(r -> r.getTableId().equals(TABLE_ID_1))
                    .findFirst().orElseThrow();
            Optional<ReplaceFileReferencesTransaction> transactionTable1 = requestTable1.getTransactionIfHeld();
            assertThat(transactionTable1).isPresent();
            assertThat(transactionTable1.get().getJobs())
                    .extracting(ReplaceFileReferencesRequest::getJobId)
                    .containsExactlyInAnyOrder("job-1", "job-3");

            // Find request for table 2
            StateStoreCommitRequest requestTable2 = sentRequests.stream()
                    .filter(r -> r.getTableId().equals(TABLE_ID_2))
                    .findFirst().orElseThrow();
            Optional<ReplaceFileReferencesTransaction> transactionTable2 = requestTable2.getTransactionIfHeld();
            assertThat(transactionTable2).isPresent();
            assertThat(transactionTable2.get().getJobs())
                    .extracting(ReplaceFileReferencesRequest::getJobId)
                    .containsExactlyInAnyOrder("job-2", "job-4");
        }
    }

    @Nested
    @DisplayName("Empty batch")
    class EmptyBatch {

        @Test
        void shouldHandleEmptyList() {
            // Given
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(sentRequests::add);

            // When
            batcher.sendBatch(Collections.emptyList());

            // Then - no requests should be sent
            assertThat(sentRequests).isEmpty();
        }
    }

    @Nested
    @DisplayName("Error handling")
    class ErrorHandling {

        @Test
        void shouldCallFailureCallbackWhenSendingFails() {
            // Given
            AtomicInteger failureCount = new AtomicInteger(0);
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(request -> {
                throw new RuntimeException("Simulated send failure");
            });
            CompactionCommitMessageHandle handle = createHandleWithCallback(TABLE_ID_1, "job-1",
                    List.of("input.parquet"), "output.parquet", failureCount::incrementAndGet);

            // When
            batcher.sendBatch(List.of(handle));

            // Then - failure callback should have been called
            assertThat(failureCount.get()).isEqualTo(1);
        }

        @Test
        void shouldCallFailureCallbackForAllRequestsInFailedBatch() {
            // Given
            AtomicInteger failureCount = new AtomicInteger(0);
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(request -> {
                throw new RuntimeException("Simulated send failure");
            });
            CompactionCommitMessageHandle handle1 = createHandleWithCallback(TABLE_ID_1, "job-1",
                    List.of("input1.parquet"), "output1.parquet", failureCount::incrementAndGet);
            CompactionCommitMessageHandle handle2 = createHandleWithCallback(TABLE_ID_1, "job-2",
                    List.of("input2.parquet"), "output2.parquet", failureCount::incrementAndGet);
            CompactionCommitMessageHandle handle3 = createHandleWithCallback(TABLE_ID_1, "job-3",
                    List.of("input3.parquet"), "output3.parquet", failureCount::incrementAndGet);

            // When
            batcher.sendBatch(List.of(handle1, handle2, handle3));

            // Then - failure callback should have been called for each request
            assertThat(failureCount.get()).isEqualTo(3);
        }

        @Test
        void shouldOnlyCallFailureCallbackForFailedTableNotSuccessfulOnes() {
            // Given
            AtomicInteger table1FailureCount = new AtomicInteger(0);
            AtomicInteger table2FailureCount = new AtomicInteger(0);

            CompactionCommitBatcher batcher = new CompactionCommitBatcher(request -> {
                if (request.getTableId().equals(TABLE_ID_2)) {
                    throw new RuntimeException("Simulated failure for table 2");
                }
                sentRequests.add(request);
            });

            CompactionCommitMessageHandle handleTable1 = createHandleWithCallback(TABLE_ID_1, "job-1",
                    List.of("input1.parquet"), "output1.parquet", table1FailureCount::incrementAndGet);
            CompactionCommitMessageHandle handleTable2 = createHandleWithCallback(TABLE_ID_2, "job-2",
                    List.of("input2.parquet"), "output2.parquet", table2FailureCount::incrementAndGet);

            // When
            batcher.sendBatch(List.of(handleTable1, handleTable2));

            // Then - only table 2's failure callback should be called
            assertThat(table1FailureCount.get()).isEqualTo(0);
            assertThat(table2FailureCount.get()).isEqualTo(1);
            // Table 1 should have been sent successfully
            assertThat(sentRequests).hasSize(1);
            assertThat(sentRequests.get(0).getTableId()).isEqualTo(TABLE_ID_1);
        }

        @Test
        void shouldContinueProcessingOtherTablesAfterOneTableFails() {
            // Given
            AtomicInteger failureCount = new AtomicInteger(0);
            String failingTableId = "failing-table";

            CompactionCommitBatcher batcher = new CompactionCommitBatcher(request -> {
                if (request.getTableId().equals(failingTableId)) {
                    throw new RuntimeException("Simulated failure");
                }
                sentRequests.add(request);
            });

            CompactionCommitMessageHandle handleFailing = createHandleWithCallback(failingTableId, "job-1",
                    List.of("input1.parquet"), "output1.parquet", failureCount::incrementAndGet);
            CompactionCommitMessageHandle handleSuccess1 = createHandle(TABLE_ID_1, "job-2",
                    List.of("input2.parquet"), "output2.parquet");
            CompactionCommitMessageHandle handleSuccess2 = createHandle(TABLE_ID_2, "job-3",
                    List.of("input3.parquet"), "output3.parquet");

            // When
            batcher.sendBatch(List.of(handleFailing, handleSuccess1, handleSuccess2));

            // Then - failure callback called for failing table, other tables processed
            assertThat(failureCount.get()).isEqualTo(1);
            assertThat(sentRequests).hasSize(2);
            assertThat(sentRequests)
                    .extracting(StateStoreCommitRequest::getTableId)
                    .containsExactlyInAnyOrder(TABLE_ID_1, TABLE_ID_2);
        }
    }

    @Nested
    @DisplayName("Task and job run ID handling")
    class TaskAndJobRunId {

        @Test
        void shouldIncludeTaskIdAndJobRunIdInTransaction() {
            // Given
            CompactionCommitBatcher batcher = new CompactionCommitBatcher(sentRequests::add);
            ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                    .jobId("job-1")
                    .taskId("task-123")
                    .jobRunId("run-456")
                    .inputFiles(List.of("input.parquet"))
                    .newReference(createFileReference("output.parquet", 100L))
                    .build();
            CompactionCommitMessageHandle handle = new CompactionCommitMessageHandle(TABLE_ID_1, request, () -> {
            });

            // When
            batcher.sendBatch(List.of(handle));

            // Then
            Optional<ReplaceFileReferencesTransaction> transaction = sentRequests.get(0).getTransactionIfHeld();
            assertThat(transaction).isPresent();
            ReplaceFileReferencesRequest job = transaction.get().getJobs().get(0);
            assertThat(job.getTaskId()).isEqualTo("task-123");
            assertThat(job.getJobRunId()).isEqualTo("run-456");
        }
    }

    private CompactionCommitMessageHandle createHandle(String tableId, String jobId, List<String> inputFiles, String outputFile) {
        return createHandle(tableId, jobId, inputFiles, outputFile, 100L);
    }

    private CompactionCommitMessageHandle createHandle(String tableId, String jobId, List<String> inputFiles, String outputFile, long records) {
        ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                .jobId(jobId)
                .inputFiles(inputFiles)
                .newReference(createFileReference(outputFile, records))
                .build();
        return new CompactionCommitMessageHandle(tableId, request, () -> {
        });
    }

    private CompactionCommitMessageHandle createHandleWithCallback(String tableId, String jobId, List<String> inputFiles,
            String outputFile, Runnable onFail) {
        ReplaceFileReferencesRequest request = ReplaceFileReferencesRequest.builder()
                .jobId(jobId)
                .inputFiles(inputFiles)
                .newReference(createFileReference(outputFile, 100L))
                .build();
        return new CompactionCommitMessageHandle(tableId, request, onFail);
    }

    private FileReference createFileReference(String filename, long records) {
        return FileReference.builder()
                .filename(filename)
                .partitionId(PARTITION_ID)
                .numberOfRecords(records)
                .countApproximate(false)
                .onlyContainsDataForThisPartition(true)
                .build();
    }
}
