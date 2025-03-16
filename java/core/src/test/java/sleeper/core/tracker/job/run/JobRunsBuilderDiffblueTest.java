package sleeper.core.tracker.job.run;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.job.status.JobStatusUpdateRecord;
import sleeper.core.tracker.job.status.JobStatusUpdateRecord.Builder;
import sleeper.core.tracker.job.status.TestJobRunStatus;

class JobRunsBuilderDiffblueTest {
  /**
   * Test {@link JobRunsBuilder#add(JobStatusUpdateRecord)}.
   * <p>
   * Method under test: {@link JobRunsBuilder#add(JobStatusUpdateRecord)}
   */
  @Test
  @DisplayName("Test add(JobStatusUpdateRecord)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunsBuilder.add(JobStatusUpdateRecord)"})
  void testAdd() {
    // Arrange
    JobRunsBuilder jobRunsBuilder = new JobRunsBuilder();
    Builder builderResult = JobStatusUpdateRecord.builder();
    Builder jobRunIdResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42");
    JobStatusUpdateRecord resultRecord = jobRunIdResult
        .statusUpdate(new TestJobRunStatus(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .taskId("42")
        .build();

    // Act
    jobRunsBuilder.add(resultRecord);

    // Assert
    assertEquals(1, jobRunsBuilder.build().getFirstRun().get().getStatusUpdates().size());
  }

  /**
   * Test {@link JobRunsBuilder#add(JobStatusUpdateRecord)}.
   * <p>
   * Method under test: {@link JobRunsBuilder#add(JobStatusUpdateRecord)}
   */
  @Test
  @DisplayName("Test add(JobStatusUpdateRecord)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunsBuilder.add(JobStatusUpdateRecord)"})
  void testAdd2() {
    // Arrange
    JobRunsBuilder jobRunsBuilder = new JobRunsBuilder();
    Builder builderResult = JobStatusUpdateRecord.builder();
    Builder jobRunIdResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId(null);
    JobStatusUpdateRecord resultRecord = jobRunIdResult
        .statusUpdate(new TestJobRunStatus(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .taskId("42")
        .build();

    // Act
    jobRunsBuilder.add(resultRecord);

    // Assert
    assertEquals(1, jobRunsBuilder.build().getFirstRun().get().getStatusUpdates().size());
  }

  /**
   * Test {@link JobRunsBuilder#add(JobStatusUpdateRecord)}.
   * <ul>
   *   <li>Then {@link JobRunsBuilder} (default constructor) build FirstRun TaskId is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunsBuilder#add(JobStatusUpdateRecord)}
   */
  @Test
  @DisplayName("Test add(JobStatusUpdateRecord); then JobRunsBuilder (default constructor) build FirstRun TaskId is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunsBuilder.add(JobStatusUpdateRecord)"})
  void testAdd_thenJobRunsBuilderBuildFirstRunTaskIdIsNull() {
    // Arrange
    JobRunsBuilder jobRunsBuilder = new JobRunsBuilder();
    Builder builderResult = JobStatusUpdateRecord.builder();
    Builder jobRunIdResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42");
    JobStatusUpdateRecord resultRecord = jobRunIdResult
        .statusUpdate(new TestJobRunStatus(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .taskId(null)
        .build();

    // Act
    jobRunsBuilder.add(resultRecord);

    // Assert
    JobRun getResult = jobRunsBuilder.build().getFirstRun().get();
    assertNull(getResult.getTaskId());
    assertEquals(1, getResult.getStatusUpdates().size());
  }

  /**
   * Test {@link JobRunsBuilder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRunsBuilder#build()}
   *   <li>default or parameterless constructor of {@link JobRunsBuilder}
   * </ul>
   */
  @Test
  @DisplayName("Test build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JobRunsBuilder.<init>()", "JobRuns JobRunsBuilder.build()"})
  void testBuild() {
    // Arrange and Act
    JobRuns actualBuildResult = (new JobRunsBuilder()).build();

    // Assert
    assertFalse(actualBuildResult.getFirstRun().isPresent());
    assertFalse(actualBuildResult.isStarted());
    assertTrue(actualBuildResult.getRunsLatestFirst().isEmpty());
  }
}
