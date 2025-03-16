package sleeper.clients.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class WebSocketErrorExceptionDiffblueTest {
  /**
   * Test {@link WebSocketErrorException#WebSocketErrorException(Exception)}.
   * <p>
   * Method under test: {@link WebSocketErrorException#WebSocketErrorException(Exception)}
   */
  @Test
  @DisplayName("Test new WebSocketErrorException(Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketErrorException.<init>(Exception)"})
  void testNewWebSocketErrorException() {
    // Arrange
    Exception error = new Exception("foo");

    // Act
    WebSocketErrorException actualWebSocketErrorException = new WebSocketErrorException(error);

    // Assert
    assertEquals("Error while running queries", actualWebSocketErrorException.getMessage());
    assertEquals(0, actualWebSocketErrorException.getSuppressed().length);
    assertSame(error, actualWebSocketErrorException.getCause());
  }

  /**
   * Test {@link WebSocketErrorException#WebSocketErrorException(String)}.
   * <p>
   * Method under test: {@link WebSocketErrorException#WebSocketErrorException(String)}
   */
  @Test
  @DisplayName("Test new WebSocketErrorException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketErrorException.<init>(String)"})
  void testNewWebSocketErrorException2() {
    // Arrange and Act
    WebSocketErrorException actualWebSocketErrorException = new WebSocketErrorException("An error occurred");

    // Assert
    assertEquals("Error while running queries: An error occurred", actualWebSocketErrorException.getLocalizedMessage());
    assertEquals("Error while running queries: An error occurred", actualWebSocketErrorException.getMessage());
    assertNull(actualWebSocketErrorException.getCause());
    assertEquals(0, actualWebSocketErrorException.getSuppressed().length);
  }
}
