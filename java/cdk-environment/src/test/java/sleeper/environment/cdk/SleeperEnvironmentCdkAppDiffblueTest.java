package sleeper.environment.cdk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SleeperEnvironmentCdkAppDiffblueTest {
  /**
   * Test {@link SleeperEnvironmentCdkApp#getStackNamesForEnvironment(String)}.
   * <p>
   * Method under test: {@link SleeperEnvironmentCdkApp#getStackNamesForEnvironment(String)}
   */
  @Test
  @DisplayName("Test getStackNamesForEnvironment(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperEnvironmentCdkApp.getStackNamesForEnvironment(String)"})
  void testGetStackNamesForEnvironment() {
    // Arrange and Act
    List<String> actualStackNamesForEnvironment = SleeperEnvironmentCdkApp.getStackNamesForEnvironment("42");

    // Assert
    assertEquals(1, actualStackNamesForEnvironment.size());
    assertEquals("42-SleeperEnvironment", actualStackNamesForEnvironment.get(0));
  }
}
