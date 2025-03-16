package sleeper.clients.status.report;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.ingest.task.IngestTaskQuery;
import sleeper.clients.status.report.ingest.task.IngestTaskStatusReporter;
import sleeper.core.tracker.ingest.task.InMemoryIngestTaskTracker;
import sleeper.core.tracker.ingest.task.IngestTaskStatus;
import sleeper.core.tracker.ingest.task.IngestTaskTracker;

class IngestTaskStatusReportDiffblueTest {
  /**
   * Test {@link IngestTaskStatusReport#run()}.
   * <ul>
   *   <li>Given {@link IngestTaskStatusReporter} {@link IngestTaskStatusReporter#report(IngestTaskQuery, List)} does nothing.</li>
   *   <li>Then calls {@link IngestTaskStatusReporter#report(IngestTaskQuery, List)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); given IngestTaskStatusReporter report(IngestTaskQuery, List) does nothing; then calls report(IngestTaskQuery, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestTaskStatusReport.run()"})
  void testRun_givenIngestTaskStatusReporterReportDoesNothing_thenCallsReport() {
    // Arrange
    IngestTaskStatusReporter reporter = mock(IngestTaskStatusReporter.class);
    doNothing().when(reporter).report(Mockito.<IngestTaskQuery>any(), Mockito.<List<IngestTaskStatus>>any());
    IngestTaskQuery query = mock(IngestTaskQuery.class);
    when(query.run(Mockito.<IngestTaskTracker>any())).thenReturn(new ArrayList<>());

    // Act
    (new IngestTaskStatusReport(new InMemoryIngestTaskTracker(), reporter, query)).run();

    // Assert
    verify(query).run(isA(IngestTaskTracker.class));
    verify(reporter).report(isA(IngestTaskQuery.class), isA(List.class));
  }

  /**
   * Test {@link IngestTaskStatusReport#run()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestTaskStatusReport.run()"})
  void testRun_thenThrowIllegalArgumentException() {
    // Arrange
    IngestTaskQuery query = mock(IngestTaskQuery.class);
    when(query.run(Mockito.<IngestTaskTracker>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new IngestTaskStatusReport(new InMemoryIngestTaskTracker(), mock(IngestTaskStatusReporter.class), query))
            .run());
    verify(query).run(isA(IngestTaskTracker.class));
  }
}
