package sleeper.clients.status.report.compaction.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.compaction.task.InMemoryCompactionTaskTracker;

class CompactionTaskQueryDiffblueTest {
  /**
   * Test {@link CompactionTaskQuery#from(String)}.
   * <ul>
   *   <li>When {@code -a}.</li>
   *   <li>Then return run {@link InMemoryCompactionTaskTracker} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskQuery#from(String)}
   */
  @Test
  @DisplayName("Test from(String); when '-a'; then return run InMemoryCompactionTaskTracker (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionTaskQuery CompactionTaskQuery.from(String)"})
  void testFrom_whenA_thenReturnRunInMemoryCompactionTaskTrackerEmpty() {
    // Arrange and Act
    CompactionTaskQuery actualFromResult = CompactionTaskQuery.from("-a");

    // Assert
    assertTrue(actualFromResult.run(new InMemoryCompactionTaskTracker()).isEmpty());
  }

  /**
   * Test {@link CompactionTaskQuery#from(String)}.
   * <ul>
   *   <li>When {@code -u}.</li>
   *   <li>Then return run {@link InMemoryCompactionTaskTracker} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskQuery#from(String)}
   */
  @Test
  @DisplayName("Test from(String); when '-u'; then return run InMemoryCompactionTaskTracker (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionTaskQuery CompactionTaskQuery.from(String)"})
  void testFrom_whenU_thenReturnRunInMemoryCompactionTaskTrackerEmpty() {
    // Arrange and Act
    CompactionTaskQuery actualFromResult = CompactionTaskQuery.from("-u");

    // Assert
    assertTrue(actualFromResult.run(new InMemoryCompactionTaskTracker()).isEmpty());
  }

  /**
   * Test {@link CompactionTaskQuery#forPeriod(Instant, Instant)}.
   * <ul>
   *   <li>Then return run {@link InMemoryCompactionTaskTracker} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskQuery#forPeriod(Instant, Instant)}
   */
  @Test
  @DisplayName("Test forPeriod(Instant, Instant); then return run InMemoryCompactionTaskTracker (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionTaskQuery CompactionTaskQuery.forPeriod(Instant, Instant)"})
  void testForPeriod_thenReturnRunInMemoryCompactionTaskTrackerEmpty() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    CompactionTaskQuery actualForPeriodResult = CompactionTaskQuery.forPeriod(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertTrue(actualForPeriodResult.run(new InMemoryCompactionTaskTracker()).isEmpty());
  }
}
