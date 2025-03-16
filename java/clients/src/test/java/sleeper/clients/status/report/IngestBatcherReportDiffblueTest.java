package sleeper.clients.status.report;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.ingest.batcher.BatcherQuery;
import sleeper.clients.status.report.ingest.batcher.BatcherQuery.Type;
import sleeper.clients.status.report.ingest.batcher.IngestBatcherReporter;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableStatusProvider;
import sleeper.ingest.batcher.core.FileIngestRequest;
import sleeper.ingest.batcher.core.testutil.InMemoryIngestBatcherStore;

class IngestBatcherReportDiffblueTest {
  /**
   * Test {@link IngestBatcherReport#run()}.
   * <ul>
   *   <li>Then calls {@link IngestBatcherReporter#report(List, Type, TableStatusProvider)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestBatcherReport#run()}
   */
  @Test
  @DisplayName("Test run(); then calls report(List, Type, TableStatusProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestBatcherReport.run()"})
  void testRun_thenCallsReport() {
    // Arrange
    IngestBatcherReporter reporter = mock(IngestBatcherReporter.class);
    doNothing().when(reporter)
        .report(Mockito.<List<FileIngestRequest>>any(), Mockito.<Type>any(), Mockito.<TableStatusProvider>any());
    InMemoryIngestBatcherStore batcherStore = new InMemoryIngestBatcherStore();

    // Act
    (new IngestBatcherReport(batcherStore, reporter, Type.ALL, new TableStatusProvider(new InMemoryTableIndex())))
        .run();

    // Assert
    verify(reporter).report(isA(List.class), eq(Type.ALL), isA(TableStatusProvider.class));
  }

  /**
   * Test {@link IngestBatcherReport#run()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestBatcherReport#run()}
   */
  @Test
  @DisplayName("Test run(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestBatcherReport.run()"})
  void testRun_thenThrowIllegalArgumentException() {
    // Arrange
    IngestBatcherReporter reporter = mock(IngestBatcherReporter.class);
    doThrow(new IllegalArgumentException("foo")).when(reporter)
        .report(Mockito.<List<FileIngestRequest>>any(), Mockito.<Type>any(), Mockito.<TableStatusProvider>any());
    InMemoryIngestBatcherStore batcherStore = new InMemoryIngestBatcherStore();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new IngestBatcherReport(batcherStore, reporter, Type.ALL,
        new TableStatusProvider(new InMemoryTableIndex()))).run());
    verify(reporter).report(isA(List.class), eq(Type.ALL), isA(TableStatusProvider.class));
  }
}
