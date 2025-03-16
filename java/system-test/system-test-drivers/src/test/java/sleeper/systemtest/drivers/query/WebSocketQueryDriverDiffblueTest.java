package sleeper.systemtest.drivers.query;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.query.QueryAllTablesDriver;
import sleeper.systemtest.dsl.query.QueryAllTablesInParallelDriver;

class WebSocketQueryDriverDiffblueTest {
  /**
   * Test {@link WebSocketQueryDriver#allTablesDriver(SystemTestInstanceContext)}.
   * <ul>
   *   <li>Then return {@link QueryAllTablesInParallelDriver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketQueryDriver#allTablesDriver(SystemTestInstanceContext)}
   */
  @Test
  @DisplayName("Test allTablesDriver(SystemTestInstanceContext); then return QueryAllTablesInParallelDriver")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryAllTablesDriver WebSocketQueryDriver.allTablesDriver(SystemTestInstanceContext)"})
  void testAllTablesDriver_thenReturnQueryAllTablesInParallelDriver() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    InstanceProperties instanceProperties2 = new InstanceProperties();
    when(instance.getTablePropertiesProvider()).thenReturn(new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act
    QueryAllTablesDriver actualAllTablesDriverResult = WebSocketQueryDriver.allTablesDriver(instance);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(instance).getInstanceProperties();
    verify(instance).getTablePropertiesProvider();
    assertTrue(actualAllTablesDriverResult instanceof QueryAllTablesInParallelDriver);
  }
}
