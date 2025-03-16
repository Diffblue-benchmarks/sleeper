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

class FileReferenceAssignedToJobExceptionDiffblueTest {
  /**
   * Test {@link FileReferenceAssignedToJobException#FileReferenceAssignedToJobException(FileReference)}.
   * <p>
   * Method under test: {@link FileReferenceAssignedToJobException#FileReferenceAssignedToJobException(FileReference)}
   */
  @Test
  @DisplayName("Test new FileReferenceAssignedToJobException(FileReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceAssignedToJobException.<init>(FileReference)"})
  void testNewFileReferenceAssignedToJobException() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    FileReferenceAssignedToJobException actualFileReferenceAssignedToJobException = new FileReferenceAssignedToJobException(
        fileReference);

    // Assert
    assertEquals("Reference to file is already assigned to job 42, in partition 42, filename foo.txt",
        actualFileReferenceAssignedToJobException.getLocalizedMessage());
    assertEquals("Reference to file is already assigned to job 42, in partition 42, filename foo.txt",
        actualFileReferenceAssignedToJobException.getMessage());
    assertNull(actualFileReferenceAssignedToJobException.getCause());
    assertEquals(0, actualFileReferenceAssignedToJobException.getSuppressed().length);
  }

  /**
   * Test {@link FileReferenceAssignedToJobException#FileReferenceAssignedToJobException(FileReference, Exception)}.
   * <p>
   * Method under test: {@link FileReferenceAssignedToJobException#FileReferenceAssignedToJobException(FileReference, Exception)}
   */
  @Test
  @DisplayName("Test new FileReferenceAssignedToJobException(FileReference, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceAssignedToJobException.<init>(FileReference, Exception)"})
  void testNewFileReferenceAssignedToJobException2() {
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
    FileReferenceAssignedToJobException actualFileReferenceAssignedToJobException = new FileReferenceAssignedToJobException(
        fileReference, cause);

    // Assert
    assertEquals("Reference to file is already assigned to job 42, in partition 42, filename foo.txt",
        actualFileReferenceAssignedToJobException.getLocalizedMessage());
    assertEquals("Reference to file is already assigned to job 42, in partition 42, filename foo.txt",
        actualFileReferenceAssignedToJobException.getMessage());
    assertEquals(0, actualFileReferenceAssignedToJobException.getSuppressed().length);
    assertSame(cause, actualFileReferenceAssignedToJobException.getCause());
  }
}
