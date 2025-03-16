package sleeper.core.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Field;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;
import sleeper.core.schema.type.Type;

class RangeCanonicaliserDiffblueTest {
  /**
   * Test {@link RangeCanonicaliser#canonicaliseRange(Range)}.
   * <p>
   * Method under test: {@link RangeCanonicaliser#canonicaliseRange(Range)}
   */
  @Test
  @DisplayName("Test canonicaliseRange(Range)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeCanonicaliser.canonicaliseRange(Range)"})
  void testCanonicaliseRange() {
    // Arrange
    ByteArrayType type = new ByteArrayType();
    Field field = new Field("Name", type);

    // Act
    Range actualCanonicaliseRangeResult = RangeCanonicaliser
        .canonicaliseRange(new Range(field, "Min", true, null, true));

    // Assert
    Type fieldType = actualCanonicaliseRangeResult.getFieldType();
    assertTrue(fieldType instanceof ByteArrayType);
    assertNull(actualCanonicaliseRangeResult.getMax());
    assertSame(field, actualCanonicaliseRangeResult.getField());
    assertSame(type, fieldType);
  }

  /**
   * Test {@link RangeCanonicaliser#canonicaliseRange(Range)}.
   * <p>
   * Method under test: {@link RangeCanonicaliser#canonicaliseRange(Range)}
   */
  @Test
  @DisplayName("Test canonicaliseRange(Range)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeCanonicaliser.canonicaliseRange(Range)"})
  void testCanonicaliseRange2() {
    // Arrange
    LongType type = new LongType();
    Field field = new Field("Name", type);

    // Act
    Range actualCanonicaliseRangeResult = RangeCanonicaliser
        .canonicaliseRange(new Range(field, "Min", true, Long.MAX_VALUE, true));

    // Assert
    Type fieldType = actualCanonicaliseRangeResult.getFieldType();
    assertTrue(fieldType instanceof LongType);
    assertNull(actualCanonicaliseRangeResult.getMax());
    assertSame(field, actualCanonicaliseRangeResult.getField());
    assertSame(type, fieldType);
  }

  /**
   * Test {@link RangeCanonicaliser#canonicaliseRange(Range)}.
   * <ul>
   *   <li>Then FieldType return {@link IntType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeCanonicaliser#canonicaliseRange(Range)}
   */
  @Test
  @DisplayName("Test canonicaliseRange(Range); then FieldType return IntType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeCanonicaliser.canonicaliseRange(Range)"})
  void testCanonicaliseRange_thenFieldTypeReturnIntType() {
    // Arrange
    IntType type = new IntType();
    Field field = new Field("Name", type);

    // Act
    Range actualCanonicaliseRangeResult = RangeCanonicaliser.canonicaliseRange(new Range(field, "Min", true, 3, true));

    // Assert
    Type fieldType = actualCanonicaliseRangeResult.getFieldType();
    assertTrue(fieldType instanceof IntType);
    assertEquals(4, ((Integer) actualCanonicaliseRangeResult.getMax()).intValue());
    assertSame(field, actualCanonicaliseRangeResult.getField());
    assertSame(type, fieldType);
  }

  /**
   * Test {@link RangeCanonicaliser#canonicaliseRange(Range)}.
   * <ul>
   *   <li>Then FieldType return {@link StringType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeCanonicaliser#canonicaliseRange(Range)}
   */
  @Test
  @DisplayName("Test canonicaliseRange(Range); then FieldType return StringType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeCanonicaliser.canonicaliseRange(Range)"})
  void testCanonicaliseRange_thenFieldTypeReturnStringType() {
    // Arrange
    StringType type = new StringType();
    Field field = new Field("Name", type);

    // Act
    Range actualCanonicaliseRangeResult = RangeCanonicaliser
        .canonicaliseRange(new Range(field, "Min", true, "Max", true));

    // Assert
    Type fieldType = actualCanonicaliseRangeResult.getFieldType();
    assertTrue(fieldType instanceof StringType);
    assertEquals("Max\u0000", actualCanonicaliseRangeResult.getMax());
    assertSame(field, actualCanonicaliseRangeResult.getField());
    assertSame(type, fieldType);
  }

  /**
   * Test {@link RangeCanonicaliser#canonicaliseRange(Range)}.
   * <ul>
   *   <li>Then return Max longValue is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeCanonicaliser#canonicaliseRange(Range)}
   */
  @Test
  @DisplayName("Test canonicaliseRange(Range); then return Max longValue is four")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeCanonicaliser.canonicaliseRange(Range)"})
  void testCanonicaliseRange_thenReturnMaxLongValueIsFour() {
    // Arrange
    LongType type = new LongType();
    Field field = new Field("Name", type);

    // Act
    Range actualCanonicaliseRangeResult = RangeCanonicaliser.canonicaliseRange(new Range(field, "Min", true, 3L, true));

    // Assert
    Type fieldType = actualCanonicaliseRangeResult.getFieldType();
    assertTrue(fieldType instanceof LongType);
    assertEquals(4L, ((Long) actualCanonicaliseRangeResult.getMax()).longValue());
    assertSame(field, actualCanonicaliseRangeResult.getField());
    assertSame(type, fieldType);
  }

  /**
   * Test {@link RangeCanonicaliser#canonicaliseRange(Range)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeCanonicaliser#canonicaliseRange(Range)}
   */
  @Test
  @DisplayName("Test canonicaliseRange(Range); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeCanonicaliser.canonicaliseRange(Range)"})
  void testCanonicaliseRange_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> RangeCanonicaliser
        .canonicaliseRange(new Range(new Field("Name", new PrimitiveType()), "Min", true, "Max", true)));
  }

  /**
   * Test {@link RangeCanonicaliser#canonicaliseRange(Range)}.
   * <ul>
   *   <li>When {@link Range#Range(Field, Object, Object)} with field is {@link Field#Field(String, Type)} and {@code Min} and {@code Max}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeCanonicaliser#canonicaliseRange(Range)}
   */
  @Test
  @DisplayName("Test canonicaliseRange(Range); when Range(Field, Object, Object) with field is Field(String, Type) and 'Min' and 'Max'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range RangeCanonicaliser.canonicaliseRange(Range)"})
  void testCanonicaliseRange_whenRangeWithFieldIsFieldAndMinAndMax() {
    // Arrange
    ByteArrayType type = new ByteArrayType();
    Field field = new Field("Name", type);

    // Act
    Range actualCanonicaliseRangeResult = RangeCanonicaliser.canonicaliseRange(new Range(field, "Min", "Max"));

    // Assert
    Type fieldType = actualCanonicaliseRangeResult.getFieldType();
    assertTrue(fieldType instanceof ByteArrayType);
    assertEquals("Max", actualCanonicaliseRangeResult.getMax());
    assertSame(field, actualCanonicaliseRangeResult.getField());
    assertSame(type, fieldType);
  }
}
