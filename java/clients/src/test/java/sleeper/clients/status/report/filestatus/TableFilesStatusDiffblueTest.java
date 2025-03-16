package sleeper.clients.status.report.filestatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.filestatus.TableFilesStatus.Builder;
import sleeper.core.statestore.AllReferencesToAllFiles;
import sleeper.core.statestore.FilesReportTestHelper;

class TableFilesStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#files(AllReferencesToAllFiles)}
   *   <li>{@link Builder#leafPartitionCount(int)}
   *   <li>{@link Builder#nonLeafPartitionCount(int)}
   *   <li>{@link Builder#statistics(TableFilesStatistics)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFilesStatus Builder.build()", "Builder Builder.files(AllReferencesToAllFiles)",
      "Builder Builder.leafPartitionCount(int)", "Builder Builder.nonLeafPartitionCount(int)",
      "Builder Builder.statistics(TableFilesStatistics)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();

    // Act
    TableFilesStatus actualBuildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Assert
    FileReferencesStats leafPartitionFileReferenceStats = actualBuildResult.getLeafPartitionFileReferenceStats();
    assertNull(leafPartitionFileReferenceStats.getAverageReferences());
    FileReferencesStats nonLeafPartitionFileReferenceStats = actualBuildResult.getNonLeafPartitionFileReferenceStats();
    assertNull(nonLeafPartitionFileReferenceStats.getAverageReferences());
    assertNull(leafPartitionFileReferenceStats.getMaxReferences());
    assertNull(nonLeafPartitionFileReferenceStats.getMaxReferences());
    assertNull(leafPartitionFileReferenceStats.getMinReferences());
    assertNull(nonLeafPartitionFileReferenceStats.getMinReferences());
    assertEquals(0, leafPartitionFileReferenceStats.getTotalReferences().intValue());
    assertEquals(0, nonLeafPartitionFileReferenceStats.getTotalReferences().intValue());
    assertEquals(0, actualBuildResult.getFileCount());
    assertEquals(0L, actualBuildResult.getFileReferenceCount());
    assertEquals(0L, actualBuildResult.getReferencesInLeafPartitions());
    assertEquals(0L, actualBuildResult.getReferencesInNonLeafPartitions());
    assertEquals(0L, actualBuildResult.getTotalRecords());
    assertEquals(0L, actualBuildResult.getTotalRecordsApprox());
    assertEquals(0L, actualBuildResult.getTotalRecordsInLeafPartitions());
    assertEquals(0L, actualBuildResult.getTotalRecordsInLeafPartitionsApprox());
    assertEquals(0L, actualBuildResult.getTotalRecordsInNonLeafPartitions());
    assertEquals(0L, actualBuildResult.getTotalRecordsInNonLeafPartitionsApprox());
    assertEquals(3, actualBuildResult.getLeafPartitionCount());
    assertEquals(3, actualBuildResult.getNonLeafPartitionCount());
    assertFalse(actualBuildResult.isMoreThanMax());
    assertTrue(actualBuildResult.getFilesWithNoReferences().isEmpty());
    assertTrue(actualBuildResult.getFilesWithReferences().isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableFilesStatus#getLeafPartitionCount()}
   *   <li>{@link TableFilesStatus#getNonLeafPartitionCount()}
   *   <li>{@link TableFilesStatus#isMoreThanMax()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int TableFilesStatus.getLeafPartitionCount()", "int TableFilesStatus.getNonLeafPartitionCount()",
      "boolean TableFilesStatus.isMoreThanMax()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act
    int actualLeafPartitionCount = buildResult.getLeafPartitionCount();
    int actualNonLeafPartitionCount = buildResult.getNonLeafPartitionCount();

    // Assert
    assertEquals(3, actualLeafPartitionCount);
    assertEquals(3, actualNonLeafPartitionCount);
    assertFalse(buildResult.isMoreThanMax());
  }

  /**
   * Test {@link TableFilesStatus#getFileCount()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getFileCount()}
   */
  @Test
  @DisplayName("Test getFileCount(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int TableFilesStatus.getFileCount()"})
  void testGetFileCount_thenReturnZero() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertEquals(0, buildResult.getFileCount());
  }

  /**
   * Test {@link TableFilesStatus#getFileReferenceCount()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getFileReferenceCount()}
   */
  @Test
  @DisplayName("Test getFileReferenceCount(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatus.getFileReferenceCount()"})
  void testGetFileReferenceCount_thenReturnZero() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertEquals(0L, buildResult.getFileReferenceCount());
  }

  /**
   * Test {@link TableFilesStatus#getReferencesInLeafPartitions()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getReferencesInLeafPartitions()}
   */
  @Test
  @DisplayName("Test getReferencesInLeafPartitions(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatus.getReferencesInLeafPartitions()"})
  void testGetReferencesInLeafPartitions_thenReturnZero() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertEquals(0L, buildResult.getReferencesInLeafPartitions());
  }

  /**
   * Test {@link TableFilesStatus#getReferencesInNonLeafPartitions()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getReferencesInNonLeafPartitions()}
   */
  @Test
  @DisplayName("Test getReferencesInNonLeafPartitions(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatus.getReferencesInNonLeafPartitions()"})
  void testGetReferencesInNonLeafPartitions_thenReturnZero() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertEquals(0L, buildResult.getReferencesInNonLeafPartitions());
  }

  /**
   * Test {@link TableFilesStatus#getLeafPartitionFileReferenceStats()}.
   * <ul>
   *   <li>Then return AverageReferences is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getLeafPartitionFileReferenceStats()}
   */
  @Test
  @DisplayName("Test getLeafPartitionFileReferenceStats(); then return AverageReferences is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReferencesStats TableFilesStatus.getLeafPartitionFileReferenceStats()"})
  void testGetLeafPartitionFileReferenceStats_thenReturnAverageReferencesIsNull() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act
    FileReferencesStats actualLeafPartitionFileReferenceStats = buildResult.getLeafPartitionFileReferenceStats();

    // Assert
    assertNull(actualLeafPartitionFileReferenceStats.getAverageReferences());
    assertNull(actualLeafPartitionFileReferenceStats.getMaxReferences());
    assertNull(actualLeafPartitionFileReferenceStats.getMinReferences());
    assertEquals(0, actualLeafPartitionFileReferenceStats.getTotalReferences().intValue());
  }

  /**
   * Test {@link TableFilesStatus#getNonLeafPartitionFileReferenceStats()}.
   * <ul>
   *   <li>Then return AverageReferences is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getNonLeafPartitionFileReferenceStats()}
   */
  @Test
  @DisplayName("Test getNonLeafPartitionFileReferenceStats(); then return AverageReferences is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReferencesStats TableFilesStatus.getNonLeafPartitionFileReferenceStats()"})
  void testGetNonLeafPartitionFileReferenceStats_thenReturnAverageReferencesIsNull() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act
    FileReferencesStats actualNonLeafPartitionFileReferenceStats = buildResult.getNonLeafPartitionFileReferenceStats();

    // Assert
    assertNull(actualNonLeafPartitionFileReferenceStats.getAverageReferences());
    assertNull(actualNonLeafPartitionFileReferenceStats.getMaxReferences());
    assertNull(actualNonLeafPartitionFileReferenceStats.getMinReferences());
    assertEquals(0, actualNonLeafPartitionFileReferenceStats.getTotalReferences().intValue());
  }

  /**
   * Test {@link TableFilesStatus#getFilesWithReferences()}.
   * <p>
   * Method under test: {@link TableFilesStatus#getFilesWithReferences()}
   */
  @Test
  @DisplayName("Test getFilesWithReferences()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Collection TableFilesStatus.getFilesWithReferences()"})
  void testGetFilesWithReferences() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertTrue(buildResult.getFilesWithReferences().isEmpty());
  }

  /**
   * Test {@link TableFilesStatus#getFilesWithNoReferences()}.
   * <p>
   * Method under test: {@link TableFilesStatus#getFilesWithNoReferences()}
   */
  @Test
  @DisplayName("Test getFilesWithNoReferences()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Collection TableFilesStatus.getFilesWithNoReferences()"})
  void testGetFilesWithNoReferences() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertTrue(buildResult.getFilesWithNoReferences().isEmpty());
  }

  /**
   * Test {@link TableFilesStatus#getTotalRecords()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getTotalRecords()}
   */
  @Test
  @DisplayName("Test getTotalRecords(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatus.getTotalRecords()"})
  void testGetTotalRecords_thenReturnZero() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertEquals(0L, buildResult.getTotalRecords());
  }

  /**
   * Test {@link TableFilesStatus#getTotalRecordsInLeafPartitions()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getTotalRecordsInLeafPartitions()}
   */
  @Test
  @DisplayName("Test getTotalRecordsInLeafPartitions(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatus.getTotalRecordsInLeafPartitions()"})
  void testGetTotalRecordsInLeafPartitions_thenReturnZero() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertEquals(0L, buildResult.getTotalRecordsInLeafPartitions());
  }

  /**
   * Test {@link TableFilesStatus#getTotalRecordsApprox()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getTotalRecordsApprox()}
   */
  @Test
  @DisplayName("Test getTotalRecordsApprox(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatus.getTotalRecordsApprox()"})
  void testGetTotalRecordsApprox_thenReturnZero() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertEquals(0L, buildResult.getTotalRecordsApprox());
  }

  /**
   * Test {@link TableFilesStatus#getTotalRecordsInLeafPartitionsApprox()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getTotalRecordsInLeafPartitionsApprox()}
   */
  @Test
  @DisplayName("Test getTotalRecordsInLeafPartitionsApprox(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatus.getTotalRecordsInLeafPartitionsApprox()"})
  void testGetTotalRecordsInLeafPartitionsApprox_thenReturnZero() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertEquals(0L, buildResult.getTotalRecordsInLeafPartitionsApprox());
  }

  /**
   * Test {@link TableFilesStatus#getTotalRecordsInNonLeafPartitions()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getTotalRecordsInNonLeafPartitions()}
   */
  @Test
  @DisplayName("Test getTotalRecordsInNonLeafPartitions(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatus.getTotalRecordsInNonLeafPartitions()"})
  void testGetTotalRecordsInNonLeafPartitions_thenReturnZero() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertEquals(0L, buildResult.getTotalRecordsInNonLeafPartitions());
  }

  /**
   * Test {@link TableFilesStatus#getTotalRecordsInNonLeafPartitionsApprox()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilesStatus#getTotalRecordsInNonLeafPartitionsApprox()}
   */
  @Test
  @DisplayName("Test getTotalRecordsInNonLeafPartitionsApprox(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long TableFilesStatus.getTotalRecordsInNonLeafPartitionsApprox()"})
  void testGetTotalRecordsInNonLeafPartitionsApprox_thenReturnZero() {
    // Arrange
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus buildResult = nonLeafPartitionCountResult
        .statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act and Assert
    assertEquals(0L, buildResult.getTotalRecordsInNonLeafPartitionsApprox());
  }
}
