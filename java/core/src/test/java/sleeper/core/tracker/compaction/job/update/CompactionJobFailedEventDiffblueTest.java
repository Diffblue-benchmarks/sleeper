package sleeper.core.tracker.compaction.job.update;

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
import sleeper.core.tracker.compaction.job.update.CompactionJobFailedEvent.Builder;

class CompactionJobFailedEventDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#failureReasons(List)}
   *   <li>{@link Builder#failureTime(Instant)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#jobRunId(String)}
   *   <li>{@link Builder#tableId(String)}
   *   <li>{@link Builder#taskId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Builder.<init>()", "CompactionJobFailedEvent Builder.build()",
      "Builder Builder.failureReasons(List)", "Builder Builder.failureTime(Instant)", "Builder Builder.jobId(String)",
      "Builder Builder.jobRunId(String)", "Builder Builder.tableId(String)", "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    ArrayList<String> failureReasons = new ArrayList<>();
    Builder failureReasonsResult = builderResult.failureReasons(failureReasons);

    // Act
    CompactionJobFailedEvent actualBuildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
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
    Instant failureTime = actualBuildResult.getFailureTime();
    assertEquals(0, failureTime.getNano());
    assertEquals(0L, failureTime.getEpochSecond());
    List<String> failureReasons2 = actualBuildResult.getFailureReasons();
    assertTrue(failureReasons2.isEmpty());
    assertSame(failureReasons, failureReasons2);
  }

  /**
   * Test Builder {@link Builder#failure(Exception)}.
   * <p>
   * Method under test: {@link Builder#failure(Exception)}
   */
  @Test
  @DisplayName("Test Builder failure(Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.failure(Exception)"})
  void testBuilderFailure() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.failure(new Exception("foo")));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobFailedEvent#toString()}
   *   <li>{@link CompactionJobFailedEvent#getFailureReasons()}
   *   <li>{@link CompactionJobFailedEvent#getFailureTime()}
   *   <li>{@link CompactionJobFailedEvent#getJobId()}
   *   <li>{@link CompactionJobFailedEvent#getJobRunId()}
   *   <li>{@link CompactionJobFailedEvent#getTableId()}
   *   <li>{@link CompactionJobFailedEvent#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CompactionJobFailedEvent.getFailureReasons()",
      "Instant CompactionJobFailedEvent.getFailureTime()", "String CompactionJobFailedEvent.getJobId()",
      "String CompactionJobFailedEvent.getJobRunId()", "String CompactionJobFailedEvent.getTableId()",
      "String CompactionJobFailedEvent.getTaskId()", "String CompactionJobFailedEvent.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    ArrayList<String> failureReasons = new ArrayList<>();
    Builder failureReasonsResult = builderResult.failureReasons(failureReasons);
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    List<String> actualFailureReasons = buildResult.getFailureReasons();
    Instant actualFailureTime = buildResult.getFailureTime();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    String actualTableId = buildResult.getTableId();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", actualTableId);
    assertEquals("42", buildResult.getTaskId());
    assertEquals(
        "CompactionJobFailedEvent{jobId=42, tableId=42, taskId=42, jobRunId=42, failureTime=1970-01-01T00:00:00Z,"
            + " failureReasons=[]}",
        actualToStringResult);
    assertTrue(actualFailureReasons.isEmpty());
    assertSame(failureReasons, actualFailureReasons);
    assertSame(actualFailureTime.EPOCH, actualFailureTime);
  }

  /**
   * Test {@link CompactionJobFailedEvent#equals(Object)}, and {@link CompactionJobFailedEvent#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobFailedEvent#equals(Object)}
   *   <li>{@link CompactionJobFailedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFailedEvent.equals(Object)", "int CompactionJobFailedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
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
   * Test {@link CompactionJobFailedEvent#equals(Object)}, and {@link CompactionJobFailedEvent#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobFailedEvent#equals(Object)}
   *   <li>{@link CompactionJobFailedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFailedEvent.equals(Object)", "int CompactionJobFailedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
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
   * Test {@link CompactionJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFailedEvent.equals(Object)", "int CompactionJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> failureReasons = new ArrayList<>();
    failureReasons.add("foo");
    Builder failureReasonsResult = CompactionJobFailedEvent.builder().failureReasons(failureReasons);
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFailedEvent.equals(Object)", "int CompactionJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFailedEvent.equals(Object)", "int CompactionJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("Job Id")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFailedEvent.equals(Object)", "int CompactionJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("Job Run Id")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFailedEvent.equals(Object)", "int CompactionJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("Table Id")
        .taskId("42")
        .build();
    Builder builderResult2 = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFailedEvent.equals(Object)", "int CompactionJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("Task Id")
        .build();
    Builder builderResult2 = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFailedEvent.equals(Object)", "int CompactionJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CompactionJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFailedEvent.equals(Object)", "int CompactionJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CompactionJobFailedEvent");
  }
}
