package sleeper.core.statestore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;

class FileReferenceNotFoundExceptionDiffblueTest {
  /**
   * Test {@link FileReferenceNotFoundException#FileReferenceNotFoundException(String, String)}.
   * <p>
   * Method under test: {@link FileReferenceNotFoundException#FileReferenceNotFoundException(String, String)}
   */
  @Test
  @DisplayName("Test new FileReferenceNotFoundException(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceNotFoundException.<init>(String, String)"})
  void testNewFileReferenceNotFoundException() {
    // Arrange and Act
    FileReferenceNotFoundException actualFileReferenceNotFoundException = new FileReferenceNotFoundException("foo.txt",
        "42");

    // Assert
    assertEquals("File reference not found in partition 42, filename foo.txt",
        actualFileReferenceNotFoundException.getLocalizedMessage());
    assertEquals("File reference not found in partition 42, filename foo.txt",
        actualFileReferenceNotFoundException.getMessage());
    assertNull(actualFileReferenceNotFoundException.getCause());
    assertEquals(0, actualFileReferenceNotFoundException.getSuppressed().length);
  }

  /**
   * Test {@link FileReferenceNotFoundException#FileReferenceNotFoundException(String, String, Exception)}.
   * <p>
   * Method under test: {@link FileReferenceNotFoundException#FileReferenceNotFoundException(String, String, Exception)}
   */
  @Test
  @DisplayName("Test new FileReferenceNotFoundException(String, String, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceNotFoundException.<init>(String, String, Exception)"})
  void testNewFileReferenceNotFoundException2() {
    // Arrange
    Exception cause = new Exception("foo");

    // Act
    FileReferenceNotFoundException actualFileReferenceNotFoundException = new FileReferenceNotFoundException("foo.txt",
        "42", cause);

    // Assert
    assertEquals("File reference not found in partition 42, filename foo.txt",
        actualFileReferenceNotFoundException.getLocalizedMessage());
    assertEquals("File reference not found in partition 42, filename foo.txt",
        actualFileReferenceNotFoundException.getMessage());
    assertEquals(0, actualFileReferenceNotFoundException.getSuppressed().length);
    assertSame(cause, actualFileReferenceNotFoundException.getCause());
  }

  /**
   * Test {@link FileReferenceNotFoundException#FileReferenceNotFoundException(FileReference)}.
   * <p>
   * Method under test: {@link FileReferenceNotFoundException#FileReferenceNotFoundException(FileReference)}
   */
  @Test
  @DisplayName("Test new FileReferenceNotFoundException(FileReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceNotFoundException.<init>(FileReference)"})
  void testNewFileReferenceNotFoundException3() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    FileReferenceNotFoundException actualFileReferenceNotFoundException = new FileReferenceNotFoundException(
        fileReference);

    // Assert
    assertEquals("File reference not found in partition 42, filename foo.txt",
        actualFileReferenceNotFoundException.getLocalizedMessage());
    assertEquals("File reference not found in partition 42, filename foo.txt",
        actualFileReferenceNotFoundException.getMessage());
    assertNull(actualFileReferenceNotFoundException.getCause());
    assertEquals(0, actualFileReferenceNotFoundException.getSuppressed().length);
  }

  /**
   * Test {@link FileReferenceNotFoundException#FileReferenceNotFoundException(FileReference, Exception)}.
   * <p>
   * Method under test: {@link FileReferenceNotFoundException#FileReferenceNotFoundException(FileReference, Exception)}
   */
  @Test
  @DisplayName("Test new FileReferenceNotFoundException(FileReference, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceNotFoundException.<init>(FileReference, Exception)"})
  void testNewFileReferenceNotFoundException4() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    Exception cause = new Exception("foo");

    // Act
    FileReferenceNotFoundException actualFileReferenceNotFoundException = new FileReferenceNotFoundException(
        fileReference, cause);

    // Assert
    assertEquals("File reference not found in partition 42, filename foo.txt",
        actualFileReferenceNotFoundException.getLocalizedMessage());
    assertEquals("File reference not found in partition 42, filename foo.txt",
        actualFileReferenceNotFoundException.getMessage());
    assertEquals(0, actualFileReferenceNotFoundException.getSuppressed().length);
    assertSame(cause, actualFileReferenceNotFoundException.getCause());
  }
}
