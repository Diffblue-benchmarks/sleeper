package sleeper.core.tracker.job.status;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.JobRunTime;
import sleeper.core.tracker.job.run.RecordsProcessed;

class JobRunEndUpdateDiffblueTest {
  /**
   * Test {@link JobRunEndUpdate#getTimeInProcess()}.
   * <p>
   * Method under test: {@link JobRunEndUpdate#getTimeInProcess()}
   */
  @Test
  @DisplayName("Test getTimeInProcess()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional JobRunEndUpdate.getTimeInProcess()"})
  void testGetTimeInProcess() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertFalse(
        TestJobStartedAndFinishedStatus
            .updateAndSummary(updateTime,
                new JobRunSummary(RecordsProcessed.NONE,
                    new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
            .getTimeInProcess()
            .isPresent());
  }

  /**
   * Test {@link JobRunEndUpdate#isSuccessful()}.
   * <p>
   * Method under test: {@link JobRunEndUpdate#isSuccessful()}
   */
  @Test
  @DisplayName("Test isSuccessful()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunEndUpdate.isSuccessful()"})
  void testIsSuccessful() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertTrue(
        AggregatedTaskJobsFinishedStatus
            .updateTimeAndSummary(updateTime,
                new JobRunSummary(RecordsProcessed.NONE,
                    new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
            .isSuccessful());
  }

  /**
   * Test {@link JobRunEndUpdate#getFailureReasons()}.
   * <p>
   * Method under test: {@link JobRunEndUpdate#getFailureReasons()}
   */
  @Test
  @DisplayName("Test getFailureReasons()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List JobRunEndUpdate.getFailureReasons()"})
  void testGetFailureReasons() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertTrue(
        AggregatedTaskJobsFinishedStatus
            .updateTimeAndSummary(updateTime,
                new JobRunSummary(RecordsProcessed.NONE,
                    new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                        TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
            .getFailureReasons()
            .isEmpty());
  }
}
