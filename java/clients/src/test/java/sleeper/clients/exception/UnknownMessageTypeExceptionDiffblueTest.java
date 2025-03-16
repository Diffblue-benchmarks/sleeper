package sleeper.clients.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UnknownMessageTypeExceptionDiffblueTest {
  /**
   * Test {@link UnknownMessageTypeException#UnknownMessageTypeException(String)}.
   * <p>
   * Method under test: {@link UnknownMessageTypeException#UnknownMessageTypeException(String)}
   */
  @Test
  @DisplayName("Test new UnknownMessageTypeException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UnknownMessageTypeException.<init>(String)"})
  void testNewUnknownMessageTypeException() {
    // Arrange and Act
    UnknownMessageTypeException actualUnknownMessageTypeException = new UnknownMessageTypeException(
        "An error occurred");

    // Assert
    assertEquals("Unknown message type received: An error occurred",
        actualUnknownMessageTypeException.getLocalizedMessage());
    assertEquals("Unknown message type received: An error occurred", actualUnknownMessageTypeException.getMessage());
    assertNull(actualUnknownMessageTypeException.getCause());
    assertEquals(0, actualUnknownMessageTypeException.getSuppressed().length);
  }
}
