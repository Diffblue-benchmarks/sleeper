package sleeper.task.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ECSFailureExceptionDiffblueTest {
  /**
   * Test {@link ECSFailureException#ECSFailureException(String)}.
   * <p>
   * Method under test: {@link ECSFailureException#ECSFailureException(String)}
   */
  @Test
  @DisplayName("Test new ECSFailureException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ECSFailureException.<init>(String)"})
  void testNewECSFailureException() {
    // Arrange and Act
    ECSFailureException actualEcsFailureException = new ECSFailureException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualEcsFailureException.getMessage());
    assertNull(actualEcsFailureException.getCause());
    assertEquals(0, actualEcsFailureException.getSuppressed().length);
  }
}
