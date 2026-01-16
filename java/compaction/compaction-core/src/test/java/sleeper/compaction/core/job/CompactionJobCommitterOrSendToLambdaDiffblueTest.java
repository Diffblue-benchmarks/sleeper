package sleeper.compaction.core.job;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.CompactionJobCommitterOrSendToLambda.BatchedCommitQueueSender;
import sleeper.compaction.core.job.CompactionJobCommitterOrSendToLambda.CommitQueueSender;
import sleeper.compaction.core.job.commit.CompactionCommitMessage;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.ReplaceFileReferencesRequest;
import sleeper.core.statestore.ReplaceFileReferencesRequest.Builder;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.testutils.InMemoryTransactionBodyStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStore;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.statestore.transactionlog.log.DuplicateTransactionNumberException;
import sleeper.core.statestore.transactionlog.log.TransactionLogEntry;
import sleeper.core.statestore.transactionlog.snapshot.TransactionLogSnapshotLoader;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.core.util.ExponentialBackoffWithJitter;
import sleeper.core.util.ExponentialBackoffWithJitter.WaitRange;

class CompactionJobCommitterOrSendToLambdaDiffblueTest {
  /**
   * Test {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob,
   * CompactionJobFinishedEvent)}.
   *
   * <ul>
   *   <li>Then calls {@link CompactionJob#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob,
   * CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test commit(CompactionJob, CompactionJobFinishedEvent); then calls getId()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CompactionJobCommitterOrSendToLambda.commit(CompactionJob, CompactionJobFinishedEvent)"
  })
  void testCommit_thenCallsGetId() throws DuplicateTransactionNumberException {
    // Arrange
    InMemoryTransactionLogStore filesLogStore = mock(InMemoryTransactionLogStore.class);
    doNothing().when(filesLogStore).addTransaction(Mockito.<TransactionLogEntry>any());
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    AddFilesTransaction.Builder builderResult = AddFilesTransaction.builder();
    AddFilesTransaction transaction =
        builderResult
            .files(new ArrayList<>())
            .jobId("42")
            .jobRunId("42")
            .taskId("42")
            .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .build();

    TransactionLogEntry entry = new TransactionLogEntry(1L, updateTime, transaction);
    filesLogStore.addTransaction(entry);

    TransactionLogStateStore.Builder minTransactionsAheadToLoadSnapshotResult =
        TransactionLogStateStore.builder()
            .filesLogStore(filesLogStore)
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class))
            .filesStateUpdateClock(mock(Supplier.class))
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    TransactionLogStateStore.Builder partitionsStateUpdateClockResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class))
            .partitionsStateUpdateClock(mock(Supplier.class));
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    TransactionLogStateStore.Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    TransactionLogStateStore.Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);
    when(tableProperties.getStatus()).thenReturn(uniqueIdAndNameResult);

    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);

    InMemoryCompactionJobTracker tracker = mock(InMemoryCompactionJobTracker.class);
    doNothing().when(tracker).jobFinished(Mockito.<CompactionJobFinishedEvent>any());

    BatchedCommitQueueSender batchedCommitQueueSender = mock(BatchedCommitQueueSender.class);
    doNothing().when(batchedCommitQueueSender).send(Mockito.<CompactionCommitMessage>any());
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    CompactionJobCommitterOrSendToLambda compactionJobCommitterOrSendToLambda =
        new CompactionJobCommitterOrSendToLambda(
            tablePropertiesProvider,
            stateStoreProvider,
            tracker,
            mock(CommitQueueSender.class),
            batchedCommitQueueSender);

    Builder builder = mock(Builder.class);

    Builder builderResult2 = ReplaceFileReferencesRequest.builder();

    Builder jobRunIdResult =
        builderResult2.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    when(builder.build())
        .thenReturn(
            jobRunIdResult
                .newReference(
                    FileReference.builder()
                        .countApproximate(true)
                        .filename("foo.txt")
                        .jobId("42")
                        .lastStateStoreUpdateTime(
                            LocalDate.of(1970, 1, 1)
                                .atStartOfDay()
                                .atZone(ZoneOffset.UTC)
                                .toInstant())
                        .numberOfRecords(1L)
                        .onlyContainsDataForThisPartition(true)
                        .partitionId("42")
                        .build())
                .taskId("42")
                .build());

    Builder builder2 = mock(Builder.class);
    when(builder2.jobRunId(Mockito.<String>any())).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.taskId(Mockito.<String>any())).thenReturn(builder2);

    CompactionJob job = mock(CompactionJob.class);
    when(job.getId()).thenReturn("42");
    when(job.replaceFileReferencesRequestBuilder(anyLong())).thenReturn(builder3);
    when(job.getTableId()).thenReturn("42");

    JobRunSummary summary = mock(JobRunSummary.class);
    when(summary.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act
    compactionJobCommitterOrSendToLambda.commit(
        job,
        CompactionJobFinishedEvent.builder()
            .jobId("42")
            .jobRunId("42")
            .summary(summary)
            .tableId("42")
            .taskId("42")
            .build());

    // Assert
    verify(job).getId();
    verify(job).getTableId();
    verify(job).replaceFileReferencesRequestBuilder(1L);
    verify(batchedCommitQueueSender).send(isA(CompactionCommitMessage.class));
    verify(tableProperties, atLeast(1)).getBoolean(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById("42");
    verify(builder).build();
    verify(builder2).jobRunId("42");
    verify(builder3).taskId("42");
    verify(filesLogStore).addTransaction(isA(TransactionLogEntry.class));
    verify(tracker).jobFinished(isA(CompactionJobFinishedEvent.class));
    verify(summary).getRecordsProcessed();
  }
}
