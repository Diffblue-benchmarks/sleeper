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

class PendingFilesQueryDiffblueTest {
  /**
   * Test {@link PendingFilesQuery#run(IngestBatcherStore)}.
   * <ul>
   *   <li>When {@link InMemoryIngestBatcherStore} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link PendingFilesQuery#run(IngestBatcherStore)}
   */
  @Test
  @DisplayName("Test run(IngestBatcherStore); when InMemoryIngestBatcherStore (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List PendingFilesQuery.run(IngestBatcherStore)"})
  void testRun_whenInMemoryIngestBatcherStore_thenReturnEmpty() {
    // Arrange
    PendingFilesQuery pendingFilesQuery = new PendingFilesQuery();

    // Act and Assert
    assertTrue(pendingFilesQuery.run(new InMemoryIngestBatcherStore()).isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link PendingFilesQuery}
   *   <li>{@link PendingFilesQuery#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PendingFilesQuery.<init>()", "Type PendingFilesQuery.getType()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(Type.PENDING, (new PendingFilesQuery()).getType());
  }
}
