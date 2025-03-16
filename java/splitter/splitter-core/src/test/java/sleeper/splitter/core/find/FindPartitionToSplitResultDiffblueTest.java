package sleeper.splitter.core.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.partition.Partition;
import sleeper.core.partition.Partition.Builder;
import sleeper.core.range.Region;
import sleeper.core.statestore.FileReference;

class FindPartitionToSplitResultDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FindPartitionToSplitResult#FindPartitionToSplitResult(String, Partition, List)}
   *   <li>{@link FindPartitionToSplitResult#getPartition()}
   *   <li>{@link FindPartitionToSplitResult#getRelevantFiles()}
   *   <li>{@link FindPartitionToSplitResult#getTableId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FindPartitionToSplitResult.<init>(String, Partition, List)",
      "Partition FindPartitionToSplitResult.getPartition()", "List FindPartitionToSplitResult.getRelevantFiles()",
      "String FindPartitionToSplitResult.getTableId()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    ArrayList<FileReference> relevantFiles = new ArrayList<>();

    // Act
    FindPartitionToSplitResult actualFindPartitionToSplitResult = new FindPartitionToSplitResult("42", partition,
        relevantFiles);
    Partition actualPartition = actualFindPartitionToSplitResult.getPartition();
    List<FileReference> actualRelevantFiles = actualFindPartitionToSplitResult.getRelevantFiles();

    // Assert
    assertEquals("42", actualFindPartitionToSplitResult.getTableId());
    assertTrue(actualRelevantFiles.isEmpty());
    assertSame(relevantFiles, actualRelevantFiles);
    assertSame(partition, actualPartition);
  }
}
