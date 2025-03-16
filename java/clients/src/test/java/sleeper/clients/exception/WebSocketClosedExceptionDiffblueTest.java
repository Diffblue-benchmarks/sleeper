package sleeper.clients.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class WebSocketClosedExceptionDiffblueTest {
  /**
   * Test {@link WebSocketClosedException#WebSocketClosedException(String)}.
   * <p>
   * Method under test: {@link WebSocketClosedException#WebSocketClosedException(String)}
   */
  @Test
  @DisplayName("Test new WebSocketClosedException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketClosedException.<init>(String)"})
  void testNewWebSocketClosedException() {
    // Arrange and Act
    WebSocketClosedException actualWebSocketClosedException = new WebSocketClosedException("Just cause");

    // Assert
    assertEquals("WebSocket closed unexpectedly with reason: Just cause",
        actualWebSocketClosedException.getLocalizedMessage());
    assertEquals("WebSocket closed unexpectedly with reason: Just cause", actualWebSocketClosedException.getMessage());
    assertNull(actualWebSocketClosedException.getCause());
    assertEquals(0, actualWebSocketClosedException.getSuppressed().length);
  }
}
