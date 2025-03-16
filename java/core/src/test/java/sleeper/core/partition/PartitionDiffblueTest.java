package sleeper.core.partition;

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
import sleeper.core.partition.Partition.Builder;
import sleeper.core.range.Range;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.type.ByteArrayType;

class PartitionDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#childPartitionIds(List)}
   *   <li>{@link Builder#dimension(int)}
   *   <li>{@link Builder#id(String)}
   *   <li>{@link Builder#leafPartition(boolean)}
   *   <li>{@link Builder#parentPartitionId(String)}
   *   <li>{@link Builder#region(Region)}
   *   <li>{@link Builder#getId()}
   *   <li>{@link Builder#getRegion()}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition Builder.build()", "Builder Builder.childPartitionIds(List)",
      "Builder Builder.dimension(int)", "String Builder.getId()", "Region Builder.getRegion()",
      "Builder Builder.id(String)", "Builder Builder.leafPartition(boolean)",
      "Builder Builder.parentPartitionId(String)", "Builder Builder.region(Region)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = Partition.builder();
    ArrayList<String> childPartitionIds = new ArrayList<>();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Region region = new Region(new ArrayList<>());

    // Act
    Partition actualBuildResult = parentPartitionIdResult.region(region).build();

    // Assert
    assertEquals("42", actualBuildResult.getId());
    assertEquals("42", actualBuildResult.getParentPartitionId());
    Builder toBuilderResult = actualBuildResult.toBuilder();
    assertEquals("42", toBuilderResult.getId());
    assertEquals(1, actualBuildResult.getDimension());
    List<String> childPartitionIds2 = actualBuildResult.getChildPartitionIds();
    assertTrue(childPartitionIds2.isEmpty());
    assertTrue(actualBuildResult.isLeafPartition());
    assertSame(childPartitionIds, childPartitionIds2);
    assertSame(region, actualBuildResult.getRegion());
    assertSame(region, toBuilderResult.getRegion());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Partition#toString()}
   *   <li>{@link Partition#getChildPartitionIds()}
   *   <li>{@link Partition#getDimension()}
   *   <li>{@link Partition#getId()}
   *   <li>{@link Partition#getParentPartitionId()}
   *   <li>{@link Partition#getRegion()}
   *   <li>{@link Partition#isLeafPartition()}
   *   <li>{@link Partition#toBuilder()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List Partition.getChildPartitionIds()", "int Partition.getDimension()",
      "String Partition.getId()", "String Partition.getParentPartitionId()", "Region Partition.getRegion()",
      "boolean Partition.isLeafPartition()", "Builder Partition.toBuilder()", "String Partition.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = Partition.builder();
    ArrayList<String> childPartitionIds = new ArrayList<>();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Region region = new Region(new ArrayList<>());
    Partition buildResult = parentPartitionIdResult.region(region).build();

    // Act
    String actualToStringResult = buildResult.toString();
    List<String> actualChildPartitionIds = buildResult.getChildPartitionIds();
    int actualDimension = buildResult.getDimension();
    String actualId = buildResult.getId();
    String actualParentPartitionId = buildResult.getParentPartitionId();
    Region actualRegion = buildResult.getRegion();
    boolean actualIsLeafPartitionResult = buildResult.isLeafPartition();
    Builder actualToBuilderResult = buildResult.toBuilder();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualParentPartitionId);
    assertEquals("42", actualToBuilderResult.getId());
    assertEquals(
        "Partition{region=Region{rowKeyFieldNameToRange={}}, id='42', leafPartition=true, parentPartitionId='42',"
            + " childPartitionIds=[], dimension=1}",
        actualToStringResult);
    assertEquals(1, actualDimension);
    assertTrue(actualChildPartitionIds.isEmpty());
    assertTrue(actualIsLeafPartitionResult);
    assertSame(childPartitionIds, actualChildPartitionIds);
    assertSame(region, actualRegion);
    assertSame(region, actualToBuilderResult.getRegion());
  }

  /**
   * Test {@link Partition#equals(Object)}, and {@link Partition#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Partition#equals(Object)}
   *   <li>{@link Partition#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Partition.equals(Object)", "int Partition.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link Partition#equals(Object)}, and {@link Partition#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Partition#equals(Object)}
   *   <li>{@link Partition#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Partition.equals(Object)", "int Partition.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link Partition#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Partition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Partition.equals(Object)", "int Partition.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add("foo");
    Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link Partition#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Partition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Partition.equals(Object)", "int Partition.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(0)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link Partition#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Partition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Partition.equals(Object)", "int Partition.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("Id")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link Partition#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Partition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Partition.equals(Object)", "int Partition.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(false)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link Partition#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Partition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Partition.equals(Object)", "int Partition.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("Parent Partition Id");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link Partition#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Partition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Partition.equals(Object)", "int Partition.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"));
    Region region = new Region(ranges);
    Builder builderResult = Partition.builder();
    Partition buildResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42")
        .region(region)
        .build();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link Partition#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Partition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Partition.equals(Object)", "int Partition.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link Partition#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Partition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Partition.equals(Object)", "int Partition.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to Partition");
  }
}
