package sleeper.ingest.core.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import sleeper.core.tracker.ingest.job.update.IngestJobValidatedEvent;
import sleeper.ingest.core.job.IngestJob.Builder;

class IngestJobDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#files(List)}
   *   <li>{@link Builder#id(String)}
   *   <li>{@link Builder#tableId(String)}
   *   <li>{@link Builder#tableName(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJob Builder.build()", "Builder Builder.files(List)", "Builder Builder.id(String)",
      "Builder Builder.tableId(String)", "Builder Builder.tableName(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    ArrayList<String> files = new ArrayList<>();

    // Act
    IngestJob actualBuildResult = builderResult.files(files).id("42").tableId("42").tableName("Table Name").build();

    // Assert
    assertEquals("42", actualBuildResult.getId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("Table Name", actualBuildResult.getTableName());
    assertEquals(0, actualBuildResult.getFileCount());
    List<String> files2 = actualBuildResult.getFiles();
    assertTrue(files2.isEmpty());
    assertSame(files, files2);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJob#acceptedEventBuilder(Instant)}
   *   <li>{@link IngestJob#addedFilesEventBuilder()}
   *   <li>{@link IngestJob#addedFilesEventBuilder(Instant)}
   *   <li>{@link IngestJob#failedEventBuilder(Instant)}
   *   <li>{@link IngestJob#startedAfterValidationEventBuilder(Instant)}
   *   <li>{@link IngestJob#startedEventBuilder(Instant)}
   *   <li>{@link IngestJob#toString()}
   *   <li>{@link IngestJob#getFiles()}
   *   <li>{@link IngestJob#getId()}
   *   <li>{@link IngestJob#getTableId()}
   *   <li>{@link IngestJob#getTableName()}
   *   <li>{@link IngestJob#toBuilder()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobValidatedEvent.Builder IngestJob.acceptedEventBuilder(Instant)",
      "sleeper.core.tracker.ingest.job.update.IngestJobAddedFilesEvent.Builder IngestJob.addedFilesEventBuilder()",
      "sleeper.core.tracker.ingest.job.update.IngestJobAddedFilesEvent.Builder IngestJob.addedFilesEventBuilder(Instant)",
      "sleeper.core.tracker.ingest.job.update.IngestJobFailedEvent.Builder IngestJob.failedEventBuilder(Instant)",
      "sleeper.core.tracker.ingest.job.update.IngestJobFinishedEvent.Builder IngestJob.finishedEventBuilder(sleeper.core.tracker.job.run.JobRunSummary)",
      "List IngestJob.getFiles()", "String IngestJob.getId()", "String IngestJob.getTableId()",
      "String IngestJob.getTableName()",
      "sleeper.core.tracker.ingest.job.update.IngestJobStartedEvent.Builder IngestJob.startedAfterValidationEventBuilder(Instant)",
      "sleeper.core.tracker.ingest.job.update.IngestJobStartedEvent.Builder IngestJob.startedEventBuilder(Instant)",
      "Builder IngestJob.toBuilder()", "String IngestJob.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    ArrayList<String> files = new ArrayList<>();
    IngestJob buildResult = builderResult.files(files).id("42").tableId("42").tableName("Table Name").build();

    // Act
    buildResult.acceptedEventBuilder(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    buildResult.addedFilesEventBuilder();
    buildResult.addedFilesEventBuilder(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    buildResult.failedEventBuilder(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    buildResult
        .startedAfterValidationEventBuilder(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    buildResult.startedEventBuilder(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    String actualToStringResult = buildResult.toString();
    List<String> actualFiles = buildResult.getFiles();
    String actualId = buildResult.getId();
    String actualTableId = buildResult.getTableId();
    String actualTableName = buildResult.getTableName();
    buildResult.toBuilder();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualTableId);
    assertEquals("IngestJob{id='42', tableName='Table Name', tableId='42', files=[]}", actualToStringResult);
    assertEquals("Table Name", actualTableName);
    assertTrue(actualFiles.isEmpty());
    assertSame(files, actualFiles);
  }

  /**
   * Test {@link IngestJob#createRejectedEvent(Instant, List)}.
   * <p>
   * Method under test: {@link IngestJob#createRejectedEvent(Instant, List)}
   */
  @Test
  @DisplayName("Test createRejectedEvent(Instant, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobValidatedEvent IngestJob.createRejectedEvent(Instant, List)"})
  void testCreateRejectedEvent() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    IngestJobValidatedEvent actualCreateRejectedEventResult = buildResult.createRejectedEvent(validationTime,
        new ArrayList<>());

    // Assert
    assertEquals("42", actualCreateRejectedEventResult.getJobId());
    assertEquals("42", actualCreateRejectedEventResult.getTableId());
    assertNull(actualCreateRejectedEventResult.getTaskId());
    assertNull(actualCreateRejectedEventResult.getJobRunId());
    assertNull(actualCreateRejectedEventResult.getJsonMessage());
    assertEquals(0, actualCreateRejectedEventResult.getFileCount());
    assertTrue(actualCreateRejectedEventResult.getReasons().isEmpty());
    assertTrue(actualCreateRejectedEventResult.isAccepted());
    Instant expectedValidationTime = validationTime.EPOCH;
    assertSame(expectedValidationTime, actualCreateRejectedEventResult.getValidationTime());
  }

  /**
   * Test {@link IngestJob#getFileCount()}.
   * <p>
   * Method under test: {@link IngestJob#getFileCount()}
   */
  @Test
  @DisplayName("Test getFileCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IngestJob.getFileCount()"})
  void testGetFileCount() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(0, buildResult.getFileCount());
  }

  /**
   * Test {@link IngestJob#equals(Object)}, and {@link IngestJob#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJob#equals(Object)}
   *   <li>{@link IngestJob#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJob.equals(Object)", "int IngestJob.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Builder builderResult2 = IngestJob.builder();
    IngestJob buildResult2 = builderResult2.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestJob#equals(Object)}, and {@link IngestJob#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJob#equals(Object)}
   *   <li>{@link IngestJob#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJob.equals(Object)", "int IngestJob.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJob.equals(Object)", "int IngestJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");
    IngestJob buildResult = IngestJob.builder().files(files).id("42").tableId("42").tableName("Table Name").build();
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult2 = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJob.equals(Object)", "int IngestJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("Id")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Builder builderResult2 = IngestJob.builder();
    IngestJob buildResult2 = builderResult2.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJob.equals(Object)", "int IngestJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("Table Id")
        .tableName("Table Name")
        .build();
    Builder builderResult2 = IngestJob.builder();
    IngestJob buildResult2 = builderResult2.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJob.equals(Object)", "int IngestJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>()).id("42").tableId("42").tableName(null).build();
    Builder builderResult2 = IngestJob.builder();
    IngestJob buildResult2 = builderResult2.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJob#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJob.equals(Object)", "int IngestJob.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestJob#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJob.equals(Object)", "int IngestJob.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJob");
  }
}
