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

class StateStoreCommitterRunStartedDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRunStarted#StateStoreCommitterRunStarted(String, Instant, Instant)}
   *   <li>{@link StateStoreCommitterRunStarted#toString()}
   *   <li>{@link StateStoreCommitterRunStarted#getLogStream()}
   *   <li>{@link StateStoreCommitterRunStarted#getStartTime()}
   *   <li>{@link StateStoreCommitterRunStarted#getTimeInCommitter()}
   *   <li>{@link StateStoreCommitterRunStarted#getTimestamp()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitterRunStarted.<init>(String, Instant, Instant)",
      "String StateStoreCommitterRunStarted.getLogStream()", "Instant StateStoreCommitterRunStarted.getStartTime()",
      "Instant StateStoreCommitterRunStarted.getTimeInCommitter()",
      "Instant StateStoreCommitterRunStarted.getTimestamp()", "String StateStoreCommitterRunStarted.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    StateStoreCommitterRunStarted actualStateStoreCommitterRunStarted = new StateStoreCommitterRunStarted("Log Stream",
        timestamp, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    String actualToStringResult = actualStateStoreCommitterRunStarted.toString();
    String actualLogStream = actualStateStoreCommitterRunStarted.getLogStream();
    Instant actualStartTime = actualStateStoreCommitterRunStarted.getStartTime();
    Instant actualTimeInCommitter = actualStateStoreCommitterRunStarted.getTimeInCommitter();
    Instant actualTimestamp = actualStateStoreCommitterRunStarted.getTimestamp();

    // Assert
    assertEquals("Log Stream", actualLogStream);
    assertEquals("StateStoreCommitterRunStarted{logStream=Log Stream, timestamp=1970-01-01T00:00:00Z, startTime=1970-01"
        + "-01T00:00:00Z}", actualToStringResult);
    Instant instant = actualTimestamp.EPOCH;
    assertSame(instant, actualStartTime);
    assertSame(instant, actualTimeInCommitter);
    assertSame(instant, actualTimestamp);
  }

  /**
   * Test {@link StateStoreCommitterRunStarted#equals(Object)}, and {@link StateStoreCommitterRunStarted#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRunStarted#equals(Object)}
   *   <li>{@link StateStoreCommitterRunStarted#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunStarted.equals(Object)",
      "int StateStoreCommitterRunStarted.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunStarted stateStoreCommitterRunStarted = new StateStoreCommitterRunStarted("Log Stream",
        timestamp, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunStarted stateStoreCommitterRunStarted2 = new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(stateStoreCommitterRunStarted, stateStoreCommitterRunStarted2);
    int expectedHashCodeResult = stateStoreCommitterRunStarted.hashCode();
    assertEquals(expectedHashCodeResult, stateStoreCommitterRunStarted2.hashCode());
  }

  /**
   * Test {@link StateStoreCommitterRunStarted#equals(Object)}, and {@link StateStoreCommitterRunStarted#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRunStarted#equals(Object)}
   *   <li>{@link StateStoreCommitterRunStarted#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunStarted.equals(Object)",
      "int StateStoreCommitterRunStarted.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunStarted stateStoreCommitterRunStarted = new StateStoreCommitterRunStarted("Log Stream",
        timestamp, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(stateStoreCommitterRunStarted, stateStoreCommitterRunStarted);
    int expectedHashCodeResult = stateStoreCommitterRunStarted.hashCode();
    assertEquals(expectedHashCodeResult, stateStoreCommitterRunStarted.hashCode());
  }

  /**
   * Test {@link StateStoreCommitterRunStarted#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRunStarted#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunStarted.equals(Object)",
      "int StateStoreCommitterRunStarted.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunStarted stateStoreCommitterRunStarted = new StateStoreCommitterRunStarted(
        "sleeper.clients.status.report.statestore.StateStoreCommitterRunStarted", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitterRunStarted, new StateStoreCommitterRunStarted("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitterRunStarted#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRunStarted#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunStarted.equals(Object)",
      "int StateStoreCommitterRunStarted.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Instant timestamp = LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunStarted stateStoreCommitterRunStarted = new StateStoreCommitterRunStarted("Log Stream",
        timestamp, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitterRunStarted, new StateStoreCommitterRunStarted("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitterRunStarted#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRunStarted#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunStarted.equals(Object)",
      "int StateStoreCommitterRunStarted.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunStarted stateStoreCommitterRunStarted = new StateStoreCommitterRunStarted("Log Stream",
        timestamp, LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitterRunStarted, new StateStoreCommitterRunStarted("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitterRunStarted#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRunStarted#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunStarted.equals(Object)",
      "int StateStoreCommitterRunStarted.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(new StateStoreCommitterRunStarted("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
  }

  /**
   * Test {@link StateStoreCommitterRunStarted#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRunStarted#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRunStarted.equals(Object)",
      "int StateStoreCommitterRunStarted.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(
        new StateStoreCommitterRunStarted("Log Stream", timestamp,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to StateStoreCommitterRunStarted");
  }
}
