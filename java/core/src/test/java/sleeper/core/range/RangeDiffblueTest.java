package sleeper.core.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.range.Range.RangeFactory;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;
import sleeper.core.schema.type.Type;

class RangeDiffblueTest {
  /**
   * Test getters and setters.
   * <ul>
   *   <li>Then return not MaxInclusive.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Range#Range(Field, Object, Object)}
   *   <li>{@link Range#toString()}
   *   <li>{@link Range#getField()}
   *   <li>{@link Range#getMax()}
   *   <li>{@link Range#getMin()}
   *   <li>{@link Range#isMaxInclusive()}
   *   <li>{@link Range#isMinInclusive()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return not MaxInclusive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Range.<init>(Field, Object, Object)",
      "void Range.<init>(Field, Object, boolean, Object, boolean)", "Field Range.getField()", "Object Range.getMax()",
      "Object Range.getMin()", "boolean Range.isMaxInclusive()", "boolean Range.isMinInclusive()",
      "String Range.toString()"})
  void testGettersAndSetters_thenReturnNotMaxInclusive() {
    // Arrange
    Field field = new Field("Name", new ByteArrayType());

    // Act
    Range actualRange = new Range(field, "Min", "Max");
    String actualToStringResult = actualRange.toString();
    Field actualField = actualRange.getField();
    Object actualMax = actualRange.getMax();
    Object actualMin = actualRange.getMin();
    boolean actualIsMaxInclusiveResult = actualRange.isMaxInclusive();

    // Assert
    assertEquals("Max", actualMax);
    assertEquals("Min", actualMin);
    assertEquals("Range{field=Field{name=Name, type=ByteArrayType{}}, min=Min, minInclusive=true, max=Max, maxInclusive"
        + "=false}", actualToStringResult);
    assertFalse(actualIsMaxInclusiveResult);
    assertTrue(actualRange.isMinInclusive());
    assertSame(field, actualField);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return MaxInclusive.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Range#Range(Field, Object, boolean, Object, boolean)}
   *   <li>{@link Range#toString()}
   *   <li>{@link Range#getField()}
   *   <li>{@link Range#getMax()}
   *   <li>{@link Range#getMin()}
   *   <li>{@link Range#isMaxInclusive()}
   *   <li>{@link Range#isMinInclusive()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when 'true'; then return MaxInclusive")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Range.<init>(Field, Object, Object)",
      "void Range.<init>(Field, Object, boolean, Object, boolean)", "Field Range.getField()", "Object Range.getMax()",
      "Object Range.getMin()", "boolean Range.isMaxInclusive()", "boolean Range.isMinInclusive()",
      "String Range.toString()"})
  void testGettersAndSetters_whenTrue_thenReturnMaxInclusive() {
    // Arrange
    Field field = new Field("Name", new ByteArrayType());

    // Act
    Range actualRange = new Range(field, "Min", true, "Max", true);
    String actualToStringResult = actualRange.toString();
    Field actualField = actualRange.getField();
    Object actualMax = actualRange.getMax();
    Object actualMin = actualRange.getMin();
    boolean actualIsMaxInclusiveResult = actualRange.isMaxInclusive();

    // Assert
    assertEquals("Max", actualMax);
    assertEquals("Min", actualMin);
    assertEquals("Range{field=Field{name=Name, type=ByteArrayType{}}, min=Min, minInclusive=true, max=Max,"
        + " maxInclusive=true}", actualToStringResult);
    assertTrue(actualIsMaxInclusiveResult);
    assertTrue(actualRange.isMinInclusive());
    assertSame(field, actualField);
  }

  /**
   * Test {@link Range#getFieldName()}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#getFieldName()}
   */
  @Test
  @DisplayName("Test getFieldName(); given Field(String, Type) with 'Name' and type is ByteArrayType (default constructor); then return 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Range.getFieldName()"})
  void testGetFieldName_givenFieldWithNameAndTypeIsByteArrayType_thenReturnName() {
    // Arrange, Act and Assert
    assertEquals("Name",
        RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"))
            .getFieldName());
  }

  /**
   * Test {@link Range#getFieldType()}.
   * <ul>
   *   <li>Then return {@link ByteArrayType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#getFieldType()}
   */
  @Test
  @DisplayName("Test getFieldType(); then return ByteArrayType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type Range.getFieldType()"})
  void testGetFieldType_thenReturnByteArrayType() {
    // Arrange
    ByteArrayType type = new ByteArrayType();

    // Act
    Type actualFieldType = RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", type), "Min", "Max"))
        .getFieldType();

    // Assert
    assertTrue(actualFieldType instanceof ByteArrayType);
    assertSame(type, actualFieldType);
  }

  /**
   * Test {@link Range#doesRangeContainObject(Object)}.
   * <p>
   * Method under test: {@link Range#doesRangeContainObject(Object)}
   */
  @Test
  @DisplayName("Test doesRangeContainObject(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeContainObject(Object)"})
  void testDoesRangeContainObject() {
    // Arrange, Act and Assert
    assertFalse(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new IntType()), 13, "Max"))
        .doesRangeContainObject(1));
  }

  /**
   * Test {@link Range#doesRangeContainObject(Object)}.
   * <p>
   * Method under test: {@link Range#doesRangeContainObject(Object)}
   */
  @Test
  @DisplayName("Test doesRangeContainObject(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeContainObject(Object)"})
  void testDoesRangeContainObject2() {
    // Arrange, Act and Assert
    assertFalse(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new IntType()), 1, 3))
        .doesRangeContainObject(42));
  }

  /**
   * Test {@link Range#doesRangeContainObject(Object)}.
   * <p>
   * Method under test: {@link Range#doesRangeContainObject(Object)}
   */
  @Test
  @DisplayName("Test doesRangeContainObject(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeContainObject(Object)"})
  void testDoesRangeContainObject3() {
    // Arrange, Act and Assert
    assertTrue(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new IntType()), 1, null))
        .doesRangeContainObject(42));
  }

  /**
   * Test {@link Range#doesRangeContainObject(Object)}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#doesRangeContainObject(Object)}
   */
  @Test
  @DisplayName("Test doesRangeContainObject(Object); given Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeContainObject(Object)"})
  void testDoesRangeContainObject_givenFieldWithNameAndTypeIsByteArrayType() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"))
            .doesRangeContainObject("Value"));
  }

  /**
   * Test {@link Range#doesRangeContainObject(Object)}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#doesRangeContainObject(Object)}
   */
  @Test
  @DisplayName("Test doesRangeContainObject(Object); given Field(String, Type) with 'Name' and type is LongType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeContainObject(Object)"})
  void testDoesRangeContainObject_givenFieldWithNameAndTypeIsLongType() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new LongType()), "Min", "Max"))
            .doesRangeContainObject("Value"));
  }

  /**
   * Test {@link Range#doesRangeContainObject(Object)}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#doesRangeContainObject(Object)}
   */
  @Test
  @DisplayName("Test doesRangeContainObject(Object); given Field(String, Type) with 'Name' and type is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeContainObject(Object)"})
  void testDoesRangeContainObject_givenFieldWithNameAndTypeIsPrimitiveType() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new PrimitiveType()), "Min", "Max"))
            .doesRangeContainObject("Value"));
  }

  /**
   * Test {@link Range#doesRangeContainObject(Object)}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#doesRangeContainObject(Object)}
   */
  @Test
  @DisplayName("Test doesRangeContainObject(Object); given Field(String, Type) with 'Name' and type is StringType (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeContainObject(Object)"})
  void testDoesRangeContainObject_givenFieldWithNameAndTypeIsStringType_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new StringType()), "Min", "Max"))
        .doesRangeContainObject("Value"));
  }

  /**
   * Test {@link Range#doesRangeContainObject(Object)}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   *   <li>When forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#doesRangeContainObject(Object)}
   */
  @Test
  @DisplayName("Test doesRangeContainObject(Object); given Field(String, Type) with 'Name' and type is StringType (default constructor); when forty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeContainObject(Object)"})
  void testDoesRangeContainObject_givenFieldWithNameAndTypeIsStringType_whenFortyTwo() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new StringType()), "Min", "Max"))
            .doesRangeContainObject(42));
  }

  /**
   * Test {@link Range#doesRangeContainObject(Object)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#doesRangeContainObject(Object)}
   */
  @Test
  @DisplayName("Test doesRangeContainObject(Object); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeContainObject(Object)"})
  void testDoesRangeContainObject_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new IntType()), 1, 3))
        .doesRangeContainObject(1));
  }

  /**
   * Test {@link Range#doesRangeContainObject(Object)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#doesRangeContainObject(Object)}
   */
  @Test
  @DisplayName("Test doesRangeContainObject(Object); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeContainObject(Object)"})
  void testDoesRangeContainObject_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new IntType()), "Min", "Max"))
            .doesRangeContainObject("Value"));
  }

  /**
   * Test {@link Range#doesRangeOverlap(Range)}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#doesRangeOverlap(Range)}
   */
  @Test
  @DisplayName("Test doesRangeOverlap(Range); given Field(String, Type) with 'Name' and type is IntType (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.doesRangeOverlap(Range)"})
  void testDoesRangeOverlap_givenFieldWithNameAndTypeIsIntType_thenReturnFalse() {
    // Arrange
    Range canonicaliseRangeResult = RangeCanonicaliser
        .canonicaliseRange(new Range(new Field("Name", new IntType()), "Min", "Max"));

    // Act and Assert
    assertFalse(canonicaliseRangeResult.doesRangeOverlap(
        RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"))));
  }

  /**
   * Test {@link Range#isInCanonicalForm()}.
   * <p>
   * Method under test: {@link Range#isInCanonicalForm()}
   */
  @Test
  @DisplayName("Test isInCanonicalForm()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.isInCanonicalForm()"})
  void testIsInCanonicalForm() {
    // Arrange, Act and Assert
    assertFalse((new Range(new Field("Name", new ByteArrayType()), "Min", true, "Max", true)).isInCanonicalForm());
  }

  /**
   * Test {@link Range#isInCanonicalForm()}.
   * <p>
   * Method under test: {@link Range#isInCanonicalForm()}
   */
  @Test
  @DisplayName("Test isInCanonicalForm()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.isInCanonicalForm()"})
  void testIsInCanonicalForm2() {
    // Arrange, Act and Assert
    assertFalse((new Range(new Field("Name", new ByteArrayType()), "Min", false, "Max", true)).isInCanonicalForm());
  }

  /**
   * Test {@link Range#isInCanonicalForm()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#isInCanonicalForm()}
   */
  @Test
  @DisplayName("Test isInCanonicalForm(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.isInCanonicalForm()"})
  void testIsInCanonicalForm_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"))
        .isInCanonicalForm());
  }

  /**
   * Test {@link Range#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.equals(Object)", "int Range.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Range canonicaliseRangeResult = RangeCanonicaliser
        .canonicaliseRange(new Range(new Field("Name", new IntType()), "Min", "Max"));

    // Act and Assert
    assertNotEquals(canonicaliseRangeResult,
        RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));
  }

  /**
   * Test {@link Range#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.equals(Object)", "int Range.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")), null);
  }

  /**
   * Test {@link Range#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Range#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Range.equals(Object)", "int Range.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")),
        "Different type to Range");
  }

  /**
   * Test RangeFactory {@link RangeFactory#createExactRange(Field, Object)} with {@code field}, {@code value}.
   * <p>
   * Method under test: {@link RangeFactory#createExactRange(Field, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createExactRange(Field, Object) with 'field', 'value'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createExactRange(Field, Object)"})
  void testRangeFactoryCreateExactRangeWithFieldValue() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createExactRange(new Field("Name", new ByteArrayType()), "Value"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createExactRange(Field, Object)} with {@code field}, {@code value}.
   * <p>
   * Method under test: {@link RangeFactory#createExactRange(Field, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createExactRange(Field, Object) with 'field', 'value'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createExactRange(Field, Object)"})
  void testRangeFactoryCreateExactRangeWithFieldValue2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field(RegionSerDe.MIN, new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createExactRange(new Field("Name", new ByteArrayType()), "Value"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createExactRange(Field, Object)} with {@code field}, {@code value}.
   * <p>
   * Method under test: {@link RangeFactory#createExactRange(Field, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createExactRange(Field, Object) with 'field', 'value'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createExactRange(Field, Object)"})
  void testRangeFactoryCreateExactRangeWithFieldValue3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createExactRange(new Field("Name", new ByteArrayType()), "Value"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createExactRange(Field, Object)} with {@code field}, {@code value}.
   * <p>
   * Method under test: {@link RangeFactory#createExactRange(Field, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createExactRange(Field, Object) with 'field', 'value'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createExactRange(Field, Object)"})
  void testRangeFactoryCreateExactRangeWithFieldValue4() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createExactRange(new Field("Name", new ByteArrayType()), "Value"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createExactRange(Field, Object)} with {@code field}, {@code value}.
   * <p>
   * Method under test: {@link RangeFactory#createExactRange(Field, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createExactRange(Field, Object) with 'field', 'value'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createExactRange(Field, Object)"})
  void testRangeFactoryCreateExactRangeWithFieldValue5() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createExactRange(new Field("Name", new ByteArrayType()), "Value"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createExactRange(Field, Object)} with {@code field}, {@code value}.
   * <ul>
   *   <li>Then return Max intValue is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#createExactRange(Field, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createExactRange(Field, Object) with 'field', 'value'; then return Max intValue is forty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createExactRange(Field, Object)"})
  void testRangeFactoryCreateExactRangeWithFieldValue_thenReturnMaxIntValueIsFortyTwo() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act
    Range actualCreateExactRangeResult = rangeFactory.createExactRange(new Field("Name", new ByteArrayType()), 42);

    // Assert
    assertEquals(42, ((Integer) actualCreateExactRangeResult.getMax()).intValue());
    assertEquals(42, ((Integer) actualCreateExactRangeResult.getMin()).intValue());
  }

  /**
   * Test RangeFactory {@link RangeFactory#createExactRange(Field, Object)} with {@code field}, {@code value}.
   * <ul>
   *   <li>Then return Max is {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#createExactRange(Field, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createExactRange(Field, Object) with 'field', 'value'; then return Max is 'Value'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createExactRange(Field, Object)"})
  void testRangeFactoryCreateExactRangeWithFieldValue_thenReturnMaxIsValue() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act
    Range actualCreateExactRangeResult = rangeFactory.createExactRange(new Field("Name", new ByteArrayType()), "Value");

    // Assert
    assertEquals("Value", actualCreateExactRangeResult.getMax());
    assertEquals("Value", actualCreateExactRangeResult.getMin());
  }

  /**
   * Test RangeFactory {@link RangeFactory#createExactRange(Field, Object)} with {@code field}, {@code value}.
   * <ul>
   *   <li>Then return Max longValue is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#createExactRange(Field, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createExactRange(Field, Object) with 'field', 'value'; then return Max longValue is forty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createExactRange(Field, Object)"})
  void testRangeFactoryCreateExactRangeWithFieldValue_thenReturnMaxLongValueIsFortyTwo() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act
    Range actualCreateExactRangeResult = rangeFactory.createExactRange(new Field("Name", new ByteArrayType()), 42L);

    // Assert
    assertEquals(42L, ((Long) actualCreateExactRangeResult.getMax()).longValue());
    assertEquals(42L, ((Long) actualCreateExactRangeResult.getMin()).longValue());
  }

  /**
   * Test RangeFactory {@link RangeFactory#createExactRange(Field, Object)} with {@code field}, {@code value}.
   * <ul>
   *   <li>When {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#createExactRange(Field, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createExactRange(Field, Object) with 'field', 'value'; when ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createExactRange(Field, Object)"})
  void testRangeFactoryCreateExactRangeWithFieldValue_whenByteArrayType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);
    Field field = new Field("Name", new ByteArrayType());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> rangeFactory.createExactRange(field, new ByteArrayType()));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createExactRange(Field, Object)} with {@code field}, {@code value}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#createExactRange(Field, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createExactRange(Field, Object) with 'field', 'value'; when 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createExactRange(Field, Object)"})
  void testRangeFactoryCreateExactRangeWithFieldValue_whenNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createExactRange(new Field("Name", new ByteArrayType()), null));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", "Max"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field(RegionSerDe.MIN, new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", "Max"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", "Max"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax4() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", "Max"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax5() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", "Max"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax6() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), null, "Max"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax7() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), 1, "Max"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax8() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), 1L, "Max"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <ul>
   *   <li>Then return {@code Max}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'; then return 'Max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax_thenReturnMax() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);
    ByteArrayType type = new ByteArrayType();
    Field field = new Field("Name", type);

    // Act
    Range actualCreateRangeResult = rangeFactory.createRange(field, "Min", "Max");

    // Assert
    Type fieldType = actualCreateRangeResult.getFieldType();
    assertTrue(fieldType instanceof ByteArrayType);
    assertEquals("Max", actualCreateRangeResult.getMax());
    assertEquals("Min", actualCreateRangeResult.getMin());
    assertEquals("Name", actualCreateRangeResult.getFieldName());
    assertFalse(actualCreateRangeResult.isMaxInclusive());
    assertTrue(actualCreateRangeResult.isInCanonicalForm());
    assertTrue(actualCreateRangeResult.isMinInclusive());
    assertSame(field, actualCreateRangeResult.getField());
    assertSame(type, fieldType);
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <ul>
   *   <li>Then return Max is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'; then return Max is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax_thenReturnMaxIsNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);
    ByteArrayType type = new ByteArrayType();
    Field field = new Field("Name", type);

    // Act
    Range actualCreateRangeResult = rangeFactory.createRange(field, "Min", null);

    // Assert
    Type fieldType = actualCreateRangeResult.getFieldType();
    assertTrue(fieldType instanceof ByteArrayType);
    assertEquals("Min", actualCreateRangeResult.getMin());
    assertEquals("Name", actualCreateRangeResult.getFieldName());
    assertNull(actualCreateRangeResult.getMax());
    assertFalse(actualCreateRangeResult.isMaxInclusive());
    assertTrue(actualCreateRangeResult.isInCanonicalForm());
    assertTrue(actualCreateRangeResult.isMinInclusive());
    assertSame(field, actualCreateRangeResult.getField());
    assertSame(type, fieldType);
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, Object)} with {@code field}, {@code min}, {@code max}.
   * <ul>
   *   <li>When {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, Object)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, Object) with 'field', 'min', 'max'; when ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, Object)"})
  void testRangeFactoryCreateRangeWithFieldMinMax_whenByteArrayType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);
    Field field = new Field("Name", new ByteArrayType());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> rangeFactory.createRange(field, new ByteArrayType(), "Max"));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", true, "Max", true));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field(RegionSerDe.MIN, new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", true, "Max", true));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", true, "Max", true));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive4() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", true, "Max", true));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive5() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", true, "Max", true));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive6() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), null, true, "Max", true));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive7() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), 1, true, "Max", true));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive8() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(new Field("Name", new ByteArrayType()), 1L, true, "Max", true));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive9() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);
    Field field = new Field("Name", new ByteArrayType());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> rangeFactory.createRange(field, new ByteArrayType(), true, "Max", true));
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive10() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act
    Range actualCreateRangeResult = rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", true, null,
        true);

    // Assert
    assertNull(actualCreateRangeResult.getMax());
    assertFalse(actualCreateRangeResult.isMaxInclusive());
    assertTrue(actualCreateRangeResult.isInCanonicalForm());
  }

  /**
   * Test RangeFactory {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)} with {@code field}, {@code min}, {@code minInclusive}, {@code max}, {@code maxInclusive}.
   * <ul>
   *   <li>Then return {@code Max}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#createRange(Field, Object, boolean, Object, boolean)}
   */
  @Test
  @DisplayName("Test RangeFactory createRange(Field, Object, boolean, Object, boolean) with 'field', 'min', 'minInclusive', 'max', 'maxInclusive'; then return 'Max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeFactory.createRange(Field, Object, boolean, Object, boolean)"})
  void testRangeFactoryCreateRangeWithFieldMinMinInclusiveMaxMaxInclusive_thenReturnMax() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RangeFactory rangeFactory = new RangeFactory(schema);

    // Act
    Range actualCreateRangeResult = rangeFactory.createRange(new Field("Name", new ByteArrayType()), "Min", true, "Max",
        true);

    // Assert
    assertEquals("Max", actualCreateRangeResult.getMax());
    assertFalse(actualCreateRangeResult.isInCanonicalForm());
    assertTrue(actualCreateRangeResult.isMaxInclusive());
  }

  /**
   * Test RangeFactory {@link RangeFactory#RangeFactory(Schema)}.
   * <p>
   * Method under test: {@link RangeFactory#RangeFactory(Schema)}
   */
  @Test
  @DisplayName("Test RangeFactory new RangeFactory(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RangeFactory.<init>(Schema)"})
  void testRangeFactoryNewRangeFactory() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    new RangeFactory(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
  }

  /**
   * Test RangeFactory {@link RangeFactory#RangeFactory(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link Schema#getRowKeyFieldNames()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#RangeFactory(Schema)}
   */
  @Test
  @DisplayName("Test RangeFactory new RangeFactory(Schema); given ArrayList(); then calls getRowKeyFieldNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RangeFactory.<init>(Schema)"})
  void testRangeFactoryNewRangeFactory_givenArrayList_thenCallsGetRowKeyFieldNames() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    new RangeFactory(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
  }

  /**
   * Test RangeFactory {@link RangeFactory#RangeFactory(Schema)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeFactory#RangeFactory(Schema)}
   */
  @Test
  @DisplayName("Test RangeFactory new RangeFactory(Schema); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RangeFactory.<init>(Schema)"})
  void testRangeFactoryNewRangeFactory_thenThrowIllegalArgumentException() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RangeFactory(schema));
    verify(schema).getRowKeyFields();
  }
}
