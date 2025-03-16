package sleeper.clients.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class QueryWebSocketClientExceptionDiffblueTest {
  /**
   * Test {@link QueryWebSocketClientException#QueryWebSocketClientException(List)}.
   * <p>
   * Method under test: {@link QueryWebSocketClientException#QueryWebSocketClientException(List)}
   */
  @Test
  @DisplayName("Test new QueryWebSocketClientException(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryWebSocketClientException.<init>(List)"})
  void testNewQueryWebSocketClientException() {
    // Arrange and Act
    QueryWebSocketClientException actualQueryWebSocketClientException = new QueryWebSocketClientException(
        new ArrayList<>());

    // Assert
    assertEquals("WebSocket client encountered 0 exceptions while running query",
        actualQueryWebSocketClientException.getLocalizedMessage());
    assertEquals("WebSocket client encountered 0 exceptions while running query",
        actualQueryWebSocketClientException.getMessage());
    assertNull(actualQueryWebSocketClientException.getCause());
    assertTrue(actualQueryWebSocketClientException.getExceptions().isEmpty());
  }

  /**
   * Test {@link QueryWebSocketClientException#QueryWebSocketClientException(List)}.
   * <p>
   * Method under test: {@link QueryWebSocketClientException#QueryWebSocketClientException(List)}
   */
  @Test
  @DisplayName("Test new QueryWebSocketClientException(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryWebSocketClientException.<init>(List)"})
  void testNewQueryWebSocketClientException2() {
    // Arrange
    ArrayList<WebSocketException> exceptions = new ArrayList<>();
    WebSocketException webSocketException = new WebSocketException("An error occurred");
    exceptions.add(webSocketException);

    // Act
    QueryWebSocketClientException actualQueryWebSocketClientException = new QueryWebSocketClientException(exceptions);

    // Assert
    assertEquals("WebSocket client encountered 1 exceptions while running query",
        actualQueryWebSocketClientException.getLocalizedMessage());
    assertEquals("WebSocket client encountered 1 exceptions while running query",
        actualQueryWebSocketClientException.getMessage());
    assertSame(exceptions, actualQueryWebSocketClientException.getExceptions());
    assertSame(webSocketException, actualQueryWebSocketClientException.getCause());
  }

  /**
   * Test {@link QueryWebSocketClientException#QueryWebSocketClientException(List)}.
   * <p>
   * Method under test: {@link QueryWebSocketClientException#QueryWebSocketClientException(List)}
   */
  @Test
  @DisplayName("Test new QueryWebSocketClientException(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryWebSocketClientException.<init>(List)"})
  void testNewQueryWebSocketClientException3() {
    // Arrange
    ArrayList<WebSocketException> exceptions = new ArrayList<>();
    exceptions.add(new WebSocketException("An error occurred"));
    WebSocketException webSocketException = new WebSocketException("An error occurred");
    exceptions.add(webSocketException);

    // Act
    QueryWebSocketClientException actualQueryWebSocketClientException = new QueryWebSocketClientException(exceptions);

    // Assert
    assertEquals("WebSocket client encountered 2 exceptions while running query",
        actualQueryWebSocketClientException.getLocalizedMessage());
    assertEquals("WebSocket client encountered 2 exceptions while running query",
        actualQueryWebSocketClientException.getMessage());
    List<WebSocketException> exceptions2 = actualQueryWebSocketClientException.getExceptions();
    assertEquals(2, exceptions2.size());
    assertSame(webSocketException, exceptions2.get(1));
  }

  /**
   * Test {@link QueryWebSocketClientException#getExceptions()}.
   * <p>
   * Method under test: {@link QueryWebSocketClientException#getExceptions()}
   */
  @Test
  @DisplayName("Test getExceptions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List QueryWebSocketClientException.getExceptions()"})
  void testGetExceptions() {
    // Arrange
    ArrayList<WebSocketException> exceptions = new ArrayList<>();

    // Act
    List<WebSocketException> actualExceptions = (new QueryWebSocketClientException(exceptions)).getExceptions();

    // Assert
    assertTrue(actualExceptions.isEmpty());
    assertSame(exceptions, actualExceptions);
  }
}
