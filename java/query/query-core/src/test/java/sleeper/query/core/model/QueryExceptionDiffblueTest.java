package sleeper.query.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class QueryExceptionDiffblueTest {
  /**
   * Test {@link QueryException#QueryException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryException#QueryException(String)}
   */
  @Test
  @DisplayName("Test new QueryException(String); when 'An error occurred'; then return Cause is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryException.<init>(String)", "void QueryException.<init>(String, Throwable)",
      "void QueryException.<init>(Throwable)"})
  void testNewQueryException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    QueryException actualQueryException = new QueryException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualQueryException.getMessage());
    assertNull(actualQueryException.getCause());
    assertEquals(0, actualQueryException.getSuppressed().length);
  }

  /**
   * Test {@link QueryException#QueryException(String, Throwable)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryException#QueryException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new QueryException(String, Throwable); when 'An error occurred'; then return Message is 'An error occurred'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryException.<init>(String)", "void QueryException.<init>(String, Throwable)",
      "void QueryException.<init>(Throwable)"})
  void testNewQueryException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable e = new Throwable();

    // Act
    QueryException actualQueryException = new QueryException("An error occurred", e);

    // Assert
    assertEquals("An error occurred", actualQueryException.getMessage());
    assertEquals(0, actualQueryException.getSuppressed().length);
    assertSame(e, actualQueryException.getCause());
  }

  /**
   * Test {@link QueryException#QueryException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryException#QueryException(Throwable)}
   */
  @Test
  @DisplayName("Test new QueryException(Throwable); when Throwable(); then return Message is 'java.lang.Throwable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryException.<init>(String)", "void QueryException.<init>(String, Throwable)",
      "void QueryException.<init>(Throwable)"})
  void testNewQueryException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    QueryException actualQueryException = new QueryException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualQueryException.getMessage());
    assertEquals(0, actualQueryException.getSuppressed().length);
    assertSame(cause, actualQueryException.getCause());
  }
}
