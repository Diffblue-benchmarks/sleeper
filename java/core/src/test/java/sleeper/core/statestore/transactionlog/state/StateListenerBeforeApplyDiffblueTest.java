package sleeper.core.statestore.transactionlog.state;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.transactionlog.log.TransactionLogEntry;
import sleeper.core.statestore.transactionlog.transaction.StateStoreTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.core.tracker.ingest.job.IngestJobTracker;

class StateListenerBeforeApplyDiffblueTest {
  /**
   * Test {@link StateListenerBeforeApply#updateTrackers(TableStatus, IngestJobTracker, CompactionJobTracker)}.
   * <p>
   * Method under test: {@link StateListenerBeforeApply#updateTrackers(TableStatus, IngestJobTracker, CompactionJobTracker)}
   */
  @Test
  @DisplayName("Test updateTrackers(TableStatus, IngestJobTracker, CompactionJobTracker)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateListenerBeforeApply StateListenerBeforeApply.updateTrackers(TableStatus, IngestJobTracker, CompactionJobTracker)"})
  void testUpdateTrackers() {
    // Arrange
    TableStatus sleeperTable = mock(TableStatus.class);
    when(sleeperTable.putOnline()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    sleeperTable.putOnline();

    // Act
    StateListenerBeforeApply actualUpdateTrackersResult = StateListenerBeforeApply.updateTrackers(sleeperTable,
        IngestJobTracker.NONE, CompactionJobTracker.NONE);
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    actualUpdateTrackersResult
        .beforeApply(new TransactionLogEntry(1L, updateTime, new AddFilesTransaction(new ArrayList<>())), null, "42");

    // Assert
    verify(sleeperTable).putOnline();
  }

  /**
   * Test {@link StateListenerBeforeApply#updateTrackers(TableStatus, IngestJobTracker, CompactionJobTracker)}.
   * <p>
   * Method under test: {@link StateListenerBeforeApply#updateTrackers(TableStatus, IngestJobTracker, CompactionJobTracker)}
   */
  @Test
  @DisplayName("Test updateTrackers(TableStatus, IngestJobTracker, CompactionJobTracker)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateListenerBeforeApply StateListenerBeforeApply.updateTrackers(TableStatus, IngestJobTracker, CompactionJobTracker)"})
  void testUpdateTrackers2() {
    // Arrange
    TableStatus sleeperTable = mock(TableStatus.class);
    when(sleeperTable.putOnline()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    sleeperTable.putOnline();

    // Act
    StateListenerBeforeApply actualUpdateTrackersResult = StateListenerBeforeApply.updateTrackers(sleeperTable,
        IngestJobTracker.NONE, CompactionJobTracker.NONE);
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TransactionLogEntry transactionLogEntry = new TransactionLogEntry(1L, updateTime,
        new AddFilesTransaction(new ArrayList<>()));

    actualUpdateTrackersResult.beforeApply(transactionLogEntry, new AddFilesTransaction(new ArrayList<>()), null);

    // Assert
    verify(sleeperTable).putOnline();
  }

  /**
   * Test {@link StateListenerBeforeApply#withFilesState(Consumer)}.
   * <ul>
   *   <li>When {@link Consumer} {@link Consumer#accept(Object)} does nothing.</li>
   *   <li>Then calls {@link Consumer#accept(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateListenerBeforeApply#withFilesState(Consumer)}
   */
  @Test
  @DisplayName("Test withFilesState(Consumer); when Consumer accept(Object) does nothing; then calls accept(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateListenerBeforeApply StateListenerBeforeApply.withFilesState(Consumer)"})
  void testWithFilesState_whenConsumerAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    Consumer<StateStoreFiles> run = mock(Consumer.class);
    doNothing().when(run).accept(Mockito.<StateStoreFiles>any());

    // Act
    StateListenerBeforeApply actualWithFilesStateResult = StateListenerBeforeApply.withFilesState(run);
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TransactionLogEntry transactionLogEntry = new TransactionLogEntry(1L, updateTime,
        new AddFilesTransaction(new ArrayList<>()));

    actualWithFilesStateResult.beforeApply(transactionLogEntry, new AddFilesTransaction(new ArrayList<>()), null);

    // Assert
    verify(run).accept(isNull());
  }

  /**
   * Test {@link StateListenerBeforeApply#byTransactionType(Class, StateListenerBeforeApplyByType)}.
   * <ul>
   *   <li>Then calls {@link StateListenerBeforeApplyByType#beforeApply(TransactionLogEntry, StateStoreTransaction, Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateListenerBeforeApply#byTransactionType(Class, StateListenerBeforeApplyByType)}
   */
  @Test
  @DisplayName("Test byTransactionType(Class, StateListenerBeforeApplyByType); then calls beforeApply(TransactionLogEntry, StateStoreTransaction, Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateListenerBeforeApply StateListenerBeforeApply.byTransactionType(Class, StateListenerBeforeApplyByType)"})
  void testByTransactionType_thenCallsBeforeApply() {
    // Arrange
    Class<StateStoreTransaction> forNameResult = StateStoreTransaction.class;
    StateListenerBeforeApplyByType<Object, StateStoreTransaction<Object>> listener = mock(
        StateListenerBeforeApplyByType.class);
    doNothing().when(listener)
        .beforeApply(Mockito.<TransactionLogEntry>any(), Mockito.<StateStoreTransaction<Object>>any(),
            Mockito.<Object>any());

    // Act
    StateListenerBeforeApply actualByTransactionTypeResult = StateListenerBeforeApply
        .byTransactionType((Class<StateStoreTransaction<Object>>) (Class) forNameResult, listener);
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TransactionLogEntry transactionLogEntry = new TransactionLogEntry(1L, updateTime,
        new AddFilesTransaction(new ArrayList<>()));

    actualByTransactionTypeResult.beforeApply(transactionLogEntry, new AddFilesTransaction(new ArrayList<>()), "42");

    // Assert
    verify(listener).beforeApply(isA(TransactionLogEntry.class), isA(StateStoreTransaction.class), isA(Object.class));
  }
}
