package sleeper.clients.admin;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.util.console.ConsoleInput;
import sleeper.clients.util.console.ConsoleOutput;
import sleeper.core.table.InMemoryTableIndex;

class TableNamesReportDiffblueTest {
  /**
   * Test {@link TableNamesReport#print()}.
   * <ul>
   *   <li>Given {@link ConsoleInput} {@link ConsoleInput#waitForLine()} does nothing.</li>
   *   <li>Then calls {@link ConsoleInput#waitForLine()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableNamesReport#print()}
   */
  @Test
  @DisplayName("Test print(); given ConsoleInput waitForLine() does nothing; then calls waitForLine()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TableNamesReport.print()"})
  void testPrint_givenConsoleInputWaitForLineDoesNothing_thenCallsWaitForLine() {
    // Arrange
    ConsoleOutput out = mock(ConsoleOutput.class);
    doNothing().when(out).println(Mockito.<String>any());
    ConsoleInput in = mock(ConsoleInput.class);
    doNothing().when(in).waitForLine();

    // Act
    (new TableNamesReport(out, in, new InMemoryTableIndex())).print();

    // Assert
    verify(in).waitForLine();
    verify(out, atLeast(1)).println(Mockito.<String>any());
  }
}
