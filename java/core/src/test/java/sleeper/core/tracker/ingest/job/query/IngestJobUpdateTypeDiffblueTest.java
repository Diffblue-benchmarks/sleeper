package sleeper.core.tracker.ingest.job.query;

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
import sleeper.core.tracker.job.status.JobStatusUpdate;

class IngestJobUpdateTypeDiffblueTest {
  /**
   * Test {@link IngestJobUpdateType#typeOfFurthestUpdateInRun(JobRun)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link JobStatusUpdate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobUpdateType#typeOfFurthestUpdateInRun(JobRun)}
   */
  @Test
  @DisplayName("Test typeOfFurthestUpdateInRun(JobRun); given ArrayList() add JobStatusUpdate")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobUpdateType IngestJobUpdateType.typeOfFurthestUpdateInRun(JobRun)"})
  void testTypeOfFurthestUpdateInRun_givenArrayListAddJobStatusUpdate() {
    // Arrange
    ArrayList<JobStatusUpdate> jobStatusUpdateList = new ArrayList<>();
    jobStatusUpdateList.add(mock(JobStatusUpdate.class));
    JobRun run = mock(JobRun.class);
    when(run.getStatusUpdates()).thenReturn(jobStatusUpdateList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> IngestJobUpdateType.typeOfFurthestUpdateInRun(run));
    verify(run).getStatusUpdates();
  }

  /**
   * Test {@link IngestJobUpdateType#typeOfFurthestUpdateInRun(JobRun)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobUpdateType#typeOfFurthestUpdateInRun(JobRun)}
   */
  @Test
  @DisplayName("Test typeOfFurthestUpdateInRun(JobRun); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobUpdateType IngestJobUpdateType.typeOfFurthestUpdateInRun(JobRun)"})
  void testTypeOfFurthestUpdateInRun_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    JobRun run = mock(JobRun.class);
    when(run.getStatusUpdates()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> IngestJobUpdateType.typeOfFurthestUpdateInRun(run));
    verify(run).getStatusUpdates();
  }

  /**
   * Test {@link IngestJobUpdateType#typeOfFurthestUpdateInRun(JobRun)}.
   * <ul>
   *   <li>When builder taskId {@code 42} build.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobUpdateType#typeOfFurthestUpdateInRun(JobRun)}
   */
  @Test
  @DisplayName("Test typeOfFurthestUpdateInRun(JobRun); when builder taskId '42' build; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobUpdateType IngestJobUpdateType.typeOfFurthestUpdateInRun(JobRun)"})
  void testTypeOfFurthestUpdateInRun_whenBuilderTaskId42Build_thenReturnNull() {
    // Arrange
    JobRun run = JobRun.builder().taskId("42").build();

    // Act and Assert
    assertNull(IngestJobUpdateType.typeOfFurthestUpdateInRun(run));
  }

  /**
   * Test {@link IngestJobUpdateType#statusTypeAfterThisInRun(JobRun)}.
   * <ul>
   *   <li>Given {@code ACCEPTED}.</li>
   *   <li>When {@link JobRun}.</li>
   *   <li>Then return {@code ACCEPTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobUpdateType#statusTypeAfterThisInRun(JobRun)}
   */
  @Test
  @DisplayName("Test statusTypeAfterThisInRun(JobRun); given 'ACCEPTED'; when JobRun; then return 'ACCEPTED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobStatusType IngestJobUpdateType.statusTypeAfterThisInRun(JobRun)"})
  void testStatusTypeAfterThisInRun_givenAccepted_whenJobRun_thenReturnAccepted() {
    // Arrange, Act and Assert
    assertEquals(IngestJobStatusType.ACCEPTED,
        IngestJobUpdateType.ACCEPTED.statusTypeAfterThisInRun(mock(JobRun.class)));
  }

  /**
   * Test {@link IngestJobUpdateType#statusTypeAfterThisInRun(JobRun)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link JobStatusUpdate}.</li>
   *   <li>Then return {@code FINISHED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobUpdateType#statusTypeAfterThisInRun(JobRun)}
   */
  @Test
  @DisplayName("Test statusTypeAfterThisInRun(JobRun); given ArrayList() add JobStatusUpdate; then return 'FINISHED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobStatusType IngestJobUpdateType.statusTypeAfterThisInRun(JobRun)"})
  void testStatusTypeAfterThisInRun_givenArrayListAddJobStatusUpdate_thenReturnFinished() {
    // Arrange
    ArrayList<JobStatusUpdate> jobStatusUpdateList = new ArrayList<>();
    jobStatusUpdateList.add(mock(JobStatusUpdate.class));
    JobRun run = mock(JobRun.class);
    when(run.getStatusUpdates()).thenReturn(jobStatusUpdateList);

    // Act
    IngestJobStatusType actualStatusTypeAfterThisInRunResult = IngestJobUpdateType.FINISHED_WHEN_FILES_COMMITTED
        .statusTypeAfterThisInRun(run);

    // Assert
    verify(run).getStatusUpdates();
    assertEquals(IngestJobStatusType.FINISHED, actualStatusTypeAfterThisInRunResult);
  }

  /**
   * Test {@link IngestJobUpdateType#statusTypeAfterThisInRun(JobRun)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code FINISHED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobUpdateType#statusTypeAfterThisInRun(JobRun)}
   */
  @Test
  @DisplayName("Test statusTypeAfterThisInRun(JobRun); given ArrayList(); then return 'FINISHED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobStatusType IngestJobUpdateType.statusTypeAfterThisInRun(JobRun)"})
  void testStatusTypeAfterThisInRun_givenArrayList_thenReturnFinished() {
    // Arrange
    JobRun run = mock(JobRun.class);
    when(run.getStatusUpdates()).thenReturn(new ArrayList<>());

    // Act
    IngestJobStatusType actualStatusTypeAfterThisInRunResult = IngestJobUpdateType.FINISHED_WHEN_FILES_COMMITTED
        .statusTypeAfterThisInRun(run);

    // Assert
    verify(run).getStatusUpdates();
    assertEquals(IngestJobStatusType.FINISHED, actualStatusTypeAfterThisInRunResult);
  }

  /**
   * Test {@link IngestJobUpdateType#statusTypeAfterThisInRun(JobRun)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobUpdateType#statusTypeAfterThisInRun(JobRun)}
   */
  @Test
  @DisplayName("Test statusTypeAfterThisInRun(JobRun); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobStatusType IngestJobUpdateType.statusTypeAfterThisInRun(JobRun)"})
  void testStatusTypeAfterThisInRun_thenThrowIllegalArgumentException() {
    // Arrange
    JobRun run = mock(JobRun.class);
    when(run.getStatusUpdates()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> IngestJobUpdateType.FINISHED_WHEN_FILES_COMMITTED.statusTypeAfterThisInRun(run));
    verify(run).getStatusUpdates();
  }

  /**
   * Test {@link IngestJobUpdateType#typeOfUpdate(JobStatusUpdate)}.
   * <ul>
   *   <li>Then return {@code ACCEPTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobUpdateType#typeOfUpdate(JobStatusUpdate)}
   */
  @Test
  @DisplayName("Test typeOfUpdate(JobStatusUpdate); then return 'ACCEPTED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobUpdateType IngestJobUpdateType.typeOfUpdate(JobStatusUpdate)"})
  void testTypeOfUpdate_thenReturnAccepted() {
    // Arrange
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertEquals(IngestJobUpdateType.ACCEPTED, IngestJobUpdateType.typeOfUpdate(IngestJobAcceptedStatus.from(3,
        validationTime, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
  }

  /**
   * Test {@link IngestJobUpdateType#typeOfUpdate(JobStatusUpdate)}.
   * <ul>
   *   <li>When {@link JobStatusUpdate}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobUpdateType#typeOfUpdate(JobStatusUpdate)}
   */
  @Test
  @DisplayName("Test typeOfUpdate(JobStatusUpdate); when JobStatusUpdate; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobUpdateType IngestJobUpdateType.typeOfUpdate(JobStatusUpdate)"})
  void testTypeOfUpdate_whenJobStatusUpdate_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> IngestJobUpdateType.typeOfUpdate(mock(JobStatusUpdate.class)));
  }
}
