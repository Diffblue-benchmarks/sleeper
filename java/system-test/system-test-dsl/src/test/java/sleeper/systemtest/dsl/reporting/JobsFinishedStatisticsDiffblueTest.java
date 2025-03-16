package sleeper.systemtest.dsl.reporting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.compaction.job.query.CompactionJobCreatedStatus;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus.Builder;
import sleeper.core.tracker.ingest.job.query.IngestJobStatus;
import sleeper.core.tracker.job.run.JobRuns;

class JobsFinishedStatisticsDiffblueTest {
  /**
   * Test {@link JobsFinishedStatistics#fromIngestJobs(List)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobsFinishedStatistics#fromIngestJobs(List)}
   */
  @Test
  @DisplayName("Test fromIngestJobs(List); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics JobsFinishedStatistics.fromIngestJobs(List)"})
  void testFromIngestJobs_thenCallsGetRunsLatestFirst() {
    // Arrange
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    IngestJobStatus.Builder builderResult = IngestJobStatus.builder();
    IngestJobStatus buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns)
        .build();

    ArrayList<IngestJobStatus> jobs = new ArrayList<>();
    jobs.add(buildResult);

    // Act
    JobsFinishedStatistics actualFromIngestJobsResult = JobsFinishedStatistics.fromIngestJobs(jobs);

    // Assert
    verify(jobRuns).getRunsLatestFirst();
    assertFalse(actualFromIngestJobsResult.isAllFinishedOneRunEach(1));
  }

  /**
   * Test {@link JobsFinishedStatistics#fromIngestJobs(List)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobsFinishedStatistics#fromIngestJobs(List)}
   */
  @Test
  @DisplayName("Test fromIngestJobs(List); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics JobsFinishedStatistics.fromIngestJobs(List)"})
  void testFromIngestJobs_thenCallsGetRunsLatestFirst2() {
    // Arrange
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    IngestJobStatus.Builder builderResult = IngestJobStatus.builder();
    IngestJobStatus buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns)
        .build();
    JobRuns jobRuns2 = mock(JobRuns.class);
    when(jobRuns2.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    IngestJobStatus.Builder builderResult2 = IngestJobStatus.builder();
    IngestJobStatus buildResult2 = builderResult2
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns2)
        .build();

    ArrayList<IngestJobStatus> jobs = new ArrayList<>();
    jobs.add(buildResult2);
    jobs.add(buildResult);

    // Act
    JobsFinishedStatistics actualFromIngestJobsResult = JobsFinishedStatistics.fromIngestJobs(jobs);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
    assertFalse(actualFromIngestJobsResult.isAllFinishedOneRunEach(1));
  }

  /**
   * Test {@link JobsFinishedStatistics#fromIngestJobs(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobsFinishedStatistics#fromIngestJobs(List)}
   */
  @Test
  @DisplayName("Test fromIngestJobs(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics JobsFinishedStatistics.fromIngestJobs(List)"})
  void testFromIngestJobs_whenArrayList() {
    // Arrange, Act and Assert
    assertFalse(JobsFinishedStatistics.fromIngestJobs(new ArrayList<>()).isAllFinishedOneRunEach(1));
  }

  /**
   * Test {@link JobsFinishedStatistics#fromCompactionJobs(List)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobsFinishedStatistics#fromCompactionJobs(List)}
   */
  @Test
  @DisplayName("Test fromCompactionJobs(List); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics JobsFinishedStatistics.fromCompactionJobs(List)"})
  void testFromCompactionJobs_thenCallsGetRunsLatestFirst() {
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
    JobsFinishedStatistics actualFromCompactionJobsResult = JobsFinishedStatistics.fromCompactionJobs(jobs);

    // Assert
    verify(jobRuns).getRunsLatestFirst();
    assertFalse(actualFromCompactionJobsResult.isAllFinishedOneRunEach(1));
  }

  /**
   * Test {@link JobsFinishedStatistics#fromCompactionJobs(List)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobsFinishedStatistics#fromCompactionJobs(List)}
   */
  @Test
  @DisplayName("Test fromCompactionJobs(List); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics JobsFinishedStatistics.fromCompactionJobs(List)"})
  void testFromCompactionJobs_thenCallsGetRunsLatestFirst2() {
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
    JobsFinishedStatistics actualFromCompactionJobsResult = JobsFinishedStatistics.fromCompactionJobs(jobs);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
    assertFalse(actualFromCompactionJobsResult.isAllFinishedOneRunEach(1));
  }

  /**
   * Test {@link JobsFinishedStatistics#fromCompactionJobs(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobsFinishedStatistics#fromCompactionJobs(List)}
   */
  @Test
  @DisplayName("Test fromCompactionJobs(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobsFinishedStatistics JobsFinishedStatistics.fromCompactionJobs(List)"})
  void testFromCompactionJobs_whenArrayList() {
    // Arrange, Act and Assert
    assertFalse(JobsFinishedStatistics.fromCompactionJobs(new ArrayList<>()).isAllFinishedOneRunEach(1));
  }
}
