package sleeper.clients.admin.properties;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.util.console.ConsoleInput;
import sleeper.clients.util.console.ConsoleOutput;

class PropertyGroupSelectHelperDiffblueTest {
  /**
   * Test {@link PropertyGroupSelectHelper#selectPropertyGroup()}.
   * <ul>
   *   <li>Given {@link ConsoleInput} {@link ConsoleInput#promptLine(String)} return {@code 42}.</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroupSelectHelper#selectPropertyGroup()}
   */
  @Test
  @DisplayName("Test selectPropertyGroup(); given ConsoleInput promptLine(String) return '42'; then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional PropertyGroupSelectHelper.selectPropertyGroup()"})
  void testSelectPropertyGroup_givenConsoleInputPromptLineReturn42_thenReturnNotPresent() {
    // Arrange
    ConsoleOutput out = mock(ConsoleOutput.class);
    doNothing().when(out).clearScreen(Mockito.<String>any());
    doNothing().when(out).printf(Mockito.<String>any(), isA(Object[].class));
    doNothing().when(out).println();
    doNothing().when(out).println(Mockito.<String>any());
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("42");

    // Act
    Optional<PropertyGroupWithCategory> actualSelectPropertyGroupResult = (new PropertyGroupSelectHelper(out, in))
        .selectPropertyGroup();

    // Assert
    verify(in).promptLine(eq("Input: "));
    verify(out).clearScreen(eq(""));
    verify(out, atLeast(1)).printf(eq("[%s] %s%n"), isA(Object[].class));
    verify(out).println();
    verify(out, atLeast(1)).println(Mockito.<String>any());
    assertFalse(actualSelectPropertyGroupResult.isPresent());
  }

  /**
   * Test {@link PropertyGroupSelectHelper#selectPropertyGroup()}.
   * <ul>
   *   <li>Given {@link ConsoleInput} {@link ConsoleInput#promptLine(String)} return {@code Prompt Line}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroupSelectHelper#selectPropertyGroup()}
   */
  @Test
  @DisplayName("Test selectPropertyGroup(); given ConsoleInput promptLine(String) return 'Prompt Line'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional PropertyGroupSelectHelper.selectPropertyGroup()"})
  void testSelectPropertyGroup_givenConsoleInputPromptLineReturnPromptLine() {
    // Arrange
    ConsoleOutput out = mock(ConsoleOutput.class);
    doNothing().when(out).clearScreen(Mockito.<String>any());
    doNothing().when(out).printf(Mockito.<String>any(), isA(Object[].class));
    doNothing().when(out).println();
    doNothing().when(out).println(Mockito.<String>any());
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("Prompt Line");

    // Act
    Optional<PropertyGroupWithCategory> actualSelectPropertyGroupResult = (new PropertyGroupSelectHelper(out, in))
        .selectPropertyGroup();

    // Assert
    verify(in).promptLine(eq("Input: "));
    verify(out).clearScreen(eq(""));
    verify(out, atLeast(1)).printf(eq("[%s] %s%n"), isA(Object[].class));
    verify(out).println();
    verify(out, atLeast(1)).println(Mockito.<String>any());
    assertFalse(actualSelectPropertyGroupResult.isPresent());
  }
}
