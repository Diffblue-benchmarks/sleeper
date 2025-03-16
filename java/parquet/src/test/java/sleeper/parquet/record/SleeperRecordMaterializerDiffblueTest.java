package sleeper.parquet.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.apache.parquet.io.api.GroupConverter;
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
import sleeper.core.schema.type.StringType;

class SleeperRecordMaterializerDiffblueTest {
  /**
   * Test {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}.
   * <p>
   * Method under test: {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}
   */
  @Test
  @DisplayName("Test new SleeperRecordMaterializer(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperRecordMaterializer.<init>(Schema)"})
  void testNewSleeperRecordMaterializer() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    SleeperRecordMaterializer actualSleeperRecordMaterializer = new SleeperRecordMaterializer(schema);

    // Assert
    verify(schema).getAllFields();
    GroupConverter rootConverter = actualSleeperRecordMaterializer.getRootConverter();
    assertTrue(rootConverter instanceof RecordConverter);
    assertFalse(rootConverter.isPrimitive());
    Record currentRecord = actualSleeperRecordMaterializer.getCurrentRecord();
    assertTrue(currentRecord.getKeys().isEmpty());
    assertSame(currentRecord, ((RecordConverter) rootConverter).getRecord());
  }

  /**
   * Test {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}.
   * <p>
   * Method under test: {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}
   */
  @Test
  @DisplayName("Test new SleeperRecordMaterializer(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperRecordMaterializer.<init>(Schema)"})
  void testNewSleeperRecordMaterializer2() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    SleeperRecordMaterializer actualSleeperRecordMaterializer = new SleeperRecordMaterializer(schema);

    // Assert
    verify(schema).getAllFields();
    GroupConverter rootConverter = actualSleeperRecordMaterializer.getRootConverter();
    assertTrue(rootConverter instanceof RecordConverter);
    assertFalse(rootConverter.isPrimitive());
    Record currentRecord = actualSleeperRecordMaterializer.getCurrentRecord();
    assertTrue(currentRecord.getKeys().isEmpty());
    assertSame(currentRecord, ((RecordConverter) rootConverter).getRecord());
  }

  /**
   * Test {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}.
   * <p>
   * Method under test: {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}
   */
  @Test
  @DisplayName("Test new SleeperRecordMaterializer(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperRecordMaterializer.<init>(Schema)"})
  void testNewSleeperRecordMaterializer3() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    IntType keyType = new IntType();
    fieldList.add(new Field("Name", new MapType(keyType, new IntType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    SleeperRecordMaterializer actualSleeperRecordMaterializer = new SleeperRecordMaterializer(schema);

    // Assert
    verify(schema).getAllFields();
    GroupConverter rootConverter = actualSleeperRecordMaterializer.getRootConverter();
    assertTrue(rootConverter instanceof RecordConverter);
    assertFalse(rootConverter.isPrimitive());
    Record currentRecord = actualSleeperRecordMaterializer.getCurrentRecord();
    assertTrue(currentRecord.getKeys().isEmpty());
    assertSame(currentRecord, ((RecordConverter) rootConverter).getRecord());
  }

  /**
   * Test {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}
   */
  @Test
  @DisplayName("Test new SleeperRecordMaterializer(Schema); given ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperRecordMaterializer.<init>(Schema)"})
  void testNewSleeperRecordMaterializer_givenArrayList() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(new ArrayList<>());

    // Act
    SleeperRecordMaterializer actualSleeperRecordMaterializer = new SleeperRecordMaterializer(schema);

    // Assert
    verify(schema).getAllFields();
    GroupConverter rootConverter = actualSleeperRecordMaterializer.getRootConverter();
    assertTrue(rootConverter instanceof RecordConverter);
    assertFalse(rootConverter.isPrimitive());
    Record currentRecord = actualSleeperRecordMaterializer.getCurrentRecord();
    assertTrue(currentRecord.getKeys().isEmpty());
    assertSame(currentRecord, ((RecordConverter) rootConverter).getRecord());
  }

  /**
   * Test {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}
   */
  @Test
  @DisplayName("Test new SleeperRecordMaterializer(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperRecordMaterializer.<init>(Schema)"})
  void testNewSleeperRecordMaterializer_givenArrayListAddFieldWithNameAndTypeIsIntType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new IntType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    SleeperRecordMaterializer actualSleeperRecordMaterializer = new SleeperRecordMaterializer(schema);

    // Assert
    verify(schema).getAllFields();
    GroupConverter rootConverter = actualSleeperRecordMaterializer.getRootConverter();
    assertTrue(rootConverter instanceof RecordConverter);
    assertFalse(rootConverter.isPrimitive());
    Record currentRecord = actualSleeperRecordMaterializer.getCurrentRecord();
    assertTrue(currentRecord.getKeys().isEmpty());
    assertSame(currentRecord, ((RecordConverter) rootConverter).getRecord());
  }

  /**
   * Test {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}
   */
  @Test
  @DisplayName("Test new SleeperRecordMaterializer(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is LongType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperRecordMaterializer.<init>(Schema)"})
  void testNewSleeperRecordMaterializer_givenArrayListAddFieldWithNameAndTypeIsLongType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new LongType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    SleeperRecordMaterializer actualSleeperRecordMaterializer = new SleeperRecordMaterializer(schema);

    // Assert
    verify(schema).getAllFields();
    GroupConverter rootConverter = actualSleeperRecordMaterializer.getRootConverter();
    assertTrue(rootConverter instanceof RecordConverter);
    assertFalse(rootConverter.isPrimitive());
    Record currentRecord = actualSleeperRecordMaterializer.getCurrentRecord();
    assertTrue(currentRecord.getKeys().isEmpty());
    assertSame(currentRecord, ((RecordConverter) rootConverter).getRecord());
  }

  /**
   * Test {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}.
   * <ul>
   *   <li>Given {@link ListType#ListType(PrimitiveType)} with elementType is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperRecordMaterializer#SleeperRecordMaterializer(Schema)}
   */
  @Test
  @DisplayName("Test new SleeperRecordMaterializer(Schema); given ListType(PrimitiveType) with elementType is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperRecordMaterializer.<init>(Schema)"})
  void testNewSleeperRecordMaterializer_givenListTypeWithElementTypeIsIntType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ListType(new IntType())));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    SleeperRecordMaterializer actualSleeperRecordMaterializer = new SleeperRecordMaterializer(schema);

    // Assert
    verify(schema).getAllFields();
    GroupConverter rootConverter = actualSleeperRecordMaterializer.getRootConverter();
    assertTrue(rootConverter instanceof RecordConverter);
    assertFalse(rootConverter.isPrimitive());
    Record currentRecord = actualSleeperRecordMaterializer.getCurrentRecord();
    assertTrue(currentRecord.getKeys().isEmpty());
    assertSame(currentRecord, ((RecordConverter) rootConverter).getRecord());
  }

  /**
   * Test {@link SleeperRecordMaterializer#getCurrentRecord()}.
   * <ul>
   *   <li>Then return Keys Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperRecordMaterializer#getCurrentRecord()}
   */
  @Test
  @DisplayName("Test getCurrentRecord(); then return Keys Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record SleeperRecordMaterializer.getCurrentRecord()"})
  void testGetCurrentRecord_thenReturnKeysEmpty() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertTrue((new SleeperRecordMaterializer(schema)).getCurrentRecord().getKeys().isEmpty());
  }
}
