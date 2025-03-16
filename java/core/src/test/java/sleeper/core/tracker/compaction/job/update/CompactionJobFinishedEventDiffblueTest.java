package sleeper.core.tracker.compaction.job.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent.Builder;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.JobRunTime;
import sleeper.core.tracker.job.run.RecordsProcessed;

class CompactionJobFinishedEventDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#jobRunId(String)}
   *   <li>{@link Builder#summary(JobRunSummary)}
   *   <li>{@link Builder#tableId(String)}
   *   <li>{@link Builder#taskId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobFinishedEvent Builder.build()", "Builder Builder.jobId(String)",
      "Builder Builder.jobRunId(String)", "Builder Builder.summary(JobRunSummary)", "Builder Builder.tableId(String)",
      "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");

    // Act
    CompactionJobFinishedEvent actualBuildResult = jobRunIdResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getJobId());
    assertEquals("42", actualBuildResult.getJobRunId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("42", actualBuildResult.getTaskId());
    Instant finishTime = actualBuildResult.getFinishTime();
    assertEquals(0, finishTime.getNano());
    RecordsProcessed recordsProcessed = actualBuildResult.getRecordsProcessed();
    assertEquals(0L, recordsProcessed.getRecordsRead());
    assertEquals(0L, recordsProcessed.getRecordsWritten());
    assertEquals(60L, finishTime.getEpochSecond());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobFinishedEvent#toString()}
   *   <li>{@link CompactionJobFinishedEvent#getJobId()}
   *   <li>{@link CompactionJobFinishedEvent#getJobRunId()}
   *   <li>{@link CompactionJobFinishedEvent#getTableId()}
   *   <li>{@link CompactionJobFinishedEvent#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobFinishedEvent.getJobId()", "String CompactionJobFinishedEvent.getJobRunId()",
      "String CompactionJobFinishedEvent.getTableId()", "String CompactionJobFinishedEvent.getTaskId()",
      "String CompactionJobFinishedEvent.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    String actualTableId = buildResult.getTableId();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", actualTableId);
    assertEquals("42", buildResult.getTaskId());
    assertEquals("CompactionJobFinishedEvent{jobId=42, tableId=42, taskId=42, jobRunId=42, summary=JobRunSummary"
        + "{recordsProcessed=RecordsProcessed{recordsRead=0, recordsWritten=0}, runTime=JobRunTime{startTime=1970"
        + "-01-01T00:00:00Z, endTime=1970-01-01T00:01:00Z, timeInProcess=PT1M}, recordsReadPerSecond=0.0,"
        + " recordsWrittenPerSecond=0.0}}", actualToStringResult);
  }

  /**
   * Test {@link CompactionJobFinishedEvent#getFinishTime()}.
   * <p>
   * Method under test: {@link CompactionJobFinishedEvent#getFinishTime()}
   */
  @Test
  @DisplayName("Test getFinishTime()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant CompactionJobFinishedEvent.getFinishTime()"})
  void testGetFinishTime() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    Instant actualFinishTime = buildResult.getFinishTime();

    // Assert
    assertEquals(0, actualFinishTime.getNano());
    assertEquals(60L, actualFinishTime.getEpochSecond());
  }

  /**
   * Test {@link CompactionJobFinishedEvent#getRecordsProcessed()}.
   * <p>
   * Method under test: {@link CompactionJobFinishedEvent#getRecordsProcessed()}
   */
  @Test
  @DisplayName("Test getRecordsProcessed()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RecordsProcessed CompactionJobFinishedEvent.getRecordsProcessed()"})
  void testGetRecordsProcessed() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    RecordsProcessed actualRecordsProcessed = buildResult.getRecordsProcessed();

    // Assert
    assertSame(actualRecordsProcessed.NONE, actualRecordsProcessed);
  }

  /**
   * Test {@link CompactionJobFinishedEvent#equals(Object)}, and {@link CompactionJobFinishedEvent#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobFinishedEvent#equals(Object)}
   *   <li>{@link CompactionJobFinishedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedEvent.equals(Object)", "int CompactionJobFinishedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult2 = jobRunIdResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link CompactionJobFinishedEvent#equals(Object)}, and {@link CompactionJobFinishedEvent#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobFinishedEvent#equals(Object)}
   *   <li>{@link CompactionJobFinishedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedEvent.equals(Object)", "int CompactionJobFinishedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link CompactionJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedEvent.equals(Object)", "int CompactionJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("Job Id").jobRunId("42");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult2 = jobRunIdResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedEvent.equals(Object)", "int CompactionJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("Job Run Id");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult2 = jobRunIdResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedEvent.equals(Object)", "int CompactionJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);

    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(
            new JobRunSummary(recordsProcessed,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult2 = jobRunIdResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedEvent.equals(Object)", "int CompactionJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("Table Id")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult2 = jobRunIdResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedEvent.equals(Object)", "int CompactionJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("Task Id")
        .build();
    Builder jobRunIdResult2 = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult2 = jobRunIdResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedEvent.equals(Object)", "int CompactionJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CompactionJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedEvent.equals(Object)", "int CompactionJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder jobRunIdResult = CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobFinishedEvent buildResult = jobRunIdResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CompactionJobFinishedEvent");
  }
}
