package sleeper.clients.status.report;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.filestatus.FileStatusReporter;
import sleeper.clients.status.report.filestatus.TableFilesStatus;
import sleeper.core.partition.Partition;
import sleeper.core.partition.Partition.Builder;
import sleeper.core.range.Region;
import sleeper.core.statestore.AllReferencesToAllFiles;
import sleeper.core.statestore.FileReferenceStoreQueries;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;

class FilesStatusReportDiffblueTest {
  /**
   * Test {@link FilesStatusReport#FilesStatusReport(StateStore, int, boolean, String)}.
   * <ul>
   *   <li>When {@code Output Type}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilesStatusReport#FilesStatusReport(StateStore, int, boolean, String)}
   */
  @Test
  @DisplayName("Test new FilesStatusReport(StateStore, int, boolean, String); when 'Output Type'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilesStatusReport.<init>(StateStore, int, boolean, String)"})
  void testNewFilesStatusReport_whenOutputType_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new FilesStatusReport(mock(StateStore.class), 3, true, "Output Type"));

  }

  /**
   * Test {@link FilesStatusReport#run()}.
   * <p>
   * Method under test: {@link FilesStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilesStatusReport.run()"})
  void testRun() throws StateStoreException {
    // Arrange
    ArrayList<Partition> partitionList = new ArrayList<>();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    partitionList.add(buildResult);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(partitionList);
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(FilesReportTestHelper.noFilesReport());

    // Act
    (new FilesStatusReport(stateStore, 3, true)).run();

    // Assert
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(3));
    verify(stateStore).getAllPartitions();
  }

  /**
   * Test {@link FilesStatusReport#run()}.
   * <p>
   * Method under test: {@link FilesStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilesStatusReport.run()"})
  void testRun2() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt()))
        .thenReturn(new AllReferencesToAllFiles(new ArrayList<>(), true));

    // Act
    (new FilesStatusReport(stateStore, 3, true)).run();

    // Assert
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(3));
    verify(stateStore).getAllPartitions();
  }

  /**
   * Test {@link FilesStatusReport#run()}.
   * <p>
   * Method under test: {@link FilesStatusReport#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilesStatusReport.run()"})
  void testRun3() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(FilesReportTestHelper.noFilesReport());

    // Act
    (new FilesStatusReport(stateStore, 3, false)).run();

    // Assert
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(3));
    verify(stateStore).getAllPartitions();
  }

  /**
   * Test {@link FilesStatusReport#run()}.
   * <ul>
   *   <li>Given {@link FileStatusReporter} {@link FileStatusReporter#report(TableFilesStatus, boolean)} does nothing.</li>
   *   <li>Then calls {@link FileStatusReporter#report(TableFilesStatus, boolean)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilesStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); given FileStatusReporter report(TableFilesStatus, boolean) does nothing; then calls report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilesStatusReport.run()"})
  void testRun_givenFileStatusReporterReportDoesNothing_thenCallsReport() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(FilesReportTestHelper.noFilesReport());
    FileStatusReporter fileStatusReporter = mock(FileStatusReporter.class);
    doNothing().when(fileStatusReporter).report(Mockito.<TableFilesStatus>any(), anyBoolean());

    // Act
    (new FilesStatusReport(stateStore, 3, true, fileStatusReporter)).run();

    // Assert
    verify(fileStatusReporter).report(isA(TableFilesStatus.class), eq(true));
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(3));
    verify(stateStore).getAllPartitions();
  }

  /**
   * Test {@link FilesStatusReport#run()}.
   * <ul>
   *   <li>Then calls {@link FileReferenceStoreQueries#getAllFilesWithMaxUnreferenced(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilesStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); then calls getAllFilesWithMaxUnreferenced(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilesStatusReport.run()"})
  void testRun_thenCallsGetAllFilesWithMaxUnreferenced() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(FilesReportTestHelper.noFilesReport());

    // Act
    (new FilesStatusReport(stateStore, 3, true)).run();

    // Assert
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(3));
    verify(stateStore).getAllPartitions();
  }

  /**
   * Test {@link FilesStatusReport#run()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilesStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilesStatusReport.run()"})
  void testRun_thenThrowIllegalArgumentException() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(FilesReportTestHelper.noFilesReport());
    FileStatusReporter fileStatusReporter = mock(FileStatusReporter.class);
    doThrow(new IllegalArgumentException("foo")).when(fileStatusReporter)
        .report(Mockito.<TableFilesStatus>any(), anyBoolean());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new FilesStatusReport(stateStore, 3, true, fileStatusReporter)).run());
    verify(fileStatusReporter).report(isA(TableFilesStatus.class), eq(true));
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(3));
    verify(stateStore).getAllPartitions();
  }
}
