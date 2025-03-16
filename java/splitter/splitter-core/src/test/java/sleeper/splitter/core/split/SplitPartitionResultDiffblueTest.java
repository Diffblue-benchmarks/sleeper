package sleeper.splitter.core.split;

import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.partition.Partition;
import sleeper.core.partition.Partition.Builder;
import sleeper.core.range.Region;

class SplitPartitionResultDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitPartitionResult#SplitPartitionResult(Partition, Partition, Partition)}
   *   <li>{@link SplitPartitionResult#getLeftChild()}
   *   <li>{@link SplitPartitionResult#getParentPartition()}
   *   <li>{@link SplitPartitionResult#getRightChild()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionResult.<init>(Partition, Partition, Partition)",
      "Partition SplitPartitionResult.getLeftChild()", "Partition SplitPartitionResult.getParentPartition()",
      "Partition SplitPartitionResult.getRightChild()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parentPartition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition leftChild = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    Builder builderResult3 = Partition.builder();
    Builder parentPartitionIdResult3 = builderResult3.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition rightChild = parentPartitionIdResult3.region(new Region(new ArrayList<>())).build();

    // Act
    SplitPartitionResult actualSplitPartitionResult = new SplitPartitionResult(parentPartition, leftChild, rightChild);
    Partition actualLeftChild = actualSplitPartitionResult.getLeftChild();
    Partition actualParentPartition = actualSplitPartitionResult.getParentPartition();

    // Assert
    assertSame(leftChild, actualLeftChild);
    assertSame(parentPartition, actualParentPartition);
    assertSame(rightChild, actualSplitPartitionResult.getRightChild());
  }
}
