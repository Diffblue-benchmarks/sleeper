package sleeper.systemtest.drivers.partitioning;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.PartitionStore;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.reporting.SystemTestReport;

class AwsPartitionReportDriverDiffblueTest {
  /**
   * Test {@link AwsPartitionReportDriver#statusReport()}.
   * <ul>
   *   <li>Then calls {@link PartitionStore#getAllPartitions()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsPartitionReportDriver#statusReport()}
   */
  @Test
  @DisplayName("Test statusReport(); then calls getAllPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestReport AwsPartitionReportDriver.statusReport()"})
  void testStatusReport_thenCallsGetAllPartitions() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));
    when(instance.getStateStore()).thenReturn(stateStore);

    // Act
    SystemTestReport actualStatusReportResult = (new AwsPartitionReportDriver(instance)).statusReport();
    PrintStream printStream = new PrintStream(new ByteArrayOutputStream(1));
    actualStatusReportResult.print(printStream,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    verify(stateStore).getAllPartitions();
    verify(instance).getStateStore();
    verify(instance).getTableProperties();
  }
}
