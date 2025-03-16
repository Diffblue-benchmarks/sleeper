package sleeper.build.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.maven.MavenModuleStructure.Builder;

class MavenModuleStructureDiffblueTest {
  /**
   * Test {@link MavenModuleStructure#artifactReference()}.
   * <p>
   * Method under test: {@link MavenModuleStructure#artifactReference()}
   */
  @Test
  @DisplayName("Test artifactReference()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.build.maven.ArtifactReference MavenModuleStructure.artifactReference()"})
  void testArtifactReference() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertEquals("42", buildResult.artifactReference().getArtifactId());
  }

  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#artifactId(String)}
   *   <li>{@link Builder#dependencies(List)}
   *   <li>{@link Builder#groupId(String)}
   *   <li>{@link Builder#moduleRef(String)}
   *   <li>{@link Builder#modules(List)}
   *   <li>{@link Builder#packaging(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.artifactId(String)", "MavenModuleStructure Builder.build()",
      "Builder Builder.dependencies(List)", "Builder Builder.groupId(String)",
      "Builder Builder.hasSrcMainJavaFolder(boolean)", "Builder Builder.moduleRef(String)",
      "Builder Builder.modules(List)", "Builder Builder.packaging(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");

    // Act
    MavenModuleStructure actualBuildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Assert
    assertEquals("Module Ref", actualBuildResult.getModuleRef());
    assertFalse(actualBuildResult.isPomPackage());
    Stream<DependencyReference> dependenciesResult = actualBuildResult.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test Builder {@link Builder#dependenciesArray(DependencyReference[])}.
   * <p>
   * Method under test: {@link Builder#dependenciesArray(DependencyReference[])}
   */
  @Test
  @DisplayName("Test Builder dependenciesArray(DependencyReference[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.dependenciesArray(DependencyReference[])"})
  void testBuilderDependenciesArray() {
    // Arrange
    Builder builderResult = MavenModuleStructure.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.dependenciesArray(mock(DependencyReference.class)));
  }

  /**
   * Test Builder {@link Builder#modulesArray(MavenModuleStructure[])}.
   * <p>
   * Method under test: {@link Builder#modulesArray(MavenModuleStructure[])}
   */
  @Test
  @DisplayName("Test Builder modulesArray(MavenModuleStructure[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.modulesArray(MavenModuleStructure[])"})
  void testBuilderModulesArray() {
    // Arrange
    Builder builderResult = MavenModuleStructure.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.modulesArray(mock(MavenModuleStructure.class)));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MavenModuleStructure#toString()}
   *   <li>{@link MavenModuleStructure#getModuleRef()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String MavenModuleStructure.getModuleRef()", "String MavenModuleStructure.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    String actualToStringResult = buildResult.toString();

    // Assert
    assertEquals("MavenModuleStructure{artifactId='42', groupId='42', packaging='Packaging', moduleRef='Module Ref',"
        + " hasSrcMainJavaFolder=false, modules=[], dependencies=[]}", actualToStringResult);
    assertEquals("Module Ref", buildResult.getModuleRef());
  }

  /**
   * Test {@link MavenModuleStructure#childModules()}.
   * <p>
   * Method under test: {@link MavenModuleStructure#childModules()}
   */
  @Test
  @DisplayName("Test childModules()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream MavenModuleStructure.childModules()"})
  void testChildModules() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    Stream<MavenModuleStructure> actualChildModulesResult = buildResult.childModules();

    // Assert
    assertTrue(actualChildModulesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link MavenModuleStructure#dependencies()}.
   * <p>
   * Method under test: {@link MavenModuleStructure#dependencies()}
   */
  @Test
  @DisplayName("Test dependencies()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream MavenModuleStructure.dependencies()"})
  void testDependencies() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    Stream<DependencyReference> actualDependenciesResult = buildResult.dependencies();

    // Assert
    assertTrue(actualDependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link MavenModuleStructure#equals(Object)}, and {@link MavenModuleStructure#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MavenModuleStructure#equals(Object)}
   *   <li>{@link MavenModuleStructure#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MavenModuleStructure.equals(Object)", "int MavenModuleStructure.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    Builder artifactIdResult2 = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult2 = artifactIdResult2.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult2 = moduleRefResult2.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link MavenModuleStructure#equals(Object)}, and {@link MavenModuleStructure#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MavenModuleStructure#equals(Object)}
   *   <li>{@link MavenModuleStructure#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MavenModuleStructure.equals(Object)", "int MavenModuleStructure.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link MavenModuleStructure#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleStructure#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MavenModuleStructure.equals(Object)", "int MavenModuleStructure.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("Artifact Id");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    Builder artifactIdResult2 = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult2 = artifactIdResult2.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult2 = moduleRefResult2.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link MavenModuleStructure#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleStructure#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MavenModuleStructure.equals(Object)", "int MavenModuleStructure.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<DependencyReference> dependencies = new ArrayList<>();
    DependencyReference buildResult = DependencyReference.builder()
        .artifactId("42")
        .exported(true)
        .groupId("42")
        .type("Type")
        .build();
    dependencies.add(buildResult);
    Builder moduleRefResult = MavenModuleStructure.builder()
        .artifactId("42")
        .dependencies(dependencies)
        .groupId("42")
        .moduleRef("Module Ref");
    MavenModuleStructure buildResult2 = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult2 = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult3 = moduleRefResult2.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertNotEquals(buildResult2, buildResult3);
  }

  /**
   * Test {@link MavenModuleStructure#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleStructure#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MavenModuleStructure.equals(Object)", "int MavenModuleStructure.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>())
        .groupId("Group Id")
        .moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    Builder artifactIdResult2 = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult2 = artifactIdResult2.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult2 = moduleRefResult2.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link MavenModuleStructure#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleStructure#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MavenModuleStructure.equals(Object)", "int MavenModuleStructure.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("42");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    Builder artifactIdResult2 = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult2 = artifactIdResult2.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult2 = moduleRefResult2.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link MavenModuleStructure#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleStructure#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MavenModuleStructure.equals(Object)", "int MavenModuleStructure.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    ArrayList<MavenModuleStructure> modules = new ArrayList<>();
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    modules.add(buildResult);
    Builder artifactIdResult2 = MavenModuleStructure.builder().artifactId("42");
    MavenModuleStructure buildResult2 = artifactIdResult2.dependencies(new ArrayList<>())
        .groupId("42")
        .moduleRef("Module Ref")
        .modules(modules)
        .packaging("Packaging")
        .build();
    Builder artifactIdResult3 = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult2 = artifactIdResult3.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult3 = moduleRefResult2.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertNotEquals(buildResult2, buildResult3);
  }

  /**
   * Test {@link MavenModuleStructure#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleStructure#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MavenModuleStructure.equals(Object)", "int MavenModuleStructure.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("42").build();
    Builder artifactIdResult2 = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult2 = artifactIdResult2.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult2 = moduleRefResult2.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link MavenModuleStructure#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleStructure#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MavenModuleStructure.equals(Object)", "int MavenModuleStructure.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link MavenModuleStructure#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleStructure#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MavenModuleStructure.equals(Object)", "int MavenModuleStructure.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to MavenModuleStructure");
  }
}
