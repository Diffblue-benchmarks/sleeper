package sleeper.core.tracker.ingest.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.job.run.JobRun;
import sleeper.core.tracker.job.status.JobStatusUpdate;

class IngestJobFilesWrittenAndAddedDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobFilesWrittenAndAdded#getFilesAddedToStateStore()}
   *   <li>{@link IngestJobFilesWrittenAndAdded#getFilesWrittenAtJobFinish()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IngestJobFilesWrittenAndAdded.getFilesAddedToStateStore()",
      "int IngestJobFilesWrittenAndAdded.getFilesWrittenAtJobFinish()"})
  void testGettersAndSetters() {
    // Arrange
    JobRun run = JobRun.builder().taskId("42").build();
    IngestJobFilesWrittenAndAdded fromResult = IngestJobFilesWrittenAndAdded.from(run);

    // Act
    int actualFilesAddedToStateStore = fromResult.getFilesAddedToStateStore();

    // Assert
    assertEquals(0, actualFilesAddedToStateStore);
    assertEquals(0, fromResult.getFilesWrittenAtJobFinish());
  }

  /**
   * Test {@link IngestJobFilesWrittenAndAdded#haveAllFilesBeenAdded()}.
   * <p>
   * Method under test: {@link IngestJobFilesWrittenAndAdded#haveAllFilesBeenAdded()}
   */
  @Test
  @DisplayName("Test haveAllFilesBeenAdded()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFilesWrittenAndAdded.haveAllFilesBeenAdded()"})
  void testHaveAllFilesBeenAdded() {
    // Arrange
    JobRun run = JobRun.builder().taskId("42").build();

    // Act and Assert
    assertTrue(IngestJobFilesWrittenAndAdded.from(run).haveAllFilesBeenAdded());
  }

  /**
   * Test {@link IngestJobFilesWrittenAndAdded#from(JobRun)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link JobStatusUpdate}.</li>
   *   <li>Then calls {@link JobRun#getStatusUpdates()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFilesWrittenAndAdded#from(JobRun)}
   */
  @Test
  @DisplayName("Test from(JobRun); given ArrayList() add JobStatusUpdate; then calls getStatusUpdates()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobFilesWrittenAndAdded IngestJobFilesWrittenAndAdded.from(JobRun)"})
  void testFrom_givenArrayListAddJobStatusUpdate_thenCallsGetStatusUpdates() {
    // Arrange
    ArrayList<JobStatusUpdate> jobStatusUpdateList = new ArrayList<>();
    jobStatusUpdateList.add(mock(JobStatusUpdate.class));
    JobRun run = mock(JobRun.class);
    when(run.getStatusUpdates()).thenReturn(jobStatusUpdateList);

    // Act
    IngestJobFilesWrittenAndAdded actualFromResult = IngestJobFilesWrittenAndAdded.from(run);

    // Assert
    verify(run).getStatusUpdates();
    assertEquals(0, actualFromResult.getFilesAddedToStateStore());
    assertEquals(0, actualFromResult.getFilesWrittenAtJobFinish());
  }

  /**
   * Test {@link IngestJobFilesWrittenAndAdded#from(JobRun)}.
   * <ul>
   *   <li>When builder taskId {@code 42} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFilesWrittenAndAdded#from(JobRun)}
   */
  @Test
  @DisplayName("Test from(JobRun); when builder taskId '42' build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobFilesWrittenAndAdded IngestJobFilesWrittenAndAdded.from(JobRun)"})
  void testFrom_whenBuilderTaskId42Build() {
    // Arrange
    JobRun run = JobRun.builder().taskId("42").build();

    // Act
    IngestJobFilesWrittenAndAdded actualFromResult = IngestJobFilesWrittenAndAdded.from(run);

    // Assert
    assertEquals(0, actualFromResult.getFilesAddedToStateStore());
    assertEquals(0, actualFromResult.getFilesWrittenAtJobFinish());
  }
}
