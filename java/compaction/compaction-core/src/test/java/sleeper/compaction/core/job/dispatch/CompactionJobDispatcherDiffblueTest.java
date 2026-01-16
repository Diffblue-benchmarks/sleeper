package sleeper.compaction.core.job.dispatch;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatcher.ReadBatch;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatcher.ReturnRequestToPendingQueue;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatcher.SendDeadLetter;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatcher.SendJobs;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.exception.FileReferenceAssignedToJobException;
import sleeper.core.statestore.testutils.FixedStateStoreProvider;
import sleeper.core.statestore.testutils.InMemoryTransactionBodyStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStore;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore.Builder;
import sleeper.core.statestore.transactionlog.log.TransactionLogRange;
import sleeper.core.statestore.transactionlog.snapshot.TransactionLogSnapshot;
import sleeper.core.statestore.transactionlog.snapshot.TransactionLogSnapshotLoader;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.util.ExponentialBackoffWithJitter;
import sleeper.core.util.ExponentialBackoffWithJitter.WaitRange;

class CompactionJobDispatcherDiffblueTest {
  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch() throws SleeperPropertiesInvalidException {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Optional<TableStatus> ofResult = Optional.of(uniqueIdAndNameResult);
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);

    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    TableStatus uniqueIdAndNameResult2 = TableStatus.uniqueIdAndName("42", "Table Name", true);
    when(tableProperties.getStatus()).thenReturn(uniqueIdAndNameResult2);

    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);

    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);
    TablePropertiesProvider tablePropertiesProvider =
        new TablePropertiesProvider(new InstanceProperties(), propertiesStore);

    Factory stateStoreFactory = mock(Factory.class);
    FileReference fileReference =
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build();
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any()))
        .thenThrow(new FileReferenceAssignedToJobException(fileReference));
    StateStoreProvider stateStoreProvider = new StateStoreProvider(0, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ArrayList<>());

    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    doNothing().when(sendDeadLetter).send(Mockito.<CompactionJobDispatchRequest>any());
    InstanceProperties instanceProperties = new InstanceProperties();

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            mock(SendJobs.class),
            3,
            mock(ReturnRequestToPendingQueue.class),
            sendDeadLetter,
            mock(Supplier.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read(null, "null/compactions/42.json");
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByUniqueId(null);
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch2() throws SleeperPropertiesInvalidException {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Optional<TableStatus> ofResult = Optional.of(uniqueIdAndNameResult);
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);

    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    TableStatus uniqueIdAndNameResult2 = TableStatus.uniqueIdAndName("42", "Table Name", true);
    when(tableProperties.getStatus()).thenReturn(uniqueIdAndNameResult2);

    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);

    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);
    TablePropertiesProvider tablePropertiesProvider =
        new TablePropertiesProvider(new InstanceProperties(), propertiesStore);

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult2 =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult2);

    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));

    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any()))
        .thenReturn(
            timeBetweenTransactionChecksResult
                .transactionBodyStore(new InMemoryTransactionBodyStore())
                .updateLogBeforeAddTransaction(true)
                .build());
    StateStoreProvider stateStoreProvider =
        new StateStoreProvider(new InstanceProperties(), stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ArrayList<>());
    InstanceProperties instanceProperties = new InstanceProperties();

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            mock(SendJobs.class),
            3,
            mock(ReturnRequestToPendingQueue.class),
            mock(SendDeadLetter.class),
            mock(Supplier.class));

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read(null, "null/compactions/42.json");
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
    verify(tableIndex).getTableByUniqueId(null);
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch3() throws SleeperPropertiesInvalidException {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Optional<TableStatus> ofResult = Optional.of(uniqueIdAndNameResult);
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);

    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    TableStatus uniqueIdAndNameResult2 = TableStatus.uniqueIdAndName("42", "Table Name", true);
    when(tableProperties.getStatus()).thenReturn(uniqueIdAndNameResult2);

    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);

    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);
    TablePropertiesProvider tablePropertiesProvider =
        new TablePropertiesProvider(new InstanceProperties(), propertiesStore);

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult2 =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult2);

    Builder builderResult2 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult2 =
        builderResult2
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Builder minTransactionsAheadToLoadSnapshotResult2 =
        filesSnapshotLoaderResult2
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult2 =
        minTransactionsAheadToLoadSnapshotResult2
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult2 =
        partitionsSnapshotLoaderResult2.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange2 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult2 =
        partitionsStateUpdateClockResult2.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange2));
    TableStatus sleeperTable2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult2 =
        retryBackoffResult2
            .sleeperTable(sleeperTable2)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));

    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any()))
        .thenReturn(
            timeBetweenTransactionChecksResult2
                .transactionBodyStore(new InMemoryTransactionBodyStore())
                .updateLogBeforeAddTransaction(true)
                .build());
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ArrayList<>());

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            mock(SendJobs.class),
            3,
            mock(ReturnRequestToPendingQueue.class),
            mock(SendDeadLetter.class),
            mock(Supplier.class));

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read("Get", "null/compactions/42.json");
    verify(tableProperties).validate();
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
    verify(tableIndex).getTableByUniqueId(null);
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch4() {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult2 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult2 =
        builderResult2
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult2 =
        filesSnapshotLoaderResult2
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult2 =
        minTransactionsAheadToLoadSnapshotResult2
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult2 =
        partitionsSnapshotLoaderResult2.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange2 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult2 =
        partitionsStateUpdateClockResult2.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange2));
    TableStatus sleeperTable2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult2 =
        retryBackoffResult2
            .sleeperTable(sleeperTable2)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult2
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult3 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult3 =
        builderResult3
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult3 =
        filesSnapshotLoaderResult3
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult3 =
        minTransactionsAheadToLoadSnapshotResult3
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult3 =
        partitionsSnapshotLoaderResult3.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange3 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult3 =
        partitionsStateUpdateClockResult3.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange3));
    TableStatus sleeperTable3 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult3 =
        retryBackoffResult3
            .sleeperTable(sleeperTable3)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult3
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));

    Factory stateStoreFactory = mock(Factory.class);
    FileReference fileReference =
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build();
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any()))
        .thenThrow(new FileReferenceAssignedToJobException(fileReference));
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ArrayList<>());

    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    doNothing().when(sendDeadLetter).send(Mockito.<CompactionJobDispatchRequest>any());

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            mock(SendJobs.class),
            3,
            mock(ReturnRequestToPendingQueue.class),
            sendDeadLetter,
            mock(Supplier.class));

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read("Get", "null/compactions/42.json");
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(null);
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch5() {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult2 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult2 =
        builderResult2
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult2 =
        filesSnapshotLoaderResult2
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult2 =
        minTransactionsAheadToLoadSnapshotResult2
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult2 =
        partitionsSnapshotLoaderResult2.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange2 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult2 =
        partitionsStateUpdateClockResult2.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange2));
    TableStatus sleeperTable2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult2 =
        retryBackoffResult2
            .sleeperTable(sleeperTable2)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult2
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult3 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult3 =
        builderResult3
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult3 =
        filesSnapshotLoaderResult3
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult3 =
        minTransactionsAheadToLoadSnapshotResult3
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult3 =
        partitionsSnapshotLoaderResult3.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange3 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult3 =
        partitionsStateUpdateClockResult3.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange3));
    TableStatus sleeperTable3 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult3 =
        retryBackoffResult3
            .sleeperTable(sleeperTable3)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult3
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult4 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult4 =
        builderResult4
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult4 =
        filesSnapshotLoaderResult4
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult4 =
        minTransactionsAheadToLoadSnapshotResult4
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult4 =
        partitionsSnapshotLoaderResult4.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange4 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult4 =
        partitionsStateUpdateClockResult4.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange4));
    TableStatus sleeperTable4 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult4 =
        retryBackoffResult4
            .sleeperTable(sleeperTable4)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult4
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult5 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult5 =
        builderResult5
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Builder minTransactionsAheadToLoadSnapshotResult5 =
        filesSnapshotLoaderResult5
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult5 =
        minTransactionsAheadToLoadSnapshotResult5
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult5 =
        partitionsSnapshotLoaderResult5.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange5 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult5 =
        partitionsStateUpdateClockResult5.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange5));
    TableStatus sleeperTable5 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult5 =
        retryBackoffResult5
            .sleeperTable(sleeperTable5)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));

    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any()))
        .thenReturn(
            timeBetweenTransactionChecksResult5
                .transactionBodyStore(new InMemoryTransactionBodyStore())
                .updateLogBeforeAddTransaction(true)
                .build());

    ArrayList<CompactionJob> compactionJobList = new ArrayList<>();

    CompactionJob.Builder builderResult6 = CompactionJob.builder();
    compactionJobList.add(
        builderResult6
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(compactionJobList);

    SendJobs sendJobs = mock(SendJobs.class);
    FileReference fileReference =
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build();
    doThrow(new FileReferenceAssignedToJobException(fileReference))
        .when(sendJobs)
        .send(Mockito.<List<CompactionJob>>any());

    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    doNothing().when(sendDeadLetter).send(Mockito.<CompactionJobDispatchRequest>any());

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            sendJobs,
            3,
            mock(ReturnRequestToPendingQueue.class),
            sendDeadLetter,
            mock(Supplier.class));

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read("Get", "null/compactions/42.json");
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(sendJobs).send(isA(List.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(null);
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code Received compaction batch for table {},
   *       held at: {}}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName(
      "Test dispatch(CompactionJobDispatchRequest); given ArrayList() add 'Received compaction batch for table {}, held at: {}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_givenArrayListAddReceivedCompactionBatchForTableHeldAt() {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult2 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult2 =
        builderResult2
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult2 =
        filesSnapshotLoaderResult2
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult2 =
        minTransactionsAheadToLoadSnapshotResult2
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult2 =
        partitionsSnapshotLoaderResult2.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange2 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult2 =
        partitionsStateUpdateClockResult2.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange2));
    TableStatus sleeperTable2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult2 =
        retryBackoffResult2
            .sleeperTable(sleeperTable2)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult2
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult3 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult3 =
        builderResult3
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult3 =
        filesSnapshotLoaderResult3
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult3 =
        minTransactionsAheadToLoadSnapshotResult3
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult3 =
        partitionsSnapshotLoaderResult3.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange3 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult3 =
        partitionsStateUpdateClockResult3.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange3));
    TableStatus sleeperTable3 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult3 =
        retryBackoffResult3
            .sleeperTable(sleeperTable3)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult3
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult4 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult4 =
        builderResult4
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult4 =
        filesSnapshotLoaderResult4
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult4 =
        minTransactionsAheadToLoadSnapshotResult4
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult4 =
        partitionsSnapshotLoaderResult4.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange4 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult4 =
        partitionsStateUpdateClockResult4.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange4));
    TableStatus sleeperTable4 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult4 =
        retryBackoffResult4
            .sleeperTable(sleeperTable4)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult4
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult5 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult5 =
        builderResult5
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Builder minTransactionsAheadToLoadSnapshotResult5 =
        filesSnapshotLoaderResult5
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult5 =
        minTransactionsAheadToLoadSnapshotResult5
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult5 =
        partitionsSnapshotLoaderResult5.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange5 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult5 =
        partitionsStateUpdateClockResult5.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange5));
    TableStatus sleeperTable5 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult5 =
        retryBackoffResult5
            .sleeperTable(sleeperTable5)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));

    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any()))
        .thenReturn(
            timeBetweenTransactionChecksResult5
                .transactionBodyStore(new InMemoryTransactionBodyStore())
                .updateLogBeforeAddTransaction(true)
                .build());

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("Received compaction batch for table {}, held at: {}");
    CompactionJob compactionJob =
        CompactionJob.builder()
            .inputFiles(inputFiles)
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    ArrayList<CompactionJob> compactionJobList = new ArrayList<>();
    compactionJobList.add(compactionJob);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(compactionJobList);

    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    doNothing().when(sendDeadLetter).send(Mockito.<CompactionJobDispatchRequest>any());

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            mock(SendJobs.class),
            3,
            mock(ReturnRequestToPendingQueue.class),
            sendDeadLetter,
            mock(Supplier.class));

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read("Get", "null/compactions/42.json");
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(null);
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <ul>
   *   <li>Given {@link SendJobs} {@link SendJobs#send(List)} does nothing.
   *   <li>Then calls {@link SendJobs#send(List)}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName(
      "Test dispatch(CompactionJobDispatchRequest); given SendJobs send(List) does nothing; then calls send(List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_givenSendJobsSendDoesNothing_thenCallsSend() {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult2 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult2 =
        builderResult2
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult2 =
        filesSnapshotLoaderResult2
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult2 =
        minTransactionsAheadToLoadSnapshotResult2
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult2 =
        partitionsSnapshotLoaderResult2.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange2 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult2 =
        partitionsStateUpdateClockResult2.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange2));
    TableStatus sleeperTable2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult2 =
        retryBackoffResult2
            .sleeperTable(sleeperTable2)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult2
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult3 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult3 =
        builderResult3
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult3 =
        filesSnapshotLoaderResult3
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult3 =
        minTransactionsAheadToLoadSnapshotResult3
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult3 =
        partitionsSnapshotLoaderResult3.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange3 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult3 =
        partitionsStateUpdateClockResult3.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange3));
    TableStatus sleeperTable3 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult3 =
        retryBackoffResult3
            .sleeperTable(sleeperTable3)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult3
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult4 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult4 =
        builderResult4
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult4 =
        filesSnapshotLoaderResult4
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult4 =
        minTransactionsAheadToLoadSnapshotResult4
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult4 =
        partitionsSnapshotLoaderResult4.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange4 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult4 =
        partitionsStateUpdateClockResult4.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange4));
    TableStatus sleeperTable4 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult4 =
        retryBackoffResult4
            .sleeperTable(sleeperTable4)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult4
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult5 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult5 =
        builderResult5
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Builder minTransactionsAheadToLoadSnapshotResult5 =
        filesSnapshotLoaderResult5
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult5 =
        minTransactionsAheadToLoadSnapshotResult5
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult5 =
        partitionsSnapshotLoaderResult5.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange5 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult5 =
        partitionsStateUpdateClockResult5.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange5));
    TableStatus sleeperTable5 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult5 =
        retryBackoffResult5
            .sleeperTable(sleeperTable5)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));

    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any()))
        .thenReturn(
            timeBetweenTransactionChecksResult5
                .transactionBodyStore(new InMemoryTransactionBodyStore())
                .updateLogBeforeAddTransaction(true)
                .build());

    ArrayList<CompactionJob> compactionJobList = new ArrayList<>();

    CompactionJob.Builder builderResult6 = CompactionJob.builder();
    compactionJobList.add(
        builderResult6
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(compactionJobList);

    SendJobs sendJobs = mock(SendJobs.class);
    doNothing().when(sendJobs).send(Mockito.<List<CompactionJob>>any());

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            sendJobs,
            3,
            mock(ReturnRequestToPendingQueue.class),
            mock(SendDeadLetter.class),
            mock(Supplier.class));

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read("Get", "null/compactions/42.json");
    verify(sendJobs).send(isA(List.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(null);
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <ul>
   *   <li>Given {@link SendJobs} {@link SendJobs#send(List)} does nothing.
   *   <li>Then calls {@link SendJobs#send(List)}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName(
      "Test dispatch(CompactionJobDispatchRequest); given SendJobs send(List) does nothing; then calls send(List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_givenSendJobsSendDoesNothing_thenCallsSend2() {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult2 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult2 =
        builderResult2
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult2 =
        filesSnapshotLoaderResult2
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult2 =
        minTransactionsAheadToLoadSnapshotResult2
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult2 =
        partitionsSnapshotLoaderResult2.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange2 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult2 =
        partitionsStateUpdateClockResult2.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange2));
    TableStatus sleeperTable2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult2 =
        retryBackoffResult2
            .sleeperTable(sleeperTable2)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult2
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult3 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult3 =
        builderResult3
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult3 =
        filesSnapshotLoaderResult3
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult3 =
        minTransactionsAheadToLoadSnapshotResult3
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult3 =
        partitionsSnapshotLoaderResult3.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange3 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult3 =
        partitionsStateUpdateClockResult3.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange3));
    TableStatus sleeperTable3 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult3 =
        retryBackoffResult3
            .sleeperTable(sleeperTable3)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult3
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult4 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult4 =
        builderResult4
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult4 =
        filesSnapshotLoaderResult4
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult4 =
        minTransactionsAheadToLoadSnapshotResult4
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult4 =
        partitionsSnapshotLoaderResult4.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange4 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult4 =
        partitionsStateUpdateClockResult4.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange4));
    TableStatus sleeperTable4 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult4 =
        retryBackoffResult4
            .sleeperTable(sleeperTable4)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult4
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult5 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult5 =
        builderResult5
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Builder minTransactionsAheadToLoadSnapshotResult5 =
        filesSnapshotLoaderResult5
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult5 =
        minTransactionsAheadToLoadSnapshotResult5
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult5 =
        partitionsSnapshotLoaderResult5.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange5 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult5 =
        partitionsStateUpdateClockResult5.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange5));
    TableStatus sleeperTable5 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult5 =
        retryBackoffResult5
            .sleeperTable(sleeperTable5)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));

    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any()))
        .thenReturn(
            timeBetweenTransactionChecksResult5
                .transactionBodyStore(new InMemoryTransactionBodyStore())
                .updateLogBeforeAddTransaction(true)
                .build());

    ArrayList<CompactionJob> compactionJobList = new ArrayList<>();

    CompactionJob.Builder builderResult6 = CompactionJob.builder();
    compactionJobList.add(
        builderResult6
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    CompactionJob.Builder builderResult7 = CompactionJob.builder();
    compactionJobList.add(
        builderResult7
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(compactionJobList);

    SendJobs sendJobs = mock(SendJobs.class);
    doNothing().when(sendJobs).send(Mockito.<List<CompactionJob>>any());

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            sendJobs,
            3,
            mock(ReturnRequestToPendingQueue.class),
            mock(SendDeadLetter.class),
            mock(Supplier.class));

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read("Get", "null/compactions/42.json");
    verify(sendJobs).send(isA(List.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(null);
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <ul>
   *   <li>Given {@link TableProperties#TableProperties(InstanceProperties)} with instanceProperties
   *       is {@link InstanceProperties#InstanceProperties()}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName(
      "Test dispatch(CompactionJobDispatchRequest); given TableProperties(InstanceProperties) with instanceProperties is InstanceProperties()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_givenTablePropertiesWithInstancePropertiesIsInstanceProperties() {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult2 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult2 =
        builderResult2
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult2 =
        filesSnapshotLoaderResult2
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult2 =
        minTransactionsAheadToLoadSnapshotResult2
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult2 =
        partitionsSnapshotLoaderResult2.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange2 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult2 =
        partitionsStateUpdateClockResult2.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange2));
    TableStatus sleeperTable2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult2 =
        retryBackoffResult2
            .sleeperTable(sleeperTable2)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult2
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult3 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult3 =
        builderResult3
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult3 =
        filesSnapshotLoaderResult3
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult3 =
        minTransactionsAheadToLoadSnapshotResult3
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult3 =
        partitionsSnapshotLoaderResult3.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange3 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult3 =
        partitionsStateUpdateClockResult3.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange3));
    TableStatus sleeperTable3 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult3 =
        retryBackoffResult3
            .sleeperTable(sleeperTable3)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult3
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult4 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult4 =
        builderResult4
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Builder minTransactionsAheadToLoadSnapshotResult4 =
        filesSnapshotLoaderResult4
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult4 =
        minTransactionsAheadToLoadSnapshotResult4
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult4 =
        partitionsSnapshotLoaderResult4.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange4 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult4 =
        partitionsStateUpdateClockResult4.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange4));
    TableStatus sleeperTable4 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult4 =
        retryBackoffResult4
            .sleeperTable(sleeperTable4)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    TransactionLogStateStore stateStore =
        timeBetweenTransactionChecksResult4
            .transactionBodyStore(new InMemoryTransactionBodyStore())
            .updateLogBeforeAddTransaction(true)
            .build();
    FixedStateStoreProvider stateStoreProvider =
        new FixedStateStoreProvider(new TableProperties(new InstanceProperties()), stateStore);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ArrayList<>());

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            mock(SendJobs.class),
            3,
            mock(ReturnRequestToPendingQueue.class),
            mock(SendDeadLetter.class),
            mock(Supplier.class));

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read("Get", "null/compactions/42.json");
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(null);
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <ul>
   *   <li>Then calls {@link StateStoreProvider#getStateStore(TableProperties)}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName(
      "Test dispatch(CompactionJobDispatchRequest); then calls getStateStore(TableProperties)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_thenCallsGetStateStore() {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult2 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult2 =
        builderResult2
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult2 =
        filesSnapshotLoaderResult2
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult2 =
        minTransactionsAheadToLoadSnapshotResult2
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult2 =
        partitionsSnapshotLoaderResult2.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange2 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult2 =
        partitionsStateUpdateClockResult2.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange2));
    TableStatus sleeperTable2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult2 =
        retryBackoffResult2
            .sleeperTable(sleeperTable2)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult2
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult3 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult3 =
        builderResult3
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult3 =
        filesSnapshotLoaderResult3
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult3 =
        minTransactionsAheadToLoadSnapshotResult3
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult3 =
        partitionsSnapshotLoaderResult3.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange3 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult3 =
        partitionsStateUpdateClockResult3.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange3));
    TableStatus sleeperTable3 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult3 =
        retryBackoffResult3
            .sleeperTable(sleeperTable3)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult3
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult4 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult4 =
        builderResult4
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult4 =
        filesSnapshotLoaderResult4
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult4 =
        minTransactionsAheadToLoadSnapshotResult4
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult4 =
        partitionsSnapshotLoaderResult4.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange4 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult4 =
        partitionsStateUpdateClockResult4.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange4));
    TableStatus sleeperTable4 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult4 =
        retryBackoffResult4
            .sleeperTable(sleeperTable4)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult4
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult5 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult5 =
        builderResult5
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Builder minTransactionsAheadToLoadSnapshotResult5 =
        filesSnapshotLoaderResult5
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult5 =
        minTransactionsAheadToLoadSnapshotResult5
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult5 =
        partitionsSnapshotLoaderResult5.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange5 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult5 =
        partitionsStateUpdateClockResult5.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange5));
    TableStatus sleeperTable5 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult5 =
        retryBackoffResult5
            .sleeperTable(sleeperTable5)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));

    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any()))
        .thenReturn(
            timeBetweenTransactionChecksResult5
                .transactionBodyStore(new InMemoryTransactionBodyStore())
                .updateLogBeforeAddTransaction(true)
                .build());

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ArrayList<>());

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            mock(SendJobs.class),
            3,
            mock(ReturnRequestToPendingQueue.class),
            mock(SendDeadLetter.class),
            mock(Supplier.class));

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read("Get", "null/compactions/42.json");
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(null);
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <ul>
   *   <li>Then calls {@link TablePropertiesStore#loadById(String)}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); then calls loadById(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_thenCallsLoadById() {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult2 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult2 =
        builderResult2
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult2 =
        filesSnapshotLoaderResult2
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult2 =
        minTransactionsAheadToLoadSnapshotResult2
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult2 =
        partitionsSnapshotLoaderResult2.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange2 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult2 =
        partitionsStateUpdateClockResult2.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange2));
    TableStatus sleeperTable2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult2 =
        retryBackoffResult2
            .sleeperTable(sleeperTable2)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult2
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    TablePropertiesStore propertiesStore = mock(TablePropertiesStore.class);
    when(propertiesStore.loadById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    TablePropertiesProvider tablePropertiesProvider =
        new TablePropertiesProvider(new InstanceProperties(), propertiesStore);

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult3 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult3 =
        builderResult3
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Builder minTransactionsAheadToLoadSnapshotResult3 =
        filesSnapshotLoaderResult3
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult3 =
        minTransactionsAheadToLoadSnapshotResult3
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult3 =
        partitionsSnapshotLoaderResult3.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange3 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult3 =
        partitionsStateUpdateClockResult3.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange3));
    TableStatus sleeperTable3 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult3 =
        retryBackoffResult3
            .sleeperTable(sleeperTable3)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));

    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any()))
        .thenReturn(
            timeBetweenTransactionChecksResult3
                .transactionBodyStore(new InMemoryTransactionBodyStore())
                .updateLogBeforeAddTransaction(true)
                .build());
    StateStoreProvider stateStoreProvider =
        new StateStoreProvider(new InstanceProperties(), stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ArrayList<>());

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            mock(SendJobs.class),
            3,
            mock(ReturnRequestToPendingQueue.class),
            mock(SendDeadLetter.class),
            mock(Supplier.class));

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read("Get", "null/compactions/42.json");
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(propertiesStore).loadById(null);
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <ul>
   *   <li>Then calls {@link TableProperties#validate()}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); then calls validate()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_thenCallsValidate() throws SleeperPropertiesInvalidException {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Optional<TableStatus> ofResult = Optional.of(uniqueIdAndNameResult);
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);

    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    TableStatus uniqueIdAndNameResult2 = TableStatus.uniqueIdAndName("42", "Table Name", true);
    when(tableProperties.getStatus()).thenReturn(uniqueIdAndNameResult2);

    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);

    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);
    TablePropertiesProvider tablePropertiesProvider =
        new TablePropertiesProvider(new InstanceProperties(), propertiesStore);

    Factory stateStoreFactory = mock(Factory.class);
    FileReference fileReference =
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build();
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any()))
        .thenThrow(new FileReferenceAssignedToJobException(fileReference));
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ArrayList<>());

    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    doNothing().when(sendDeadLetter).send(Mockito.<CompactionJobDispatchRequest>any());
    InstanceProperties instanceProperties = new InstanceProperties();

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            mock(SendJobs.class),
            3,
            mock(ReturnRequestToPendingQueue.class),
            sendDeadLetter,
            mock(Supplier.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    compactionJobDispatcher.dispatch(request);

    // Assert
    verify(readBatch).read(null, "null/compactions/42.json");
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByUniqueId(null);
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   *
   * <ul>
   *   <li>Then throw {@link StateStoreException}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); then throw StateStoreException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_thenThrowStateStoreException() {
    // Arrange
    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult =
        minTransactionsAheadToLoadSnapshotResult
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult2 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult2 =
        builderResult2
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult2 =
        filesSnapshotLoaderResult2
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult2 =
        minTransactionsAheadToLoadSnapshotResult2
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult2 =
        partitionsSnapshotLoaderResult2.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange2 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult2 =
        partitionsStateUpdateClockResult2.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange2));
    TableStatus sleeperTable2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult2 =
        retryBackoffResult2
            .sleeperTable(sleeperTable2)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult2
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult3 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult3 =
        builderResult3
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult3 =
        filesSnapshotLoaderResult3
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult3 =
        minTransactionsAheadToLoadSnapshotResult3
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult3 =
        partitionsSnapshotLoaderResult3.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange3 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult3 =
        partitionsStateUpdateClockResult3.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange3));
    TableStatus sleeperTable3 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult3 =
        retryBackoffResult3
            .sleeperTable(sleeperTable3)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult3
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    Builder builderResult4 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult4 =
        builderResult4
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder minTransactionsAheadToLoadSnapshotResult4 =
        filesSnapshotLoaderResult4
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult4 =
        minTransactionsAheadToLoadSnapshotResult4
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult4 =
        partitionsSnapshotLoaderResult4.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange4 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult4 =
        partitionsStateUpdateClockResult4.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange4));
    TableStatus sleeperTable4 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult4 =
        retryBackoffResult4
            .sleeperTable(sleeperTable4)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    timeBetweenTransactionChecksResult4
        .transactionBodyStore(new InMemoryTransactionBodyStore())
        .updateLogBeforeAddTransaction(true)
        .build();

    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult5 = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult5 =
        builderResult5
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Builder minTransactionsAheadToLoadSnapshotResult5 =
        filesSnapshotLoaderResult5
            .filesStateUpdateClock(request::getCreateTime)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsSnapshotLoaderResult5 =
        minTransactionsAheadToLoadSnapshotResult5
            .partitionsLogStore(new InMemoryTransactionLogStore())
            .partitionsSnapshotLoader(mock(TransactionLogSnapshotLoader.class));

    Builder partitionsStateUpdateClockResult5 =
        partitionsSnapshotLoaderResult5.partitionsStateUpdateClock(request::getCreateTime);
    WaitRange waitRange5 = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult5 =
        partitionsStateUpdateClockResult5.retryBackoff(
            new ExponentialBackoffWithJitter(waitRange5));
    TableStatus sleeperTable5 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult5 =
        retryBackoffResult5
            .sleeperTable(sleeperTable5)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));

    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any()))
        .thenReturn(
            timeBetweenTransactionChecksResult5
                .transactionBodyStore(new InMemoryTransactionBodyStore())
                .updateLogBeforeAddTransaction(true)
                .build());

    ArrayList<CompactionJob> compactionJobList = new ArrayList<>();

    CompactionJob.Builder builderResult6 = CompactionJob.builder();
    compactionJobList.add(
        builderResult6
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(compactionJobList);

    SendJobs sendJobs = mock(SendJobs.class);
    doThrow(new StateStoreException("An error occurred"))
        .when(sendJobs)
        .send(Mockito.<List<CompactionJob>>any());

    CompactionJobDispatcher compactionJobDispatcher =
        new CompactionJobDispatcher(
            instanceProperties,
            tablePropertiesProvider,
            stateStoreProvider,
            new InMemoryCompactionJobTracker(),
            readBatch,
            sendJobs,
            3,
            mock(ReturnRequestToPendingQueue.class),
            mock(SendDeadLetter.class),
            mock(Supplier.class));

    // Act and Assert
    assertThrows(StateStoreException.class, () -> compactionJobDispatcher.dispatch(request));
    verify(readBatch).read("Get", "null/compactions/42.json");
    verify(sendJobs).send(isA(List.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(null);
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }
}
