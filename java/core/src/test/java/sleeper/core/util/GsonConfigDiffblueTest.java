package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GsonConfigDiffblueTest {
  /**
   * Test {@link GsonConfig#standardBuilder()}.
   * <p>
   * Method under test: {@link GsonConfig#standardBuilder()}
   */
  @Test
  @DisplayName("Test standardBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GsonBuilder GsonConfig.standardBuilder()"})
  void testStandardBuilder() {
    // Arrange, Act and Assert
    Gson createResult = GsonConfig.standardBuilder().create();
    assertFalse(createResult.serializeNulls());
    assertTrue(createResult.htmlSafe());
  }
}
