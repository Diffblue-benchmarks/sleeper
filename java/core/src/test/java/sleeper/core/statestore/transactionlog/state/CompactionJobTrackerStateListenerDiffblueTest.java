package sleeper.core.statestore.transactionlog.state;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.transactionlog.log.TransactionLogEntry;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.ReplaceFileReferencesTransaction;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;

class CompactionJobTrackerStateListenerDiffblueTest {
  /**
   * Test {@link CompactionJobTrackerStateListener#beforeApply(TransactionLogEntry, ReplaceFileReferencesTransaction, StateStoreFiles)} with {@code TransactionLogEntry}, {@code ReplaceFileReferencesTransaction}, {@code StateStoreFiles}.
   * <p>
   * Method under test: {@link CompactionJobTrackerStateListener#beforeApply(TransactionLogEntry, ReplaceFileReferencesTransaction, StateStoreFiles)}
   */
  @Test
  @DisplayName("Test beforeApply(TransactionLogEntry, ReplaceFileReferencesTransaction, StateStoreFiles) with 'TransactionLogEntry', 'ReplaceFileReferencesTransaction', 'StateStoreFiles'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void CompactionJobTrackerStateListener.beforeApply(TransactionLogEntry, ReplaceFileReferencesTransaction, StateStoreFiles)"})
  void testBeforeApplyWithTransactionLogEntryReplaceFileReferencesTransactionStateStoreFiles() {
    // Arrange
    CompactionJobTrackerStateListener compactionJobTrackerStateListener = new CompactionJobTrackerStateListener(
        TableStatus.uniqueIdAndName("42", "Table Name", true), CompactionJobTracker.NONE);
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TransactionLogEntry entry = new TransactionLogEntry(1L, updateTime, new AddFilesTransaction(new ArrayList<>()));

    ReplaceFileReferencesTransaction transaction = mock(ReplaceFileReferencesTransaction.class);
    doNothing().when(transaction)
        .reportJobCommits(Mockito.<CompactionJobTracker>any(), Mockito.<TableStatus>any(),
            Mockito.<StateStoreFiles>any(), Mockito.<Instant>any());

    // Act
    compactionJobTrackerStateListener.beforeApply(entry, transaction, new StateStoreFiles());

    // Assert
    verify(transaction).reportJobCommits(isA(CompactionJobTracker.class), isA(TableStatus.class),
        isA(StateStoreFiles.class), isA(Instant.class));
  }
}
