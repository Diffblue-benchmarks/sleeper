package sleeper.build.github.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import sleeper.build.github.GitHubWorkflowRun;
import sleeper.build.github.api.GitHubWorkflowRunsResponse.Commit;
import sleeper.build.github.api.GitHubWorkflowRunsResponse.Run;

class GitHubWorkflowRunsResponseDiffblueTest {
  /**
   * Test {@link GitHubWorkflowRunsResponse#GitHubWorkflowRunsResponse(List)}.
   * <ul>
   *   <li>Then return WorkflowRuns is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRunsResponse#GitHubWorkflowRunsResponse(List)}
   */
  @Test
  @DisplayName("Test new GitHubWorkflowRunsResponse(List); then return WorkflowRuns is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GitHubWorkflowRunsResponse.<init>(List)"})
  void testNewGitHubWorkflowRunsResponse_thenReturnWorkflowRunsIsArrayList() {
    // Arrange
    ArrayList<Run> workflowRuns = new ArrayList<>();
    Instant runStartedAt = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    workflowRuns.add(new Run(1L, "Head Sha", "Status", "Conclusion", "https://example.org/example", runStartedAt,
        new Commit("Not all who wander are lost")));
    Instant runStartedAt2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    workflowRuns.add(new Run(1L, "Head Sha", "Status", "Conclusion", "https://example.org/example", runStartedAt2,
        new Commit("Not all who wander are lost")));

    // Act and Assert
    assertSame(workflowRuns, (new GitHubWorkflowRunsResponse(workflowRuns)).getWorkflowRuns());
  }

  /**
   * Test {@link GitHubWorkflowRunsResponse#GitHubWorkflowRunsResponse(List)}.
   * <ul>
   *   <li>Then return WorkflowRuns size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRunsResponse#GitHubWorkflowRunsResponse(List)}
   */
  @Test
  @DisplayName("Test new GitHubWorkflowRunsResponse(List); then return WorkflowRuns size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GitHubWorkflowRunsResponse.<init>(List)"})
  void testNewGitHubWorkflowRunsResponse_thenReturnWorkflowRunsSizeIsOne() {
    // Arrange
    ArrayList<Run> workflowRuns = new ArrayList<>();
    Instant runStartedAt = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Run run = new Run(1L, "Head Sha", "Status", "Conclusion", "https://example.org/example", runStartedAt,
        new Commit("Not all who wander are lost"));

    workflowRuns.add(run);

    // Act and Assert
    List<Run> workflowRuns2 = (new GitHubWorkflowRunsResponse(workflowRuns)).getWorkflowRuns();
    assertEquals(1, workflowRuns2.size());
    assertSame(workflowRuns, workflowRuns2);
    assertSame(run, workflowRuns2.get(0));
  }

  /**
   * Test {@link GitHubWorkflowRunsResponse#GitHubWorkflowRunsResponse(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return WorkflowRuns Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRunsResponse#GitHubWorkflowRunsResponse(List)}
   */
  @Test
  @DisplayName("Test new GitHubWorkflowRunsResponse(List); when ArrayList(); then return WorkflowRuns Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GitHubWorkflowRunsResponse.<init>(List)"})
  void testNewGitHubWorkflowRunsResponse_whenArrayList_thenReturnWorkflowRunsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new GitHubWorkflowRunsResponse(new ArrayList<>())).getWorkflowRuns().isEmpty());
  }

  /**
   * Test {@link GitHubWorkflowRunsResponse#GitHubWorkflowRunsResponse(List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return WorkflowRuns Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRunsResponse#GitHubWorkflowRunsResponse(List)}
   */
  @Test
  @DisplayName("Test new GitHubWorkflowRunsResponse(List); when 'null'; then return WorkflowRuns Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GitHubWorkflowRunsResponse.<init>(List)"})
  void testNewGitHubWorkflowRunsResponse_whenNull_thenReturnWorkflowRunsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new GitHubWorkflowRunsResponse(null)).getWorkflowRuns().isEmpty());
  }

  /**
   * Test {@link GitHubWorkflowRunsResponse#getWorkflowRuns()}.
   * <p>
   * Method under test: {@link GitHubWorkflowRunsResponse#getWorkflowRuns()}
   */
  @Test
  @DisplayName("Test getWorkflowRuns()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List GitHubWorkflowRunsResponse.getWorkflowRuns()"})
  void testGetWorkflowRuns() {
    // Arrange
    ArrayList<Run> workflowRuns = new ArrayList<>();

    // Act
    List<Run> actualWorkflowRuns = (new GitHubWorkflowRunsResponse(workflowRuns)).getWorkflowRuns();

    // Assert
    assertTrue(actualWorkflowRuns.isEmpty());
    assertSame(workflowRuns, actualWorkflowRuns);
  }

  /**
   * Test Run {@link Run#Run(long, String, String, String, String, Instant, Commit)}.
   * <ul>
   *   <li>Then return toInternalRun CommitMessage is {@code Not all who wander are lost}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Run#Run(long, String, String, String, String, Instant, Commit)}
   */
  @Test
  @DisplayName("Test Run new Run(long, String, String, String, String, Instant, Commit); then return toInternalRun CommitMessage is 'Not all who wander are lost'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Run.<init>(long, String, String, String, String, Instant, Commit)"})
  void testRunNewRun_thenReturnToInternalRunCommitMessageIsNotAllWhoWanderAreLost() {
    // Arrange
    Instant runStartedAt = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    GitHubWorkflowRun toInternalRunResult = (new Run(1L, "Head Sha", "Status", "Conclusion",
        "https://example.org/example", runStartedAt, new Commit("Not all who wander are lost"))).toInternalRun();
    assertEquals("Conclusion", toInternalRunResult.getConclusion());
    assertEquals("Head Sha", toInternalRunResult.getCommitSha());
    assertEquals("Not all who wander are lost", toInternalRunResult.getCommitMessage());
    assertEquals("Status", toInternalRunResult.getStatus());
    assertEquals("https://example.org/example", toInternalRunResult.getRunUrl());
    assertEquals(1L, toInternalRunResult.getRunId().longValue());
    assertTrue(toInternalRunResult.getPathsChangedSinceThisRun().isEmpty());
    Instant expectedRunStarted = runStartedAt.EPOCH;
    assertSame(expectedRunStarted, toInternalRunResult.getRunStarted());
  }

  /**
   * Test Run {@link Run#Run(long, String, String, String, String, Instant, Commit)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return toInternalRun CommitMessage is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Run#Run(long, String, String, String, String, Instant, Commit)}
   */
  @Test
  @DisplayName("Test Run new Run(long, String, String, String, String, Instant, Commit); when 'null'; then return toInternalRun CommitMessage is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Run.<init>(long, String, String, String, String, Instant, Commit)"})
  void testRunNewRun_whenNull_thenReturnToInternalRunCommitMessageIsNull() {
    // Arrange
    Instant runStartedAt = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    GitHubWorkflowRun toInternalRunResult = (new Run(1L, "Head Sha", "Status", "Conclusion",
        "https://example.org/example", runStartedAt, null)).toInternalRun();
    assertEquals("Conclusion", toInternalRunResult.getConclusion());
    assertEquals("Head Sha", toInternalRunResult.getCommitSha());
    assertEquals("Status", toInternalRunResult.getStatus());
    assertEquals("https://example.org/example", toInternalRunResult.getRunUrl());
    assertNull(toInternalRunResult.getCommitMessage());
    assertEquals(1L, toInternalRunResult.getRunId().longValue());
    assertTrue(toInternalRunResult.getPathsChangedSinceThisRun().isEmpty());
    Instant expectedRunStarted = runStartedAt.EPOCH;
    assertSame(expectedRunStarted, toInternalRunResult.getRunStarted());
  }

  /**
   * Test Run {@link Run#toInternalRun()}.
   * <ul>
   *   <li>Then return CommitSha is {@code Head Sha}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Run#toInternalRun()}
   */
  @Test
  @DisplayName("Test Run toInternalRun(); then return CommitSha is 'Head Sha'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Run.toInternalRun()"})
  void testRunToInternalRun_thenReturnCommitShaIsHeadSha() {
    // Arrange
    Instant runStartedAt = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    GitHubWorkflowRun actualToInternalRunResult = (new Run(1L, "Head Sha", "Status", "Conclusion",
        "https://example.org/example", runStartedAt, new Commit("Not all who wander are lost"))).toInternalRun();

    // Assert
    assertEquals("Conclusion", actualToInternalRunResult.getConclusion());
    assertEquals("Head Sha", actualToInternalRunResult.getCommitSha());
    assertEquals("Not all who wander are lost", actualToInternalRunResult.getCommitMessage());
    assertEquals("Status", actualToInternalRunResult.getStatus());
    assertEquals("https://example.org/example", actualToInternalRunResult.getRunUrl());
    Instant runStarted = actualToInternalRunResult.getRunStarted();
    assertEquals(0, runStarted.getNano());
    assertEquals(0L, runStarted.getEpochSecond());
    assertEquals(1L, actualToInternalRunResult.getRunId().longValue());
    assertTrue(actualToInternalRunResult.getPathsChangedSinceThisRun().isEmpty());
  }

  /**
   * Test Run {@link Run#toInternalRun()}.
   * <ul>
   *   <li>Then return CommitSha is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Run#toInternalRun()}
   */
  @Test
  @DisplayName("Test Run toInternalRun(); then return CommitSha is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubWorkflowRun Run.toInternalRun()"})
  void testRunToInternalRun_thenReturnCommitShaIsNull() {
    // Arrange
    Instant runStartedAt = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    GitHubWorkflowRun actualToInternalRunResult = (new Run(1L, "", "Status", "Conclusion",
        "https://example.org/example", runStartedAt, new Commit("Not all who wander are lost"))).toInternalRun();

    // Assert
    assertEquals("Conclusion", actualToInternalRunResult.getConclusion());
    assertEquals("Not all who wander are lost", actualToInternalRunResult.getCommitMessage());
    assertEquals("Status", actualToInternalRunResult.getStatus());
    assertEquals("https://example.org/example", actualToInternalRunResult.getRunUrl());
    assertNull(actualToInternalRunResult.getCommitSha());
    Instant runStarted = actualToInternalRunResult.getRunStarted();
    assertEquals(0, runStarted.getNano());
    assertEquals(0L, runStarted.getEpochSecond());
    assertEquals(1L, actualToInternalRunResult.getRunId().longValue());
    assertTrue(actualToInternalRunResult.getPathsChangedSinceThisRun().isEmpty());
  }
}
