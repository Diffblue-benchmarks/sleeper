package sleeper.core.tracker.ingest.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class IngestTaskTrackerDiffblueTest {
  /**
   * Test {@link IngestTaskTracker#getTask(String)}.
   * <p>
   * Method under test: {@link IngestTaskTracker#getTask(String)}
   */
  @Test
  @DisplayName("Test getTask(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.core.tracker.ingest.task.IngestTaskStatus IngestTaskTracker.getTask(String)"})
  void testGetTask() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> (new InMemoryIngestTaskTracker()).getTask("42"));
  }

  /**
   * Test {@link IngestTaskTracker#getAllTasks()}.
   * <p>
   * Method under test: {@link IngestTaskTracker#getAllTasks()}
   */
  @Test
  @DisplayName("Test getAllTasks()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestTaskTracker.getAllTasks()"})
  void testGetAllTasks() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> IngestTaskTracker.NONE.getAllTasks());
  }

  /**
   * Test {@link IngestTaskTracker#getTasksInTimePeriod(Instant, Instant)}.
   * <p>
   * Method under test: {@link IngestTaskTracker#getTasksInTimePeriod(Instant, Instant)}
   */
  @Test
  @DisplayName("Test getTasksInTimePeriod(Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestTaskTracker.getTasksInTimePeriod(Instant, Instant)"})
  void testGetTasksInTimePeriod() {
    // Arrange
    InMemoryIngestTaskTracker inMemoryIngestTaskTracker = new InMemoryIngestTaskTracker();
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> inMemoryIngestTaskTracker.getTasksInTimePeriod(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link IngestTaskTracker#getTasksInProgress()}.
   * <p>
   * Method under test: {@link IngestTaskTracker#getTasksInProgress()}
   */
  @Test
  @DisplayName("Test getTasksInProgress()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestTaskTracker.getTasksInProgress()"})
  void testGetTasksInProgress() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> IngestTaskTracker.NONE.getTasksInProgress());
  }
}
