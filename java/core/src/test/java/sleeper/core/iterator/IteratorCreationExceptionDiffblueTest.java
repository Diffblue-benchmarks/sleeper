package sleeper.core.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class IteratorCreationExceptionDiffblueTest {
  /**
   * Test {@link IteratorCreationException#IteratorCreationException(String, Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IteratorCreationException#IteratorCreationException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new IteratorCreationException(String, Throwable); then return Message is 'An error occurred'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IteratorCreationException.<init>(String)",
      "void IteratorCreationException.<init>(String, Throwable)", "void IteratorCreationException.<init>(Throwable)"})
  void testNewIteratorCreationException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable e = new Throwable();

    // Act
    IteratorCreationException actualIteratorCreationException = new IteratorCreationException("An error occurred", e);

    // Assert
    assertEquals("An error occurred", actualIteratorCreationException.getMessage());
    assertEquals(0, actualIteratorCreationException.getSuppressed().length);
    assertSame(e, actualIteratorCreationException.getCause());
  }

  /**
   * Test {@link IteratorCreationException#IteratorCreationException(Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IteratorCreationException#IteratorCreationException(Throwable)}
   */
  @Test
  @DisplayName("Test new IteratorCreationException(Throwable); then return Message is 'java.lang.Throwable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IteratorCreationException.<init>(String)",
      "void IteratorCreationException.<init>(String, Throwable)", "void IteratorCreationException.<init>(Throwable)"})
  void testNewIteratorCreationException_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    IteratorCreationException actualIteratorCreationException = new IteratorCreationException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualIteratorCreationException.getMessage());
    assertEquals(0, actualIteratorCreationException.getSuppressed().length);
    assertSame(cause, actualIteratorCreationException.getCause());
  }

  /**
   * Test {@link IteratorCreationException#IteratorCreationException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IteratorCreationException#IteratorCreationException(String)}
   */
  @Test
  @DisplayName("Test new IteratorCreationException(String); when 'An error occurred'; then return Cause is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IteratorCreationException.<init>(String)",
      "void IteratorCreationException.<init>(String, Throwable)", "void IteratorCreationException.<init>(Throwable)"})
  void testNewIteratorCreationException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    IteratorCreationException actualIteratorCreationException = new IteratorCreationException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualIteratorCreationException.getMessage());
    assertNull(actualIteratorCreationException.getCause());
    assertEquals(0, actualIteratorCreationException.getSuppressed().length);
  }
}
