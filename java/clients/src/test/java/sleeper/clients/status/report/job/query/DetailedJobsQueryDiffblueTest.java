package sleeper.clients.status.report.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.job.IngestJobTracker;

class DetailedJobsQueryDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DetailedJobsQuery#DetailedJobsQuery(List)}
   *   <li>{@link DetailedJobsQuery#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DetailedJobsQuery.<init>(List)", "Type DetailedJobsQuery.getType()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(Type.DETAILED, (new DetailedJobsQuery(new ArrayList<>())).getType());
  }

  /**
   * Test {@link DetailedJobsQuery#run(CompactionJobTracker)} with {@code CompactionJobTracker}.
   * <ul>
   *   <li>When {@link InMemoryCompactionJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DetailedJobsQuery#run(CompactionJobTracker)}
   */
  @Test
  @DisplayName("Test run(CompactionJobTracker) with 'CompactionJobTracker'; when InMemoryCompactionJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DetailedJobsQuery.run(CompactionJobTracker)"})
  void testRunWithCompactionJobTracker_whenInMemoryCompactionJobTracker_thenReturnEmpty() {
    // Arrange
    DetailedJobsQuery detailedJobsQuery = new DetailedJobsQuery(new ArrayList<>());

    // Act and Assert
    assertTrue(detailedJobsQuery.run(new InMemoryCompactionJobTracker()).isEmpty());
  }

  /**
   * Test {@link DetailedJobsQuery#run(IngestJobTracker)} with {@code IngestJobTracker}.
   * <ul>
   *   <li>When {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DetailedJobsQuery#run(IngestJobTracker)}
   */
  @Test
  @DisplayName("Test run(IngestJobTracker) with 'IngestJobTracker'; when InMemoryIngestJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DetailedJobsQuery.run(IngestJobTracker)"})
  void testRunWithIngestJobTracker_whenInMemoryIngestJobTracker_thenReturnEmpty() {
    // Arrange
    DetailedJobsQuery detailedJobsQuery = new DetailedJobsQuery(new ArrayList<>());

    // Act and Assert
    assertTrue(detailedJobsQuery.run(new InMemoryIngestJobTracker()).isEmpty());
  }

  /**
   * Test {@link DetailedJobsQuery#fromParameters(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DetailedJobsQuery#fromParameters(String)}
   */
  @Test
  @DisplayName("Test fromParameters(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery DetailedJobsQuery.fromParameters(String)"})
  void testFromParameters_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DetailedJobsQuery.fromParameters(""));
  }

  /**
   * Test {@link DetailedJobsQuery#fromParameters(String)}.
   * <ul>
   *   <li>When {@code Query Parameters}.</li>
   *   <li>Then return {@link DetailedJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DetailedJobsQuery#fromParameters(String)}
   */
  @Test
  @DisplayName("Test fromParameters(String); when 'Query Parameters'; then return DetailedJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery DetailedJobsQuery.fromParameters(String)"})
  void testFromParameters_whenQueryParameters_thenReturnDetailedJobsQuery() {
    // Arrange and Act
    JobQuery actualFromParametersResult = DetailedJobsQuery.fromParameters("Query Parameters");

    // Assert
    assertTrue(actualFromParametersResult instanceof DetailedJobsQuery);
    assertEquals(Type.DETAILED, actualFromParametersResult.getType());
  }
}
