package sleeper.systemtest.dsl.compaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.partition.PartitionTree;
import sleeper.systemtest.dsl.compaction.FoundCompactionJobs.FullCompactionCheckSummary;
import sleeper.systemtest.dsl.sourcedata.IngestSourceFilesContext;

class FoundCompactionJobsDiffblueTest {
  /**
   * Test {@link FoundCompactionJobs#checkFullCompactionWithPartitionsAndInputFiles(PartitionTree, String[])}.
   * <ul>
   *   <li>Then return numJobs is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link FoundCompactionJobs#checkFullCompactionWithPartitionsAndInputFiles(PartitionTree, String[])}
   */
  @Test
  @DisplayName("Test checkFullCompactionWithPartitionsAndInputFiles(PartitionTree, String[]); then return numJobs is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "FullCompactionCheckSummary FoundCompactionJobs.checkFullCompactionWithPartitionsAndInputFiles(PartitionTree, String[])"})
  void testCheckFullCompactionWithPartitionsAndInputFiles_thenReturnNumJobsIsZero() {
    // Arrange
    IngestSourceFilesContext sourceFiles = mock(IngestSourceFilesContext.class);
    when(sourceFiles.getFilePath(Mockito.<String>any())).thenReturn("/directory/foo.txt");
    FoundCompactionJobs fromResult = FoundCompactionJobs.from(sourceFiles, new ArrayList<>());
    PartitionTree partitions = mock(PartitionTree.class);
    when(partitions.getLeafPartitions()).thenReturn(new ArrayList<>());

    // Act
    FullCompactionCheckSummary actualCheckFullCompactionWithPartitionsAndInputFilesResult = fromResult
        .checkFullCompactionWithPartitionsAndInputFiles(partitions, "Input Files");

    // Assert
    verify(partitions).getLeafPartitions();
    verify(sourceFiles).getFilePath(eq("Input Files"));
    assertEquals(0, actualCheckFullCompactionWithPartitionsAndInputFilesResult.numJobs());
    assertEquals(0, actualCheckFullCompactionWithPartitionsAndInputFilesResult.numUncompactedPartitions());
    assertEquals(0, actualCheckFullCompactionWithPartitionsAndInputFilesResult.numUnexpectedPartitions());
    assertFalse(actualCheckFullCompactionWithPartitionsAndInputFilesResult.inputFilesMatched());
  }

  /**
   * Test {@link FoundCompactionJobs#expectedFullCompactionCheckWithJobs(int)}.
   * <p>
   * Method under test: {@link FoundCompactionJobs#expectedFullCompactionCheckWithJobs(int)}
   */
  @Test
  @DisplayName("Test expectedFullCompactionCheckWithJobs(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FullCompactionCheckSummary FoundCompactionJobs.expectedFullCompactionCheckWithJobs(int)"})
  void testExpectedFullCompactionCheckWithJobs() {
    // Arrange and Act
    FullCompactionCheckSummary actualExpectedFullCompactionCheckWithJobsResult = FoundCompactionJobs
        .expectedFullCompactionCheckWithJobs(10);

    // Assert
    assertEquals(0, actualExpectedFullCompactionCheckWithJobsResult.numUncompactedPartitions());
    assertEquals(0, actualExpectedFullCompactionCheckWithJobsResult.numUnexpectedPartitions());
    assertEquals(10, actualExpectedFullCompactionCheckWithJobsResult.numJobs());
    assertTrue(actualExpectedFullCompactionCheckWithJobsResult.inputFilesMatched());
  }
}
