package sleeper.clients.status.report.compaction.task;

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
import sleeper.core.tracker.compaction.task.CompactionTaskFinishedStatus;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus.Builder;

class CompactionTaskStatusReporterDiffblueTest {
  /**
   * Test {@link CompactionTaskStatusReporter#from(String, PrintStream)}.
   * <ul>
   *   <li>When {@code json}.</li>
   *   <li>Then return {@link JsonCompactionTaskStatusReporter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatusReporter#from(String, PrintStream)}
   */
  @Test
  @DisplayName("Test from(String, PrintStream); when 'json'; then return JsonCompactionTaskStatusReporter")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionTaskStatusReporter CompactionTaskStatusReporter.from(String, PrintStream)"})
  void testFrom_whenJson_thenReturnJsonCompactionTaskStatusReporter() {
    // Arrange and Act
    CompactionTaskStatusReporter actualFromResult = CompactionTaskStatusReporter.from("json",
        new PrintStream(new ByteArrayOutputStream(1)));
    CompactionTaskQuery compactionTaskQuery = mock(CompactionTaskQuery.class);
    actualFromResult.report(compactionTaskQuery, new ArrayList<>());

    // Assert
    assertTrue(actualFromResult instanceof JsonCompactionTaskStatusReporter);
  }

  /**
   * Test {@link CompactionTaskStatusReporter#from(String, PrintStream)}.
   * <ul>
   *   <li>When {@code json}.</li>
   *   <li>Then return {@link JsonCompactionTaskStatusReporter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatusReporter#from(String, PrintStream)}
   */
  @Test
  @DisplayName("Test from(String, PrintStream); when 'json'; then return JsonCompactionTaskStatusReporter")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionTaskStatusReporter CompactionTaskStatusReporter.from(String, PrintStream)"})
  void testFrom_whenJson_thenReturnJsonCompactionTaskStatusReporter2() {
    // Arrange and Act
    CompactionTaskStatusReporter actualFromResult = CompactionTaskStatusReporter.from("json",
        new PrintStream(new ByteArrayOutputStream(1)));
    CompactionTaskQuery compactionTaskQuery = mock(CompactionTaskQuery.class);
    ArrayList<CompactionTaskStatus> compactionTaskStatusList = new ArrayList<>();
    Builder builderResult = CompactionTaskStatus.builder();
    Builder expiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskFinishedStatus.Builder builderResult2 = CompactionTaskFinishedStatus.builder();
    CompactionTaskFinishedStatus finishedStatus = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsReadPerSecond(10.0d)
        .recordsWrittenPerSecond(10.0d)
        .timeSpentOnJobs(PageThroughLogs.PAGE_MIN_AGE)
        .totalJobRuns(1)
        .totalRecordsRead(1L)
        .totalRecordsWritten(1L)
        .build();
    Builder finishedStatusResult = expiryDateResult.finishedStatus(finishedStatus);
    CompactionTaskStatus buildResult = finishedStatusResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .taskId("42")
        .build();
    compactionTaskStatusList.add(buildResult);
    actualFromResult.report(compactionTaskQuery, compactionTaskStatusList);

    // Assert
    assertTrue(actualFromResult instanceof JsonCompactionTaskStatusReporter);
  }
}
