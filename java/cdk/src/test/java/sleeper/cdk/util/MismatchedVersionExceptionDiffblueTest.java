package sleeper.cdk.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MismatchedVersionExceptionDiffblueTest {
  /**
   * Test {@link MismatchedVersionException#MismatchedVersionException(String)}.
   * <p>
   * Method under test: {@link MismatchedVersionException#MismatchedVersionException(String)}
   */
  @Test
  @DisplayName("Test new MismatchedVersionException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MismatchedVersionException.<init>(String)"})
  void testNewMismatchedVersionException() {
    // Arrange and Act
    MismatchedVersionException actualMismatchedVersionException = new MismatchedVersionException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualMismatchedVersionException.getMessage());
    assertNull(actualMismatchedVersionException.getCause());
    assertEquals(0, actualMismatchedVersionException.getSuppressed().length);
  }
}
