package sleeper.clients.status.report.filestatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.AllReferencesToAllFiles;
import sleeper.core.statestore.FilesReportTestHelper;

class TableFilesStatisticsDiffblueTest {
  /**
   * Test {@link TableFilesStatistics#from(AllReferencesToAllFiles, Map)}.
   * <ul>
   *   <li>When noFilesReport.</li>
   *   <li>Then return FileCount is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatistics#from(AllReferencesToAllFiles, Map)}
   */
  @Test
  @DisplayName("Test from(AllReferencesToAllFiles, Map); when noFilesReport; then return FileCount is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFilesStatistics TableFilesStatistics.from(AllReferencesToAllFiles, Map)"})
  void testFrom_whenNoFilesReport_thenReturnFileCountIsZero() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act
    TableFilesStatistics actualFromResult = TableFilesStatistics.from(files, new HashMap<>());

    // Assert
    assertEquals(0, actualFromResult.getFileCount());
    assertEquals(0, actualFromResult.getFileReferenceCount());
    assertEquals(0L, actualFromResult.getReferencesInLeafPartitions());
    assertEquals(0L, actualFromResult.getReferencesInNonLeafPartitions());
    assertEquals(0L, actualFromResult.getTotalRecords());
    assertEquals(0L, actualFromResult.getTotalRecordsApprox());
    assertEquals(0L, actualFromResult.getTotalRecordsInLeafPartitions());
    assertEquals(0L, actualFromResult.getTotalRecordsInLeafPartitionsApprox());
    assertEquals(0L, actualFromResult.getTotalRecordsInNonLeafPartitions());
    assertEquals(0L, actualFromResult.getTotalRecordsInNonLeafPartitionsApprox());
  }

  /**
   * Test {@link TableFilesStatistics#getReferencesInLeafPartitions()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getReferencesInLeafPartitions()}
   */
  @Test
  @DisplayName("Test getReferencesInLeafPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatistics.getReferencesInLeafPartitions()"})
  void testGetReferencesInLeafPartitions() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(0L, TableFilesStatistics.from(files, new HashMap<>()).getReferencesInLeafPartitions());
  }

  /**
   * Test {@link TableFilesStatistics#getReferencesInNonLeafPartitions()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getReferencesInNonLeafPartitions()}
   */
  @Test
  @DisplayName("Test getReferencesInNonLeafPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatistics.getReferencesInNonLeafPartitions()"})
  void testGetReferencesInNonLeafPartitions() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(0L, TableFilesStatistics.from(files, new HashMap<>()).getReferencesInNonLeafPartitions());
  }

  /**
   * Test {@link TableFilesStatistics#getLeafPartitionFileReferenceStats()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getLeafPartitionFileReferenceStats()}
   */
  @Test
  @DisplayName("Test getLeafPartitionFileReferenceStats()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReferencesStats TableFilesStatistics.getLeafPartitionFileReferenceStats()"})
  void testGetLeafPartitionFileReferenceStats() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act
    FileReferencesStats actualLeafPartitionFileReferenceStats = TableFilesStatistics.from(files, new HashMap<>())
        .getLeafPartitionFileReferenceStats();

    // Assert
    assertNull(actualLeafPartitionFileReferenceStats.getAverageReferences());
    assertNull(actualLeafPartitionFileReferenceStats.getMaxReferences());
    assertNull(actualLeafPartitionFileReferenceStats.getMinReferences());
    assertEquals(0, actualLeafPartitionFileReferenceStats.getTotalReferences().intValue());
  }

  /**
   * Test {@link TableFilesStatistics#getNonLeafPartitionFileReferenceStats()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getNonLeafPartitionFileReferenceStats()}
   */
  @Test
  @DisplayName("Test getNonLeafPartitionFileReferenceStats()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReferencesStats TableFilesStatistics.getNonLeafPartitionFileReferenceStats()"})
  void testGetNonLeafPartitionFileReferenceStats() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act
    FileReferencesStats actualNonLeafPartitionFileReferenceStats = TableFilesStatistics.from(files, new HashMap<>())
        .getNonLeafPartitionFileReferenceStats();

    // Assert
    assertNull(actualNonLeafPartitionFileReferenceStats.getAverageReferences());
    assertNull(actualNonLeafPartitionFileReferenceStats.getMaxReferences());
    assertNull(actualNonLeafPartitionFileReferenceStats.getMinReferences());
    assertEquals(0, actualNonLeafPartitionFileReferenceStats.getTotalReferences().intValue());
  }

  /**
   * Test {@link TableFilesStatistics#getTotalRecords()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getTotalRecords()}
   */
  @Test
  @DisplayName("Test getTotalRecords()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatistics.getTotalRecords()"})
  void testGetTotalRecords() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(0L, TableFilesStatistics.from(files, new HashMap<>()).getTotalRecords());
  }

  /**
   * Test {@link TableFilesStatistics#getTotalRecordsApprox()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getTotalRecordsApprox()}
   */
  @Test
  @DisplayName("Test getTotalRecordsApprox()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatistics.getTotalRecordsApprox()"})
  void testGetTotalRecordsApprox() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(0L, TableFilesStatistics.from(files, new HashMap<>()).getTotalRecordsApprox());
  }

  /**
   * Test {@link TableFilesStatistics#getTotalRecordsInLeafPartitions()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getTotalRecordsInLeafPartitions()}
   */
  @Test
  @DisplayName("Test getTotalRecordsInLeafPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatistics.getTotalRecordsInLeafPartitions()"})
  void testGetTotalRecordsInLeafPartitions() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(0L, TableFilesStatistics.from(files, new HashMap<>()).getTotalRecordsInLeafPartitions());
  }

  /**
   * Test {@link TableFilesStatistics#getTotalRecordsInLeafPartitionsApprox()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getTotalRecordsInLeafPartitionsApprox()}
   */
  @Test
  @DisplayName("Test getTotalRecordsInLeafPartitionsApprox()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatistics.getTotalRecordsInLeafPartitionsApprox()"})
  void testGetTotalRecordsInLeafPartitionsApprox() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(0L, TableFilesStatistics.from(files, new HashMap<>()).getTotalRecordsInLeafPartitionsApprox());
  }

  /**
   * Test {@link TableFilesStatistics#getTotalRecordsInNonLeafPartitions()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getTotalRecordsInNonLeafPartitions()}
   */
  @Test
  @DisplayName("Test getTotalRecordsInNonLeafPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatistics.getTotalRecordsInNonLeafPartitions()"})
  void testGetTotalRecordsInNonLeafPartitions() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(0L, TableFilesStatistics.from(files, new HashMap<>()).getTotalRecordsInNonLeafPartitions());
  }

  /**
   * Test {@link TableFilesStatistics#getTotalRecordsInNonLeafPartitionsApprox()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getTotalRecordsInNonLeafPartitionsApprox()}
   */
  @Test
  @DisplayName("Test getTotalRecordsInNonLeafPartitionsApprox()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatistics.getTotalRecordsInNonLeafPartitionsApprox()"})
  void testGetTotalRecordsInNonLeafPartitionsApprox() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(0L, TableFilesStatistics.from(files, new HashMap<>()).getTotalRecordsInNonLeafPartitionsApprox());
  }

  /**
   * Test {@link TableFilesStatistics#getFileCount()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getFileCount()}
   */
  @Test
  @DisplayName("Test getFileCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int TableFilesStatistics.getFileCount()"})
  void testGetFileCount() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(0, TableFilesStatistics.from(files, new HashMap<>()).getFileCount());
  }

  /**
   * Test {@link TableFilesStatistics#getFileReferenceCount()}.
   * <p>
   * Method under test: {@link TableFilesStatistics#getFileReferenceCount()}
   */
  @Test
  @DisplayName("Test getFileReferenceCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int TableFilesStatistics.getFileReferenceCount()"})
  void testGetFileReferenceCount() {
    // Arrange
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act and Assert
    assertEquals(0, TableFilesStatistics.from(files, new HashMap<>()).getFileReferenceCount());
  }
}
