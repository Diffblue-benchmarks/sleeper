package sleeper.core.tracker.ingest.job;

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
import sleeper.core.tracker.ingest.job.query.IngestJobStatus;

class IngestJobTrackerDiffblueTest {
  /**
   * Test {@link IngestJobTracker#streamAllJobs(String)}.
   * <ul>
   *   <li>Given {@link IngestJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#streamAllJobs(String)}
   */
  @Test
  @DisplayName("Test streamAllJobs(String); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream IngestJobTracker.streamAllJobs(String)"})
  void testStreamAllJobs_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> IngestJobTracker.NONE.streamAllJobs("42"));
  }

  /**
   * Test {@link IngestJobTracker#streamAllJobs(String)}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#streamAllJobs(String)}
   */
  @Test
  @DisplayName("Test streamAllJobs(String); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream IngestJobTracker.streamAllJobs(String)"})
  void testStreamAllJobs_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<IngestJobStatus> actualStreamAllJobsResult = (new InMemoryIngestJobTracker()).streamAllJobs("42");

    // Assert
    assertTrue(actualStreamAllJobsResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link IngestJobTracker#getAllJobs(String)}.
   * <ul>
   *   <li>Given {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getAllJobs(String)}
   */
  @Test
  @DisplayName("Test getAllJobs(String); given InMemoryIngestJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestJobTracker.getAllJobs(String)"})
  void testGetAllJobs_givenInMemoryIngestJobTracker_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new InMemoryIngestJobTracker()).getAllJobs("42").isEmpty());
  }

  /**
   * Test {@link IngestJobTracker#getAllJobs(String)}.
   * <ul>
   *   <li>Given {@link IngestJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getAllJobs(String)}
   */
  @Test
  @DisplayName("Test getAllJobs(String); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestJobTracker.getAllJobs(String)"})
  void testGetAllJobs_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> IngestJobTracker.NONE.getAllJobs("42"));
  }

  /**
   * Test {@link IngestJobTracker#getUnfinishedJobs(String)}.
   * <ul>
   *   <li>Given {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getUnfinishedJobs(String)}
   */
  @Test
  @DisplayName("Test getUnfinishedJobs(String); given InMemoryIngestJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestJobTracker.getUnfinishedJobs(String)"})
  void testGetUnfinishedJobs_givenInMemoryIngestJobTracker_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new InMemoryIngestJobTracker()).getUnfinishedJobs("42").isEmpty());
  }

  /**
   * Test {@link IngestJobTracker#getUnfinishedJobs(String)}.
   * <ul>
   *   <li>Given {@link IngestJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getUnfinishedJobs(String)}
   */
  @Test
  @DisplayName("Test getUnfinishedJobs(String); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestJobTracker.getUnfinishedJobs(String)"})
  void testGetUnfinishedJobs_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> IngestJobTracker.NONE.getUnfinishedJobs("42"));
  }

  /**
   * Test {@link IngestJobTracker#getJobsByTaskId(String, String)}.
   * <ul>
   *   <li>Given {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getJobsByTaskId(String, String)}
   */
  @Test
  @DisplayName("Test getJobsByTaskId(String, String); given InMemoryIngestJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestJobTracker.getJobsByTaskId(String, String)"})
  void testGetJobsByTaskId_givenInMemoryIngestJobTracker_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new InMemoryIngestJobTracker()).getJobsByTaskId("42", "42").isEmpty());
  }

  /**
   * Test {@link IngestJobTracker#getJobsByTaskId(String, String)}.
   * <ul>
   *   <li>Given {@link IngestJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getJobsByTaskId(String, String)}
   */
  @Test
  @DisplayName("Test getJobsByTaskId(String, String); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestJobTracker.getJobsByTaskId(String, String)"})
  void testGetJobsByTaskId_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> IngestJobTracker.NONE.getJobsByTaskId("42", "42"));
  }

  /**
   * Test {@link IngestJobTracker#getJobsInTimePeriod(String, Instant, Instant)}.
   * <ul>
   *   <li>Given {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getJobsInTimePeriod(String, Instant, Instant)}
   */
  @Test
  @DisplayName("Test getJobsInTimePeriod(String, Instant, Instant); given InMemoryIngestJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestJobTracker.getJobsInTimePeriod(String, Instant, Instant)"})
  void testGetJobsInTimePeriod_givenInMemoryIngestJobTracker_thenReturnEmpty() {
    // Arrange
    InMemoryIngestJobTracker inMemoryIngestJobTracker = new InMemoryIngestJobTracker();
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertTrue(
        inMemoryIngestJobTracker
            .getJobsInTimePeriod("42", startTime,
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .isEmpty());
  }

  /**
   * Test {@link IngestJobTracker#getJobsInTimePeriod(String, Instant, Instant)}.
   * <ul>
   *   <li>Given {@link IngestJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getJobsInTimePeriod(String, Instant, Instant)}
   */
  @Test
  @DisplayName("Test getJobsInTimePeriod(String, Instant, Instant); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestJobTracker.getJobsInTimePeriod(String, Instant, Instant)"})
  void testGetJobsInTimePeriod_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> IngestJobTracker.NONE.getJobsInTimePeriod("42", startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link IngestJobTracker#getJob(String)}.
   * <ul>
   *   <li>Given {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getJob(String)}
   */
  @Test
  @DisplayName("Test getJob(String); given InMemoryIngestJobTracker (default constructor); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional IngestJobTracker.getJob(String)"})
  void testGetJob_givenInMemoryIngestJobTracker_thenReturnNotPresent() {
    // Arrange, Act and Assert
    assertFalse((new InMemoryIngestJobTracker()).getJob("42").isPresent());
  }

  /**
   * Test {@link IngestJobTracker#getJob(String)}.
   * <ul>
   *   <li>Given {@link IngestJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getJob(String)}
   */
  @Test
  @DisplayName("Test getJob(String); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional IngestJobTracker.getJob(String)"})
  void testGetJob_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> IngestJobTracker.NONE.getJob("42"));
  }

  /**
   * Test {@link IngestJobTracker#getInvalidJobs()}.
   * <ul>
   *   <li>Given {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getInvalidJobs()}
   */
  @Test
  @DisplayName("Test getInvalidJobs(); given InMemoryIngestJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestJobTracker.getInvalidJobs()"})
  void testGetInvalidJobs_givenInMemoryIngestJobTracker_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new InMemoryIngestJobTracker()).getInvalidJobs().isEmpty());
  }

  /**
   * Test {@link IngestJobTracker#getInvalidJobs()}.
   * <ul>
   *   <li>Given {@link IngestJobTracker#NONE}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTracker#getInvalidJobs()}
   */
  @Test
  @DisplayName("Test getInvalidJobs(); given NONE; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestJobTracker.getInvalidJobs()"})
  void testGetInvalidJobs_givenNone_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> IngestJobTracker.NONE.getInvalidJobs());
  }
}
