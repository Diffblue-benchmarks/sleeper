package sleeper.compaction.core.job.creation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.AssignJobIdRequest;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.commit.StateStoreCommitRequestSender;
import sleeper.core.statestore.testutils.InMemoryTransactionBodyStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStore;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore.Builder;
import sleeper.core.statestore.transactionlog.snapshot.TransactionLogSnapshotLoader;
import sleeper.core.table.TableStatus;
import sleeper.core.util.ExponentialBackoffWithJitter;
import sleeper.core.util.ExponentialBackoffWithJitter.WaitRange;

class AssignJobIdToFilesDiffblueTest {
  /**
   * Test {@link AssignJobIdToFiles#synchronous(StateStore)}.
   *
   * <ul>
   *   <li>Then calls {@link AssignJobIdRequest#getFilenames()}.
   * </ul>
   *
   * <p>Method under test: {@link AssignJobIdToFiles#synchronous(StateStore)}
   */
  @Test
  @DisplayName("Test synchronous(StateStore); then calls getFilenames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AssignJobIdToFiles AssignJobIdToFiles.synchronous(StateStore)"})
  void testSynchronous_thenCallsGetFilenames() {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder minTransactionsAheadToLoadSnapshotResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class))
            .filesStateUpdateClock(mock(Supplier.class))
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsStateUpdateClockResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class))
            .partitionsStateUpdateClock(mock(Supplier.class));
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    TransactionLogStateStore stateStore =
        timeBetweenTransactionChecksResult
            .transactionBodyStore(new InMemoryTransactionBodyStore())
            .updateLogBeforeAddTransaction(true)
            .build();

    // Act
    AssignJobIdToFiles actualSynchronousResult = AssignJobIdToFiles.synchronous(stateStore);
    AssignJobIdRequest assignJobIdRequest = mock(AssignJobIdRequest.class);
    when(assignJobIdRequest.getFilenames()).thenReturn(new ArrayList<>());
    AssignJobIdRequest assignJobIdRequest2 = mock(AssignJobIdRequest.class);
    when(assignJobIdRequest2.getFilenames()).thenReturn(new ArrayList<>());
    ArrayList<AssignJobIdRequest> assignJobIdRequestList = new ArrayList<>();
    assignJobIdRequestList.add(assignJobIdRequest2);
    assignJobIdRequestList.add(assignJobIdRequest);
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);
    actualSynchronousResult.assignJobIds(assignJobIdRequestList, uniqueIdAndNameResult);

    // Assert
    verify(assignJobIdRequest2).getFilenames();
    verify(assignJobIdRequest).getFilenames();
  }

  /**
   * Test {@link AssignJobIdToFiles#byQueue(StateStoreCommitRequestSender)}.
   *
   * <p>Method under test: {@link AssignJobIdToFiles#byQueue(StateStoreCommitRequestSender)}
   */
  @Test
  @DisplayName("Test byQueue(StateStoreCommitRequestSender)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AssignJobIdToFiles AssignJobIdToFiles.byQueue(StateStoreCommitRequestSender)"
  })
  void testByQueue() {
    // Arrange
    StateStoreCommitRequestSender queueSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(queueSender).send(Mockito.<StateStoreCommitRequest>any());

    // Act
    AssignJobIdToFiles actualByQueueResult = AssignJobIdToFiles.byQueue(queueSender);
    ArrayList<AssignJobIdRequest> assignJobIdRequestList = new ArrayList<>();
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);
    actualByQueueResult.assignJobIds(assignJobIdRequestList, uniqueIdAndNameResult);

    // Assert that nothing has changed
    verify(queueSender).send(isA(StateStoreCommitRequest.class));
    assertEquals("42", uniqueIdAndNameResult.getTableUniqueId());
    assertEquals("Table Name", uniqueIdAndNameResult.getTableName());
    assertTrue(assignJobIdRequestList.isEmpty());
    assertTrue(uniqueIdAndNameResult.isOnline());
  }
}
