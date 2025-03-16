package sleeper.core.tracker.job.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.JobRunTime;
import sleeper.core.tracker.job.run.RecordsProcessed;

class AggregatedTaskJobsFinishedStatusDiffblueTest {
  /**
   * Test {@link AggregatedTaskJobsFinishedStatus#updateTimeAndSummary(Instant, JobRunSummary)}.
   * <ul>
   *   <li>Then return FinishTime Nano is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobsFinishedStatus#updateTimeAndSummary(Instant, JobRunSummary)}
   */
  @Test
  @DisplayName("Test updateTimeAndSummary(Instant, JobRunSummary); then return FinishTime Nano is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AggregatedTaskJobsFinishedStatus AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(Instant, JobRunSummary)"})
  void testUpdateTimeAndSummary_thenReturnFinishTimeNanoIsZero() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    AggregatedTaskJobsFinishedStatus actualUpdateTimeAndSummaryResult = AggregatedTaskJobsFinishedStatus
        .updateTimeAndSummary(updateTime,
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));

    // Assert
    Instant finishTime = actualUpdateTimeAndSummaryResult.getFinishTime();
    assertEquals(0, finishTime.getNano());
    RecordsProcessed recordsProcessed = actualUpdateTimeAndSummaryResult.getRecordsProcessed();
    assertEquals(0L, recordsProcessed.getRecordsRead());
    assertEquals(0L, recordsProcessed.getRecordsWritten());
    Optional<Duration> timeInProcess = actualUpdateTimeAndSummaryResult.getTimeInProcess();
    assertEquals(60000000000L, timeInProcess.get().toNanos());
    assertEquals(60L, finishTime.getEpochSecond());
    assertTrue(actualUpdateTimeAndSummaryResult.getFailureReasons().isEmpty());
    assertTrue(timeInProcess.isPresent());
    assertTrue(actualUpdateTimeAndSummaryResult.isSuccessful());
    Instant expectedUpdateTime = updateTime.EPOCH;
    assertSame(expectedUpdateTime, actualUpdateTimeAndSummaryResult.getUpdateTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AggregatedTaskJobsFinishedStatus#toString()}
   *   <li>{@link AggregatedTaskJobsFinishedStatus#getFinishTime()}
   *   <li>{@link AggregatedTaskJobsFinishedStatus#getRecordsProcessed()}
   *   <li>{@link AggregatedTaskJobsFinishedStatus#getUpdateTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant AggregatedTaskJobsFinishedStatus.getFinishTime()",
      "RecordsProcessed AggregatedTaskJobsFinishedStatus.getRecordsProcessed()",
      "Instant AggregatedTaskJobsFinishedStatus.getUpdateTime()", "String AggregatedTaskJobsFinishedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AggregatedTaskJobsFinishedStatus updateTimeAndSummaryResult = AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(
        updateTime,
        new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));

    // Act
    String actualToStringResult = updateTimeAndSummaryResult.toString();
    Instant actualFinishTime = updateTimeAndSummaryResult.getFinishTime();
    RecordsProcessed actualRecordsProcessed = updateTimeAndSummaryResult.getRecordsProcessed();
    Instant actualUpdateTime = updateTimeAndSummaryResult.getUpdateTime();

    // Assert
    assertEquals(
        "JobRunFinishedStatus{updateTime=1970-01-01T00:00:00Z, finishTime=1970-01-01T00:01:00Z, recordsProcessed"
            + "=RecordsProcessed{recordsRead=0, recordsWritten=0}, timeInProcess=PT1M}",
        actualToStringResult);
    assertEquals(0, actualFinishTime.getNano());
    assertEquals(60L, actualFinishTime.getEpochSecond());
    assertSame(actualUpdateTime.EPOCH, actualUpdateTime);
    assertSame(actualRecordsProcessed.NONE, actualRecordsProcessed);
  }

  /**
   * Test {@link AggregatedTaskJobsFinishedStatus#getTimeInProcess()}.
   * <p>
   * Method under test: {@link AggregatedTaskJobsFinishedStatus#getTimeInProcess()}
   */
  @Test
  @DisplayName("Test getTimeInProcess()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional AggregatedTaskJobsFinishedStatus.getTimeInProcess()"})
  void testGetTimeInProcess() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    Optional<Duration> actualTimeInProcess = AggregatedTaskJobsFinishedStatus
        .updateTimeAndSummary(updateTime,
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .getTimeInProcess();

    // Assert
    assertEquals(60000000000L, actualTimeInProcess.get().toNanos());
    assertTrue(actualTimeInProcess.isPresent());
  }

  /**
   * Test {@link AggregatedTaskJobsFinishedStatus#equals(Object)}, and {@link AggregatedTaskJobsFinishedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AggregatedTaskJobsFinishedStatus#equals(Object)}
   *   <li>{@link AggregatedTaskJobsFinishedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobsFinishedStatus.equals(Object)",
      "int AggregatedTaskJobsFinishedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AggregatedTaskJobsFinishedStatus updateTimeAndSummaryResult = AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(
        updateTime,
        new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));
    Instant updateTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AggregatedTaskJobsFinishedStatus updateTimeAndSummaryResult2 = AggregatedTaskJobsFinishedStatus
        .updateTimeAndSummary(updateTime2,
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));

    // Act and Assert
    assertEquals(updateTimeAndSummaryResult, updateTimeAndSummaryResult2);
    int expectedHashCodeResult = updateTimeAndSummaryResult.hashCode();
    assertEquals(expectedHashCodeResult, updateTimeAndSummaryResult2.hashCode());
  }

  /**
   * Test {@link AggregatedTaskJobsFinishedStatus#equals(Object)}, and {@link AggregatedTaskJobsFinishedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AggregatedTaskJobsFinishedStatus#equals(Object)}
   *   <li>{@link AggregatedTaskJobsFinishedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobsFinishedStatus.equals(Object)",
      "int AggregatedTaskJobsFinishedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AggregatedTaskJobsFinishedStatus updateTimeAndSummaryResult = AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(
        updateTime,
        new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));

    // Act and Assert
    assertEquals(updateTimeAndSummaryResult, updateTimeAndSummaryResult);
    int expectedHashCodeResult = updateTimeAndSummaryResult.hashCode();
    assertEquals(expectedHashCodeResult, updateTimeAndSummaryResult.hashCode());
  }

  /**
   * Test {@link AggregatedTaskJobsFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobsFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobsFinishedStatus.equals(Object)",
      "int AggregatedTaskJobsFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Instant updateTime = LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AggregatedTaskJobsFinishedStatus updateTimeAndSummaryResult = AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(
        updateTime,
        new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));
    Instant updateTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(updateTimeAndSummaryResult,
        AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(updateTime2,
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS))));
  }

  /**
   * Test {@link AggregatedTaskJobsFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobsFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobsFinishedStatus.equals(Object)",
      "int AggregatedTaskJobsFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);

    AggregatedTaskJobsFinishedStatus updateTimeAndSummaryResult = AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(
        updateTime,
        new JobRunSummary(recordsProcessed,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));
    Instant updateTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(updateTimeAndSummaryResult,
        AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(updateTime2,
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS))));
  }

  /**
   * Test {@link AggregatedTaskJobsFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobsFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobsFinishedStatus.equals(Object)",
      "int AggregatedTaskJobsFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AggregatedTaskJobsFinishedStatus updateTimeAndSummaryResult = AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(
        updateTime,
        new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)));
    Instant updateTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(updateTimeAndSummaryResult,
        AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(updateTime2,
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS))));
  }

  /**
   * Test {@link AggregatedTaskJobsFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobsFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobsFinishedStatus.equals(Object)",
      "int AggregatedTaskJobsFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(updateTime,
        new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS))),
        null);
  }

  /**
   * Test {@link AggregatedTaskJobsFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AggregatedTaskJobsFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AggregatedTaskJobsFinishedStatus.equals(Object)",
      "int AggregatedTaskJobsFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(
        AggregatedTaskJobsFinishedStatus.updateTimeAndSummary(updateTime,
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS))),
        "Different type to AggregatedTaskJobsFinishedStatus");
  }
}
