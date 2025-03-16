package sleeper.core.partition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import sleeper.core.partition.PartitionSerDe.PartitionJsonSerDe;
import sleeper.core.range.Range;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.StringType;

class PartitionSerDeDiffblueTest {
  /**
   * Test {@link PartitionSerDe#PartitionSerDe(Schema)}.
   * <p>
   * Method under test: {@link PartitionSerDe#PartitionSerDe(Schema)}
   */
  @Test
  @DisplayName("Test new PartitionSerDe(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionSerDe.<init>(Schema)"})
  void testNewPartitionSerDe() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    PartitionSerDe actualPartitionSerDe = new PartitionSerDe(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
    assertEquals("null", actualPartitionSerDe.toJson(null));
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); given 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testPartitionJsonSerDeDeserialize_givenFalse() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionJsonSerDe partitionJsonSerDe = new PartitionJsonSerDe(schema);

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(false);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> partitionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); when JsonArray(int) with capacity is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testPartitionJsonSerDeDeserialize_whenJsonArrayWithCapacityIsThree() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionJsonSerDe partitionJsonSerDe = new PartitionJsonSerDe(schema);
    JsonArray jsonElement = new JsonArray(3);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> partitionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonArray#JsonArray(int)} with capacity is three add valueOf one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); when JsonArray(int) with capacity is three add valueOf one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testPartitionJsonSerDeDeserialize_whenJsonArrayWithCapacityIsThreeAddValueOfOne() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionJsonSerDe partitionJsonSerDe = new PartitionJsonSerDe(schema);

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(Integer.valueOf(1));

    // Act and Assert
    assertThrows(JsonParseException.class, () -> partitionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(Boolean)} with bool is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); when JsonPrimitive(Boolean) with bool is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testPartitionJsonSerDeDeserialize_whenJsonPrimitiveWithBoolIsTrue() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionJsonSerDe partitionJsonSerDe = new PartitionJsonSerDe(schema);
    JsonPrimitive jsonElement = new JsonPrimitive(true);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> partitionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); when JsonPrimitive(String) with 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testPartitionJsonSerDeDeserialize_whenJsonPrimitiveWithString() throws JsonParseException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionJsonSerDe partitionJsonSerDe = new PartitionJsonSerDe(schema);
    JsonPrimitive jsonElement = new JsonPrimitive("String");

    // Act and Assert
    assertThrows(JsonParseException.class, () -> partitionJsonSerDe.deserialize(jsonElement, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class)));
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#PartitionJsonSerDe(Schema)}.
   * <p>
   * Method under test: {@link PartitionJsonSerDe#PartitionJsonSerDe(Schema)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe new PartitionJsonSerDe(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionJsonSerDe.<init>(Schema)"})
  void testPartitionJsonSerDeNewPartitionJsonSerDe() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    new PartitionJsonSerDe(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#PartitionJsonSerDe(Schema)}.
   * <ul>
   *   <li>Then calls {@link Schema#getRowKeyFieldNames()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionJsonSerDe#PartitionJsonSerDe(Schema)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe new PartitionJsonSerDe(Schema); then calls getRowKeyFieldNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionJsonSerDe.<init>(Schema)"})
  void testPartitionJsonSerDeNewPartitionJsonSerDe_thenCallsGetRowKeyFieldNames() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    new PartitionJsonSerDe(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#PartitionJsonSerDe(Schema)}.
   * <ul>
   *   <li>Then throw {@link JsonParseException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionJsonSerDe#PartitionJsonSerDe(Schema)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe new PartitionJsonSerDe(Schema); then throw JsonParseException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionJsonSerDe.<init>(Schema)"})
  void testPartitionJsonSerDeNewPartitionJsonSerDe_thenThrowJsonParseException() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new JsonParseException("Msg"));

    // Act and Assert
    assertThrows(JsonParseException.class, () -> new PartitionJsonSerDe(schema));
    verify(schema).getRowKeyFields();
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#serialize(Partition, Type, JsonSerializationContext)} with {@code Partition}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link PartitionJsonSerDe#serialize(Partition, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe serialize(Partition, Type, JsonSerializationContext) with 'Partition', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement PartitionJsonSerDe.serialize(Partition, Type, JsonSerializationContext)"})
  void testPartitionJsonSerDeSerializeWithPartitionTypeJsonSerializationContext() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionJsonSerDe partitionJsonSerDe = new PartitionJsonSerDe(schema);
    Partition partition = mock(Partition.class);
    when(partition.getDimension()).thenReturn(1);
    when(partition.isLeafPartition()).thenReturn(true);
    when(partition.getId()).thenReturn("42");
    when(partition.getParentPartitionId()).thenReturn("42");
    when(partition.getChildPartitionIds()).thenReturn(new ArrayList<>());
    when(partition.getRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    JsonElement actualSerializeResult = partitionJsonSerDe.serialize(partition, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    verify(partition, atLeast(1)).getChildPartitionIds();
    verify(partition).getDimension();
    verify(partition).getId();
    verify(partition).getParentPartitionId();
    verify(partition).getRegion();
    verify(partition).isLeafPartition();
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(6, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#serialize(Partition, Type, JsonSerializationContext)} with {@code Partition}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link PartitionJsonSerDe#serialize(Partition, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe serialize(Partition, Type, JsonSerializationContext) with 'Partition', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement PartitionJsonSerDe.serialize(Partition, Type, JsonSerializationContext)"})
  void testPartitionJsonSerDeSerializeWithPartitionTypeJsonSerializationContext2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionJsonSerDe partitionJsonSerDe = new PartitionJsonSerDe(schema);
    Partition partition = mock(Partition.class);
    when(partition.getDimension()).thenThrow(new JsonParseException(PartitionSerDe.PARTITION_ID));
    when(partition.isLeafPartition()).thenReturn(true);
    when(partition.getId()).thenReturn("42");
    when(partition.getParentPartitionId()).thenReturn("42");
    when(partition.getChildPartitionIds()).thenReturn(new ArrayList<>());
    when(partition.getRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act and Assert
    assertThrows(JsonParseException.class,
        () -> partitionJsonSerDe.serialize(partition, new PlaceholderForType(1), mock(JsonSerializationContext.class)));
    verify(partition, atLeast(1)).getChildPartitionIds();
    verify(partition).getDimension();
    verify(partition).getId();
    verify(partition).getParentPartitionId();
    verify(partition).getRegion();
    verify(partition).isLeafPartition();
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#serialize(Partition, Type, JsonSerializationContext)} with {@code Partition}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link PartitionJsonSerDe#serialize(Partition, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe serialize(Partition, Type, JsonSerializationContext) with 'Partition', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement PartitionJsonSerDe.serialize(Partition, Type, JsonSerializationContext)"})
  void testPartitionJsonSerDeSerializeWithPartitionTypeJsonSerializationContext3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionJsonSerDe partitionJsonSerDe = new PartitionJsonSerDe(schema);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add(PartitionSerDe.PARTITION_ID);
    Partition partition = mock(Partition.class);
    when(partition.getDimension()).thenReturn(1);
    when(partition.isLeafPartition()).thenReturn(true);
    when(partition.getId()).thenReturn("42");
    when(partition.getParentPartitionId()).thenReturn("42");
    when(partition.getChildPartitionIds()).thenReturn(stringList);
    when(partition.getRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    JsonElement actualSerializeResult = partitionJsonSerDe.serialize(partition, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    verify(partition, atLeast(1)).getChildPartitionIds();
    verify(partition).getDimension();
    verify(partition).getId();
    verify(partition).getParentPartitionId();
    verify(partition).getRegion();
    verify(partition).isLeafPartition();
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(6, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test PartitionJsonSerDe {@link PartitionJsonSerDe#serialize(Partition, Type, JsonSerializationContext)} with {@code Partition}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link PartitionJsonSerDe#serialize(Partition, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test PartitionJsonSerDe serialize(Partition, Type, JsonSerializationContext) with 'Partition', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement PartitionJsonSerDe.serialize(Partition, Type, JsonSerializationContext)"})
  void testPartitionJsonSerDeSerializeWithPartitionTypeJsonSerializationContext4() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field(PartitionSerDe.PARTITION_ID, new StringType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionJsonSerDe partitionJsonSerDe = new PartitionJsonSerDe(schema);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(new Range(new Field(PartitionSerDe.PARTITION_ID, new ByteArrayType()), "Min", "Max"));
    Region region = new Region(ranges);
    Partition partition = mock(Partition.class);
    when(partition.getDimension()).thenReturn(1);
    when(partition.isLeafPartition()).thenReturn(true);
    when(partition.getId()).thenReturn("42");
    when(partition.getParentPartitionId()).thenReturn("42");
    when(partition.getChildPartitionIds()).thenReturn(new ArrayList<>());
    when(partition.getRegion()).thenReturn(region);

    // Act
    JsonElement actualSerializeResult = partitionJsonSerDe.serialize(partition, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    verify(partition, atLeast(1)).getChildPartitionIds();
    verify(partition).getDimension();
    verify(partition).getId();
    verify(partition).getParentPartitionId();
    verify(partition).getRegion();
    verify(partition).isLeafPartition();
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(6, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition)} with {@code partition}.
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition)}
   */
  @Test
  @DisplayName("Test toJson(Partition) with 'partition'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition)"})
  void testToJsonWithPartition() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(false)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(
        "{\"partitionId\":\"42\",\"isLeafPartition\":false,\"parentPartitionId\":\"42\",\"childPartitionIds\":[],\"region\""
            + ":{\"stringsBase64Encoded\":true},\"dimension\":1}",
        partitionSerDe.toJson(partition));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition, boolean)} with {@code partition}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Partition, boolean) with 'partition', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition, boolean)"})
  void testToJsonWithPartitionPrettyPrint() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(false)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"partitionId\": \"42\",\n" + "  \"isLeafPartition\": false,\n"
            + "  \"parentPartitionId\": \"42\",\n" + "  \"childPartitionIds\": [],\n" + "  \"region\": {\n"
            + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"dimension\": 1\n" + "}",
        partitionSerDe.toJson(partition, true));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition, boolean)} with {@code partition}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Partition, boolean) with 'partition', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition, boolean)"})
  void testToJsonWithPartitionPrettyPrint2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"));
    Region region = new Region(ranges);
    Partition.Builder builderResult = Partition.builder();
    Partition partition = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42")
        .region(region)
        .build();

    // Act and Assert
    assertEquals("{\n" + "  \"partitionId\": \"42\",\n" + "  \"isLeafPartition\": true,\n"
        + "  \"parentPartitionId\": \"42\",\n" + "  \"childPartitionIds\": [],\n" + "  \"region\": {\n"
        + "    \"Name\": {\n" + "      \"min\": \"TWlu\",\n" + "      \"minInclusive\": true,\n"
        + "      \"max\": \"TWF4\",\n" + "      \"maxInclusive\": false\n" + "    },\n"
        + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"dimension\": 1\n" + "}",
        partitionSerDe.toJson(partition, true));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition, boolean)} with {@code partition}, {@code prettyPrint}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link ArrayList#ArrayList()} add empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Partition, boolean) with 'partition', 'prettyPrint'; given empty string; when ArrayList() add empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition, boolean)"})
  void testToJsonWithPartitionPrettyPrint_givenEmptyString_whenArrayListAddEmptyString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);

    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add("");
    Partition.Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"partitionId\": \"42\",\n" + "  \"isLeafPartition\": true,\n" + "  \"parentPartitionId\": \"42\",\n"
            + "  \"childPartitionIds\": [\n" + "    \"\"\n" + "  ],\n" + "  \"region\": {\n"
            + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"dimension\": 1\n" + "}",
        partitionSerDe.toJson(partition, true));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition, boolean)} with {@code partition}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link PartitionSerDe#IS_LEAF_PARTITION}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Partition, boolean) with 'partition', 'prettyPrint'; given IS_LEAF_PARTITION")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition, boolean)"})
  void testToJsonWithPartitionPrettyPrint_givenIs_leaf_partition() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);

    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add(PartitionSerDe.IS_LEAF_PARTITION);
    childPartitionIds.add(PartitionSerDe.PARTITION_ID);
    Partition.Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"partitionId\": \"42\",\n" + "  \"isLeafPartition\": true,\n" + "  \"parentPartitionId\": \"42\",\n"
            + "  \"childPartitionIds\": [\n" + "    \"isLeafPartition\",\n" + "    \"partitionId\"\n" + "  ],\n"
            + "  \"region\": {\n" + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"dimension\": 1\n" + "}",
        partitionSerDe.toJson(partition, true));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition, boolean)} with {@code partition}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link PartitionSerDe#PARTITION_ID}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Partition, boolean) with 'partition', 'prettyPrint'; given PARTITION_ID")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition, boolean)"})
  void testToJsonWithPartitionPrettyPrint_givenPartition_id() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);

    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add(PartitionSerDe.PARTITION_ID);
    Partition.Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"partitionId\": \"42\",\n" + "  \"isLeafPartition\": true,\n" + "  \"parentPartitionId\": \"42\",\n"
            + "  \"childPartitionIds\": [\n" + "    \"partitionId\"\n" + "  ],\n" + "  \"region\": {\n"
            + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"dimension\": 1\n" + "}",
        partitionSerDe.toJson(partition, true));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition, boolean)} with {@code partition}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Partition, boolean) with 'partition', 'prettyPrint'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition, boolean)"})
  void testToJsonWithPartitionPrettyPrint_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals("{\n" + "  \"partitionId\": \"42\",\n" + "  \"isLeafPartition\": true,\n"
        + "  \"parentPartitionId\": \"42\",\n" + "  \"childPartitionIds\": [],\n" + "  \"region\": {\n"
        + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"dimension\": 1\n" + "}",
        partitionSerDe.toJson(partition, true));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition, boolean)} with {@code partition}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Partition, boolean) with 'partition', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition, boolean)"})
  void testToJsonWithPartitionPrettyPrint_whenNull_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals("null", (new PartitionSerDe(schema)).toJson(null, true));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition)} with {@code partition}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition)}
   */
  @Test
  @DisplayName("Test toJson(Partition) with 'partition'; given ArrayList() add Field(String, Type) with 'Name' and type is StringType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition)"})
  void testToJsonWithPartition_givenArrayListAddFieldWithNameAndTypeIsStringType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"));
    Region region = new Region(ranges);
    Partition.Builder builderResult = Partition.builder();
    Partition partition = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42")
        .region(region)
        .build();

    // Act and Assert
    assertEquals(
        "{\"partitionId\":\"42\",\"isLeafPartition\":true,\"parentPartitionId\":\"42\",\"childPartitionIds\":[],\"region\":"
            + "{\"Name\":{\"min\":\"TWlu\",\"minInclusive\":true,\"max\":\"TWF4\",\"maxInclusive\":false},\"stringsBase64Encoded\""
            + ":true},\"dimension\":1}",
        partitionSerDe.toJson(partition));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition)} with {@code partition}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link ArrayList#ArrayList()} add empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition)}
   */
  @Test
  @DisplayName("Test toJson(Partition) with 'partition'; given empty string; when ArrayList() add empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition)"})
  void testToJsonWithPartition_givenEmptyString_whenArrayListAddEmptyString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);

    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add("");
    Partition.Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(
        "{\"partitionId\":\"42\",\"isLeafPartition\":true,\"parentPartitionId\":\"42\",\"childPartitionIds\":[\"\"],\"region"
            + "\":{\"stringsBase64Encoded\":true},\"dimension\":1}",
        partitionSerDe.toJson(partition));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition)} with {@code partition}.
   * <ul>
   *   <li>Given {@link PartitionSerDe#IS_LEAF_PARTITION}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link PartitionSerDe#IS_LEAF_PARTITION}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition)}
   */
  @Test
  @DisplayName("Test toJson(Partition) with 'partition'; given IS_LEAF_PARTITION; when ArrayList() add IS_LEAF_PARTITION")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition)"})
  void testToJsonWithPartition_givenIs_leaf_partition_whenArrayListAddIs_leaf_partition() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);

    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add(PartitionSerDe.IS_LEAF_PARTITION);
    childPartitionIds.add(PartitionSerDe.PARTITION_ID);
    Partition.Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(
        "{\"partitionId\":\"42\",\"isLeafPartition\":true,\"parentPartitionId\":\"42\",\"childPartitionIds\":[\"isLeafPartition"
            + "\",\"partitionId\"],\"region\":{\"stringsBase64Encoded\":true},\"dimension\":1}",
        partitionSerDe.toJson(partition));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition)} with {@code partition}.
   * <ul>
   *   <li>Given {@link PartitionSerDe#PARTITION_ID}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link PartitionSerDe#PARTITION_ID}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition)}
   */
  @Test
  @DisplayName("Test toJson(Partition) with 'partition'; given PARTITION_ID; when ArrayList() add PARTITION_ID")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition)"})
  void testToJsonWithPartition_givenPartition_id_whenArrayListAddPartition_id() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);

    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add(PartitionSerDe.PARTITION_ID);
    Partition.Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(
        "{\"partitionId\":\"42\",\"isLeafPartition\":true,\"parentPartitionId\":\"42\",\"childPartitionIds\":[\"partitionId"
            + "\"],\"region\":{\"stringsBase64Encoded\":true},\"dimension\":1}",
        partitionSerDe.toJson(partition));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition)} with {@code partition}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition)}
   */
  @Test
  @DisplayName("Test toJson(Partition) with 'partition'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition)"})
  void testToJsonWithPartition_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    PartitionSerDe partitionSerDe = new PartitionSerDe(schema);
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(
        "{\"partitionId\":\"42\",\"isLeafPartition\":true,\"parentPartitionId\":\"42\",\"childPartitionIds\":[],\"region\":"
            + "{\"stringsBase64Encoded\":true},\"dimension\":1}",
        partitionSerDe.toJson(partition));
  }

  /**
   * Test {@link PartitionSerDe#toJson(Partition)} with {@code partition}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#toJson(Partition)}
   */
  @Test
  @DisplayName("Test toJson(Partition) with 'partition'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PartitionSerDe.toJson(Partition)"})
  void testToJsonWithPartition_whenNull_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertEquals("null", (new PartitionSerDe(schema)).toJson(null));
  }

  /**
   * Test {@link PartitionSerDe#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Partition PartitionSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertNull((new PartitionSerDe(schema)).fromJson(""));
  }
}
