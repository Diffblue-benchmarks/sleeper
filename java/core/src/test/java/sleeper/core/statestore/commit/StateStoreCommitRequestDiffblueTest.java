package sleeper.core.statestore.commit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.transaction.StateStoreTransaction;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.ClearFilesTransaction;

class StateStoreCommitRequestDiffblueTest {
  /**
   * Test {@link StateStoreCommitRequest#create(String, String, TransactionType)} with {@code tableId}, {@code bodyKey}, {@code transactionType}.
   * <p>
   * Method under test: {@link StateStoreCommitRequest#create(String, String, TransactionType)}
   */
  @Test
  @DisplayName("Test create(String, String, TransactionType) with 'tableId', 'bodyKey', 'transactionType'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitRequest.create(String, String, TransactionType)"})
  void testCreateWithTableIdBodyKeyTransactionType() {
    // Arrange and Act
    StateStoreCommitRequest actualCreateResult = StateStoreCommitRequest.create("42", "Not all who wander are lost",
        TransactionType.ADD_FILES);

    // Assert
    assertEquals("42", actualCreateResult.getTableId());
    assertEquals("Not all who wander are lost", actualCreateResult.getBodyKey());
    assertEquals(TransactionType.ADD_FILES, actualCreateResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitRequest#create(String, StateStoreTransaction)} with {@code tableId}, {@code transaction}.
   * <ul>
   *   <li>Then return TransactionType is {@code ADD_FILES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequest#create(String, StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test create(String, StateStoreTransaction) with 'tableId', 'transaction'; then return TransactionType is 'ADD_FILES'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitRequest.create(String, StateStoreTransaction)"})
  void testCreateWithTableIdTransaction_thenReturnTransactionTypeIsAddFiles() {
    // Arrange and Act
    StateStoreCommitRequest actualCreateResult = StateStoreCommitRequest.create("42",
        new AddFilesTransaction(new ArrayList<>()));

    // Assert
    assertEquals("42", actualCreateResult.getTableId());
    assertNull(actualCreateResult.getBodyKey());
    assertEquals(TransactionType.ADD_FILES, actualCreateResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitRequest#create(String, StateStoreTransaction)} with {@code tableId}, {@code transaction}.
   * <ul>
   *   <li>Then return TransactionType is {@code CLEAR_FILES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequest#create(String, StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test create(String, StateStoreTransaction) with 'tableId', 'transaction'; then return TransactionType is 'CLEAR_FILES'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitRequest.create(String, StateStoreTransaction)"})
  void testCreateWithTableIdTransaction_thenReturnTransactionTypeIsClearFiles() {
    // Arrange and Act
    StateStoreCommitRequest actualCreateResult = StateStoreCommitRequest.create("42", new ClearFilesTransaction());

    // Assert
    assertEquals("42", actualCreateResult.getTableId());
    assertNull(actualCreateResult.getBodyKey());
    assertEquals(TransactionType.CLEAR_FILES, actualCreateResult.getTransactionType());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitRequest#toString()}
   *   <li>{@link StateStoreCommitRequest#getBodyKey()}
   *   <li>{@link StateStoreCommitRequest#getTableId()}
   *   <li>{@link StateStoreCommitRequest#getTransactionType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequest.getBodyKey()", "String StateStoreCommitRequest.getTableId()",
      "TransactionType StateStoreCommitRequest.getTransactionType()", "String StateStoreCommitRequest.toString()"})
  void testGettersAndSetters() {
    // Arrange
    StateStoreCommitRequest createResult = StateStoreCommitRequest.create("42", "Not all who wander are lost",
        TransactionType.ADD_FILES);

    // Act
    String actualToStringResult = createResult.toString();
    String actualBodyKey = createResult.getBodyKey();
    String actualTableId = createResult.getTableId();

    // Assert
    assertEquals("42", actualTableId);
    assertEquals("Not all who wander are lost", actualBodyKey);
    assertEquals("StateStoreCommitRequest{tableId=42, transactionType=ADD_FILES, bodyKey=Not all who wander are lost,"
        + " transaction=null}", actualToStringResult);
    assertEquals(TransactionType.ADD_FILES, createResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitRequest#getTransactionIfHeld()}.
   * <p>
   * Method under test: {@link StateStoreCommitRequest#getTransactionIfHeld()}
   */
  @Test
  @DisplayName("Test getTransactionIfHeld()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional StateStoreCommitRequest.getTransactionIfHeld()"})
  void testGetTransactionIfHeld() {
    // Arrange, Act and Assert
    assertFalse(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES)
        .getTransactionIfHeld()
        .isPresent());
  }

  /**
   * Test {@link StateStoreCommitRequest#equals(Object)}, and {@link StateStoreCommitRequest#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitRequest#equals(Object)}
   *   <li>{@link StateStoreCommitRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitRequest.equals(Object)", "int StateStoreCommitRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    StateStoreCommitRequest createResult = StateStoreCommitRequest.create("42", "Not all who wander are lost",
        TransactionType.ADD_FILES);
    StateStoreCommitRequest createResult2 = StateStoreCommitRequest.create("42", "Not all who wander are lost",
        TransactionType.ADD_FILES);

    // Act and Assert
    assertEquals(createResult, createResult2);
    int expectedHashCodeResult = createResult.hashCode();
    assertEquals(expectedHashCodeResult, createResult2.hashCode());
  }

  /**
   * Test {@link StateStoreCommitRequest#equals(Object)}, and {@link StateStoreCommitRequest#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitRequest#equals(Object)}
   *   <li>{@link StateStoreCommitRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitRequest.equals(Object)", "int StateStoreCommitRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    StateStoreCommitRequest createResult = StateStoreCommitRequest.create("42", "Not all who wander are lost",
        TransactionType.ADD_FILES);

    // Act and Assert
    assertEquals(createResult, createResult);
    int expectedHashCodeResult = createResult.hashCode();
    assertEquals(expectedHashCodeResult, createResult.hashCode());
  }

  /**
   * Test {@link StateStoreCommitRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitRequest.equals(Object)", "int StateStoreCommitRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    StateStoreCommitRequest createResult = StateStoreCommitRequest.create("Table Id", "Not all who wander are lost",
        TransactionType.ADD_FILES);

    // Act and Assert
    assertNotEquals(createResult,
        StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES));
  }

  /**
   * Test {@link StateStoreCommitRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitRequest.equals(Object)", "int StateStoreCommitRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    StateStoreCommitRequest createResult = StateStoreCommitRequest.create("42", "Body Key", TransactionType.ADD_FILES);

    // Act and Assert
    assertNotEquals(createResult,
        StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES));
  }

  /**
   * Test {@link StateStoreCommitRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitRequest.equals(Object)", "int StateStoreCommitRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    StateStoreCommitRequest createResult = StateStoreCommitRequest.create("42", "Not all who wander are lost",
        TransactionType.ASSIGN_JOB_IDS);

    // Act and Assert
    assertNotEquals(createResult,
        StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES));
  }

  /**
   * Test {@link StateStoreCommitRequest#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitRequest.equals(Object)", "int StateStoreCommitRequest.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES),
        null);
  }

  /**
   * Test {@link StateStoreCommitRequest#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitRequest.equals(Object)", "int StateStoreCommitRequest.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES),
        "Different type to StateStoreCommitRequest");
  }
}
