package sleeper.clients.util.console.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.util.console.UserExitedException;

class MenuOptionDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MenuOption#MenuOption(String, MenuOperation)}
   *   <li>{@link MenuOption#getDescription()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MenuOption.<init>(String, MenuOperation)", "String MenuOption.getDescription()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("The characteristics of someone or something",
        (new MenuOption("The characteristics of someone or something", mock(MenuOperation.class))).getDescription());
  }

  /**
   * Test {@link MenuOption#run()}.
   * <ul>
   *   <li>Given {@link MenuOperation} {@link MenuOperation#run()} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link MenuOption#run()}
   */
  @Test
  @DisplayName("Test run(); given MenuOperation run() does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MenuOption.run()"})
  void testRun_givenMenuOperationRunDoesNothing() throws InterruptedException, UserExitedException {
    // Arrange
    MenuOperation operation = mock(MenuOperation.class);
    doNothing().when(operation).run();

    // Act
    (new MenuOption("The characteristics of someone or something", operation)).run();

    // Assert
    verify(operation).run();
  }

  /**
   * Test {@link MenuOption#run()}.
   * <ul>
   *   <li>Then throw {@link UserExitedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MenuOption#run()}
   */
  @Test
  @DisplayName("Test run(); then throw UserExitedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MenuOption.run()"})
  void testRun_thenThrowUserExitedException() throws InterruptedException, UserExitedException {
    // Arrange
    MenuOperation operation = mock(MenuOperation.class);
    doThrow(new UserExitedException()).when(operation).run();

    // Act and Assert
    assertThrows(UserExitedException.class,
        () -> (new MenuOption("The characteristics of someone or something", operation)).run());
    verify(operation).run();
  }
}
