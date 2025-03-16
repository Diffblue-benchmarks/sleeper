package sleeper.core.record.serialiser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.record.Record;
import sleeper.core.record.ResultsBatch;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.PrimitiveType;

class Base64ResultsBatchSerialiserDiffblueTest {
  /**
   * Test {@link Base64ResultsBatchSerialiser#serialise(ResultsBatch)}.
   * <ul>
   *   <li>Then return {@code AAI0MgAAAAA=}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Base64ResultsBatchSerialiser#serialise(ResultsBatch)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch); then return 'AAI0MgAAAAA='")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Base64ResultsBatchSerialiser.serialise(ResultsBatch)"})
  void testSerialise_thenReturnAAI0MgAAAAA() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Base64ResultsBatchSerialiser base64ResultsBatchSerialiser = new Base64ResultsBatchSerialiser(schema);

    ArrayList<Field> rowKeyFields2 = new ArrayList<>();
    rowKeyFields2.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult2 = Schema.builder().rowKeyFields(rowKeyFields2);
    Builder sortKeyFieldsResult2 = rowKeyFieldsResult2.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult2.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals("AAI0MgAAAAA=",
        base64ResultsBatchSerialiser.serialise(new ResultsBatch("42", schema2, new ArrayList<>())));
  }

  /**
   * Test {@link Base64ResultsBatchSerialiser#serialise(ResultsBatch)}.
   * <ul>
   *   <li>Then return {@code AAAAAAAA}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Base64ResultsBatchSerialiser#serialise(ResultsBatch)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch); then return 'AAAAAAAA'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Base64ResultsBatchSerialiser.serialise(ResultsBatch)"})
  void testSerialise_thenReturnAaaaaaaa() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Base64ResultsBatchSerialiser base64ResultsBatchSerialiser = new Base64ResultsBatchSerialiser(schema);

    ArrayList<Field> rowKeyFields2 = new ArrayList<>();
    rowKeyFields2.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult2 = Schema.builder().rowKeyFields(rowKeyFields2);
    Builder sortKeyFieldsResult2 = rowKeyFieldsResult2.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult2.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals("AAAAAAAA", base64ResultsBatchSerialiser.serialise(new ResultsBatch("", schema2, new ArrayList<>())));
  }

  /**
   * Test {@link Base64ResultsBatchSerialiser#serialise(ResultsBatch)}.
   * <ul>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Base64ResultsBatchSerialiser#serialise(ResultsBatch)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch); then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Base64ResultsBatchSerialiser.serialise(ResultsBatch)"})
  void testSerialise_thenThrowUncheckedIOException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Base64ResultsBatchSerialiser base64ResultsBatchSerialiser = new Base64ResultsBatchSerialiser(schema);

    ArrayList<Field> rowKeyFields2 = new ArrayList<>();
    rowKeyFields2.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult2 = Schema.builder().rowKeyFields(rowKeyFields2);
    Builder sortKeyFieldsResult2 = rowKeyFieldsResult2.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult2.valueFields(new ArrayList<>()).build();

    ArrayList<Record> records = new ArrayList<>();
    records.add(new Record());

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> base64ResultsBatchSerialiser.serialise(new ResultsBatch("42", schema2, records)));
  }

  /**
   * Test {@link Base64ResultsBatchSerialiser#deserialise(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Base64ResultsBatchSerialiser#deserialise(String)}
   */
  @Test
  @DisplayName("Test deserialise(String); when '42'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsBatch Base64ResultsBatchSerialiser.deserialise(String)"})
  void testDeserialise_when42_thenThrowUncheckedIOException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(UncheckedIOException.class, () -> (new Base64ResultsBatchSerialiser(schema)).deserialise("42"));
  }

  /**
   * Test {@link Base64ResultsBatchSerialiser#deserialise(String)}.
   * <ul>
   *   <li>When {@code 4242}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Base64ResultsBatchSerialiser#deserialise(String)}
   */
  @Test
  @DisplayName("Test deserialise(String); when '4242'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsBatch Base64ResultsBatchSerialiser.deserialise(String)"})
  void testDeserialise_when4242_thenThrowUncheckedIOException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(UncheckedIOException.class, () -> (new Base64ResultsBatchSerialiser(schema)).deserialise("4242"));
  }

  /**
   * Test {@link Base64ResultsBatchSerialiser#deserialise(String)}.
   * <ul>
   *   <li>When {@code Serialised Records42}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Base64ResultsBatchSerialiser#deserialise(String)}
   */
  @Test
  @DisplayName("Test deserialise(String); when 'Serialised Records42'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsBatch Base64ResultsBatchSerialiser.deserialise(String)"})
  void testDeserialise_whenSerialisedRecords42_thenThrowUncheckedIOException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> (new Base64ResultsBatchSerialiser(schema)).deserialise("Serialised Records42"));
  }

  /**
   * Test {@link Base64ResultsBatchSerialiser#deserialise(String)}.
   * <ul>
   *   <li>When {@code Serialised Records}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Base64ResultsBatchSerialiser#deserialise(String)}
   */
  @Test
  @DisplayName("Test deserialise(String); when 'Serialised Records'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsBatch Base64ResultsBatchSerialiser.deserialise(String)"})
  void testDeserialise_whenSerialisedRecords_thenThrowUncheckedIOException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> (new Base64ResultsBatchSerialiser(schema)).deserialise("Serialised Records"));
  }
}
