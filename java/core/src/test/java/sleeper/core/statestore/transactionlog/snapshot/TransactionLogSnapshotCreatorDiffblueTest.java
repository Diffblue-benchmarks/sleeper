package sleeper.core.statestore.transactionlog.snapshot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.testutils.InMemoryTransactionBodyStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStore;
import sleeper.core.statestore.transactionlog.log.DuplicateTransactionNumberException;
import sleeper.core.statestore.transactionlog.log.TransactionBodyStore;
import sleeper.core.statestore.transactionlog.log.TransactionLogEntry;
import sleeper.core.statestore.transactionlog.log.TransactionLogStore;
import sleeper.core.statestore.transactionlog.state.StateStoreFiles;
import sleeper.core.statestore.transactionlog.transaction.StateStoreTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.table.TableStatus;

class TransactionLogSnapshotCreatorDiffblueTest {
  /**
   * Test {@link TransactionLogSnapshotCreator#createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus)}.
   * <p>
   * Method under test: {@link TransactionLogSnapshotCreator#createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus)}
   */
  @Test
  @DisplayName("Test createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional TransactionLogSnapshotCreator.createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus)"})
  void testCreateSnapshotIfChanged() {
    // Arrange
    TransactionLogSnapshot lastSnapshot = new TransactionLogSnapshot("State", 1L);

    InMemoryTransactionLogStore logStore = new InMemoryTransactionLogStore();
    InMemoryTransactionBodyStore transactionBodyStore = new InMemoryTransactionBodyStore();
    Class<StateStoreTransaction> forNameResult = StateStoreTransaction.class;

    // Act
    Optional<TransactionLogSnapshot> actualCreateSnapshotIfChangedResult = TransactionLogSnapshotCreator
        .createSnapshotIfChanged(lastSnapshot, logStore, transactionBodyStore,
            (Class<StateStoreTransaction<Object>>) (Class) forNameResult,
            TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    assertFalse(actualCreateSnapshotIfChangedResult.isPresent());
  }

  /**
   * Test {@link TransactionLogSnapshotCreator#createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus)}.
   * <ul>
   *   <li>Then {@link Optional#get()} State return {@link StateStoreFiles}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogSnapshotCreator#createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus)}
   */
  @Test
  @DisplayName("Test createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus); then get() State return StateStoreFiles")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional TransactionLogSnapshotCreator.createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus)"})
  void testCreateSnapshotIfChanged_thenGetStateReturnStateStoreFiles() throws DuplicateTransactionNumberException {
    // Arrange
    TransactionLogSnapshot lastSnapshot = TransactionLogSnapshot.filesInitialState();

    InMemoryTransactionLogStore logStore = new InMemoryTransactionLogStore();
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logStore.addTransaction(new TransactionLogEntry(1L, updateTime, new AddFilesTransaction(new ArrayList<>())));
    InMemoryTransactionBodyStore transactionBodyStore = new InMemoryTransactionBodyStore();
    Class<StateStoreTransaction> forNameResult = StateStoreTransaction.class;

    // Act
    Optional<TransactionLogSnapshot> actualCreateSnapshotIfChangedResult = TransactionLogSnapshotCreator
        .createSnapshotIfChanged(lastSnapshot, logStore, transactionBodyStore,
            (Class<StateStoreTransaction<Object>>) (Class) forNameResult,
            TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    TransactionLogSnapshot getResult = actualCreateSnapshotIfChangedResult.get();
    Object state = getResult.getState();
    assertTrue(state instanceof StateStoreFiles);
    assertEquals(1L, getResult.getTransactionNumber());
    assertTrue(actualCreateSnapshotIfChangedResult.isPresent());
    assertTrue(((StateStoreFiles) state).isEmpty());
  }

  /**
   * Test {@link TransactionLogSnapshotCreator#createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus)}.
   * <ul>
   *   <li>When filesInitialState.</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogSnapshotCreator#createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus)}
   */
  @Test
  @DisplayName("Test createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus); when filesInitialState; then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional TransactionLogSnapshotCreator.createSnapshotIfChanged(TransactionLogSnapshot, TransactionLogStore, TransactionBodyStore, Class, TableStatus)"})
  void testCreateSnapshotIfChanged_whenFilesInitialState_thenReturnNotPresent() {
    // Arrange
    TransactionLogSnapshot lastSnapshot = TransactionLogSnapshot.filesInitialState();
    InMemoryTransactionLogStore logStore = new InMemoryTransactionLogStore();
    InMemoryTransactionBodyStore transactionBodyStore = new InMemoryTransactionBodyStore();
    Class<StateStoreTransaction> forNameResult = StateStoreTransaction.class;

    // Act
    Optional<TransactionLogSnapshot> actualCreateSnapshotIfChangedResult = TransactionLogSnapshotCreator
        .createSnapshotIfChanged(lastSnapshot, logStore, transactionBodyStore,
            (Class<StateStoreTransaction<Object>>) (Class) forNameResult,
            TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    assertFalse(actualCreateSnapshotIfChangedResult.isPresent());
  }
}
