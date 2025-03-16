package sleeper.task.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class QueueMessageCountDiffblueTest {
  /**
   * Test {@link QueueMessageCount#approximateNumberVisibleAndNotVisible(int, int)}.
   * <p>
   * Method under test: {@link QueueMessageCount#approximateNumberVisibleAndNotVisible(int, int)}
   */
  @Test
  @DisplayName("Test approximateNumberVisibleAndNotVisible(int, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueueMessageCount QueueMessageCount.approximateNumberVisibleAndNotVisible(int, int)"})
  void testApproximateNumberVisibleAndNotVisible() {
    // Arrange and Act
    QueueMessageCount actualApproximateNumberVisibleAndNotVisibleResult = QueueMessageCount
        .approximateNumberVisibleAndNotVisible(1, 1);

    // Assert
    assertEquals(1, actualApproximateNumberVisibleAndNotVisibleResult.getApproximateNumberOfMessages());
    assertEquals(1, actualApproximateNumberVisibleAndNotVisibleResult.getApproximateNumberOfMessagesNotVisible());
  }

  /**
   * Test {@link QueueMessageCount#equals(Object)}, and {@link QueueMessageCount#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link QueueMessageCount#equals(Object)}
   *   <li>{@link QueueMessageCount#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueueMessageCount.equals(Object)", "int QueueMessageCount.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    QueueMessageCount approximateNumberVisibleAndNotVisibleResult = QueueMessageCount
        .approximateNumberVisibleAndNotVisible(1, 1);
    QueueMessageCount approximateNumberVisibleAndNotVisibleResult2 = QueueMessageCount
        .approximateNumberVisibleAndNotVisible(1, 1);

    // Act and Assert
    assertEquals(approximateNumberVisibleAndNotVisibleResult, approximateNumberVisibleAndNotVisibleResult2);
    int expectedHashCodeResult = approximateNumberVisibleAndNotVisibleResult.hashCode();
    assertEquals(expectedHashCodeResult, approximateNumberVisibleAndNotVisibleResult2.hashCode());
  }

  /**
   * Test {@link QueueMessageCount#equals(Object)}, and {@link QueueMessageCount#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link QueueMessageCount#equals(Object)}
   *   <li>{@link QueueMessageCount#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueueMessageCount.equals(Object)", "int QueueMessageCount.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    QueueMessageCount approximateNumberVisibleAndNotVisibleResult = QueueMessageCount
        .approximateNumberVisibleAndNotVisible(1, 1);

    // Act and Assert
    assertEquals(approximateNumberVisibleAndNotVisibleResult, approximateNumberVisibleAndNotVisibleResult);
    int expectedHashCodeResult = approximateNumberVisibleAndNotVisibleResult.hashCode();
    assertEquals(expectedHashCodeResult, approximateNumberVisibleAndNotVisibleResult.hashCode());
  }

  /**
   * Test {@link QueueMessageCount#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueueMessageCount#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueueMessageCount.equals(Object)", "int QueueMessageCount.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    QueueMessageCount approximateNumberVisibleAndNotVisibleResult = QueueMessageCount
        .approximateNumberVisibleAndNotVisible(0, 1);

    // Act and Assert
    assertNotEquals(approximateNumberVisibleAndNotVisibleResult,
        QueueMessageCount.approximateNumberVisibleAndNotVisible(1, 1));
  }

  /**
   * Test {@link QueueMessageCount#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueueMessageCount#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueueMessageCount.equals(Object)", "int QueueMessageCount.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    QueueMessageCount approximateNumberVisibleAndNotVisibleResult = QueueMessageCount
        .approximateNumberVisibleAndNotVisible(1, 0);

    // Act and Assert
    assertNotEquals(approximateNumberVisibleAndNotVisibleResult,
        QueueMessageCount.approximateNumberVisibleAndNotVisible(1, 1));
  }

  /**
   * Test {@link QueueMessageCount#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueueMessageCount#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueueMessageCount.equals(Object)", "int QueueMessageCount.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(QueueMessageCount.approximateNumberVisibleAndNotVisible(1, 1), null);
  }

  /**
   * Test {@link QueueMessageCount#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueueMessageCount#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueueMessageCount.equals(Object)", "int QueueMessageCount.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(QueueMessageCount.approximateNumberVisibleAndNotVisible(1, 1),
        "Different type to QueueMessageCount");
  }
}
