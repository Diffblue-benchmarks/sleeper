package sleeper.systemtest.dsl.sourcedata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.instance.SystemTestParameters.Builder;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySystemTestDeploymentDriver;
import sleeper.systemtest.dsl.util.TestContext;

class IngestSourceFilesContextDiffblueTest {
  /**
   * Test {@link IngestSourceFilesContext#getFilePath(String)}.
   * <ul>
   *   <li>When {@code Name}.</li>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestSourceFilesContext#getFilePath(String)}
   */
  @Test
  @DisplayName("Test getFilePath(String); when 'Name'; then throw IllegalStateException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestSourceFilesContext.getFilePath(String)"})
  void testGetFilePath_whenName_thenThrowIllegalStateException() {
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
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new IngestSourceFilesContext(systemTest, new SystemTestInstanceContext(mock(SystemTestParameters.class),
            mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class))))
            .getFilePath("Name"));
  }

  /**
   * Test {@link IngestSourceFilesContext#getIngestJobFilesInBucket(Stream)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestSourceFilesContext#getIngestJobFilesInBucket(Stream)}
   */
  @Test
  @DisplayName("Test getIngestJobFilesInBucket(Stream); when ArrayList() stream; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestSourceFilesContext.getIngestJobFilesInBucket(Stream)"})
  void testGetIngestJobFilesInBucket_whenArrayListStream_thenReturnEmpty() {
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
    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(parameters,
        new InMemorySystemTestDeploymentDriver());

    IngestSourceFilesContext ingestSourceFilesContext = new IngestSourceFilesContext(systemTest,
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)));

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> files = stringList.stream();

    // Act and Assert
    assertTrue(ingestSourceFilesContext.getIngestJobFilesInBucket(files).isEmpty());
  }

  /**
   * Test {@link IngestSourceFilesContext#ingestJobFileInBucket(String)}.
   * <ul>
   *   <li>Then calls {@link DeployedSystemTestResources#getSystemTestBucketName()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestSourceFilesContext#ingestJobFileInBucket(String)}
   */
  @Test
  @DisplayName("Test ingestJobFileInBucket(String); then calls getSystemTestBucketName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestSourceFilesContext.ingestJobFileInBucket(String)"})
  void testIngestJobFileInBucket_thenCallsGetSystemTestBucketName() {
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
    DeployedSystemTestResources systemTest = mock(DeployedSystemTestResources.class);
    when(systemTest.getSystemTestBucketName()).thenReturn("bucket-name");

    // Act
    (new IngestSourceFilesContext(systemTest, new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class))))
        .ingestJobFileInBucket("bucket-name");

    // Assert
    verify(systemTest).getSystemTestBucketName();
  }

  /**
   * Test {@link IngestSourceFilesContext#getSourceBucketName()}.
   * <ul>
   *   <li>Then return {@code bucket-name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestSourceFilesContext#getSourceBucketName()}
   */
  @Test
  @DisplayName("Test getSourceBucketName(); then return 'bucket-name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestSourceFilesContext.getSourceBucketName()"})
  void testGetSourceBucketName_thenReturnBucketName() {
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
    DeployedSystemTestResources systemTest = mock(DeployedSystemTestResources.class);
    when(systemTest.getSystemTestBucketName()).thenReturn("bucket-name");

    // Act
    String actualSourceBucketName = (new IngestSourceFilesContext(systemTest,
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class))))
        .getSourceBucketName();

    // Assert
    verify(systemTest).getSystemTestBucketName();
    assertEquals("bucket-name", actualSourceBucketName);
  }
}
