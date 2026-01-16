package sleeper.compaction.core.job.creation.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJob.Builder;
import sleeper.compaction.core.job.CompactionJobFactory;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.FileReference;
import sleeper.core.table.TableStatus;

@ExtendWith(MockitoExtension.class)
class BasicLeafStrategyDiffblueTest {
  @InjectMocks private BasicLeafStrategy basicLeafStrategy;

  @Mock private CompactionJobFactory compactionJobFactory;

  /**
   * Test {@link BasicLeafStrategy#init(InstanceProperties, TableProperties, CompactionJobFactory)}.
   *
   * <ul>
   *   <li>Given {@link BasicLeafStrategy} (default constructor).
   *   <li>Then calls {@link TableProperties#getInt(SleeperProperty)}.
   * </ul>
   *
   * <p>Method under test: {@link BasicLeafStrategy#init(InstanceProperties, TableProperties,
   * CompactionJobFactory)}
   */
  @Test
  @DisplayName(
      "Test init(InstanceProperties, TableProperties, CompactionJobFactory); given BasicLeafStrategy (default constructor); then calls getInt(SleeperProperty)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BasicLeafStrategy.init(InstanceProperties, TableProperties, CompactionJobFactory)"
  })
  void testInit_givenBasicLeafStrategy_thenCallsGetInt() {
    // Arrange
    BasicLeafStrategy basicLeafStrategy = new BasicLeafStrategy();
    InstanceProperties instanceProperties = new InstanceProperties();

    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory =
        new CompactionJobFactory(
            instanceProperties2, new TableProperties(new InstanceProperties()));

    // Act
    basicLeafStrategy.init(instanceProperties, tableProperties, factory);

    // Assert that nothing has changed
    verify(tableProperties).getInt(isA(TableProperty.class));
  }

  /**
   * Test {@link BasicLeafStrategy#createJobsForLeafPartition(FilesInPartition)}.
   *
   * <ul>
   *   <li>Given {@link BasicLeafStrategy} (default constructor).
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link BasicLeafStrategy#createJobsForLeafPartition(FilesInPartition)}
   */
  @Test
  @DisplayName(
      "Test createJobsForLeafPartition(FilesInPartition); given BasicLeafStrategy (default constructor); then return Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List BasicLeafStrategy.createJobsForLeafPartition(FilesInPartition)"})
  void testCreateJobsForLeafPartition_givenBasicLeafStrategy_thenReturnEmpty() {
    // Arrange
    BasicLeafStrategy basicLeafStrategy = new BasicLeafStrategy();

    FilesInPartition filesInPartition = mock(FilesInPartition.class);
    when(filesInPartition.getPartitionId()).thenReturn("42");
    when(filesInPartition.getFilesWithNoJobIdInAscendingOrder()).thenReturn(new ArrayList<>());
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);
    when(filesInPartition.getTableStatus()).thenReturn(uniqueIdAndNameResult);

    // Act
    List<CompactionJob> actualCreateJobsForLeafPartitionResult =
        basicLeafStrategy.createJobsForLeafPartition(filesInPartition);

    // Assert
    verify(filesInPartition).getFilesWithNoJobIdInAscendingOrder();
    verify(filesInPartition).getPartitionId();
    verify(filesInPartition).getTableStatus();
    assertTrue(actualCreateJobsForLeafPartitionResult.isEmpty());
  }

  /**
   * Test {@link BasicLeafStrategy#createJobsForLeafPartition(FilesInPartition)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link BasicLeafStrategy#createJobsForLeafPartition(FilesInPartition)}
   */
  @Test
  @DisplayName("Test createJobsForLeafPartition(FilesInPartition); then return size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List BasicLeafStrategy.createJobsForLeafPartition(FilesInPartition)"})
  void testCreateJobsForLeafPartition_thenReturnSizeIsOne() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    when(compactionJobFactory.createCompactionJob(
            Mockito.<List<FileReference>>any(), Mockito.<String>any()))
        .thenReturn(
            builderResult
                .inputFiles(new ArrayList<>())
                .jobId("42")
                .outputFile("Output File")
                .partitionId("42")
                .tableId("42")
                .build());

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

    FilesInPartition filesInPartition = mock(FilesInPartition.class);
    when(filesInPartition.getFilesWithNoJobIdInAscendingOrder()).thenReturn(fileReferenceList);
    when(filesInPartition.getPartitionId()).thenReturn("42");
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);
    when(filesInPartition.getTableStatus()).thenReturn(uniqueIdAndNameResult);

    // Act
    List<CompactionJob> actualCreateJobsForLeafPartitionResult =
        basicLeafStrategy.createJobsForLeafPartition(filesInPartition);

    // Assert
    verify(compactionJobFactory).createCompactionJob(isA(List.class), eq("42"));
    verify(filesInPartition).getFilesWithNoJobIdInAscendingOrder();
    verify(filesInPartition, atLeast(1)).getPartitionId();
    verify(filesInPartition).getTableStatus();
    assertEquals(1, actualCreateJobsForLeafPartitionResult.size());
    CompactionJob getResult = actualCreateJobsForLeafPartitionResult.get(0);
    assertEquals("42", getResult.getId());
    assertEquals("42", getResult.getPartitionId());
    assertEquals("42", getResult.getTableId());
    assertEquals("Output File", getResult.getOutputFile());
    assertNull(getResult.getIteratorClassName());
    assertNull(getResult.getIteratorConfig());
    assertTrue(getResult.getInputFiles().isEmpty());
  }
}
