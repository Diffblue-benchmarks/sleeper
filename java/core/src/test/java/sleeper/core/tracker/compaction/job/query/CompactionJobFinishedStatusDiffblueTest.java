package sleeper.core.tracker.compaction.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.compaction.job.query.CompactionJobFinishedStatus.Builder;
import sleeper.core.tracker.job.run.RecordsProcessed;

class CompactionJobFinishedStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#finishTime(Instant)}
   *   <li>{@link Builder#recordsProcessed(RecordsProcessed)}
   *   <li>{@link Builder#updateTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobFinishedStatus Builder.build()", "Builder Builder.finishTime(Instant)",
      "Builder Builder.recordsProcessed(RecordsProcessed)", "Builder Builder.updateTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);

    // Act
    CompactionJobFinishedStatus actualBuildResult = recordsProcessedResult
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
    assertTrue(actualBuildResult.getFailureReasons().isEmpty());
    assertTrue(actualBuildResult.isSuccessful());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobFinishedStatus#toString()}
   *   <li>{@link CompactionJobFinishedStatus#getFinishTime()}
   *   <li>{@link CompactionJobFinishedStatus#getRecordsProcessed()}
   *   <li>{@link CompactionJobFinishedStatus#getUpdateTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant CompactionJobFinishedStatus.getFinishTime()",
      "RecordsProcessed CompactionJobFinishedStatus.getRecordsProcessed()",
      "Instant CompactionJobFinishedStatus.getUpdateTime()", "String CompactionJobFinishedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    Instant actualFinishTime = buildResult.getFinishTime();
    RecordsProcessed actualRecordsProcessed = buildResult.getRecordsProcessed();
    Instant actualUpdateTime = buildResult.getUpdateTime();

    // Assert
    assertEquals("CompactionJobFinishedStatus{updateTime=1970-01-01T00:00:00Z, finishTime=1970-01-01T00:00:00Z,"
        + " recordsProcessed=RecordsProcessed{recordsRead=0, recordsWritten=0}}", actualToStringResult);
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, actualFinishTime);
    assertSame(instant, actualUpdateTime);
    assertSame(actualRecordsProcessed.NONE, actualRecordsProcessed);
  }

  /**
   * Test {@link CompactionJobFinishedStatus#equals(Object)}, and {@link CompactionJobFinishedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobFinishedStatus#equals(Object)}
   *   <li>{@link CompactionJobFinishedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedStatus.equals(Object)",
      "int CompactionJobFinishedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult2 = recordsProcessedResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link CompactionJobFinishedStatus#equals(Object)}, and {@link CompactionJobFinishedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobFinishedStatus#equals(Object)}
   *   <li>{@link CompactionJobFinishedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedStatus.equals(Object)",
      "int CompactionJobFinishedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link CompactionJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedStatus.equals(Object)",
      "int CompactionJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult = builderResult
        .finishTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult2 = recordsProcessedResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedStatus.equals(Object)",
      "int CompactionJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = CompactionJobFinishedStatus.builder();
    Builder finishTimeResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Builder recordsProcessedResult = finishTimeResult.recordsProcessed(new RecordsProcessed(1L, 1L));
    CompactionJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult2 = recordsProcessedResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedStatus.equals(Object)",
      "int CompactionJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder builderResult2 = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult2 = builderResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult2 = recordsProcessedResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedStatus.equals(Object)",
      "int CompactionJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CompactionJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobFinishedStatus.equals(Object)",
      "int CompactionJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = CompactionJobFinishedStatus.builder();
    Builder recordsProcessedResult = builderResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .recordsProcessed(RecordsProcessed.NONE);
    CompactionJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CompactionJobFinishedStatus");
  }
}
