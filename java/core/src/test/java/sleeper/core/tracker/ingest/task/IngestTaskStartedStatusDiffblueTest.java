package sleeper.core.tracker.ingest.task;

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

class IngestTaskStartedStatusDiffblueTest {
  /**
   * Test {@link IngestTaskStartedStatus#startTime(Instant)}.
   * <p>
   * Method under test: {@link IngestTaskStartedStatus#startTime(Instant)}
   */
  @Test
  @DisplayName("Test startTime(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskStartedStatus IngestTaskStartedStatus.startTime(Instant)"})
  void testStartTime() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    IngestTaskStartedStatus actualStartTimeResult = IngestTaskStartedStatus.startTime(startTime);

    // Assert
    Instant instant = startTime.EPOCH;
    assertSame(instant, actualStartTimeResult.getStartTime());
    assertSame(instant, actualStartTimeResult.getUpdateTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestTaskStartedStatus#toString()}
   *   <li>{@link IngestTaskStartedStatus#getStartTime()}
   *   <li>{@link IngestTaskStartedStatus#getUpdateTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant IngestTaskStartedStatus.getStartTime()",
      "Instant IngestTaskStartedStatus.getUpdateTime()", "String IngestTaskStartedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    IngestTaskStartedStatus startTimeResult = IngestTaskStartedStatus
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    String actualToStringResult = startTimeResult.toString();
    Instant actualStartTime = startTimeResult.getStartTime();
    Instant actualUpdateTime = startTimeResult.getUpdateTime();

    // Assert
    assertEquals("IngestTaskStartedStatus{startTime=1970-01-01T00:00:00Z}", actualToStringResult);
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, actualStartTime);
    assertSame(instant, actualUpdateTime);
  }

  /**
   * Test {@link IngestTaskStartedStatus#equals(Object)}, and {@link IngestTaskStartedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestTaskStartedStatus#equals(Object)}
   *   <li>{@link IngestTaskStartedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStartedStatus.equals(Object)", "int IngestTaskStartedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    IngestTaskStartedStatus startTimeResult = IngestTaskStartedStatus
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestTaskStartedStatus startTimeResult2 = IngestTaskStartedStatus
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(startTimeResult, startTimeResult2);
    int expectedHashCodeResult = startTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, startTimeResult2.hashCode());
  }

  /**
   * Test {@link IngestTaskStartedStatus#equals(Object)}, and {@link IngestTaskStartedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestTaskStartedStatus#equals(Object)}
   *   <li>{@link IngestTaskStartedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStartedStatus.equals(Object)", "int IngestTaskStartedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    IngestTaskStartedStatus startTimeResult = IngestTaskStartedStatus
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(startTimeResult, startTimeResult);
    int expectedHashCodeResult = startTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, startTimeResult.hashCode());
  }

  /**
   * Test {@link IngestTaskStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStartedStatus.equals(Object)", "int IngestTaskStartedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    IngestTaskStartedStatus startTimeResult = IngestTaskStartedStatus
        .startTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertNotEquals(startTimeResult,
        IngestTaskStartedStatus.startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link IngestTaskStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStartedStatus.equals(Object)", "int IngestTaskStartedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        IngestTaskStartedStatus.startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        null);
  }

  /**
   * Test {@link IngestTaskStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTaskStartedStatus.equals(Object)", "int IngestTaskStartedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        IngestTaskStartedStatus.startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to IngestTaskStartedStatus");
  }
}
