package sleeper.build.chunks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.chunks.ProjectChunksYaml.YamlChunk;

class ProjectChunksYamlDiffblueTest {
  /**
   * Test YamlChunk {@link YamlChunk#build(String)}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link YamlChunk#build(String)}
   *   <li>{@link YamlChunk#YamlChunk(String, String, List)}
   * </ul>
   */
  @Test
  @DisplayName("Test YamlChunk build(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void YamlChunk.<init>(String, String, List)", "ProjectChunk YamlChunk.build(String)"})
  void testYamlChunkBuild() {
    // Arrange and Act
    ProjectChunk actualBuildResult = (new YamlChunk("Name", "Workflow", new ArrayList<>())).build("42");

    // Assert
    assertEquals("", actualBuildResult.getMavenProjectList());
    assertEquals("42", actualBuildResult.getId());
    assertEquals("Name", actualBuildResult.getName());
    assertEquals("Workflow", actualBuildResult.getWorkflow());
    assertTrue(actualBuildResult.getModules().isEmpty());
    assertTrue(actualBuildResult.getWorkflowOutputs().isEmpty());
  }
}
