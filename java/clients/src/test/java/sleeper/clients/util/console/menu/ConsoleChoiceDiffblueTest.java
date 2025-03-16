package sleeper.clients.util.console.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ConsoleChoiceDiffblueTest {
  /**
   * Test {@link ConsoleChoice#describedAs(String)}.
   * <p>
   * Method under test: {@link ConsoleChoice#describedAs(String)}
   */
  @Test
  @DisplayName("Test describedAs(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ConsoleChoice ConsoleChoice.describedAs(String)"})
  void testDescribedAs() {
    // Arrange, Act and Assert
    assertEquals("The characteristics of someone or something",
        ConsoleChoice.describedAs("The characteristics of someone or something").getDescription());
  }
}
