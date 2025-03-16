package sleeper.core.tracker.compaction.job.query;

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

class CompactionJobStartedStatusDiffblueTest {
  /**
   * Test {@link CompactionJobStartedStatus#startAndUpdateTime(Instant, Instant)}.
   * <p>
   * Method under test: {@link CompactionJobStartedStatus#startAndUpdateTime(Instant, Instant)}
   */
  @Test
  @DisplayName("Test startAndUpdateTime(Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobStartedStatus CompactionJobStartedStatus.startAndUpdateTime(Instant, Instant)"})
  void testStartAndUpdateTime() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    CompactionJobStartedStatus actualStartAndUpdateTimeResult = CompactionJobStartedStatus.startAndUpdateTime(startTime,
        updateTime);

    // Assert
    Instant instant = updateTime.EPOCH;
    assertSame(instant, actualStartAndUpdateTimeResult.getStartTime());
    assertSame(instant, actualStartAndUpdateTimeResult.getUpdateTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobStartedStatus#toString()}
   *   <li>{@link CompactionJobStartedStatus#getStartTime()}
   *   <li>{@link CompactionJobStartedStatus#getUpdateTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant CompactionJobStartedStatus.getStartTime()",
      "Instant CompactionJobStartedStatus.getUpdateTime()", "String CompactionJobStartedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobStartedStatus startAndUpdateTimeResult = CompactionJobStartedStatus.startAndUpdateTime(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    String actualToStringResult = startAndUpdateTimeResult.toString();
    Instant actualStartTime = startAndUpdateTimeResult.getStartTime();
    Instant actualUpdateTime = startAndUpdateTimeResult.getUpdateTime();

    // Assert
    assertEquals("CompactionJobStartedStatus{updateTime=1970-01-01T00:00:00Z, startTime=1970-01-01T00:00:00Z}",
        actualToStringResult);
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, actualStartTime);
    assertSame(instant, actualUpdateTime);
  }

  /**
   * Test {@link CompactionJobStartedStatus#equals(Object)}, and {@link CompactionJobStartedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobStartedStatus#equals(Object)}
   *   <li>{@link CompactionJobStartedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedStatus.equals(Object)", "int CompactionJobStartedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobStartedStatus startAndUpdateTimeResult = CompactionJobStartedStatus.startAndUpdateTime(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant startTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobStartedStatus startAndUpdateTimeResult2 = CompactionJobStartedStatus.startAndUpdateTime(startTime2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(startAndUpdateTimeResult, startAndUpdateTimeResult2);
    int expectedHashCodeResult = startAndUpdateTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, startAndUpdateTimeResult2.hashCode());
  }

  /**
   * Test {@link CompactionJobStartedStatus#equals(Object)}, and {@link CompactionJobStartedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobStartedStatus#equals(Object)}
   *   <li>{@link CompactionJobStartedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedStatus.equals(Object)", "int CompactionJobStartedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobStartedStatus startAndUpdateTimeResult = CompactionJobStartedStatus.startAndUpdateTime(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(startAndUpdateTimeResult, startAndUpdateTimeResult);
    int expectedHashCodeResult = startAndUpdateTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, startAndUpdateTimeResult.hashCode());
  }

  /**
   * Test {@link CompactionJobStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedStatus.equals(Object)", "int CompactionJobStartedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Instant startTime = LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobStartedStatus startAndUpdateTimeResult = CompactionJobStartedStatus.startAndUpdateTime(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant startTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(startAndUpdateTimeResult, CompactionJobStartedStatus.startAndUpdateTime(startTime2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link CompactionJobStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedStatus.equals(Object)", "int CompactionJobStartedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobStartedStatus startAndUpdateTimeResult = CompactionJobStartedStatus.startAndUpdateTime(startTime,
        LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant startTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(startAndUpdateTimeResult, CompactionJobStartedStatus.startAndUpdateTime(startTime2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link CompactionJobStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedStatus.equals(Object)", "int CompactionJobStartedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(CompactionJobStartedStatus.startAndUpdateTime(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
  }

  /**
   * Test {@link CompactionJobStartedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStartedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobStartedStatus.equals(Object)", "int CompactionJobStartedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(
        CompactionJobStartedStatus.startAndUpdateTime(startTime,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to CompactionJobStartedStatus");
  }
}
