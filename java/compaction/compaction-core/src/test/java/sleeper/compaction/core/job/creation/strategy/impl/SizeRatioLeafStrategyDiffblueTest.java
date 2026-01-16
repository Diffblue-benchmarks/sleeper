package sleeper.compaction.core.job.creation.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobFactory;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.FileReference;

class SizeRatioLeafStrategyDiffblueTest {
  /**
   * Test {@link SizeRatioLeafStrategy#init(InstanceProperties, TableProperties,
   * CompactionJobFactory)}.
   *
   * <ul>
   *   <li>Given {@link SizeRatioLeafStrategy} (default constructor).
   *   <li>Then calls {@link TableProperties#getInt(SleeperProperty)}.
   * </ul>
   *
   * <p>Method under test: {@link SizeRatioLeafStrategy#init(InstanceProperties, TableProperties,
   * CompactionJobFactory)}
   */
  @Test
  @DisplayName(
      "Test init(InstanceProperties, TableProperties, CompactionJobFactory); given SizeRatioLeafStrategy (default constructor); then calls getInt(SleeperProperty)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SizeRatioLeafStrategy.init(InstanceProperties, TableProperties, CompactionJobFactory)"
  })
  void testInit_givenSizeRatioLeafStrategy_thenCallsGetInt() {
    // Arrange
    SizeRatioLeafStrategy sizeRatioLeafStrategy = new SizeRatioLeafStrategy();
    InstanceProperties instanceProperties = new InstanceProperties();

    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory =
        new CompactionJobFactory(
            instanceProperties2, new TableProperties(new InstanceProperties()));

    // Act
    sizeRatioLeafStrategy.init(instanceProperties, tableProperties, factory);

    // Assert
    verify(tableProperties, atLeast(1)).getInt(Mockito.<TableProperty>any());
  }

  /**
   * Test {@link SizeRatioLeafStrategy#createJobsForLeafPartition(FilesInPartition)}.
   *
   * <p>Method under test: {@link
   * SizeRatioLeafStrategy#createJobsForLeafPartition(FilesInPartition)}
   */
  @Test
  @DisplayName("Test createJobsForLeafPartition(FilesInPartition)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List SizeRatioLeafStrategy.createJobsForLeafPartition(FilesInPartition)"})
  void testCreateJobsForLeafPartition() {
    // Arrange
    SizeRatioLeafStrategy sizeRatioLeafStrategy = new SizeRatioLeafStrategy();

    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    fileReferenceList.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    fileReferenceList.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());

    FilesInPartition filesInPartition = mock(FilesInPartition.class);
    when(filesInPartition.getPartitionId()).thenReturn("42");
    when(filesInPartition.getFilesWithNoJobIdInAscendingOrder()).thenReturn(fileReferenceList);

    // Act
    List<CompactionJob> actualCreateJobsForLeafPartitionResult =
        sizeRatioLeafStrategy.createJobsForLeafPartition(filesInPartition);

    // Assert
    verify(filesInPartition).getFilesWithNoJobIdInAscendingOrder();
    verify(filesInPartition).getPartitionId();
    assertTrue(actualCreateJobsForLeafPartitionResult.isEmpty());
  }

  /**
   * Test {@link SizeRatioLeafStrategy#createJobsForLeafPartition(FilesInPartition)}.
   *
   * <p>Method under test: {@link
   * SizeRatioLeafStrategy#createJobsForLeafPartition(FilesInPartition)}
   */
  @Test
  @DisplayName("Test createJobsForLeafPartition(FilesInPartition)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List SizeRatioLeafStrategy.createJobsForLeafPartition(FilesInPartition)"})
  void testCreateJobsForLeafPartition2() {
    // Arrange
    SizeRatioLeafStrategy sizeRatioLeafStrategy = new SizeRatioLeafStrategy();

    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    fileReferenceList.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    fileReferenceList.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(Long.MAX_VALUE)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    fileReferenceList.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());

    FilesInPartition filesInPartition = mock(FilesInPartition.class);
    when(filesInPartition.getPartitionId()).thenReturn("42");
    when(filesInPartition.getFilesWithNoJobIdInAscendingOrder()).thenReturn(fileReferenceList);

    // Act
    List<CompactionJob> actualCreateJobsForLeafPartitionResult =
        sizeRatioLeafStrategy.createJobsForLeafPartition(filesInPartition);

    // Assert
    verify(filesInPartition).getFilesWithNoJobIdInAscendingOrder();
    verify(filesInPartition).getPartitionId();
    assertTrue(actualCreateJobsForLeafPartitionResult.isEmpty());
  }

  /**
   * Test {@link SizeRatioLeafStrategy#createJobsForLeafPartition(FilesInPartition)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SizeRatioLeafStrategy#createJobsForLeafPartition(FilesInPartition)}
   */
  @Test
  @DisplayName("Test createJobsForLeafPartition(FilesInPartition); given ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List SizeRatioLeafStrategy.createJobsForLeafPartition(FilesInPartition)"})
  void testCreateJobsForLeafPartition_givenArrayList() {
    // Arrange
    SizeRatioLeafStrategy sizeRatioLeafStrategy = new SizeRatioLeafStrategy();

    FilesInPartition filesInPartition = mock(FilesInPartition.class);
    when(filesInPartition.getPartitionId()).thenReturn("42");
    when(filesInPartition.getFilesWithNoJobIdInAscendingOrder()).thenReturn(new ArrayList<>());

    // Act
    List<CompactionJob> actualCreateJobsForLeafPartitionResult =
        sizeRatioLeafStrategy.createJobsForLeafPartition(filesInPartition);

    // Assert
    verify(filesInPartition).getFilesWithNoJobIdInAscendingOrder();
    verify(filesInPartition).getPartitionId();
    assertTrue(actualCreateJobsForLeafPartitionResult.isEmpty());
  }
}
