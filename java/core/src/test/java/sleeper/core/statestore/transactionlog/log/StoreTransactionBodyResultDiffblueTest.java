package sleeper.core.statestore.transactionlog.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StoreTransactionBodyResultDiffblueTest {
  /**
   * Test {@link StoreTransactionBodyResult#stored(String)}.
   * <p>
   * Method under test: {@link StoreTransactionBodyResult#stored(String)}
   */
  @Test
  @DisplayName("Test stored(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StoreTransactionBodyResult StoreTransactionBodyResult.stored(String)"})
  void testStored() {
    // Arrange and Act
    StoreTransactionBodyResult actualStoredResult = StoreTransactionBodyResult.stored("Not all who wander are lost");

    // Assert
    Optional<String> bodyKey = actualStoredResult.getBodyKey();
    assertEquals("Not all who wander are lost", bodyKey.get());
    assertFalse(actualStoredResult.getSerialisedTransaction().isPresent());
    assertTrue(bodyKey.isPresent());
  }

  /**
   * Test {@link StoreTransactionBodyResult#notStored()}.
   * <p>
   * Method under test: {@link StoreTransactionBodyResult#notStored()}
   */
  @Test
  @DisplayName("Test notStored()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StoreTransactionBodyResult StoreTransactionBodyResult.notStored()"})
  void testNotStored() {
    // Arrange and Act
    StoreTransactionBodyResult actualNotStoredResult = StoreTransactionBodyResult.notStored();

    // Assert
    Optional<String> bodyKey = actualNotStoredResult.getBodyKey();
    assertFalse(bodyKey.isPresent());
    assertSame(bodyKey, actualNotStoredResult.getSerialisedTransaction());
  }

  /**
   * Test {@link StoreTransactionBodyResult#notStored(String)} with {@code String}.
   * <p>
   * Method under test: {@link StoreTransactionBodyResult#notStored(String)}
   */
  @Test
  @DisplayName("Test notStored(String) with 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StoreTransactionBodyResult StoreTransactionBodyResult.notStored(String)"})
  void testNotStoredWithString() {
    // Arrange and Act
    StoreTransactionBodyResult actualNotStoredResult = StoreTransactionBodyResult.notStored("Serialised Transaction");

    // Assert
    Optional<String> serialisedTransaction = actualNotStoredResult.getSerialisedTransaction();
    assertEquals("Serialised Transaction", serialisedTransaction.get());
    assertFalse(actualNotStoredResult.getBodyKey().isPresent());
    assertTrue(serialisedTransaction.isPresent());
  }

  /**
   * Test {@link StoreTransactionBodyResult#getBodyKey()}.
   * <p>
   * Method under test: {@link StoreTransactionBodyResult#getBodyKey()}
   */
  @Test
  @DisplayName("Test getBodyKey()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional StoreTransactionBodyResult.getBodyKey()"})
  void testGetBodyKey() {
    // Arrange, Act and Assert
    assertFalse(StoreTransactionBodyResult.notStored().getBodyKey().isPresent());
  }

  /**
   * Test {@link StoreTransactionBodyResult#getSerialisedTransaction()}.
   * <p>
   * Method under test: {@link StoreTransactionBodyResult#getSerialisedTransaction()}
   */
  @Test
  @DisplayName("Test getSerialisedTransaction()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional StoreTransactionBodyResult.getSerialisedTransaction()"})
  void testGetSerialisedTransaction() {
    // Arrange, Act and Assert
    assertFalse(StoreTransactionBodyResult.notStored().getSerialisedTransaction().isPresent());
  }

  /**
   * Test {@link StoreTransactionBodyResult#equals(Object)}, and {@link StoreTransactionBodyResult#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StoreTransactionBodyResult#equals(Object)}
   *   <li>{@link StoreTransactionBodyResult#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StoreTransactionBodyResult.equals(Object)", "int StoreTransactionBodyResult.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    StoreTransactionBodyResult notStoredResult = StoreTransactionBodyResult.notStored();
    StoreTransactionBodyResult notStoredResult2 = StoreTransactionBodyResult.notStored();

    // Act and Assert
    assertEquals(notStoredResult, notStoredResult2);
    int expectedHashCodeResult = notStoredResult.hashCode();
    assertEquals(expectedHashCodeResult, notStoredResult2.hashCode());
  }

  /**
   * Test {@link StoreTransactionBodyResult#equals(Object)}, and {@link StoreTransactionBodyResult#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StoreTransactionBodyResult#equals(Object)}
   *   <li>{@link StoreTransactionBodyResult#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StoreTransactionBodyResult.equals(Object)", "int StoreTransactionBodyResult.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    StoreTransactionBodyResult notStoredResult = StoreTransactionBodyResult.notStored();

    // Act and Assert
    assertEquals(notStoredResult, notStoredResult);
    int expectedHashCodeResult = notStoredResult.hashCode();
    assertEquals(expectedHashCodeResult, notStoredResult.hashCode());
  }

  /**
   * Test {@link StoreTransactionBodyResult#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreTransactionBodyResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StoreTransactionBodyResult.equals(Object)", "int StoreTransactionBodyResult.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    StoreTransactionBodyResult notStoredResult = StoreTransactionBodyResult.notStored("Serialised Transaction");

    // Act and Assert
    assertNotEquals(notStoredResult, StoreTransactionBodyResult.notStored());
  }

  /**
   * Test {@link StoreTransactionBodyResult#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreTransactionBodyResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StoreTransactionBodyResult.equals(Object)", "int StoreTransactionBodyResult.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    StoreTransactionBodyResult storedResult = StoreTransactionBodyResult.stored("Not all who wander are lost");

    // Act and Assert
    assertNotEquals(storedResult, StoreTransactionBodyResult.notStored());
  }

  /**
   * Test {@link StoreTransactionBodyResult#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreTransactionBodyResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StoreTransactionBodyResult.equals(Object)", "int StoreTransactionBodyResult.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(StoreTransactionBodyResult.notStored(), null);
  }

  /**
   * Test {@link StoreTransactionBodyResult#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StoreTransactionBodyResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StoreTransactionBodyResult.equals(Object)", "int StoreTransactionBodyResult.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(StoreTransactionBodyResult.notStored(), "Different type to StoreTransactionBodyResult");
  }

  /**
   * Test {@link StoreTransactionBodyResult#toString()}.
   * <p>
   * Method under test: {@link StoreTransactionBodyResult#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StoreTransactionBodyResult.toString()"})
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("StoreTransactionBodyResult{bodyKey=null, serialisedTransaction=null}",
        StoreTransactionBodyResult.notStored().toString());
  }
}
