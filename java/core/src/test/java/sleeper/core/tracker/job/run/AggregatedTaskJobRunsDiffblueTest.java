package sleeper.core.tracker.job.run;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.tracker.job.status.AggregatedTaskJobsFinishedStatus;
import sleeper.core.tracker.job.status.JobRunStartedUpdate;
import sleeper.core.tracker.job.status.TestJobStartedAndFinishedStatus;

class AggregatedTaskJobRunsDiffblueTest {
  /**
   * Test {@link AggregatedTaskJobRuns#from(String, JobRunStartedUpdate, AggregatedTaskJobsFinishedStatus)}.
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#from(String, JobRunStartedUpdate, AggregatedTaskJobsFinishedStatus)}
   */
  @Test
  @DisplayName("Test from(String, JobRunStartedUpdate, AggregatedTaskJobsFinishedStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AggregatedTaskJobRuns AggregatedTaskJobRuns.from(String, JobRunStartedUpdate, AggregatedTaskJobsFinishedStatus)"})
  void testFrom() {
    // Arrange and Act
    AggregatedTaskJobRuns actualFromResult = AggregatedTaskJobRuns.from("42", null,
        mock(AggregatedTaskJobsFinishedStatus.class));

    // Assert
    assertEquals("42", actualFromResult.getTaskId());
    assertNull(actualFromResult.getFinishTime());
    assertTrue(actualFromResult.isFinished());
    assertTrue(actualFromResult.isFinishedSuccessfully());
  }

  /**
   * Test {@link AggregatedTaskJobRuns#getTaskId()}.
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#getTaskId()}
   */
  @Test
  @DisplayName("Test getTaskId()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String AggregatedTaskJobRuns.getTaskId()"})
  void testGetTaskId() {
    // Arrange, Act and Assert
    assertEquals("42", AggregatedTaskJobRuns.from("42", null, null).getTaskId());
  }

  /**
   * Test {@link AggregatedTaskJobRuns#getStatusUpdates()}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#getStatusUpdates()}
   */
  @Test
  @DisplayName("Test getStatusUpdates(); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List AggregatedTaskJobRuns.getStatusUpdates()"})
  void testGetStatusUpdates_thenReturnSizeIsOne() {
    // Arrange, Act and Assert
    assertEquals(1,
        AggregatedTaskJobRuns.from("42", mock(TestJobStartedAndFinishedStatus.class), null).getStatusUpdates().size());
  }

  /**
   * Test {@link AggregatedTaskJobRuns#getStatusUpdates()}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#getStatusUpdates()}
   */
  @Test
  @DisplayName("Test getStatusUpdates(); then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List AggregatedTaskJobRuns.getStatusUpdates()"})
  void testGetStatusUpdates_thenReturnSizeIsTwo() {
    // Arrange, Act and Assert
    assertEquals(2,
        AggregatedTaskJobRuns
            .from("42", mock(TestJobStartedAndFinishedStatus.class), mock(AggregatedTaskJobsFinishedStatus.class))
            .getStatusUpdates()
            .size());
  }

  /**
   * Test {@link AggregatedTaskJobRuns#isFinished()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#isFinished()}
   */
  @Test
  @DisplayName("Test isFinished(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobRuns.isFinished()"})
  void testIsFinished_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(AggregatedTaskJobRuns.from("42", null, null).isFinished());
  }

  /**
   * Test {@link AggregatedTaskJobRuns#isFinished()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#isFinished()}
   */
  @Test
  @DisplayName("Test isFinished(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobRuns.isFinished()"})
  void testIsFinished_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(AggregatedTaskJobRuns.from("42", null, mock(AggregatedTaskJobsFinishedStatus.class)).isFinished());
  }

  /**
   * Test {@link AggregatedTaskJobRuns#isFinishedSuccessfully()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#isFinishedSuccessfully()}
   */
  @Test
  @DisplayName("Test isFinishedSuccessfully(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobRuns.isFinishedSuccessfully()"})
  void testIsFinishedSuccessfully_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(AggregatedTaskJobRuns.from("42", null, null).isFinishedSuccessfully());
  }

  /**
   * Test {@link AggregatedTaskJobRuns#isFinishedSuccessfully()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#isFinishedSuccessfully()}
   */
  @Test
  @DisplayName("Test isFinishedSuccessfully(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobRuns.isFinishedSuccessfully()"})
  void testIsFinishedSuccessfully_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        AggregatedTaskJobRuns.from("42", null, mock(AggregatedTaskJobsFinishedStatus.class)).isFinishedSuccessfully());
  }

  /**
   * Test {@link AggregatedTaskJobRuns#getStartTime()}.
   * <ul>
   *   <li>Then return {@link Instant#EPOCH}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#getStartTime()}
   */
  @Test
  @DisplayName("Test getStartTime(); then return EPOCH")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant AggregatedTaskJobRuns.getStartTime()"})
  void testGetStartTime_thenReturnEpoch() {
    // Arrange
    TestJobStartedAndFinishedStatus startedStatus = mock(TestJobStartedAndFinishedStatus.class);
    when(startedStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Instant actualStartTime = AggregatedTaskJobRuns
        .from("42", startedStatus, mock(AggregatedTaskJobsFinishedStatus.class))
        .getStartTime();

    // Assert
    verify(startedStatus).getStartTime();
    assertSame(actualStartTime.EPOCH, actualStartTime);
  }

  /**
   * Test {@link AggregatedTaskJobRuns#getFinishTime()}.
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#getFinishTime()}
   */
  @Test
  @DisplayName("Test getFinishTime()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant AggregatedTaskJobRuns.getFinishTime()"})
  void testGetFinishTime() {
    // Arrange
    AggregatedTaskJobsFinishedStatus finishedStatus = mock(AggregatedTaskJobsFinishedStatus.class);
    when(finishedStatus.getFinishTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Instant actualFinishTime = AggregatedTaskJobRuns.from("42", null, finishedStatus).getFinishTime();

    // Assert
    verify(finishedStatus).getFinishTime();
    assertSame(actualFinishTime.EPOCH, actualFinishTime);
  }

  /**
   * Test {@link AggregatedTaskJobRuns#getFinishedSummary()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#getFinishedSummary()}
   */
  @Test
  @DisplayName("Test getFinishedSummary(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunSummary AggregatedTaskJobRuns.getFinishedSummary()"})
  void testGetFinishedSummary_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(AggregatedTaskJobRuns.from("42", null, null).getFinishedSummary());
  }

  /**
   * Test {@link AggregatedTaskJobRuns#getFinishedSummary()}.
   * <ul>
   *   <li>Then return RecordsReadPerSecond is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#getFinishedSummary()}
   */
  @Test
  @DisplayName("Test getFinishedSummary(); then return RecordsReadPerSecond is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunSummary AggregatedTaskJobRuns.getFinishedSummary()"})
  void testGetFinishedSummary_thenReturnRecordsReadPerSecondIsZero() {
    // Arrange
    TestJobStartedAndFinishedStatus startedStatus = mock(TestJobStartedAndFinishedStatus.class);
    when(startedStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AggregatedTaskJobsFinishedStatus finishedStatus = mock(AggregatedTaskJobsFinishedStatus.class);
    when(finishedStatus.getRecordsProcessed()).thenReturn(RecordsProcessed.NONE);
    when(finishedStatus.getFinishTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Optional<Duration> ofResult = Optional.of(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);
    when(finishedStatus.getTimeInProcess()).thenReturn(ofResult);

    // Act
    JobRunSummary actualFinishedSummary = AggregatedTaskJobRuns.from("42", startedStatus, finishedStatus)
        .getFinishedSummary();

    // Assert
    verify(finishedStatus).getFinishTime();
    verify(finishedStatus).getRecordsProcessed();
    verify(finishedStatus).getTimeInProcess();
    verify(startedStatus).getStartTime();
    assertEquals(0.0d, actualFinishedSummary.getRecordsReadPerSecond());
    assertEquals(0.0d, actualFinishedSummary.getRecordsWrittenPerSecond());
    JobRunTime runTime = actualFinishedSummary.getRunTime();
    assertEquals(60.0d, runTime.getTimeInProcessInSeconds());
    Duration timeInProcess = actualFinishedSummary.getTimeInProcess();
    assertEquals(60000000000L, timeInProcess.toNanos());
    assertSame(timeInProcess, runTime.getTimeInProcess());
  }

  /**
   * Test {@link AggregatedTaskJobRuns#getFinishedSummary()}.
   * <ul>
   *   <li>Then return RunTime TimeInProcessInSeconds is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobRuns#getFinishedSummary()}
   */
  @Test
  @DisplayName("Test getFinishedSummary(); then return RunTime TimeInProcessInSeconds is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunSummary AggregatedTaskJobRuns.getFinishedSummary()"})
  void testGetFinishedSummary_thenReturnRunTimeTimeInProcessInSecondsIsZero() {
    // Arrange
    TestJobStartedAndFinishedStatus startedStatus = mock(TestJobStartedAndFinishedStatus.class);
    when(startedStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AggregatedTaskJobsFinishedStatus finishedStatus = mock(AggregatedTaskJobsFinishedStatus.class);
    when(finishedStatus.getRecordsProcessed()).thenReturn(RecordsProcessed.NONE);
    when(finishedStatus.getFinishTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Optional<Duration> emptyResult = Optional.empty();
    when(finishedStatus.getTimeInProcess()).thenReturn(emptyResult);

    // Act
    JobRunSummary actualFinishedSummary = AggregatedTaskJobRuns.from("42", startedStatus, finishedStatus)
        .getFinishedSummary();

    // Assert
    verify(finishedStatus).getFinishTime();
    verify(finishedStatus).getRecordsProcessed();
    verify(finishedStatus).getTimeInProcess();
    verify(startedStatus).getStartTime();
    assertEquals(0.0d, actualFinishedSummary.getRunTime().getTimeInProcessInSeconds());
    assertEquals(Double.NaN, actualFinishedSummary.getRecordsReadPerSecond());
    assertEquals(Double.NaN, actualFinishedSummary.getRecordsWrittenPerSecond());
  }
}
