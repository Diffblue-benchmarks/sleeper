package sleeper.parquet.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.hadoop.api.ReadSupport.ReadContext;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.Type;
import org.apache.parquet.schema.Type.Repetition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.parquet.record.ParquetRecordReader.Builder;

class ParquetRecordReaderDiffblueTest {
  /**
   * Test Builder {@link Builder#getReadSupport()}.
   * <ul>
   *   <li>Then return {@link RecordReadSupport}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#getReadSupport()}
   */
  @Test
  @DisplayName("Test Builder getReadSupport(); then return RecordReadSupport")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ReadSupport Builder.getReadSupport()"})
  void testBuilderGetReadSupport_thenReturnRecordReadSupport() throws IllegalArgumentException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    ReadSupport<Record> actualReadSupport = (new Builder(new Path("Path String"), schema)).getReadSupport();

    // Assert
    assertTrue(actualReadSupport instanceof RecordReadSupport);
    ReadContext initResult = actualReadSupport.init(null);
    MessageType requestedSchema = initResult.getRequestedSchema();
    assertEquals("record", requestedSchema.getName());
    assertNull(initResult.getReadSupportMetadata());
    assertNull(requestedSchema.getLogicalTypeAnnotation());
    assertNull(requestedSchema.getOriginalType());
    assertNull(requestedSchema.getId());
    assertEquals(1, requestedSchema.getFields().size());
    assertEquals(1, requestedSchema.getColumns().size());
    assertEquals(1, requestedSchema.getPaths().size());
    assertEquals(1, requestedSchema.getFieldCount());
    assertEquals(Repetition.REPEATED, requestedSchema.getRepetition());
    assertFalse(requestedSchema.isPrimitive());
  }

  /**
   * Test Builder {@link Builder#Builder(Path, Schema)}.
   * <ul>
   *   <li>Then ReadSupport return {@link RecordReadSupport}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#Builder(Path, Schema)}
   */
  @Test
  @DisplayName("Test Builder new Builder(Path, Schema); then ReadSupport return RecordReadSupport")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Builder.<init>(Path, Schema)"})
  void testBuilderNewBuilder_thenReadSupportReturnRecordReadSupport() throws IllegalArgumentException {
    // Arrange, Act and Assert
    assertTrue(
        (new Builder(new Path("Path String"), mock(Schema.class))).getReadSupport() instanceof RecordReadSupport);
  }

  /**
   * Test {@link ParquetRecordReader#ParquetRecordReader(Path, Schema)}.
   * <ul>
   *   <li>Then return CurrentRowIndex is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParquetRecordReader#ParquetRecordReader(Path, Schema)}
   */
  @Test
  @DisplayName("Test new ParquetRecordReader(Path, Schema); then return CurrentRowIndex is minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ParquetRecordReader.<init>(Path, Schema)"})
  void testNewParquetRecordReader_thenReturnCurrentRowIndexIsMinusOne() throws IOException, IllegalArgumentException {
    // Arrange, Act and Assert
    assertEquals(-1L, (new ParquetRecordReader(new Path(Path.SEPARATOR), mock(Schema.class))).getCurrentRowIndex());
  }
}
