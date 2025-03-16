package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.deploy.DeployInstanceConfiguration;
import sleeper.core.deploy.DeployInstanceConfiguration.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.testutil.InMemorySystemTestDrivers;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySystemTestDeploymentDriver;
import sleeper.systemtest.dsl.util.NoScheduleRulesDriver;

class DeployedSleeperInstanceDiffblueTest {
  /**
   * Test {@link DeployedSleeperInstance#loadOrDeployAtFirstConnect(String, SystemTestInstanceConfiguration, SystemTestParameters, DeployedSystemTestResources, SleeperInstanceDriver, AssumeAdminRoleDriver, ScheduleRulesDriver)}.
   * <p>
   * Method under test: {@link DeployedSleeperInstance#loadOrDeployAtFirstConnect(String, SystemTestInstanceConfiguration, SystemTestParameters, DeployedSystemTestResources, SleeperInstanceDriver, AssumeAdminRoleDriver, ScheduleRulesDriver)}
   */
  @Test
  @DisplayName("Test loadOrDeployAtFirstConnect(String, SystemTestInstanceConfiguration, SystemTestParameters, DeployedSystemTestResources, SleeperInstanceDriver, AssumeAdminRoleDriver, ScheduleRulesDriver)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DeployedSleeperInstance DeployedSleeperInstance.loadOrDeployAtFirstConnect(String, SystemTestInstanceConfiguration, SystemTestParameters, DeployedSystemTestResources, SleeperInstanceDriver, AssumeAdminRoleDriver, ScheduleRulesDriver)"})
  void testLoadOrDeployAtFirstConnect() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(instanceProperties.isSet(Mockito.<InstanceProperty>any())).thenReturn(true);
    Builder instancePropertiesResult = DeployInstanceConfiguration.builder().instanceProperties(instanceProperties);
    DeployInstanceConfiguration buildResult = instancePropertiesResult.tableProperties(new ArrayList<>()).build();
    SystemTestInstanceConfiguration configuration = mock(SystemTestInstanceConfiguration.class);
    when(configuration.buildDeployConfig(Mockito.<SystemTestParameters>any(),
        Mockito.<DeployedSystemTestResources>any())).thenReturn(buildResult);
    SystemTestParameters.Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    SystemTestParameters.Builder shortTestIdResult = forceStateStoreClassnameResult
        .instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
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
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    SleeperInstanceDriver driver = mock(SleeperInstanceDriver.class);
    doNothing().when(driver).resetOnFirstConnect(Mockito.<InstanceProperties>any());
    doNothing().when(driver).saveInstanceProperties(Mockito.<InstanceProperties>any());
    when(driver.deployInstanceIfNotPresent(Mockito.<String>any(), Mockito.<DeployInstanceConfiguration>any()))
        .thenReturn(true);
    doNothing().when(driver).loadInstanceProperties(Mockito.<InstanceProperties>any(), Mockito.<String>any());
    AssumeAdminRoleDriver assumeRoleDriver = mock(AssumeAdminRoleDriver.class);
    when(assumeRoleDriver.assumeAdminRole(Mockito.<InstanceProperties>any()))
        .thenReturn(new InMemorySystemTestDrivers());

    // Act
    DeployedSleeperInstance actualLoadOrDeployAtFirstConnectResult = DeployedSleeperInstance.loadOrDeployAtFirstConnect(
        "42", configuration, null, systemTest, driver, assumeRoleDriver, new NoScheduleRulesDriver());

    // Assert
    verify(instanceProperties, atLeast(1)).isSet(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(assumeRoleDriver).assumeAdminRole(isA(InstanceProperties.class));
    verify(driver).deployInstanceIfNotPresent(eq("42"), isA(DeployInstanceConfiguration.class));
    verify(driver).loadInstanceProperties(isA(InstanceProperties.class), eq("42"));
    verify(driver).resetOnFirstConnect(isA(InstanceProperties.class));
    verify(driver).saveInstanceProperties(isA(InstanceProperties.class));
    verify(configuration).buildDeployConfig(isNull(), isA(DeployedSystemTestResources.class));
    InstanceProperties instanceProperties2 = actualLoadOrDeployAtFirstConnectResult.getInstanceProperties();
    Properties properties = instanceProperties2.getProperties();
    assertEquals(187, properties.size());
    assertEquals("Get", properties.get("sleeper.default.bulk.import.emr.master.x86.instance.types"));
    assertEquals("Get", properties.get("sleeper.default.bulk.import.job.files.commit.async"));
    assertEquals("Get", properties.get("sleeper.statestore.transaction.deletion.lambda.timeout.seconds"));
    assertEquals("Get", properties.get("sleeper.vpc.endpoint.check"));
    assertTrue(instanceProperties2.getTagsProperties().isEmpty());
    assertEquals(properties, instanceProperties2.toMap());
  }

  /**
   * Test {@link DeployedSleeperInstance#loadOrDeployAtFirstConnect(String, SystemTestInstanceConfiguration, SystemTestParameters, DeployedSystemTestResources, SleeperInstanceDriver, AssumeAdminRoleDriver, ScheduleRulesDriver)}.
   * <ul>
   *   <li>Then return InstanceProperties is {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperInstance#loadOrDeployAtFirstConnect(String, SystemTestInstanceConfiguration, SystemTestParameters, DeployedSystemTestResources, SleeperInstanceDriver, AssumeAdminRoleDriver, ScheduleRulesDriver)}
   */
  @Test
  @DisplayName("Test loadOrDeployAtFirstConnect(String, SystemTestInstanceConfiguration, SystemTestParameters, DeployedSystemTestResources, SleeperInstanceDriver, AssumeAdminRoleDriver, ScheduleRulesDriver); then return InstanceProperties is InstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DeployedSleeperInstance DeployedSleeperInstance.loadOrDeployAtFirstConnect(String, SystemTestInstanceConfiguration, SystemTestParameters, DeployedSystemTestResources, SleeperInstanceDriver, AssumeAdminRoleDriver, ScheduleRulesDriver)"})
  void testLoadOrDeployAtFirstConnect_thenReturnInstancePropertiesIsInstanceProperties() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.isSet(Mockito.<InstanceProperty>any())).thenReturn(false);
    Builder instancePropertiesResult = DeployInstanceConfiguration.builder().instanceProperties(instanceProperties);
    DeployInstanceConfiguration buildResult = instancePropertiesResult.tableProperties(new ArrayList<>()).build();
    SystemTestInstanceConfiguration configuration = mock(SystemTestInstanceConfiguration.class);
    when(configuration.buildDeployConfig(Mockito.<SystemTestParameters>any(),
        Mockito.<DeployedSystemTestResources>any())).thenReturn(buildResult);
    SystemTestParameters.Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    InstanceProperties instancePropertiesOverrides = new InstanceProperties();
    SystemTestParameters.Builder shortTestIdResult = forceStateStoreClassnameResult
        .instancePropertiesOverrides(instancePropertiesOverrides)
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
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
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    SleeperInstanceDriver driver = mock(SleeperInstanceDriver.class);
    doNothing().when(driver).resetOnFirstConnect(Mockito.<InstanceProperties>any());
    doNothing().when(driver).saveInstanceProperties(Mockito.<InstanceProperties>any());
    when(driver.deployInstanceIfNotPresent(Mockito.<String>any(), Mockito.<DeployInstanceConfiguration>any()))
        .thenReturn(true);
    doNothing().when(driver).loadInstanceProperties(Mockito.<InstanceProperties>any(), Mockito.<String>any());
    AssumeAdminRoleDriver assumeRoleDriver = mock(AssumeAdminRoleDriver.class);
    when(assumeRoleDriver.assumeAdminRole(Mockito.<InstanceProperties>any()))
        .thenReturn(new InMemorySystemTestDrivers());

    // Act
    DeployedSleeperInstance actualLoadOrDeployAtFirstConnectResult = DeployedSleeperInstance.loadOrDeployAtFirstConnect(
        "42", configuration, null, systemTest, driver, assumeRoleDriver, new NoScheduleRulesDriver());

    // Assert
    verify(instanceProperties, atLeast(1)).isSet(Mockito.<InstanceProperty>any());
    verify(assumeRoleDriver).assumeAdminRole(isA(InstanceProperties.class));
    verify(driver).deployInstanceIfNotPresent(eq("42"), isA(DeployInstanceConfiguration.class));
    verify(driver).loadInstanceProperties(isA(InstanceProperties.class), eq("42"));
    verify(driver).resetOnFirstConnect(isA(InstanceProperties.class));
    verify(driver).saveInstanceProperties(isA(InstanceProperties.class));
    verify(configuration).buildDeployConfig(isNull(), isA(DeployedSystemTestResources.class));
    assertEquals(instancePropertiesOverrides, actualLoadOrDeployAtFirstConnectResult.getInstanceProperties());
  }

  /**
   * Test {@link DeployedSleeperInstance#getDefaultTables()}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSleeperInstance#getDefaultTables()}
   */
  @Test
  @DisplayName("Test getDefaultTables(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DeployedSleeperInstance.getDefaultTables()"})
  void testGetDefaultTables_thenReturnEmpty() {
    // Arrange
    SystemTestInstanceConfiguration configuration = mock(SystemTestInstanceConfiguration.class);
    Builder builderResult = DeployInstanceConfiguration.builder();
    Builder instancePropertiesResult = builderResult.instanceProperties(new InstanceProperties());
    DeployInstanceConfiguration buildResult = instancePropertiesResult.tableProperties(new ArrayList<>()).build();
    when(configuration.buildDeployConfig(Mockito.<SystemTestParameters>any(),
        Mockito.<DeployedSystemTestResources>any())).thenReturn(buildResult);
    SleeperInstanceDriver driver = mock(SleeperInstanceDriver.class);
    doNothing().when(driver).resetOnFirstConnect(Mockito.<InstanceProperties>any());
    doNothing().when(driver).saveInstanceProperties(Mockito.<InstanceProperties>any());
    when(driver.deployInstanceIfNotPresent(Mockito.<String>any(), Mockito.<DeployInstanceConfiguration>any()))
        .thenReturn(true);
    doNothing().when(driver).loadInstanceProperties(Mockito.<InstanceProperties>any(), Mockito.<String>any());
    AssumeAdminRoleDriver assumeRoleDriver = mock(AssumeAdminRoleDriver.class);
    when(assumeRoleDriver.assumeAdminRole(Mockito.<InstanceProperties>any()))
        .thenReturn(new InMemorySystemTestDrivers());

    // Act
    List<TableProperties> actualDefaultTables = DeployedSleeperInstance
        .loadOrDeployAtFirstConnect("42", configuration, mock(SystemTestParameters.class),
            mock(DeployedSystemTestResources.class), driver, assumeRoleDriver, mock(ScheduleRulesDriver.class))
        .getDefaultTables();

    // Assert
    verify(assumeRoleDriver).assumeAdminRole(isA(InstanceProperties.class));
    verify(driver).deployInstanceIfNotPresent(eq("42"), isA(DeployInstanceConfiguration.class));
    verify(driver).loadInstanceProperties(isA(InstanceProperties.class), eq("42"));
    verify(driver).resetOnFirstConnect(isA(InstanceProperties.class));
    verify(driver).saveInstanceProperties(isA(InstanceProperties.class));
    verify(configuration).buildDeployConfig(isA(SystemTestParameters.class), isA(DeployedSystemTestResources.class));
    assertTrue(actualDefaultTables.isEmpty());
  }
}
