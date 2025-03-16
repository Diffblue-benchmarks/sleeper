package sleeper.clients.deploy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.deploy.StackDockerImage.Builder;
import sleeper.core.deploy.LambdaJar;

class StackDockerImageDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#createEmrServerlessPolicy(boolean)}
   *   <li>{@link Builder#directoryName(String)}
   *   <li>{@link Builder#imageName(String)}
   *   <li>{@link Builder#lambdaJar(LambdaJar)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackDockerImage Builder.build()", "Builder Builder.createEmrServerlessPolicy(boolean)",
      "Builder Builder.directoryName(String)", "Builder Builder.imageName(String)", "Builder Builder.isBuildx(boolean)",
      "Builder Builder.lambdaJar(LambdaJar)"})
  void testBuilderBuild() {
    // Arrange
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    LambdaJar lambdaJar = LambdaJar.withFormatAndImage("Format", "Image Name");

    // Act
    StackDockerImage actualBuildResult = imageNameResult.lambdaJar(lambdaJar).build();

    // Assert
    assertEquals("/directory", actualBuildResult.getDirectoryName());
    assertEquals("Image Name", actualBuildResult.getImageName());
    assertFalse(actualBuildResult.isBuildx());
    Optional<LambdaJar> lambdaJar2 = actualBuildResult.getLambdaJar();
    assertTrue(lambdaJar2.isPresent());
    assertTrue(actualBuildResult.isCreateEmrServerlessPolicy());
    assertSame(lambdaJar, lambdaJar2.get());
  }

  /**
   * Test {@link StackDockerImage#dockerBuildImage(String)}.
   * <p>
   * Method under test: {@link StackDockerImage#dockerBuildImage(String)}
   */
  @Test
  @DisplayName("Test dockerBuildImage(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackDockerImage StackDockerImage.dockerBuildImage(String)"})
  void testDockerBuildImage() {
    // Arrange and Act
    StackDockerImage actualDockerBuildImageResult = StackDockerImage.dockerBuildImage("Image Name");

    // Assert
    assertEquals("Image Name", actualDockerBuildImageResult.getDirectoryName());
    assertEquals("Image Name", actualDockerBuildImageResult.getImageName());
    assertFalse(actualDockerBuildImageResult.getLambdaJar().isPresent());
    assertFalse(actualDockerBuildImageResult.isBuildx());
    assertFalse(actualDockerBuildImageResult.isCreateEmrServerlessPolicy());
  }

  /**
   * Test {@link StackDockerImage#dockerBuildxImage(String)}.
   * <p>
   * Method under test: {@link StackDockerImage#dockerBuildxImage(String)}
   */
  @Test
  @DisplayName("Test dockerBuildxImage(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackDockerImage StackDockerImage.dockerBuildxImage(String)"})
  void testDockerBuildxImage() {
    // Arrange and Act
    StackDockerImage actualDockerBuildxImageResult = StackDockerImage.dockerBuildxImage("Image Name");

    // Assert
    assertEquals("Image Name", actualDockerBuildxImageResult.getDirectoryName());
    assertEquals("Image Name", actualDockerBuildxImageResult.getImageName());
    assertFalse(actualDockerBuildxImageResult.getLambdaJar().isPresent());
    assertFalse(actualDockerBuildxImageResult.isCreateEmrServerlessPolicy());
    assertTrue(actualDockerBuildxImageResult.isBuildx());
  }

  /**
   * Test {@link StackDockerImage#emrServerlessImage(String)}.
   * <p>
   * Method under test: {@link StackDockerImage#emrServerlessImage(String)}
   */
  @Test
  @DisplayName("Test emrServerlessImage(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackDockerImage StackDockerImage.emrServerlessImage(String)"})
  void testEmrServerlessImage() {
    // Arrange and Act
    StackDockerImage actualEmrServerlessImageResult = StackDockerImage.emrServerlessImage("Image Name");

    // Assert
    assertEquals("Image Name", actualEmrServerlessImageResult.getDirectoryName());
    assertEquals("Image Name", actualEmrServerlessImageResult.getImageName());
    assertFalse(actualEmrServerlessImageResult.getLambdaJar().isPresent());
    assertFalse(actualEmrServerlessImageResult.isBuildx());
    assertTrue(actualEmrServerlessImageResult.isCreateEmrServerlessPolicy());
  }

  /**
   * Test {@link StackDockerImage#lambdaImage(LambdaJar)}.
   * <ul>
   *   <li>When withFormatAndImage {@code Format} and {@code Image Name}.</li>
   *   <li>Then return {@code Image Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackDockerImage#lambdaImage(LambdaJar)}
   */
  @Test
  @DisplayName("Test lambdaImage(LambdaJar); when withFormatAndImage 'Format' and 'Image Name'; then return 'Image Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackDockerImage StackDockerImage.lambdaImage(LambdaJar)"})
  void testLambdaImage_whenWithFormatAndImageFormatAndImageName_thenReturnImageName() {
    // Arrange
    LambdaJar lambdaJar = LambdaJar.withFormatAndImage("Format", "Image Name");

    // Act
    StackDockerImage actualLambdaImageResult = StackDockerImage.lambdaImage(lambdaJar);

    // Assert
    assertEquals("Image Name", actualLambdaImageResult.getImageName());
    assertEquals("lambda", actualLambdaImageResult.getDirectoryName());
    assertFalse(actualLambdaImageResult.isBuildx());
    assertFalse(actualLambdaImageResult.isCreateEmrServerlessPolicy());
    Optional<LambdaJar> lambdaJar2 = actualLambdaImageResult.getLambdaJar();
    assertTrue(lambdaJar2.isPresent());
    assertSame(lambdaJar, lambdaJar2.get());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StackDockerImage#toString()}
   *   <li>{@link StackDockerImage#getDirectoryName()}
   *   <li>{@link StackDockerImage#getImageName()}
   *   <li>{@link StackDockerImage#isBuildx()}
   *   <li>{@link StackDockerImage#isCreateEmrServerlessPolicy()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StackDockerImage.getDirectoryName()", "String StackDockerImage.getImageName()",
      "boolean StackDockerImage.isBuildx()", "boolean StackDockerImage.isCreateEmrServerlessPolicy()",
      "String StackDockerImage.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualDirectoryName = buildResult.getDirectoryName();
    String actualImageName = buildResult.getImageName();
    boolean actualIsBuildxResult = buildResult.isBuildx();

    // Assert
    assertEquals("/directory", actualDirectoryName);
    assertEquals("Image Name", actualImageName);
    assertEquals(
        "StackDockerImage{imageName=Image Name, directoryName=/directory, isBuildx=false, createEmrServerlessPolicy"
            + "=true, lambdaJar=LambdaJar{filename=Format, imageName=Image Name}}",
        actualToStringResult);
    assertFalse(actualIsBuildxResult);
    assertTrue(buildResult.isCreateEmrServerlessPolicy());
  }

  /**
   * Test {@link StackDockerImage#getLambdaJar()}.
   * <p>
   * Method under test: {@link StackDockerImage#getLambdaJar()}
   */
  @Test
  @DisplayName("Test getLambdaJar()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional StackDockerImage.getLambdaJar()"})
  void testGetLambdaJar() {
    // Arrange
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    LambdaJar lambdaJar = LambdaJar.withFormatAndImage("Format", "Image Name");
    StackDockerImage buildResult = imageNameResult.lambdaJar(lambdaJar).build();

    // Act
    Optional<LambdaJar> actualLambdaJar = buildResult.getLambdaJar();

    // Assert
    assertTrue(actualLambdaJar.isPresent());
    assertSame(lambdaJar, actualLambdaJar.get());
  }

  /**
   * Test {@link StackDockerImage#equals(Object)}, and {@link StackDockerImage#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StackDockerImage#equals(Object)}
   *   <li>{@link StackDockerImage#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackDockerImage.equals(Object)", "int StackDockerImage.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link StackDockerImage#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackDockerImage#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackDockerImage.equals(Object)", "int StackDockerImage.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();
    Builder imageNameResult2 = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult2 = imageNameResult2.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link StackDockerImage#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackDockerImage#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackDockerImage.equals(Object)", "int StackDockerImage.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(false)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();
    Builder imageNameResult2 = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult2 = imageNameResult2.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link StackDockerImage#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackDockerImage#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackDockerImage.equals(Object)", "int StackDockerImage.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("Directory Name")
        .imageName("Image Name");
    StackDockerImage buildResult = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();
    Builder imageNameResult2 = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult2 = imageNameResult2.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link StackDockerImage#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackDockerImage#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackDockerImage.equals(Object)", "int StackDockerImage.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("42");
    StackDockerImage buildResult = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();
    Builder imageNameResult2 = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult2 = imageNameResult2.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link StackDockerImage#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackDockerImage#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackDockerImage.equals(Object)", "int StackDockerImage.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link StackDockerImage#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackDockerImage#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackDockerImage.equals(Object)", "int StackDockerImage.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to StackDockerImage");
  }
}
