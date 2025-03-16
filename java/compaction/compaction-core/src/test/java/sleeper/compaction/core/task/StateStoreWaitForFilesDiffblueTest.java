package sleeper.compaction.core.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.util.function.Supplier;
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
import sleeper.core.util.ExponentialBackoffWithJitter;
import sleeper.core.util.ExponentialBackoffWithJitter.WaitRange;

class StateStoreWaitForFilesDiffblueTest {
  /**
   * Test {@link StateStoreWaitForFiles#wait(CompactionJob, String, String)} with {@code CompactionJob}, {@code String}, {@code String}.
   * <p>
   * Method under test: {@link StateStoreWaitForFiles#wait(CompactionJob, String, String)}
   */
  @Test
  @DisplayName("Test wait(CompactionJob, String, String) with 'CompactionJob', 'String', 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreWaitForFiles.wait(CompactionJob, String, String)"})
  void testWaitWithCompactionJobStringString() throws InterruptedException {
    // Arrange
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TimedOutWaitingForFileAssignmentsException());
    ExponentialBackoffWithJitter jobAssignmentWaitBackoff = new ExponentialBackoffWithJitter(
        WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d));
    InstanceProperties instanceProperties = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    // Act and Assert
    assertThrows(TimedOutWaitingForFileAssignmentsException.class,
        () -> (new StateStoreWaitForFiles(1, jobAssignmentWaitBackoff,
            StateStoreWaitForFiles.JOB_ASSIGNMENT_THROTTLING_RETRIES, tablePropertiesProvider, stateStoreProvider,
            new InMemoryCompactionJobTracker(), timeSupplier)).wait(null, "42", "42"));
    verify(timeSupplier).get();
  }
}
