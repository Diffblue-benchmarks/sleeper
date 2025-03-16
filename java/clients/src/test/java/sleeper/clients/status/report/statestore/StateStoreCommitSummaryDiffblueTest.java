package sleeper.clients.status.report.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StateStoreCommitSummaryDiffblueTest {
  /**
   * Test {@link StateStoreCommitSummary#StateStoreCommitSummary(String, Instant, String, String, Instant)}.
   * <p>
   * Method under test: {@link StateStoreCommitSummary#StateStoreCommitSummary(String, Instant, String, String, Instant)}
   */
  @Test
  @DisplayName("Test new StateStoreCommitSummary(String, Instant, String, String, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitSummary.<init>(String, Instant, String, String, Instant)"})
  void testNewStateStoreCommitSummary() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Instant finishTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    StateStoreCommitSummary actualStateStoreCommitSummary = new StateStoreCommitSummary("Log Stream", timestamp, "42",
        "Type", finishTime);

    // Assert
    assertEquals("42", actualStateStoreCommitSummary.getTableId());
    assertEquals("Log Stream", actualStateStoreCommitSummary.getLogStream());
    assertEquals("Type", actualStateStoreCommitSummary.getType());
    Instant instant = finishTime.EPOCH;
    assertSame(instant, actualStateStoreCommitSummary.getFinishTime());
    assertSame(instant, actualStateStoreCommitSummary.getTimeInCommitter());
    assertSame(instant, actualStateStoreCommitSummary.getTimestamp());
  }

  /**
   * Test {@link StateStoreCommitSummary#countNumCommitsByTableId(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitSummary#countNumCommitsByTableId(List)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitSummary.countNumCommitsByTableId(List)"})
  void testCountNumCommitsByTableId() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> entries = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    entries.add(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    Map<String, Integer> actualCountNumCommitsByTableIdResult = StateStoreCommitSummary
        .countNumCommitsByTableId(entries);

    // Assert
    assertTrue(actualCountNumCommitsByTableIdResult.isEmpty());
  }

  /**
   * Test {@link StateStoreCommitSummary#countNumCommitsByTableId(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitSummary#countNumCommitsByTableId(List)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitSummary.countNumCommitsByTableId(List)"})
  void testCountNumCommitsByTableId2() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> entries = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    entries.add(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    entries.add(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    Map<String, Integer> actualCountNumCommitsByTableIdResult = StateStoreCommitSummary
        .countNumCommitsByTableId(entries);

    // Assert
    assertTrue(actualCountNumCommitsByTableIdResult.isEmpty());
  }

  /**
   * Test {@link StateStoreCommitSummary#countNumCommitsByTableId(List)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitSummary#countNumCommitsByTableId(List)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(List); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitSummary.countNumCommitsByTableId(List)"})
  void testCountNumCommitsByTableId_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> entries = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    entries.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    Map<String, Integer> actualCountNumCommitsByTableIdResult = StateStoreCommitSummary
        .countNumCommitsByTableId(entries);

    // Assert
    assertEquals(1, actualCountNumCommitsByTableIdResult.size());
    assertEquals(1, actualCountNumCommitsByTableIdResult.get("42").intValue());
  }

  /**
   * Test {@link StateStoreCommitSummary#countNumCommitsByTableId(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitSummary#countNumCommitsByTableId(List)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(List); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitSummary.countNumCommitsByTableId(List)"})
  void testCountNumCommitsByTableId_whenArrayList_thenReturnEmpty() {
    // Arrange and Act
    Map<String, Integer> actualCountNumCommitsByTableIdResult = StateStoreCommitSummary
        .countNumCommitsByTableId(new ArrayList<>());

    // Assert
    assertTrue(actualCountNumCommitsByTableIdResult.isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitSummary#toString()}
   *   <li>{@link StateStoreCommitSummary#getFinishTime()}
   *   <li>{@link StateStoreCommitSummary#getLogStream()}
   *   <li>{@link StateStoreCommitSummary#getTableId()}
   *   <li>{@link StateStoreCommitSummary#getTimeInCommitter()}
   *   <li>{@link StateStoreCommitSummary#getTimestamp()}
   *   <li>{@link StateStoreCommitSummary#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant StateStoreCommitSummary.getFinishTime()", "String StateStoreCommitSummary.getLogStream()",
      "String StateStoreCommitSummary.getTableId()", "Instant StateStoreCommitSummary.getTimeInCommitter()",
      "Instant StateStoreCommitSummary.getTimestamp()", "String StateStoreCommitSummary.getType()",
      "String StateStoreCommitSummary.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitSummary stateStoreCommitSummary = new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    String actualToStringResult = stateStoreCommitSummary.toString();
    Instant actualFinishTime = stateStoreCommitSummary.getFinishTime();
    String actualLogStream = stateStoreCommitSummary.getLogStream();
    String actualTableId = stateStoreCommitSummary.getTableId();
    Instant actualTimeInCommitter = stateStoreCommitSummary.getTimeInCommitter();
    Instant actualTimestamp = stateStoreCommitSummary.getTimestamp();

    // Assert
    assertEquals("42", actualTableId);
    assertEquals("Log Stream", actualLogStream);
    assertEquals("StateStoreCommitSummary{logStream=Log Stream, timestamp=1970-01-01T00:00:00Z, tableId=42, type=Type,"
        + " finishTime=1970-01-01T00:00:00Z}", actualToStringResult);
    assertEquals("Type", stateStoreCommitSummary.getType());
    Instant instant = actualTimestamp.EPOCH;
    assertSame(instant, actualFinishTime);
    assertSame(instant, actualTimeInCommitter);
    assertSame(instant, actualTimestamp);
  }

  /**
   * Test {@link StateStoreCommitSummary#equals(Object)}, and {@link StateStoreCommitSummary#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitSummary#equals(Object)}
   *   <li>{@link StateStoreCommitSummary#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitSummary.equals(Object)", "int StateStoreCommitSummary.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitSummary stateStoreCommitSummary = new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitSummary stateStoreCommitSummary2 = new StateStoreCommitSummary("Log Stream", timestamp2, "42",
        "Type", LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(stateStoreCommitSummary, stateStoreCommitSummary2);
    int expectedHashCodeResult = stateStoreCommitSummary.hashCode();
    assertEquals(expectedHashCodeResult, stateStoreCommitSummary2.hashCode());
  }

  /**
   * Test {@link StateStoreCommitSummary#equals(Object)}, and {@link StateStoreCommitSummary#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitSummary#equals(Object)}
   *   <li>{@link StateStoreCommitSummary#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitSummary.equals(Object)", "int StateStoreCommitSummary.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitSummary stateStoreCommitSummary = new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(stateStoreCommitSummary, stateStoreCommitSummary);
    int expectedHashCodeResult = stateStoreCommitSummary.hashCode();
    assertEquals(expectedHashCodeResult, stateStoreCommitSummary.hashCode());
  }

  /**
   * Test {@link StateStoreCommitSummary#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitSummary#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitSummary.equals(Object)", "int StateStoreCommitSummary.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitSummary stateStoreCommitSummary = new StateStoreCommitSummary("java.lang.String", timestamp, "42",
        "Type", LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitSummary, new StateStoreCommitSummary("Log Stream", timestamp2, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitSummary#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitSummary#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitSummary.equals(Object)", "int StateStoreCommitSummary.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Instant timestamp = LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitSummary stateStoreCommitSummary = new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitSummary, new StateStoreCommitSummary("Log Stream", timestamp2, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitSummary#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitSummary#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitSummary.equals(Object)", "int StateStoreCommitSummary.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitSummary stateStoreCommitSummary = new StateStoreCommitSummary("Log Stream", timestamp, "Table Id",
        "Type", LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitSummary, new StateStoreCommitSummary("Log Stream", timestamp2, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitSummary#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitSummary#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitSummary.equals(Object)", "int StateStoreCommitSummary.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitSummary stateStoreCommitSummary = new StateStoreCommitSummary("Log Stream", timestamp, "42",
        "java.lang.String", LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitSummary, new StateStoreCommitSummary("Log Stream", timestamp2, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitSummary#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitSummary#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitSummary.equals(Object)", "int StateStoreCommitSummary.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitSummary stateStoreCommitSummary = new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(stateStoreCommitSummary, new StateStoreCommitSummary("Log Stream", timestamp2, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link StateStoreCommitSummary#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitSummary#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitSummary.equals(Object)", "int StateStoreCommitSummary.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
  }

  /**
   * Test {@link StateStoreCommitSummary#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitSummary#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitSummary.equals(Object)", "int StateStoreCommitSummary.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(
        new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to StateStoreCommitSummary");
  }
}
