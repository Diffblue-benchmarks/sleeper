package sleeper.core.tracker.compaction.job.update;

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
import sleeper.core.tracker.compaction.job.update.CompactionJobStartedEvent.Builder;

class CompactionJobStartedEventDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
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
  @MethodsUnderTest({"CompactionJobStartedEvent Builder.build()", "Builder Builder.jobId(String)",
      "Builder Builder.jobRunId(String)", "Builder Builder.startTime(Instant)", "Builder Builder.tableId(String)",
      "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");

    // Act
    CompactionJobStartedEvent actualBuildResult = jobRunIdResult
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
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobStartedEvent#toString()}
   *   <li>{@link CompactionJobStartedEvent#getJobId()}
   *   <li>{@link CompactionJobStartedEvent#getJobRunId()}
   *   <li>{@link CompactionJobStartedEvent#getStartTime()}
   *   <li>{@link CompactionJobStartedEvent#getTableId()}
   *   <li>{@link CompactionJobStartedEvent#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobStartedEvent.getJobId()", "String CompactionJobStartedEvent.getJobRunId()",
      "Instant CompactionJobStartedEvent.getStartTime()", "String CompactionJobStartedEvent.getTableId()",
      "String CompactionJobStartedEvent.getTaskId()", "String CompactionJobStartedEvent.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    Instant actualStartTime = buildResult.getStartTime();
    String actualTableId = buildResult.getTableId();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", actualTableId);
    assertEquals("42", buildResult.getTaskId());
    assertEquals(
        "CompactionJobStartedEvent{jobId=42, tableId=42, taskId=42, jobRunId=42, startTime=1970-01-01T00" + ":00:00Z}",
        actualToStringResult);
    assertSame(actualStartTime.EPOCH, actualStartTime);
  }

  /**
   * Test {@link CompactionJobStartedEvent#equals(Object)}, and {@link CompactionJobStartedEvent#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobStartedEvent#equals(Object)}
   *   <li>{@link CompactionJobStartedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedEvent.equals(Object)", "int CompactionJobStartedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult2 = jobRunIdResult2
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
   * Test {@link CompactionJobStartedEvent#equals(Object)}, and {@link CompactionJobStartedEvent#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobStartedEvent#equals(Object)}
   *   <li>{@link CompactionJobStartedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedEvent.equals(Object)", "int CompactionJobStartedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult = jobRunIdResult
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
   * Test {@link CompactionJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedEvent.equals(Object)", "int CompactionJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("Job Id").jobRunId("42");
    CompactionJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedEvent.equals(Object)", "int CompactionJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("Job Run Id");
    CompactionJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedEvent.equals(Object)", "int CompactionJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedEvent.equals(Object)", "int CompactionJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("Table Id")
        .taskId("42")
        .build();
    Builder jobRunIdResult2 = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedEvent.equals(Object)", "int CompactionJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("Task Id")
        .build();
    Builder jobRunIdResult2 = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult2 = jobRunIdResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedEvent.equals(Object)", "int CompactionJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CompactionJobStartedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedEvent.equals(Object)", "int CompactionJobStartedEvent.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent buildResult = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CompactionJobStartedEvent");
  }
}
