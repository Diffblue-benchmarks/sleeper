package sleeper.core.tracker.ingest.job.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.ingest.job.update.IngestJobRunIds.Builder;

class IngestJobRunIdsDiffblueTest {
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
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobRunIds Builder.build()", "Builder Builder.jobId(String)",
      "Builder Builder.jobRunId(String)", "Builder Builder.tableId(String)", "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange and Act
    IngestJobRunIds actualBuildResult = IngestJobRunIds.builder()
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
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobRunIds#toString()}
   *   <li>{@link IngestJobRunIds#getJobId()}
   *   <li>{@link IngestJobRunIds#getJobRunId()}
   *   <li>{@link IngestJobRunIds#getTableId()}
   *   <li>{@link IngestJobRunIds#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobRunIds.getJobId()", "String IngestJobRunIds.getJobRunId()",
      "String IngestJobRunIds.getTableId()", "String IngestJobRunIds.getTaskId()", "String IngestJobRunIds.toString()"})
  void testGettersAndSetters() {
    // Arrange
    IngestJobRunIds buildResult = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    String actualTableId = buildResult.getTableId();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", actualTableId);
    assertEquals("42", buildResult.getTaskId());
    assertEquals("IngestJobRunIds{jobId=42, tableId=42, jobRunId=42, taskId=42}", actualToStringResult);
  }

  /**
   * Test {@link IngestJobRunIds#equals(Object)}, and {@link IngestJobRunIds#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobRunIds#equals(Object)}
   *   <li>{@link IngestJobRunIds#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRunIds.equals(Object)", "int IngestJobRunIds.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    IngestJobRunIds buildResult = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    IngestJobRunIds buildResult2 = IngestJobRunIds.builder()
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
   * Test {@link IngestJobRunIds#equals(Object)}, and {@link IngestJobRunIds#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobRunIds#equals(Object)}
   *   <li>{@link IngestJobRunIds#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRunIds.equals(Object)", "int IngestJobRunIds.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    IngestJobRunIds buildResult = IngestJobRunIds.builder()
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
   * Test {@link IngestJobRunIds#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRunIds#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRunIds.equals(Object)", "int IngestJobRunIds.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    IngestJobRunIds buildResult = IngestJobRunIds.builder()
        .jobId("Job Id")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    IngestJobRunIds buildResult2 = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobRunIds#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRunIds#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRunIds.equals(Object)", "int IngestJobRunIds.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    IngestJobRunIds buildResult = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("Job Run Id")
        .tableId("42")
        .taskId("42")
        .build();
    IngestJobRunIds buildResult2 = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobRunIds#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRunIds#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRunIds.equals(Object)", "int IngestJobRunIds.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    IngestJobRunIds buildResult = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("42")
        .tableId("Table Id")
        .taskId("42")
        .build();
    IngestJobRunIds buildResult2 = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobRunIds#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRunIds#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRunIds.equals(Object)", "int IngestJobRunIds.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    IngestJobRunIds buildResult = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("Task Id")
        .build();
    IngestJobRunIds buildResult2 = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobRunIds#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRunIds#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRunIds.equals(Object)", "int IngestJobRunIds.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    IngestJobRunIds buildResult = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestJobRunIds#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRunIds#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRunIds.equals(Object)", "int IngestJobRunIds.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    IngestJobRunIds buildResult = IngestJobRunIds.builder()
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJobRunIds");
  }
}
