package sleeper.core.statestore.transactionlog.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.table.TableIndex;
import sleeper.core.table.TableStatus;

class TransactionSerDeProviderDiffblueTest {
  /**
   * Test {@link TransactionSerDeProvider#from(TablePropertiesProvider)}.
   * <ul>
   *   <li>Then ByTableId is {@code foo} toJsonTree {@code null} return {@link JsonNull}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDeProvider#from(TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test from(TablePropertiesProvider); then ByTableId is 'foo' toJsonTree 'null' return JsonNull")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionSerDeProvider TransactionSerDeProvider.from(TablePropertiesProvider)"})
  void testFrom_thenByTableIdIsFooToJsonTreeNullReturnJsonNull() throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
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

    // Act
    TransactionSerDe actualByTableId = TransactionSerDeProvider
        .from(new TablePropertiesProvider(new InstanceProperties(), propertiesStore))
        .getByTableId("foo");

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getSchema();
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(eq("foo"));
    JsonElement toJsonTreeResult = actualByTableId.toJsonTree(null);
    assertTrue(toJsonTreeResult instanceof JsonNull);
    assertEquals("null", actualByTableId.toJson(null));
    assertEquals("null", actualByTableId.toJsonPrettyPrint(null));
    assertFalse(toJsonTreeResult.isJsonArray());
    assertFalse(toJsonTreeResult.isJsonObject());
    assertFalse(toJsonTreeResult.isJsonPrimitive());
    assertTrue(toJsonTreeResult.isJsonNull());
    assertSame(toJsonTreeResult, toJsonTreeResult.getAsJsonNull());
  }

  /**
   * Test {@link TransactionSerDeProvider#forOneTable(TableProperties)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDeProvider#forOneTable(TableProperties)}
   */
  @Test
  @DisplayName("Test forOneTable(TableProperties); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionSerDeProvider TransactionSerDeProvider.forOneTable(TableProperties)"})
  void testForOneTable_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.setSchema(schema);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> TransactionSerDeProvider.forOneTable(tableProperties).getByTableId("foo"));
  }

  /**
   * Test {@link TransactionSerDeProvider#forFileTransactions()}.
   * <p>
   * Method under test: {@link TransactionSerDeProvider#forFileTransactions()}
   */
  @Test
  @DisplayName("Test forFileTransactions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionSerDeProvider TransactionSerDeProvider.forFileTransactions()"})
  void testForFileTransactions() {
    // Arrange and Act
    TransactionSerDe actualByTableId = TransactionSerDeProvider.forFileTransactions().getByTableId("foo");

    // Assert
    JsonElement toJsonTreeResult = actualByTableId.toJsonTree(null);
    assertTrue(toJsonTreeResult instanceof JsonNull);
    assertEquals("null", actualByTableId.toJson(null));
    assertEquals("null", actualByTableId.toJsonPrettyPrint(null));
    assertFalse(toJsonTreeResult.isJsonArray());
    assertFalse(toJsonTreeResult.isJsonObject());
    assertFalse(toJsonTreeResult.isJsonPrimitive());
    assertTrue(toJsonTreeResult.isJsonNull());
    assertSame(toJsonTreeResult, toJsonTreeResult.getAsJsonNull());
  }
}
