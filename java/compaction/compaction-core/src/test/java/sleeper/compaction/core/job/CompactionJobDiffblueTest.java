package sleeper.compaction.core.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.core.job.CompactionJob.Builder;
import sleeper.core.statestore.AssignJobIdRequest;
import sleeper.core.statestore.CheckFileAssignmentsRequest;
import sleeper.core.statestore.FileReference;
import sleeper.core.tracker.compaction.job.update.CompactionJobCreatedEvent;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.JobRunTime;
import sleeper.core.tracker.job.run.RecordsProcessed;

class CompactionJobDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#inputFiles(List)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#outputFile(String)}
   *   <li>{@link Builder#partitionId(String)}
   *   <li>{@link Builder#tableId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CompactionJob Builder.build()",
    "Builder Builder.inputFiles(List)",
    "Builder Builder.iteratorClassName(String)",
    "Builder Builder.iteratorConfig(String)",
    "Builder Builder.jobId(String)",
    "Builder Builder.outputFile(String)",
    "Builder Builder.partitionId(String)",
    "Builder Builder.tableId(String)"
  })
  void testBuilderBuild() {
    // Arrange and Act
    Builder actualBuilderResult = CompactionJob.builder();
    ArrayList<String> inputFiles = new ArrayList<>();
    CompactionJob actualCompactionJob =
        actualBuilderResult
            .inputFiles(inputFiles)
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    // Assert
    assertEquals("42", actualCompactionJob.getId());
    assertEquals("42", actualCompactionJob.getPartitionId());
    assertEquals("42", actualCompactionJob.getTableId());
    assertEquals("Output File", actualCompactionJob.getOutputFile());
    assertNull(actualCompactionJob.getIteratorClassName());
    assertNull(actualCompactionJob.getIteratorConfig());
    List<String> inputFiles2 = actualCompactionJob.getInputFiles();
    assertTrue(inputFiles2.isEmpty());
    assertSame(inputFiles, inputFiles2);
  }

  /**
   * Test {@link CompactionJob#createInputFileAssignmentsCheck()}.
   *
   * <p>Method under test: {@link CompactionJob#createInputFileAssignmentsCheck()}
   */
  @Test
  @DisplayName("Test createInputFileAssignmentsCheck()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"CheckFileAssignmentsRequest CompactionJob.createInputFileAssignmentsCheck()"})
  void testCreateInputFileAssignmentsCheck() {
    // Arrange
    Builder builderResult = CompactionJob.builder();

    // Act
    CheckFileAssignmentsRequest actualCreateInputFileAssignmentsCheckResult =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build()
            .createInputFileAssignmentsCheck();

    // Assert
    assertEquals("42", actualCreateInputFileAssignmentsCheckResult.getJobId());
    assertEquals("42", actualCreateInputFileAssignmentsCheckResult.getPartitionId());
    assertTrue(actualCreateInputFileAssignmentsCheckResult.getFilenames().isEmpty());
  }

  /**
   * Test {@link CompactionJob#createAssignJobIdRequest()}.
   *
   * <p>Method under test: {@link CompactionJob#createAssignJobIdRequest()}
   */
  @Test
  @DisplayName("Test createAssignJobIdRequest()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AssignJobIdRequest CompactionJob.createAssignJobIdRequest()"})
  void testCreateAssignJobIdRequest() {
    // Arrange
    Builder builderResult = CompactionJob.builder();

    // Act
    AssignJobIdRequest actualCreateAssignJobIdRequestResult =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build()
            .createAssignJobIdRequest();

    // Assert
    assertEquals("42", actualCreateAssignJobIdRequestResult.getJobId());
    assertEquals("42", actualCreateAssignJobIdRequestResult.getPartitionId());
    assertTrue(actualCreateAssignJobIdRequestResult.getFilenames().isEmpty());
  }

  /**
   * Test {@link CompactionJob#createCreatedEvent()}.
   *
   * <p>Method under test: {@link CompactionJob#createCreatedEvent()}
   */
  @Test
  @DisplayName("Test createCreatedEvent()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"CompactionJobCreatedEvent CompactionJob.createCreatedEvent()"})
  void testCreateCreatedEvent() {
    // Arrange
    Builder builderResult = CompactionJob.builder();

    // Act
    CompactionJobCreatedEvent actualCreateCreatedEventResult =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build()
            .createCreatedEvent();

    // Assert
    assertEquals("42", actualCreateCreatedEventResult.getJobId());
    assertEquals("42", actualCreateCreatedEventResult.getPartitionId());
    assertEquals("42", actualCreateCreatedEventResult.getTableId());
    assertEquals(0, actualCreateCreatedEventResult.getInputFilesCount());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CompactionJob#committedEventBuilder(Instant)}
   *   <li>{@link CompactionJob#failedEventBuilder(Instant)}
   *   <li>{@link CompactionJob#finishedEventBuilder(JobRunSummary)}
   *   <li>{@link CompactionJob#replaceFileReferencesRequestBuilder(long)}
   *   <li>{@link CompactionJob#startedEventBuilder(Instant)}
   *   <li>{@link CompactionJob#toString()}
   *   <li>{@link CompactionJob#getId()}
   *   <li>{@link CompactionJob#getInputFiles()}
   *   <li>{@link CompactionJob#getIteratorClassName()}
   *   <li>{@link CompactionJob#getIteratorConfig()}
   *   <li>{@link CompactionJob#getOutputFile()}
   *   <li>{@link CompactionJob#getPartitionId()}
   *   <li>{@link CompactionJob#getTableId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "sleeper.core.tracker.compaction.job.update.CompactionJobCommittedEvent.Builder CompactionJob.committedEventBuilder(Instant)",
    "sleeper.core.tracker.compaction.job.update.CompactionJobFailedEvent.Builder CompactionJob.failedEventBuilder(Instant)",
    "sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent.Builder CompactionJob.finishedEventBuilder(JobRunSummary)",
    "String CompactionJob.getId()",
    "List CompactionJob.getInputFiles()",
    "String CompactionJob.getIteratorClassName()",
    "String CompactionJob.getIteratorConfig()",
    "String CompactionJob.getOutputFile()",
    "String CompactionJob.getPartitionId()",
    "String CompactionJob.getTableId()",
    "sleeper.core.statestore.ReplaceFileReferencesRequest.Builder CompactionJob.replaceFileReferencesRequestBuilder(long)",
    "sleeper.core.tracker.compaction.job.update.CompactionJobStartedEvent.Builder CompactionJob.startedEventBuilder(Instant)",
    "String CompactionJob.toString()"
  })
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    ArrayList<String> inputFiles = new ArrayList<>();
    CompactionJob compactionJob =
        builderResult
            .inputFiles(inputFiles)
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    // Act
    compactionJob.committedEventBuilder(
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    compactionJob.failedEventBuilder(
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);
    JobRunTime runTime =
        new JobRunTime(
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            Duration.ofSeconds(1L));
    JobRunSummary summary = new JobRunSummary(recordsProcessed, runTime);
    compactionJob.finishedEventBuilder(summary);
    compactionJob.replaceFileReferencesRequestBuilder(1L);
    compactionJob.startedEventBuilder(
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    String actualToStringResult = compactionJob.toString();
    String actualId = compactionJob.getId();
    List<String> actualInputFiles = compactionJob.getInputFiles();
    String actualIteratorClassName = compactionJob.getIteratorClassName();
    String actualIteratorConfig = compactionJob.getIteratorConfig();
    String actualOutputFile = compactionJob.getOutputFile();
    String actualPartitionId = compactionJob.getPartitionId();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualPartitionId);
    assertEquals("42", compactionJob.getTableId());
    assertEquals(
        "CompactionJob{tableId='42', jobId='42', inputFiles=[], outputFile='Output File', partitionId='42',"
            + " iteratorClassName='null', iteratorConfig='null'}",
        actualToStringResult);
    assertEquals("Output File", actualOutputFile);
    assertNull(actualIteratorClassName);
    assertNull(actualIteratorConfig);
    assertTrue(actualInputFiles.isEmpty());
    assertSame(inputFiles, actualInputFiles);
  }

  /**
   * Test {@link CompactionJob#createOutputFileReference(long)}.
   *
   * <p>Method under test: {@link CompactionJob#createOutputFileReference(long)}
   */
  @Test
  @DisplayName("Test createOutputFileReference(long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"FileReference CompactionJob.createOutputFileReference(long)"})
  void testCreateOutputFileReference() {
    // Arrange
    Builder builderResult = CompactionJob.builder();

    // Act
    FileReference actualCreateOutputFileReferenceResult =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build()
            .createOutputFileReference(1L);

    // Assert
    assertEquals("42", actualCreateOutputFileReferenceResult.getPartitionId());
    assertEquals("Output File", actualCreateOutputFileReferenceResult.getFilename());
    assertNull(actualCreateOutputFileReferenceResult.getJobId());
    assertNull(actualCreateOutputFileReferenceResult.getLastStateStoreUpdateTime());
    assertEquals(1L, actualCreateOutputFileReferenceResult.getNumberOfRecords().longValue());
    assertFalse(actualCreateOutputFileReferenceResult.isCountApproximate());
    assertTrue(actualCreateOutputFileReferenceResult.onlyContainsDataForThisPartition());
  }

  /**
   * Test {@link CompactionJob#equals(Object)}, and {@link CompactionJob#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CompactionJob#equals(Object)}
   *   <li>{@link CompactionJob#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob compactionJob =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    Builder builderResult2 = CompactionJob.builder();
    CompactionJob compactionJob2 =
        builderResult2
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    // Act and Assert
    assertEquals(compactionJob, compactionJob2);
    assertEquals(compactionJob.hashCode(), compactionJob2.hashCode());
  }

  /**
   * Test {@link CompactionJob#equals(Object)}, and {@link CompactionJob#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CompactionJob#equals(Object)}
   *   <li>{@link CompactionJob#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob compactionJob =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    // Act and Assert
    assertEquals(compactionJob, compactionJob);
    int expectedHashCodeResult = compactionJob.hashCode();
    assertEquals(expectedHashCodeResult, compactionJob.hashCode());
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("foo");
    CompactionJob compactionJob =
        CompactionJob.builder()
            .inputFiles(inputFiles)
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    Builder builderResult = CompactionJob.builder();

    // Act and Assert
    assertNotEquals(
        compactionJob,
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob compactionJob =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("Job Id")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    Builder builderResult2 = CompactionJob.builder();

    // Act and Assert
    assertNotEquals(
        compactionJob,
        builderResult2
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob compactionJob =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("42")
            .partitionId("42")
            .tableId("42")
            .build();

    Builder builderResult2 = CompactionJob.builder();

    // Act and Assert
    assertNotEquals(
        compactionJob,
        builderResult2
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob compactionJob =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("Partition Id")
            .tableId("42")
            .build();

    Builder builderResult2 = CompactionJob.builder();

    // Act and Assert
    assertNotEquals(
        compactionJob,
        builderResult2
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob compactionJob =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("Table Id")
            .build();

    Builder builderResult2 = CompactionJob.builder();

    // Act and Assert
    assertNotEquals(
        compactionJob,
        builderResult2
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJob.builder();

    // Act and Assert
    assertNotEquals(
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build(),
        null);
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJob.builder();

    // Act and Assert
    assertNotEquals(
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build(),
        "Different type to CompactionJob");
  }
}
