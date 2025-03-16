package sleeper.bulkimport.runner.dataframelocalsort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRow;
import org.apache.spark.sql.execution.aggregate.MutableAggregationBufferImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.key.Key;
import sleeper.core.partition.Partition;
import sleeper.core.partition.PartitionTree;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;

class AddPartitionAsIntIteratorDiffblueTest {
  /**
   * Test {@link AddPartitionAsIntIterator#AddPartitionAsIntIterator(Iterator, Schema, PartitionTree)}.
   * <p>
   * Method under test: {@link AddPartitionAsIntIterator#AddPartitionAsIntIterator(Iterator, Schema, PartitionTree)}
   */
  @Test
  @DisplayName("Test new AddPartitionAsIntIterator(Iterator, Schema, PartitionTree)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddPartitionAsIntIterator.<init>(Iterator, Schema, PartitionTree)"})
  void testNewAddPartitionAsIntIterator() {
    // Arrange
    ArrayList<Row> rowList = new ArrayList<>();
    Iterator<Row> input = rowList.iterator();

    ArrayList<Partition> partitionList = new ArrayList<>();
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    partitionList.add(buildResult);
    PartitionTree partitionTree = mock(PartitionTree.class);
    when(partitionTree.getAllPartitions()).thenReturn(partitionList);

    // Act
    AddPartitionAsIntIterator actualAddPartitionAsIntIterator = new AddPartitionAsIntIterator(input, null,
        partitionTree);

    // Assert
    verify(partitionTree).getAllPartitions();
    assertFalse(actualAddPartitionAsIntIterator.hasNext());
  }

  /**
   * Test {@link AddPartitionAsIntIterator#AddPartitionAsIntIterator(Iterator, Schema, PartitionTree)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return not hasNext.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddPartitionAsIntIterator#AddPartitionAsIntIterator(Iterator, Schema, PartitionTree)}
   */
  @Test
  @DisplayName("Test new AddPartitionAsIntIterator(Iterator, Schema, PartitionTree); given ArrayList(); then return not hasNext")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddPartitionAsIntIterator.<init>(Iterator, Schema, PartitionTree)"})
  void testNewAddPartitionAsIntIterator_givenArrayList_thenReturnNotHasNext() {
    // Arrange
    ArrayList<Row> rowList = new ArrayList<>();
    Iterator<Row> input = rowList.iterator();
    PartitionTree partitionTree = mock(PartitionTree.class);
    when(partitionTree.getAllPartitions()).thenReturn(new ArrayList<>());

    // Act
    AddPartitionAsIntIterator actualAddPartitionAsIntIterator = new AddPartitionAsIntIterator(input, null,
        partitionTree);

    // Assert
    verify(partitionTree).getAllPartitions();
    assertFalse(actualAddPartitionAsIntIterator.hasNext());
  }

  /**
   * Test {@link AddPartitionAsIntIterator#hasNext()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddPartitionAsIntIterator#hasNext()}
   */
  @Test
  @DisplayName("Test hasNext(); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddPartitionAsIntIterator.hasNext()"})
  void testHasNext_givenArrayListAddFieldWithNameAndTypeIsByteArrayType_thenReturnFalse() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree partitionTree = mock(PartitionTree.class);
    when(partitionTree.getAllPartitions()).thenReturn(new ArrayList<>());

    ArrayList<Row> rowList = new ArrayList<>();

    // Act
    boolean actualHasNextResult = (new AddPartitionAsIntIterator(rowList.iterator(), schema, partitionTree)).hasNext();

    // Assert
    verify(partitionTree).getAllPartitions();
    assertFalse(actualHasNextResult);
  }

  /**
   * Test {@link AddPartitionAsIntIterator#hasNext()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link GenericRow#GenericRow()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddPartitionAsIntIterator#hasNext()}
   */
  @Test
  @DisplayName("Test hasNext(); given ArrayList() add GenericRow(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddPartitionAsIntIterator.hasNext()"})
  void testHasNext_givenArrayListAddGenericRow_thenReturnTrue() {
    // Arrange
    ArrayList<Row> rowList = new ArrayList<>();
    rowList.add(new GenericRow());
    Iterator<Row> input = rowList.iterator();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree partitionTree = mock(PartitionTree.class);
    when(partitionTree.getAllPartitions()).thenReturn(new ArrayList<>());

    // Act
    boolean actualHasNextResult = (new AddPartitionAsIntIterator(input, schema, partitionTree)).hasNext();

    // Assert
    verify(partitionTree).getAllPartitions();
    assertTrue(actualHasNextResult);
  }

  /**
   * Test {@link AddPartitionAsIntIterator#next()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link GenericRow#GenericRow(int)} with size is three.</li>
   *   <li>Then return first element is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddPartitionAsIntIterator#next()}
   */
  @Test
  @DisplayName("Test next(); given ArrayList() add GenericRow(int) with size is three; then return first element is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Row AddPartitionAsIntIterator.next()"})
  void testNext_givenArrayListAddGenericRowWithSizeIsThree_thenReturnFirstElementIsNull() {
    // Arrange
    ArrayList<Row> rowList = new ArrayList<>();
    rowList.add(new GenericRow(3));
    Iterator<Row> input = rowList.iterator();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree partitionTree = mock(PartitionTree.class);
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    when(partitionTree.getLeafPartition(Mockito.<Schema>any(), Mockito.<Key>any())).thenReturn(buildResult);
    when(partitionTree.getAllPartitions()).thenReturn(new ArrayList<>());
    AddPartitionAsIntIterator addPartitionAsIntIterator = new AddPartitionAsIntIterator(input, schema, partitionTree);

    // Act
    Row actualNextResult = addPartitionAsIntIterator.next();

    // Assert
    verify(partitionTree).getAllPartitions();
    verify(partitionTree).getLeafPartition(isA(Schema.class), isA(Key.class));
    assertTrue(actualNextResult instanceof GenericRow);
    Object[] valuesResult = ((GenericRow) actualNextResult).values();
    assertNull(valuesResult[0]);
    assertNull(valuesResult[1]);
    assertNull(actualNextResult.schema());
    assertEquals(2, actualNextResult.length());
    assertEquals(2, actualNextResult.size());
    assertEquals(2, valuesResult.length);
    assertFalse(addPartitionAsIntIterator.hasNext());
  }

  /**
   * Test {@link AddPartitionAsIntIterator#next()}.
   * <ul>
   *   <li>Then return first element is {@code Get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddPartitionAsIntIterator#next()}
   */
  @Test
  @DisplayName("Test next(); then return first element is 'Get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Row AddPartitionAsIntIterator.next()"})
  void testNext_thenReturnFirstElementIsGet() {
    // Arrange
    MutableAggregationBufferImpl mutableAggregationBufferImpl = mock(MutableAggregationBufferImpl.class);
    when(mutableAggregationBufferImpl.get(anyInt())).thenReturn("Get");

    ArrayList<Row> rowList = new ArrayList<>();
    rowList.add(mutableAggregationBufferImpl);
    Iterator<Row> input = rowList.iterator();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionTree partitionTree = mock(PartitionTree.class);
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    when(partitionTree.getLeafPartition(Mockito.<Schema>any(), Mockito.<Key>any())).thenReturn(buildResult);
    when(partitionTree.getAllPartitions()).thenReturn(new ArrayList<>());
    AddPartitionAsIntIterator addPartitionAsIntIterator = new AddPartitionAsIntIterator(input, schema, partitionTree);

    // Act
    Row actualNextResult = addPartitionAsIntIterator.next();

    // Assert
    verify(mutableAggregationBufferImpl).get(eq(0));
    verify(partitionTree).getAllPartitions();
    verify(partitionTree).getLeafPartition(isA(Schema.class), isA(Key.class));
    assertTrue(actualNextResult instanceof GenericRow);
    Object[] valuesResult = ((GenericRow) actualNextResult).values();
    assertEquals("Get", valuesResult[0]);
    assertNull(valuesResult[1]);
    assertNull(actualNextResult.schema());
    assertEquals(2, actualNextResult.length());
    assertEquals(2, actualNextResult.size());
    assertEquals(2, valuesResult.length);
    assertFalse(addPartitionAsIntIterator.hasNext());
  }
}
