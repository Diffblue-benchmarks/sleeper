package sleeper.task.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ECSAbortExceptionDiffblueTest {
  /**
   * Test new {@link ECSAbortException} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ECSAbortException}
   */
  @Test
  @DisplayName("Test new ECSAbortException (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ECSAbortException.<init>()"})
  void testNewECSAbortException() {
    // Arrange and Act
    ECSAbortException actualEcsAbortException = new ECSAbortException();

    // Assert
    assertEquals("Run tasks aborted", actualEcsAbortException.getMessage());
    assertNull(actualEcsAbortException.getCause());
    assertEquals(0, actualEcsAbortException.getSuppressed().length);
  }
}
