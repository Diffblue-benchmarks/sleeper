package sleeper.ingest.runner.impl.commit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.commit.StateStoreCommitRequestSender;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.job.IngestJobTracker;
import sleeper.core.tracker.ingest.job.update.IngestJobRunIds;

class AddFilesToStateStoreDiffblueTest {
  /**
   * Test {@link AddFilesToStateStore#synchronousWithJob(TableProperties, StateStore, IngestJobTracker, Supplier, IngestJobRunIds)}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#synchronousWithJob(TableProperties, StateStore, IngestJobTracker, Supplier, IngestJobRunIds)}
   */
  @Test
  @DisplayName("Test synchronousWithJob(TableProperties, StateStore, IngestJobTracker, Supplier, IngestJobRunIds); then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AddFilesToStateStore AddFilesToStateStore.synchronousWithJob(TableProperties, StateStore, IngestJobTracker, Supplier, IngestJobRunIds)"})
  void testSynchronousWithJob_thenThrowStateStoreException() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    InMemoryIngestJobTracker tracker = new InMemoryIngestJobTracker();
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    IngestJobRunIds jobRunIds = mock(IngestJobRunIds.class);
    when(jobRunIds.getJobId()).thenThrow(new StateStoreException("An error occurred"));

    // Act
    AddFilesToStateStore actualSynchronousWithJobResult = AddFilesToStateStore.synchronousWithJob(tableProperties,
        stateStore, tracker, timeSupplier, jobRunIds);

    // Assert
    assertThrows(StateStoreException.class, () -> actualSynchronousWithJobResult.addFiles(new ArrayList<>()));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(jobRunIds, atLeast(1)).getJobId();
  }

  /**
   * Test {@link AddFilesToStateStore#asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)}.
   * <ul>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)}
   */
  @Test
  @DisplayName("Test asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds); then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AddFilesToStateStore AddFilesToStateStore.asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)"})
  void testAsynchronousWithJob_thenCallsGet() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStoreCommitRequestSender commitSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(commitSender).send(Mockito.<StateStoreCommitRequest>any());
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRunIds jobRunIds = mock(IngestJobRunIds.class);
    when(jobRunIds.getJobId()).thenReturn("42");
    when(jobRunIds.getJobRunId()).thenReturn("42");
    when(jobRunIds.getTaskId()).thenReturn("42");

    // Act
    AddFilesToStateStore actualAsynchronousWithJobResult = AddFilesToStateStore.asynchronousWithJob(tableProperties,
        commitSender, timeSupplier, jobRunIds);
    actualAsynchronousWithJobResult.addFiles(new ArrayList<>());

    // Assert
    verify(timeSupplier).get();
    verify(commitSender).send(isA(StateStoreCommitRequest.class));
    verify(jobRunIds).getJobId();
    verify(jobRunIds).getJobRunId();
    verify(jobRunIds).getTaskId();
  }

  /**
   * Test {@link AddFilesToStateStore#asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)}.
   * <ul>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)}
   */
  @Test
  @DisplayName("Test asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds); then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AddFilesToStateStore AddFilesToStateStore.asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)"})
  void testAsynchronousWithJob_thenCallsGet2() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStoreCommitRequestSender commitSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(commitSender).send(Mockito.<StateStoreCommitRequest>any());
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRunIds jobRunIds = mock(IngestJobRunIds.class);
    when(jobRunIds.getJobId()).thenReturn("42");
    when(jobRunIds.getJobRunId()).thenReturn("42");
    when(jobRunIds.getTaskId()).thenReturn("42");

    // Act
    AddFilesToStateStore actualAsynchronousWithJobResult = AddFilesToStateStore.asynchronousWithJob(tableProperties,
        commitSender, timeSupplier, jobRunIds);
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    actualAsynchronousWithJobResult.addFiles(fileReferenceList);

    // Assert
    verify(timeSupplier).get();
    verify(commitSender).send(isA(StateStoreCommitRequest.class));
    verify(jobRunIds).getJobId();
    verify(jobRunIds).getJobRunId();
    verify(jobRunIds).getTaskId();
  }

  /**
   * Test {@link AddFilesToStateStore#asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)}.
   * <ul>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)}
   */
  @Test
  @DisplayName("Test asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds); then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AddFilesToStateStore AddFilesToStateStore.asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)"})
  void testAsynchronousWithJob_thenCallsGet3() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStoreCommitRequestSender commitSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(commitSender).send(Mockito.<StateStoreCommitRequest>any());
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRunIds jobRunIds = mock(IngestJobRunIds.class);
    when(jobRunIds.getJobId()).thenReturn("42");
    when(jobRunIds.getJobRunId()).thenReturn("42");
    when(jobRunIds.getTaskId()).thenReturn("42");

    // Act
    AddFilesToStateStore actualAsynchronousWithJobResult = AddFilesToStateStore.asynchronousWithJob(tableProperties,
        commitSender, timeSupplier, jobRunIds);
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult2);
    actualAsynchronousWithJobResult.addFiles(fileReferenceList);

    // Assert
    verify(timeSupplier).get();
    verify(commitSender).send(isA(StateStoreCommitRequest.class));
    verify(jobRunIds).getJobId();
    verify(jobRunIds).getJobRunId();
    verify(jobRunIds).getTaskId();
  }

  /**
   * Test {@link AddFilesToStateStore#asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)}
   */
  @Test
  @DisplayName("Test asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds); then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AddFilesToStateStore AddFilesToStateStore.asynchronousWithJob(TableProperties, StateStoreCommitRequestSender, Supplier, IngestJobRunIds)"})
  void testAsynchronousWithJob_thenThrowStateStoreException() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStoreCommitRequestSender commitSender = mock(StateStoreCommitRequestSender.class);
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    IngestJobRunIds jobRunIds = mock(IngestJobRunIds.class);
    when(jobRunIds.getJobId()).thenThrow(new StateStoreException("An error occurred"));

    // Act
    AddFilesToStateStore actualAsynchronousWithJobResult = AddFilesToStateStore.asynchronousWithJob(tableProperties,
        commitSender, timeSupplier, jobRunIds);

    // Assert
    assertThrows(StateStoreException.class, () -> actualAsynchronousWithJobResult.addFiles(new ArrayList<>()));
    verify(jobRunIds).getJobId();
  }

  /**
   * Test {@link AddFilesToStateStore#synchronousNoJob(StateStore)}.
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#synchronousNoJob(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousNoJob(StateStore); given RuntimeException(String) with 'foo'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AddFilesToStateStore AddFilesToStateStore.synchronousNoJob(StateStore)"})
  void testSynchronousNoJob_givenRuntimeExceptionWithFoo_thenThrowRuntimeException() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    doThrow(new RuntimeException("foo")).when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    AddFilesToStateStore actualSynchronousNoJobResult = AddFilesToStateStore.synchronousNoJob(stateStore);

    // Assert
    assertThrows(RuntimeException.class, () -> actualSynchronousNoJobResult.addFiles(new ArrayList<>()));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link AddFilesToStateStore#synchronousNoJob(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link StateStore#addTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#synchronousNoJob(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousNoJob(StateStore); when StateStore addTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AddFilesToStateStore AddFilesToStateStore.synchronousNoJob(StateStore)"})
  void testSynchronousNoJob_whenStateStoreAddTransactionDoesNothing() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    AddFilesToStateStore actualSynchronousNoJobResult = AddFilesToStateStore.synchronousNoJob(stateStore);
    actualSynchronousNoJobResult.addFiles(new ArrayList<>());

    // Assert
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link AddFilesToStateStore#synchronousNoJob(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link StateStore#addTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#synchronousNoJob(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousNoJob(StateStore); when StateStore addTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AddFilesToStateStore AddFilesToStateStore.synchronousNoJob(StateStore)"})
  void testSynchronousNoJob_whenStateStoreAddTransactionDoesNothing2() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    AddFilesToStateStore actualSynchronousNoJobResult = AddFilesToStateStore.synchronousNoJob(stateStore);
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    actualSynchronousNoJobResult.addFiles(fileReferenceList);

    // Assert
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link AddFilesToStateStore#synchronousNoJob(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link StateStore#addTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#synchronousNoJob(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousNoJob(StateStore); when StateStore addTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AddFilesToStateStore AddFilesToStateStore.synchronousNoJob(StateStore)"})
  void testSynchronousNoJob_whenStateStoreAddTransactionDoesNothing3() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    AddFilesToStateStore actualSynchronousNoJobResult = AddFilesToStateStore.synchronousNoJob(stateStore);
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult2);
    actualSynchronousNoJobResult.addFiles(fileReferenceList);

    // Assert
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link AddFilesToStateStore#asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)}.
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with a string.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)}
   */
  @Test
  @DisplayName("Test asynchronousNoJob(TableProperties, StateStoreCommitRequestSender); given RuntimeException(String) with a string; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AddFilesToStateStore AddFilesToStateStore.asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)"})
  void testAsynchronousNoJob_givenRuntimeExceptionWithAString_thenThrowRuntimeException() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStoreCommitRequestSender commitSender = mock(StateStoreCommitRequestSender.class);
    doThrow(new RuntimeException(
        "Submitted asynchronous request to state store committer to add {} files with {} references in" + " table {}"))
        .when(commitSender)
        .send(Mockito.<StateStoreCommitRequest>any());

    // Act
    AddFilesToStateStore actualAsynchronousNoJobResult = AddFilesToStateStore.asynchronousNoJob(tableProperties,
        commitSender);

    // Assert
    assertThrows(RuntimeException.class, () -> actualAsynchronousNoJobResult.addFiles(new ArrayList<>()));
    verify(commitSender).send(isA(StateStoreCommitRequest.class));
  }

  /**
   * Test {@link AddFilesToStateStore#asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)}.
   * <ul>
   *   <li>Then calls {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)}
   */
  @Test
  @DisplayName("Test asynchronousNoJob(TableProperties, StateStoreCommitRequestSender); then calls send(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AddFilesToStateStore AddFilesToStateStore.asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)"})
  void testAsynchronousNoJob_thenCallsSend() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStoreCommitRequestSender commitSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(commitSender).send(Mockito.<StateStoreCommitRequest>any());

    // Act
    AddFilesToStateStore actualAsynchronousNoJobResult = AddFilesToStateStore.asynchronousNoJob(tableProperties,
        commitSender);
    actualAsynchronousNoJobResult.addFiles(new ArrayList<>());

    // Assert
    verify(commitSender).send(isA(StateStoreCommitRequest.class));
  }

  /**
   * Test {@link AddFilesToStateStore#asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)}.
   * <ul>
   *   <li>Then calls {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)}
   */
  @Test
  @DisplayName("Test asynchronousNoJob(TableProperties, StateStoreCommitRequestSender); then calls send(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AddFilesToStateStore AddFilesToStateStore.asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)"})
  void testAsynchronousNoJob_thenCallsSend2() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStoreCommitRequestSender commitSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(commitSender).send(Mockito.<StateStoreCommitRequest>any());

    // Act
    AddFilesToStateStore actualAsynchronousNoJobResult = AddFilesToStateStore.asynchronousNoJob(tableProperties,
        commitSender);
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    actualAsynchronousNoJobResult.addFiles(fileReferenceList);

    // Assert
    verify(commitSender).send(isA(StateStoreCommitRequest.class));
  }

  /**
   * Test {@link AddFilesToStateStore#asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)}.
   * <ul>
   *   <li>Then calls {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesToStateStore#asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)}
   */
  @Test
  @DisplayName("Test asynchronousNoJob(TableProperties, StateStoreCommitRequestSender); then calls send(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AddFilesToStateStore AddFilesToStateStore.asynchronousNoJob(TableProperties, StateStoreCommitRequestSender)"})
  void testAsynchronousNoJob_thenCallsSend3() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStoreCommitRequestSender commitSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(commitSender).send(Mockito.<StateStoreCommitRequest>any());

    // Act
    AddFilesToStateStore actualAsynchronousNoJobResult = AddFilesToStateStore.asynchronousNoJob(tableProperties,
        commitSender);
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult2);
    actualAsynchronousNoJobResult.addFiles(fileReferenceList);

    // Assert
    verify(commitSender).send(isA(StateStoreCommitRequest.class));
  }
}
