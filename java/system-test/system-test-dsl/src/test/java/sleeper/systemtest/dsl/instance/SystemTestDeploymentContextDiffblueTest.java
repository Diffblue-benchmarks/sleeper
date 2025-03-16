package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.SystemTestDrivers;
import sleeper.systemtest.dsl.instance.SystemTestParameters.Builder;
import sleeper.systemtest.dsl.testutil.InMemorySystemTestDrivers;

class SystemTestDeploymentContextDiffblueTest {
  /**
   * Test {@link SystemTestDeploymentContext#SystemTestDeploymentContext(SystemTestParameters, SystemTestDrivers)}.
   * <ul>
   *   <li>Then return deployedResources Properties is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestDeploymentContext#SystemTestDeploymentContext(SystemTestParameters, SystemTestDrivers)}
   */
  @Test
  @DisplayName("Test new SystemTestDeploymentContext(SystemTestParameters, SystemTestDrivers); then return deployedResources Properties is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestDeploymentContext.<init>(SystemTestParameters, SystemTestDrivers)"})
  void testNewSystemTestDeploymentContext_thenReturnDeployedResourcesPropertiesIsNull() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    InMemorySystemTestDrivers drivers = new InMemorySystemTestDrivers();

    // Act
    SystemTestDeploymentContext actualSystemTestDeploymentContext = new SystemTestDeploymentContext(parameters,
        drivers);

    // Assert
    DeployedSystemTestResources deployedResourcesResult = actualSystemTestDeploymentContext.deployedResources();
    assertNull(deployedResourcesResult.getProperties());
    assertFalse(deployedResourcesResult.isSystemTestClusterEnabled());
    assertSame(drivers, actualSystemTestDeploymentContext.drivers());
    assertSame(parameters, actualSystemTestDeploymentContext.parameters());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SystemTestDeploymentContext#deployedInstances()}
   *   <li>{@link SystemTestDeploymentContext#deployedResources()}
   *   <li>{@link SystemTestDeploymentContext#drivers()}
   *   <li>{@link SystemTestDeploymentContext#parameters()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.systemtest.dsl.instance.DeployedSleeperInstances SystemTestDeploymentContext.deployedInstances()",
      "DeployedSystemTestResources SystemTestDeploymentContext.deployedResources()",
      "SystemTestDrivers SystemTestDeploymentContext.drivers()",
      "SystemTestParameters SystemTestDeploymentContext.parameters()"})
  void testGettersAndSetters() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Path outputDirectory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Path pythonDirectory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Path scriptsDirectory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(outputDirectory)
        .pythonDirectory(pythonDirectory)
        .region("us-east-2")
        .scriptsDirectory(scriptsDirectory)
        .shortTestId("42");
    SystemTestParameters parameters = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    InMemorySystemTestDrivers drivers = new InMemorySystemTestDrivers();
    SystemTestDeploymentContext systemTestDeploymentContext = new SystemTestDeploymentContext(parameters, drivers);

    // Act
    systemTestDeploymentContext.deployedInstances();
    DeployedSystemTestResources actualDeployedResourcesResult = systemTestDeploymentContext.deployedResources();
    SystemTestDrivers actualDriversResult = systemTestDeploymentContext.drivers();
    SystemTestParameters actualParametersResult = systemTestDeploymentContext.parameters();

    // Assert
    assertEquals("3", actualParametersResult.getAccount());
    assertEquals("42", actualParametersResult.getSystemTestShortId());
    assertEquals("42", actualParametersResult.getVpcId());
    assertEquals("Subnet Ids", actualParametersResult.getSubnetIds());
    File toFileResult = actualParametersResult.getDockerDirectory().toFile();
    assertEquals("docker", toFileResult.getName());
    File toFileResult2 = actualParametersResult.getGeneratedDirectory().toFile();
    assertEquals("generated", toFileResult2.getName());
    File toFileResult3 = actualParametersResult.getJarsDirectory().toFile();
    assertEquals("jars", toFileResult3.getName());
    assertEquals("us-east-2", actualParametersResult.getRegion());
    assertNull(actualDeployedResourcesResult.getProperties());
    assertTrue(toFileResult.isAbsolute());
    assertTrue(toFileResult2.isAbsolute());
    assertTrue(toFileResult3.isAbsolute());
    assertTrue(actualParametersResult.isForceRedeployInstances());
    assertTrue(actualParametersResult.isForceRedeploySystemTest());
    assertTrue(actualParametersResult.isSystemTestClusterEnabled());
    assertSame(drivers, actualDriversResult);
    assertSame(outputDirectory, actualParametersResult.getOutputDirectory());
    assertSame(pythonDirectory, actualParametersResult.getPythonDirectory());
    assertSame(scriptsDirectory, actualParametersResult.getScriptsDirectory());
  }
}
