package sleeper.clients.status.report.ingest.batcher.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.ingest.batcher.BatcherQuery;
import sleeper.clients.status.report.ingest.batcher.BatcherQuery.Type;
import sleeper.clients.util.console.ConsoleInput;

class BatcherQueryPromptDiffblueTest {
  /**
   * Test {@link BatcherQueryPrompt#from(ConsoleInput)}.
   * <ul>
   *   <li>Given {@code a}.</li>
   *   <li>When {@link ConsoleInput} {@link ConsoleInput#promptLine(String)} return {@code a}.</li>
   *   <li>Then return {@link AllFilesQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BatcherQueryPrompt#from(ConsoleInput)}
   */
  @Test
  @DisplayName("Test from(ConsoleInput); given 'a'; when ConsoleInput promptLine(String) return 'a'; then return AllFilesQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BatcherQuery BatcherQueryPrompt.from(ConsoleInput)"})
  void testFrom_givenA_whenConsoleInputPromptLineReturnA_thenReturnAllFilesQuery() {
    // Arrange
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("a");

    // Act
    BatcherQuery actualFromResult = BatcherQueryPrompt.from(in);

    // Assert
    verify(in).promptLine(eq("All (a) or Pending (p) query? "));
    assertTrue(actualFromResult instanceof AllFilesQuery);
    assertEquals(Type.ALL, actualFromResult.getType());
  }

  /**
   * Test {@link BatcherQueryPrompt#from(ConsoleInput)}.
   * <ul>
   *   <li>Given {@code p}.</li>
   *   <li>When {@link ConsoleInput} {@link ConsoleInput#promptLine(String)} return {@code p}.</li>
   *   <li>Then return {@link PendingFilesQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BatcherQueryPrompt#from(ConsoleInput)}
   */
  @Test
  @DisplayName("Test from(ConsoleInput); given 'p'; when ConsoleInput promptLine(String) return 'p'; then return PendingFilesQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BatcherQuery BatcherQueryPrompt.from(ConsoleInput)"})
  void testFrom_givenP_whenConsoleInputPromptLineReturnP_thenReturnPendingFilesQuery() {
    // Arrange
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("p");

    // Act
    BatcherQuery actualFromResult = BatcherQueryPrompt.from(in);

    // Assert
    verify(in).promptLine(eq("All (a) or Pending (p) query? "));
    assertTrue(actualFromResult instanceof PendingFilesQuery);
    assertEquals(Type.PENDING, actualFromResult.getType());
  }
}
