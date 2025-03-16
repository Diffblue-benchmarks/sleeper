package sleeper.clients.status.report.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.job.IngestJobTracker;

class UnfinishedJobsQueryDiffblueTest {
  /**
   * Test {@link UnfinishedJobsQuery#UnfinishedJobsQuery(TableStatus)}.
   * <ul>
   *   <li>Then return Type is {@code UNFINISHED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UnfinishedJobsQuery#UnfinishedJobsQuery(TableStatus)}
   */
  @Test
  @DisplayName("Test new UnfinishedJobsQuery(TableStatus); then return Type is 'UNFINISHED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UnfinishedJobsQuery.<init>(TableStatus)"})
  void testNewUnfinishedJobsQuery_thenReturnTypeIsUnfinished() {
    // Arrange, Act and Assert
    assertEquals(Type.UNFINISHED,
        (new UnfinishedJobsQuery(TableStatus.uniqueIdAndName("42", "Table Name", true))).getType());
  }

  /**
   * Test {@link UnfinishedJobsQuery#run(CompactionJobTracker)} with {@code CompactionJobTracker}.
   * <ul>
   *   <li>When {@link InMemoryCompactionJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link UnfinishedJobsQuery#run(CompactionJobTracker)}
   */
  @Test
  @DisplayName("Test run(CompactionJobTracker) with 'CompactionJobTracker'; when InMemoryCompactionJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List UnfinishedJobsQuery.run(CompactionJobTracker)"})
  void testRunWithCompactionJobTracker_whenInMemoryCompactionJobTracker_thenReturnEmpty() {
    // Arrange
    UnfinishedJobsQuery unfinishedJobsQuery = new UnfinishedJobsQuery(
        TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Act and Assert
    assertTrue(unfinishedJobsQuery.run(new InMemoryCompactionJobTracker()).isEmpty());
  }

  /**
   * Test {@link UnfinishedJobsQuery#run(IngestJobTracker)} with {@code IngestJobTracker}.
   * <ul>
   *   <li>When {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link UnfinishedJobsQuery#run(IngestJobTracker)}
   */
  @Test
  @DisplayName("Test run(IngestJobTracker) with 'IngestJobTracker'; when InMemoryIngestJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List UnfinishedJobsQuery.run(IngestJobTracker)"})
  void testRunWithIngestJobTracker_whenInMemoryIngestJobTracker_thenReturnEmpty() {
    // Arrange
    UnfinishedJobsQuery unfinishedJobsQuery = new UnfinishedJobsQuery(
        TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Act and Assert
    assertTrue(unfinishedJobsQuery.run(new InMemoryIngestJobTracker()).isEmpty());
  }

  /**
   * Test {@link UnfinishedJobsQuery#getType()}.
   * <p>
   * Method under test: {@link UnfinishedJobsQuery#getType()}
   */
  @Test
  @DisplayName("Test getType()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type UnfinishedJobsQuery.getType()"})
  void testGetType() {
    // Arrange, Act and Assert
    assertEquals(Type.UNFINISHED,
        (new UnfinishedJobsQuery(TableStatus.uniqueIdAndName("42", "Table Name", true))).getType());
  }
}
