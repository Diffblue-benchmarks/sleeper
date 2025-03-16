package sleeper.build.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

class GitHubWorkflowRunDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#branch(String)}
   *   <li>{@link Builder#commitMessage(String)}
   *   <li>{@link Builder#commitSha(String)}
   *   <li>{@link Builder#conclusion(String)}
   *   <li>{@link Builder#pathsChangedSinceThisRun(List)}
   *   <li>{@link Builder#runId(Long)}
   *   <li>{@link Builder#runStarted(Instant)}
   *   <li>{@link Builder#runUrl(String)}
   *   <li>{@link Builder#status(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.branch(String)", "GitHubWorkflowRun Builder.build()",
      "Builder Builder.commitMessage(String)", "Builder Builder.commitSha(String)",
      "Builder Builder.conclusion(String)", "Builder Builder.pathsChangedSinceThisRun(List)",
      "Builder Builder.runId(Long)", "Builder Builder.runStarted(Instant)", "Builder Builder.runUrl(String)",
      "Builder Builder.status(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);

    // Act
    GitHubWorkflowRun actualBuildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Assert
    assertEquals("Commit Message", actualBuildResult.getCommitMessage());
    assertEquals("Commit Sha", actualBuildResult.getCommitSha());
    assertEquals("Conclusion", actualBuildResult.getConclusion());
    assertEquals("Status", actualBuildResult.getStatus());
    assertEquals("https://example.org/example", actualBuildResult.getRunUrl());
    Instant runStarted = actualBuildResult.getRunStarted();
    assertEquals(0, runStarted.getNano());
    assertEquals(0L, runStarted.getEpochSecond());
    assertEquals(1L, actualBuildResult.getRunId().longValue());
    assertTrue(actualBuildResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#cancelled()}.
   * <ul>
   *   <li>Given builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#cancelled()}
   */
  @Test
  @DisplayName("Test Builder cancelled(); given builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.cancelled()"})
  void testBuilderCancelled_givenBuilder() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();

    // Act
    GitHubWorkflowRun actualCancelledResult = builderResult.cancelled();

    // Assert
    GitHubWorkflowRun buildResult = builderResult.build();
    assertEquals("cancelled", buildResult.getConclusion());
    assertEquals("cancelled", actualCancelledResult.getConclusion());
    assertEquals("completed", buildResult.getStatus());
    assertEquals("completed", actualCancelledResult.getStatus());
    assertNull(actualCancelledResult.getRunId());
    assertNull(actualCancelledResult.getCommitMessage());
    assertNull(actualCancelledResult.getCommitSha());
    assertNull(actualCancelledResult.getRunUrl());
    assertNull(actualCancelledResult.getRunStarted());
    assertTrue(actualCancelledResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#cancelled()}.
   * <ul>
   *   <li>Given builder branch {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#cancelled()}
   */
  @Test
  @DisplayName("Test Builder cancelled(); given builder branch 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.cancelled()"})
  void testBuilderCancelled_givenBuilderBranchNull() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();
    builderResult.branch(null);
    builderResult.commitSha(null);
    builderResult.commitMessage(null);
    builderResult.runUrl("");
    builderResult.status(null);
    builderResult.conclusion(null);

    // Act
    GitHubWorkflowRun actualCancelledResult = builderResult.cancelled();

    // Assert
    GitHubWorkflowRun buildResult = builderResult.build();
    assertEquals("cancelled", buildResult.getConclusion());
    assertEquals("cancelled", actualCancelledResult.getConclusion());
    assertEquals("completed", buildResult.getStatus());
    assertEquals("completed", actualCancelledResult.getStatus());
    assertNull(actualCancelledResult.getRunId());
    assertNull(actualCancelledResult.getCommitMessage());
    assertNull(actualCancelledResult.getCommitSha());
    assertNull(actualCancelledResult.getRunUrl());
    assertNull(actualCancelledResult.getRunStarted());
    assertTrue(actualCancelledResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#failure()}.
   * <ul>
   *   <li>Given builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#failure()}
   */
  @Test
  @DisplayName("Test Builder failure(); given builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.failure()"})
  void testBuilderFailure_givenBuilder() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();

    // Act
    GitHubWorkflowRun actualFailureResult = builderResult.failure();

    // Assert
    GitHubWorkflowRun buildResult = builderResult.build();
    assertEquals("completed", buildResult.getStatus());
    assertEquals("completed", actualFailureResult.getStatus());
    assertEquals("failure", buildResult.getConclusion());
    assertEquals("failure", actualFailureResult.getConclusion());
    assertNull(actualFailureResult.getRunId());
    assertNull(actualFailureResult.getCommitMessage());
    assertNull(actualFailureResult.getCommitSha());
    assertNull(actualFailureResult.getRunUrl());
    assertNull(actualFailureResult.getRunStarted());
    assertTrue(actualFailureResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#failure()}.
   * <ul>
   *   <li>Given builder runUrl {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#failure()}
   */
  @Test
  @DisplayName("Test Builder failure(); given builder runUrl 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.failure()"})
  void testBuilderFailure_givenBuilderRunUrlNull() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();
    builderResult.runUrl(null);
    builderResult.commitSha(null);
    builderResult.commitMessage(null);
    builderResult.status(null);
    builderResult.branch("");
    builderResult.conclusion(null);

    // Act
    GitHubWorkflowRun actualFailureResult = builderResult.failure();

    // Assert
    GitHubWorkflowRun buildResult = builderResult.build();
    assertEquals("completed", buildResult.getStatus());
    assertEquals("completed", actualFailureResult.getStatus());
    assertEquals("failure", buildResult.getConclusion());
    assertEquals("failure", actualFailureResult.getConclusion());
    assertNull(actualFailureResult.getRunId());
    assertNull(actualFailureResult.getCommitMessage());
    assertNull(actualFailureResult.getCommitSha());
    assertNull(actualFailureResult.getRunUrl());
    assertNull(actualFailureResult.getRunStarted());
    assertTrue(actualFailureResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#inProgress()}.
   * <ul>
   *   <li>Given builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#inProgress()}
   */
  @Test
  @DisplayName("Test Builder inProgress(); given builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.inProgress()"})
  void testBuilderInProgress_givenBuilder() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();

    // Act
    GitHubWorkflowRun actualInProgressResult = builderResult.inProgress();

    // Assert
    assertEquals("in_progress", builderResult.build().getStatus());
    assertEquals("in_progress", actualInProgressResult.getStatus());
    assertNull(actualInProgressResult.getRunId());
    assertNull(actualInProgressResult.getCommitMessage());
    assertNull(actualInProgressResult.getCommitSha());
    assertNull(actualInProgressResult.getConclusion());
    assertNull(actualInProgressResult.getRunUrl());
    assertNull(actualInProgressResult.getRunStarted());
    assertTrue(actualInProgressResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#inProgress()}.
   * <ul>
   *   <li>Given builder runUrl {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#inProgress()}
   */
  @Test
  @DisplayName("Test Builder inProgress(); given builder runUrl 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.inProgress()"})
  void testBuilderInProgress_givenBuilderRunUrlNull() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();
    builderResult.runUrl(null);
    builderResult.commitMessage(null);
    builderResult.commitSha(null);
    builderResult.conclusion(null);
    builderResult.branch("");
    builderResult.status(null);

    // Act
    GitHubWorkflowRun actualInProgressResult = builderResult.inProgress();

    // Assert
    assertEquals("in_progress", builderResult.build().getStatus());
    assertEquals("in_progress", actualInProgressResult.getStatus());
    assertNull(actualInProgressResult.getRunId());
    assertNull(actualInProgressResult.getCommitMessage());
    assertNull(actualInProgressResult.getCommitSha());
    assertNull(actualInProgressResult.getConclusion());
    assertNull(actualInProgressResult.getRunUrl());
    assertNull(actualInProgressResult.getRunStarted());
    assertTrue(actualInProgressResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#noBuild()}.
   * <ul>
   *   <li>Given builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#noBuild()}
   */
  @Test
  @DisplayName("Test Builder noBuild(); given builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.noBuild()"})
  void testBuilderNoBuild_givenBuilder() {
    // Arrange and Act
    GitHubWorkflowRun actualNoBuildResult = GitHubWorkflowRun.builder().noBuild();

    // Assert
    assertNull(actualNoBuildResult.getRunId());
    assertNull(actualNoBuildResult.getCommitMessage());
    assertNull(actualNoBuildResult.getCommitSha());
    assertNull(actualNoBuildResult.getConclusion());
    assertNull(actualNoBuildResult.getRunUrl());
    assertNull(actualNoBuildResult.getStatus());
    assertNull(actualNoBuildResult.getRunStarted());
    assertTrue(actualNoBuildResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#noBuild()}.
   * <ul>
   *   <li>Given builder branch empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#noBuild()}
   */
  @Test
  @DisplayName("Test Builder noBuild(); given builder branch empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.noBuild()"})
  void testBuilderNoBuild_givenBuilderBranchEmptyString() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();
    builderResult.conclusion(null);
    builderResult.commitMessage(null);
    builderResult.commitSha(null);
    builderResult.status(null);
    builderResult.runUrl(null);
    builderResult.branch("");

    // Act
    GitHubWorkflowRun actualNoBuildResult = builderResult.noBuild();

    // Assert
    assertNull(actualNoBuildResult.getRunId());
    assertNull(actualNoBuildResult.getCommitMessage());
    assertNull(actualNoBuildResult.getCommitSha());
    assertNull(actualNoBuildResult.getConclusion());
    assertNull(actualNoBuildResult.getRunUrl());
    assertNull(actualNoBuildResult.getStatus());
    assertNull(actualNoBuildResult.getRunStarted());
    assertTrue(actualNoBuildResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#noBuild()}.
   * <ul>
   *   <li>Given builder branch {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#noBuild()}
   */
  @Test
  @DisplayName("Test Builder noBuild(); given builder branch 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.noBuild()"})
  void testBuilderNoBuild_givenBuilderBranchFoo() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();
    builderResult.conclusion(null);
    builderResult.commitMessage(null);
    builderResult.commitSha(null);
    builderResult.status(null);
    builderResult.runUrl(null);
    builderResult.branch("foo");

    // Act
    GitHubWorkflowRun actualNoBuildResult = builderResult.noBuild();

    // Assert
    assertNull(actualNoBuildResult.getRunId());
    assertNull(actualNoBuildResult.getCommitMessage());
    assertNull(actualNoBuildResult.getCommitSha());
    assertNull(actualNoBuildResult.getConclusion());
    assertNull(actualNoBuildResult.getRunUrl());
    assertNull(actualNoBuildResult.getStatus());
    assertNull(actualNoBuildResult.getRunStarted());
    assertTrue(actualNoBuildResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#pathsChangedSinceThisRunArray(String[])}.
   * <p>
   * Method under test: {@link Builder#pathsChangedSinceThisRunArray(String[])}
   */
  @Test
  @DisplayName("Test Builder pathsChangedSinceThisRunArray(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.pathsChangedSinceThisRunArray(String[])"})
  void testBuilderPathsChangedSinceThisRunArray() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();

    // Act
    Builder actualPathsChangedSinceThisRunArrayResult = builderResult.pathsChangedSinceThisRunArray("Changed Paths");

    // Assert
    List<String> pathsChangedSinceThisRun = builderResult.build().getPathsChangedSinceThisRun();
    assertEquals(1, pathsChangedSinceThisRun.size());
    assertEquals("Changed Paths", pathsChangedSinceThisRun.get(0));
    assertSame(builderResult, actualPathsChangedSinceThisRunArrayResult);
  }

  /**
   * Test Builder {@link Builder#success()}.
   * <ul>
   *   <li>Given builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#success()}
   */
  @Test
  @DisplayName("Test Builder success(); given builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.success()"})
  void testBuilderSuccess_givenBuilder() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();

    // Act
    GitHubWorkflowRun actualSuccessResult = builderResult.success();

    // Assert
    GitHubWorkflowRun buildResult = builderResult.build();
    assertEquals("completed", buildResult.getStatus());
    assertEquals("completed", actualSuccessResult.getStatus());
    assertEquals("success", buildResult.getConclusion());
    assertEquals("success", actualSuccessResult.getConclusion());
    assertNull(actualSuccessResult.getRunId());
    assertNull(actualSuccessResult.getCommitMessage());
    assertNull(actualSuccessResult.getCommitSha());
    assertNull(actualSuccessResult.getRunUrl());
    assertNull(actualSuccessResult.getRunStarted());
    assertTrue(actualSuccessResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Builder {@link Builder#success()}.
   * <ul>
   *   <li>Given builder commitMessage {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#success()}
   */
  @Test
  @DisplayName("Test Builder success(); given builder commitMessage 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Builder.success()"})
  void testBuilderSuccess_givenBuilderCommitMessageNull() {
    // Arrange
    Builder builderResult = GitHubWorkflowRun.builder();
    builderResult.commitMessage(null);
    builderResult.status(null);
    builderResult.commitSha(null);
    builderResult.runUrl(null);
    builderResult.branch("");
    builderResult.conclusion(null);

    // Act
    GitHubWorkflowRun actualSuccessResult = builderResult.success();

    // Assert
    GitHubWorkflowRun buildResult = builderResult.build();
    assertEquals("completed", buildResult.getStatus());
    assertEquals("completed", actualSuccessResult.getStatus());
    assertEquals("success", buildResult.getConclusion());
    assertEquals("success", actualSuccessResult.getConclusion());
    assertNull(actualSuccessResult.getRunId());
    assertNull(actualSuccessResult.getCommitMessage());
    assertNull(actualSuccessResult.getCommitSha());
    assertNull(actualSuccessResult.getRunUrl());
    assertNull(actualSuccessResult.getRunStarted());
    assertTrue(actualSuccessResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test {@link GitHubWorkflowRun#isSameCommit(GitHubHead)}.
   * <p>
   * Method under test: {@link GitHubWorkflowRun#isSameCommit(GitHubHead)}
   */
  @Test
  @DisplayName("Test isSameCommit(GitHubHead)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.isSameCommit(GitHubHead)"})
  void testIsSameCommit() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertFalse(buildResult.isSameCommit(head));
  }

  /**
   * Test {@link GitHubWorkflowRun#isSameBranch(GitHubHead)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#isSameBranch(GitHubHead)}
   */
  @Test
  @DisplayName("Test isSameBranch(GitHubHead); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.isSameBranch(GitHubHead)"})
  void testIsSameBranch_thenReturnFalse() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("Branch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertFalse(buildResult.isSameBranch(head));
  }

  /**
   * Test {@link GitHubWorkflowRun#isSameBranch(GitHubHead)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#isSameBranch(GitHubHead)}
   */
  @Test
  @DisplayName("Test isSameBranch(GitHubHead); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.isSameBranch(GitHubHead)"})
  void testIsSameBranch_thenReturnTrue() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertTrue(buildResult.isSameBranch(head));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubWorkflowRun#toString()}
   *   <li>{@link GitHubWorkflowRun#withCommitSha(String)}
   *   <li>{@link GitHubWorkflowRun#getCommitMessage()}
   *   <li>{@link GitHubWorkflowRun#getCommitSha()}
   *   <li>{@link GitHubWorkflowRun#getConclusion()}
   *   <li>{@link GitHubWorkflowRun#getPathsChangedSinceThisRun()}
   *   <li>{@link GitHubWorkflowRun#getRunId()}
   *   <li>{@link GitHubWorkflowRun#getRunStarted()}
   *   <li>{@link GitHubWorkflowRun#getRunUrl()}
   *   <li>{@link GitHubWorkflowRun#getStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String GitHubWorkflowRun.getCommitMessage()", "String GitHubWorkflowRun.getCommitSha()",
      "String GitHubWorkflowRun.getConclusion()", "List GitHubWorkflowRun.getPathsChangedSinceThisRun()",
      "Long GitHubWorkflowRun.getRunId()", "Instant GitHubWorkflowRun.getRunStarted()",
      "String GitHubWorkflowRun.getRunUrl()", "String GitHubWorkflowRun.getStatus()",
      "String GitHubWorkflowRun.toString()", "Builder GitHubWorkflowRun.withCommitSha(String)"})
  void testGettersAndSetters() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    buildResult.withCommitSha("Commit Sha");
    String actualCommitMessage = buildResult.getCommitMessage();
    String actualCommitSha = buildResult.getCommitSha();
    String actualConclusion = buildResult.getConclusion();
    List<String> actualPathsChangedSinceThisRun = buildResult.getPathsChangedSinceThisRun();
    Long actualRunId = buildResult.getRunId();
    Instant actualRunStarted = buildResult.getRunStarted();
    String actualRunUrl = buildResult.getRunUrl();

    // Assert
    assertEquals("Commit Message", actualCommitMessage);
    assertEquals("Commit Sha", actualCommitSha);
    assertEquals("Conclusion", actualConclusion);
    assertEquals(
        "GitHubWorkflowRun{status='Status', conclusion='Conclusion', runId=1, runUrl='https://example.org/example',"
            + " runStarted=1970-01-01T00:00:00Z, commitSha='Commit Sha', commitMessage='Commit Message', changedPaths"
            + "=[]}",
        actualToStringResult);
    assertEquals("Status", buildResult.getStatus());
    assertEquals("https://example.org/example", actualRunUrl);
    assertEquals(1L, actualRunId.longValue());
    assertTrue(actualPathsChangedSinceThisRun.isEmpty());
    assertSame(actualRunStarted.EPOCH, actualRunStarted);
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}, and {@link GitHubWorkflowRun#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubWorkflowRun#equals(Object)}
   *   <li>{@link GitHubWorkflowRun#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}, and {@link GitHubWorkflowRun#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubWorkflowRun#equals(Object)}
   *   <li>{@link GitHubWorkflowRun#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage(null)
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha(null)
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion(null);
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    ArrayList<String> changedPaths = new ArrayList<>();
    changedPaths.add("foo");
    Builder runIdResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion")
        .pathsChangedSinceThisRun(changedPaths)
        .runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(2L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("Run Url")
        .status("Status")
        .build();
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status(null)
        .build();
    Builder conclusionResult2 = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult2 = conclusionResult2.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult2 = runIdResult2
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link GitHubWorkflowRun#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubWorkflowRun.equals(Object)", "int GitHubWorkflowRun.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder conclusionResult = GitHubWorkflowRun.builder()
        .branch("janedoe/featurebranch")
        .commitMessage("Commit Message")
        .commitSha("Commit Sha")
        .conclusion("Conclusion");
    Builder runIdResult = conclusionResult.pathsChangedSinceThisRun(new ArrayList<>()).runId(1L);
    GitHubWorkflowRun buildResult = runIdResult
        .runStarted(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .runUrl("https://example.org/example")
        .status("Status")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to GitHubWorkflowRun");
  }
}
