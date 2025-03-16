package sleeper.systemtest.dsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.statestore.testutils.InMemoryTransactionLogsPerTable;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.instance.AssumeAdminRoleDriver;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.instance.SystemTestParameters.Builder;
import sleeper.systemtest.dsl.testutil.InMemorySystemTestDrivers;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperInstanceDriver;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperTablesDriver;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySystemTestDeploymentDriver;
import sleeper.systemtest.dsl.util.NoScheduleRulesDriver;
import sleeper.systemtest.dsl.util.TestContext;

class SystemTestContextDiffblueTest {
  /**
   * Test {@link SystemTestContext#SystemTestContext(SystemTestParameters, SystemTestDrivers, DeployedSystemTestResources, DeployedSleeperInstances, TestContext)}.
   * <p>
   * Method under test: {@link SystemTestContext#SystemTestContext(SystemTestParameters, SystemTestDrivers, DeployedSystemTestResources, DeployedSleeperInstances, TestContext)}
   */
  @Test
  @DisplayName("Test new SystemTestContext(SystemTestParameters, SystemTestDrivers, DeployedSystemTestResources, DeployedSleeperInstances, TestContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SystemTestContext.<init>(SystemTestParameters, SystemTestDrivers, DeployedSystemTestResources, DeployedSleeperInstances, TestContext)"})
  void testNewSystemTestContext() {
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
    InMemorySystemTestDrivers drivers = new InMemorySystemTestDrivers();
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
    DeployedSystemTestResources systemTestResources = new DeployedSystemTestResources(parameters2,
        new InMemorySystemTestDeploymentDriver());

    Builder forceStateStoreClassnameResult3 = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult3 = forceStateStoreClassnameResult3.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters3 = shortTestIdResult3
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    Builder forceStateStoreClassnameResult4 = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult4 = forceStateStoreClassnameResult4.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters4 = shortTestIdResult4
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters4,
        new InMemorySystemTestDeploymentDriver());

    InMemorySleeperInstanceDriver instanceDriver = new InMemorySleeperInstanceDriver(
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()));
    AssumeAdminRoleDriver assumeRoleDriver = mock(AssumeAdminRoleDriver.class);
    DeployedSleeperInstances deployedInstances = new DeployedSleeperInstances(parameters3, systemTest, instanceDriver,
        assumeRoleDriver, new NoScheduleRulesDriver());

    TestContext.Builder displayNameResult = TestContext.builder().displayName("Display Name");
    TestContext.Builder tagsResult = displayNameResult.tags(new HashSet<>());
    Class<Object> testClass = Object.class;
    TestContext testContext = tagsResult.testClass(testClass).testMethod(null).build();

    // Act and Assert
    assertSame(parameters,
        (new SystemTestContext(parameters, drivers, systemTestResources, deployedInstances, testContext)).parameters());
  }

  /**
   * Test {@link SystemTestContext#SystemTestContext(SystemTestParameters, SystemTestDrivers, DeployedSystemTestResources, DeployedSleeperInstances, TestContext)}.
   * <ul>
   *   <li>Then return sourceFiles SourceBucketName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestContext#SystemTestContext(SystemTestParameters, SystemTestDrivers, DeployedSystemTestResources, DeployedSleeperInstances, TestContext)}
   */
  @Test
  @DisplayName("Test new SystemTestContext(SystemTestParameters, SystemTestDrivers, DeployedSystemTestResources, DeployedSleeperInstances, TestContext); then return sourceFiles SourceBucketName is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SystemTestContext.<init>(SystemTestParameters, SystemTestDrivers, DeployedSystemTestResources, DeployedSleeperInstances, TestContext)"})
  void testNewSystemTestContext_thenReturnSourceFilesSourceBucketNameIsNull() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));

    // Act
    SystemTestContext actualSystemTestContext = new SystemTestContext(parameters, drivers,
        mock(DeployedSystemTestResources.class), mock(DeployedSleeperInstances.class), mock(TestContext.class));

    // Assert
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(parameters).getOutputDirectory();
    assertNull(actualSystemTestContext.sourceFiles().getSourceBucketName());
    assertSame(parameters, actualSystemTestContext.parameters());
  }

  /**
   * Test {@link SystemTestContext#systemTest()}.
   * <p>
   * Method under test: {@link SystemTestContext#systemTest()}
   */
  @Test
  @DisplayName("Test systemTest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DeployedSystemTestResources SystemTestContext.systemTest()"})
  void testSystemTest() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));

    // Act
    (new SystemTestContext(parameters, drivers, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class))).systemTest();

    // Assert
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(parameters).getOutputDirectory();
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SystemTestContext#instance()}
   *   <li>{@link SystemTestContext#parameters()}
   *   <li>{@link SystemTestContext#reporting()}
   *   <li>{@link SystemTestContext#sourceFiles()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.systemtest.dsl.instance.SystemTestInstanceContext SystemTestContext.instance()",
      "SystemTestParameters SystemTestContext.parameters()",
      "sleeper.systemtest.dsl.reporting.ReportingContext SystemTestContext.reporting()",
      "sleeper.systemtest.dsl.sourcedata.IngestSourceFilesContext SystemTestContext.sourceFiles()"})
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
    DeployedSystemTestResources systemTestResources = new DeployedSystemTestResources(parameters2,
        new InMemorySystemTestDeploymentDriver());

    Builder forceStateStoreClassnameResult3 = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult3 = forceStateStoreClassnameResult3.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters3 = shortTestIdResult3
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    Builder forceStateStoreClassnameResult4 = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult4 = forceStateStoreClassnameResult4.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters4 = shortTestIdResult4
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters4,
        new InMemorySystemTestDeploymentDriver());

    InMemorySleeperInstanceDriver instanceDriver = new InMemorySleeperInstanceDriver(
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()));
    AssumeAdminRoleDriver assumeRoleDriver = mock(AssumeAdminRoleDriver.class);
    DeployedSleeperInstances deployedInstances = new DeployedSleeperInstances(parameters3, systemTest, instanceDriver,
        assumeRoleDriver, new NoScheduleRulesDriver());

    TestContext.Builder displayNameResult = TestContext.builder().displayName("Display Name");
    TestContext.Builder tagsResult = displayNameResult.tags(new HashSet<>());
    Class<Object> testClass = Object.class;
    TestContext testContext = tagsResult.testClass(testClass).testMethod(null).build();
    SystemTestContext systemTestContext = new SystemTestContext(parameters, drivers, systemTestResources,
        deployedInstances, testContext);

    // Act
    systemTestContext.instance();
    SystemTestParameters actualParametersResult = systemTestContext.parameters();
    systemTestContext.reporting();
    systemTestContext.sourceFiles();

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
    assertTrue(toFileResult.isAbsolute());
    assertTrue(toFileResult2.isAbsolute());
    assertTrue(toFileResult3.isAbsolute());
    assertTrue(actualParametersResult.isForceRedeployInstances());
    assertTrue(actualParametersResult.isForceRedeploySystemTest());
    assertTrue(actualParametersResult.isSystemTestClusterEnabled());
    assertSame(outputDirectory, actualParametersResult.getOutputDirectory());
    assertSame(pythonDirectory, actualParametersResult.getPythonDirectory());
    assertSame(scriptsDirectory, actualParametersResult.getScriptsDirectory());
  }
}
