package sleeper.core.tracker.compaction.job.query;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus.Builder;
import sleeper.core.tracker.job.run.JobRun;
import sleeper.core.tracker.job.run.JobRuns;

class CompactionJobStatusDiffblueTest {
  /**
   * Test {@link CompactionJobStatus#builder()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobStatus#builder()}
   *   <li>{@link CompactionJobStatus#createdStatus(CompactionJobCreatedStatus)}
   *   <li>{@link CompactionJobStatus#expiryDate(Instant)}
   *   <li>{@link CompactionJobStatus#jobId(String)}
   *   <li>{@link CompactionJobStatus#jobRuns(JobRuns)}
   * </ul>
   */
  @Test
  @DisplayName("Test builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobStatus Builder.build()", "Builder Builder.createdStatus(CompactionJobCreatedStatus)",
      "Builder Builder.expiryDate(Instant)", "Builder Builder.jobId(String)", "Builder Builder.jobRuns(JobRuns)"})
  void testBuilder() {
    // Arrange and Act
    Builder actualBuilderResult = CompactionJobStatus.builder();
    CompactionJobCreatedStatus.Builder partitionIdResult = CompactionJobCreatedStatus.builder()
        .inputFilesCount(3)
        .partitionId("42");
    CompactionJobCreatedStatus createdStatus = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder actualCreatedStatusResult = actualBuilderResult.createdStatus(createdStatus);
    Builder actualJobIdResult = actualCreatedStatusResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42");

    // Assert
    assertSame(actualJobIdResult, actualJobIdResult.jobRuns(null));
  }

  /**
   * Test Builder {@link Builder#jobRunsLatestFirst(List)}.
   * <ul>
   *   <li>Given builder taskId {@code 42} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#jobRunsLatestFirst(List)}
   */
  @Test
  @DisplayName("Test Builder jobRunsLatestFirst(List); given builder taskId '42' build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.jobRunsLatestFirst(List)"})
  void testBuilderJobRunsLatestFirst_givenBuilderTaskId42Build() {
    // Arrange
    Builder builderResult = CompactionJobStatus.builder();

    ArrayList<JobRun> jobRunList = new ArrayList<>();
    JobRun buildResult = JobRun.builder().taskId("42").build();
    jobRunList.add(buildResult);

    // Act and Assert
    assertSame(builderResult, builderResult.jobRunsLatestFirst(jobRunList));
  }

  /**
   * Test Builder {@link Builder#jobRunsLatestFirst(List)}.
   * <ul>
   *   <li>Given builder taskId {@code 42} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#jobRunsLatestFirst(List)}
   */
  @Test
  @DisplayName("Test Builder jobRunsLatestFirst(List); given builder taskId '42' build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.jobRunsLatestFirst(List)"})
  void testBuilderJobRunsLatestFirst_givenBuilderTaskId42Build2() {
    // Arrange
    Builder builderResult = CompactionJobStatus.builder();

    ArrayList<JobRun> jobRunList = new ArrayList<>();
    JobRun buildResult = JobRun.builder().taskId("42").build();
    jobRunList.add(buildResult);
    JobRun buildResult2 = JobRun.builder().taskId("42").build();
    jobRunList.add(buildResult2);

    // Act and Assert
    assertSame(builderResult, builderResult.jobRunsLatestFirst(jobRunList));
  }

  /**
   * Test Builder {@link Builder#jobRunsLatestFirst(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#jobRunsLatestFirst(List)}
   */
  @Test
  @DisplayName("Test Builder jobRunsLatestFirst(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.jobRunsLatestFirst(List)"})
  void testBuilderJobRunsLatestFirst_whenArrayList() {
    // Arrange
    Builder builderResult = CompactionJobStatus.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.jobRunsLatestFirst(new ArrayList<>()));
  }

  /**
   * Test Builder {@link Builder#singleJobRun(JobRun)}.
   * <p>
   * Method under test: {@link Builder#singleJobRun(JobRun)}
   */
  @Test
  @DisplayName("Test Builder singleJobRun(JobRun)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.singleJobRun(JobRun)"})
  void testBuilderSingleJobRun() {
    // Arrange
    Builder builderResult = CompactionJobStatus.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.singleJobRun(mock(JobRun.class)));
  }
}
