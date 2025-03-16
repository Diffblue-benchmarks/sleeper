package sleeper.build.github.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.github.GitHubRunToHead;
import sleeper.build.github.GitHubWorkflowRun;
import sleeper.build.github.api.GitHubCompareResponse.File;

class GitHubCompareResponseDiffblueTest {
  /**
   * Test {@link GitHubCompareResponse#toRunToHead(GitHubWorkflowRun)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link File#File(String)} with filename is {@code foo.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubCompareResponse#toRunToHead(GitHubWorkflowRun)}
   */
  @Test
  @DisplayName("Test toRunToHead(GitHubWorkflowRun); given ArrayList() add File(String) with filename is 'foo.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubRunToHead GitHubCompareResponse.toRunToHead(GitHubWorkflowRun)"})
  void testToRunToHead_givenArrayListAddFileWithFilenameIsFooTxt() {
    // Arrange
    ArrayList<File> files = new ArrayList<>();
    files.add(new File("foo.txt"));
    GitHubWorkflowRun run = mock(GitHubWorkflowRun.class);

    // Act
    GitHubRunToHead actualToRunToHeadResult = (new GitHubCompareResponse(1, 1, files)).toRunToHead(run);

    // Assert
    assertFalse(actualToRunToHeadResult.isRunForHeadOrBehind());
    assertSame(run, actualToRunToHeadResult.getRun());
  }

  /**
   * Test {@link GitHubCompareResponse#toRunToHead(GitHubWorkflowRun)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link File#File(String)} with filename is {@code foo.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubCompareResponse#toRunToHead(GitHubWorkflowRun)}
   */
  @Test
  @DisplayName("Test toRunToHead(GitHubWorkflowRun); given ArrayList() add File(String) with filename is 'foo.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubRunToHead GitHubCompareResponse.toRunToHead(GitHubWorkflowRun)"})
  void testToRunToHead_givenArrayListAddFileWithFilenameIsFooTxt2() {
    // Arrange
    ArrayList<File> files = new ArrayList<>();
    files.add(new File("foo.txt"));
    files.add(new File("foo.txt"));
    GitHubWorkflowRun run = mock(GitHubWorkflowRun.class);

    // Act
    GitHubRunToHead actualToRunToHeadResult = (new GitHubCompareResponse(1, 1, files)).toRunToHead(run);

    // Assert
    assertFalse(actualToRunToHeadResult.isRunForHeadOrBehind());
    assertSame(run, actualToRunToHeadResult.getRun());
  }

  /**
   * Test {@link GitHubCompareResponse#toRunToHead(GitHubWorkflowRun)}.
   * <ul>
   *   <li>When {@link GitHubWorkflowRun}.</li>
   *   <li>Then return not RunForHeadOrBehind.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubCompareResponse#toRunToHead(GitHubWorkflowRun)}
   */
  @Test
  @DisplayName("Test toRunToHead(GitHubWorkflowRun); when GitHubWorkflowRun; then return not RunForHeadOrBehind")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubRunToHead GitHubCompareResponse.toRunToHead(GitHubWorkflowRun)"})
  void testToRunToHead_whenGitHubWorkflowRun_thenReturnNotRunForHeadOrBehind() {
    // Arrange
    GitHubWorkflowRun run = mock(GitHubWorkflowRun.class);

    // Act
    GitHubRunToHead actualToRunToHeadResult = (new GitHubCompareResponse(1, 1, new ArrayList<>())).toRunToHead(run);

    // Assert
    assertFalse(actualToRunToHeadResult.isRunForHeadOrBehind());
    assertSame(run, actualToRunToHeadResult.getRun());
  }
}
