package sleeper.build.chunks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.maven.MavenModuleAndPath;
import sleeper.build.maven.MavenModuleStructure;
import sleeper.build.maven.MavenModuleStructure.Builder;

class ProjectChunksDiffblueTest {
  /**
   * Test {@link ProjectChunks#ProjectChunks(List)}.
   * <p>
   * Method under test: {@link ProjectChunks#ProjectChunks(List)}
   */
  @Test
  @DisplayName("Test new ProjectChunks(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProjectChunks.<init>(List)"})
  void testNewProjectChunks() {
    // Arrange, Act and Assert
    Stream<ProjectChunk> streamResult = (new ProjectChunks(new ArrayList<>())).stream();
    assertTrue(streamResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link ProjectChunks#getById(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProjectChunks#getById(String)}
   */
  @Test
  @DisplayName("Test getById(String); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ProjectChunk ProjectChunks.getById(String)"})
  void testGetById_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new ProjectChunks(new ArrayList<>())).getById("42"));
  }

  /**
   * Test {@link ProjectChunks#validate(ProjectStructure, PrintStream)}.
   * <p>
   * Method under test: {@link ProjectChunks#validate(ProjectStructure, PrintStream)}
   */
  @Test
  @DisplayName("Test validate(ProjectStructure, PrintStream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProjectChunks.validate(ProjectStructure, PrintStream)"})
  void testValidate() throws IOException {
    // Arrange
    ProjectChunks projectChunks = new ProjectChunks(new ArrayList<>());
    ProjectStructure project = mock(ProjectStructure.class);
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("pom").build();
    when(project.loadMavenStructure()).thenReturn(buildResult);

    // Act
    projectChunks.validate(project, new PrintStream(new ByteArrayOutputStream(1)));

    // Assert
    verify(project).loadMavenStructure();
  }

  /**
   * Test {@link ProjectChunks#validate(ProjectStructure, PrintStream)}.
   * <p>
   * Method under test: {@link ProjectChunks#validate(ProjectStructure, PrintStream)}
   */
  @Test
  @DisplayName("Test validate(ProjectStructure, PrintStream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProjectChunks.validate(ProjectStructure, PrintStream)"})
  void testValidate2() throws IOException {
    // Arrange
    ProjectChunks projectChunks = new ProjectChunks(new ArrayList<>());

    ArrayList<MavenModuleStructure> modules = new ArrayList<>();
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    modules.add(buildResult);
    Builder artifactIdResult2 = MavenModuleStructure.builder().artifactId("groupId must not be null");
    MavenModuleStructure buildResult2 = artifactIdResult2.dependencies(new ArrayList<>())
        .groupId("42")
        .moduleRef("Module Ref")
        .modules(modules)
        .packaging("Packaging")
        .build();
    ProjectStructure project = mock(ProjectStructure.class);
    when(project.loadMavenStructure()).thenReturn(buildResult2);

    // Act
    projectChunks.validate(project, new PrintStream(new ByteArrayOutputStream(1)));

    // Assert
    verify(project).loadMavenStructure();
  }

  /**
   * Test {@link ProjectChunks#validate(ProjectStructure, PrintStream)}.
   * <ul>
   *   <li>Then calls {@link ProjectStructure#loadMavenStructure()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProjectChunks#validate(ProjectStructure, PrintStream)}
   */
  @Test
  @DisplayName("Test validate(ProjectStructure, PrintStream); then calls loadMavenStructure()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProjectChunks.validate(ProjectStructure, PrintStream)"})
  void testValidate_thenCallsLoadMavenStructure() throws IOException {
    // Arrange
    ProjectChunks projectChunks = new ProjectChunks(new ArrayList<>());
    ProjectStructure project = mock(ProjectStructure.class);
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    when(project.loadMavenStructure()).thenReturn(buildResult);

    // Act
    projectChunks.validate(project, new PrintStream(new ByteArrayOutputStream(1)));

    // Assert
    verify(project).loadMavenStructure();
  }

  /**
   * Test {@link ProjectChunks#validate(ProjectStructure, PrintStream)}.
   * <ul>
   *   <li>Then throw {@link ProjectChunksValidationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProjectChunks#validate(ProjectStructure, PrintStream)}
   */
  @Test
  @DisplayName("Test validate(ProjectStructure, PrintStream); then throw ProjectChunksValidationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProjectChunks.validate(ProjectStructure, PrintStream)"})
  void testValidate_thenThrowProjectChunksValidationException() throws IOException {
    // Arrange
    ProjectChunks projectChunks = new ProjectChunks(new ArrayList<>());
    ProjectStructure project = mock(ProjectStructure.class);
    when(project.loadMavenStructure()).thenThrow(new ProjectChunksValidationException("An error occurred"));

    // Act and Assert
    assertThrows(ProjectChunksValidationException.class,
        () -> projectChunks.validate(project, new PrintStream(new ByteArrayOutputStream(1))));
    verify(project).loadMavenStructure();
  }

  /**
   * Test {@link ProjectChunks#validateAllConfigured(ProjectStructure, MavenModuleStructure, PrintStream)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} stream.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProjectChunks#validateAllConfigured(ProjectStructure, MavenModuleStructure, PrintStream)}
   */
  @Test
  @DisplayName("Test validateAllConfigured(ProjectStructure, MavenModuleStructure, PrintStream); given ArrayList() stream")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProjectChunks.validateAllConfigured(ProjectStructure, MavenModuleStructure, PrintStream)"})
  void testValidateAllConfigured_givenArrayListStream() {
    // Arrange
    ProjectChunks projectChunks = new ProjectChunks(new ArrayList<>());
    ProjectStructure project = mock(ProjectStructure.class);
    MavenModuleStructure maven = mock(MavenModuleStructure.class);

    ArrayList<MavenModuleAndPath> mavenModuleAndPathList = new ArrayList<>();
    Stream<MavenModuleAndPath> streamResult = mavenModuleAndPathList.stream();
    when(maven.allJavaModules()).thenReturn(streamResult);

    // Act
    projectChunks.validateAllConfigured(project, maven, new PrintStream(new ByteArrayOutputStream(1)));

    // Assert
    verify(maven).allJavaModules();
  }

  /**
   * Test {@link ProjectChunks#validateAllConfigured(ProjectStructure, MavenModuleStructure, PrintStream)}.
   * <ul>
   *   <li>Then throw {@link ProjectChunksValidationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProjectChunks#validateAllConfigured(ProjectStructure, MavenModuleStructure, PrintStream)}
   */
  @Test
  @DisplayName("Test validateAllConfigured(ProjectStructure, MavenModuleStructure, PrintStream); then throw ProjectChunksValidationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProjectChunks.validateAllConfigured(ProjectStructure, MavenModuleStructure, PrintStream)"})
  void testValidateAllConfigured_thenThrowProjectChunksValidationException() {
    // Arrange
    ProjectChunks projectChunks = new ProjectChunks(new ArrayList<>());
    ProjectStructure project = mock(ProjectStructure.class);
    MavenModuleStructure maven = mock(MavenModuleStructure.class);
    when(maven.allJavaModules()).thenThrow(new ProjectChunksValidationException("An error occurred"));

    // Act and Assert
    assertThrows(ProjectChunksValidationException.class,
        () -> projectChunks.validateAllConfigured(project, maven, new PrintStream(new ByteArrayOutputStream(1))));
    verify(maven).allJavaModules();
  }

  /**
   * Test {@link ProjectChunks#stream()}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProjectChunks#stream()}
   */
  @Test
  @DisplayName("Test stream(); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream ProjectChunks.stream()"})
  void testStream_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<ProjectChunk> actualStreamResult = (new ProjectChunks(new ArrayList<>())).stream();

    // Assert
    assertTrue(actualStreamResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link ProjectChunks#toString()}.
   * <p>
   * Method under test: {@link ProjectChunks#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ProjectChunks.toString()"})
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("[]", (new ProjectChunks(new ArrayList<>())).toString());
  }
}
