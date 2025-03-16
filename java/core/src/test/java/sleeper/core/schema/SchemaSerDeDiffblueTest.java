package sleeper.core.schema;

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
import com.google.gson.internal.LazilyParsedNumber;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.SchemaSerDe.AbstractTypeJsonDeserializer;
import sleeper.core.schema.SchemaSerDe.AbstractTypeJsonSerializer;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.ListType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.MapType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;

class SchemaSerDeDiffblueTest {
  /**
   * Test AbstractTypeJsonDeserializer {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Given null.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonDeserializer deserialize(JsonElement, Type, JsonDeserializationContext); given null")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.schema.type.Type AbstractTypeJsonDeserializer.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testAbstractTypeJsonDeserializerDeserialize_givenNull() throws JsonParseException {
    // Arrange
    AbstractTypeJsonDeserializer abstractTypeJsonDeserializer = new AbstractTypeJsonDeserializer();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add('\u0000');
    jsonElement.add(true);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> abstractTypeJsonDeserializer.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test AbstractTypeJsonDeserializer {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonDeserializer deserialize(JsonElement, Type, JsonDeserializationContext); given 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.schema.type.Type AbstractTypeJsonDeserializer.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testAbstractTypeJsonDeserializerDeserialize_givenTrue() throws JsonParseException {
    // Arrange
    AbstractTypeJsonDeserializer abstractTypeJsonDeserializer = new AbstractTypeJsonDeserializer();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(true);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> abstractTypeJsonDeserializer.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test AbstractTypeJsonDeserializer {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Given valueOf one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonDeserializer deserialize(JsonElement, Type, JsonDeserializationContext); given valueOf one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.schema.type.Type AbstractTypeJsonDeserializer.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testAbstractTypeJsonDeserializerDeserialize_givenValueOfOne() throws JsonParseException {
    // Arrange
    AbstractTypeJsonDeserializer abstractTypeJsonDeserializer = new AbstractTypeJsonDeserializer();

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(Integer.valueOf(1));
    jsonElement.add(true);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> abstractTypeJsonDeserializer.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test AbstractTypeJsonDeserializer {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Then return {@link ByteArrayType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonDeserializer deserialize(JsonElement, Type, JsonDeserializationContext); then return ByteArrayType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.schema.type.Type AbstractTypeJsonDeserializer.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testAbstractTypeJsonDeserializerDeserialize_thenReturnByteArrayType() throws JsonParseException {
    // Arrange
    AbstractTypeJsonDeserializer abstractTypeJsonDeserializer = new AbstractTypeJsonDeserializer();
    JsonPrimitive jsonElement = new JsonPrimitive("ByteArrayType");

    // Act and Assert
    assertTrue(abstractTypeJsonDeserializer.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)) instanceof ByteArrayType);
  }

  /**
   * Test AbstractTypeJsonDeserializer {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Then return {@link IntType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonDeserializer deserialize(JsonElement, Type, JsonDeserializationContext); then return IntType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.schema.type.Type AbstractTypeJsonDeserializer.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testAbstractTypeJsonDeserializerDeserialize_thenReturnIntType() throws JsonParseException {
    // Arrange
    AbstractTypeJsonDeserializer abstractTypeJsonDeserializer = new AbstractTypeJsonDeserializer();
    JsonPrimitive jsonElement = new JsonPrimitive("IntType");

    // Act and Assert
    assertTrue(abstractTypeJsonDeserializer.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)) instanceof IntType);
  }

  /**
   * Test AbstractTypeJsonDeserializer {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Then return {@link LongType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonDeserializer deserialize(JsonElement, Type, JsonDeserializationContext); then return LongType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.schema.type.Type AbstractTypeJsonDeserializer.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testAbstractTypeJsonDeserializerDeserialize_thenReturnLongType() throws JsonParseException {
    // Arrange
    AbstractTypeJsonDeserializer abstractTypeJsonDeserializer = new AbstractTypeJsonDeserializer();
    JsonPrimitive jsonElement = new JsonPrimitive("LongType");

    // Act and Assert
    assertTrue(abstractTypeJsonDeserializer.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)) instanceof LongType);
  }

  /**
   * Test AbstractTypeJsonDeserializer {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Then return {@link StringType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonDeserializer deserialize(JsonElement, Type, JsonDeserializationContext); then return StringType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.schema.type.Type AbstractTypeJsonDeserializer.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testAbstractTypeJsonDeserializerDeserialize_thenReturnStringType() throws JsonParseException {
    // Arrange
    AbstractTypeJsonDeserializer abstractTypeJsonDeserializer = new AbstractTypeJsonDeserializer();
    JsonPrimitive jsonElement = new JsonPrimitive("StringType");

    // Act and Assert
    assertTrue(abstractTypeJsonDeserializer.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)) instanceof StringType);
  }

  /**
   * Test AbstractTypeJsonDeserializer {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonDeserializer deserialize(JsonElement, Type, JsonDeserializationContext); when JsonArray(int) with capacity is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.schema.type.Type AbstractTypeJsonDeserializer.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testAbstractTypeJsonDeserializerDeserialize_whenJsonArrayWithCapacityIsThree() throws JsonParseException {
    // Arrange
    AbstractTypeJsonDeserializer abstractTypeJsonDeserializer = new AbstractTypeJsonDeserializer();
    JsonArray jsonElement = new JsonArray(3);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> abstractTypeJsonDeserializer.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test AbstractTypeJsonDeserializer {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link AbstractTypeJsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonDeserializer deserialize(JsonElement, Type, JsonDeserializationContext); when JsonObject (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.schema.type.Type AbstractTypeJsonDeserializer.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testAbstractTypeJsonDeserializerDeserialize_whenJsonObject() throws JsonParseException {
    // Arrange
    AbstractTypeJsonDeserializer abstractTypeJsonDeserializer = new AbstractTypeJsonDeserializer();
    JsonObject jsonElement = new JsonObject();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> abstractTypeJsonDeserializer.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test AbstractTypeJsonSerializer {@link AbstractTypeJsonSerializer#serialize(Type, Type, JsonSerializationContext)} with {@code Type}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link AbstractTypeJsonSerializer#serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonSerializer serialize(Type, Type, JsonSerializationContext) with 'Type', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement AbstractTypeJsonSerializer.serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)"})
  void testAbstractTypeJsonSerializerSerializeWithTypeTypeJsonSerializationContext() {
    // Arrange
    AbstractTypeJsonSerializer abstractTypeJsonSerializer = new AbstractTypeJsonSerializer();
    ByteArrayType type = new ByteArrayType();

    // Act
    JsonElement actualSerializeResult = abstractTypeJsonSerializer.serialize(type, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonPrimitive);
    Number asNumber = actualSerializeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("ByteArrayType", actualSerializeResult.getAsString());
    assertEquals("ByteArrayType", asNumber.toString());
    assertEquals('B', actualSerializeResult.getAsCharacter());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonPrimitive());
  }

  /**
   * Test AbstractTypeJsonSerializer {@link AbstractTypeJsonSerializer#serialize(Type, Type, JsonSerializationContext)} with {@code Type}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link AbstractTypeJsonSerializer#serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonSerializer serialize(Type, Type, JsonSerializationContext) with 'Type', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement AbstractTypeJsonSerializer.serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)"})
  void testAbstractTypeJsonSerializerSerializeWithTypeTypeJsonSerializationContext2() {
    // Arrange
    AbstractTypeJsonSerializer abstractTypeJsonSerializer = new AbstractTypeJsonSerializer();
    IntType type = new IntType();

    // Act
    JsonElement actualSerializeResult = abstractTypeJsonSerializer.serialize(type, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonPrimitive);
    Number asNumber = actualSerializeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("IntType", actualSerializeResult.getAsString());
    assertEquals("IntType", asNumber.toString());
    assertEquals('I', actualSerializeResult.getAsCharacter());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonPrimitive());
  }

  /**
   * Test AbstractTypeJsonSerializer {@link AbstractTypeJsonSerializer#serialize(Type, Type, JsonSerializationContext)} with {@code Type}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link AbstractTypeJsonSerializer#serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonSerializer serialize(Type, Type, JsonSerializationContext) with 'Type', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement AbstractTypeJsonSerializer.serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)"})
  void testAbstractTypeJsonSerializerSerializeWithTypeTypeJsonSerializationContext3() {
    // Arrange
    AbstractTypeJsonSerializer abstractTypeJsonSerializer = new AbstractTypeJsonSerializer();
    LongType type = new LongType();

    // Act
    JsonElement actualSerializeResult = abstractTypeJsonSerializer.serialize(type, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonPrimitive);
    Number asNumber = actualSerializeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("LongType", actualSerializeResult.getAsString());
    assertEquals("LongType", asNumber.toString());
    assertEquals('L', actualSerializeResult.getAsCharacter());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonPrimitive());
  }

  /**
   * Test AbstractTypeJsonSerializer {@link AbstractTypeJsonSerializer#serialize(Type, Type, JsonSerializationContext)} with {@code Type}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link AbstractTypeJsonSerializer#serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonSerializer serialize(Type, Type, JsonSerializationContext) with 'Type', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement AbstractTypeJsonSerializer.serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)"})
  void testAbstractTypeJsonSerializerSerializeWithTypeTypeJsonSerializationContext4() {
    // Arrange
    AbstractTypeJsonSerializer abstractTypeJsonSerializer = new AbstractTypeJsonSerializer();
    StringType type = new StringType();

    // Act
    JsonElement actualSerializeResult = abstractTypeJsonSerializer.serialize(type, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonPrimitive);
    Number asNumber = actualSerializeResult.getAsNumber();
    assertTrue(asNumber instanceof LazilyParsedNumber);
    assertEquals("StringType", actualSerializeResult.getAsString());
    assertEquals("StringType", asNumber.toString());
    assertEquals('S', actualSerializeResult.getAsCharacter());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonPrimitive());
  }

  /**
   * Test AbstractTypeJsonSerializer {@link AbstractTypeJsonSerializer#serialize(Type, Type, JsonSerializationContext)} with {@code Type}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link AbstractTypeJsonSerializer#serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonSerializer serialize(Type, Type, JsonSerializationContext) with 'Type', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement AbstractTypeJsonSerializer.serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)"})
  void testAbstractTypeJsonSerializerSerializeWithTypeTypeJsonSerializationContext5() {
    // Arrange
    AbstractTypeJsonSerializer abstractTypeJsonSerializer = new AbstractTypeJsonSerializer();
    PrimitiveType keyType = new PrimitiveType();
    MapType type = new MapType(keyType, new PrimitiveType());

    // Act
    JsonElement actualSerializeResult = abstractTypeJsonSerializer.serialize(type, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test AbstractTypeJsonSerializer {@link AbstractTypeJsonSerializer#serialize(Type, Type, JsonSerializationContext)} with {@code Type}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link AbstractTypeJsonSerializer#serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonSerializer serialize(Type, Type, JsonSerializationContext) with 'Type', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement AbstractTypeJsonSerializer.serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)"})
  void testAbstractTypeJsonSerializerSerializeWithTypeTypeJsonSerializationContext6() {
    // Arrange
    AbstractTypeJsonSerializer abstractTypeJsonSerializer = new AbstractTypeJsonSerializer();
    ListType type = new ListType(new PrimitiveType());

    // Act
    JsonElement actualSerializeResult = abstractTypeJsonSerializer.serialize(type, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test AbstractTypeJsonSerializer {@link AbstractTypeJsonSerializer#serialize(Type, Type, JsonSerializationContext)} with {@code Type}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link AbstractTypeJsonSerializer#serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test AbstractTypeJsonSerializer serialize(Type, Type, JsonSerializationContext) with 'Type', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement AbstractTypeJsonSerializer.serialize(sleeper.core.schema.type.Type, Type, JsonSerializationContext)"})
  void testAbstractTypeJsonSerializerSerializeWithTypeTypeJsonSerializationContext7() {
    // Arrange
    AbstractTypeJsonSerializer abstractTypeJsonSerializer = new AbstractTypeJsonSerializer();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> abstractTypeJsonSerializer.serialize(null,
        new PlaceholderForType(1), mock(JsonSerializationContext.class)));
  }

  /**
   * Test new {@link SchemaSerDe} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SchemaSerDe}
   */
  @Test
  @DisplayName("Test new SchemaSerDe (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SchemaSerDe.<init>()"})
  void testNewSchemaSerDe() {
    // Arrange, Act and Assert
    assertEquals("null", (new SchemaSerDe()).toJson(null));
  }

  /**
   * Test {@link SchemaSerDe#toJson(Schema)} with {@code schema}.
   * <p>
   * Method under test: {@link SchemaSerDe#toJson(Schema)}
   */
  @Test
  @DisplayName("Test toJson(Schema) with 'schema'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SchemaSerDe.toJson(Schema)"})
  void testToJsonWithSchema() {
    // Arrange
    SchemaSerDe schemaSerDe = new SchemaSerDe();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\"rowKeyFields\":[{\"name\":\"Name\",\"type\":\"ByteArrayType\"}],\"sortKeyFields\":[],\"valueFields\":[]}",
        schemaSerDe.toJson(schema));
  }

  /**
   * Test {@link SchemaSerDe#toJson(Schema)} with {@code schema}.
   * <p>
   * Method under test: {@link SchemaSerDe#toJson(Schema)}
   */
  @Test
  @DisplayName("Test toJson(Schema) with 'schema'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SchemaSerDe.toJson(Schema)"})
  void testToJsonWithSchema2() {
    // Arrange
    SchemaSerDe schemaSerDe = new SchemaSerDe();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\"rowKeyFields\":[{\"name\":\"\",\"type\":\"ByteArrayType\"}],\"sortKeyFields\":[],\"valueFields\":[]}",
        schemaSerDe.toJson(schema));
  }

  /**
   * Test {@link SchemaSerDe#toJson(Schema, boolean)} with {@code schema}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link SchemaSerDe#toJson(Schema, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Schema, boolean) with 'schema', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SchemaSerDe.toJson(Schema, boolean)"})
  void testToJsonWithSchemaPrettyPrint() {
    // Arrange
    SchemaSerDe schemaSerDe = new SchemaSerDe();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\"rowKeyFields\":[{\"name\":\"Name\",\"type\":\"ByteArrayType\"}],\"sortKeyFields\":[],\"valueFields\":[]}",
        schemaSerDe.toJson(schema, false));
  }

  /**
   * Test {@link SchemaSerDe#toJson(Schema, boolean)} with {@code schema}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link SchemaSerDe#toJson(Schema, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Schema, boolean) with 'schema', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SchemaSerDe.toJson(Schema, boolean)"})
  void testToJsonWithSchemaPrettyPrint2() {
    // Arrange
    SchemaSerDe schemaSerDe = new SchemaSerDe();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\"rowKeyFields\":[{\"name\":\"\",\"type\":\"ByteArrayType\"}],\"sortKeyFields\":[],\"valueFields\":[]}",
        schemaSerDe.toJson(schema, false));
  }

  /**
   * Test {@link SchemaSerDe#toJson(Schema, boolean)} with {@code schema}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaSerDe#toJson(Schema, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Schema, boolean) with 'schema', 'prettyPrint'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SchemaSerDe.toJson(Schema, boolean)"})
  void testToJsonWithSchemaPrettyPrint_thenReturnAString() {
    // Arrange
    SchemaSerDe schemaSerDe = new SchemaSerDe();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("ByteArrayType", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\"rowKeyFields\":[{\"name\":\"ByteArrayType\",\"type\":\"ByteArrayType\"},{\"name\":\"Name\",\"type\":\"ByteArrayType"
            + "\"}],\"sortKeyFields\":[],\"valueFields\":[]}",
        schemaSerDe.toJson(schema, false));
  }

  /**
   * Test {@link SchemaSerDe#toJson(Schema, boolean)} with {@code schema}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaSerDe#toJson(Schema, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Schema, boolean) with 'schema', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SchemaSerDe.toJson(Schema, boolean)"})
  void testToJsonWithSchemaPrettyPrint_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new SchemaSerDe()).toJson(null, true));
  }

  /**
   * Test {@link SchemaSerDe#toJson(Schema, boolean)} with {@code schema}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaSerDe#toJson(Schema, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Schema, boolean) with 'schema', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SchemaSerDe.toJson(Schema, boolean)"})
  void testToJsonWithSchemaPrettyPrint_whenNull_thenReturnNull2() {
    // Arrange, Act and Assert
    assertEquals("null", (new SchemaSerDe()).toJson(null, false));
  }

  /**
   * Test {@link SchemaSerDe#toJson(Schema)} with {@code schema}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaSerDe#toJson(Schema)}
   */
  @Test
  @DisplayName("Test toJson(Schema) with 'schema'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SchemaSerDe.toJson(Schema)"})
  void testToJsonWithSchema_thenReturnAString() {
    // Arrange
    SchemaSerDe schemaSerDe = new SchemaSerDe();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("ByteArrayType", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(
        "{\"rowKeyFields\":[{\"name\":\"ByteArrayType\",\"type\":\"ByteArrayType\"},{\"name\":\"Name\",\"type\":\"ByteArrayType"
            + "\"}],\"sortKeyFields\":[],\"valueFields\":[]}",
        schemaSerDe.toJson(schema));
  }

  /**
   * Test {@link SchemaSerDe#toJson(Schema)} with {@code schema}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaSerDe#toJson(Schema)}
   */
  @Test
  @DisplayName("Test toJson(Schema) with 'schema'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SchemaSerDe.toJson(Schema)"})
  void testToJsonWithSchema_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new SchemaSerDe()).toJson(null));
  }

  /**
   * Test {@link SchemaSerDe#fromJson(InputStream)} with {@code inputStream}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaSerDe#fromJson(InputStream)}
   */
  @Test
  @DisplayName("Test fromJson(InputStream) with 'inputStream'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Schema SchemaSerDe.fromJson(InputStream)"})
  void testFromJsonWithInputStream_thenReturnNull() {
    // Arrange
    SchemaSerDe schemaSerDe = new SchemaSerDe();

    // Act and Assert
    assertNull(schemaSerDe.fromJson(new ByteArrayInputStream(new byte[]{})));
  }
}
