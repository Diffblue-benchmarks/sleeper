package sleeper.core.tracker.compaction.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.job.run.JobRun;
import sleeper.core.tracker.job.status.JobRunFailedStatus;
import sleeper.core.tracker.job.status.JobStatusUpdate;

class CompactionJobUpdateTypeInRunDiffblueTest {
  /**
   * Test {@link CompactionJobUpdateTypeInRun#typeOfFurthestUpdateInRun(JobRun)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link JobStatusUpdate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobUpdateTypeInRun#typeOfFurthestUpdateInRun(JobRun)}
   */
  @Test
  @DisplayName("Test typeOfFurthestUpdateInRun(JobRun); given ArrayList() add JobStatusUpdate")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobUpdateTypeInRun CompactionJobUpdateTypeInRun.typeOfFurthestUpdateInRun(JobRun)"})
  void testTypeOfFurthestUpdateInRun_givenArrayListAddJobStatusUpdate() {
    // Arrange
    ArrayList<JobStatusUpdate> jobStatusUpdateList = new ArrayList<>();
    jobStatusUpdateList.add(mock(JobStatusUpdate.class));
    JobRun run = mock(JobRun.class);
    when(run.getStatusUpdates()).thenReturn(jobStatusUpdateList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> CompactionJobUpdateTypeInRun.typeOfFurthestUpdateInRun(run));
    verify(run).getStatusUpdates();
  }

  /**
   * Test {@link CompactionJobUpdateTypeInRun#typeOfFurthestUpdateInRun(JobRun)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobUpdateTypeInRun#typeOfFurthestUpdateInRun(JobRun)}
   */
  @Test
  @DisplayName("Test typeOfFurthestUpdateInRun(JobRun); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobUpdateTypeInRun CompactionJobUpdateTypeInRun.typeOfFurthestUpdateInRun(JobRun)"})
  void testTypeOfFurthestUpdateInRun_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    JobRun run = mock(JobRun.class);
    when(run.getStatusUpdates()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> CompactionJobUpdateTypeInRun.typeOfFurthestUpdateInRun(run));
    verify(run).getStatusUpdates();
  }

  /**
   * Test {@link CompactionJobUpdateTypeInRun#typeOfFurthestUpdateInRun(JobRun)}.
   * <ul>
   *   <li>When builder taskId {@code 42} build.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobUpdateTypeInRun#typeOfFurthestUpdateInRun(JobRun)}
   */
  @Test
  @DisplayName("Test typeOfFurthestUpdateInRun(JobRun); when builder taskId '42' build; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobUpdateTypeInRun CompactionJobUpdateTypeInRun.typeOfFurthestUpdateInRun(JobRun)"})
  void testTypeOfFurthestUpdateInRun_whenBuilderTaskId42Build_thenReturnNull() {
    // Arrange
    JobRun run = JobRun.builder().taskId("42").build();

    // Act and Assert
    assertNull(CompactionJobUpdateTypeInRun.typeOfFurthestUpdateInRun(run));
  }

  /**
   * Test {@link CompactionJobUpdateTypeInRun#typeOfUpdateInRun(JobStatusUpdate)}.
   * <ul>
   *   <li>Then return {@code COMMITTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobUpdateTypeInRun#typeOfUpdateInRun(JobStatusUpdate)}
   */
  @Test
  @DisplayName("Test typeOfUpdateInRun(JobStatusUpdate); then return 'COMMITTED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobUpdateTypeInRun CompactionJobUpdateTypeInRun.typeOfUpdateInRun(JobStatusUpdate)"})
  void testTypeOfUpdateInRun_thenReturnCommitted() {
    // Arrange
    Instant commitTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertEquals(CompactionJobUpdateTypeInRun.COMMITTED,
        CompactionJobUpdateTypeInRun.typeOfUpdateInRun(CompactionJobCommittedStatus.commitAndUpdateTime(commitTime,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
  }

  /**
   * Test {@link CompactionJobUpdateTypeInRun#typeOfUpdateInRun(JobStatusUpdate)}.
   * <ul>
   *   <li>Then return {@code FINISHED_WHEN_COMMITTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobUpdateTypeInRun#typeOfUpdateInRun(JobStatusUpdate)}
   */
  @Test
  @DisplayName("Test typeOfUpdateInRun(JobStatusUpdate); then return 'FINISHED_WHEN_COMMITTED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobUpdateTypeInRun CompactionJobUpdateTypeInRun.typeOfUpdateInRun(JobStatusUpdate)"})
  void testTypeOfUpdateInRun_thenReturnFinishedWhenCommitted() {
    // Arrange, Act and Assert
    assertEquals(CompactionJobUpdateTypeInRun.FINISHED_WHEN_COMMITTED,
        CompactionJobUpdateTypeInRun.typeOfUpdateInRun(mock(CompactionJobFinishedStatus.class)));
  }

  /**
   * Test {@link CompactionJobUpdateTypeInRun#typeOfUpdateInRun(JobStatusUpdate)}.
   * <ul>
   *   <li>Then return {@code STARTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobUpdateTypeInRun#typeOfUpdateInRun(JobStatusUpdate)}
   */
  @Test
  @DisplayName("Test typeOfUpdateInRun(JobStatusUpdate); then return 'STARTED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobUpdateTypeInRun CompactionJobUpdateTypeInRun.typeOfUpdateInRun(JobStatusUpdate)"})
  void testTypeOfUpdateInRun_thenReturnStarted() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertEquals(CompactionJobUpdateTypeInRun.STARTED,
        CompactionJobUpdateTypeInRun.typeOfUpdateInRun(CompactionJobStartedStatus.startAndUpdateTime(startTime,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
  }

  /**
   * Test {@link CompactionJobUpdateTypeInRun#typeOfUpdateInRun(JobStatusUpdate)}.
   * <ul>
   *   <li>When {@link JobRunFailedStatus}.</li>
   *   <li>Then return {@code FAILED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobUpdateTypeInRun#typeOfUpdateInRun(JobStatusUpdate)}
   */
  @Test
  @DisplayName("Test typeOfUpdateInRun(JobStatusUpdate); when JobRunFailedStatus; then return 'FAILED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobUpdateTypeInRun CompactionJobUpdateTypeInRun.typeOfUpdateInRun(JobStatusUpdate)"})
  void testTypeOfUpdateInRun_whenJobRunFailedStatus_thenReturnFailed() {
    // Arrange, Act and Assert
    assertEquals(CompactionJobUpdateTypeInRun.FAILED,
        CompactionJobUpdateTypeInRun.typeOfUpdateInRun(mock(JobRunFailedStatus.class)));
  }

  /**
   * Test {@link CompactionJobUpdateTypeInRun#typeOfUpdateInRun(JobStatusUpdate)}.
   * <ul>
   *   <li>When {@link JobStatusUpdate}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobUpdateTypeInRun#typeOfUpdateInRun(JobStatusUpdate)}
   */
  @Test
  @DisplayName("Test typeOfUpdateInRun(JobStatusUpdate); when JobStatusUpdate; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobUpdateTypeInRun CompactionJobUpdateTypeInRun.typeOfUpdateInRun(JobStatusUpdate)"})
  void testTypeOfUpdateInRun_whenJobStatusUpdate_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> CompactionJobUpdateTypeInRun.typeOfUpdateInRun(mock(JobStatusUpdate.class)));
  }

  /**
   * Test {@link CompactionJobUpdateTypeInRun#getJobStatusTypeAfterUpdate()}.
   * <p>
   * Method under test: {@link CompactionJobUpdateTypeInRun#getJobStatusTypeAfterUpdate()}
   */
  @Test
  @DisplayName("Test getJobStatusTypeAfterUpdate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobStatusType CompactionJobUpdateTypeInRun.getJobStatusTypeAfterUpdate()"})
  void testGetJobStatusTypeAfterUpdate() {
    // Arrange, Act and Assert
    assertEquals(CompactionJobStatusType.IN_PROGRESS,
        CompactionJobUpdateTypeInRun.valueOf("STARTED").getJobStatusTypeAfterUpdate());
  }
}
