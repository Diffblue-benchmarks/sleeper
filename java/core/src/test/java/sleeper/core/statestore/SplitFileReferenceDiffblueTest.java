package sleeper.core.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.FileReference.Builder;

class SplitFileReferenceDiffblueTest {
  /**
   * Test {@link SplitFileReference#referenceForChildPartition(FileReference, String)} with {@code file}, {@code childPartitionId}.
   * <p>
   * Method under test: {@link SplitFileReference#referenceForChildPartition(FileReference, String)}
   */
  @Test
  @DisplayName("Test referenceForChildPartition(FileReference, String) with 'file', 'childPartitionId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReference SplitFileReference.referenceForChildPartition(FileReference, String)"})
  void testReferenceForChildPartitionWithFileChildPartitionId() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    FileReference actualReferenceForChildPartitionResult = SplitFileReference.referenceForChildPartition(file, "42");

    // Assert
    assertEquals("42", actualReferenceForChildPartitionResult.getPartitionId());
    assertEquals("foo.txt", actualReferenceForChildPartitionResult.getFilename());
    assertNull(actualReferenceForChildPartitionResult.getJobId());
    assertNull(actualReferenceForChildPartitionResult.getLastStateStoreUpdateTime());
    assertEquals(0L, actualReferenceForChildPartitionResult.getNumberOfRecords().longValue());
    assertFalse(actualReferenceForChildPartitionResult.onlyContainsDataForThisPartition());
    assertTrue(actualReferenceForChildPartitionResult.isCountApproximate());
  }

  /**
   * Test {@link SplitFileReference#referenceForChildPartition(FileReference, String, long)} with {@code file}, {@code childPartitionId}, {@code numberOfRecords}.
   * <p>
   * Method under test: {@link SplitFileReference#referenceForChildPartition(FileReference, String, long)}
   */
  @Test
  @DisplayName("Test referenceForChildPartition(FileReference, String, long) with 'file', 'childPartitionId', 'numberOfRecords'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReference SplitFileReference.referenceForChildPartition(FileReference, String, long)"})
  void testReferenceForChildPartitionWithFileChildPartitionIdNumberOfRecords() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    FileReference actualReferenceForChildPartitionResult = SplitFileReference.referenceForChildPartition(file, "42",
        1L);

    // Assert
    assertEquals("42", actualReferenceForChildPartitionResult.getPartitionId());
    assertEquals("foo.txt", actualReferenceForChildPartitionResult.getFilename());
    assertNull(actualReferenceForChildPartitionResult.getJobId());
    assertNull(actualReferenceForChildPartitionResult.getLastStateStoreUpdateTime());
    assertEquals(1L, actualReferenceForChildPartitionResult.getNumberOfRecords().longValue());
    assertFalse(actualReferenceForChildPartitionResult.onlyContainsDataForThisPartition());
    assertTrue(actualReferenceForChildPartitionResult.isCountApproximate());
  }

  /**
   * Test {@link SplitFileReference#referenceForChildPartition(FileReference, String, long)} with {@code file}, {@code childPartitionId}, {@code numberOfRecords}.
   * <p>
   * Method under test: {@link SplitFileReference#referenceForChildPartition(FileReference, String, long)}
   */
  @Test
  @DisplayName("Test referenceForChildPartition(FileReference, String, long) with 'file', 'childPartitionId', 'numberOfRecords'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReference SplitFileReference.referenceForChildPartition(FileReference, String, long)"})
  void testReferenceForChildPartitionWithFileChildPartitionIdNumberOfRecords2() {
    // Arrange
    FileReference file = mock(FileReference.class);
    when(file.getFilename()).thenReturn("foo.txt");

    // Act
    FileReference actualReferenceForChildPartitionResult = SplitFileReference.referenceForChildPartition(file, "42",
        1L);

    // Assert
    verify(file).getFilename();
    assertEquals("42", actualReferenceForChildPartitionResult.getPartitionId());
    assertEquals("foo.txt", actualReferenceForChildPartitionResult.getFilename());
    assertNull(actualReferenceForChildPartitionResult.getJobId());
    assertNull(actualReferenceForChildPartitionResult.getLastStateStoreUpdateTime());
    assertEquals(1L, actualReferenceForChildPartitionResult.getNumberOfRecords().longValue());
    assertFalse(actualReferenceForChildPartitionResult.onlyContainsDataForThisPartition());
    assertTrue(actualReferenceForChildPartitionResult.isCountApproximate());
  }

  /**
   * Test {@link SplitFileReference#referenceForChildPartition(FileReference, String)} with {@code file}, {@code childPartitionId}.
   * <ul>
   *   <li>Then calls {@link FileReference#getFilename()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReference#referenceForChildPartition(FileReference, String)}
   */
  @Test
  @DisplayName("Test referenceForChildPartition(FileReference, String) with 'file', 'childPartitionId'; then calls getFilename()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReference SplitFileReference.referenceForChildPartition(FileReference, String)"})
  void testReferenceForChildPartitionWithFileChildPartitionId_thenCallsGetFilename() {
    // Arrange
    FileReference file = mock(FileReference.class);
    when(file.getNumberOfRecords()).thenReturn(1L);
    when(file.getFilename()).thenReturn("foo.txt");

    // Act
    FileReference actualReferenceForChildPartitionResult = SplitFileReference.referenceForChildPartition(file, "42");

    // Assert
    verify(file).getFilename();
    verify(file).getNumberOfRecords();
    assertEquals("42", actualReferenceForChildPartitionResult.getPartitionId());
    assertEquals("foo.txt", actualReferenceForChildPartitionResult.getFilename());
    assertNull(actualReferenceForChildPartitionResult.getJobId());
    assertNull(actualReferenceForChildPartitionResult.getLastStateStoreUpdateTime());
    assertEquals(0L, actualReferenceForChildPartitionResult.getNumberOfRecords().longValue());
    assertFalse(actualReferenceForChildPartitionResult.onlyContainsDataForThisPartition());
    assertTrue(actualReferenceForChildPartitionResult.isCountApproximate());
  }
}
