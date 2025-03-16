package sleeper.ingest.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;
import sleeper.core.tracker.job.run.RecordsProcessed;

class IngestResultDiffblueTest {
  /**
   * Test {@link IngestResult#allReadWereWritten(List)}.
   * <ul>
   *   <li>Then return FileReferenceList size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestResult#allReadWereWritten(List)}
   */
  @Test
  @DisplayName("Test allReadWereWritten(List); then return FileReferenceList size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestResult IngestResult.allReadWereWritten(List)"})
  void testAllReadWereWritten_thenReturnFileReferenceListSizeIsTwo() {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult2);

    // Act
    IngestResult actualAllReadWereWrittenResult = IngestResult.allReadWereWritten(fileReferenceList);

    // Assert
    List<FileReference> fileReferenceList2 = actualAllReadWereWrittenResult.getFileReferenceList();
    assertEquals(2, fileReferenceList2.size());
    assertEquals(2L, actualAllReadWereWrittenResult.getRecordsWritten());
    assertEquals(fileReferenceList2.get(0), fileReferenceList2.get(1));
  }

  /**
   * Test {@link IngestResult#allReadWereWritten(List)}.
   * <ul>
   *   <li>Then return RecordsWritten is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestResult#allReadWereWritten(List)}
   */
  @Test
  @DisplayName("Test allReadWereWritten(List); then return RecordsWritten is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestResult IngestResult.allReadWereWritten(List)"})
  void testAllReadWereWritten_thenReturnRecordsWrittenIsOne() {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);

    // Act
    IngestResult actualAllReadWereWrittenResult = IngestResult.allReadWereWritten(fileReferenceList);

    // Assert
    assertEquals(1L, actualAllReadWereWrittenResult.getRecordsWritten());
    assertEquals(fileReferenceList, actualAllReadWereWrittenResult.getFileReferenceList());
  }

  /**
   * Test {@link IngestResult#allReadWereWritten(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return RecordsWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestResult#allReadWereWritten(List)}
   */
  @Test
  @DisplayName("Test allReadWereWritten(List); when ArrayList(); then return RecordsWritten is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestResult IngestResult.allReadWereWritten(List)"})
  void testAllReadWereWritten_whenArrayList_thenReturnRecordsWrittenIsZero() {
    // Arrange and Act
    IngestResult actualAllReadWereWrittenResult = IngestResult.allReadWereWritten(new ArrayList<>());

    // Assert
    assertEquals(0L, actualAllReadWereWrittenResult.getRecordsWritten());
    assertTrue(actualAllReadWereWrittenResult.getFileReferenceList().isEmpty());
  }

  /**
   * Test {@link IngestResult#fromReadAndWritten(long, List)}.
   * <ul>
   *   <li>Then return FileReferenceList size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestResult#fromReadAndWritten(long, List)}
   */
  @Test
  @DisplayName("Test fromReadAndWritten(long, List); then return FileReferenceList size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestResult IngestResult.fromReadAndWritten(long, List)"})
  void testFromReadAndWritten_thenReturnFileReferenceListSizeIsTwo() {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult2);

    // Act
    IngestResult actualFromReadAndWrittenResult = IngestResult.fromReadAndWritten(1L, fileReferenceList);

    // Assert
    List<FileReference> fileReferenceList2 = actualFromReadAndWrittenResult.getFileReferenceList();
    assertEquals(2, fileReferenceList2.size());
    assertEquals(2L, actualFromReadAndWrittenResult.getRecordsWritten());
    assertEquals(fileReferenceList2.get(0), fileReferenceList2.get(1));
  }

  /**
   * Test {@link IngestResult#fromReadAndWritten(long, List)}.
   * <ul>
   *   <li>Then return RecordsWritten is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestResult#fromReadAndWritten(long, List)}
   */
  @Test
  @DisplayName("Test fromReadAndWritten(long, List); then return RecordsWritten is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestResult IngestResult.fromReadAndWritten(long, List)"})
  void testFromReadAndWritten_thenReturnRecordsWrittenIsOne() {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);

    // Act
    IngestResult actualFromReadAndWrittenResult = IngestResult.fromReadAndWritten(1L, fileReferenceList);

    // Assert
    assertEquals(1L, actualFromReadAndWrittenResult.getRecordsWritten());
    assertEquals(fileReferenceList, actualFromReadAndWrittenResult.getFileReferenceList());
  }

  /**
   * Test {@link IngestResult#fromReadAndWritten(long, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return RecordsWritten is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestResult#fromReadAndWritten(long, List)}
   */
  @Test
  @DisplayName("Test fromReadAndWritten(long, List); when ArrayList(); then return RecordsWritten is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestResult IngestResult.fromReadAndWritten(long, List)"})
  void testFromReadAndWritten_whenArrayList_thenReturnRecordsWrittenIsZero() {
    // Arrange and Act
    IngestResult actualFromReadAndWrittenResult = IngestResult.fromReadAndWritten(1L, new ArrayList<>());

    // Assert
    assertEquals(0L, actualFromReadAndWrittenResult.getRecordsWritten());
    assertTrue(actualFromReadAndWrittenResult.getFileReferenceList().isEmpty());
  }

  /**
   * Test {@link IngestResult#noFiles()}.
   * <p>
   * Method under test: {@link IngestResult#noFiles()}
   */
  @Test
  @DisplayName("Test noFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestResult IngestResult.noFiles()"})
  void testNoFiles() {
    // Arrange and Act
    IngestResult actualNoFilesResult = IngestResult.noFiles();

    // Assert
    assertEquals(0L, actualNoFilesResult.getRecordsWritten());
    assertTrue(actualNoFilesResult.getFileReferenceList().isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestResult#toString()}
   *   <li>{@link IngestResult#getRecordsWritten()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long IngestResult.getRecordsWritten()", "String IngestResult.toString()"})
  void testGettersAndSetters() {
    // Arrange
    IngestResult noFilesResult = IngestResult.noFiles();

    // Act
    String actualToStringResult = noFilesResult.toString();

    // Assert
    assertEquals("IngestResult{recordsWritten=0,fileReferenceList=[]}", actualToStringResult);
    assertEquals(0L, noFilesResult.getRecordsWritten());
  }

  /**
   * Test {@link IngestResult#getFileReferenceList()}.
   * <p>
   * Method under test: {@link IngestResult#getFileReferenceList()}
   */
  @Test
  @DisplayName("Test getFileReferenceList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List IngestResult.getFileReferenceList()"})
  void testGetFileReferenceList() {
    // Arrange, Act and Assert
    assertTrue(IngestResult.noFiles().getFileReferenceList().isEmpty());
  }

  /**
   * Test {@link IngestResult#asRecordsProcessed()}.
   * <p>
   * Method under test: {@link IngestResult#asRecordsProcessed()}
   */
  @Test
  @DisplayName("Test asRecordsProcessed()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RecordsProcessed IngestResult.asRecordsProcessed()"})
  void testAsRecordsProcessed() {
    // Arrange and Act
    RecordsProcessed actualAsRecordsProcessedResult = IngestResult.noFiles().asRecordsProcessed();

    // Assert
    assertEquals(actualAsRecordsProcessedResult.NONE, actualAsRecordsProcessedResult);
  }

  /**
   * Test {@link IngestResult#equals(Object)}, and {@link IngestResult#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestResult#equals(Object)}
   *   <li>{@link IngestResult#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestResult.equals(Object)", "int IngestResult.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    IngestResult noFilesResult = IngestResult.noFiles();
    IngestResult noFilesResult2 = IngestResult.noFiles();

    // Act and Assert
    assertEquals(noFilesResult, noFilesResult2);
    int expectedHashCodeResult = noFilesResult.hashCode();
    assertEquals(expectedHashCodeResult, noFilesResult2.hashCode());
  }

  /**
   * Test {@link IngestResult#equals(Object)}, and {@link IngestResult#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestResult#equals(Object)}
   *   <li>{@link IngestResult#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestResult.equals(Object)", "int IngestResult.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    IngestResult noFilesResult = IngestResult.noFiles();

    // Act and Assert
    assertEquals(noFilesResult, noFilesResult);
    int expectedHashCodeResult = noFilesResult.hashCode();
    assertEquals(expectedHashCodeResult, noFilesResult.hashCode());
  }

  /**
   * Test {@link IngestResult#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestResult.equals(Object)", "int IngestResult.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(IngestResult.noFiles(), null);
  }

  /**
   * Test {@link IngestResult#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestResult.equals(Object)", "int IngestResult.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(IngestResult.noFiles(), null);
  }

  /**
   * Test {@link IngestResult#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestResult.equals(Object)", "int IngestResult.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(IngestResult.noFiles(), "Different type to IngestResult");
  }
}
