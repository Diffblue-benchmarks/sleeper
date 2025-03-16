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

class FileReferenceAlreadyExistsExceptionDiffblueTest {
  /**
   * Test {@link FileReferenceAlreadyExistsException#FileReferenceAlreadyExistsException(FileReference)}.
   * <p>
   * Method under test: {@link FileReferenceAlreadyExistsException#FileReferenceAlreadyExistsException(FileReference)}
   */
  @Test
  @DisplayName("Test new FileReferenceAlreadyExistsException(FileReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceAlreadyExistsException.<init>(FileReference)"})
  void testNewFileReferenceAlreadyExistsException() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    FileReferenceAlreadyExistsException actualFileReferenceAlreadyExistsException = new FileReferenceAlreadyExistsException(
        fileReference);

    // Assert
    assertEquals("Reference to file already exists in partition 42, filename foo.txt",
        actualFileReferenceAlreadyExistsException.getLocalizedMessage());
    assertEquals("Reference to file already exists in partition 42, filename foo.txt",
        actualFileReferenceAlreadyExistsException.getMessage());
    assertNull(actualFileReferenceAlreadyExistsException.getCause());
    assertEquals(0, actualFileReferenceAlreadyExistsException.getSuppressed().length);
  }

  /**
   * Test {@link FileReferenceAlreadyExistsException#FileReferenceAlreadyExistsException(FileReference, Exception)}.
   * <p>
   * Method under test: {@link FileReferenceAlreadyExistsException#FileReferenceAlreadyExistsException(FileReference, Exception)}
   */
  @Test
  @DisplayName("Test new FileReferenceAlreadyExistsException(FileReference, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceAlreadyExistsException.<init>(FileReference, Exception)"})
  void testNewFileReferenceAlreadyExistsException2() {
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
    FileReferenceAlreadyExistsException actualFileReferenceAlreadyExistsException = new FileReferenceAlreadyExistsException(
        fileReference, cause);

    // Assert
    assertEquals("Reference to file already exists in partition 42, filename foo.txt",
        actualFileReferenceAlreadyExistsException.getLocalizedMessage());
    assertEquals("Reference to file already exists in partition 42, filename foo.txt",
        actualFileReferenceAlreadyExistsException.getMessage());
    assertEquals(0, actualFileReferenceAlreadyExistsException.getSuppressed().length);
    assertSame(cause, actualFileReferenceAlreadyExistsException.getCause());
  }
}
