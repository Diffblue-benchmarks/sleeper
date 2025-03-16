package sleeper.clients.status.report.ingest.batcher;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.ingest.batcher.BatcherQuery.Type;
import sleeper.core.table.TableIndex;
import sleeper.core.table.TableStatus;
import sleeper.core.table.TableStatusProvider;
import sleeper.ingest.batcher.core.FileIngestRequest;
import sleeper.ingest.batcher.core.FileIngestRequest.Builder;

class StandardIngestBatcherReporterDiffblueTest {
  /**
   * Test {@link StandardIngestBatcherReporter#report(List, Type, TableStatusProvider)}.
   * <ul>
   *   <li>Then calls {@link TableStatusProvider#getById(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestBatcherReporter#report(List, Type, TableStatusProvider)}
   */
  @Test
  @DisplayName("Test report(List, Type, TableStatusProvider); then calls getById(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestBatcherReporter.report(List, Type, TableStatusProvider)"})
  void testReport_thenCallsGetById() {
    // Arrange
    StandardIngestBatcherReporter standardIngestBatcherReporter = new StandardIngestBatcherReporter();

    ArrayList<FileIngestRequest> statusList = new ArrayList<>();
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();
    statusList.add(buildResult);
    TableStatusProvider tableProvider = mock(TableStatusProvider.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableProvider.getById(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    standardIngestBatcherReporter.report(statusList, Type.PROMPT, tableProvider);

    // Assert
    verify(tableProvider).getById(eq("42"));
  }

  /**
   * Test {@link StandardIngestBatcherReporter#report(List, Type, TableStatusProvider)}.
   * <ul>
   *   <li>Then calls {@link TableIndex#getTableByUniqueId(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardIngestBatcherReporter#report(List, Type, TableStatusProvider)}
   */
  @Test
  @DisplayName("Test report(List, Type, TableStatusProvider); then calls getTableByUniqueId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardIngestBatcherReporter.report(List, Type, TableStatusProvider)"})
  void testReport_thenCallsGetTableByUniqueId() {
    // Arrange
    StandardIngestBatcherReporter standardIngestBatcherReporter = new StandardIngestBatcherReporter();

    ArrayList<FileIngestRequest> statusList = new ArrayList<>();
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();
    statusList.add(buildResult);
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    standardIngestBatcherReporter.report(statusList, Type.PROMPT, new TableStatusProvider(tableIndex));

    // Assert
    verify(tableIndex).getTableByUniqueId(eq("42"));
  }
}
