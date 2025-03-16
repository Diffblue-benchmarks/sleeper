package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.schema.Schema;
import sleeper.core.table.TableStatus;
import sleeper.systemtest.dsl.util.SystemTestSchema;
import sleeper.systemtest.dsl.util.TestContext;

class SystemTestTablesDiffblueTest {
  /**
   * Test {@link SystemTestTables#createMany(int, Schema)}.
   * <ul>
   *   <li>Then calls {@link SystemTestInstanceContext#createTables(int, Schema, Map)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTables#createMany(int, Schema)}
   */
  @Test
  @DisplayName("Test createMany(int, Schema); then calls createTables(int, Schema, Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestTables.createMany(int, Schema)"})
  void testCreateMany_thenCallsCreateTables() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    doNothing().when(instance).createTables(anyInt(), Mockito.<Schema>any(), Mockito.<Map<TableProperty, String>>any());

    // Act
    (new SystemTestTables(instance)).createMany(10, SystemTestSchema.DEFAULT_SCHEMA);

    // Assert
    verify(instance).createTables(eq(10), isA(Schema.class), isA(Map.class));
  }

  /**
   * Test {@link SystemTestTables#create(String, Schema)} with {@code name}, {@code schema}.
   * <p>
   * Method under test: {@link SystemTestTables#create(String, Schema)}
   */
  @Test
  @DisplayName("Test create(String, Schema) with 'name', 'schema'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestTables SystemTestTables.create(String, Schema)"})
  void testCreateWithNameSchema() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    doNothing().when(instance)
        .createTable(Mockito.<String>any(), Mockito.<Schema>any(), Mockito.<Map<TableProperty, String>>any());
    SystemTestTables systemTestTables = new SystemTestTables(instance);

    // Act
    SystemTestTables actualCreateResult = systemTestTables.create("Name", SystemTestSchema.DEFAULT_SCHEMA);

    // Assert
    verify(instance).createTable(eq("Name"), isA(Schema.class), isA(Map.class));
    assertSame(systemTestTables, actualCreateResult);
  }

  /**
   * Test {@link SystemTestTables#create(List, Schema)} with {@code names}, {@code schema}.
   * <p>
   * Method under test: {@link SystemTestTables#create(List, Schema)}
   */
  @Test
  @DisplayName("Test create(List, Schema) with 'names', 'schema'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestTables SystemTestTables.create(List, Schema)"})
  void testCreateWithNamesSchema() {
    // Arrange
    SystemTestTables systemTestTables = new SystemTestTables(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)));

    // Act and Assert
    assertSame(systemTestTables, systemTestTables.create(new ArrayList<>(), SystemTestSchema.DEFAULT_SCHEMA));
  }

  /**
   * Test {@link SystemTestTables#createWithProperties(String, Schema, Map)} with {@code name}, {@code schema}, {@code setProperties}.
   * <p>
   * Method under test: {@link SystemTestTables#createWithProperties(String, Schema, Map)}
   */
  @Test
  @DisplayName("Test createWithProperties(String, Schema, Map) with 'name', 'schema', 'setProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestTables SystemTestTables.createWithProperties(String, Schema, Map)"})
  void testCreateWithPropertiesWithNameSchemaSetProperties() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    doNothing().when(instance)
        .createTable(Mockito.<String>any(), Mockito.<Schema>any(), Mockito.<Map<TableProperty, String>>any());
    SystemTestTables systemTestTables = new SystemTestTables(instance);

    // Act
    SystemTestTables actualCreateWithPropertiesResult = systemTestTables.createWithProperties("Name",
        SystemTestSchema.DEFAULT_SCHEMA, new HashMap<>());

    // Assert
    verify(instance).createTable(eq("Name"), isA(Schema.class), isA(Map.class));
    assertSame(systemTestTables, actualCreateWithPropertiesResult);
  }

  /**
   * Test {@link SystemTestTables#createWithProperties(List, Schema, Map)} with {@code names}, {@code schema}, {@code setProperties}.
   * <p>
   * Method under test: {@link SystemTestTables#createWithProperties(List, Schema, Map)}
   */
  @Test
  @DisplayName("Test createWithProperties(List, Schema, Map) with 'names', 'schema', 'setProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestTables SystemTestTables.createWithProperties(List, Schema, Map)"})
  void testCreateWithPropertiesWithNamesSchemaSetProperties() {
    // Arrange
    SystemTestTables systemTestTables = new SystemTestTables(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)));
    ArrayList<String> names = new ArrayList<>();

    // Act and Assert
    assertSame(systemTestTables,
        systemTestTables.createWithProperties(names, SystemTestSchema.DEFAULT_SCHEMA, new HashMap<>()));
  }

  /**
   * Test {@link SystemTestTables#createManyWithProperties(int, Schema, Map)}.
   * <ul>
   *   <li>Then calls {@link SystemTestInstanceContext#createTables(int, Schema, Map)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTables#createManyWithProperties(int, Schema, Map)}
   */
  @Test
  @DisplayName("Test createManyWithProperties(int, Schema, Map); then calls createTables(int, Schema, Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestTables.createManyWithProperties(int, Schema, Map)"})
  void testCreateManyWithProperties_thenCallsCreateTables() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    doNothing().when(instance).createTables(anyInt(), Mockito.<Schema>any(), Mockito.<Map<TableProperty, String>>any());
    SystemTestTables systemTestTables = new SystemTestTables(instance);

    // Act
    systemTestTables.createManyWithProperties(10, SystemTestSchema.DEFAULT_SCHEMA, new HashMap<>());

    // Assert
    verify(instance).createTables(eq(10), isA(Schema.class), isA(Map.class));
  }

  /**
   * Test {@link SystemTestTables#list()}.
   * <ul>
   *   <li>Given {@link SystemTestInstanceContext} {@link SystemTestInstanceContext#loadTables()} return {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTables#list()}
   */
  @Test
  @DisplayName("Test list(); given SystemTestInstanceContext loadTables() return ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SystemTestTables.list()"})
  void testList_givenSystemTestInstanceContextLoadTablesReturnArrayList_thenReturnEmpty() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.loadTables()).thenReturn(new ArrayList<>());

    // Act
    List<TableStatus> actualListResult = (new SystemTestTables(instance)).list();

    // Assert
    verify(instance).loadTables();
    assertTrue(actualListResult.isEmpty());
  }

  /**
   * Test {@link SystemTestTables#forEach(Runnable)}.
   * <ul>
   *   <li>Then calls {@link Runnable#run()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTables#forEach(Runnable)}
   */
  @Test
  @DisplayName("Test forEach(Runnable); then calls run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestTables.forEach(Runnable)"})
  void testForEach_thenCallsRun() {
    // Arrange
    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(new TableProperties(new InstanceProperties()));
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.streamTableProperties()).thenReturn(streamResult);
    doNothing().when(instance).setCurrentTable(Mockito.<TableProperties>any());
    SystemTestTables systemTestTables = new SystemTestTables(instance);
    Runnable runnable = mock(Runnable.class);
    doNothing().when(runnable).run();

    // Act
    systemTestTables.forEach(runnable);

    // Assert
    verify(runnable).run();
    verify(instance, atLeast(1)).setCurrentTable(Mockito.<TableProperties>any());
    verify(instance).streamTableProperties();
  }

  /**
   * Test {@link SystemTestTables#forEach(Runnable)}.
   * <ul>
   *   <li>Then calls {@link Runnable#run()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTables#forEach(Runnable)}
   */
  @Test
  @DisplayName("Test forEach(Runnable); then calls run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestTables.forEach(Runnable)"})
  void testForEach_thenCallsRun2() {
    // Arrange
    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(new TableProperties(new InstanceProperties()));
    tablePropertiesList.add(new TableProperties(new InstanceProperties()));
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.streamTableProperties()).thenReturn(streamResult);
    doNothing().when(instance).setCurrentTable(Mockito.<TableProperties>any());
    SystemTestTables systemTestTables = new SystemTestTables(instance);
    Runnable runnable = mock(Runnable.class);
    doNothing().when(runnable).run();

    // Act
    systemTestTables.forEach(runnable);

    // Assert
    verify(runnable, atLeast(1)).run();
    verify(instance, atLeast(1)).setCurrentTable(Mockito.<TableProperties>any());
    verify(instance).streamTableProperties();
  }

  /**
   * Test {@link SystemTestTables#forEach(Runnable)}.
   * <ul>
   *   <li>When {@link Runnable}.</li>
   *   <li>Then calls {@link SystemTestInstanceContext#setCurrentTable(TableProperties)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestTables#forEach(Runnable)}
   */
  @Test
  @DisplayName("Test forEach(Runnable); when Runnable; then calls setCurrentTable(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestTables.forEach(Runnable)"})
  void testForEach_whenRunnable_thenCallsSetCurrentTable() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    when(instance.streamTableProperties()).thenReturn(streamResult);
    doNothing().when(instance).setCurrentTable(Mockito.<TableProperties>any());

    // Act
    (new SystemTestTables(instance)).forEach(mock(Runnable.class));

    // Assert
    verify(instance).setCurrentTable((TableProperties) isNull());
    verify(instance).streamTableProperties();
  }
}
