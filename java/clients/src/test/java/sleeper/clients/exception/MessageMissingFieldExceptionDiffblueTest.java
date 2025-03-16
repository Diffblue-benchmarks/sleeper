package sleeper.clients.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MessageMissingFieldExceptionDiffblueTest {
  /**
   * Test {@link MessageMissingFieldException#MessageMissingFieldException(String, String)}.
   * <p>
   * Method under test: {@link MessageMissingFieldException#MessageMissingFieldException(String, String)}
   */
  @Test
  @DisplayName("Test new MessageMissingFieldException(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MessageMissingFieldException.<init>(String, String)"})
  void testNewMessageMissingFieldException() {
    // Arrange and Act
    MessageMissingFieldException actualMessageMissingFieldException = new MessageMissingFieldException("Field", "Json");

    // Assert
    assertEquals("Message missing required field Field: Json",
        actualMessageMissingFieldException.getLocalizedMessage());
    assertEquals("Message missing required field Field: Json", actualMessageMissingFieldException.getMessage());
    assertNull(actualMessageMissingFieldException.getCause());
    assertEquals(0, actualMessageMissingFieldException.getSuppressed().length);
  }
}
