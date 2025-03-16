package sleeper.clients.util.console.menu;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.util.console.ConsoleInput;
import sleeper.clients.util.console.ConsoleOutput;
import sleeper.clients.util.console.UserExitedException;

class ChooseOneDiffblueTest {
  /**
   * Test {@link ChooseOne#chooseFrom(ConsoleChoice[])} with {@code ConsoleChoice[]}.
   * <ul>
   *   <li>Then throw {@link UserExitedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChooseOne#chooseFrom(ConsoleChoice[])}
   */
  @Test
  @DisplayName("Test chooseFrom(ConsoleChoice[]) with 'ConsoleChoice[]'; then throw UserExitedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.clients.util.console.menu.Chosen ChooseOne.chooseFrom(ConsoleChoice[])"})
  void testChooseFromWithConsoleChoice_thenThrowUserExitedException() throws UserExitedException {
    // Arrange
    ConsoleOutput out = new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1)));
    ChooseOne chooseOne = new ChooseOne(out, new ConsoleInput(null));
    ConsoleChoice consoleChoice = mock(ConsoleChoice.class);
    when(consoleChoice.getDescription()).thenThrow(new UserExitedException());

    // Act and Assert
    assertThrows(UserExitedException.class, () -> chooseOne.chooseFrom(consoleChoice, mock(ConsoleChoice.class)));
    verify(consoleChoice).getDescription();
  }

  /**
   * Test {@link ChooseOne#chooseWithMessageFrom(String, ConsoleChoice[])} with {@code String}, {@code ConsoleChoice[]}.
   * <ul>
   *   <li>Then throw {@link UserExitedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChooseOne#chooseWithMessageFrom(String, ConsoleChoice[])}
   */
  @Test
  @DisplayName("Test chooseWithMessageFrom(String, ConsoleChoice[]) with 'String', 'ConsoleChoice[]'; then throw UserExitedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.clients.util.console.menu.Chosen ChooseOne.chooseWithMessageFrom(String, ConsoleChoice[])"})
  void testChooseWithMessageFromWithStringConsoleChoice_thenThrowUserExitedException() throws UserExitedException {
    // Arrange
    ConsoleOutput out = new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1)));
    ChooseOne chooseOne = new ChooseOne(out, new ConsoleInput(null));
    ConsoleChoice consoleChoice = mock(ConsoleChoice.class);
    when(consoleChoice.getDescription()).thenThrow(new UserExitedException());

    // Act and Assert
    assertThrows(UserExitedException.class,
        () -> chooseOne.chooseWithMessageFrom("Not all who wander are lost", consoleChoice, mock(ConsoleChoice.class)));
    verify(consoleChoice).getDescription();
  }
}
