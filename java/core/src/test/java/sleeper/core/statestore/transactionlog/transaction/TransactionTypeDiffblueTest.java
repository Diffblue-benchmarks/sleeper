package sleeper.core.statestore.transactionlog.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.ClearFilesTransaction;

class TransactionTypeDiffblueTest {
  /**
   * Test {@link TransactionType#getType(StateStoreTransaction)} with {@code StateStoreTransaction}.
   * <ul>
   *   <li>Then return {@code ADD_FILES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionType#getType(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test getType(StateStoreTransaction) with 'StateStoreTransaction'; then return 'ADD_FILES'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionType TransactionType.getType(StateStoreTransaction)"})
  void testGetTypeWithStateStoreTransaction_thenReturnAddFiles() {
    // Arrange, Act and Assert
    assertEquals(TransactionType.ADD_FILES, TransactionType.getType(new AddFilesTransaction(new ArrayList<>())));
  }

  /**
   * Test {@link TransactionType#getType(StateStoreTransaction)} with {@code StateStoreTransaction}.
   * <ul>
   *   <li>Then return {@code CLEAR_FILES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionType#getType(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test getType(StateStoreTransaction) with 'StateStoreTransaction'; then return 'CLEAR_FILES'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionType TransactionType.getType(StateStoreTransaction)"})
  void testGetTypeWithStateStoreTransaction_thenReturnClearFiles() {
    // Arrange, Act and Assert
    assertEquals(TransactionType.CLEAR_FILES, TransactionType.getType(new ClearFilesTransaction()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TransactionType#getType()}
   *   <li>{@link TransactionType#isFileTransaction()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Class TransactionType.getType()", "boolean TransactionType.isFileTransaction()"})
  void testGettersAndSetters() {
    // Arrange
    TransactionType valueOfResult = TransactionType.valueOf("ADD_FILES");

    // Act
    Class<? extends StateStoreTransaction<?>> actualType = valueOfResult.getType();

    // Assert
    assertTrue(valueOfResult.isFileTransaction());
    Class<AddFilesTransaction> expectedType = AddFilesTransaction.class;
    assertEquals(expectedType, actualType);
  }
}
