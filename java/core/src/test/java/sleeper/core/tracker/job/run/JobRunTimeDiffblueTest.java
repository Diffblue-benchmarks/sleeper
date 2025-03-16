package sleeper.core.tracker.job.run;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;

class JobRunTimeDiffblueTest {
  /**
   * Test {@link JobRunTime#JobRunTime(Instant, Duration)}.
   * <p>
   * Method under test: {@link JobRunTime#JobRunTime(Instant, Duration)}
   */
  @Test
  @DisplayName("Test new JobRunTime(Instant, Duration)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunTime.<init>(Instant, Duration)"})
  void testNewJobRunTime() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Duration duration = TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS;

    // Act
    JobRunTime actualJobRunTime = new JobRunTime(startTime, duration);

    // Assert
    Instant finishTime = actualJobRunTime.getFinishTime();
    assertEquals(0, finishTime.getNano());
    assertEquals(60.0d, actualJobRunTime.getDurationInSeconds());
    assertEquals(60.0d, actualJobRunTime.getTimeInProcessInSeconds());
    assertEquals(60000000000L, actualJobRunTime.getDuration().toNanos());
    assertEquals(60L, finishTime.getEpochSecond());
    Instant expectedStartTime = startTime.EPOCH;
    assertSame(expectedStartTime, actualJobRunTime.getStartTime());
    assertSame(duration, actualJobRunTime.getTimeInProcess());
  }

  /**
   * Test {@link JobRunTime#JobRunTime(Instant, Instant)}.
   * <p>
   * Method under test: {@link JobRunTime#JobRunTime(Instant, Instant)}
   */
  @Test
  @DisplayName("Test new JobRunTime(Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunTime.<init>(Instant, Instant)"})
  void testNewJobRunTime2() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Instant finishTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    JobRunTime actualJobRunTime = new JobRunTime(startTime, finishTime);

    // Assert
    assertEquals(0.0d, actualJobRunTime.getDurationInSeconds());
    assertEquals(0.0d, actualJobRunTime.getTimeInProcessInSeconds());
    Duration duration = actualJobRunTime.getDuration();
    assertEquals(0L, duration.toNanos());
    assertSame(duration, actualJobRunTime.getTimeInProcess());
    Instant instant = finishTime.EPOCH;
    assertSame(instant, actualJobRunTime.getFinishTime());
    assertSame(instant, actualJobRunTime.getStartTime());
  }

  /**
   * Test {@link JobRunTime#JobRunTime(Instant, Instant, Duration)}.
   * <ul>
   *   <li>Then return DurationInSeconds is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunTime#JobRunTime(Instant, Instant, Duration)}
   */
  @Test
  @DisplayName("Test new JobRunTime(Instant, Instant, Duration); then return DurationInSeconds is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunTime.<init>(Instant, Instant, Duration)"})
  void testNewJobRunTime_thenReturnDurationInSecondsIsZero() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Instant finishTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Duration timeInProcess = TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS;

    // Act
    JobRunTime actualJobRunTime = new JobRunTime(startTime, finishTime, timeInProcess);

    // Assert
    assertEquals(0.0d, actualJobRunTime.getDurationInSeconds());
    Duration duration = actualJobRunTime.getDuration();
    assertEquals(0L, duration.toNanos());
    assertEquals(60.0d, actualJobRunTime.getTimeInProcessInSeconds());
    assertSame(timeInProcess.ZERO, duration);
    Instant instant = finishTime.EPOCH;
    assertSame(instant, actualJobRunTime.getFinishTime());
    assertSame(instant, actualJobRunTime.getStartTime());
    assertSame(timeInProcess, actualJobRunTime.getTimeInProcess());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRunTime#toString()}
   *   <li>{@link JobRunTime#getDuration()}
   *   <li>{@link JobRunTime#getFinishTime()}
   *   <li>{@link JobRunTime#getStartTime()}
   *   <li>{@link JobRunTime#getTimeInProcess()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Duration JobRunTime.getDuration()", "Instant JobRunTime.getFinishTime()",
      "Instant JobRunTime.getStartTime()", "Duration JobRunTime.getTimeInProcess()", "String JobRunTime.toString()"})
  void testGettersAndSetters() {
    // Arrange
    JobRunTime jobRunTime = new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);

    // Act
    String actualToStringResult = jobRunTime.toString();
    Duration actualDuration = jobRunTime.getDuration();
    Instant actualFinishTime = jobRunTime.getFinishTime();
    Instant actualStartTime = jobRunTime.getStartTime();
    Duration actualTimeInProcess = jobRunTime.getTimeInProcess();

    // Assert
    assertEquals("JobRunTime{startTime=1970-01-01T00:00:00Z, endTime=1970-01-01T00:01:00Z, timeInProcess=PT1M}",
        actualToStringResult);
    assertEquals(0, actualFinishTime.getNano());
    assertEquals(60000000000L, actualDuration.toNanos());
    assertEquals(60000000000L, actualTimeInProcess.toNanos());
    assertEquals(60L, actualFinishTime.getEpochSecond());
    assertSame(actualStartTime.EPOCH, actualStartTime);
  }

  /**
   * Test {@link JobRunTime#getDurationInSeconds()}.
   * <p>
   * Method under test: {@link JobRunTime#getDurationInSeconds()}
   */
  @Test
  @DisplayName("Test getDurationInSeconds()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double JobRunTime.getDurationInSeconds()"})
  void testGetDurationInSeconds() {
    // Arrange, Act and Assert
    assertEquals(60.0d, (new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)).getDurationInSeconds());
  }

  /**
   * Test {@link JobRunTime#getTimeInProcessInSeconds()}.
   * <p>
   * Method under test: {@link JobRunTime#getTimeInProcessInSeconds()}
   */
  @Test
  @DisplayName("Test getTimeInProcessInSeconds()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double JobRunTime.getTimeInProcessInSeconds()"})
  void testGetTimeInProcessInSeconds() {
    // Arrange, Act and Assert
    assertEquals(60.0d, (new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)).getTimeInProcessInSeconds());
  }

  /**
   * Test {@link JobRunTime#equals(Object)}, and {@link JobRunTime#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRunTime#equals(Object)}
   *   <li>{@link JobRunTime#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunTime.equals(Object)", "int JobRunTime.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JobRunTime jobRunTime = new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);
    JobRunTime jobRunTime2 = new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);

    // Act and Assert
    assertEquals(jobRunTime, jobRunTime2);
    int expectedHashCodeResult = jobRunTime.hashCode();
    assertEquals(expectedHashCodeResult, jobRunTime2.hashCode());
  }

  /**
   * Test {@link JobRunTime#equals(Object)}, and {@link JobRunTime#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRunTime#equals(Object)}
   *   <li>{@link JobRunTime#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunTime.equals(Object)", "int JobRunTime.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JobRunTime jobRunTime = new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);

    // Act and Assert
    assertEquals(jobRunTime, jobRunTime);
    int expectedHashCodeResult = jobRunTime.hashCode();
    assertEquals(expectedHashCodeResult, jobRunTime.hashCode());
  }

  /**
   * Test {@link JobRunTime#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunTime#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunTime.equals(Object)", "int JobRunTime.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    JobRunTime jobRunTime = new JobRunTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS);

    // Act and Assert
    assertNotEquals(jobRunTime,
        new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS));
  }

  /**
   * Test {@link JobRunTime#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunTime#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunTime.equals(Object)", "int JobRunTime.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    JobRunTime jobRunTime = new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_TRANSACTION_CHECKS);

    // Act and Assert
    assertNotEquals(jobRunTime,
        new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS));
  }

  /**
   * Test {@link JobRunTime#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunTime#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunTime.equals(Object)", "int JobRunTime.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    JobRunTime jobRunTime = new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_TRANSACTION_CHECKS);
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(jobRunTime,
        new JobRunTime(startTime, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS));
  }

  /**
   * Test {@link JobRunTime#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunTime#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunTime.equals(Object)", "int JobRunTime.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS), null);
  }

  /**
   * Test {@link JobRunTime#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunTime#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunTime.equals(Object)", "int JobRunTime.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS), "Different type to JobRunTime");
  }
}
