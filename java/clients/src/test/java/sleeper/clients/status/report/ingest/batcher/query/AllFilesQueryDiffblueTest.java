package sleeper.clients.status.report.ingest.batcher.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.ingest.batcher.BatcherQuery;
import sleeper.clients.status.report.ingest.batcher.BatcherQuery.Type;
import sleeper.ingest.batcher.core.IngestBatcherStore;
import sleeper.ingest.batcher.core.testutil.InMemoryIngestBatcherStore;

class AllFilesQueryDiffblueTest {
  /**
   * Test {@link AllFilesQuery#run(IngestBatcherStore)}.
   * <ul>
   *   <li>When {@link InMemoryIngestBatcherStore} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllFilesQuery#run(IngestBatcherStore)}
   */
  @Test
  @DisplayName("Test run(IngestBatcherStore); when InMemoryIngestBatcherStore (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List AllFilesQuery.run(IngestBatcherStore)"})
  void testRun_whenInMemoryIngestBatcherStore_thenReturnEmpty() {
    // Arrange
    AllFilesQuery allFilesQuery = new AllFilesQuery();

    // Act and Assert
    assertTrue(allFilesQuery.run(new InMemoryIngestBatcherStore()).isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link AllFilesQuery}
   *   <li>{@link AllFilesQuery#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AllFilesQuery.<init>()", "Type AllFilesQuery.getType()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(Type.ALL, (new AllFilesQuery()).getType());
  }
}
