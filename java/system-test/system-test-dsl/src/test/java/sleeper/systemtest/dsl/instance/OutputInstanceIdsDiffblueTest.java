package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OutputInstanceIdsDiffblueTest {
  /**
   * Test {@link OutputInstanceIds#addInstanceIdToOutput(String, SystemTestParameters)}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OutputInstanceIds#addInstanceIdToOutput(String, SystemTestParameters)}
   */
  @Test
  @DisplayName("Test addInstanceIdToOutput(String, SystemTestParameters); given IOException(String) with 'foo'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void OutputInstanceIds.addInstanceIdToOutput(String, SystemTestParameters)"})
  void testAddInstanceIdToOutput_givenIOExceptionWithFoo_thenThrowUncheckedIOException() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenThrow(new UncheckedIOException(new IOException("foo")));

    // Act and Assert
    assertThrows(UncheckedIOException.class, () -> OutputInstanceIds.addInstanceIdToOutput("42", parameters));
    verify(parameters).getOutputDirectory();
  }
}
