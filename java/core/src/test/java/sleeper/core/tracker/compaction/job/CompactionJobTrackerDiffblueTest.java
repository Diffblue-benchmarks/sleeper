package sleeper.core.tracker.compaction.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;

class CompactionJobTrackerDiffblueTest {
  /**
   * Test {@link CompactionJobTracker#getJob(String)}.
   * <ul>
   *   <li>Given {@link InMemoryCompactionJobTracker} (default constructor).</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#getJob(String)}
   */
  @Test
  @DisplayName("Test getJob(String); given InMemoryCompactionJobTracker (default constructor); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional CompactionJobTracker.getJob(String)"})
  void testGetJob_givenInMemoryCompactionJobTracker_thenReturnNotPresent() {
    // Arrange, Act and Assert
    assertFalse((new InMemoryCompactionJobTracker()).getJob("42").isPresent());
  }

  /**
   * Test {@link CompactionJobTracker#getJob(String)}.
   * <ul>
   *   <li>Given {@link CompactionJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#getJob(String)}
   */
  @Test
  @DisplayName("Test getJob(String); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional CompactionJobTracker.getJob(String)"})
  void testGetJob_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> CompactionJobTracker.NONE.getJob("42"));
  }

  /**
   * Test {@link CompactionJobTracker#streamAllJobs(String)}.
   * <ul>
   *   <li>Given {@link CompactionJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#streamAllJobs(String)}
   */
  @Test
  @DisplayName("Test streamAllJobs(String); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream CompactionJobTracker.streamAllJobs(String)"})
  void testStreamAllJobs_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> CompactionJobTracker.NONE.streamAllJobs("42"));
  }

  /**
   * Test {@link CompactionJobTracker#streamAllJobs(String)}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#streamAllJobs(String)}
   */
  @Test
  @DisplayName("Test streamAllJobs(String); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream CompactionJobTracker.streamAllJobs(String)"})
  void testStreamAllJobs_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<CompactionJobStatus> actualStreamAllJobsResult = (new InMemoryCompactionJobTracker()).streamAllJobs("42");

    // Assert
    assertTrue(actualStreamAllJobsResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link CompactionJobTracker#getAllJobs(String)}.
   * <ul>
   *   <li>Given {@link InMemoryCompactionJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#getAllJobs(String)}
   */
  @Test
  @DisplayName("Test getAllJobs(String); given InMemoryCompactionJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionJobTracker.getAllJobs(String)"})
  void testGetAllJobs_givenInMemoryCompactionJobTracker_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new InMemoryCompactionJobTracker()).getAllJobs("42").isEmpty());
  }

  /**
   * Test {@link CompactionJobTracker#getAllJobs(String)}.
   * <ul>
   *   <li>Given {@link CompactionJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#getAllJobs(String)}
   */
  @Test
  @DisplayName("Test getAllJobs(String); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionJobTracker.getAllJobs(String)"})
  void testGetAllJobs_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> CompactionJobTracker.NONE.getAllJobs("42"));
  }

  /**
   * Test {@link CompactionJobTracker#getUnfinishedJobs(String)}.
   * <ul>
   *   <li>Given {@link InMemoryCompactionJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#getUnfinishedJobs(String)}
   */
  @Test
  @DisplayName("Test getUnfinishedJobs(String); given InMemoryCompactionJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionJobTracker.getUnfinishedJobs(String)"})
  void testGetUnfinishedJobs_givenInMemoryCompactionJobTracker_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new InMemoryCompactionJobTracker()).getUnfinishedJobs("42").isEmpty());
  }

  /**
   * Test {@link CompactionJobTracker#getUnfinishedJobs(String)}.
   * <ul>
   *   <li>Given {@link CompactionJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#getUnfinishedJobs(String)}
   */
  @Test
  @DisplayName("Test getUnfinishedJobs(String); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionJobTracker.getUnfinishedJobs(String)"})
  void testGetUnfinishedJobs_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> CompactionJobTracker.NONE.getUnfinishedJobs("42"));
  }

  /**
   * Test {@link CompactionJobTracker#getJobsByTaskId(String, String)}.
   * <ul>
   *   <li>Given {@link InMemoryCompactionJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#getJobsByTaskId(String, String)}
   */
  @Test
  @DisplayName("Test getJobsByTaskId(String, String); given InMemoryCompactionJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionJobTracker.getJobsByTaskId(String, String)"})
  void testGetJobsByTaskId_givenInMemoryCompactionJobTracker_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new InMemoryCompactionJobTracker()).getJobsByTaskId("42", "42").isEmpty());
  }

  /**
   * Test {@link CompactionJobTracker#getJobsByTaskId(String, String)}.
   * <ul>
   *   <li>Given {@link CompactionJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#getJobsByTaskId(String, String)}
   */
  @Test
  @DisplayName("Test getJobsByTaskId(String, String); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionJobTracker.getJobsByTaskId(String, String)"})
  void testGetJobsByTaskId_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> CompactionJobTracker.NONE.getJobsByTaskId("42", "42"));
  }

  /**
   * Test {@link CompactionJobTracker#getJobsInTimePeriod(String, Instant, Instant)}.
   * <ul>
   *   <li>Given {@link InMemoryCompactionJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#getJobsInTimePeriod(String, Instant, Instant)}
   */
  @Test
  @DisplayName("Test getJobsInTimePeriod(String, Instant, Instant); given InMemoryCompactionJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionJobTracker.getJobsInTimePeriod(String, Instant, Instant)"})
  void testGetJobsInTimePeriod_givenInMemoryCompactionJobTracker_thenReturnEmpty() {
    // Arrange
    InMemoryCompactionJobTracker inMemoryCompactionJobTracker = new InMemoryCompactionJobTracker();
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertTrue(
        inMemoryCompactionJobTracker
            .getJobsInTimePeriod("42", startTime,
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .isEmpty());
  }

  /**
   * Test {@link CompactionJobTracker#getJobsInTimePeriod(String, Instant, Instant)}.
   * <ul>
   *   <li>Given {@link CompactionJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTracker#getJobsInTimePeriod(String, Instant, Instant)}
   */
  @Test
  @DisplayName("Test getJobsInTimePeriod(String, Instant, Instant); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionJobTracker.getJobsInTimePeriod(String, Instant, Instant)"})
  void testGetJobsInTimePeriod_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> CompactionJobTracker.NONE.getJobsInTimePeriod("42",
        startTime, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }
}
