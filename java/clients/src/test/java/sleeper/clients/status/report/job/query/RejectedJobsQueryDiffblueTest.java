package sleeper.clients.status.report.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.job.IngestJobTracker;

class RejectedJobsQueryDiffblueTest {
  /**
   * Test {@link RejectedJobsQuery#run(CompactionJobTracker)} with {@code CompactionJobTracker}.
   * <p>
   * Method under test: {@link RejectedJobsQuery#run(CompactionJobTracker)}
   */
  @Test
  @DisplayName("Test run(CompactionJobTracker) with 'CompactionJobTracker'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List RejectedJobsQuery.run(CompactionJobTracker)"})
  void testRunWithCompactionJobTracker() {
    // Arrange
    RejectedJobsQuery rejectedJobsQuery = new RejectedJobsQuery();

    // Act and Assert
    assertTrue(rejectedJobsQuery.run(new InMemoryCompactionJobTracker()).isEmpty());
  }

  /**
   * Test {@link RejectedJobsQuery#run(IngestJobTracker)} with {@code IngestJobTracker}.
   * <ul>
   *   <li>When {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RejectedJobsQuery#run(IngestJobTracker)}
   */
  @Test
  @DisplayName("Test run(IngestJobTracker) with 'IngestJobTracker'; when InMemoryIngestJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List RejectedJobsQuery.run(IngestJobTracker)"})
  void testRunWithIngestJobTracker_whenInMemoryIngestJobTracker_thenReturnEmpty() {
    // Arrange
    RejectedJobsQuery rejectedJobsQuery = new RejectedJobsQuery();

    // Act and Assert
    assertTrue(rejectedJobsQuery.run(new InMemoryIngestJobTracker()).isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link RejectedJobsQuery}
   *   <li>{@link RejectedJobsQuery#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RejectedJobsQuery.<init>()", "Type RejectedJobsQuery.getType()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(Type.REJECTED, (new RejectedJobsQuery()).getType());
  }
}
