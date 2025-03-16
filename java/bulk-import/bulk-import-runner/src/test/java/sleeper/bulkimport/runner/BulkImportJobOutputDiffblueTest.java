package sleeper.bulkimport.runner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

class BulkImportJobOutputDiffblueTest {
  /**
   * Test {@link BulkImportJobOutput#BulkImportJobOutput(List, Runnable)}.
   * <ul>
   *   <li>Then return fileReferences size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobOutput#BulkImportJobOutput(List, Runnable)}
   */
  @Test
  @DisplayName("Test new BulkImportJobOutput(List, Runnable); then return fileReferences size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobOutput.<init>(List, Runnable)"})
  void testNewBulkImportJobOutput_thenReturnFileReferencesSizeIsTwo() {
    // Arrange
    ArrayList<FileReference> fileReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult2);

    // Act
    BulkImportJobOutput actualBulkImportJobOutput = new BulkImportJobOutput(fileReferences, mock(Runnable.class));

    // Assert
    List<FileReference> fileReferencesResult = actualBulkImportJobOutput.fileReferences();
    assertEquals(2, fileReferencesResult.size());
    assertEquals(2L, actualBulkImportJobOutput.numRecords());
    assertEquals(fileReferencesResult.get(0), fileReferencesResult.get(1));
  }

  /**
   * Test {@link BulkImportJobOutput#BulkImportJobOutput(List, Runnable)}.
   * <ul>
   *   <li>Then return numRecords is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobOutput#BulkImportJobOutput(List, Runnable)}
   */
  @Test
  @DisplayName("Test new BulkImportJobOutput(List, Runnable); then return numRecords is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobOutput.<init>(List, Runnable)"})
  void testNewBulkImportJobOutput_thenReturnNumRecordsIsOne() {
    // Arrange
    ArrayList<FileReference> fileReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult);

    // Act
    BulkImportJobOutput actualBulkImportJobOutput = new BulkImportJobOutput(fileReferences, mock(Runnable.class));

    // Assert
    assertEquals(1L, actualBulkImportJobOutput.numRecords());
    assertSame(fileReferences, actualBulkImportJobOutput.fileReferences());
  }

  /**
   * Test {@link BulkImportJobOutput#BulkImportJobOutput(List, Runnable)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return numRecords is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobOutput#BulkImportJobOutput(List, Runnable)}
   */
  @Test
  @DisplayName("Test new BulkImportJobOutput(List, Runnable); when ArrayList(); then return numRecords is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobOutput.<init>(List, Runnable)"})
  void testNewBulkImportJobOutput_whenArrayList_thenReturnNumRecordsIsZero() {
    // Arrange and Act
    BulkImportJobOutput actualBulkImportJobOutput = new BulkImportJobOutput(new ArrayList<>(), mock(Runnable.class));

    // Assert
    assertEquals(0L, actualBulkImportJobOutput.numRecords());
    assertTrue(actualBulkImportJobOutput.fileReferences().isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BulkImportJobOutput#fileReferences()}
   *   <li>{@link BulkImportJobOutput#numRecords()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List BulkImportJobOutput.fileReferences()", "long BulkImportJobOutput.numRecords()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<FileReference> fileReferences = new ArrayList<>();
    BulkImportJobOutput bulkImportJobOutput = new BulkImportJobOutput(fileReferences, mock(Runnable.class));

    // Act
    List<FileReference> actualFileReferencesResult = bulkImportJobOutput.fileReferences();

    // Assert
    assertEquals(0L, bulkImportJobOutput.numRecords());
    assertTrue(actualFileReferencesResult.isEmpty());
    assertSame(fileReferences, actualFileReferencesResult);
  }

  /**
   * Test {@link BulkImportJobOutput#numFiles()}.
   * <p>
   * Method under test: {@link BulkImportJobOutput#numFiles()}
   */
  @Test
  @DisplayName("Test numFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int BulkImportJobOutput.numFiles()"})
  void testNumFiles() {
    // Arrange, Act and Assert
    assertEquals(0, (new BulkImportJobOutput(new ArrayList<>(), mock(Runnable.class))).numFiles());
  }

  /**
   * Test {@link BulkImportJobOutput#stopSparkContext()}.
   * <p>
   * Method under test: {@link BulkImportJobOutput#stopSparkContext()}
   */
  @Test
  @DisplayName("Test stopSparkContext()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobOutput.stopSparkContext()"})
  void testStopSparkContext() {
    // Arrange
    Runnable stopSparkContext = mock(Runnable.class);
    doNothing().when(stopSparkContext).run();

    // Act
    (new BulkImportJobOutput(new ArrayList<>(), stopSparkContext)).stopSparkContext();

    // Assert
    verify(stopSparkContext).run();
  }
}
