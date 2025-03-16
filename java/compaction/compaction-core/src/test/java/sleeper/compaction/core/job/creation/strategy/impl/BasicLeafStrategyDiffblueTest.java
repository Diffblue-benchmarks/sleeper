package sleeper.compaction.core.job.creation.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
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
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.table.TableStatus;

class BasicLeafStrategyDiffblueTest {
  /**
   * Test {@link BasicLeafStrategy#init(InstanceProperties, TableProperties, CompactionJobFactory)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link TableProperties} {@link SleeperPropertyValues#getInt(SleeperProperty)} return one.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicLeafStrategy#init(InstanceProperties, TableProperties, CompactionJobFactory)}
   */
  @Test
  @DisplayName("Test init(InstanceProperties, TableProperties, CompactionJobFactory); given one; when TableProperties getInt(SleeperProperty) return one; then calls getInt(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BasicLeafStrategy.init(InstanceProperties, TableProperties, CompactionJobFactory)"})
  void testInit_givenOne_whenTablePropertiesGetIntReturnOne_thenCallsGetInt() {
    // Arrange
    BasicLeafStrategy basicLeafStrategy = new BasicLeafStrategy();
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    InstanceProperties instanceProperties2 = new InstanceProperties();

    // Act
    basicLeafStrategy.init(instanceProperties, tableProperties,
        new CompactionJobFactory(instanceProperties2, new TableProperties(new InstanceProperties())));

    // Assert
    verify(tableProperties).getInt(isA(TableProperty.class));
  }

  /**
   * Test {@link BasicLeafStrategy#createJobsForLeafPartition(FilesInPartition)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link BasicLeafStrategy#createJobsForLeafPartition(FilesInPartition)}
   */
  @Test
  @DisplayName("Test createJobsForLeafPartition(FilesInPartition); given ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List BasicLeafStrategy.createJobsForLeafPartition(FilesInPartition)"})
  void testCreateJobsForLeafPartition_givenArrayList_thenReturnEmpty() {
    // Arrange
    BasicLeafStrategy basicLeafStrategy = new BasicLeafStrategy();
    FilesInPartition filesInPartition = mock(FilesInPartition.class);
    when(filesInPartition.getPartitionId()).thenReturn("42");
    when(filesInPartition.getFilesWithNoJobIdInAscendingOrder()).thenReturn(new ArrayList<>());
    when(filesInPartition.getTableStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Act
    List<CompactionJob> actualCreateJobsForLeafPartitionResult = basicLeafStrategy
        .createJobsForLeafPartition(filesInPartition);

    // Assert
    verify(filesInPartition).getFilesWithNoJobIdInAscendingOrder();
    verify(filesInPartition).getPartitionId();
    verify(filesInPartition).getTableStatus();
    assertTrue(actualCreateJobsForLeafPartitionResult.isEmpty());
  }
}
