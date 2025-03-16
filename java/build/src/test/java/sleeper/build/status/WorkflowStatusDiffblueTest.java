package sleeper.build.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.github.GitHubHead;
import sleeper.build.status.ChunkStatuses.Builder;

class WorkflowStatusDiffblueTest {
  /**
   * Test {@link WorkflowStatus#WorkflowStatus(ChunkStatuses, List)}.
   * <ul>
   *   <li>Given {@code chunkIdsToBuild must not be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowStatus#WorkflowStatus(ChunkStatuses, List)}
   */
  @Test
  @DisplayName("Test new WorkflowStatus(ChunkStatuses, List); given 'chunkIdsToBuild must not be null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WorkflowStatus.<init>(ChunkStatuses, List)"})
  void testNewWorkflowStatus_givenChunkIdsToBuildMustNotBeNull() {
    // Arrange
    ChunkStatuses chunks = mock(ChunkStatuses.class);

    ArrayList<String> chunkIdsToBuild = new ArrayList<>();
    chunkIdsToBuild.add("chunkIdsToBuild must not be null");
    chunkIdsToBuild.add("chunks must not be null");

    // Act
    WorkflowStatus actualWorkflowStatus = new WorkflowStatus(chunks, chunkIdsToBuild);

    // Assert
    assertFalse(actualWorkflowStatus.hasPreviousFailures());
    assertEquals(chunkIdsToBuild, actualWorkflowStatus.chunkIdsToBuild());
  }

  /**
   * Test {@link WorkflowStatus#WorkflowStatus(ChunkStatuses, List)}.
   * <ul>
   *   <li>Then return chunkIdsToBuild is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowStatus#WorkflowStatus(ChunkStatuses, List)}
   */
  @Test
  @DisplayName("Test new WorkflowStatus(ChunkStatuses, List); then return chunkIdsToBuild is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WorkflowStatus.<init>(ChunkStatuses, List)"})
  void testNewWorkflowStatus_thenReturnChunkIdsToBuildIsArrayList() {
    // Arrange
    ChunkStatuses chunks = mock(ChunkStatuses.class);

    ArrayList<String> chunkIdsToBuild = new ArrayList<>();
    chunkIdsToBuild.add("chunks must not be null");

    // Act
    WorkflowStatus actualWorkflowStatus = new WorkflowStatus(chunks, chunkIdsToBuild);

    // Assert
    assertFalse(actualWorkflowStatus.hasPreviousFailures());
    assertEquals(chunkIdsToBuild, actualWorkflowStatus.chunkIdsToBuild());
  }

  /**
   * Test {@link WorkflowStatus#WorkflowStatus(ChunkStatuses, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return chunkIdsToBuild Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowStatus#WorkflowStatus(ChunkStatuses, List)}
   */
  @Test
  @DisplayName("Test new WorkflowStatus(ChunkStatuses, List); when ArrayList(); then return chunkIdsToBuild Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WorkflowStatus.<init>(ChunkStatuses, List)"})
  void testNewWorkflowStatus_whenArrayList_thenReturnChunkIdsToBuildEmpty() {
    // Arrange
    ChunkStatuses chunks = mock(ChunkStatuses.class);

    // Act
    WorkflowStatus actualWorkflowStatus = new WorkflowStatus(chunks, new ArrayList<>());

    // Assert
    assertFalse(actualWorkflowStatus.hasPreviousFailures());
    assertTrue(actualWorkflowStatus.chunkIdsToBuild().isEmpty());
  }

  /**
   * Test {@link WorkflowStatus#hasPreviousFailures()}.
   * <p>
   * Method under test: {@link WorkflowStatus#hasPreviousFailures()}
   */
  @Test
  @DisplayName("Test hasPreviousFailures()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowStatus.hasPreviousFailures()"})
  void testHasPreviousFailures() {
    // Arrange
    Builder builderResult = ChunkStatuses.builder();
    Builder chunksResult = builderResult.chunks(new ArrayList<>());
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses chunks = chunksResult.head(head).build();

    // Act and Assert
    assertFalse((new WorkflowStatus(chunks, new ArrayList<>())).hasPreviousFailures());
  }

  /**
   * Test {@link WorkflowStatus#previousBuildsReportLines()}.
   * <p>
   * Method under test: {@link WorkflowStatus#previousBuildsReportLines()}
   */
  @Test
  @DisplayName("Test previousBuildsReportLines()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List WorkflowStatus.previousBuildsReportLines()"})
  void testPreviousBuildsReportLines() throws UnsupportedEncodingException {
    // Arrange
    Builder builderResult = ChunkStatuses.builder();
    Builder chunksResult = builderResult.chunks(new ArrayList<>());
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses chunks = chunksResult.head(head).build();

    // Act
    List<String> actualPreviousBuildsReportLinesResult = (new WorkflowStatus(chunks, new ArrayList<>()))
        .previousBuildsReportLines();

    // Assert
    assertEquals(1, actualPreviousBuildsReportLinesResult.size());
    assertEquals("", actualPreviousBuildsReportLinesResult.get(0));
  }

  /**
   * Test {@link WorkflowStatus#chunkIdsToBuild()}.
   * <p>
   * Method under test: {@link WorkflowStatus#chunkIdsToBuild()}
   */
  @Test
  @DisplayName("Test chunkIdsToBuild()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List WorkflowStatus.chunkIdsToBuild()"})
  void testChunkIdsToBuild() {
    // Arrange
    Builder builderResult = ChunkStatuses.builder();
    Builder chunksResult = builderResult.chunks(new ArrayList<>());
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses chunks = chunksResult.head(head).build();

    // Act and Assert
    assertTrue((new WorkflowStatus(chunks, new ArrayList<>())).chunkIdsToBuild().isEmpty());
  }
}
