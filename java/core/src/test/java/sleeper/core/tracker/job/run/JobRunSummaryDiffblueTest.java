package sleeper.core.tracker.job.run;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import sleeper.core.tracker.job.status.JobRunEndUpdate;
import sleeper.core.tracker.job.status.JobRunStartedUpdate;
import sleeper.core.tracker.job.status.TestJobStartedAndFinishedStatus;

class JobRunSummaryDiffblueTest {
  /**
   * Test {@link JobRunSummary#JobRunSummary(RecordsProcessed, Instant, Instant)}.
   * <ul>
   *   <li>When {@link RecordsProcessed#NONE}.</li>
   *   <li>Then return DurationInSeconds is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunSummary#JobRunSummary(RecordsProcessed, Instant, Instant)}
   */
  @Test
  @DisplayName("Test new JobRunSummary(RecordsProcessed, Instant, Instant); when NONE; then return DurationInSeconds is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunSummary.<init>(RecordsProcessed, Instant, Instant)"})
  void testNewJobRunSummary_whenNone_thenReturnDurationInSecondsIsZero() {
    // Arrange
    RecordsProcessed recordsProcessed = RecordsProcessed.NONE;
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Instant finishTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    JobRunSummary actualJobRunSummary = new JobRunSummary(recordsProcessed, startTime, finishTime);

    // Assert
    assertEquals(0.0d, actualJobRunSummary.getDurationInSeconds());
    assertEquals(0L, actualJobRunSummary.getRecordsRead());
    assertEquals(0L, actualJobRunSummary.getRecordsWritten());
    assertEquals(Double.NaN, actualJobRunSummary.getRecordsReadPerSecond());
    assertEquals(Double.NaN, actualJobRunSummary.getRecordsWrittenPerSecond());
    Instant instant = finishTime.EPOCH;
    assertSame(instant, actualJobRunSummary.getFinishTime());
    assertSame(instant, actualJobRunSummary.getStartTime());
    RecordsProcessed expectedRecordsProcessed = recordsProcessed.NONE;
    assertSame(expectedRecordsProcessed, actualJobRunSummary.getRecordsProcessed());
  }

  /**
   * Test {@link JobRunSummary#JobRunSummary(RecordsProcessed, Instant, Instant, Duration)}.
   * <ul>
   *   <li>When {@link RecordsProcessed#NONE}.</li>
   *   <li>Then return DurationInSeconds is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunSummary#JobRunSummary(RecordsProcessed, Instant, Instant, Duration)}
   */
  @Test
  @DisplayName("Test new JobRunSummary(RecordsProcessed, Instant, Instant, Duration); when NONE; then return DurationInSeconds is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunSummary.<init>(RecordsProcessed, Instant, Instant, Duration)"})
  void testNewJobRunSummary_whenNone_thenReturnDurationInSecondsIsZero2() {
    // Arrange
    RecordsProcessed recordsProcessed = RecordsProcessed.NONE;
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Instant finishTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Duration timeInProcess = TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS;

    // Act
    JobRunSummary actualJobRunSummary = new JobRunSummary(recordsProcessed, startTime, finishTime, timeInProcess);

    // Assert
    assertEquals(0.0d, actualJobRunSummary.getDurationInSeconds());
    assertEquals(0.0d, actualJobRunSummary.getRecordsReadPerSecond());
    assertEquals(0.0d, actualJobRunSummary.getRecordsWrittenPerSecond());
    assertEquals(0L, actualJobRunSummary.getRecordsRead());
    assertEquals(0L, actualJobRunSummary.getRecordsWritten());
    Duration expectedDuration = timeInProcess.ZERO;
    assertSame(expectedDuration, actualJobRunSummary.getDuration());
    Instant instant = finishTime.EPOCH;
    assertSame(instant, actualJobRunSummary.getFinishTime());
    assertSame(instant, actualJobRunSummary.getStartTime());
    RecordsProcessed expectedRecordsProcessed = recordsProcessed.NONE;
    assertSame(expectedRecordsProcessed, actualJobRunSummary.getRecordsProcessed());
    assertSame(timeInProcess, actualJobRunSummary.getTimeInProcess());
  }

  /**
   * Test {@link JobRunSummary#JobRunSummary(RecordsProcessed, Instant, Duration)}.
   * <ul>
   *   <li>When {@link RecordsProcessed#NONE}.</li>
   *   <li>Then return RecordsReadPerSecond is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunSummary#JobRunSummary(RecordsProcessed, Instant, Duration)}
   */
  @Test
  @DisplayName("Test new JobRunSummary(RecordsProcessed, Instant, Duration); when NONE; then return RecordsReadPerSecond is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunSummary.<init>(RecordsProcessed, Instant, Duration)"})
  void testNewJobRunSummary_whenNone_thenReturnRecordsReadPerSecondIsZero() {
    // Arrange
    RecordsProcessed recordsProcessed = RecordsProcessed.NONE;
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Duration duration = TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS;

    // Act
    JobRunSummary actualJobRunSummary = new JobRunSummary(recordsProcessed, startTime, duration);

    // Assert
    assertEquals(0.0d, actualJobRunSummary.getRecordsReadPerSecond());
    assertEquals(0.0d, actualJobRunSummary.getRecordsWrittenPerSecond());
    assertEquals(0L, actualJobRunSummary.getRecordsRead());
    assertEquals(0L, actualJobRunSummary.getRecordsWritten());
    assertEquals(60.0d, actualJobRunSummary.getDurationInSeconds());
    Instant expectedStartTime = startTime.EPOCH;
    assertSame(expectedStartTime, actualJobRunSummary.getStartTime());
    RecordsProcessed expectedRecordsProcessed = recordsProcessed.NONE;
    assertSame(expectedRecordsProcessed, actualJobRunSummary.getRecordsProcessed());
    assertSame(duration, actualJobRunSummary.getTimeInProcess());
  }

  /**
   * Test {@link JobRunSummary#JobRunSummary(RecordsProcessed, JobRunTime)}.
   * <ul>
   *   <li>When {@link RecordsProcessed#NONE}.</li>
   *   <li>Then return RecordsReadPerSecond is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunSummary#JobRunSummary(RecordsProcessed, JobRunTime)}
   */
  @Test
  @DisplayName("Test new JobRunSummary(RecordsProcessed, JobRunTime); when NONE; then return RecordsReadPerSecond is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunSummary.<init>(RecordsProcessed, JobRunTime)"})
  void testNewJobRunSummary_whenNone_thenReturnRecordsReadPerSecondIsZero2() {
    // Arrange
    RecordsProcessed recordsProcessed = RecordsProcessed.NONE;
    JobRunTime runTime = new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);

    // Act
    JobRunSummary actualJobRunSummary = new JobRunSummary(recordsProcessed, runTime);

    // Assert
    assertEquals(0.0d, actualJobRunSummary.getRecordsReadPerSecond());
    assertEquals(0.0d, actualJobRunSummary.getRecordsWrittenPerSecond());
    assertEquals(0L, actualJobRunSummary.getRecordsRead());
    assertEquals(0L, actualJobRunSummary.getRecordsWritten());
    assertEquals(60.0d, actualJobRunSummary.getDurationInSeconds());
    assertSame(runTime, actualJobRunSummary.getRunTime());
    RecordsProcessed expectedRecordsProcessed = recordsProcessed.NONE;
    assertSame(expectedRecordsProcessed, actualJobRunSummary.getRecordsProcessed());
  }

  /**
   * Test {@link JobRunSummary#from(Instant, JobRunEndUpdate)} with {@code startTime}, {@code finishedUpdate}.
   * <p>
   * Method under test: {@link JobRunSummary#from(Instant, JobRunEndUpdate)}
   */
  @Test
  @DisplayName("Test from(Instant, JobRunEndUpdate) with 'startTime', 'finishedUpdate'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunSummary JobRunSummary.from(Instant, JobRunEndUpdate)"})
  void testFromWithStartTimeFinishedUpdate() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AggregatedTaskJobsFinishedStatus finishedUpdate = mock(AggregatedTaskJobsFinishedStatus.class);
    when(finishedUpdate.getRecordsProcessed()).thenReturn(RecordsProcessed.NONE);
    when(finishedUpdate.getFinishTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Optional<Duration> emptyResult = Optional.empty();
    when(finishedUpdate.getTimeInProcess()).thenReturn(emptyResult);

    // Act
    JobRunSummary actualFromResult = JobRunSummary.from(startTime, finishedUpdate);

    // Assert
    verify(finishedUpdate).getFinishTime();
    verify(finishedUpdate).getRecordsProcessed();
    verify(finishedUpdate).getTimeInProcess();
    assertEquals(0.0d, actualFromResult.getRunTime().getTimeInProcessInSeconds());
    assertEquals(Double.NaN, actualFromResult.getRecordsReadPerSecond());
    assertEquals(Double.NaN, actualFromResult.getRecordsWrittenPerSecond());
  }

  /**
   * Test {@link JobRunSummary#from(Instant, JobRunEndUpdate)} with {@code startTime}, {@code finishedUpdate}.
   * <ul>
   *   <li>Then return RecordsReadPerSecond is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunSummary#from(Instant, JobRunEndUpdate)}
   */
  @Test
  @DisplayName("Test from(Instant, JobRunEndUpdate) with 'startTime', 'finishedUpdate'; then return RecordsReadPerSecond is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunSummary JobRunSummary.from(Instant, JobRunEndUpdate)"})
  void testFromWithStartTimeFinishedUpdate_thenReturnRecordsReadPerSecondIsZero() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AggregatedTaskJobsFinishedStatus finishedUpdate = mock(AggregatedTaskJobsFinishedStatus.class);
    when(finishedUpdate.getRecordsProcessed()).thenReturn(RecordsProcessed.NONE);
    when(finishedUpdate.getFinishTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Optional<Duration> ofResult = Optional.of(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);
    when(finishedUpdate.getTimeInProcess()).thenReturn(ofResult);

    // Act
    JobRunSummary actualFromResult = JobRunSummary.from(startTime, finishedUpdate);

    // Assert
    verify(finishedUpdate).getFinishTime();
    verify(finishedUpdate).getRecordsProcessed();
    verify(finishedUpdate).getTimeInProcess();
    assertEquals(0.0d, actualFromResult.getRecordsReadPerSecond());
    assertEquals(0.0d, actualFromResult.getRecordsWrittenPerSecond());
    JobRunTime runTime = actualFromResult.getRunTime();
    assertEquals(60.0d, runTime.getTimeInProcessInSeconds());
    Duration timeInProcess = actualFromResult.getTimeInProcess();
    assertEquals(60000000000L, timeInProcess.toNanos());
    assertSame(timeInProcess, runTime.getTimeInProcess());
  }

  /**
   * Test {@link JobRunSummary#from(JobRunStartedUpdate, JobRunEndUpdate)} with {@code startedUpdate}, {@code finishedUpdate}.
   * <p>
   * Method under test: {@link JobRunSummary#from(JobRunStartedUpdate, JobRunEndUpdate)}
   */
  @Test
  @DisplayName("Test from(JobRunStartedUpdate, JobRunEndUpdate) with 'startedUpdate', 'finishedUpdate'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunSummary JobRunSummary.from(JobRunStartedUpdate, JobRunEndUpdate)"})
  void testFromWithStartedUpdateFinishedUpdate() {
    // Arrange
    TestJobStartedAndFinishedStatus startedUpdate = mock(TestJobStartedAndFinishedStatus.class);
    when(startedUpdate.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AggregatedTaskJobsFinishedStatus finishedUpdate = mock(AggregatedTaskJobsFinishedStatus.class);
    when(finishedUpdate.getRecordsProcessed()).thenReturn(RecordsProcessed.NONE);
    when(finishedUpdate.getFinishTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Optional<Duration> emptyResult = Optional.empty();
    when(finishedUpdate.getTimeInProcess()).thenReturn(emptyResult);

    // Act
    JobRunSummary actualFromResult = JobRunSummary.from(startedUpdate, finishedUpdate);

    // Assert
    verify(finishedUpdate).getFinishTime();
    verify(finishedUpdate).getRecordsProcessed();
    verify(finishedUpdate).getTimeInProcess();
    verify(startedUpdate).getStartTime();
    assertEquals(0.0d, actualFromResult.getRunTime().getTimeInProcessInSeconds());
    assertEquals(Double.NaN, actualFromResult.getRecordsReadPerSecond());
    assertEquals(Double.NaN, actualFromResult.getRecordsWrittenPerSecond());
  }

  /**
   * Test {@link JobRunSummary#from(JobRunStartedUpdate, JobRunEndUpdate)} with {@code startedUpdate}, {@code finishedUpdate}.
   * <ul>
   *   <li>Then return RecordsReadPerSecond is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunSummary#from(JobRunStartedUpdate, JobRunEndUpdate)}
   */
  @Test
  @DisplayName("Test from(JobRunStartedUpdate, JobRunEndUpdate) with 'startedUpdate', 'finishedUpdate'; then return RecordsReadPerSecond is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunSummary JobRunSummary.from(JobRunStartedUpdate, JobRunEndUpdate)"})
  void testFromWithStartedUpdateFinishedUpdate_thenReturnRecordsReadPerSecondIsZero() {
    // Arrange
    TestJobStartedAndFinishedStatus startedUpdate = mock(TestJobStartedAndFinishedStatus.class);
    when(startedUpdate.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AggregatedTaskJobsFinishedStatus finishedUpdate = mock(AggregatedTaskJobsFinishedStatus.class);
    when(finishedUpdate.getRecordsProcessed()).thenReturn(RecordsProcessed.NONE);
    when(finishedUpdate.getFinishTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Optional<Duration> ofResult = Optional.of(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);
    when(finishedUpdate.getTimeInProcess()).thenReturn(ofResult);

    // Act
    JobRunSummary actualFromResult = JobRunSummary.from(startedUpdate, finishedUpdate);

    // Assert
    verify(finishedUpdate).getFinishTime();
    verify(finishedUpdate).getRecordsProcessed();
    verify(finishedUpdate).getTimeInProcess();
    verify(startedUpdate).getStartTime();
    assertEquals(0.0d, actualFromResult.getRecordsReadPerSecond());
    assertEquals(0.0d, actualFromResult.getRecordsWrittenPerSecond());
    JobRunTime runTime = actualFromResult.getRunTime();
    assertEquals(60.0d, runTime.getTimeInProcessInSeconds());
    Duration timeInProcess = actualFromResult.getTimeInProcess();
    assertEquals(60000000000L, timeInProcess.toNanos());
    assertSame(timeInProcess, runTime.getTimeInProcess());
  }

  /**
   * Test {@link JobRunSummary#noProcessingDoneAtTime(Instant)}.
   * <p>
   * Method under test: {@link JobRunSummary#noProcessingDoneAtTime(Instant)}
   */
  @Test
  @DisplayName("Test noProcessingDoneAtTime(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunSummary JobRunSummary.noProcessingDoneAtTime(Instant)"})
  void testNoProcessingDoneAtTime() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    JobRunSummary actualNoProcessingDoneAtTimeResult = JobRunSummary.noProcessingDoneAtTime(startTime);

    // Assert
    assertEquals(0.0d, actualNoProcessingDoneAtTimeResult.getDurationInSeconds());
    assertEquals(0L, actualNoProcessingDoneAtTimeResult.getRecordsRead());
    assertEquals(0L, actualNoProcessingDoneAtTimeResult.getRecordsWritten());
    assertEquals(Double.NaN, actualNoProcessingDoneAtTimeResult.getRecordsReadPerSecond());
    assertEquals(Double.NaN, actualNoProcessingDoneAtTimeResult.getRecordsWrittenPerSecond());
    Instant instant = startTime.EPOCH;
    assertSame(instant, actualNoProcessingDoneAtTimeResult.getFinishTime());
    assertSame(instant, actualNoProcessingDoneAtTimeResult.getStartTime());
  }

  /**
   * Test {@link JobRunSummary#noRecordsProcessed(JobRunTime)}.
   * <ul>
   *   <li>Then return RecordsReadPerSecond is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunSummary#noRecordsProcessed(JobRunTime)}
   */
  @Test
  @DisplayName("Test noRecordsProcessed(JobRunTime); then return RecordsReadPerSecond is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunSummary JobRunSummary.noRecordsProcessed(JobRunTime)"})
  void testNoRecordsProcessed_thenReturnRecordsReadPerSecondIsZero() {
    // Arrange
    JobRunTime runTime = new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);

    // Act
    JobRunSummary actualNoRecordsProcessedResult = JobRunSummary.noRecordsProcessed(runTime);

    // Assert
    assertEquals(0.0d, actualNoRecordsProcessedResult.getRecordsReadPerSecond());
    assertEquals(0.0d, actualNoRecordsProcessedResult.getRecordsWrittenPerSecond());
    assertEquals(0L, actualNoRecordsProcessedResult.getRecordsRead());
    assertEquals(0L, actualNoRecordsProcessedResult.getRecordsWritten());
    assertEquals(60.0d, actualNoRecordsProcessedResult.getDurationInSeconds());
    assertSame(runTime, actualNoRecordsProcessedResult.getRunTime());
  }

  /**
   * Test {@link JobRunSummary#getRecordsRead()}.
   * <p>
   * Method under test: {@link JobRunSummary#getRecordsRead()}
   */
  @Test
  @DisplayName("Test getRecordsRead()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long JobRunSummary.getRecordsRead()"})
  void testGetRecordsRead() {
    // Arrange, Act and Assert
    assertEquals(0L,
        JobRunSummary.noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .getRecordsRead());
  }

  /**
   * Test {@link JobRunSummary#getRecordsWritten()}.
   * <p>
   * Method under test: {@link JobRunSummary#getRecordsWritten()}
   */
  @Test
  @DisplayName("Test getRecordsWritten()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long JobRunSummary.getRecordsWritten()"})
  void testGetRecordsWritten() {
    // Arrange, Act and Assert
    assertEquals(0L,
        JobRunSummary.noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .getRecordsWritten());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRunSummary#toString()}
   *   <li>{@link JobRunSummary#getRecordsProcessed()}
   *   <li>{@link JobRunSummary#getRecordsReadPerSecond()}
   *   <li>{@link JobRunSummary#getRecordsWrittenPerSecond()}
   *   <li>{@link JobRunSummary#getRunTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RecordsProcessed JobRunSummary.getRecordsProcessed()",
      "double JobRunSummary.getRecordsReadPerSecond()", "double JobRunSummary.getRecordsWrittenPerSecond()",
      "JobRunTime JobRunSummary.getRunTime()", "String JobRunSummary.toString()"})
  void testGettersAndSetters() {
    // Arrange
    JobRunSummary noProcessingDoneAtTimeResult = JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    String actualToStringResult = noProcessingDoneAtTimeResult.toString();
    RecordsProcessed actualRecordsProcessed = noProcessingDoneAtTimeResult.getRecordsProcessed();
    double actualRecordsReadPerSecond = noProcessingDoneAtTimeResult.getRecordsReadPerSecond();
    double actualRecordsWrittenPerSecond = noProcessingDoneAtTimeResult.getRecordsWrittenPerSecond();
    JobRunTime actualRunTime = noProcessingDoneAtTimeResult.getRunTime();

    // Assert
    assertEquals("JobRunSummary{recordsProcessed=RecordsProcessed{recordsRead=0, recordsWritten=0}, runTime=JobRunTime"
        + "{startTime=1970-01-01T00:00:00Z, endTime=1970-01-01T00:00:00Z, timeInProcess=PT0S}, recordsReadPerSecond=NaN,"
        + " recordsWrittenPerSecond=NaN}", actualToStringResult);
    Instant finishTime = actualRunTime.getFinishTime();
    assertEquals(0, finishTime.getNano());
    assertEquals(0.0d, actualRunTime.getDurationInSeconds());
    assertEquals(0.0d, actualRunTime.getTimeInProcessInSeconds());
    assertEquals(0L, actualRunTime.getDuration().toNanos());
    assertEquals(0L, finishTime.getEpochSecond());
    assertEquals(actualRecordsProcessed.NONE, actualRecordsProcessed);
    assertEquals(Double.NaN, actualRecordsReadPerSecond);
    assertEquals(Double.NaN, actualRecordsWrittenPerSecond);
  }

  /**
   * Test {@link JobRunSummary#getStartTime()}.
   * <p>
   * Method under test: {@link JobRunSummary#getStartTime()}
   */
  @Test
  @DisplayName("Test getStartTime()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant JobRunSummary.getStartTime()"})
  void testGetStartTime() {
    // Arrange
    JobRunSummary noProcessingDoneAtTimeResult = JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Instant actualStartTime = noProcessingDoneAtTimeResult.getStartTime();

    // Assert
    Instant instant = actualStartTime.EPOCH;
    assertSame(instant, noProcessingDoneAtTimeResult.getFinishTime());
    assertSame(instant, actualStartTime);
    JobRunTime runTime = noProcessingDoneAtTimeResult.getRunTime();
    assertSame(instant, runTime.getFinishTime());
    assertSame(instant, runTime.getStartTime());
  }

  /**
   * Test {@link JobRunSummary#getFinishTime()}.
   * <p>
   * Method under test: {@link JobRunSummary#getFinishTime()}
   */
  @Test
  @DisplayName("Test getFinishTime()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant JobRunSummary.getFinishTime()"})
  void testGetFinishTime() {
    // Arrange
    JobRunSummary noProcessingDoneAtTimeResult = JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Instant actualFinishTime = noProcessingDoneAtTimeResult.getFinishTime();

    // Assert
    Instant instant = actualFinishTime.EPOCH;
    assertSame(instant, actualFinishTime);
    assertSame(instant, noProcessingDoneAtTimeResult.getStartTime());
    JobRunTime runTime = noProcessingDoneAtTimeResult.getRunTime();
    assertSame(instant, runTime.getFinishTime());
    assertSame(instant, runTime.getStartTime());
  }

  /**
   * Test {@link JobRunSummary#getDurationInSeconds()}.
   * <p>
   * Method under test: {@link JobRunSummary#getDurationInSeconds()}
   */
  @Test
  @DisplayName("Test getDurationInSeconds()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double JobRunSummary.getDurationInSeconds()"})
  void testGetDurationInSeconds() {
    // Arrange, Act and Assert
    assertEquals(0.0d,
        JobRunSummary.noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .getDurationInSeconds());
  }

  /**
   * Test {@link JobRunSummary#getDuration()}.
   * <p>
   * Method under test: {@link JobRunSummary#getDuration()}
   */
  @Test
  @DisplayName("Test getDuration()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Duration JobRunSummary.getDuration()"})
  void testGetDuration() {
    // Arrange
    JobRunSummary noProcessingDoneAtTimeResult = JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Duration actualDuration = noProcessingDoneAtTimeResult.getDuration();

    // Assert
    assertEquals(0L, actualDuration.toNanos());
    Duration duration = actualDuration.ZERO;
    assertSame(duration, actualDuration);
    assertSame(duration, noProcessingDoneAtTimeResult.getTimeInProcess());
    JobRunTime runTime = noProcessingDoneAtTimeResult.getRunTime();
    assertSame(duration, runTime.getDuration());
    assertSame(duration, runTime.getTimeInProcess());
  }

  /**
   * Test {@link JobRunSummary#getTimeInProcess()}.
   * <p>
   * Method under test: {@link JobRunSummary#getTimeInProcess()}
   */
  @Test
  @DisplayName("Test getTimeInProcess()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Duration JobRunSummary.getTimeInProcess()"})
  void testGetTimeInProcess() {
    // Arrange
    JobRunSummary noProcessingDoneAtTimeResult = JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Duration actualTimeInProcess = noProcessingDoneAtTimeResult.getTimeInProcess();

    // Assert
    assertEquals(0L, actualTimeInProcess.toNanos());
    Duration duration = actualTimeInProcess.ZERO;
    assertSame(duration, noProcessingDoneAtTimeResult.getDuration());
    assertSame(duration, actualTimeInProcess);
    JobRunTime runTime = noProcessingDoneAtTimeResult.getRunTime();
    assertSame(duration, runTime.getDuration());
    assertSame(duration, runTime.getTimeInProcess());
  }

  /**
   * Test {@link JobRunSummary#equals(Object)}, and {@link JobRunSummary#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRunSummary#equals(Object)}
   *   <li>{@link JobRunSummary#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunSummary.equals(Object)", "int JobRunSummary.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JobRunSummary noProcessingDoneAtTimeResult = JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunSummary noProcessingDoneAtTimeResult2 = JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(noProcessingDoneAtTimeResult, noProcessingDoneAtTimeResult2);
    int expectedHashCodeResult = noProcessingDoneAtTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, noProcessingDoneAtTimeResult2.hashCode());
  }

  /**
   * Test {@link JobRunSummary#equals(Object)}, and {@link JobRunSummary#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRunSummary#equals(Object)}
   *   <li>{@link JobRunSummary#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunSummary.equals(Object)", "int JobRunSummary.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JobRunSummary noProcessingDoneAtTimeResult = JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(noProcessingDoneAtTimeResult, noProcessingDoneAtTimeResult);
    int expectedHashCodeResult = noProcessingDoneAtTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, noProcessingDoneAtTimeResult.hashCode());
  }

  /**
   * Test {@link JobRunSummary#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunSummary#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunSummary.equals(Object)", "int JobRunSummary.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    JobRunSummary noProcessingDoneAtTimeResult = JobRunSummary
        .noProcessingDoneAtTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertNotEquals(noProcessingDoneAtTimeResult, JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link JobRunSummary#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunSummary#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunSummary.equals(Object)", "int JobRunSummary.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
  }

  /**
   * Test {@link JobRunSummary#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunSummary#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunSummary.equals(Object)", "int JobRunSummary.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        JobRunSummary
            .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to JobRunSummary");
  }
}
