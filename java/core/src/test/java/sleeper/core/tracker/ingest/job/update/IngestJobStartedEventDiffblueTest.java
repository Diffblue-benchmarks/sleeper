package sleeper.core.tracker.ingest.job.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.ingest.job.update.IngestJobStartedEvent.Builder;

class IngestJobStartedEventDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#fileCount(int)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#jobRunId(String)}
   *   <li>{@link Builder#startTime(Instant)}
   *   <li>{@link Builder#tableId(String)}
   *   <li>{@link Builder#taskId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobStartedEvent Builder.build()", "Builder Builder.fileCount(int)",
      "Builder Builder.jobId(String)", "Builder Builder.jobRunId(String)", "Builder Builder.startTime(Instant)",
      "Builder Builder.tableId(String)", "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");

    // Act
    IngestJobStartedEvent actualBuildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getJobId());
    assertEquals("42", actualBuildResult.getJobRunId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("42", actualBuildResult.getTaskId());
    Instant startTime = actualBuildResult.getStartTime();
    assertEquals(0, startTime.getNano());
    assertEquals(0L, startTime.getEpochSecond());
    assertEquals(3, actualBuildResult.getFileCount());
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
    Builder builderResult = IngestJobStartedEvent.builder();
    IngestJobRunIds jobRunIds = IngestJobRunIds.builder().jobId("42").jobRunId("42").tableId("42").taskId("42").build();

    // Act and Assert
    assertSame(builderResult, builderResult.jobRunIds(jobRunIds));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobStartedEvent#toString()}
   *   <li>{@link IngestJobStartedEvent#getFileCount()}
   *   <li>{@link IngestJobStartedEvent#getJobId()}
   *   <li>{@link IngestJobStartedEvent#getJobRunId()}
   *   <li>{@link IngestJobStartedEvent#getStartTime()}
   *   <li>{@link IngestJobStartedEvent#getTableId()}
   *   <li>{@link IngestJobStartedEvent#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IngestJobStartedEvent.getFileCount()", "String IngestJobStartedEvent.getJobId()",
      "String IngestJobStartedEvent.getJobRunId()", "Instant IngestJobStartedEvent.getStartTime()",
      "String IngestJobStartedEvent.getTableId()", "String IngestJobStartedEvent.getTaskId()",
      "String IngestJobStartedEvent.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    int actualFileCount = buildResult.getFileCount();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    Instant actualStartTime = buildResult.getStartTime();
    String actualTableId = buildResult.getTableId();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", actualTableId);
    assertEquals("42", buildResult.getTaskId());
    assertEquals("IngestJobStartedEvent{jobId='42', tableId='42', fileCount=3, jobRunId='42', taskId='42', startTime"
        + "=1970-01-01T00:00:00Z}", actualToStringResult);
    assertEquals(3, actualFileCount);
    assertSame(actualStartTime.EPOCH, actualStartTime);
  }

  /**
   * Test {@link IngestJobStartedEvent#equals(Object)}, and {@link IngestJobStartedEvent#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobStartedEvent#equals(Object)}
   *   <li>{@link IngestJobStartedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedEvent.equals(Object)", "int IngestJobStartedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestJobStartedEvent#equals(Object)}, and {@link IngestJobStartedEvent#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobStartedEvent#equals(Object)}
   *   <li>{@link IngestJobStartedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedEvent.equals(Object)", "int IngestJobStartedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedEvent.equals(Object)", "int IngestJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(1).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedEvent.equals(Object)", "int IngestJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("Job Id").jobRunId("42");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedEvent.equals(Object)", "int IngestJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("Job Run Id");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedEvent.equals(Object)", "int IngestJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedEvent.equals(Object)", "int IngestJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("Table Id")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedEvent.equals(Object)", "int IngestJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("Task Id")
        .build();
    Builder jobRunIdResult2 = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedEvent.equals(Object)", "int IngestJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedEvent.equals(Object)", "int IngestJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder jobRunIdResult = IngestJobStartedEvent.builder().fileCount(3).jobId("42").jobRunId("42");
    IngestJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJobStartedEvent");
  }
}
