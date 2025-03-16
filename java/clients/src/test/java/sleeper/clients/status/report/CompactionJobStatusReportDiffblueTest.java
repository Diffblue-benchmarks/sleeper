package sleeper.clients.status.report;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.compaction.job.CompactionJobStatusReporter;
import sleeper.clients.status.report.job.query.AllJobsQuery;
import sleeper.clients.status.report.job.query.DetailedJobsQuery;
import sleeper.clients.status.report.job.query.JobQuery;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.clients.status.report.job.query.RangeJobsQuery;
import sleeper.clients.status.report.job.query.RejectedJobsQuery;
import sleeper.clients.status.report.job.query.UnfinishedJobsQuery;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;

class CompactionJobStatusReportDiffblueTest {
  /**
   * Test {@link CompactionJobStatusReport#run()}.
   * <p>
   * Method under test: {@link CompactionJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobStatusReport.run()"})
  void testRun() {
    // Arrange
    CompactionJobStatusReporter reporter = mock(CompactionJobStatusReporter.class);
    doNothing().when(reporter).report(Mockito.<List<CompactionJobStatus>>any(), Mockito.<Type>any());
    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();

    // Act
    (new CompactionJobStatusReport(compactionJobTracker, reporter, new RejectedJobsQuery())).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.REJECTED));
  }

  /**
   * Test {@link CompactionJobStatusReport#run()}.
   * <p>
   * Method under test: {@link CompactionJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobStatusReport.run()"})
  void testRun2() {
    // Arrange
    CompactionJobStatusReporter reporter = mock(CompactionJobStatusReporter.class);
    doNothing().when(reporter).report(Mockito.<List<CompactionJobStatus>>any(), Mockito.<Type>any());
    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();

    // Act
    (new CompactionJobStatusReport(compactionJobTracker, reporter,
        new AllJobsQuery(TableStatus.uniqueIdAndName("42", "Table Name", true)))).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.ALL));
  }

  /**
   * Test {@link CompactionJobStatusReport#run()}.
   * <p>
   * Method under test: {@link CompactionJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobStatusReport.run()"})
  void testRun3() {
    // Arrange
    CompactionJobStatusReporter reporter = mock(CompactionJobStatusReporter.class);
    doNothing().when(reporter).report(Mockito.<List<CompactionJobStatus>>any(), Mockito.<Type>any());
    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();

    // Act
    (new CompactionJobStatusReport(compactionJobTracker, reporter, new DetailedJobsQuery(new ArrayList<>()))).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.DETAILED));
  }

  /**
   * Test {@link CompactionJobStatusReport#run()}.
   * <p>
   * Method under test: {@link CompactionJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobStatusReport.run()"})
  void testRun4() {
    // Arrange
    CompactionJobStatusReporter reporter = mock(CompactionJobStatusReporter.class);
    doNothing().when(reporter).report(Mockito.<List<CompactionJobStatus>>any(), Mockito.<Type>any());
    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Instant start = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    (new CompactionJobStatusReport(compactionJobTracker, reporter,
        new RangeJobsQuery(table, start, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())))
        .run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.RANGE));
  }

  /**
   * Test {@link CompactionJobStatusReport#run()}.
   * <p>
   * Method under test: {@link CompactionJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobStatusReport.run()"})
  void testRun5() {
    // Arrange
    CompactionJobStatusReporter reporter = mock(CompactionJobStatusReporter.class);
    doNothing().when(reporter).report(Mockito.<List<CompactionJobStatus>>any(), Mockito.<Type>any());
    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();

    // Act
    (new CompactionJobStatusReport(compactionJobTracker, reporter,
        new UnfinishedJobsQuery(TableStatus.uniqueIdAndName("42", "Table Name", true)))).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.UNFINISHED));
  }

  /**
   * Test {@link CompactionJobStatusReport#run()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then calls {@link CompactionJobStatusReporter#report(List, Type)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); given ArrayList() add '42'; then calls report(List, Type)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobStatusReport.run()"})
  void testRun_givenArrayListAdd42_thenCallsReport() {
    // Arrange
    CompactionJobStatusReporter reporter = mock(CompactionJobStatusReporter.class);
    doNothing().when(reporter).report(Mockito.<List<CompactionJobStatus>>any(), Mockito.<Type>any());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("42");
    jobIds.add("foo");
    DetailedJobsQuery query = new DetailedJobsQuery(jobIds);

    // Act
    (new CompactionJobStatusReport(new InMemoryCompactionJobTracker(), reporter, query)).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.DETAILED));
  }

  /**
   * Test {@link CompactionJobStatusReport#run()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then calls {@link CompactionJobStatusReporter#report(List, Type)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); given ArrayList() add 'foo'; then calls report(List, Type)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobStatusReport.run()"})
  void testRun_givenArrayListAddFoo_thenCallsReport() {
    // Arrange
    CompactionJobStatusReporter reporter = mock(CompactionJobStatusReporter.class);
    doNothing().when(reporter).report(Mockito.<List<CompactionJobStatus>>any(), Mockito.<Type>any());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("foo");
    DetailedJobsQuery query = new DetailedJobsQuery(jobIds);

    // Act
    (new CompactionJobStatusReport(new InMemoryCompactionJobTracker(), reporter, query)).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.DETAILED));
  }

  /**
   * Test {@link CompactionJobStatusReport#run()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobStatusReport.run()"})
  void testRun_thenThrowIllegalArgumentException() {
    // Arrange
    CompactionJobStatusReporter reporter = mock(CompactionJobStatusReporter.class);
    doThrow(new IllegalArgumentException("foo")).when(reporter)
        .report(Mockito.<List<CompactionJobStatus>>any(), Mockito.<Type>any());
    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new CompactionJobStatusReport(compactionJobTracker, reporter, new RejectedJobsQuery())).run());
    verify(reporter).report(isA(List.class), eq(Type.REJECTED));
  }
}
