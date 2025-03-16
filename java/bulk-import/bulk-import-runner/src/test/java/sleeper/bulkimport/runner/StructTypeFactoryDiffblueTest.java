package sleeper.bulkimport.runner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import scala.collection.immutable.List;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Stream.Cons;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.ListType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.MapType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;

class StructTypeFactoryDiffblueTest {
  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();

    ArrayList<Field> fieldList = new ArrayList<>();
    PrimitiveType keyType = new PrimitiveType();
    fieldList.add(new Field("Name", new MapType(keyType, new PrimitiveType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> structTypeFactory.getStructType(schema));
    verify(schema, atLeast(1)).getAllFields();
  }

  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType_givenArrayListAddFieldWithNameAndTypeIsByteArrayType() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    StructType actualStructType = structTypeFactory.getStructType(schema);

    // Assert
    verify(schema, atLeast(1)).getAllFields();
    Object distinctResult = actualStructType.distinct();
    assertTrue(distinctResult instanceof scala.collection.immutable.$colon$colon);
    List<StructField> toListResult = actualStructType.toList();
    assertTrue(toListResult instanceof scala.collection.immutable.$colon$colon);
    Object initResult = actualStructType.init();
    assertTrue(initResult instanceof scala.collection.immutable.Nil$);
    assertTrue(actualStructType.toStream() instanceof Cons);
    assertEquals(1, actualStructType.length());
    assertEquals(1, actualStructType.size());
    assertEquals(1, actualStructType.copy$default$1().length);
    assertFalse(actualStructType.isEmpty());
    assertEquals(distinctResult, actualStructType.toVector());
    assertEquals(distinctResult, toListResult);
    assertSame(initResult, actualStructType.tail());
  }

  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType_givenArrayListAddFieldWithNameAndTypeIsIntType() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new IntType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    StructType actualStructType = structTypeFactory.getStructType(schema);

    // Assert
    verify(schema, atLeast(1)).getAllFields();
    Object distinctResult = actualStructType.distinct();
    assertTrue(distinctResult instanceof scala.collection.immutable.$colon$colon);
    List<StructField> toListResult = actualStructType.toList();
    assertTrue(toListResult instanceof scala.collection.immutable.$colon$colon);
    Object initResult = actualStructType.init();
    assertTrue(initResult instanceof scala.collection.immutable.Nil$);
    assertTrue(actualStructType.toStream() instanceof Cons);
    assertEquals(1, actualStructType.length());
    assertEquals(1, actualStructType.size());
    assertEquals(1, actualStructType.copy$default$1().length);
    assertFalse(actualStructType.isEmpty());
    assertEquals(distinctResult, actualStructType.toVector());
    assertEquals(distinctResult, toListResult);
    assertSame(initResult, actualStructType.tail());
  }

  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is LongType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType_givenArrayListAddFieldWithNameAndTypeIsLongType() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new LongType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    StructType actualStructType = structTypeFactory.getStructType(schema);

    // Assert
    verify(schema, atLeast(1)).getAllFields();
    Object distinctResult = actualStructType.distinct();
    assertTrue(distinctResult instanceof scala.collection.immutable.$colon$colon);
    List<StructField> toListResult = actualStructType.toList();
    assertTrue(toListResult instanceof scala.collection.immutable.$colon$colon);
    Object initResult = actualStructType.init();
    assertTrue(initResult instanceof scala.collection.immutable.Nil$);
    assertTrue(actualStructType.toStream() instanceof Cons);
    assertEquals(1, actualStructType.length());
    assertEquals(1, actualStructType.size());
    assertEquals(1, actualStructType.copy$default$1().length);
    assertFalse(actualStructType.isEmpty());
    assertEquals(distinctResult, actualStructType.toVector());
    assertEquals(distinctResult, toListResult);
    assertSame(initResult, actualStructType.tail());
  }

  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType_givenArrayListAddFieldWithNameAndTypeIsPrimitiveType() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new PrimitiveType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> structTypeFactory.getStructType(schema));
    verify(schema, atLeast(1)).getAllFields();
  }

  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is StringType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType_givenArrayListAddFieldWithNameAndTypeIsStringType() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    StructType actualStructType = structTypeFactory.getStructType(schema);

    // Assert
    verify(schema, atLeast(1)).getAllFields();
    Object distinctResult = actualStructType.distinct();
    assertTrue(distinctResult instanceof scala.collection.immutable.$colon$colon);
    List<StructField> toListResult = actualStructType.toList();
    assertTrue(toListResult instanceof scala.collection.immutable.$colon$colon);
    Object initResult = actualStructType.init();
    assertTrue(initResult instanceof scala.collection.immutable.Nil$);
    assertTrue(actualStructType.toStream() instanceof Cons);
    assertEquals(1, actualStructType.length());
    assertEquals(1, actualStructType.size());
    assertEquals(1, actualStructType.copy$default$1().length);
    assertFalse(actualStructType.isEmpty());
    assertEquals(distinctResult, actualStructType.toVector());
    assertEquals(distinctResult, toListResult);
    assertSame(initResult, actualStructType.tail());
  }

  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> structTypeFactory.getStructType(schema));
    verify(schema).getAllFields();
  }

  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <ul>
   *   <li>Given {@link ListType#ListType(PrimitiveType)} with elementType is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema); given ListType(PrimitiveType) with elementType is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType_givenListTypeWithElementTypeIsIntType() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ListType(new IntType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    StructType actualStructType = structTypeFactory.getStructType(schema);

    // Assert
    verify(schema, atLeast(1)).getAllFields();
    Object distinctResult = actualStructType.distinct();
    assertTrue(distinctResult instanceof scala.collection.immutable.$colon$colon);
    List<StructField> toListResult = actualStructType.toList();
    assertTrue(toListResult instanceof scala.collection.immutable.$colon$colon);
    Object initResult = actualStructType.init();
    assertTrue(initResult instanceof scala.collection.immutable.Nil$);
    assertTrue(actualStructType.toStream() instanceof Cons);
    assertEquals(1, actualStructType.length());
    assertEquals(1, actualStructType.size());
    assertEquals(1, actualStructType.copy$default$1().length);
    assertFalse(actualStructType.isEmpty());
    assertEquals(distinctResult, actualStructType.toVector());
    assertEquals(distinctResult, toListResult);
    assertSame(initResult, actualStructType.tail());
  }

  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <ul>
   *   <li>Given {@link ListType#ListType(PrimitiveType)} with elementType is {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema); given ListType(PrimitiveType) with elementType is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType_givenListTypeWithElementTypeIsPrimitiveType() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ListType(new PrimitiveType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> structTypeFactory.getStructType(schema));
    verify(schema, atLeast(1)).getAllFields();
  }

  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <ul>
   *   <li>Given {@link MapType#MapType(PrimitiveType, PrimitiveType)} with keyType is {@link IntType} (default constructor) and valueType is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema); given MapType(PrimitiveType, PrimitiveType) with keyType is IntType (default constructor) and valueType is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType_givenMapTypeWithKeyTypeIsIntTypeAndValueTypeIsIntType() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();

    ArrayList<Field> fieldList = new ArrayList<>();
    IntType keyType = new IntType();
    fieldList.add(new Field("Name", new MapType(keyType, new IntType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    StructType actualStructType = structTypeFactory.getStructType(schema);

    // Assert
    verify(schema, atLeast(1)).getAllFields();
    Object distinctResult = actualStructType.distinct();
    assertTrue(distinctResult instanceof scala.collection.immutable.$colon$colon);
    List<StructField> toListResult = actualStructType.toList();
    assertTrue(toListResult instanceof scala.collection.immutable.$colon$colon);
    Object initResult = actualStructType.init();
    assertTrue(initResult instanceof scala.collection.immutable.Nil$);
    assertTrue(actualStructType.toStream() instanceof Cons);
    assertEquals(1, actualStructType.length());
    assertEquals(1, actualStructType.size());
    assertEquals(1, actualStructType.copy$default$1().length);
    assertFalse(actualStructType.isEmpty());
    assertEquals(distinctResult, actualStructType.toVector());
    assertEquals(distinctResult, toListResult);
    assertSame(initResult, actualStructType.tail());
  }

  /**
   * Test {@link StructTypeFactory#getStructType(Schema)}.
   * <ul>
   *   <li>Given {@link MapType#MapType(PrimitiveType, PrimitiveType)} with keyType is {@link IntType} (default constructor) and valueType is {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StructTypeFactory#getStructType(Schema)}
   */
  @Test
  @DisplayName("Test getStructType(Schema); given MapType(PrimitiveType, PrimitiveType) with keyType is IntType (default constructor) and valueType is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType StructTypeFactory.getStructType(Schema)"})
  void testGetStructType_givenMapTypeWithKeyTypeIsIntTypeAndValueTypeIsPrimitiveType() {
    // Arrange
    StructTypeFactory structTypeFactory = new StructTypeFactory();

    ArrayList<Field> fieldList = new ArrayList<>();
    IntType keyType = new IntType();
    fieldList.add(new Field("Name", new MapType(keyType, new PrimitiveType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> structTypeFactory.getStructType(schema));
    verify(schema, atLeast(1)).getAllFields();
  }
}
