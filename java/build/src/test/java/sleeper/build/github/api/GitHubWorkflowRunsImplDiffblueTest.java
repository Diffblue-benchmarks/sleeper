package sleeper.build.github.api;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GitHubWorkflowRunsImplDiffblueTest {
  /**
   * Test {@link GitHubWorkflowRunsImpl#close()}.
   * <ul>
   *   <li>Given {@link GitHubApi} {@link GitHubApi#close()} does nothing.</li>
   *   <li>Then calls {@link GitHubApi#close()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubWorkflowRunsImpl#close()}
   */
  @Test
  @DisplayName("Test close(); given GitHubApi close() does nothing; then calls close()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GitHubWorkflowRunsImpl.close()"})
  void testClose_givenGitHubApiCloseDoesNothing_thenCallsClose() {
    // Arrange
    GitHubApi api = mock(GitHubApi.class);
    doNothing().when(api).close();

    // Act
    (new GitHubWorkflowRunsImpl(api)).close();

    // Assert
    verify(api).close();
  }
}
