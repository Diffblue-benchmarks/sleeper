package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RateLimitUtilsDiffblueTest {
  /**
   * Test {@link RateLimitUtils#sleepForSustainedRatePerSecond(double, ThreadSleep)} with {@code ratePerSecond}, {@code threadSleep}.
   * <p>
   * Method under test: {@link RateLimitUtils#sleepForSustainedRatePerSecond(double, ThreadSleep)}
   */
  @Test
  @DisplayName("Test sleepForSustainedRatePerSecond(double, ThreadSleep) with 'ratePerSecond', 'threadSleep'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RateLimitUtils.sleepForSustainedRatePerSecond(double, ThreadSleep)"})
  void testSleepForSustainedRatePerSecondWithRatePerSecondThreadSleep() throws InterruptedException {
    // Arrange
    ThreadSleep threadSleep = mock(ThreadSleep.class);
    doNothing().when(threadSleep).waitForMillis(anyLong());

    // Act
    RateLimitUtils.sleepForSustainedRatePerSecond(10.0d, threadSleep);

    // Assert
    verify(threadSleep).waitForMillis(eq(100L));
  }

  /**
   * Test {@link RateLimitUtils#sleepForSustainedRatePerSecond(double, ThreadSleep)} with {@code ratePerSecond}, {@code threadSleep}.
   * <p>
   * Method under test: {@link RateLimitUtils#sleepForSustainedRatePerSecond(double, ThreadSleep)}
   */
  @Test
  @DisplayName("Test sleepForSustainedRatePerSecond(double, ThreadSleep) with 'ratePerSecond', 'threadSleep'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RateLimitUtils.sleepForSustainedRatePerSecond(double, ThreadSleep)"})
  void testSleepForSustainedRatePerSecondWithRatePerSecondThreadSleep2() throws InterruptedException {
    // Arrange
    ThreadSleep threadSleep = mock(ThreadSleep.class);
    doThrow(new InterruptedException("Sleeping for {} ")).when(threadSleep).waitForMillis(anyLong());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> RateLimitUtils.sleepForSustainedRatePerSecond(10.0d, threadSleep));
    verify(threadSleep).waitForMillis(eq(100L));
  }

  /**
   * Test {@link RateLimitUtils#sleepForSustainedRatePerSecond(double, ThreadSleep)} with {@code ratePerSecond}, {@code threadSleep}.
   * <p>
   * Method under test: {@link RateLimitUtils#sleepForSustainedRatePerSecond(double, ThreadSleep)}
   */
  @Test
  @DisplayName("Test sleepForSustainedRatePerSecond(double, ThreadSleep) with 'ratePerSecond', 'threadSleep'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RateLimitUtils.sleepForSustainedRatePerSecond(double, ThreadSleep)"})
  void testSleepForSustainedRatePerSecondWithRatePerSecondThreadSleep3() throws InterruptedException {
    // Arrange
    ThreadSleep threadSleep = mock(ThreadSleep.class);
    doThrow(new RuntimeException("Sleeping for {} ")).when(threadSleep).waitForMillis(anyLong());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> RateLimitUtils.sleepForSustainedRatePerSecond(10.0d, threadSleep));
    verify(threadSleep).waitForMillis(eq(100L));
  }

  /**
   * Test {@link RateLimitUtils#calculateMillisSleepForSustainedRatePerSecond(double)}.
   * <p>
   * Method under test: {@link RateLimitUtils#calculateMillisSleepForSustainedRatePerSecond(double)}
   */
  @Test
  @DisplayName("Test calculateMillisSleepForSustainedRatePerSecond(double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long RateLimitUtils.calculateMillisSleepForSustainedRatePerSecond(double)"})
  void testCalculateMillisSleepForSustainedRatePerSecond() {
    // Arrange, Act and Assert
    assertEquals(100L, RateLimitUtils.calculateMillisSleepForSustainedRatePerSecond(10.0d));
  }

  /**
   * Test {@link RateLimitUtils#calculateSleepForSustainedRatePerSecond(double)}.
   * <p>
   * Method under test: {@link RateLimitUtils#calculateSleepForSustainedRatePerSecond(double)}
   */
  @Test
  @DisplayName("Test calculateSleepForSustainedRatePerSecond(double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.time.Duration RateLimitUtils.calculateSleepForSustainedRatePerSecond(double)"})
  void testCalculateSleepForSustainedRatePerSecond() {
    // Arrange, Act and Assert
    assertEquals(100000000L, RateLimitUtils.calculateSleepForSustainedRatePerSecond(10.0d).toNanos());
  }
}
