package sleeper.core.partition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.range.Range;
import sleeper.core.range.Range.RangeFactory;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;
import sleeper.core.schema.type.Type;

class PartitionsFromSplitPointsDiffblueTest {
  /**
   * Test {@link PartitionsFromSplitPoints#PartitionsFromSplitPoints(Schema, List)}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#PartitionsFromSplitPoints(Schema, List)}
   */
  @Test
  @DisplayName("Test new PartitionsFromSplitPoints(Schema, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionsFromSplitPoints.<init>(Schema, List)"})
  void testNewPartitionsFromSplitPoints() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    new PartitionsFromSplitPoints(schema, new ArrayList<>());

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#PartitionsFromSplitPoints(Schema, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#PartitionsFromSplitPoints(Schema, List)}
   */
  @Test
  @DisplayName("Test new PartitionsFromSplitPoints(Schema, List); given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionsFromSplitPoints.<init>(Schema, List)"})
  void testNewPartitionsFromSplitPoints_given42_whenArrayListAdd42() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act
    new PartitionsFromSplitPoints(schema, splitPoints);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#PartitionsFromSplitPoints(Schema, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#PartitionsFromSplitPoints(Schema, List)}
   */
  @Test
  @DisplayName("Test new PartitionsFromSplitPoints(Schema, List); given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionsFromSplitPoints.<init>(Schema, List)"})
  void testNewPartitionsFromSplitPoints_given42_whenArrayListAdd422() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");
    splitPoints.add("42");

    // Act
    new PartitionsFromSplitPoints(schema, splitPoints);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#PartitionsFromSplitPoints(Schema, List)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#PartitionsFromSplitPoints(Schema, List)}
   */
  @Test
  @DisplayName("Test new PartitionsFromSplitPoints(Schema, List); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionsFromSplitPoints.<init>(Schema, List)"})
  void testNewPartitionsFromSplitPoints_thenThrowIllegalArgumentException() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new PartitionsFromSplitPoints(schema, new ArrayList<>()));

    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#PartitionsFromSplitPoints(Schema, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return construct size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#PartitionsFromSplitPoints(Schema, List)}
   */
  @Test
  @DisplayName("Test new PartitionsFromSplitPoints(Schema, List); when ArrayList(); then return construct size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionsFromSplitPoints.<init>(Schema, List)"})
  void testNewPartitionsFromSplitPoints_whenArrayList_thenReturnConstructSizeIsOne() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    PartitionsFromSplitPoints actualPartitionsFromSplitPoints = new PartitionsFromSplitPoints(schema,
        new ArrayList<>());

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
    List<Partition> constructResult = actualPartitionsFromSplitPoints.construct();
    assertEquals(1, constructResult.size());
    Partition getResult = constructResult.get(0);
    assertEquals("root", getResult.getId());
    Partition.Builder toBuilderResult = getResult.toBuilder();
    assertEquals("root", toBuilderResult.getId());
    assertNull(getResult.getParentPartitionId());
    assertEquals(-1, getResult.getDimension());
    assertTrue(getResult.getChildPartitionIds().isEmpty());
    Region region = getResult.getRegion();
    assertTrue(region.getRanges().isEmpty());
    assertTrue(getResult.isLeafPartition());
    assertSame(region, toBuilderResult.getRegion());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new ByteArrayType()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new ByteArrayType()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new ByteArrayType()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add '42'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAdd42_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ByteArrayType} (default constructor).</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add ByteArrayType (default constructor); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddByteArrayType_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(new ByteArrayType());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add empty string.</li>
   *   <li>Then return size is five.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add empty string; then return size is five")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddEmptyString_thenReturnSizeIsFive() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("");
    splitPoints.add("42");

    // Act
    List<Partition> actualConstructResult = (new PartitionsFromSplitPoints(schema, splitPoints)).construct();

    // Assert
    assertEquals(5, actualConstructResult.size());
    Partition getResult = actualConstructResult.get(4);
    assertNull(getResult.getParentPartitionId());
    Partition getResult2 = actualConstructResult.get(2);
    assertEquals(-1, getResult2.getDimension());
    Partition getResult3 = actualConstructResult.get(3);
    assertEquals(0, getResult3.getDimension());
    assertEquals(0, getResult.getDimension());
    assertEquals(2, getResult3.getChildPartitionIds().size());
    assertEquals(2, getResult.getChildPartitionIds().size());
    assertFalse(getResult3.isLeafPartition());
    assertFalse(getResult.isLeafPartition());
    assertTrue(getResult2.getChildPartitionIds().isEmpty());
    assertTrue(getResult2.isLeafPartition());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddFieldWithNameAndTypeIsByteArrayType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add Field(String, Type) with 'Name' and type is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddFieldWithNameAndTypeIsIntType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add Field(String, Type) with 'Name' and type is LongType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddFieldWithNameAndTypeIsLongType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add Field(String, Type) with 'Name' and type is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddFieldWithNameAndTypeIsPrimitiveType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, new ArrayList<>())).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add Field(String, Type) with 'Name' and type is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddFieldWithNameAndTypeIsPrimitiveType2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add 'foo'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddFoo_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("foo");
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddRegionWithRangesIsArrayList() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(new Region(new ArrayList<>()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddRegionWithRangesIsArrayList2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(new Region(new ArrayList<>()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenArrayListAddRegionWithRangesIsArrayList3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(new Region(new ArrayList<>()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with name is {@code Split points are valid} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given Field(String, Type) with name is 'Split points are valid' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenFieldWithNameIsSplitPointsAreValidAndTypeIsByteArrayType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(new Range(new Field("Split points are valid", new ByteArrayType()), "Min", "Max"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with name is {@code Split points are valid} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given Field(String, Type) with name is 'Split points are valid' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenFieldWithNameIsSplitPointsAreValidAndTypeIsByteArrayType2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(new Range(new Field("Split points are valid", new ByteArrayType()), "Min", "Max"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with name is {@code Split points are valid} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); given Field(String, Type) with name is 'Split points are valid' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_givenFieldWithNameIsSplitPointsAreValidAndTypeIsByteArrayType3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(new Range(new Field("Split points are valid", new ByteArrayType()), "Min", "Max"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new PartitionsFromSplitPoints(schema, splitPoints)).construct());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Then first Region Ranges first FieldType return {@link IntType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); then first Region Ranges first FieldType return IntType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_thenFirstRegionRangesFirstFieldTypeReturnIntType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    IntType type = new IntType();
    Field field = new Field("Name", type);

    rowKeyFields.add(field);
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<Partition> actualConstructResult = (new PartitionsFromSplitPoints(schema, new ArrayList<>())).construct();

    // Assert
    assertEquals(1, actualConstructResult.size());
    List<Range> ranges = actualConstructResult.get(0).getRegion().getRanges();
    assertEquals(1, ranges.size());
    Range getResult = ranges.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof IntType);
    assertEquals(Integer.MIN_VALUE, ((Integer) getResult.getMin()).intValue());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Then first Region Ranges first FieldType return {@link LongType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); then first Region Ranges first FieldType return LongType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_thenFirstRegionRangesFirstFieldTypeReturnLongType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    LongType type = new LongType();
    Field field = new Field("Name", type);

    rowKeyFields.add(field);
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<Partition> actualConstructResult = (new PartitionsFromSplitPoints(schema, new ArrayList<>())).construct();

    // Assert
    assertEquals(1, actualConstructResult.size());
    List<Range> ranges = actualConstructResult.get(0).getRegion().getRanges();
    assertEquals(1, ranges.size());
    Range getResult = ranges.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof LongType);
    assertEquals(Long.MIN_VALUE, ((Long) getResult.getMin()).longValue());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Then first Region Ranges first FieldType return {@link StringType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); then first Region Ranges first FieldType return StringType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_thenFirstRegionRangesFirstFieldTypeReturnStringType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    StringType type = new StringType();
    Field field = new Field("Name", type);

    rowKeyFields.add(field);
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<Partition> actualConstructResult = (new PartitionsFromSplitPoints(schema, new ArrayList<>())).construct();

    // Assert
    assertEquals(1, actualConstructResult.size());
    List<Range> ranges = actualConstructResult.get(0).getRegion().getRanges();
    assertEquals(1, ranges.size());
    Range getResult = ranges.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof StringType);
    assertEquals("", getResult.getMin());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Then return first Region Ranges first Max intValue is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); then return first Region Ranges first Max intValue is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_thenReturnFirstRegionRangesFirstMaxIntValueIsTwo() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    IntType type = new IntType();
    Field field = new Field("Name", type);

    rowKeyFields.add(field);
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(2);

    // Act
    List<Partition> actualConstructResult = (new PartitionsFromSplitPoints(schema, splitPoints)).construct();

    // Assert
    assertEquals(3, actualConstructResult.size());
    List<Range> ranges = actualConstructResult.get(0).getRegion().getRanges();
    assertEquals(1, ranges.size());
    List<Range> ranges2 = actualConstructResult.get(1).getRegion().getRanges();
    assertEquals(1, ranges2.size());
    List<Range> ranges3 = actualConstructResult.get(2).getRegion().getRanges();
    assertEquals(1, ranges3.size());
    assertEquals(2, ((Integer) ranges.get(0).getMax()).intValue());
    Range getResult = ranges2.get(0);
    assertEquals(2, ((Integer) getResult.getMin()).intValue());
    Range getResult2 = ranges3.get(0);
    assertEquals(Integer.MIN_VALUE, ((Integer) getResult2.getMin()).intValue());
    assertSame(field, getResult.getField());
    assertSame(field, getResult2.getField());
    assertSame(type, getResult.getFieldType());
    assertSame(type, getResult2.getFieldType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Then return first Region Ranges first Max longValue is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); then return first Region Ranges first Max longValue is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_thenReturnFirstRegionRangesFirstMaxLongValueIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    LongType type = new LongType();
    Field field = new Field("Name", type);

    rowKeyFields.add(field);
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(1L);

    // Act
    List<Partition> actualConstructResult = (new PartitionsFromSplitPoints(schema, splitPoints)).construct();

    // Assert
    assertEquals(3, actualConstructResult.size());
    List<Range> ranges = actualConstructResult.get(0).getRegion().getRanges();
    assertEquals(1, ranges.size());
    List<Range> ranges2 = actualConstructResult.get(1).getRegion().getRanges();
    assertEquals(1, ranges2.size());
    List<Range> ranges3 = actualConstructResult.get(2).getRegion().getRanges();
    assertEquals(1, ranges3.size());
    assertEquals(1L, ((Long) ranges.get(0).getMax()).longValue());
    Range getResult = ranges2.get(0);
    assertEquals(1L, ((Long) getResult.getMin()).longValue());
    Range getResult2 = ranges3.get(0);
    assertEquals(Long.MIN_VALUE, ((Long) getResult2.getMin()).longValue());
    assertSame(field, getResult.getField());
    assertSame(field, getResult2.getField());
    assertSame(type, getResult.getFieldType());
    assertSame(type, getResult2.getFieldType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#construct()}.
   * <ul>
   *   <li>Then return third Region Ranges first Min is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#construct()}
   */
  @Test
  @DisplayName("Test construct(); then return third Region Ranges first Min is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.construct()"})
  void testConstruct_thenReturnThirdRegionRangesFirstMinIsEmptyString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    StringType type = new StringType();
    Field field = new Field("Name", type);

    rowKeyFields.add(field);
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act
    List<Partition> actualConstructResult = (new PartitionsFromSplitPoints(schema, splitPoints)).construct();

    // Assert
    assertEquals(3, actualConstructResult.size());
    List<Range> ranges = actualConstructResult.get(2).getRegion().getRanges();
    assertEquals(1, ranges.size());
    Range getResult = ranges.get(0);
    assertEquals("", getResult.getMin());
    List<Range> ranges2 = actualConstructResult.get(0).getRegion().getRanges();
    assertEquals(1, ranges2.size());
    assertEquals("42", ranges2.get(0).getMax());
    List<Range> ranges3 = actualConstructResult.get(1).getRegion().getRanges();
    assertEquals(1, ranges3.size());
    Range getResult2 = ranges3.get(0);
    assertEquals("42", getResult2.getMin());
    assertSame(field, getResult2.getField());
    assertSame(field, getResult.getField());
    assertSame(type, getResult2.getFieldType());
    assertSame(type, getResult.getFieldType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)} with {@code Schema}, {@code RangeFactory}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)}
   */
  @Test
  @DisplayName("Test createRootPartitionThatIsLeaf(Schema, RangeFactory) with 'Schema', 'RangeFactory'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition.Builder PartitionsFromSplitPoints.createRootPartitionThatIsLeaf(Schema, RangeFactory)"})
  void testCreateRootPartitionThatIsLeafWithSchemaRangeFactory() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new IllegalArgumentException("root"));

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> PartitionsFromSplitPoints.createRootPartitionThatIsLeaf(schema, new RangeFactory(schema2)));
    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)} with {@code Schema}, {@code RangeFactory}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)}
   */
  @Test
  @DisplayName("Test createRootPartitionThatIsLeaf(Schema, RangeFactory) with 'Schema', 'RangeFactory'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition.Builder PartitionsFromSplitPoints.createRootPartitionThatIsLeaf(Schema, RangeFactory)"})
  void testCreateRootPartitionThatIsLeafWithSchemaRangeFactory2() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new PrimitiveType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> PartitionsFromSplitPoints.createRootPartitionThatIsLeaf(schema, new RangeFactory(schema2)));
    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)} with {@code Schema}, {@code RangeFactory}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)}
   */
  @Test
  @DisplayName("Test createRootPartitionThatIsLeaf(Schema, RangeFactory) with 'Schema', 'RangeFactory'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition.Builder PartitionsFromSplitPoints.createRootPartitionThatIsLeaf(Schema, RangeFactory)"})
  void testCreateRootPartitionThatIsLeafWithSchemaRangeFactory3() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    IntType type = new IntType();
    Field field = new Field("Name", type);

    fieldList.add(field);
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    Partition.Builder actualCreateRootPartitionThatIsLeafResult = PartitionsFromSplitPoints
        .createRootPartitionThatIsLeaf(schema, new RangeFactory(schema2));

    // Assert
    verify(schema).getRowKeyFields();
    List<Range> ranges = actualCreateRootPartitionThatIsLeafResult.getRegion().getRanges();
    assertEquals(1, ranges.size());
    Range getResult = ranges.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof IntType);
    assertEquals(Integer.MIN_VALUE, ((Integer) getResult.getMin()).intValue());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)} with {@code Schema}, {@code RangeFactory}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)}
   */
  @Test
  @DisplayName("Test createRootPartitionThatIsLeaf(Schema, RangeFactory) with 'Schema', 'RangeFactory'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition.Builder PartitionsFromSplitPoints.createRootPartitionThatIsLeaf(Schema, RangeFactory)"})
  void testCreateRootPartitionThatIsLeafWithSchemaRangeFactory4() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    LongType type = new LongType();
    Field field = new Field("Name", type);

    fieldList.add(field);
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    Partition.Builder actualCreateRootPartitionThatIsLeafResult = PartitionsFromSplitPoints
        .createRootPartitionThatIsLeaf(schema, new RangeFactory(schema2));

    // Assert
    verify(schema).getRowKeyFields();
    List<Range> ranges = actualCreateRootPartitionThatIsLeafResult.getRegion().getRanges();
    assertEquals(1, ranges.size());
    Range getResult = ranges.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof LongType);
    assertEquals(Long.MIN_VALUE, ((Long) getResult.getMin()).longValue());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)} with {@code Schema}, {@code RangeFactory}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)}
   */
  @Test
  @DisplayName("Test createRootPartitionThatIsLeaf(Schema, RangeFactory) with 'Schema', 'RangeFactory'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition.Builder PartitionsFromSplitPoints.createRootPartitionThatIsLeaf(Schema, RangeFactory)"})
  void testCreateRootPartitionThatIsLeafWithSchemaRangeFactory5() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    StringType type = new StringType();
    Field field = new Field("Name", type);

    fieldList.add(field);
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    Partition.Builder actualCreateRootPartitionThatIsLeafResult = PartitionsFromSplitPoints
        .createRootPartitionThatIsLeaf(schema, new RangeFactory(schema2));

    // Assert
    verify(schema).getRowKeyFields();
    List<Range> ranges = actualCreateRootPartitionThatIsLeafResult.getRegion().getRanges();
    assertEquals(1, ranges.size());
    Range getResult = ranges.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof StringType);
    assertEquals("", getResult.getMin());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)} with {@code Schema}, {@code RangeFactory}.
   * <ul>
   *   <li>Then return Id is {@code root}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#createRootPartitionThatIsLeaf(Schema, RangeFactory)}
   */
  @Test
  @DisplayName("Test createRootPartitionThatIsLeaf(Schema, RangeFactory) with 'Schema', 'RangeFactory'; then return Id is 'root'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition.Builder PartitionsFromSplitPoints.createRootPartitionThatIsLeaf(Schema, RangeFactory)"})
  void testCreateRootPartitionThatIsLeafWithSchemaRangeFactory_thenReturnIdIsRoot() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    Partition.Builder actualCreateRootPartitionThatIsLeafResult = PartitionsFromSplitPoints
        .createRootPartitionThatIsLeaf(schema, new RangeFactory(schema2));

    // Assert
    verify(schema).getRowKeyFields();
    assertEquals("root", actualCreateRootPartitionThatIsLeafResult.getId());
    assertTrue(actualCreateRootPartitionThatIsLeafResult.getRegion().getRanges().isEmpty());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#leafRegionsFromDimensionSplitPoints(Schema, int, List)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#leafRegionsFromDimensionSplitPoints(Schema, int, List)}
   */
  @Test
  @DisplayName("Test leafRegionsFromDimensionSplitPoints(Schema, int, List); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.leafRegionsFromDimensionSplitPoints(Schema, int, List)"})
  void testLeafRegionsFromDimensionSplitPoints_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> PartitionsFromSplitPoints.leafRegionsFromDimensionSplitPoints(schema, 1, new ArrayList<>()));
    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#leafRegionsFromDimensionSplitPoints(Schema, int, List)}.
   * <ul>
   *   <li>Then calls {@link Schema#getRowKeyFieldNames()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#leafRegionsFromDimensionSplitPoints(Schema, int, List)}
   */
  @Test
  @DisplayName("Test leafRegionsFromDimensionSplitPoints(Schema, int, List); then calls getRowKeyFieldNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.leafRegionsFromDimensionSplitPoints(Schema, int, List)"})
  void testLeafRegionsFromDimensionSplitPoints_thenCallsGetRowKeyFieldNames() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new PrimitiveType()));
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> PartitionsFromSplitPoints.leafRegionsFromDimensionSplitPoints(schema, 1, new ArrayList<>()));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#leafRegionsFromDimensionSplitPoints(Schema, int, List)}.
   * <ul>
   *   <li>Then calls {@link Schema#getRowKeyFieldNames()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#leafRegionsFromDimensionSplitPoints(Schema, int, List)}
   */
  @Test
  @DisplayName("Test leafRegionsFromDimensionSplitPoints(Schema, int, List); then calls getRowKeyFieldNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PartitionsFromSplitPoints.leafRegionsFromDimensionSplitPoints(Schema, int, List)"})
  void testLeafRegionsFromDimensionSplitPoints_thenCallsGetRowKeyFieldNames2() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    fieldList.add(new Field("Name", new PrimitiveType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> PartitionsFromSplitPoints.leafRegionsFromDimensionSplitPoints(schema, 1, new ArrayList<>()));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new IllegalArgumentException(
        "Constructing partition tree from no split points - tree will consist of one partition"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>()));
    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom2() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new PrimitiveType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>()));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom3() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsFromSplitPoints.treeFrom(schema, splitPoints));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom4() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new IntType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsFromSplitPoints.treeFrom(schema, splitPoints));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom5() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new LongType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsFromSplitPoints.treeFrom(schema, splitPoints));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom6() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new PrimitiveType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsFromSplitPoints.treeFrom(schema, splitPoints));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom7() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");
    splitPoints.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new ByteArrayType()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsFromSplitPoints.treeFrom(schema, splitPoints));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return RootPartition Id is {@code root}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List); given ArrayList(); when ArrayList(); then return RootPartition Id is 'root'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom_givenArrayList_whenArrayList_thenReturnRootPartitionIdIsRoot() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    PartitionTree actualTreeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
    Partition rootPartition = actualTreeFromResult.getRootPartition();
    assertEquals("root", rootPartition.getId());
    assertEquals("root", rootPartition.toBuilder().getId());
    assertEquals(-1, rootPartition.getDimension());
    List<Partition> allPartitions = actualTreeFromResult.getAllPartitions();
    assertEquals(1, allPartitions.size());
    assertTrue(rootPartition.getChildPartitionIds().isEmpty());
    assertTrue(rootPartition.getRegion().getRanges().isEmpty());
    assertTrue(rootPartition.isLeafPartition());
    assertEquals(allPartitions, actualTreeFromResult.getLeafPartitions());
    assertSame(rootPartition, allPartitions.get(0));
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <ul>
   *   <li>Given {@link ByteArrayType} (default constructor).</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List); given ByteArrayType (default constructor); when ArrayList() add ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom_givenByteArrayType_whenArrayListAddByteArrayType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");
    splitPoints.add(new ByteArrayType());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsFromSplitPoints.treeFrom(schema, splitPoints));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link ArrayList#ArrayList()} add empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List); given empty string; when ArrayList() add empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom_givenEmptyString_whenArrayListAddEmptyString() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");
    splitPoints.add("");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsFromSplitPoints.treeFrom(schema, splitPoints));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <ul>
   *   <li>Then RootPartition Region Ranges first FieldType return {@link IntType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List); then RootPartition Region Ranges first FieldType return IntType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom_thenRootPartitionRegionRangesFirstFieldTypeReturnIntType() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Constructing partition tree from no split points - tree will consist of one partition");

    ArrayList<Field> fieldList = new ArrayList<>();
    IntType type = new IntType();
    Field field = new Field("Constructing partition tree from no split points - tree will consist of one partition",
        type);

    fieldList.add(field);
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    PartitionTree actualTreeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
    List<Range> ranges = actualTreeFromResult.getRootPartition().getRegion().getRanges();
    assertEquals(1, ranges.size());
    Range getResult = ranges.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof IntType);
    assertEquals(Integer.MIN_VALUE, ((Integer) getResult.getMin()).intValue());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <ul>
   *   <li>Then RootPartition Region Ranges first FieldType return {@link LongType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List); then RootPartition Region Ranges first FieldType return LongType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom_thenRootPartitionRegionRangesFirstFieldTypeReturnLongType() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Constructing partition tree from no split points - tree will consist of one partition");

    ArrayList<Field> fieldList = new ArrayList<>();
    LongType type = new LongType();
    Field field = new Field("Constructing partition tree from no split points - tree will consist of one partition",
        type);

    fieldList.add(field);
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    PartitionTree actualTreeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
    List<Range> ranges = actualTreeFromResult.getRootPartition().getRegion().getRanges();
    assertEquals(1, ranges.size());
    Range getResult = ranges.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof LongType);
    assertEquals(Long.MIN_VALUE, ((Long) getResult.getMin()).longValue());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <ul>
   *   <li>Then RootPartition Region Ranges first FieldType return {@link StringType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List); then RootPartition Region Ranges first FieldType return StringType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom_thenRootPartitionRegionRangesFirstFieldTypeReturnStringType() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Constructing partition tree from no split points - tree will consist of one partition");

    ArrayList<Field> fieldList = new ArrayList<>();
    StringType type = new StringType();
    Field field = new Field("Constructing partition tree from no split points - tree will consist of one partition",
        type);

    fieldList.add(field);
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    PartitionTree actualTreeFromResult = PartitionsFromSplitPoints.treeFrom(schema, new ArrayList<>());

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
    List<Range> ranges = actualTreeFromResult.getRootPartition().getRegion().getRanges();
    assertEquals(1, ranges.size());
    Range getResult = ranges.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof StringType);
    assertEquals("", getResult.getMin());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsFromSplitPoints#treeFrom(Schema, List)}
   */
  @Test
  @DisplayName("Test treeFrom(Schema, List); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree PartitionsFromSplitPoints.treeFrom(Schema, List)"})
  void testTreeFrom_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");
    splitPoints.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsFromSplitPoints.treeFrom(schema, splitPoints));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }
}
