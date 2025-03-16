package sleeper.core.tracker.job.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.job.status.JobStatusUpdateRecord.Builder;

class JobStatusUpdateRecordDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#expiryDate(Instant)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#jobRunId(String)}
   *   <li>{@link Builder#statusUpdate(JobStatusUpdate)}
   *   <li>{@link Builder#taskId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobStatusUpdateRecord Builder.build()", "Builder Builder.expiryDate(Instant)",
      "Builder Builder.jobId(String)", "Builder Builder.jobRunId(String)",
      "Builder Builder.statusUpdate(JobStatusUpdate)", "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = JobStatusUpdateRecord.builder();

    // Act
    JobStatusUpdateRecord actualBuildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getJobId());
    assertEquals("42", actualBuildResult.getJobRunId());
    assertEquals("42", actualBuildResult.getTaskId());
    assertNull(actualBuildResult.getUpdateTime());
    Instant expiryDate = actualBuildResult.getExpiryDate();
    assertEquals(0, expiryDate.getNano());
    assertEquals(0L, expiryDate.getEpochSecond());
  }

  /**
   * Test {@link JobStatusUpdateRecord#getUpdateTime()}.
   * <p>
   * Method under test: {@link JobStatusUpdateRecord#getUpdateTime()}
   */
  @Test
  @DisplayName("Test getUpdateTime()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant JobStatusUpdateRecord.getUpdateTime()"})
  void testGetUpdateTime() {
    // Arrange
    JobStatusUpdate statusUpdate = mock(JobStatusUpdate.class);
    when(statusUpdate.getUpdateTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(statusUpdate)
        .taskId("42")
        .build();

    // Act
    Instant actualUpdateTime = buildResult.getUpdateTime();

    // Assert
    verify(statusUpdate).getUpdateTime();
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, buildResult.getExpiryDate());
    assertSame(instant, actualUpdateTime);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobStatusUpdateRecord#toString()}
   *   <li>{@link JobStatusUpdateRecord#getExpiryDate()}
   *   <li>{@link JobStatusUpdateRecord#getJobId()}
   *   <li>{@link JobStatusUpdateRecord#getJobRunId()}
   *   <li>{@link JobStatusUpdateRecord#getStatusUpdate()}
   *   <li>{@link JobStatusUpdateRecord#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant JobStatusUpdateRecord.getExpiryDate()", "String JobStatusUpdateRecord.getJobId()",
      "String JobStatusUpdateRecord.getJobRunId()", "JobStatusUpdate JobStatusUpdateRecord.getStatusUpdate()",
      "String JobStatusUpdateRecord.getTaskId()", "String JobStatusUpdateRecord.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();

    // Act
    buildResult.toString();
    Instant actualExpiryDate = buildResult.getExpiryDate();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    buildResult.getStatusUpdate();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", buildResult.getTaskId());
    assertSame(actualExpiryDate.EPOCH, actualExpiryDate);
  }

  /**
   * Test {@link JobStatusUpdateRecord#equals(Object)}, and {@link JobStatusUpdateRecord#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobStatusUpdateRecord#equals(Object)}
   *   <li>{@link JobStatusUpdateRecord#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobStatusUpdateRecord.equals(Object)", "int JobStatusUpdateRecord.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link JobStatusUpdateRecord#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdateRecord#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobStatusUpdateRecord.equals(Object)", "int JobStatusUpdateRecord.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();
    Builder builderResult2 = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult2 = builderResult2
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link JobStatusUpdateRecord#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdateRecord#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobStatusUpdateRecord.equals(Object)", "int JobStatusUpdateRecord.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("Job Id")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();
    Builder builderResult2 = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult2 = builderResult2
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link JobStatusUpdateRecord#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdateRecord#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobStatusUpdateRecord.equals(Object)", "int JobStatusUpdateRecord.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link JobStatusUpdateRecord#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdateRecord#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobStatusUpdateRecord.equals(Object)", "int JobStatusUpdateRecord.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to JobStatusUpdateRecord");
  }
}
