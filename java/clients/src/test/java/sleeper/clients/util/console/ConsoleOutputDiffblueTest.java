package sleeper.clients.util.console;

import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ConsoleOutputDiffblueTest {
  /**
   * Test {@link ConsoleOutput#printStream()}.
   * <p>
   * Method under test: {@link ConsoleOutput#printStream()}
   */
  @Test
  @DisplayName("Test printStream()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PrintStream ConsoleOutput.printStream()"})
  void testPrintStream() {
    // Arrange
    PrintStream out = new PrintStream(new ByteArrayOutputStream(1));

    // Act and Assert
    assertSame(out, (new ConsoleOutput(out)).printStream());
  }
}
