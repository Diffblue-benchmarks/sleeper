package sleeper.core.tracker.compaction.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.tracker.compaction.task.CompactionTaskFinishedStatus.Builder;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.JobRunTime;
import sleeper.core.tracker.job.run.RecordsProcessed;

class CompactionTaskFinishedStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#addJobSummary(JobRunSummary)}.
   * <p>
   * Method under test: {@link Builder#addJobSummary(JobRunSummary)}
   */
  @Test
  @DisplayName("Test Builder addJobSummary(JobRunSummary)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addJobSummary(JobRunSummary)"})
  void testBuilderAddJobSummary() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();

    // Act and Assert
    assertSame(builderResult,
        builderResult.addJobSummary(new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS))));
  }

  /**
   * Test Builder {@link Builder#addJobSummary(JobRunSummary)}.
   * <p>
   * Method under test: {@link Builder#addJobSummary(JobRunSummary)}
   */
  @Test
  @DisplayName("Test Builder addJobSummary(JobRunSummary)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addJobSummary(JobRunSummary)"})
  void testBuilderAddJobSummary2() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);

    // Act and Assert
    assertSame(builderResult,
        builderResult.addJobSummary(new JobRunSummary(recordsProcessed,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS))));
  }

  /**
   * Test Builder {@link Builder#addJobSummary(JobRunSummary)}.
   * <p>
   * Method under test: {@link Builder#addJobSummary(JobRunSummary)}
   */
  @Test
  @DisplayName("Test Builder addJobSummary(JobRunSummary)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addJobSummary(JobRunSummary)"})
  void testBuilderAddJobSummary3() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();

    // Act and Assert
    assertSame(builderResult,
        builderResult.addJobSummary(new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_TRANSACTION_CHECKS))));
  }

  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#finishTime(Instant)}
   *   <li>{@link Builder#recordsReadPerSecond(double)}
   *   <li>{@link Builder#recordsWrittenPerSecond(double)}
   *   <li>{@link Builder#timeSpentOnJobs(Duration)}
   *   <li>{@link Builder#totalJobRuns(int)}
   *   <li>{@link Builder#totalRecordsRead(long)}
   *   <li>{@link Builder#totalRecordsWritten(long)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionTaskFinishedStatus Builder.build()", "Builder Builder.finishTime(Instant)",
      "Builder Builder.recordsReadPerSecond(double)", "Builder Builder.recordsWrittenPerSecond(double)",
      "Builder Builder.timeSpentOnJobs(Duration)", "Builder Builder.totalJobRuns(int)",
      "Builder Builder.totalRecordsRead(long)", "Builder Builder.totalRecordsWritten(long)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();

    // Act
    CompactionTaskFinishedStatus actualBuildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Assert
    Instant finishTime = actualBuildResult.getFinishTime();
    assertEquals(0, finishTime.getNano());
    assertEquals(0L, finishTime.getEpochSecond());
    assertEquals(1, actualBuildResult.getTotalJobRuns());
    assertEquals(10.0d, actualBuildResult.getRecordsReadPerSecond());
    assertEquals(10.0d, actualBuildResult.getRecordsWrittenPerSecond());
    assertEquals(1L, actualBuildResult.getTotalRecordsRead());
    assertEquals(1L, actualBuildResult.getTotalRecordsWritten());
    assertEquals(60000000000L, actualBuildResult.getTimeSpentOnJobs().toNanos());
  }

  /**
   * Test Builder {@link Builder#finish(Instant)}.
   * <p>
   * Method under test: {@link Builder#finish(Instant)}
   */
  @Test
  @DisplayName("Test Builder finish(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.finish(Instant)"})
  void testBuilderFinish() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    Instant finishTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    Builder actualFinishResult = builderResult.finish(finishTime);

    // Assert
    CompactionTaskFinishedStatus buildResult = builderResult.build();
    assertEquals(0, buildResult.getTotalJobRuns());
    assertEquals(0L, buildResult.getTotalRecordsRead());
    assertEquals(0L, buildResult.getTotalRecordsWritten());
    assertEquals(Double.NaN, buildResult.getRecordsReadPerSecond());
    assertEquals(Double.NaN, buildResult.getRecordsWrittenPerSecond());
    assertSame(builderResult, actualFinishResult);
    Instant expectedFinishTime = finishTime.EPOCH;
    assertSame(expectedFinishTime, buildResult.getFinishTime());
  }

  /**
   * Test Builder {@link Builder#jobSummaries(Stream)}.
   * <p>
   * Method under test: {@link Builder#jobSummaries(Stream)}
   */
  @Test
  @DisplayName("Test Builder jobSummaries(Stream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.jobSummaries(Stream)"})
  void testBuilderJobSummaries() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();

    ArrayList<JobRunSummary> jobRunSummaryList = new ArrayList<>();
    jobRunSummaryList.add(new JobRunSummary(RecordsProcessed.NONE,
        new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));
    Stream<JobRunSummary> jobSummaries = jobRunSummaryList.stream();

    // Act and Assert
    assertSame(builderResult, builderResult.jobSummaries(jobSummaries));
  }

  /**
   * Test Builder {@link Builder#jobSummaries(Stream)}.
   * <p>
   * Method under test: {@link Builder#jobSummaries(Stream)}
   */
  @Test
  @DisplayName("Test Builder jobSummaries(Stream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.jobSummaries(Stream)"})
  void testBuilderJobSummaries2() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();

    ArrayList<JobRunSummary> jobRunSummaryList = new ArrayList<>();
    jobRunSummaryList.add(new JobRunSummary(RecordsProcessed.NONE,
        new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));
    jobRunSummaryList.add(new JobRunSummary(RecordsProcessed.NONE,
        new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));
    Stream<JobRunSummary> jobSummaries = jobRunSummaryList.stream();

    // Act and Assert
    assertSame(builderResult, builderResult.jobSummaries(jobSummaries));
  }

  /**
   * Test Builder {@link Builder#jobSummaries(Stream)}.
   * <p>
   * Method under test: {@link Builder#jobSummaries(Stream)}
   */
  @Test
  @DisplayName("Test Builder jobSummaries(Stream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.jobSummaries(Stream)"})
  void testBuilderJobSummaries3() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();

    ArrayList<JobRunSummary> jobRunSummaryList = new ArrayList<>();
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);

    jobRunSummaryList.add(new JobRunSummary(recordsProcessed,
        new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));
    Stream<JobRunSummary> jobSummaries = jobRunSummaryList.stream();

    // Act and Assert
    assertSame(builderResult, builderResult.jobSummaries(jobSummaries));
  }

  /**
   * Test Builder {@link Builder#jobSummaries(Stream)}.
   * <p>
   * Method under test: {@link Builder#jobSummaries(Stream)}
   */
  @Test
  @DisplayName("Test Builder jobSummaries(Stream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.jobSummaries(Stream)"})
  void testBuilderJobSummaries4() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();

    ArrayList<JobRunSummary> jobRunSummaryList = new ArrayList<>();
    jobRunSummaryList.add(new JobRunSummary(RecordsProcessed.NONE,
        new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_TRANSACTION_CHECKS)));
    Stream<JobRunSummary> jobSummaries = jobRunSummaryList.stream();

    // Act and Assert
    assertSame(builderResult, builderResult.jobSummaries(jobSummaries));
  }

  /**
   * Test Builder {@link Builder#jobSummaries(Stream)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#jobSummaries(Stream)}
   */
  @Test
  @DisplayName("Test Builder jobSummaries(Stream); when ArrayList() stream; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.jobSummaries(Stream)"})
  void testBuilderJobSummaries_whenArrayListStream_thenReturnBuilder() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();

    ArrayList<JobRunSummary> jobRunSummaryList = new ArrayList<>();
    Stream<JobRunSummary> jobSummaries = jobRunSummaryList.stream();

    // Act and Assert
    assertSame(builderResult, builderResult.jobSummaries(jobSummaries));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionTaskFinishedStatus#toString()}
   *   <li>{@link CompactionTaskFinishedStatus#getFinishTime()}
   *   <li>{@link CompactionTaskFinishedStatus#getRecordsReadPerSecond()}
   *   <li>{@link CompactionTaskFinishedStatus#getRecordsWrittenPerSecond()}
   *   <li>{@link CompactionTaskFinishedStatus#getTimeSpentOnJobs()}
   *   <li>{@link CompactionTaskFinishedStatus#getTotalJobRuns()}
   *   <li>{@link CompactionTaskFinishedStatus#getTotalRecordsRead()}
   *   <li>{@link CompactionTaskFinishedStatus#getTotalRecordsWritten()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant CompactionTaskFinishedStatus.getFinishTime()",
      "double CompactionTaskFinishedStatus.getRecordsReadPerSecond()",
      "double CompactionTaskFinishedStatus.getRecordsWrittenPerSecond()",
      "Duration CompactionTaskFinishedStatus.getTimeSpentOnJobs()",
      "int CompactionTaskFinishedStatus.getTotalJobRuns()", "long CompactionTaskFinishedStatus.getTotalRecordsRead()",
      "long CompactionTaskFinishedStatus.getTotalRecordsWritten()", "String CompactionTaskFinishedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    Instant actualFinishTime = buildResult.getFinishTime();
    double actualRecordsReadPerSecond = buildResult.getRecordsReadPerSecond();
    double actualRecordsWrittenPerSecond = buildResult.getRecordsWrittenPerSecond();
    Duration actualTimeSpentOnJobs = buildResult.getTimeSpentOnJobs();
    int actualTotalJobRuns = buildResult.getTotalJobRuns();
    long actualTotalRecordsRead = buildResult.getTotalRecordsRead();

    // Assert
    assertEquals(
        "CompactionTaskFinishedStatus{finishTime=1970-01-01T00:00:00Z, totalJobs=1, timeSpentOnJobs=PT1M,"
            + " totalRecordsRead=1, totalRecordsWritten=1, recordsReadPerSecond=10.0, recordsWrittenPerSecond=10.0}",
        actualToStringResult);
    assertEquals(1, actualTotalJobRuns);
    assertEquals(10.0d, actualRecordsReadPerSecond);
    assertEquals(10.0d, actualRecordsWrittenPerSecond);
    assertEquals(1L, actualTotalRecordsRead);
    assertEquals(1L, buildResult.getTotalRecordsWritten());
    assertEquals(60000000000L, actualTimeSpentOnJobs.toNanos());
    assertSame(actualFinishTime.EPOCH, actualFinishTime);
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#asSummary(Instant)}.
   * <p>
   * Method under test: {@link CompactionTaskFinishedStatus#asSummary(Instant)}
   */
  @Test
  @DisplayName("Test asSummary(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunSummary CompactionTaskFinishedStatus.asSummary(Instant)"})
  void testAsSummary() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    JobRunSummary actualAsSummaryResult = buildResult.asSummary(startTime);

    // Assert
    assertEquals(0.016666666666666666d, actualAsSummaryResult.getRecordsReadPerSecond());
    assertEquals(0.016666666666666666d, actualAsSummaryResult.getRecordsWrittenPerSecond());
    assertEquals(0.0d, actualAsSummaryResult.getDurationInSeconds());
    assertEquals(1L, actualAsSummaryResult.getRecordsRead());
    assertEquals(1L, actualAsSummaryResult.getRecordsWritten());
    Instant instant = startTime.EPOCH;
    assertSame(instant, actualAsSummaryResult.getFinishTime());
    assertSame(instant, actualAsSummaryResult.getStartTime());
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}, and {@link CompactionTaskFinishedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionTaskFinishedStatus#equals(Object)}
   *   <li>{@link CompactionTaskFinishedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}, and {@link CompactionTaskFinishedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionTaskFinishedStatus#equals(Object)}
   *   <li>{@link CompactionTaskFinishedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(0.5d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(0.5d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_TRANSACTION_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(0)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(0L)
        .totalRecordsWritten(1L)
        .build();
    Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(0L)
        .build();
    Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CompactionTaskFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskFinishedStatus.equals(Object)",
      "int CompactionTaskFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus buildResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CompactionTaskFinishedStatus");
  }
}
