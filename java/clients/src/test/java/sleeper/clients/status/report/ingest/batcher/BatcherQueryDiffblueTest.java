package sleeper.clients.status.report.ingest.batcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.ingest.batcher.BatcherQuery.Type;
import sleeper.clients.status.report.ingest.batcher.query.AllFilesQuery;
import sleeper.clients.status.report.ingest.batcher.query.PendingFilesQuery;
import sleeper.clients.util.console.ConsoleInput;

class BatcherQueryDiffblueTest {
  /**
   * Test {@link BatcherQuery#from(Type, ConsoleInput)}.
   * <ul>
   *   <li>When {@link Type#ALL}.</li>
   *   <li>Then return {@link AllFilesQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BatcherQuery#from(Type, ConsoleInput)}
   */
  @Test
  @DisplayName("Test from(Type, ConsoleInput); when ALL; then return AllFilesQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BatcherQuery BatcherQuery.from(Type, ConsoleInput)"})
  void testFrom_whenAll_thenReturnAllFilesQuery() {
    // Arrange and Act
    BatcherQuery actualFromResult = BatcherQuery.from(Type.ALL, new ConsoleInput(null));

    // Assert
    assertTrue(actualFromResult instanceof AllFilesQuery);
    assertEquals(Type.ALL, actualFromResult.getType());
  }

  /**
   * Test {@link BatcherQuery#from(Type, ConsoleInput)}.
   * <ul>
   *   <li>When {@link Type#PENDING}.</li>
   *   <li>Then return {@link PendingFilesQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BatcherQuery#from(Type, ConsoleInput)}
   */
  @Test
  @DisplayName("Test from(Type, ConsoleInput); when PENDING; then return PendingFilesQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BatcherQuery BatcherQuery.from(Type, ConsoleInput)"})
  void testFrom_whenPending_thenReturnPendingFilesQuery() {
    // Arrange and Act
    BatcherQuery actualFromResult = BatcherQuery.from(Type.PENDING, new ConsoleInput(null));

    // Assert
    assertTrue(actualFromResult instanceof PendingFilesQuery);
    assertEquals(Type.PENDING, actualFromResult.getType());
  }
}
