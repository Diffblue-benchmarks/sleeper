package sleeper.job.common.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ActionExceptionDiffblueTest {
  /**
   * Test {@link ActionException#ActionException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ActionException#ActionException(String)}
   */
  @Test
  @DisplayName("Test new ActionException(String); when 'An error occurred'; then return Cause is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ActionException.<init>(String)", "void ActionException.<init>(String, Throwable)",
      "void ActionException.<init>(Throwable)"})
  void testNewActionException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    ActionException actualActionException = new ActionException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualActionException.getMessage());
    assertNull(actualActionException.getCause());
    assertEquals(0, actualActionException.getSuppressed().length);
  }

  /**
   * Test {@link ActionException#ActionException(String, Throwable)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ActionException#ActionException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new ActionException(String, Throwable); when 'An error occurred'; then return Message is 'An error occurred'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ActionException.<init>(String)", "void ActionException.<init>(String, Throwable)",
      "void ActionException.<init>(Throwable)"})
  void testNewActionException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable e = new Throwable();

    // Act
    ActionException actualActionException = new ActionException("An error occurred", e);

    // Assert
    assertEquals("An error occurred", actualActionException.getMessage());
    assertEquals(0, actualActionException.getSuppressed().length);
    assertSame(e, actualActionException.getCause());
  }

  /**
   * Test {@link ActionException#ActionException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ActionException#ActionException(Throwable)}
   */
  @Test
  @DisplayName("Test new ActionException(Throwable); when Throwable(); then return Message is 'java.lang.Throwable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ActionException.<init>(String)", "void ActionException.<init>(String, Throwable)",
      "void ActionException.<init>(Throwable)"})
  void testNewActionException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    ActionException actualActionException = new ActionException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualActionException.getMessage());
    assertEquals(0, actualActionException.getSuppressed().length);
    assertSame(cause, actualActionException.getCause());
  }
}
