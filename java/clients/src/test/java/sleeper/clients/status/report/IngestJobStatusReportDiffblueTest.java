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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.ingest.job.IngestJobStatusReporter;
import sleeper.clients.status.report.ingest.job.IngestQueueMessages;
import sleeper.clients.status.report.job.query.AllJobsQuery;
import sleeper.clients.status.report.job.query.DetailedJobsQuery;
import sleeper.clients.status.report.job.query.JobQuery;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.clients.status.report.job.query.RangeJobsQuery;
import sleeper.clients.status.report.job.query.RejectedJobsQuery;
import sleeper.clients.status.report.job.query.UnfinishedJobsQuery;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.job.query.IngestJobStatus;
import sleeper.task.common.QueueMessageCount;
import sleeper.task.common.QueueMessageCount.Client;

class IngestJobStatusReportDiffblueTest {
  /**
   * Test {@link IngestJobStatusReport#run()}.
   * <p>
   * Method under test: {@link IngestJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestJobStatusReport.run()"})
  void testRun() {
    // Arrange
    IngestJobStatusReporter reporter = mock(IngestJobStatusReporter.class);
    doNothing().when(reporter)
        .report(Mockito.<List<IngestJobStatus>>any(), Mockito.<Type>any(), Mockito.<IngestQueueMessages>any(),
            Mockito.<Map<String, Integer>>any());
    InMemoryIngestJobTracker tracker = new InMemoryIngestJobTracker();
    RejectedJobsQuery query = new RejectedJobsQuery();
    Client queueClient = mock(Client.class);
    InstanceProperties properties = new InstanceProperties();

    // Act
    (new IngestJobStatusReport(tracker, query, reporter, queueClient, properties, new HashMap<>())).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.REJECTED), isA(IngestQueueMessages.class), isA(Map.class));
  }

  /**
   * Test {@link IngestJobStatusReport#run()}.
   * <p>
   * Method under test: {@link IngestJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestJobStatusReport.run()"})
  void testRun2() {
    // Arrange
    IngestJobStatusReporter reporter = mock(IngestJobStatusReporter.class);
    doNothing().when(reporter)
        .report(Mockito.<List<IngestJobStatus>>any(), Mockito.<Type>any(), Mockito.<IngestQueueMessages>any(),
            Mockito.<Map<String, Integer>>any());
    InMemoryIngestJobTracker tracker = new InMemoryIngestJobTracker();
    AllJobsQuery query = new AllJobsQuery(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client queueClient = mock(Client.class);
    InstanceProperties properties = new InstanceProperties();

    // Act
    (new IngestJobStatusReport(tracker, query, reporter, queueClient, properties, new HashMap<>())).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.ALL), isA(IngestQueueMessages.class), isA(Map.class));
  }

  /**
   * Test {@link IngestJobStatusReport#run()}.
   * <p>
   * Method under test: {@link IngestJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestJobStatusReport.run()"})
  void testRun3() {
    // Arrange
    IngestJobStatusReporter reporter = mock(IngestJobStatusReporter.class);
    doNothing().when(reporter)
        .report(Mockito.<List<IngestJobStatus>>any(), Mockito.<Type>any(), Mockito.<IngestQueueMessages>any(),
            Mockito.<Map<String, Integer>>any());
    InMemoryIngestJobTracker tracker = new InMemoryIngestJobTracker();
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Instant start = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    RangeJobsQuery query = new RangeJobsQuery(table, start,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Client queueClient = mock(Client.class);
    InstanceProperties properties = new InstanceProperties();

    // Act
    (new IngestJobStatusReport(tracker, query, reporter, queueClient, properties, new HashMap<>())).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.RANGE), isA(IngestQueueMessages.class), isA(Map.class));
  }

  /**
   * Test {@link IngestJobStatusReport#run()}.
   * <ul>
   *   <li>Given {@link DetailedJobsQuery#DetailedJobsQuery(List)} with jobIds is {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link IngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); given DetailedJobsQuery(List) with jobIds is ArrayList(); then calls report(List, Type, IngestQueueMessages, Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestJobStatusReport.run()"})
  void testRun_givenDetailedJobsQueryWithJobIdsIsArrayList_thenCallsReport() {
    // Arrange
    IngestJobStatusReporter reporter = mock(IngestJobStatusReporter.class);
    doNothing().when(reporter)
        .report(Mockito.<List<IngestJobStatus>>any(), Mockito.<Type>any(), Mockito.<IngestQueueMessages>any(),
            Mockito.<Map<String, Integer>>any());
    InMemoryIngestJobTracker tracker = new InMemoryIngestJobTracker();
    DetailedJobsQuery query = new DetailedJobsQuery(new ArrayList<>());
    Client queueClient = mock(Client.class);
    InstanceProperties properties = new InstanceProperties();

    // Act
    (new IngestJobStatusReport(tracker, query, reporter, queueClient, properties, new HashMap<>())).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.DETAILED), isA(IngestQueueMessages.class), isA(Map.class));
  }

  /**
   * Test {@link IngestJobStatusReport#run()}.
   * <ul>
   *   <li>Given {@link UnfinishedJobsQuery#UnfinishedJobsQuery(TableStatus)} with table is {@link TableStatus#uniqueIdAndName(String, String, boolean)}.</li>
   *   <li>Then calls {@link IngestJobStatusReporter#report(List, Type, IngestQueueMessages, Map)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); given UnfinishedJobsQuery(TableStatus) with table is uniqueIdAndName(String, String, boolean); then calls report(List, Type, IngestQueueMessages, Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestJobStatusReport.run()"})
  void testRun_givenUnfinishedJobsQueryWithTableIsUniqueIdAndName_thenCallsReport() {
    // Arrange
    IngestJobStatusReporter reporter = mock(IngestJobStatusReporter.class);
    doNothing().when(reporter)
        .report(Mockito.<List<IngestJobStatus>>any(), Mockito.<Type>any(), Mockito.<IngestQueueMessages>any(),
            Mockito.<Map<String, Integer>>any());
    InMemoryIngestJobTracker tracker = new InMemoryIngestJobTracker();
    UnfinishedJobsQuery query = new UnfinishedJobsQuery(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client queueClient = mock(Client.class);
    InstanceProperties properties = new InstanceProperties();

    // Act
    (new IngestJobStatusReport(tracker, query, reporter, queueClient, properties, new HashMap<>())).run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.UNFINISHED), isA(IngestQueueMessages.class), isA(Map.class));
  }

  /**
   * Test {@link IngestJobStatusReport#run()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestJobStatusReport.run()"})
  void testRun_thenThrowIllegalArgumentException() {
    // Arrange
    IngestJobStatusReporter reporter = mock(IngestJobStatusReporter.class);
    doThrow(new IllegalArgumentException("foo")).when(reporter)
        .report(Mockito.<List<IngestJobStatus>>any(), Mockito.<Type>any(), Mockito.<IngestQueueMessages>any(),
            Mockito.<Map<String, Integer>>any());
    InMemoryIngestJobTracker tracker = new InMemoryIngestJobTracker();
    RejectedJobsQuery query = new RejectedJobsQuery();
    Client queueClient = mock(Client.class);
    InstanceProperties properties = new InstanceProperties();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new IngestJobStatusReport(tracker, query, reporter, queueClient, properties, new HashMap<>())).run());
    verify(reporter).report(isA(List.class), eq(Type.REJECTED), isA(IngestQueueMessages.class), isA(Map.class));
  }
}
