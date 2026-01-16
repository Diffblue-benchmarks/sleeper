package sleeper.compaction.core.job.creation.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJob.Builder;
import sleeper.compaction.core.job.CompactionJobFactory;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.compaction.core.job.creation.strategy.impl.BasicLeafStrategy;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.FileReference;
import sleeper.core.table.TableStatus;

class DelegatingCompactionStrategyDiffblueTest {
  /**
   * Test {@link
   * DelegatingCompactionStrategy#DelegatingCompactionStrategy(LeafPartitionCompactionStrategy)}.
   *
   * <p>Method under test: {@link
   * DelegatingCompactionStrategy#DelegatingCompactionStrategy(LeafPartitionCompactionStrategy)}
   */
  @Test
  @DisplayName("Test new DelegatingCompactionStrategy(LeafPartitionCompactionStrategy)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DelegatingCompactionStrategy.<init>(LeafPartitionCompactionStrategy)"})
  void testNewDelegatingCompactionStrategy() {
    // Arrange and Act
    DelegatingCompactionStrategy actualDelegatingCompactionStrategy =
        new DelegatingCompactionStrategy(new BasicLeafStrategy());
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory =
        new CompactionJobFactory(
            instanceProperties2, new TableProperties(new InstanceProperties()));
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> allFileReferences = new ArrayList<>();
    CompactionStrategyIndex index =
        new CompactionStrategyIndex(tableStatus, allFileReferences, new ArrayList<>());

    // Assert
    assertTrue(
        actualDelegatingCompactionStrategy
            .createCompactionJobs(instanceProperties, tableProperties, factory, index)
            .isEmpty());
  }

  /**
   * Test {@link DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties,
   * TableProperties, CompactionJobFactory, CompactionStrategyIndex)}.
   *
   * <p>Method under test: {@link
   * DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties, TableProperties,
   * CompactionJobFactory, CompactionStrategyIndex)}
   */
  @Test
  @DisplayName(
      "Test createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DelegatingCompactionStrategy.createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)"
  })
  void testCreateCompactionJobs() {
    // Arrange
    ShouldCreateJobsStrategy shouldCreateJobsStrategy = mock(ShouldCreateJobsStrategy.class);
    when(shouldCreateJobsStrategy.maxCompactionJobsToCreate(Mockito.<FilesInPartition>any()))
        .thenReturn(1L);
    doNothing()
        .when(shouldCreateJobsStrategy)
        .init(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any());
    DelegatingCompactionStrategy delegatingCompactionStrategy =
        new DelegatingCompactionStrategy(new BasicLeafStrategy(), shouldCreateJobsStrategy);
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory =
        new CompactionJobFactory(
            instanceProperties2, new TableProperties(new InstanceProperties()));

    ArrayList<FilesInPartition> filesInPartitionList = new ArrayList<>();
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();

    FilesInPartition filesInPartition =
        new FilesInPartition(
            tableStatus, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>());
    filesInPartitionList.add(filesInPartition);

    CompactionStrategyIndex index = mock(CompactionStrategyIndex.class);
    when(index.getFilesInLeafPartitions()).thenReturn(filesInPartitionList);

    // Act
    List<CompactionJob> actualCreateCompactionJobsResult =
        delegatingCompactionStrategy.createCompactionJobs(
            instanceProperties, tableProperties, factory, index);

    // Assert
    verify(index).getFilesInLeafPartitions();
    verify(shouldCreateJobsStrategy)
        .init(isA(InstanceProperties.class), isA(TableProperties.class));
    verify(shouldCreateJobsStrategy).maxCompactionJobsToCreate(isA(FilesInPartition.class));
    assertTrue(actualCreateCompactionJobsResult.isEmpty());
  }

  /**
   * Test {@link DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties,
   * TableProperties, CompactionJobFactory, CompactionStrategyIndex)}.
   *
   * <p>Method under test: {@link
   * DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties, TableProperties,
   * CompactionJobFactory, CompactionStrategyIndex)}
   */
  @Test
  @DisplayName(
      "Test createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DelegatingCompactionStrategy.createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)"
  })
  void testCreateCompactionJobs2() {
    // Arrange
    DelegatingCompactionStrategy delegatingCompactionStrategy =
        new DelegatingCompactionStrategy(new BasicLeafStrategy());
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory =
        new CompactionJobFactory(
            instanceProperties2, new TableProperties(new InstanceProperties()));

    ArrayList<FilesInPartition> filesInPartitionList = new ArrayList<>();
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();

    FilesInPartition filesInPartition =
        new FilesInPartition(
            tableStatus, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>());
    filesInPartitionList.add(filesInPartition);

    CompactionStrategyIndex index = mock(CompactionStrategyIndex.class);
    when(index.getFilesInLeafPartitions()).thenReturn(filesInPartitionList);

    // Act
    List<CompactionJob> actualCreateCompactionJobsResult =
        delegatingCompactionStrategy.createCompactionJobs(
            instanceProperties, tableProperties, factory, index);

    // Assert
    verify(index).getFilesInLeafPartitions();
    assertTrue(actualCreateCompactionJobsResult.isEmpty());
  }

  /**
   * Test {@link DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties,
   * TableProperties, CompactionJobFactory, CompactionStrategyIndex)}.
   *
   * <p>Method under test: {@link
   * DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties, TableProperties,
   * CompactionJobFactory, CompactionStrategyIndex)}
   */
  @Test
  @DisplayName(
      "Test createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DelegatingCompactionStrategy.createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)"
  })
  void testCreateCompactionJobs3() {
    // Arrange
    ShouldCreateJobsStrategy shouldCreateJobsStrategy = mock(ShouldCreateJobsStrategy.class);
    when(shouldCreateJobsStrategy.maxCompactionJobsToCreate(Mockito.<FilesInPartition>any()))
        .thenReturn(-1L);
    doNothing()
        .when(shouldCreateJobsStrategy)
        .init(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any());
    DelegatingCompactionStrategy delegatingCompactionStrategy =
        new DelegatingCompactionStrategy(new BasicLeafStrategy(), shouldCreateJobsStrategy);
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory =
        new CompactionJobFactory(
            instanceProperties2, new TableProperties(new InstanceProperties()));

    ArrayList<FilesInPartition> filesInPartitionList = new ArrayList<>();
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();

    FilesInPartition filesInPartition =
        new FilesInPartition(
            tableStatus, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>());
    filesInPartitionList.add(filesInPartition);

    CompactionStrategyIndex index = mock(CompactionStrategyIndex.class);
    when(index.getFilesInLeafPartitions()).thenReturn(filesInPartitionList);

    // Act
    List<CompactionJob> actualCreateCompactionJobsResult =
        delegatingCompactionStrategy.createCompactionJobs(
            instanceProperties, tableProperties, factory, index);

    // Assert
    verify(index).getFilesInLeafPartitions();
    verify(shouldCreateJobsStrategy)
        .init(isA(InstanceProperties.class), isA(TableProperties.class));
    verify(shouldCreateJobsStrategy).maxCompactionJobsToCreate(isA(FilesInPartition.class));
    assertTrue(actualCreateCompactionJobsResult.isEmpty());
  }

  /**
   * Test {@link DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties,
   * TableProperties, CompactionJobFactory, CompactionStrategyIndex)}.
   *
   * <ul>
   *   <li>Then return {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties, TableProperties,
   * CompactionJobFactory, CompactionStrategyIndex)}
   */
  @Test
  @DisplayName(
      "Test createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex); then return ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DelegatingCompactionStrategy.createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)"
  })
  void testCreateCompactionJobs_thenReturnArrayList() {
    // Arrange
    ArrayList<CompactionJob> compactionJobList = new ArrayList<>();

    Builder builderResult = CompactionJob.builder();
    compactionJobList.add(
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    Builder builderResult2 = CompactionJob.builder();
    compactionJobList.add(
        builderResult2
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    LeafPartitionCompactionStrategy leafStrategy = mock(LeafPartitionCompactionStrategy.class);
    when(leafStrategy.createJobsForLeafPartition(Mockito.<FilesInPartition>any()))
        .thenReturn(compactionJobList);
    doNothing()
        .when(leafStrategy)
        .init(
            Mockito.<InstanceProperties>any(),
            Mockito.<TableProperties>any(),
            Mockito.<CompactionJobFactory>any());

    ShouldCreateJobsStrategy shouldCreateJobsStrategy = mock(ShouldCreateJobsStrategy.class);
    when(shouldCreateJobsStrategy.maxCompactionJobsToCreate(Mockito.<FilesInPartition>any()))
        .thenReturn(1L);
    doNothing()
        .when(shouldCreateJobsStrategy)
        .init(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any());

    DelegatingCompactionStrategy delegatingCompactionStrategy =
        new DelegatingCompactionStrategy(leafStrategy, shouldCreateJobsStrategy);
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory =
        new CompactionJobFactory(
            instanceProperties2, new TableProperties(new InstanceProperties()));

    ArrayList<FilesInPartition> filesInPartitionList = new ArrayList<>();
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Max jobs to create = {}", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();

    FilesInPartition filesInPartition =
        new FilesInPartition(
            tableStatus, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>());
    filesInPartitionList.add(filesInPartition);

    CompactionStrategyIndex index = mock(CompactionStrategyIndex.class);
    when(index.getFilesInLeafPartitions()).thenReturn(filesInPartitionList);

    // Act
    List<CompactionJob> actualCreateCompactionJobsResult =
        delegatingCompactionStrategy.createCompactionJobs(
            instanceProperties, tableProperties, factory, index);

    // Assert
    verify(index).getFilesInLeafPartitions();
    verify(leafStrategy).createJobsForLeafPartition(isA(FilesInPartition.class));
    verify(leafStrategy)
        .init(
            isA(InstanceProperties.class),
            isA(TableProperties.class),
            isA(CompactionJobFactory.class));
    verify(shouldCreateJobsStrategy)
        .init(isA(InstanceProperties.class), isA(TableProperties.class));
    verify(shouldCreateJobsStrategy).maxCompactionJobsToCreate(isA(FilesInPartition.class));
    assertEquals(compactionJobList, actualCreateCompactionJobsResult);
  }
}
