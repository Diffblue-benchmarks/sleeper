package sleeper.build.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PrintUtilsDiffblueTest {
  /**
   * Test {@link PrintUtils#printLines(Consumer)}.
   * <p>
   * Method under test: {@link PrintUtils#printLines(Consumer)}
   */
  @Test
  @DisplayName("Test printLines(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PrintUtils.printLines(Consumer)"})
  void testPrintLines() throws UnsupportedEncodingException {
    // Arrange
    Consumer<PrintStream> printer = mock(Consumer.class);
    doNothing().when(printer).accept(Mockito.<PrintStream>any());

    // Act
    List<String> actualPrintLinesResult = PrintUtils.printLines(printer);

    // Assert
    verify(printer).accept(isA(PrintStream.class));
    assertEquals(1, actualPrintLinesResult.size());
    assertEquals("", actualPrintLinesResult.get(0));
  }
}
