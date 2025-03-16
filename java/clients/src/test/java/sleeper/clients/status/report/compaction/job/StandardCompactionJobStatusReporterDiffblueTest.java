package sleeper.clients.status.report.compaction.job;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.query.JobQuery;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.core.tracker.compaction.job.query.CompactionJobCreatedStatus;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus.Builder;
import sleeper.core.tracker.job.run.JobRuns;

class StandardCompactionJobStatusReporterDiffblueTest {
  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>()"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.PROMPT);

    // Assert
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>()"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst2() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.PROMPT);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>()"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst3() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.ALL);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>()"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst4() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.DETAILED);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>()"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst5() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.RANGE);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>()"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst6() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.UNFINISHED);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>(PrintStream)"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst7() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.PROMPT);

    // Assert
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>(PrintStream)"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst8() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.PROMPT);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>(PrintStream)"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst9() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.ALL);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>(PrintStream)"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst10() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.DETAILED);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>(PrintStream)"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst11() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.RANGE);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#StandardCompactionJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new StandardCompactionJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.<init>(PrintStream)"})
  void testNewStandardCompactionJobStatusReporter_thenCallsGetRunsLatestFirst12() {
    // Arrange and Act
    StandardCompactionJobStatusReporter actualStandardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
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
    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);
    actualStandardCompactionJobStatusReporter.report(jobStatusList, Type.UNFINISHED);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#report(List, Type)}.
   * <ul>
   *   <li>When {@code ALL}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#report(List, Type)}
   */
  @Test
  @DisplayName("Test report(List, Type); when 'ALL'; then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.report(List, Type)"})
  void testReport_whenAll_thenCallsGetRunsLatestFirst() {
    // Arrange
    StandardCompactionJobStatusReporter standardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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

    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);

    // Act
    standardCompactionJobStatusReporter.report(jobStatusList, Type.ALL);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#report(List, Type)}.
   * <ul>
   *   <li>When {@code DETAILED}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#report(List, Type)}
   */
  @Test
  @DisplayName("Test report(List, Type); when 'DETAILED'; then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.report(List, Type)"})
  void testReport_whenDetailed_thenCallsGetRunsLatestFirst() {
    // Arrange
    StandardCompactionJobStatusReporter standardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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

    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);

    // Act
    standardCompactionJobStatusReporter.report(jobStatusList, Type.DETAILED);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#report(List, Type)}.
   * <ul>
   *   <li>When {@code PROMPT}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#report(List, Type)}
   */
  @Test
  @DisplayName("Test report(List, Type); when 'PROMPT'; then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.report(List, Type)"})
  void testReport_whenPrompt_thenCallsGetRunsLatestFirst() {
    // Arrange
    StandardCompactionJobStatusReporter standardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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

    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult);

    // Act
    standardCompactionJobStatusReporter.report(jobStatusList, Type.PROMPT);

    // Assert
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#report(List, Type)}.
   * <ul>
   *   <li>When {@code PROMPT}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#report(List, Type)}
   */
  @Test
  @DisplayName("Test report(List, Type); when 'PROMPT'; then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.report(List, Type)"})
  void testReport_whenPrompt_thenCallsGetRunsLatestFirst2() {
    // Arrange
    StandardCompactionJobStatusReporter standardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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

    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);

    // Act
    standardCompactionJobStatusReporter.report(jobStatusList, Type.PROMPT);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#report(List, Type)}.
   * <ul>
   *   <li>When {@code RANGE}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#report(List, Type)}
   */
  @Test
  @DisplayName("Test report(List, Type); when 'RANGE'; then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.report(List, Type)"})
  void testReport_whenRange_thenCallsGetRunsLatestFirst() {
    // Arrange
    StandardCompactionJobStatusReporter standardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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

    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);

    // Act
    standardCompactionJobStatusReporter.report(jobStatusList, Type.RANGE);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardCompactionJobStatusReporter#report(List, Type)}.
   * <ul>
   *   <li>When {@code UNFINISHED}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardCompactionJobStatusReporter#report(List, Type)}
   */
  @Test
  @DisplayName("Test report(List, Type); when 'UNFINISHED'; then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardCompactionJobStatusReporter.report(List, Type)"})
  void testReport_whenUnfinished_thenCallsGetRunsLatestFirst() {
    // Arrange
    StandardCompactionJobStatusReporter standardCompactionJobStatusReporter = new StandardCompactionJobStatusReporter();
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

    ArrayList<CompactionJobStatus> jobStatusList = new ArrayList<>();
    jobStatusList.add(buildResult2);
    jobStatusList.add(buildResult);

    // Act
    standardCompactionJobStatusReporter.report(jobStatusList, Type.UNFINISHED);

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }
}
