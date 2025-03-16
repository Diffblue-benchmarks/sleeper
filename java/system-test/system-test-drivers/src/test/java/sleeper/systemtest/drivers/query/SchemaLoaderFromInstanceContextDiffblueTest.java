package sleeper.systemtest.drivers.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.util.TestContext;

class SchemaLoaderFromInstanceContextDiffblueTest {
  /**
   * Test {@link SchemaLoaderFromInstanceContext#getSchemaByTableName(String)}.
   * <ul>
   *   <li>Then return {@link Optional#get()} AllFieldNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaLoaderFromInstanceContext#getSchemaByTableName(String)}
   */
  @Test
  @DisplayName("Test getSchemaByTableName(String); then return get() AllFieldNames size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional SchemaLoaderFromInstanceContext.getSchemaByTableName(String)"})
  void testGetSchemaByTableName_thenReturnGetAllFieldNamesSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getSchema()).thenReturn(buildResult);
    Optional<TableProperties> ofResult = Optional.of(tableProperties);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTablePropertiesByDeployedName(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    Optional<Schema> actualSchemaByTableName = (new SchemaLoaderFromInstanceContext(instance))
        .getSchemaByTableName("Table Name");

    // Assert
    verify(tableProperties).getSchema();
    verify(instance).getTablePropertiesByDeployedName(eq("Table Name"));
    Schema getResult = actualSchemaByTableName.get();
    List<String> allFieldNames = getResult.getAllFieldNames();
    assertEquals(1, allFieldNames.size());
    assertEquals(1, getResult.getAllFields().size());
    assertEquals(1, getResult.getRowKeyFields().size());
    assertEquals(1, getResult.getRowKeyTypes().size());
    List<String> sortKeyFieldNames = getResult.getSortKeyFieldNames();
    assertTrue(sortKeyFieldNames.isEmpty());
    assertTrue(getResult.getSortKeyFields().isEmpty());
    assertTrue(getResult.getValueFields().isEmpty());
    assertTrue(actualSchemaByTableName.isPresent());
    assertEquals(allFieldNames, getResult.getRowKeyFieldNames());
    assertSame(sortKeyFieldNames, getResult.getSortKeyTypes());
    assertSame(sortKeyFieldNames, getResult.getValueFieldNames());
  }

  /**
   * Test {@link SchemaLoaderFromInstanceContext#getSchemaByTableName(String)}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaLoaderFromInstanceContext#getSchemaByTableName(String)}
   */
  @Test
  @DisplayName("Test getSchemaByTableName(String); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional SchemaLoaderFromInstanceContext.getSchemaByTableName(String)"})
  void testGetSchemaByTableName_thenReturnNotPresent() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    Optional<TableProperties> ofResult = Optional.of(new TableProperties(new InstanceProperties()));
    when(instance.getTablePropertiesByDeployedName(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    Optional<Schema> actualSchemaByTableName = (new SchemaLoaderFromInstanceContext(instance))
        .getSchemaByTableName("Table Name");

    // Assert
    verify(instance).getTablePropertiesByDeployedName(eq("Table Name"));
    assertFalse(actualSchemaByTableName.isPresent());
  }

  /**
   * Test {@link SchemaLoaderFromInstanceContext#getSchemaByTableName(String)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SchemaLoaderFromInstanceContext#getSchemaByTableName(String)}
   */
  @Test
  @DisplayName("Test getSchemaByTableName(String); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional SchemaLoaderFromInstanceContext.getSchemaByTableName(String)"})
  void testGetSchemaByTableName_thenThrowUnsupportedOperationException() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getSchema()).thenThrow(new UnsupportedOperationException("foo"));
    Optional<TableProperties> ofResult = Optional.of(tableProperties);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTablePropertiesByDeployedName(Mockito.<String>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> (new SchemaLoaderFromInstanceContext(instance)).getSchemaByTableName("Table Name"));
    verify(tableProperties).getSchema();
    verify(instance).getTablePropertiesByDeployedName(eq("Table Name"));
  }

  /**
   * Test {@link SchemaLoaderFromInstanceContext#getSchemaByTableId(String)}.
   * <p>
   * Method under test: {@link SchemaLoaderFromInstanceContext#getSchemaByTableId(String)}
   */
  @Test
  @DisplayName("Test getSchemaByTableId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional SchemaLoaderFromInstanceContext.getSchemaByTableId(String)"})
  void testGetSchemaByTableId() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> (new SchemaLoaderFromInstanceContext(new SystemTestInstanceContext(mock(SystemTestParameters.class),
            mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class))))
            .getSchemaByTableId("42"));
  }
}
