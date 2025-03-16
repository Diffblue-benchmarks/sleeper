package sleeper.core.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.FileReference.Builder;

class AllReferencesToAllFilesDiffblueTest {
  /**
   * Test {@link AllReferencesToAllFiles#AllReferencesToAllFiles(Collection, boolean)}.
   * <ul>
   *   <li>Then return FilesWithNoReferences size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#AllReferencesToAllFiles(Collection, boolean)}
   */
  @Test
  @DisplayName("Test new AllReferencesToAllFiles(Collection, boolean); then return FilesWithNoReferences size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AllReferencesToAllFiles.<init>(Collection, boolean)"})
  void testNewAllReferencesToAllFiles_thenReturnFilesWithNoReferencesSizeIsOne() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act
    AllReferencesToAllFiles actualAllReferencesToAllFiles = new AllReferencesToAllFiles(files, true);

    // Assert
    assertEquals(1, actualAllReferencesToAllFiles.getFiles().size());
    assertEquals(1, actualAllReferencesToAllFiles.getFilesWithNoReferences().size());
    assertTrue(actualAllReferencesToAllFiles.getFilesWithReferences().isEmpty());
    assertTrue(actualAllReferencesToAllFiles.isMoreThanMax());
  }

  /**
   * Test {@link AllReferencesToAllFiles#AllReferencesToAllFiles(Collection, boolean)}.
   * <ul>
   *   <li>Then return FilesWithNoReferences size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#AllReferencesToAllFiles(Collection, boolean)}
   */
  @Test
  @DisplayName("Test new AllReferencesToAllFiles(Collection, boolean); then return FilesWithNoReferences size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AllReferencesToAllFiles.<init>(Collection, boolean)"})
  void testNewAllReferencesToAllFiles_thenReturnFilesWithNoReferencesSizeIsOne2() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    AllReferencesToAFile.Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    files.add(buildResult2);

    // Act
    AllReferencesToAllFiles actualAllReferencesToAllFiles = new AllReferencesToAllFiles(files, true);

    // Assert
    assertEquals(1, actualAllReferencesToAllFiles.getFiles().size());
    assertEquals(1, actualAllReferencesToAllFiles.getFilesWithNoReferences().size());
    assertTrue(actualAllReferencesToAllFiles.getFilesWithReferences().isEmpty());
    assertTrue(actualAllReferencesToAllFiles.isMoreThanMax());
  }

  /**
   * Test {@link AllReferencesToAllFiles#AllReferencesToAllFiles(Collection, boolean)}.
   * <ul>
   *   <li>Then return FilesWithReferences size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#AllReferencesToAllFiles(Collection, boolean)}
   */
  @Test
  @DisplayName("Test new AllReferencesToAllFiles(Collection, boolean); then return FilesWithReferences size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AllReferencesToAllFiles.<init>(Collection, boolean)"})
  void testNewAllReferencesToAllFiles_thenReturnFilesWithReferencesSizeIsOne() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult2 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    files.add(buildResult2);

    // Act
    AllReferencesToAllFiles actualAllReferencesToAllFiles = new AllReferencesToAllFiles(files, true);

    // Assert
    assertEquals(1, actualAllReferencesToAllFiles.getFiles().size());
    assertEquals(1, actualAllReferencesToAllFiles.getFilesWithReferences().size());
    assertTrue(actualAllReferencesToAllFiles.getFilesWithNoReferences().isEmpty());
    assertTrue(actualAllReferencesToAllFiles.isMoreThanMax());
  }

  /**
   * Test {@link AllReferencesToAllFiles#AllReferencesToAllFiles(Collection, boolean)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Files Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#AllReferencesToAllFiles(Collection, boolean)}
   */
  @Test
  @DisplayName("Test new AllReferencesToAllFiles(Collection, boolean); when ArrayList(); then return Files Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AllReferencesToAllFiles.<init>(Collection, boolean)"})
  void testNewAllReferencesToAllFiles_whenArrayList_thenReturnFilesEmpty() {
    // Arrange and Act
    AllReferencesToAllFiles actualAllReferencesToAllFiles = new AllReferencesToAllFiles(new ArrayList<>(), true);

    // Assert
    assertTrue(actualAllReferencesToAllFiles.getFiles().isEmpty());
    assertTrue(actualAllReferencesToAllFiles.getFilesWithNoReferences().isEmpty());
    assertTrue(actualAllReferencesToAllFiles.getFilesWithReferences().isEmpty());
    assertTrue(actualAllReferencesToAllFiles.isMoreThanMax());
  }

  /**
   * Test {@link AllReferencesToAllFiles#getFiles()}.
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#getFiles()}
   */
  @Test
  @DisplayName("Test getFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Collection AllReferencesToAllFiles.getFiles()"})
  void testGetFiles() {
    // Arrange, Act and Assert
    assertTrue(FilesReportTestHelper.noFilesReport().getFiles().isEmpty());
  }

  /**
   * Test {@link AllReferencesToAllFiles#getFilesWithReferences()}.
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#getFilesWithReferences()}
   */
  @Test
  @DisplayName("Test getFilesWithReferences()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Collection AllReferencesToAllFiles.getFilesWithReferences()"})
  void testGetFilesWithReferences() {
    // Arrange, Act and Assert
    assertTrue(FilesReportTestHelper.noFilesReport().getFilesWithReferences().isEmpty());
  }

  /**
   * Test {@link AllReferencesToAllFiles#getFilesWithNoReferences()}.
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#getFilesWithNoReferences()}
   */
  @Test
  @DisplayName("Test getFilesWithNoReferences()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Collection AllReferencesToAllFiles.getFilesWithNoReferences()"})
  void testGetFilesWithNoReferences() {
    // Arrange, Act and Assert
    assertTrue(FilesReportTestHelper.noFilesReport().getFilesWithNoReferences().isEmpty());
  }

  /**
   * Test {@link AllReferencesToAllFiles#listFileReferences()}.
   * <ul>
   *   <li>Given noFilesReport.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#listFileReferences()}
   */
  @Test
  @DisplayName("Test listFileReferences(); given noFilesReport; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AllReferencesToAllFiles.listFileReferences()"})
  void testListFileReferences_givenNoFilesReport_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(FilesReportTestHelper.noFilesReport().listFileReferences().isEmpty());
  }

  /**
   * Test {@link AllReferencesToAllFiles#listFileReferences()}.
   * <ul>
   *   <li>Then return {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#listFileReferences()}
   */
  @Test
  @DisplayName("Test listFileReferences(); then return ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AllReferencesToAllFiles.listFileReferences()"})
  void testListFileReferences_thenReturnArrayList() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);

    // Act and Assert
    assertEquals(references,
        FilesReportTestHelper
            .activeFilesReport(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), references)
            .listFileReferences());
  }

  /**
   * Test {@link AllReferencesToAllFiles#listFileReferences()}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#listFileReferences()}
   */
  @Test
  @DisplayName("Test listFileReferences(); then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AllReferencesToAllFiles.listFileReferences()"})
  void testListFileReferences_thenReturnSizeIsTwo() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("Filename").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);

    // Act
    List<FileReference> actualListFileReferencesResult = FilesReportTestHelper
        .activeFilesReport(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), references)
        .listFileReferences();

    // Assert
    assertEquals(2, actualListFileReferencesResult.size());
    FileReference getResult = actualListFileReferencesResult.get(1);
    assertEquals("42", getResult.getJobId());
    assertEquals("42", getResult.getPartitionId());
    assertEquals("Filename", actualListFileReferencesResult.get(0).getFilename());
    assertEquals("foo.txt", getResult.getFilename());
    assertEquals(1L, getResult.getNumberOfRecords().longValue());
    assertTrue(getResult.isCountApproximate());
    assertTrue(getResult.onlyContainsDataForThisPartition());
  }

  /**
   * Test {@link AllReferencesToAllFiles#recordsByFilename()}.
   * <ul>
   *   <li>Given noFilesReport.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#recordsByFilename()}
   */
  @Test
  @DisplayName("Test recordsByFilename(); given noFilesReport; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map AllReferencesToAllFiles.recordsByFilename()"})
  void testRecordsByFilename_givenNoFilesReport_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(FilesReportTestHelper.noFilesReport().recordsByFilename().isEmpty());
  }

  /**
   * Test {@link AllReferencesToAllFiles#recordsByFilename()}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#recordsByFilename()}
   */
  @Test
  @DisplayName("Test recordsByFilename(); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map AllReferencesToAllFiles.recordsByFilename()"})
  void testRecordsByFilename_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);

    // Act
    Map<String, Long> actualRecordsByFilenameResult = FilesReportTestHelper
        .activeFilesReport(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), references)
        .recordsByFilename();

    // Assert
    assertEquals(1, actualRecordsByFilenameResult.size());
    assertEquals(1L, actualRecordsByFilenameResult.get("foo.txt").longValue());
  }

  /**
   * Test {@link AllReferencesToAllFiles#recordsByFilename()}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#recordsByFilename()}
   */
  @Test
  @DisplayName("Test recordsByFilename(); then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map AllReferencesToAllFiles.recordsByFilename()"})
  void testRecordsByFilename_thenReturnSizeIsTwo() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("Filename").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);

    // Act
    Map<String, Long> actualRecordsByFilenameResult = FilesReportTestHelper
        .activeFilesReport(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), references)
        .recordsByFilename();

    // Assert
    assertEquals(2, actualRecordsByFilenameResult.size());
    assertEquals(1L, actualRecordsByFilenameResult.get("Filename").longValue());
    assertEquals(1L, actualRecordsByFilenameResult.get("foo.txt").longValue());
  }

  /**
   * Test {@link AllReferencesToAllFiles#estimateRecordsInTable()}.
   * <ul>
   *   <li>Given noFilesReport.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#estimateRecordsInTable()}
   */
  @Test
  @DisplayName("Test estimateRecordsInTable(); given noFilesReport; then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long AllReferencesToAllFiles.estimateRecordsInTable()"})
  void testEstimateRecordsInTable_givenNoFilesReport_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0L, FilesReportTestHelper.noFilesReport().estimateRecordsInTable());
  }

  /**
   * Test {@link AllReferencesToAllFiles#estimateRecordsInTable()}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#estimateRecordsInTable()}
   */
  @Test
  @DisplayName("Test estimateRecordsInTable(); then return one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long AllReferencesToAllFiles.estimateRecordsInTable()"})
  void testEstimateRecordsInTable_thenReturnOne() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);

    // Act and Assert
    assertEquals(1L,
        FilesReportTestHelper
            .activeFilesReport(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), references)
            .estimateRecordsInTable());
  }

  /**
   * Test {@link AllReferencesToAllFiles#estimateRecordsInTable()}.
   * <ul>
   *   <li>Then return two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#estimateRecordsInTable()}
   */
  @Test
  @DisplayName("Test estimateRecordsInTable(); then return two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long AllReferencesToAllFiles.estimateRecordsInTable()"})
  void testEstimateRecordsInTable_thenReturnTwo() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("Filename").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);

    // Act and Assert
    assertEquals(2L,
        FilesReportTestHelper
            .activeFilesReport(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), references)
            .estimateRecordsInTable());
  }

  /**
   * Test {@link AllReferencesToAllFiles#countFileReferences()}.
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#countFileReferences()}
   */
  @Test
  @DisplayName("Test countFileReferences()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long AllReferencesToAllFiles.countFileReferences()"})
  void testCountFileReferences() {
    // Arrange, Act and Assert
    assertEquals(0L, FilesReportTestHelper.noFilesReport().countFileReferences());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AllReferencesToAllFiles#toString()}
   *   <li>{@link AllReferencesToAllFiles#isMoreThanMax()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAllFiles.isMoreThanMax()", "String AllReferencesToAllFiles.toString()"})
  void testGettersAndSetters() {
    // Arrange
    AllReferencesToAllFiles allReferencesToAllFiles = new AllReferencesToAllFiles(new ArrayList<>(), true);

    // Act
    String actualToStringResult = allReferencesToAllFiles.toString();

    // Assert
    assertEquals("AllReferencesToAllFiles{files=[], moreThanMax=true}", actualToStringResult);
    assertTrue(allReferencesToAllFiles.isMoreThanMax());
  }

  /**
   * Test {@link AllReferencesToAllFiles#equals(Object)}, and {@link AllReferencesToAllFiles#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AllReferencesToAllFiles#equals(Object)}
   *   <li>{@link AllReferencesToAllFiles#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAllFiles.equals(Object)", "int AllReferencesToAllFiles.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    AllReferencesToAllFiles noFilesReportResult = FilesReportTestHelper.noFilesReport();
    AllReferencesToAllFiles noFilesReportResult2 = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(noFilesReportResult, noFilesReportResult2);
    int expectedHashCodeResult = noFilesReportResult.hashCode();
    assertEquals(expectedHashCodeResult, noFilesReportResult2.hashCode());
  }

  /**
   * Test {@link AllReferencesToAllFiles#equals(Object)}, and {@link AllReferencesToAllFiles#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AllReferencesToAllFiles#equals(Object)}
   *   <li>{@link AllReferencesToAllFiles#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAllFiles.equals(Object)", "int AllReferencesToAllFiles.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    AllReferencesToAllFiles noFilesReportResult = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(noFilesReportResult, noFilesReportResult);
    int expectedHashCodeResult = noFilesReportResult.hashCode();
    assertEquals(expectedHashCodeResult, noFilesReportResult.hashCode());
  }

  /**
   * Test {@link AllReferencesToAllFiles#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAllFiles.equals(Object)", "int AllReferencesToAllFiles.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    AllReferencesToAllFiles partialReadyForGCFilesReportResult = FilesReportTestHelper.partialReadyForGCFilesReport(
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), "foo.txt");

    // Act and Assert
    assertNotEquals(partialReadyForGCFilesReportResult, FilesReportTestHelper.noFilesReport());
  }

  /**
   * Test {@link AllReferencesToAllFiles#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAllFiles.equals(Object)", "int AllReferencesToAllFiles.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    AllReferencesToAllFiles activeFilesReportResult = FilesReportTestHelper
        .activeFilesReport(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), references);

    // Act and Assert
    assertNotEquals(activeFilesReportResult, FilesReportTestHelper.noFilesReport());
  }

  /**
   * Test {@link AllReferencesToAllFiles#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAllFiles.equals(Object)", "int AllReferencesToAllFiles.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(FilesReportTestHelper.noFilesReport(), null);
  }

  /**
   * Test {@link AllReferencesToAllFiles#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAllFiles#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAllFiles.equals(Object)", "int AllReferencesToAllFiles.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(FilesReportTestHelper.noFilesReport(), "Different type to AllReferencesToAllFiles");
  }
}
