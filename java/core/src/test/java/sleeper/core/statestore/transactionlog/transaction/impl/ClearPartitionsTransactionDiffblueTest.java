package sleeper.core.statestore.transactionlog.transaction.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ClearPartitionsTransactionDiffblueTest {
  /**
   * Test {@link ClearPartitionsTransaction#create()}.
   * <p>
   * Method under test: {@link ClearPartitionsTransaction#create()}
   */
  @Test
  @DisplayName("Test create()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.statestore.transactionlog.transaction.impl.InitialisePartitionsTransaction ClearPartitionsTransaction.create()"})
  void testCreate() {
    // Arrange, Act and Assert
    assertFalse(ClearPartitionsTransaction.create().isEmpty());
  }
}
