package sleeper.systemtest.dsl.statestore;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.testutils.InMemoryTransactionLogsPerTable;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;
import sleeper.systemtest.dsl.SystemTestContext;
import sleeper.systemtest.dsl.SystemTestDrivers;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperInstanceDriver;
import sleeper.systemtest.dsl.testutil.drivers.InMemorySleeperTablesDriver;
import sleeper.systemtest.dsl.util.PollWithRetriesDriver;
import sleeper.systemtest.dsl.util.TestContext;

class SystemTestStateStoreFakeCommitsDiffblueTest {
  /**
   * Test {@link SystemTestStateStoreFakeCommits#SystemTestStateStoreFakeCommits(SystemTestContext, StateStoreCommitterDriver, StateStoreCommitterLogsDriver, PollWithRetriesDriver)}.
   * <ul>
   *   <li>Then calls {@link SystemTestDrivers#instance(SystemTestParameters)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestStateStoreFakeCommits#SystemTestStateStoreFakeCommits(SystemTestContext, StateStoreCommitterDriver, StateStoreCommitterLogsDriver, PollWithRetriesDriver)}
   */
  @Test
  @DisplayName("Test new SystemTestStateStoreFakeCommits(SystemTestContext, StateStoreCommitterDriver, StateStoreCommitterLogsDriver, PollWithRetriesDriver); then calls instance(SystemTestParameters)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SystemTestStateStoreFakeCommits.<init>(SystemTestContext, StateStoreCommitterDriver, StateStoreCommitterLogsDriver, PollWithRetriesDriver)"})
  void testNewSystemTestStateStoreFakeCommits_thenCallsInstance() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));

    // Act
    new SystemTestStateStoreFakeCommits(
        new SystemTestContext(parameters, drivers, mock(DeployedSystemTestResources.class),
            mock(DeployedSleeperInstances.class), mock(TestContext.class)),
        mock(StateStoreCommitterDriver.class), mock(StateStoreCommitterLogsDriver.class),
        mock(PollWithRetriesDriver.class));

    // Assert
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(parameters).getOutputDirectory();
  }

  /**
   * Test {@link SystemTestStateStoreFakeCommits#waitForCommitLogs()}.
   * <p>
   * Method under test: {@link SystemTestStateStoreFakeCommits#waitForCommitLogs()}
   */
  @Test
  @DisplayName("Test waitForCommitLogs()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestStateStoreFakeCommits SystemTestStateStoreFakeCommits.waitForCommitLogs()"})
  void testWaitForCommitLogs() throws InterruptedException {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));
    SystemTestContext context = new SystemTestContext(parameters, drivers, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class));

    StateStoreCommitterLogs stateStoreCommitterLogs = mock(StateStoreCommitterLogs.class);
    when(stateStoreCommitterLogs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(new HashMap<>());
    StateStoreCommitterLogsDriver logsDriver = mock(StateStoreCommitterLogsDriver.class);
    when(logsDriver.getLogsInPeriod(Mockito.<Instant>any(), Mockito.<Instant>any()))
        .thenReturn(stateStoreCommitterLogs);
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenReturn(buildResult);
    SystemTestStateStoreFakeCommits systemTestStateStoreFakeCommits = new SystemTestStateStoreFakeCommits(context,
        mock(StateStoreCommitterDriver.class), logsDriver, pollDriver);

    // Act
    SystemTestStateStoreFakeCommits actualWaitForCommitLogsResult = systemTestStateStoreFakeCommits.waitForCommitLogs();

    // Assert
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(parameters).getOutputDirectory();
    verify(stateStoreCommitterLogs).countNumCommitsByTableId(isA(Set.class));
    verify(logsDriver).getLogsInPeriod(isA(Instant.class), isA(Instant.class));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
    assertSame(systemTestStateStoreFakeCommits, actualWaitForCommitLogsResult);
  }

  /**
   * Test {@link SystemTestStateStoreFakeCommits#waitForCommitLogs()}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code all state store commits are applied} is five.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestStateStoreFakeCommits#waitForCommitLogs()}
   */
  @Test
  @DisplayName("Test waitForCommitLogs(); given HashMap() 'all state store commits are applied' is five")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestStateStoreFakeCommits SystemTestStateStoreFakeCommits.waitForCommitLogs()"})
  void testWaitForCommitLogs_givenHashMapAllStateStoreCommitsAreAppliedIsFive() throws InterruptedException {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));
    SystemTestContext context = new SystemTestContext(parameters, drivers, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class));

    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.put("all state store commits are applied", 5);
    stringIntegerMap.put("Waiting for commits by table ID: {}", 20);
    StateStoreCommitterLogs stateStoreCommitterLogs = mock(StateStoreCommitterLogs.class);
    when(stateStoreCommitterLogs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(stringIntegerMap);
    StateStoreCommitterLogsDriver logsDriver = mock(StateStoreCommitterLogsDriver.class);
    when(logsDriver.getLogsInPeriod(Mockito.<Instant>any(), Mockito.<Instant>any()))
        .thenReturn(stateStoreCommitterLogs);
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenReturn(buildResult);
    SystemTestStateStoreFakeCommits systemTestStateStoreFakeCommits = new SystemTestStateStoreFakeCommits(context,
        mock(StateStoreCommitterDriver.class), logsDriver, pollDriver);

    // Act
    SystemTestStateStoreFakeCommits actualWaitForCommitLogsResult = systemTestStateStoreFakeCommits.waitForCommitLogs();

    // Assert
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(parameters).getOutputDirectory();
    verify(stateStoreCommitterLogs).countNumCommitsByTableId(isA(Set.class));
    verify(logsDriver).getLogsInPeriod(isA(Instant.class), isA(Instant.class));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
    assertSame(systemTestStateStoreFakeCommits, actualWaitForCommitLogsResult);
  }

  /**
   * Test {@link SystemTestStateStoreFakeCommits#waitForCommitLogs()}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code Waiting for commits by table ID: {}} is twenty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestStateStoreFakeCommits#waitForCommitLogs()}
   */
  @Test
  @DisplayName("Test waitForCommitLogs(); given HashMap() 'Waiting for commits by table ID: {}' is twenty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestStateStoreFakeCommits SystemTestStateStoreFakeCommits.waitForCommitLogs()"})
  void testWaitForCommitLogs_givenHashMapWaitingForCommitsByTableIdIsTwenty() throws InterruptedException {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));
    SystemTestContext context = new SystemTestContext(parameters, drivers, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class));

    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.put("Waiting for commits by table ID: {}", 20);
    StateStoreCommitterLogs stateStoreCommitterLogs = mock(StateStoreCommitterLogs.class);
    when(stateStoreCommitterLogs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(stringIntegerMap);
    StateStoreCommitterLogsDriver logsDriver = mock(StateStoreCommitterLogsDriver.class);
    when(logsDriver.getLogsInPeriod(Mockito.<Instant>any(), Mockito.<Instant>any()))
        .thenReturn(stateStoreCommitterLogs);
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenReturn(buildResult);
    SystemTestStateStoreFakeCommits systemTestStateStoreFakeCommits = new SystemTestStateStoreFakeCommits(context,
        mock(StateStoreCommitterDriver.class), logsDriver, pollDriver);

    // Act
    SystemTestStateStoreFakeCommits actualWaitForCommitLogsResult = systemTestStateStoreFakeCommits.waitForCommitLogs();

    // Assert
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(parameters).getOutputDirectory();
    verify(stateStoreCommitterLogs).countNumCommitsByTableId(isA(Set.class));
    verify(logsDriver).getLogsInPeriod(isA(Instant.class), isA(Instant.class));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
    assertSame(systemTestStateStoreFakeCommits, actualWaitForCommitLogsResult);
  }

  /**
   * Test {@link SystemTestStateStoreFakeCommits#pauseReceivingCommitMessages()}.
   * <p>
   * Method under test: {@link SystemTestStateStoreFakeCommits#pauseReceivingCommitMessages()}
   */
  @Test
  @DisplayName("Test pauseReceivingCommitMessages()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestStateStoreFakeCommits SystemTestStateStoreFakeCommits.pauseReceivingCommitMessages()"})
  void testPauseReceivingCommitMessages() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));
    SystemTestContext context = new SystemTestContext(parameters, drivers, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class));

    StateStoreCommitterDriver driver = mock(StateStoreCommitterDriver.class);
    doNothing().when(driver).pauseReceivingMessages();
    SystemTestStateStoreFakeCommits systemTestStateStoreFakeCommits = new SystemTestStateStoreFakeCommits(context,
        driver, mock(StateStoreCommitterLogsDriver.class), mock(PollWithRetriesDriver.class));

    // Act
    SystemTestStateStoreFakeCommits actualPauseReceivingCommitMessagesResult = systemTestStateStoreFakeCommits
        .pauseReceivingCommitMessages();

    // Assert
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(parameters).getOutputDirectory();
    verify(driver).pauseReceivingMessages();
    assertSame(systemTestStateStoreFakeCommits, actualPauseReceivingCommitMessagesResult);
  }

  /**
   * Test {@link SystemTestStateStoreFakeCommits#resumeReceivingCommitMessages()}.
   * <p>
   * Method under test: {@link SystemTestStateStoreFakeCommits#resumeReceivingCommitMessages()}
   */
  @Test
  @DisplayName("Test resumeReceivingCommitMessages()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestStateStoreFakeCommits SystemTestStateStoreFakeCommits.resumeReceivingCommitMessages()"})
  void testResumeReceivingCommitMessages() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    when(parameters.getOutputDirectory()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestDrivers drivers = mock(SystemTestDrivers.class);
    when(drivers.instance(Mockito.<SystemTestParameters>any())).thenReturn(
        new InMemorySleeperInstanceDriver(new InMemorySleeperTablesDriver(new InMemoryTransactionLogsPerTable())));
    SystemTestContext context = new SystemTestContext(parameters, drivers, mock(DeployedSystemTestResources.class),
        mock(DeployedSleeperInstances.class), mock(TestContext.class));

    StateStoreCommitterDriver driver = mock(StateStoreCommitterDriver.class);
    doNothing().when(driver).resumeReceivingMessages();
    SystemTestStateStoreFakeCommits systemTestStateStoreFakeCommits = new SystemTestStateStoreFakeCommits(context,
        driver, mock(StateStoreCommitterLogsDriver.class), mock(PollWithRetriesDriver.class));

    // Act
    SystemTestStateStoreFakeCommits actualResumeReceivingCommitMessagesResult = systemTestStateStoreFakeCommits
        .resumeReceivingCommitMessages();

    // Assert
    verify(drivers).instance(isA(SystemTestParameters.class));
    verify(parameters).getOutputDirectory();
    verify(driver).resumeReceivingMessages();
    assertSame(systemTestStateStoreFakeCommits, actualResumeReceivingCommitMessagesResult);
  }
}
