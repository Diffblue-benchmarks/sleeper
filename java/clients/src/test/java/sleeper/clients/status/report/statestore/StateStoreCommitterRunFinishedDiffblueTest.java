package sleeper.clients.status.report.statestore;

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

class StateStoreCommitterRunFinishedDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRunFinished#StateStoreCommitterRunFinished(String, Instant, Instant)}
   *   <li>{@link StateStoreCommitterRunFinished#toString()}
   *   <li>{@link StateStoreCommitterRunFinished#getFinishTime()}
   *   <li>{@link StateStoreCommitterRunFinished#getLogStream()}
   *   <li>{@link StateStoreCommitterRunFinished#getTimeInCommitter()}
   *   <li>{@link StateStoreCommitterRunFinished#getTimestamp()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitterRunFinished.<init>(String, Instant, Instant)",
      "Instant StateStoreCommitterRunFinished.getFinishTime()", "String StateStoreCommitterRunFinished.getLogStream()",
      "Instant StateStoreCommitterRunFinished.getTimeInCommitter()",
      "Instant StateStoreCommitterRunFinished.getTimestamp()", "String StateStoreCommitterRunFinished.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    StateStoreCommitterRunFinished actualStateStoreCommitterRunFinished = new StateStoreCommitterRunFinished(
        "Log Stream", timestamp, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    String actualToStringResult = actualStateStoreCommitterRunFinished.toString();
    Instant actualFinishTime = actualStateStoreCommitterRunFinished.getFinishTime();
    String actualLogStream = actualStateStoreCommitterRunFinished.getLogStream();
    Instant actualTimeInCommitter = actualStateStoreCommitterRunFinished.getTimeInCommitter();
    Instant actualTimestamp = actualStateStoreCommitterRunFinished.getTimestamp();

    // Assert
    assertEquals("Log Stream", actualLogStream);
    assertEquals("StateStoreCommitterRunFinished{logStream=Log Stream, timestamp=1970-01-01T00:00:00Z, finishTime=1970"
        + "-01-01T00:00:00Z}", actualToStringResult);
    Instant instant = actualTimestamp.EPOCH;
    assertSame(instant, actualFinishTime);
    assertSame(instant, actualTimeInCommitter);
    assertSame(instant, actualTimestamp);
  }

  /**
   * Test {@link StateStoreCommitterRunFinished#equals(Object)}, and {@link StateStoreCommitterRunFinished#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRunFinished#equals(Object)}
   *   <li>{@link StateStoreCommitterRunFinished#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunFinished.equals(Object)",
      "int StateStoreCommitterRunFinished.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunFinished stateStoreCommitterRunFinished = new StateStoreCommitterRunFinished("Log Stream",
        timestamp, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunFinished stateStoreCommitterRunFinished2 = new StateStoreCommitterRunFinished("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(stateStoreCommitterRunFinished, stateStoreCommitterRunFinished2);
    int expectedHashCodeResult = stateStoreCommitterRunFinished.hashCode();
    assertEquals(expectedHashCodeResult, stateStoreCommitterRunFinished2.hashCode());
  }

  /**
   * Test {@link StateStoreCommitterRunFinished#equals(Object)}, and {@link StateStoreCommitterRunFinished#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRunFinished#equals(Object)}
   *   <li>{@link StateStoreCommitterRunFinished#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunFinished.equals(Object)",
      "int StateStoreCommitterRunFinished.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunFinished stateStoreCommitterRunFinished = new StateStoreCommitterRunFinished("Log Stream",
        timestamp, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(stateStoreCommitterRunFinished, stateStoreCommitterRunFinished);
    int expectedHashCodeResult = stateStoreCommitterRunFinished.hashCode();
    assertEquals(expectedHashCodeResult, stateStoreCommitterRunFinished.hashCode());
  }

  /**
   * Test {@link StateStoreCommitterRunFinished#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRunFinished#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunFinished.equals(Object)",
      "int StateStoreCommitterRunFinished.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunFinished stateStoreCommitterRunFinished = new StateStoreCommitterRunFinished(
        "sleeper.clients.status.report.statestore.StateStoreCommitterRunFinished", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitterRunFinished, new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitterRunFinished#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRunFinished#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunFinished.equals(Object)",
      "int StateStoreCommitterRunFinished.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Instant timestamp = LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunFinished stateStoreCommitterRunFinished = new StateStoreCommitterRunFinished("Log Stream",
        timestamp, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitterRunFinished, new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitterRunFinished#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRunFinished#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunFinished.equals(Object)",
      "int StateStoreCommitterRunFinished.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunFinished stateStoreCommitterRunFinished = new StateStoreCommitterRunFinished("Log Stream",
        timestamp, LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitterRunFinished, new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitterRunFinished#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRunFinished#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunFinished.equals(Object)",
      "int StateStoreCommitterRunFinished.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
  }

  /**
   * Test {@link StateStoreCommitterRunFinished#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRunFinished#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunFinished.equals(Object)",
      "int StateStoreCommitterRunFinished.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(
        new StateStoreCommitterRunFinished("Log Stream", timestamp,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to StateStoreCommitterRunFinished");
  }
}
