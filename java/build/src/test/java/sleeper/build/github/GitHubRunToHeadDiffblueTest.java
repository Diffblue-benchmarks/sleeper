package sleeper.build.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.github.GitHubWorkflowRun.Builder;

class GitHubRunToHeadDiffblueTest {
  /**
   * Test {@link GitHubRunToHead#GitHubRunToHead(GitHubWorkflowRun, int, int, List)}.
   * <ul>
   *   <li>Given {@code changedPaths must not be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRunToHead#GitHubRunToHead(GitHubWorkflowRun, int, int, List)}
   */
  @Test
  @DisplayName("Test new GitHubRunToHead(GitHubWorkflowRun, int, int, List); given 'changedPaths must not be null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GitHubRunToHead.<init>(GitHubWorkflowRun, int, int, List)"})
  void testNewGitHubRunToHead_givenChangedPathsMustNotBeNull() {
    // Arrange
    GitHubWorkflowRun run = mock(GitHubWorkflowRun.class);

    ArrayList<String> changedPaths = new ArrayList<>();
    changedPaths.add("changedPaths must not be null");
    changedPaths.add("run must not be null");

    // Act
    GitHubRunToHead actualGitHubRunToHead = new GitHubRunToHead(run, 1, 1, changedPaths);

    // Assert
    assertFalse(actualGitHubRunToHead.isRunForHeadOrBehind());
    assertSame(run, actualGitHubRunToHead.getRun());
  }

  /**
   * Test {@link GitHubRunToHead#GitHubRunToHead(GitHubWorkflowRun, int, int, List)}.
   * <ul>
   *   <li>Given {@code run must not be null}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code run must not be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRunToHead#GitHubRunToHead(GitHubWorkflowRun, int, int, List)}
   */
  @Test
  @DisplayName("Test new GitHubRunToHead(GitHubWorkflowRun, int, int, List); given 'run must not be null'; when ArrayList() add 'run must not be null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GitHubRunToHead.<init>(GitHubWorkflowRun, int, int, List)"})
  void testNewGitHubRunToHead_givenRunMustNotBeNull_whenArrayListAddRunMustNotBeNull() {
    // Arrange
    GitHubWorkflowRun run = mock(GitHubWorkflowRun.class);

    ArrayList<String> changedPaths = new ArrayList<>();
    changedPaths.add("run must not be null");

    // Act
    GitHubRunToHead actualGitHubRunToHead = new GitHubRunToHead(run, 1, 1, changedPaths);

    // Assert
    assertFalse(actualGitHubRunToHead.isRunForHeadOrBehind());
    assertSame(run, actualGitHubRunToHead.getRun());
  }

  /**
   * Test {@link GitHubRunToHead#GitHubRunToHead(GitHubWorkflowRun, int, int, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return not RunForHeadOrBehind.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRunToHead#GitHubRunToHead(GitHubWorkflowRun, int, int, List)}
   */
  @Test
  @DisplayName("Test new GitHubRunToHead(GitHubWorkflowRun, int, int, List); when ArrayList(); then return not RunForHeadOrBehind")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GitHubRunToHead.<init>(GitHubWorkflowRun, int, int, List)"})
  void testNewGitHubRunToHead_whenArrayList_thenReturnNotRunForHeadOrBehind() {
    // Arrange
    GitHubWorkflowRun run = mock(GitHubWorkflowRun.class);

    // Act
    GitHubRunToHead actualGitHubRunToHead = new GitHubRunToHead(run, 1, 1, new ArrayList<>());

    // Assert
    assertFalse(actualGitHubRunToHead.isRunForHeadOrBehind());
    assertSame(run, actualGitHubRunToHead.getRun());
  }

  /**
   * Test {@link GitHubRunToHead#sameSha(GitHubWorkflowRun)}.
   * <ul>
   *   <li>When {@link GitHubWorkflowRun}.</li>
   *   <li>Then return RunForHeadOrBehind.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRunToHead#sameSha(GitHubWorkflowRun)}
   */
  @Test
  @DisplayName("Test sameSha(GitHubWorkflowRun); when GitHubWorkflowRun; then return RunForHeadOrBehind")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubRunToHead GitHubRunToHead.sameSha(GitHubWorkflowRun)"})
  void testSameSha_whenGitHubWorkflowRun_thenReturnRunForHeadOrBehind() {
    // Arrange
    GitHubWorkflowRun run = mock(GitHubWorkflowRun.class);

    // Act
    GitHubRunToHead actualSameShaResult = GitHubRunToHead.sameSha(run);

    // Assert
    assertTrue(actualSameShaResult.isRunForHeadOrBehind());
    assertSame(run, actualSameShaResult.getRun());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubRunToHead#toString()}
   *   <li>{@link GitHubRunToHead#getRun()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun GitHubRunToHead.getRun()", "String GitHubRunToHead.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    GitHubRunToHead sameShaResult = GitHubRunToHead.sameSha(run);

    // Act
    String actualToStringResult = sameShaResult.toString();
    GitHubWorkflowRun actualRun = sameShaResult.getRun();

    // Assert
    assertEquals("Commit Message", actualRun.getCommitMessage());
    assertEquals("Commit Sha", actualRun.getCommitSha());
    assertEquals("Conclusion", actualRun.getConclusion());
    assertEquals(
        "GitHubRunToHead{run=GitHubWorkflowRun{status='Status', conclusion='Conclusion', runId=1, runUrl='https"
            + "://example.org/example', runStarted=1970-01-01T00:00:00Z, commitSha='Commit Sha', commitMessage='Commit"
            + " Message', changedPaths=[]}, aheadBy=0, behindBy=0, changedPaths=[]}",
        actualToStringResult);
    assertEquals("Status", actualRun.getStatus());
    assertEquals("https://example.org/example", actualRun.getRunUrl());
    Instant runStarted = actualRun.getRunStarted();
    assertEquals(0, runStarted.getNano());
    assertEquals(0L, runStarted.getEpochSecond());
    assertEquals(1L, actualRun.getRunId().longValue());
    assertTrue(actualRun.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test {@link GitHubRunToHead#isRunForHeadOrBehind()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRunToHead#isRunForHeadOrBehind()}
   */
  @Test
  @DisplayName("Test isRunForHeadOrBehind(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubRunToHead.isRunForHeadOrBehind()"})
  void testIsRunForHeadOrBehind_thenReturnFalse() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertFalse((new GitHubRunToHead(run, 1, 1, new ArrayList<>())).isRunForHeadOrBehind());
  }

  /**
   * Test {@link GitHubRunToHead#isRunForHeadOrBehind()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRunToHead#isRunForHeadOrBehind()}
   */
  @Test
  @DisplayName("Test isRunForHeadOrBehind(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubRunToHead.isRunForHeadOrBehind()"})
  void testIsRunForHeadOrBehind_thenReturnTrue() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertTrue(GitHubRunToHead.sameSha(run).isRunForHeadOrBehind());
  }

  /**
   * Test {@link GitHubRunToHead#equals(Object)}, and {@link GitHubRunToHead#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubRunToHead#equals(Object)}
   *   <li>{@link GitHubRunToHead#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubRunToHead.equals(Object)", "int GitHubRunToHead.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    GitHubRunToHead sameShaResult = GitHubRunToHead.sameSha(run);
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    GitHubRunToHead sameShaResult2 = GitHubRunToHead.sameSha(run2);

    // Act and Assert
    assertEquals(sameShaResult, sameShaResult2);
    int expectedHashCodeResult = sameShaResult.hashCode();
    assertEquals(expectedHashCodeResult, sameShaResult2.hashCode());
  }

  /**
   * Test {@link GitHubRunToHead#equals(Object)}, and {@link GitHubRunToHead#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubRunToHead#equals(Object)}
   *   <li>{@link GitHubRunToHead#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubRunToHead.equals(Object)", "int GitHubRunToHead.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    GitHubRunToHead sameShaResult = GitHubRunToHead.sameSha(run);

    // Act and Assert
    assertEquals(sameShaResult, sameShaResult);
    int expectedHashCodeResult = sameShaResult.hashCode();
    assertEquals(expectedHashCodeResult, sameShaResult.hashCode());
  }

  /**
   * Test {@link GitHubRunToHead#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRunToHead#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubRunToHead.equals(Object)", "int GitHubRunToHead.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage(null)
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    GitHubRunToHead sameShaResult = GitHubRunToHead.sameSha(run);
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(sameShaResult, GitHubRunToHead.sameSha(run2));
  }

  /**
   * Test {@link GitHubRunToHead#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRunToHead#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubRunToHead.equals(Object)", "int GitHubRunToHead.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    GitHubRunToHead gitHubRunToHead = new GitHubRunToHead(run, 1, 1, new ArrayList<>());
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(gitHubRunToHead, GitHubRunToHead.sameSha(run2));
  }

  /**
   * Test {@link GitHubRunToHead#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRunToHead#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubRunToHead.equals(Object)", "int GitHubRunToHead.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(GitHubRunToHead.sameSha(run), null);
  }

  /**
   * Test {@link GitHubRunToHead#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRunToHead#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubRunToHead.equals(Object)", "int GitHubRunToHead.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun run = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(GitHubRunToHead.sameSha(run), "Different type to GitHubRunToHead");
  }
}
