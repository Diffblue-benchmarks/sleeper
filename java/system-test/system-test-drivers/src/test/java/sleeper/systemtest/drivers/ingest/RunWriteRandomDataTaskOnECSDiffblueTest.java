package sleeper.systemtest.drivers.ingest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
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
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.systemtest.configuration.SystemTestProperties;
import sleeper.systemtest.configuration.SystemTestProperty;
import sleeper.systemtest.configuration.SystemTestPropertyValues;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import software.amazon.awssdk.services.ecs.EcsClient;

class RunWriteRandomDataTaskOnECSDiffblueTest {
  /**
   * Test {@link RunWriteRandomDataTaskOnECS#RunWriteRandomDataTaskOnECS(InstanceProperties, TableProperties, SystemTestStandaloneProperties, EcsClient)}.
   * <ul>
   *   <li>Then return run Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunWriteRandomDataTaskOnECS#RunWriteRandomDataTaskOnECS(InstanceProperties, TableProperties, SystemTestStandaloneProperties, EcsClient)}
   */
  @Test
  @DisplayName("Test new RunWriteRandomDataTaskOnECS(InstanceProperties, TableProperties, SystemTestStandaloneProperties, EcsClient); then return run Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void RunWriteRandomDataTaskOnECS.<init>(InstanceProperties, TableProperties, SystemTestStandaloneProperties, EcsClient)"})
  void testNewRunWriteRandomDataTaskOnECS_thenReturnRunEmpty() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    SystemTestStandaloneProperties systemTestProperties = mock(SystemTestStandaloneProperties.class);
    when(systemTestProperties.get(Mockito.<SystemTestProperty>any())).thenReturn("Get");

    // Act
    RunWriteRandomDataTaskOnECS actualRunWriteRandomDataTaskOnECS = new RunWriteRandomDataTaskOnECS(instanceProperties,
        tableProperties, systemTestProperties, mock(EcsClient.class));

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
    verify(systemTestProperties).get(isA(SystemTestProperty.class));
    assertTrue(actualRunWriteRandomDataTaskOnECS.run().isEmpty());
  }

  /**
   * Test {@link RunWriteRandomDataTaskOnECS#RunWriteRandomDataTaskOnECS(SystemTestProperties, TableProperties, EcsClient)}.
   * <ul>
   *   <li>Then return run Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunWriteRandomDataTaskOnECS#RunWriteRandomDataTaskOnECS(SystemTestProperties, TableProperties, EcsClient)}
   */
  @Test
  @DisplayName("Test new RunWriteRandomDataTaskOnECS(SystemTestProperties, TableProperties, EcsClient); then return run Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunWriteRandomDataTaskOnECS.<init>(SystemTestProperties, TableProperties, EcsClient)"})
  void testNewRunWriteRandomDataTaskOnECS_thenReturnRunEmpty2() {
    // Arrange
    SystemTestProperties systemTestProperties = mock(SystemTestProperties.class);
    when(systemTestProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(systemTestProperties.testPropertiesOnly()).thenReturn(mock(SystemTestPropertyValues.class));
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    // Act
    RunWriteRandomDataTaskOnECS actualRunWriteRandomDataTaskOnECS = new RunWriteRandomDataTaskOnECS(
        systemTestProperties, tableProperties, mock(EcsClient.class));

    // Assert
    verify(systemTestProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
    verify(systemTestProperties).testPropertiesOnly();
    assertTrue(actualRunWriteRandomDataTaskOnECS.run().isEmpty());
  }
}
