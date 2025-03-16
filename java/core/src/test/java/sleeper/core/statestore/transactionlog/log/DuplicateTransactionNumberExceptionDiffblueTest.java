package sleeper.core.statestore.transactionlog.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DuplicateTransactionNumberExceptionDiffblueTest {
  /**
   * Test {@link DuplicateTransactionNumberException#DuplicateTransactionNumberException(long)}.
   * <p>
   * Method under test: {@link DuplicateTransactionNumberException#DuplicateTransactionNumberException(long)}
   */
  @Test
  @DisplayName("Test new DuplicateTransactionNumberException(long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DuplicateTransactionNumberException.<init>(long)"})
  void testNewDuplicateTransactionNumberException() {
    // Arrange and Act
    DuplicateTransactionNumberException actualDuplicateTransactionNumberException = new DuplicateTransactionNumberException(
        1L);

    // Assert
    assertEquals("Unread transaction found. Adding transaction number 1, but it already exists.",
        actualDuplicateTransactionNumberException.getLocalizedMessage());
    assertEquals("Unread transaction found. Adding transaction number 1, but it already exists.",
        actualDuplicateTransactionNumberException.getMessage());
    assertNull(actualDuplicateTransactionNumberException.getCause());
    assertEquals(0, actualDuplicateTransactionNumberException.getSuppressed().length);
  }

  /**
   * Test {@link DuplicateTransactionNumberException#DuplicateTransactionNumberException(long, Throwable)}.
   * <p>
   * Method under test: {@link DuplicateTransactionNumberException#DuplicateTransactionNumberException(long, Throwable)}
   */
  @Test
  @DisplayName("Test new DuplicateTransactionNumberException(long, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DuplicateTransactionNumberException.<init>(long, Throwable)"})
  void testNewDuplicateTransactionNumberException2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DuplicateTransactionNumberException actualDuplicateTransactionNumberException = new DuplicateTransactionNumberException(
        1L, cause);

    // Assert
    assertEquals("Unread transaction found. Adding transaction number 1, but it already exists.",
        actualDuplicateTransactionNumberException.getLocalizedMessage());
    assertEquals("Unread transaction found. Adding transaction number 1, but it already exists.",
        actualDuplicateTransactionNumberException.getMessage());
    assertEquals(0, actualDuplicateTransactionNumberException.getSuppressed().length);
    assertSame(cause, actualDuplicateTransactionNumberException.getCause());
  }
}
