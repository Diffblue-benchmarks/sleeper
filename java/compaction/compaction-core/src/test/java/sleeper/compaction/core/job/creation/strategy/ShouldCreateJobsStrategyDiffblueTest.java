package sleeper.compaction.core.job.creation.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.core.statestore.FileReference;
import sleeper.core.table.TableStatus;

class ShouldCreateJobsStrategyDiffblueTest {
  /**
   * Test {@link ShouldCreateJobsStrategy#yes()}.
   * <p>
   * Method under test: {@link ShouldCreateJobsStrategy#yes()}
   */
  @Test
  @DisplayName("Test yes()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ShouldCreateJobsStrategy ShouldCreateJobsStrategy.yes()"})
  void testYes() {
    // Arrange and Act
    ShouldCreateJobsStrategy actualYesResult = ShouldCreateJobsStrategy.yes();
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();

    // Assert
    assertEquals(Long.MAX_VALUE, actualYesResult.maxCompactionJobsToCreate(
        new FilesInPartition(tableStatus, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>())));
  }
}
