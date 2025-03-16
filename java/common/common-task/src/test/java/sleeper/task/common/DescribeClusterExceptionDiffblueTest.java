package sleeper.task.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DescribeClusterExceptionDiffblueTest {
  /**
   * Test {@link DescribeClusterException#DescribeClusterException(String, Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DescribeClusterException#DescribeClusterException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new DescribeClusterException(String, Throwable); then return Message is 'An error occurred'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DescribeClusterException.<init>(String)",
      "void DescribeClusterException.<init>(String, Throwable)", "void DescribeClusterException.<init>(Throwable)"})
  void testNewDescribeClusterException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable e = new Throwable();

    // Act
    DescribeClusterException actualDescribeClusterException = new DescribeClusterException("An error occurred", e);

    // Assert
    assertEquals("An error occurred", actualDescribeClusterException.getMessage());
    assertEquals(0, actualDescribeClusterException.getSuppressed().length);
    assertSame(e, actualDescribeClusterException.getCause());
  }

  /**
   * Test {@link DescribeClusterException#DescribeClusterException(Throwable)}.
   * <ul>
   *   <li>Then return Message is {@code Throwable}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DescribeClusterException#DescribeClusterException(Throwable)}
   */
  @Test
  @DisplayName("Test new DescribeClusterException(Throwable); then return Message is 'java.lang.Throwable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DescribeClusterException.<init>(String)",
      "void DescribeClusterException.<init>(String, Throwable)", "void DescribeClusterException.<init>(Throwable)"})
  void testNewDescribeClusterException_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DescribeClusterException actualDescribeClusterException = new DescribeClusterException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualDescribeClusterException.getMessage());
    assertEquals(0, actualDescribeClusterException.getSuppressed().length);
    assertSame(cause, actualDescribeClusterException.getCause());
  }

  /**
   * Test {@link DescribeClusterException#DescribeClusterException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DescribeClusterException#DescribeClusterException(String)}
   */
  @Test
  @DisplayName("Test new DescribeClusterException(String); when 'An error occurred'; then return Cause is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DescribeClusterException.<init>(String)",
      "void DescribeClusterException.<init>(String, Throwable)", "void DescribeClusterException.<init>(Throwable)"})
  void testNewDescribeClusterException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    DescribeClusterException actualDescribeClusterException = new DescribeClusterException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualDescribeClusterException.getMessage());
    assertNull(actualDescribeClusterException.getCause());
    assertEquals(0, actualDescribeClusterException.getSuppressed().length);
  }
}
