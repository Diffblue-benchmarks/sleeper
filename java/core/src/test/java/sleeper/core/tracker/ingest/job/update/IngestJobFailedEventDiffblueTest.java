package sleeper.core.tracker.ingest.job.update;

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
import sleeper.core.tracker.ingest.job.update.IngestJobFailedEvent.Builder;

class IngestJobFailedEventDiffblueTest {
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
  @MethodsUnderTest({"void Builder.<init>()", "IngestJobFailedEvent Builder.build()",
      "Builder Builder.failureReasons(List)", "Builder Builder.failureTime(Instant)", "Builder Builder.jobId(String)",
      "Builder Builder.jobRunId(String)", "Builder Builder.tableId(String)", "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    ArrayList<String> failureReasons = new ArrayList<>();
    Builder failureReasonsResult = builderResult.failureReasons(failureReasons);

    // Act
    IngestJobFailedEvent actualBuildResult = failureReasonsResult
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
    Builder builderResult = IngestJobFailedEvent.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.failure(new Exception("foo")));
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
    Builder builderResult = IngestJobFailedEvent.builder();
    IngestJobRunIds jobRunIds = IngestJobRunIds.builder().jobId("42").jobRunId("42").tableId("42").taskId("42").build();

    // Act
    Builder actualJobRunIdsResult = builderResult.jobRunIds(jobRunIds);

    // Assert
    IngestJobFailedEvent buildResult = builderResult.build();
    assertEquals("42", buildResult.getJobId());
    assertEquals("42", buildResult.getJobRunId());
    assertEquals("42", buildResult.getTableId());
    assertEquals("42", buildResult.getTaskId());
    assertNull(buildResult.getFailureTime());
    assertNull(buildResult.getFailureReasons());
    assertSame(builderResult, actualJobRunIdsResult);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobFailedEvent#toString()}
   *   <li>{@link IngestJobFailedEvent#getFailureReasons()}
   *   <li>{@link IngestJobFailedEvent#getFailureTime()}
   *   <li>{@link IngestJobFailedEvent#getJobId()}
   *   <li>{@link IngestJobFailedEvent#getJobRunId()}
   *   <li>{@link IngestJobFailedEvent#getTableId()}
   *   <li>{@link IngestJobFailedEvent#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List IngestJobFailedEvent.getFailureReasons()", "Instant IngestJobFailedEvent.getFailureTime()",
      "String IngestJobFailedEvent.getJobId()", "String IngestJobFailedEvent.getJobRunId()",
      "String IngestJobFailedEvent.getTableId()", "String IngestJobFailedEvent.getTaskId()",
      "String IngestJobFailedEvent.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    ArrayList<String> failureReasons = new ArrayList<>();
    Builder failureReasonsResult = builderResult.failureReasons(failureReasons);
    IngestJobFailedEvent buildResult = failureReasonsResult
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
    assertEquals("IngestJobFailedEvent{jobId=42, tableId=42, jobRunId=42, taskId=42, failureTime=1970-01-01T00:00:00Z,"
        + " failureReasons=[]}", actualToStringResult);
    assertTrue(actualFailureReasons.isEmpty());
    assertSame(failureReasons, actualFailureReasons);
    assertSame(actualFailureTime.EPOCH, actualFailureTime);
  }

  /**
   * Test {@link IngestJobFailedEvent#equals(Object)}, and {@link IngestJobFailedEvent#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobFailedEvent#equals(Object)}
   *   <li>{@link IngestJobFailedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFailedEvent.equals(Object)", "int IngestJobFailedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = IngestJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult2 = failureReasonsResult2
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
   * Test {@link IngestJobFailedEvent#equals(Object)}, and {@link IngestJobFailedEvent#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobFailedEvent#equals(Object)}
   *   <li>{@link IngestJobFailedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFailedEvent.equals(Object)", "int IngestJobFailedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult = failureReasonsResult
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
   * Test {@link IngestJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFailedEvent.equals(Object)", "int IngestJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> failureReasons = new ArrayList<>();
    failureReasons.add("foo");
    Builder failureReasonsResult = IngestJobFailedEvent.builder().failureReasons(failureReasons);
    IngestJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult = IngestJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult2 = failureReasonsResult2
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
   * Test {@link IngestJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFailedEvent.equals(Object)", "int IngestJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = IngestJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult2 = failureReasonsResult2
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
   * Test {@link IngestJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFailedEvent.equals(Object)", "int IngestJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("Job Id")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = IngestJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult2 = failureReasonsResult2
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
   * Test {@link IngestJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFailedEvent.equals(Object)", "int IngestJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("Job Run Id")
        .tableId("42")
        .taskId("42")
        .build();
    Builder builderResult2 = IngestJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult2 = failureReasonsResult2
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
   * Test {@link IngestJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFailedEvent.equals(Object)", "int IngestJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("Table Id")
        .taskId("42")
        .build();
    Builder builderResult2 = IngestJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult2 = failureReasonsResult2
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
   * Test {@link IngestJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFailedEvent.equals(Object)", "int IngestJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("Task Id")
        .build();
    Builder builderResult2 = IngestJobFailedEvent.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult2 = failureReasonsResult2
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
   * Test {@link IngestJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFailedEvent.equals(Object)", "int IngestJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult = failureReasonsResult
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
   * Test {@link IngestJobFailedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFailedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFailedEvent.equals(Object)", "int IngestJobFailedEvent.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = IngestJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent buildResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJobFailedEvent");
  }
}
