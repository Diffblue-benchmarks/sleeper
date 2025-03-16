package sleeper.clients.status.report.ingest.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.tracker.ingest.task.InMemoryIngestTaskTracker;
import sleeper.core.tracker.ingest.task.IngestTaskStatus;

class IngestTaskQueryDiffblueTest {
  /**
   * Test {@link IngestTaskQuery#from(String)}.
   * <ul>
   *   <li>When {@code -a}.</li>
   *   <li>Then return run {@link InMemoryIngestTaskTracker} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskQuery#from(String)}
   */
  @Test
  @DisplayName("Test from(String); when '-a'; then return run InMemoryIngestTaskTracker (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskQuery IngestTaskQuery.from(String)"})
  void testFrom_whenA_thenReturnRunInMemoryIngestTaskTrackerEmpty() {
    // Arrange and Act
    IngestTaskQuery actualFromResult = IngestTaskQuery.from("-a");

    // Assert
    assertTrue(actualFromResult.run(new InMemoryIngestTaskTracker()).isEmpty());
  }

  /**
   * Test {@link IngestTaskQuery#from(String)}.
   * <ul>
   *   <li>When {@code -u}.</li>
   *   <li>Then return run {@link InMemoryIngestTaskTracker} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskQuery#from(String)}
   */
  @Test
  @DisplayName("Test from(String); when '-u'; then return run InMemoryIngestTaskTracker (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskQuery IngestTaskQuery.from(String)"})
  void testFrom_whenU_thenReturnRunInMemoryIngestTaskTrackerEmpty() {
    // Arrange and Act
    IngestTaskQuery actualFromResult = IngestTaskQuery.from("-u");

    // Assert
    assertTrue(actualFromResult.run(new InMemoryIngestTaskTracker()).isEmpty());
  }

  /**
   * Test {@link IngestTaskQuery#forPeriod(Instant, Instant)}.
   * <ul>
   *   <li>Then return run {@link InMemoryIngestTaskTracker} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskQuery#forPeriod(Instant, Instant)}
   */
  @Test
  @DisplayName("Test forPeriod(Instant, Instant); then return run InMemoryIngestTaskTracker Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskQuery IngestTaskQuery.forPeriod(Instant, Instant)"})
  void testForPeriod_thenReturnRunInMemoryIngestTaskTrackerEmpty() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    IngestTaskQuery actualForPeriodResult = IngestTaskQuery.forPeriod(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InMemoryIngestTaskTracker inMemoryIngestTaskTracker = mock(InMemoryIngestTaskTracker.class);
    when(inMemoryIngestTaskTracker.getTasksInTimePeriod(Mockito.<Instant>any(), Mockito.<Instant>any()))
        .thenReturn(new ArrayList<>());
    List<IngestTaskStatus> actualRunResult = actualForPeriodResult.run(inMemoryIngestTaskTracker);

    // Assert
    verify(inMemoryIngestTaskTracker).getTasksInTimePeriod(isA(Instant.class), isA(Instant.class));
    assertTrue(actualRunResult.isEmpty());
  }
}
