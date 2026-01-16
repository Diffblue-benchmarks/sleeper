package sleeper.compaction.tracker.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DynamoDBCompactionJobTrackerDiffblueTest {
  /**
   * Test {@link DynamoDBCompactionJobTracker#jobUpdatesTableName(String)}.
   *
   * <p>Method under test: {@link DynamoDBCompactionJobTracker#jobUpdatesTableName(String)}
   */
  @Test
  @DisplayName("Test jobUpdatesTableName(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String DynamoDBCompactionJobTracker.jobUpdatesTableName(String)"})
  void testJobUpdatesTableName() {
    // Arrange, Act and Assert
    assertEquals(
        "sleeper-42-compaction-job-updates",
        DynamoDBCompactionJobTracker.jobUpdatesTableName("42"));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobLookupTableName(String)}.
   *
   * <p>Method under test: {@link DynamoDBCompactionJobTracker#jobLookupTableName(String)}
   */
  @Test
  @DisplayName("Test jobLookupTableName(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String DynamoDBCompactionJobTracker.jobLookupTableName(String)"})
  void testJobLookupTableName() {
    // Arrange, Act and Assert
    assertEquals(
        "sleeper-42-compaction-job-lookup", DynamoDBCompactionJobTracker.jobLookupTableName("42"));
  }
}
