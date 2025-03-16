package sleeper.ingest.runner.impl.partitionfilewriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.FileReference;

class PartitionFileWriterUtilsDiffblueTest {
  /**
   * Test {@link PartitionFileWriterUtils#createFileReference(String, String, long)}.
   * <p>
   * Method under test: {@link PartitionFileWriterUtils#createFileReference(String, String, long)}
   */
  @Test
  @DisplayName("Test createFileReference(String, String, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReference PartitionFileWriterUtils.createFileReference(String, String, long)"})
  void testCreateFileReference() {
    // Arrange and Act
    FileReference actualCreateFileReferenceResult = PartitionFileWriterUtils.createFileReference("foo.txt", "42", 1L);

    // Assert
    assertEquals("42", actualCreateFileReferenceResult.getPartitionId());
    assertEquals("foo.txt", actualCreateFileReferenceResult.getFilename());
    assertNull(actualCreateFileReferenceResult.getJobId());
    assertNull(actualCreateFileReferenceResult.getLastStateStoreUpdateTime());
    assertEquals(1L, actualCreateFileReferenceResult.getNumberOfRecords().longValue());
    assertFalse(actualCreateFileReferenceResult.isCountApproximate());
    assertTrue(actualCreateFileReferenceResult.onlyContainsDataForThisPartition());
  }
}
