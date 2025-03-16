package sleeper.core.statestore.transactionlog.snapshot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.log.TransactionLogRange;

class TransactionLogSnapshotLoaderDiffblueTest {
  /**
   * Test {@link TransactionLogSnapshotLoader#neverLoad()}.
   * <p>
   * Method under test: {@link TransactionLogSnapshotLoader#neverLoad()}
   */
  @Test
  @DisplayName("Test neverLoad()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogSnapshotLoader TransactionLogSnapshotLoader.neverLoad()"})
  void testNeverLoad() {
    // Arrange and Act
    TransactionLogSnapshotLoader actualNeverLoadResult = TransactionLogSnapshotLoader.neverLoad();

    // Assert
    assertFalse(actualNeverLoadResult.loadLatestSnapshotInRange(TransactionLogRange.fromMinimum(1L)).isPresent());
  }
}
