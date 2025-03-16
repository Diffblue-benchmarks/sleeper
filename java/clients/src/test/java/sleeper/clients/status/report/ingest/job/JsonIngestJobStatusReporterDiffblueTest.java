package sleeper.clients.status.report.ingest.job;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.query.JobQuery;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.core.tracker.ingest.job.query.IngestJobStatus;
import sleeper.core.tracker.ingest.job.query.IngestJobStatus.Builder;
import sleeper.core.tracker.job.run.JobRuns;

class JsonIngestJobStatusReporterDiffblueTest {
  /**
   * Test {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new JsonIngestJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.<init>()"})
  void testNewJsonIngestJobStatusReporter_thenCallsGetRunsLatestFirst() {
    // Arrange and Act
    JsonIngestJobStatusReporter actualJsonIngestJobStatusReporter = new JsonIngestJobStatusReporter();
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
    actualJsonIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new JsonIngestJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.<init>()"})
  void testNewJsonIngestJobStatusReporter_thenCallsGetRunsLatestFirst2() {
    // Arrange and Act
    JsonIngestJobStatusReporter actualJsonIngestJobStatusReporter = new JsonIngestJobStatusReporter();
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
    actualJsonIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new JsonIngestJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.<init>()"})
  void testNewJsonIngestJobStatusReporter_thenCallsGetRunsLatestFirst3() {
    // Arrange and Act
    JsonIngestJobStatusReporter actualJsonIngestJobStatusReporter = new JsonIngestJobStatusReporter();
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
        .jobId("")
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
    actualJsonIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter()}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter()}
   */
  @Test
  @DisplayName("Test new JsonIngestJobStatusReporter(); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.<init>()"})
  void testNewJsonIngestJobStatusReporter_thenCallsGetRunsLatestFirst4() {
    // Arrange and Act
    JsonIngestJobStatusReporter actualJsonIngestJobStatusReporter = new JsonIngestJobStatusReporter();
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
    HashMap<String, Integer> persistentEmrStepCount = new HashMap<>();
    persistentEmrStepCount.put("queueMessages", 1);
    actualJsonIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, persistentEmrStepCount);

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new JsonIngestJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.<init>(PrintStream)"})
  void testNewJsonIngestJobStatusReporter_thenCallsGetRunsLatestFirst5() {
    // Arrange and Act
    JsonIngestJobStatusReporter actualJsonIngestJobStatusReporter = new JsonIngestJobStatusReporter(
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
    actualJsonIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new JsonIngestJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.<init>(PrintStream)"})
  void testNewJsonIngestJobStatusReporter_thenCallsGetRunsLatestFirst6() {
    // Arrange and Act
    JsonIngestJobStatusReporter actualJsonIngestJobStatusReporter = new JsonIngestJobStatusReporter(
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
    actualJsonIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new JsonIngestJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.<init>(PrintStream)"})
  void testNewJsonIngestJobStatusReporter_thenCallsGetRunsLatestFirst7() {
    // Arrange and Act
    JsonIngestJobStatusReporter actualJsonIngestJobStatusReporter = new JsonIngestJobStatusReporter(
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
        .jobId("")
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
    actualJsonIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, new HashMap<>());

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter(PrintStream)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#JsonIngestJobStatusReporter(PrintStream)}
   */
  @Test
  @DisplayName("Test new JsonIngestJobStatusReporter(PrintStream); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.<init>(PrintStream)"})
  void testNewJsonIngestJobStatusReporter_thenCallsGetRunsLatestFirst8() {
    // Arrange and Act
    JsonIngestJobStatusReporter actualJsonIngestJobStatusReporter = new JsonIngestJobStatusReporter(
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
    HashMap<String, Integer> persistentEmrStepCount = new HashMap<>();
    persistentEmrStepCount.put("queueMessages", 1);
    actualJsonIngestJobStatusReporter.report(statusList, Type.PROMPT, queueMessages, persistentEmrStepCount);

    // Assert
    verify(jobRuns2, atLeast(1)).getRunsLatestFirst();
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport() {
    // Arrange
    JsonIngestJobStatusReporter jsonIngestJobStatusReporter = new JsonIngestJobStatusReporter();
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJobStatus.builder();
    IngestJobStatus buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("")
        .jobRuns(jobRuns)
        .build();

    ArrayList<IngestJobStatus> statusList = new ArrayList<>();
    statusList.add(buildResult);

    // Act
    jsonIngestJobStatusReporter.report(statusList, Type.PROMPT, null, new HashMap<>());

    // Assert
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>Given {@code queueMessages}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code queueMessages} is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); given 'queueMessages'; when HashMap() 'queueMessages' is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_givenQueueMessages_whenHashMapQueueMessagesIsOne() {
    // Arrange
    JsonIngestJobStatusReporter jsonIngestJobStatusReporter = new JsonIngestJobStatusReporter();
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

    HashMap<String, Integer> persistentEmrStepCount = new HashMap<>();
    persistentEmrStepCount.put("queueMessages", 1);

    // Act
    jsonIngestJobStatusReporter.report(statusList, Type.PROMPT, null, persistentEmrStepCount);

    // Assert
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }

  /**
   * Test {@link JsonIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.
   * <ul>
   *   <li>Then calls {@link JobRuns#getRunsLatestFirst()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonIngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}
   */
  @Test
  @DisplayName("Test report(List, Type, IngestQueueMessages, Map); then calls getRunsLatestFirst()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonIngestJobStatusReporter.report(List, Type, IngestQueueMessages, Map)"})
  void testReport_thenCallsGetRunsLatestFirst() {
    // Arrange
    JsonIngestJobStatusReporter jsonIngestJobStatusReporter = new JsonIngestJobStatusReporter();
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

    // Act
    jsonIngestJobStatusReporter.report(statusList, Type.PROMPT, null, new HashMap<>());

    // Assert
    verify(jobRuns, atLeast(1)).getRunsLatestFirst();
  }
}
