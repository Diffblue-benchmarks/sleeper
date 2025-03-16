package sleeper.compaction.core.job.commit;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.ReplaceFileReferencesRequest;
import sleeper.core.statestore.ReplaceFileReferencesRequest.Builder;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.commit.StateStoreCommitRequestSender;

class CompactionCommitBatcherDiffblueTest {
  /**
   * Test {@link CompactionCommitBatcher#sendBatch(List)}.
   * <p>
   * Method under test: {@link CompactionCommitBatcher#sendBatch(List)}
   */
  @Test
  @DisplayName("Test sendBatch(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionCommitBatcher.sendBatch(List)"})
  void testSendBatch() {
    // Arrange
    StateStoreCommitRequestSender sendStateStoreCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendStateStoreCommit).send(Mockito.<StateStoreCommitRequest>any());
    CompactionCommitBatcher compactionCommitBatcher = new CompactionCommitBatcher(sendStateStoreCommit);

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles
        .add("Submitted asynchronous request to state store committer with {} compaction commits for table ID {}");
    Builder jobRunIdResult = ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest request = jobRunIdResult.newReference(newReference).taskId("42").build();
    CompactionCommitMessageHandle compactionCommitMessageHandle = new CompactionCommitMessageHandle("42", request,
        mock(Runnable.class));

    ArrayList<CompactionCommitMessageHandle> requests = new ArrayList<>();
    requests.add(compactionCommitMessageHandle);

    // Act
    compactionCommitBatcher.sendBatch(requests);

    // Assert
    verify(sendStateStoreCommit).send(isA(StateStoreCommitRequest.class));
  }

  /**
   * Test {@link CompactionCommitBatcher#sendBatch(List)}.
   * <p>
   * Method under test: {@link CompactionCommitBatcher#sendBatch(List)}
   */
  @Test
  @DisplayName("Test sendBatch(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionCommitBatcher.sendBatch(List)"})
  void testSendBatch2() {
    // Arrange
    StateStoreCommitRequestSender sendStateStoreCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendStateStoreCommit).send(Mockito.<StateStoreCommitRequest>any());
    CompactionCommitBatcher compactionCommitBatcher = new CompactionCommitBatcher(sendStateStoreCommit);

    ArrayList<CompactionCommitMessageHandle> requests = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest request = jobRunIdResult.newReference(newReference).taskId("42").build();
    requests.add(new CompactionCommitMessageHandle("42", request, mock(Runnable.class)));
    Builder builderResult2 = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult2 = builderResult2.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest request2 = jobRunIdResult2.newReference(newReference2).taskId("42").build();
    requests.add(new CompactionCommitMessageHandle(
        "Submitted asynchronous request to state store committer with {} compaction commits for table ID {}", request2,
        mock(Runnable.class)));

    // Act
    compactionCommitBatcher.sendBatch(requests);

    // Assert
    verify(sendStateStoreCommit, atLeast(1)).send(Mockito.<StateStoreCommitRequest>any());
  }

  /**
   * Test {@link CompactionCommitBatcher#sendBatch(List)}.
   * <ul>
   *   <li>Given {@link StateStoreCommitRequestSender} {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)} does nothing.</li>
   *   <li>Then calls {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionCommitBatcher#sendBatch(List)}
   */
  @Test
  @DisplayName("Test sendBatch(List); given StateStoreCommitRequestSender send(StateStoreCommitRequest) does nothing; then calls send(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionCommitBatcher.sendBatch(List)"})
  void testSendBatch_givenStateStoreCommitRequestSenderSendDoesNothing_thenCallsSend() {
    // Arrange
    StateStoreCommitRequestSender sendStateStoreCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendStateStoreCommit).send(Mockito.<StateStoreCommitRequest>any());
    CompactionCommitBatcher compactionCommitBatcher = new CompactionCommitBatcher(sendStateStoreCommit);

    ArrayList<CompactionCommitMessageHandle> requests = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest request = jobRunIdResult.newReference(newReference).taskId("42").build();
    requests.add(new CompactionCommitMessageHandle("42", request, mock(Runnable.class)));

    // Act
    compactionCommitBatcher.sendBatch(requests);

    // Assert
    verify(sendStateStoreCommit).send(isA(StateStoreCommitRequest.class));
  }

  /**
   * Test {@link CompactionCommitBatcher#sendBatch(List)}.
   * <ul>
   *   <li>Given {@link StateStoreCommitRequestSender} {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)} does nothing.</li>
   *   <li>Then calls {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionCommitBatcher#sendBatch(List)}
   */
  @Test
  @DisplayName("Test sendBatch(List); given StateStoreCommitRequestSender send(StateStoreCommitRequest) does nothing; then calls send(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionCommitBatcher.sendBatch(List)"})
  void testSendBatch_givenStateStoreCommitRequestSenderSendDoesNothing_thenCallsSend2() {
    // Arrange
    StateStoreCommitRequestSender sendStateStoreCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendStateStoreCommit).send(Mockito.<StateStoreCommitRequest>any());
    CompactionCommitBatcher compactionCommitBatcher = new CompactionCommitBatcher(sendStateStoreCommit);

    ArrayList<CompactionCommitMessageHandle> requests = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest request = jobRunIdResult.newReference(newReference).taskId("42").build();
    requests.add(new CompactionCommitMessageHandle("42", request, mock(Runnable.class)));
    Builder builderResult2 = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult2 = builderResult2.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest request2 = jobRunIdResult2.newReference(newReference2).taskId("42").build();
    requests.add(new CompactionCommitMessageHandle("42", request2, mock(Runnable.class)));

    // Act
    compactionCommitBatcher.sendBatch(requests);

    // Assert
    verify(sendStateStoreCommit).send(isA(StateStoreCommitRequest.class));
  }

  /**
   * Test {@link CompactionCommitBatcher#sendBatch(List)}.
   * <ul>
   *   <li>Then calls {@link Runnable#run()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionCommitBatcher#sendBatch(List)}
   */
  @Test
  @DisplayName("Test sendBatch(List); then calls run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionCommitBatcher.sendBatch(List)"})
  void testSendBatch_thenCallsRun() {
    // Arrange
    CompactionCommitBatcher compactionCommitBatcher = new CompactionCommitBatcher(
        mock(StateStoreCommitRequestSender.class));

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles
        .add("Submitted asynchronous request to state store committer with {} compaction commits for table ID {}");
    Builder jobRunIdResult = ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder()
        .countApproximate(true)
        .filename("Submitted asynchronous request to state store committer with {} compaction commits for table ID {}")
        .jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest request = jobRunIdResult.newReference(newReference).taskId("42").build();
    Runnable callbackOnFail = mock(Runnable.class);
    doNothing().when(callbackOnFail).run();
    CompactionCommitMessageHandle compactionCommitMessageHandle = new CompactionCommitMessageHandle("42", request,
        callbackOnFail);

    ArrayList<CompactionCommitMessageHandle> requests = new ArrayList<>();
    requests.add(compactionCommitMessageHandle);

    // Act
    compactionCommitBatcher.sendBatch(requests);

    // Assert
    verify(callbackOnFail).run();
  }

  /**
   * Test {@link CompactionCommitBatcher#sendBatch(List)}.
   * <ul>
   *   <li>Then calls {@link Runnable#run()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionCommitBatcher#sendBatch(List)}
   */
  @Test
  @DisplayName("Test sendBatch(List); then calls run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionCommitBatcher.sendBatch(List)"})
  void testSendBatch_thenCallsRun2() {
    // Arrange
    CompactionCommitBatcher compactionCommitBatcher = new CompactionCommitBatcher(
        mock(StateStoreCommitRequestSender.class));

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles
        .add("Submitted asynchronous request to state store committer with {} compaction commits for table ID {}");
    Builder jobRunIdResult = ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder()
        .countApproximate(true)
        .filename("Submitted asynchronous request to state store committer with {} compaction commits for table ID {}")
        .jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest request = jobRunIdResult.newReference(newReference).taskId("42").build();
    Runnable callbackOnFail = mock(Runnable.class);
    doNothing().when(callbackOnFail).run();
    CompactionCommitMessageHandle compactionCommitMessageHandle = new CompactionCommitMessageHandle("42", request,
        callbackOnFail);

    Runnable callbackOnFail2 = mock(Runnable.class);
    doNothing().when(callbackOnFail2).run();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult2 = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest request2 = jobRunIdResult2.newReference(newReference2).taskId("42").build();
    CompactionCommitMessageHandle compactionCommitMessageHandle2 = new CompactionCommitMessageHandle("42", request2,
        callbackOnFail2);

    ArrayList<CompactionCommitMessageHandle> requests = new ArrayList<>();
    requests.add(compactionCommitMessageHandle2);
    requests.add(compactionCommitMessageHandle);

    // Act
    compactionCommitBatcher.sendBatch(requests);

    // Assert
    verify(callbackOnFail2).run();
    verify(callbackOnFail).run();
  }
}
