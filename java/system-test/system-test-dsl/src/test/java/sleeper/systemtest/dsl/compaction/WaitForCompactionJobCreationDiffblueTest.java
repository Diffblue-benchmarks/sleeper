package sleeper.systemtest.dsl.compaction;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.PollWithRetries.CheckFailedException;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.util.TestContext;

class WaitForCompactionJobCreationDiffblueTest {
  /**
   * Test {@link WaitForCompactionJobCreation#WaitForCompactionJobCreation(SystemTestInstanceContext, CompactionDriver)}.
   * <ul>
   *   <li>Given {@link InMemoryCompactionJobTracker} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForCompactionJobCreation#WaitForCompactionJobCreation(SystemTestInstanceContext, CompactionDriver)}
   */
  @Test
  @DisplayName("Test new WaitForCompactionJobCreation(SystemTestInstanceContext, CompactionDriver); given InMemoryCompactionJobTracker (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForCompactionJobCreation.<init>(SystemTestInstanceContext, CompactionDriver)"})
  void testNewWaitForCompactionJobCreation_givenInMemoryCompactionJobTracker() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    CompactionDriver driver = mock(CompactionDriver.class);
    when(driver.getJobTracker()).thenReturn(new InMemoryCompactionJobTracker());

    // Act
    new WaitForCompactionJobCreation(instance, driver);

    // Assert
    verify(driver).getJobTracker();
  }

  /**
   * Test {@link WaitForCompactionJobCreation#WaitForCompactionJobCreation(SystemTestInstanceContext, CompactionDriver)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForCompactionJobCreation#WaitForCompactionJobCreation(SystemTestInstanceContext, CompactionDriver)}
   */
  @Test
  @DisplayName("Test new WaitForCompactionJobCreation(SystemTestInstanceContext, CompactionDriver); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForCompactionJobCreation.<init>(SystemTestInstanceContext, CompactionDriver)"})
  void testNewWaitForCompactionJobCreation_thenThrowRuntimeException() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    CompactionDriver driver = mock(CompactionDriver.class);
    when(driver.getJobTracker()).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new WaitForCompactionJobCreation(instance, driver));

    verify(driver).getJobTracker();
  }

  /**
   * Test {@link WaitForCompactionJobCreation#createJobsGetIds(int, PollWithRetries, Runnable)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForCompactionJobCreation#createJobsGetIds(int, PollWithRetries, Runnable)}
   */
  @Test
  @DisplayName("Test createJobsGetIds(int, PollWithRetries, Runnable); given ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List WaitForCompactionJobCreation.createJobsGetIds(int, PollWithRetries, Runnable)"})
  void testCreateJobsGetIds_givenArrayList_thenReturnEmpty() throws InterruptedException, CheckFailedException {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(tableProperties);
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.streamTableProperties()).thenReturn(streamResult);
    CompactionJobTracker compactionJobTracker = mock(CompactionJobTracker.class);

    ArrayList<CompactionJobStatus> compactionJobStatusList = new ArrayList<>();
    Stream<CompactionJobStatus> streamResult2 = compactionJobStatusList.stream();
    when(compactionJobTracker.streamAllJobs(Mockito.<String>any())).thenReturn(streamResult2);
    CompactionDriver driver = mock(CompactionDriver.class);
    when(driver.getJobTracker()).thenReturn(compactionJobTracker);
    WaitForCompactionJobCreation waitForCompactionJobCreation = new WaitForCompactionJobCreation(instance, driver);
    PollWithRetries poll = mock(PollWithRetries.class);
    when(poll.queryUntil(Mockito.<String>any(), Mockito.<Supplier<Object>>any(), Mockito.<Predicate<Object>>any()))
        .thenReturn(new ArrayList<>());
    Runnable createJobs = mock(Runnable.class);
    doNothing().when(createJobs).run();

    // Act
    List<String> actualCreateJobsGetIdsResult = waitForCompactionJobCreation.createJobsGetIds(1, poll, createJobs);

    // Assert
    verify(createJobs).run();
    verify(tableProperties).get(isA(TableProperty.class));
    verify(compactionJobTracker).streamAllJobs(eq("Get"));
    verify(poll).queryUntil(eq("compaction jobs were created"), isA(Supplier.class), isA(Predicate.class));
    verify(driver).getJobTracker();
    verify(instance).streamTableProperties();
    assertTrue(actualCreateJobsGetIdsResult.isEmpty());
  }

  /**
   * Test {@link WaitForCompactionJobCreation#createJobsGetIds(int, PollWithRetries, Runnable)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForCompactionJobCreation#createJobsGetIds(int, PollWithRetries, Runnable)}
   */
  @Test
  @DisplayName("Test createJobsGetIds(int, PollWithRetries, Runnable); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List WaitForCompactionJobCreation.createJobsGetIds(int, PollWithRetries, Runnable)"})
  void testCreateJobsGetIds_thenThrowRuntimeException() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    when(instance.streamTableProperties()).thenReturn(streamResult);
    CompactionDriver driver = mock(CompactionDriver.class);
    when(driver.getJobTracker()).thenReturn(new InMemoryCompactionJobTracker());
    WaitForCompactionJobCreation waitForCompactionJobCreation = new WaitForCompactionJobCreation(instance, driver);
    Runnable createJobs = mock(Runnable.class);
    doThrow(new RuntimeException("compaction jobs were created")).when(createJobs).run();

    // Act and Assert
    assertThrows(RuntimeException.class, () -> waitForCompactionJobCreation.createJobsGetIds(1, null, createJobs));
    verify(createJobs).run();
    verify(driver).getJobTracker();
    verify(instance).streamTableProperties();
  }
}
