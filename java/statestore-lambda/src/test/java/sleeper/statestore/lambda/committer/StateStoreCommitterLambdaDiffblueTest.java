package sleeper.statestore.lambda.committer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.commit.StateStoreCommitRequestSerDe;
import sleeper.core.statestore.testutils.InMemoryTransactionBodyStore;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;
import sleeper.statestore.committer.StateStoreCommitter;

class StateStoreCommitterLambdaDiffblueTest {
  /**
   * Test {@link StateStoreCommitterLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Then return BatchItemFailures Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; then return BatchItemFailures Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "com.amazonaws.services.lambda.runtime.events.SQSBatchResponse StateStoreCommitterLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_thenReturnBatchItemFailuresEmpty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    StateStoreCommitRequestSerDe serDe = StateStoreCommitRequestSerDe.forFileTransactions();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    InstanceProperties instanceProperties3 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider2 = new TablePropertiesProvider(instanceProperties3,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    StateStoreProvider stateStoreProvider2 = new StateStoreProvider(3, mock(Factory.class));

    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();
    InMemoryIngestJobTracker ingestJobTracker = new InMemoryIngestJobTracker();
    StateStoreCommitter committer = new StateStoreCommitter(instanceProperties2, tablePropertiesProvider2,
        stateStoreProvider2, compactionJobTracker, ingestJobTracker, new InMemoryTransactionBodyStore(),
        mock(Supplier.class));

    PollWithRetries throttlingRetriesConfig = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    StateStoreCommitterLambda stateStoreCommitterLambda = new StateStoreCommitterLambda(tablePropertiesProvider,
        stateStoreProvider, serDe, committer, throttlingRetriesConfig);

    SQSEvent event = new SQSEvent();
    event.setRecords(new ArrayList<>());

    // Act and Assert
    assertTrue(stateStoreCommitterLambda.handleRequest(event, mock(Context.class)).getBatchItemFailures().isEmpty());
  }
}
