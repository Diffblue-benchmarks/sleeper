package sleeper.clients.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class WebSocketExceptionDiffblueTest {
  /**
   * Test {@link WebSocketException#WebSocketException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketException#WebSocketException(String)}
   */
  @Test
  @DisplayName("Test new WebSocketException(String); when 'An error occurred'; then return Cause is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketException.<init>(String)", "void WebSocketException.<init>(String, Exception)"})
  void testNewWebSocketException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    WebSocketException actualWebSocketException = new WebSocketException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualWebSocketException.getMessage());
    assertNull(actualWebSocketException.getCause());
    assertEquals(0, actualWebSocketException.getSuppressed().length);
  }

  /**
   * Test {@link WebSocketException#WebSocketException(String, Exception)}.
   * <ul>
   *   <li>When {@link Exception#Exception(String)} with {@code foo}.</li>
   *   <li>Then return Cause is {@link Exception#Exception(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketException#WebSocketException(String, Exception)}
   */
  @Test
  @DisplayName("Test new WebSocketException(String, Exception); when Exception(String) with 'foo'; then return Cause is Exception(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketException.<init>(String)", "void WebSocketException.<init>(String, Exception)"})
  void testNewWebSocketException_whenExceptionWithFoo_thenReturnCauseIsExceptionWithFoo() {
    // Arrange
    Exception cause = new Exception("foo");

    // Act
    WebSocketException actualWebSocketException = new WebSocketException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualWebSocketException.getMessage());
    assertEquals(0, actualWebSocketException.getSuppressed().length);
    assertSame(cause, actualWebSocketException.getCause());
  }
}
