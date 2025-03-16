package sleeper.sketches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.datasketches.quantiles.ItemsSketch;
import org.apache.datasketches.quantiles.ItemsUnion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.Type;
import sleeper.sketches.Sketches.FieldSketch;

class SketchesDiffblueTest {
  /**
   * Test {@link Sketches#createSketch(Type, int)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#createSketch(Type, int)}
   */
  @Test
  @DisplayName("Test createSketch(Type, int); when 'null'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ItemsSketch Sketches.createSketch(Type, int)"})
  void testCreateSketch_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Sketches.createSketch(null, 1));
  }

  /**
   * Test {@link Sketches#createSketch(Type, int)}.
   * <ul>
   *   <li>When two.</li>
   *   <li>Then return MaxValue is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#createSketch(Type, int)}
   */
  @Test
  @DisplayName("Test createSketch(Type, int); when two; then return MaxValue is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ItemsSketch Sketches.createSketch(Type, int)"})
  void testCreateSketch_whenTwo_thenReturnMaxValueIsNull() {
    // Arrange and Act
    ItemsSketch<Object> actualCreateSketchResult = Sketches.createSketch(new PrimitiveType(), 2);

    // Assert
    assertNull(actualCreateSketchResult.getMaxValue());
    assertNull(actualCreateSketchResult.getMinValue());
    assertEquals(0, actualCreateSketchResult.getRetainedItems());
    assertEquals(0L, actualCreateSketchResult.getN());
    assertEquals(1L, actualCreateSketchResult.iterator().getWeight());
    assertEquals(2, actualCreateSketchResult.getK());
    assertFalse(actualCreateSketchResult.isDirect());
    assertFalse(actualCreateSketchResult.isEstimationMode());
    assertTrue(actualCreateSketchResult.isEmpty());
  }

  /**
   * Test {@link Sketches#createUnion(Type, int)}.
   * <ul>
   *   <li>When {@link ByteArrayType} (default constructor).</li>
   *   <li>Then return ResultAndReset is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#createUnion(Type, int)}
   */
  @Test
  @DisplayName("Test createUnion(Type, int); when ByteArrayType (default constructor); then return ResultAndReset is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ItemsUnion Sketches.createUnion(Type, int)"})
  void testCreateUnion_whenByteArrayType_thenReturnResultAndResetIsNull() {
    // Arrange and Act
    ItemsUnion<Object> actualCreateUnionResult = Sketches.createUnion(new ByteArrayType(), 3);

    // Assert
    assertNull(actualCreateUnionResult.getResultAndReset());
    assertEquals(3, actualCreateUnionResult.getEffectiveK());
    assertEquals(3, actualCreateUnionResult.getMaxK());
    assertFalse(actualCreateUnionResult.isDirect());
    assertTrue(actualCreateUnionResult.isEmpty());
  }

  /**
   * Test {@link Sketches#createUnion(Type, int)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#createUnion(Type, int)}
   */
  @Test
  @DisplayName("Test createUnion(Type, int); when 'null'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ItemsUnion Sketches.createUnion(Type, int)"})
  void testCreateUnion_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Sketches.createUnion(null, 3));
  }

  /**
   * Test {@link Sketches#createComparator(Type)}.
   * <ul>
   *   <li>When {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#createComparator(Type)}
   */
  @Test
  @DisplayName("Test createComparator(Type); when ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Comparator Sketches.createComparator(Type)"})
  void testCreateComparator_whenByteArrayType() {
    // Arrange and Act
    Comparator<Object> actualCreateComparatorResult = Sketches.createComparator(new ByteArrayType());

    // Assert
    assertEquals(0, actualCreateComparatorResult.compare("42", "42"));
  }

  /**
   * Test {@link Sketches#createComparator(Type)}.
   * <ul>
   *   <li>When {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#createComparator(Type)}
   */
  @Test
  @DisplayName("Test createComparator(Type); when PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Comparator Sketches.createComparator(Type)"})
  void testCreateComparator_whenPrimitiveType() {
    // Arrange and Act
    Comparator<Object> actualCreateComparatorResult = Sketches.createComparator(new PrimitiveType());

    // Assert
    assertEquals(0, actualCreateComparatorResult.compare("42", "42"));
  }

  /**
   * Test {@link Sketches#getQuantilesSketch(String)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#getQuantilesSketch(String)}
   */
  @Test
  @DisplayName("Test getQuantilesSketch(String); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ItemsSketch Sketches.getQuantilesSketch(String)"})
  void testGetQuantilesSketch_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertNull(Sketches.from(schema).getQuantilesSketch("Key Field Name"));
  }

  /**
   * Test {@link Sketches#fieldSketches()}.
   * <ul>
   *   <li>Then return limit five collect toList size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#fieldSketches()}
   */
  @Test
  @DisplayName("Test fieldSketches(); then return limit five collect toList size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream Sketches.fieldSketches()"})
  void testFieldSketches_thenReturnLimitFiveCollectToListSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    Field field = new Field("Name", new ByteArrayType());

    rowKeyFields.add(field);
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    Stream<FieldSketch> actualFieldSketchesResult = Sketches.from(schema).fieldSketches();

    // Assert
    List<FieldSketch> collectResult = actualFieldSketchesResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    FieldSketch getResult = collectResult.get(0);
    ItemsSketch<Object> sketch = getResult.getSketch();
    assertNull(sketch.getMaxValue());
    assertNull(sketch.getMinValue());
    assertEquals(0, sketch.getRetainedItems());
    assertEquals(0L, sketch.getN());
    assertEquals(1024, sketch.getK());
    assertFalse(sketch.isDirect());
    assertFalse(sketch.isEstimationMode());
    assertTrue(sketch.isEmpty());
    assertSame(field, getResult.getField());
  }

  /**
   * Test {@link Sketches#update(Record)} with {@code record}.
   * <p>
   * Method under test: {@link Sketches#update(Record)}
   */
  @Test
  @DisplayName("Test update(Record) with 'record'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Sketches.update(Record)"})
  void testUpdateWithRecord() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Sketches fromResult = Sketches.from(schema);

    // Act
    fromResult.update(new Record());

    // Assert that nothing has changed
    Stream<FieldSketch> fieldSketchesResult = fromResult.fieldSketches();
    List<FieldSketch> collectResult = fieldSketchesResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    ItemsSketch<Object> sketch = collectResult.get(0).getSketch();
    assertEquals(0, sketch.getRetainedItems());
    assertEquals(0L, sketch.getN());
    assertTrue(sketch.isEmpty());
  }

  /**
   * Test {@link Sketches#update(Record)} with {@code record}.
   * <p>
   * Method under test: {@link Sketches#update(Record)}
   */
  @Test
  @DisplayName("Test update(Record) with 'record'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Sketches.update(Record)"})
  void testUpdateWithRecord2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Sketches fromResult = Sketches.from(schema);
    Record resultRecord = mock(Record.class);
    when(resultRecord.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    fromResult.update(resultRecord);

    // Assert
    verify(resultRecord).get(eq("Name"));
    Stream<FieldSketch> fieldSketchesResult = fromResult.fieldSketches();
    List<FieldSketch> collectResult = fieldSketchesResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    ItemsSketch<Object> sketch = collectResult.get(0).getSketch();
    assertEquals("Get", sketch.getMaxValue());
    assertEquals("Get", sketch.getMinValue());
    assertEquals(1, sketch.getRetainedItems());
    assertEquals(1L, sketch.getN());
    assertFalse(sketch.isEmpty());
  }

  /**
   * Test {@link Sketches#readValueFromSketchWithWrappedBytes(Object, Field)}.
   * <ul>
   *   <li>Then return intValue is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#readValueFromSketchWithWrappedBytes(Object, Field)}
   */
  @Test
  @DisplayName("Test readValueFromSketchWithWrappedBytes(Object, Field); then return intValue is forty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object Sketches.readValueFromSketchWithWrappedBytes(Object, Field)"})
  void testReadValueFromSketchWithWrappedBytes_thenReturnIntValueIsFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42,
        ((Integer) Sketches.readValueFromSketchWithWrappedBytes(42L, new Field("Name", new IntType()))).intValue());
  }

  /**
   * Test {@link Sketches#readValueFromSketchWithWrappedBytes(Object, Field)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#readValueFromSketchWithWrappedBytes(Object, Field)}
   */
  @Test
  @DisplayName("Test readValueFromSketchWithWrappedBytes(Object, Field); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object Sketches.readValueFromSketchWithWrappedBytes(Object, Field)"})
  void testReadValueFromSketchWithWrappedBytes_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(Sketches.readValueFromSketchWithWrappedBytes(null, new Field("Name", new ByteArrayType())));
  }

  /**
   * Test {@link Sketches#readValueFromSketchWithWrappedBytes(Object, Field)}.
   * <ul>
   *   <li>When {@code Value}.</li>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Sketches#readValueFromSketchWithWrappedBytes(Object, Field)}
   */
  @Test
  @DisplayName("Test readValueFromSketchWithWrappedBytes(Object, Field); when 'Value'; then return 'Value'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object Sketches.readValueFromSketchWithWrappedBytes(Object, Field)"})
  void testReadValueFromSketchWithWrappedBytes_whenValue_thenReturnValue() {
    // Arrange, Act and Assert
    assertEquals("Value",
        Sketches.readValueFromSketchWithWrappedBytes("Value", new Field("Name", new ByteArrayType())));
  }
}
