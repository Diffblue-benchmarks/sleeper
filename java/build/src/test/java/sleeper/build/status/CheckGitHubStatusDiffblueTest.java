package sleeper.build.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.chunks.ProjectChunks;
import sleeper.build.github.GitHubHead;
import sleeper.build.github.InMemoryGitHubWorkflowRuns;
import sleeper.build.github.api.GitHubWorkflowRunsImpl;
import sleeper.build.status.CheckGitHubStatusConfig.Builder;

class CheckGitHubStatusDiffblueTest {
  /**
   * Test {@link CheckGitHubStatus#checkStatus()}.
   * <p>
   * Method under test: {@link CheckGitHubStatus#checkStatus()}
   */
  @Test
  @DisplayName("Test checkStatus()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ChunkStatuses CheckGitHubStatus.checkStatus()"})
  void testCheckStatus() throws UnsupportedEncodingException {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig configuration = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act
    ChunkStatuses actualCheckStatusResult = (new CheckGitHubStatus(configuration, new GitHubWorkflowRunsImpl("ABC123")))
        .checkStatus();

    // Assert
    List<String> reportLinesResult = actualCheckStatusResult.reportLines();
    assertEquals(1, reportLinesResult.size());
    assertEquals("", reportLinesResult.get(0));
    assertFalse(actualCheckStatusResult.isFailCheck());
  }

  /**
   * Test {@link CheckGitHubStatus#checkStatusSingleWorkflow(String)}.
   * <ul>
   *   <li>Then return not hasPreviousFailures.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckGitHubStatus#checkStatusSingleWorkflow(String)}
   */
  @Test
  @DisplayName("Test checkStatusSingleWorkflow(String); then return not hasPreviousFailures")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"WorkflowStatus CheckGitHubStatus.checkStatusSingleWorkflow(String)"})
  void testCheckStatusSingleWorkflow_thenReturnNotHasPreviousFailures() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig configuration = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();
    GitHubHead head2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act
    WorkflowStatus actualCheckStatusSingleWorkflowResult = (new CheckGitHubStatus(configuration,
        new InMemoryGitHubWorkflowRuns(head2, "Workflow"))).checkStatusSingleWorkflow("Workflow");

    // Assert
    assertFalse(actualCheckStatusSingleWorkflowResult.hasPreviousFailures());
    assertTrue(actualCheckStatusSingleWorkflowResult.chunkIdsToBuild().isEmpty());
  }
}
