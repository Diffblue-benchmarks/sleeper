package sleeper.build.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.build.chunks.ProjectStructure;
import sleeper.build.maven.MavenModuleStructure.Builder;

class MavenModuleAndPathDiffblueTest {
  /**
   * Test {@link MavenModuleAndPath#child(MavenModuleStructure)}.
   * <p>
   * Method under test: {@link MavenModuleAndPath#child(MavenModuleStructure)}
   */
  @Test
  @DisplayName("Test child(MavenModuleStructure)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MavenModuleAndPath MavenModuleAndPath.child(MavenModuleStructure)"})
  void testChild() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    MavenModuleAndPath rootResult = MavenModuleAndPath.root(structure);
    Builder artifactIdResult2 = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult2 = artifactIdResult2.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure2 = moduleRefResult2.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    MavenModuleAndPath actualChildResult = rootResult.child(structure2);

    // Assert
    assertEquals("42", actualChildResult.getParentReference().getArtifactId());
    assertEquals("Module Ref", actualChildResult.getPath());
    assertEquals("Module Ref", actualChildResult.toString());
    assertEquals("Module Ref/pom.xml", actualChildResult.getPomPath());
    Stream<DependencyReference> dependenciesResult = actualChildResult.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
    assertSame(structure2, actualChildResult.getStructure());
  }

  /**
   * Test {@link MavenModuleAndPath#child(MavenModuleStructure)}.
   * <ul>
   *   <li>Given {@code Module Ref}.</li>
   *   <li>Then return Structure is {@link MavenModuleStructure}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleAndPath#child(MavenModuleStructure)}
   */
  @Test
  @DisplayName("Test child(MavenModuleStructure); given 'Module Ref'; then return Structure is MavenModuleStructure")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MavenModuleAndPath MavenModuleAndPath.child(MavenModuleStructure)"})
  void testChild_givenModuleRef_thenReturnStructureIsMavenModuleStructure() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    MavenModuleAndPath rootResult = MavenModuleAndPath.root(structure);
    MavenModuleStructure structure2 = mock(MavenModuleStructure.class);
    when(structure2.getModuleRef()).thenReturn("Module Ref");

    // Act
    MavenModuleAndPath actualChildResult = rootResult.child(structure2);

    // Assert
    verify(structure2).getModuleRef();
    assertEquals("42", actualChildResult.getParentReference().getArtifactId());
    assertEquals("Module Ref", actualChildResult.getPath());
    assertEquals("Module Ref", actualChildResult.toString());
    assertEquals("Module Ref/pom.xml", actualChildResult.getPomPath());
    Stream<DependencyReference> dependenciesResult = actualChildResult.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
    assertSame(structure2, actualChildResult.getStructure());
  }

  /**
   * Test {@link MavenModuleAndPath#root(MavenModuleStructure)}.
   * <p>
   * Method under test: {@link MavenModuleAndPath#root(MavenModuleStructure)}
   */
  @Test
  @DisplayName("Test root(MavenModuleStructure)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MavenModuleAndPath MavenModuleAndPath.root(MavenModuleStructure)"})
  void testRoot() {
    // Arrange
    MavenModuleStructure structure = mock(MavenModuleStructure.class);

    // Act
    MavenModuleAndPath actualRootResult = MavenModuleAndPath.root(structure);

    // Assert
    assertEquals("pom.xml", actualRootResult.getPomPath());
    assertNull(actualRootResult.getPath());
    assertNull(actualRootResult.toString());
    assertNull(actualRootResult.getParentReference());
    Stream<DependencyReference> dependenciesResult = actualRootResult.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
    assertSame(structure, actualRootResult.getStructure());
  }

  /**
   * Test {@link MavenModuleAndPath#thisAndDescendents()}.
   * <p>
   * Method under test: {@link MavenModuleAndPath#thisAndDescendents()}
   */
  @Test
  @DisplayName("Test thisAndDescendents()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream MavenModuleAndPath.thisAndDescendents()"})
  void testThisAndDescendents() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    MavenModuleAndPath rootResult = MavenModuleAndPath.root(structure);

    // Act
    Stream<MavenModuleAndPath> actualThisAndDescendentsResult = rootResult.thisAndDescendents();

    // Assert
    List<MavenModuleAndPath> collectResult = actualThisAndDescendentsResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    assertSame(rootResult, collectResult.get(0));
  }

  /**
   * Test {@link MavenModuleAndPath#descendents()}.
   * <p>
   * Method under test: {@link MavenModuleAndPath#descendents()}
   */
  @Test
  @DisplayName("Test descendents()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream MavenModuleAndPath.descendents()"})
  void testDescendents() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    Stream<MavenModuleAndPath> actualDescendentsResult = MavenModuleAndPath.root(structure).descendents();

    // Assert
    assertTrue(actualDescendentsResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link MavenModuleAndPath#getPomPath()}.
   * <p>
   * Method under test: {@link MavenModuleAndPath#getPomPath()}
   */
  @Test
  @DisplayName("Test getPomPath()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String MavenModuleAndPath.getPomPath()"})
  void testGetPomPath() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertEquals("pom.xml", MavenModuleAndPath.root(structure).getPomPath());
  }

  /**
   * Test {@link MavenModuleAndPath#artifactReference()}.
   * <p>
   * Method under test: {@link MavenModuleAndPath#artifactReference()}
   */
  @Test
  @DisplayName("Test artifactReference()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ArtifactReference MavenModuleAndPath.artifactReference()"})
  void testArtifactReference() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertEquals("42", MavenModuleAndPath.root(structure).artifactReference().getArtifactId());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MavenModuleAndPath#getParentReference()}
   *   <li>{@link MavenModuleAndPath#getPath()}
   *   <li>{@link MavenModuleAndPath#getStructure()}
   *   <li>{@link MavenModuleAndPath#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ArtifactReference MavenModuleAndPath.getParentReference()", "String MavenModuleAndPath.getPath()",
      "MavenModuleStructure MavenModuleAndPath.getStructure()", "String MavenModuleAndPath.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    MavenModuleAndPath rootResult = MavenModuleAndPath.root(structure);

    // Act
    ArtifactReference actualParentReference = rootResult.getParentReference();
    String actualPath = rootResult.getPath();
    MavenModuleStructure actualStructure = rootResult.getStructure();
    String actualToStringResult = rootResult.toString();

    // Assert
    assertEquals("Module Ref", actualStructure.getModuleRef());
    assertNull(actualPath);
    assertNull(actualToStringResult);
    assertNull(actualParentReference);
    assertFalse(actualStructure.isPomPackage());
    Stream<DependencyReference> dependenciesResult = actualStructure.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link MavenModuleAndPath#pathInRepository(ProjectStructure)}.
   * <ul>
   *   <li>Then return Property is {@code java.io.tmpdir} is array of {@link String} with {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleAndPath#pathInRepository(ProjectStructure)}
   */
  @Test
  @DisplayName("Test pathInRepository(ProjectStructure); then return Property is 'java.io.tmpdir' is array of String with 'test.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path MavenModuleAndPath.pathInRepository(ProjectStructure)"})
  void testPathInRepository_thenReturnPropertyIsJavaIoTmpdirIsArrayOfStringWithTestTxt() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    MavenModuleAndPath rootResult = MavenModuleAndPath.root(structure);
    ProjectStructure project = mock(ProjectStructure.class);
    Path getResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    when(project.relativizeMavenPathInRepository(Mockito.<String>any())).thenReturn(getResult);

    // Act
    Path actualPathInRepositoryResult = rootResult.pathInRepository(project);

    // Assert
    verify(project).relativizeMavenPathInRepository(isNull());
    assertSame(getResult, actualPathInRepositoryResult);
  }

  /**
   * Test {@link MavenModuleAndPath#pomPathInRepository(ProjectStructure)}.
   * <p>
   * Method under test: {@link MavenModuleAndPath#pomPathInRepository(ProjectStructure)}
   */
  @Test
  @DisplayName("Test pomPathInRepository(ProjectStructure)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path MavenModuleAndPath.pomPathInRepository(ProjectStructure)"})
  void testPomPathInRepository() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    MavenModuleAndPath rootResult = MavenModuleAndPath.root(structure);
    ProjectStructure project = mock(ProjectStructure.class);
    Path getResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    when(project.relativizeMavenPathInRepository(Mockito.<String>any())).thenReturn(getResult);

    // Act
    Path actualPomPathInRepositoryResult = rootResult.pomPathInRepository(project);

    // Assert
    verify(project).relativizeMavenPathInRepository(eq("pom.xml"));
    assertSame(getResult, actualPomPathInRepositoryResult);
  }

  /**
   * Test {@link MavenModuleAndPath#pomPathInRepository(ProjectStructure)}.
   * <ul>
   *   <li>Then return toFile Name is {@code pom.xml}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleAndPath#pomPathInRepository(ProjectStructure)}
   */
  @Test
  @DisplayName("Test pomPathInRepository(ProjectStructure); then return toFile Name is 'pom.xml'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path MavenModuleAndPath.pomPathInRepository(ProjectStructure)"})
  void testPomPathInRepository_thenReturnToFileNameIsPomXml() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    MavenModuleAndPath rootResult = MavenModuleAndPath.root(structure);
    ProjectStructure project = ProjectStructure.builder()
        .chunksYamlPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .mavenProjectPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .workflowsPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    File toFileResult = rootResult.pomPathInRepository(project).toFile();
    assertEquals("pom.xml", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link MavenModuleAndPath#dependencies()}.
   * <p>
   * Method under test: {@link MavenModuleAndPath#dependencies()}
   */
  @Test
  @DisplayName("Test dependencies()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream MavenModuleAndPath.dependencies()"})
  void testDependencies() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    Stream<DependencyReference> actualDependenciesResult = MavenModuleAndPath.root(structure).dependencies();

    // Assert
    assertTrue(actualDependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link MavenModuleAndPath#exportedDependencies()}.
   * <p>
   * Method under test: {@link MavenModuleAndPath#exportedDependencies()}
   */
  @Test
  @DisplayName("Test exportedDependencies()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream MavenModuleAndPath.exportedDependencies()"})
  void testExportedDependencies() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    Stream<DependencyReference> actualExportedDependenciesResult = MavenModuleAndPath.root(structure)
        .exportedDependencies();

    // Assert
    assertTrue(actualExportedDependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link MavenModuleAndPath#internalExportedDependencies()}.
   * <p>
   * Method under test: {@link MavenModuleAndPath#internalExportedDependencies()}
   */
  @Test
  @DisplayName("Test internalExportedDependencies()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream MavenModuleAndPath.internalExportedDependencies()"})
  void testInternalExportedDependencies() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    Stream<DependencyReference> actualInternalExportedDependenciesResult = MavenModuleAndPath.root(structure)
        .internalExportedDependencies();

    // Assert
    assertTrue(actualInternalExportedDependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link MavenModuleAndPath#transitiveInternalDependencies(InternalModuleIndex)}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenModuleAndPath#transitiveInternalDependencies(InternalModuleIndex)}
   */
  @Test
  @DisplayName("Test transitiveInternalDependencies(InternalModuleIndex); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream MavenModuleAndPath.transitiveInternalDependencies(InternalModuleIndex)"})
  void testTransitiveInternalDependencies_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    MavenModuleAndPath rootResult = MavenModuleAndPath.root(structure);

    // Act
    Stream<MavenModuleAndPath> actualTransitiveInternalDependenciesResult = rootResult
        .transitiveInternalDependencies(new InternalModuleIndex(new ArrayList<>()));

    // Assert
    assertTrue(actualTransitiveInternalDependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
