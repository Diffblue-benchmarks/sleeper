package sleeper.systemtest.dsl.sourcedata;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.query.core.recordretrieval.InMemoryDataStore;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.SystemTestContext;
import sleeper.systemtest.dsl.SystemTestDrivers;
import sleeper.systemtest.dsl.ingest.IngestByQueue;
import sleeper.systemtest.dsl.ingest.IngestByQueueDriver;
import sleeper.systemtest.dsl.ingest.IngestTasksDriver;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.instance.SystemTestParameters.Builder;
import sleeper.systemtest.dsl.testutil.InMemorySystemTestDrivers;
import sleeper.systemtest.dsl.testutil.drivers.InMemoryGeneratedIngestSourceFilesDriver;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySystemTestDeploymentDriver;
import sleeper.systemtest.dsl.util.PollWithRetriesDriver;
import sleeper.systemtest.dsl.util.TestContext;

class SystemTestClusterDiffblueTest {
  /**
   * Test {@link SystemTestCluster#SystemTestCluster(SystemTestContext, SystemTestDrivers)}.
   * <ul>
   *   <li>Then calls {@link InMemorySystemTestDrivers#ingestByQueue(SystemTestContext)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestCluster#SystemTestCluster(SystemTestContext, SystemTestDrivers)}
   */
  @Test
  @DisplayName("Test new SystemTestCluster(SystemTestContext, SystemTestDrivers); then calls ingestByQueue(SystemTestContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestCluster.<init>(SystemTestContext, SystemTestDrivers)"})
  void testNewSystemTestCluster_thenCallsIngestByQueue() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    InMemorySystemTestDrivers inMemorySystemTestDrivers = mock(InMemorySystemTestDrivers.class);
    when(inMemorySystemTestDrivers.ingestByQueue(Mockito.<SystemTestContext>any())).thenReturn(new IngestByQueue(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)),
        mock(IngestByQueueDriver.class)));
    when(inMemorySystemTestDrivers.ingestTasks(Mockito.<SystemTestContext>any()))
        .thenReturn(mock(IngestTasksDriver.class));
    when(inMemorySystemTestDrivers.pollWithRetries()).thenReturn(mock(PollWithRetriesDriver.class));
    when(inMemorySystemTestDrivers.waitForBulkImport(Mockito.<SystemTestContext>any())).thenReturn(null);
    when(inMemorySystemTestDrivers.waitForIngest(Mockito.<SystemTestContext>any())).thenReturn(null);
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    when(systemTestInstanceContext.adminDrivers()).thenReturn(inMemorySystemTestDrivers);
    SystemTestContext context = mock(SystemTestContext.class);
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
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    when(context.parameters()).thenReturn(buildResult);
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
    SystemTestParameters parameters = shortTestIdResult2
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    when(context.systemTest())
        .thenReturn(new DeployedSystemTestResources(parameters, new InMemorySystemTestDeploymentDriver()));
    when(context.instance()).thenReturn(systemTestInstanceContext);
    InMemorySystemTestDrivers baseDrivers = mock(InMemorySystemTestDrivers.class);
    when(baseDrivers.dataGenerationTasks(Mockito.<SystemTestContext>any()))
        .thenReturn(mock(DataGenerationTasksDriver.class));
    when(baseDrivers.generatedSourceFiles(Mockito.<SystemTestParameters>any(),
        Mockito.<DeployedSystemTestResources>any()))
        .thenReturn(new InMemoryGeneratedIngestSourceFilesDriver(new InMemoryDataStore()));

    // Act
    new SystemTestCluster(context, baseDrivers);

    // Assert
    verify(context).instance();
    verify(context).parameters();
    verify(context, atLeast(1)).systemTest();
    verify(systemTestInstanceContext).adminDrivers();
    verify(baseDrivers).generatedSourceFiles(isA(SystemTestParameters.class), isA(DeployedSystemTestResources.class));
    verify(inMemorySystemTestDrivers).ingestByQueue(isA(SystemTestContext.class));
    verify(inMemorySystemTestDrivers).ingestTasks(isA(SystemTestContext.class));
    verify(inMemorySystemTestDrivers).pollWithRetries();
    verify(inMemorySystemTestDrivers).waitForBulkImport(isA(SystemTestContext.class));
    verify(inMemorySystemTestDrivers).waitForIngest(isA(SystemTestContext.class));
    verify(baseDrivers).dataGenerationTasks(isA(SystemTestContext.class));
  }

  /**
   * Test {@link SystemTestCluster#SystemTestCluster(SystemTestContext, SystemTestDrivers)}.
   * <ul>
   *   <li>Then calls {@link SystemTestContext#instance()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestCluster#SystemTestCluster(SystemTestContext, SystemTestDrivers)}
   */
  @Test
  @DisplayName("Test new SystemTestCluster(SystemTestContext, SystemTestDrivers); then calls instance()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestCluster.<init>(SystemTestContext, SystemTestDrivers)"})
  void testNewSystemTestCluster_thenCallsInstance() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SystemTestInstanceContext systemTestInstanceContext = mock(SystemTestInstanceContext.class);
    when(systemTestInstanceContext.adminDrivers()).thenReturn(new InMemorySystemTestDrivers());
    SystemTestContext context = mock(SystemTestContext.class);
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
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    when(context.parameters()).thenReturn(buildResult);
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
    SystemTestParameters parameters = shortTestIdResult2
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    when(context.systemTest())
        .thenReturn(new DeployedSystemTestResources(parameters, new InMemorySystemTestDeploymentDriver()));
    when(context.instance()).thenReturn(systemTestInstanceContext);
    InMemorySystemTestDrivers baseDrivers = mock(InMemorySystemTestDrivers.class);
    when(baseDrivers.dataGenerationTasks(Mockito.<SystemTestContext>any()))
        .thenReturn(mock(DataGenerationTasksDriver.class));
    when(baseDrivers.generatedSourceFiles(Mockito.<SystemTestParameters>any(),
        Mockito.<DeployedSystemTestResources>any()))
        .thenReturn(new InMemoryGeneratedIngestSourceFilesDriver(new InMemoryDataStore()));

    // Act
    new SystemTestCluster(context, baseDrivers);

    // Assert
    verify(context, atLeast(1)).instance();
    verify(context).parameters();
    verify(context, atLeast(1)).systemTest();
    verify(systemTestInstanceContext).adminDrivers();
    verify(baseDrivers).generatedSourceFiles(isA(SystemTestParameters.class), isA(DeployedSystemTestResources.class));
    verify(baseDrivers).dataGenerationTasks(isA(SystemTestContext.class));
  }
}
