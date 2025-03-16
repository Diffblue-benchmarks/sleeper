package sleeper.parquet.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.Converter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.ListType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.MapType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;
import sleeper.parquet.record.RecordConverter.ByteArrayConverter;
import sleeper.parquet.record.RecordConverter.IntConverter;
import sleeper.parquet.record.RecordConverter.ListConverter;
import sleeper.parquet.record.RecordConverter.LongConverter;
import sleeper.parquet.record.RecordConverter.MapConverter;
import sleeper.parquet.record.RecordConverter.StringConverter;

class RecordConverterDiffblueTest {
  /**
   * Test ByteArrayConverter {@link ByteArrayConverter#addBinary(Binary)}.
   * <ul>
   *   <li>Then calls {@link Binary#getBytes()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ByteArrayConverter#addBinary(Binary)}
   */
  @Test
  @DisplayName("Test ByteArrayConverter addBinary(Binary); then calls getBytes()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ByteArrayConverter.addBinary(Binary)"})
  void testByteArrayConverterAddBinary_thenCallsGetBytes() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayConverter byteArrayConverter = new ByteArrayConverter("Name", new Record());
    Binary value = mock(Binary.class);
    when(value.getBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));

    // Act
    byteArrayConverter.addBinary(value);

    // Assert
    verify(value).getBytes();
  }

  /**
   * Test ByteArrayConverter {@link ByteArrayConverter#ByteArrayConverter(String, Record)}.
   * <p>
   * Method under test: {@link ByteArrayConverter#ByteArrayConverter(String, Record)}
   */
  @Test
  @DisplayName("Test ByteArrayConverter new ByteArrayConverter(String, Record)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ByteArrayConverter.<init>(String, Record)"})
  void testByteArrayConverterNewByteArrayConverter() {
    // Arrange and Act
    ByteArrayConverter actualByteArrayConverter = new ByteArrayConverter("Name", new Record());

    // Assert
    assertFalse(actualByteArrayConverter.hasDictionarySupport());
    assertTrue(actualByteArrayConverter.isPrimitive());
  }

  /**
   * Test IntConverter {@link IntConverter#IntConverter(String, Record)}.
   * <p>
   * Method under test: {@link IntConverter#IntConverter(String, Record)}
   */
  @Test
  @DisplayName("Test IntConverter new IntConverter(String, Record)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IntConverter.<init>(String, Record)"})
  void testIntConverterNewIntConverter() {
    // Arrange and Act
    IntConverter actualIntConverter = new IntConverter("Name", new Record());

    // Assert
    assertFalse(actualIntConverter.hasDictionarySupport());
    assertTrue(actualIntConverter.isPrimitive());
  }

  /**
   * Test ListConverter {@link ListConverter#getConverter(int)}.
   * <p>
   * Method under test: {@link ListConverter#getConverter(int)}
   */
  @Test
  @DisplayName("Test ListConverter getConverter(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Converter ListConverter.getConverter(int)"})
  void testListConverterGetConverter() {
    // Arrange
    ByteArrayType elementType = new ByteArrayType();
    ListConverter<Object> listConverter = new ListConverter<>("Name", elementType, new Record());

    // Act
    Converter actualConverter = listConverter.getConverter(0);

    // Assert
    assertSame(actualConverter, actualConverter.asGroupConverter());
  }

  /**
   * Test ListConverter {@link ListConverter#getConverter(int)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListConverter#getConverter(int)}
   */
  @Test
  @DisplayName("Test ListConverter getConverter(int); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Converter ListConverter.getConverter(int)"})
  void testListConverterGetConverter_thenThrowRuntimeException() {
    // Arrange
    ByteArrayType elementType = new ByteArrayType();
    ListConverter<Object> listConverter = new ListConverter<>("Name", elementType, new Record());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> listConverter.getConverter(1));
  }

  /**
   * Test ListConverter {@link ListConverter#ListConverter(String, PrimitiveType, Record)}.
   * <ul>
   *   <li>When {@link ByteArrayType} (default constructor).</li>
   *   <li>Then return not Primitive.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListConverter#ListConverter(String, PrimitiveType, Record)}
   */
  @Test
  @DisplayName("Test ListConverter new ListConverter(String, PrimitiveType, Record); when ByteArrayType (default constructor); then return not Primitive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ListConverter.<init>(String, PrimitiveType, Record)"})
  void testListConverterNewListConverter_whenByteArrayType_thenReturnNotPrimitive() {
    // Arrange
    ByteArrayType elementType = new ByteArrayType();

    // Act
    ListConverter<Object> actualListConverter = new ListConverter<>("Name", elementType, new Record());

    // Assert
    assertFalse(actualListConverter.isPrimitive());
  }

  /**
   * Test ListConverter {@link ListConverter#ListConverter(String, PrimitiveType, Record)}.
   * <ul>
   *   <li>When {@link IntType} (default constructor).</li>
   *   <li>Then return not Primitive.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListConverter#ListConverter(String, PrimitiveType, Record)}
   */
  @Test
  @DisplayName("Test ListConverter new ListConverter(String, PrimitiveType, Record); when IntType (default constructor); then return not Primitive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ListConverter.<init>(String, PrimitiveType, Record)"})
  void testListConverterNewListConverter_whenIntType_thenReturnNotPrimitive() {
    // Arrange
    IntType elementType = new IntType();

    // Act
    ListConverter<Object> actualListConverter = new ListConverter<>("Name", elementType, new Record());

    // Assert
    assertFalse(actualListConverter.isPrimitive());
  }

  /**
   * Test ListConverter {@link ListConverter#ListConverter(String, PrimitiveType, Record)}.
   * <ul>
   *   <li>When {@link LongType} (default constructor).</li>
   *   <li>Then return not Primitive.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListConverter#ListConverter(String, PrimitiveType, Record)}
   */
  @Test
  @DisplayName("Test ListConverter new ListConverter(String, PrimitiveType, Record); when LongType (default constructor); then return not Primitive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ListConverter.<init>(String, PrimitiveType, Record)"})
  void testListConverterNewListConverter_whenLongType_thenReturnNotPrimitive() {
    // Arrange
    LongType elementType = new LongType();

    // Act
    ListConverter<Object> actualListConverter = new ListConverter<>("Name", elementType, new Record());

    // Assert
    assertFalse(actualListConverter.isPrimitive());
  }

  /**
   * Test ListConverter {@link ListConverter#ListConverter(String, PrimitiveType, Record)}.
   * <ul>
   *   <li>When {@link StringType} (default constructor).</li>
   *   <li>Then return not Primitive.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListConverter#ListConverter(String, PrimitiveType, Record)}
   */
  @Test
  @DisplayName("Test ListConverter new ListConverter(String, PrimitiveType, Record); when StringType (default constructor); then return not Primitive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ListConverter.<init>(String, PrimitiveType, Record)"})
  void testListConverterNewListConverter_whenStringType_thenReturnNotPrimitive() {
    // Arrange
    StringType elementType = new StringType();

    // Act
    ListConverter<Object> actualListConverter = new ListConverter<>("Name", elementType, new Record());

    // Assert
    assertFalse(actualListConverter.isPrimitive());
  }

  /**
   * Test LongConverter {@link LongConverter#LongConverter(String, Record)}.
   * <p>
   * Method under test: {@link LongConverter#LongConverter(String, Record)}
   */
  @Test
  @DisplayName("Test LongConverter new LongConverter(String, Record)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void LongConverter.<init>(String, Record)"})
  void testLongConverterNewLongConverter() {
    // Arrange and Act
    LongConverter actualLongConverter = new LongConverter("Name", new Record());

    // Assert
    assertFalse(actualLongConverter.hasDictionarySupport());
    assertTrue(actualLongConverter.isPrimitive());
  }

  /**
   * Test MapConverter {@link MapConverter#getConverter(int)}.
   * <p>
   * Method under test: {@link MapConverter#getConverter(int)}
   */
  @Test
  @DisplayName("Test MapConverter getConverter(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Converter MapConverter.getConverter(int)"})
  void testMapConverterGetConverter() {
    // Arrange
    ByteArrayType keyType = new ByteArrayType();
    ByteArrayType valueType = new ByteArrayType();
    MapConverter<Object, Object> mapConverter = new MapConverter<>("Name", keyType, valueType, new Record());

    // Act
    Converter actualConverter = mapConverter.getConverter(0);

    // Assert
    assertSame(actualConverter, actualConverter.asGroupConverter());
  }

  /**
   * Test MapConverter {@link MapConverter#getConverter(int)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapConverter#getConverter(int)}
   */
  @Test
  @DisplayName("Test MapConverter getConverter(int); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Converter MapConverter.getConverter(int)"})
  void testMapConverterGetConverter_thenThrowRuntimeException() {
    // Arrange
    ByteArrayType keyType = new ByteArrayType();
    ByteArrayType valueType = new ByteArrayType();
    MapConverter<Object, Object> mapConverter = new MapConverter<>("Name", keyType, valueType, new Record());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> mapConverter.getConverter(1));
  }

  /**
   * Test MapConverter {@link MapConverter#MapConverter(String, PrimitiveType, PrimitiveType, Record)}.
   * <ul>
   *   <li>When {@link ByteArrayType} (default constructor).</li>
   *   <li>Then return not Primitive.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapConverter#MapConverter(String, PrimitiveType, PrimitiveType, Record)}
   */
  @Test
  @DisplayName("Test MapConverter new MapConverter(String, PrimitiveType, PrimitiveType, Record); when ByteArrayType (default constructor); then return not Primitive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MapConverter.<init>(String, PrimitiveType, PrimitiveType, Record)"})
  void testMapConverterNewMapConverter_whenByteArrayType_thenReturnNotPrimitive() {
    // Arrange
    IntType keyType = new IntType();
    ByteArrayType valueType = new ByteArrayType();

    // Act
    MapConverter<Object, Object> actualMapConverter = new MapConverter<>("Name", keyType, valueType, new Record());

    // Assert
    assertFalse(actualMapConverter.isPrimitive());
  }

  /**
   * Test MapConverter {@link MapConverter#MapConverter(String, PrimitiveType, PrimitiveType, Record)}.
   * <ul>
   *   <li>When {@link IntType} (default constructor).</li>
   *   <li>Then return not Primitive.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapConverter#MapConverter(String, PrimitiveType, PrimitiveType, Record)}
   */
  @Test
  @DisplayName("Test MapConverter new MapConverter(String, PrimitiveType, PrimitiveType, Record); when IntType (default constructor); then return not Primitive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MapConverter.<init>(String, PrimitiveType, PrimitiveType, Record)"})
  void testMapConverterNewMapConverter_whenIntType_thenReturnNotPrimitive() {
    // Arrange
    IntType keyType = new IntType();
    IntType valueType = new IntType();

    // Act
    MapConverter<Object, Object> actualMapConverter = new MapConverter<>("Name", keyType, valueType, new Record());

    // Assert
    assertFalse(actualMapConverter.isPrimitive());
  }

  /**
   * Test MapConverter {@link MapConverter#MapConverter(String, PrimitiveType, PrimitiveType, Record)}.
   * <ul>
   *   <li>When {@link LongType} (default constructor).</li>
   *   <li>Then return not Primitive.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapConverter#MapConverter(String, PrimitiveType, PrimitiveType, Record)}
   */
  @Test
  @DisplayName("Test MapConverter new MapConverter(String, PrimitiveType, PrimitiveType, Record); when LongType (default constructor); then return not Primitive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MapConverter.<init>(String, PrimitiveType, PrimitiveType, Record)"})
  void testMapConverterNewMapConverter_whenLongType_thenReturnNotPrimitive() {
    // Arrange
    IntType keyType = new IntType();
    LongType valueType = new LongType();

    // Act
    MapConverter<Object, Object> actualMapConverter = new MapConverter<>("Name", keyType, valueType, new Record());

    // Assert
    assertFalse(actualMapConverter.isPrimitive());
  }

  /**
   * Test MapConverter {@link MapConverter#MapConverter(String, PrimitiveType, PrimitiveType, Record)}.
   * <ul>
   *   <li>When {@link StringType} (default constructor).</li>
   *   <li>Then return not Primitive.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapConverter#MapConverter(String, PrimitiveType, PrimitiveType, Record)}
   */
  @Test
  @DisplayName("Test MapConverter new MapConverter(String, PrimitiveType, PrimitiveType, Record); when StringType (default constructor); then return not Primitive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MapConverter.<init>(String, PrimitiveType, PrimitiveType, Record)"})
  void testMapConverterNewMapConverter_whenStringType_thenReturnNotPrimitive() {
    // Arrange
    IntType keyType = new IntType();
    StringType valueType = new StringType();

    // Act
    MapConverter<Object, Object> actualMapConverter = new MapConverter<>("Name", keyType, valueType, new Record());

    // Assert
    assertFalse(actualMapConverter.isPrimitive());
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    PrimitiveType keyType = new PrimitiveType();
    fieldList.add(new Field("Name", new MapType(keyType, new PrimitiveType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RecordConverter(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter2() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    IntType keyType = new IntType();
    fieldList.add(new Field("Name", new MapType(keyType, new PrimitiveType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RecordConverter(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter3() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    LongType keyType = new LongType();
    fieldList.add(new Field("Name", new MapType(keyType, new PrimitiveType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RecordConverter(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter4() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    StringType keyType = new StringType();
    fieldList.add(new Field("Name", new MapType(keyType, new PrimitiveType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RecordConverter(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter5() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    ByteArrayType keyType = new ByteArrayType();
    fieldList.add(new Field("Name", new MapType(keyType, new PrimitiveType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RecordConverter(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter_givenArrayListAddFieldWithNameAndTypeIsByteArrayType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    RecordConverter actualRecordConverter = new RecordConverter(schema);

    // Assert
    verify(schema).getAllFields();
    assertFalse(actualRecordConverter.isPrimitive());
    assertTrue(actualRecordConverter.getRecord().getKeys().isEmpty());
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter_givenArrayListAddFieldWithNameAndTypeIsIntType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new IntType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    RecordConverter actualRecordConverter = new RecordConverter(schema);

    // Assert
    verify(schema).getAllFields();
    assertFalse(actualRecordConverter.isPrimitive());
    assertTrue(actualRecordConverter.getRecord().getKeys().isEmpty());
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is LongType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter_givenArrayListAddFieldWithNameAndTypeIsLongType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new LongType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    RecordConverter actualRecordConverter = new RecordConverter(schema);

    // Assert
    verify(schema).getAllFields();
    assertFalse(actualRecordConverter.isPrimitive());
    assertTrue(actualRecordConverter.getRecord().getKeys().isEmpty());
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter_givenArrayListAddFieldWithNameAndTypeIsPrimitiveType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new PrimitiveType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RecordConverter(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is StringType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter_givenArrayListAddFieldWithNameAndTypeIsStringType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    RecordConverter actualRecordConverter = new RecordConverter(schema);

    // Assert
    verify(schema).getAllFields();
    assertFalse(actualRecordConverter.isPrimitive());
    assertTrue(actualRecordConverter.getRecord().getKeys().isEmpty());
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return not Primitive.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema); given ArrayList(); then return not Primitive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter_givenArrayList_thenReturnNotPrimitive() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(new ArrayList<>());

    // Act
    RecordConverter actualRecordConverter = new RecordConverter(schema);

    // Assert
    verify(schema).getAllFields();
    assertFalse(actualRecordConverter.isPrimitive());
    assertTrue(actualRecordConverter.getRecord().getKeys().isEmpty());
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RecordConverter(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <ul>
   *   <li>Given {@link ListType#ListType(PrimitiveType)} with elementType is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema); given ListType(PrimitiveType) with elementType is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter_givenListTypeWithElementTypeIsIntType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ListType(new IntType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    RecordConverter actualRecordConverter = new RecordConverter(schema);

    // Assert
    verify(schema).getAllFields();
    assertFalse(actualRecordConverter.isPrimitive());
    assertTrue(actualRecordConverter.getRecord().getKeys().isEmpty());
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <ul>
   *   <li>Given {@link ListType#ListType(PrimitiveType)} with elementType is {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema); given ListType(PrimitiveType) with elementType is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter_givenListTypeWithElementTypeIsPrimitiveType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ListType(new PrimitiveType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RecordConverter(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link RecordConverter#RecordConverter(Schema)}.
   * <ul>
   *   <li>Given {@link MapType#MapType(PrimitiveType, PrimitiveType)} with keyType is {@link IntType} (default constructor) and valueType is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#RecordConverter(Schema)}
   */
  @Test
  @DisplayName("Test new RecordConverter(Schema); given MapType(PrimitiveType, PrimitiveType) with keyType is IntType (default constructor) and valueType is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordConverter.<init>(Schema)"})
  void testNewRecordConverter_givenMapTypeWithKeyTypeIsIntTypeAndValueTypeIsIntType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    IntType keyType = new IntType();
    fieldList.add(new Field("Name", new MapType(keyType, new IntType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    RecordConverter actualRecordConverter = new RecordConverter(schema);

    // Assert
    verify(schema).getAllFields();
    assertFalse(actualRecordConverter.isPrimitive());
    assertTrue(actualRecordConverter.getRecord().getKeys().isEmpty());
  }

  /**
   * Test {@link RecordConverter#getConverter(int)}.
   * <ul>
   *   <li>Then return {@link ByteArrayConverter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordConverter#getConverter(int)}
   */
  @Test
  @DisplayName("Test getConverter(int); then return ByteArrayConverter")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Converter RecordConverter.getConverter(int)"})
  void testGetConverter_thenReturnByteArrayConverter() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("42", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    Converter actualConverter = (new RecordConverter(schema)).getConverter(1);

    // Assert
    assertTrue(actualConverter instanceof ByteArrayConverter);
    assertFalse(((ByteArrayConverter) actualConverter).hasDictionarySupport());
    assertTrue(actualConverter.isPrimitive());
  }

  /**
   * Test StringConverter {@link StringConverter#addBinary(Binary)}.
   * <ul>
   *   <li>Then calls {@link Binary#toStringUsingUTF8()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringConverter#addBinary(Binary)}
   */
  @Test
  @DisplayName("Test StringConverter addBinary(Binary); then calls toStringUsingUTF8()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StringConverter.addBinary(Binary)"})
  void testStringConverterAddBinary_thenCallsToStringUsingUTF8() {
    // Arrange
    StringConverter stringConverter = new StringConverter("Name", new Record());
    Binary value = mock(Binary.class);
    when(value.toStringUsingUTF8()).thenReturn("String Using UTF8");

    // Act
    stringConverter.addBinary(value);

    // Assert
    verify(value).toStringUsingUTF8();
  }

  /**
   * Test StringConverter {@link StringConverter#StringConverter(String, Record)}.
   * <p>
   * Method under test: {@link StringConverter#StringConverter(String, Record)}
   */
  @Test
  @DisplayName("Test StringConverter new StringConverter(String, Record)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StringConverter.<init>(String, Record)"})
  void testStringConverterNewStringConverter() {
    // Arrange and Act
    StringConverter actualStringConverter = new StringConverter("Name", new Record());

    // Assert
    assertFalse(actualStringConverter.hasDictionarySupport());
    assertTrue(actualStringConverter.isPrimitive());
  }
}
