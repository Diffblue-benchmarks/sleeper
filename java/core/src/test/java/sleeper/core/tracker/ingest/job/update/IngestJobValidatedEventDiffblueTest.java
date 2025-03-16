package sleeper.core.tracker.ingest.job.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import sleeper.core.tracker.ingest.job.update.IngestJobValidatedEvent.Builder;

class IngestJobValidatedEventDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#fileCount(int)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#jobRunId(String)}
   *   <li>{@link Builder#jsonMessage(String)}
   *   <li>{@link Builder#reasons(List)}
   *   <li>{@link Builder#tableId(String)}
   *   <li>{@link Builder#validationTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobValidatedEvent Builder.build()", "Builder Builder.fileCount(int)",
      "Builder Builder.jobId(String)", "Builder Builder.jobRunId(String)", "Builder Builder.jsonMessage(String)",
      "Builder Builder.reasons(List)", "Builder Builder.tableId(String)", "Builder Builder.validationTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    ArrayList<String> reasons = new ArrayList<>();
    Builder tableIdResult = jsonMessageResult.reasons(reasons).tableId("42");

    // Act
    IngestJobValidatedEvent actualBuildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getJobId());
    assertEquals("42", actualBuildResult.getJobRunId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("Json Message", actualBuildResult.getJsonMessage());
    assertNull(actualBuildResult.getTaskId());
    Instant validationTime = actualBuildResult.getValidationTime();
    assertEquals(0, validationTime.getNano());
    assertEquals(0L, validationTime.getEpochSecond());
    assertEquals(3, actualBuildResult.getFileCount());
    List<String> reasons2 = actualBuildResult.getReasons();
    assertTrue(reasons2.isEmpty());
    assertTrue(actualBuildResult.isAccepted());
    assertSame(reasons, reasons2);
  }

  /**
   * Test Builder {@link Builder#reasons(String[])} with {@code String[]}.
   * <p>
   * Method under test: {@link Builder#reasons(String[])}
   */
  @Test
  @DisplayName("Test Builder reasons(String[]) with 'String[]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.reasons(String[])"})
  void testBuilderReasonsWithString() {
    // Arrange
    Builder builderResult = IngestJobValidatedEvent.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.reasons("Just cause"));
  }

  /**
   * Test {@link IngestJobValidatedEvent#ingestJobRejected(String, String, Instant, String[])}.
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#ingestJobRejected(String, String, Instant, String[])}
   */
  @Test
  @DisplayName("Test ingestJobRejected(String, String, Instant, String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "IngestJobValidatedEvent IngestJobValidatedEvent.ingestJobRejected(String, String, Instant, String[])"})
  void testIngestJobRejected() {
    // Arrange
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    IngestJobValidatedEvent actualIngestJobRejectedResult = IngestJobValidatedEvent.ingestJobRejected("42",
        "Json Message", validationTime, "Just cause");

    // Assert
    assertEquals("42", actualIngestJobRejectedResult.getJobId());
    assertEquals("Json Message", actualIngestJobRejectedResult.getJsonMessage());
    List<String> reasons = actualIngestJobRejectedResult.getReasons();
    assertEquals(1, reasons.size());
    assertEquals("Just cause", reasons.get(0));
    assertNull(actualIngestJobRejectedResult.getTaskId());
    assertNull(actualIngestJobRejectedResult.getJobRunId());
    assertNull(actualIngestJobRejectedResult.getTableId());
    assertEquals(0, actualIngestJobRejectedResult.getFileCount());
    assertFalse(actualIngestJobRejectedResult.isAccepted());
    Instant expectedValidationTime = validationTime.EPOCH;
    assertSame(expectedValidationTime, actualIngestJobRejectedResult.getValidationTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobValidatedEvent#toString()}
   *   <li>{@link IngestJobValidatedEvent#getFileCount()}
   *   <li>{@link IngestJobValidatedEvent#getJobId()}
   *   <li>{@link IngestJobValidatedEvent#getJobRunId()}
   *   <li>{@link IngestJobValidatedEvent#getJsonMessage()}
   *   <li>{@link IngestJobValidatedEvent#getReasons()}
   *   <li>{@link IngestJobValidatedEvent#getTableId()}
   *   <li>{@link IngestJobValidatedEvent#getValidationTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IngestJobValidatedEvent.getFileCount()", "String IngestJobValidatedEvent.getJobId()",
      "String IngestJobValidatedEvent.getJobRunId()", "String IngestJobValidatedEvent.getJsonMessage()",
      "List IngestJobValidatedEvent.getReasons()", "String IngestJobValidatedEvent.getTableId()",
      "Instant IngestJobValidatedEvent.getValidationTime()", "String IngestJobValidatedEvent.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    ArrayList<String> reasons = new ArrayList<>();
    Builder tableIdResult = jsonMessageResult.reasons(reasons).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    int actualFileCount = buildResult.getFileCount();
    String actualJobId = buildResult.getJobId();
    String actualJobRunId = buildResult.getJobRunId();
    String actualJsonMessage = buildResult.getJsonMessage();
    List<String> actualReasons = buildResult.getReasons();
    String actualTableId = buildResult.getTableId();
    Instant actualValidationTime = buildResult.getValidationTime();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualJobRunId);
    assertEquals("42", actualTableId);
    assertEquals("IngestJobValidatedEvent{jobId='42', tableId='42', fileCount=3, validationTime=1970-01-01T00:00:00Z,"
        + " reasons=[], jobRunId='42', jsonMessage='Json Message'}", actualToStringResult);
    assertEquals("Json Message", actualJsonMessage);
    assertEquals(3, actualFileCount);
    assertTrue(actualReasons.isEmpty());
    assertSame(reasons, actualReasons);
    assertSame(actualValidationTime.EPOCH, actualValidationTime);
  }

  /**
   * Test {@link IngestJobValidatedEvent#isAccepted()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#isAccepted()}
   */
  @Test
  @DisplayName("Test isAccepted(); given ArrayList() add 'foo'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.isAccepted()"})
  void testIsAccepted_givenArrayListAddFoo_thenReturnFalse() {
    // Arrange
    ArrayList<String> reasons = new ArrayList<>();
    reasons.add("foo");
    Builder tableIdResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message")
        .reasons(reasons)
        .tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertFalse(buildResult.isAccepted());
  }

  /**
   * Test {@link IngestJobValidatedEvent#isAccepted()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#isAccepted()}
   */
  @Test
  @DisplayName("Test isAccepted(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.isAccepted()"})
  void testIsAccepted_thenReturnTrue() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertTrue(buildResult.isAccepted());
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}, and {@link IngestJobValidatedEvent#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobValidatedEvent#equals(Object)}
   *   <li>{@link IngestJobValidatedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult2 = jsonMessageResult2.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult2 = tableIdResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}, and {@link IngestJobValidatedEvent#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobValidatedEvent#equals(Object)}
   *   <li>{@link IngestJobValidatedEvent#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(1)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult2 = jsonMessageResult2.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult2 = tableIdResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("Job Id")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult2 = jsonMessageResult2.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult2 = tableIdResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("Job Run Id")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult2 = jsonMessageResult2.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult2 = tableIdResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage(null);
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult2 = jsonMessageResult2.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult2 = tableIdResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    ArrayList<String> reasons = new ArrayList<>();
    reasons.add("foo");
    Builder tableIdResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message")
        .reasons(reasons)
        .tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult2 = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult2 = tableIdResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("Table Id");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult2 = jsonMessageResult2.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult2 = tableIdResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder jsonMessageResult2 = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult2 = jsonMessageResult2.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult2 = tableIdResult2
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestJobValidatedEvent#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobValidatedEvent#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobValidatedEvent.equals(Object)", "int IngestJobValidatedEvent.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent buildResult = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJobValidatedEvent");
  }
}
