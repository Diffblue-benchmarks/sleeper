package sleeper.core.tracker.ingest.job.query;

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
import sleeper.core.tracker.ingest.job.query.IngestJobStartedStatus.Builder;

class IngestJobStartedStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#inputFileCount(int)}
   *   <li>{@link Builder#startTime(Instant)}
   *   <li>{@link Builder#updateTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Builder.<init>()", "IngestJobStartedStatus Builder.build()",
      "Builder Builder.inputFileCount(int)", "Builder Builder.startTime(Instant)",
      "Builder Builder.updateTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder inputFileCountResult = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult = inputFileCountResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    IngestJobStartedStatus actualBuildResult = startTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Assert
    Instant startTime = actualBuildResult.getStartTime();
    assertEquals(0, startTime.getNano());
    assertEquals(0L, startTime.getEpochSecond());
    assertEquals(3, actualBuildResult.getInputFileCount());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobStartedStatus#toString()}
   *   <li>{@link IngestJobStartedStatus#getInputFileCount()}
   *   <li>{@link IngestJobStartedStatus#getStartTime()}
   *   <li>{@link IngestJobStartedStatus#getUpdateTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IngestJobStartedStatus.getInputFileCount()", "Instant IngestJobStartedStatus.getStartTime()",
      "Instant IngestJobStartedStatus.getUpdateTime()", "String IngestJobStartedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder inputFileCountResult = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult = inputFileCountResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult = startTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    int actualInputFileCount = buildResult.getInputFileCount();
    Instant actualStartTime = buildResult.getStartTime();
    Instant actualUpdateTime = buildResult.getUpdateTime();

    // Assert
    assertEquals("IngestJobStartedStatus{inputFileCount=3, startTime=1970-01-01T00:00:00Z, updateTime=1970-01-01T00"
        + ":00:00Z}", actualToStringResult);
    assertEquals(3, actualInputFileCount);
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, actualStartTime);
    assertSame(instant, actualUpdateTime);
  }

  /**
   * Test {@link IngestJobStartedStatus#equals(Object)}, and {@link IngestJobStartedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobStartedStatus#equals(Object)}
   *   <li>{@link IngestJobStartedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedStatus.equals(Object)", "int IngestJobStartedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder inputFileCountResult = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult = inputFileCountResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult = startTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder inputFileCountResult2 = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult2 = inputFileCountResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult2 = startTimeResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestJobStartedStatus#equals(Object)}, and {@link IngestJobStartedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobStartedStatus#equals(Object)}
   *   <li>{@link IngestJobStartedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedStatus.equals(Object)", "int IngestJobStartedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder inputFileCountResult = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult = inputFileCountResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult = startTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestJobStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedStatus.equals(Object)", "int IngestJobStartedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder inputFileCountResult = IngestJobStartedStatus.builder().inputFileCount(1);
    Builder startTimeResult = inputFileCountResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult = startTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder inputFileCountResult2 = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult2 = inputFileCountResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult2 = startTimeResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedStatus.equals(Object)", "int IngestJobStartedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder inputFileCountResult = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult = inputFileCountResult
        .startTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult = startTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder inputFileCountResult2 = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult2 = inputFileCountResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult2 = startTimeResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedStatus.equals(Object)", "int IngestJobStartedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder inputFileCountResult = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult = inputFileCountResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult = startTimeResult
        .updateTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder inputFileCountResult2 = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult2 = inputFileCountResult2
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult2 = startTimeResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedStatus.equals(Object)", "int IngestJobStartedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder inputFileCountResult = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult = inputFileCountResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult = startTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestJobStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStartedStatus.equals(Object)", "int IngestJobStartedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder inputFileCountResult = IngestJobStartedStatus.builder().inputFileCount(3);
    Builder startTimeResult = inputFileCountResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobStartedStatus buildResult = startTimeResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJobStartedStatus");
  }
}
