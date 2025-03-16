package sleeper.systemtest.dsl.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;

class PollWithRetriesDriverDiffblueTest {
  /**
   * Test {@link PollWithRetriesDriver#realWaits()}.
   * <p>
   * Method under test: {@link PollWithRetriesDriver#realWaits()}
   */
  @Test
  @DisplayName("Test realWaits()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PollWithRetriesDriver PollWithRetriesDriver.realWaits()"})
  void testRealWaits() {
    // Arrange and Act
    PollWithRetriesDriver actualRealWaitsResult = PollWithRetriesDriver.realWaits();
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Assert
    assertSame(buildResult, actualRealWaitsResult.poll(buildResult));
  }

  /**
   * Test {@link PollWithRetriesDriver#noWaits()}.
   * <p>
   * Method under test: {@link PollWithRetriesDriver#noWaits()}
   */
  @Test
  @DisplayName("Test noWaits()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PollWithRetriesDriver PollWithRetriesDriver.noWaits()"})
  void testNoWaits() {
    // Arrange and Act
    PollWithRetriesDriver actualNoWaitsResult = PollWithRetriesDriver.noWaits();
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Assert
    assertEquals(buildResult, actualNoWaitsResult.poll(buildResult));
  }
}
