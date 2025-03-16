package sleeper.clients.status.report.ingest.job;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.job.query.JobQuery;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.core.tracker.ingest.job.query.IngestJobStatus;
import sleeper.core.tracker.ingest.job.query.IngestJobStatus.Builder;
import sleeper.core.tracker.job.run.JobRuns;

class StandardIngestJobStatusReporterDiffblueTest {
  /**
   * Test {@link StandardIngestJobStatusReporter#StandardIngestJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#StandardIngestJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new StandardIngestJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.<init>()"})
  void testNewStandardIngestJobStatusReporter_thenCallsGetRunsLatestFirst() {
    // Arrange and Act
    StandardIngestJobStatusReporter actualStandardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJobStatus.builder();
    IngestJobStatus buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns)
        .build();
    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();
    actualStandardIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#StandardIngestJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#StandardIngestJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new StandardIngestJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.<init>()"})
  void testNewStandardIngestJobStatusReporter_thenCallsGetRunsLatestFirst2() {
    // Arrange and Act
    StandardIngestJobStatusReporter actualStandardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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
    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();
    actualStandardIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#StandardIngestJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#StandardIngestJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new StandardIngestJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.<init>(PrintStream)"})
  void testNewStandardIngestJobStatusReporter_thenCallsGetRunsLatestFirst3() {
    // Arrange and Act
    StandardIngestJobStatusReporter actualStandardIngestJobStatusReporter = new StandardIngestJobStatusReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJobStatus.builder();
    IngestJobStatus buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns)
        .build();
    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();
    actualStandardIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#StandardIngestJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#StandardIngestJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new StandardIngestJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.<init>(PrintStream)"})
  void testNewStandardIngestJobStatusReporter_thenCallsGetRunsLatestFirst4() {
    // Arrange and Act
    StandardIngestJobStatusReporter actualStandardIngestJobStatusReporter = new StandardIngestJobStatusReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
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
    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();
    actualStandardIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>Given {@code Ingest Job Status Report}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code Ingest Job Status Report} is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); given 'Ingest Job Status Report'; when HashMap() 'Ingest Job Status Report' is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_givenIngestJobStatusReport_whenHashMapIngestJobStatusReportIsOne() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);
    doNothing().when(queueMessages).print(Mockito.<PrintStream>any());

    HashMap<String, Integer> persistentEmrStepCount = new HashMap<>();
    persistentEmrStepCount.put("Ingest Job Status Report", 1);

    // Act
    standardIngestJobStatusReporter.report(statusList, Type.ALL, queueMessages, persistentEmrStepCount);

    // Assert
    verify(queueMessages).print(isA(PrintStream.class));
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>Given {@link JobRuns} {@link JobRuns#getRunsLatestFirst()} return {@link ArrayList#ArrayList()}.</li>
   *   <li>When {@code ALL}.</li>
   *   <li>Then calls {@link IngestQueueMessages#print(PrintStream)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); given JobRuns getRunsLatestFirst() return ArrayList(); when 'ALL'; then calls print(PrintStream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_givenJobRunsGetRunsLatestFirstReturnArrayList_whenAll_thenCallsPrint() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);
    doNothing().when(queueMessages).print(Mockito.<PrintStream>any());

    // Act
    standardIngestJobStatusReporter.report(statusList, Type.ALL, queueMessages, new HashMap<>());

    // Assert
    verify(queueMessages).print(isA(PrintStream.class));
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>When {@code ALL}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); when 'ALL'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_whenAll_thenThrowRuntimeException() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);
    doThrow(new RuntimeException("Ingest Job Status Report")).when(queueMessages).print(Mockito.<PrintStream>any());

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> standardIngestJobStatusReporter.report(statusList, Type.ALL, queueMessages, new HashMap<>()));
    verify(queueMessages).print(isA(PrintStream.class));
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>When {@code DETAILED}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); when 'DETAILED'; then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_whenDetailed_thenCallsGetRunsLatestFirst() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);

    // Act
    standardIngestJobStatusReporter.report(statusList, Type.DETAILED, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>When {@code PROMPT}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); when 'PROMPT'; then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_whenPrompt_thenCallsGetRunsLatestFirst() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJobStatus.builder();
    IngestJobStatus buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRuns(jobRuns)
        .build();

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);

    // Act
    standardIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>When {@code PROMPT}.</li>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); when 'PROMPT'; then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_whenPrompt_thenCallsGetRunsLatestFirst2() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);

    // Act
    standardIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>When {@code RANGE}.</li>
   *   <li>Then calls {@link IngestQueueMessages#print(PrintStream)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); when 'RANGE'; then calls print(PrintStream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_whenRange_thenCallsPrint() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);
    doNothing().when(queueMessages).print(Mockito.<PrintStream>any());

    // Act
    standardIngestJobStatusReporter.report(statusList, Type.RANGE, queueMessages, new HashMap<>());

    // Assert
    verify(queueMessages).print(isA(PrintStream.class));
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>When {@code RANGE}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); when 'RANGE'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_whenRange_thenThrowRuntimeException() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);
    doThrow(new RuntimeException("Ingest Job Status Report")).when(queueMessages).print(Mockito.<PrintStream>any());

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> standardIngestJobStatusReporter.report(statusList, Type.RANGE, queueMessages, new HashMap<>()));
    verify(queueMessages).print(isA(PrintStream.class));
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>When {@code REJECTED}.</li>
   *   <li>Then calls {@link IngestQueueMessages#print(PrintStream)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); when 'REJECTED'; then calls print(PrintStream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_whenRejected_thenCallsPrint() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);
    doNothing().when(queueMessages).print(Mockito.<PrintStream>any());

    // Act
    standardIngestJobStatusReporter.report(statusList, Type.REJECTED, queueMessages, new HashMap<>());

    // Assert
    verify(queueMessages).print(isA(PrintStream.class));
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>When {@code REJECTED}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); when 'REJECTED'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_whenRejected_thenThrowRuntimeException() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);
    doThrow(new RuntimeException("Ingest Job Status Report")).when(queueMessages).print(Mockito.<PrintStream>any());

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> standardIngestJobStatusReporter.report(statusList, Type.REJECTED, queueMessages, new HashMap<>()));
    verify(queueMessages).print(isA(PrintStream.class));
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>When {@code UNFINISHED}.</li>
   *   <li>Then calls {@link IngestQueueMessages#print(PrintStream)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); when 'UNFINISHED'; then calls print(PrintStream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_whenUnfinished_thenCallsPrint() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);
    doNothing().when(queueMessages).print(Mockito.<PrintStream>any());

    // Act
    standardIngestJobStatusReporter.report(statusList, Type.UNFINISHED, queueMessages, new HashMap<>());

    // Assert
    verify(queueMessages).print(isA(PrintStream.class));
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }

  /**
   * Test {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>When {@code UNFINISHED}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); when 'UNFINISHED'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_whenUnfinished_thenThrowRuntimeException() {
    // Arrange
    StandardIngestJobStatusReporter standardIngestJobStatusReporter = new StandardIngestJobStatusReporter();
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

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult2);
    statusList.add(buildResult);
    IngestQueueMessages queueMessages = mock(IngestQueueMessages.class);
    doThrow(new RuntimeException("Ingest Job Status Report")).when(queueMessages).print(Mockito.<PrintStream>any());

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> standardIngestJobStatusReporter.report(statusList, Type.UNFINISHED, queueMessages, new HashMap<>()));
    verify(queueMessages).print(isA(PrintStream.class));
    verify(jobRuns2).getRunsLatestFirst();
    verify(jobRuns).getRunsLatestFirst();
  }
}
