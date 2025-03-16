package sleeper.systemtest.dsl.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.PollWithRetries.CheckFailedException;
import sleeper.core.util.ThreadSleep;

class WaitForTasksDiffblueTest {
  /**
   * Test {@link WaitForTasks#waitUntilOneTaskStartedAJob(List, PollWithRetriesDriver)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilOneTaskStartedAJob(List, PollWithRetriesDriver)}
   */
  @Test
  @DisplayName("Test waitUntilOneTaskStartedAJob(List, PollWithRetriesDriver); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilOneTaskStartedAJob(List, PollWithRetriesDriver)"})
  void testWaitUntilOneTaskStartedAJob_thenThrowRuntimeException() throws InterruptedException {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryCompactionJobTracker());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("Need jobs to wait for before invoking tasks, none are yet specified");
    ThreadSleep sleepInInterval = mock(ThreadSleep.class);
    doThrow(new InterruptedException("Found {} tasks with runs for given jobs")).when(sleepInInterval)
        .waitForMillis(anyLong());
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(sleepInInterval)
        .build();
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenReturn(buildResult);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> waitForTasks.waitUntilOneTaskStartedAJob(jobIds, pollDriver));
    verify(sleepInInterval).waitForMillis(eq(42L));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
  }

  /**
   * Test {@link WaitForTasks#waitUntilOneTaskStartedAJob(List, PollWithRetriesDriver)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilOneTaskStartedAJob(List, PollWithRetriesDriver)}
   */
  @Test
  @DisplayName("Test waitUntilOneTaskStartedAJob(List, PollWithRetriesDriver); when ArrayList(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilOneTaskStartedAJob(List, PollWithRetriesDriver)"})
  void testWaitUntilOneTaskStartedAJob_whenArrayList_thenThrowIllegalArgumentException() {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryCompactionJobTracker());
    ArrayList<String> jobIds = new ArrayList<>();
    PollWithRetriesDriver pollDriver = mock(PollWithRetriesDriver.class);
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    when(pollDriver.pollWithIntervalAndTimeout(Mockito.<Duration>any(), Mockito.<Duration>any()))
        .thenReturn(buildResult);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> waitForTasks.waitUntilOneTaskStartedAJob(jobIds, pollDriver));
    verify(pollDriver).pollWithIntervalAndTimeout(isA(Duration.class), isA(Duration.class));
  }

  /**
   * Test {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}.
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNumTasksStartedAJob(int, List, PollWithRetries)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilNumTasksStartedAJob(int, List, PollWithRetries)"})
  void testWaitUntilNumTasksStartedAJob() throws InterruptedException, CheckFailedException {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryCompactionJobTracker());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("Need jobs to wait for before invoking tasks, none are yet specified");
    PollWithRetries poll = mock(PollWithRetries.class);
    doNothing().when(poll).pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act
    waitForTasks.waitUntilNumTasksStartedAJob(1, jobIds, poll);

    // Assert
    verify(poll).pollUntil(eq("expected number of tasks have picked up a job"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}.
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNumTasksStartedAJob(int, List, PollWithRetries)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilNumTasksStartedAJob(int, List, PollWithRetries)"})
  void testWaitUntilNumTasksStartedAJob2() throws InterruptedException, CheckFailedException {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryIngestJobTracker());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("Need jobs to wait for before invoking tasks, none are yet specified");
    PollWithRetries poll = mock(PollWithRetries.class);
    doNothing().when(poll).pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act
    waitForTasks.waitUntilNumTasksStartedAJob(1, jobIds, poll);

    // Assert
    verify(poll).pollUntil(eq("expected number of tasks have picked up a job"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}.
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNumTasksStartedAJob(int, List, PollWithRetries)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilNumTasksStartedAJob(int, List, PollWithRetries)"})
  void testWaitUntilNumTasksStartedAJob3() throws InterruptedException, CheckFailedException {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryCompactionJobTracker());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("Need jobs to wait for before invoking tasks, none are yet specified");
    PollWithRetries poll = mock(PollWithRetries.class);
    doThrow(new IllegalArgumentException("Found {} tasks with runs for given jobs")).when(poll)
        .pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> waitForTasks.waitUntilNumTasksStartedAJob(1, jobIds, poll));
    verify(poll).pollUntil(eq("expected number of tasks have picked up a job"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}.
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNumTasksStartedAJob(int, List, PollWithRetries)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilNumTasksStartedAJob(int, List, PollWithRetries)"})
  void testWaitUntilNumTasksStartedAJob4() throws InterruptedException, CheckFailedException {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryCompactionJobTracker());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("foo");
    jobIds.add("Need jobs to wait for before invoking tasks, none are yet specified");
    PollWithRetries poll = mock(PollWithRetries.class);
    doThrow(new IllegalArgumentException("Found {} tasks with runs for given jobs")).when(poll)
        .pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> waitForTasks.waitUntilNumTasksStartedAJob(1, jobIds, poll));
    verify(poll).pollUntil(eq("expected number of tasks have picked up a job"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}.
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNumTasksStartedAJob(int, List, PollWithRetries)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilNumTasksStartedAJob(int, List, PollWithRetries)"})
  void testWaitUntilNumTasksStartedAJob5() throws InterruptedException, CheckFailedException {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryIngestJobTracker());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("foo");
    jobIds.add("Need jobs to wait for before invoking tasks, none are yet specified");
    PollWithRetries poll = mock(PollWithRetries.class);
    doNothing().when(poll).pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act
    waitForTasks.waitUntilNumTasksStartedAJob(1, jobIds, poll);

    // Assert
    verify(poll).pollUntil(eq("expected number of tasks have picked up a job"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}.
   * <ul>
   *   <li>Given {@code expected number of tasks have picked up a job}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNumTasksStartedAJob(int, List, PollWithRetries); given 'expected number of tasks have picked up a job'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilNumTasksStartedAJob(int, List, PollWithRetries)"})
  void testWaitUntilNumTasksStartedAJob_givenExpectedNumberOfTasksHavePickedUpAJob()
      throws InterruptedException, CheckFailedException {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryCompactionJobTracker());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("expected number of tasks have picked up a job");
    jobIds.add("Need jobs to wait for before invoking tasks, none are yet specified");
    PollWithRetries poll = mock(PollWithRetries.class);
    doNothing().when(poll).pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act
    waitForTasks.waitUntilNumTasksStartedAJob(1, jobIds, poll);

    // Assert
    verify(poll).pollUntil(eq("expected number of tasks have picked up a job"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}.
   * <ul>
   *   <li>Given {@code Found {} tasks with runs for given jobs}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNumTasksStartedAJob(int, List, PollWithRetries); given 'Found {} tasks with runs for given jobs'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilNumTasksStartedAJob(int, List, PollWithRetries)"})
  void testWaitUntilNumTasksStartedAJob_givenFoundTasksWithRunsForGivenJobs()
      throws InterruptedException, CheckFailedException {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryIngestJobTracker());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("Found {} tasks with runs for given jobs");
    jobIds.add("foo");
    jobIds.add("Need jobs to wait for before invoking tasks, none are yet specified");
    PollWithRetries poll = mock(PollWithRetries.class);
    doNothing().when(poll).pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act
    waitForTasks.waitUntilNumTasksStartedAJob(1, jobIds, poll);

    // Assert
    verify(poll).pollUntil(eq("expected number of tasks have picked up a job"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNumTasksStartedAJob(int, List, PollWithRetries); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilNumTasksStartedAJob(int, List, PollWithRetries)"})
  void testWaitUntilNumTasksStartedAJob_thenThrowRuntimeException() throws InterruptedException, CheckFailedException {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryCompactionJobTracker());

    ArrayList<String> jobIds = new ArrayList<>();
    jobIds.add("Need jobs to wait for before invoking tasks, none are yet specified");
    PollWithRetries poll = mock(PollWithRetries.class);
    doThrow(new InterruptedException("Found {} tasks with runs for given jobs")).when(poll)
        .pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> waitForTasks.waitUntilNumTasksStartedAJob(1, jobIds, poll));
    verify(poll).pollUntil(eq("expected number of tasks have picked up a job"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForTasks#waitUntilNumTasksStartedAJob(int, List, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNumTasksStartedAJob(int, List, PollWithRetries); when ArrayList(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForTasks.waitUntilNumTasksStartedAJob(int, List, PollWithRetries)"})
  void testWaitUntilNumTasksStartedAJob_whenArrayList_thenThrowIllegalArgumentException() {
    // Arrange
    WaitForTasks waitForTasks = new WaitForTasks(new InMemoryCompactionJobTracker());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> waitForTasks.waitUntilNumTasksStartedAJob(1, new ArrayList<>(), mock(PollWithRetries.class)));
  }
}
