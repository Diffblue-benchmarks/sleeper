package sleeper.splitter.core.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
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
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.partition.Partition;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableStatus;
import sleeper.splitter.core.find.SplitPartitionJobDefinitionSerDe.SplitPartitionJobDefinitionJsonSerDe;

class SplitPartitionJobDefinitionSerDeDiffblueTest {
  /**
   * Test {@link SplitPartitionJobDefinitionSerDe#SplitPartitionJobDefinitionSerDe(TablePropertiesProvider)}.
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionSerDe#SplitPartitionJobDefinitionSerDe(TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test new SplitPartitionJobDefinitionSerDe(TablePropertiesProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionJobDefinitionSerDe.<init>(TablePropertiesProvider)"})
  void testNewSplitPartitionJobDefinitionSerDe() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertEquals("null", (new SplitPartitionJobDefinitionSerDe(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))))).toJson(null));
  }

  /**
   * Test SplitPartitionJobDefinitionJsonSerDe {@link SplitPartitionJobDefinitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test SplitPartitionJobDefinitionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SplitPartitionJobDefinition SplitPartitionJobDefinitionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testSplitPartitionJobDefinitionJsonSerDeDeserialize() throws JsonParseException {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    SplitPartitionJobDefinitionJsonSerDe splitPartitionJobDefinitionJsonSerDe = new SplitPartitionJobDefinitionJsonSerDe(
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    JsonArray jsonElement = new JsonArray(3);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> splitPartitionJobDefinitionJsonSerDe.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test SplitPartitionJobDefinitionJsonSerDe {@link SplitPartitionJobDefinitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test SplitPartitionJobDefinitionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SplitPartitionJobDefinition SplitPartitionJobDefinitionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testSplitPartitionJobDefinitionJsonSerDeDeserialize2() throws JsonParseException {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    SplitPartitionJobDefinitionJsonSerDe splitPartitionJobDefinitionJsonSerDe = new SplitPartitionJobDefinitionJsonSerDe(
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    JsonPrimitive jsonElement = new JsonPrimitive(true);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> splitPartitionJobDefinitionJsonSerDe.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test SplitPartitionJobDefinitionJsonSerDe {@link SplitPartitionJobDefinitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test SplitPartitionJobDefinitionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SplitPartitionJobDefinition SplitPartitionJobDefinitionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testSplitPartitionJobDefinitionJsonSerDeDeserialize3() throws JsonParseException {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    SplitPartitionJobDefinitionJsonSerDe splitPartitionJobDefinitionJsonSerDe = new SplitPartitionJobDefinitionJsonSerDe(
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(Integer.valueOf(1));
    jsonElement.add(true);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> splitPartitionJobDefinitionJsonSerDe.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test SplitPartitionJobDefinitionJsonSerDe {@link SplitPartitionJobDefinitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test SplitPartitionJobDefinitionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); given 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SplitPartitionJobDefinition SplitPartitionJobDefinitionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testSplitPartitionJobDefinitionJsonSerDeDeserialize_givenTrue() throws JsonParseException {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    SplitPartitionJobDefinitionJsonSerDe splitPartitionJobDefinitionJsonSerDe = new SplitPartitionJobDefinitionJsonSerDe(
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    JsonArray jsonElement = new JsonArray(3);
    jsonElement.add(true);

    // Act and Assert
    assertThrows(JsonParseException.class, () -> splitPartitionJobDefinitionJsonSerDe.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test SplitPartitionJobDefinitionJsonSerDe {@link SplitPartitionJobDefinitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>When {@link JsonPrimitive#JsonPrimitive(String)} with {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test SplitPartitionJobDefinitionJsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); when JsonPrimitive(String) with 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SplitPartitionJobDefinition SplitPartitionJobDefinitionJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testSplitPartitionJobDefinitionJsonSerDeDeserialize_whenJsonPrimitiveWithString() throws JsonParseException {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    SplitPartitionJobDefinitionJsonSerDe splitPartitionJobDefinitionJsonSerDe = new SplitPartitionJobDefinitionJsonSerDe(
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    JsonPrimitive jsonElement = new JsonPrimitive("String");

    // Act and Assert
    assertThrows(JsonParseException.class, () -> splitPartitionJobDefinitionJsonSerDe.deserialize(jsonElement,
        new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test SplitPartitionJobDefinitionJsonSerDe {@link SplitPartitionJobDefinitionJsonSerDe#serialize(SplitPartitionJobDefinition, Type, JsonSerializationContext)} with {@code SplitPartitionJobDefinition}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionJsonSerDe#serialize(SplitPartitionJobDefinition, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test SplitPartitionJobDefinitionJsonSerDe serialize(SplitPartitionJobDefinition, Type, JsonSerializationContext) with 'SplitPartitionJobDefinition', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement SplitPartitionJobDefinitionJsonSerDe.serialize(SplitPartitionJobDefinition, Type, JsonSerializationContext)"})
  void testSplitPartitionJobDefinitionJsonSerDeSerializeWithSplitPartitionJobDefinitionTypeJsonSerializationContext()
      throws SleeperPropertiesInvalidException {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(buildResult);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    SplitPartitionJobDefinitionJsonSerDe splitPartitionJobDefinitionJsonSerDe = new SplitPartitionJobDefinitionJsonSerDe(
        new TablePropertiesProvider(new InstanceProperties(), propertiesStore));
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionJobDefinition job = new SplitPartitionJobDefinition("42", partition, new ArrayList<>());

    // Act
    JsonElement actualSerializeResult = splitPartitionJobDefinitionJsonSerDe.serialize(job, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getSchema();
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(eq("42"));
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
   * Test {@link SplitPartitionJobDefinitionSerDe#toJson(SplitPartitionJobDefinition, boolean)} with {@code splitPartitionJobDefinition}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionSerDe#toJson(SplitPartitionJobDefinition, boolean)}
   */
  @Test
  @DisplayName("Test toJson(SplitPartitionJobDefinition, boolean) with 'splitPartitionJobDefinition', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SplitPartitionJobDefinitionSerDe.toJson(SplitPartitionJobDefinition, boolean)"})
  void testToJsonWithSplitPartitionJobDefinitionPrettyPrint_whenNull_thenReturnNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertEquals("null", (new SplitPartitionJobDefinitionSerDe(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))))).toJson(null, true));
  }

  /**
   * Test {@link SplitPartitionJobDefinitionSerDe#toJson(SplitPartitionJobDefinition, boolean)} with {@code splitPartitionJobDefinition}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionSerDe#toJson(SplitPartitionJobDefinition, boolean)}
   */
  @Test
  @DisplayName("Test toJson(SplitPartitionJobDefinition, boolean) with 'splitPartitionJobDefinition', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SplitPartitionJobDefinitionSerDe.toJson(SplitPartitionJobDefinition, boolean)"})
  void testToJsonWithSplitPartitionJobDefinitionPrettyPrint_whenNull_thenReturnNull2() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertEquals("null", (new SplitPartitionJobDefinitionSerDe(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))))).toJson(null, false));
  }

  /**
   * Test {@link SplitPartitionJobDefinitionSerDe#toJson(SplitPartitionJobDefinition)} with {@code splitPartitionJobDefinition}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionSerDe#toJson(SplitPartitionJobDefinition)}
   */
  @Test
  @DisplayName("Test toJson(SplitPartitionJobDefinition) with 'splitPartitionJobDefinition'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SplitPartitionJobDefinitionSerDe.toJson(SplitPartitionJobDefinition)"})
  void testToJsonWithSplitPartitionJobDefinition_whenNull_thenReturnNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertEquals("null", (new SplitPartitionJobDefinitionSerDe(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))))).toJson(null));
  }

  /**
   * Test {@link SplitPartitionJobDefinitionSerDe#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionJobDefinitionSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SplitPartitionJobDefinition SplitPartitionJobDefinitionSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertNull((new SplitPartitionJobDefinitionSerDe(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))))).fromJson(""));
  }
}
