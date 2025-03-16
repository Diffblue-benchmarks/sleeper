package sleeper.splitter.core.find;

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
import sleeper.core.partition.Partition;
import sleeper.core.partition.Partition.Builder;
import sleeper.core.range.Region;

class SplitPartitionJobDefinitionDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitPartitionJobDefinition#SplitPartitionJobDefinition(String, Partition, List)}
   *   <li>{@link SplitPartitionJobDefinition#toString()}
   *   <li>{@link SplitPartitionJobDefinition#getFileNames()}
   *   <li>{@link SplitPartitionJobDefinition#getPartition()}
   *   <li>{@link SplitPartitionJobDefinition#getTableId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionJobDefinition.<init>(String, Partition, List)",
      "List SplitPartitionJobDefinition.getFileNames()", "Partition SplitPartitionJobDefinition.getPartition()",
      "String SplitPartitionJobDefinition.getTableId()", "String SplitPartitionJobDefinition.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    ArrayList<String> fileNames = new ArrayList<>();

    // Act
    SplitPartitionJobDefinition actualSplitPartitionJobDefinition = new SplitPartitionJobDefinition("42", partition,
        fileNames);
    String actualToStringResult = actualSplitPartitionJobDefinition.toString();
    List<String> actualFileNames = actualSplitPartitionJobDefinition.getFileNames();
    Partition actualPartition = actualSplitPartitionJobDefinition.getPartition();

    // Assert
    assertEquals("42", actualSplitPartitionJobDefinition.getTableId());
    assertEquals(
        "SplitPartitionJobDefinition{tableId='42', partition=Partition{region=Region{rowKeyFieldNameToRange={}},"
            + " id='42', leafPartition=true, parentPartitionId='42', childPartitionIds=[], dimension=1},"
            + " fileNames=[]}",
        actualToStringResult);
    assertTrue(actualFileNames.isEmpty());
    assertSame(fileNames, actualFileNames);
    assertSame(partition, actualPartition);
  }

  /**
   * Test {@link SplitPartitionJobDefinition#equals(Object)}, and {@link SplitPartitionJobDefinition#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitPartitionJobDefinition#equals(Object)}
   *   <li>{@link SplitPartitionJobDefinition#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionJobDefinition.equals(Object)",
      "int SplitPartitionJobDefinition.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionJobDefinition splitPartitionJobDefinition = new SplitPartitionJobDefinition("42", partition,
        new ArrayList<>());
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    SplitPartitionJobDefinition splitPartitionJobDefinition2 = new SplitPartitionJobDefinition("42", partition2,
        new ArrayList<>());

    // Act and Assert
    assertEquals(splitPartitionJobDefinition, splitPartitionJobDefinition2);
    int expectedHashCodeResult = splitPartitionJobDefinition.hashCode();
    assertEquals(expectedHashCodeResult, splitPartitionJobDefinition2.hashCode());
  }

  /**
   * Test {@link SplitPartitionJobDefinition#equals(Object)}, and {@link SplitPartitionJobDefinition#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitPartitionJobDefinition#equals(Object)}
   *   <li>{@link SplitPartitionJobDefinition#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionJobDefinition.equals(Object)",
      "int SplitPartitionJobDefinition.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionJobDefinition splitPartitionJobDefinition = new SplitPartitionJobDefinition("42", partition,
        new ArrayList<>());

    // Act and Assert
    assertEquals(splitPartitionJobDefinition, splitPartitionJobDefinition);
    int expectedHashCodeResult = splitPartitionJobDefinition.hashCode();
    assertEquals(expectedHashCodeResult, splitPartitionJobDefinition.hashCode());
  }

  /**
   * Test {@link SplitPartitionJobDefinition#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionJobDefinition.equals(Object)",
      "int SplitPartitionJobDefinition.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionJobDefinition splitPartitionJobDefinition = new SplitPartitionJobDefinition("Table Id", partition,
        new ArrayList<>());
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(splitPartitionJobDefinition, new SplitPartitionJobDefinition("42", partition2, new ArrayList<>()));
  }

  /**
   * Test {@link SplitPartitionJobDefinition#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionJobDefinition.equals(Object)",
      "int SplitPartitionJobDefinition.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add("foo");
    Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionJobDefinition splitPartitionJobDefinition = new SplitPartitionJobDefinition("42", partition,
        new ArrayList<>());
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(splitPartitionJobDefinition, new SplitPartitionJobDefinition("42", partition2, new ArrayList<>()));
  }

  /**
   * Test {@link SplitPartitionJobDefinition#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionJobDefinition.equals(Object)",
      "int SplitPartitionJobDefinition.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<String> fileNames = new ArrayList<>();
    fileNames.add("foo");
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionJobDefinition splitPartitionJobDefinition = new SplitPartitionJobDefinition("42", partition,
        fileNames);
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(splitPartitionJobDefinition, new SplitPartitionJobDefinition("42", partition2, new ArrayList<>()));
  }

  /**
   * Test {@link SplitPartitionJobDefinition#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionJobDefinition.equals(Object)",
      "int SplitPartitionJobDefinition.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(new SplitPartitionJobDefinition("42", partition, new ArrayList<>()), null);
  }

  /**
   * Test {@link SplitPartitionJobDefinition#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinition#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionJobDefinition.equals(Object)",
      "int SplitPartitionJobDefinition.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(new SplitPartitionJobDefinition("42", partition, new ArrayList<>()),
        "Different type to SplitPartitionJobDefinition");
  }
}
