package sleeper.systemtest.dsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.statestore.testutils.InMemoryTransactionLogsPerTable;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.compaction.CompactionDriver;
import sleeper.systemtest.dsl.instance.AssumeAdminRoleDriver;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceConfiguration;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.instance.SystemTestParameters.Builder;
import sleeper.systemtest.dsl.query.SystemTestQuery;
import sleeper.systemtest.dsl.sourcedata.IngestSourceFilesContext;
import sleeper.systemtest.dsl.testutil.InMemorySystemTestDrivers;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperInstanceDriver;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperTablesDriver;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySystemTestDeploymentDriver;
import sleeper.systemtest.dsl.util.NoScheduleRulesDriver;
import sleeper.systemtest.dsl.util.PollWithRetriesDriver;
import sleeper.systemtest.dsl.util.TestContext;
import sleeper.systemtest.dsl.util.WaitForJobs;

class SleeperSystemTestDiffblueTest {
  /**
   * Test {@link SleeperSystemTest#SleeperSystemTest(SystemTestParameters, SystemTestDrivers, SystemTestContext)}.
   * <p>
   * Method under test: {@link SleeperSystemTest#SleeperSystemTest(SystemTestParameters, SystemTestDrivers, SystemTestContext)}
   */
  @Test
  @DisplayName("Test new SleeperSystemTest(SystemTestParameters, SystemTestDrivers, SystemTestContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperSystemTest.<init>(SystemTestParameters, SystemTestDrivers, SystemTestContext)"})
  void testNewSleeperSystemTest() {
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
    InMemorySystemTestDrivers baseDrivers = new InMemorySystemTestDrivers();
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
    InMemorySystemTestDrivers drivers = new InMemorySystemTestDrivers();
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
    DeployedSystemTestResources systemTestResources = new DeployedSystemTestResources(parameters3,
        new InMemorySystemTestDeploymentDriver());

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
    Builder forceStateStoreClassnameResult5 = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult5 = forceStateStoreClassnameResult5.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters5 = shortTestIdResult5
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters5,
        new InMemorySystemTestDeploymentDriver());

    InMemorySleeperInstanceDriver instanceDriver = new InMemorySleeperInstanceDriver(
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()));
    AssumeAdminRoleDriver assumeRoleDriver = mock(AssumeAdminRoleDriver.class);
    DeployedSleeperInstances deployedInstances = new DeployedSleeperInstances(parameters4, systemTest, instanceDriver,
        assumeRoleDriver, new NoScheduleRulesDriver());

    TestContext.Builder displayNameResult = TestContext.builder().displayName("Display Name");
    TestContext.Builder tagsResult = displayNameResult.tags(new HashSet<>());
    Class<Object> testClass = Object.class;
    TestContext testContext = tagsResult.testClass(testClass).testMethod(null).build();

    // Act and Assert
    File toFileResult = (new SleeperSystemTest(parameters, baseDrivers,
        new SystemTestContext(parameters2, drivers, systemTestResources, deployedInstances, testContext)))
        .getSplitPointsDirectory()
        .toFile();
    assertEquals("splitpoints", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link SleeperSystemTest#SleeperSystemTest(SystemTestParameters, SystemTestDrivers, SystemTestContext)}.
   * <p>
   * Method under test: {@link SleeperSystemTest#SleeperSystemTest(SystemTestParameters, SystemTestDrivers, SystemTestContext)}
   */
  @Test
  @DisplayName("Test new SleeperSystemTest(SystemTestParameters, SystemTestDrivers, SystemTestContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperSystemTest.<init>(SystemTestParameters, SystemTestDrivers, SystemTestContext)"})
  void testNewSleeperSystemTest2() {
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
    InMemorySystemTestDrivers baseDrivers = new InMemorySystemTestDrivers();
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
    InMemorySystemTestDrivers drivers = new InMemorySystemTestDrivers();
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
    DeployedSystemTestResources systemTestResources = new DeployedSystemTestResources(parameters3,
        new InMemorySystemTestDeploymentDriver());

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
    Builder forceStateStoreClassnameResult5 = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult5 = forceStateStoreClassnameResult5.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters5 = shortTestIdResult5
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters5,
        new InMemorySystemTestDeploymentDriver());

    InMemorySleeperInstanceDriver instanceDriver = new InMemorySleeperInstanceDriver(
        new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable()));
    AssumeAdminRoleDriver assumeRoleDriver = mock(AssumeAdminRoleDriver.class);
    DeployedSleeperInstances deployedInstances = new DeployedSleeperInstances(parameters4, systemTest, instanceDriver,
        assumeRoleDriver, new NoScheduleRulesDriver());

    TestContext.Builder displayNameResult = TestContext.builder().displayName("Display Name");
    TestContext.Builder tagsResult = displayNameResult.tags(new HashSet<>());
    Class<Object> testClass = Object.class;
    TestContext testContext = tagsResult.testClass(testClass).testMethod(null).build();

    // Act and Assert
    File toFileResult = (new SleeperSystemTest(parameters, baseDrivers,
        new SystemTestContext(parameters2, drivers, systemTestResources, deployedInstances, testContext)))
        .getSplitPointsDirectory()
        .toFile();
    assertEquals("splitpoints", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link SleeperSystemTest#connectToInstance(SystemTestInstanceConfiguration)}.
   * <ul>
   *   <li>Then calls {@link SystemTestContext#instance()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperSystemTest#connectToInstance(SystemTestInstanceConfiguration)}
   */
  @Test
  @DisplayName("Test connectToInstance(SystemTestInstanceConfiguration); then calls instance()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperSystemTest.connectToInstance(SystemTestInstanceConfiguration)"})
  void testConnectToInstance_thenCallsInstance() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    doNothing().when(systemTestInstanceContext).addDefaultTables();
    doNothing().when(systemTestInstanceContext).connectTo(Mockito.<SystemTestInstanceConfiguration>any());
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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

    // Act
    (new SleeperSystemTest(parameters, new InMemorySystemTestDrivers(), context)).connectToInstance(null);

    // Assert
    verify(context, atLeast(1)).instance();
    verify(systemTestInstanceContext).addDefaultTables();
    verify(systemTestInstanceContext).connectTo(isNull());
  }

  /**
   * Test {@link SleeperSystemTest#connectToInstance(SystemTestInstanceConfiguration)}.
   * <ul>
   *   <li>Then calls {@link SystemTestContext#instance()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperSystemTest#connectToInstance(SystemTestInstanceConfiguration)}
   */
  @Test
  @DisplayName("Test connectToInstance(SystemTestInstanceConfiguration); then calls instance()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperSystemTest.connectToInstance(SystemTestInstanceConfiguration)"})
  void testConnectToInstance_thenCallsInstance2() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    doNothing().when(systemTestInstanceContext).addDefaultTables();
    doNothing().when(systemTestInstanceContext).connectTo(Mockito.<SystemTestInstanceConfiguration>any());
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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

    // Act
    (new SleeperSystemTest(parameters, new InMemorySystemTestDrivers(), context)).connectToInstance(null);

    // Assert
    verify(context, atLeast(1)).instance();
    verify(systemTestInstanceContext).addDefaultTables();
    verify(systemTestInstanceContext).connectTo(isNull());
  }

  /**
   * Test {@link SleeperSystemTest#connectToInstanceNoTables(SystemTestInstanceConfiguration)}.
   * <ul>
   *   <li>Then calls {@link SystemTestContext#instance()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperSystemTest#connectToInstanceNoTables(SystemTestInstanceConfiguration)}
   */
  @Test
  @DisplayName("Test connectToInstanceNoTables(SystemTestInstanceConfiguration); then calls instance()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperSystemTest.connectToInstanceNoTables(SystemTestInstanceConfiguration)"})
  void testConnectToInstanceNoTables_thenCallsInstance() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    doNothing().when(systemTestInstanceContext).connectTo(Mockito.<SystemTestInstanceConfiguration>any());
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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

    // Act
    (new SleeperSystemTest(parameters, new InMemorySystemTestDrivers(), context)).connectToInstanceNoTables(null);

    // Assert
    verify(context).instance();
    verify(systemTestInstanceContext).connectTo(isNull());
  }

  /**
   * Test {@link SleeperSystemTest#connectToInstanceNoTables(SystemTestInstanceConfiguration)}.
   * <ul>
   *   <li>Then calls {@link SystemTestContext#instance()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperSystemTest#connectToInstanceNoTables(SystemTestInstanceConfiguration)}
   */
  @Test
  @DisplayName("Test connectToInstanceNoTables(SystemTestInstanceConfiguration); then calls instance()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperSystemTest.connectToInstanceNoTables(SystemTestInstanceConfiguration)"})
  void testConnectToInstanceNoTables_thenCallsInstance2() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    doNothing().when(systemTestInstanceContext).connectTo(Mockito.<SystemTestInstanceConfiguration>any());
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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

    // Act
    (new SleeperSystemTest(parameters, new InMemorySystemTestDrivers(), context)).connectToInstanceNoTables(null);

    // Assert
    verify(context).instance();
    verify(systemTestInstanceContext).connectTo(isNull());
  }

  /**
   * Test {@link SleeperSystemTest#directQuery()}.
   * <ul>
   *   <li>Then return allRecordsByTable Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperSystemTest#directQuery()}
   */
  @Test
  @DisplayName("Test directQuery(); then return allRecordsByTable Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestQuery SleeperSystemTest.directQuery()"})
  void testDirectQuery_thenReturnAllRecordsByTableEmpty() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    when(systemTestInstanceContext.adminDrivers()).thenReturn(new InMemorySystemTestDrivers());
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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

    // Act
    SystemTestQuery actualDirectQueryResult = (new SleeperSystemTest(parameters, new InMemorySystemTestDrivers(),
        context)).directQuery();

    // Assert
    verify(context, atLeast(1)).instance();
    verify(systemTestInstanceContext).adminDrivers();
    assertTrue(actualDirectQueryResult.allRecordsByTable().isEmpty());
  }

  /**
   * Test {@link SleeperSystemTest#directQuery()}.
   * <ul>
   *   <li>Then return allRecordsByTable Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperSystemTest#directQuery()}
   */
  @Test
  @DisplayName("Test directQuery(); then return allRecordsByTable Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestQuery SleeperSystemTest.directQuery()"})
  void testDirectQuery_thenReturnAllRecordsByTableEmpty2() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    when(systemTestInstanceContext.adminDrivers()).thenReturn(new InMemorySystemTestDrivers());
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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

    // Act
    SystemTestQuery actualDirectQueryResult = (new SleeperSystemTest(parameters, new InMemorySystemTestDrivers(),
        context)).directQuery();

    // Assert
    verify(context, atLeast(1)).instance();
    verify(systemTestInstanceContext).adminDrivers();
    assertTrue(actualDirectQueryResult.allRecordsByTable().isEmpty());
  }

  /**
   * Test {@link SleeperSystemTest#compaction()}.
   * <p>
   * Method under test: {@link SleeperSystemTest#compaction()}
   */
  @Test
  @DisplayName("Test compaction()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.systemtest.dsl.compaction.SystemTestCompaction SleeperSystemTest.compaction()"})
  void testCompaction() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    when(systemTestInstanceContext.adminDrivers()).thenReturn(new InMemorySystemTestDrivers());
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    when(context.sourceFiles()).thenReturn(
        new IngestSourceFilesContext(systemTest, new SystemTestInstanceContext(mock(SystemTestParameters.class),
            mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class))));
    Builder forceStateStoreClassnameResult2 = SystemTestParameters.builder()
        .account("compaction")
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

    // Act
    (new SleeperSystemTest(parameters2, new InMemorySystemTestDrivers(), context)).compaction();

    // Assert
    verify(context, atLeast(1)).instance();
    verify(context).sourceFiles();
    verify(systemTestInstanceContext).adminDrivers();
  }

  /**
   * Test {@link SleeperSystemTest#compaction()}.
   * <p>
   * Method under test: {@link SleeperSystemTest#compaction()}
   */
  @Test
  @DisplayName("Test compaction()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.systemtest.dsl.compaction.SystemTestCompaction SleeperSystemTest.compaction()"})
  void testCompaction2() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    when(systemTestInstanceContext.adminDrivers()).thenReturn(new InMemorySystemTestDrivers());
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    when(context.sourceFiles()).thenReturn(
        new IngestSourceFilesContext(systemTest, new SystemTestInstanceContext(mock(SystemTestParameters.class),
            mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class))));
    Builder forceStateStoreClassnameResult2 = SystemTestParameters.builder()
        .account("compaction")
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

    // Act
    (new SleeperSystemTest(parameters2, new InMemorySystemTestDrivers(), context)).compaction();

    // Assert
    verify(context, atLeast(1)).instance();
    verify(context).sourceFiles();
    verify(systemTestInstanceContext).adminDrivers();
  }

  /**
   * Test {@link SleeperSystemTest#compaction()}.
   * <ul>
   *   <li>Then calls {@link CompactionDriver#getJobTracker()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperSystemTest#compaction()}
   */
  @Test
  @DisplayName("Test compaction(); then calls getJobTracker()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.systemtest.dsl.compaction.SystemTestCompaction SleeperSystemTest.compaction()"})
  void testCompaction_thenCallsGetJobTracker() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    CompactionDriver compactionDriver = mock(CompactionDriver.class);
    when(compactionDriver.getJobTracker()).thenReturn(new InMemoryCompactionJobTracker());
    InMemorySystemTestDrivers inMemorySystemTestDrivers = mock(InMemorySystemTestDrivers.class);
    when(inMemorySystemTestDrivers.waitForCompaction(Mockito.<SystemTestContext>any()))
        .thenReturn(WaitForJobs.forBulkImport(
            new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
                mock(SleeperInstanceDriver.class), mock(TestContext.class)),
            mock(Function.class), mock(PollWithRetriesDriver.class)));
    when(inMemorySystemTestDrivers.compaction(Mockito.<SystemTestContext>any())).thenReturn(compactionDriver);
    when(inMemorySystemTestDrivers.pollWithRetries()).thenReturn(mock(PollWithRetriesDriver.class));
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    when(systemTestInstanceContext.adminDrivers()).thenReturn(inMemorySystemTestDrivers);
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    when(context.sourceFiles()).thenReturn(
        new IngestSourceFilesContext(systemTest, new SystemTestInstanceContext(mock(SystemTestParameters.class),
            mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class))));
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

    // Act
    (new SleeperSystemTest(parameters2, new InMemorySystemTestDrivers(), context)).compaction();

    // Assert
    verify(context, atLeast(1)).instance();
    verify(context).sourceFiles();
    verify(compactionDriver).getJobTracker();
    verify(systemTestInstanceContext).adminDrivers();
    verify(inMemorySystemTestDrivers).compaction(isA(SystemTestContext.class));
    verify(inMemorySystemTestDrivers).pollWithRetries();
    verify(inMemorySystemTestDrivers).waitForCompaction(isA(SystemTestContext.class));
  }

  /**
   * Test {@link SleeperSystemTest#compaction()}.
   * <ul>
   *   <li>Then calls {@link CompactionDriver#getJobTracker()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperSystemTest#compaction()}
   */
  @Test
  @DisplayName("Test compaction(); then calls getJobTracker()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.systemtest.dsl.compaction.SystemTestCompaction SleeperSystemTest.compaction()"})
  void testCompaction_thenCallsGetJobTracker2() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    CompactionDriver compactionDriver = mock(CompactionDriver.class);
    when(compactionDriver.getJobTracker()).thenReturn(new InMemoryCompactionJobTracker());
    InMemorySystemTestDrivers inMemorySystemTestDrivers = mock(InMemorySystemTestDrivers.class);
    when(inMemorySystemTestDrivers.waitForCompaction(Mockito.<SystemTestContext>any()))
        .thenReturn(WaitForJobs.forBulkImport(
            new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
                mock(SleeperInstanceDriver.class), mock(TestContext.class)),
            mock(Function.class), mock(PollWithRetriesDriver.class)));
    when(inMemorySystemTestDrivers.compaction(Mockito.<SystemTestContext>any())).thenReturn(compactionDriver);
    when(inMemorySystemTestDrivers.pollWithRetries()).thenReturn(mock(PollWithRetriesDriver.class));
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    when(systemTestInstanceContext.adminDrivers()).thenReturn(inMemorySystemTestDrivers);
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    when(context.sourceFiles()).thenReturn(
        new IngestSourceFilesContext(systemTest, new SystemTestInstanceContext(mock(SystemTestParameters.class),
            mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class))));
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

    // Act
    (new SleeperSystemTest(parameters2, new InMemorySystemTestDrivers(), context)).compaction();

    // Assert
    verify(context, atLeast(1)).instance();
    verify(context).sourceFiles();
    verify(compactionDriver).getJobTracker();
    verify(systemTestInstanceContext).adminDrivers();
    verify(inMemorySystemTestDrivers).compaction(isA(SystemTestContext.class));
    verify(inMemorySystemTestDrivers).pollWithRetries();
    verify(inMemorySystemTestDrivers).waitForCompaction(isA(SystemTestContext.class));
  }

  /**
   * Test {@link SleeperSystemTest#compaction()}.
   * <ul>
   *   <li>Then calls {@link SystemTestContext#instance()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperSystemTest#compaction()}
   */
  @Test
  @DisplayName("Test compaction(); then calls instance()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.systemtest.dsl.compaction.SystemTestCompaction SleeperSystemTest.compaction()"})
  void testCompaction_thenCallsInstance() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    when(systemTestInstanceContext.adminDrivers()).thenReturn(new InMemorySystemTestDrivers());
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    when(context.sourceFiles()).thenReturn(
        new IngestSourceFilesContext(systemTest, new SystemTestInstanceContext(mock(SystemTestParameters.class),
            mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class))));
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

    // Act
    (new SleeperSystemTest(parameters2, new InMemorySystemTestDrivers(), context)).compaction();

    // Assert
    verify(context, atLeast(1)).instance();
    verify(context).sourceFiles();
    verify(systemTestInstanceContext).adminDrivers();
  }

  /**
   * Test {@link SleeperSystemTest#compaction()}.
   * <ul>
   *   <li>Then calls {@link SystemTestContext#instance()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperSystemTest#compaction()}
   */
  @Test
  @DisplayName("Test compaction(); then calls instance()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.systemtest.dsl.compaction.SystemTestCompaction SleeperSystemTest.compaction()"})
  void testCompaction_thenCallsInstance2() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    when(systemTestInstanceContext.adminDrivers()).thenReturn(new InMemorySystemTestDrivers());
    SystemTestContext context = mock(SystemTestContext.class);
    when(context.instance()).thenReturn(systemTestInstanceContext);
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
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    when(context.sourceFiles()).thenReturn(
        new IngestSourceFilesContext(systemTest, new SystemTestInstanceContext(mock(SystemTestParameters.class),
            mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class))));
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

    // Act
    (new SleeperSystemTest(parameters2, new InMemorySystemTestDrivers(), context)).compaction();

    // Assert
    verify(context, atLeast(1)).instance();
    verify(context).sourceFiles();
    verify(systemTestInstanceContext).adminDrivers();
  }
}
