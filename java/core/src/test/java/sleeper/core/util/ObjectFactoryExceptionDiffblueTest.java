package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ObjectFactoryExceptionDiffblueTest {
  /**
   * Test {@link ObjectFactoryException#ObjectFactoryException(String, Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectFactoryException#ObjectFactoryException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new ObjectFactoryException(String, Throwable); then return Message is 'An error occurred'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ObjectFactoryException.<init>(String)",
      "void ObjectFactoryException.<init>(String, Throwable)", "void ObjectFactoryException.<init>(Throwable)"})
  void testNewObjectFactoryException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable e = new Throwable();

    // Act
    ObjectFactoryException actualObjectFactoryException = new ObjectFactoryException("An error occurred", e);

    // Assert
    assertEquals("An error occurred", actualObjectFactoryException.getMessage());
    assertEquals(0, actualObjectFactoryException.getSuppressed().length);
    assertSame(e, actualObjectFactoryException.getCause());
  }

  /**
   * Test {@link ObjectFactoryException#ObjectFactoryException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectFactoryException#ObjectFactoryException(String)}
   */
  @Test
  @DisplayName("Test new ObjectFactoryException(String); when 'An error occurred'; then return Cause is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ObjectFactoryException.<init>(String)",
      "void ObjectFactoryException.<init>(String, Throwable)", "void ObjectFactoryException.<init>(Throwable)"})
  void testNewObjectFactoryException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    ObjectFactoryException actualObjectFactoryException = new ObjectFactoryException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualObjectFactoryException.getMessage());
    assertNull(actualObjectFactoryException.getCause());
    assertEquals(0, actualObjectFactoryException.getSuppressed().length);
  }

  /**
   * Test {@link ObjectFactoryException#ObjectFactoryException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectFactoryException#ObjectFactoryException(Throwable)}
   */
  @Test
  @DisplayName("Test new ObjectFactoryException(Throwable); when Throwable(); then return Message is 'java.lang.Throwable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ObjectFactoryException.<init>(String)",
      "void ObjectFactoryException.<init>(String, Throwable)", "void ObjectFactoryException.<init>(Throwable)"})
  void testNewObjectFactoryException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    ObjectFactoryException actualObjectFactoryException = new ObjectFactoryException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualObjectFactoryException.getMessage());
    assertEquals(0, actualObjectFactoryException.getSuppressed().length);
    assertSame(cause, actualObjectFactoryException.getCause());
  }
}
