package sleeper.core.statestore.transactionlog.snapshot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.state.StateStoreFiles;
import sleeper.core.statestore.transactionlog.state.StateStorePartitions;

class TransactionLogSnapshotDiffblueTest {
  /**
   * Test {@link TransactionLogSnapshot#filesInitialState()}.
   * <p>
   * Method under test: {@link TransactionLogSnapshot#filesInitialState()}
   */
  @Test
  @DisplayName("Test filesInitialState()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogSnapshot TransactionLogSnapshot.filesInitialState()"})
  void testFilesInitialState() {
    // Arrange and Act
    TransactionLogSnapshot actualFilesInitialStateResult = TransactionLogSnapshot.filesInitialState();

    // Assert
    Object state = actualFilesInitialStateResult.getState();
    assertTrue(state instanceof StateStoreFiles);
    assertEquals(0L, actualFilesInitialStateResult.getTransactionNumber());
    assertTrue(((StateStoreFiles) state).isEmpty());
  }

  /**
   * Test {@link TransactionLogSnapshot#partitionsInitialState()}.
   * <p>
   * Method under test: {@link TransactionLogSnapshot#partitionsInitialState()}
   */
  @Test
  @DisplayName("Test partitionsInitialState()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogSnapshot TransactionLogSnapshot.partitionsInitialState()"})
  void testPartitionsInitialState() {
    // Arrange and Act
    TransactionLogSnapshot actualPartitionsInitialStateResult = TransactionLogSnapshot.partitionsInitialState();

    // Assert
    assertTrue(actualPartitionsInitialStateResult.getState() instanceof StateStorePartitions);
    assertEquals(0L, actualPartitionsInitialStateResult.getTransactionNumber());
  }
}
