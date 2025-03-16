package sleeper.core.statestore.transactionlog.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TransactionLogRangeDiffblueTest {
  /**
   * Test {@link TransactionLogRange#toUpdateLocalStateAt(long)}.
   * <p>
   * Method under test: {@link TransactionLogRange#toUpdateLocalStateAt(long)}
   */
  @Test
  @DisplayName("Test toUpdateLocalStateAt(long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogRange TransactionLogRange.toUpdateLocalStateAt(long)"})
  void testToUpdateLocalStateAt() {
    // Arrange and Act
    TransactionLogRange actualToUpdateLocalStateAtResult = TransactionLogRange.toUpdateLocalStateAt(1L);

    // Assert
    assertEquals(-1L, actualToUpdateLocalStateAtResult.endExclusive());
    assertEquals(2L, actualToUpdateLocalStateAtResult.startInclusive());
  }

  /**
   * Test {@link TransactionLogRange#toUpdateLocalStateToApply(long, long)}.
   * <p>
   * Method under test: {@link TransactionLogRange#toUpdateLocalStateToApply(long, long)}
   */
  @Test
  @DisplayName("Test toUpdateLocalStateToApply(long, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogRange TransactionLogRange.toUpdateLocalStateToApply(long, long)"})
  void testToUpdateLocalStateToApply() {
    // Arrange and Act
    TransactionLogRange actualToUpdateLocalStateToApplyResult = TransactionLogRange.toUpdateLocalStateToApply(1L, 1L);

    // Assert
    assertEquals(1L, actualToUpdateLocalStateToApplyResult.endExclusive());
    assertEquals(2L, actualToUpdateLocalStateToApplyResult.startInclusive());
  }

  /**
   * Test {@link TransactionLogRange#fromMinimum(long)}.
   * <p>
   * Method under test: {@link TransactionLogRange#fromMinimum(long)}
   */
  @Test
  @DisplayName("Test fromMinimum(long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogRange TransactionLogRange.fromMinimum(long)"})
  void testFromMinimum() {
    // Arrange and Act
    TransactionLogRange actualFromMinimumResult = TransactionLogRange.fromMinimum(1L);

    // Assert
    assertEquals(-1L, actualFromMinimumResult.endExclusive());
    assertEquals(1L, actualFromMinimumResult.startInclusive());
  }

  /**
   * Test {@link TransactionLogRange#isMaxTransactionBounded()}.
   * <ul>
   *   <li>Given fromMinimum one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogRange#isMaxTransactionBounded()}
   */
  @Test
  @DisplayName("Test isMaxTransactionBounded(); given fromMinimum one; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TransactionLogRange.isMaxTransactionBounded()"})
  void testIsMaxTransactionBounded_givenFromMinimumOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(TransactionLogRange.fromMinimum(1L).isMaxTransactionBounded());
  }

  /**
   * Test {@link TransactionLogRange#isMaxTransactionBounded()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogRange#isMaxTransactionBounded()}
   */
  @Test
  @DisplayName("Test isMaxTransactionBounded(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TransactionLogRange.isMaxTransactionBounded()"})
  void testIsMaxTransactionBounded_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(TransactionLogRange.toUpdateLocalStateToApply(-1L, 1L).isMaxTransactionBounded());
  }

  /**
   * Test {@link TransactionLogRange#withMinTransactionNumber(long)}.
   * <ul>
   *   <li>Given fromMinimum one.</li>
   *   <li>Then return {@link Optional#get()} is fromMinimum one.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogRange#withMinTransactionNumber(long)}
   */
  @Test
  @DisplayName("Test withMinTransactionNumber(long); given fromMinimum one; then return get() is fromMinimum one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional TransactionLogRange.withMinTransactionNumber(long)"})
  void testWithMinTransactionNumber_givenFromMinimumOne_thenReturnGetIsFromMinimumOne() {
    // Arrange
    TransactionLogRange fromMinimumResult = TransactionLogRange.fromMinimum(1L);

    // Act
    Optional<TransactionLogRange> actualWithMinTransactionNumberResult = fromMinimumResult.withMinTransactionNumber(1L);

    // Assert
    assertTrue(actualWithMinTransactionNumberResult.isPresent());
    assertEquals(fromMinimumResult, actualWithMinTransactionNumberResult.get());
  }

  /**
   * Test {@link TransactionLogRange#withMinTransactionNumber(long)}.
   * <ul>
   *   <li>Then return {@link Optional#get()} is fromMinimum {@link Long#MAX_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogRange#withMinTransactionNumber(long)}
   */
  @Test
  @DisplayName("Test withMinTransactionNumber(long); then return get() is fromMinimum MAX_VALUE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional TransactionLogRange.withMinTransactionNumber(long)"})
  void testWithMinTransactionNumber_thenReturnGetIsFromMinimumMax_value() {
    // Arrange
    TransactionLogRange fromMinimumResult = TransactionLogRange.fromMinimum(Long.MAX_VALUE);

    // Act
    Optional<TransactionLogRange> actualWithMinTransactionNumberResult = fromMinimumResult.withMinTransactionNumber(1L);

    // Assert
    assertTrue(actualWithMinTransactionNumberResult.isPresent());
    assertSame(fromMinimumResult, actualWithMinTransactionNumberResult.get());
  }

  /**
   * Test {@link TransactionLogRange#withMinTransactionNumber(long)}.
   * <ul>
   *   <li>Then return {@link Optional#get()} startInclusive is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogRange#withMinTransactionNumber(long)}
   */
  @Test
  @DisplayName("Test withMinTransactionNumber(long); then return get() startInclusive is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional TransactionLogRange.withMinTransactionNumber(long)"})
  void testWithMinTransactionNumber_thenReturnGetStartInclusiveIsOne() {
    // Arrange and Act
    Optional<TransactionLogRange> actualWithMinTransactionNumberResult = TransactionLogRange
        .toUpdateLocalStateToApply(-1L, Long.MAX_VALUE)
        .withMinTransactionNumber(1L);

    // Assert
    TransactionLogRange getResult = actualWithMinTransactionNumberResult.get();
    assertEquals(1L, getResult.startInclusive());
    assertTrue(actualWithMinTransactionNumberResult.isPresent());
    assertEquals(Long.MAX_VALUE, getResult.endExclusive());
  }

  /**
   * Test {@link TransactionLogRange#withMinTransactionNumber(long)}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogRange#withMinTransactionNumber(long)}
   */
  @Test
  @DisplayName("Test withMinTransactionNumber(long); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional TransactionLogRange.withMinTransactionNumber(long)"})
  void testWithMinTransactionNumber_thenReturnNotPresent() {
    // Arrange, Act and Assert
    assertFalse(TransactionLogRange.toUpdateLocalStateToApply(-1L, 1L).withMinTransactionNumber(1L).isPresent());
  }
}
