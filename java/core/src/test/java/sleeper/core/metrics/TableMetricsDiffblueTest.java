package sleeper.core.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.metrics.TableMetrics.Builder;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.table.TableStatus;

class TableMetricsDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#averageFileReferencesPerPartition(double)}
   *   <li>{@link Builder#fileCount(int)}
   *   <li>{@link Builder#instanceId(String)}
   *   <li>{@link Builder#leafPartitionCount(int)}
   *   <li>{@link Builder#partitionCount(int)}
   *   <li>{@link Builder#recordCount(long)}
   *   <li>{@link Builder#tableName(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.averageFileReferencesPerPartition(double)", "TableMetrics Builder.build()",
      "Builder Builder.fileCount(int)", "Builder Builder.instanceId(String)", "Builder Builder.leafPartitionCount(int)",
      "Builder Builder.partitionCount(int)", "Builder Builder.recordCount(long)", "Builder Builder.tableName(String)"})
  void testBuilderBuild() {
    // Arrange and Act
    TableMetrics actualBuildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getInstanceId());
    assertEquals("Table Name", actualBuildResult.getTableName());
    assertEquals(10.0d, actualBuildResult.getAverageFileReferencesPerPartition());
    assertEquals(3, actualBuildResult.getFileCount());
    assertEquals(3, actualBuildResult.getLeafPartitionCount());
    assertEquals(3, actualBuildResult.getPartitionCount());
    assertEquals(3L, actualBuildResult.getRecordCount());
  }

  /**
   * Test {@link TableMetrics#from(String, TableStatus, StateStore)}.
   * <ul>
   *   <li>Given noFilesReport.</li>
   *   <li>Then return InstanceId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableMetrics#from(String, TableStatus, StateStore)}
   */
  @Test
  @DisplayName("Test from(String, TableStatus, StateStore); given noFilesReport; then return InstanceId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableMetrics TableMetrics.from(String, TableStatus, StateStore)"})
  void testFrom_givenNoFilesReport_thenReturnInstanceIdIs42() throws StateStoreException {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(FilesReportTestHelper.noFilesReport());

    // Act
    TableMetrics actualFromResult = TableMetrics.from("42", table, stateStore);

    // Assert
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(0));
    verify(stateStore).getAllPartitions();
    assertEquals("42", actualFromResult.getInstanceId());
    assertEquals("Table Name", actualFromResult.getTableName());
    assertEquals(0, actualFromResult.getFileCount());
    assertEquals(0, actualFromResult.getLeafPartitionCount());
    assertEquals(0, actualFromResult.getPartitionCount());
    assertEquals(0.0d, actualFromResult.getAverageFileReferencesPerPartition());
    assertEquals(0L, actualFromResult.getRecordCount());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableMetrics#toString()}
   *   <li>{@link TableMetrics#getAverageFileReferencesPerPartition()}
   *   <li>{@link TableMetrics#getFileCount()}
   *   <li>{@link TableMetrics#getInstanceId()}
   *   <li>{@link TableMetrics#getLeafPartitionCount()}
   *   <li>{@link TableMetrics#getPartitionCount()}
   *   <li>{@link TableMetrics#getRecordCount()}
   *   <li>{@link TableMetrics#getTableName()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double TableMetrics.getAverageFileReferencesPerPartition()", "int TableMetrics.getFileCount()",
      "String TableMetrics.getInstanceId()", "int TableMetrics.getLeafPartitionCount()",
      "int TableMetrics.getPartitionCount()", "long TableMetrics.getRecordCount()",
      "String TableMetrics.getTableName()", "String TableMetrics.toString()"})
  void testGettersAndSetters() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    double actualAverageFileReferencesPerPartition = buildResult.getAverageFileReferencesPerPartition();
    int actualFileCount = buildResult.getFileCount();
    String actualInstanceId = buildResult.getInstanceId();
    int actualLeafPartitionCount = buildResult.getLeafPartitionCount();
    int actualPartitionCount = buildResult.getPartitionCount();
    long actualRecordCount = buildResult.getRecordCount();

    // Assert
    assertEquals("42", actualInstanceId);
    assertEquals("Table Name", buildResult.getTableName());
    assertEquals("TableMetrics{instanceId='42', tableName='Table Name', fileCount=3, recordCount=3, partitionCount=3,"
        + " leafPartitionCount=3, averageFileReferencesPerPartition=10.0}", actualToStringResult);
    assertEquals(10.0d, actualAverageFileReferencesPerPartition);
    assertEquals(3, actualFileCount);
    assertEquals(3, actualLeafPartitionCount);
    assertEquals(3, actualPartitionCount);
    assertEquals(3L, actualRecordCount);
  }

  /**
   * Test {@link TableMetrics#equals(Object)}, and {@link TableMetrics#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableMetrics#equals(Object)}
   *   <li>{@link TableMetrics#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();
    TableMetrics buildResult2 = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link TableMetrics#equals(Object)}, and {@link TableMetrics#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableMetrics#equals(Object)}
   *   <li>{@link TableMetrics#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link TableMetrics#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableMetrics#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(0.5d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();
    TableMetrics buildResult2 = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TableMetrics#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableMetrics#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(1)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();
    TableMetrics buildResult2 = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TableMetrics#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableMetrics#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("Instance Id")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();
    TableMetrics buildResult2 = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TableMetrics#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableMetrics#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(1)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();
    TableMetrics buildResult2 = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TableMetrics#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableMetrics#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(1)
        .recordCount(3L)
        .tableName("Table Name")
        .build();
    TableMetrics buildResult2 = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TableMetrics#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableMetrics#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(1L)
        .tableName("Table Name")
        .build();
    TableMetrics buildResult2 = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TableMetrics#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableMetrics#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName(null)
        .build();
    TableMetrics buildResult2 = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TableMetrics#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableMetrics#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link TableMetrics#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableMetrics#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableMetrics.equals(Object)", "int TableMetrics.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to TableMetrics");
  }
}
