package sleeper.bulkimport.runner.dataframe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
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

class AddPartitionIteratorDiffblueTest {
  /**
   * Test {@link AddPartitionIterator#hasNext()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddPartitionIterator#hasNext()}
   */
  @Test
  @DisplayName("Test hasNext(); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddPartitionIterator.hasNext()"})
  void testHasNext_givenArrayListAddFieldWithNameAndTypeIsByteArrayType_thenReturnFalse() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Row> rowList = new ArrayList<>();

    // Act and Assert
    assertFalse((new AddPartitionIterator(rowList.iterator(), schema, null)).hasNext());
  }

  /**
   * Test {@link AddPartitionIterator#hasNext()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link GenericRow#GenericRow()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddPartitionIterator#hasNext()}
   */
  @Test
  @DisplayName("Test hasNext(); given ArrayList() add GenericRow(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddPartitionIterator.hasNext()"})
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

    // Act and Assert
    assertTrue((new AddPartitionIterator(input, schema, null)).hasNext());
  }

  /**
   * Test {@link AddPartitionIterator#next()}.
   * <ul>
   *   <li>Given {@link Field} {@link Field#getName()} return {@code Name}.</li>
   *   <li>Then return {@link GenericRow}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddPartitionIterator#next()}
   */
  @Test
  @DisplayName("Test next(); given Field getName() return 'Name'; then return GenericRow")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Row AddPartitionIterator.next()"})
  void testNext_givenFieldGetNameReturnName_thenReturnGenericRow() {
    // Arrange
    MutableAggregationBufferImpl mutableAggregationBufferImpl = mock(MutableAggregationBufferImpl.class);
    when(mutableAggregationBufferImpl.get(anyInt())).thenReturn("Get");

    ArrayList<Row> rowList = new ArrayList<>();
    rowList.add(mutableAggregationBufferImpl);
    Iterator<Row> input = rowList.iterator();
    Field field = mock(Field.class);
    when(field.getName()).thenReturn("Name");
    when(field.getType()).thenReturn(new ByteArrayType());

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(field);
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
    AddPartitionIterator addPartitionIterator = new AddPartitionIterator(input, schema, partitionTree);

    // Act
    Row actualNextResult = addPartitionIterator.next();

    // Assert
    verify(mutableAggregationBufferImpl).get(eq(0));
    verify(partitionTree).getLeafPartition(isA(Schema.class), isA(Key.class));
    verify(field, atLeast(1)).getName();
    verify(field).getType();
    assertTrue(actualNextResult instanceof GenericRow);
    Object[] valuesResult = ((GenericRow) actualNextResult).values();
    assertEquals("42", valuesResult[1]);
    assertEquals("Get", valuesResult[0]);
    assertNull(actualNextResult.schema());
    assertEquals(2, actualNextResult.length());
    assertEquals(2, actualNextResult.size());
    assertEquals(2, valuesResult.length);
    assertFalse(addPartitionIterator.hasNext());
  }
}
