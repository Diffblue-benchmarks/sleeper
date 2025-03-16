package sleeper.systemtest.dsl.ingest;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.testutils.InMemoryTransactionLogsPerTable;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.ingest.batcher.core.testutil.InMemoryIngestBatcherStore;
import sleeper.query.core.recordretrieval.InMemoryDataStore;
import sleeper.systemtest.dsl.SystemTestContext;
import sleeper.systemtest.dsl.SystemTestDrivers;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.testutil.InMemorySystemTestDrivers;
import sleeper.systemtest.dsl.testutil.drivers.InMemoryIngestBatcherDriver;
import sleeper.systemtest.dsl.testutil.drivers.InMemoryIngestByQueue;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySketchesStore;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperInstanceDriver;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperTablesDriver;
import sleeper.systemtest.dsl.util.PollWithRetriesDriver;
import sleeper.systemtest.dsl.util.TestContext;
import sleeper.systemtest.dsl.util.WaitForTasks;

class SystemTestIngestBatcherDiffblueTest {
  /**
   * Test {@link SystemTestIngestBatcher#SystemTestIngestBatcher(SystemTestContext, SystemTestDrivers)}.
   * <ul>
   *   <li>Then calls {@link SystemTestDrivers#instance(SystemTestParameters)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestBatcher#SystemTestIngestBatcher(SystemTestContext, SystemTestDrivers)}
   */
  @Test
  @DisplayName("Test new SystemTestIngestBatcher(SystemTestContext, SystemTestDrivers); then calls instance(SystemTestParameters)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestIngestBatcher.<init>(SystemTestContext, SystemTestDrivers)"})
  void testNewSystemTestIngestBatcher_thenCallsInstance() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));
    SystemTestContext context = new SystemTestContext(parameters, drivers, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class));

    // Act
    new SystemTestIngestBatcher(context, new InMemorySystemTestDrivers());

    // Assert
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(parameters).getOutputDirectory();
  }

  /**
   * Test {@link SystemTestIngestBatcher#waitForStandardIngestTask()}.
   * <p>
   * Method under test: {@link SystemTestIngestBatcher#waitForStandardIngestTask()}
   */
  @Test
  @DisplayName("Test waitForStandardIngestTask()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestBatcher SystemTestIngestBatcher.waitForStandardIngestTask()"})
  void testWaitForStandardIngestTask() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));
    SystemTestContext context = new SystemTestContext(parameters, drivers, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class));

    SystemTestParameters parameters2 = mock(SystemTestParameters.class);
    when(parameters2.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers2 = mock(SystemTestDrivers.class);
    when(drivers2.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));
    SystemTestContext context2 = new SystemTestContext(parameters2, drivers2, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class));

    InMemoryIngestBatcherStore store = new InMemoryIngestBatcherStore();
    InMemoryDataStore sourceFiles = new InMemoryDataStore();
    InMemoryDataStore data = new InMemoryDataStore();
    InMemoryIngestBatcherDriver inMemoryIngestBatcherDriver = new InMemoryIngestBatcherDriver(context2, store,
        new InMemoryIngestByQueue(sourceFiles, data, new InMemorySketchesStore()), 3L);

    WaitForTasks waitForTasks = mock(WaitForTasks.class);
    doNothing().when(waitForTasks)
        .waitUntilOneTaskStartedAJob(Mockito.<List<String>>any(), Mockito.<PollWithRetriesDriver>any());
    IngestTasksDriver ingestTasksDriver = mock(IngestTasksDriver.class);
    when(ingestTasksDriver.waitForTasksForCurrentInstance()).thenReturn(waitForTasks);
    InMemorySystemTestDrivers drivers3 = mock(InMemorySystemTestDrivers.class);
    when(drivers3.ingestBatcher(Mockito.<SystemTestContext>any())).thenReturn(inMemoryIngestBatcherDriver);
    when(drivers3.ingestTasks(Mockito.<SystemTestContext>any())).thenReturn(ingestTasksDriver);
    when(drivers3.pollWithRetries()).thenReturn(mock(PollWithRetriesDriver.class));
    when(drivers3.waitForBulkImport(Mockito.<SystemTestContext>any())).thenReturn(null);
    when(drivers3.waitForIngest(Mockito.<SystemTestContext>any())).thenReturn(null);
    SystemTestIngestBatcher systemTestIngestBatcher = new SystemTestIngestBatcher(context, drivers3);

    // Act
    SystemTestIngestBatcher actualWaitForStandardIngestTaskResult = systemTestIngestBatcher.waitForStandardIngestTask();

    // Assert
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(drivers2).instance(isA(SystemTestParameters.class));
    verify(ingestTasksDriver).waitForTasksForCurrentInstance();
    verify(parameters).getOutputDirectory();
    verify(parameters2).getOutputDirectory();
    verify(drivers3).ingestBatcher(isA(SystemTestContext.class));
    verify(drivers3).ingestTasks(isA(SystemTestContext.class));
    verify(drivers3).pollWithRetries();
    verify(drivers3).waitForBulkImport(isA(SystemTestContext.class));
    verify(drivers3).waitForIngest(isA(SystemTestContext.class));
    verify(waitForTasks).waitUntilOneTaskStartedAJob(isA(List.class), isA(PollWithRetriesDriver.class));
    assertSame(systemTestIngestBatcher, actualWaitForStandardIngestTaskResult);
  }

  /**
   * Test {@link SystemTestIngestBatcher#waitForStandardIngestTask()}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestBatcher#waitForStandardIngestTask()}
   */
  @Test
  @DisplayName("Test waitForStandardIngestTask(); then throw IllegalStateException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestIngestBatcher SystemTestIngestBatcher.waitForStandardIngestTask()"})
  void testWaitForStandardIngestTask_thenThrowIllegalStateException() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));
    SystemTestContext context = new SystemTestContext(parameters, drivers, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class));

    SystemTestParameters parameters2 = mock(SystemTestParameters.class);
    when(parameters2.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers2 = mock(SystemTestDrivers.class);
    when(drivers2.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));
    SystemTestContext context2 = new SystemTestContext(parameters2, drivers2, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class));

    InMemoryIngestBatcherStore store = new InMemoryIngestBatcherStore();
    InMemoryDataStore sourceFiles = new InMemoryDataStore();
    InMemoryDataStore data = new InMemoryDataStore();
    InMemoryIngestBatcherDriver inMemoryIngestBatcherDriver = new InMemoryIngestBatcherDriver(context2, store,
        new InMemoryIngestByQueue(sourceFiles, data, new InMemorySketchesStore()), 3L);

    IngestTasksDriver ingestTasksDriver = mock(IngestTasksDriver.class);
    when(ingestTasksDriver.waitForTasksForCurrentInstance())
        .thenReturn(new WaitForTasks(new InMemoryCompactionJobTracker()));
    PollWithRetriesDriver pollWithRetriesDriver = mock(PollWithRetriesDriver.class);
    when(pollWithRetriesDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenThrow(new IllegalStateException("Need jobs to wait for before invoking tasks, none are yet specified"));
    InMemorySystemTestDrivers drivers3 = mock(InMemorySystemTestDrivers.class);
    when(drivers3.ingestBatcher(Mockito.<SystemTestContext>any())).thenReturn(inMemoryIngestBatcherDriver);
    when(drivers3.ingestTasks(Mockito.<SystemTestContext>any())).thenReturn(ingestTasksDriver);
    when(drivers3.pollWithRetries()).thenReturn(pollWithRetriesDriver);
    when(drivers3.waitForBulkImport(Mockito.<SystemTestContext>any())).thenReturn(null);
    when(drivers3.waitForIngest(Mockito.<SystemTestContext>any())).thenReturn(null);

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new SystemTestIngestBatcher(context, drivers3)).waitForStandardIngestTask());
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(drivers2).instance(isA(SystemTestParameters.class));
    verify(ingestTasksDriver).waitForTasksForCurrentInstance();
    verify(parameters).getOutputDirectory();
    verify(parameters2).getOutputDirectory();
    verify(drivers3).ingestBatcher(isA(SystemTestContext.class));
    verify(drivers3).ingestTasks(isA(SystemTestContext.class));
    verify(drivers3).pollWithRetries();
    verify(drivers3).waitForBulkImport(isA(SystemTestContext.class));
    verify(drivers3).waitForIngest(isA(SystemTestContext.class));
    verify(pollWithRetriesDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
  }
}
