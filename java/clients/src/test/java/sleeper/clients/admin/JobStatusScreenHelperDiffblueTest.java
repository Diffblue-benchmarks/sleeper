package sleeper.clients.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.util.console.ConsoleInput;

class JobStatusScreenHelperDiffblueTest {
  /**
   * Test {@link JobStatusScreenHelper#promptForJobId(ConsoleInput)}.
   * <ul>
   *   <li>Given {@code Prompt Line}.</li>
   *   <li>Then return {@code Prompt Line}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusScreenHelper#promptForJobId(ConsoleInput)}
   */
  @Test
  @DisplayName("Test promptForJobId(ConsoleInput); given 'Prompt Line'; then return 'Prompt Line'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JobStatusScreenHelper.promptForJobId(ConsoleInput)"})
  void testPromptForJobId_givenPromptLine_thenReturnPromptLine() {
    // Arrange
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("Prompt Line");

    // Act
    String actualPromptForJobIdResult = JobStatusScreenHelper.promptForJobId(in);

    // Assert
    verify(in).promptLine(eq("Enter the ID of the job you want to view the details for: "));
    assertEquals("Prompt Line", actualPromptForJobIdResult);
  }

  /**
   * Test {@link JobStatusScreenHelper#promptForRange(ConsoleInput)}.
   * <ul>
   *   <li>Given {@code Prompt Line}.</li>
   *   <li>Then return {@code Prompt Line,Prompt Line}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusScreenHelper#promptForRange(ConsoleInput)}
   */
  @Test
  @DisplayName("Test promptForRange(ConsoleInput); given 'Prompt Line'; then return 'Prompt Line,Prompt Line'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JobStatusScreenHelper.promptForRange(ConsoleInput)"})
  void testPromptForRange_givenPromptLine_thenReturnPromptLinePromptLine() {
    // Arrange
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("Prompt Line");

    // Act
    String actualPromptForRangeResult = JobStatusScreenHelper.promptForRange(in);

    // Assert
    verify(in, atLeast(1)).promptLine(Mockito.<String>any());
    assertEquals("Prompt Line,Prompt Line", actualPromptForRangeResult);
  }
}
