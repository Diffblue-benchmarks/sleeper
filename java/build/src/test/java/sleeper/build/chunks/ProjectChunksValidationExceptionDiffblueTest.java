package sleeper.build.chunks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ProjectChunksValidationExceptionDiffblueTest {
  /**
   * Test {@link ProjectChunksValidationException#ProjectChunksValidationException(String)}.
   * <p>
   * Method under test: {@link ProjectChunksValidationException#ProjectChunksValidationException(String)}
   */
  @Test
  @DisplayName("Test new ProjectChunksValidationException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProjectChunksValidationException.<init>(String)"})
  void testNewProjectChunksValidationException() {
    // Arrange and Act
    ProjectChunksValidationException actualProjectChunksValidationException = new ProjectChunksValidationException(
        "An error occurred");

    // Assert
    assertEquals("An error occurred", actualProjectChunksValidationException.getMessage());
    assertNull(actualProjectChunksValidationException.getCause());
    assertEquals(0, actualProjectChunksValidationException.getSuppressed().length);
  }
}
