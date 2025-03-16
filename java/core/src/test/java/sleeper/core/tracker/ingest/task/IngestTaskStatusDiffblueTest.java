package sleeper.core.tracker.ingest.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import sleeper.core.tracker.ingest.task.IngestTaskStatus.Builder;

class IngestTaskStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#expiryDate(Instant)}
   *   <li>{@link Builder#finishedStatus(IngestTaskFinishedStatus)}
   *   <li>{@link Builder#startTime(Instant)}
   *   <li>{@link Builder#taskId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskStatus Builder.build()", "Builder Builder.expiryDate(Instant)",
      "Builder Builder.finishedStatus(IngestTaskFinishedStatus)", "Builder Builder.startTime(Instant)",
      "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
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
    IngestTaskStatus actualBuildResult = finishedStatusResult
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
    IngestTaskFinishedStatus finishedStatus2 = actualBuildResult.getFinishedStatus();
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
   * Test Builder {@link Builder#finished(Instant, Builder)}.
   * <ul>
   *   <li>When builder.</li>
   *   <li>Then builder build TotalJobRuns is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#finished(Instant, IngestTaskFinishedStatus.Builder)}
   */
  @Test
  @DisplayName("Test Builder finished(Instant, Builder); when builder; then builder build TotalJobRuns is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.finished(Instant, IngestTaskFinishedStatus.Builder)"})
  void testBuilderFinished_whenBuilder_thenBuilderBuildTotalJobRunsIsZero() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Instant finishTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    IngestTaskFinishedStatus.Builder taskFinishedBuilder = IngestTaskFinishedStatus.builder();

    // Act
    Builder actualFinishedResult = builderResult.finished(finishTime, taskFinishedBuilder);

    // Assert
    IngestTaskFinishedStatus buildResult = taskFinishedBuilder.build();
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
   * Test Builder {@link Builder#startTime(long)} with {@code long}.
   * <p>
   * Method under test: {@link Builder#startTime(long)}
   */
  @Test
  @DisplayName("Test Builder startTime(long) with 'long'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.startTime(long)"})
  void testBuilderStartTimeWithLong() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.startTime(1L));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestTaskStatus#toString()}
   *   <li>{@link IngestTaskStatus#getExpiryDate()}
   *   <li>{@link IngestTaskStatus#getFinishedStatus()}
   *   <li>{@link IngestTaskStatus#getStartTime()}
   *   <li>{@link IngestTaskStatus#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant IngestTaskStatus.getExpiryDate()",
      "IngestTaskFinishedStatus IngestTaskStatus.getFinishedStatus()", "Instant IngestTaskStatus.getStartTime()",
      "String IngestTaskStatus.getTaskId()", "String IngestTaskStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    IngestTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    Instant actualExpiryDate = buildResult.getExpiryDate();
    IngestTaskFinishedStatus actualFinishedStatus = buildResult.getFinishedStatus();
    Instant actualStartTime = buildResult.getStartTime();

    // Assert
    assertEquals("42", buildResult.getTaskId());
    assertEquals("IngestTaskStatus{taskId='42', startTime=1970-01-01T00:00:00Z, finishedStatus=IngestTaskFinishedStatus"
        + "{finishTime=1970-01-01T00:00:00Z, totalJobs=1, timeSpentOnJobs=PT1M, totalRecordsRead=1,"
        + " totalRecordsWritten=1, recordsReadPerSecond=10.0, recordsWrittenPerSecond=10.0}, expiryDate=1970"
        + "-01-01T00:00:00Z}", actualToStringResult);
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
   * Test {@link IngestTaskStatus#equals(Object)}, and {@link IngestTaskStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestTaskStatus#equals(Object)}
   *   <li>{@link IngestTaskStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStatus.equals(Object)", "int IngestTaskStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    IngestTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();
    Builder builderResult3 = IngestTaskStatus.builder();
    Builder expiryDateResult2 = builderResult3
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult4 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus2 = builderResult4
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult2 = expiryDateResult2.finishedStatus(finishedStatus2);
    IngestTaskStatus buildResult2 = finishedStatusResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestTaskStatus#equals(Object)}, and {@link IngestTaskStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestTaskStatus#equals(Object)}
   *   <li>{@link IngestTaskStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStatus.equals(Object)", "int IngestTaskStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    IngestTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStatus.equals(Object)", "int IngestTaskStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    IngestTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();
    Builder builderResult3 = IngestTaskStatus.builder();
    Builder expiryDateResult2 = builderResult3
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult4 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus2 = builderResult4
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult2 = expiryDateResult2.finishedStatus(finishedStatus2);
    IngestTaskStatus buildResult2 = finishedStatusResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStatus.equals(Object)", "int IngestTaskStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    IngestTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();
    Builder builderResult3 = IngestTaskStatus.builder();
    Builder expiryDateResult2 = builderResult3
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult4 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus2 = builderResult4
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult2 = expiryDateResult2.finishedStatus(finishedStatus2);
    IngestTaskStatus buildResult2 = finishedStatusResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStatus.equals(Object)", "int IngestTaskStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    IngestTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();
    Builder builderResult3 = IngestTaskStatus.builder();
    Builder expiryDateResult2 = builderResult3
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult4 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus2 = builderResult4
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult2 = expiryDateResult2.finishedStatus(finishedStatus2);
    IngestTaskStatus buildResult2 = finishedStatusResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStatus.equals(Object)", "int IngestTaskStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    IngestTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("Task Id")
        .build();
    Builder builderResult3 = IngestTaskStatus.builder();
    Builder expiryDateResult2 = builderResult3
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult4 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus2 = builderResult4
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult2 = expiryDateResult2.finishedStatus(finishedStatus2);
    IngestTaskStatus buildResult2 = finishedStatusResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStatus.equals(Object)", "int IngestTaskStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    IngestTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestTaskStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStatus.equals(Object)", "int IngestTaskStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    IngestTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestTaskStatus");
  }
}
