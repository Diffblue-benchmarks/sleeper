package sleeper.clients.status.report.compaction.job;

import static org.mockito.Mockito.atLeast;
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

class JsonCompactionJobStatusReporterDiffblueTest {
  /**
   * Test {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new JsonCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonCompactionJobStatusReporter.<init>()"})
  void testNewJsonCompactionJobStatusReporter_thenCallsGetRunsLatestFirst() {
    // Arrange and Act
    JsonCompactionJobStatusReporter actualJsonCompactionJobStatusReporter = new JsonCompactionJobStatusReporter();
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
    ArrayList<CompactionJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult);
    actualJsonCompactionJobStatusReporter.report(statusList, Type.PROMPT);

    // Assert
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new JsonCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonCompactionJobStatusReporter.<init>()"})
  void testNewJsonCompactionJobStatusReporter_thenCallsGetRunsLatestFirst2() {
    // Arrange and Act
    JsonCompactionJobStatusReporter actualJsonCompactionJobStatusReporter = new JsonCompactionJobStatusReporter();
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
    ArrayList<CompactionJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    actualJsonCompactionJobStatusReporter.report(statusList, Type.PROMPT);

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new JsonCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonCompactionJobStatusReporter.<init>()"})
  void testNewJsonCompactionJobStatusReporter_thenCallsGetRunsLatestFirst3() {
    // Arrange and Act
    JsonCompactionJobStatusReporter actualJsonCompactionJobStatusReporter = new JsonCompactionJobStatusReporter();
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
        .partitionId("");
    CompactionJobCreatedStatus createdStatus2 = partitionIdResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder createdStatusResult2 = builderResult2.createdStatus(createdStatus2);
    CompactionJobStatus buildResult2 = createdStatusResult2
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns2)
        .build();
    ArrayList<CompactionJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    actualJsonCompactionJobStatusReporter.report(statusList, Type.PROMPT);

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new JsonCompactionJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonCompactionJobStatusReporter.<init>(PrintStream)"})
  void testNewJsonCompactionJobStatusReporter_thenCallsGetRunsLatestFirst4() {
    // Arrange and Act
    JsonCompactionJobStatusReporter actualJsonCompactionJobStatusReporter = new JsonCompactionJobStatusReporter(
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
    ArrayList<CompactionJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult);
    actualJsonCompactionJobStatusReporter.report(statusList, Type.PROMPT);

    // Assert
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new JsonCompactionJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonCompactionJobStatusReporter.<init>(PrintStream)"})
  void testNewJsonCompactionJobStatusReporter_thenCallsGetRunsLatestFirst5() {
    // Arrange and Act
    JsonCompactionJobStatusReporter actualJsonCompactionJobStatusReporter = new JsonCompactionJobStatusReporter(
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
    ArrayList<CompactionJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    actualJsonCompactionJobStatusReporter.report(statusList, Type.PROMPT);

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new JsonCompactionJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonCompactionJobStatusReporter.<init>(PrintStream)"})
  void testNewJsonCompactionJobStatusReporter_thenCallsGetRunsLatestFirst6() {
    // Arrange and Act
    JsonCompactionJobStatusReporter actualJsonCompactionJobStatusReporter = new JsonCompactionJobStatusReporter(
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
        .partitionId("");
    CompactionJobCreatedStatus createdStatus2 = partitionIdResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder createdStatusResult2 = builderResult2.createdStatus(createdStatus2);
    CompactionJobStatus buildResult2 = createdStatusResult2
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns2)
        .build();
    ArrayList<CompactionJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    actualJsonCompactionJobStatusReporter.report(statusList, Type.PROMPT);

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonCompactionJobStatusReporter#report(List, Type)}.
   * <p>
   * Method under test: {@link JsonCompactionJobStatusReporter#report(List, Type)}
   */
  @Test
  @DisplayName("Test report(List, Type)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonCompactionJobStatusReporter.report(List, Type)"})
  void testReport() {
    // Arrange
    JsonCompactionJobStatusReporter jsonCompactionJobStatusReporter = new JsonCompactionJobStatusReporter();
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
        .partitionId("");
    CompactionJobCreatedStatus createdStatus2 = partitionIdResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder createdStatusResult2 = builderResult2.createdStatus(createdStatus2);
    CompactionJobStatus buildResult2 = createdStatusResult2
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns2)
        .build();

    ArrayList<CompactionJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);

    // Act
    jsonCompactionJobStatusReporter.report(statusList, Type.PROMPT);

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonCompactionJobStatusReporter#report(List, Type)}.
   * <ul>
   *   <li>Given {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter()}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonCompactionJobStatusReporter#report(List, Type)}
   */
  @Test
  @DisplayName("Test report(List, Type); given JsonCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonCompactionJobStatusReporter.report(List, Type)"})
  void testReport_givenJsonCompactionJobStatusReporter_thenCallsGetRunsLatestFirst() {
    // Arrange
    JsonCompactionJobStatusReporter jsonCompactionJobStatusReporter = new JsonCompactionJobStatusReporter();
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

    ArrayList<CompactionJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult);

    // Act
    jsonCompactionJobStatusReporter.report(statusList, Type.PROMPT);

    // Assert
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonCompactionJobStatusReporter#report(List, Type)}.
   * <ul>
   *   <li>Given {@link JsonCompactionJobStatusReporter#JsonCompactionJobStatusReporter()}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonCompactionJobStatusReporter#report(List, Type)}
   */
  @Test
  @DisplayName("Test report(List, Type); given JsonCompactionJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonCompactionJobStatusReporter.report(List, Type)"})
  void testReport_givenJsonCompactionJobStatusReporter_thenCallsGetRunsLatestFirst2() {
    // Arrange
    JsonCompactionJobStatusReporter jsonCompactionJobStatusReporter = new JsonCompactionJobStatusReporter();
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

    ArrayList<CompactionJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);

    // Act
    jsonCompactionJobStatusReporter.report(statusList, Type.PROMPT);

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }
}
