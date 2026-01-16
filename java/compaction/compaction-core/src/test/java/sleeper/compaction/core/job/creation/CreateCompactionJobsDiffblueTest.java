package sleeper.compaction.core.job.creation;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.creation.CreateCompactionJobs.BatchJobsWriter;
import sleeper.compaction.core.job.creation.CreateCompactionJobs.BatchMessageSender;
import sleeper.compaction.core.job.creation.CreateCompactionJobs.GenerateBatchId;
import sleeper.compaction.core.job.creation.CreateCompactionJobs.GenerateJobId;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.commit.StateStoreCommitRequestSender;
import sleeper.core.statestore.testutils.FixedStateStoreProvider;
import sleeper.core.statestore.testutils.InMemoryTransactionBodyStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStore;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore.Builder;
import sleeper.core.statestore.transactionlog.log.DuplicateTransactionNumberException;
import sleeper.core.statestore.transactionlog.log.TransactionLogEntry;
import sleeper.core.statestore.transactionlog.log.TransactionLogRange;
import sleeper.core.statestore.transactionlog.snapshot.TransactionLogSnapshot;
import sleeper.core.statestore.transactionlog.snapshot.TransactionLogSnapshotLoader;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.table.TableStatus;
import sleeper.core.util.ExponentialBackoffWithJitter;
import sleeper.core.util.ExponentialBackoffWithJitter.WaitRange;
import sleeper.core.util.ObjectFactory;
import sleeper.core.util.ObjectFactoryException;

class CreateCompactionJobsDiffblueTest {
  /**
   * Test {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}.
   *
   * <ul>
   *   <li>Then calls {@link Consumer#accept(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobsWithStrategy(TableProperties); then calls accept(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateCompactionJobs.createJobsWithStrategy(TableProperties)"})
  void testCreateJobsWithStrategy_thenCallsAccept()
      throws IOException, DuplicateTransactionNumberException, ObjectFactoryException {
    // Arrange
    Consumer<TransactionLogEntry> onReadTransactionLogEntry = mock(Consumer.class);
    doNothing().when(onReadTransactionLogEntry).accept(Mockito.<TransactionLogEntry>any());

    InMemoryTransactionLogStore filesLogStore = new InMemoryTransactionLogStore();
    filesLogStore.onReadTransactionLogEntry(onReadTransactionLogEntry);
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    AddFilesTransaction.Builder builderResult = AddFilesTransaction.builder();
    AddFilesTransaction transaction =
        builderResult
            .files(new ArrayList<>())
            .jobId("42")
            .jobRunId("42")
            .taskId("42")
            .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .build();

    TransactionLogEntry entry = new TransactionLogEntry(1L, updateTime, transaction);
    filesLogStore.addTransaction(entry);

    Builder filesLogStoreResult = TransactionLogStateStore.builder().filesLogStore(filesLogStore);

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder filesSnapshotLoaderResult =
        filesLogStoreResult.filesSnapshotLoader(filesSnapshotLoader);

    Supplier<Instant> filesStateUpdateClock = mock(Supplier.class);
    when(filesStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(filesStateUpdateClock)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsLogStoreResult =
        minTransactionsAheadToLoadSnapshotResult.partitionsLogStore(
            new InMemoryTransactionLogStore());

    TransactionLogSnapshotLoader partitionsSnapshotLoader =
        mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult2 =
        Optional.of(TransactionLogSnapshot.partitionsInitialState());
    when(partitionsSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult2);

    Builder partitionsSnapshotLoaderResult =
        partitionsLogStoreResult.partitionsSnapshotLoader(partitionsSnapshotLoader);

    Supplier<Instant> partitionsStateUpdateClock = mock(Supplier.class);
    when(partitionsStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(partitionsStateUpdateClock);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    TransactionLogStateStore stateStore =
        timeBetweenTransactionChecksResult
            .transactionBodyStore(new InMemoryTransactionBodyStore())
            .updateLogBeforeAddTransaction(true)
            .build();
    FixedStateStoreProvider stateStoreProvider =
        new FixedStateStoreProvider(new TableProperties(new InstanceProperties()), stateStore);
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender =
        mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);

    CreateCompactionJobs createCompactionJobs =
        new CreateCompactionJobs(
            objectFactory,
            instanceProperties,
            stateStoreProvider,
            batchJobsWriter,
            batchMessageSender,
            stateStoreCommitSender,
            generateJobId,
            generateBatchId,
            new Random(),
            mock(Supplier.class));

    // Act
    createCompactionJobs.createJobsWithStrategy(new TableProperties(new InstanceProperties()));

    // Assert
    verify(onReadTransactionLogEntry).accept(isA(TransactionLogEntry.class));
    verify(filesStateUpdateClock, atLeast(1)).get();
    verify(partitionsStateUpdateClock, atLeast(1)).get();
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
    verify(partitionsSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}.
   *
   * <ul>
   *   <li>Then calls {@link Supplier#get()}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobsWithStrategy(TableProperties); then calls get()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateCompactionJobs.createJobsWithStrategy(TableProperties)"})
  void testCreateJobsWithStrategy_thenCallsGet() throws IOException, ObjectFactoryException {
    // Arrange
    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Supplier<Instant> filesStateUpdateClock = mock(Supplier.class);
    when(filesStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(filesStateUpdateClock)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsLogStoreResult =
        minTransactionsAheadToLoadSnapshotResult.partitionsLogStore(
            new InMemoryTransactionLogStore());

    TransactionLogSnapshotLoader partitionsSnapshotLoader =
        mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult2 =
        Optional.of(TransactionLogSnapshot.partitionsInitialState());
    when(partitionsSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult2);

    Builder partitionsSnapshotLoaderResult =
        partitionsLogStoreResult.partitionsSnapshotLoader(partitionsSnapshotLoader);

    Supplier<Instant> partitionsStateUpdateClock = mock(Supplier.class);
    when(partitionsStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(partitionsStateUpdateClock);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    TransactionLogStateStore stateStore =
        timeBetweenTransactionChecksResult
            .transactionBodyStore(new InMemoryTransactionBodyStore())
            .updateLogBeforeAddTransaction(true)
            .build();
    FixedStateStoreProvider stateStoreProvider =
        new FixedStateStoreProvider(new TableProperties(new InstanceProperties()), stateStore);
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender =
        mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);

    CreateCompactionJobs createCompactionJobs =
        new CreateCompactionJobs(
            objectFactory,
            instanceProperties,
            stateStoreProvider,
            batchJobsWriter,
            batchMessageSender,
            stateStoreCommitSender,
            generateJobId,
            generateBatchId,
            new Random(),
            mock(Supplier.class));

    // Act
    createCompactionJobs.createJobsWithStrategy(new TableProperties(new InstanceProperties()));

    // Assert
    verify(filesStateUpdateClock, atLeast(1)).get();
    verify(partitionsStateUpdateClock, atLeast(1)).get();
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
    verify(partitionsSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}.
   *
   * <ul>
   *   <li>Then calls {@link Factory#getStateStore(TableProperties)}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}
   */
  @Test
  @DisplayName(
      "Test createJobsWithStrategy(TableProperties); then calls getStateStore(TableProperties)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateCompactionJobs.createJobsWithStrategy(TableProperties)"})
  void testCreateJobsWithStrategy_thenCallsGetStateStore()
      throws IOException, ObjectFactoryException {
    // Arrange
    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Supplier<Instant> filesStateUpdateClock = mock(Supplier.class);
    when(filesStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(filesStateUpdateClock)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsLogStoreResult =
        minTransactionsAheadToLoadSnapshotResult.partitionsLogStore(
            new InMemoryTransactionLogStore());

    TransactionLogSnapshotLoader partitionsSnapshotLoader =
        mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult2 =
        Optional.of(TransactionLogSnapshot.partitionsInitialState());
    when(partitionsSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult2);

    Builder partitionsSnapshotLoaderResult =
        partitionsLogStoreResult.partitionsSnapshotLoader(partitionsSnapshotLoader);

    Supplier<Instant> partitionsStateUpdateClock = mock(Supplier.class);
    when(partitionsStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(partitionsStateUpdateClock);
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
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender =
        mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);

    CreateCompactionJobs createCompactionJobs =
        new CreateCompactionJobs(
            objectFactory,
            instanceProperties,
            stateStoreProvider,
            batchJobsWriter,
            batchMessageSender,
            stateStoreCommitSender,
            generateJobId,
            generateBatchId,
            new Random(),
            mock(Supplier.class));

    // Act
    createCompactionJobs.createJobsWithStrategy(new TableProperties(new InstanceProperties()));

    // Assert
    verify(filesStateUpdateClock, atLeast(1)).get();
    verify(partitionsStateUpdateClock, atLeast(1)).get();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
    verify(partitionsSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}.
   *
   * <ul>
   *   <li>Then calls {@link Consumer#accept(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobWithForceAllFiles(TableProperties); then calls accept(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateCompactionJobs.createJobWithForceAllFiles(TableProperties)"})
  void testCreateJobWithForceAllFiles_thenCallsAccept()
      throws IOException, DuplicateTransactionNumberException, ObjectFactoryException {
    // Arrange
    Consumer<TransactionLogEntry> onReadTransactionLogEntry = mock(Consumer.class);
    doNothing().when(onReadTransactionLogEntry).accept(Mockito.<TransactionLogEntry>any());

    InMemoryTransactionLogStore filesLogStore = new InMemoryTransactionLogStore();
    filesLogStore.onReadTransactionLogEntry(onReadTransactionLogEntry);
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    AddFilesTransaction.Builder builderResult = AddFilesTransaction.builder();
    AddFilesTransaction transaction =
        builderResult
            .files(new ArrayList<>())
            .jobId("42")
            .jobRunId("42")
            .taskId("42")
            .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .build();

    TransactionLogEntry entry = new TransactionLogEntry(1L, updateTime, transaction);
    filesLogStore.addTransaction(entry);

    Builder filesLogStoreResult = TransactionLogStateStore.builder().filesLogStore(filesLogStore);

    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder filesSnapshotLoaderResult =
        filesLogStoreResult.filesSnapshotLoader(filesSnapshotLoader);

    Supplier<Instant> filesStateUpdateClock = mock(Supplier.class);
    when(filesStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(filesStateUpdateClock)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsLogStoreResult =
        minTransactionsAheadToLoadSnapshotResult.partitionsLogStore(
            new InMemoryTransactionLogStore());

    TransactionLogSnapshotLoader partitionsSnapshotLoader =
        mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult2 =
        Optional.of(TransactionLogSnapshot.partitionsInitialState());
    when(partitionsSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult2);

    Builder partitionsSnapshotLoaderResult =
        partitionsLogStoreResult.partitionsSnapshotLoader(partitionsSnapshotLoader);

    Supplier<Instant> partitionsStateUpdateClock = mock(Supplier.class);
    when(partitionsStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(partitionsStateUpdateClock);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    TransactionLogStateStore stateStore =
        timeBetweenTransactionChecksResult
            .transactionBodyStore(new InMemoryTransactionBodyStore())
            .updateLogBeforeAddTransaction(true)
            .build();
    FixedStateStoreProvider stateStoreProvider =
        new FixedStateStoreProvider(new TableProperties(new InstanceProperties()), stateStore);
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender =
        mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);

    CreateCompactionJobs createCompactionJobs =
        new CreateCompactionJobs(
            objectFactory,
            instanceProperties,
            stateStoreProvider,
            batchJobsWriter,
            batchMessageSender,
            stateStoreCommitSender,
            generateJobId,
            generateBatchId,
            new Random(),
            mock(Supplier.class));

    // Act
    createCompactionJobs.createJobWithForceAllFiles(new TableProperties(new InstanceProperties()));

    // Assert
    verify(onReadTransactionLogEntry).accept(isA(TransactionLogEntry.class));
    verify(filesStateUpdateClock, atLeast(1)).get();
    verify(partitionsStateUpdateClock, atLeast(1)).get();
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
    verify(partitionsSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}.
   *
   * <ul>
   *   <li>Then calls {@link Supplier#get()}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobWithForceAllFiles(TableProperties); then calls get()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateCompactionJobs.createJobWithForceAllFiles(TableProperties)"})
  void testCreateJobWithForceAllFiles_thenCallsGet() throws IOException, ObjectFactoryException {
    // Arrange
    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Supplier<Instant> filesStateUpdateClock = mock(Supplier.class);
    when(filesStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(filesStateUpdateClock)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsLogStoreResult =
        minTransactionsAheadToLoadSnapshotResult.partitionsLogStore(
            new InMemoryTransactionLogStore());

    TransactionLogSnapshotLoader partitionsSnapshotLoader =
        mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult2 =
        Optional.of(TransactionLogSnapshot.partitionsInitialState());
    when(partitionsSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult2);

    Builder partitionsSnapshotLoaderResult =
        partitionsLogStoreResult.partitionsSnapshotLoader(partitionsSnapshotLoader);

    Supplier<Instant> partitionsStateUpdateClock = mock(Supplier.class);
    when(partitionsStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(partitionsStateUpdateClock);
    WaitRange waitRange = WaitRange.firstAndMaxWaitCeilingSecs(10.0d, 10.0d);

    Builder retryBackoffResult =
        partitionsStateUpdateClockResult.retryBackoff(new ExponentialBackoffWithJitter(waitRange));
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    Builder timeBetweenTransactionChecksResult =
        retryBackoffResult
            .sleeperTable(sleeperTable)
            .timeBetweenSnapshotChecks(Duration.ofSeconds(1L))
            .timeBetweenTransactionChecks(Duration.ofSeconds(1L));
    TransactionLogStateStore stateStore =
        timeBetweenTransactionChecksResult
            .transactionBodyStore(new InMemoryTransactionBodyStore())
            .updateLogBeforeAddTransaction(true)
            .build();
    FixedStateStoreProvider stateStoreProvider =
        new FixedStateStoreProvider(new TableProperties(new InstanceProperties()), stateStore);
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender =
        mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);

    CreateCompactionJobs createCompactionJobs =
        new CreateCompactionJobs(
            objectFactory,
            instanceProperties,
            stateStoreProvider,
            batchJobsWriter,
            batchMessageSender,
            stateStoreCommitSender,
            generateJobId,
            generateBatchId,
            new Random(),
            mock(Supplier.class));

    // Act
    createCompactionJobs.createJobWithForceAllFiles(new TableProperties(new InstanceProperties()));

    // Assert
    verify(filesStateUpdateClock, atLeast(1)).get();
    verify(partitionsStateUpdateClock, atLeast(1)).get();
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
    verify(partitionsSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}.
   *
   * <ul>
   *   <li>Then calls {@link Factory#getStateStore(TableProperties)}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}
   */
  @Test
  @DisplayName(
      "Test createJobWithForceAllFiles(TableProperties); then calls getStateStore(TableProperties)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateCompactionJobs.createJobWithForceAllFiles(TableProperties)"})
  void testCreateJobWithForceAllFiles_thenCallsGetStateStore()
      throws IOException, ObjectFactoryException {
    // Arrange
    TransactionLogSnapshotLoader filesSnapshotLoader = mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult =
        Optional.of(TransactionLogSnapshot.filesInitialState());
    when(filesSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult);

    Builder builderResult = TransactionLogStateStore.builder();

    Builder filesSnapshotLoaderResult =
        builderResult
            .filesLogStore(new InMemoryTransactionLogStore())
            .filesSnapshotLoader(filesSnapshotLoader);

    Supplier<Instant> filesStateUpdateClock = mock(Supplier.class);
    when(filesStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder minTransactionsAheadToLoadSnapshotResult =
        filesSnapshotLoaderResult
            .filesStateUpdateClock(filesStateUpdateClock)
            .maxAddTransactionAttempts(3)
            .minTransactionsAheadToLoadSnapshot(1L);

    Builder partitionsLogStoreResult =
        minTransactionsAheadToLoadSnapshotResult.partitionsLogStore(
            new InMemoryTransactionLogStore());

    TransactionLogSnapshotLoader partitionsSnapshotLoader =
        mock(TransactionLogSnapshotLoader.class);
    Optional<TransactionLogSnapshot> ofResult2 =
        Optional.of(TransactionLogSnapshot.partitionsInitialState());
    when(partitionsSnapshotLoader.loadLatestSnapshotInRange(Mockito.<TransactionLogRange>any()))
        .thenReturn(ofResult2);

    Builder partitionsSnapshotLoaderResult =
        partitionsLogStoreResult.partitionsSnapshotLoader(partitionsSnapshotLoader);

    Supplier<Instant> partitionsStateUpdateClock = mock(Supplier.class);
    when(partitionsStateUpdateClock.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder partitionsStateUpdateClockResult =
        partitionsSnapshotLoaderResult.partitionsStateUpdateClock(partitionsStateUpdateClock);
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
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender =
        mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);

    CreateCompactionJobs createCompactionJobs =
        new CreateCompactionJobs(
            objectFactory,
            instanceProperties,
            stateStoreProvider,
            batchJobsWriter,
            batchMessageSender,
            stateStoreCommitSender,
            generateJobId,
            generateBatchId,
            new Random(),
            mock(Supplier.class));

    // Act
    createCompactionJobs.createJobWithForceAllFiles(new TableProperties(new InstanceProperties()));

    // Assert
    verify(filesStateUpdateClock, atLeast(1)).get();
    verify(partitionsStateUpdateClock, atLeast(1)).get();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(filesSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
    verify(partitionsSnapshotLoader).loadLatestSnapshotInRange(isA(TransactionLogRange.class));
  }
}
