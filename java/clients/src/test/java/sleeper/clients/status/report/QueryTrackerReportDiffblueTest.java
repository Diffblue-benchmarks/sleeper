package sleeper.clients.status.report;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import sleeper.clients.status.report.query.QueryTrackerReporter;
import sleeper.clients.status.report.query.TrackerQuery;
import sleeper.query.core.tracker.QueryTrackerStore;
import sleeper.query.core.tracker.TrackedQuery;

class QueryTrackerReportDiffblueTest {
  /**
   * Test {@link QueryTrackerReport#run()}.
   * <ul>
   *   <li>Given {@link QueryTrackerReporter} {@link QueryTrackerReporter#report(TrackerQuery, List)} does nothing.</li>
   *   <li>Then calls {@link QueryTrackerReporter#report(TrackerQuery, List)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryTrackerReport#run()}
   */
  @Test
  @DisplayName("Test run(); given QueryTrackerReporter report(TrackerQuery, List) does nothing; then calls report(TrackerQuery, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryTrackerReport.run()"})
  void testRun_givenQueryTrackerReporterReportDoesNothing_thenCallsReport() {
    // Arrange
    TrackerQuery queryType = mock(TrackerQuery.class);
    when(queryType.run(Mockito.<QueryTrackerStore>any())).thenReturn(new ArrayList<>());
    QueryTrackerReporter reporter = mock(QueryTrackerReporter.class);
    doNothing().when(reporter).report(Mockito.<TrackerQuery>any(), Mockito.<List<TrackedQuery>>any());

    // Act
    (new QueryTrackerReport(null, queryType, reporter)).run();

    // Assert
    verify(reporter).report(isA(TrackerQuery.class), isA(List.class));
    verify(queryType).run(isNull());
  }

  /**
   * Test {@link QueryTrackerReport#run()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryTrackerReport#run()}
   */
  @Test
  @DisplayName("Test run(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryTrackerReport.run()"})
  void testRun_thenThrowIllegalArgumentException() {
    // Arrange
    TrackerQuery queryType = mock(TrackerQuery.class);
    when(queryType.run(Mockito.<QueryTrackerStore>any())).thenReturn(new ArrayList<>());
    QueryTrackerReporter reporter = mock(QueryTrackerReporter.class);
    doThrow(new IllegalArgumentException("foo")).when(reporter)
        .report(Mockito.<TrackerQuery>any(), Mockito.<List<TrackedQuery>>any());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new QueryTrackerReport(null, queryType, reporter)).run());
    verify(reporter).report(isA(TrackerQuery.class), isA(List.class));
    verify(queryType).run(isNull());
  }
}
