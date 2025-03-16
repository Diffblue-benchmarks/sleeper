package sleeper.core.statestore.transactionlog.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.ClearFilesTransaction;

class StateStoreTransactionDiffblueTest {
  /**
   * Test {@link StateStoreTransaction#isEmpty()}.
   * <ul>
   *   <li>Given {@link AddFilesTransaction#AddFilesTransaction(List)} with files is {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); given AddFilesTransaction(List) with files is ArrayList(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreTransaction.isEmpty()"})
  void testIsEmpty_givenAddFilesTransactionWithFilesIsArrayList_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new AddFilesTransaction(new ArrayList<>())).isEmpty());
  }

  /**
   * Test {@link StateStoreTransaction#isEmpty()}.
   * <ul>
   *   <li>Given {@link ClearFilesTransaction} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); given ClearFilesTransaction (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreTransaction.isEmpty()"})
  void testIsEmpty_givenClearFilesTransaction_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ClearFilesTransaction()).isEmpty());
  }
}
