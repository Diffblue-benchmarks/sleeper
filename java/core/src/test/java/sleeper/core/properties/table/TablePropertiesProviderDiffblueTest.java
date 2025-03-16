package sleeper.core.properties.table;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperProperties;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableIndex;
import sleeper.core.table.TableStatus;

class TablePropertiesProviderDiffblueTest {
  /**
   * Test {@link TablePropertiesProvider#TablePropertiesProvider(InstanceProperties, TablePropertiesStore)}.
   * <p>
   * Method under test: {@link TablePropertiesProvider#TablePropertiesProvider(InstanceProperties, TablePropertiesStore)}
   */
  @Test
  @DisplayName("Test new TablePropertiesProvider(InstanceProperties, TablePropertiesStore)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TablePropertiesProvider.<init>(InstanceProperties, TablePropertiesStore)"})
  void testNewTablePropertiesProvider() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    TablePropertiesProvider actualTablePropertiesProvider = new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    // Assert
    Stream<TableProperties> streamAllTablesResult = actualTablePropertiesProvider.streamAllTables();
    assertTrue(streamAllTablesResult.limit(5).collect(Collectors.toList()).isEmpty());
    Stream<TableProperties> streamOnlineTablesResult = actualTablePropertiesProvider.streamOnlineTables();
    assertTrue(streamOnlineTablesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link TablePropertiesProvider#TablePropertiesProvider(InstanceProperties, TablePropertiesStore, Supplier)}.
   * <p>
   * Method under test: {@link TablePropertiesProvider#TablePropertiesProvider(InstanceProperties, TablePropertiesStore, Supplier)}
   */
  @Test
  @DisplayName("Test new TablePropertiesProvider(InstanceProperties, TablePropertiesStore, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TablePropertiesProvider.<init>(InstanceProperties, TablePropertiesStore, Supplier)"})
  void testNewTablePropertiesProvider2() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    TablePropertiesProvider actualTablePropertiesProvider = new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)), mock(Supplier.class));

    // Assert
    Stream<TableProperties> streamAllTablesResult = actualTablePropertiesProvider.streamAllTables();
    assertTrue(streamAllTablesResult.limit(5).collect(Collectors.toList()).isEmpty());
    Stream<TableProperties> streamOnlineTablesResult = actualTablePropertiesProvider.streamOnlineTables();
    assertTrue(streamOnlineTablesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link TablePropertiesProvider#getByName(String)}.
   * <p>
   * Method under test: {@link TablePropertiesProvider#getByName(String)}
   */
  @Test
  @DisplayName("Test getByName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesProvider.getByName(String)"})
  void testGetByName() throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    (new TablePropertiesProvider(new InstanceProperties(), propertiesStore)).getByName("Table Name");

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link TablePropertiesProvider#getByName(String)}.
   * <p>
   * Method under test: {@link TablePropertiesProvider#getByName(String)}
   */
  @Test
  @DisplayName("Test getByName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesProvider.getByName(String)"})
  void testGetByName2() throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName(null, "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    (new TablePropertiesProvider(new InstanceProperties(), propertiesStore)).getByName("Table Name");

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link TablePropertiesProvider#getByName(String)}.
   * <p>
   * Method under test: {@link TablePropertiesProvider#getByName(String)}
   */
  @Test
  @DisplayName("Test getByName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesProvider.getByName(String)"})
  void testGetByName3() throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", false));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    (new TablePropertiesProvider(new InstanceProperties(), propertiesStore)).getByName("Table Name");

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link TablePropertiesProvider#getByName(String)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link TableProperties#getStatus()} return {@code null}.</li>
   *   <li>Then calls {@link SleeperProperties#validate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesProvider#getByName(String)}
   */
  @Test
  @DisplayName("Test getByName(String); given TableProperties getStatus() return 'null'; then calls validate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesProvider.getByName(String)"})
  void testGetByName_givenTablePropertiesGetStatusReturnNull_thenCallsValidate()
      throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(null);
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    (new TablePropertiesProvider(new InstanceProperties(), propertiesStore)).getByName("Table Name");

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link TablePropertiesProvider#getById(String)}.
   * <p>
   * Method under test: {@link TablePropertiesProvider#getById(String)}
   */
  @Test
  @DisplayName("Test getById(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesProvider.getById(String)"})
  void testGetById() throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    (new TablePropertiesProvider(new InstanceProperties(), propertiesStore)).getById("42");

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(eq("42"));
  }

  /**
   * Test {@link TablePropertiesProvider#getById(String)}.
   * <p>
   * Method under test: {@link TablePropertiesProvider#getById(String)}
   */
  @Test
  @DisplayName("Test getById(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesProvider.getById(String)"})
  void testGetById2() throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName(null, "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    (new TablePropertiesProvider(new InstanceProperties(), propertiesStore)).getById("42");

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(eq("42"));
  }

  /**
   * Test {@link TablePropertiesProvider#getById(String)}.
   * <p>
   * Method under test: {@link TablePropertiesProvider#getById(String)}
   */
  @Test
  @DisplayName("Test getById(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesProvider.getById(String)"})
  void testGetById3() throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", false));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    (new TablePropertiesProvider(new InstanceProperties(), propertiesStore)).getById("42");

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(eq("42"));
  }

  /**
   * Test {@link TablePropertiesProvider#getById(String)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link TableProperties#getStatus()} return {@code null}.</li>
   *   <li>Then calls {@link SleeperProperties#validate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesProvider#getById(String)}
   */
  @Test
  @DisplayName("Test getById(String); given TableProperties getStatus() return 'null'; then calls validate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesProvider.getById(String)"})
  void testGetById_givenTablePropertiesGetStatusReturnNull_thenCallsValidate()
      throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(null);
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    (new TablePropertiesProvider(new InstanceProperties(), propertiesStore)).getById("42");

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(eq("42"));
  }

  /**
   * Test {@link TablePropertiesProvider#streamAllTables()}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesProvider#streamAllTables()}
   */
  @Test
  @DisplayName("Test streamAllTables(); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream TablePropertiesProvider.streamAllTables()"})
  void testStreamAllTables_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Stream<TableProperties> actualStreamAllTablesResult = (new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)))).streamAllTables();

    // Assert
    assertTrue(actualStreamAllTablesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link TablePropertiesProvider#streamOnlineTables()}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesProvider#streamOnlineTables()}
   */
  @Test
  @DisplayName("Test streamOnlineTables(); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream TablePropertiesProvider.streamOnlineTables()"})
  void testStreamOnlineTables_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Stream<TableProperties> actualStreamOnlineTablesResult = (new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)))).streamOnlineTables();

    // Assert
    assertTrue(actualStreamOnlineTablesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
