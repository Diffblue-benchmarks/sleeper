package sleeper.systemtest.dsl.reporting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import sleeper.core.tracker.ingest.job.query.IngestJobStatus;
import sleeper.core.tracker.ingest.job.query.IngestJobStatus.Builder;
import sleeper.core.tracker.job.run.JobRuns;

class SystemTestIngestJobsReportDiffblueTest {
  /**
   * Test {@link SystemTestIngestJobsReport#finishedStatistics()}.
   * <p>
   * Method under test: {@link SystemTestIngestJobsReport#finishedStatistics()}
   */
  @Test
  @DisplayName("Test finishedStatistics()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics SystemTestIngestJobsReport.finishedStatistics()"})
  void testFinishedStatistics() {
    // Arrange, Act and Assert
    assertFalse((new SystemTestIngestJobsReport(new ArrayList<>())).finishedStatistics().isAllFinishedOneRunEach(1));
  }

  /**
   * Test {@link SystemTestIngestJobsReport#finishedStatistics()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestJobsReport#finishedStatistics()}
   */
  @Test
  @DisplayName("Test finishedStatistics(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics SystemTestIngestJobsReport.finishedStatistics()"})
  void testFinishedStatistics_thenCallsGetRunsLatestFirst() {
    // Arrange
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJobStatus.builder();
    IngestJobStatus buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns)
        .build();

    ArrayList<IngestJobStatus> jobs = new ArrayList<>();
    jobs.add(buildResult);

    // Act
    JobsFinishedStatistics actualFinishedStatisticsResult = (new SystemTestIngestJobsReport(jobs)).finishedStatistics();

    // Assert
    verify(jobRuns).getRunsLatestFirst();
    assertFalse(actualFinishedStatisticsResult.isAllFinishedOneRunEach(1));
  }

  /**
   * Test {@link SystemTestIngestJobsReport#finishedStatistics()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestJobsReport#finishedStatistics()}
   */
  @Test
  @DisplayName("Test finishedStatistics(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics SystemTestIngestJobsReport.finishedStatistics()"})
  void testFinishedStatistics_thenCallsGetRunsLatestFirst2() {
    // Arrange
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJobStatus.builder();
    IngestJobStatus buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns)
        .build();
    JobRuns jobRuns2 = mock(JobRuns.class);
    when(jobRuns2.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    Builder builderResult2 = IngestJobStatus.builder();
    IngestJobStatus buildResult2 = builderResult2
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns2)
        .build();

    ArrayList<IngestJobStatus> jobs = new ArrayList<>();
    jobs.add(buildResult2);
    jobs.add(buildResult);

    // Act
    JobsFinishedStatistics actualFinishedStatisticsResult = (new SystemTestIngestJobsReport(jobs)).finishedStatistics();

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
    assertFalse(actualFinishedStatisticsResult.isAllFinishedOneRunEach(1));
  }

  /**
   * Test {@link SystemTestIngestJobsReport#jobIds()}.
   * <p>
   * Method under test: {@link SystemTestIngestJobsReport#jobIds()}
   */
  @Test
  @DisplayName("Test jobIds()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List SystemTestIngestJobsReport.jobIds()"})
  void testJobIds() {
    // Arrange, Act and Assert
    assertTrue((new SystemTestIngestJobsReport(new ArrayList<>())).jobIds().isEmpty());
  }
}
