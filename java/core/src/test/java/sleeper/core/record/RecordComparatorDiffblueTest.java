package sleeper.core.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.PrimitiveType;

class RecordComparatorDiffblueTest {
  /**
   * Test {@link RecordComparator#RecordComparator(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordComparator#RecordComparator(Schema)}
   */
  @Test
  @DisplayName("Test new RecordComparator(Schema); given ArrayList() add ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordComparator.<init>(Schema)"})
  void testNewRecordComparator_givenArrayListAddByteArrayType() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");

    ArrayList<PrimitiveType> primitiveTypeList = new ArrayList<>();
    primitiveTypeList.add(new ByteArrayType());
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);
    when(schema.getRowKeyTypes()).thenReturn(primitiveTypeList);
    when(schema.getSortKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyTypes()).thenReturn(new ArrayList<>());

    // Act
    RecordComparator actualRecordComparator = new RecordComparator(schema);
    Record record1 = new Record();
    int actualCompareResult = actualRecordComparator.compare(record1, new Record());

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyTypes();
    verify(schema).getSortKeyFieldNames();
    verify(schema).getSortKeyTypes();
    assertEquals(0, actualCompareResult);
  }

  /**
   * Test {@link RecordComparator#RecordComparator(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordComparator#RecordComparator(Schema)}
   */
  @Test
  @DisplayName("Test new RecordComparator(Schema); given ArrayList() add 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordComparator.<init>(Schema)"})
  void testNewRecordComparator_givenArrayListAddFoo() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);
    when(schema.getRowKeyTypes()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyTypes()).thenReturn(new ArrayList<>());

    // Act
    RecordComparator actualRecordComparator = new RecordComparator(schema);
    Record record1 = new Record();
    int actualCompareResult = actualRecordComparator.compare(record1, new Record());

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyTypes();
    verify(schema).getSortKeyFieldNames();
    verify(schema).getSortKeyTypes();
    assertEquals(0, actualCompareResult);
  }

  /**
   * Test {@link RecordComparator#RecordComparator(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordComparator#RecordComparator(Schema)}
   */
  @Test
  @DisplayName("Test new RecordComparator(Schema); given ArrayList() add 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordComparator.<init>(Schema)"})
  void testNewRecordComparator_givenArrayListAddFoo2() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");

    ArrayList<PrimitiveType> primitiveTypeList = new ArrayList<>();
    primitiveTypeList.add(new PrimitiveType());
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);
    when(schema.getRowKeyTypes()).thenReturn(primitiveTypeList);
    when(schema.getSortKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyTypes()).thenReturn(new ArrayList<>());

    // Act
    RecordComparator actualRecordComparator = new RecordComparator(schema);
    Record record1 = new Record();
    int actualCompareResult = actualRecordComparator.compare(record1, new Record());

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyTypes();
    verify(schema).getSortKeyFieldNames();
    verify(schema).getSortKeyTypes();
    assertEquals(0, actualCompareResult);
  }

  /**
   * Test {@link RecordComparator#RecordComparator(Schema)}.
   * <ul>
   *   <li>Then return compare {@link Record#Record()} and {@link Record#Record()} is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordComparator#RecordComparator(Schema)}
   */
  @Test
  @DisplayName("Test new RecordComparator(Schema); then return compare Record() and Record() is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordComparator.<init>(Schema)"})
  void testNewRecordComparator_thenReturnCompareRecordAndRecordIsZero() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyTypes()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyTypes()).thenReturn(new ArrayList<>());

    // Act
    RecordComparator actualRecordComparator = new RecordComparator(schema);
    Record record1 = new Record();
    int actualCompareResult = actualRecordComparator.compare(record1, new Record());

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyTypes();
    verify(schema).getSortKeyFieldNames();
    verify(schema).getSortKeyTypes();
    assertEquals(0, actualCompareResult);
  }

  /**
   * Test {@link RecordComparator#compare(Record, Record)} with {@code Record}, {@code Record}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordComparator#compare(Record, Record)}
   */
  @Test
  @DisplayName("Test compare(Record, Record) with 'Record', 'Record'; given ArrayList() add Field(String, Type) with 'Name' and type is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int RecordComparator.compare(Record, Record)"})
  void testCompareWithRecordRecord_givenArrayListAddFieldWithNameAndTypeIsIntType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordComparator recordComparator = new RecordComparator(schema);
    Record record1 = new Record();

    // Act and Assert
    assertEquals(0, recordComparator.compare(record1, new Record()));
  }

  /**
   * Test {@link RecordComparator#compare(Record, Record)} with {@code Record}, {@code Record}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordComparator#compare(Record, Record)}
   */
  @Test
  @DisplayName("Test compare(Record, Record) with 'Record', 'Record'; then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int RecordComparator.compare(Record, Record)"})
  void testCompareWithRecordRecord_thenReturnZero() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordComparator recordComparator = new RecordComparator(schema);
    Record record1 = new Record();

    // Act and Assert
    assertEquals(0, recordComparator.compare(record1, new Record()));
  }
}
