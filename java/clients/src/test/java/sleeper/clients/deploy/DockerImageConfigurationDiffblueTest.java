package sleeper.clients.deploy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.admin.properties.PropertiesDiff;
import sleeper.core.deploy.LambdaJar;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.validation.OptionalStack;

class DockerImageConfigurationDiffblueTest {
  /**
   * Test {@link DockerImageConfiguration#getImagesToUploadOnUpdate(InstanceProperties, PropertiesDiff)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DockerImageConfiguration#getImagesToUploadOnUpdate(InstanceProperties, PropertiesDiff)}
   */
  @Test
  @DisplayName("Test getImagesToUploadOnUpdate(InstanceProperties, PropertiesDiff); when InstanceProperties(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DockerImageConfiguration.getImagesToUploadOnUpdate(InstanceProperties, PropertiesDiff)"})
  void testGetImagesToUploadOnUpdate_whenInstanceProperties_thenReturnEmpty() {
    // Arrange
    DockerImageConfiguration resultDefault = DockerImageConfiguration.getDefault();
    InstanceProperties properties = new InstanceProperties();

    // Act and Assert
    assertTrue(resultDefault.getImagesToUploadOnUpdate(properties, PropertiesDiff.noChanges()).isEmpty());
  }

  /**
   * Test {@link DockerImageConfiguration#getImagesToUpload(InstanceProperties)} with {@code properties}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return size is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link DockerImageConfiguration#getImagesToUpload(InstanceProperties)}
   */
  @Test
  @DisplayName("Test getImagesToUpload(InstanceProperties) with 'properties'; when InstanceProperties(); then return size is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DockerImageConfiguration.getImagesToUpload(InstanceProperties)"})
  void testGetImagesToUploadWithProperties_whenInstanceProperties_thenReturnSizeIsThree() {
    // Arrange
    DockerImageConfiguration resultDefault = DockerImageConfiguration.getDefault();

    // Act
    List<StackDockerImage> actualImagesToUpload = resultDefault.getImagesToUpload(new InstanceProperties());

    // Assert
    assertEquals(3, actualImagesToUpload.size());
    StackDockerImage getResult = actualImagesToUpload.get(1);
    assertEquals("bulk-import-runner-emr-serverless", getResult.getDirectoryName());
    assertEquals("bulk-import-runner-emr-serverless", getResult.getImageName());
    StackDockerImage getResult2 = actualImagesToUpload.get(2);
    assertEquals("compaction-job-execution", getResult2.getDirectoryName());
    assertEquals("compaction-job-execution", getResult2.getImageName());
    StackDockerImage getResult3 = actualImagesToUpload.get(0);
    assertEquals("ingest", getResult3.getDirectoryName());
    assertEquals("ingest", getResult3.getImageName());
    Optional<LambdaJar> lambdaJar = getResult3.getLambdaJar();
    assertFalse(lambdaJar.isPresent());
    assertFalse(getResult3.isBuildx());
    assertFalse(getResult.isBuildx());
    assertFalse(getResult3.isCreateEmrServerlessPolicy());
    assertFalse(getResult2.isCreateEmrServerlessPolicy());
    assertTrue(getResult2.isBuildx());
    assertTrue(getResult.isCreateEmrServerlessPolicy());
    assertSame(lambdaJar, getResult.getLambdaJar());
    assertSame(lambdaJar, getResult2.getLambdaJar());
  }

  /**
   * Test {@link DockerImageConfiguration#getInstanceIdFromRepoName(String)}.
   * <p>
   * Method under test: {@link DockerImageConfiguration#getInstanceIdFromRepoName(String)}
   */
  @Test
  @DisplayName("Test getInstanceIdFromRepoName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DockerImageConfiguration.getInstanceIdFromRepoName(String)"})
  void testGetInstanceIdFromRepoName() {
    // Arrange
    HashMap<OptionalStack, StackDockerImage> imageByStack = new HashMap<>();

    // Act and Assert
    assertFalse(
        (new DockerImageConfiguration(imageByStack, new ArrayList<>())).getInstanceIdFromRepoName("Repository Name")
            .isPresent());
  }

  /**
   * Test {@link DockerImageConfiguration#getInstanceIdFromRepoName(String)}.
   * <ul>
   *   <li>Given Default.</li>
   * </ul>
   * <p>
   * Method under test: {@link DockerImageConfiguration#getInstanceIdFromRepoName(String)}
   */
  @Test
  @DisplayName("Test getInstanceIdFromRepoName(String); given Default")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DockerImageConfiguration.getInstanceIdFromRepoName(String)"})
  void testGetInstanceIdFromRepoName_givenDefault() {
    // Arrange, Act and Assert
    assertFalse(DockerImageConfiguration.getDefault().getInstanceIdFromRepoName("Repository Name").isPresent());
  }
}
