package sleeper.clients.util.cdk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CdkFailedExceptionDiffblueTest {
  /**
   * Test {@link CdkFailedException#CdkFailedException(int)}.
   * <p>
   * Method under test: {@link CdkFailedException#CdkFailedException(int)}
   */
  @Test
  @DisplayName("Test new CdkFailedException(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CdkFailedException.<init>(int)"})
  void testNewCdkFailedException() {
    // Arrange and Act
    CdkFailedException actualCdkFailedException = new CdkFailedException(1);

    // Assert
    assertEquals("Exit code from CDK: 1", actualCdkFailedException.getLocalizedMessage());
    assertEquals("Exit code from CDK: 1", actualCdkFailedException.getMessage());
    assertNull(actualCdkFailedException.getCause());
    assertEquals(0, actualCdkFailedException.getSuppressed().length);
  }
}
