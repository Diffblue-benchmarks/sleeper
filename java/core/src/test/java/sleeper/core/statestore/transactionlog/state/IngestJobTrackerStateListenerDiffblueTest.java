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
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.ingest.job.IngestJobTracker;

class IngestJobTrackerStateListenerDiffblueTest {
  /**
   * Test {@link IngestJobTrackerStateListener#beforeApply(TransactionLogEntry, AddFilesTransaction, StateStoreFiles)} with {@code TransactionLogEntry}, {@code AddFilesTransaction}, {@code StateStoreFiles}.
   * <p>
   * Method under test: {@link IngestJobTrackerStateListener#beforeApply(TransactionLogEntry, AddFilesTransaction, StateStoreFiles)}
   */
  @Test
  @DisplayName("Test beforeApply(TransactionLogEntry, AddFilesTransaction, StateStoreFiles) with 'TransactionLogEntry', 'AddFilesTransaction', 'StateStoreFiles'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void IngestJobTrackerStateListener.beforeApply(TransactionLogEntry, AddFilesTransaction, StateStoreFiles)"})
  void testBeforeApplyWithTransactionLogEntryAddFilesTransactionStateStoreFiles() {
    // Arrange
    IngestJobTrackerStateListener ingestJobTrackerStateListener = new IngestJobTrackerStateListener(
        TableStatus.uniqueIdAndName("42", "Table Name", true), IngestJobTracker.NONE);
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TransactionLogEntry entry = new TransactionLogEntry(1L, updateTime, new AddFilesTransaction(new ArrayList<>()));

    AddFilesTransaction transaction = mock(AddFilesTransaction.class);
    doNothing().when(transaction)
        .reportJobCommit(Mockito.<IngestJobTracker>any(), Mockito.<TableStatus>any(), Mockito.<StateStoreFiles>any());

    // Act
    ingestJobTrackerStateListener.beforeApply(entry, transaction, new StateStoreFiles());

    // Assert
    verify(transaction).reportJobCommit(isA(IngestJobTracker.class), isA(TableStatus.class),
        isA(StateStoreFiles.class));
  }
}
