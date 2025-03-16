package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.util.ExponentialBackoffWithJitter.WaitRange;

class ExponentialBackoffWithJitterDiffblueTest {
  /**
   * Test {@link ExponentialBackoffWithJitter#waitBeforeAttempt(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExponentialBackoffWithJitter#waitBeforeAttempt(int)}
   */
  @Test
  @DisplayName("Test waitBeforeAttempt(int); when one; then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long ExponentialBackoffWithJitter.waitBeforeAttempt(int)"})
  void testWaitBeforeAttempt_whenOne_thenReturnZero() throws InterruptedException {
    // Arrange, Act and Assert
    assertEquals(0L,
        (new ExponentialBackoffWithJitter(WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d))).waitBeforeAttempt(1));
  }

  /**
   * Test {@link ExponentialBackoffWithJitter#waitBeforeAttempt(int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExponentialBackoffWithJitter#waitBeforeAttempt(int)}
   */
  @Test
  @DisplayName("Test waitBeforeAttempt(int); when zero; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long ExponentialBackoffWithJitter.waitBeforeAttempt(int)"})
  void testWaitBeforeAttempt_whenZero_thenThrowIllegalArgumentException() throws InterruptedException {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new ExponentialBackoffWithJitter(WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d)))
            .waitBeforeAttempt(0));
  }

  /**
   * Test WaitRange {@link WaitRange#firstAndMaxWaitCeilingSecs(double, double)}.
   * <p>
   * Method under test: {@link WaitRange#firstAndMaxWaitCeilingSecs(double, double)}
   */
  @Test
  @DisplayName("Test WaitRange firstAndMaxWaitCeilingSecs(double, double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"WaitRange WaitRange.firstAndMaxWaitCeilingSecs(double, double)"})
  void testWaitRangeFirstAndMaxWaitCeilingSecs() {
    // Arrange and Act
    WaitRange actualFirstAndMaxWaitCeilingSecsResult = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    // Assert
    assertEquals(10000000000L, actualFirstAndMaxWaitCeilingSecsResult.getFirstWaitCeiling().toNanos());
    assertEquals(10000000000L, actualFirstAndMaxWaitCeilingSecsResult.getMaxWaitCeiling().toNanos());
  }

  /**
   * Test WaitRange {@link WaitRange#getFirstWaitCeiling()}.
   * <p>
   * Method under test: {@link WaitRange#getFirstWaitCeiling()}
   */
  @Test
  @DisplayName("Test WaitRange getFirstWaitCeiling()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.time.Duration WaitRange.getFirstWaitCeiling()"})
  void testWaitRangeGetFirstWaitCeiling() {
    // Arrange, Act and Assert
    assertEquals(10000000000L, WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d).getFirstWaitCeiling().toNanos());
  }

  /**
   * Test WaitRange {@link WaitRange#getMaxWaitCeiling()}.
   * <p>
   * Method under test: {@link WaitRange#getMaxWaitCeiling()}
   */
  @Test
  @DisplayName("Test WaitRange getMaxWaitCeiling()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.time.Duration WaitRange.getMaxWaitCeiling()"})
  void testWaitRangeGetMaxWaitCeiling() {
    // Arrange, Act and Assert
    assertEquals(10000000000L, WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d).getMaxWaitCeiling().toNanos());
  }
}
