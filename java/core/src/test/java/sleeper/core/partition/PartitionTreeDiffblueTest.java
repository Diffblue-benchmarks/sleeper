package sleeper.core.partition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.key.Key;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;

class PartitionTreeDiffblueTest {
  /**
   * Test {@link PartitionTree#PartitionTree(Collection)}.
   * <p>
   * Method under test: {@link PartitionTree#PartitionTree(Collection)}
   */
  @Test
  @DisplayName("Test new PartitionTree(Collection)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionTree.<init>(Collection)"})
  void testNewPartitionTree() {
    // Arrange
    ArrayList<Partition> partitions = new ArrayList<>();
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    partitions.add(buildResult);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new PartitionTree(partitions));
  }

  /**
   * Test {@link PartitionTree#PartitionTree(Collection)}.
   * <p>
   * Method under test: {@link PartitionTree#PartitionTree(Collection)}
   */
  @Test
  @DisplayName("Test new PartitionTree(Collection)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionTree.<init>(Collection)"})
  void testNewPartitionTree2() {
    // Arrange
    ArrayList<Partition> partitions = new ArrayList<>();
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    partitions.add(buildResult);
    Partition.Builder builderResult2 = Partition.builder();
    Partition.Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    partitions.add(buildResult2);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new PartitionTree(partitions));
  }

  /**
   * Test {@link PartitionTree#PartitionTree(Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#PartitionTree(Collection)}
   */
  @Test
  @DisplayName("Test new PartitionTree(Collection); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionTree.<init>(Collection)"})
  void testNewPartitionTree_whenArrayList() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new PartitionTree(new ArrayList<>()));
  }

  /**
   * Test {@link PartitionTree#getChildIds(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getChildIds(String)}
   */
  @Test
  @DisplayName("Test getChildIds(String); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionTree.getChildIds(String)"})
  void testGetChildIds_thenThrowIllegalArgumentException() throws IllegalArgumentException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>()).getChildIds("42"));
  }

  /**
   * Test {@link PartitionTree#getAllAncestorIds(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getAllAncestorIds(String)}
   */
  @Test
  @DisplayName("Test getAllAncestorIds(String); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionTree.getAllAncestorIds(String)"})
  void testGetAllAncestorIds_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>()).getAllAncestorIds("42"));
  }

  /**
   * Test {@link PartitionTree#getAllAncestors(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getAllAncestors(String)}
   */
  @Test
  @DisplayName("Test getAllAncestors(String); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionTree.getAllAncestors(String)"})
  void testGetAllAncestors_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>()).getAllAncestors("42"));
  }

  /**
   * Test {@link PartitionTree#ancestorsOf(Partition)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link Partition} {@link Partition#getParentPartitionId()} return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#ancestorsOf(Partition)}
   */
  @Test
  @DisplayName("Test ancestorsOf(Partition); given '42'; when Partition getParentPartitionId() return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream PartitionTree.ancestorsOf(Partition)"})
  void testAncestorsOf_given42_whenPartitionGetParentPartitionIdReturn42() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition partition = mock(Partition.class);
    when(partition.getParentPartitionId()).thenReturn("42");

    // Act
    Stream<Partition> actualAncestorsOfResult = treeFromResult.ancestorsOf(partition);

    // Assert
    verify(partition, atLeast(1)).getParentPartitionId();
    assertTrue(actualAncestorsOfResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link PartitionTree#ancestorsOf(Partition)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link Partition} {@link Partition#getParentPartitionId()} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#ancestorsOf(Partition)}
   */
  @Test
  @DisplayName("Test ancestorsOf(Partition); given 'null'; when Partition getParentPartitionId() return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream PartitionTree.ancestorsOf(Partition)"})
  void testAncestorsOf_givenNull_whenPartitionGetParentPartitionIdReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition partition = mock(Partition.class);
    when(partition.getParentPartitionId()).thenReturn(null);

    // Act
    Stream<Partition> actualAncestorsOfResult = treeFromResult.ancestorsOf(partition);

    // Assert
    verify(partition).getParentPartitionId();
    assertTrue(actualAncestorsOfResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link PartitionTree#ancestorsOf(Partition)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#ancestorsOf(Partition)}
   */
  @Test
  @DisplayName("Test ancestorsOf(Partition); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream PartitionTree.ancestorsOf(Partition)"})
  void testAncestorsOf_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition partition = mock(Partition.class);
    when(partition.getParentPartitionId()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> treeFromResult.ancestorsOf(partition));
    verify(partition).getParentPartitionId();
  }

  /**
   * Test {@link PartitionTree#descendentsOf(Partition)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#descendentsOf(Partition)}
   */
  @Test
  @DisplayName("Test descendentsOf(Partition); given ArrayList(); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream PartitionTree.descendentsOf(Partition)"})
  void testDescendentsOf_givenArrayList_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition partition = mock(Partition.class);
    when(partition.getChildPartitionIds()).thenReturn(new ArrayList<>());

    // Act
    Stream<Partition> actualDescendentsOfResult = treeFromResult.descendentsOf(partition);

    // Assert
    verify(partition).getChildPartitionIds();
    assertTrue(actualDescendentsOfResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link PartitionTree#descendentsOf(Partition)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#descendentsOf(Partition)}
   */
  @Test
  @DisplayName("Test descendentsOf(Partition); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream PartitionTree.descendentsOf(Partition)"})
  void testDescendentsOf_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition partition = mock(Partition.class);
    when(partition.getChildPartitionIds()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> treeFromResult.descendentsOf(partition));
    verify(partition).getChildPartitionIds();
  }

  /**
   * Test {@link PartitionTree#getParent(Partition)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link Partition} {@link Partition#getParentPartitionId()} return {@code 42}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getParent(Partition)}
   */
  @Test
  @DisplayName("Test getParent(Partition); given '42'; when Partition getParentPartitionId() return '42'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getParent(Partition)"})
  void testGetParent_given42_whenPartitionGetParentPartitionIdReturn42_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition partition = mock(Partition.class);
    when(partition.getParentPartitionId()).thenReturn("42");

    // Act
    Partition actualParent = treeFromResult.getParent(partition);

    // Assert
    verify(partition, atLeast(1)).getParentPartitionId();
    assertNull(actualParent);
  }

  /**
   * Test {@link PartitionTree#getParent(Partition)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link Partition} {@link Partition#getParentPartitionId()} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getParent(Partition)}
   */
  @Test
  @DisplayName("Test getParent(Partition); given 'null'; when Partition getParentPartitionId() return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getParent(Partition)"})
  void testGetParent_givenNull_whenPartitionGetParentPartitionIdReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition partition = mock(Partition.class);
    when(partition.getParentPartitionId()).thenReturn(null);

    // Act
    Partition actualParent = treeFromResult.getParent(partition);

    // Assert
    verify(partition).getParentPartitionId();
    assertNull(actualParent);
  }

  /**
   * Test {@link PartitionTree#getParent(Partition)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getParent(Partition)}
   */
  @Test
  @DisplayName("Test getParent(Partition); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getParent(Partition)"})
  void testGetParent_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition partition = mock(Partition.class);
    when(partition.getParentPartitionId()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> treeFromResult.getParent(partition));
    verify(partition).getParentPartitionId();
  }

  /**
   * Test {@link PartitionTree#getPartition(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getPartition(String)}
   */
  @Test
  @DisplayName("Test getPartition(String); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getPartition(String)"})
  void testGetPartition_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertNull(PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>()).getPartition("42"));
  }

  /**
   * Test {@link PartitionTree#getAllPartitions()}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getAllPartitions()}
   */
  @Test
  @DisplayName("Test getAllPartitions(); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionTree.getAllPartitions()"})
  void testGetAllPartitions_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<Partition> actualAllPartitions = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>())
        .getAllPartitions();

    // Assert
    assertEquals(1, actualAllPartitions.size());
    Partition getResult = actualAllPartitions.get(0);
    assertEquals("root", getResult.getId());
    Partition.Builder toBuilderResult = getResult.toBuilder();
    assertEquals("root", toBuilderResult.getId());
    assertNull(getResult.getParentPartitionId());
    assertEquals(-1, getResult.getDimension());
    Region region = getResult.getRegion();
    assertEquals(1, region.getRanges().size());
    assertTrue(getResult.getChildPartitionIds().isEmpty());
    assertTrue(getResult.isLeafPartition());
    assertSame(region, toBuilderResult.getRegion());
  }

  /**
   * Test {@link PartitionTree#getLeafPartitions()}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getLeafPartitions()}
   */
  @Test
  @DisplayName("Test getLeafPartitions(); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionTree.getLeafPartitions()"})
  void testGetLeafPartitions_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<Partition> actualLeafPartitions = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>())
        .getLeafPartitions();

    // Assert
    assertEquals(1, actualLeafPartitions.size());
    Partition getResult = actualLeafPartitions.get(0);
    assertEquals("root", getResult.getId());
    Partition.Builder toBuilderResult = getResult.toBuilder();
    assertEquals("root", toBuilderResult.getId());
    assertNull(getResult.getParentPartitionId());
    assertEquals(-1, getResult.getDimension());
    Region region = getResult.getRegion();
    assertEquals(1, region.getRanges().size());
    assertTrue(getResult.getChildPartitionIds().isEmpty());
    assertTrue(getResult.isLeafPartition());
    assertSame(region, toBuilderResult.getRegion());
  }

  /**
   * Test {@link PartitionTree#getLeafPartition(Schema, Key)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getLeafPartition(Schema, Key)}
   */
  @Test
  @DisplayName("Test getLeafPartition(Schema, Key); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getLeafPartition(Schema, Key)"})
  void testGetLeafPartition_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Schema schema2 = mock(Schema.class);
    Key key = mock(Key.class);
    when(key.size()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> treeFromResult.getLeafPartition(schema2, key));
    verify(key).size();
  }

  /**
   * Test {@link PartitionTree#getLeafPartition(Schema, Key)}.
   * <ul>
   *   <li>Given three.</li>
   *   <li>When {@link Key} {@link Key#size()} return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getLeafPartition(Schema, Key)}
   */
  @Test
  @DisplayName("Test getLeafPartition(Schema, Key); given three; when Key size() return three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getLeafPartition(Schema, Key)"})
  void testGetLeafPartition_givenThree_whenKeySizeReturnThree() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Schema schema2 = mock(Schema.class);
    when(schema2.getRowKeyFields()).thenReturn(new ArrayList<>());
    Key key = mock(Key.class);
    when(key.size()).thenReturn(3);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> treeFromResult.getLeafPartition(schema2, key));
    verify(key).size();
    verify(schema2, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionTree#getLeafPartition(Schema, Key)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link Key} {@link Key#size()} return zero.</li>
   *   <li>Then return Id is {@code root}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getLeafPartition(Schema, Key)}
   */
  @Test
  @DisplayName("Test getLeafPartition(Schema, Key); given zero; when Key size() return zero; then return Id is 'root'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getLeafPartition(Schema, Key)"})
  void testGetLeafPartition_givenZero_whenKeySizeReturnZero_thenReturnIdIsRoot() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Schema schema2 = mock(Schema.class);
    when(schema2.getRowKeyFields()).thenReturn(new ArrayList<>());
    Key key = mock(Key.class);
    when(key.size()).thenReturn(0);

    // Act
    Partition actualLeafPartition = treeFromResult.getLeafPartition(schema2, key);

    // Assert
    verify(key).size();
    verify(schema2).getRowKeyFields();
    assertEquals("root", actualLeafPartition.getId());
    Partition.Builder toBuilderResult = actualLeafPartition.toBuilder();
    assertEquals("root", toBuilderResult.getId());
    assertNull(actualLeafPartition.getParentPartitionId());
    assertEquals(-1, actualLeafPartition.getDimension());
    Region region = actualLeafPartition.getRegion();
    assertEquals(1, region.getRanges().size());
    assertTrue(actualLeafPartition.getChildPartitionIds().isEmpty());
    assertTrue(actualLeafPartition.isLeafPartition());
    assertSame(region, toBuilderResult.getRegion());
  }

  /**
   * Test {@link PartitionTree#getNearestCommonAncestor(Partition, Partition)} with {@code a}, {@code b}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link Partition} {@link Partition#getId()} return {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getNearestCommonAncestor(Partition, Partition)}
   */
  @Test
  @DisplayName("Test getNearestCommonAncestor(Partition, Partition) with 'a', 'b'; given 'foo'; when Partition getId() return 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getNearestCommonAncestor(Partition, Partition)"})
  void testGetNearestCommonAncestorWithAB_givenFoo_whenPartitionGetIdReturnFoo() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition a = mock(Partition.class);
    when(a.getId()).thenReturn("foo");
    Partition b = mock(Partition.class);
    when(b.getId()).thenReturn("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> treeFromResult.getNearestCommonAncestor(a, b));
    verify(a).getId();
    verify(b, atLeast(1)).getId();
  }

  /**
   * Test {@link PartitionTree#getNearestCommonAncestor(Partition, Partition)} with {@code a}, {@code b}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getNearestCommonAncestor(Partition, Partition)}
   */
  @Test
  @DisplayName("Test getNearestCommonAncestor(Partition, Partition) with 'a', 'b'; given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getNearestCommonAncestor(Partition, Partition)"})
  void testGetNearestCommonAncestorWithAB_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition a = mock(Partition.class);
    when(a.getId()).thenReturn("42");
    Partition b = mock(Partition.class);
    when(b.getId()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> treeFromResult.getNearestCommonAncestor(a, b));
    verify(a).getId();
    verify(b).getId();
  }

  /**
   * Test {@link PartitionTree#getNearestCommonAncestor(Partition, Partition)} with {@code a}, {@code b}.
   * <ul>
   *   <li>Then return {@link Partition}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getNearestCommonAncestor(Partition, Partition)}
   */
  @Test
  @DisplayName("Test getNearestCommonAncestor(Partition, Partition) with 'a', 'b'; then return Partition")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getNearestCommonAncestor(Partition, Partition)"})
  void testGetNearestCommonAncestorWithAB_thenReturnPartition() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Partition a = mock(Partition.class);
    when(a.getId()).thenReturn("42");
    Partition b = mock(Partition.class);
    when(b.getId()).thenReturn("42");

    // Act
    Partition actualNearestCommonAncestor = treeFromResult.getNearestCommonAncestor(a, b);

    // Assert
    verify(a).getId();
    verify(b).getId();
    assertSame(a, actualNearestCommonAncestor);
  }

  /**
   * Test {@link PartitionTree#getNearestCommonAncestor(Schema, Key, Key)} with {@code schema}, {@code a}, {@code b}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code root}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getNearestCommonAncestor(Schema, Key, Key)}
   */
  @Test
  @DisplayName("Test getNearestCommonAncestor(Schema, Key, Key) with 'schema', 'a', 'b'; given IllegalArgumentException(String) with 'root'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getNearestCommonAncestor(Schema, Key, Key)"})
  void testGetNearestCommonAncestorWithSchemaAB_givenIllegalArgumentExceptionWithRoot() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Schema schema2 = mock(Schema.class);
    Key a = mock(Key.class);
    when(a.size()).thenThrow(new IllegalArgumentException("root"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> treeFromResult.getNearestCommonAncestor(schema2, a, mock(Key.class)));
    verify(a).size();
  }

  /**
   * Test {@link PartitionTree#getNearestCommonAncestor(Schema, Key, Key)} with {@code schema}, {@code a}, {@code b}.
   * <ul>
   *   <li>Given three.</li>
   *   <li>When {@link Key} {@link Key#size()} return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getNearestCommonAncestor(Schema, Key, Key)}
   */
  @Test
  @DisplayName("Test getNearestCommonAncestor(Schema, Key, Key) with 'schema', 'a', 'b'; given three; when Key size() return three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getNearestCommonAncestor(Schema, Key, Key)"})
  void testGetNearestCommonAncestorWithSchemaAB_givenThree_whenKeySizeReturnThree() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Schema schema2 = mock(Schema.class);
    when(schema2.getRowKeyFields()).thenReturn(new ArrayList<>());
    Key a = mock(Key.class);
    when(a.size()).thenReturn(3);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> treeFromResult.getNearestCommonAncestor(schema2, a, mock(Key.class)));
    verify(a).size();
    verify(schema2, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionTree#getNearestCommonAncestor(Schema, Key, Key)} with {@code schema}, {@code a}, {@code b}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>Then return Id is {@code root}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getNearestCommonAncestor(Schema, Key, Key)}
   */
  @Test
  @DisplayName("Test getNearestCommonAncestor(Schema, Key, Key) with 'schema', 'a', 'b'; given zero; then return Id is 'root'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getNearestCommonAncestor(Schema, Key, Key)"})
  void testGetNearestCommonAncestorWithSchemaAB_givenZero_thenReturnIdIsRoot() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Schema schema2 = mock(Schema.class);
    when(schema2.getRowKeyFields()).thenReturn(new ArrayList<>());
    Key a = mock(Key.class);
    when(a.size()).thenReturn(0);
    Key b = mock(Key.class);
    when(b.size()).thenReturn(0);

    // Act
    Partition actualNearestCommonAncestor = treeFromResult.getNearestCommonAncestor(schema2, a, b);

    // Assert
    verify(a).size();
    verify(b).size();
    verify(schema2, atLeast(1)).getRowKeyFields();
    assertEquals("root", actualNearestCommonAncestor.getId());
    Partition.Builder toBuilderResult = actualNearestCommonAncestor.toBuilder();
    assertEquals("root", toBuilderResult.getId());
    assertNull(actualNearestCommonAncestor.getParentPartitionId());
    assertEquals(-1, actualNearestCommonAncestor.getDimension());
    Region region = actualNearestCommonAncestor.getRegion();
    assertEquals(1, region.getRanges().size());
    assertTrue(actualNearestCommonAncestor.getChildPartitionIds().isEmpty());
    assertTrue(actualNearestCommonAncestor.isLeafPartition());
    assertSame(region, toBuilderResult.getRegion());
  }

  /**
   * Test {@link PartitionTree#getNearestCommonAncestor(Schema, Key, Key)} with {@code schema}, {@code a}, {@code b}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link Key} {@link Key#size()} return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#getNearestCommonAncestor(Schema, Key, Key)}
   */
  @Test
  @DisplayName("Test getNearestCommonAncestor(Schema, Key, Key) with 'schema', 'a', 'b'; given zero; when Key size() return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionTree.getNearestCommonAncestor(Schema, Key, Key)"})
  void testGetNearestCommonAncestorWithSchemaAB_givenZero_whenKeySizeReturnZero() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());
    Schema schema2 = mock(Schema.class);
    when(schema2.getRowKeyFields()).thenReturn(new ArrayList<>());
    Key a = mock(Key.class);
    when(a.size()).thenReturn(0);
    Key b = mock(Key.class);
    when(b.size()).thenReturn(3);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> treeFromResult.getNearestCommonAncestor(schema2, a, b));
    verify(a).size();
    verify(b).size();
    verify(schema2, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionTree#traverseLeavesFirst()}.
   * <ul>
   *   <li>Then return limit five collect toList size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#traverseLeavesFirst()}
   */
  @Test
  @DisplayName("Test traverseLeavesFirst(); then return limit five collect toList size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream PartitionTree.traverseLeavesFirst()"})
  void testTraverseLeavesFirst_thenReturnLimitFiveCollectToListSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    Stream<Partition> actualTraverseLeavesFirstResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>())
        .traverseLeavesFirst();

    // Assert
    List<Partition> collectResult = actualTraverseLeavesFirstResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    Partition getResult = collectResult.get(0);
    assertEquals("root", getResult.getId());
    Partition.Builder toBuilderResult = getResult.toBuilder();
    assertEquals("root", toBuilderResult.getId());
    assertNull(getResult.getParentPartitionId());
    assertEquals(-1, getResult.getDimension());
    Region region = getResult.getRegion();
    assertEquals(1, region.getRanges().size());
    assertTrue(getResult.getChildPartitionIds().isEmpty());
    assertTrue(getResult.isLeafPartition());
    assertSame(region, toBuilderResult.getRegion());
  }

  /**
   * Test {@link PartitionTree#streamLeavesInTreeOrder()}.
   * <ul>
   *   <li>Then return limit five collect toList size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#streamLeavesInTreeOrder()}
   */
  @Test
  @DisplayName("Test streamLeavesInTreeOrder(); then return limit five collect toList size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream PartitionTree.streamLeavesInTreeOrder()"})
  void testStreamLeavesInTreeOrder_thenReturnLimitFiveCollectToListSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    Stream<Partition> actualStreamLeavesInTreeOrderResult = PartitionsFromSplitPoints
        .treeFrom(schema, new ArrayList<>())
        .streamLeavesInTreeOrder();

    // Assert
    List<Partition> collectResult = actualStreamLeavesInTreeOrderResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    Partition getResult = collectResult.get(0);
    assertEquals("root", getResult.getId());
    Partition.Builder toBuilderResult = getResult.toBuilder();
    assertEquals("root", toBuilderResult.getId());
    assertNull(getResult.getParentPartitionId());
    assertEquals(-1, getResult.getDimension());
    Region region = getResult.getRegion();
    assertEquals(1, region.getRanges().size());
    assertTrue(getResult.getChildPartitionIds().isEmpty());
    assertTrue(getResult.isLeafPartition());
    assertSame(region, toBuilderResult.getRegion());
  }

  /**
   * Test {@link PartitionTree#equals(Object)}, and {@link PartitionTree#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PartitionTree#equals(Object)}
   *   <li>{@link PartitionTree#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PartitionTree.equals(Object)", "int PartitionTree.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());

    ArrayList<Field> rowKeyFields2 = new ArrayList<>();
    rowKeyFields2.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult2 = Schema.builder().rowKeyFields(rowKeyFields2);
    Builder sortKeyFieldsResult2 = rowKeyFieldsResult2.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult2.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult2 = PartitionsFromSplitPoints.treeFrom(schema2, new ArrayList<>());

    // Act and Assert
    assertEquals(treeFromResult, treeFromResult2);
    int expectedHashCodeResult = treeFromResult.hashCode();
    assertEquals(expectedHashCodeResult, treeFromResult2.hashCode());
  }

  /**
   * Test {@link PartitionTree#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PartitionTree.equals(Object)", "int PartitionTree.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());

    ArrayList<Field> rowKeyFields2 = new ArrayList<>();
    rowKeyFields2.add(new Field("java.lang.String", new ByteArrayType()));
    Builder rowKeyFieldsResult2 = Schema.builder().rowKeyFields(rowKeyFields2);
    Builder sortKeyFieldsResult2 = rowKeyFieldsResult2.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult2.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(treeFromResult, PartitionsFromSplitPoints.treeFrom(schema2, new ArrayList<>()));
  }

  /**
   * Test {@link PartitionTree#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PartitionTree.equals(Object)", "int PartitionTree.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree treeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());

    // Act and Assert
    assertNotEquals(treeFromResult, new ByteArrayType());
  }

  /**
   * Test {@link PartitionTree#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionTree#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PartitionTree.equals(Object)", "int PartitionTree.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>()), null);
  }
}
