package sleeper.core.statestore.transactionlog.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

class StateStorePartitionsDiffblueTest {
  /**
   * Test {@link StateStorePartitions#from(List)}.
   * <ul>
   *   <li>Then return all size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStorePartitions#from(List)}
   */
  @Test
  @DisplayName("Test from(List); then return all size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStorePartitions StateStorePartitions.from(List)"})
  void testFrom_thenReturnAllSizeIsOne() {
    // Arrange
    ArrayList<Partition> partitions = new ArrayList<>();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    partitions.add(buildResult);

    // Act and Assert
    assertEquals(1, StateStorePartitions.from(partitions).all().size());
  }

  /**
   * Test {@link StateStorePartitions#from(List)}.
   * <ul>
   *   <li>Then return all size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStorePartitions#from(List)}
   */
  @Test
  @DisplayName("Test from(List); then return all size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStorePartitions StateStorePartitions.from(List)"})
  void testFrom_thenReturnAllSizeIsOne2() {
    // Arrange
    ArrayList<Partition> partitions = new ArrayList<>();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    partitions.add(buildResult);
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    partitions.add(buildResult2);

    // Act and Assert
    assertEquals(1, StateStorePartitions.from(partitions).all().size());
  }

  /**
   * Test {@link StateStorePartitions#from(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return all Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStorePartitions#from(List)}
   */
  @Test
  @DisplayName("Test from(List); when ArrayList(); then return all Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStorePartitions StateStorePartitions.from(List)"})
  void testFrom_whenArrayList_thenReturnAllEmpty() {
    // Arrange, Act and Assert
    assertTrue(StateStorePartitions.from(new ArrayList<>()).all().isEmpty());
  }

  /**
   * Test {@link StateStorePartitions#all()}.
   * <p>
   * Method under test: {@link StateStorePartitions#all()}
   */
  @Test
  @DisplayName("Test all()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Collection StateStorePartitions.all()"})
  void testAll() {
    // Arrange, Act and Assert
    assertTrue((new StateStorePartitions()).all().isEmpty());
  }

  /**
   * Test {@link StateStorePartitions#byId(String)}.
   * <p>
   * Method under test: {@link StateStorePartitions#byId(String)}
   */
  @Test
  @DisplayName("Test byId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional StateStorePartitions.byId(String)"})
  void testById() {
    // Arrange, Act and Assert
    assertFalse((new StateStorePartitions()).byId("42").isPresent());
  }

  /**
   * Test {@link StateStorePartitions#put(Partition)}.
   * <p>
   * Method under test: {@link StateStorePartitions#put(Partition)}
   */
  @Test
  @DisplayName("Test put(Partition)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStorePartitions.put(Partition)"})
  void testPut() {
    // Arrange
    StateStorePartitions stateStorePartitions = new StateStorePartitions();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act
    stateStorePartitions.put(partition);

    // Assert
    assertEquals(1, stateStorePartitions.all().size());
  }

  /**
   * Test {@link StateStorePartitions#equals(Object)}, and {@link StateStorePartitions#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStorePartitions#equals(Object)}
   *   <li>{@link StateStorePartitions#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStorePartitions.equals(Object)", "int StateStorePartitions.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    StateStorePartitions stateStorePartitions = new StateStorePartitions();
    StateStorePartitions stateStorePartitions2 = new StateStorePartitions();

    // Act and Assert
    assertEquals(stateStorePartitions, stateStorePartitions2);
    int expectedHashCodeResult = stateStorePartitions.hashCode();
    assertEquals(expectedHashCodeResult, stateStorePartitions2.hashCode());
  }

  /**
   * Test {@link StateStorePartitions#equals(Object)}, and {@link StateStorePartitions#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStorePartitions#equals(Object)}
   *   <li>{@link StateStorePartitions#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStorePartitions.equals(Object)", "int StateStorePartitions.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    StateStorePartitions stateStorePartitions = new StateStorePartitions();

    // Act and Assert
    assertEquals(stateStorePartitions, stateStorePartitions);
    int expectedHashCodeResult = stateStorePartitions.hashCode();
    assertEquals(expectedHashCodeResult, stateStorePartitions.hashCode());
  }

  /**
   * Test {@link StateStorePartitions#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStorePartitions#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStorePartitions.equals(Object)", "int StateStorePartitions.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    StateStorePartitions stateStorePartitions = new StateStorePartitions();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    stateStorePartitions.put(partition);

    // Act and Assert
    assertNotEquals(stateStorePartitions, new StateStorePartitions());
  }

  /**
   * Test {@link StateStorePartitions#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStorePartitions#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStorePartitions.equals(Object)", "int StateStorePartitions.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new StateStorePartitions(), null);
  }

  /**
   * Test {@link StateStorePartitions#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStorePartitions#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStorePartitions.equals(Object)", "int StateStorePartitions.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new StateStorePartitions(), "Different type to StateStorePartitions");
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link StateStorePartitions}
   *   <li>{@link StateStorePartitions#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStorePartitions.<init>()", "String StateStorePartitions.toString()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("StateStorePartitions{partitionById={}}", (new StateStorePartitions()).toString());
  }
}
