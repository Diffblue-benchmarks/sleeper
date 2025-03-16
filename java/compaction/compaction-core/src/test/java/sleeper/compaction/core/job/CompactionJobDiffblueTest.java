package sleeper.compaction.core.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import sleeper.compaction.core.job.CompactionJob.Builder;
import sleeper.core.statestore.AssignJobIdRequest;
import sleeper.core.statestore.CheckFileAssignmentsRequest;
import sleeper.core.statestore.FileReference;
import sleeper.core.tracker.compaction.job.update.CompactionJobCreatedEvent;

class CompactionJobDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJob Builder.build()", "Builder Builder.inputFiles(List)",
      "Builder Builder.iteratorClassName(String)", "Builder Builder.iteratorConfig(String)",
      "Builder Builder.jobId(String)", "Builder Builder.outputFile(String)", "Builder Builder.partitionId(String)",
      "Builder Builder.tableId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    ArrayList<String> inputFiles = new ArrayList<>();

    // Act
    CompactionJob actualBuildResult = builderResult.inputFiles(inputFiles)
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getId());
    assertEquals("42", actualBuildResult.getPartitionId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("Output File", actualBuildResult.getOutputFile());
    assertNull(actualBuildResult.getIteratorClassName());
    assertNull(actualBuildResult.getIteratorConfig());
    List<String> inputFiles2 = actualBuildResult.getInputFiles();
    assertTrue(inputFiles2.isEmpty());
    assertSame(inputFiles, inputFiles2);
  }

  /**
   * Test {@link CompactionJob#createInputFileAssignmentsCheck()}.
   * <p>
   * Method under test: {@link CompactionJob#createInputFileAssignmentsCheck()}
   */
  @Test
  @DisplayName("Test createInputFileAssignmentsCheck()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CheckFileAssignmentsRequest CompactionJob.createInputFileAssignmentsCheck()"})
  void testCreateInputFileAssignmentsCheck() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act
    CheckFileAssignmentsRequest actualCreateInputFileAssignmentsCheckResult = buildResult
        .createInputFileAssignmentsCheck();

    // Assert
    assertEquals("42", actualCreateInputFileAssignmentsCheckResult.getJobId());
    assertEquals("42", actualCreateInputFileAssignmentsCheckResult.getPartitionId());
    assertTrue(actualCreateInputFileAssignmentsCheckResult.getFilenames().isEmpty());
  }

  /**
   * Test {@link CompactionJob#createAssignJobIdRequest()}.
   * <p>
   * Method under test: {@link CompactionJob#createAssignJobIdRequest()}
   */
  @Test
  @DisplayName("Test createAssignJobIdRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssignJobIdRequest CompactionJob.createAssignJobIdRequest()"})
  void testCreateAssignJobIdRequest() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act
    AssignJobIdRequest actualCreateAssignJobIdRequestResult = buildResult.createAssignJobIdRequest();

    // Assert
    assertEquals("42", actualCreateAssignJobIdRequestResult.getJobId());
    assertEquals("42", actualCreateAssignJobIdRequestResult.getPartitionId());
    assertTrue(actualCreateAssignJobIdRequestResult.getFilenames().isEmpty());
  }

  /**
   * Test {@link CompactionJob#createCreatedEvent()}.
   * <p>
   * Method under test: {@link CompactionJob#createCreatedEvent()}
   */
  @Test
  @DisplayName("Test createCreatedEvent()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobCreatedEvent CompactionJob.createCreatedEvent()"})
  void testCreateCreatedEvent() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act
    CompactionJobCreatedEvent actualCreateCreatedEventResult = buildResult.createCreatedEvent();

    // Assert
    assertEquals("42", actualCreateCreatedEventResult.getJobId());
    assertEquals("42", actualCreateCreatedEventResult.getPartitionId());
    assertEquals("42", actualCreateCreatedEventResult.getTableId());
    assertEquals(0, actualCreateCreatedEventResult.getInputFilesCount());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJob#committedEventBuilder(Instant)}
   *   <li>{@link CompactionJob#failedEventBuilder(Instant)}
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.update.CompactionJobCommittedEvent.Builder CompactionJob.committedEventBuilder(Instant)",
      "sleeper.core.tracker.compaction.job.update.CompactionJobFailedEvent.Builder CompactionJob.failedEventBuilder(Instant)",
      "sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent.Builder CompactionJob.finishedEventBuilder(sleeper.core.tracker.job.run.JobRunSummary)",
      "String CompactionJob.getId()", "List CompactionJob.getInputFiles()",
      "String CompactionJob.getIteratorClassName()", "String CompactionJob.getIteratorConfig()",
      "String CompactionJob.getOutputFile()", "String CompactionJob.getPartitionId()",
      "String CompactionJob.getTableId()",
      "sleeper.core.statestore.ReplaceFileReferencesRequest.Builder CompactionJob.replaceFileReferencesRequestBuilder(long)",
      "sleeper.core.tracker.compaction.job.update.CompactionJobStartedEvent.Builder CompactionJob.startedEventBuilder(Instant)",
      "String CompactionJob.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    ArrayList<String> inputFiles = new ArrayList<>();
    CompactionJob buildResult = builderResult.inputFiles(inputFiles)
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act
    buildResult.committedEventBuilder(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    buildResult.failedEventBuilder(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    buildResult.replaceFileReferencesRequestBuilder(1L);
    buildResult.startedEventBuilder(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    String actualToStringResult = buildResult.toString();
    String actualId = buildResult.getId();
    List<String> actualInputFiles = buildResult.getInputFiles();
    String actualIteratorClassName = buildResult.getIteratorClassName();
    String actualIteratorConfig = buildResult.getIteratorConfig();
    String actualOutputFile = buildResult.getOutputFile();
    String actualPartitionId = buildResult.getPartitionId();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualPartitionId);
    assertEquals("42", buildResult.getTableId());
    assertEquals("CompactionJob{tableId='42', jobId='42', inputFiles=[], outputFile='Output File', partitionId='42',"
        + " iteratorClassName='null', iteratorConfig='null'}", actualToStringResult);
    assertEquals("Output File", actualOutputFile);
    assertNull(actualIteratorClassName);
    assertNull(actualIteratorConfig);
    assertTrue(actualInputFiles.isEmpty());
    assertSame(inputFiles, actualInputFiles);
  }

  /**
   * Test {@link CompactionJob#createOutputFileReference(long)}.
   * <p>
   * Method under test: {@link CompactionJob#createOutputFileReference(long)}
   */
  @Test
  @DisplayName("Test createOutputFileReference(long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReference CompactionJob.createOutputFileReference(long)"})
  void testCreateOutputFileReference() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act
    FileReference actualCreateOutputFileReferenceResult = buildResult.createOutputFileReference(1L);

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
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJob#equals(Object)}
   *   <li>{@link CompactionJob#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    Builder builderResult2 = CompactionJob.builder();
    CompactionJob buildResult2 = builderResult2.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link CompactionJob#equals(Object)}, and {@link CompactionJob#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJob#equals(Object)}
   *   <li>{@link CompactionJob#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("foo");
    CompactionJob buildResult = CompactionJob.builder()
        .inputFiles(inputFiles)
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult2 = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("Job Id")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    Builder builderResult2 = CompactionJob.builder();
    CompactionJob buildResult2 = builderResult2.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("42")
        .partitionId("42")
        .tableId("42")
        .build();
    Builder builderResult2 = CompactionJob.builder();
    CompactionJob buildResult2 = builderResult2.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("Partition Id")
        .tableId("42")
        .build();
    Builder builderResult2 = CompactionJob.builder();
    CompactionJob buildResult2 = builderResult2.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("Table Id")
        .build();
    Builder builderResult2 = CompactionJob.builder();
    CompactionJob buildResult2 = builderResult2.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CompactionJob#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJob.equals(Object)", "int CompactionJob.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CompactionJob");
  }
}
