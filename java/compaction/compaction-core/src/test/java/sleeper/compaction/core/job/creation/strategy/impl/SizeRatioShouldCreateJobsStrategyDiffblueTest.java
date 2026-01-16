package sleeper.compaction.core.job.creation.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

class SizeRatioShouldCreateJobsStrategyDiffblueTest {
  /**
   * Test {@link SizeRatioShouldCreateJobsStrategy#maxCompactionJobsToCreate(FilesInPartition)}.
   *
   * <ul>
   *   <li>Then return {@code 2147483647}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SizeRatioShouldCreateJobsStrategy#maxCompactionJobsToCreate(CompactionStrategyIndex.FilesInPartition)}
   */
  @Test
  @DisplayName("Test maxCompactionJobsToCreate(FilesInPartition); then return '2147483647'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long SizeRatioShouldCreateJobsStrategy.maxCompactionJobsToCreate(CompactionStrategyIndex.FilesInPartition)"
  })
  void testMaxCompactionJobsToCreate_thenReturn2147483647() {
    // Arrange
    SizeRatioShouldCreateJobsStrategy sizeRatioShouldCreateJobsStrategy =
        new SizeRatioShouldCreateJobsStrategy();
    InstanceProperties instanceProperties = new InstanceProperties();
    sizeRatioShouldCreateJobsStrategy.init(
        instanceProperties, new TableProperties(new InstanceProperties()));

    FilesInPartition filesInPartition = mock(FilesInPartition.class);
    when(filesInPartition.getFilesWithJobId()).thenReturn(new ArrayList<>());

    // Act
    long actualMaxCompactionJobsToCreateResult =
        sizeRatioShouldCreateJobsStrategy.maxCompactionJobsToCreate(filesInPartition);

    // Assert
    verify(filesInPartition).getFilesWithJobId();
    assertEquals(2147483647L, actualMaxCompactionJobsToCreateResult);
  }

  /**
   * Test {@link SizeRatioShouldCreateJobsStrategy#maxCompactionJobsToCreate(FilesInPartition)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * SizeRatioShouldCreateJobsStrategy#maxCompactionJobsToCreate(CompactionStrategyIndex.FilesInPartition)}
   */
  @Test
  @DisplayName("Test maxCompactionJobsToCreate(FilesInPartition); then return zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long SizeRatioShouldCreateJobsStrategy.maxCompactionJobsToCreate(CompactionStrategyIndex.FilesInPartition)"
  })
  void testMaxCompactionJobsToCreate_thenReturnZero() {
    // Arrange
    SizeRatioShouldCreateJobsStrategy sizeRatioShouldCreateJobsStrategy =
        new SizeRatioShouldCreateJobsStrategy();

    FilesInPartition filesInPartition = mock(FilesInPartition.class);
    when(filesInPartition.getPartitionId()).thenReturn("42");
    when(filesInPartition.getFilesWithJobId()).thenReturn(new ArrayList<>());

    // Act
    long actualMaxCompactionJobsToCreateResult =
        sizeRatioShouldCreateJobsStrategy.maxCompactionJobsToCreate(filesInPartition);

    // Assert
    verify(filesInPartition).getFilesWithJobId();
    verify(filesInPartition).getPartitionId();
    assertEquals(0L, actualMaxCompactionJobsToCreateResult);
  }
}
