package sleeper.compaction.core.job.creation.strategy;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobFactory;
import sleeper.compaction.core.job.creation.strategy.impl.BasicLeafStrategy;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.FileReference;
import sleeper.core.table.TableStatus;

class DelegatingCompactionStrategyDiffblueTest {
  /**
   * Test {@link DelegatingCompactionStrategy#DelegatingCompactionStrategy(LeafPartitionCompactionStrategy)}.
   * <p>
   * Method under test: {@link DelegatingCompactionStrategy#DelegatingCompactionStrategy(LeafPartitionCompactionStrategy)}
   */
  @Test
  @DisplayName("Test new DelegatingCompactionStrategy(LeafPartitionCompactionStrategy)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DelegatingCompactionStrategy.<init>(LeafPartitionCompactionStrategy)"})
  void testNewDelegatingCompactionStrategy() {
    // Arrange and Act
    DelegatingCompactionStrategy actualDelegatingCompactionStrategy = new DelegatingCompactionStrategy(
        new BasicLeafStrategy());
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory = new CompactionJobFactory(instanceProperties2,
        new TableProperties(new InstanceProperties()));

    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> allFileReferences = new ArrayList<>();

    // Assert
    assertTrue(
        actualDelegatingCompactionStrategy
            .createCompactionJobs(instanceProperties, tableProperties, factory,
                new CompactionStrategyIndex(tableStatus, allFileReferences, new ArrayList<>()))
            .isEmpty());
  }

  /**
   * Test {@link DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)}.
   * <p>
   * Method under test: {@link DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)}
   */
  @Test
  @DisplayName("Test createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "List DelegatingCompactionStrategy.createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)"})
  void testCreateCompactionJobs() {
    // Arrange
    DelegatingCompactionStrategy delegatingCompactionStrategy = new DelegatingCompactionStrategy(
        new BasicLeafStrategy());
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory = new CompactionJobFactory(instanceProperties2,
        new TableProperties(new InstanceProperties()));

    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> allFileReferences = new ArrayList<>();

    // Act and Assert
    assertTrue(
        delegatingCompactionStrategy
            .createCompactionJobs(instanceProperties, tableProperties, factory,
                new CompactionStrategyIndex(tableStatus, allFileReferences, new ArrayList<>()))
            .isEmpty());
  }

  /**
   * Test {@link DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)}.
   * <ul>
   *   <li>Then calls {@link ShouldCreateJobsStrategy#init(InstanceProperties, TableProperties)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DelegatingCompactionStrategy#createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)}
   */
  @Test
  @DisplayName("Test createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex); then calls init(InstanceProperties, TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "List DelegatingCompactionStrategy.createCompactionJobs(InstanceProperties, TableProperties, CompactionJobFactory, CompactionStrategyIndex)"})
  void testCreateCompactionJobs_thenCallsInit() {
    // Arrange
    ShouldCreateJobsStrategy shouldCreateJobsStrategy = mock(ShouldCreateJobsStrategy.class);
    doNothing().when(shouldCreateJobsStrategy).init(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any());
    DelegatingCompactionStrategy delegatingCompactionStrategy = new DelegatingCompactionStrategy(
        new BasicLeafStrategy(), shouldCreateJobsStrategy);
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory = new CompactionJobFactory(instanceProperties2,
        new TableProperties(new InstanceProperties()));

    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> allFileReferences = new ArrayList<>();

    // Act
    List<CompactionJob> actualCreateCompactionJobsResult = delegatingCompactionStrategy.createCompactionJobs(
        instanceProperties, tableProperties, factory,
        new CompactionStrategyIndex(tableStatus, allFileReferences, new ArrayList<>()));

    // Assert
    verify(shouldCreateJobsStrategy).init(isA(InstanceProperties.class), isA(TableProperties.class));
    assertTrue(actualCreateCompactionJobsResult.isEmpty());
  }
}
