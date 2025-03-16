package sleeper.core.statestore.transactionlog.transaction.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.ReplaceFileReferencesRequest;
import sleeper.core.statestore.ReplaceFileReferencesRequest.Builder;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.exception.ReplaceRequestsFailedException;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.statestore.transactionlog.state.StateStoreFile;
import sleeper.core.statestore.transactionlog.state.StateStoreFiles;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatusType;

class ReplaceFileReferencesTransactionDiffblueTest {
  /**
   * Test {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)}.
   * <ul>
   *   <li>Then return Jobs first InputFiles size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)}
   */
  @Test
  @DisplayName("Test new ReplaceFileReferencesTransaction(List); then return Jobs first InputFiles size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.<init>(List)"})
  void testNewReplaceFileReferencesTransaction_thenReturnJobsFirstInputFilesSizeIsOne() throws StateStoreException {
    // Arrange
    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("filename must not be null");
    Builder jobRunIdResult = ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();

    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    jobs.add(buildResult);

    // Act and Assert
    List<ReplaceFileReferencesRequest> jobs2 = (new ReplaceFileReferencesTransaction(jobs)).getJobs();
    assertEquals(1, jobs2.size());
    List<String> inputFiles2 = jobs2.get(0).getInputFiles();
    assertEquals(1, inputFiles2.size());
    assertEquals("filename must not be null", inputFiles2.get(0));
    assertSame(inputFiles, inputFiles2);
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)}.
   * <ul>
   *   <li>Then return Jobs first NewReference JobId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)}
   */
  @Test
  @DisplayName("Test new ReplaceFileReferencesTransaction(List); then return Jobs first NewReference JobId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.<init>(List)"})
  void testNewReplaceFileReferencesTransaction_thenReturnJobsFirstNewReferenceJobIdIs42() throws StateStoreException {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);

    // Act
    ReplaceFileReferencesTransaction actualReplaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(
        jobs);

    // Assert
    List<ReplaceFileReferencesRequest> jobs2 = actualReplaceFileReferencesTransaction.getJobs();
    assertEquals(1, jobs2.size());
    ReplaceFileReferencesRequest getResult = jobs2.get(0);
    FileReference newReference2 = getResult.getNewReference();
    assertEquals("42", newReference2.getJobId());
    assertEquals("42", newReference2.getPartitionId());
    assertEquals("42", getResult.getJobId());
    assertEquals("42", getResult.getJobRunId());
    assertEquals("42", getResult.getPartitionId());
    assertEquals("42", getResult.getTaskId());
    assertEquals("foo.txt", newReference2.getFilename());
    assertNull(newReference2.getLastStateStoreUpdateTime());
    assertEquals(1L, newReference2.getNumberOfRecords().longValue());
    assertFalse(actualReplaceFileReferencesTransaction.isEmpty());
    assertTrue(getResult.getInputFiles().isEmpty());
    assertTrue(newReference2.isCountApproximate());
    assertTrue(newReference2.onlyContainsDataForThisPartition());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)}.
   * <ul>
   *   <li>Then return Jobs size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)}
   */
  @Test
  @DisplayName("Test new ReplaceFileReferencesTransaction(List); then return Jobs size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.<init>(List)"})
  void testNewReplaceFileReferencesTransaction_thenReturnJobsSizeIsTwo() throws StateStoreException {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);
    Builder builderResult2 = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult2 = builderResult2.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult2 = jobRunIdResult2.newReference(newReference2).taskId("42").build();
    jobs.add(buildResult2);

    // Act and Assert
    List<ReplaceFileReferencesRequest> jobs2 = (new ReplaceFileReferencesTransaction(jobs)).getJobs();
    assertEquals(2, jobs2.size());
    assertEquals(jobs2.get(0), jobs2.get(1));
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)}.
   * <ul>
   *   <li>Then throw {@link ReplaceRequestsFailedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)}
   */
  @Test
  @DisplayName("Test new ReplaceFileReferencesTransaction(List); then throw ReplaceRequestsFailedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.<init>(List)"})
  void testNewReplaceFileReferencesTransaction_thenThrowReplaceRequestsFailedException() throws StateStoreException {
    // Arrange
    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("foo.txt");
    Builder jobRunIdResult = ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();

    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    jobs.add(buildResult);

    // Act and Assert
    assertThrows(ReplaceRequestsFailedException.class, () -> new ReplaceFileReferencesTransaction(jobs));
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Jobs Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)}
   */
  @Test
  @DisplayName("Test new ReplaceFileReferencesTransaction(List); when ArrayList(); then return Jobs Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.<init>(List)"})
  void testNewReplaceFileReferencesTransaction_whenArrayList_thenReturnJobsEmpty() throws StateStoreException {
    // Arrange and Act
    ReplaceFileReferencesTransaction actualReplaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(
        new ArrayList<>());

    // Assert
    assertTrue(actualReplaceFileReferencesTransaction.getJobs().isEmpty());
    assertTrue(actualReplaceFileReferencesTransaction.isEmpty());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>Then throw {@link ReplaceRequestsFailedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); then throw ReplaceRequestsFailedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_thenThrowReplaceRequestsFailedException() throws StateStoreException {
    // Arrange
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(
        new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doThrow(new StateStoreException("An error occurred")).when(stateStore)
        .addTransaction(Mockito.<AddTransactionRequest>any());

    // Act and Assert
    assertThrows(ReplaceRequestsFailedException.class,
        () -> replaceFileReferencesTransaction.synchronousCommit(stateStore));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link StateStore#addTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); when StateStore addTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_whenStateStoreAddTransactionDoesNothing() throws StateStoreException {
    // Arrange
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(
        new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    replaceFileReferencesTransaction.synchronousCommit(stateStore);

    // Assert
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code filename must not be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'; given ArrayList() add 'filename must not be null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant_givenArrayListAddFilenameMustNotBeNull() throws StateStoreException {
    // Arrange
    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("filename must not be null");
    Builder jobRunIdResult = ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();

    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    jobs.add(buildResult);
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(jobs);
    StateStoreFiles stateStoreFiles = new StateStoreFiles();

    // Act
    replaceFileReferencesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert that nothing has changed
    assertTrue(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <ul>
   *   <li>Then not {@link StateStoreFiles} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'; then not StateStoreFiles (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant_thenNotStateStoreFilesEmpty() throws StateStoreException {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(jobs);
    StateStoreFiles stateStoreFiles = new StateStoreFiles();

    // Act
    replaceFileReferencesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertFalse(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <ul>
   *   <li>Then not {@link StateStoreFiles} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'; then not StateStoreFiles (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant_thenNotStateStoreFilesEmpty2() throws StateStoreException {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);
    Builder builderResult2 = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult2 = builderResult2.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult2 = jobRunIdResult2.newReference(newReference2).taskId("42").build();
    jobs.add(buildResult2);
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(jobs);
    StateStoreFiles stateStoreFiles = new StateStoreFiles();

    // Act
    replaceFileReferencesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertFalse(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <ul>
   *   <li>When {@link StateStoreFiles} (default constructor).</li>
   *   <li>Then {@link StateStoreFiles} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'; when StateStoreFiles (default constructor); then StateStoreFiles (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant_whenStateStoreFiles_thenStateStoreFilesEmpty() throws StateStoreException {
    // Arrange
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(
        new ArrayList<>());
    StateStoreFiles stateStoreFiles = new StateStoreFiles();

    // Act
    replaceFileReferencesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert that nothing has changed
    assertTrue(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#isEmpty()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesTransaction.isEmpty()"})
  void testIsEmpty_thenReturnFalse() throws StateStoreException {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);

    // Act and Assert
    assertFalse((new ReplaceFileReferencesTransaction(jobs)).isEmpty());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#isEmpty()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesTransaction.isEmpty()"})
  void testIsEmpty_thenReturnTrue() throws StateStoreException {
    // Arrange, Act and Assert
    assertTrue((new ReplaceFileReferencesTransaction(new ArrayList<>())).isEmpty());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#reportJobCommits(CompactionJobTracker, TableStatus, StateStoreFiles, Instant)}.
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#reportJobCommits(CompactionJobTracker, TableStatus, StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test reportJobCommits(CompactionJobTracker, TableStatus, StateStoreFiles, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void ReplaceFileReferencesTransaction.reportJobCommits(CompactionJobTracker, TableStatus, StateStoreFiles, Instant)"})
  void testReportJobCommits() throws StateStoreException {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(jobs);
    InMemoryCompactionJobTracker tracker = new InMemoryCompactionJobTracker();
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);
    StateStoreFiles stateBefore = new StateStoreFiles();

    // Act
    replaceFileReferencesTransaction.reportJobCommits(tracker, sleeperTable, stateBefore,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    Stream<CompactionJobStatus> streamAllJobsResult = tracker.streamAllJobs("42");
    List<CompactionJobStatus> collectResult = streamAllJobsResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    List<CompactionJobStatus> allJobs = tracker.getAllJobs("42");
    assertEquals(1, allJobs.size());
    CompactionJobStatus getResult = collectResult.get(0);
    assertEquals(1, getResult.getRunsLatestFirst().size());
    CompactionJobStatus getResult2 = allJobs.get(0);
    assertEquals(1, getResult2.getRunsLatestFirst().size());
    CompactionJobStatus getResult3 = tracker.getJob("42").get();
    assertEquals(1, getResult3.getRunsLatestFirst().size());
    assertEquals(CompactionJobStatusType.FINISHED, getResult.getFurthestStatusType());
    assertEquals(CompactionJobStatusType.FINISHED, getResult2.getFurthestStatusType());
    assertEquals(CompactionJobStatusType.FINISHED, getResult3.getFurthestStatusType());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#reportJobCommits(CompactionJobTracker, TableStatus, StateStoreFiles, Instant)}.
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#reportJobCommits(CompactionJobTracker, TableStatus, StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test reportJobCommits(CompactionJobTracker, TableStatus, StateStoreFiles, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void ReplaceFileReferencesTransaction.reportJobCommits(CompactionJobTracker, TableStatus, StateStoreFiles, Instant)"})
  void testReportJobCommits2() throws StateStoreException {
    // Arrange
    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("jobId must not be null");
    Builder jobRunIdResult = ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();

    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    jobs.add(buildResult);
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(jobs);
    InMemoryCompactionJobTracker tracker = new InMemoryCompactionJobTracker();
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);
    StateStoreFiles stateBefore = new StateStoreFiles();

    // Act
    replaceFileReferencesTransaction.reportJobCommits(tracker, sleeperTable, stateBefore,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    Stream<CompactionJobStatus> streamAllJobsResult = tracker.streamAllJobs("42");
    assertEquals(1, streamAllJobsResult.limit(5).collect(Collectors.toList()).size());
    assertEquals(1, tracker.getAllJobs("42").size());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#reportJobsAllFailed(CompactionJobTracker, TableStatus, Instant, Exception)}.
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#reportJobsAllFailed(CompactionJobTracker, TableStatus, Instant, Exception)}
   */
  @Test
  @DisplayName("Test reportJobsAllFailed(CompactionJobTracker, TableStatus, Instant, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void ReplaceFileReferencesTransaction.reportJobsAllFailed(CompactionJobTracker, TableStatus, Instant, Exception)"})
  void testReportJobsAllFailed() throws StateStoreException {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(jobs);
    InMemoryCompactionJobTracker tracker = new InMemoryCompactionJobTracker();
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Instant now = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    replaceFileReferencesTransaction.reportJobsAllFailed(tracker, sleeperTable, now, new Exception("foo"));

    // Assert
    Stream<CompactionJobStatus> streamAllJobsResult = tracker.streamAllJobs("42");
    List<CompactionJobStatus> collectResult = streamAllJobsResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    List<CompactionJobStatus> allJobs = tracker.getAllJobs("42");
    assertEquals(1, allJobs.size());
    Optional<CompactionJobStatus> job = tracker.getJob("42");
    assertTrue(job.isPresent());
    CompactionJobStatus getResult = allJobs.get(0);
    assertEquals(getResult, collectResult.get(0));
    assertEquals(getResult, job.get());
    assertEquals(allJobs, tracker.getUnfinishedJobs("42"));
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#validateStateChange(StateStoreFiles)}.
   * <ul>
   *   <li>Given {@link StateStoreException#StateStoreException(String)} with message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#validateStateChange(StateStoreFiles)}
   */
  @Test
  @DisplayName("Test validateStateChange(StateStoreFiles); given StateStoreException(String) with message is 'An error occurred'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.validateStateChange(StateStoreFiles)"})
  void testValidateStateChange_givenStateStoreExceptionWithMessageIsAnErrorOccurred() throws StateStoreException {
    // Arrange
    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("foo");
    Builder jobRunIdResult = ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();

    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    jobs.add(buildResult);
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(jobs);
    StateStoreFiles stateStoreFiles = mock(StateStoreFiles.class);
    when(stateStoreFiles.file(Mockito.<String>any())).thenThrow(new StateStoreException("An error occurred"));

    // Act and Assert
    assertThrows(StateStoreException.class,
        () -> replaceFileReferencesTransaction.validateStateChange(stateStoreFiles));
    verify(stateStoreFiles).file(eq("foo"));
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#validateStateChange(StateStoreFiles)}.
   * <ul>
   *   <li>Then calls {@link StateStoreFile#getReferenceForPartitionId(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#validateStateChange(StateStoreFiles)}
   */
  @Test
  @DisplayName("Test validateStateChange(StateStoreFiles); then calls getReferenceForPartitionId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceFileReferencesTransaction.validateStateChange(StateStoreFiles)"})
  void testValidateStateChange_thenCallsGetReferenceForPartitionId() throws StateStoreException {
    // Arrange
    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("");
    Builder jobRunIdResult = ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();

    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    jobs.add(buildResult);
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(jobs);
    StateStoreFile stateStoreFile = mock(StateStoreFile.class);
    when(stateStoreFile.getReferenceForPartitionId(Mockito.<String>any()))
        .thenThrow(new StateStoreException("An error occurred"));
    Optional<StateStoreFile> ofResult = Optional.of(stateStoreFile);
    StateStoreFiles stateStoreFiles = mock(StateStoreFiles.class);
    when(stateStoreFiles.file(Mockito.<String>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(StateStoreException.class,
        () -> replaceFileReferencesTransaction.validateStateChange(stateStoreFiles));
    verify(stateStoreFile).getReferenceForPartitionId(eq("42"));
    verify(stateStoreFiles).file(eq(""));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReplaceFileReferencesTransaction#validate(StateStoreFiles)}
   *   <li>{@link ReplaceFileReferencesTransaction#toString()}
   *   <li>{@link ReplaceFileReferencesTransaction#getJobs()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReplaceFileReferencesTransaction.getJobs()",
      "String ReplaceFileReferencesTransaction.toString()",
      "void ReplaceFileReferencesTransaction.validate(StateStoreFiles)"})
  void testGettersAndSetters() throws StateStoreException {
    // Arrange
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(
        new ArrayList<>());

    // Act
    replaceFileReferencesTransaction.validate(new StateStoreFiles());
    String actualToStringResult = replaceFileReferencesTransaction.toString();

    // Assert
    assertEquals("ReplaceFileReferencesTransaction{jobs=[]}", actualToStringResult);
    assertTrue(replaceFileReferencesTransaction.getJobs().isEmpty());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#equals(Object)}, and {@link ReplaceFileReferencesTransaction#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReplaceFileReferencesTransaction#equals(Object)}
   *   <li>{@link ReplaceFileReferencesTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesTransaction.equals(Object)",
      "int ReplaceFileReferencesTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() throws StateStoreException {
    // Arrange
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(
        new ArrayList<>());
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction2 = new ReplaceFileReferencesTransaction(
        new ArrayList<>());

    // Act and Assert
    assertEquals(replaceFileReferencesTransaction, replaceFileReferencesTransaction2);
    int expectedHashCodeResult = replaceFileReferencesTransaction.hashCode();
    assertEquals(expectedHashCodeResult, replaceFileReferencesTransaction2.hashCode());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#equals(Object)}, and {@link ReplaceFileReferencesTransaction#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReplaceFileReferencesTransaction#equals(Object)}
   *   <li>{@link ReplaceFileReferencesTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesTransaction.equals(Object)",
      "int ReplaceFileReferencesTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() throws StateStoreException {
    // Arrange
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(
        new ArrayList<>());

    // Act and Assert
    assertEquals(replaceFileReferencesTransaction, replaceFileReferencesTransaction);
    int expectedHashCodeResult = replaceFileReferencesTransaction.hashCode();
    assertEquals(expectedHashCodeResult, replaceFileReferencesTransaction.hashCode());
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesTransaction.equals(Object)",
      "int ReplaceFileReferencesTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() throws StateStoreException {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);
    ReplaceFileReferencesTransaction replaceFileReferencesTransaction = new ReplaceFileReferencesTransaction(jobs);

    // Act and Assert
    assertNotEquals(replaceFileReferencesTransaction, new ReplaceFileReferencesTransaction(new ArrayList<>()));
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesTransaction.equals(Object)",
      "int ReplaceFileReferencesTransaction.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() throws StateStoreException {
    // Arrange, Act and Assert
    assertNotEquals(new ReplaceFileReferencesTransaction(new ArrayList<>()), null);
  }

  /**
   * Test {@link ReplaceFileReferencesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesTransaction.equals(Object)",
      "int ReplaceFileReferencesTransaction.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() throws StateStoreException {
    // Arrange, Act and Assert
    assertNotEquals(new ReplaceFileReferencesTransaction(new ArrayList<>()),
        "Different type to ReplaceFileReferencesTransaction");
  }
}
