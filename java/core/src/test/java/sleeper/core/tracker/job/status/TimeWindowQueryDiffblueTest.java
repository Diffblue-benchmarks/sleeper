package sleeper.core.tracker.job.status;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TimeWindowQueryDiffblueTest {
  /**
   * Test {@link TimeWindowQuery#isUnfinishedJobInWindow(Instant)}.
   * <p>
   * Method under test: {@link TimeWindowQuery#isUnfinishedJobInWindow(Instant)}
   */
  @Test
  @DisplayName("Test isUnfinishedJobInWindow(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TimeWindowQuery.isUnfinishedJobInWindow(Instant)"})
  void testIsUnfinishedJobInWindow() {
    // Arrange
    Instant windowStartTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TimeWindowQuery timeWindowQuery = new TimeWindowQuery(windowStartTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertFalse(timeWindowQuery
        .isUnfinishedJobInWindow(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link TimeWindowQuery#isUnfinishedJobInWindow(Instant)}.
   * <p>
   * Method under test: {@link TimeWindowQuery#isUnfinishedJobInWindow(Instant)}
   */
  @Test
  @DisplayName("Test isUnfinishedJobInWindow(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TimeWindowQuery.isUnfinishedJobInWindow(Instant)"})
  void testIsUnfinishedJobInWindow2() {
    // Arrange
    Instant windowStartTime = LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TimeWindowQuery timeWindowQuery = new TimeWindowQuery(windowStartTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertFalse(timeWindowQuery
        .isUnfinishedJobInWindow(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link TimeWindowQuery#isFinishedJobInWindow(Instant, Instant)}.
   * <p>
   * Method under test: {@link TimeWindowQuery#isFinishedJobInWindow(Instant, Instant)}
   */
  @Test
  @DisplayName("Test isFinishedJobInWindow(Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TimeWindowQuery.isFinishedJobInWindow(Instant, Instant)"})
  void testIsFinishedJobInWindow() {
    // Arrange
    Instant windowStartTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TimeWindowQuery timeWindowQuery = new TimeWindowQuery(windowStartTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertFalse(timeWindowQuery.isFinishedJobInWindow(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link TimeWindowQuery#isFinishedJobInWindow(Instant, Instant)}.
   * <p>
   * Method under test: {@link TimeWindowQuery#isFinishedJobInWindow(Instant, Instant)}
   */
  @Test
  @DisplayName("Test isFinishedJobInWindow(Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TimeWindowQuery.isFinishedJobInWindow(Instant, Instant)"})
  void testIsFinishedJobInWindow2() {
    // Arrange
    Instant windowStartTime = LocalDate.ofYearDay(1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TimeWindowQuery timeWindowQuery = new TimeWindowQuery(windowStartTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertFalse(timeWindowQuery.isFinishedJobInWindow(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }
}
