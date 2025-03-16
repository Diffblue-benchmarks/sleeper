package sleeper.clients.util.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UserExitedExceptionDiffblueTest {
  /**
   * Test new {@link UserExitedException} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link UserExitedException}
   */
  @Test
  @DisplayName("Test new UserExitedException (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UserExitedException.<init>()"})
  void testNewUserExitedException() {
    // Arrange and Act
    UserExitedException actualUserExitedException = new UserExitedException();

    // Assert
    assertEquals("User chose to exit", actualUserExitedException.getMessage());
    assertNull(actualUserExitedException.getCause());
    assertEquals(0, actualUserExitedException.getSuppressed().length);
  }
}
