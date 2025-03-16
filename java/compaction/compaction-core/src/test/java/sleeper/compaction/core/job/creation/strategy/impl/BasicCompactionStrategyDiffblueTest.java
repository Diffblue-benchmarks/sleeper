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

class BasicCompactionStrategyDiffblueTest {
  /**
   * Test new {@link BasicCompactionStrategy} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link BasicCompactionStrategy}
   */
  @Test
  @DisplayName("Test new BasicCompactionStrategy (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BasicCompactionStrategy.<init>()"})
  void testNewBasicCompactionStrategy() {
    // Arrange and Act
    BasicCompactionStrategy actualBasicCompactionStrategy = new BasicCompactionStrategy();
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InstanceProperties instanceProperties2 = new InstanceProperties();
    CompactionJobFactory factory = new CompactionJobFactory(instanceProperties2,
        new TableProperties(new InstanceProperties()));

    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> allFileReferences = new ArrayList<>();

    // Assert
    assertTrue(
        actualBasicCompactionStrategy
            .createCompactionJobs(instanceProperties, tableProperties, factory,
                new CompactionStrategyIndex(tableStatus, allFileReferences, new ArrayList<>()))
            .isEmpty());
  }
}
