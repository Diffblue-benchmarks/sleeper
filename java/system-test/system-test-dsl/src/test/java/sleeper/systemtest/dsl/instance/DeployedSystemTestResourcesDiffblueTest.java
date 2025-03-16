package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.instance.SystemTestParameters.Builder;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySystemTestDeploymentDriver;

class DeployedSystemTestResourcesDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DeployedSystemTestResources#DeployedSystemTestResources(SystemTestParameters, SystemTestDeploymentDriver)}
   *   <li>{@link DeployedSystemTestResources#getProperties()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSystemTestResources.<init>(SystemTestParameters, SystemTestDeploymentDriver)",
      "SystemTestStandaloneProperties DeployedSystemTestResources.getProperties()"})
  void testGettersAndSetters() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
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

    // Act and Assert
    assertNull((new DeployedSystemTestResources(parameters, new InMemorySystemTestDeploymentDriver())).getProperties());
  }

  /**
   * Test {@link DeployedSystemTestResources#updateProperties(Consumer)}.
   * <p>
   * Method under test: {@link DeployedSystemTestResources#updateProperties(Consumer)}
   */
  @Test
  @DisplayName("Test updateProperties(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSystemTestResources.updateProperties(Consumer)"})
  void testUpdateProperties() {
    // Arrange
    InMemorySystemTestDeploymentDriver driver = new InMemorySystemTestDeploymentDriver();
    driver.deployIfNotPresent(new SystemTestStandaloneProperties());
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
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
    DeployedSystemTestResources deployedSystemTestResources = new DeployedSystemTestResources(parameters, driver);
    Consumer<SystemTestStandaloneProperties> config = mock(Consumer.class);
    doNothing().when(config).accept(Mockito.<SystemTestStandaloneProperties>any());

    // Act
    deployedSystemTestResources.updateProperties(config);

    // Assert
    verify(config).accept(isNull());
  }

  /**
   * Test {@link DeployedSystemTestResources#updateProperties(Consumer)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSystemTestResources#updateProperties(Consumer)}
   */
  @Test
  @DisplayName("Test updateProperties(Consumer); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSystemTestResources.updateProperties(Consumer)"})
  void testUpdateProperties_thenThrowRuntimeException() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
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
    DeployedSystemTestResources deployedSystemTestResources = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());
    Consumer<SystemTestStandaloneProperties> config = mock(Consumer.class);
    doThrow(new RuntimeException("System test not yet deployed")).when(config)
        .accept(Mockito.<SystemTestStandaloneProperties>any());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> deployedSystemTestResources.updateProperties(config));
    verify(config).accept(isNull());
  }

  /**
   * Test {@link DeployedSystemTestResources#deployIfMissing()}.
   * <p>
   * Method under test: {@link DeployedSystemTestResources#deployIfMissing()}
   */
  @Test
  @DisplayName("Test deployIfMissing()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSystemTestResources.deployIfMissing()"})
  void testDeployIfMissing() throws InterruptedException {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
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
    DeployedSystemTestResources deployedSystemTestResources = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    // Act
    deployedSystemTestResources.deployIfMissing();

    // Assert
    assertEquals(24, deployedSystemTestResources.getProperties().getPropertiesIndex().getUserDefined().size());
    assertTrue(deployedSystemTestResources.isSystemTestClusterEnabled());
  }

  /**
   * Test {@link DeployedSystemTestResources#deployIfMissing()}.
   * <p>
   * Method under test: {@link DeployedSystemTestResources#deployIfMissing()}
   */
  @Test
  @DisplayName("Test deployIfMissing()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployedSystemTestResources.deployIfMissing()"})
  void testDeployIfMissing2() throws InterruptedException {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(false)
        .vpcId("42")
        .build();
    DeployedSystemTestResources deployedSystemTestResources = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    // Act
    deployedSystemTestResources.deployIfMissing();

    // Assert
    assertEquals(24, deployedSystemTestResources.getProperties().getPropertiesIndex().getUserDefined().size());
    assertFalse(deployedSystemTestResources.isSystemTestClusterEnabled());
  }

  /**
   * Test {@link DeployedSystemTestResources#isSystemTestClusterEnabled()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployedSystemTestResources#isSystemTestClusterEnabled()}
   */
  @Test
  @DisplayName("Test isSystemTestClusterEnabled(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeployedSystemTestResources.isSystemTestClusterEnabled()"})
  void testIsSystemTestClusterEnabled_thenReturnFalse() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(false)
        .vpcId("42")
        .build();

    // Act and Assert
    assertFalse((new DeployedSystemTestResources(parameters, new InMemorySystemTestDeploymentDriver()))
        .isSystemTestClusterEnabled());
  }
}
