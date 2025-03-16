package sleeper.systemtest.dsl.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.job.IngestJobTracker;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;

class WaitForJobsDiffblueTest {
  /**
   * Test {@link WaitForJobs#waitForJobs(Collection, PollWithRetries, PollWithRetries)} with {@code jobIds}, {@code pollUntilJobsFinished}, {@code pollUntilJobsCommit}.
   * <p>
   * Method under test: {@link WaitForJobs#waitForJobs(Collection, PollWithRetries, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitForJobs(Collection, PollWithRetries, PollWithRetries) with 'jobIds', 'pollUntilJobsFinished', 'pollUntilJobsCommit'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForJobs.waitForJobs(Collection, PollWithRetries, PollWithRetries)"})
  void testWaitForJobsWithJobIdsPollUntilJobsFinishedPollUntilJobsCommit() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    Function<InstanceProperties, IngestJobTracker> getJobTracker = mock(Function.class);
    when(getJobTracker.apply(Mockito.<InstanceProperties>any()))
        .thenThrow(new RuntimeException("Waiting for {} jobs to finish: {}"));
    WaitForJobs forBulkImportResult = WaitForJobs.forBulkImport(instance, getJobTracker,
        mock(PollWithRetriesDriver.class));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> forBulkImportResult.waitForJobs(new ArrayList<>(), null, null));
    verify(getJobTracker).apply(isA(InstanceProperties.class));
    verify(instance).getInstanceProperties();
  }

  /**
   * Test {@link WaitForJobs#waitForJobs(Collection, PollWithRetries, PollWithRetries)} with {@code jobIds}, {@code pollUntilJobsFinished}, {@code pollUntilJobsCommit}.
   * <p>
   * Method under test: {@link WaitForJobs#waitForJobs(Collection, PollWithRetries, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitForJobs(Collection, PollWithRetries, PollWithRetries) with 'jobIds', 'pollUntilJobsFinished', 'pollUntilJobsCommit'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForJobs.waitForJobs(Collection, PollWithRetries, PollWithRetries)"})
  void testWaitForJobsWithJobIdsPollUntilJobsFinishedPollUntilJobsCommit2() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(null);
    Function<InstanceProperties, IngestJobTracker> getJobTracker = mock(Function.class);
    when(getJobTracker.apply(Mockito.<InstanceProperties>any())).thenReturn(new InMemoryIngestJobTracker());
    WaitForJobs forBulkImportResult = WaitForJobs.forBulkImport(instance, getJobTracker,
        mock(PollWithRetriesDriver.class));
    ArrayList<String> jobIds = new ArrayList<>();
    PollWithRetries pollUntilJobsFinished = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act
    forBulkImportResult.waitForJobs(jobIds, pollUntilJobsFinished, null);

    // Assert
    verify(getJobTracker).apply(isNull());
    verify(instance).getInstanceProperties();
  }

  /**
   * Test {@link WaitForJobs#waitForJobs(Collection, PollWithRetries, PollWithRetries)} with {@code jobIds}, {@code pollUntilJobsFinished}, {@code pollUntilJobsCommit}.
   * <ul>
   *   <li>Then calls {@link Function#apply(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForJobs#waitForJobs(Collection, PollWithRetries, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitForJobs(Collection, PollWithRetries, PollWithRetries) with 'jobIds', 'pollUntilJobsFinished', 'pollUntilJobsCommit'; then calls apply(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForJobs.waitForJobs(Collection, PollWithRetries, PollWithRetries)"})
  void testWaitForJobsWithJobIdsPollUntilJobsFinishedPollUntilJobsCommit_thenCallsApply() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    Function<InstanceProperties, IngestJobTracker> getJobTracker = mock(Function.class);
    when(getJobTracker.apply(Mockito.<InstanceProperties>any())).thenReturn(new InMemoryIngestJobTracker());
    WaitForJobs forBulkImportResult = WaitForJobs.forBulkImport(instance, getJobTracker,
        mock(PollWithRetriesDriver.class));
    ArrayList<String> jobIds = new ArrayList<>();
    PollWithRetries pollUntilJobsFinished = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act
    forBulkImportResult.waitForJobs(jobIds, pollUntilJobsFinished, null);

    // Assert
    verify(getJobTracker).apply(isA(InstanceProperties.class));
    verify(instance).getInstanceProperties();
  }

  /**
   * Test {@link WaitForJobs#waitForJobs(Collection, PollWithRetries)} with {@code jobIds}, {@code pollUntilJobsFinished}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForJobs#waitForJobs(Collection, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitForJobs(Collection, PollWithRetries) with 'jobIds', 'pollUntilJobsFinished'; given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForJobs.waitForJobs(Collection, PollWithRetries)"})
  void testWaitForJobsWithJobIdsPollUntilJobsFinished_given42_whenArrayListAdd42() {
    // Arrange
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenThrow(new RuntimeException("Not connected to a Sleeper instance"));
    WaitForJobs forCompactionResult = WaitForJobs.forCompaction(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)),
        mock(Function.class), mock(Function.class), pollDriver);

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("42");
    jobIds.add("foo");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> forCompactionResult.waitForJobs(jobIds, mock(PollWithRetries.class)));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
  }

  /**
   * Test {@link WaitForJobs#waitForJobs(Collection, PollWithRetries)} with {@code jobIds}, {@code pollUntilJobsFinished}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForJobs#waitForJobs(Collection, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitForJobs(Collection, PollWithRetries) with 'jobIds', 'pollUntilJobsFinished'; given 'foo'; when ArrayList() add 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForJobs.waitForJobs(Collection, PollWithRetries)"})
  void testWaitForJobsWithJobIdsPollUntilJobsFinished_givenFoo_whenArrayListAddFoo() {
    // Arrange
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenThrow(new RuntimeException("Not connected to a Sleeper instance"));
    WaitForJobs forCompactionResult = WaitForJobs.forCompaction(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)),
        mock(Function.class), mock(Function.class), pollDriver);

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("foo");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> forCompactionResult.waitForJobs(jobIds, mock(PollWithRetries.class)));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
  }

  /**
   * Test {@link WaitForJobs#waitForJobs(Collection, PollWithRetries)} with {@code jobIds}, {@code pollUntilJobsFinished}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForJobs#waitForJobs(Collection, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitForJobs(Collection, PollWithRetries) with 'jobIds', 'pollUntilJobsFinished'; when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForJobs.waitForJobs(Collection, PollWithRetries)"})
  void testWaitForJobsWithJobIdsPollUntilJobsFinished_whenArrayList() {
    // Arrange
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenThrow(new RuntimeException("Not connected to a Sleeper instance"));
    WaitForJobs forCompactionResult = WaitForJobs.forCompaction(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)),
        mock(Function.class), mock(Function.class), pollDriver);

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> forCompactionResult.waitForJobs(new ArrayList<>(), mock(PollWithRetries.class)));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
  }

  /**
   * Test {@link WaitForJobs#waitForJobs(Collection)} with {@code jobIds}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForJobs#waitForJobs(Collection)}
   */
  @Test
  @DisplayName("Test waitForJobs(Collection) with 'jobIds'; given '42'; when ArrayList() add '42'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForJobs.waitForJobs(Collection)"})
  void testWaitForJobsWithJobIds_given42_whenArrayListAdd42_thenThrowRuntimeException() {
    // Arrange
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenThrow(new RuntimeException("Not connected to a Sleeper instance"));
    WaitForJobs forCompactionResult = WaitForJobs.forCompaction(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)),
        mock(Function.class), mock(Function.class), pollDriver);

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("42");
    jobIds.add("foo");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> forCompactionResult.waitForJobs(jobIds));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
  }

  /**
   * Test {@link WaitForJobs#waitForJobs(Collection)} with {@code jobIds}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForJobs#waitForJobs(Collection)}
   */
  @Test
  @DisplayName("Test waitForJobs(Collection) with 'jobIds'; given 'foo'; when ArrayList() add 'foo'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForJobs.waitForJobs(Collection)"})
  void testWaitForJobsWithJobIds_givenFoo_whenArrayListAddFoo_thenThrowRuntimeException() {
    // Arrange
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenThrow(new RuntimeException("Not connected to a Sleeper instance"));
    WaitForJobs forCompactionResult = WaitForJobs.forCompaction(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)),
        mock(Function.class), mock(Function.class), pollDriver);

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("foo");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> forCompactionResult.waitForJobs(jobIds));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
  }

  /**
   * Test {@link WaitForJobs#waitForJobs(Collection)} with {@code jobIds}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForJobs#waitForJobs(Collection)}
   */
  @Test
  @DisplayName("Test waitForJobs(Collection) with 'jobIds'; when ArrayList(); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForJobs.waitForJobs(Collection)"})
  void testWaitForJobsWithJobIds_whenArrayList_thenThrowRuntimeException() {
    // Arrange
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenThrow(new RuntimeException("Not connected to a Sleeper instance"));
    WaitForJobs forCompactionResult = WaitForJobs.forCompaction(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)),
        mock(Function.class), mock(Function.class), pollDriver);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> forCompactionResult.waitForJobs(new ArrayList<>()));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
  }
}
