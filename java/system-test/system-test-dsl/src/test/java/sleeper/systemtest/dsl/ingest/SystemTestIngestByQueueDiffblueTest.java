package sleeper.systemtest.dsl.ingest;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.testutils.DummyInstanceProperty;
import sleeper.core.util.PollWithRetries;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.instance.SystemTestParameters.Builder;
import sleeper.systemtest.dsl.sourcedata.IngestSourceFilesContext;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperInstanceDriver;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperTablesDriver;
import sleeper.systemtest.dsl.util.PollWithRetriesDriver;
import sleeper.systemtest.dsl.util.TestContext;
import sleeper.systemtest.dsl.util.WaitForJobs;
import sleeper.systemtest.dsl.util.WaitForTasks;

class SystemTestIngestByQueueDiffblueTest {
  /**
   * Test {@link SystemTestIngestByQueue#sendSourceFiles(String[])} with {@code files}.
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#sendSourceFiles(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFiles(String[]) with 'files'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestByQueue SystemTestIngestByQueue.sendSourceFiles(String[])"})
  void testSendSourceFilesWithFiles() {
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
    shortTestIdResult.systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    IngestSourceFilesContext sourceFiles = mock(IngestSourceFilesContext.class);
    when(sourceFiles.getIngestJobFilesInBucket(Mockito.<Stream<String>>any())).thenReturn(new ArrayList<>());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableName()).thenReturn("Table Name");
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingest = new IngestByQueue(instance, driver);

    SystemTestIngestByQueue systemTestIngestByQueue = new SystemTestIngestByQueue(sourceFiles, ingest,
        new DummyInstanceProperty("Property Name"), mock(IngestTasksDriver.class), null,
        mock(PollWithRetriesDriver.class));

    // Act
    SystemTestIngestByQueue actualSendSourceFilesResult = systemTestIngestByQueue.sendSourceFiles("Files");

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("Table Name"), isA(List.class));
    verify(instance).getInstanceProperties();
    verify(instance).getTableName();
    verify(sourceFiles).getIngestJobFilesInBucket(isA(Stream.class));
    assertSame(systemTestIngestByQueue, actualSendSourceFilesResult);
  }

  /**
   * Test {@link SystemTestIngestByQueue#sendSourceFiles(String[])} with {@code files}.
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#sendSourceFiles(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFiles(String[]) with 'files'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestByQueue SystemTestIngestByQueue.sendSourceFiles(String[])"})
  void testSendSourceFilesWithFiles2() {
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
    shortTestIdResult.systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    IngestSourceFilesContext sourceFiles = mock(IngestSourceFilesContext.class);
    when(sourceFiles.getIngestJobFilesInBucket(Mockito.<Stream<String>>any())).thenReturn(new ArrayList<>());
    IngestByQueue ingest = mock(IngestByQueue.class);
    when(ingest.sendJobGetId(Mockito.<InstanceProperty>any(), Mockito.<List<String>>any())).thenReturn("42");
    SystemTestIngestByQueue systemTestIngestByQueue = new SystemTestIngestByQueue(sourceFiles, ingest,
        new DummyInstanceProperty("Property Name"), mock(IngestTasksDriver.class), null,
        mock(PollWithRetriesDriver.class));

    // Act
    SystemTestIngestByQueue actualSendSourceFilesResult = systemTestIngestByQueue.sendSourceFiles("Files");

    // Assert
    verify(ingest).sendJobGetId(isA(InstanceProperty.class), isA(List.class));
    verify(sourceFiles).getIngestJobFilesInBucket(isA(Stream.class));
    assertSame(systemTestIngestByQueue, actualSendSourceFilesResult);
  }

  /**
   * Test {@link SystemTestIngestByQueue#sendSourceFiles(InstanceProperty, String[])} with {@code queueProperty}, {@code files}.
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#sendSourceFiles(InstanceProperty, String[])}
   */
  @Test
  @DisplayName("Test sendSourceFiles(InstanceProperty, String[]) with 'queueProperty', 'files'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestByQueue SystemTestIngestByQueue.sendSourceFiles(InstanceProperty, String[])"})
  void testSendSourceFilesWithQueuePropertyFiles() {
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
    shortTestIdResult.systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    IngestSourceFilesContext sourceFiles = mock(IngestSourceFilesContext.class);
    when(sourceFiles.getIngestJobFilesInBucket(Mockito.<Stream<String>>any())).thenReturn(new ArrayList<>());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableName()).thenReturn("Table Name");
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingest = new IngestByQueue(instance, driver);

    SystemTestIngestByQueue systemTestIngestByQueue = new SystemTestIngestByQueue(sourceFiles, ingest,
        new DummyInstanceProperty("Property Name"), mock(IngestTasksDriver.class), null,
        mock(PollWithRetriesDriver.class));

    // Act
    SystemTestIngestByQueue actualSendSourceFilesResult = systemTestIngestByQueue
        .sendSourceFiles(new DummyInstanceProperty("Property Name"), "Files");

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("Table Name"), isA(List.class));
    verify(instance).getInstanceProperties();
    verify(instance).getTableName();
    verify(sourceFiles).getIngestJobFilesInBucket(isA(Stream.class));
    assertSame(systemTestIngestByQueue, actualSendSourceFilesResult);
  }

  /**
   * Test {@link SystemTestIngestByQueue#sendSourceFiles(InstanceProperty, String[])} with {@code queueProperty}, {@code files}.
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#sendSourceFiles(InstanceProperty, String[])}
   */
  @Test
  @DisplayName("Test sendSourceFiles(InstanceProperty, String[]) with 'queueProperty', 'files'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestByQueue SystemTestIngestByQueue.sendSourceFiles(InstanceProperty, String[])"})
  void testSendSourceFilesWithQueuePropertyFiles2() {
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
    shortTestIdResult.systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    IngestSourceFilesContext sourceFiles = mock(IngestSourceFilesContext.class);
    when(sourceFiles.getIngestJobFilesInBucket(Mockito.<Stream<String>>any())).thenReturn(new ArrayList<>());
    IngestByQueue ingest = mock(IngestByQueue.class);
    when(ingest.sendJobGetId(Mockito.<InstanceProperty>any(), Mockito.<List<String>>any())).thenReturn("42");
    SystemTestIngestByQueue systemTestIngestByQueue = new SystemTestIngestByQueue(sourceFiles, ingest,
        new DummyInstanceProperty("Property Name"), mock(IngestTasksDriver.class), null,
        mock(PollWithRetriesDriver.class));

    // Act
    SystemTestIngestByQueue actualSendSourceFilesResult = systemTestIngestByQueue
        .sendSourceFiles(new DummyInstanceProperty("Property Name"), "Files");

    // Assert
    verify(ingest).sendJobGetId(isA(InstanceProperty.class), isA(List.class));
    verify(sourceFiles).getIngestJobFilesInBucket(isA(Stream.class));
    assertSame(systemTestIngestByQueue, actualSendSourceFilesResult);
  }

  /**
   * Test {@link SystemTestIngestByQueue#sendSourceFilesToAllTables(String[])}.
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#sendSourceFilesToAllTables(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFilesToAllTables(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestByQueue SystemTestIngestByQueue.sendSourceFilesToAllTables(String[])"})
  void testSendSourceFilesToAllTables() {
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
    shortTestIdResult.systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    IngestSourceFilesContext sourceFiles = mock(IngestSourceFilesContext.class);
    when(sourceFiles.getIngestJobFilesInBucket(Mockito.<Stream<String>>any())).thenReturn(new ArrayList<>());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(instance.streamDeployedTableNames()).thenReturn(streamResult);
    IngestByQueue ingest = new IngestByQueue(instance, mock(IngestByQueueDriver.class));

    SystemTestIngestByQueue systemTestIngestByQueue = new SystemTestIngestByQueue(sourceFiles, ingest,
        new DummyInstanceProperty("Property Name"), mock(IngestTasksDriver.class), null,
        mock(PollWithRetriesDriver.class));

    // Act
    SystemTestIngestByQueue actualSendSourceFilesToAllTablesResult = systemTestIngestByQueue
        .sendSourceFilesToAllTables("Files");

    // Assert
    verify(instance).streamDeployedTableNames();
    verify(sourceFiles).getIngestJobFilesInBucket(isA(Stream.class));
    assertSame(systemTestIngestByQueue, actualSendSourceFilesToAllTablesResult);
  }

  /**
   * Test {@link SystemTestIngestByQueue#sendSourceFilesToAllTables(String[])}.
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#sendSourceFilesToAllTables(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFilesToAllTables(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestByQueue SystemTestIngestByQueue.sendSourceFilesToAllTables(String[])"})
  void testSendSourceFilesToAllTables2() {
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
    shortTestIdResult.systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    IngestSourceFilesContext sourceFiles = mock(IngestSourceFilesContext.class);
    when(sourceFiles.getIngestJobFilesInBucket(Mockito.<Stream<String>>any())).thenReturn(new ArrayList<>());
    IngestByQueue ingest = mock(IngestByQueue.class);
    when(ingest.sendJobToAllTablesGetIds(Mockito.<InstanceProperty>any(), Mockito.<List<String>>any()))
        .thenReturn(new ArrayList<>());
    SystemTestIngestByQueue systemTestIngestByQueue = new SystemTestIngestByQueue(sourceFiles, ingest,
        new DummyInstanceProperty("Property Name"), mock(IngestTasksDriver.class), null,
        mock(PollWithRetriesDriver.class));

    // Act
    SystemTestIngestByQueue actualSendSourceFilesToAllTablesResult = systemTestIngestByQueue
        .sendSourceFilesToAllTables("Files");

    // Assert
    verify(ingest).sendJobToAllTablesGetIds(isA(InstanceProperty.class), isA(List.class));
    verify(sourceFiles).getIngestJobFilesInBucket(isA(Stream.class));
    assertSame(systemTestIngestByQueue, actualSendSourceFilesToAllTablesResult);
  }

  /**
   * Test {@link SystemTestIngestByQueue#sendSourceFilesToAllTables(String[])}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#sendSourceFilesToAllTables(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFilesToAllTables(String[]); given ArrayList() add '42'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestByQueue SystemTestIngestByQueue.sendSourceFilesToAllTables(String[])"})
  void testSendSourceFilesToAllTables_givenArrayListAdd42_thenCallsGet() {
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
    shortTestIdResult.systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    IngestSourceFilesContext sourceFiles = mock(IngestSourceFilesContext.class);
    when(sourceFiles.getIngestJobFilesInBucket(Mockito.<Stream<String>>any())).thenReturn(new ArrayList<>());

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("42");
    stringList.add("foo");
    Stream<String> streamResult = stringList.stream();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.streamDeployedTableNames()).thenReturn(streamResult);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingest = new IngestByQueue(instance, driver);

    SystemTestIngestByQueue systemTestIngestByQueue = new SystemTestIngestByQueue(sourceFiles, ingest,
        new DummyInstanceProperty("Property Name"), mock(IngestTasksDriver.class), null,
        mock(PollWithRetriesDriver.class));

    // Act
    SystemTestIngestByQueue actualSendSourceFilesToAllTablesResult = systemTestIngestByQueue
        .sendSourceFilesToAllTables("Files");

    // Assert
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    verify(driver, atLeast(1)).sendJobGetId(eq("Get"), Mockito.<String>any(), isA(List.class));
    verify(instance, atLeast(1)).getInstanceProperties();
    verify(instance).streamDeployedTableNames();
    verify(sourceFiles).getIngestJobFilesInBucket(isA(Stream.class));
    assertSame(systemTestIngestByQueue, actualSendSourceFilesToAllTablesResult);
  }

  /**
   * Test {@link SystemTestIngestByQueue#sendSourceFilesToAllTables(String[])}.
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#sendSourceFilesToAllTables(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFilesToAllTables(String[]); given InstanceProperties get(InstanceProperty) return 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestByQueue SystemTestIngestByQueue.sendSourceFilesToAllTables(String[])"})
  void testSendSourceFilesToAllTables_givenInstancePropertiesGetReturnGet_thenCallsGet() {
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
    shortTestIdResult.systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    IngestSourceFilesContext sourceFiles = mock(IngestSourceFilesContext.class);
    when(sourceFiles.getIngestJobFilesInBucket(Mockito.<Stream<String>>any())).thenReturn(new ArrayList<>());

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    Stream<String> streamResult = stringList.stream();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.streamDeployedTableNames()).thenReturn(streamResult);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingest = new IngestByQueue(instance, driver);

    SystemTestIngestByQueue systemTestIngestByQueue = new SystemTestIngestByQueue(sourceFiles, ingest,
        new DummyInstanceProperty("Property Name"), mock(IngestTasksDriver.class), null,
        mock(PollWithRetriesDriver.class));

    // Act
    SystemTestIngestByQueue actualSendSourceFilesToAllTablesResult = systemTestIngestByQueue
        .sendSourceFilesToAllTables("Files");

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("foo"), isA(List.class));
    verify(instance).getInstanceProperties();
    verify(instance).streamDeployedTableNames();
    verify(sourceFiles).getIngestJobFilesInBucket(isA(Stream.class));
    assertSame(systemTestIngestByQueue, actualSendSourceFilesToAllTablesResult);
  }

  /**
   * Test {@link SystemTestIngestByQueue#waitForTask()}.
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#waitForTask()}
   */
  @Test
  @DisplayName("Test waitForTask()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestByQueue SystemTestIngestByQueue.waitForTask()"})
  void testWaitForTask() {
    // Arrange
    WaitForTasks waitForTasks = mock(WaitForTasks.class);
    doNothing().when(waitForTasks)
        .waitUntilOneTaskStartedAJob(Mockito.<List<String>>any(), Mockito.<PollWithRetriesDriver>any());
    IngestTasksDriver tasksDriver = mock(IngestTasksDriver.class);
    when(tasksDriver.waitForTasksForCurrentInstance()).thenReturn(waitForTasks);
    IngestSourceFilesContext sourceFiles = new IngestSourceFilesContext(mock(DeployedSystemTestResources.class),
        mock(SystemTestInstanceContext.class));

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
    InMemorySleeperInstanceDriver instanceDriver = new InMemorySleeperInstanceDriver(null);
    TestContext.Builder displayNameResult = TestContext.builder().displayName("Display Name");
    TestContext.Builder tagsResult = displayNameResult.tags(new HashSet<>());
    Class<Object> testClass = Object.class;
    TestContext testContext = tagsResult.testClass(testClass).testMethod(null).build();
    IngestByQueue ingest = new IngestByQueue(
        new SystemTestInstanceContext(parameters, null, instanceDriver, testContext), mock(IngestByQueueDriver.class));

    SystemTestIngestByQueue systemTestIngestByQueue = new SystemTestIngestByQueue(sourceFiles, ingest,
        new DummyInstanceProperty("Property Name"), tasksDriver, null, mock(PollWithRetriesDriver.class));

    // Act
    SystemTestIngestByQueue actualWaitForTaskResult = systemTestIngestByQueue.waitForTask();

    // Assert
    verify(tasksDriver).waitForTasksForCurrentInstance();
    verify(waitForTasks).waitUntilOneTaskStartedAJob(isA(List.class), isA(PollWithRetriesDriver.class));
    assertSame(systemTestIngestByQueue, actualWaitForTaskResult);
  }

  /**
   * Test {@link SystemTestIngestByQueue#waitForJobs(PollWithRetries)} with {@code PollWithRetries}.
   * <ul>
   *   <li>Then calls {@link WaitForJobs#waitForJobs(Collection, PollWithRetries)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#waitForJobs(PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitForJobs(PollWithRetries) with 'PollWithRetries'; then calls waitForJobs(Collection, PollWithRetries)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestIngestByQueue.waitForJobs(PollWithRetries)"})
  void testWaitForJobsWithPollWithRetries_thenCallsWaitForJobs() {
    // Arrange
    WaitForJobs waitForJobs = mock(WaitForJobs.class);
    doNothing().when(waitForJobs).waitForJobs(Mockito.<Collection<String>>any(), Mockito.<PollWithRetries>any());
    IngestSourceFilesContext sourceFiles = new IngestSourceFilesContext(mock(DeployedSystemTestResources.class),
        mock(SystemTestInstanceContext.class));

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
    InMemorySleeperInstanceDriver instanceDriver = new InMemorySleeperInstanceDriver(
        mock(InMemorySleeperTablesDriver.class));
    TestContext.Builder displayNameResult = TestContext.builder().displayName("Display Name");
    TestContext.Builder tagsResult = displayNameResult.tags(new HashSet<>());
    Class<Object> testClass = Object.class;
    TestContext testContext = tagsResult.testClass(testClass).testMethod(null).build();
    IngestByQueue ingest = new IngestByQueue(
        new SystemTestInstanceContext(parameters, null, instanceDriver, testContext), mock(IngestByQueueDriver.class));

    // Act
    (new SystemTestIngestByQueue(sourceFiles, ingest, new DummyInstanceProperty("Property Name"),
        mock(IngestTasksDriver.class), waitForJobs, mock(PollWithRetriesDriver.class)))
        .waitForJobs(mock(PollWithRetries.class));

    // Assert
    verify(waitForJobs).waitForJobs(isA(Collection.class), isA(PollWithRetries.class));
  }

  /**
   * Test {@link SystemTestIngestByQueue#waitForJobs()}.
   * <ul>
   *   <li>Given {@link WaitForJobs} {@link WaitForJobs#waitForJobs(Collection)} does nothing.</li>
   *   <li>Then calls {@link WaitForJobs#waitForJobs(Collection)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestByQueue#waitForJobs()}
   */
  @Test
  @DisplayName("Test waitForJobs(); given WaitForJobs waitForJobs(Collection) does nothing; then calls waitForJobs(Collection)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestIngestByQueue.waitForJobs()"})
  void testWaitForJobs_givenWaitForJobsWaitForJobsDoesNothing_thenCallsWaitForJobs() {
    // Arrange
    WaitForJobs waitForJobs = mock(WaitForJobs.class);
    doNothing().when(waitForJobs).waitForJobs(Mockito.<Collection<String>>any());
    IngestSourceFilesContext sourceFiles = new IngestSourceFilesContext(mock(DeployedSystemTestResources.class),
        mock(SystemTestInstanceContext.class));

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
    InMemorySleeperInstanceDriver instanceDriver = new InMemorySleeperInstanceDriver(
        mock(InMemorySleeperTablesDriver.class));
    TestContext.Builder displayNameResult = TestContext.builder().displayName("Display Name");
    TestContext.Builder tagsResult = displayNameResult.tags(new HashSet<>());
    Class<Object> testClass = Object.class;
    TestContext testContext = tagsResult.testClass(testClass).testMethod(null).build();
    IngestByQueue ingest = new IngestByQueue(
        new SystemTestInstanceContext(parameters, null, instanceDriver, testContext), mock(IngestByQueueDriver.class));

    // Act
    (new SystemTestIngestByQueue(sourceFiles, ingest, new DummyInstanceProperty("Property Name"),
        mock(IngestTasksDriver.class), waitForJobs, mock(PollWithRetriesDriver.class))).waitForJobs();

    // Assert
    verify(waitForJobs).waitForJobs(isA(Collection.class));
  }
}
