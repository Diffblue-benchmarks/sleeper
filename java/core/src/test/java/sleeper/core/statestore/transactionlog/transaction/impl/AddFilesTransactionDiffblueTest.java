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
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.AllReferencesToAFile;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.exception.FileAlreadyExistsException;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.statestore.transactionlog.state.StateStoreFile;
import sleeper.core.statestore.transactionlog.state.StateStoreFiles;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction.Builder;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.ingest.job.IngestJobTracker;
import sleeper.core.tracker.ingest.job.update.IngestJobAddedFilesEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobFailedEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobRunIds;

class AddFilesTransactionDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#files(List)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#jobRunId(String)}
   *   <li>{@link Builder#taskId(String)}
   *   <li>{@link Builder#writtenTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AddFilesTransaction Builder.build()", "Builder Builder.files(List)",
      "Builder Builder.jobId(String)", "Builder Builder.jobRunId(String)", "Builder Builder.taskId(String)",
      "Builder Builder.writtenTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");

    // Act
    AddFilesTransaction actualBuildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Assert
    assertTrue(actualBuildResult.getFiles().isEmpty());
    assertTrue(actualBuildResult.isEmpty());
  }

  /**
   * Test Builder {@link Builder#fileReferences(List)}.
   * <ul>
   *   <li>Then builder build Files first References size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#fileReferences(List)}
   */
  @Test
  @DisplayName("Test Builder fileReferences(List); then builder build Files first References size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.fileReferences(List)"})
  void testBuilderFileReferences_thenBuilderBuildFilesFirstReferencesSizeIsTwo() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();

    ArrayList<FileReference> fileReferences = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult2);

    // Act
    Builder actualFileReferencesResult = builderResult.fileReferences(fileReferences);

    // Assert
    List<AllReferencesToAFile> files = builderResult.build().getFiles();
    assertEquals(1, files.size());
    AllReferencesToAFile getResult = files.get(0);
    List<FileReference> references = getResult.getReferences();
    assertEquals(2, references.size());
    FileReference getResult2 = references.get(1);
    assertEquals("42", getResult2.getJobId());
    assertEquals("42", getResult2.getPartitionId());
    assertEquals("foo.txt", getResult2.getFilename());
    assertNull(getResult2.getLastStateStoreUpdateTime());
    List<AllReferencesToAFile> files2 = actualFileReferencesResult.build().getFiles();
    assertEquals(1, files2.size());
    assertEquals(1L, getResult2.getNumberOfRecords().longValue());
    AllReferencesToAFile getResult3 = files2.get(0);
    assertEquals(2, getResult3.getReferences().size());
    assertEquals(2, getResult3.getReferenceCount());
    assertEquals(2, getResult.getReferenceCount());
    assertTrue(getResult2.isCountApproximate());
    assertTrue(getResult2.onlyContainsDataForThisPartition());
  }

  /**
   * Test Builder {@link Builder#fileReferences(List)}.
   * <ul>
   *   <li>Then return build Files first Filename is {@code foo.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#fileReferences(List)}
   */
  @Test
  @DisplayName("Test Builder fileReferences(List); then return build Files first Filename is 'foo.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.fileReferences(List)"})
  void testBuilderFileReferences_thenReturnBuildFilesFirstFilenameIsFooTxt() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();

    ArrayList<FileReference> fileReferences = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult);

    // Act and Assert
    List<AllReferencesToAFile> files = builderResult.fileReferences(fileReferences).build().getFiles();
    assertEquals(1, files.size());
    AllReferencesToAFile getResult = files.get(0);
    assertEquals("foo.txt", getResult.getFilename());
    List<AllReferencesToAFile> files2 = builderResult.build().getFiles();
    assertEquals(1, files2.size());
    AllReferencesToAFile getResult2 = files2.get(0);
    assertEquals("foo.txt", getResult2.getFilename());
    List<FileReference> references = getResult.getReferences();
    assertEquals(1, references.size());
    assertEquals("foo.txt", references.get(0).getFilename());
    List<FileReference> references2 = getResult2.getReferences();
    assertEquals(1, references2.size());
    assertEquals("foo.txt", references2.get(0).getFilename());
    assertEquals(1, getResult.getReferenceCount());
    assertEquals(1, getResult2.getReferenceCount());
  }

  /**
   * Test Builder {@link Builder#fileReferences(List)}.
   * <ul>
   *   <li>Then return build Files size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#fileReferences(List)}
   */
  @Test
  @DisplayName("Test Builder fileReferences(List); then return build Files size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.fileReferences(List)"})
  void testBuilderFileReferences_thenReturnBuildFilesSizeIsTwo() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();

    ArrayList<FileReference> fileReferences = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder()
        .countApproximate(true)
        .filename("filename must not be null")
        .jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult2);

    // Act and Assert
    List<AllReferencesToAFile> files = builderResult.fileReferences(fileReferences).build().getFiles();
    assertEquals(2, files.size());
    assertEquals("filename must not be null", files.get(0).getFilename());
    List<AllReferencesToAFile> files2 = builderResult.build().getFiles();
    assertEquals(2, files2.size());
    assertEquals("filename must not be null", files2.get(0).getFilename());
    AllReferencesToAFile getResult = files.get(1);
    assertEquals("foo.txt", getResult.getFilename());
    AllReferencesToAFile getResult2 = files2.get(1);
    assertEquals("foo.txt", getResult2.getFilename());
    assertNull(getResult.getLastStateStoreUpdateTime());
    assertNull(getResult2.getLastStateStoreUpdateTime());
    assertEquals(1, getResult.getReferences().size());
    assertEquals(1, getResult2.getReferences().size());
    assertEquals(1, getResult.getReferenceCount());
    assertEquals(1, getResult2.getReferenceCount());
  }

  /**
   * Test Builder {@link Builder#fileReferences(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then builder build Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#fileReferences(List)}
   */
  @Test
  @DisplayName("Test Builder fileReferences(List); when ArrayList(); then builder build Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.fileReferences(List)"})
  void testBuilderFileReferences_whenArrayList_thenBuilderBuildEmpty() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();

    // Act
    Builder actualFileReferencesResult = builderResult.fileReferences(new ArrayList<>());

    // Assert
    assertTrue(builderResult.build().isEmpty());
    assertSame(builderResult, actualFileReferencesResult);
  }

  /**
   * Test Builder {@link Builder#jobRunIds(IngestJobRunIds)}.
   * <p>
   * Method under test: {@link Builder#jobRunIds(IngestJobRunIds)}
   */
  @Test
  @DisplayName("Test Builder jobRunIds(IngestJobRunIds)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.jobRunIds(IngestJobRunIds)"})
  void testBuilderJobRunIds() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    IngestJobRunIds jobRunIds = IngestJobRunIds.builder().jobId("42").jobRunId("42").tableId("42").taskId("42").build();

    // Act and Assert
    assertSame(builderResult, builderResult.jobRunIds(jobRunIds));
  }

  /**
   * Test {@link AddFilesTransaction#AddFilesTransaction(List)}.
   * <ul>
   *   <li>Then return Files first Filename is {@code foo.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#AddFilesTransaction(List)}
   */
  @Test
  @DisplayName("Test new AddFilesTransaction(List); then return Files first Filename is 'foo.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.<init>(List)"})
  void testNewAddFilesTransaction_thenReturnFilesFirstFilenameIsFooTxt() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act
    AddFilesTransaction actualAddFilesTransaction = new AddFilesTransaction(files);

    // Assert
    List<AllReferencesToAFile> files2 = actualAddFilesTransaction.getFiles();
    assertEquals(1, files2.size());
    AllReferencesToAFile getResult = files2.get(0);
    assertEquals("foo.txt", getResult.getFilename());
    assertNull(getResult.getLastStateStoreUpdateTime());
    assertEquals(0, getResult.getReferenceCount());
    assertFalse(actualAddFilesTransaction.isEmpty());
    assertTrue(getResult.getReferences().isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#AddFilesTransaction(List)}.
   * <ul>
   *   <li>Then return Files first References size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#AddFilesTransaction(List)}
   */
  @Test
  @DisplayName("Test new AddFilesTransaction(List); then return Files first References size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.<init>(List)"})
  void testNewAddFilesTransaction_thenReturnFilesFirstReferencesSizeIsOne() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult2 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    files.add(buildResult2);

    // Act and Assert
    List<AllReferencesToAFile> files2 = (new AddFilesTransaction(files)).getFiles();
    assertEquals(1, files2.size());
    AllReferencesToAFile getResult = files2.get(0);
    List<FileReference> references2 = getResult.getReferences();
    assertEquals(1, references2.size());
    FileReference getResult2 = references2.get(0);
    assertEquals("42", getResult2.getJobId());
    assertEquals("42", getResult2.getPartitionId());
    assertEquals("foo.txt", getResult2.getFilename());
    assertNull(getResult2.getLastStateStoreUpdateTime());
    assertEquals(1, getResult.getReferenceCount());
    assertEquals(1L, getResult2.getNumberOfRecords().longValue());
    assertTrue(getResult2.isCountApproximate());
    assertTrue(getResult2.onlyContainsDataForThisPartition());
  }

  /**
   * Test {@link AddFilesTransaction#AddFilesTransaction(List)}.
   * <ul>
   *   <li>Then return Files first References size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#AddFilesTransaction(List)}
   */
  @Test
  @DisplayName("Test new AddFilesTransaction(List); then return Files first References size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.<init>(List)"})
  void testNewAddFilesTransaction_thenReturnFilesFirstReferencesSizeIsTwo() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult3 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    files.add(buildResult3);

    // Act and Assert
    List<AllReferencesToAFile> files2 = (new AddFilesTransaction(files)).getFiles();
    assertEquals(1, files2.size());
    AllReferencesToAFile getResult = files2.get(0);
    List<FileReference> references2 = getResult.getReferences();
    assertEquals(2, references2.size());
    FileReference getResult2 = references2.get(0);
    assertEquals("42", getResult2.getJobId());
    assertEquals("42", getResult2.getPartitionId());
    assertEquals("foo.txt", getResult2.getFilename());
    assertNull(getResult2.getLastStateStoreUpdateTime());
    assertEquals(1L, getResult2.getNumberOfRecords().longValue());
    assertEquals(2, getResult.getReferenceCount());
    assertTrue(getResult2.isCountApproximate());
    assertTrue(getResult2.onlyContainsDataForThisPartition());
  }

  /**
   * Test {@link AddFilesTransaction#AddFilesTransaction(List)}.
   * <ul>
   *   <li>Then return Files size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#AddFilesTransaction(List)}
   */
  @Test
  @DisplayName("Test new AddFilesTransaction(List); then return Files size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.<init>(List)"})
  void testNewAddFilesTransaction_thenReturnFilesSizeIsTwo() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    AllReferencesToAFile.Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    files.add(buildResult2);

    // Act and Assert
    List<AllReferencesToAFile> files2 = (new AddFilesTransaction(files)).getFiles();
    assertEquals(2, files2.size());
    assertEquals(files2.get(0), files2.get(1));
  }

  /**
   * Test {@link AddFilesTransaction#AddFilesTransaction(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Files Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#AddFilesTransaction(List)}
   */
  @Test
  @DisplayName("Test new AddFilesTransaction(List); when ArrayList(); then return Files Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.<init>(List)"})
  void testNewAddFilesTransaction_whenArrayList_thenReturnFilesEmpty() {
    // Arrange and Act
    AddFilesTransaction actualAddFilesTransaction = new AddFilesTransaction(new ArrayList<>());

    // Assert
    assertTrue(actualAddFilesTransaction.getFiles().isEmpty());
    assertTrue(actualAddFilesTransaction.isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#fromReferences(List)}.
   * <ul>
   *   <li>Then return Files first ReferenceCount is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#fromReferences(List)}
   */
  @Test
  @DisplayName("Test fromReferences(List); then return Files first ReferenceCount is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AddFilesTransaction AddFilesTransaction.fromReferences(List)"})
  void testFromReferences_thenReturnFilesFirstReferenceCountIsOne() {
    // Arrange
    ArrayList<FileReference> fileReferences = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult);

    // Act and Assert
    List<AllReferencesToAFile> files = AddFilesTransaction.fromReferences(fileReferences).getFiles();
    assertEquals(1, files.size());
    AllReferencesToAFile getResult = files.get(0);
    assertEquals("foo.txt", getResult.getFilename());
    List<FileReference> references = getResult.getReferences();
    assertEquals(1, references.size());
    assertEquals("foo.txt", references.get(0).getFilename());
    assertEquals(1, getResult.getReferenceCount());
  }

  /**
   * Test {@link AddFilesTransaction#fromReferences(List)}.
   * <ul>
   *   <li>Then return Files first References size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#fromReferences(List)}
   */
  @Test
  @DisplayName("Test fromReferences(List); then return Files first References size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AddFilesTransaction AddFilesTransaction.fromReferences(List)"})
  void testFromReferences_thenReturnFilesFirstReferencesSizeIsTwo() {
    // Arrange
    ArrayList<FileReference> fileReferences = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult2);

    // Act and Assert
    List<AllReferencesToAFile> files = AddFilesTransaction.fromReferences(fileReferences).getFiles();
    assertEquals(1, files.size());
    AllReferencesToAFile getResult = files.get(0);
    assertEquals("foo.txt", getResult.getFilename());
    List<FileReference> references = getResult.getReferences();
    assertEquals(2, references.size());
    assertEquals("foo.txt", references.get(0).getFilename());
    assertEquals(2, getResult.getReferenceCount());
  }

  /**
   * Test {@link AddFilesTransaction#fromReferences(List)}.
   * <ul>
   *   <li>Then return Files size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#fromReferences(List)}
   */
  @Test
  @DisplayName("Test fromReferences(List); then return Files size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AddFilesTransaction AddFilesTransaction.fromReferences(List)"})
  void testFromReferences_thenReturnFilesSizeIsTwo() {
    // Arrange
    ArrayList<FileReference> fileReferences = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder()
        .countApproximate(true)
        .filename("filename must not be null")
        .jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult2);

    // Act and Assert
    List<AllReferencesToAFile> files = AddFilesTransaction.fromReferences(fileReferences).getFiles();
    assertEquals(2, files.size());
    AllReferencesToAFile getResult = files.get(1);
    List<FileReference> references = getResult.getReferences();
    assertEquals(1, references.size());
    FileReference getResult2 = references.get(0);
    assertEquals("42", getResult2.getJobId());
    assertEquals("42", getResult2.getPartitionId());
    AllReferencesToAFile getResult3 = files.get(0);
    assertEquals("filename must not be null", getResult3.getFilename());
    List<FileReference> references2 = getResult3.getReferences();
    assertEquals(1, references2.size());
    assertEquals("filename must not be null", references2.get(0).getFilename());
    assertEquals("foo.txt", getResult.getFilename());
    assertEquals("foo.txt", getResult2.getFilename());
    assertNull(getResult.getLastStateStoreUpdateTime());
    assertNull(getResult2.getLastStateStoreUpdateTime());
    assertEquals(1, getResult.getReferenceCount());
    assertEquals(1L, getResult2.getNumberOfRecords().longValue());
    assertTrue(getResult2.isCountApproximate());
    assertTrue(getResult2.onlyContainsDataForThisPartition());
  }

  /**
   * Test {@link AddFilesTransaction#fromReferences(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Files Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#fromReferences(List)}
   */
  @Test
  @DisplayName("Test fromReferences(List); when ArrayList(); then return Files Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AddFilesTransaction AddFilesTransaction.fromReferences(List)"})
  void testFromReferences_whenArrayList_thenReturnFilesEmpty() {
    // Arrange and Act
    AddFilesTransaction actualFromReferencesResult = AddFilesTransaction.fromReferences(new ArrayList<>());

    // Assert
    assertTrue(actualFromReferencesResult.getFiles().isEmpty());
    assertTrue(actualFromReferencesResult.isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>Then throw {@link FileAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); then throw FileAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_thenThrowFileAlreadyExistsException() throws StateStoreException {
    // Arrange
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doThrow(new FileAlreadyExistsException("foo.txt")).when(stateStore)
        .addTransaction(Mockito.<AddTransactionRequest>any());

    // Act and Assert
    assertThrows(FileAlreadyExistsException.class, () -> addFilesTransaction.synchronousCommit(stateStore));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link AddFilesTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link StateStore#addTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); when StateStore addTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_whenStateStoreAddTransactionDoesNothing() throws StateStoreException {
    // Arrange
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    addFilesTransaction.synchronousCommit(stateStore);

    // Assert
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link AddFilesTransaction#validateFiles(StateStoreFiles)}.
   * <ul>
   *   <li>Then throw {@link FileAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#validateFiles(StateStoreFiles)}
   */
  @Test
  @DisplayName("Test validateFiles(StateStoreFiles); then throw FileAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.validateFiles(StateStoreFiles)"})
  void testValidateFiles_thenThrowFileAlreadyExistsException() throws StateStoreException {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(files);

    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Instant lastStateStoreUpdateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    stateStoreFiles.add(new StateStoreFile("foo.txt", lastStateStoreUpdateTime, new ArrayList<>()));

    // Act and Assert
    assertThrows(FileAlreadyExistsException.class, () -> addFilesTransaction.validateFiles(stateStoreFiles));
  }

  /**
   * Test {@link AddFilesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <p>
   * Method under test: {@link AddFilesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult2 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    files.add(buildResult2);
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(files);
    StateStoreFiles stateStoreFiles = new StateStoreFiles();

    // Act
    addFilesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertFalse(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <p>
   * Method under test: {@link AddFilesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant2() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult3 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    files.add(buildResult3);
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(files);
    StateStoreFiles stateStoreFiles = new StateStoreFiles();

    // Act
    addFilesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertFalse(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <p>
   * Method under test: {@link AddFilesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant3() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(files);

    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Instant lastStateStoreUpdateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    stateStoreFiles.add(new StateStoreFile("foo.txt", lastStateStoreUpdateTime, new ArrayList<>()));

    // Act
    addFilesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert that nothing has changed
    assertFalse(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <ul>
   *   <li>Then not {@link StateStoreFiles} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'; then not StateStoreFiles (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant_thenNotStateStoreFilesEmpty() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(files);
    StateStoreFiles stateStoreFiles = new StateStoreFiles();

    // Act
    addFilesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertFalse(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <ul>
   *   <li>When {@link StateStoreFiles} (default constructor).</li>
   *   <li>Then {@link StateStoreFiles} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'; when StateStoreFiles (default constructor); then StateStoreFiles (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant_whenStateStoreFiles_thenStateStoreFilesEmpty() {
    // Arrange
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(new ArrayList<>());
    StateStoreFiles stateStoreFiles = new StateStoreFiles();

    // Act
    addFilesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert that nothing has changed
    assertTrue(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#isEmpty()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.isEmpty()"})
  void testIsEmpty_thenReturnFalse() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act and Assert
    assertFalse((new AddFilesTransaction(files)).isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#isEmpty()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.isEmpty()"})
  void testIsEmpty_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new AddFilesTransaction(new ArrayList<>())).isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles)}.
   * <ul>
   *   <li>Then calls {@link StateStoreFiles#file(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles)}
   */
  @Test
  @DisplayName("Test reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles); then calls file(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles)"})
  void testReportJobCommit_thenCallsFile() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    Builder taskIdResult = AddFilesTransaction.builder().files(files).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult2 = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    IngestJobTracker tracker = mock(IngestJobTracker.class);
    TableStatus sleeperTable = mock(TableStatus.class);
    when(sleeperTable.getTableUniqueId()).thenThrow(new FileAlreadyExistsException("foo.txt"));
    StateStoreFiles stateBefore = mock(StateStoreFiles.class);
    Instant lastStateStoreUpdateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Optional<StateStoreFile> ofResult = Optional
        .of(new StateStoreFile("foo.txt", lastStateStoreUpdateTime, new ArrayList<>()));
    when(stateBefore.file(Mockito.<String>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(FileAlreadyExistsException.class,
        () -> buildResult2.reportJobCommit(tracker, sleeperTable, stateBefore));
    verify(stateBefore).file(eq("foo.txt"));
    verify(sleeperTable).getTableUniqueId();
  }

  /**
   * Test {@link AddFilesTransaction#reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles)}.
   * <ul>
   *   <li>Then calls {@link IngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles)}
   */
  @Test
  @DisplayName("Test reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles); then calls jobAddedFiles(IngestJobAddedFilesEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles)"})
  void testReportJobCommit_thenCallsJobAddedFiles() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    IngestJobTracker tracker = mock(IngestJobTracker.class);
    doNothing().when(tracker).jobAddedFiles(Mockito.<IngestJobAddedFilesEvent>any());
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act
    buildResult.reportJobCommit(tracker, sleeperTable, new StateStoreFiles());

    // Assert
    verify(tracker).jobAddedFiles(isA(IngestJobAddedFilesEvent.class));
  }

  /**
   * Test {@link AddFilesTransaction#reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles)}.
   * <ul>
   *   <li>When {@link IngestJobTracker}.</li>
   *   <li>Then throw {@link FileAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles)}
   */
  @Test
  @DisplayName("Test reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles); when IngestJobTracker; then throw FileAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.reportJobCommit(IngestJobTracker, TableStatus, StateStoreFiles)"})
  void testReportJobCommit_whenIngestJobTracker_thenThrowFileAlreadyExistsException() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    IngestJobTracker tracker = mock(IngestJobTracker.class);
    TableStatus sleeperTable = mock(TableStatus.class);
    when(sleeperTable.getTableUniqueId()).thenThrow(new FileAlreadyExistsException("foo.txt"));

    // Act and Assert
    assertThrows(FileAlreadyExistsException.class,
        () -> buildResult.reportJobCommit(tracker, sleeperTable, new StateStoreFiles()));
    verify(sleeperTable, atLeast(1)).getTableUniqueId();
  }

  /**
   * Test {@link AddFilesTransaction#reportJobCommitOrThrow(IngestJobTracker, TableStatus, StateStoreFiles)}.
   * <ul>
   *   <li>Then throw {@link FileAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#reportJobCommitOrThrow(IngestJobTracker, TableStatus, StateStoreFiles)}
   */
  @Test
  @DisplayName("Test reportJobCommitOrThrow(IngestJobTracker, TableStatus, StateStoreFiles); then throw FileAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.reportJobCommitOrThrow(IngestJobTracker, TableStatus, StateStoreFiles)"})
  void testReportJobCommitOrThrow_thenThrowFileAlreadyExistsException() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(files);
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    StateStoreFiles stateBefore = new StateStoreFiles();
    Instant lastStateStoreUpdateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    stateBefore.add(new StateStoreFile("foo.txt", lastStateStoreUpdateTime, new ArrayList<>()));

    // Act and Assert
    assertThrows(FileAlreadyExistsException.class,
        () -> addFilesTransaction.reportJobCommitOrThrow(IngestJobTracker.NONE, sleeperTable, stateBefore));
  }

  /**
   * Test {@link AddFilesTransaction#reportJobFailed(IngestJobTracker, TableStatus, Exception)}.
   * <ul>
   *   <li>When {@link IngestJobTracker} {@link IngestJobTracker#jobFailed(IngestJobFailedEvent)} does nothing.</li>
   *   <li>Then calls {@link IngestJobTracker#jobFailed(IngestJobFailedEvent)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#reportJobFailed(IngestJobTracker, TableStatus, Exception)}
   */
  @Test
  @DisplayName("Test reportJobFailed(IngestJobTracker, TableStatus, Exception); when IngestJobTracker jobFailed(IngestJobFailedEvent) does nothing; then calls jobFailed(IngestJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddFilesTransaction.reportJobFailed(IngestJobTracker, TableStatus, Exception)"})
  void testReportJobFailed_whenIngestJobTrackerJobFailedDoesNothing_thenCallsJobFailed() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    IngestJobTracker tracker = mock(IngestJobTracker.class);
    doNothing().when(tracker).jobFailed(Mockito.<IngestJobFailedEvent>any());
    TableStatus sleeperTable = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act
    buildResult.reportJobFailed(tracker, sleeperTable, new Exception("foo"));

    // Assert
    verify(tracker).jobFailed(isA(IngestJobFailedEvent.class));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AddFilesTransaction#validate(StateStoreFiles)}
   *   <li>{@link AddFilesTransaction#toString()}
   *   <li>{@link AddFilesTransaction#getFiles()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AddFilesTransaction.getFiles()", "String AddFilesTransaction.toString()",
      "void AddFilesTransaction.validate(StateStoreFiles)"})
  void testGettersAndSetters() throws StateStoreException {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    buildResult.validate(new StateStoreFiles());
    String actualToStringResult = buildResult.toString();

    // Assert
    assertEquals("AddFilesTransaction{jobId=42, taskId=42, jobRunId=42, writtenTime=1970-01-01T00:00:00Z, files=[]}",
        actualToStringResult);
    assertTrue(buildResult.getFiles().isEmpty());
  }

  /**
   * Test {@link AddFilesTransaction#equals(Object)}, and {@link AddFilesTransaction#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AddFilesTransaction#equals(Object)}
   *   <li>{@link AddFilesTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.equals(Object)", "int AddFilesTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = AddFilesTransaction.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link AddFilesTransaction#equals(Object)}, and {@link AddFilesTransaction#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AddFilesTransaction#equals(Object)}
   *   <li>{@link AddFilesTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.equals(Object)", "int AddFilesTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link AddFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.equals(Object)", "int AddFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    Builder taskIdResult = AddFilesTransaction.builder().files(files).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult2 = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult2 = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult3 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult2, buildResult3);
  }

  /**
   * Test {@link AddFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.equals(Object)", "int AddFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("Job Id").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = AddFilesTransaction.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link AddFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.equals(Object)", "int AddFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("Job Run Id").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = AddFilesTransaction.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link AddFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.equals(Object)", "int AddFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("Task Id");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = AddFilesTransaction.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link AddFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.equals(Object)", "int AddFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = AddFilesTransaction.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link AddFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.equals(Object)", "int AddFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link AddFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddFilesTransaction.equals(Object)", "int AddFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = AddFilesTransaction.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").taskId("42");
    AddFilesTransaction buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to AddFilesTransaction");
  }
}
