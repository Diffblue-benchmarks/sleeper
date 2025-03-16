package sleeper.core.record.serialiser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.record.Record;
import sleeper.core.record.serialiser.RecordJSONSerDe.RecordGsonSerialiser;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;

class RecordJSONSerDeDiffblueTest {
  /**
   * Test {@link RecordJSONSerDe#RecordJSONSerDe(Schema)}.
   * <p>
   * Method under test: {@link RecordJSONSerDe#RecordJSONSerDe(Schema)}
   */
  @Test
  @DisplayName("Test new RecordJSONSerDe(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordJSONSerDe.<init>(Schema)"})
  void testNewRecordJSONSerDe() {
    // Arrange, Act and Assert
    assertEquals("null", (new RecordJSONSerDe(mock(Schema.class))).toJson(null));
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordGsonSerialiser#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser deserialize(JsonElement, Type, JsonDeserializationContext); given 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record RecordGsonSerialiser.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRecordGsonSerialiserDeserialize_givenFalse() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(false);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> recordGsonSerialiser.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Given valueOf one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordGsonSerialiser#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser deserialize(JsonElement, Type, JsonDeserializationContext); given valueOf one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record RecordGsonSerialiser.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRecordGsonSerialiserDeserialize_givenValueOfOne() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(Integer.valueOf(1));

    // Act and Assert
    assertThrows(JsonParseException.class, () -> recordGsonSerialiser.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordGsonSerialiser#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser deserialize(JsonElement, Type, JsonDeserializationContext); when JsonArray(int) with capacity is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record RecordGsonSerialiser.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRecordGsonSerialiserDeserialize_whenJsonArrayWithCapacityIsThree() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    JsonArray jsonElement = new JsonArray(3);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> recordGsonSerialiser.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordGsonSerialiser#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser deserialize(JsonElement, Type, JsonDeserializationContext); when JsonPrimitive(Boolean) with bool is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record RecordGsonSerialiser.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRecordGsonSerialiserDeserialize_whenJsonPrimitiveWithBoolIsTrue() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    JsonPrimitive jsonElement = new JsonPrimitive(true);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> recordGsonSerialiser.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordGsonSerialiser#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser deserialize(JsonElement, Type, JsonDeserializationContext); when JsonPrimitive(String) with 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record RecordGsonSerialiser.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRecordGsonSerialiserDeserialize_whenJsonPrimitiveWithString() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    JsonPrimitive jsonElement = new JsonPrimitive("String");

    // Act and Assert
    assertThrows(JsonParseException.class, () -> recordGsonSerialiser.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)} with {@code Record}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser serialize(Record, Type, JsonSerializationContext) with 'Record', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RecordGsonSerialiser.serialize(Record, Type, JsonSerializationContext)"})
  void testRecordGsonSerialiserSerializeWithRecordTypeJsonSerializationContext() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    Record resultRecord = new Record();

    // Act
    JsonElement actualSerializeResult = recordGsonSerialiser.serialize(resultRecord, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)} with {@code Record}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser serialize(Record, Type, JsonSerializationContext) with 'Record', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RecordGsonSerialiser.serialize(Record, Type, JsonSerializationContext)"})
  void testRecordGsonSerialiserSerializeWithRecordTypeJsonSerializationContext2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("java.lang.String", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    Record resultRecord = new Record();

    // Act
    JsonElement actualSerializeResult = recordGsonSerialiser.serialize(resultRecord, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)} with {@code Record}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser serialize(Record, Type, JsonSerializationContext) with 'Record', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RecordGsonSerialiser.serialize(Record, Type, JsonSerializationContext)"})
  void testRecordGsonSerialiserSerializeWithRecordTypeJsonSerializationContext3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("42", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    Record resultRecord = new Record();

    // Act
    JsonElement actualSerializeResult = recordGsonSerialiser.serialize(resultRecord, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)} with {@code Record}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser serialize(Record, Type, JsonSerializationContext) with 'Record', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RecordGsonSerialiser.serialize(Record, Type, JsonSerializationContext)"})
  void testRecordGsonSerialiserSerializeWithRecordTypeJsonSerializationContext4() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    Record resultRecord = new Record();

    // Act
    JsonElement actualSerializeResult = recordGsonSerialiser.serialize(resultRecord, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)} with {@code Record}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser serialize(Record, Type, JsonSerializationContext) with 'Record', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RecordGsonSerialiser.serialize(Record, Type, JsonSerializationContext)"})
  void testRecordGsonSerialiserSerializeWithRecordTypeJsonSerializationContext5() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    Record resultRecord = new Record();

    // Act
    JsonElement actualSerializeResult = recordGsonSerialiser.serialize(resultRecord, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)} with {@code Record}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser serialize(Record, Type, JsonSerializationContext) with 'Record', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RecordGsonSerialiser.serialize(Record, Type, JsonSerializationContext)"})
  void testRecordGsonSerialiserSerializeWithRecordTypeJsonSerializationContext6() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    Record resultRecord = new Record();

    // Act
    JsonElement actualSerializeResult = recordGsonSerialiser.serialize(resultRecord, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)} with {@code Record}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser serialize(Record, Type, JsonSerializationContext) with 'Record', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RecordGsonSerialiser.serialize(Record, Type, JsonSerializationContext)"})
  void testRecordGsonSerialiserSerializeWithRecordTypeJsonSerializationContext7() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("42", new ByteArrayType()));
    rowKeyFields.add(new Field("java.lang.String", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    Record resultRecord = new Record();

    // Act
    JsonElement actualSerializeResult = recordGsonSerialiser.serialize(resultRecord, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(3, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)} with {@code Record}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser serialize(Record, Type, JsonSerializationContext) with 'Record', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RecordGsonSerialiser.serialize(Record, Type, JsonSerializationContext)"})
  void testRecordGsonSerialiserSerializeWithRecordTypeJsonSerializationContext8() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("sleeper.core.schema.Field", new ByteArrayType()));
    rowKeyFields.add(new Field("java.lang.String", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    Record resultRecord = new Record();

    // Act
    JsonElement actualSerializeResult = recordGsonSerialiser.serialize(resultRecord, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(3, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)} with {@code Record}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser serialize(Record, Type, JsonSerializationContext) with 'Record', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RecordGsonSerialiser.serialize(Record, Type, JsonSerializationContext)"})
  void testRecordGsonSerialiserSerializeWithRecordTypeJsonSerializationContext9() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("java.lang.String", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);

    ArrayList<Field> sortKeyFields = new ArrayList<>();
    sortKeyFields.add(new Field("sleeper.core.schema.Field", new ByteArrayType()));
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(sortKeyFields);
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);
    Record resultRecord = new Record();

    // Act
    JsonElement actualSerializeResult = recordGsonSerialiser.serialize(resultRecord, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(3, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test RecordGsonSerialiser {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)} with {@code Record}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RecordGsonSerialiser#serialize(Record, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RecordGsonSerialiser serialize(Record, Type, JsonSerializationContext) with 'Record', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RecordGsonSerialiser.serialize(Record, Type, JsonSerializationContext)"})
  void testRecordGsonSerialiserSerializeWithRecordTypeJsonSerializationContext10() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("java.lang.String", new StringType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordGsonSerialiser recordGsonSerialiser = new RecordGsonSerialiser(schema);

    Record resultRecord = new Record();
    resultRecord.put("java.lang.String", "Value");

    // Act
    JsonElement actualSerializeResult = recordGsonSerialiser.serialize(resultRecord, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\n  \"Name\": null\n}", recordJSONSerDe.toJson(new Record(), true));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'; given ArrayList() add Field(String, Type) with 'Name' and type is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint_givenArrayListAddFieldWithNameAndTypeIsIntType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\n  \"Name\": null\n}", recordJSONSerDe.toJson(new Record(), true));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'; given ArrayList() add Field(String, Type) with 'Name' and type is LongType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint_givenArrayListAddFieldWithNameAndTypeIsLongType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\n  \"Name\": null\n}", recordJSONSerDe.toJson(new Record(), true));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return {@code { "42": null, "Name": null }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'; then return '{ \"42\": null, \"Name\": null }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint_thenReturn42NullNameNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("42", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\n  \"42\": null,\n  \"Name\": null\n}", recordJSONSerDe.toJson(new Record(), true));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return {@code { "String": null, "Name": null }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'; then return '{ \"java.lang.String\": null, \"Name\": null }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint_thenReturnJavaLangStringNullNameNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("java.lang.String", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\n  \"java.lang.String\": null,\n  \"Name\": null\n}", recordJSONSerDe.toJson(new Record(), true));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return {@code { "Name": null }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'; then return '{ \"Name\": null }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint_thenReturnNameNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\n  \"Name\": null\n}", recordJSONSerDe.toJson(new Record(), true));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return {@code { "": null }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'; then return '{ \"\": null }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\n  \"\": null\n}", recordJSONSerDe.toJson(new Record(), true));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return {@code { "": "Value" }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'; then return '{ \"\": \"Value\" }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint_thenReturnValue() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    Record resultRecord = new Record();
    resultRecord.put("", "Value");

    // Act and Assert
    assertEquals("{\n  \"\": \"Value\"\n}", recordJSONSerDe.toJson(resultRecord, true));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> recordJSONSerDe.toJson(new Record(), true));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@code {"Name":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'; when 'false'; then return '{\"Name\":null}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint_whenFalse_thenReturnNameNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\"Name\":null}", recordJSONSerDe.toJson(new Record(), false));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record, boolean)} with {@code record}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Record, boolean) with 'record', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record, boolean)"})
  void testToJsonWithRecordPrettyPrint_whenNull_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals("null", (new RecordJSONSerDe(schema)).toJson(null, true));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record)} with {@code record}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record)}
   */
  @Test
  @DisplayName("Test toJson(Record) with 'record'; given ArrayList() add Field(String, Type) with 'Name' and type is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record)"})
  void testToJsonWithRecord_givenArrayListAddFieldWithNameAndTypeIsIntType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\"Name\":null}", recordJSONSerDe.toJson(new Record()));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record)} with {@code record}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record)}
   */
  @Test
  @DisplayName("Test toJson(Record) with 'record'; given ArrayList() add Field(String, Type) with 'Name' and type is LongType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record)"})
  void testToJsonWithRecord_givenArrayListAddFieldWithNameAndTypeIsLongType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\"Name\":null}", recordJSONSerDe.toJson(new Record()));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record)} with {@code record}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record)}
   */
  @Test
  @DisplayName("Test toJson(Record) with 'record'; given ArrayList() add Field(String, Type) with 'Name' and type is StringType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record)"})
  void testToJsonWithRecord_givenArrayListAddFieldWithNameAndTypeIsStringType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\"Name\":null}", recordJSONSerDe.toJson(new Record()));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record)} with {@code record}.
   * <ul>
   *   <li>Then return {@code {"42":null,"Name":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record)}
   */
  @Test
  @DisplayName("Test toJson(Record) with 'record'; then return '{\"42\":null,\"Name\":null}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record)"})
  void testToJsonWithRecord_thenReturn42NullNameNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("42", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\"42\":null,\"Name\":null}", recordJSONSerDe.toJson(new Record()));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record)} with {@code record}.
   * <ul>
   *   <li>Then return {@code {"String":null,"Name":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record)}
   */
  @Test
  @DisplayName("Test toJson(Record) with 'record'; then return '{\"java.lang.String\":null,\"Name\":null}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record)"})
  void testToJsonWithRecord_thenReturnJavaLangStringNullNameNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("java.lang.String", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\"java.lang.String\":null,\"Name\":null}", recordJSONSerDe.toJson(new Record()));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record)} with {@code record}.
   * <ul>
   *   <li>Then return {@code {"Name":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record)}
   */
  @Test
  @DisplayName("Test toJson(Record) with 'record'; then return '{\"Name\":null}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record)"})
  void testToJsonWithRecord_thenReturnNameNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\"Name\":null}", recordJSONSerDe.toJson(new Record()));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record)} with {@code record}.
   * <ul>
   *   <li>Then return {@code {"":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record)}
   */
  @Test
  @DisplayName("Test toJson(Record) with 'record'; then return '{\"\":null}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record)"})
  void testToJsonWithRecord_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertEquals("{\"\":null}", recordJSONSerDe.toJson(new Record()));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record)} with {@code record}.
   * <ul>
   *   <li>Then return {@code {"":"Value"}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record)}
   */
  @Test
  @DisplayName("Test toJson(Record) with 'record'; then return '{\"\":\"Value\"}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record)"})
  void testToJsonWithRecord_thenReturnValue() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    Record resultRecord = new Record();
    resultRecord.put("", "Value");

    // Act and Assert
    assertEquals("{\"\":\"Value\"}", recordJSONSerDe.toJson(resultRecord));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record)} with {@code record}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record)}
   */
  @Test
  @DisplayName("Test toJson(Record) with 'record'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record)"})
  void testToJsonWithRecord_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RecordJSONSerDe recordJSONSerDe = new RecordJSONSerDe(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> recordJSONSerDe.toJson(new Record()));
  }

  /**
   * Test {@link RecordJSONSerDe#toJson(Record)} with {@code record}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#toJson(Record)}
   */
  @Test
  @DisplayName("Test toJson(Record) with 'record'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RecordJSONSerDe.toJson(Record)"})
  void testToJsonWithRecord_whenNull_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals("null", (new RecordJSONSerDe(schema)).toJson(null));
  }

  /**
   * Test {@link RecordJSONSerDe#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordJSONSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record RecordJSONSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertNull((new RecordJSONSerDe(schema)).fromJson(""));
  }
}
