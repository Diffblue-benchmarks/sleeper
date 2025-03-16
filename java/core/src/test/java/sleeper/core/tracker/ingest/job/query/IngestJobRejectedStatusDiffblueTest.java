package sleeper.core.tracker.ingest.job.query;

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
import sleeper.core.tracker.ingest.job.query.IngestJobRejectedStatus.Builder;
import sleeper.core.tracker.job.run.RecordsProcessed;

class IngestJobRejectedStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#inputFileCount(int)}
   *   <li>{@link Builder#jsonMessage(String)}
   *   <li>{@link Builder#reasons(List)}
   *   <li>{@link Builder#updateTime(Instant)}
   *   <li>{@link Builder#validationTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobRejectedStatus Builder.build()", "Builder Builder.inputFileCount(int)",
      "Builder Builder.jsonMessage(String)", "Builder Builder.reasons(List)", "Builder Builder.updateTime(Instant)",
      "Builder Builder.validationTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    ArrayList<String> reasons = new ArrayList<>();
    Builder reasonsResult = jsonMessageResult.reasons(reasons);
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    IngestJobRejectedStatus actualBuildResult = updateTimeResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Assert
    assertEquals("Json Message", actualBuildResult.getJsonMessage());
    Instant finishTime = actualBuildResult.getFinishTime();
    assertEquals(0, finishTime.getNano());
    assertEquals(0L, finishTime.getEpochSecond());
    RecordsProcessed recordsProcessed = actualBuildResult.getRecordsProcessed();
    assertEquals(0L, recordsProcessed.getRecordsRead());
    assertEquals(0L, recordsProcessed.getRecordsWritten());
    assertEquals(3, actualBuildResult.getInputFileCount());
    assertFalse(actualBuildResult.getTimeInProcess().isPresent());
    assertFalse(actualBuildResult.isSuccessful());
    assertFalse(actualBuildResult.isValid());
    List<String> failureReasons = actualBuildResult.getFailureReasons();
    assertTrue(failureReasons.isEmpty());
    assertSame(reasons, failureReasons);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobRejectedStatus#toString()}
   *   <li>{@link IngestJobRejectedStatus#getFailureReasons()}
   *   <li>{@link IngestJobRejectedStatus#getFinishTime()}
   *   <li>{@link IngestJobRejectedStatus#getInputFileCount()}
   *   <li>{@link IngestJobRejectedStatus#getJsonMessage()}
   *   <li>{@link IngestJobRejectedStatus#getRecordsProcessed()}
   *   <li>{@link IngestJobRejectedStatus#getStartTime()}
   *   <li>{@link IngestJobRejectedStatus#getUpdateTime()}
   *   <li>{@link IngestJobRejectedStatus#isSuccessful()}
   *   <li>{@link IngestJobRejectedStatus#isValid()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List IngestJobRejectedStatus.getFailureReasons()",
      "Instant IngestJobRejectedStatus.getFinishTime()", "int IngestJobRejectedStatus.getInputFileCount()",
      "String IngestJobRejectedStatus.getJsonMessage()",
      "RecordsProcessed IngestJobRejectedStatus.getRecordsProcessed()",
      "Instant IngestJobRejectedStatus.getStartTime()", "Instant IngestJobRejectedStatus.getUpdateTime()",
      "boolean IngestJobRejectedStatus.isSuccessful()", "boolean IngestJobRejectedStatus.isValid()",
      "String IngestJobRejectedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    ArrayList<String> reasons = new ArrayList<>();
    Builder reasonsResult = jsonMessageResult.reasons(reasons);
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult = updateTimeResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    List<String> actualFailureReasons = buildResult.getFailureReasons();
    Instant actualFinishTime = buildResult.getFinishTime();
    int actualInputFileCount = buildResult.getInputFileCount();
    String actualJsonMessage = buildResult.getJsonMessage();
    RecordsProcessed actualRecordsProcessed = buildResult.getRecordsProcessed();
    Instant actualStartTime = buildResult.getStartTime();
    Instant actualUpdateTime = buildResult.getUpdateTime();
    boolean actualIsSuccessfulResult = buildResult.isSuccessful();

    // Assert
    assertEquals("IngestJobRejectedStatus{validationTime=1970-01-01T00:00:00Z, updateTime=1970-01-01T00:00:00Z,"
        + " inputFileCount=3, reasons=[], jsonMessage=\"Json Message\"}", actualToStringResult);
    assertEquals("Json Message", actualJsonMessage);
    assertEquals(3, actualInputFileCount);
    assertFalse(actualIsSuccessfulResult);
    assertFalse(buildResult.isValid());
    assertTrue(actualFailureReasons.isEmpty());
    assertSame(reasons, actualFailureReasons);
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, actualFinishTime);
    assertSame(instant, actualStartTime);
    assertSame(instant, actualUpdateTime);
    assertSame(actualRecordsProcessed.NONE, actualRecordsProcessed);
  }

  /**
   * Test {@link IngestJobRejectedStatus#equals(Object)}, and {@link IngestJobRejectedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobRejectedStatus#equals(Object)}
   *   <li>{@link IngestJobRejectedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRejectedStatus.equals(Object)", "int IngestJobRejectedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult = jsonMessageResult.reasons(new ArrayList<>());
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult = updateTimeResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult2 = jsonMessageResult2.reasons(new ArrayList<>());
    Builder updateTimeResult2 = reasonsResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult2 = updateTimeResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestJobRejectedStatus#equals(Object)}, and {@link IngestJobRejectedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobRejectedStatus#equals(Object)}
   *   <li>{@link IngestJobRejectedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRejectedStatus.equals(Object)", "int IngestJobRejectedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult = jsonMessageResult.reasons(new ArrayList<>());
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult = updateTimeResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestJobRejectedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRejectedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRejectedStatus.equals(Object)", "int IngestJobRejectedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(1).jsonMessage("Json Message");
    Builder reasonsResult = jsonMessageResult.reasons(new ArrayList<>());
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult = updateTimeResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult2 = jsonMessageResult2.reasons(new ArrayList<>());
    Builder updateTimeResult2 = reasonsResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult2 = updateTimeResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobRejectedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRejectedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRejectedStatus.equals(Object)", "int IngestJobRejectedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage(null);
    Builder reasonsResult = jsonMessageResult.reasons(new ArrayList<>());
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult = updateTimeResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult2 = jsonMessageResult2.reasons(new ArrayList<>());
    Builder updateTimeResult2 = reasonsResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult2 = updateTimeResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobRejectedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRejectedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRejectedStatus.equals(Object)", "int IngestJobRejectedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<String> reasons = new ArrayList<>();
    reasons.add("foo");
    Builder reasonsResult = IngestJobRejectedStatus.builder()
        .inputFileCount(3)
        .jsonMessage("Json Message")
        .reasons(reasons);
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult = updateTimeResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult2 = jsonMessageResult.reasons(new ArrayList<>());
    Builder updateTimeResult2 = reasonsResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult2 = updateTimeResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobRejectedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRejectedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRejectedStatus.equals(Object)", "int IngestJobRejectedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult = jsonMessageResult.reasons(new ArrayList<>());
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult = updateTimeResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult2 = jsonMessageResult2.reasons(new ArrayList<>());
    Builder updateTimeResult2 = reasonsResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult2 = updateTimeResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobRejectedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRejectedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRejectedStatus.equals(Object)", "int IngestJobRejectedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult = jsonMessageResult.reasons(new ArrayList<>());
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult = updateTimeResult
        .validationTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult2 = jsonMessageResult2.reasons(new ArrayList<>());
    Builder updateTimeResult2 = reasonsResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult2 = updateTimeResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobRejectedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRejectedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRejectedStatus.equals(Object)", "int IngestJobRejectedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult = jsonMessageResult.reasons(new ArrayList<>());
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult = updateTimeResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestJobRejectedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRejectedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobRejectedStatus.equals(Object)", "int IngestJobRejectedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder jsonMessageResult = IngestJobRejectedStatus.builder().inputFileCount(3).jsonMessage("Json Message");
    Builder reasonsResult = jsonMessageResult.reasons(new ArrayList<>());
    Builder updateTimeResult = reasonsResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobRejectedStatus buildResult = updateTimeResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJobRejectedStatus");
  }
}
