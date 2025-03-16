package sleeper.build.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.maven.DependencyReference.Builder;
import sleeper.build.maven.MavenPom.Dependency;

class DependencyReferenceDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#artifactId(String)}
   *   <li>{@link Builder#exported(boolean)}
   *   <li>{@link Builder#groupId(String)}
   *   <li>{@link Builder#type(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.artifactId(String)", "DependencyReference Builder.build()",
      "Builder Builder.exported(boolean)", "Builder Builder.groupId(String)", "Builder Builder.type(String)"})
  void testBuilderBuild() {
    // Arrange and Act
    DependencyReference actualBuildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Assert
    assertFalse(actualBuildResult.isSleeper());
    assertTrue(actualBuildResult.isExported());
  }

  /**
   * Test {@link DependencyReference#from(Dependency)}.
   * <p>
   * Method under test: {@link DependencyReference#from(Dependency)}
   */
  @Test
  @DisplayName("Test from(Dependency)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DependencyReference DependencyReference.from(Dependency)"})
  void testFrom() {
    // Arrange and Act
    DependencyReference actualFromResult = DependencyReference
        .from(new Dependency("42", "42", "1.0.2", "Type", "compile"));

    // Assert
    assertFalse(actualFromResult.isSleeper());
    assertTrue(actualFromResult.isExported());
  }

  /**
   * Test {@link DependencyReference#from(Dependency)}.
   * <p>
   * Method under test: {@link DependencyReference#from(Dependency)}
   */
  @Test
  @DisplayName("Test from(Dependency)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DependencyReference DependencyReference.from(Dependency)"})
  void testFrom2() {
    // Arrange and Act
    DependencyReference actualFromResult = DependencyReference
        .from(new Dependency("42", "42", "1.0.2", "Type", "runtime"));

    // Assert
    assertFalse(actualFromResult.isSleeper());
    assertTrue(actualFromResult.isExported());
  }

  /**
   * Test {@link DependencyReference#from(Dependency)}.
   * <p>
   * Method under test: {@link DependencyReference#from(Dependency)}
   */
  @Test
  @DisplayName("Test from(Dependency)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DependencyReference DependencyReference.from(Dependency)"})
  void testFrom3() {
    // Arrange and Act
    DependencyReference actualFromResult = DependencyReference.from(new Dependency("42", "42", "1.0.2", "Type", null));

    // Assert
    assertFalse(actualFromResult.isSleeper());
    assertTrue(actualFromResult.isExported());
  }

  /**
   * Test {@link DependencyReference#from(Dependency)}.
   * <ul>
   *   <li>Then return not Exported.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyReference#from(Dependency)}
   */
  @Test
  @DisplayName("Test from(Dependency); then return not Exported")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DependencyReference DependencyReference.from(Dependency)"})
  void testFrom_thenReturnNotExported() {
    // Arrange and Act
    DependencyReference actualFromResult = DependencyReference
        .from(new Dependency("42", "42", "1.0.2", "Type", "Scope"));

    // Assert
    assertFalse(actualFromResult.isExported());
    assertFalse(actualFromResult.isSleeper());
  }

  /**
   * Test {@link DependencyReference#artifactReference()}.
   * <p>
   * Method under test: {@link DependencyReference#artifactReference()}
   */
  @Test
  @DisplayName("Test artifactReference()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.build.maven.ArtifactReference DependencyReference.artifactReference()"})
  void testArtifactReference() {
    // Arrange
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Act and Assert
    assertEquals("42", buildResult.artifactReference().getArtifactId());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DependencyReference#toString()}
   *   <li>{@link DependencyReference#isExported()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyReference.isExported()", "String DependencyReference.toString()"})
  void testGettersAndSetters() {
    // Arrange
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();

    // Assert
    assertEquals("DependencyReference{artifactId='42', groupId='42', type='Type', exported=true}",
        actualToStringResult);
    assertTrue(buildResult.isExported());
  }

  /**
   * Test {@link DependencyReference#equals(Object)}, and {@link DependencyReference#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DependencyReference#equals(Object)}
   *   <li>{@link DependencyReference#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyReference.equals(Object)", "int DependencyReference.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();
    DependencyReference buildResult2 = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link DependencyReference#equals(Object)}, and {@link DependencyReference#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DependencyReference#equals(Object)}
   *   <li>{@link DependencyReference#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyReference.equals(Object)", "int DependencyReference.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link DependencyReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyReference#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyReference.equals(Object)", "int DependencyReference.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("Artifact Id")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();
    DependencyReference buildResult2 = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link DependencyReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyReference#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyReference.equals(Object)", "int DependencyReference.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(false)
        .groupId("42")
        .type("Type")
        .build();
    DependencyReference buildResult2 = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link DependencyReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyReference#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyReference.equals(Object)", "int DependencyReference.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("Group Id")
        .type("Type")
        .build();
    DependencyReference buildResult2 = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link DependencyReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyReference#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyReference.equals(Object)", "int DependencyReference.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("42")
        .build();
    DependencyReference buildResult2 = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link DependencyReference#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyReference#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyReference.equals(Object)", "int DependencyReference.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link DependencyReference#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyReference#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyReference.equals(Object)", "int DependencyReference.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to DependencyReference");
  }
}
