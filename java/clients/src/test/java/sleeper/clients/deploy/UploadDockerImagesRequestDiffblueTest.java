package sleeper.clients.deploy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.admin.properties.PropertiesDiff;
import sleeper.clients.deploy.UploadDockerImagesRequest.Builder;
import sleeper.core.deploy.LambdaJar;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.validation.LambdaDeployType;
import sleeper.core.properties.validation.OptionalStack;

class UploadDockerImagesRequestDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#account(String)}
   *   <li>{@link Builder#ecrPrefix(String)}
   *   <li>{@link Builder#images(List)}
   *   <li>{@link Builder#region(String)}
   *   <li>{@link Builder#version(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.account(String)", "UploadDockerImagesRequest Builder.build()",
      "Builder Builder.ecrPrefix(String)", "Builder Builder.images(List)", "Builder Builder.region(String)",
      "Builder Builder.version(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    ArrayList<StackDockerImage> images = new ArrayList<>();

    // Act
    UploadDockerImagesRequest actualBuildResult = ecrPrefixResult.images(images)
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Assert
    assertEquals("1.0.2", actualBuildResult.getVersion());
    assertEquals("3", actualBuildResult.getAccount());
    assertEquals("Ecr Prefix", actualBuildResult.getEcrPrefix());
    assertEquals("us-east-2", actualBuildResult.getRegion());
    List<StackDockerImage> images2 = actualBuildResult.getImages();
    assertTrue(images2.isEmpty());
    assertSame(images, images2);
  }

  /**
   * Test Builder {@link Builder#properties(InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(InstanceProperties)}
   */
  @Test
  @DisplayName("Test Builder properties(InstanceProperties); when InstanceProperties(); then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(InstanceProperties)"})
  void testBuilderProperties_whenInstanceProperties_thenReturnBuilder() {
    // Arrange
    Builder builderResult = UploadDockerImagesRequest.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.properties(new InstanceProperties()));
  }

  /**
   * Test {@link UploadDockerImagesRequest#forNewDeployment(InstanceProperties, DockerImageConfiguration)} with {@code properties}, {@code configuration}.
   * <ul>
   *   <li>Given {@code JAR}.</li>
   *   <li>Then return Account is {@code Get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#forNewDeployment(InstanceProperties, DockerImageConfiguration)}
   */
  @Test
  @DisplayName("Test forNewDeployment(InstanceProperties, DockerImageConfiguration) with 'properties', 'configuration'; given 'JAR'; then return Account is 'Get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "UploadDockerImagesRequest UploadDockerImagesRequest.forNewDeployment(InstanceProperties, DockerImageConfiguration)"})
  void testForNewDeploymentWithPropertiesConfiguration_givenJar_thenReturnAccountIsGet() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getEnumValue(Mockito.<InstanceProperty>any(), Mockito.<Class<LambdaDeployType>>any()))
        .thenReturn(LambdaDeployType.JAR);
    when(properties.getEnumList(Mockito.<InstanceProperty>any(), Mockito.<Class<OptionalStack>>any()))
        .thenReturn(new ArrayList<>());
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    UploadDockerImagesRequest actualForNewDeploymentResult = UploadDockerImagesRequest.forNewDeployment(properties,
        DockerImageConfiguration.getDefault());

    // Assert
    verify(properties).getEnumList(isA(InstanceProperty.class), isA(Class.class));
    verify(properties).getEnumValue(isA(InstanceProperty.class), isA(Class.class));
    verify(properties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    assertEquals("Get", actualForNewDeploymentResult.getAccount());
    assertEquals("Get", actualForNewDeploymentResult.getEcrPrefix());
    assertEquals("Get", actualForNewDeploymentResult.getRegion());
    assertEquals("Get", actualForNewDeploymentResult.getVersion());
    assertTrue(actualForNewDeploymentResult.getImages().isEmpty());
  }

  /**
   * Test {@link UploadDockerImagesRequest#forNewDeployment(InstanceProperties, DockerImageConfiguration)} with {@code properties}, {@code configuration}.
   * <ul>
   *   <li>Then return Images size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#forNewDeployment(InstanceProperties, DockerImageConfiguration)}
   */
  @Test
  @DisplayName("Test forNewDeployment(InstanceProperties, DockerImageConfiguration) with 'properties', 'configuration'; then return Images size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "UploadDockerImagesRequest UploadDockerImagesRequest.forNewDeployment(InstanceProperties, DockerImageConfiguration)"})
  void testForNewDeploymentWithPropertiesConfiguration_thenReturnImagesSizeIsTwo() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getEnumValue(Mockito.<InstanceProperty>any(), Mockito.<Class<LambdaDeployType>>any()))
        .thenReturn(LambdaDeployType.CONTAINER);
    when(properties.getEnumList(Mockito.<InstanceProperty>any(), Mockito.<Class<OptionalStack>>any()))
        .thenReturn(new ArrayList<>());
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    UploadDockerImagesRequest actualForNewDeploymentResult = UploadDockerImagesRequest.forNewDeployment(properties,
        DockerImageConfiguration.getDefault());

    // Assert
    verify(properties).getEnumList(isA(InstanceProperty.class), isA(Class.class));
    verify(properties).getEnumValue(isA(InstanceProperty.class), isA(Class.class));
    verify(properties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    List<StackDockerImage> images = actualForNewDeploymentResult.getImages();
    assertEquals(2, images.size());
    StackDockerImage getResult = images.get(0);
    assertEquals("custom-resources-lambda", getResult.getImageName());
    assertEquals("lambda", getResult.getDirectoryName());
    StackDockerImage getResult2 = images.get(1);
    assertEquals("lambda", getResult2.getDirectoryName());
    assertEquals("statestore-lambda", getResult2.getImageName());
    assertFalse(getResult.isBuildx());
    assertFalse(getResult2.isBuildx());
    assertFalse(getResult.isCreateEmrServerlessPolicy());
    assertFalse(getResult2.isCreateEmrServerlessPolicy());
    assertTrue(getResult.getLambdaJar().isPresent());
    assertTrue(getResult2.getLambdaJar().isPresent());
  }

  /**
   * Test {@link UploadDockerImagesRequest#forNewDeployment(InstanceProperties, String)} with {@code properties}, {@code version}.
   * <ul>
   *   <li>Given {@code JAR}.</li>
   *   <li>Then return Version is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#forNewDeployment(InstanceProperties, String)}
   */
  @Test
  @DisplayName("Test forNewDeployment(InstanceProperties, String) with 'properties', 'version'; given 'JAR'; then return Version is '1.0.2'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "UploadDockerImagesRequest UploadDockerImagesRequest.forNewDeployment(InstanceProperties, String)"})
  void testForNewDeploymentWithPropertiesVersion_givenJar_thenReturnVersionIs102() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getEnumValue(Mockito.<InstanceProperty>any(), Mockito.<Class<LambdaDeployType>>any()))
        .thenReturn(LambdaDeployType.JAR);
    when(properties.getEnumList(Mockito.<InstanceProperty>any(), Mockito.<Class<OptionalStack>>any()))
        .thenReturn(new ArrayList<>());
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    UploadDockerImagesRequest actualForNewDeploymentResult = UploadDockerImagesRequest.forNewDeployment(properties,
        "1.0.2");

    // Assert
    verify(properties).getEnumList(isA(InstanceProperty.class), isA(Class.class));
    verify(properties).getEnumValue(isA(InstanceProperty.class), isA(Class.class));
    verify(properties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    assertEquals("1.0.2", actualForNewDeploymentResult.getVersion());
    assertEquals("Get", actualForNewDeploymentResult.getAccount());
    assertEquals("Get", actualForNewDeploymentResult.getEcrPrefix());
    assertEquals("Get", actualForNewDeploymentResult.getRegion());
    assertTrue(actualForNewDeploymentResult.getImages().isEmpty());
  }

  /**
   * Test {@link UploadDockerImagesRequest#forNewDeployment(InstanceProperties, String)} with {@code properties}, {@code version}.
   * <ul>
   *   <li>Then return Images size is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#forNewDeployment(InstanceProperties, String)}
   */
  @Test
  @DisplayName("Test forNewDeployment(InstanceProperties, String) with 'properties', 'version'; then return Images size is four")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "UploadDockerImagesRequest UploadDockerImagesRequest.forNewDeployment(InstanceProperties, String)"})
  void testForNewDeploymentWithPropertiesVersion_thenReturnImagesSizeIsFour() {
    // Arrange
    ArrayList<OptionalStack> optionalStackList = new ArrayList<>();
    optionalStackList.add(OptionalStack.IngestBatcherStack);
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getEnumValue(Mockito.<InstanceProperty>any(), Mockito.<Class<LambdaDeployType>>any()))
        .thenReturn(LambdaDeployType.CONTAINER);
    when(properties.getEnumList(Mockito.<InstanceProperty>any(), Mockito.<Class<OptionalStack>>any()))
        .thenReturn(optionalStackList);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    UploadDockerImagesRequest actualForNewDeploymentResult = UploadDockerImagesRequest.forNewDeployment(properties,
        "1.0.2");

    // Assert
    verify(properties).getEnumList(isA(InstanceProperty.class), isA(Class.class));
    verify(properties).getEnumValue(isA(InstanceProperty.class), isA(Class.class));
    verify(properties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    List<StackDockerImage> images = actualForNewDeploymentResult.getImages();
    assertEquals(4, images.size());
    StackDockerImage getResult = images.get(2);
    assertEquals("custom-resources-lambda", getResult.getImageName());
    assertEquals("ingest-batcher-job-creator-lambda", images.get(1).getImageName());
    assertEquals("ingest-batcher-submitter-lambda", images.get(0).getImageName());
    assertEquals("lambda", getResult.getDirectoryName());
    StackDockerImage getResult2 = images.get(3);
    assertEquals("lambda", getResult2.getDirectoryName());
    assertEquals("statestore-lambda", getResult2.getImageName());
    assertFalse(getResult.isBuildx());
    assertFalse(getResult2.isBuildx());
    assertFalse(getResult.isCreateEmrServerlessPolicy());
    assertFalse(getResult2.isCreateEmrServerlessPolicy());
  }

  /**
   * Test {@link UploadDockerImagesRequest#forNewDeployment(InstanceProperties, String)} with {@code properties}, {@code version}.
   * <ul>
   *   <li>Then return Images size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#forNewDeployment(InstanceProperties, String)}
   */
  @Test
  @DisplayName("Test forNewDeployment(InstanceProperties, String) with 'properties', 'version'; then return Images size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "UploadDockerImagesRequest UploadDockerImagesRequest.forNewDeployment(InstanceProperties, String)"})
  void testForNewDeploymentWithPropertiesVersion_thenReturnImagesSizeIsTwo() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getEnumValue(Mockito.<InstanceProperty>any(), Mockito.<Class<LambdaDeployType>>any()))
        .thenReturn(LambdaDeployType.CONTAINER);
    when(properties.getEnumList(Mockito.<InstanceProperty>any(), Mockito.<Class<OptionalStack>>any()))
        .thenReturn(new ArrayList<>());
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    UploadDockerImagesRequest actualForNewDeploymentResult = UploadDockerImagesRequest.forNewDeployment(properties,
        "1.0.2");

    // Assert
    verify(properties).getEnumList(isA(InstanceProperty.class), isA(Class.class));
    verify(properties).getEnumValue(isA(InstanceProperty.class), isA(Class.class));
    verify(properties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    List<StackDockerImage> images = actualForNewDeploymentResult.getImages();
    assertEquals(2, images.size());
    StackDockerImage getResult = images.get(0);
    LambdaJar getResult2 = getResult.getLambdaJar().get();
    assertEquals("cdk-custom-resources-0.29.0-SNAPSHOT.jar", getResult2.getFilename());
    assertEquals("custom-resources-lambda", getResult.getImageName());
    assertEquals("custom-resources-lambda", getResult2.getImageName());
    StackDockerImage getResult3 = images.get(1);
    assertEquals("statestore-lambda", getResult3.getImageName());
    LambdaJar getResult4 = getResult3.getLambdaJar().get();
    assertEquals("statestore-lambda", getResult4.getImageName());
    assertEquals("statestore-lambda-0.29.0-SNAPSHOT.jar", getResult4.getFilename());
  }

  /**
   * Test {@link UploadDockerImagesRequest#forExistingInstance(InstanceProperties)}.
   * <ul>
   *   <li>Given {@code CONTAINER}.</li>
   *   <li>Then return Images size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#forExistingInstance(InstanceProperties)}
   */
  @Test
  @DisplayName("Test forExistingInstance(InstanceProperties); given 'CONTAINER'; then return Images size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UploadDockerImagesRequest UploadDockerImagesRequest.forExistingInstance(InstanceProperties)"})
  void testForExistingInstance_givenContainer_thenReturnImagesSizeIsTwo() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getEnumValue(Mockito.<InstanceProperty>any(), Mockito.<Class<LambdaDeployType>>any()))
        .thenReturn(LambdaDeployType.CONTAINER);
    when(properties.getEnumList(Mockito.<InstanceProperty>any(), Mockito.<Class<OptionalStack>>any()))
        .thenReturn(new ArrayList<>());
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    UploadDockerImagesRequest actualForExistingInstanceResult = UploadDockerImagesRequest
        .forExistingInstance(properties);

    // Assert
    verify(properties).getEnumList(isA(InstanceProperty.class), isA(Class.class));
    verify(properties).getEnumValue(isA(InstanceProperty.class), isA(Class.class));
    verify(properties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    List<StackDockerImage> images = actualForExistingInstanceResult.getImages();
    assertEquals(2, images.size());
    StackDockerImage getResult = images.get(0);
    LambdaJar getResult2 = getResult.getLambdaJar().get();
    assertEquals("cdk-custom-resources-0.29.0-SNAPSHOT.jar", getResult2.getFilename());
    assertEquals("custom-resources-lambda", getResult.getImageName());
    assertEquals("custom-resources-lambda", getResult2.getImageName());
    StackDockerImage getResult3 = images.get(1);
    assertEquals("statestore-lambda", getResult3.getImageName());
    LambdaJar getResult4 = getResult3.getLambdaJar().get();
    assertEquals("statestore-lambda", getResult4.getImageName());
    assertEquals("statestore-lambda-0.29.0-SNAPSHOT.jar", getResult4.getFilename());
  }

  /**
   * Test {@link UploadDockerImagesRequest#forExistingInstance(InstanceProperties)}.
   * <ul>
   *   <li>Given {@code JAR}.</li>
   *   <li>Then return Account is {@code Get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#forExistingInstance(InstanceProperties)}
   */
  @Test
  @DisplayName("Test forExistingInstance(InstanceProperties); given 'JAR'; then return Account is 'Get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UploadDockerImagesRequest UploadDockerImagesRequest.forExistingInstance(InstanceProperties)"})
  void testForExistingInstance_givenJar_thenReturnAccountIsGet() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getEnumValue(Mockito.<InstanceProperty>any(), Mockito.<Class<LambdaDeployType>>any()))
        .thenReturn(LambdaDeployType.JAR);
    when(properties.getEnumList(Mockito.<InstanceProperty>any(), Mockito.<Class<OptionalStack>>any()))
        .thenReturn(new ArrayList<>());
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    UploadDockerImagesRequest actualForExistingInstanceResult = UploadDockerImagesRequest
        .forExistingInstance(properties);

    // Assert
    verify(properties).getEnumList(isA(InstanceProperty.class), isA(Class.class));
    verify(properties).getEnumValue(isA(InstanceProperty.class), isA(Class.class));
    verify(properties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    assertEquals("Get", actualForExistingInstanceResult.getAccount());
    assertEquals("Get", actualForExistingInstanceResult.getEcrPrefix());
    assertEquals("Get", actualForExistingInstanceResult.getRegion());
    assertEquals("Get", actualForExistingInstanceResult.getVersion());
    assertTrue(actualForExistingInstanceResult.getImages().isEmpty());
  }

  /**
   * Test {@link UploadDockerImagesRequest#forExistingInstance(InstanceProperties)}.
   * <ul>
   *   <li>Then return Images size is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#forExistingInstance(InstanceProperties)}
   */
  @Test
  @DisplayName("Test forExistingInstance(InstanceProperties); then return Images size is four")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UploadDockerImagesRequest UploadDockerImagesRequest.forExistingInstance(InstanceProperties)"})
  void testForExistingInstance_thenReturnImagesSizeIsFour() {
    // Arrange
    ArrayList<OptionalStack> optionalStackList = new ArrayList<>();
    optionalStackList.add(OptionalStack.IngestBatcherStack);
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getEnumValue(Mockito.<InstanceProperty>any(), Mockito.<Class<LambdaDeployType>>any()))
        .thenReturn(LambdaDeployType.CONTAINER);
    when(properties.getEnumList(Mockito.<InstanceProperty>any(), Mockito.<Class<OptionalStack>>any()))
        .thenReturn(optionalStackList);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    UploadDockerImagesRequest actualForExistingInstanceResult = UploadDockerImagesRequest
        .forExistingInstance(properties);

    // Assert
    verify(properties).getEnumList(isA(InstanceProperty.class), isA(Class.class));
    verify(properties).getEnumValue(isA(InstanceProperty.class), isA(Class.class));
    verify(properties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    List<StackDockerImage> images = actualForExistingInstanceResult.getImages();
    assertEquals(4, images.size());
    StackDockerImage getResult = images.get(2);
    assertEquals("custom-resources-lambda", getResult.getImageName());
    assertEquals("ingest-batcher-job-creator-lambda", images.get(1).getImageName());
    assertEquals("ingest-batcher-submitter-lambda", images.get(0).getImageName());
    assertEquals("lambda", getResult.getDirectoryName());
    StackDockerImage getResult2 = images.get(3);
    assertEquals("lambda", getResult2.getDirectoryName());
    assertEquals("statestore-lambda", getResult2.getImageName());
    assertFalse(getResult.isBuildx());
    assertFalse(getResult2.isBuildx());
    assertFalse(getResult.isCreateEmrServerlessPolicy());
    assertFalse(getResult2.isCreateEmrServerlessPolicy());
  }

  /**
   * Test {@link UploadDockerImagesRequest#forUpdateIfNeeded(InstanceProperties, PropertiesDiff, DockerImageConfiguration)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#forUpdateIfNeeded(InstanceProperties, PropertiesDiff, DockerImageConfiguration)}
   */
  @Test
  @DisplayName("Test forUpdateIfNeeded(InstanceProperties, PropertiesDiff, DockerImageConfiguration); when InstanceProperties(); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional UploadDockerImagesRequest.forUpdateIfNeeded(InstanceProperties, PropertiesDiff, DockerImageConfiguration)"})
  void testForUpdateIfNeeded_whenInstanceProperties_thenReturnNotPresent() {
    // Arrange
    InstanceProperties properties = new InstanceProperties();
    PropertiesDiff diff = PropertiesDiff.noChanges();

    // Act
    Optional<UploadDockerImagesRequest> actualForUpdateIfNeededResult = UploadDockerImagesRequest
        .forUpdateIfNeeded(properties, diff, DockerImageConfiguration.getDefault());

    // Assert
    assertFalse(actualForUpdateIfNeededResult.isPresent());
  }

  /**
   * Test {@link UploadDockerImagesRequest#withExtraImages(List)}.
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#withExtraImages(List)}
   */
  @Test
  @DisplayName("Test withExtraImages(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UploadDockerImagesRequest UploadDockerImagesRequest.withExtraImages(List)"})
  void testWithExtraImages() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult = ecrPrefixResult.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult.withExtraImages(new ArrayList<>()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UploadDockerImagesRequest#toString()}
   *   <li>{@link UploadDockerImagesRequest#getAccount()}
   *   <li>{@link UploadDockerImagesRequest#getEcrPrefix()}
   *   <li>{@link UploadDockerImagesRequest#getImages()}
   *   <li>{@link UploadDockerImagesRequest#getRegion()}
   *   <li>{@link UploadDockerImagesRequest#getVersion()}
   *   <li>{@link UploadDockerImagesRequest#toBuilder()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String UploadDockerImagesRequest.getAccount()", "String UploadDockerImagesRequest.getEcrPrefix()",
      "List UploadDockerImagesRequest.getImages()", "String UploadDockerImagesRequest.getRegion()",
      "String UploadDockerImagesRequest.getVersion()", "Builder UploadDockerImagesRequest.toBuilder()",
      "String UploadDockerImagesRequest.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    ArrayList<StackDockerImage> images = new ArrayList<>();
    UploadDockerImagesRequest buildResult = ecrPrefixResult.images(images).region("us-east-2").version("1.0.2").build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualAccount = buildResult.getAccount();
    String actualEcrPrefix = buildResult.getEcrPrefix();
    List<StackDockerImage> actualImages = buildResult.getImages();
    String actualRegion = buildResult.getRegion();
    String actualVersion = buildResult.getVersion();
    buildResult.toBuilder();

    // Assert
    assertEquals("1.0.2", actualVersion);
    assertEquals("3", actualAccount);
    assertEquals("Ecr Prefix", actualEcrPrefix);
    assertEquals("StacksForDockerUpload{ecrPrefix='Ecr Prefix', account='3', region='us-east-2', version='1.0.2',"
        + " images=[]}", actualToStringResult);
    assertEquals("us-east-2", actualRegion);
    assertTrue(actualImages.isEmpty());
    assertSame(images, actualImages);
  }

  /**
   * Test {@link UploadDockerImagesRequest#equals(Object)}, and {@link UploadDockerImagesRequest#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UploadDockerImagesRequest#equals(Object)}
   *   <li>{@link UploadDockerImagesRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean UploadDockerImagesRequest.equals(Object)", "int UploadDockerImagesRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult = ecrPrefixResult.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();
    Builder ecrPrefixResult2 = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult2 = ecrPrefixResult2.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link UploadDockerImagesRequest#equals(Object)}, and {@link UploadDockerImagesRequest#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UploadDockerImagesRequest#equals(Object)}
   *   <li>{@link UploadDockerImagesRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean UploadDockerImagesRequest.equals(Object)", "int UploadDockerImagesRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult = ecrPrefixResult.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link UploadDockerImagesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean UploadDockerImagesRequest.equals(Object)", "int UploadDockerImagesRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("Account").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult = ecrPrefixResult.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();
    Builder ecrPrefixResult2 = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult2 = ecrPrefixResult2.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link UploadDockerImagesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean UploadDockerImagesRequest.equals(Object)", "int UploadDockerImagesRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("42");
    UploadDockerImagesRequest buildResult = ecrPrefixResult.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();
    Builder ecrPrefixResult2 = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult2 = ecrPrefixResult2.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link UploadDockerImagesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean UploadDockerImagesRequest.equals(Object)", "int UploadDockerImagesRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<StackDockerImage> images = new ArrayList<>();
    StackDockerImage.Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();
    images.add(buildResult);
    UploadDockerImagesRequest buildResult2 = UploadDockerImagesRequest.builder()
        .account("3")
        .ecrPrefix("Ecr Prefix")
        .images(images)
        .region("us-east-2")
        .version("1.0.2")
        .build();
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult3 = ecrPrefixResult.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertNotEquals(buildResult2, buildResult3);
  }

  /**
   * Test {@link UploadDockerImagesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean UploadDockerImagesRequest.equals(Object)", "int UploadDockerImagesRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult = ecrPrefixResult.images(new ArrayList<>())
        .region("Region")
        .version("1.0.2")
        .build();
    Builder ecrPrefixResult2 = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult2 = ecrPrefixResult2.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link UploadDockerImagesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean UploadDockerImagesRequest.equals(Object)", "int UploadDockerImagesRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult = ecrPrefixResult.images(new ArrayList<>())
        .region("us-east-2")
        .version("Version")
        .build();
    Builder ecrPrefixResult2 = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult2 = ecrPrefixResult2.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link UploadDockerImagesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean UploadDockerImagesRequest.equals(Object)", "int UploadDockerImagesRequest.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult = ecrPrefixResult.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link UploadDockerImagesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImagesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean UploadDockerImagesRequest.equals(Object)", "int UploadDockerImagesRequest.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder ecrPrefixResult = UploadDockerImagesRequest.builder().account("3").ecrPrefix("Ecr Prefix");
    UploadDockerImagesRequest buildResult = ecrPrefixResult.images(new ArrayList<>())
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to UploadDockerImagesRequest");
  }
}
