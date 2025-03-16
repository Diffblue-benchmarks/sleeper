package sleeper.clients.status.report.ingest.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.statestore.PageThroughLogs;
import sleeper.core.tracker.ingest.task.IngestTaskFinishedStatus;
import sleeper.core.tracker.ingest.task.IngestTaskStatus;
import sleeper.core.tracker.ingest.task.IngestTaskStatus.Builder;

class IngestTaskStatusReporterDiffblueTest {
  /**
   * Test {@link IngestTaskStatusReporter#from(String, PrintStream)}.
   * <ul>
   *   <li>When {@code json}.</li>
   *   <li>Then return {@link JsonIngestTaskStatusReporter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatusReporter#from(String, PrintStream)}
   */
  @Test
  @DisplayName("Test from(String, PrintStream); when 'json'; then return JsonIngestTaskStatusReporter")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskStatusReporter IngestTaskStatusReporter.from(String, PrintStream)"})
  void testFrom_whenJson_thenReturnJsonIngestTaskStatusReporter() {
    // Arrange and Act
    IngestTaskStatusReporter actualFromResult = IngestTaskStatusReporter.from("json",
        new PrintStream(new ByteArrayOutputStream(1)));
    IngestTaskQuery ingestTaskQuery = mock(IngestTaskQuery.class);
    actualFromResult.report(ingestTaskQuery, new ArrayList<>());

    // Assert
    assertTrue(actualFromResult instanceof JsonIngestTaskStatusReporter);
  }

  /**
   * Test {@link IngestTaskStatusReporter#from(String, PrintStream)}.
   * <ul>
   *   <li>When {@code json}.</li>
   *   <li>Then return {@link JsonIngestTaskStatusReporter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatusReporter#from(String, PrintStream)}
   */
  @Test
  @DisplayName("Test from(String, PrintStream); when 'json'; then return JsonIngestTaskStatusReporter")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskStatusReporter IngestTaskStatusReporter.from(String, PrintStream)"})
  void testFrom_whenJson_thenReturnJsonIngestTaskStatusReporter2() {
    // Arrange and Act
    IngestTaskStatusReporter actualFromResult = IngestTaskStatusReporter.from("json",
        new PrintStream(new ByteArrayOutputStream(1)));
    IngestTaskQuery ingestTaskQuery = mock(IngestTaskQuery.class);
    ArrayList<IngestTaskStatus> ingestTaskStatusList = new ArrayList<>();
    Builder builderResult = IngestTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskFinishedStatus.Builder builderResult2 = IngestTaskFinishedStatus.builder();
    IngestTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(PageThroughLogs.PAGE_MIN_AGE)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    IngestTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();
    ingestTaskStatusList.add(buildResult);
    actualFromResult.report(ingestTaskQuery, ingestTaskStatusList);

    // Assert
    assertTrue(actualFromResult instanceof JsonIngestTaskStatusReporter);
  }
}
