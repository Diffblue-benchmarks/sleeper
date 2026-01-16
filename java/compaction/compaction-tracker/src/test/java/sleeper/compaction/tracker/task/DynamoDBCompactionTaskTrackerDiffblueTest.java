package sleeper.compaction.tracker.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DynamoDBCompactionTaskTrackerDiffblueTest {
  /**
   * Test {@link DynamoDBCompactionTaskTracker#taskStatusTableName(String)}.
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskTracker#taskStatusTableName(String)}
   */
  @Test
  @DisplayName("Test taskStatusTableName(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String DynamoDBCompactionTaskTracker.taskStatusTableName(String)"})
  void testTaskStatusTableName() {
    // Arrange, Act and Assert
    assertEquals(
        "sleeper-42-compaction-task-status",
        DynamoDBCompactionTaskTracker.taskStatusTableName("42"));
  }
}
