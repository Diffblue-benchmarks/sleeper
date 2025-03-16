package sleeper.compaction.core.job.creation.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.core.job.CompactionJobFactory;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.FileReference;
import sleeper.core.table.TableStatus;

class SizeRatioCompactionStrategyDiffblueTest {
  /**
   * Test new {@link SizeRatioCompactionStrategy} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SizeRatioCompactionStrategy}
   */
  @Test
  @DisplayName("Test new SizeRatioCompactionStrategy (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SizeRatioCompactionStrategy.<init>()"})
  void testNewSizeRatioCompactionStrategy() {
    // Arrange and Act
    SizeRatioCompactionStrategy actualSizeRatioCompactionStrategy = new SizeRatioCompactionStrategy();
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory = new CompactionJobFactory(instanceProperties2,
        new TableProperties(new InstanceProperties()));

    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> allFileReferences = new ArrayList<>();

    // Assert
    assertTrue(
        actualSizeRatioCompactionStrategy
            .createCompactionJobs(instanceProperties, tableProperties, factory,
                new CompactionStrategyIndex(tableStatus, allFileReferences, new ArrayList<>()))
            .isEmpty());
  }
}
