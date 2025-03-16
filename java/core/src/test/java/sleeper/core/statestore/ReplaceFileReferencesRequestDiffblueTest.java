package sleeper.core.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.ReplaceFileReferencesRequest.Builder;

class ReplaceFileReferencesRequestDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#inputFiles(List)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#jobRunId(String)}
   *   <li>{@link Builder#newReference(FileReference)}
   *   <li>{@link Builder#taskId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ReplaceFileReferencesRequest Builder.build()", "Builder Builder.inputFiles(List)",
      "Builder Builder.jobId(String)", "Builder Builder.jobRunId(String)",
      "Builder Builder.newReference(FileReference)", "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    ArrayList<String> inputFiles = new ArrayList<>();
    Builder jobRunIdResult = builderResult.inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    ReplaceFileReferencesRequest actualBuildResult = jobRunIdResult.newReference(newReference).taskId("42").build();

    // Assert
    FileReference newReference2 = actualBuildResult.getNewReference();
    assertEquals("42", newReference2.getJobId());
    assertEquals("42", newReference2.getPartitionId());
    assertEquals("42", actualBuildResult.getJobId());
    assertEquals("42", actualBuildResult.getJobRunId());
    assertEquals("42", actualBuildResult.getPartitionId());
    assertEquals("42", actualBuildResult.getTaskId());
    assertEquals("foo.txt", newReference2.getFilename());
    Instant lastStateStoreUpdateTime = newReference2.getLastStateStoreUpdateTime();
    assertEquals(0, lastStateStoreUpdateTime.getNano());
    assertEquals(0L, lastStateStoreUpdateTime.getEpochSecond());
    assertEquals(1L, newReference2.getNumberOfRecords().longValue());
    List<String> inputFiles2 = actualBuildResult.getInputFiles();
    assertTrue(inputFiles2.isEmpty());
    assertTrue(newReference2.isCountApproximate());
    assertTrue(newReference2.onlyContainsDataForThisPartition());
    assertSame(inputFiles, inputFiles2);
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#replaceJobFileReferences(String, List, FileReference)}.
   * <ul>
   *   <li>Given {@code inputFiles must not be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#replaceJobFileReferences(String, List, FileReference)}
   */
  @Test
  @DisplayName("Test replaceJobFileReferences(String, List, FileReference); given 'inputFiles must not be null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "ReplaceFileReferencesRequest ReplaceFileReferencesRequest.replaceJobFileReferences(String, List, FileReference)"})
  void testReplaceJobFileReferences_givenInputFilesMustNotBeNull() {
    // Arrange
    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("inputFiles must not be null");
    inputFiles.add("jobId must not be null");
    FileReference newReference = mock(FileReference.class);

    // Act
    ReplaceFileReferencesRequest actualReplaceJobFileReferencesResult = ReplaceFileReferencesRequest
        .replaceJobFileReferences("42", inputFiles, newReference);

    // Assert
    assertEquals("42", actualReplaceJobFileReferencesResult.getJobId());
    assertNull(actualReplaceJobFileReferencesResult.getJobRunId());
    assertNull(actualReplaceJobFileReferencesResult.getPartitionId());
    assertNull(actualReplaceJobFileReferencesResult.getTaskId());
    assertSame(inputFiles, actualReplaceJobFileReferencesResult.getInputFiles());
    assertSame(newReference, actualReplaceJobFileReferencesResult.getNewReference());
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#replaceJobFileReferences(String, List, FileReference)}.
   * <ul>
   *   <li>Then return InputFiles is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#replaceJobFileReferences(String, List, FileReference)}
   */
  @Test
  @DisplayName("Test replaceJobFileReferences(String, List, FileReference); then return InputFiles is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "ReplaceFileReferencesRequest ReplaceFileReferencesRequest.replaceJobFileReferences(String, List, FileReference)"})
  void testReplaceJobFileReferences_thenReturnInputFilesIsArrayList() {
    // Arrange
    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("jobId must not be null");
    FileReference newReference = mock(FileReference.class);

    // Act
    ReplaceFileReferencesRequest actualReplaceJobFileReferencesResult = ReplaceFileReferencesRequest
        .replaceJobFileReferences("42", inputFiles, newReference);

    // Assert
    assertEquals("42", actualReplaceJobFileReferencesResult.getJobId());
    assertNull(actualReplaceJobFileReferencesResult.getJobRunId());
    assertNull(actualReplaceJobFileReferencesResult.getPartitionId());
    assertNull(actualReplaceJobFileReferencesResult.getTaskId());
    assertSame(inputFiles, actualReplaceJobFileReferencesResult.getInputFiles());
    assertSame(newReference, actualReplaceJobFileReferencesResult.getNewReference());
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#replaceJobFileReferences(String, List, FileReference)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return InputFiles Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#replaceJobFileReferences(String, List, FileReference)}
   */
  @Test
  @DisplayName("Test replaceJobFileReferences(String, List, FileReference); when ArrayList(); then return InputFiles Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "ReplaceFileReferencesRequest ReplaceFileReferencesRequest.replaceJobFileReferences(String, List, FileReference)"})
  void testReplaceJobFileReferences_whenArrayList_thenReturnInputFilesEmpty() {
    // Arrange
    FileReference newReference = mock(FileReference.class);

    // Act
    ReplaceFileReferencesRequest actualReplaceJobFileReferencesResult = ReplaceFileReferencesRequest
        .replaceJobFileReferences("42", new ArrayList<>(), newReference);

    // Assert
    assertEquals("42", actualReplaceJobFileReferencesResult.getJobId());
    assertNull(actualReplaceJobFileReferencesResult.getJobRunId());
    assertNull(actualReplaceJobFileReferencesResult.getPartitionId());
    assertNull(actualReplaceJobFileReferencesResult.getTaskId());
    assertTrue(actualReplaceJobFileReferencesResult.getInputFiles().isEmpty());
    assertSame(newReference, actualReplaceJobFileReferencesResult.getNewReference());
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#withNoUpdateTime()}.
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#withNoUpdateTime()}
   */
  @Test
  @DisplayName("Test withNoUpdateTime()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ReplaceFileReferencesRequest ReplaceFileReferencesRequest.withNoUpdateTime()"})
  void testWithNoUpdateTime() {
    // Arrange
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

    // Act
    ReplaceFileReferencesRequest actualWithNoUpdateTimeResult = buildResult.withNoUpdateTime();

    // Assert
    FileReference newReference2 = actualWithNoUpdateTimeResult.getNewReference();
    assertEquals("42", newReference2.getJobId());
    assertEquals("42", newReference2.getPartitionId());
    assertEquals("42", actualWithNoUpdateTimeResult.getJobId());
    assertEquals("42", actualWithNoUpdateTimeResult.getJobRunId());
    assertEquals("42", actualWithNoUpdateTimeResult.getPartitionId());
    assertEquals("42", actualWithNoUpdateTimeResult.getTaskId());
    assertEquals("foo.txt", newReference2.getFilename());
    assertNull(newReference2.getLastStateStoreUpdateTime());
    assertEquals(1L, newReference2.getNumberOfRecords().longValue());
    assertTrue(actualWithNoUpdateTimeResult.getInputFiles().isEmpty());
    assertTrue(newReference2.isCountApproximate());
    assertTrue(newReference2.onlyContainsDataForThisPartition());
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#getPartitionId()}.
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#getPartitionId()}
   */
  @Test
  @DisplayName("Test getPartitionId()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ReplaceFileReferencesRequest.getPartitionId()"})
  void testGetPartitionId() {
    // Arrange
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

    // Act and Assert
    assertEquals("42", buildResult.getPartitionId());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReplaceFileReferencesRequest#toString()}
   *   <li>{@link ReplaceFileReferencesRequest#getInputFiles()}
   *   <li>{@link ReplaceFileReferencesRequest#getJobId()}
   *   <li>{@link ReplaceFileReferencesRequest#getJobRunId()}
   *   <li>{@link ReplaceFileReferencesRequest#getNewReference()}
   *   <li>{@link ReplaceFileReferencesRequest#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReplaceFileReferencesRequest.getInputFiles()",
      "String ReplaceFileReferencesRequest.getJobId()", "String ReplaceFileReferencesRequest.getJobRunId()",
      "FileReference ReplaceFileReferencesRequest.getNewReference()", "String ReplaceFileReferencesRequest.getTaskId()",
      "String ReplaceFileReferencesRequest.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    ArrayList<String> inputFiles = new ArrayList<>();
    Builder jobRunIdResult = builderResult.inputFiles(inputFiles).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();

    // Act
    String actualToStringResult = buildResult.toString();
    List<String> actualInputFiles = buildResult.getInputFiles();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    FileReference actualNewReference = buildResult.getNewReference();
    String actualTaskId = buildResult.getTaskId();

    // Assert
    assertEquals("42", actualNewReference.getJobId());
    assertEquals("42", actualNewReference.getPartitionId());
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", actualTaskId);
    assertEquals(
        "ReplaceFileReferencesRequest{jobId=42, taskId=42, jobRunId=42, inputFiles=[], newReference=FileReference"
            + "{filename='foo.txt', partitionId='42', numberOfRecords=1, jobId='42', lastStateStoreUpdateTime=1970"
            + "-01-01T00:00:00Z, countApproximate=true, onlyContainsDataForThisPartition=true}}",
        actualToStringResult);
    assertEquals("foo.txt", actualNewReference.getFilename());
    Instant lastStateStoreUpdateTime = actualNewReference.getLastStateStoreUpdateTime();
    assertEquals(0, lastStateStoreUpdateTime.getNano());
    assertEquals(0L, lastStateStoreUpdateTime.getEpochSecond());
    assertEquals(1L, actualNewReference.getNumberOfRecords().longValue());
    assertTrue(actualInputFiles.isEmpty());
    assertTrue(actualNewReference.isCountApproximate());
    assertTrue(actualNewReference.onlyContainsDataForThisPartition());
    assertSame(inputFiles, actualInputFiles);
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#equals(Object)}, and {@link ReplaceFileReferencesRequest#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReplaceFileReferencesRequest#equals(Object)}
   *   <li>{@link ReplaceFileReferencesRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesRequest.equals(Object)",
      "int ReplaceFileReferencesRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
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

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#equals(Object)}, and {@link ReplaceFileReferencesRequest#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReplaceFileReferencesRequest#equals(Object)}
   *   <li>{@link ReplaceFileReferencesRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesRequest.equals(Object)",
      "int ReplaceFileReferencesRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
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

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesRequest.equals(Object)",
      "int ReplaceFileReferencesRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
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
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult2 = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult2 = jobRunIdResult2.newReference(newReference2).taskId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesRequest.equals(Object)",
      "int ReplaceFileReferencesRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("Job Id").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
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

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesRequest.equals(Object)",
      "int ReplaceFileReferencesRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("Job Run Id");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
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

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesRequest.equals(Object)",
      "int ReplaceFileReferencesRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(false).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
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

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesRequest.equals(Object)",
      "int ReplaceFileReferencesRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("Task Id").build();
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

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesRequest.equals(Object)",
      "int ReplaceFileReferencesRequest.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
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

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link ReplaceFileReferencesRequest#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReplaceFileReferencesRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReplaceFileReferencesRequest.equals(Object)",
      "int ReplaceFileReferencesRequest.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
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

    // Act and Assert
    assertNotEquals(buildResult, "Different type to ReplaceFileReferencesRequest");
  }
}
