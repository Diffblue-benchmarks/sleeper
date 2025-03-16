package sleeper.compaction.core.job;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.CompactionJob.Builder;
import sleeper.compaction.core.job.CompactionJobCommitterOrSendToLambda.BatchedCommitQueueSender;
import sleeper.compaction.core.job.CompactionJobCommitterOrSendToLambda.CommitQueueSender;
import sleeper.compaction.core.job.commit.CompactionCommitMessage;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.properties.validation.DefaultAsyncCommitBehaviour;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.update.CompactionJobCommittedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
import sleeper.core.tracker.job.run.RecordsProcessed;

class CompactionJobCommitterOrSendToLambdaDiffblueTest {
  /**
   * Test {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}.
   * <p>
   * Method under test: {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test commit(CompactionJob, CompactionJobFinishedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobCommitterOrSendToLambda.commit(CompactionJob, CompactionJobFinishedEvent)"})
  void testCommit() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(false);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(0, stateStoreFactory);

    InMemoryCompactionJobTracker tracker = mock(InMemoryCompactionJobTracker.class);
    doNothing().when(tracker).jobCommitted(Mockito.<CompactionJobCommittedEvent>any());
    doNothing().when(tracker).jobFinished(Mockito.<CompactionJobFinishedEvent>any());
    CompactionJobCommitterOrSendToLambda compactionJobCommitterOrSendToLambda = new CompactionJobCommitterOrSendToLambda(
        tablePropertiesProvider, stateStoreProvider, tracker, mock(CommitQueueSender.class),
        mock(BatchedCommitQueueSender.class));
    Builder builderResult = CompactionJob.builder();
    CompactionJob job = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    CompactionJobFinishedEvent finishedEvent = mock(CompactionJobFinishedEvent.class);
    when(finishedEvent.getJobRunId()).thenReturn("42");
    when(finishedEvent.getTaskId()).thenReturn("42");
    when(finishedEvent.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act
    compactionJobCommitterOrSendToLambda.commit(job, finishedEvent);

    // Assert
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tracker).jobCommitted(isA(CompactionJobCommittedEvent.class));
    verify(tracker).jobFinished(isA(CompactionJobFinishedEvent.class));
    verify(finishedEvent).getJobRunId();
    verify(finishedEvent).getRecordsProcessed();
    verify(finishedEvent).getTaskId();
  }

  /**
   * Test {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}.
   * <ul>
   *   <li>Given {@link Factory} {@link Factory#getStateStore(TableProperties)} return {@link StateStore}.</li>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test commit(CompactionJob, CompactionJobFinishedEvent); given Factory getStateStore(TableProperties) return StateStore; then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobCommitterOrSendToLambda.commit(CompactionJob, CompactionJobFinishedEvent)"})
  void testCommit_givenFactoryGetStateStoreReturnStateStore_thenCallsGet() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(false);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    InMemoryCompactionJobTracker tracker = mock(InMemoryCompactionJobTracker.class);
    doNothing().when(tracker).jobCommitted(Mockito.<CompactionJobCommittedEvent>any());
    doNothing().when(tracker).jobFinished(Mockito.<CompactionJobFinishedEvent>any());
    CompactionJobCommitterOrSendToLambda compactionJobCommitterOrSendToLambda = new CompactionJobCommitterOrSendToLambda(
        tablePropertiesProvider, stateStoreProvider, tracker, mock(CommitQueueSender.class),
        mock(BatchedCommitQueueSender.class));
    Builder builderResult = CompactionJob.builder();
    CompactionJob job = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    CompactionJobFinishedEvent finishedEvent = mock(CompactionJobFinishedEvent.class);
    when(finishedEvent.getJobRunId()).thenReturn("42");
    when(finishedEvent.getTaskId()).thenReturn("42");
    when(finishedEvent.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act
    compactionJobCommitterOrSendToLambda.commit(job, finishedEvent);

    // Assert
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tracker).jobCommitted(isA(CompactionJobCommittedEvent.class));
    verify(tracker).jobFinished(isA(CompactionJobFinishedEvent.class));
    verify(finishedEvent).getJobRunId();
    verify(finishedEvent).getRecordsProcessed();
    verify(finishedEvent).getTaskId();
  }

  /**
   * Test {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}.
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link SleeperPropertyValues#getEnumValue(SleeperProperty, Class)} return {@code DISABLED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test commit(CompactionJob, CompactionJobFinishedEvent); given InstanceProperties getEnumValue(SleeperProperty, Class) return 'DISABLED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobCommitterOrSendToLambda.commit(CompactionJob, CompactionJobFinishedEvent)"})
  void testCommit_givenInstancePropertiesGetEnumValueReturnDisabled() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getEnumValue(Mockito.<InstanceProperty>any(),
        Mockito.<Class<DefaultAsyncCommitBehaviour>>any())).thenReturn(DefaultAsyncCommitBehaviour.DISABLED);
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(new TableProperties(instanceProperties));
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    InMemoryCompactionJobTracker tracker = mock(InMemoryCompactionJobTracker.class);
    doNothing().when(tracker).jobCommitted(Mockito.<CompactionJobCommittedEvent>any());
    doNothing().when(tracker).jobFinished(Mockito.<CompactionJobFinishedEvent>any());
    CompactionJobCommitterOrSendToLambda compactionJobCommitterOrSendToLambda = new CompactionJobCommitterOrSendToLambda(
        tablePropertiesProvider, stateStoreProvider, tracker, mock(CommitQueueSender.class),
        mock(BatchedCommitQueueSender.class));
    Builder builderResult = CompactionJob.builder();
    CompactionJob job = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    CompactionJobFinishedEvent finishedEvent = mock(CompactionJobFinishedEvent.class);
    when(finishedEvent.getJobRunId()).thenReturn("42");
    when(finishedEvent.getTaskId()).thenReturn("42");
    when(finishedEvent.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act
    compactionJobCommitterOrSendToLambda.commit(job, finishedEvent);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties).getEnumValue(isA(InstanceProperty.class), isA(Class.class));
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tracker).jobCommitted(isA(CompactionJobCommittedEvent.class));
    verify(tracker).jobFinished(isA(CompactionJobFinishedEvent.class));
    verify(finishedEvent).getJobRunId();
    verify(finishedEvent).getRecordsProcessed();
    verify(finishedEvent).getTaskId();
  }

  /**
   * Test {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}.
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link CommitQueueSender#send(StateStoreCommitRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test commit(CompactionJob, CompactionJobFinishedEvent); given InstanceProperties get(InstanceProperty) return 'Get'; then calls send(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobCommitterOrSendToLambda.commit(CompactionJob, CompactionJobFinishedEvent)"})
  void testCommit_givenInstancePropertiesGetReturnGet_thenCallsSend() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getEnumValue(Mockito.<InstanceProperty>any(),
        Mockito.<Class<DefaultAsyncCommitBehaviour>>any())).thenReturn(DefaultAsyncCommitBehaviour.PER_IMPLEMENTATION);
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(new TableProperties(instanceProperties));
    InMemoryCompactionJobTracker tracker = mock(InMemoryCompactionJobTracker.class);
    doNothing().when(tracker).jobFinished(Mockito.<CompactionJobFinishedEvent>any());
    CommitQueueSender jobCommitQueueSender = mock(CommitQueueSender.class);
    doNothing().when(jobCommitQueueSender).send(Mockito.<StateStoreCommitRequest>any());
    CompactionJobCommitterOrSendToLambda compactionJobCommitterOrSendToLambda = new CompactionJobCommitterOrSendToLambda(
        tablePropertiesProvider, new StateStoreProvider(3, mock(Factory.class)), tracker, jobCommitQueueSender,
        mock(BatchedCommitQueueSender.class));
    Builder builderResult = CompactionJob.builder();
    CompactionJob job = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    CompactionJobFinishedEvent finishedEvent = mock(CompactionJobFinishedEvent.class);
    when(finishedEvent.getJobRunId()).thenReturn("42");
    when(finishedEvent.getTaskId()).thenReturn("42");
    when(finishedEvent.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act
    compactionJobCommitterOrSendToLambda.commit(job, finishedEvent);

    // Assert
    verify(jobCommitQueueSender).send(isA(StateStoreCommitRequest.class));
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties).getEnumValue(isA(InstanceProperty.class), isA(Class.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(tracker).jobFinished(isA(CompactionJobFinishedEvent.class));
    verify(finishedEvent).getJobRunId();
    verify(finishedEvent).getRecordsProcessed();
    verify(finishedEvent).getTaskId();
  }

  /**
   * Test {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperPropertyValues#getBoolean(SleeperProperty)} return {@code true}.</li>
   *   <li>Then calls {@link BatchedCommitQueueSender#send(CompactionCommitMessage)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test commit(CompactionJob, CompactionJobFinishedEvent); given TableProperties getBoolean(SleeperProperty) return 'true'; then calls send(CompactionCommitMessage)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobCommitterOrSendToLambda.commit(CompactionJob, CompactionJobFinishedEvent)"})
  void testCommit_givenTablePropertiesGetBooleanReturnTrue_thenCallsSend() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    InMemoryCompactionJobTracker tracker = mock(InMemoryCompactionJobTracker.class);
    doNothing().when(tracker).jobFinished(Mockito.<CompactionJobFinishedEvent>any());
    BatchedCommitQueueSender batchedCommitQueueSender = mock(BatchedCommitQueueSender.class);
    doNothing().when(batchedCommitQueueSender).send(Mockito.<CompactionCommitMessage>any());
    CompactionJobCommitterOrSendToLambda compactionJobCommitterOrSendToLambda = new CompactionJobCommitterOrSendToLambda(
        tablePropertiesProvider, new StateStoreProvider(3, mock(Factory.class)), tracker, mock(CommitQueueSender.class),
        batchedCommitQueueSender);
    Builder builderResult = CompactionJob.builder();
    CompactionJob job = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    CompactionJobFinishedEvent finishedEvent = mock(CompactionJobFinishedEvent.class);
    when(finishedEvent.getJobRunId()).thenReturn("42");
    when(finishedEvent.getTaskId()).thenReturn("42");
    when(finishedEvent.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act
    compactionJobCommitterOrSendToLambda.commit(job, finishedEvent);

    // Assert
    verify(batchedCommitQueueSender).send(isA(CompactionCommitMessage.class));
    verify(tableProperties, atLeast(1)).getBoolean(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(tracker).jobFinished(isA(CompactionJobFinishedEvent.class));
    verify(finishedEvent).getJobRunId();
    verify(finishedEvent).getRecordsProcessed();
    verify(finishedEvent).getTaskId();
  }

  /**
   * Test {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}.
   * <ul>
   *   <li>Then calls {@link StateStoreProvider#getStateStore(TableProperties)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test commit(CompactionJob, CompactionJobFinishedEvent); then calls getStateStore(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobCommitterOrSendToLambda.commit(CompactionJob, CompactionJobFinishedEvent)"})
  void testCommit_thenCallsGetStateStore() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(false);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    InMemoryCompactionJobTracker tracker = mock(InMemoryCompactionJobTracker.class);
    doNothing().when(tracker).jobCommitted(Mockito.<CompactionJobCommittedEvent>any());
    doNothing().when(tracker).jobFinished(Mockito.<CompactionJobFinishedEvent>any());
    CompactionJobCommitterOrSendToLambda compactionJobCommitterOrSendToLambda = new CompactionJobCommitterOrSendToLambda(
        tablePropertiesProvider, stateStoreProvider, tracker, mock(CommitQueueSender.class),
        mock(BatchedCommitQueueSender.class));
    Builder builderResult = CompactionJob.builder();
    CompactionJob job = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    CompactionJobFinishedEvent finishedEvent = mock(CompactionJobFinishedEvent.class);
    when(finishedEvent.getJobRunId()).thenReturn("42");
    when(finishedEvent.getTaskId()).thenReturn("42");
    when(finishedEvent.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act
    compactionJobCommitterOrSendToLambda.commit(job, finishedEvent);

    // Assert
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(tracker).jobCommitted(isA(CompactionJobCommittedEvent.class));
    verify(tracker).jobFinished(isA(CompactionJobFinishedEvent.class));
    verify(finishedEvent).getJobRunId();
    verify(finishedEvent).getRecordsProcessed();
    verify(finishedEvent).getTaskId();
  }

  /**
   * Test {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}.
   * <ul>
   *   <li>Then calls {@link StateStoreProvider#getStateStore(TableProperties)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test commit(CompactionJob, CompactionJobFinishedEvent); then calls getStateStore(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobCommitterOrSendToLambda.commit(CompactionJob, CompactionJobFinishedEvent)"})
  void testCommit_thenCallsGetStateStore2() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(false);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    InMemoryCompactionJobTracker tracker = mock(InMemoryCompactionJobTracker.class);
    doNothing().when(tracker).jobCommitted(Mockito.<CompactionJobCommittedEvent>any());
    doNothing().when(tracker).jobFinished(Mockito.<CompactionJobFinishedEvent>any());
    CompactionJobCommitterOrSendToLambda compactionJobCommitterOrSendToLambda = new CompactionJobCommitterOrSendToLambda(
        tablePropertiesProvider, stateStoreProvider, tracker, mock(CommitQueueSender.class),
        mock(BatchedCommitQueueSender.class));

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("Committing compaction job {} inside compaction task");
    CompactionJob job = CompactionJob.builder()
        .inputFiles(inputFiles)
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    CompactionJobFinishedEvent finishedEvent = mock(CompactionJobFinishedEvent.class);
    when(finishedEvent.getJobRunId()).thenReturn("42");
    when(finishedEvent.getTaskId()).thenReturn("42");
    when(finishedEvent.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act
    compactionJobCommitterOrSendToLambda.commit(job, finishedEvent);

    // Assert
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(tracker).jobCommitted(isA(CompactionJobCommittedEvent.class));
    verify(tracker).jobFinished(isA(CompactionJobFinishedEvent.class));
    verify(finishedEvent).getJobRunId();
    verify(finishedEvent).getRecordsProcessed();
    verify(finishedEvent).getTaskId();
  }

  /**
   * Test {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}.
   * <ul>
   *   <li>Then calls {@link BatchedCommitQueueSender#send(CompactionCommitMessage)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommitterOrSendToLambda#commit(CompactionJob, CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test commit(CompactionJob, CompactionJobFinishedEvent); then calls send(CompactionCommitMessage)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobCommitterOrSendToLambda.commit(CompactionJob, CompactionJobFinishedEvent)"})
  void testCommit_thenCallsSend() {
    // Arrange
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    InMemoryCompactionJobTracker tracker = mock(InMemoryCompactionJobTracker.class);
    doNothing().when(tracker).jobFinished(Mockito.<CompactionJobFinishedEvent>any());
    BatchedCommitQueueSender batchedCommitQueueSender = mock(BatchedCommitQueueSender.class);
    doNothing().when(batchedCommitQueueSender).send(Mockito.<CompactionCommitMessage>any());
    CompactionJobCommitterOrSendToLambda compactionJobCommitterOrSendToLambda = new CompactionJobCommitterOrSendToLambda(
        tablePropertiesProvider, new StateStoreProvider(3, mock(Factory.class)), tracker, mock(CommitQueueSender.class),
        batchedCommitQueueSender);
    Builder builderResult = CompactionJob.builder();
    CompactionJob job = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    CompactionJobFinishedEvent finishedEvent = mock(CompactionJobFinishedEvent.class);
    when(finishedEvent.getJobRunId()).thenReturn("42");
    when(finishedEvent.getTaskId()).thenReturn("42");
    when(finishedEvent.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act
    compactionJobCommitterOrSendToLambda.commit(job, finishedEvent);

    // Assert
    verify(batchedCommitQueueSender).send(isA(CompactionCommitMessage.class));
    verify(tablePropertiesProvider).getById(eq("42"));
    verify(tracker).jobFinished(isA(CompactionJobFinishedEvent.class));
    verify(finishedEvent).getJobRunId();
    verify(finishedEvent).getRecordsProcessed();
    verify(finishedEvent).getTaskId();
  }
}
