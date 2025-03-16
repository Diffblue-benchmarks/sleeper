package sleeper.core.tracker.job.run;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.tracker.job.run.AverageRecordRate.Builder;

class AverageRecordRateDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#finishTime(Instant)}
   *   <li>{@link Builder#startTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AverageRecordRate Builder.build()", "Builder Builder.finishTime(Instant)",
      "Builder Builder.startTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = AverageRecordRate.builder();
    Builder finishTimeResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    AverageRecordRate actualBuildResult = finishTimeResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Assert
    assertEquals(0, actualBuildResult.getRunCount());
    assertEquals(0L, actualBuildResult.getTotalDuration().toNanos());
    assertEquals(0L, actualBuildResult.getRecordsRead());
    assertEquals(0L, actualBuildResult.getRecordsWritten());
    assertEquals(Double.NaN, actualBuildResult.getAverageRunRecordsReadPerSecond());
    assertEquals(Double.NaN, actualBuildResult.getAverageRunRecordsWrittenPerSecond());
    assertEquals(Double.NaN, actualBuildResult.getRecordsReadPerSecond());
    assertEquals(Double.NaN, actualBuildResult.getRecordsWrittenPerSecond());
  }

  /**
   * Test Builder {@link Builder#summaries(Stream)}.
   * <ul>
   *   <li>Then return build RecordsReadPerSecond is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#summaries(Stream)}
   */
  @Test
  @DisplayName("Test Builder summaries(Stream); then return build RecordsReadPerSecond is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.summaries(Stream)"})
  void testBuilderSummaries_thenReturnBuildRecordsReadPerSecondIsZero() {
    // Arrange
    Builder builderResult = AverageRecordRate.builder();

    ArrayList<JobRunSummary> jobRunSummaryList = new ArrayList<>();
    jobRunSummaryList.add(JobRunSummary
        .noRecordsProcessed(new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));
    Stream<JobRunSummary> summaries = jobRunSummaryList.stream();

    // Act and Assert
    AverageRecordRate buildResult = builderResult.summaries(summaries).build();
    assertEquals(0.0d, buildResult.getRecordsReadPerSecond());
    AverageRecordRate buildResult2 = builderResult.build();
    assertEquals(0.0d, buildResult2.getRecordsReadPerSecond());
    assertEquals(0.0d, buildResult.getRecordsWrittenPerSecond());
    assertEquals(0.0d, buildResult2.getRecordsWrittenPerSecond());
    assertEquals(60000000000L, buildResult.getTotalDuration().toNanos());
  }

  /**
   * Test Builder {@link Builder#summaries(Stream)}.
   * <ul>
   *   <li>Then return build RunCount is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#summaries(Stream)}
   */
  @Test
  @DisplayName("Test Builder summaries(Stream); then return build RunCount is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.summaries(Stream)"})
  void testBuilderSummaries_thenReturnBuildRunCountIsOne() {
    // Arrange
    Builder builderResult = AverageRecordRate.builder();

    ArrayList<JobRunSummary> jobRunSummaryList = new ArrayList<>();
    jobRunSummaryList.add(JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Stream<JobRunSummary> summaries = jobRunSummaryList.stream();

    // Act and Assert
    AverageRecordRate buildResult = builderResult.summaries(summaries).build();
    assertEquals(0L, buildResult.getTotalDuration().toNanos());
    assertEquals(1, buildResult.getRunCount());
    AverageRecordRate buildResult2 = builderResult.build();
    assertEquals(1, buildResult2.getRunCount());
    assertEquals(Double.NaN, buildResult.getRecordsReadPerSecond());
    assertEquals(Double.NaN, buildResult2.getRecordsReadPerSecond());
    assertEquals(Double.NaN, buildResult.getRecordsWrittenPerSecond());
    assertEquals(Double.NaN, buildResult2.getRecordsWrittenPerSecond());
  }

  /**
   * Test Builder {@link Builder#summaries(Stream)}.
   * <ul>
   *   <li>Then return build RunCount is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#summaries(Stream)}
   */
  @Test
  @DisplayName("Test Builder summaries(Stream); then return build RunCount is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.summaries(Stream)"})
  void testBuilderSummaries_thenReturnBuildRunCountIsTwo() {
    // Arrange
    Builder builderResult = AverageRecordRate.builder();

    ArrayList<JobRunSummary> jobRunSummaryList = new ArrayList<>();
    jobRunSummaryList.add(JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    jobRunSummaryList.add(JobRunSummary
        .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Stream<JobRunSummary> summaries = jobRunSummaryList.stream();

    // Act and Assert
    AverageRecordRate buildResult = builderResult.summaries(summaries).build();
    assertEquals(0L, buildResult.getTotalDuration().toNanos());
    assertEquals(2, buildResult.getRunCount());
    AverageRecordRate buildResult2 = builderResult.build();
    assertEquals(2, buildResult2.getRunCount());
    assertEquals(Double.NaN, buildResult.getRecordsReadPerSecond());
    assertEquals(Double.NaN, buildResult2.getRecordsReadPerSecond());
    assertEquals(Double.NaN, buildResult.getRecordsWrittenPerSecond());
    assertEquals(Double.NaN, buildResult2.getRecordsWrittenPerSecond());
  }

  /**
   * Test Builder {@link Builder#summaries(Stream)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then return build RunCount is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#summaries(Stream)}
   */
  @Test
  @DisplayName("Test Builder summaries(Stream); when ArrayList() stream; then return build RunCount is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.summaries(Stream)"})
  void testBuilderSummaries_whenArrayListStream_thenReturnBuildRunCountIsZero() {
    // Arrange
    Builder builderResult = AverageRecordRate.builder();

    ArrayList<JobRunSummary> jobRunSummaryList = new ArrayList<>();
    Stream<JobRunSummary> summaries = jobRunSummaryList.stream();

    // Act and Assert
    AverageRecordRate buildResult = builderResult.summaries(summaries).build();
    assertEquals(0, buildResult.getRunCount());
    AverageRecordRate buildResult2 = builderResult.build();
    assertEquals(0, buildResult2.getRunCount());
    assertEquals(0L, buildResult.getTotalDuration().toNanos());
    assertEquals(Double.NaN, buildResult.getRecordsReadPerSecond());
    assertEquals(Double.NaN, buildResult2.getRecordsReadPerSecond());
    assertEquals(Double.NaN, buildResult.getRecordsWrittenPerSecond());
    assertEquals(Double.NaN, buildResult2.getRecordsWrittenPerSecond());
  }

  /**
   * Test Builder {@link Builder#summary(JobRunSummary)}.
   * <ul>
   *   <li>Then return build RecordsReadPerSecond is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#summary(JobRunSummary)}
   */
  @Test
  @DisplayName("Test Builder summary(JobRunSummary); then return build RecordsReadPerSecond is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.summary(JobRunSummary)"})
  void testBuilderSummary_thenReturnBuildRecordsReadPerSecondIsZero() {
    // Arrange
    Builder builderResult = AverageRecordRate.builder();

    // Act and Assert
    AverageRecordRate buildResult = builderResult.summary(JobRunSummary
        .noRecordsProcessed(new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .build();
    assertEquals(0.0d, buildResult.getRecordsReadPerSecond());
    AverageRecordRate buildResult2 = builderResult.build();
    assertEquals(0.0d, buildResult2.getRecordsReadPerSecond());
    assertEquals(0.0d, buildResult.getRecordsWrittenPerSecond());
    assertEquals(0.0d, buildResult2.getRecordsWrittenPerSecond());
    assertEquals(60000000000L, buildResult.getTotalDuration().toNanos());
  }

  /**
   * Test Builder {@link Builder#summary(JobRunSummary)}.
   * <ul>
   *   <li>Then return build TotalDuration toNanos is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#summary(JobRunSummary)}
   */
  @Test
  @DisplayName("Test Builder summary(JobRunSummary); then return build TotalDuration toNanos is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.summary(JobRunSummary)"})
  void testBuilderSummary_thenReturnBuildTotalDurationToNanosIsZero() {
    // Arrange
    Builder builderResult = AverageRecordRate.builder();

    // Act and Assert
    AverageRecordRate buildResult = builderResult
        .summary(JobRunSummary
            .noProcessingDoneAtTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .build();
    assertEquals(0L, buildResult.getTotalDuration().toNanos());
    assertEquals(Double.NaN, buildResult.getRecordsReadPerSecond());
    AverageRecordRate buildResult2 = builderResult.build();
    assertEquals(Double.NaN, buildResult2.getRecordsReadPerSecond());
    assertEquals(Double.NaN, buildResult.getRecordsWrittenPerSecond());
    assertEquals(Double.NaN, buildResult2.getRecordsWrittenPerSecond());
  }

  /**
   * Test {@link AverageRecordRate#of(Stream)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then return RunCount is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AverageRecordRate#of(Stream)}
   */
  @Test
  @DisplayName("Test of(Stream); when ArrayList() stream; then return RunCount is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AverageRecordRate AverageRecordRate.of(Stream)"})
  void testOf_whenArrayListStream_thenReturnRunCountIsZero() {
    // Arrange
    ArrayList<JobRunReport> jobRunReportList = new ArrayList<>();
    Stream<JobRunReport> runs = jobRunReportList.stream();

    // Act
    AverageRecordRate actualOfResult = AverageRecordRate.of(runs);

    // Assert
    assertEquals(0, actualOfResult.getRunCount());
    assertEquals(0L, actualOfResult.getTotalDuration().toNanos());
    assertEquals(0L, actualOfResult.getRecordsRead());
    assertEquals(0L, actualOfResult.getRecordsWritten());
    assertEquals(Double.NaN, actualOfResult.getAverageRunRecordsReadPerSecond());
    assertEquals(Double.NaN, actualOfResult.getAverageRunRecordsWrittenPerSecond());
    assertEquals(Double.NaN, actualOfResult.getRecordsReadPerSecond());
    assertEquals(Double.NaN, actualOfResult.getRecordsWrittenPerSecond());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AverageRecordRate#toString()}
   *   <li>{@link AverageRecordRate#getAverageRunRecordsReadPerSecond()}
   *   <li>{@link AverageRecordRate#getAverageRunRecordsWrittenPerSecond()}
   *   <li>{@link AverageRecordRate#getRecordsRead()}
   *   <li>{@link AverageRecordRate#getRecordsReadPerSecond()}
   *   <li>{@link AverageRecordRate#getRecordsWritten()}
   *   <li>{@link AverageRecordRate#getRecordsWrittenPerSecond()}
   *   <li>{@link AverageRecordRate#getRunCount()}
   *   <li>{@link AverageRecordRate#getTotalDuration()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double AverageRecordRate.getAverageRunRecordsReadPerSecond()",
      "double AverageRecordRate.getAverageRunRecordsWrittenPerSecond()", "long AverageRecordRate.getRecordsRead()",
      "double AverageRecordRate.getRecordsReadPerSecond()", "long AverageRecordRate.getRecordsWritten()",
      "double AverageRecordRate.getRecordsWrittenPerSecond()", "int AverageRecordRate.getRunCount()",
      "java.time.Duration AverageRecordRate.getTotalDuration()", "String AverageRecordRate.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = AverageRecordRate.builder();
    Builder finishTimeResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AverageRecordRate buildResult = finishTimeResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    double actualAverageRunRecordsReadPerSecond = buildResult.getAverageRunRecordsReadPerSecond();
    double actualAverageRunRecordsWrittenPerSecond = buildResult.getAverageRunRecordsWrittenPerSecond();
    long actualRecordsRead = buildResult.getRecordsRead();
    double actualRecordsReadPerSecond = buildResult.getRecordsReadPerSecond();
    long actualRecordsWritten = buildResult.getRecordsWritten();
    double actualRecordsWrittenPerSecond = buildResult.getRecordsWrittenPerSecond();
    int actualRunCount = buildResult.getRunCount();

    // Assert
    assertEquals(
        "AverageRecordRate{runCount=0, recordsRead=0, recordsWritten=0, totalDuration=PT0S, recordsReadPerSecond=NaN,"
            + " recordsWrittenPerSecond=NaN, averageJobRecordsReadPerSecond=NaN, averageJobRecordsWrittenPerSecond"
            + "=NaN}",
        actualToStringResult);
    assertEquals(0, actualRunCount);
    assertEquals(0L, buildResult.getTotalDuration().toNanos());
    assertEquals(0L, actualRecordsRead);
    assertEquals(0L, actualRecordsWritten);
    assertEquals(Double.NaN, actualAverageRunRecordsReadPerSecond);
    assertEquals(Double.NaN, actualAverageRunRecordsWrittenPerSecond);
    assertEquals(Double.NaN, actualRecordsReadPerSecond);
    assertEquals(Double.NaN, actualRecordsWrittenPerSecond);
  }
}
