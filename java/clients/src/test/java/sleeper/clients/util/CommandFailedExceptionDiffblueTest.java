package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CommandFailedExceptionDiffblueTest {
  /**
   * Test {@link CommandFailedException#CommandFailedException(CommandPipeline, int)}.
   * <p>
   * Method under test: {@link CommandFailedException#CommandFailedException(CommandPipeline, int)}
   */
  @Test
  @DisplayName("Test new CommandFailedException(CommandPipeline, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CommandFailedException.<init>(CommandPipeline, int)"})
  void testNewCommandFailedException() {
    // Arrange
    CommandPipeline command = CommandPipeline.pipeline(Command.command("Command"));

    // Act
    CommandFailedException actualCommandFailedException = new CommandFailedException(command, 1);

    // Assert
    assertEquals("Command failed with exit code 1: [Command]", actualCommandFailedException.getLocalizedMessage());
    assertEquals("Command failed with exit code 1: [Command]", actualCommandFailedException.getMessage());
    assertNull(actualCommandFailedException.getCause());
    assertEquals(0, actualCommandFailedException.getSuppressed().length);
    assertEquals(1, actualCommandFailedException.getExitCode());
    assertSame(command, actualCommandFailedException.getCommand());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CommandFailedException#getCommand()}
   *   <li>{@link CommandFailedException#getExitCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CommandPipeline CommandFailedException.getCommand()", "int CommandFailedException.getExitCode()"})
  void testGettersAndSetters() {
    // Arrange
    CommandPipeline command = CommandPipeline.pipeline(Command.command("Command"));
    CommandFailedException commandFailedException = new CommandFailedException(command, 1);

    // Act
    CommandPipeline actualCommand = commandFailedException.getCommand();

    // Assert
    assertEquals(1, commandFailedException.getExitCode());
    assertSame(command, actualCommand);
  }
}
