package sleeper.clients.util.cdk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.util.cdk.CdkDeploy.Builder;

class CdkDeployDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#deployPaused(boolean)}
   *   <li>{@link Builder#ensureNewInstance(boolean)}
   *   <li>{@link Builder#skipVersionCheck(boolean)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CdkDeploy Builder.build()", "Builder Builder.deployPaused(boolean)",
      "Builder Builder.ensureNewInstance(boolean)", "Builder Builder.skipVersionCheck(boolean)"})
  void testBuilderBuild() {
    // Arrange and Act
    CdkDeploy actualBuildResult = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();

    // Assert
    Stream<String> command = actualBuildResult.getCommand();
    List<String> collectResult = command.limit(5).collect(Collectors.toList());
    assertEquals(3, collectResult.size());
    assertEquals("--require-approval", collectResult.get(1));
    Stream<String> arguments = actualBuildResult.getArguments();
    List<String> collectResult2 = arguments.limit(5).collect(Collectors.toList());
    assertEquals(5, collectResult2.size());
    assertEquals("-c", collectResult2.get(0));
    assertEquals("-c", collectResult2.get(2));
    assertEquals("-c", collectResult2.get(4));
    assertEquals("deploy", collectResult.get(0));
    assertEquals("never", collectResult.get(2));
    assertEquals("newinstance=true", collectResult2.get(1));
    assertEquals("skipVersionCheck=true", collectResult2.get(3));
  }

  /**
   * Test {@link CdkDeploy#getCommand()}.
   * <p>
   * Method under test: {@link CdkDeploy#getCommand()}
   */
  @Test
  @DisplayName("Test getCommand()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream CdkDeploy.getCommand()"})
  void testGetCommand() {
    // Arrange
    CdkDeploy buildResult = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();

    // Act
    Stream<String> actualCommand = buildResult.getCommand();

    // Assert
    List<String> collectResult = actualCommand.limit(5).collect(Collectors.toList());
    assertEquals(3, collectResult.size());
    assertEquals("--require-approval", collectResult.get(1));
    assertEquals("deploy", collectResult.get(0));
    assertEquals("never", collectResult.get(2));
  }

  /**
   * Test {@link CdkDeploy#equals(Object)}, and {@link CdkDeploy#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CdkDeploy#equals(Object)}
   *   <li>{@link CdkDeploy#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CdkDeploy.equals(Object)", "int CdkDeploy.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    CdkDeploy buildResult = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();
    CdkDeploy buildResult2 = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link CdkDeploy#equals(Object)}, and {@link CdkDeploy#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CdkDeploy#equals(Object)}
   *   <li>{@link CdkDeploy#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CdkDeploy.equals(Object)", "int CdkDeploy.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    CdkDeploy buildResult = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link CdkDeploy#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CdkDeploy#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CdkDeploy.equals(Object)", "int CdkDeploy.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    CdkDeploy buildResult = CdkDeploy.builder()
        .deployPaused(false)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();
    CdkDeploy buildResult2 = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CdkDeploy#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CdkDeploy#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CdkDeploy.equals(Object)", "int CdkDeploy.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    CdkDeploy buildResult = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(false)
        .skipVersionCheck(true)
        .build();
    CdkDeploy buildResult2 = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CdkDeploy#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CdkDeploy#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CdkDeploy.equals(Object)", "int CdkDeploy.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    CdkDeploy buildResult = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(false)
        .build();
    CdkDeploy buildResult2 = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CdkDeploy#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CdkDeploy#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CdkDeploy.equals(Object)", "int CdkDeploy.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    CdkDeploy buildResult = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CdkDeploy#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CdkDeploy#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CdkDeploy.equals(Object)", "int CdkDeploy.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    CdkDeploy buildResult = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CdkDeploy");
  }

  /**
   * Test {@link CdkDeploy#toString()}.
   * <p>
   * Method under test: {@link CdkDeploy#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CdkDeploy.toString()"})
  void testToString() {
    // Arrange
    CdkDeploy buildResult = CdkDeploy.builder()
        .deployPaused(true)
        .ensureNewInstance(true)
        .skipVersionCheck(true)
        .build();

    // Act and Assert
    assertEquals("CdkDeploy{ensureNewInstance=true, skipVersionCheck=true, deployPaused=true}", buildResult.toString());
  }
}
