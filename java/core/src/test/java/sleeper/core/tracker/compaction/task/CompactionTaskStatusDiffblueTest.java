package sleeper.core.tracker.compaction.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus.Builder;

class CompactionTaskStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#expiryDate(Instant)}
   *   <li>{@link Builder#finishedStatus(CompactionTaskFinishedStatus)}
   *   <li>{@link Builder#startTime(Instant)}
   *   <li>{@link Builder#taskId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionTaskStatus Builder.build()", "Builder Builder.expiryDate(Instant)",
      "Builder Builder.finishedStatus(CompactionTaskFinishedStatus)", "String Builder.getTaskId()",
      "Builder Builder.startTime(Instant)", "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);

    // Act
    CompactionTaskStatus actualBuildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getTaskId());
    Instant expiryDate = actualBuildResult.getExpiryDate();
    assertEquals(0, expiryDate.getNano());
    assertEquals(0L, actualBuildResult.getDuration().toNanos());
    assertEquals(0L, expiryDate.getEpochSecond());
    assertEquals(1, actualBuildResult.getJobRunsOrNull().intValue());
    CompactionTaskFinishedStatus finishedStatus2 = actualBuildResult.getFinishedStatus();
    assertEquals(1, finishedStatus2.getTotalJobRuns());
    assertEquals(1, actualBuildResult.getJobRuns());
    assertEquals(10.0d, finishedStatus2.getRecordsReadPerSecond());
    assertEquals(10.0d, finishedStatus2.getRecordsWrittenPerSecond());
    assertEquals(1L, finishedStatus2.getTotalRecordsRead());
    assertEquals(1L, finishedStatus2.getTotalRecordsWritten());
    assertEquals(60000000000L, finishedStatus2.getTimeSpentOnJobs().toNanos());
    assertTrue(actualBuildResult.isFinished());
  }

  /**
   * Test Builder {@link Builder#builder()}.
   * <p>
   * Method under test: {@link Builder#builder()}
   */
  @Test
  @DisplayName("Test Builder builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.builder()"})
  void testBuilderBuilder() {
    // Arrange, Act and Assert
    assertNull(Builder.builder().getTaskId());
  }

  /**
   * Test Builder {@link Builder#finished(Instant, Builder)}.
   * <ul>
   *   <li>When builder.</li>
   *   <li>Then builder build TotalJobRuns is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#finished(Instant, CompactionTaskFinishedStatus.Builder)}
   */
  @Test
  @DisplayName("Test Builder finished(Instant, Builder); when builder; then builder build TotalJobRuns is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.finished(Instant, CompactionTaskFinishedStatus.Builder)"})
  void testBuilderFinished_whenBuilder_thenBuilderBuildTotalJobRunsIsZero() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Instant finishTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionTaskFinishedStatus.Builder taskFinishedBuilder = CompactionTaskFinishedStatus.builder();

    // Act
    Builder actualFinishedResult = builderResult.finished(finishTime, taskFinishedBuilder);

    // Assert
    CompactionTaskFinishedStatus buildResult = taskFinishedBuilder.build();
    assertEquals(0, buildResult.getTotalJobRuns());
    assertEquals(0L, buildResult.getTimeSpentOnJobs().toNanos());
    assertEquals(0L, buildResult.getTotalRecordsRead());
    assertEquals(0L, buildResult.getTotalRecordsWritten());
    assertEquals(Double.NaN, buildResult.getRecordsReadPerSecond());
    assertEquals(Double.NaN, buildResult.getRecordsWrittenPerSecond());
    assertSame(builderResult, actualFinishedResult);
    Instant expectedFinishTime = finishTime.EPOCH;
    assertSame(expectedFinishTime, buildResult.getFinishTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionTaskStatus#toString()}
   *   <li>{@link CompactionTaskStatus#getExpiryDate()}
   *   <li>{@link CompactionTaskStatus#getFinishedStatus()}
   *   <li>{@link CompactionTaskStatus#getStartTime()}
   *   <li>{@link CompactionTaskStatus#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant CompactionTaskStatus.getExpiryDate()",
      "CompactionTaskFinishedStatus CompactionTaskStatus.getFinishedStatus()",
      "Instant CompactionTaskStatus.getStartTime()", "String CompactionTaskStatus.getTaskId()",
      "String CompactionTaskStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    CompactionTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    Instant actualExpiryDate = buildResult.getExpiryDate();
    CompactionTaskFinishedStatus actualFinishedStatus = buildResult.getFinishedStatus();
    Instant actualStartTime = buildResult.getStartTime();

    // Assert
    assertEquals("42", buildResult.getTaskId());
    assertEquals(
        "CompactionTaskStatus{taskId='42', startTime=1970-01-01T00:00:00Z, finishedStatus=CompactionTaskFinishedStatus"
            + "{finishTime=1970-01-01T00:00:00Z, totalJobs=1, timeSpentOnJobs=PT1M, totalRecordsRead=1, totalRecordsWritten"
            + "=1, recordsReadPerSecond=10.0, recordsWrittenPerSecond=10.0}, expiryDate=1970-01-01T00:00:00Z}",
        actualToStringResult);
    assertEquals(1, actualFinishedStatus.getTotalJobRuns());
    assertEquals(10.0d, actualFinishedStatus.getRecordsReadPerSecond());
    assertEquals(10.0d, actualFinishedStatus.getRecordsWrittenPerSecond());
    assertEquals(1L, actualFinishedStatus.getTotalRecordsRead());
    assertEquals(1L, actualFinishedStatus.getTotalRecordsWritten());
    assertEquals(60000000000L, actualFinishedStatus.getTimeSpentOnJobs().toNanos());
    Instant instant = actualStartTime.EPOCH;
    assertSame(instant, actualFinishedStatus.getFinishTime());
    assertSame(instant, actualExpiryDate);
    assertSame(instant, actualStartTime);
  }

  /**
   * Test {@link CompactionTaskStatus#equals(Object)}, and {@link CompactionTaskStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionTaskStatus#equals(Object)}
   *   <li>{@link CompactionTaskStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStatus.equals(Object)", "int CompactionTaskStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    CompactionTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();
    Builder builderResult3 = CompactionTaskStatus.builder();
    Builder expiryDateResult2 = builderResult3
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult4 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus2 = builderResult4
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult2 = expiryDateResult2.finishedStatus(finishedStatus2);
    CompactionTaskStatus buildResult2 = finishedStatusResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link CompactionTaskStatus#equals(Object)}, and {@link CompactionTaskStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionTaskStatus#equals(Object)}
   *   <li>{@link CompactionTaskStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStatus.equals(Object)", "int CompactionTaskStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    CompactionTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link CompactionTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStatus.equals(Object)", "int CompactionTaskStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    CompactionTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();
    Builder builderResult3 = CompactionTaskStatus.builder();
    Builder expiryDateResult2 = builderResult3
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult4 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus2 = builderResult4
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult2 = expiryDateResult2.finishedStatus(finishedStatus2);
    CompactionTaskStatus buildResult2 = finishedStatusResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStatus.equals(Object)", "int CompactionTaskStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    CompactionTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();
    Builder builderResult3 = CompactionTaskStatus.builder();
    Builder expiryDateResult2 = builderResult3
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult4 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus2 = builderResult4
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult2 = expiryDateResult2.finishedStatus(finishedStatus2);
    CompactionTaskStatus buildResult2 = finishedStatusResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStatus.equals(Object)", "int CompactionTaskStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    CompactionTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();
    Builder builderResult3 = CompactionTaskStatus.builder();
    Builder expiryDateResult2 = builderResult3
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult4 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus2 = builderResult4
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult2 = expiryDateResult2.finishedStatus(finishedStatus2);
    CompactionTaskStatus buildResult2 = finishedStatusResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStatus.equals(Object)", "int CompactionTaskStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    CompactionTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("Task Id")
        .build();
    Builder builderResult3 = CompactionTaskStatus.builder();
    Builder expiryDateResult2 = builderResult3
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult4 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus2 = builderResult4
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult2 = expiryDateResult2.finishedStatus(finishedStatus2);
    CompactionTaskStatus buildResult2 = finishedStatusResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStatus.equals(Object)", "int CompactionTaskStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    CompactionTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CompactionTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStatus.equals(Object)", "int CompactionTaskStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    CompactionTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CompactionTaskStatus");
  }
}
