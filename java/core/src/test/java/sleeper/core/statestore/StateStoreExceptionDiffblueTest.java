package sleeper.core.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StateStoreExceptionDiffblueTest {
  /**
   * Test {@link StateStoreException#StateStoreException(String)}.
   * <ul>
   *   <li>When {@code An error occurred}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreException#StateStoreException(String)}
   */
  @Test
  @DisplayName("Test new StateStoreException(String); when 'An error occurred'; then return Cause is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreException.<init>(String)", "void StateStoreException.<init>(String, Throwable)"})
  void testNewStateStoreException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    StateStoreException actualStateStoreException = new StateStoreException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualStateStoreException.getMessage());
    assertNull(actualStateStoreException.getCause());
    assertEquals(0, actualStateStoreException.getSuppressed().length);
  }

  /**
   * Test {@link StateStoreException#StateStoreException(String, Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return Cause is {@link Throwable#Throwable()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreException#StateStoreException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new StateStoreException(String, Throwable); when Throwable(); then return Cause is Throwable()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreException.<init>(String)", "void StateStoreException.<init>(String, Throwable)"})
  void testNewStateStoreException_whenThrowable_thenReturnCauseIsThrowable() {
    // Arrange
    Throwable e = new Throwable();

    // Act
    StateStoreException actualStateStoreException = new StateStoreException("An error occurred", e);

    // Assert
    assertEquals("An error occurred", actualStateStoreException.getMessage());
    assertEquals(0, actualStateStoreException.getSuppressed().length);
    assertSame(e, actualStateStoreException.getCause());
  }
}
