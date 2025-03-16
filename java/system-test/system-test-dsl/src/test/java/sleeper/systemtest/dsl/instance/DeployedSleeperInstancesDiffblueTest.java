package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.deploy.DeployInstanceConfiguration;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.instance.SystemTestParameters.Builder;
import sleeper.systemtest.dsl.testutil.InMemorySystemTestDrivers;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySystemTestDeploymentDriver;
import sleeper.systemtest.dsl.util.NoScheduleRulesDriver;

class DeployedSleeperInstancesDiffblueTest {
  /**
   * Test {@link DeployedSleeperInstances#connectToAndReset(SystemTestInstanceConfiguration)}.
   * <ul>
   *   <li>Then throw {@link InstanceDidNotDeployException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperInstances#connectToAndReset(SystemTestInstanceConfiguration)}
   */
  @Test
  @DisplayName("Test connectToAndReset(SystemTestInstanceConfiguration); then throw InstanceDidNotDeployException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.systemtest.dsl.instance.DeployedSleeperInstance DeployedSleeperInstances.connectToAndReset(SystemTestInstanceConfiguration)"})
  void testConnectToAndReset_thenThrowInstanceDidNotDeployException() {
    // Arrange
    System.getProperty("java.io.tmpdir");
    SleeperInstanceDriver instanceDriver = mock(SleeperInstanceDriver.class);
    doNothing().when(instanceDriver).resetOnFirstConnect(Mockito.<InstanceProperties>any());
    when(instanceDriver.deployInstanceIfNotPresent(Mockito.<String>any(), Mockito.<DeployInstanceConfiguration>any()))
        .thenReturn(true);
    doNothing().when(instanceDriver).loadInstanceProperties(Mockito.<InstanceProperties>any(), Mockito.<String>any());
    AssumeAdminRoleDriver assumeRoleDriver = mock(AssumeAdminRoleDriver.class);
    when(assumeRoleDriver.assumeAdminRole(Mockito.<InstanceProperties>any()))
        .thenReturn(new InMemorySystemTestDrivers());
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(null)
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    Builder forceStateStoreClassnameResult2 = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult2 = forceStateStoreClassnameResult2.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters2 = shortTestIdResult2
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters2,
        new InMemorySystemTestDeploymentDriver());

    DeployedSleeperInstances deployedSleeperInstances = new DeployedSleeperInstances(parameters, systemTest,
        instanceDriver, assumeRoleDriver, new NoScheduleRulesDriver());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any()))
        .thenThrow(new InstanceDidNotDeployException("42", new Throwable()));
    when(instanceProperties.isSet(Mockito.<InstanceProperty>any())).thenReturn(true);
    DeployInstanceConfiguration.Builder instancePropertiesResult = DeployInstanceConfiguration.builder()
        .instanceProperties(instanceProperties);
    DeployInstanceConfiguration buildResult = instancePropertiesResult.tableProperties(new ArrayList<>()).build();
    SystemTestInstanceConfiguration configuration = mock(SystemTestInstanceConfiguration.class);
    when(configuration.buildDeployConfig(Mockito.<SystemTestParameters>any(),
        Mockito.<DeployedSystemTestResources>any())).thenReturn(buildResult);
    when(configuration.getShortName()).thenReturn("Short Name");

    // Act and Assert
    assertThrows(InstanceDidNotDeployException.class, () -> deployedSleeperInstances.connectToAndReset(configuration));
    verify(instanceProperties).isSet(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(assumeRoleDriver).assumeAdminRole(isA(InstanceProperties.class));
    verify(instanceDriver).deployInstanceIfNotPresent(eq("42-Short Name"), isA(DeployInstanceConfiguration.class));
    verify(instanceDriver).loadInstanceProperties(isA(InstanceProperties.class), eq("42-Short Name"));
    verify(instanceDriver).resetOnFirstConnect(isA(InstanceProperties.class));
    verify(configuration).buildDeployConfig(isA(SystemTestParameters.class), isA(DeployedSystemTestResources.class));
    verify(configuration).getShortName();
  }
}
