package sleeper.core.statestore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FileAlreadyExistsExceptionDiffblueTest {
  /**
   * Test {@link FileAlreadyExistsException#FileAlreadyExistsException(String)}.
   * <p>
   * Method under test: {@link FileAlreadyExistsException#FileAlreadyExistsException(String)}
   */
  @Test
  @DisplayName("Test new FileAlreadyExistsException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileAlreadyExistsException.<init>(String)"})
  void testNewFileAlreadyExistsException() {
    // Arrange and Act
    FileAlreadyExistsException actualFileAlreadyExistsException = new FileAlreadyExistsException("foo.txt");

    // Assert
    assertEquals("File already exists: foo.txt", actualFileAlreadyExistsException.getLocalizedMessage());
    assertEquals("File already exists: foo.txt", actualFileAlreadyExistsException.getMessage());
    assertNull(actualFileAlreadyExistsException.getCause());
    assertEquals(0, actualFileAlreadyExistsException.getSuppressed().length);
  }

  /**
   * Test {@link FileAlreadyExistsException#FileAlreadyExistsException(String, Exception)}.
   * <p>
   * Method under test: {@link FileAlreadyExistsException#FileAlreadyExistsException(String, Exception)}
   */
  @Test
  @DisplayName("Test new FileAlreadyExistsException(String, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileAlreadyExistsException.<init>(String, Exception)"})
  void testNewFileAlreadyExistsException2() {
    // Arrange
    Exception cause = new Exception("foo");

    // Act
    FileAlreadyExistsException actualFileAlreadyExistsException = new FileAlreadyExistsException("foo.txt", cause);

    // Assert
    assertEquals("File already exists: foo.txt", actualFileAlreadyExistsException.getLocalizedMessage());
    assertEquals("File already exists: foo.txt", actualFileAlreadyExistsException.getMessage());
    assertEquals(0, actualFileAlreadyExistsException.getSuppressed().length);
    assertSame(cause, actualFileAlreadyExistsException.getCause());
  }
}
