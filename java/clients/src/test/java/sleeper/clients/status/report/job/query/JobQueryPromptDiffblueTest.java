package sleeper.clients.status.report.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.clients.util.console.ConsoleInput;
import sleeper.core.table.TableStatus;

class JobQueryPromptDiffblueTest {
  /**
   * Test {@link JobQueryPrompt#from(TableStatus, Clock, ConsoleInput, Map)}.
   * <ul>
   *   <li>Given {@code a}.</li>
   *   <li>When {@link ConsoleInput} {@link ConsoleInput#promptLine(String)} return {@code a}.</li>
   *   <li>Then return {@link AllJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQueryPrompt#from(TableStatus, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Clock, ConsoleInput, Map); given 'a'; when ConsoleInput promptLine(String) return 'a'; then return AllJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQueryPrompt.from(TableStatus, Clock, ConsoleInput, Map)"})
  void testFrom_givenA_whenConsoleInputPromptLineReturnA_thenReturnAllJobsQuery() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("a");

    // Act
    JobQuery actualFromResult = JobQueryPrompt.from(table, clock, in, new HashMap<>());

    // Assert
    verify(in).promptLine(eq("All (a), Detailed (d), range (r), or unfinished (u) query? "));
    assertTrue(actualFromResult instanceof AllJobsQuery);
    assertEquals(Type.ALL, actualFromResult.getType());
  }

  /**
   * Test {@link JobQueryPrompt#from(TableStatus, Clock, ConsoleInput, Map)}.
   * <ul>
   *   <li>Given {@code d}.</li>
   *   <li>When {@link ConsoleInput} {@link ConsoleInput#promptLine(String)} return {@code d}.</li>
   *   <li>Then return {@link DetailedJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQueryPrompt#from(TableStatus, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Clock, ConsoleInput, Map); given 'd'; when ConsoleInput promptLine(String) return 'd'; then return DetailedJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQueryPrompt.from(TableStatus, Clock, ConsoleInput, Map)"})
  void testFrom_givenD_whenConsoleInputPromptLineReturnD_thenReturnDetailedJobsQuery() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("d");

    // Act
    JobQuery actualFromResult = JobQueryPrompt.from(table, clock, in, new HashMap<>());

    // Assert
    verify(in, atLeast(1)).promptLine(Mockito.<String>any());
    assertTrue(actualFromResult instanceof DetailedJobsQuery);
    assertEquals(Type.DETAILED, actualFromResult.getType());
  }

  /**
   * Test {@link JobQueryPrompt#from(TableStatus, Clock, ConsoleInput, Map)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQueryPrompt#from(TableStatus, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Clock, ConsoleInput, Map); given empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQueryPrompt.from(TableStatus, Clock, ConsoleInput, Map)"})
  void testFrom_givenEmptyString_thenReturnNull() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("");

    // Act
    JobQuery actualFromResult = JobQueryPrompt.from(table, clock, in, new HashMap<>());

    // Assert
    verify(in).promptLine(eq("All (a), Detailed (d), range (r), or unfinished (u) query? "));
    assertNull(actualFromResult);
  }

  /**
   * Test {@link JobQueryPrompt#from(TableStatus, Clock, ConsoleInput, Map)}.
   * <ul>
   *   <li>Given {@code u}.</li>
   *   <li>When {@link ConsoleInput} {@link ConsoleInput#promptLine(String)} return {@code u}.</li>
   *   <li>Then return {@link UnfinishedJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQueryPrompt#from(TableStatus, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Clock, ConsoleInput, Map); given 'u'; when ConsoleInput promptLine(String) return 'u'; then return UnfinishedJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQueryPrompt.from(TableStatus, Clock, ConsoleInput, Map)"})
  void testFrom_givenU_whenConsoleInputPromptLineReturnU_thenReturnUnfinishedJobsQuery() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("u");

    // Act
    JobQuery actualFromResult = JobQueryPrompt.from(table, clock, in, new HashMap<>());

    // Assert
    verify(in).promptLine(eq("All (a), Detailed (d), range (r), or unfinished (u) query? "));
    assertTrue(actualFromResult instanceof UnfinishedJobsQuery);
    assertEquals(Type.UNFINISHED, actualFromResult.getType());
  }
}
