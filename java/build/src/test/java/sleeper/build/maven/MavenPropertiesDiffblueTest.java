package sleeper.build.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MavenPropertiesDiffblueTest {
  /**
   * Test {@link MavenProperties#resolve(String, Map)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then return {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenProperties#resolve(String, Map)}
   */
  @Test
  @DisplayName("Test resolve(String, Map); when 'String'; then return 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String MavenProperties.resolve(String, Map)"})
  void testResolve_whenString_thenReturnString() {
    // Arrange, Act and Assert
    assertEquals("String", MavenProperties.resolve("String", new HashMap<>()));
  }
}
