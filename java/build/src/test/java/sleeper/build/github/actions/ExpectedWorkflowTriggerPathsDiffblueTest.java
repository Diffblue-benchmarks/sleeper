package sleeper.build.github.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.build.chunks.ProjectChunk;
import sleeper.build.chunks.ProjectStructure;
import sleeper.build.maven.InternalModuleIndex;
import sleeper.build.maven.MavenModuleAndPath;

class ExpectedWorkflowTriggerPathsDiffblueTest {
  /**
   * Test {@link ExpectedWorkflowTriggerPaths#from(ProjectStructure, InternalModuleIndex, ProjectChunk, GitHubActionsChunkWorkflow)}.
   * <ul>
   *   <li>Then return size is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExpectedWorkflowTriggerPaths#from(ProjectStructure, InternalModuleIndex, ProjectChunk, GitHubActionsChunkWorkflow)}
   */
  @Test
  @DisplayName("Test from(ProjectStructure, InternalModuleIndex, ProjectChunk, GitHubActionsChunkWorkflow); then return size is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "List ExpectedWorkflowTriggerPaths.from(ProjectStructure, InternalModuleIndex, ProjectChunk, GitHubActionsChunkWorkflow)"})
  void testFrom_thenReturnSizeIsThree() {
    // Arrange
    ProjectStructure project = mock(ProjectStructure.class);
    when(project.getChunksYamlRelative()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    when(project.workflowPathInRepository(Mockito.<ProjectChunk>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    InternalModuleIndex maven = mock(InternalModuleIndex.class);

    ArrayList<MavenModuleAndPath> mavenModuleAndPathList = new ArrayList<>();
    Stream<MavenModuleAndPath> streamResult = mavenModuleAndPathList.stream();
    when(maven.ancestorsForModules(Mockito.<List<String>>any())).thenReturn(streamResult);

    ArrayList<MavenModuleAndPath> mavenModuleAndPathList2 = new ArrayList<>();
    Stream<MavenModuleAndPath> streamResult2 = mavenModuleAndPathList2.stream();
    when(maven.dependenciesForModules(Mockito.<List<String>>any())).thenReturn(streamResult2);
    ProjectChunk chunk = mock(ProjectChunk.class);
    when(chunk.getModules()).thenReturn(new ArrayList<>());
    GitHubActionsChunkWorkflow actualWorkflow = mock(GitHubActionsChunkWorkflow.class);
    when(actualWorkflow.getUsesWorkflowPath()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Act
    List<String> actualFromResult = ExpectedWorkflowTriggerPaths.from(project, maven, chunk, actualWorkflow);

    // Assert
    verify(chunk, atLeast(1)).getModules();
    verify(project).getChunksYamlRelative();
    verify(project).workflowPathInRepository(isA(ProjectChunk.class));
    verify(actualWorkflow).getUsesWorkflowPath();
    verify(maven).ancestorsForModules(isA(List.class));
    verify(maven).dependenciesForModules(isA(List.class));
    assertEquals(3, actualFromResult.size());
    assertEquals(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(), actualFromResult.get(0));
    assertEquals(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(), actualFromResult.get(1));
    assertEquals(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(), actualFromResult.get(2));
  }
}
