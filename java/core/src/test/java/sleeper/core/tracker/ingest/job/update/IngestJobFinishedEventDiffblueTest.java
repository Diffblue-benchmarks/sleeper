package sleeper.core.tracker.ingest.job.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.AllReferencesToAFile;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.tracker.ingest.job.update.IngestJobFinishedEvent.Builder;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.JobRunTime;
import sleeper.core.tracker.job.run.RecordsProcessed;

class IngestJobFinishedEventDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#committedBySeparateFileUpdates(boolean)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#jobRunId(String)}
   *   <li>{@link Builder#summary(JobRunSummary)}
   *   <li>{@link Builder#tableId(String)}
   *   <li>{@link Builder#taskId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobFinishedEvent Builder.build()",
      "Builder Builder.committedBySeparateFileUpdates(boolean)", "Builder Builder.jobId(String)",
      "Builder Builder.jobRunId(String)", "Builder Builder.summary(JobRunSummary)", "Builder Builder.tableId(String)",
      "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);

    // Act
    IngestJobFinishedEvent actualBuildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getJobId());
    assertEquals("42", actualBuildResult.getJobRunId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("42", actualBuildResult.getTaskId());
    Instant finishTime = actualBuildResult.getFinishTime();
    assertEquals(0, finishTime.getNano());
    RecordsProcessed recordsProcessed = actualBuildResult.getRecordsProcessed();
    assertEquals(0L, recordsProcessed.getRecordsRead());
    assertEquals(0L, recordsProcessed.getRecordsWritten());
    assertEquals(10, actualBuildResult.getNumFilesWrittenByJob());
    assertEquals(60L, finishTime.getEpochSecond());
    assertTrue(actualBuildResult.isCommittedBySeparateFileUpdates());
  }

  /**
   * Test Builder {@link Builder#fileReferencesAddedByJob(List)}.
   * <p>
   * Method under test: {@link Builder#fileReferencesAddedByJob(List)}
   */
  @Test
  @DisplayName("Test Builder fileReferencesAddedByJob(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.fileReferencesAddedByJob(List)"})
  void testBuilderFileReferencesAddedByJob() {
    // Arrange
    Builder builderResult = IngestJobFinishedEvent.builder();

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
    assertSame(builderResult, builderResult.fileReferencesAddedByJob(fileReferences));
  }

  /**
   * Test Builder {@link Builder#fileReferencesAddedByJob(List)}.
   * <p>
   * Method under test: {@link Builder#fileReferencesAddedByJob(List)}
   */
  @Test
  @DisplayName("Test Builder fileReferencesAddedByJob(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.fileReferencesAddedByJob(List)"})
  void testBuilderFileReferencesAddedByJob2() {
    // Arrange
    Builder builderResult = IngestJobFinishedEvent.builder();

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
    assertSame(builderResult, builderResult.fileReferencesAddedByJob(fileReferences));
  }

  /**
   * Test Builder {@link Builder#fileReferencesAddedByJob(List)}.
   * <p>
   * Method under test: {@link Builder#fileReferencesAddedByJob(List)}
   */
  @Test
  @DisplayName("Test Builder fileReferencesAddedByJob(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.fileReferencesAddedByJob(List)"})
  void testBuilderFileReferencesAddedByJob3() {
    // Arrange
    Builder builderResult = IngestJobFinishedEvent.builder();

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
    assertSame(builderResult, builderResult.fileReferencesAddedByJob(fileReferences));
  }

  /**
   * Test Builder {@link Builder#fileReferencesAddedByJob(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#fileReferencesAddedByJob(List)}
   */
  @Test
  @DisplayName("Test Builder fileReferencesAddedByJob(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.fileReferencesAddedByJob(List)"})
  void testBuilderFileReferencesAddedByJob_whenArrayList() {
    // Arrange
    Builder builderResult = IngestJobFinishedEvent.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.fileReferencesAddedByJob(new ArrayList<>()));
  }

  /**
   * Test Builder {@link Builder#filesWrittenByJob(List)}.
   * <p>
   * Method under test: {@link Builder#filesWrittenByJob(List)}
   */
  @Test
  @DisplayName("Test Builder filesWrittenByJob(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.filesWrittenByJob(List)"})
  void testBuilderFilesWrittenByJob() {
    // Arrange
    Builder builderResult = IngestJobFinishedEvent.builder();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act and Assert
    assertSame(builderResult, builderResult.filesWrittenByJob(files));
  }

  /**
   * Test Builder {@link Builder#filesWrittenByJob(List)}.
   * <p>
   * Method under test: {@link Builder#filesWrittenByJob(List)}
   */
  @Test
  @DisplayName("Test Builder filesWrittenByJob(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.filesWrittenByJob(List)"})
  void testBuilderFilesWrittenByJob2() {
    // Arrange
    Builder builderResult = IngestJobFinishedEvent.builder();

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
    assertSame(builderResult, builderResult.filesWrittenByJob(files));
  }

  /**
   * Test Builder {@link Builder#filesWrittenByJob(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#filesWrittenByJob(List)}
   */
  @Test
  @DisplayName("Test Builder filesWrittenByJob(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.filesWrittenByJob(List)"})
  void testBuilderFilesWrittenByJob_whenArrayList() {
    // Arrange
    Builder builderResult = IngestJobFinishedEvent.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.filesWrittenByJob(new ArrayList<>()));
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
    Builder builderResult = IngestJobFinishedEvent.builder();
    IngestJobRunIds jobRunIds = IngestJobRunIds.builder().jobId("42").jobRunId("42").tableId("42").taskId("42").build();

    // Act and Assert
    assertSame(builderResult, builderResult.jobRunIds(jobRunIds));
  }

  /**
   * Test Builder {@link Builder#numFilesWrittenByJob(int)}.
   * <p>
   * Method under test: {@link Builder#numFilesWrittenByJob(int)}
   */
  @Test
  @DisplayName("Test Builder numFilesWrittenByJob(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.numFilesWrittenByJob(int)"})
  void testBuilderNumFilesWrittenByJob() {
    // Arrange
    Builder builderResult = IngestJobFinishedEvent.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.numFilesWrittenByJob(10));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobFinishedEvent#toString()}
   *   <li>{@link IngestJobFinishedEvent#getJobId()}
   *   <li>{@link IngestJobFinishedEvent#getJobRunId()}
   *   <li>{@link IngestJobFinishedEvent#getNumFilesWrittenByJob()}
   *   <li>{@link IngestJobFinishedEvent#getTableId()}
   *   <li>{@link IngestJobFinishedEvent#getTaskId()}
   *   <li>{@link IngestJobFinishedEvent#isCommittedBySeparateFileUpdates()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobFinishedEvent.getJobId()", "String IngestJobFinishedEvent.getJobRunId()",
      "int IngestJobFinishedEvent.getNumFilesWrittenByJob()", "String IngestJobFinishedEvent.getTableId()",
      "String IngestJobFinishedEvent.getTaskId()", "boolean IngestJobFinishedEvent.isCommittedBySeparateFileUpdates()",
      "String IngestJobFinishedEvent.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    int actualNumFilesWrittenByJob = buildResult.getNumFilesWrittenByJob();
    String actualTableId = buildResult.getTableId();
    String actualTaskId = buildResult.getTaskId();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", actualTableId);
    assertEquals("42", actualTaskId);
    assertEquals(
        "IngestJobFinishedEvent{jobId=42, tableId=42, summary=JobRunSummary{recordsProcessed=RecordsProcessed"
            + "{recordsRead=0, recordsWritten=0}, runTime=JobRunTime{startTime=1970-01-01T00:00:00Z, endTime=1970-01"
            + "-01T00:01:00Z, timeInProcess=PT1M}, recordsReadPerSecond=0.0, recordsWrittenPerSecond=0.0},"
            + " numFilesWrittenByJob=10, committedBySeparateFileUpdates=true, jobRunId=42, taskId=42}",
        actualToStringResult);
    assertEquals(10, actualNumFilesWrittenByJob);
    assertTrue(buildResult.isCommittedBySeparateFileUpdates());
  }

  /**
   * Test {@link IngestJobFinishedEvent#getFinishTime()}.
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#getFinishTime()}
   */
  @Test
  @DisplayName("Test getFinishTime()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant IngestJobFinishedEvent.getFinishTime()"})
  void testGetFinishTime() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    Instant actualFinishTime = buildResult.getFinishTime();

    // Assert
    assertEquals(0, actualFinishTime.getNano());
    assertEquals(60L, actualFinishTime.getEpochSecond());
  }

  /**
   * Test {@link IngestJobFinishedEvent#getRecordsProcessed()}.
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#getRecordsProcessed()}
   */
  @Test
  @DisplayName("Test getRecordsProcessed()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RecordsProcessed IngestJobFinishedEvent.getRecordsProcessed()"})
  void testGetRecordsProcessed() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    RecordsProcessed actualRecordsProcessed = buildResult.getRecordsProcessed();

    // Assert
    assertSame(actualRecordsProcessed.NONE, actualRecordsProcessed);
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}, and {@link IngestJobFinishedEvent#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobFinishedEvent#equals(Object)}
   *   <li>{@link IngestJobFinishedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();
    Builder numFilesWrittenByJobResult2 = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult2 = numFilesWrittenByJobResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}, and {@link IngestJobFinishedEvent#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobFinishedEvent#equals(Object)}
   *   <li>{@link IngestJobFinishedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(false)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();
    Builder numFilesWrittenByJobResult2 = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult2 = numFilesWrittenByJobResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("Job Id")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();
    Builder numFilesWrittenByJobResult2 = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult2 = numFilesWrittenByJobResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("Job Run Id")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();
    Builder numFilesWrittenByJobResult2 = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult2 = numFilesWrittenByJobResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(1);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();
    Builder numFilesWrittenByJobResult2 = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult2 = numFilesWrittenByJobResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);

    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(recordsProcessed,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();
    Builder numFilesWrittenByJobResult2 = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult2 = numFilesWrittenByJobResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("Table Id")
        .taskId("42")
        .build();
    Builder numFilesWrittenByJobResult2 = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult2 = numFilesWrittenByJobResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(new JobRunSummary(RecordsProcessed.NONE,
            new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("Task Id")
        .build();
    Builder numFilesWrittenByJobResult2 = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult2 = numFilesWrittenByJobResult2
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestJobFinishedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedEvent.equals(Object)", "int IngestJobFinishedEvent.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder numFilesWrittenByJobResult = IngestJobFinishedEvent.builder()
        .committedBySeparateFileUpdates(true)
        .jobId("42")
        .jobRunId("42")
        .numFilesWrittenByJob(10);
    IngestJobFinishedEvent buildResult = numFilesWrittenByJobResult
        .summary(
            new JobRunSummary(RecordsProcessed.NONE,
                new JobRunTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
                    TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS)))
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJobFinishedEvent");
  }
}
