package sleeper.compaction.core.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.core.job.CompactionJob;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;

class StateStoreWaitForFilesDiffblueTest {
  /**
   * Test {@link StateStoreWaitForFiles#wait(CompactionJob, String, String)} with {@code
   * CompactionJob}, {@code String}, {@code String}.
   *
   * <p>Method under test: {@link StateStoreWaitForFiles#wait(CompactionJob, String, String)}
   */
  @Test
  @DisplayName("Test wait(CompactionJob, String, String) with 'CompactionJob', 'String', 'String'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StateStoreWaitForFiles.wait(CompactionJob, String, String)"})
  void testWaitWithCompactionJobStringString() throws InterruptedException {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    TablePropertiesStore propertiesStore =
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class));

    TablePropertiesProvider tablePropertiesProvider =
        new TablePropertiesProvider(instanceProperties, propertiesStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    StateStoreWaitForFiles stateStoreWaitForFiles =
        new StateStoreWaitForFiles(
            tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker());

    CompactionJob job = mock(CompactionJob.class);
    when(job.getTableId()).thenThrow(new TimedOutWaitingForFileAssignmentsException());

    // Act and Assert
    assertThrows(
        TimedOutWaitingForFileAssignmentsException.class,
        () -> stateStoreWaitForFiles.wait(job, "42", "42"));
    verify(job).getTableId();
  }
}
