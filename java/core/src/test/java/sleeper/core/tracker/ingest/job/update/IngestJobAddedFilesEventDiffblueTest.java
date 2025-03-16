package sleeper.core.tracker.ingest.job.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import sleeper.core.tracker.ingest.job.update.IngestJobAddedFilesEvent.Builder;

class IngestJobAddedFilesEventDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#jobRunId(String)}
   *   <li>{@link Builder#tableId(String)}
   *   <li>{@link Builder#taskId(String)}
   *   <li>{@link Builder#writtenTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Builder.<init>()", "IngestJobAddedFilesEvent Builder.build()",
      "Builder Builder.jobId(String)", "Builder Builder.jobRunId(String)", "Builder Builder.tableId(String)",
      "Builder Builder.taskId(String)", "Builder Builder.writtenTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").tableId("42").taskId("42");

    // Act
    IngestJobAddedFilesEvent actualBuildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getJobId());
    assertEquals("42", actualBuildResult.getJobRunId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("42", actualBuildResult.getTaskId());
    Instant writtenTime = actualBuildResult.getWrittenTime();
    assertEquals(0, writtenTime.getNano());
    assertEquals(0, actualBuildResult.getFileCount());
    assertEquals(0L, writtenTime.getEpochSecond());
  }

  /**
   * Test Builder {@link Builder#files(List)}.
   * <p>
   * Method under test: {@link Builder#files(List)}
   */
  @Test
  @DisplayName("Test Builder files(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.files(List)"})
  void testBuilderFiles() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act and Assert
    assertSame(builderResult, builderResult.files(files));
  }

  /**
   * Test Builder {@link Builder#files(List)}.
   * <p>
   * Method under test: {@link Builder#files(List)}
   */
  @Test
  @DisplayName("Test Builder files(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.files(List)"})
  void testBuilderFiles2() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();

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
    assertSame(builderResult, builderResult.files(files));
  }

  /**
   * Test Builder {@link Builder#files(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#files(List)}
   */
  @Test
  @DisplayName("Test Builder files(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.files(List)"})
  void testBuilderFiles_whenArrayList() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.files(new ArrayList<>()));
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
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    IngestJobRunIds jobRunIds = IngestJobRunIds.builder().jobId("42").jobRunId("42").tableId("42").taskId("42").build();

    // Act and Assert
    assertSame(builderResult, builderResult.jobRunIds(jobRunIds));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobAddedFilesEvent#toString()}
   *   <li>{@link IngestJobAddedFilesEvent#getFileCount()}
   *   <li>{@link IngestJobAddedFilesEvent#getJobId()}
   *   <li>{@link IngestJobAddedFilesEvent#getJobRunId()}
   *   <li>{@link IngestJobAddedFilesEvent#getTableId()}
   *   <li>{@link IngestJobAddedFilesEvent#getTaskId()}
   *   <li>{@link IngestJobAddedFilesEvent#getWrittenTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IngestJobAddedFilesEvent.getFileCount()", "String IngestJobAddedFilesEvent.getJobId()",
      "String IngestJobAddedFilesEvent.getJobRunId()", "String IngestJobAddedFilesEvent.getTableId()",
      "String IngestJobAddedFilesEvent.getTaskId()", "Instant IngestJobAddedFilesEvent.getWrittenTime()",
      "String IngestJobAddedFilesEvent.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").tableId("42").taskId("42");
    IngestJobAddedFilesEvent buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    int actualFileCount = buildResult.getFileCount();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    String actualTableId = buildResult.getTableId();
    String actualTaskId = buildResult.getTaskId();
    Instant actualWrittenTime = buildResult.getWrittenTime();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", actualTableId);
    assertEquals("42", actualTaskId);
    assertEquals(
        "IngestJobAddedFilesEvent{jobId=42, tableId=42, jobRunId=42, taskId=42, writtenTime=1970-01-01T00:00:00Z,"
            + " fileCount=0}",
        actualToStringResult);
    assertEquals(0, actualFileCount);
    assertSame(actualWrittenTime.EPOCH, actualWrittenTime);
  }

  /**
   * Test {@link IngestJobAddedFilesEvent#equals(Object)}, and {@link IngestJobAddedFilesEvent#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobAddedFilesEvent#equals(Object)}
   *   <li>{@link IngestJobAddedFilesEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesEvent.equals(Object)", "int IngestJobAddedFilesEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").tableId("42").taskId("42");
    IngestJobAddedFilesEvent buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestJobAddedFilesEvent#equals(Object)}, and {@link IngestJobAddedFilesEvent#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobAddedFilesEvent#equals(Object)}
   *   <li>{@link IngestJobAddedFilesEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesEvent.equals(Object)", "int IngestJobAddedFilesEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").tableId("42").taskId("42");
    IngestJobAddedFilesEvent buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestJobAddedFilesEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesEvent.equals(Object)", "int IngestJobAddedFilesEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    Builder taskIdResult = IngestJobAddedFilesEvent.builder()
        .files(files)
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult2 = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult2 = builderResult.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult3 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult2, buildResult3);
  }

  /**
   * Test {@link IngestJobAddedFilesEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesEvent.equals(Object)", "int IngestJobAddedFilesEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>())
        .jobId("Job Id")
        .jobRunId("42")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobAddedFilesEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesEvent.equals(Object)", "int IngestJobAddedFilesEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("Job Run Id")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobAddedFilesEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesEvent.equals(Object)", "int IngestJobAddedFilesEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .tableId("Table Id")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobAddedFilesEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesEvent.equals(Object)", "int IngestJobAddedFilesEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("Task Id");
    IngestJobAddedFilesEvent buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobAddedFilesEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesEvent.equals(Object)", "int IngestJobAddedFilesEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").tableId("42").taskId("42");
    IngestJobAddedFilesEvent buildResult = taskIdResult
        .writtenTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult2 = builderResult2.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent buildResult2 = taskIdResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobAddedFilesEvent#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesEvent.equals(Object)", "int IngestJobAddedFilesEvent.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").tableId("42").taskId("42");
    IngestJobAddedFilesEvent buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestJobAddedFilesEvent#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesEvent.equals(Object)", "int IngestJobAddedFilesEvent.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = IngestJobAddedFilesEvent.builder();
    Builder taskIdResult = builderResult.files(new ArrayList<>()).jobId("42").jobRunId("42").tableId("42").taskId("42");
    IngestJobAddedFilesEvent buildResult = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJobAddedFilesEvent");
  }
}
