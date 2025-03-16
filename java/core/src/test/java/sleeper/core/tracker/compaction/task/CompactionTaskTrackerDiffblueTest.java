package sleeper.core.tracker.compaction.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CompactionTaskTrackerDiffblueTest {
  /**
   * Test {@link CompactionTaskTracker#getTask(String)}.
   * <p>
   * Method under test: {@link CompactionTaskTracker#getTask(String)}
   */
  @Test
  @DisplayName("Test getTask(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.core.tracker.compaction.task.CompactionTaskStatus CompactionTaskTracker.getTask(String)"})
  void testGetTask() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> CompactionTaskTracker.NONE.getTask("42"));
  }

  /**
   * Test {@link CompactionTaskTracker#getAllTasks()}.
   * <p>
   * Method under test: {@link CompactionTaskTracker#getAllTasks()}
   */
  @Test
  @DisplayName("Test getAllTasks()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionTaskTracker.getAllTasks()"})
  void testGetAllTasks() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> CompactionTaskTracker.NONE.getAllTasks());
  }

  /**
   * Test {@link CompactionTaskTracker#getTasksInTimePeriod(Instant, Instant)}.
   * <p>
   * Method under test: {@link CompactionTaskTracker#getTasksInTimePeriod(Instant, Instant)}
   */
  @Test
  @DisplayName("Test getTasksInTimePeriod(Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionTaskTracker.getTasksInTimePeriod(Instant, Instant)"})
  void testGetTasksInTimePeriod() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> CompactionTaskTracker.NONE.getTasksInTimePeriod(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link CompactionTaskTracker#getTasksInProgress()}.
   * <p>
   * Method under test: {@link CompactionTaskTracker#getTasksInProgress()}
   */
  @Test
  @DisplayName("Test getTasksInProgress()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionTaskTracker.getTasksInProgress()"})
  void testGetTasksInProgress() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> CompactionTaskTracker.NONE.getTasksInProgress());
  }
}
