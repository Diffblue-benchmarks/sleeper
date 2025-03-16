package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ClientsGsonConfigDiffblueTest {
  /**
   * Test {@link ClientsGsonConfig#standardBuilder()}.
   * <p>
   * Method under test: {@link ClientsGsonConfig#standardBuilder()}
   */
  @Test
  @DisplayName("Test standardBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GsonBuilder ClientsGsonConfig.standardBuilder()"})
  void testStandardBuilder() {
    // Arrange, Act and Assert
    Gson createResult = ClientsGsonConfig.standardBuilder().create();
    assertFalse(createResult.serializeNulls());
    assertTrue(createResult.htmlSafe());
  }
}
