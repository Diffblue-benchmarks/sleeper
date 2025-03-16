package sleeper.core.statestore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FileNotFoundExceptionDiffblueTest {
  /**
   * Test {@link FileNotFoundException#FileNotFoundException(String)}.
   * <p>
   * Method under test: {@link FileNotFoundException#FileNotFoundException(String)}
   */
  @Test
  @DisplayName("Test new FileNotFoundException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileNotFoundException.<init>(String)"})
  void testNewFileNotFoundException() {
    // Arrange and Act
    FileNotFoundException actualFileNotFoundException = new FileNotFoundException("foo.txt");

    // Assert
    assertEquals("File not found: foo.txt", actualFileNotFoundException.getLocalizedMessage());
    assertEquals("File not found: foo.txt", actualFileNotFoundException.getMessage());
    assertNull(actualFileNotFoundException.getCause());
    assertEquals(0, actualFileNotFoundException.getSuppressed().length);
  }

  /**
   * Test {@link FileNotFoundException#FileNotFoundException(String, Exception)}.
   * <p>
   * Method under test: {@link FileNotFoundException#FileNotFoundException(String, Exception)}
   */
  @Test
  @DisplayName("Test new FileNotFoundException(String, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileNotFoundException.<init>(String, Exception)"})
  void testNewFileNotFoundException2() {
    // Arrange
    Exception cause = new Exception("foo");

    // Act
    FileNotFoundException actualFileNotFoundException = new FileNotFoundException("foo.txt", cause);

    // Assert
    assertEquals("File not found: foo.txt", actualFileNotFoundException.getLocalizedMessage());
    assertEquals("File not found: foo.txt", actualFileNotFoundException.getMessage());
    assertEquals(0, actualFileNotFoundException.getSuppressed().length);
    assertSame(cause, actualFileNotFoundException.getCause());
  }
}
