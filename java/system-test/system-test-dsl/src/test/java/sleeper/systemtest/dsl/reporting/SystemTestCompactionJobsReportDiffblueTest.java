package sleeper.systemtest.dsl.reporting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.compaction.job.query.CompactionJobCreatedStatus;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus.Builder;
import sleeper.core.tracker.job.run.JobRuns;

class SystemTestCompactionJobsReportDiffblueTest {
  /**
   * Test {@link SystemTestCompactionJobsReport#finishedStatistics()}.
   * <p>
   * Method under test: {@link SystemTestCompactionJobsReport#finishedStatistics()}
   */
  @Test
  @DisplayName("Test finishedStatistics()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics SystemTestCompactionJobsReport.finishedStatistics()"})
  void testFinishedStatistics() {
    // Arrange, Act and Assert
    assertFalse(
        (new SystemTestCompactionJobsReport(new ArrayList<>())).finishedStatistics().isAllFinishedOneRunEach(1));
  }

  /**
   * Test {@link SystemTestCompactionJobsReport#finishedStatistics()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestCompactionJobsReport#finishedStatistics()}
   */
  @Test
  @DisplayName("Test finishedStatistics(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics SystemTestCompactionJobsReport.finishedStatistics()"})
  void testFinishedStatistics_thenCallsGetRunsLatestFirst() {
    // Arrange
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    Builder builderResult = CompactionJobStatus.builder();
    CompactionJobCreatedStatus.Builder partitionIdResult = CompactionJobCreatedStatus.builder()
        .inputFilesCount(3)
        .partitionId("42");
    CompactionJobCreatedStatus createdStatus = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder createdStatusResult = builderResult.createdStatus(createdStatus);
    CompactionJobStatus buildResult = createdStatusResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns)
        .build();

    ArrayList<CompactionJobStatus> jobs = new ArrayList<>();
    jobs.add(buildResult);

    // Act
    JobsFinishedStatistics actualFinishedStatisticsResult = (new SystemTestCompactionJobsReport(jobs))
        .finishedStatistics();

    // Assert
    verify(jobRuns).getRunsLatestFirst();
    assertFalse(actualFinishedStatisticsResult.isAllFinishedOneRunEach(1));
  }

  /**
   * Test {@link SystemTestCompactionJobsReport#finishedStatistics()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestCompactionJobsReport#finishedStatistics()}
   */
  @Test
  @DisplayName("Test finishedStatistics(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics SystemTestCompactionJobsReport.finishedStatistics()"})
  void testFinishedStatistics_thenCallsGetRunsLatestFirst2() {
    // Arrange
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    Builder builderResult = CompactionJobStatus.builder();
    CompactionJobCreatedStatus.Builder partitionIdResult = CompactionJobCreatedStatus.builder()
        .inputFilesCount(3)
        .partitionId("42");
    CompactionJobCreatedStatus createdStatus = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder createdStatusResult = builderResult.createdStatus(createdStatus);
    CompactionJobStatus buildResult = createdStatusResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns)
        .build();
    JobRuns jobRuns2 = mock(JobRuns.class);
    when(jobRuns2.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    Builder builderResult2 = CompactionJobStatus.builder();
    CompactionJobCreatedStatus.Builder partitionIdResult2 = CompactionJobCreatedStatus.builder()
        .inputFilesCount(3)
        .partitionId("42");
    CompactionJobCreatedStatus createdStatus2 = partitionIdResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder createdStatusResult2 = builderResult2.createdStatus(createdStatus2);
    CompactionJobStatus buildResult2 = createdStatusResult2
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns2)
        .build();

    ArrayList<CompactionJobStatus> jobs = new ArrayList<>();
    jobs.add(buildResult2);
    jobs.add(buildResult);

    // Act
    JobsFinishedStatistics actualFinishedStatisticsResult = (new SystemTestCompactionJobsReport(jobs))
        .finishedStatistics();

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
    assertFalse(actualFinishedStatisticsResult.isAllFinishedOneRunEach(1));
  }
}
