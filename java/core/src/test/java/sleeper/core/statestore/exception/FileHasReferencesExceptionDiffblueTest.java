package sleeper.core.statestore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.AllReferencesToAFile;
import sleeper.core.statestore.AllReferencesToAFile.Builder;

class FileHasReferencesExceptionDiffblueTest {
  /**
   * Test {@link FileHasReferencesException#FileHasReferencesException(String, int)}.
   * <p>
   * Method under test: {@link FileHasReferencesException#FileHasReferencesException(String, int)}
   */
  @Test
  @DisplayName("Test new FileHasReferencesException(String, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileHasReferencesException.<init>(String, int)"})
  void testNewFileHasReferencesException() {
    // Arrange and Act
    FileHasReferencesException actualFileHasReferencesException = new FileHasReferencesException("foo.txt", 3);

    // Assert
    assertEquals("Cannot remove file as it still has references, filename foo.txt, 3 references",
        actualFileHasReferencesException.getLocalizedMessage());
    assertEquals("Cannot remove file as it still has references, filename foo.txt, 3 references",
        actualFileHasReferencesException.getMessage());
    assertNull(actualFileHasReferencesException.getCause());
    assertEquals(0, actualFileHasReferencesException.getSuppressed().length);
  }

  /**
   * Test {@link FileHasReferencesException#FileHasReferencesException(String, int, Exception)}.
   * <p>
   * Method under test: {@link FileHasReferencesException#FileHasReferencesException(String, int, Exception)}
   */
  @Test
  @DisplayName("Test new FileHasReferencesException(String, int, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileHasReferencesException.<init>(String, int, Exception)"})
  void testNewFileHasReferencesException2() {
    // Arrange
    Exception cause = new Exception("foo");

    // Act
    FileHasReferencesException actualFileHasReferencesException = new FileHasReferencesException("foo.txt", 3, cause);

    // Assert
    assertEquals("Cannot remove file as it still has references, filename foo.txt, 3 references",
        actualFileHasReferencesException.getLocalizedMessage());
    assertEquals("Cannot remove file as it still has references, filename foo.txt, 3 references",
        actualFileHasReferencesException.getMessage());
    assertEquals(0, actualFileHasReferencesException.getSuppressed().length);
    assertSame(cause, actualFileHasReferencesException.getCause());
  }

  /**
   * Test {@link FileHasReferencesException#FileHasReferencesException(AllReferencesToAFile)}.
   * <p>
   * Method under test: {@link FileHasReferencesException#FileHasReferencesException(AllReferencesToAFile)}
   */
  @Test
  @DisplayName("Test new FileHasReferencesException(AllReferencesToAFile)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileHasReferencesException.<init>(AllReferencesToAFile)"})
  void testNewFileHasReferencesException3() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile fileReferences = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act
    FileHasReferencesException actualFileHasReferencesException = new FileHasReferencesException(fileReferences);

    // Assert
    assertEquals("Cannot remove file as it still has references, filename foo.txt, 0 references",
        actualFileHasReferencesException.getLocalizedMessage());
    assertEquals("Cannot remove file as it still has references, filename foo.txt, 0 references",
        actualFileHasReferencesException.getMessage());
    assertNull(actualFileHasReferencesException.getCause());
    assertEquals(0, actualFileHasReferencesException.getSuppressed().length);
  }
}
