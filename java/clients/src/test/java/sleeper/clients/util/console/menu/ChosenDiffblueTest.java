package sleeper.clients.util.console.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.util.console.UserExitedException;

class ChosenDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Chosen#Chosen(String, ConsoleChoice)}
   *   <li>{@link Chosen#getEntered()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Chosen.<init>(String, ConsoleChoice)", "String Chosen.getEntered()"})
  void testGettersAndSetters() {
    // Arrange and Act
    Chosen<ConsoleChoice> actualChosen = new Chosen<>("Entered", mock(ConsoleChoice.class));

    // Assert
    assertEquals("Entered", actualChosen.getEntered());
  }

  /**
   * Test {@link Chosen#nothing(String)}.
   * <p>
   * Method under test: {@link Chosen#nothing(String)}
   */
  @Test
  @DisplayName("Test nothing(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Chosen Chosen.nothing(String)"})
  void testNothing() {
    // Arrange and Act
    Chosen<ConsoleChoice> actualNothingResult = Chosen.nothing("Entered");

    // Assert
    assertEquals("Entered", actualNothingResult.getEntered());
    assertFalse(actualNothingResult.getChoice().isPresent());
  }

  /**
   * Test {@link Chosen#getChoice()}.
   * <p>
   * Method under test: {@link Chosen#getChoice()}
   */
  @Test
  @DisplayName("Test getChoice()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional Chosen.getChoice()"})
  void testGetChoice() {
    // Arrange
    Chosen<ConsoleChoice> nothingResult = Chosen.nothing("Entered");

    // Act and Assert
    assertFalse(nothingResult.getChoice().isPresent());
  }

  /**
   * Test {@link Chosen#chooseUntilSomethingEntered(Supplier)}.
   * <ul>
   *   <li>Then calls {@link ChooseOneTestBase#chooseTestOption()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Chosen#chooseUntilSomethingEntered(Supplier)}
   */
  @Test
  @DisplayName("Test chooseUntilSomethingEntered(Supplier); then calls chooseTestOption()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Chosen Chosen.chooseUntilSomethingEntered(Supplier)"})
  void testChooseUntilSomethingEntered_thenCallsChooseTestOption() throws UserExitedException {
    // Arrange
    Chosen<ConsoleChoice> nothingResult = Chosen.nothing("");
    ChooseOneTest chooseOneTest = mock(ChooseOneTest.class);
    Chosen<ConsoleChoice> nothingResult2 = Chosen.nothing("Entered");
    when(chooseOneTest.chooseTestOption()).thenReturn(nothingResult2);

    // Act
    Chosen<ConsoleChoice> actualChooseUntilSomethingEnteredResult = nothingResult
        .chooseUntilSomethingEntered(chooseOneTest::chooseTestOption);

    // Assert
    verify(chooseOneTest).chooseTestOption();
    assertSame(nothingResult2, actualChooseUntilSomethingEnteredResult);
  }

  /**
   * Test {@link Chosen#chooseUntilSomethingEntered(Supplier)}.
   * <ul>
   *   <li>When {@link ChooseOneTest}.</li>
   *   <li>Then return nothing {@code Entered}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Chosen#chooseUntilSomethingEntered(Supplier)}
   */
  @Test
  @DisplayName("Test chooseUntilSomethingEntered(Supplier); when ChooseOneTest; then return nothing 'Entered'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Chosen Chosen.chooseUntilSomethingEntered(Supplier)"})
  void testChooseUntilSomethingEntered_whenChooseOneTest_thenReturnNothingEntered() throws UserExitedException {
    // Arrange
    Chosen<ConsoleChoice> nothingResult = Chosen.nothing("Entered");

    // Act and Assert
    assertSame(nothingResult, nothingResult.chooseUntilSomethingEntered(mock(ChooseOneTest.class)::chooseTestOption));
  }
}
