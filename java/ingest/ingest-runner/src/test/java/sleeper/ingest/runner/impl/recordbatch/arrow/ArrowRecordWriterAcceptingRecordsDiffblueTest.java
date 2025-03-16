package sleeper.ingest.runner.impl.recordbatch.arrow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.apache.arrow.memory.OutOfMemoryException;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.type.PrimitiveType;

class ArrowRecordWriterAcceptingRecordsDiffblueTest {
  /**
   * Test {@link ArrowRecordWriterAcceptingRecords#insert(List, VectorSchemaRoot, Record, int)} with {@code List}, {@code VectorSchemaRoot}, {@code Record}, {@code int}.
   * <p>
   * Method under test: {@link ArrowRecordWriterAcceptingRecords#insert(List, VectorSchemaRoot, Record, int)}
   */
  @Test
  @DisplayName("Test insert(List, VectorSchemaRoot, Record, int) with 'List', 'VectorSchemaRoot', 'Record', 'int'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int ArrowRecordWriterAcceptingRecords.insert(List, VectorSchemaRoot, Record, int)"})
  void testInsertWithListVectorSchemaRootRecordInt() throws OutOfMemoryException {
    // Arrange
    ArrowRecordWriterAcceptingRecords arrowRecordWriterAcceptingRecords = new ArrowRecordWriterAcceptingRecords();
    ArrayList<Field> allFields = new ArrayList<>();
    VectorSchemaRoot vectorSchemaRoot = new VectorSchemaRoot(new ArrayList<>());

    // Act
    int actualInsertResult = arrowRecordWriterAcceptingRecords.insert(allFields, vectorSchemaRoot, new Record(), 1);

    // Assert
    assertEquals(2, vectorSchemaRoot.getRowCount());
    assertEquals(2, actualInsertResult);
  }

  /**
   * Test {@link ArrowRecordWriterAcceptingRecords#insert(List, VectorSchemaRoot, Record, int)} with {@code List}, {@code VectorSchemaRoot}, {@code Record}, {@code int}.
   * <p>
   * Method under test: {@link ArrowRecordWriterAcceptingRecords#insert(List, VectorSchemaRoot, Record, int)}
   */
  @Test
  @DisplayName("Test insert(List, VectorSchemaRoot, Record, int) with 'List', 'VectorSchemaRoot', 'Record', 'int'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int ArrowRecordWriterAcceptingRecords.insert(List, VectorSchemaRoot, Record, int)"})
  void testInsertWithListVectorSchemaRootRecordInt2() throws OutOfMemoryException {
    // Arrange
    ArrowRecordWriterAcceptingRecords arrowRecordWriterAcceptingRecords = new ArrowRecordWriterAcceptingRecords();
    ArrayList<Field> allFields = new ArrayList<>();

    ArrayList<FieldVector> vectors = new ArrayList<>();
    vectors.add(new NullVector());
    VectorSchemaRoot vectorSchemaRoot = new VectorSchemaRoot(vectors);

    // Act
    int actualInsertResult = arrowRecordWriterAcceptingRecords.insert(allFields, vectorSchemaRoot, new Record(), 1);

    // Assert
    List<FieldVector> fieldVectors = vectorSchemaRoot.getFieldVectors();
    assertEquals(1, fieldVectors.size());
    FieldVector getResult = fieldVectors.get(0);
    assertTrue(getResult instanceof NullVector);
    assertEquals(2, getResult.getNullCount());
    assertEquals(2, getResult.getValueCapacity());
    assertEquals(2, getResult.getValueCount());
    assertEquals(2, vectorSchemaRoot.getRowCount());
    assertEquals(2, actualInsertResult);
  }

  /**
   * Test {@link ArrowRecordWriterAcceptingRecords#insert(List, VectorSchemaRoot, Record, int)} with {@code List}, {@code VectorSchemaRoot}, {@code Record}, {@code int}.
   * <p>
   * Method under test: {@link ArrowRecordWriterAcceptingRecords#insert(List, VectorSchemaRoot, Record, int)}
   */
  @Test
  @DisplayName("Test insert(List, VectorSchemaRoot, Record, int) with 'List', 'VectorSchemaRoot', 'Record', 'int'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int ArrowRecordWriterAcceptingRecords.insert(List, VectorSchemaRoot, Record, int)"})
  void testInsertWithListVectorSchemaRootRecordInt3() throws OutOfMemoryException {
    // Arrange
    ArrowRecordWriterAcceptingRecords arrowRecordWriterAcceptingRecords = new ArrowRecordWriterAcceptingRecords();

    ArrayList<Field> allFields = new ArrayList<>();
    allFields.add(new Field("Name", new PrimitiveType()));
    VectorSchemaRoot vectorSchemaRoot = new VectorSchemaRoot(new ArrayList<>());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> arrowRecordWriterAcceptingRecords.insert(allFields, vectorSchemaRoot, new Record(), 1));
  }

  /**
   * Test {@link ArrowRecordWriterAcceptingRecords#writeRecord(List, VectorSchemaRoot, Record, int)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrowRecordWriterAcceptingRecords#writeRecord(List, VectorSchemaRoot, Record, int)}
   */
  @Test
  @DisplayName("Test writeRecord(List, VectorSchemaRoot, Record, int); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ArrowRecordWriterAcceptingRecords.writeRecord(List, VectorSchemaRoot, Record, int)"})
  void testWriteRecord_thenThrowUnsupportedOperationException() {
    // Arrange
    ArrayList<Field> allFields = new ArrayList<>();
    allFields.add(new Field("Name", new PrimitiveType()));
    VectorSchemaRoot vectorSchemaRoot = new VectorSchemaRoot(new ArrayList<>());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> ArrowRecordWriterAcceptingRecords.writeRecord(allFields, vectorSchemaRoot, new Record(), 19088743));
  }
}
