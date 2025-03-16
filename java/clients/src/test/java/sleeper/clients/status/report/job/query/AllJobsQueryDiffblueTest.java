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

class AllJobsQueryDiffblueTest {
  /**
   * Test {@link AllJobsQuery#AllJobsQuery(TableStatus)}.
   * <ul>
   *   <li>Then return Type is {@code ALL}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllJobsQuery#AllJobsQuery(TableStatus)}
   */
  @Test
  @DisplayName("Test new AllJobsQuery(TableStatus); then return Type is 'ALL'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AllJobsQuery.<init>(TableStatus)"})
  void testNewAllJobsQuery_thenReturnTypeIsAll() {
    // Arrange, Act and Assert
    assertEquals(Type.ALL, (new AllJobsQuery(TableStatus.uniqueIdAndName("42", "Table Name", true))).getType());
  }

  /**
   * Test {@link AllJobsQuery#run(CompactionJobTracker)} with {@code CompactionJobTracker}.
   * <ul>
   *   <li>When {@link InMemoryCompactionJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllJobsQuery#run(CompactionJobTracker)}
   */
  @Test
  @DisplayName("Test run(CompactionJobTracker) with 'CompactionJobTracker'; when InMemoryCompactionJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List AllJobsQuery.run(CompactionJobTracker)"})
  void testRunWithCompactionJobTracker_whenInMemoryCompactionJobTracker_thenReturnEmpty() {
    // Arrange
    AllJobsQuery allJobsQuery = new AllJobsQuery(TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Act and Assert
    assertTrue(allJobsQuery.run(new InMemoryCompactionJobTracker()).isEmpty());
  }

  /**
   * Test {@link AllJobsQuery#run(IngestJobTracker)} with {@code IngestJobTracker}.
   * <ul>
   *   <li>When {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllJobsQuery#run(IngestJobTracker)}
   */
  @Test
  @DisplayName("Test run(IngestJobTracker) with 'IngestJobTracker'; when InMemoryIngestJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List AllJobsQuery.run(IngestJobTracker)"})
  void testRunWithIngestJobTracker_whenInMemoryIngestJobTracker_thenReturnEmpty() {
    // Arrange
    AllJobsQuery allJobsQuery = new AllJobsQuery(TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Act and Assert
    assertTrue(allJobsQuery.run(new InMemoryIngestJobTracker()).isEmpty());
  }

  /**
   * Test {@link AllJobsQuery#getType()}.
   * <p>
   * Method under test: {@link AllJobsQuery#getType()}
   */
  @Test
  @DisplayName("Test getType()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type AllJobsQuery.getType()"})
  void testGetType() {
    // Arrange, Act and Assert
    assertEquals(Type.ALL, (new AllJobsQuery(TableStatus.uniqueIdAndName("42", "Table Name", true))).getType());
  }
}
