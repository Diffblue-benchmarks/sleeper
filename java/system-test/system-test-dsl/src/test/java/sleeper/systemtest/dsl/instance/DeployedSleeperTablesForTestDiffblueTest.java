package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.testutils.InMemoryTransactionLogsPerTable;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperTablesDriver;
import sleeper.systemtest.dsl.util.SystemTestSchema;

class DeployedSleeperTablesForTestDiffblueTest {
  /**
   * Test {@link DeployedSleeperTablesForTest#DeployedSleeperTablesForTest(InstanceProperties, SleeperTablesDriver)}.
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#DeployedSleeperTablesForTest(InstanceProperties, SleeperTablesDriver)}
   */
  @Test
  @DisplayName("Test new DeployedSleeperTablesForTest(InstanceProperties, SleeperTablesDriver)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSleeperTablesForTest.<init>(InstanceProperties, SleeperTablesDriver)"})
  void testNewDeployedSleeperTablesForTest() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    SleeperTablesDriver driver = mock(SleeperTablesDriver.class);
    InstanceProperties instanceProperties2 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    when(driver.createTablePropertiesProvider(Mockito.<InstanceProperties>any())).thenReturn(tablePropertiesProvider);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    when(driver.createStateStoreProvider(Mockito.<InstanceProperties>any())).thenReturn(stateStoreProvider);

    // Act
    DeployedSleeperTablesForTest actualDeployedSleeperTablesForTest = new DeployedSleeperTablesForTest(
        instanceProperties, driver);

    // Assert
    verify(driver).createStateStoreProvider(isA(InstanceProperties.class));
    verify(driver).createTablePropertiesProvider(isA(InstanceProperties.class));
    assertSame(tablePropertiesProvider, actualDeployedSleeperTablesForTest.getTablePropertiesProvider());
    assertSame(stateStoreProvider, actualDeployedSleeperTablesForTest.getStateStoreProvider());
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#DeployedSleeperTablesForTest(InstanceProperties, SleeperTablesDriver)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#DeployedSleeperTablesForTest(InstanceProperties, SleeperTablesDriver)}
   */
  @Test
  @DisplayName("Test new DeployedSleeperTablesForTest(InstanceProperties, SleeperTablesDriver); given one; then calls getInt(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSleeperTablesForTest.<init>(InstanceProperties, SleeperTablesDriver)"})
  void testNewDeployedSleeperTablesForTest_givenOne_thenCallsGetInt() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new DeployedSleeperTablesForTest(instanceProperties,
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()));

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#addTablesAndSetCurrent(SleeperTablesDriver, List)}.
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#addTablesAndSetCurrent(SleeperTablesDriver, List)}
   */
  @Test
  @DisplayName("Test addTablesAndSetCurrent(SleeperTablesDriver, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSleeperTablesForTest.addTablesAndSetCurrent(SleeperTablesDriver, List)"})
  void testAddTablesAndSetCurrent() {
    // Arrange
    InMemorySleeperTablesDriver driver = mock(InMemorySleeperTablesDriver.class);
    InstanceProperties instanceProperties = new InstanceProperties();
    when(driver.createTablePropertiesProvider(Mockito.<InstanceProperties>any()))
        .thenReturn(new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    when(driver.createStateStoreProvider(Mockito.<InstanceProperties>any()))
        .thenReturn(new StateStoreProvider(3, mock(Factory.class)));
    DeployedSleeperTablesForTest deployedSleeperTablesForTest = new DeployedSleeperTablesForTest(
        new InstanceProperties(), driver);
    InMemorySleeperTablesDriver driver2 = new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable());

    // Act
    deployedSleeperTablesForTest.addTablesAndSetCurrent(driver2, new ArrayList<>());

    // Assert
    verify(driver).createStateStoreProvider(isA(InstanceProperties.class));
    verify(driver).createTablePropertiesProvider(isA(InstanceProperties.class));
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#addTablesAndSetCurrent(SleeperTablesDriver, List)}.
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#addTablesAndSetCurrent(SleeperTablesDriver, List)}
   */
  @Test
  @DisplayName("Test addTablesAndSetCurrent(SleeperTablesDriver, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSleeperTablesForTest.addTablesAndSetCurrent(SleeperTablesDriver, List)"})
  void testAddTablesAndSetCurrent2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(mock(StateStore.class));
    StateStoreProvider stateStoreProvider = new StateStoreProvider(0, stateStoreFactory);

    InMemorySleeperTablesDriver driver = mock(InMemorySleeperTablesDriver.class);
    InstanceProperties instanceProperties2 = new InstanceProperties();
    when(driver.createTablePropertiesProvider(Mockito.<InstanceProperties>any()))
        .thenReturn(new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    when(driver.createStateStoreProvider(Mockito.<InstanceProperties>any())).thenReturn(stateStoreProvider);
    DeployedSleeperTablesForTest deployedSleeperTablesForTest = new DeployedSleeperTablesForTest(instanceProperties,
        driver);
    SleeperTablesDriver driver2 = mock(SleeperTablesDriver.class);
    doNothing().when(driver2).addTable(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any());
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(tableProperties);

    // Act
    deployedSleeperTablesForTest.addTablesAndSetCurrent(driver2, tables);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(driver2).addTable(isA(InstanceProperties.class), isA(TableProperties.class));
    verify(driver).createStateStoreProvider(isA(InstanceProperties.class));
    verify(driver).createTablePropertiesProvider(isA(InstanceProperties.class));
    assertNull(deployedSleeperTablesForTest.getSchema());
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#addTablesAndSetCurrent(SleeperTablesDriver, List)}.
   * <ul>
   *   <li>Then calls {@link Factory#getStateStore(TableProperties)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#addTablesAndSetCurrent(SleeperTablesDriver, List)}
   */
  @Test
  @DisplayName("Test addTablesAndSetCurrent(SleeperTablesDriver, List); then calls getStateStore(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSleeperTablesForTest.addTablesAndSetCurrent(SleeperTablesDriver, List)"})
  void testAddTablesAndSetCurrent_thenCallsGetStateStore() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(mock(StateStore.class));
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    InMemorySleeperTablesDriver driver = mock(InMemorySleeperTablesDriver.class);
    InstanceProperties instanceProperties2 = new InstanceProperties();
    when(driver.createTablePropertiesProvider(Mockito.<InstanceProperties>any()))
        .thenReturn(new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    when(driver.createStateStoreProvider(Mockito.<InstanceProperties>any())).thenReturn(stateStoreProvider);
    DeployedSleeperTablesForTest deployedSleeperTablesForTest = new DeployedSleeperTablesForTest(instanceProperties,
        driver);
    SleeperTablesDriver driver2 = mock(SleeperTablesDriver.class);
    doNothing().when(driver2).addTable(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any());
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(tableProperties);

    // Act
    deployedSleeperTablesForTest.addTablesAndSetCurrent(driver2, tables);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(driver2).addTable(isA(InstanceProperties.class), isA(TableProperties.class));
    verify(driver).createStateStoreProvider(isA(InstanceProperties.class));
    verify(driver).createTablePropertiesProvider(isA(InstanceProperties.class));
    assertNull(deployedSleeperTablesForTest.getSchema());
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#addTablesAndSetCurrent(SleeperTablesDriver, List)}.
   * <ul>
   *   <li>Then calls {@link StateStoreProvider#getStateStore(TableProperties)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#addTablesAndSetCurrent(SleeperTablesDriver, List)}
   */
  @Test
  @DisplayName("Test addTablesAndSetCurrent(SleeperTablesDriver, List); then calls getStateStore(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSleeperTablesForTest.addTablesAndSetCurrent(SleeperTablesDriver, List)"})
  void testAddTablesAndSetCurrent_thenCallsGetStateStore2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(mock(StateStore.class));
    InMemorySleeperTablesDriver driver = mock(InMemorySleeperTablesDriver.class);
    InstanceProperties instanceProperties2 = new InstanceProperties();
    when(driver.createTablePropertiesProvider(Mockito.<InstanceProperties>any()))
        .thenReturn(new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    when(driver.createStateStoreProvider(Mockito.<InstanceProperties>any())).thenReturn(stateStoreProvider);
    DeployedSleeperTablesForTest deployedSleeperTablesForTest = new DeployedSleeperTablesForTest(instanceProperties,
        driver);
    SleeperTablesDriver driver2 = mock(SleeperTablesDriver.class);
    doNothing().when(driver2).addTable(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any());
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(tableProperties);

    // Act
    deployedSleeperTablesForTest.addTablesAndSetCurrent(driver2, tables);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(driver2).addTable(isA(InstanceProperties.class), isA(TableProperties.class));
    verify(driver).createStateStoreProvider(isA(InstanceProperties.class));
    verify(driver).createTablePropertiesProvider(isA(InstanceProperties.class));
    assertNull(deployedSleeperTablesForTest.getSchema());
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#addTablesAndSetCurrent(SleeperTablesDriver, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#addTablesAndSetCurrent(SleeperTablesDriver, List)}
   */
  @Test
  @DisplayName("Test addTablesAndSetCurrent(SleeperTablesDriver, List); when ArrayList(); then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSleeperTablesForTest.addTablesAndSetCurrent(SleeperTablesDriver, List)"})
  void testAddTablesAndSetCurrent_whenArrayList_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    InMemorySleeperTablesDriver driver = mock(InMemorySleeperTablesDriver.class);
    InstanceProperties instanceProperties2 = new InstanceProperties();
    when(driver.createTablePropertiesProvider(Mockito.<InstanceProperties>any()))
        .thenReturn(new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    when(driver.createStateStoreProvider(Mockito.<InstanceProperties>any()))
        .thenReturn(new StateStoreProvider(3, mock(Factory.class)));
    DeployedSleeperTablesForTest deployedSleeperTablesForTest = new DeployedSleeperTablesForTest(instanceProperties,
        driver);
    InMemorySleeperTablesDriver driver2 = new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable());

    // Act
    deployedSleeperTablesForTest.addTablesAndSetCurrent(driver2, new ArrayList<>());

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).createStateStoreProvider(isA(InstanceProperties.class));
    verify(driver).createTablePropertiesProvider(isA(InstanceProperties.class));
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#getTablePropertiesByName(String)}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#getTablePropertiesByName(String)}
   */
  @Test
  @DisplayName("Test getTablePropertiesByName(String); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DeployedSleeperTablesForTest.getTablePropertiesByName(String)"})
  void testGetTablePropertiesByName_thenReturnNotPresent() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    Optional<TableProperties> actualTablePropertiesByName = (new DeployedSleeperTablesForTest(instanceProperties,
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()))).getTablePropertiesByName("Table Name");

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    assertFalse(actualTablePropertiesByName.isPresent());
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#getTablePropertiesById(String)}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#getTablePropertiesById(String)}
   */
  @Test
  @DisplayName("Test getTablePropertiesById(String); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DeployedSleeperTablesForTest.getTablePropertiesById(String)"})
  void testGetTablePropertiesById_thenReturnNotPresent() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    Optional<TableProperties> actualTablePropertiesById = (new DeployedSleeperTablesForTest(instanceProperties,
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()))).getTablePropertiesById("42");

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    assertFalse(actualTablePropertiesById.isPresent());
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#getTableProperties()}.
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#getTableProperties()}
   */
  @Test
  @DisplayName("Test getTableProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties DeployedSleeperTablesForTest.getTableProperties()"})
  void testGetTableProperties() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    DeployedSleeperTablesForTest deployedSleeperTablesForTest = new DeployedSleeperTablesForTest(instanceProperties,
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()));
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    deployedSleeperTablesForTest.setCurrent(tableProperties);

    // Act
    TableProperties actualTableProperties = deployedSleeperTablesForTest.getTableProperties();

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    assertSame(tableProperties, actualTableProperties);
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#getSchema()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#getSchema()}
   */
  @Test
  @DisplayName("Test getSchema(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Schema DeployedSleeperTablesForTest.getSchema()"})
  void testGetSchema_thenReturnNull() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    DeployedSleeperTablesForTest deployedSleeperTablesForTest = new DeployedSleeperTablesForTest(instanceProperties,
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()));
    deployedSleeperTablesForTest.setCurrent(new TableProperties(new InstanceProperties()));

    // Act
    Schema actualSchema = deployedSleeperTablesForTest.getSchema();

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    assertNull(actualSchema);
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#getSchema()}.
   * <ul>
   *   <li>Then return RowKeyFieldNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#getSchema()}
   */
  @Test
  @DisplayName("Test getSchema(); then return RowKeyFieldNames size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Schema DeployedSleeperTablesForTest.getSchema()"})
  void testGetSchema_thenReturnRowKeyFieldNamesSizeIsOne() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getSchema()).thenReturn(SystemTestSchema.DEFAULT_SCHEMA);

    DeployedSleeperTablesForTest deployedSleeperTablesForTest = new DeployedSleeperTablesForTest(instanceProperties,
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()));
    deployedSleeperTablesForTest.setCurrent(tableProperties);

    // Act
    Schema actualSchema = deployedSleeperTablesForTest.getSchema();

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    verify(tableProperties).getSchema();
    assertEquals(1, actualSchema.getRowKeyFieldNames().size());
    assertEquals(1, actualSchema.getRowKeyFields().size());
    assertEquals(1, actualSchema.getRowKeyTypes().size());
    assertEquals(1, actualSchema.getSortKeyFieldNames().size());
    assertEquals(1, actualSchema.getSortKeyFields().size());
    assertEquals(1, actualSchema.getSortKeyTypes().size());
    assertEquals(1, actualSchema.getValueFieldNames().size());
    assertEquals(1, actualSchema.getValueFields().size());
    assertEquals(3, actualSchema.getAllFieldNames().size());
    assertEquals(3, actualSchema.getAllFields().size());
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#getSchema()}.
   * <ul>
   *   <li>Then throw {@link NoTableChosenException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#getSchema()}
   */
  @Test
  @DisplayName("Test getSchema(); then throw NoTableChosenException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Schema DeployedSleeperTablesForTest.getSchema()"})
  void testGetSchema_thenThrowNoTableChosenException() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getSchema()).thenThrow(new NoTableChosenException());

    DeployedSleeperTablesForTest deployedSleeperTablesForTest = new DeployedSleeperTablesForTest(instanceProperties,
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()));
    deployedSleeperTablesForTest.setCurrent(tableProperties);

    // Act and Assert
    assertThrows(NoTableChosenException.class, () -> deployedSleeperTablesForTest.getSchema());
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    verify(tableProperties).getSchema();
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#getStateStore(TableProperties)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link TableProperties} {@link TableProperties#get(TableProperty)} return {@code Get}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#getStateStore(TableProperties)}
   */
  @Test
  @DisplayName("Test getStateStore(TableProperties); given 'Get'; when TableProperties get(TableProperty) return 'Get'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStore DeployedSleeperTablesForTest.getStateStore(TableProperties)"})
  void testGetStateStore_givenGet_whenTablePropertiesGetReturnGet_thenReturnNull() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    DeployedSleeperTablesForTest deployedSleeperTablesForTest = new DeployedSleeperTablesForTest(instanceProperties,
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()));
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    // Act
    StateStore actualStateStore = deployedSleeperTablesForTest.getStateStore(tableProperties);

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    assertNull(actualStateStore);
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#streamTableNames()}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#streamTableNames()}
   */
  @Test
  @DisplayName("Test streamTableNames(); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DeployedSleeperTablesForTest.streamTableNames()"})
  void testStreamTableNames_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    Stream<String> actualStreamTableNamesResult = (new DeployedSleeperTablesForTest(instanceProperties,
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()))).streamTableNames();

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    assertTrue(actualStreamTableNamesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DeployedSleeperTablesForTest#streamTableProperties()}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperTablesForTest#streamTableProperties()}
   */
  @Test
  @DisplayName("Test streamTableProperties(); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DeployedSleeperTablesForTest.streamTableProperties()"})
  void testStreamTableProperties_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    Stream<TableProperties> actualStreamTablePropertiesResult = (new DeployedSleeperTablesForTest(instanceProperties,
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()))).streamTableProperties();

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    assertTrue(actualStreamTablePropertiesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
