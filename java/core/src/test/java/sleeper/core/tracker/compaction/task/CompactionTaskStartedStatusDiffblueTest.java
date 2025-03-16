package sleeper.core.tracker.compaction.task;

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

class CompactionTaskStartedStatusDiffblueTest {
  /**
   * Test {@link CompactionTaskStartedStatus#startTime(Instant)}.
   * <p>
   * Method under test: {@link CompactionTaskStartedStatus#startTime(Instant)}
   */
  @Test
  @DisplayName("Test startTime(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionTaskStartedStatus CompactionTaskStartedStatus.startTime(Instant)"})
  void testStartTime() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    CompactionTaskStartedStatus actualStartTimeResult = CompactionTaskStartedStatus.startTime(startTime);

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
   *   <li>{@link CompactionTaskStartedStatus#toString()}
   *   <li>{@link CompactionTaskStartedStatus#getStartTime()}
   *   <li>{@link CompactionTaskStartedStatus#getUpdateTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant CompactionTaskStartedStatus.getStartTime()",
      "Instant CompactionTaskStartedStatus.getUpdateTime()", "String CompactionTaskStartedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    CompactionTaskStartedStatus startTimeResult = CompactionTaskStartedStatus
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    String actualToStringResult = startTimeResult.toString();
    Instant actualStartTime = startTimeResult.getStartTime();
    Instant actualUpdateTime = startTimeResult.getUpdateTime();

    // Assert
    assertEquals("CompactionTaskStartedStatus{startTime=1970-01-01T00:00:00Z}", actualToStringResult);
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, actualStartTime);
    assertSame(instant, actualUpdateTime);
  }

  /**
   * Test {@link CompactionTaskStartedStatus#equals(Object)}, and {@link CompactionTaskStartedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionTaskStartedStatus#equals(Object)}
   *   <li>{@link CompactionTaskStartedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStartedStatus.equals(Object)",
      "int CompactionTaskStartedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    CompactionTaskStartedStatus startTimeResult = CompactionTaskStartedStatus
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionTaskStartedStatus startTimeResult2 = CompactionTaskStartedStatus
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(startTimeResult, startTimeResult2);
    int expectedHashCodeResult = startTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, startTimeResult2.hashCode());
  }

  /**
   * Test {@link CompactionTaskStartedStatus#equals(Object)}, and {@link CompactionTaskStartedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionTaskStartedStatus#equals(Object)}
   *   <li>{@link CompactionTaskStartedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStartedStatus.equals(Object)",
      "int CompactionTaskStartedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    CompactionTaskStartedStatus startTimeResult = CompactionTaskStartedStatus
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(startTimeResult, startTimeResult);
    int expectedHashCodeResult = startTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, startTimeResult.hashCode());
  }

  /**
   * Test {@link CompactionTaskStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStartedStatus.equals(Object)",
      "int CompactionTaskStartedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    CompactionTaskStartedStatus startTimeResult = CompactionTaskStartedStatus
        .startTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertNotEquals(startTimeResult, CompactionTaskStartedStatus
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link CompactionTaskStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStartedStatus.equals(Object)",
      "int CompactionTaskStartedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(CompactionTaskStartedStatus
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
  }

  /**
   * Test {@link CompactionTaskStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionTaskStartedStatus.equals(Object)",
      "int CompactionTaskStartedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        CompactionTaskStartedStatus
            .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to CompactionTaskStartedStatus");
  }
}
