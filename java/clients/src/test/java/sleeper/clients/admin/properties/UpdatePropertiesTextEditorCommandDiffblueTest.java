package sleeper.clients.admin.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UpdatePropertiesTextEditorCommandDiffblueTest {
  /**
   * Test {@link UpdatePropertiesTextEditorCommand#UpdatePropertiesTextEditorCommand(Function)}.
   * <p>
   * Method under test: {@link UpdatePropertiesTextEditorCommand#UpdatePropertiesTextEditorCommand(Function)}
   */
  @Test
  @DisplayName("Test new UpdatePropertiesTextEditorCommand(Function)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UpdatePropertiesTextEditorCommand.<init>(Function)"})
  void testNewUpdatePropertiesTextEditorCommand() {
    // Arrange, Act and Assert
    assertEquals("nano", (new UpdatePropertiesTextEditorCommand(mock(Function.class))).getCommand());
  }

  /**
   * Test {@link UpdatePropertiesTextEditorCommand#UpdatePropertiesTextEditorCommand()}.
   * <p>
   * Method under test: {@link UpdatePropertiesTextEditorCommand#UpdatePropertiesTextEditorCommand()}
   */
  @Test
  @DisplayName("Test new UpdatePropertiesTextEditorCommand()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UpdatePropertiesTextEditorCommand.<init>()"})
  void testNewUpdatePropertiesTextEditorCommand2() {
    // Arrange, Act and Assert
    assertEquals("nano", (new UpdatePropertiesTextEditorCommand()).getCommand());
  }

  /**
   * Test {@link UpdatePropertiesTextEditorCommand#getCommand()}.
   * <ul>
   *   <li>Given {@link Function} {@link Function#apply(Object)} return {@code Apply}.</li>
   *   <li>Then return {@code Apply}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UpdatePropertiesTextEditorCommand#getCommand()}
   */
  @Test
  @DisplayName("Test getCommand(); given Function apply(Object) return 'Apply'; then return 'Apply'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String UpdatePropertiesTextEditorCommand.getCommand()"})
  void testGetCommand_givenFunctionApplyReturnApply_thenReturnApply() {
    // Arrange
    Function<String, String> readEnvironmentVariable = mock(Function.class);
    when(readEnvironmentVariable.apply(Mockito.<String>any())).thenReturn("Apply");

    // Act
    String actualCommand = (new UpdatePropertiesTextEditorCommand(readEnvironmentVariable)).getCommand();

    // Assert
    verify(readEnvironmentVariable).apply(eq("EDITOR"));
    assertEquals("Apply", actualCommand);
  }

  /**
   * Test {@link UpdatePropertiesTextEditorCommand#getCommand()}.
   * <ul>
   *   <li>Given {@link Function} {@link Function#apply(Object)} return empty string.</li>
   *   <li>Then return {@code nano}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UpdatePropertiesTextEditorCommand#getCommand()}
   */
  @Test
  @DisplayName("Test getCommand(); given Function apply(Object) return empty string; then return 'nano'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String UpdatePropertiesTextEditorCommand.getCommand()"})
  void testGetCommand_givenFunctionApplyReturnEmptyString_thenReturnNano() {
    // Arrange
    Function<String, String> readEnvironmentVariable = mock(Function.class);
    when(readEnvironmentVariable.apply(Mockito.<String>any())).thenReturn("");

    // Act
    String actualCommand = (new UpdatePropertiesTextEditorCommand(readEnvironmentVariable)).getCommand();

    // Assert
    verify(readEnvironmentVariable).apply(eq("EDITOR"));
    assertEquals("nano", actualCommand);
  }

  /**
   * Test {@link UpdatePropertiesTextEditorCommand#getCommand()}.
   * <ul>
   *   <li>Given {@link UpdatePropertiesTextEditorCommand#UpdatePropertiesTextEditorCommand()}.</li>
   *   <li>Then return {@code nano}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UpdatePropertiesTextEditorCommand#getCommand()}
   */
  @Test
  @DisplayName("Test getCommand(); given UpdatePropertiesTextEditorCommand(); then return 'nano'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String UpdatePropertiesTextEditorCommand.getCommand()"})
  void testGetCommand_givenUpdatePropertiesTextEditorCommand_thenReturnNano() {
    // Arrange, Act and Assert
    assertEquals("nano", (new UpdatePropertiesTextEditorCommand()).getCommand());
  }
}
