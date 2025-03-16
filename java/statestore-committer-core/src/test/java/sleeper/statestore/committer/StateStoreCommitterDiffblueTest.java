package sleeper.statestore.committer;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.testutils.InMemoryTransactionBodyStore;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.statestore.transactionlog.transaction.StateStoreTransaction;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;

class StateStoreCommitterDiffblueTest {
  /**
   * Test {@link StateStoreCommitter#apply(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitter#apply(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test apply(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitter.apply(StateStoreCommitRequest)"})
  void testApply() throws StateStoreException {
    // Arrange
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    InMemoryTransactionBodyStore transactionBodyStore = mock(InMemoryTransactionBodyStore.class);
    Mockito.<StateStoreTransaction<?>>when(transactionBodyStore.getTransaction(Mockito.<StateStoreCommitRequest>any()))
        .thenReturn(new AddFilesTransaction(new ArrayList<>()));
    InstanceProperties instanceProperties = new InstanceProperties();
    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();
    StateStoreCommitter stateStoreCommitter = new StateStoreCommitter(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, compactionJobTracker, new InMemoryIngestJobTracker(), transactionBodyStore,
        mock(Supplier.class));

    // Act
    stateStoreCommitter
        .apply(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ASSIGN_JOB_IDS));

    // Assert
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(transactionBodyStore).getTransaction(isA(StateStoreCommitRequest.class));
  }

  /**
   * Test {@link StateStoreCommitter#apply(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Given {@code ADD_FILES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitter#apply(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test apply(StateStoreCommitRequest); given 'ADD_FILES'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitter.apply(StateStoreCommitRequest)"})
  void testApply_givenAddFiles() throws StateStoreException {
    // Arrange
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    InMemoryTransactionBodyStore transactionBodyStore = mock(InMemoryTransactionBodyStore.class);
    Mockito.<StateStoreTransaction<?>>when(transactionBodyStore.getTransaction(Mockito.<StateStoreCommitRequest>any()))
        .thenReturn(new AddFilesTransaction(new ArrayList<>()));
    InstanceProperties instanceProperties = new InstanceProperties();
    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();
    StateStoreCommitter stateStoreCommitter = new StateStoreCommitter(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, compactionJobTracker, new InMemoryIngestJobTracker(), transactionBodyStore,
        mock(Supplier.class));
    StateStoreCommitRequest request = mock(StateStoreCommitRequest.class);
    when(request.getBodyKey()).thenReturn("Not all who wander are lost");
    when(request.getTableId()).thenReturn("42");
    when(request.getTransactionType()).thenReturn(TransactionType.ADD_FILES);

    // Act
    stateStoreCommitter.apply(request);

    // Assert
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(request).getBodyKey();
    verify(request, atLeast(1)).getTableId();
    verify(request, atLeast(1)).getTransactionType();
    verify(transactionBodyStore).getTransaction(isA(StateStoreCommitRequest.class));
  }

  /**
   * Test {@link StateStoreCommitter#apply(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Given {@code ASSIGN_JOB_IDS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitter#apply(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test apply(StateStoreCommitRequest); given 'ASSIGN_JOB_IDS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitter.apply(StateStoreCommitRequest)"})
  void testApply_givenAssignJobIds() throws StateStoreException {
    // Arrange
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    InMemoryTransactionBodyStore transactionBodyStore = mock(InMemoryTransactionBodyStore.class);
    Mockito.<StateStoreTransaction<?>>when(transactionBodyStore.getTransaction(Mockito.<StateStoreCommitRequest>any()))
        .thenReturn(new AddFilesTransaction(new ArrayList<>()));
    InstanceProperties instanceProperties = new InstanceProperties();
    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();
    StateStoreCommitter stateStoreCommitter = new StateStoreCommitter(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, compactionJobTracker, new InMemoryIngestJobTracker(), transactionBodyStore,
        mock(Supplier.class));
    StateStoreCommitRequest request = mock(StateStoreCommitRequest.class);
    when(request.getBodyKey()).thenReturn("Not all who wander are lost");
    when(request.getTableId()).thenReturn("42");
    when(request.getTransactionType()).thenReturn(TransactionType.ASSIGN_JOB_IDS);

    // Act
    stateStoreCommitter.apply(request);

    // Assert
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(request).getBodyKey();
    verify(request, atLeast(1)).getTableId();
    verify(request, atLeast(1)).getTransactionType();
    verify(transactionBodyStore).getTransaction(isA(StateStoreCommitRequest.class));
  }

  /**
   * Test {@link StateStoreCommitter#apply(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Then calls {@link TablePropertiesProvider#getById(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitter#apply(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test apply(StateStoreCommitRequest); then calls getById(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitter.apply(StateStoreCommitRequest)"})
  void testApply_thenCallsGetById() throws StateStoreException {
    // Arrange
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    InMemoryTransactionBodyStore transactionBodyStore = mock(InMemoryTransactionBodyStore.class);
    Mockito.<StateStoreTransaction<?>>when(transactionBodyStore.getTransaction(Mockito.<StateStoreCommitRequest>any()))
        .thenReturn(new AddFilesTransaction(new ArrayList<>()));
    InstanceProperties instanceProperties = new InstanceProperties();
    InMemoryCompactionJobTracker compactionJobTracker = new InMemoryCompactionJobTracker();
    StateStoreCommitter stateStoreCommitter = new StateStoreCommitter(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, compactionJobTracker, new InMemoryIngestJobTracker(), transactionBodyStore,
        mock(Supplier.class));

    // Act
    stateStoreCommitter
        .apply(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES));

    // Assert
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(transactionBodyStore).getTransaction(isA(StateStoreCommitRequest.class));
  }
}
