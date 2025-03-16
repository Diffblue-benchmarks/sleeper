package sleeper.parquet.record;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.LogicalTypeAnnotation;
import org.apache.parquet.schema.LogicalTypeAnnotation.ListLogicalTypeAnnotation;
import org.apache.parquet.schema.LogicalTypeAnnotation.MapLogicalTypeAnnotation;
import org.apache.parquet.schema.LogicalTypeAnnotation.StringLogicalTypeAnnotation;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.OriginalType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName;
import org.apache.parquet.schema.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.ListType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.MapType;
import sleeper.core.schema.type.StringType;

class SchemaConverterDiffblueTest {
  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("record", new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    MessageType actualSchema = SchemaConverter.getSchema(schema);

    // Assert
    verify(schema).getAllFields();
    List<Type> fields = actualSchema.getFields();
    assertEquals(1, fields.size());
    Type getResult = fields.get(0);
    LogicalTypeAnnotation logicalTypeAnnotation = getResult.getLogicalTypeAnnotation();
    assertTrue(logicalTypeAnnotation instanceof StringLogicalTypeAnnotation);
    assertTrue(getResult instanceof PrimitiveType);
    List<ColumnDescriptor> columns = actualSchema.getColumns();
    assertEquals(1, columns.size());
    assertEquals(OriginalType.UTF8, logicalTypeAnnotation.toOriginalType());
    assertEquals(OriginalType.UTF8, getResult.getOriginalType());
    assertEquals(PrimitiveTypeName.BINARY, columns.get(0).getType());
    assertEquals(PrimitiveTypeName.BINARY, ((PrimitiveType) getResult).getPrimitiveTypeName());
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with name is {@code record} and type is {@link sleeper.core.schema.type.PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); given ArrayList() add Field(String, Type) with name is 'record' and type is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_givenArrayListAddFieldWithNameIsRecordAndTypeIsPrimitiveType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("record", new sleeper.core.schema.type.PrimitiveType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> SchemaConverter.getSchema(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return FieldCount is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); given ArrayList(); then return FieldCount is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_givenArrayList_thenReturnFieldCountIsZero() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(new ArrayList<>());

    // Act
    MessageType actualSchema = SchemaConverter.getSchema(schema);

    // Assert
    verify(schema).getAllFields();
    assertEquals(0, actualSchema.getFieldCount());
    assertTrue(actualSchema.getFields().isEmpty());
    assertTrue(actualSchema.getColumns().isEmpty());
    assertTrue(actualSchema.getPaths().isEmpty());
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code record}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); given IllegalArgumentException(String) with 'record'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_givenIllegalArgumentExceptionWithRecord() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenThrow(new IllegalArgumentException("record"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> SchemaConverter.getSchema(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Given {@link ListType#ListType(PrimitiveType)} with elementType is {@link sleeper.core.schema.type.PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); given ListType(PrimitiveType) with elementType is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_givenListTypeWithElementTypeIsPrimitiveType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("record", new ListType(new sleeper.core.schema.type.PrimitiveType())));
    fieldList.add(new Field("record", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> SchemaConverter.getSchema(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Given {@link MapType#MapType(PrimitiveType, PrimitiveType)} with keyType is {@link ByteArrayType} (default constructor) and valueType is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); given MapType(PrimitiveType, PrimitiveType) with keyType is ByteArrayType (default constructor) and valueType is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_givenMapTypeWithKeyTypeIsByteArrayTypeAndValueTypeIsByteArrayType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    ByteArrayType keyType = new ByteArrayType();
    fieldList.add(new Field("record", new MapType(keyType, new ByteArrayType())));
    fieldList.add(new Field("record", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    MessageType actualSchema = SchemaConverter.getSchema(schema);

    // Assert
    verify(schema).getAllFields();
    List<Type> fields = actualSchema.getFields();
    assertEquals(2, fields.size());
    Type getResult = fields.get(0);
    assertTrue(getResult instanceof GroupType);
    assertTrue(getResult.getLogicalTypeAnnotation() instanceof MapLogicalTypeAnnotation);
    List<String[]> paths = actualSchema.getPaths();
    assertEquals(3, paths.size());
    String[] getResult2 = paths.get(0);
    assertEquals("key", getResult2[2]);
    assertEquals("key_value", getResult2[1]);
    String[] getResult3 = paths.get(1);
    assertEquals("key_value", getResult3[1]);
    assertEquals("value", getResult3[2]);
    assertEquals(3, getResult2.length);
    assertEquals(3, getResult3.length);
    assertEquals(OriginalType.MAP, getResult.getOriginalType());
    assertArrayEquals(new String[]{"record"}, paths.get(2));
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Given {@link MapType#MapType(PrimitiveType, PrimitiveType)} with keyType is {@link ByteArrayType} (default constructor) and valueType is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); given MapType(PrimitiveType, PrimitiveType) with keyType is ByteArrayType (default constructor) and valueType is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_givenMapTypeWithKeyTypeIsByteArrayTypeAndValueTypeIsIntType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    ByteArrayType keyType = new ByteArrayType();
    fieldList.add(new Field("record", new MapType(keyType, new IntType())));
    fieldList.add(new Field("record", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    MessageType actualSchema = SchemaConverter.getSchema(schema);

    // Assert
    verify(schema).getAllFields();
    List<Type> fields = actualSchema.getFields();
    assertEquals(2, fields.size());
    Type getResult = fields.get(0);
    assertTrue(getResult instanceof GroupType);
    assertTrue(getResult.getLogicalTypeAnnotation() instanceof MapLogicalTypeAnnotation);
    List<String[]> paths = actualSchema.getPaths();
    assertEquals(3, paths.size());
    String[] getResult2 = paths.get(0);
    assertEquals("key", getResult2[2]);
    assertEquals("key_value", getResult2[1]);
    String[] getResult3 = paths.get(1);
    assertEquals("key_value", getResult3[1]);
    assertEquals("value", getResult3[2]);
    assertEquals(3, getResult2.length);
    assertEquals(3, getResult3.length);
    assertEquals(OriginalType.MAP, getResult.getOriginalType());
    assertArrayEquals(new String[]{"record"}, paths.get(2));
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Given {@link MapType#MapType(PrimitiveType, PrimitiveType)} with keyType is {@link ByteArrayType} (default constructor) and valueType is {@link sleeper.core.schema.type.PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); given MapType(PrimitiveType, PrimitiveType) with keyType is ByteArrayType (default constructor) and valueType is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_givenMapTypeWithKeyTypeIsByteArrayTypeAndValueTypeIsPrimitiveType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    ByteArrayType keyType = new ByteArrayType();
    fieldList.add(new Field("record", new MapType(keyType, new sleeper.core.schema.type.PrimitiveType())));
    fieldList.add(new Field("record", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> SchemaConverter.getSchema(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Given {@link MapType#MapType(PrimitiveType, PrimitiveType)} with keyType is {@link sleeper.core.schema.type.PrimitiveType} (default constructor) and valueType is {@link sleeper.core.schema.type.PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); given MapType(PrimitiveType, PrimitiveType) with keyType is PrimitiveType (default constructor) and valueType is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_givenMapTypeWithKeyTypeIsPrimitiveTypeAndValueTypeIsPrimitiveType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    sleeper.core.schema.type.PrimitiveType keyType = new sleeper.core.schema.type.PrimitiveType();
    fieldList.add(new Field("record", new MapType(keyType, new sleeper.core.schema.type.PrimitiveType())));
    fieldList.add(new Field("record", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> SchemaConverter.getSchema(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Then return Columns first Type is {@code BINARY}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); then return Columns first Type is 'BINARY'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_thenReturnColumnsFirstTypeIsBinary() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("record", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    MessageType actualSchema = SchemaConverter.getSchema(schema);

    // Assert
    verify(schema).getAllFields();
    List<Type> fields = actualSchema.getFields();
    assertEquals(1, fields.size());
    Type getResult = fields.get(0);
    assertTrue(getResult instanceof PrimitiveType);
    assertNull(getResult.getLogicalTypeAnnotation());
    assertNull(getResult.getOriginalType());
    List<ColumnDescriptor> columns = actualSchema.getColumns();
    assertEquals(1, columns.size());
    assertEquals(PrimitiveTypeName.BINARY, columns.get(0).getType());
    assertEquals(PrimitiveTypeName.BINARY, ((PrimitiveType) getResult).getPrimitiveTypeName());
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Then return Columns first Type is {@code INT32}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); then return Columns first Type is 'INT32'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_thenReturnColumnsFirstTypeIsInt32() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("record", new IntType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    MessageType actualSchema = SchemaConverter.getSchema(schema);

    // Assert
    verify(schema).getAllFields();
    List<Type> fields = actualSchema.getFields();
    assertEquals(1, fields.size());
    Type getResult = fields.get(0);
    assertTrue(getResult instanceof PrimitiveType);
    assertNull(getResult.getLogicalTypeAnnotation());
    assertNull(getResult.getOriginalType());
    List<ColumnDescriptor> columns = actualSchema.getColumns();
    assertEquals(1, columns.size());
    assertEquals(PrimitiveTypeName.INT32, columns.get(0).getType());
    assertEquals(PrimitiveTypeName.INT32, ((PrimitiveType) getResult).getPrimitiveTypeName());
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Then return Columns first Type is {@code INT64}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); then return Columns first Type is 'INT64'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_thenReturnColumnsFirstTypeIsInt64() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("record", new LongType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    MessageType actualSchema = SchemaConverter.getSchema(schema);

    // Assert
    verify(schema).getAllFields();
    List<Type> fields = actualSchema.getFields();
    assertEquals(1, fields.size());
    Type getResult = fields.get(0);
    assertTrue(getResult instanceof PrimitiveType);
    assertNull(getResult.getLogicalTypeAnnotation());
    assertNull(getResult.getOriginalType());
    List<ColumnDescriptor> columns = actualSchema.getColumns();
    assertEquals(1, columns.size());
    assertEquals(PrimitiveTypeName.INT64, columns.get(0).getType());
    assertEquals(PrimitiveTypeName.INT64, ((PrimitiveType) getResult).getPrimitiveTypeName());
  }

  /**
   * Test {@link SchemaConverter#getSchema(Schema)}.
   * <ul>
   *   <li>Then return Fields first Fields size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaConverter#getSchema(Schema)}
   */
  @Test
  @DisplayName("Test getSchema(Schema); then return Fields first Fields size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageType SchemaConverter.getSchema(Schema)"})
  void testGetSchema_thenReturnFieldsFirstFieldsSizeIsOne() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("record", new ListType(new ByteArrayType())));
    fieldList.add(new Field("record", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    MessageType actualSchema = SchemaConverter.getSchema(schema);

    // Assert
    verify(schema).getAllFields();
    List<Type> fields = actualSchema.getFields();
    assertEquals(2, fields.size());
    Type getResult = fields.get(0);
    assertTrue(getResult instanceof GroupType);
    List<Type> fields2 = ((GroupType) getResult).getFields();
    assertEquals(1, fields2.size());
    assertTrue(fields2.get(0) instanceof GroupType);
    LogicalTypeAnnotation logicalTypeAnnotation = getResult.getLogicalTypeAnnotation();
    assertTrue(logicalTypeAnnotation instanceof ListLogicalTypeAnnotation);
    List<String[]> paths = actualSchema.getPaths();
    assertEquals(2, paths.size());
    String[] getResult2 = paths.get(0);
    assertEquals("list", getResult2[1]);
    assertEquals("record", getResult2[2]);
    assertEquals(1, paths.get(1).length);
    assertEquals(3, getResult2.length);
    assertEquals(OriginalType.LIST, logicalTypeAnnotation.toOriginalType());
    assertEquals(OriginalType.LIST, getResult.getOriginalType());
  }
}
