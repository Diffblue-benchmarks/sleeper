package sleeper.core.tracker.ingest.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.ingest.job.query.IngestJobFinishedStatus.Builder;
import sleeper.core.tracker.job.run.RecordsProcessed;

class IngestJobFinishedStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#committedBySeparateFileUpdates(boolean)}
   *   <li>{@link Builder#finishTime(Instant)}
   *   <li>{@link Builder#numFilesWrittenByJob(int)}
   *   <li>{@link Builder#recordsProcessed(RecordsProcessed)}
   *   <li>{@link Builder#updateTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobFinishedStatus Builder.build()",
      "Builder Builder.committedBySeparateFileUpdates(boolean)", "Builder Builder.finishTime(Instant)",
      "Builder Builder.numFilesWrittenByJob(int)", "Builder Builder.recordsProcessed(RecordsProcessed)",
      "Builder Builder.updateTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder committedBySeparateFileUpdatesResult = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);

    // Act
    IngestJobFinishedStatus actualBuildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Assert
    Instant finishTime = actualBuildResult.getFinishTime();
    assertEquals(0, finishTime.getNano());
    assertEquals(0L, finishTime.getEpochSecond());
    RecordsProcessed recordsProcessed = actualBuildResult.getRecordsProcessed();
    assertEquals(0L, recordsProcessed.getRecordsRead());
    assertEquals(0L, recordsProcessed.getRecordsWritten());
    assertEquals(10, actualBuildResult.getNumFilesWrittenByJob());
    assertFalse(actualBuildResult.getTimeInProcess().isPresent());
    assertTrue(actualBuildResult.getFailureReasons().isEmpty());
    assertTrue(actualBuildResult.isCommittedBySeparateFileUpdates());
    assertTrue(actualBuildResult.isSuccessful());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobFinishedStatus#toString()}
   *   <li>{@link IngestJobFinishedStatus#getFinishTime()}
   *   <li>{@link IngestJobFinishedStatus#getNumFilesWrittenByJob()}
   *   <li>{@link IngestJobFinishedStatus#getRecordsProcessed()}
   *   <li>{@link IngestJobFinishedStatus#getUpdateTime()}
   *   <li>{@link IngestJobFinishedStatus#isCommittedBySeparateFileUpdates()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant IngestJobFinishedStatus.getFinishTime()",
      "int IngestJobFinishedStatus.getNumFilesWrittenByJob()",
      "RecordsProcessed IngestJobFinishedStatus.getRecordsProcessed()",
      "Instant IngestJobFinishedStatus.getUpdateTime()",
      "boolean IngestJobFinishedStatus.isCommittedBySeparateFileUpdates()",
      "String IngestJobFinishedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder committedBySeparateFileUpdatesResult = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    Instant actualFinishTime = buildResult.getFinishTime();
    int actualNumFilesWrittenByJob = buildResult.getNumFilesWrittenByJob();
    RecordsProcessed actualRecordsProcessed = buildResult.getRecordsProcessed();
    Instant actualUpdateTime = buildResult.getUpdateTime();

    // Assert
    assertEquals(
        "IngestJobFinishedStatus{updateTime=1970-01-01T00:00:00Z, finishTime=1970-01-01T00:00:00Z, recordsProcessed"
            + "=RecordsProcessed{recordsRead=0, recordsWritten=0}, numFilesWrittenByJob=10, committedBySeparateFileUpdates"
            + "=true}",
        actualToStringResult);
    assertEquals(10, actualNumFilesWrittenByJob);
    assertTrue(buildResult.isCommittedBySeparateFileUpdates());
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, actualFinishTime);
    assertSame(instant, actualUpdateTime);
    assertSame(actualRecordsProcessed.NONE, actualRecordsProcessed);
  }

  /**
   * Test {@link IngestJobFinishedStatus#equals(Object)}, and {@link IngestJobFinishedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobFinishedStatus#equals(Object)}
   *   <li>{@link IngestJobFinishedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedStatus.equals(Object)", "int IngestJobFinishedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder committedBySeparateFileUpdatesResult = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder committedBySeparateFileUpdatesResult2 = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult2 = committedBySeparateFileUpdatesResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult2 = recordsProcessedResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestJobFinishedStatus#equals(Object)}, and {@link IngestJobFinishedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobFinishedStatus#equals(Object)}
   *   <li>{@link IngestJobFinishedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedStatus.equals(Object)", "int IngestJobFinishedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder committedBySeparateFileUpdatesResult = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedStatus.equals(Object)", "int IngestJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.committedBySeparateFileUpdates(anyBoolean())).thenReturn(IngestJobFinishedStatus.builder());
    Builder committedBySeparateFileUpdatesResult = builder.committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder committedBySeparateFileUpdatesResult2 = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult2 = committedBySeparateFileUpdatesResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult2 = recordsProcessedResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedStatus.equals(Object)", "int IngestJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.committedBySeparateFileUpdates(anyBoolean())).thenReturn(IngestJobFinishedStatus.builder());
    Builder committedBySeparateFileUpdatesResult = builder.committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder committedBySeparateFileUpdatesResult2 = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult2 = committedBySeparateFileUpdatesResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult2 = recordsProcessedResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedStatus.equals(Object)", "int IngestJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.committedBySeparateFileUpdates(anyBoolean())).thenReturn(IngestJobFinishedStatus.builder());
    Builder committedBySeparateFileUpdatesResult = builder.committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(1)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder committedBySeparateFileUpdatesResult2 = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult2 = committedBySeparateFileUpdatesResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult2 = recordsProcessedResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedStatus.equals(Object)", "int IngestJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.committedBySeparateFileUpdates(anyBoolean())).thenReturn(IngestJobFinishedStatus.builder());
    Builder committedBySeparateFileUpdatesResult = builder.committedBySeparateFileUpdates(true);
    Builder numFilesWrittenByJobResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10);
    Builder recordsProcessedResult = numFilesWrittenByJobResult.recordsProcessed(new RecordsProcessed(1L, 1L));
    IngestJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder committedBySeparateFileUpdatesResult2 = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult2 = committedBySeparateFileUpdatesResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult2 = recordsProcessedResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedStatus.equals(Object)", "int IngestJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.committedBySeparateFileUpdates(anyBoolean())).thenReturn(IngestJobFinishedStatus.builder());
    Builder committedBySeparateFileUpdatesResult = builder.committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder committedBySeparateFileUpdatesResult2 = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult2 = committedBySeparateFileUpdatesResult2
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult2 = recordsProcessedResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedStatus.equals(Object)", "int IngestJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder committedBySeparateFileUpdatesResult = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestJobFinishedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobFinishedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobFinishedStatus.equals(Object)", "int IngestJobFinishedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder committedBySeparateFileUpdatesResult = IngestJobFinishedStatus.builder()
        .committedBySeparateFileUpdates(true);
    Builder recordsProcessedResult = committedBySeparateFileUpdatesResult
        .finishTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numFilesWrittenByJob(10)
        .recordsProcessed(RecordsProcessed.NONE);
    IngestJobFinishedStatus buildResult = recordsProcessedResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJobFinishedStatus");
  }
}
