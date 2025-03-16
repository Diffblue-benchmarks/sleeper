package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;

class DurationStatisticsDiffblueTest {
  /**
   * Test {@link DurationStatistics#fromIfAny(Stream)}.
   * <ul>
   *   <li>Given {@link TransactionLogStateStore#DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS}.</li>
   *   <li>Then return Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link DurationStatistics#fromIfAny(Stream)}
   */
  @Test
  @DisplayName("Test fromIfAny(Stream); given DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS; then return Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DurationStatistics.fromIfAny(Stream)"})
  void testFromIfAny_givenDefault_time_between_snapshot_checks_thenReturnPresent() {
    // Arrange
    ArrayList<Duration> durationList = new ArrayList<>();
    durationList.add(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);
    Stream<Duration> durations = durationList.stream();

    // Act
    Optional<DurationStatistics> actualFromIfAnyResult = DurationStatistics.fromIfAny(durations);

    // Assert
    assertTrue(actualFromIfAnyResult.isPresent());
  }

  /**
   * Test {@link DurationStatistics#fromIfAny(Stream)}.
   * <ul>
   *   <li>Given {@link TransactionLogStateStore#DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS}.</li>
   *   <li>Then return Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link DurationStatistics#fromIfAny(Stream)}
   */
  @Test
  @DisplayName("Test fromIfAny(Stream); given DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS; then return Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DurationStatistics.fromIfAny(Stream)"})
  void testFromIfAny_givenDefault_time_between_snapshot_checks_thenReturnPresent2() {
    // Arrange
    ArrayList<Duration> durationList = new ArrayList<>();
    durationList.add(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);
    durationList.add(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);
    Stream<Duration> durations = durationList.stream();

    // Act
    Optional<DurationStatistics> actualFromIfAnyResult = DurationStatistics.fromIfAny(durations);

    // Assert
    assertTrue(actualFromIfAnyResult.isPresent());
  }

  /**
   * Test {@link DurationStatistics#fromIfAny(Stream)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link DurationStatistics#fromIfAny(Stream)}
   */
  @Test
  @DisplayName("Test fromIfAny(Stream); when ArrayList() stream; then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DurationStatistics.fromIfAny(Stream)"})
  void testFromIfAny_whenArrayListStream_thenReturnNotPresent() {
    // Arrange
    ArrayList<Duration> durationList = new ArrayList<>();
    Stream<Duration> durations = durationList.stream();

    // Act
    Optional<DurationStatistics> actualFromIfAnyResult = DurationStatistics.fromIfAny(durations);

    // Assert
    assertFalse(actualFromIfAnyResult.isPresent());
  }
}
