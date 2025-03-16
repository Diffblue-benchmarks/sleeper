package sleeper.build.chunks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.chunks.ProjectStructure.Builder;

class ProjectStructureDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#chunksYamlPath(Path)}
   *   <li>{@link Builder#mavenProjectPath(Path)}
   *   <li>{@link Builder#workflowsPath(Path)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ProjectStructure Builder.build()", "Builder Builder.chunksYamlPath(Path)",
      "Builder Builder.mavenProjectPath(Path)", "Builder Builder.workflowsPath(Path)"})
  void testBuilderBuild() {
    // Arrange, Act and Assert
    File toFileResult = ProjectStructure.builder()
        .chunksYamlPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .mavenProjectPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .workflowsPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build()
        .getChunksYamlRelative()
        .toFile();
    assertEquals("test.txt", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link ProjectStructure#getChunksYamlRelative()}.
   * <p>
   * Method under test: {@link ProjectStructure#getChunksYamlRelative()}
   */
  @Test
  @DisplayName("Test getChunksYamlRelative()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path ProjectStructure.getChunksYamlRelative()"})
  void testGetChunksYamlRelative() {
    // Arrange
    ProjectStructure buildResult = ProjectStructure.builder()
        .chunksYamlPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .mavenProjectPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .workflowsPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    File toFileResult = buildResult.getChunksYamlRelative().toFile();
    assertEquals("test.txt", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link ProjectStructure#relativizeMavenPathInRepository(String)}.
   * <p>
   * Method under test: {@link ProjectStructure#relativizeMavenPathInRepository(String)}
   */
  @Test
  @DisplayName("Test relativizeMavenPathInRepository(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path ProjectStructure.relativizeMavenPathInRepository(String)"})
  void testRelativizeMavenPathInRepository() {
    // Arrange
    ProjectStructure buildResult = ProjectStructure.builder()
        .chunksYamlPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .mavenProjectPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .workflowsPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    File toFileResult = buildResult.relativizeMavenPathInRepository("Path").toFile();
    assertEquals("Path", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link ProjectStructure#isUnderMavenPathRepositoryRelative(String)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProjectStructure#isUnderMavenPathRepositoryRelative(String)}
   */
  @Test
  @DisplayName("Test isUnderMavenPathRepositoryRelative(String); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ProjectStructure.isUnderMavenPathRepositoryRelative(String)"})
  void testIsUnderMavenPathRepositoryRelative_thenReturnFalse() {
    // Arrange
    ProjectStructure buildResult = ProjectStructure.builder()
        .chunksYamlPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .mavenProjectPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .workflowsPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertFalse(buildResult.isUnderMavenPathRepositoryRelative("Path"));
  }

  /**
   * Test {@link ProjectStructure#isUnderMavenPathRepositoryRelative(String)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProjectStructure#isUnderMavenPathRepositoryRelative(String)}
   */
  @Test
  @DisplayName("Test isUnderMavenPathRepositoryRelative(String); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ProjectStructure.isUnderMavenPathRepositoryRelative(String)"})
  void testIsUnderMavenPathRepositoryRelative_thenReturnTrue() {
    // Arrange
    ProjectStructure buildResult = ProjectStructure.builder()
        .chunksYamlPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .mavenProjectPath(Paths.get(System.getProperty("java.io.tmpdir"), "42"))
        .workflowsPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertTrue(buildResult.isUnderMavenPathRepositoryRelative("42"));
  }
}
