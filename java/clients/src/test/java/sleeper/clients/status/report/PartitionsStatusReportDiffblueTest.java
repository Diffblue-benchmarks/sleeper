package sleeper.clients.status.report;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.partitions.PartitionsStatusReporter;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.PartitionStore;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;

class PartitionsStatusReportDiffblueTest {
  /**
   * Test {@link PartitionsStatusReport#run()}.
   * <ul>
   *   <li>Then calls {@link PartitionStore#getAllPartitions()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); then calls getAllPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionsStatusReport.run()"})
  void testRun_thenCallsGetAllPartitions() throws StateStoreException {
    // Arrange
    StateStore store = mock(StateStore.class);
    when(store.getAllPartitions()).thenReturn(new ArrayList<>());
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    (new PartitionsStatusReport(store, tableProperties,
        new PartitionsStatusReporter(new PrintStream(new ByteArrayOutputStream(1))))).run();

    // Assert
    verify(store).getAllPartitions();
  }
}
