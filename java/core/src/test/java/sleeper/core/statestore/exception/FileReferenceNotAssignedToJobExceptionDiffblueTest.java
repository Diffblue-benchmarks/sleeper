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

class FileReferenceNotAssignedToJobExceptionDiffblueTest {
  /**
   * Test {@link FileReferenceNotAssignedToJobException#FileReferenceNotAssignedToJobException(FileReference, String)}.
   * <p>
   * Method under test: {@link FileReferenceNotAssignedToJobException#FileReferenceNotAssignedToJobException(FileReference, String)}
   */
  @Test
  @DisplayName("Test new FileReferenceNotAssignedToJobException(FileReference, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceNotAssignedToJobException.<init>(FileReference, String)"})
  void testNewFileReferenceNotAssignedToJobException() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    FileReferenceNotAssignedToJobException actualFileReferenceNotAssignedToJobException = new FileReferenceNotAssignedToJobException(
        fileReference, "42");

    // Assert
    assertEquals("Reference to file is not assigned to job 42, in partition 42, filename foo.txt",
        actualFileReferenceNotAssignedToJobException.getLocalizedMessage());
    assertEquals("Reference to file is not assigned to job 42, in partition 42, filename foo.txt",
        actualFileReferenceNotAssignedToJobException.getMessage());
    assertNull(actualFileReferenceNotAssignedToJobException.getCause());
    assertEquals(0, actualFileReferenceNotAssignedToJobException.getSuppressed().length);
  }

  /**
   * Test {@link FileReferenceNotAssignedToJobException#FileReferenceNotAssignedToJobException(FileReference, String, Exception)}.
   * <p>
   * Method under test: {@link FileReferenceNotAssignedToJobException#FileReferenceNotAssignedToJobException(FileReference, String, Exception)}
   */
  @Test
  @DisplayName("Test new FileReferenceNotAssignedToJobException(FileReference, String, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceNotAssignedToJobException.<init>(FileReference, String, Exception)"})
  void testNewFileReferenceNotAssignedToJobException2() {
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
    FileReferenceNotAssignedToJobException actualFileReferenceNotAssignedToJobException = new FileReferenceNotAssignedToJobException(
        fileReference, "42", cause);

    // Assert
    assertEquals("Reference to file is not assigned to job 42, in partition 42, filename foo.txt",
        actualFileReferenceNotAssignedToJobException.getLocalizedMessage());
    assertEquals("Reference to file is not assigned to job 42, in partition 42, filename foo.txt",
        actualFileReferenceNotAssignedToJobException.getMessage());
    assertEquals(0, actualFileReferenceNotAssignedToJobException.getSuppressed().length);
    assertSame(cause, actualFileReferenceNotAssignedToJobException.getCause());
  }
}
