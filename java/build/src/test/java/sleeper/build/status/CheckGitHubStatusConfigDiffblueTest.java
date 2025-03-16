package sleeper.build.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.chunks.ProjectChunk;
import sleeper.build.chunks.ProjectChunks;
import sleeper.build.github.GitHubHead;
import sleeper.build.github.GitHubWorkflowRuns;
import sleeper.build.github.api.GitHubWorkflowRunsImpl;
import sleeper.build.status.CheckGitHubStatusConfig.Builder;

class CheckGitHubStatusConfigDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#chunks(ProjectChunks)}
   *   <li>{@link Builder#head(GitHubHead)}
   *   <li>{@link Builder#maxRetries(long)}
   *   <li>{@link Builder#retrySeconds(long)}
   *   <li>{@link Builder#token(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CheckGitHubStatusConfig Builder.build()", "Builder Builder.chunks(ProjectChunks)",
      "Builder Builder.head(GitHubHead)", "Builder Builder.maxRetries(long)", "Builder Builder.retrySeconds(long)",
      "Builder Builder.token(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    ProjectChunks chunks = new ProjectChunks(new ArrayList<>());
    Builder chunksResult = builderResult.chunks(chunks);
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act
    CheckGitHubStatusConfig actualBuildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Assert
    GitHubHead head2 = actualBuildResult.getHead();
    assertEquals("Owner/Repository", head2.getOwnerAndRepository());
    assertEquals("Sha", head2.getSha());
    assertEquals("janedoe/featurebranch", head2.getBranch());
    assertEquals(1L, actualBuildResult.getMaxRetries());
    assertEquals(1L, actualBuildResult.getRetrySeconds());
    ProjectChunks chunks2 = actualBuildResult.getChunks();
    Stream<ProjectChunk> streamResult = chunks2.stream();
    assertTrue(streamResult.limit(5).collect(Collectors.toList()).isEmpty());
    assertSame(chunks, chunks2);
  }

  /**
   * Test Builder {@link Builder#chunks(List)} with {@code List}.
   * <p>
   * Method under test: {@link Builder#chunks(List)}
   */
  @Test
  @DisplayName("Test Builder chunks(List) with 'List'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.chunks(List)"})
  void testBuilderChunksWithList() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.chunks(new ArrayList<>()));
  }

  /**
   * Test {@link CheckGitHubStatusConfig#checkStatus(GitHubWorkflowRuns)}.
   * <p>
   * Method under test: {@link CheckGitHubStatusConfig#checkStatus(GitHubWorkflowRuns)}
   */
  @Test
  @DisplayName("Test checkStatus(GitHubWorkflowRuns)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ChunkStatuses CheckGitHubStatusConfig.checkStatus(GitHubWorkflowRuns)"})
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
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act
    ChunkStatuses actualCheckStatusResult = buildResult.checkStatus(new GitHubWorkflowRunsImpl("ABC123"));

    // Assert
    List<String> reportLinesResult = actualCheckStatusResult.reportLines();
    assertEquals(1, reportLinesResult.size());
    assertEquals("", reportLinesResult.get(0));
    assertFalse(actualCheckStatusResult.isFailCheck());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CheckGitHubStatusConfig#toString()}
   *   <li>{@link CheckGitHubStatusConfig#getChunks()}
   *   <li>{@link CheckGitHubStatusConfig#getHead()}
   *   <li>{@link CheckGitHubStatusConfig#getMaxRetries()}
   *   <li>{@link CheckGitHubStatusConfig#getRetrySeconds()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ProjectChunks CheckGitHubStatusConfig.getChunks()",
      "GitHubHead CheckGitHubStatusConfig.getHead()", "long CheckGitHubStatusConfig.getMaxRetries()",
      "long CheckGitHubStatusConfig.getRetrySeconds()", "String CheckGitHubStatusConfig.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    ProjectChunks chunks = new ProjectChunks(new ArrayList<>());
    Builder chunksResult = builderResult.chunks(chunks);
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    ProjectChunks actualChunks = buildResult.getChunks();
    GitHubHead actualHead = buildResult.getHead();
    long actualMaxRetries = buildResult.getMaxRetries();
    long actualRetrySeconds = buildResult.getRetrySeconds();

    // Assert
    assertEquals("Owner/Repository", actualHead.getOwnerAndRepository());
    assertEquals(
        "ProjectConfiguration{head=GitHubHead{owner='Owner', repository='Repository', branch='janedoe/featurebranch',"
            + " sha='Sha'}, chunks=[], retrySeconds=1, maxRetries=1}",
        actualToStringResult);
    assertEquals("Sha", actualHead.getSha());
    assertEquals("janedoe/featurebranch", actualHead.getBranch());
    assertEquals(1L, actualMaxRetries);
    assertEquals(1L, actualRetrySeconds);
    Stream<ProjectChunk> streamResult = actualChunks.stream();
    assertTrue(streamResult.limit(5).collect(Collectors.toList()).isEmpty());
    assertSame(chunks, actualChunks);
  }

  /**
   * Test {@link CheckGitHubStatusConfig#equals(Object)}, and {@link CheckGitHubStatusConfig#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CheckGitHubStatusConfig#equals(Object)}
   *   <li>{@link CheckGitHubStatusConfig#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckGitHubStatusConfig.equals(Object)", "int CheckGitHubStatusConfig.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();
    Builder builderResult2 = CheckGitHubStatusConfig.builder();
    Builder chunksResult2 = builderResult2.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult2 = chunksResult2.head(head2)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link CheckGitHubStatusConfig#equals(Object)}, and {@link CheckGitHubStatusConfig#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CheckGitHubStatusConfig#equals(Object)}
   *   <li>{@link CheckGitHubStatusConfig#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckGitHubStatusConfig.equals(Object)", "int CheckGitHubStatusConfig.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link CheckGitHubStatusConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckGitHubStatusConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckGitHubStatusConfig.equals(Object)", "int CheckGitHubStatusConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<ProjectChunk> chunks = new ArrayList<>();
    chunks.add(mock(ProjectChunk.class));
    ProjectChunks chunks2 = new ProjectChunks(chunks);
    Builder chunksResult = CheckGitHubStatusConfig.builder().chunks(chunks2);
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult2 = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult2 = chunksResult2.head(head2)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CheckGitHubStatusConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckGitHubStatusConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckGitHubStatusConfig.equals(Object)", "int CheckGitHubStatusConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head = GitHubHead.builder().branch("ABC123").owner("Owner").repository("Repository").sha("Sha").build();
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();
    Builder builderResult2 = CheckGitHubStatusConfig.builder();
    Builder chunksResult2 = builderResult2.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult2 = chunksResult2.head(head2)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CheckGitHubStatusConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckGitHubStatusConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckGitHubStatusConfig.equals(Object)", "int CheckGitHubStatusConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(0L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();
    Builder builderResult2 = CheckGitHubStatusConfig.builder();
    Builder chunksResult2 = builderResult2.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult2 = chunksResult2.head(head2)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CheckGitHubStatusConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckGitHubStatusConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckGitHubStatusConfig.equals(Object)", "int CheckGitHubStatusConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(0L)
        .token("ABC123")
        .build();
    Builder builderResult2 = CheckGitHubStatusConfig.builder();
    Builder chunksResult2 = builderResult2.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult2 = chunksResult2.head(head2)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CheckGitHubStatusConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckGitHubStatusConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckGitHubStatusConfig.equals(Object)", "int CheckGitHubStatusConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("Owner")
        .build();
    Builder builderResult2 = CheckGitHubStatusConfig.builder();
    Builder chunksResult2 = builderResult2.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult2 = chunksResult2.head(head2)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CheckGitHubStatusConfig#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckGitHubStatusConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckGitHubStatusConfig.equals(Object)", "int CheckGitHubStatusConfig.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CheckGitHubStatusConfig#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckGitHubStatusConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckGitHubStatusConfig.equals(Object)", "int CheckGitHubStatusConfig.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CheckGitHubStatusConfig.builder();
    Builder chunksResult = builderResult.chunks(new ProjectChunks(new ArrayList<>()));
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    CheckGitHubStatusConfig buildResult = chunksResult.head(head)
        .maxRetries(1L)
        .retrySeconds(1L)
        .token("ABC123")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CheckGitHubStatusConfig");
  }
}
