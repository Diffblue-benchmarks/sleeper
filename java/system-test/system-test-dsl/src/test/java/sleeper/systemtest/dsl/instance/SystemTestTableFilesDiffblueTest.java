package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.AllReferencesToAllFiles;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceStoreQueries;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.PollWithRetries.CheckFailedException;
import sleeper.systemtest.dsl.util.TestContext;

class SystemTestTableFilesDiffblueTest {
  /**
   * Test {@link SystemTestTableFiles#all()}.
   * <ul>
   *   <li>Then return noFilesReport.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTableFiles#all()}
   */
  @Test
  @DisplayName("Test all(); then return noFilesReport")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles SystemTestTableFiles.all()"})
  void testAll_thenReturnNoFilesReport() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    AllReferencesToAllFiles noFilesReportResult = FilesReportTestHelper.noFilesReport();
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(noFilesReportResult);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore()).thenReturn(stateStore);

    // Act
    AllReferencesToAllFiles actualAllResult = (new SystemTestTableFiles(instance)).all();

    // Assert
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(10000));
    verify(instance).getStateStore();
    assertSame(noFilesReportResult, actualAllResult);
  }

  /**
   * Test {@link SystemTestTableFiles#references()}.
   * <ul>
   *   <li>Given {@link StateStore} {@link FileReferenceStoreQueries#getFileReferences()} return {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTableFiles#references()}
   */
  @Test
  @DisplayName("Test references(); given StateStore getFileReferences() return ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SystemTestTableFiles.references()"})
  void testReferences_givenStateStoreGetFileReferencesReturnArrayList_thenReturnEmpty() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(new ArrayList<>());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore()).thenReturn(stateStore);

    // Act
    List<FileReference> actualReferencesResult = (new SystemTestTableFiles(instance)).references();

    // Assert
    verify(stateStore).getFileReferences();
    verify(instance).getStateStore();
    assertTrue(actualReferencesResult.isEmpty());
  }

  /**
   * Test {@link SystemTestTableFiles#recordsByFilename()}.
   * <p>
   * Method under test: {@link SystemTestTableFiles#recordsByFilename()}
   */
  @Test
  @DisplayName("Test recordsByFilename()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map SystemTestTableFiles.recordsByFilename()"})
  void testRecordsByFilename() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(FilesReportTestHelper.noFilesReport());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore()).thenReturn(stateStore);

    // Act
    Map<String, Long> actualRecordsByFilenameResult = (new SystemTestTableFiles(instance)).recordsByFilename();

    // Assert
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(10000));
    verify(instance).getStateStore();
    assertTrue(actualRecordsByFilenameResult.isEmpty());
  }

  /**
   * Test {@link SystemTestTableFiles#recordsByFilename()}.
   * <ul>
   *   <li>Then calls {@link AllReferencesToAllFiles#recordsByFilename()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTableFiles#recordsByFilename()}
   */
  @Test
  @DisplayName("Test recordsByFilename(); then calls recordsByFilename()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map SystemTestTableFiles.recordsByFilename()"})
  void testRecordsByFilename_thenCallsRecordsByFilename() throws StateStoreException {
    // Arrange
    AllReferencesToAllFiles allReferencesToAllFiles = mock(AllReferencesToAllFiles.class);
    when(allReferencesToAllFiles.recordsByFilename()).thenReturn(new HashMap<>());
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(allReferencesToAllFiles);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore()).thenReturn(stateStore);

    // Act
    Map<String, Long> actualRecordsByFilenameResult = (new SystemTestTableFiles(instance)).recordsByFilename();

    // Assert
    verify(allReferencesToAllFiles).recordsByFilename();
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(10000));
    verify(instance).getStateStore();
    assertTrue(actualRecordsByFilenameResult.isEmpty());
  }

  /**
   * Test {@link SystemTestTableFiles#waitForState(Predicate, PollWithRetries)}.
   * <p>
   * Method under test: {@link SystemTestTableFiles#waitForState(Predicate, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitForState(Predicate, PollWithRetries)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestTableFiles SystemTestTableFiles.waitForState(Predicate, PollWithRetries)"})
  void testWaitForState() throws InterruptedException, CheckFailedException {
    // Arrange
    SystemTestTableFiles systemTestTableFiles = new SystemTestTableFiles(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)));
    Predicate<AllReferencesToAllFiles> stateCheck = mock(Predicate.class);
    PollWithRetries poll = mock(PollWithRetries.class);
    doNothing().when(poll).pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act
    SystemTestTableFiles actualWaitForStateResult = systemTestTableFiles.waitForState(stateCheck, poll);

    // Assert
    verify(poll).pollUntil(eq("files meet expected state"), isA(BooleanSupplier.class));
    assertSame(systemTestTableFiles, actualWaitForStateResult);
  }

  /**
   * Test {@link SystemTestTableFiles#filesByTable()}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link TableProperties#get(TableProperty)} return {@code Get}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTableFiles#filesByTable()}
   */
  @Test
  @DisplayName("Test filesByTable(); given TableProperties get(TableProperty) return 'Get'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map SystemTestTableFiles.filesByTable()"})
  void testFilesByTable_givenTablePropertiesGetReturnGet_thenReturnSizeIsOne() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(tableProperties);
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    StateStore stateStore = mock(StateStore.class);
    AllReferencesToAllFiles noFilesReportResult = FilesReportTestHelper.noFilesReport();
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(noFilesReportResult);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    when(instance.streamTableProperties()).thenReturn(streamResult);

    // Act
    Map<String, AllReferencesToAllFiles> actualFilesByTableResult = (new SystemTestTableFiles(instance)).filesByTable();

    // Assert
    verify(tableProperties).get(isA(TableProperty.class));
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(100));
    verify(instance).getStateStore(isA(TableProperties.class));
    verify(instance).streamTableProperties();
    assertEquals(1, actualFilesByTableResult.size());
    assertSame(noFilesReportResult, actualFilesByTableResult.get("Get"));
  }

  /**
   * Test {@link SystemTestTableFiles#filesByTable()}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTableFiles#filesByTable()}
   */
  @Test
  @DisplayName("Test filesByTable(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map SystemTestTableFiles.filesByTable()"})
  void testFilesByTable_thenReturnEmpty() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    when(instance.streamTableProperties()).thenReturn(streamResult);

    // Act
    Map<String, AllReferencesToAllFiles> actualFilesByTableResult = (new SystemTestTableFiles(instance)).filesByTable();

    // Assert
    verify(instance).streamTableProperties();
    assertTrue(actualFilesByTableResult.isEmpty());
  }

  /**
   * Test {@link SystemTestTableFiles#referencesByTable()}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link TableProperties#get(TableProperty)} return {@code Get}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTableFiles#referencesByTable()}
   */
  @Test
  @DisplayName("Test referencesByTable(); given TableProperties get(TableProperty) return 'Get'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map SystemTestTableFiles.referencesByTable()"})
  void testReferencesByTable_givenTablePropertiesGetReturnGet_thenReturnSizeIsOne() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(tableProperties);
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(new ArrayList<>());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    when(instance.streamTableProperties()).thenReturn(streamResult);

    // Act
    Map<String, List<FileReference>> actualReferencesByTableResult = (new SystemTestTableFiles(instance))
        .referencesByTable();

    // Assert
    verify(tableProperties).get(isA(TableProperty.class));
    verify(stateStore).getFileReferences();
    verify(instance).getStateStore(isA(TableProperties.class));
    verify(instance).streamTableProperties();
    assertEquals(1, actualReferencesByTableResult.size());
    assertTrue(actualReferencesByTableResult.get("Get").isEmpty());
  }

  /**
   * Test {@link SystemTestTableFiles#referencesByTable()}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTableFiles#referencesByTable()}
   */
  @Test
  @DisplayName("Test referencesByTable(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map SystemTestTableFiles.referencesByTable()"})
  void testReferencesByTable_thenReturnEmpty() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    when(instance.streamTableProperties()).thenReturn(streamResult);

    // Act
    Map<String, List<FileReference>> actualReferencesByTableResult = (new SystemTestTableFiles(instance))
        .referencesByTable();

    // Assert
    verify(instance).streamTableProperties();
    assertTrue(actualReferencesByTableResult.isEmpty());
  }
}
