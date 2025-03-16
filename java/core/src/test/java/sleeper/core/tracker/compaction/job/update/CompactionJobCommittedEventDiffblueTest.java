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
import sleeper.core.tracker.compaction.job.update.CompactionJobCommittedEvent.Builder;

class CompactionJobCommittedEventDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#commitTime(Instant)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#jobRunId(String)}
   *   <li>{@link Builder#tableId(String)}
   *   <li>{@link Builder#taskId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobCommittedEvent Builder.build()", "Builder Builder.commitTime(Instant)",
      "Builder Builder.jobId(String)", "Builder Builder.jobRunId(String)", "Builder Builder.tableId(String)",
      "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();

    // Act
    CompactionJobCommittedEvent actualBuildResult = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getJobId());
    assertEquals("42", actualBuildResult.getJobRunId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("42", actualBuildResult.getTaskId());
    Instant commitTime = actualBuildResult.getCommitTime();
    assertEquals(0, commitTime.getNano());
    assertEquals(0L, commitTime.getEpochSecond());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobCommittedEvent#toString()}
   *   <li>{@link CompactionJobCommittedEvent#getCommitTime()}
   *   <li>{@link CompactionJobCommittedEvent#getJobId()}
   *   <li>{@link CompactionJobCommittedEvent#getJobRunId()}
   *   <li>{@link CompactionJobCommittedEvent#getTableId()}
   *   <li>{@link CompactionJobCommittedEvent#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant CompactionJobCommittedEvent.getCommitTime()",
      "String CompactionJobCommittedEvent.getJobId()", "String CompactionJobCommittedEvent.getJobRunId()",
      "String CompactionJobCommittedEvent.getTableId()", "String CompactionJobCommittedEvent.getTaskId()",
      "String CompactionJobCommittedEvent.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    Instant actualCommitTime = buildResult.getCommitTime();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    String actualTableId = buildResult.getTableId();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", actualTableId);
    assertEquals("42", buildResult.getTaskId());
    assertEquals("CompactionJobCommittedEvent{jobId=42, tableId=42, taskId=42, jobRunId=42, commitTime=1970-01-01T00"
        + ":00:00Z}", actualToStringResult);
    assertSame(actualCommitTime.EPOCH, actualCommitTime);
  }

  /**
   * Test {@link CompactionJobCommittedEvent#equals(Object)}, and {@link CompactionJobCommittedEvent#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobCommittedEvent#equals(Object)}
   *   <li>{@link CompactionJobCommittedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedEvent.equals(Object)",
      "int CompactionJobCommittedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult2 = builderResult2
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link CompactionJobCommittedEvent#equals(Object)}, and {@link CompactionJobCommittedEvent#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobCommittedEvent#equals(Object)}
   *   <li>{@link CompactionJobCommittedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedEvent.equals(Object)",
      "int CompactionJobCommittedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link CompactionJobCommittedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedEvent.equals(Object)",
      "int CompactionJobCommittedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult = builderResult
        .commitTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult2 = builderResult2
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobCommittedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedEvent.equals(Object)",
      "int CompactionJobCommittedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("Job Id")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult2 = builderResult2
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobCommittedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedEvent.equals(Object)",
      "int CompactionJobCommittedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("Job Run Id")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult2 = builderResult2
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobCommittedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedEvent.equals(Object)",
      "int CompactionJobCommittedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("Table Id")
        .taskId("42")
        .build();
    Builder builderResult2 = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult2 = builderResult2
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobCommittedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedEvent.equals(Object)",
      "int CompactionJobCommittedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("Task Id")
        .build();
    Builder builderResult2 = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult2 = builderResult2
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobCommittedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedEvent.equals(Object)",
      "int CompactionJobCommittedEvent.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CompactionJobCommittedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedEvent.equals(Object)",
      "int CompactionJobCommittedEvent.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent buildResult = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CompactionJobCommittedEvent");
  }
}
