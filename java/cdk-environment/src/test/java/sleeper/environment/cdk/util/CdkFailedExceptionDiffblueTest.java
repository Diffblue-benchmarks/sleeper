package sleeper.environment.cdk.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CdkFailedExceptionDiffblueTest {
  /**
   * Test {@link CdkFailedException#CdkFailedException(List, int)}.
   * <p>
   * Method under test: {@link CdkFailedException#CdkFailedException(List, int)}
   */
  @Test
  @DisplayName("Test new CdkFailedException(List, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CdkFailedException.<init>(List, int)"})
  void testNewCdkFailedException() {
    // Arrange and Act
    CdkFailedException actualCdkFailedException = new CdkFailedException(new ArrayList<>(), 1);

    // Assert
    assertEquals("CDK failed with exit code 1. Command: []", actualCdkFailedException.getLocalizedMessage());
    assertEquals("CDK failed with exit code 1. Command: []", actualCdkFailedException.getMessage());
    assertNull(actualCdkFailedException.getCause());
    assertEquals(0, actualCdkFailedException.getSuppressed().length);
  }

  /**
   * Test {@link CdkFailedException#CdkFailedException(List, int)}.
   * <p>
   * Method under test: {@link CdkFailedException#CdkFailedException(List, int)}
   */
  @Test
  @DisplayName("Test new CdkFailedException(List, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CdkFailedException.<init>(List, int)"})
  void testNewCdkFailedException2() {
    // Arrange
    ArrayList<String> command = new ArrayList<>();
    command.add("CDK failed with exit code %d. Command: %s");

    // Act
    CdkFailedException actualCdkFailedException = new CdkFailedException(command, 1);

    // Assert
    assertEquals("CDK failed with exit code 1. Command: [CDK failed with exit code %d. Command: %s]",
        actualCdkFailedException.getLocalizedMessage());
    assertEquals("CDK failed with exit code 1. Command: [CDK failed with exit code %d. Command: %s]",
        actualCdkFailedException.getMessage());
    assertNull(actualCdkFailedException.getCause());
    assertEquals(0, actualCdkFailedException.getSuppressed().length);
  }

  /**
   * Test {@link CdkFailedException#CdkFailedException(List, int)}.
   * <p>
   * Method under test: {@link CdkFailedException#CdkFailedException(List, int)}
   */
  @Test
  @DisplayName("Test new CdkFailedException(List, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CdkFailedException.<init>(List, int)"})
  void testNewCdkFailedException3() {
    // Arrange
    ArrayList<String> command = new ArrayList<>();
    command.add("foo");
    command.add("CDK failed with exit code %d. Command: %s");

    // Act
    CdkFailedException actualCdkFailedException = new CdkFailedException(command, 1);

    // Assert
    assertEquals("CDK failed with exit code 1. Command: [foo, CDK failed with exit code %d. Command: %s]",
        actualCdkFailedException.getLocalizedMessage());
    assertEquals("CDK failed with exit code 1. Command: [foo, CDK failed with exit code %d. Command: %s]",
        actualCdkFailedException.getMessage());
    assertNull(actualCdkFailedException.getCause());
    assertEquals(0, actualCdkFailedException.getSuppressed().length);
  }
}
