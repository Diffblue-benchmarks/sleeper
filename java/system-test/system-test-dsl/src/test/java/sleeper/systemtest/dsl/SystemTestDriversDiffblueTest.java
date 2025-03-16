package sleeper.systemtest.dsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;
import sleeper.systemtest.dsl.testutil.InMemorySystemTestDrivers;
import sleeper.systemtest.dsl.util.PollWithRetriesDriver;

class SystemTestDriversDiffblueTest {
  /**
   * Test {@link SystemTestDrivers#pollWithRetries()}.
   * <p>
   * Method under test: {@link SystemTestDrivers#pollWithRetries()}
   */
  @Test
  @DisplayName("Test pollWithRetries()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PollWithRetriesDriver SystemTestDrivers.pollWithRetries()"})
  void testPollWithRetries() {
    // Arrange and Act
    PollWithRetriesDriver actualPollWithRetriesResult = (new InMemorySystemTestDrivers()).pollWithRetries();
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Assert
    assertEquals(buildResult, actualPollWithRetriesResult.poll(buildResult));
  }
}
