package sleeper.systemtest.dsl.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.JobRunTime;
import sleeper.core.tracker.job.run.RecordsProcessed;

class StateStoreCommitMessageFactoryDiffblueTest {
  /**
   * Test {@link StateStoreCommitMessageFactory#addFiles(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#addFiles(List)}
   */
  @Test
  @DisplayName("Test addFiles(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.addFiles(List)"})
  void testAddFiles() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);

    // Act
    StateStoreCommitRequest actualAddFilesResult = stateStoreCommitMessageFactory.addFiles(files);

    // Assert
    assertNull(actualAddFilesResult.getBodyKey());
    assertNull(actualAddFilesResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualAddFilesResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#addFiles(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#addFiles(List)}
   */
  @Test
  @DisplayName("Test addFiles(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.addFiles(List)"})
  void testAddFiles2() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult2);

    // Act
    StateStoreCommitRequest actualAddFilesResult = stateStoreCommitMessageFactory.addFiles(files);

    // Assert
    assertNull(actualAddFilesResult.getBodyKey());
    assertNull(actualAddFilesResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualAddFilesResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#addFiles(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#addFiles(List)}
   */
  @Test
  @DisplayName("Test addFiles(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.addFiles(List)"})
  void testAddFiles3() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("Filename").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult2);

    // Act
    StateStoreCommitRequest actualAddFilesResult = stateStoreCommitMessageFactory.addFiles(files);

    // Assert
    assertNull(actualAddFilesResult.getBodyKey());
    assertNull(actualAddFilesResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualAddFilesResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#addFiles(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return BodyKey is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#addFiles(List)}
   */
  @Test
  @DisplayName("Test addFiles(List); when ArrayList(); then return BodyKey is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.addFiles(List)"})
  void testAddFiles_whenArrayList_thenReturnBodyKeyIsNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    // Act
    StateStoreCommitRequest actualAddFilesResult = stateStoreCommitMessageFactory.addFiles(new ArrayList<>());

    // Assert
    assertNull(actualAddFilesResult.getBodyKey());
    assertNull(actualAddFilesResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualAddFilesResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#addFileWithJob(FileReference)}.
   * <ul>
   *   <li>Then return BodyKey is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#addFileWithJob(FileReference)}
   */
  @Test
  @DisplayName("Test addFileWithJob(FileReference); then return BodyKey is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.addFileWithJob(FileReference)"})
  void testAddFileWithJob_thenReturnBodyKeyIsNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    StateStoreCommitRequest actualAddFileWithJobResult = stateStoreCommitMessageFactory.addFileWithJob(file);

    // Assert
    assertNull(actualAddFileWithJobResult.getBodyKey());
    assertNull(actualAddFileWithJobResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualAddFileWithJobResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#addFilesWithJob(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#addFilesWithJob(List)}
   */
  @Test
  @DisplayName("Test addFilesWithJob(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.addFilesWithJob(List)"})
  void testAddFilesWithJob() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);

    // Act
    StateStoreCommitRequest actualAddFilesWithJobResult = stateStoreCommitMessageFactory.addFilesWithJob(files);

    // Assert
    assertNull(actualAddFilesWithJobResult.getBodyKey());
    assertNull(actualAddFilesWithJobResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualAddFilesWithJobResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#addFilesWithJob(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#addFilesWithJob(List)}
   */
  @Test
  @DisplayName("Test addFilesWithJob(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.addFilesWithJob(List)"})
  void testAddFilesWithJob2() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult2);

    // Act
    StateStoreCommitRequest actualAddFilesWithJobResult = stateStoreCommitMessageFactory.addFilesWithJob(files);

    // Assert
    assertNull(actualAddFilesWithJobResult.getBodyKey());
    assertNull(actualAddFilesWithJobResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualAddFilesWithJobResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#addFilesWithJob(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#addFilesWithJob(List)}
   */
  @Test
  @DisplayName("Test addFilesWithJob(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.addFilesWithJob(List)"})
  void testAddFilesWithJob3() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("Filename").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult2);

    // Act
    StateStoreCommitRequest actualAddFilesWithJobResult = stateStoreCommitMessageFactory.addFilesWithJob(files);

    // Assert
    assertNull(actualAddFilesWithJobResult.getBodyKey());
    assertNull(actualAddFilesWithJobResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualAddFilesWithJobResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#addFilesWithJob(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return BodyKey is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#addFilesWithJob(List)}
   */
  @Test
  @DisplayName("Test addFilesWithJob(List); when ArrayList(); then return BodyKey is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.addFilesWithJob(List)"})
  void testAddFilesWithJob_whenArrayList_thenReturnBodyKeyIsNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    // Act
    StateStoreCommitRequest actualAddFilesWithJobResult = stateStoreCommitMessageFactory
        .addFilesWithJob(new ArrayList<>());

    // Assert
    assertNull(actualAddFilesWithJobResult.getBodyKey());
    assertNull(actualAddFilesWithJobResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualAddFilesWithJobResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#assignJobOnPartitionToFiles(String, String, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#assignJobOnPartitionToFiles(String, String, List)}
   */
  @Test
  @DisplayName("Test assignJobOnPartitionToFiles(String, String, List); given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreCommitRequest StateStoreCommitMessageFactory.assignJobOnPartitionToFiles(String, String, List)"})
  void testAssignJobOnPartitionToFiles_given42_whenArrayListAdd42() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("42");
    filenames.add("foo");

    // Act
    StateStoreCommitRequest actualAssignJobOnPartitionToFilesResult = stateStoreCommitMessageFactory
        .assignJobOnPartitionToFiles("42", "42", filenames);

    // Assert
    assertNull(actualAssignJobOnPartitionToFilesResult.getBodyKey());
    assertNull(actualAssignJobOnPartitionToFilesResult.getTableId());
    assertEquals(TransactionType.ASSIGN_JOB_IDS, actualAssignJobOnPartitionToFilesResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#assignJobOnPartitionToFiles(String, String, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#assignJobOnPartitionToFiles(String, String, List)}
   */
  @Test
  @DisplayName("Test assignJobOnPartitionToFiles(String, String, List); given 'foo'; when ArrayList() add 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreCommitRequest StateStoreCommitMessageFactory.assignJobOnPartitionToFiles(String, String, List)"})
  void testAssignJobOnPartitionToFiles_givenFoo_whenArrayListAddFoo() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("foo");

    // Act
    StateStoreCommitRequest actualAssignJobOnPartitionToFilesResult = stateStoreCommitMessageFactory
        .assignJobOnPartitionToFiles("42", "42", filenames);

    // Assert
    assertNull(actualAssignJobOnPartitionToFilesResult.getBodyKey());
    assertNull(actualAssignJobOnPartitionToFilesResult.getTableId());
    assertEquals(TransactionType.ASSIGN_JOB_IDS, actualAssignJobOnPartitionToFilesResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#assignJobOnPartitionToFiles(String, String, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return BodyKey is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#assignJobOnPartitionToFiles(String, String, List)}
   */
  @Test
  @DisplayName("Test assignJobOnPartitionToFiles(String, String, List); when ArrayList(); then return BodyKey is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreCommitRequest StateStoreCommitMessageFactory.assignJobOnPartitionToFiles(String, String, List)"})
  void testAssignJobOnPartitionToFiles_whenArrayList_thenReturnBodyKeyIsNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    // Act
    StateStoreCommitRequest actualAssignJobOnPartitionToFilesResult = stateStoreCommitMessageFactory
        .assignJobOnPartitionToFiles("42", "42", new ArrayList<>());

    // Assert
    assertNull(actualAssignJobOnPartitionToFilesResult.getBodyKey());
    assertNull(actualAssignJobOnPartitionToFilesResult.getTableId());
    assertEquals(TransactionType.ASSIGN_JOB_IDS, actualAssignJobOnPartitionToFilesResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#commitCompactionForPartitionOnTaskInRun(String, String, List, String, String, JobRunSummary)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return TableId is {@code Get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#commitCompactionForPartitionOnTaskInRun(String, String, List, String, String, JobRunSummary)}
   */
  @Test
  @DisplayName("Test commitCompactionForPartitionOnTaskInRun(String, String, List, String, String, JobRunSummary); given '42'; then return TableId is 'Get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreCommitRequest StateStoreCommitMessageFactory.commitCompactionForPartitionOnTaskInRun(String, String, List, String, String, JobRunSummary)"})
  void testCommitCompactionForPartitionOnTaskInRun_given42_thenReturnTableIdIsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, tableProperties);

    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("42");
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);

    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    StateStoreCommitRequest actualCommitCompactionForPartitionOnTaskInRunResult = stateStoreCommitMessageFactory
        .commitCompactionForPartitionOnTaskInRun("42", "42", filenames, "42", "42", new JobRunSummary(recordsProcessed,
            new JobRunTime(startTime, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    assertEquals("Get", actualCommitCompactionForPartitionOnTaskInRunResult.getTableId());
    assertNull(actualCommitCompactionForPartitionOnTaskInRunResult.getBodyKey());
    assertEquals(TransactionType.REPLACE_FILE_REFERENCES,
        actualCommitCompactionForPartitionOnTaskInRunResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#commitCompactionForPartitionOnTaskInRun(String, String, List, String, String, JobRunSummary)}.
   * <ul>
   *   <li>Then return TableId is {@code Get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#commitCompactionForPartitionOnTaskInRun(String, String, List, String, String, JobRunSummary)}
   */
  @Test
  @DisplayName("Test commitCompactionForPartitionOnTaskInRun(String, String, List, String, String, JobRunSummary); then return TableId is 'Get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreCommitRequest StateStoreCommitMessageFactory.commitCompactionForPartitionOnTaskInRun(String, String, List, String, String, JobRunSummary)"})
  void testCommitCompactionForPartitionOnTaskInRun_thenReturnTableIdIsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, tableProperties);
    ArrayList<String> filenames = new ArrayList<>();
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);

    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    StateStoreCommitRequest actualCommitCompactionForPartitionOnTaskInRunResult = stateStoreCommitMessageFactory
        .commitCompactionForPartitionOnTaskInRun("42", "42", filenames, "42", "42", new JobRunSummary(recordsProcessed,
            new JobRunTime(startTime, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    assertEquals("Get", actualCommitCompactionForPartitionOnTaskInRunResult.getTableId());
    assertNull(actualCommitCompactionForPartitionOnTaskInRunResult.getBodyKey());
    assertEquals(TransactionType.REPLACE_FILE_REFERENCES,
        actualCommitCompactionForPartitionOnTaskInRunResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#filesDeleted(List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return BodyKey is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#filesDeleted(List)}
   */
  @Test
  @DisplayName("Test filesDeleted(List); given '42'; when ArrayList() add '42'; then return BodyKey is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.filesDeleted(List)"})
  void testFilesDeleted_given42_whenArrayListAdd42_thenReturnBodyKeyIsNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("42");
    filenames.add("foo");

    // Act
    StateStoreCommitRequest actualFilesDeletedResult = stateStoreCommitMessageFactory.filesDeleted(filenames);

    // Assert
    assertNull(actualFilesDeletedResult.getBodyKey());
    assertNull(actualFilesDeletedResult.getTableId());
    assertEquals(TransactionType.DELETE_FILES, actualFilesDeletedResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#filesDeleted(List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return BodyKey is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#filesDeleted(List)}
   */
  @Test
  @DisplayName("Test filesDeleted(List); given 'foo'; when ArrayList() add 'foo'; then return BodyKey is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.filesDeleted(List)"})
  void testFilesDeleted_givenFoo_whenArrayListAddFoo_thenReturnBodyKeyIsNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("foo");

    // Act
    StateStoreCommitRequest actualFilesDeletedResult = stateStoreCommitMessageFactory.filesDeleted(filenames);

    // Assert
    assertNull(actualFilesDeletedResult.getBodyKey());
    assertNull(actualFilesDeletedResult.getTableId());
    assertEquals(TransactionType.DELETE_FILES, actualFilesDeletedResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessageFactory#filesDeleted(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return BodyKey is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessageFactory#filesDeleted(List)}
   */
  @Test
  @DisplayName("Test filesDeleted(List); when ArrayList(); then return BodyKey is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitMessageFactory.filesDeleted(List)"})
  void testFilesDeleted_whenArrayList_thenReturnBodyKeyIsNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitMessageFactory stateStoreCommitMessageFactory = new StateStoreCommitMessageFactory(
        instanceProperties, new TableProperties(new InstanceProperties()));

    // Act
    StateStoreCommitRequest actualFilesDeletedResult = stateStoreCommitMessageFactory.filesDeleted(new ArrayList<>());

    // Assert
    assertNull(actualFilesDeletedResult.getBodyKey());
    assertNull(actualFilesDeletedResult.getTableId());
    assertEquals(TransactionType.DELETE_FILES, actualFilesDeletedResult.getTransactionType());
  }
}
