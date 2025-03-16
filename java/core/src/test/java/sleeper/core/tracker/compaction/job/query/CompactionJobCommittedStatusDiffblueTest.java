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

class CompactionJobCommittedStatusDiffblueTest {
  /**
   * Test {@link CompactionJobCommittedStatus#commitAndUpdateTime(Instant, Instant)}.
   * <p>
   * Method under test: {@link CompactionJobCommittedStatus#commitAndUpdateTime(Instant, Instant)}
   */
  @Test
  @DisplayName("Test commitAndUpdateTime(Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobCommittedStatus CompactionJobCommittedStatus.commitAndUpdateTime(Instant, Instant)"})
  void testCommitAndUpdateTime() {
    // Arrange
    Instant commitTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    CompactionJobCommittedStatus actualCommitAndUpdateTimeResult = CompactionJobCommittedStatus
        .commitAndUpdateTime(commitTime, updateTime);

    // Assert
    Instant instant = updateTime.EPOCH;
    assertSame(instant, actualCommitAndUpdateTimeResult.getCommitTime());
    assertSame(instant, actualCommitAndUpdateTimeResult.getUpdateTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobCommittedStatus#toString()}
   *   <li>{@link CompactionJobCommittedStatus#getCommitTime()}
   *   <li>{@link CompactionJobCommittedStatus#getUpdateTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant CompactionJobCommittedStatus.getCommitTime()",
      "Instant CompactionJobCommittedStatus.getUpdateTime()", "String CompactionJobCommittedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Instant commitTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobCommittedStatus commitAndUpdateTimeResult = CompactionJobCommittedStatus
        .commitAndUpdateTime(commitTime, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    String actualToStringResult = commitAndUpdateTimeResult.toString();
    Instant actualCommitTime = commitAndUpdateTimeResult.getCommitTime();
    Instant actualUpdateTime = commitAndUpdateTimeResult.getUpdateTime();

    // Assert
    assertEquals("CompactionJobCommittedStatus{commitTime=1970-01-01T00:00:00Z, updateTime=1970-01-01T00:00:00Z}",
        actualToStringResult);
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, actualCommitTime);
    assertSame(instant, actualUpdateTime);
  }

  /**
   * Test {@link CompactionJobCommittedStatus#equals(Object)}, and {@link CompactionJobCommittedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobCommittedStatus#equals(Object)}
   *   <li>{@link CompactionJobCommittedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedStatus.equals(Object)",
      "int CompactionJobCommittedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Instant commitTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobCommittedStatus commitAndUpdateTimeResult = CompactionJobCommittedStatus
        .commitAndUpdateTime(commitTime, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant commitTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobCommittedStatus commitAndUpdateTimeResult2 = CompactionJobCommittedStatus
        .commitAndUpdateTime(commitTime2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(commitAndUpdateTimeResult, commitAndUpdateTimeResult2);
    int expectedHashCodeResult = commitAndUpdateTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, commitAndUpdateTimeResult2.hashCode());
  }

  /**
   * Test {@link CompactionJobCommittedStatus#equals(Object)}, and {@link CompactionJobCommittedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobCommittedStatus#equals(Object)}
   *   <li>{@link CompactionJobCommittedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedStatus.equals(Object)",
      "int CompactionJobCommittedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Instant commitTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobCommittedStatus commitAndUpdateTimeResult = CompactionJobCommittedStatus
        .commitAndUpdateTime(commitTime, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(commitAndUpdateTimeResult, commitAndUpdateTimeResult);
    int expectedHashCodeResult = commitAndUpdateTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, commitAndUpdateTimeResult.hashCode());
  }

  /**
   * Test {@link CompactionJobCommittedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedStatus.equals(Object)",
      "int CompactionJobCommittedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Instant commitTime = LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobCommittedStatus commitAndUpdateTimeResult = CompactionJobCommittedStatus
        .commitAndUpdateTime(commitTime, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant commitTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(commitAndUpdateTimeResult, CompactionJobCommittedStatus.commitAndUpdateTime(commitTime2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link CompactionJobCommittedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedStatus.equals(Object)",
      "int CompactionJobCommittedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Instant commitTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    CompactionJobCommittedStatus commitAndUpdateTimeResult = CompactionJobCommittedStatus
        .commitAndUpdateTime(commitTime, LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant commitTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(commitAndUpdateTimeResult, CompactionJobCommittedStatus.commitAndUpdateTime(commitTime2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link CompactionJobCommittedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedStatus.equals(Object)",
      "int CompactionJobCommittedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Instant commitTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(CompactionJobCommittedStatus.commitAndUpdateTime(commitTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
  }

  /**
   * Test {@link CompactionJobCommittedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCommittedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCommittedStatus.equals(Object)",
      "int CompactionJobCommittedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Instant commitTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(
        CompactionJobCommittedStatus.commitAndUpdateTime(commitTime,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to CompactionJobCommittedStatus");
  }
}
