package sleeper.core.tracker.job.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.core.tracker.job.status.JobRunFailedStatus.Builder;

class JobRunFailedStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#failureReasons(List)}
   *   <li>{@link Builder#failureTime(Instant)}
   *   <li>{@link Builder#updateTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRunFailedStatus Builder.build()", "Builder Builder.failureReasons(List)",
      "Builder Builder.failureTime(Instant)", "Builder Builder.updateTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = JobRunFailedStatus.builder();
    ArrayList<String> failureReasons = new ArrayList<>();
    Builder failureReasonsResult = builderResult.failureReasons(failureReasons);
    Builder failureTimeResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    JobRunFailedStatus actualBuildResult = failureTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Assert
    Instant finishTime = actualBuildResult.getFinishTime();
    assertEquals(0, finishTime.getNano());
    assertEquals(0L, finishTime.getEpochSecond());
    RecordsProcessed recordsProcessed = actualBuildResult.getRecordsProcessed();
    assertEquals(0L, recordsProcessed.getRecordsRead());
    assertEquals(0L, recordsProcessed.getRecordsWritten());
    assertFalse(actualBuildResult.getTimeInProcess().isPresent());
    assertFalse(actualBuildResult.isSuccessful());
    List<String> failureReasons2 = actualBuildResult.getFailureReasons();
    assertTrue(failureReasons2.isEmpty());
    assertSame(failureReasons, failureReasons2);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRunFailedStatus#toString()}
   *   <li>{@link JobRunFailedStatus#getFailureReasons()}
   *   <li>{@link JobRunFailedStatus#getFinishTime()}
   *   <li>{@link JobRunFailedStatus#getRecordsProcessed()}
   *   <li>{@link JobRunFailedStatus#getUpdateTime()}
   *   <li>{@link JobRunFailedStatus#isSuccessful()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List JobRunFailedStatus.getFailureReasons()", "Instant JobRunFailedStatus.getFinishTime()",
      "RecordsProcessed JobRunFailedStatus.getRecordsProcessed()", "Instant JobRunFailedStatus.getUpdateTime()",
      "boolean JobRunFailedStatus.isSuccessful()", "String JobRunFailedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = JobRunFailedStatus.builder();
    ArrayList<String> failureReasons = new ArrayList<>();
    Builder failureReasonsResult = builderResult.failureReasons(failureReasons);
    Builder failureTimeResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult = failureTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    List<String> actualFailureReasons = buildResult.getFailureReasons();
    Instant actualFinishTime = buildResult.getFinishTime();
    RecordsProcessed actualRecordsProcessed = buildResult.getRecordsProcessed();
    Instant actualUpdateTime = buildResult.getUpdateTime();

    // Assert
    assertEquals(
        "JobRunFailedStatus{updateTime=1970-01-01T00:00:00Z, failureTime=1970-01-01T00:00:00Z," + " failureReasons=[]}",
        actualToStringResult);
    assertFalse(buildResult.isSuccessful());
    assertTrue(actualFailureReasons.isEmpty());
    assertSame(failureReasons, actualFailureReasons);
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, actualFinishTime);
    assertSame(instant, actualUpdateTime);
    assertSame(actualRecordsProcessed.NONE, actualRecordsProcessed);
  }

  /**
   * Test {@link JobRunFailedStatus#equals(Object)}, and {@link JobRunFailedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRunFailedStatus#equals(Object)}
   *   <li>{@link JobRunFailedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunFailedStatus.equals(Object)", "int JobRunFailedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = JobRunFailedStatus.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    Builder failureTimeResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult = failureTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = JobRunFailedStatus.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    Builder failureTimeResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult2 = failureTimeResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link JobRunFailedStatus#equals(Object)}, and {@link JobRunFailedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRunFailedStatus#equals(Object)}
   *   <li>{@link JobRunFailedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunFailedStatus.equals(Object)", "int JobRunFailedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = JobRunFailedStatus.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    Builder failureTimeResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult = failureTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link JobRunFailedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunFailedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunFailedStatus.equals(Object)", "int JobRunFailedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> failureReasons = new ArrayList<>();
    failureReasons.add("foo");
    Builder failureReasonsResult = JobRunFailedStatus.builder().failureReasons(failureReasons);
    Builder failureTimeResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult = failureTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult = JobRunFailedStatus.builder();
    Builder failureReasonsResult2 = builderResult.failureReasons(new ArrayList<>());
    Builder failureTimeResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult2 = failureTimeResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link JobRunFailedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunFailedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunFailedStatus.equals(Object)", "int JobRunFailedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = JobRunFailedStatus.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    Builder failureTimeResult = failureReasonsResult
        .failureTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult = failureTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = JobRunFailedStatus.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    Builder failureTimeResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult2 = failureTimeResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link JobRunFailedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunFailedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunFailedStatus.equals(Object)", "int JobRunFailedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = JobRunFailedStatus.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    Builder failureTimeResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult = failureTimeResult
        .updateTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = JobRunFailedStatus.builder();
    Builder failureReasonsResult2 = builderResult2.failureReasons(new ArrayList<>());
    Builder failureTimeResult2 = failureReasonsResult2
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult2 = failureTimeResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link JobRunFailedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunFailedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunFailedStatus.equals(Object)", "int JobRunFailedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = JobRunFailedStatus.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    Builder failureTimeResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult = failureTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link JobRunFailedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRunFailedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRunFailedStatus.equals(Object)", "int JobRunFailedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = JobRunFailedStatus.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    Builder failureTimeResult = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobRunFailedStatus buildResult = failureTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to JobRunFailedStatus");
  }
}
