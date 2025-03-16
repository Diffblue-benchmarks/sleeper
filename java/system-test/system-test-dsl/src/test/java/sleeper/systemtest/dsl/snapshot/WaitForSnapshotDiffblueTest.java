package sleeper.systemtest.dsl.snapshot;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.partition.PartitionTree;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.AllReferencesToAllFiles;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.util.NoSnapshotsDriver;

class WaitForSnapshotDiffblueTest {
  /**
   * Test {@link WaitForSnapshot#waitForFilesSnapshot(PollWithRetries, Predicate)}.
   * <ul>
   *   <li>Then return noFilesReport.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForSnapshot#waitForFilesSnapshot(PollWithRetries, Predicate)}
   */
  @Test
  @DisplayName("Test waitForFilesSnapshot(PollWithRetries, Predicate); then return noFilesReport")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles WaitForSnapshot.waitForFilesSnapshot(PollWithRetries, Predicate)"})
  void testWaitForFilesSnapshot_thenReturnNoFilesReport() throws InterruptedException {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    when(instance.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));
    NoSnapshotsDriver driver = mock(NoSnapshotsDriver.class);
    AllReferencesToAllFiles noFilesReportResult = FilesReportTestHelper.noFilesReport();
    Optional<AllReferencesToAllFiles> ofResult = Optional.of(noFilesReportResult);
    when(driver.loadLatestFilesSnapshot(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn(ofResult);
    WaitForSnapshot waitForSnapshot = new WaitForSnapshot(instance, driver);
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    Predicate<AllReferencesToAllFiles> condition = mock(Predicate.class);
    when(condition.test(Mockito.<AllReferencesToAllFiles>any())).thenReturn(true);

    // Act
    AllReferencesToAllFiles actualWaitForFilesSnapshotResult = waitForSnapshot.waitForFilesSnapshot(poll, condition);

    // Assert
    verify(condition).test(isA(AllReferencesToAllFiles.class));
    verify(instance).getInstanceProperties();
    verify(instance).getTableProperties();
    verify(driver).loadLatestFilesSnapshot(isA(InstanceProperties.class), isA(TableProperties.class));
    assertSame(noFilesReportResult, actualWaitForFilesSnapshotResult);
  }

  /**
   * Test {@link WaitForSnapshot#waitForPartitionsSnapshot(PollWithRetries, Predicate)}.
   * <ul>
   *   <li>Then calls {@link Predicate#test(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForSnapshot#waitForPartitionsSnapshot(PollWithRetries, Predicate)}
   */
  @Test
  @DisplayName("Test waitForPartitionsSnapshot(PollWithRetries, Predicate); then calls test(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionTree WaitForSnapshot.waitForPartitionsSnapshot(PollWithRetries, Predicate)"})
  void testWaitForPartitionsSnapshot_thenCallsTest() throws InterruptedException {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).addToListIfMissing(Mockito.<TableProperty>any(), Mockito.<List<String>>any());
    TableProperty tableProperty = mock(TableProperty.class);
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    when(instance.getTableProperties()).thenReturn(tableProperties);
    PartitionTree partitionTree = mock(PartitionTree.class);
    when(partitionTree.getAllPartitions()).thenReturn(new ArrayList<>());
    Optional<PartitionTree> ofResult = Optional.of(partitionTree);
    NoSnapshotsDriver driver = mock(NoSnapshotsDriver.class);
    when(driver.loadLatestPartitionsSnapshot(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn(ofResult);
    WaitForSnapshot waitForSnapshot = new WaitForSnapshot(instance, driver);
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    Predicate<PartitionTree> condition = mock(Predicate.class);
    when(condition.test(Mockito.<PartitionTree>any())).thenReturn(true);

    // Act
    waitForSnapshot.waitForPartitionsSnapshot(poll, condition);

    // Assert
    verify(condition).test(isA(PartitionTree.class));
    verify(partitionTree).getAllPartitions();
    verify(tableProperties).addToListIfMissing(isA(TableProperty.class), isA(List.class));
    verify(instance).getInstanceProperties();
    verify(instance).getTableProperties();
    verify(driver).loadLatestPartitionsSnapshot(isA(InstanceProperties.class), isA(TableProperties.class));
  }
}
