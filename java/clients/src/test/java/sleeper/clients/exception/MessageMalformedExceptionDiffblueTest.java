package sleeper.clients.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MessageMalformedExceptionDiffblueTest {
  /**
   * Test {@link MessageMalformedException#MessageMalformedException(String)}.
   * <p>
   * Method under test: {@link MessageMalformedException#MessageMalformedException(String)}
   */
  @Test
  @DisplayName("Test new MessageMalformedException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MessageMalformedException.<init>(String)"})
  void testNewMessageMalformedException() {
    // Arrange and Act
    MessageMalformedException actualMessageMalformedException = new MessageMalformedException("Json");

    // Assert
    assertEquals("Received malformed message JSON: Json", actualMessageMalformedException.getLocalizedMessage());
    assertEquals("Received malformed message JSON: Json", actualMessageMalformedException.getMessage());
    assertNull(actualMessageMalformedException.getCause());
    assertEquals(0, actualMessageMalformedException.getSuppressed().length);
  }
}
