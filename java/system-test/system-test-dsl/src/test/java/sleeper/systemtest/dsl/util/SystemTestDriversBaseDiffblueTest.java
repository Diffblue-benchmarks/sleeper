package sleeper.systemtest.dsl.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.testutils.InMemoryTransactionLogsPerTable;
import sleeper.systemtest.dsl.SystemTestContext;
import sleeper.systemtest.dsl.SystemTestDrivers;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.testutil.InMemorySystemTestDrivers;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperInstanceDriver;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperTablesDriver;

class SystemTestDriversBaseDiffblueTest {
  /**
   * Test {@link SystemTestDriversBase#pythonQuery(SystemTestContext)}.
   * <p>
   * Method under test: {@link SystemTestDriversBase#pythonQuery(SystemTestContext)}
   */
  @Test
  @DisplayName("Test pythonQuery(SystemTestContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.systemtest.dsl.python.PythonQueryTypesDriver SystemTestDriversBase.pythonQuery(SystemTestContext)"})
  void testPythonQuery() {
    // Arrange
    InMemorySystemTestDrivers inMemorySystemTestDrivers = new InMemorySystemTestDrivers();
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> inMemorySystemTestDrivers.pythonQuery(new SystemTestContext(parameters, drivers,
            mock(DeployedSystemTestResources.class), mock(DeployedSleeperInstances.class), mock(TestContext.class))));
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(parameters).getOutputDirectory();
  }
}
