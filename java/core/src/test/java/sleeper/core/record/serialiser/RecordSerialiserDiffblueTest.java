package sleeper.core.record.serialiser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.PrimitiveType;

class RecordSerialiserDiffblueTest {
  /**
   * Test {@link RecordSerialiser#serialise(Record)}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordSerialiser#serialise(Record)}
   */
  @Test
  @DisplayName("Test serialise(Record); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] RecordSerialiser.serialise(Record)"})
  void testSerialise_thenThrowIOException() throws IOException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordSerialiser recordSerialiser = new RecordSerialiser(schema);

    // Act and Assert
    assertThrows(IOException.class, () -> recordSerialiser.serialise(new Record()));
  }

  /**
   * Test {@link RecordSerialiser#deserialise(byte[])}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordSerialiser#deserialise(byte[])}
   */
  @Test
  @DisplayName("Test deserialise(byte[]); given ArrayList() add Field(String, Type) with 'Name' and type is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record RecordSerialiser.deserialise(byte[])"})
  void testDeserialise_givenArrayListAddFieldWithNameAndTypeIsIntType() throws IOException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordSerialiser recordSerialiser = new RecordSerialiser(schema);

    // Act and Assert
    Set<String> keys = recordSerialiser.deserialise("AXAXAXAX".getBytes("UTF-8")).getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RecordSerialiser#deserialise(byte[])}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordSerialiser#deserialise(byte[])}
   */
  @Test
  @DisplayName("Test deserialise(byte[]); given ArrayList() add Field(String, Type) with 'Name' and type is LongType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record RecordSerialiser.deserialise(byte[])"})
  void testDeserialise_givenArrayListAddFieldWithNameAndTypeIsLongType() throws IOException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordSerialiser recordSerialiser = new RecordSerialiser(schema);

    // Act and Assert
    Set<String> keys = recordSerialiser.deserialise("AXAXAXAX".getBytes("UTF-8")).getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RecordSerialiser#deserialise(byte[])}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordSerialiser#deserialise(byte[])}
   */
  @Test
  @DisplayName("Test deserialise(byte[]); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record RecordSerialiser.deserialise(byte[])"})
  void testDeserialise_thenThrowIOException() throws IOException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordSerialiser recordSerialiser = new RecordSerialiser(schema);

    // Act and Assert
    assertThrows(IOException.class, () -> recordSerialiser.deserialise("AXAXAXAX".getBytes("UTF-8")));
  }
}
