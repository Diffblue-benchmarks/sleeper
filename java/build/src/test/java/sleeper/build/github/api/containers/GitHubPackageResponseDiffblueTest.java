package sleeper.build.github.api.containers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GitHubPackageResponseDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubPackageResponse#GitHubPackageResponse(String)}
   *   <li>{@link GitHubPackageResponse#getName()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GitHubPackageResponse.<init>(String)", "String GitHubPackageResponse.getName()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("Name", (new GitHubPackageResponse("Name")).getName());
  }
}
