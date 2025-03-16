package sleeper.core.statestore.transactionlog.transaction.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;
import sleeper.core.statestore.FileReferenceStore;
import sleeper.core.statestore.SplitFileReferenceRequest;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.exception.FileNotFoundException;
import sleeper.core.statestore.exception.FileReferenceAlreadyExistsException;
import sleeper.core.statestore.exception.FileReferenceAssignedToJobException;
import sleeper.core.statestore.exception.FileReferenceNotFoundException;
import sleeper.core.statestore.exception.SplitRequestsFailedException;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.statestore.transactionlog.state.StateStoreFile;
import sleeper.core.statestore.transactionlog.state.StateStoreFiles;

class SplitFileReferencesTransactionDiffblueTest {
  /**
   * Test {@link SplitFileReferencesTransaction#SplitFileReferencesTransaction(List)}.
   * <ul>
   *   <li>Then return not Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#SplitFileReferencesTransaction(List)}
   */
  @Test
  @DisplayName("Test new SplitFileReferencesTransaction(List); then return not Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferencesTransaction.<init>(List)"})
  void testNewSplitFileReferencesTransaction_thenReturnNotEmpty() {
    // Arrange
    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference oldReference = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileReferenceRequest = new SplitFileReferenceRequest(oldReference, newReferences);

    ArrayList<SplitFileReferenceRequest> requests = new ArrayList<>();
    requests.add(splitFileReferenceRequest);

    // Act and Assert
    assertFalse((new SplitFileReferencesTransaction(requests)).isEmpty());
  }

  /**
   * Test {@link SplitFileReferencesTransaction#SplitFileReferencesTransaction(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#SplitFileReferencesTransaction(List)}
   */
  @Test
  @DisplayName("Test new SplitFileReferencesTransaction(List); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferencesTransaction.<init>(List)"})
  void testNewSplitFileReferencesTransaction_whenArrayList_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new SplitFileReferencesTransaction(new ArrayList<>())).isEmpty());
  }

  /**
   * Test {@link SplitFileReferencesTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>Then throw {@link SplitRequestsFailedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); then throw SplitRequestsFailedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferencesTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_thenThrowSplitRequestsFailedException() {
    // Arrange
    SplitFileReferencesTransaction splitFileReferencesTransaction = new SplitFileReferencesTransaction(
        new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doThrow(new StateStoreException("An error occurred")).when(stateStore)
        .addFilesTransaction(Mockito.<AddTransactionRequest>any());

    // Act and Assert
    assertThrows(SplitRequestsFailedException.class,
        () -> splitFileReferencesTransaction.synchronousCommit(stateStore));
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link SplitFileReferencesTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link FileReferenceStore#addFilesTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); when StateStore addFilesTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferencesTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_whenStateStoreAddFilesTransactionDoesNothing() {
    // Arrange
    SplitFileReferencesTransaction splitFileReferencesTransaction = new SplitFileReferencesTransaction(
        new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    splitFileReferencesTransaction.synchronousCommit(stateStore);

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link SplitFileReferencesTransaction#validate(StateStoreFiles)} with {@code StateStoreFiles}.
   * <ul>
   *   <li>Then throw {@link FileNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#validate(StateStoreFiles)}
   */
  @Test
  @DisplayName("Test validate(StateStoreFiles) with 'StateStoreFiles'; then throw FileNotFoundException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferencesTransaction.validate(StateStoreFiles)"})
  void testValidateWithStateStoreFiles_thenThrowFileNotFoundException() throws StateStoreException {
    // Arrange
    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference oldReference = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileReferenceRequest = new SplitFileReferenceRequest(oldReference, newReferences);

    ArrayList<SplitFileReferenceRequest> requests = new ArrayList<>();
    requests.add(splitFileReferenceRequest);
    SplitFileReferencesTransaction splitFileReferencesTransaction = new SplitFileReferencesTransaction(requests);

    // Act and Assert
    assertThrows(FileNotFoundException.class, () -> splitFileReferencesTransaction.validate(new StateStoreFiles()));
  }

  /**
   * Test {@link SplitFileReferencesTransaction#validate(StateStoreFiles)} with {@code StateStoreFiles}.
   * <ul>
   *   <li>Then throw {@link FileReferenceAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#validate(StateStoreFiles)}
   */
  @Test
  @DisplayName("Test validate(StateStoreFiles) with 'StateStoreFiles'; then throw FileReferenceAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferencesTransaction.validate(StateStoreFiles)"})
  void testValidateWithStateStoreFiles_thenThrowFileReferenceAlreadyExistsException() throws StateStoreException {
    // Arrange
    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference oldReference = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileReferenceRequest = new SplitFileReferenceRequest(oldReference, newReferences);

    ArrayList<SplitFileReferenceRequest> requests = new ArrayList<>();
    requests.add(splitFileReferenceRequest);
    SplitFileReferencesTransaction splitFileReferencesTransaction = new SplitFileReferencesTransaction(requests);

    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult3 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId(null);
    FileReference buildResult2 = jobIdResult3
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);
    StateStoreFile file = new StateStoreFile("foo.txt",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), references);

    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    stateStoreFiles.add(file);

    // Act and Assert
    assertThrows(FileReferenceAlreadyExistsException.class,
        () -> splitFileReferencesTransaction.validate(stateStoreFiles));
  }

  /**
   * Test {@link SplitFileReferencesTransaction#validate(StateStoreFiles)} with {@code StateStoreFiles}.
   * <ul>
   *   <li>Then throw {@link FileReferenceAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#validate(StateStoreFiles)}
   */
  @Test
  @DisplayName("Test validate(StateStoreFiles) with 'StateStoreFiles'; then throw FileReferenceAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferencesTransaction.validate(StateStoreFiles)"})
  void testValidateWithStateStoreFiles_thenThrowFileReferenceAlreadyExistsException2() throws StateStoreException {
    // Arrange
    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("Partition Id")
        .build();
    newReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult2);
    Builder jobIdResult3 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference oldReference = jobIdResult3
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileReferenceRequest = new SplitFileReferenceRequest(oldReference, newReferences);

    ArrayList<SplitFileReferenceRequest> requests = new ArrayList<>();
    requests.add(splitFileReferenceRequest);
    SplitFileReferencesTransaction splitFileReferencesTransaction = new SplitFileReferencesTransaction(requests);

    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult4 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId(null);
    FileReference buildResult3 = jobIdResult4
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult3);
    StateStoreFile file = new StateStoreFile("foo.txt",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), references);

    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    stateStoreFiles.add(file);

    // Act and Assert
    assertThrows(FileReferenceAlreadyExistsException.class,
        () -> splitFileReferencesTransaction.validate(stateStoreFiles));
  }

  /**
   * Test {@link SplitFileReferencesTransaction#validate(StateStoreFiles)} with {@code StateStoreFiles}.
   * <ul>
   *   <li>Then throw {@link FileReferenceAssignedToJobException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#validate(StateStoreFiles)}
   */
  @Test
  @DisplayName("Test validate(StateStoreFiles) with 'StateStoreFiles'; then throw FileReferenceAssignedToJobException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferencesTransaction.validate(StateStoreFiles)"})
  void testValidateWithStateStoreFiles_thenThrowFileReferenceAssignedToJobException() throws StateStoreException {
    // Arrange
    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference oldReference = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileReferenceRequest = new SplitFileReferenceRequest(oldReference, newReferences);

    ArrayList<SplitFileReferenceRequest> requests = new ArrayList<>();
    requests.add(splitFileReferenceRequest);
    SplitFileReferencesTransaction splitFileReferencesTransaction = new SplitFileReferencesTransaction(requests);

    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult3 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult3
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);
    StateStoreFile file = new StateStoreFile("foo.txt",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), references);

    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    stateStoreFiles.add(file);

    // Act and Assert
    assertThrows(FileReferenceAssignedToJobException.class,
        () -> splitFileReferencesTransaction.validate(stateStoreFiles));
  }

  /**
   * Test {@link SplitFileReferencesTransaction#validate(StateStoreFiles)} with {@code StateStoreFiles}.
   * <ul>
   *   <li>Then throw {@link FileReferenceNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#validate(StateStoreFiles)}
   */
  @Test
  @DisplayName("Test validate(StateStoreFiles) with 'StateStoreFiles'; then throw FileReferenceNotFoundException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferencesTransaction.validate(StateStoreFiles)"})
  void testValidateWithStateStoreFiles_thenThrowFileReferenceNotFoundException() throws StateStoreException {
    // Arrange
    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference oldReference = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileReferenceRequest = new SplitFileReferenceRequest(oldReference, newReferences);

    ArrayList<SplitFileReferenceRequest> requests = new ArrayList<>();
    requests.add(splitFileReferenceRequest);
    SplitFileReferencesTransaction splitFileReferencesTransaction = new SplitFileReferencesTransaction(requests);

    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Instant lastStateStoreUpdateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    stateStoreFiles.add(new StateStoreFile("foo.txt", lastStateStoreUpdateTime, new ArrayList<>()));

    // Act and Assert
    assertThrows(FileReferenceNotFoundException.class, () -> splitFileReferencesTransaction.validate(stateStoreFiles));
  }

  /**
   * Test {@link SplitFileReferencesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <ul>
   *   <li>Then calls {@link StateStoreFiles#updateFile(String, Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'; then calls updateFile(String, Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferencesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant_thenCallsUpdateFile() {
    // Arrange
    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference oldReference = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileReferenceRequest = new SplitFileReferenceRequest(oldReference, newReferences);

    ArrayList<SplitFileReferenceRequest> requests = new ArrayList<>();
    requests.add(splitFileReferenceRequest);
    SplitFileReferencesTransaction splitFileReferencesTransaction = new SplitFileReferencesTransaction(requests);
    StateStoreFiles stateStoreFiles = mock(StateStoreFiles.class);
    doNothing().when(stateStoreFiles).updateFile(Mockito.<String>any(), Mockito.<Consumer<StateStoreFile>>any());

    // Act
    splitFileReferencesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    verify(stateStoreFiles).updateFile(eq("foo.txt"), isA(Consumer.class));
  }

  /**
   * Test {@link SplitFileReferencesTransaction#isEmpty()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferencesTransaction.isEmpty()"})
  void testIsEmpty_thenReturnFalse() {
    // Arrange
    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference oldReference = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileReferenceRequest = new SplitFileReferenceRequest(oldReference, newReferences);

    ArrayList<SplitFileReferenceRequest> requests = new ArrayList<>();
    requests.add(splitFileReferenceRequest);

    // Act and Assert
    assertFalse((new SplitFileReferencesTransaction(requests)).isEmpty());
  }

  /**
   * Test {@link SplitFileReferencesTransaction#isEmpty()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferencesTransaction.isEmpty()"})
  void testIsEmpty_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new SplitFileReferencesTransaction(new ArrayList<>())).isEmpty());
  }

  /**
   * Test {@link SplitFileReferencesTransaction#equals(Object)}, and {@link SplitFileReferencesTransaction#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitFileReferencesTransaction#equals(Object)}
   *   <li>{@link SplitFileReferencesTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferencesTransaction.equals(Object)",
      "int SplitFileReferencesTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    SplitFileReferencesTransaction splitFileReferencesTransaction = new SplitFileReferencesTransaction(
        new ArrayList<>());
    SplitFileReferencesTransaction splitFileReferencesTransaction2 = new SplitFileReferencesTransaction(
        new ArrayList<>());

    // Act and Assert
    assertEquals(splitFileReferencesTransaction, splitFileReferencesTransaction2);
    int expectedHashCodeResult = splitFileReferencesTransaction.hashCode();
    assertEquals(expectedHashCodeResult, splitFileReferencesTransaction2.hashCode());
  }

  /**
   * Test {@link SplitFileReferencesTransaction#equals(Object)}, and {@link SplitFileReferencesTransaction#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitFileReferencesTransaction#equals(Object)}
   *   <li>{@link SplitFileReferencesTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferencesTransaction.equals(Object)",
      "int SplitFileReferencesTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    SplitFileReferencesTransaction splitFileReferencesTransaction = new SplitFileReferencesTransaction(
        new ArrayList<>());

    // Act and Assert
    assertEquals(splitFileReferencesTransaction, splitFileReferencesTransaction);
    int expectedHashCodeResult = splitFileReferencesTransaction.hashCode();
    assertEquals(expectedHashCodeResult, splitFileReferencesTransaction.hashCode());
  }

  /**
   * Test {@link SplitFileReferencesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferencesTransaction.equals(Object)",
      "int SplitFileReferencesTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new SplitFileReferencesTransaction(new ArrayList<>()), 1);
  }

  /**
   * Test {@link SplitFileReferencesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferencesTransaction.equals(Object)",
      "int SplitFileReferencesTransaction.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new SplitFileReferencesTransaction(new ArrayList<>()), null);
  }

  /**
   * Test {@link SplitFileReferencesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferencesTransaction.equals(Object)",
      "int SplitFileReferencesTransaction.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new SplitFileReferencesTransaction(new ArrayList<>()),
        "Different type to SplitFileReferencesTransaction");
  }

  /**
   * Test {@link SplitFileReferencesTransaction#toString()}.
   * <p>
   * Method under test: {@link SplitFileReferencesTransaction#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SplitFileReferencesTransaction.toString()"})
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("SplitFileReferencesTransaction{requests=[]}",
        (new SplitFileReferencesTransaction(new ArrayList<>())).toString());
  }
}
