package sleeper.core.record.serialiser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
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

class JSONResultsBatchSerialiserDiffblueTest {
  /**
   * Test new {@link JSONResultsBatchSerialiser} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link JSONResultsBatchSerialiser}
   */
  @Test
  @DisplayName("Test new JSONResultsBatchSerialiser (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JSONResultsBatchSerialiser.<init>()"})
  void testNewJSONResultsBatchSerialiser() {
    // Arrange, Act and Assert
    assertEquals("null", (new JSONResultsBatchSerialiser()).serialise(null));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch)} with {@code resultsBatch}.
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch) with 'resultsBatch'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch)"})
  void testSerialiseWithResultsBatch() {
    // Arrange
    JSONResultsBatchSerialiser jsonResultsBatchSerialiser = new JSONResultsBatchSerialiser();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\"queryId\":\"42\",\"schema\":{\"rowKeyFields\":[{\"name\":\"\",\"type\":\"ByteArrayType\"}],\"sortKeyFields\":[],"
            + "\"valueFields\":[]},\"records\":[]}",
        jsonResultsBatchSerialiser.serialise(new ResultsBatch("42", schema, new ArrayList<>())));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)} with {@code resultsBatch}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch, boolean) with 'resultsBatch', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch, boolean)"})
  void testSerialiseWithResultsBatchPrettyPrint() {
    // Arrange
    JSONResultsBatchSerialiser jsonResultsBatchSerialiser = new JSONResultsBatchSerialiser();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"queryId\": \"42\",\n" + "  \"schema\": {\n" + "    \"rowKeyFields\": [\n" + "      {\n"
            + "        \"name\": \"Name\",\n" + "        \"type\": \"ByteArrayType\"\n" + "      }\n" + "    ],\n"
            + "    \"sortKeyFields\": [],\n" + "    \"valueFields\": []\n" + "  },\n" + "  \"records\": []\n" + "}",
        jsonResultsBatchSerialiser.serialise(new ResultsBatch("42", schema, new ArrayList<>()), true));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)} with {@code resultsBatch}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch, boolean) with 'resultsBatch', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch, boolean)"})
  void testSerialiseWithResultsBatchPrettyPrint2() {
    // Arrange
    JSONResultsBatchSerialiser jsonResultsBatchSerialiser = new JSONResultsBatchSerialiser();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("queryId", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"queryId\": \"42\",\n" + "  \"schema\": {\n" + "    \"rowKeyFields\": [\n" + "      {\n"
            + "        \"name\": \"queryId\",\n" + "        \"type\": \"ByteArrayType\"\n" + "      },\n" + "      {\n"
            + "        \"name\": \"Name\",\n" + "        \"type\": \"ByteArrayType\"\n" + "      }\n" + "    ],\n"
            + "    \"sortKeyFields\": [],\n" + "    \"valueFields\": []\n" + "  },\n" + "  \"records\": []\n" + "}",
        jsonResultsBatchSerialiser.serialise(new ResultsBatch("42", schema, new ArrayList<>()), true));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)} with {@code resultsBatch}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch, boolean) with 'resultsBatch', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch, boolean)"})
  void testSerialiseWithResultsBatchPrettyPrint3() {
    // Arrange
    JSONResultsBatchSerialiser jsonResultsBatchSerialiser = new JSONResultsBatchSerialiser();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"queryId\": \"42\",\n" + "  \"schema\": {\n" + "    \"rowKeyFields\": [\n" + "      {\n"
            + "        \"name\": \"\",\n" + "        \"type\": \"ByteArrayType\"\n" + "      }\n" + "    ],\n"
            + "    \"sortKeyFields\": [],\n" + "    \"valueFields\": []\n" + "  },\n" + "  \"records\": []\n" + "}",
        jsonResultsBatchSerialiser.serialise(new ResultsBatch("42", schema, new ArrayList<>()), true));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)} with {@code resultsBatch}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link Record#Record()}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link Record#Record()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch, boolean) with 'resultsBatch', 'prettyPrint'; given Record(); when ArrayList() add Record()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch, boolean)"})
  void testSerialiseWithResultsBatchPrettyPrint_givenRecord_whenArrayListAddRecord() {
    // Arrange
    JSONResultsBatchSerialiser jsonResultsBatchSerialiser = new JSONResultsBatchSerialiser();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Record> records = new ArrayList<>();
    records.add(new Record());

    // Act and Assert
    assertEquals(
        "{\n" + "  \"queryId\": \"42\",\n" + "  \"schema\": {\n" + "    \"rowKeyFields\": [\n" + "      {\n"
            + "        \"name\": \"Name\",\n" + "        \"type\": \"ByteArrayType\"\n" + "      }\n" + "    ],\n"
            + "    \"sortKeyFields\": [],\n" + "    \"valueFields\": []\n" + "  },\n" + "  \"records\": [\n" + "    {\n"
            + "      \"Name\": null\n" + "    }\n" + "  ]\n" + "}",
        jsonResultsBatchSerialiser.serialise(new ResultsBatch("42", schema, records), true));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)} with {@code resultsBatch}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch, boolean) with 'resultsBatch', 'prettyPrint'; when 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch, boolean)"})
  void testSerialiseWithResultsBatchPrettyPrint_whenFalse() {
    // Arrange
    JSONResultsBatchSerialiser jsonResultsBatchSerialiser = new JSONResultsBatchSerialiser();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\"queryId\":\"42\",\"schema\":{\"rowKeyFields\":[{\"name\":\"Name\",\"type\":\"ByteArrayType\"}],\"sortKeyFields\":[]"
            + ",\"valueFields\":[]},\"records\":[]}",
        jsonResultsBatchSerialiser.serialise(new ResultsBatch("42", schema, new ArrayList<>()), false));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)} with {@code resultsBatch}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch, boolean)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch, boolean) with 'resultsBatch', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch, boolean)"})
  void testSerialiseWithResultsBatchPrettyPrint_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new JSONResultsBatchSerialiser()).serialise(null, true));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch)} with {@code resultsBatch}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch) with 'resultsBatch'; given Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch)"})
  void testSerialiseWithResultsBatch_givenFieldWithNameAndTypeIsByteArrayType() {
    // Arrange
    JSONResultsBatchSerialiser jsonResultsBatchSerialiser = new JSONResultsBatchSerialiser();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\"queryId\":\"42\",\"schema\":{\"rowKeyFields\":[{\"name\":\"Name\",\"type\":\"ByteArrayType\"}],\"sortKeyFields\":[]"
            + ",\"valueFields\":[]},\"records\":[]}",
        jsonResultsBatchSerialiser.serialise(new ResultsBatch("42", schema, new ArrayList<>())));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch)} with {@code resultsBatch}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with name is {@code queryId} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch) with 'resultsBatch'; given Field(String, Type) with name is 'queryId' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch)"})
  void testSerialiseWithResultsBatch_givenFieldWithNameIsQueryIdAndTypeIsByteArrayType() {
    // Arrange
    JSONResultsBatchSerialiser jsonResultsBatchSerialiser = new JSONResultsBatchSerialiser();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("queryId", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\"queryId\":\"42\",\"schema\":{\"rowKeyFields\":[{\"name\":\"queryId\",\"type\":\"ByteArrayType\"},{\"name\":\"Name\","
            + "\"type\":\"ByteArrayType\"}],\"sortKeyFields\":[],\"valueFields\":[]},\"records\":[]}",
        jsonResultsBatchSerialiser.serialise(new ResultsBatch("42", schema, new ArrayList<>())));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch)} with {@code resultsBatch}.
   * <ul>
   *   <li>Given {@link Record#Record()}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link Record#Record()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch) with 'resultsBatch'; given Record(); when ArrayList() add Record()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch)"})
  void testSerialiseWithResultsBatch_givenRecord_whenArrayListAddRecord() {
    // Arrange
    JSONResultsBatchSerialiser jsonResultsBatchSerialiser = new JSONResultsBatchSerialiser();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<Record> records = new ArrayList<>();
    records.add(new Record());

    // Act and Assert
    assertEquals(
        "{\"queryId\":\"42\",\"schema\":{\"rowKeyFields\":[{\"name\":\"Name\",\"type\":\"ByteArrayType\"}],\"sortKeyFields\":[]"
            + ",\"valueFields\":[]},\"records\":[{\"Name\":null}]}",
        jsonResultsBatchSerialiser.serialise(new ResultsBatch("42", schema, records)));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#serialise(ResultsBatch)} with {@code resultsBatch}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#serialise(ResultsBatch)}
   */
  @Test
  @DisplayName("Test serialise(ResultsBatch) with 'resultsBatch'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JSONResultsBatchSerialiser.serialise(ResultsBatch)"})
  void testSerialiseWithResultsBatch_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new JSONResultsBatchSerialiser()).serialise(null));
  }

  /**
   * Test {@link JSONResultsBatchSerialiser#deserialise(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JSONResultsBatchSerialiser#deserialise(String)}
   */
  @Test
  @DisplayName("Test deserialise(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsBatch JSONResultsBatchSerialiser.deserialise(String)"})
  void testDeserialise_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new JSONResultsBatchSerialiser()).deserialise(""));
  }
}
