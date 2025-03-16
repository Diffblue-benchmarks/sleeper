package sleeper.ingest.runner.impl.recordbatch.arrow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Set;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.Types.MinorType;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.ArrowType.Binary;
import org.apache.arrow.vector.types.pojo.ArrowType.Int;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.util.Text;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.record.Record;

class ArrowToRecordConversionUtilsDiffblueTest {
  /**
   * Test {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}.
   * <p>
   * Method under test: {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}
   */
  @Test
  @DisplayName("Test convertVectorSchemaRootToRecord(VectorSchemaRoot, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record ArrowToRecordConversionUtils.convertVectorSchemaRootToRecord(VectorSchemaRoot, int)"})
  void testConvertVectorSchemaRootToRecord() {
    // Arrange
    ArrayList<Field> fields = new ArrayList<>();
    Binary type = new Binary();
    FieldType fieldType = new FieldType(true, type, new DictionaryEncoding(1L, true, new Int(1, true)));

    fields.add(new Field("Name", fieldType, new ArrayList<>()));
    Schema schema = new Schema(fields);
    VectorSchemaRoot vectorSchemaRoot = mock(VectorSchemaRoot.class);
    when(vectorSchemaRoot.getVector(anyInt())).thenReturn(new NullVector());
    when(vectorSchemaRoot.getSchema()).thenReturn(schema);

    // Act
    Record actualConvertVectorSchemaRootToRecordResult = ArrowToRecordConversionUtils
        .convertVectorSchemaRootToRecord(vectorSchemaRoot, 1);

    // Assert
    verify(vectorSchemaRoot).getSchema();
    verify(vectorSchemaRoot).getVector(eq(0));
    Set<String> keys = actualConvertVectorSchemaRootToRecordResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("$data$"));
  }

  /**
   * Test {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}.
   * <ul>
   *   <li>Given {@link FieldVector} {@link ValueVector#getObject(int)} return {@link Text#Text()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}
   */
  @Test
  @DisplayName("Test convertVectorSchemaRootToRecord(VectorSchemaRoot, int); given FieldVector getObject(int) return Text()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record ArrowToRecordConversionUtils.convertVectorSchemaRootToRecord(VectorSchemaRoot, int)"})
  void testConvertVectorSchemaRootToRecord_givenFieldVectorGetObjectReturnText() {
    // Arrange
    ArrayList<Field> fields = new ArrayList<>();
    Binary type = new Binary();
    FieldType fieldType = new FieldType(true, type, new DictionaryEncoding(1L, true, new Int(1, true)));

    fields.add(new Field("Name", fieldType, new ArrayList<>()));
    Schema schema = new Schema(fields);
    FieldVector fieldVector = mock(FieldVector.class);
    when(fieldVector.getObject(anyInt())).thenReturn(new Text());
    when(fieldVector.getName()).thenReturn("Name");
    when(fieldVector.getMinorType()).thenReturn(MinorType.NULL);
    VectorSchemaRoot vectorSchemaRoot = mock(VectorSchemaRoot.class);
    when(vectorSchemaRoot.getVector(anyInt())).thenReturn(fieldVector);
    when(vectorSchemaRoot.getSchema()).thenReturn(schema);

    // Act
    Record actualConvertVectorSchemaRootToRecordResult = ArrowToRecordConversionUtils
        .convertVectorSchemaRootToRecord(vectorSchemaRoot, 1);

    // Assert
    verify(fieldVector).getMinorType();
    verify(fieldVector).getName();
    verify(fieldVector).getObject(eq(1));
    verify(vectorSchemaRoot).getSchema();
    verify(vectorSchemaRoot).getVector(eq(0));
    Set<String> keys = actualConvertVectorSchemaRootToRecordResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}.
   * <ul>
   *   <li>Then calls {@link Schema#getFields()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}
   */
  @Test
  @DisplayName("Test convertVectorSchemaRootToRecord(VectorSchemaRoot, int); then calls getFields()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record ArrowToRecordConversionUtils.convertVectorSchemaRootToRecord(VectorSchemaRoot, int)"})
  void testConvertVectorSchemaRootToRecord_thenCallsGetFields() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getFields()).thenReturn(new ArrayList<>());
    VectorSchemaRoot vectorSchemaRoot = mock(VectorSchemaRoot.class);
    when(vectorSchemaRoot.getSchema()).thenReturn(schema);

    // Act
    Record actualConvertVectorSchemaRootToRecordResult = ArrowToRecordConversionUtils
        .convertVectorSchemaRootToRecord(vectorSchemaRoot, 1);

    // Assert
    verify(vectorSchemaRoot).getSchema();
    verify(schema).getFields();
    assertTrue(actualConvertVectorSchemaRootToRecordResult.getKeys().isEmpty());
  }

  /**
   * Test {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}.
   * <ul>
   *   <li>Then return Keys contains {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}
   */
  @Test
  @DisplayName("Test convertVectorSchemaRootToRecord(VectorSchemaRoot, int); then return Keys contains 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record ArrowToRecordConversionUtils.convertVectorSchemaRootToRecord(VectorSchemaRoot, int)"})
  void testConvertVectorSchemaRootToRecord_thenReturnKeysContainsName() {
    // Arrange
    ArrayList<Field> fields = new ArrayList<>();
    Binary type = new Binary();
    FieldType fieldType = new FieldType(true, type, new DictionaryEncoding(1L, true, new Int(1, true)));

    fields.add(new Field("Name", fieldType, new ArrayList<>()));
    Schema schema = new Schema(fields);
    FieldVector fieldVector = mock(FieldVector.class);
    when(fieldVector.getObject(anyInt())).thenReturn("Object");
    when(fieldVector.getName()).thenReturn("Name");
    when(fieldVector.getMinorType()).thenReturn(MinorType.NULL);
    VectorSchemaRoot vectorSchemaRoot = mock(VectorSchemaRoot.class);
    when(vectorSchemaRoot.getVector(anyInt())).thenReturn(fieldVector);
    when(vectorSchemaRoot.getSchema()).thenReturn(schema);

    // Act
    Record actualConvertVectorSchemaRootToRecordResult = ArrowToRecordConversionUtils
        .convertVectorSchemaRootToRecord(vectorSchemaRoot, 1);

    // Assert
    verify(fieldVector).getMinorType();
    verify(fieldVector).getName();
    verify(fieldVector).getObject(eq(1));
    verify(vectorSchemaRoot).getSchema();
    verify(vectorSchemaRoot).getVector(eq(0));
    Set<String> keys = actualConvertVectorSchemaRootToRecordResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}.
   * <ul>
   *   <li>Then return Keys Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}
   */
  @Test
  @DisplayName("Test convertVectorSchemaRootToRecord(VectorSchemaRoot, int); then return Keys Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record ArrowToRecordConversionUtils.convertVectorSchemaRootToRecord(VectorSchemaRoot, int)"})
  void testConvertVectorSchemaRootToRecord_thenReturnKeysEmpty() {
    // Arrange
    VectorSchemaRoot vectorSchemaRoot = mock(VectorSchemaRoot.class);
    when(vectorSchemaRoot.getSchema()).thenReturn(new Schema(new ArrayList<>()));

    // Act
    Record actualConvertVectorSchemaRootToRecordResult = ArrowToRecordConversionUtils
        .convertVectorSchemaRootToRecord(vectorSchemaRoot, 1);

    // Assert
    verify(vectorSchemaRoot).getSchema();
    assertTrue(actualConvertVectorSchemaRootToRecordResult.getKeys().isEmpty());
  }

  /**
   * Test {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add {@link NullVector#NullVector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}
   */
  @Test
  @DisplayName("Test convertVectorSchemaRootToRecord(VectorSchemaRoot, int); when ArrayList() add NullVector()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record ArrowToRecordConversionUtils.convertVectorSchemaRootToRecord(VectorSchemaRoot, int)"})
  void testConvertVectorSchemaRootToRecord_whenArrayListAddNullVector() {
    // Arrange
    ArrayList<FieldVector> vectors = new ArrayList<>();
    vectors.add(new NullVector());

    // Act and Assert
    Set<String> keys = ArrowToRecordConversionUtils.convertVectorSchemaRootToRecord(new VectorSchemaRoot(vectors), 1)
        .getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("$data$"));
  }

  /**
   * Test {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}.
   * <ul>
   *   <li>When {@link VectorSchemaRoot#VectorSchemaRoot(Iterable)} with vectors is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrowToRecordConversionUtils#convertVectorSchemaRootToRecord(VectorSchemaRoot, int)}
   */
  @Test
  @DisplayName("Test convertVectorSchemaRootToRecord(VectorSchemaRoot, int); when VectorSchemaRoot(Iterable) with vectors is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record ArrowToRecordConversionUtils.convertVectorSchemaRootToRecord(VectorSchemaRoot, int)"})
  void testConvertVectorSchemaRootToRecord_whenVectorSchemaRootWithVectorsIsArrayList() {
    // Arrange, Act and Assert
    assertTrue(ArrowToRecordConversionUtils.convertVectorSchemaRootToRecord(new VectorSchemaRoot(new ArrayList<>()), 1)
        .getKeys()
        .isEmpty());
  }
}
