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
import sleeper.clients.status.report.compaction.task.CompactionTaskQuery;
import sleeper.clients.status.report.compaction.task.CompactionTaskStatusReporter;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus;
import sleeper.core.tracker.compaction.task.CompactionTaskTracker;
import sleeper.core.tracker.compaction.task.InMemoryCompactionTaskTracker;

class CompactionTaskStatusReportDiffblueTest {
  /**
   * Test {@link CompactionTaskStatusReport#run()}.
   * <ul>
   *   <li>Given {@link CompactionTaskStatusReporter} {@link CompactionTaskStatusReporter#report(CompactionTaskQuery, List)} does nothing.</li>
   *   <li>Then calls {@link CompactionTaskStatusReporter#report(CompactionTaskQuery, List)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); given CompactionTaskStatusReporter report(CompactionTaskQuery, List) does nothing; then calls report(CompactionTaskQuery, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionTaskStatusReport.run()"})
  void testRun_givenCompactionTaskStatusReporterReportDoesNothing_thenCallsReport() {
    // Arrange
    CompactionTaskStatusReporter reporter = mock(CompactionTaskStatusReporter.class);
    doNothing().when(reporter).report(Mockito.<CompactionTaskQuery>any(), Mockito.<List<CompactionTaskStatus>>any());
    CompactionTaskQuery query = mock(CompactionTaskQuery.class);
    when(query.run(Mockito.<CompactionTaskTracker>any())).thenReturn(new ArrayList<>());

    // Act
    (new CompactionTaskStatusReport(new InMemoryCompactionTaskTracker(), reporter, query)).run();

    // Assert
    verify(query).run(isA(CompactionTaskTracker.class));
    verify(reporter).report(isA(CompactionTaskQuery.class), isA(List.class));
  }

  /**
   * Test {@link CompactionTaskStatusReport#run()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionTaskStatusReport.run()"})
  void testRun_thenThrowIllegalArgumentException() {
    // Arrange
    CompactionTaskQuery query = mock(CompactionTaskQuery.class);
    when(query.run(Mockito.<CompactionTaskTracker>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new CompactionTaskStatusReport(new InMemoryCompactionTaskTracker(),
            mock(CompactionTaskStatusReporter.class), query)).run());
    verify(query).run(isA(CompactionTaskTracker.class));
  }
}
