package sleeper.build.github.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class InstallationAccessTokenResponseDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link InstallationAccessTokenResponse#InstallationAccessTokenResponse(String)}
   *   <li>{@link InstallationAccessTokenResponse#getToken()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InstallationAccessTokenResponse.<init>(String)",
      "String InstallationAccessTokenResponse.getToken()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("ABC123", (new InstallationAccessTokenResponse("ABC123")).getToken());
  }
}
