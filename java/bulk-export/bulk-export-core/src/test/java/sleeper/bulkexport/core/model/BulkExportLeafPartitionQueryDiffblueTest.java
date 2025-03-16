package sleeper.bulkexport.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.bulkexport.core.model.BulkExportLeafPartitionQuery.Builder;
import sleeper.core.range.Range;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.type.ByteArrayType;

class BulkExportLeafPartitionQueryDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#exportId(String)}
   *   <li>{@link Builder#files(List)}
   *   <li>{@link Builder#leafPartitionId(String)}
   *   <li>{@link Builder#partitionRegion(Region)}
   *   <li>{@link Builder#regions(List)}
   *   <li>{@link Builder#subExportId(String)}
   *   <li>{@link Builder#tableId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BulkExportLeafPartitionQuery Builder.build()", "Builder Builder.exportId(String)",
      "Builder Builder.files(List)", "Builder Builder.leafPartitionId(String)",
      "Builder Builder.partitionRegion(Region)", "Builder Builder.regions(List)", "Builder Builder.subExportId(String)",
      "Builder Builder.tableId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    ArrayList<String> files = new ArrayList<>();
    Builder leafPartitionIdResult = exportIdResult.files(files).leafPartitionId("42");
    Region partitionRegion = new Region(new ArrayList<>());
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(partitionRegion);
    ArrayList<Region> regions = new ArrayList<>();

    // Act
    BulkExportLeafPartitionQuery actualBuildResult = partitionRegionResult.regions(regions)
        .subExportId("42")
        .tableId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getExportId());
    assertEquals("42", actualBuildResult.getLeafPartitionId());
    assertEquals("42", actualBuildResult.getSubExportId());
    assertEquals("42", actualBuildResult.getTableId());
    List<String> files2 = actualBuildResult.getFiles();
    assertTrue(files2.isEmpty());
    List<Region> regions2 = actualBuildResult.getRegions();
    assertTrue(regions2.isEmpty());
    assertSame(files, files2);
    assertSame(regions, regions2);
    assertSame(partitionRegion, actualBuildResult.getPartitionRegion());
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#validate()}.
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuery#validate()}
   */
  @Test
  @DisplayName("Test validate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BulkExportLeafPartitionQuery BulkExportLeafPartitionQuery.validate()"})
  void testValidate() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult = exportIdResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult.validate());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BulkExportLeafPartitionQuery#toString()}
   *   <li>{@link BulkExportLeafPartitionQuery#getExportId()}
   *   <li>{@link BulkExportLeafPartitionQuery#getFiles()}
   *   <li>{@link BulkExportLeafPartitionQuery#getLeafPartitionId()}
   *   <li>{@link BulkExportLeafPartitionQuery#getPartitionRegion()}
   *   <li>{@link BulkExportLeafPartitionQuery#getRegions()}
   *   <li>{@link BulkExportLeafPartitionQuery#getSubExportId()}
   *   <li>{@link BulkExportLeafPartitionQuery#getTableId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Builder BulkExportLeafPartitionQuery.forPartition(sleeper.bulkexport.core.model.BulkExportQuery, sleeper.core.properties.table.TableProperties, sleeper.core.partition.Partition)",
      "String BulkExportLeafPartitionQuery.getExportId()", "List BulkExportLeafPartitionQuery.getFiles()",
      "String BulkExportLeafPartitionQuery.getLeafPartitionId()",
      "Region BulkExportLeafPartitionQuery.getPartitionRegion()", "List BulkExportLeafPartitionQuery.getRegions()",
      "String BulkExportLeafPartitionQuery.getSubExportId()", "String BulkExportLeafPartitionQuery.getTableId()",
      "String BulkExportLeafPartitionQuery.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    ArrayList<String> files = new ArrayList<>();
    Builder leafPartitionIdResult = exportIdResult.files(files).leafPartitionId("42");
    Region partitionRegion = new Region(new ArrayList<>());
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(partitionRegion);
    ArrayList<Region> regions = new ArrayList<>();
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(regions)
        .subExportId("42")
        .tableId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualExportId = buildResult.getExportId();
    List<String> actualFiles = buildResult.getFiles();
    String actualLeafPartitionId = buildResult.getLeafPartitionId();
    Region actualPartitionRegion = buildResult.getPartitionRegion();
    List<Region> actualRegions = buildResult.getRegions();
    String actualSubExportId = buildResult.getSubExportId();

    // Assert
    assertEquals("42", actualExportId);
    assertEquals("42", actualLeafPartitionId);
    assertEquals("42", actualSubExportId);
    assertEquals("42", buildResult.getTableId());
    assertEquals(
        "BulkExportLeafPartitionQuery{tableId='42', exportId='42', subExportId='42', regions=[], leafPartitionId='42',"
            + " partitionRegion=Region{rowKeyFieldNameToRange={}}, files=[]}",
        actualToStringResult);
    assertTrue(actualFiles.isEmpty());
    assertTrue(actualRegions.isEmpty());
    assertSame(files, actualFiles);
    assertSame(regions, actualRegions);
    assertSame(partitionRegion, actualPartitionRegion);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}, and {@link BulkExportLeafPartitionQuery#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BulkExportLeafPartitionQuery#equals(Object)}
   *   <li>{@link BulkExportLeafPartitionQuery#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult = exportIdResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();
    Builder exportIdResult2 = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult2 = exportIdResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult2 = partitionRegionResult2.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}, and {@link BulkExportLeafPartitionQuery#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BulkExportLeafPartitionQuery#equals(Object)}
   *   <li>{@link BulkExportLeafPartitionQuery#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult = exportIdResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("Export Id");
    Builder leafPartitionIdResult = exportIdResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();
    Builder exportIdResult2 = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult2 = exportIdResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult2 = partitionRegionResult2.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");
    Builder leafPartitionIdResult = BulkExportLeafPartitionQuery.builder()
        .exportId("42")
        .files(files)
        .leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult2 = exportIdResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult2 = partitionRegionResult2.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult = exportIdResult.files(new ArrayList<>()).leafPartitionId("Leaf Partition Id");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();
    Builder exportIdResult2 = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult2 = exportIdResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult2 = partitionRegionResult2.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"));
    Region partitionRegion = new Region(ranges);
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder partitionRegionResult = exportIdResult.files(new ArrayList<>())
        .leafPartitionId("42")
        .partitionRegion(partitionRegion);
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();
    Builder exportIdResult2 = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult = exportIdResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult2 = partitionRegionResult2.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    ArrayList<Region> regions = new ArrayList<>();
    regions.add(new Region(new ArrayList<>()));
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult = exportIdResult.files(new ArrayList<>()).leafPartitionId("42");
    BulkExportLeafPartitionQuery buildResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()))
        .regions(regions)
        .subExportId("42")
        .tableId("42")
        .build();
    Builder exportIdResult2 = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult2 = exportIdResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult2 = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult = exportIdResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("Sub Export Id")
        .tableId("42")
        .build();
    Builder exportIdResult2 = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult2 = exportIdResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult2 = partitionRegionResult2.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult = exportIdResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("Table Id")
        .build();
    Builder exportIdResult2 = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult2 = exportIdResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult2 = partitionRegionResult2.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult = exportIdResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkExportLeafPartitionQuery.equals(Object)",
      "int BulkExportLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder exportIdResult = BulkExportLeafPartitionQuery.builder().exportId("42");
    Builder leafPartitionIdResult = exportIdResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery buildResult = partitionRegionResult.regions(new ArrayList<>())
        .subExportId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to BulkExportLeafPartitionQuery");
  }
}
