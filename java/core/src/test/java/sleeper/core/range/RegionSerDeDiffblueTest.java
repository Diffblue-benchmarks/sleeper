package sleeper.core.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.range.RegionSerDe.KeyDoesNotExistException;
import sleeper.core.range.RegionSerDe.RegionJsonSerDe;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;

class RegionSerDeDiffblueTest {
  /**
   * Test KeyDoesNotExistException {@link KeyDoesNotExistException#KeyDoesNotExistException(String)}.
   * <p>
   * Method under test: {@link KeyDoesNotExistException#KeyDoesNotExistException(String)}
   */
  @Test
  @DisplayName("Test KeyDoesNotExistException new KeyDoesNotExistException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyDoesNotExistException.<init>(String)"})
  void testKeyDoesNotExistExceptionNewKeyDoesNotExistException() {
    // Arrange and Act
    KeyDoesNotExistException actualKeyDoesNotExistException = new KeyDoesNotExistException("Key Name");

    // Assert
    assertEquals("Key \"Key Name\" was not a row key field in the table schema",
        actualKeyDoesNotExistException.getLocalizedMessage());
    assertEquals("Key \"Key Name\" was not a row key field in the table schema",
        actualKeyDoesNotExistException.getMessage());
    assertNull(actualKeyDoesNotExistException.getCause());
    assertEquals(0, actualKeyDoesNotExistException.getSuppressed().length);
  }

  /**
   * Test {@link RegionSerDe#RegionSerDe(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#RegionSerDe(Schema)}
   */
  @Test
  @DisplayName("Test new RegionSerDe(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RegionSerDe.<init>(Schema)"})
  void testNewRegionSerDe_givenArrayListAddFieldWithNameAndTypeIsByteArrayType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    RegionSerDe actualRegionSerDe = new RegionSerDe(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
    JsonElement toJsonTreeResult = actualRegionSerDe.toJsonTree(null);
    assertTrue(toJsonTreeResult instanceof JsonNull);
    assertEquals("null", actualRegionSerDe.toJson(null));
    assertNull(actualRegionSerDe.fromJsonTree(null));
    assertFalse(toJsonTreeResult.isJsonArray());
    assertFalse(toJsonTreeResult.isJsonObject());
    assertFalse(toJsonTreeResult.isJsonPrimitive());
    assertTrue(toJsonTreeResult.isJsonNull());
    assertSame(toJsonTreeResult, toJsonTreeResult.getAsJsonNull());
  }

  /**
   * Test {@link RegionSerDe#RegionSerDe(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then toJsonTree {@code null} return {@link JsonNull}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#RegionSerDe(Schema)}
   */
  @Test
  @DisplayName("Test new RegionSerDe(Schema); given ArrayList(); then toJsonTree 'null' return JsonNull")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RegionSerDe.<init>(Schema)"})
  void testNewRegionSerDe_givenArrayList_thenToJsonTreeNullReturnJsonNull() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    RegionSerDe actualRegionSerDe = new RegionSerDe(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
    JsonElement toJsonTreeResult = actualRegionSerDe.toJsonTree(null);
    assertTrue(toJsonTreeResult instanceof JsonNull);
    assertEquals("null", actualRegionSerDe.toJson(null));
    assertNull(actualRegionSerDe.fromJsonTree(null));
    assertFalse(toJsonTreeResult.isJsonArray());
    assertFalse(toJsonTreeResult.isJsonObject());
    assertFalse(toJsonTreeResult.isJsonPrimitive());
    assertTrue(toJsonTreeResult.isJsonNull());
    assertSame(toJsonTreeResult, toJsonTreeResult.getAsJsonNull());
  }

  /**
   * Test {@link RegionSerDe#RegionSerDe(Schema)}.
   * <ul>
   *   <li>Then throw {@link KeyDoesNotExistException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#RegionSerDe(Schema)}
   */
  @Test
  @DisplayName("Test new RegionSerDe(Schema); then throw KeyDoesNotExistException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RegionSerDe.<init>(Schema)"})
  void testNewRegionSerDe_thenThrowKeyDoesNotExistException() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new KeyDoesNotExistException("Key Name"));

    // Act and Assert
    assertThrows(KeyDoesNotExistException.class, () -> new RegionSerDe(schema));
    verify(schema).getRowKeyFields();
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <p>
   * Method under test: {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRegionJsonSerDeDeserialize() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);

    JsonObject jsonElement = new JsonObject();
    jsonElement.addProperty(RegionSerDe.STRINGS_BASE64_ENCODED, "42");
    jsonElement.add("42", new JsonArray(3));
    jsonElement.add("Property", new JsonArray(3));

    // Act and Assert
    assertThrows(JsonParseException.class, () -> regionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <p>
   * Method under test: {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRegionJsonSerDeDeserialize2() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);

    JsonObject jsonElement = new JsonObject();
    jsonElement.addProperty(RegionSerDe.STRINGS_BASE64_ENCODED, Integer.valueOf(1));
    jsonElement.add("42", new JsonArray(3));
    jsonElement.add("Property", new JsonArray(3));

    // Act and Assert
    assertThrows(JsonParseException.class, () -> regionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRegionJsonSerDeDeserialize_given42() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);

    JsonObject jsonElement = new JsonObject();
    jsonElement.add("42", new JsonArray(3));
    jsonElement.add("Property", new JsonArray(3));

    // Act and Assert
    assertThrows(JsonParseException.class, () -> regionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); given 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRegionJsonSerDeDeserialize_givenFalse() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(false);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> regionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Given {@code Property}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); given 'Property'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRegionJsonSerDeDeserialize_givenProperty() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);

    JsonObject jsonElement = new JsonObject();
    jsonElement.add("Property", new JsonArray(3));

    // Act and Assert
    assertThrows(JsonParseException.class, () -> regionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); when JsonArray(int) with capacity is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRegionJsonSerDeDeserialize_whenJsonArrayWithCapacityIsThree() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);
    JsonArray jsonElement = new JsonArray(3);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> regionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); when JsonArray(int) with capacity is three add valueOf one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRegionJsonSerDeDeserialize_whenJsonArrayWithCapacityIsThreeAddValueOfOne() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(Integer.valueOf(1));

    // Act and Assert
    assertThrows(JsonParseException.class, () -> regionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).</li>
   *   <li>Then return Ranges Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); when JsonObject (default constructor); then return Ranges Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRegionJsonSerDeDeserialize_whenJsonObject_thenReturnRangesEmpty() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);
    JsonObject jsonElement = new JsonObject();

    // Act and Assert
    assertTrue(
        regionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1), mock(JsonDeserializationContext.class))
            .getRanges()
            .isEmpty());
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); when JsonPrimitive(Boolean) with bool is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRegionJsonSerDeDeserialize_whenJsonPrimitiveWithBoolIsTrue() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);
    JsonPrimitive jsonElement = new JsonPrimitive(true);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> regionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); when JsonPrimitive(String) with 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testRegionJsonSerDeDeserialize_whenJsonPrimitiveWithString() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);
    JsonPrimitive jsonElement = new JsonPrimitive("String");

    // Act and Assert
    assertThrows(JsonParseException.class, () -> regionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#RegionJsonSerDe(Schema)}.
   * <p>
   * Method under test: {@link RegionJsonSerDe#RegionJsonSerDe(Schema)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe new RegionJsonSerDe(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RegionJsonSerDe.<init>(Schema)"})
  void testRegionJsonSerDeNewRegionJsonSerDe() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    new RegionJsonSerDe(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#RegionJsonSerDe(Schema)}.
   * <ul>
   *   <li>Then calls {@link Schema#getRowKeyFieldNames()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionJsonSerDe#RegionJsonSerDe(Schema)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe new RegionJsonSerDe(Schema); then calls getRowKeyFieldNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RegionJsonSerDe.<init>(Schema)"})
  void testRegionJsonSerDeNewRegionJsonSerDe_thenCallsGetRowKeyFieldNames() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    new RegionJsonSerDe(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#RegionJsonSerDe(Schema)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionJsonSerDe#RegionJsonSerDe(Schema)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe new RegionJsonSerDe(Schema); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RegionJsonSerDe.<init>(Schema)"})
  void testRegionJsonSerDeNewRegionJsonSerDe_thenThrowIllegalArgumentException() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RegionJsonSerDe(schema));
    verify(schema).getRowKeyFields();
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#serialize(Region, Type, JsonSerializationContext)} with {@code Region}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RegionJsonSerDe#serialize(Region, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe serialize(Region, Type, JsonSerializationContext) with 'Region', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RegionJsonSerDe.serialize(Region, Type, JsonSerializationContext)"})
  void testRegionJsonSerDeSerializeWithRegionTypeJsonSerializationContext() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);
    Region region = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));

    // Act
    JsonElement actualSerializeResult = regionJsonSerDe.serialize(region, new PlaceholderForType(1),
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
   * Test RegionJsonSerDe {@link RegionJsonSerDe#serialize(Region, Type, JsonSerializationContext)} with {@code Region}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RegionJsonSerDe#serialize(Region, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe serialize(Region, Type, JsonSerializationContext) with 'Region', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RegionJsonSerDe.serialize(Region, Type, JsonSerializationContext)"})
  void testRegionJsonSerDeSerializeWithRegionTypeJsonSerializationContext2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field(RegionSerDe.MIN, new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));
    Region region = RegionCanonicaliser.canonicaliseRegion(new Region(ranges));

    // Act and Assert
    assertThrows(KeyDoesNotExistException.class,
        () -> regionJsonSerDe.serialize(region, new PlaceholderForType(1), mock(JsonSerializationContext.class)));
  }

  /**
   * Test RegionJsonSerDe {@link RegionJsonSerDe#serialize(Region, Type, JsonSerializationContext)} with {@code Region}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RegionJsonSerDe#serialize(Region, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe serialize(Region, Type, JsonSerializationContext) with 'Region', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RegionJsonSerDe.serialize(Region, Type, JsonSerializationContext)"})
  void testRegionJsonSerDeSerializeWithRegionTypeJsonSerializationContext3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));
    Region region = RegionCanonicaliser.canonicaliseRegion(new Region(ranges));

    // Act
    JsonElement actualSerializeResult = regionJsonSerDe.serialize(region, new PlaceholderForType(1),
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
   * Test RegionJsonSerDe {@link RegionJsonSerDe#serialize(Region, Type, JsonSerializationContext)} with {@code Region}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link RegionJsonSerDe#serialize(Region, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test RegionJsonSerDe serialize(Region, Type, JsonSerializationContext) with 'Region', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RegionJsonSerDe.serialize(Region, Type, JsonSerializationContext)"})
  void testRegionJsonSerDeSerializeWithRegionTypeJsonSerializationContext4() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionJsonSerDe regionJsonSerDe = new RegionJsonSerDe(schema);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));
    Region region = RegionCanonicaliser.canonicaliseRegion(new Region(ranges));

    // Act and Assert
    assertThrows(JsonParseException.class,
        () -> regionJsonSerDe.serialize(region, new PlaceholderForType(1), mock(JsonSerializationContext.class)));
  }

  /**
   * Test {@link RegionSerDe#toJson(Region, boolean)} with {@code region}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#toJson(Region, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Region, boolean) with 'region', 'prettyPrint'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RegionSerDe.toJson(Region, boolean)"})
  void testToJsonWithRegionPrettyPrint_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionSerDe regionSerDe = new RegionSerDe(schema);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));

    // Act and Assert
    assertEquals(
        "{\n" + "  \"Name\": {\n" + "    \"min\": \"TWlu\",\n" + "    \"minInclusive\": true,\n"
            + "    \"max\": \"TWF4\",\n" + "    \"maxInclusive\": false\n" + "  },\n"
            + "  \"stringsBase64Encoded\": true\n" + "}",
        regionSerDe.toJson(RegionCanonicaliser.canonicaliseRegion(new Region(ranges)), true));
  }

  /**
   * Test {@link RegionSerDe#toJson(Region, boolean)} with {@code region}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return {@code { "stringsBase64Encoded": true }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#toJson(Region, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Region, boolean) with 'region', 'prettyPrint'; then return '{ \"stringsBase64Encoded\": true }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RegionSerDe.toJson(Region, boolean)"})
  void testToJsonWithRegionPrettyPrint_thenReturnStringsBase64EncodedTrue() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionSerDe regionSerDe = new RegionSerDe(schema);

    // Act and Assert
    assertEquals("{\n  \"stringsBase64Encoded\": true\n}",
        regionSerDe.toJson(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>())), true));
  }

  /**
   * Test {@link RegionSerDe#toJson(Region, boolean)} with {@code region}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#toJson(Region, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Region, boolean) with 'region', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RegionSerDe.toJson(Region, boolean)"})
  void testToJsonWithRegionPrettyPrint_whenNull_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals("null", (new RegionSerDe(schema)).toJson(null, true));
  }

  /**
   * Test {@link RegionSerDe#toJson(Region)} with {@code region}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#toJson(Region)}
   */
  @Test
  @DisplayName("Test toJson(Region) with 'region'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RegionSerDe.toJson(Region)"})
  void testToJsonWithRegion_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionSerDe regionSerDe = new RegionSerDe(schema);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));

    // Act and Assert
    assertEquals(
        "{\"Name\":{\"min\":\"TWlu\",\"minInclusive\":true,\"max\":\"TWF4\",\"maxInclusive\":false},\"stringsBase64Encoded"
            + "\":true}",
        regionSerDe.toJson(RegionCanonicaliser.canonicaliseRegion(new Region(ranges))));
  }

  /**
   * Test {@link RegionSerDe#toJson(Region)} with {@code region}.
   * <ul>
   *   <li>Then return {@code {"stringsBase64Encoded":true}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#toJson(Region)}
   */
  @Test
  @DisplayName("Test toJson(Region) with 'region'; then return '{\"stringsBase64Encoded\":true}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RegionSerDe.toJson(Region)"})
  void testToJsonWithRegion_thenReturnStringsBase64EncodedTrue() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionSerDe regionSerDe = new RegionSerDe(schema);

    // Act and Assert
    assertEquals("{\"stringsBase64Encoded\":true}",
        regionSerDe.toJson(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()))));
  }

  /**
   * Test {@link RegionSerDe#toJson(Region)} with {@code region}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#toJson(Region)}
   */
  @Test
  @DisplayName("Test toJson(Region) with 'region'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RegionSerDe.toJson(Region)"})
  void testToJsonWithRegion_whenNull_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals("null", (new RegionSerDe(schema)).toJson(null));
  }

  /**
   * Test {@link RegionSerDe#toJsonTree(Region)}.
   * <p>
   * Method under test: {@link RegionSerDe#toJsonTree(Region)}
   */
  @Test
  @DisplayName("Test toJsonTree(Region)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RegionSerDe.toJsonTree(Region)"})
  void testToJsonTree() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field(RegionSerDe.STRINGS_BASE64_ENCODED, new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionSerDe regionSerDe = new RegionSerDe(schema);

    // Act
    JsonElement actualToJsonTreeResult = regionSerDe
        .toJsonTree(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>())));

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link RegionSerDe#toJsonTree(Region)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#toJsonTree(Region)}
   */
  @Test
  @DisplayName("Test toJsonTree(Region); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RegionSerDe.toJsonTree(Region)"})
  void testToJsonTree_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionSerDe regionSerDe = new RegionSerDe(schema);

    // Act
    JsonElement actualToJsonTreeResult = regionSerDe
        .toJsonTree(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>())));

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link RegionSerDe#toJsonTree(Region)}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#toJsonTree(Region)}
   */
  @Test
  @DisplayName("Test toJsonTree(Region); then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RegionSerDe.toJsonTree(Region)"})
  void testToJsonTree_thenReturnSizeIsTwo() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionSerDe regionSerDe = new RegionSerDe(schema);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));

    // Act
    JsonElement actualToJsonTreeResult = regionSerDe
        .toJsonTree(RegionCanonicaliser.canonicaliseRegion(new Region(ranges)));

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link RegionSerDe#toJsonTree(Region)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link JsonNull#INSTANCE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#toJsonTree(Region)}
   */
  @Test
  @DisplayName("Test toJsonTree(Region); when 'null'; then return INSTANCE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RegionSerDe.toJsonTree(Region)"})
  void testToJsonTree_whenNull_thenReturnInstance() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    JsonElement actualToJsonTreeResult = (new RegionSerDe(schema)).toJsonTree(null);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link RegionSerDe#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertNull((new RegionSerDe(schema)).fromJson(""));
  }

  /**
   * Test {@link RegionSerDe#fromJsonTree(JsonElement)}.
   * <ul>
   *   <li>When {@link JsonNull} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#fromJsonTree(JsonElement)}
   */
  @Test
  @DisplayName("Test fromJsonTree(JsonElement); when JsonNull (default constructor); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionSerDe.fromJsonTree(JsonElement)"})
  void testFromJsonTree_whenJsonNull_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionSerDe regionSerDe = new RegionSerDe(schema);

    // Act and Assert
    assertNull(regionSerDe.fromJsonTree(new JsonNull()));
  }

  /**
   * Test {@link RegionSerDe#fromJsonTree(JsonElement)}.
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).</li>
   *   <li>Then return Ranges Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#fromJsonTree(JsonElement)}
   */
  @Test
  @DisplayName("Test fromJsonTree(JsonElement); when JsonObject (default constructor); then return Ranges Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionSerDe.fromJsonTree(JsonElement)"})
  void testFromJsonTree_whenJsonObject_thenReturnRangesEmpty() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    RegionSerDe regionSerDe = new RegionSerDe(schema);

    // Act and Assert
    assertTrue(regionSerDe.fromJsonTree(new JsonObject()).getRanges().isEmpty());
  }

  /**
   * Test {@link RegionSerDe#fromJsonTree(JsonElement)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionSerDe#fromJsonTree(JsonElement)}
   */
  @Test
  @DisplayName("Test fromJsonTree(JsonElement); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionSerDe.fromJsonTree(JsonElement)"})
  void testFromJsonTree_whenNull_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertNull((new RegionSerDe(schema)).fromJsonTree(null));
  }
}
