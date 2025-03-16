package sleeper.systemtest.drivers.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.statestore.StateStoreCommitSummary;
import sleeper.clients.status.report.statestore.StateStoreCommitterLogEntry;
import sleeper.clients.status.report.statestore.StateStoreCommitterRunFinished;
import sleeper.clients.status.report.statestore.StateStoreCommitterRunStarted;

class StateStoreCommitterLogEntriesDiffblueTest {
  /**
   * Test {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}.
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.countNumCommitsByTableId(Set)"})
  void testCountNumCommitsByTableId() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.countNumCommitsByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}.
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.countNumCommitsByTableId(Set)"})
  void testCountNumCommitsByTableId2() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.countNumCommitsByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}.
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.countNumCommitsByTableId(Set)"})
  void testCountNumCommitsByTableId3() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.countNumCommitsByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HashSet#HashSet()} add {@code 42}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(Set); given '42'; when HashSet() add '42'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.countNumCommitsByTableId(Set)"})
  void testCountNumCommitsByTableId_given42_whenHashSetAdd42_thenReturnEmpty() {
    // Arrange
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(new ArrayList<>());

    HashSet<String> tableIds = new HashSet<>();
    tableIds.add("42");
    tableIds.add("foo");

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.countNumCommitsByTableId(tableIds).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link HashSet#HashSet()} add {@code foo}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(Set); given 'foo'; when HashSet() add 'foo'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.countNumCommitsByTableId(Set)"})
  void testCountNumCommitsByTableId_givenFoo_whenHashSetAddFoo_thenReturnEmpty() {
    // Arrange
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(new ArrayList<>());

    HashSet<String> tableIds = new HashSet<>();
    tableIds.add("foo");

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.countNumCommitsByTableId(tableIds).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(Set); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.countNumCommitsByTableId(Set)"})
  void testCountNumCommitsByTableId_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    HashSet<String> tableIds = new HashSet<>();
    tableIds.add("42");

    // Act
    Map<String, Integer> actualCountNumCommitsByTableIdResult = stateStoreCommitterLogEntries
        .countNumCommitsByTableId(tableIds);

    // Assert
    assertEquals(1, actualCountNumCommitsByTableIdResult.size());
    assertEquals(1, actualCountNumCommitsByTableIdResult.get("42").intValue());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}.
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#countNumCommitsByTableId(Set)}
   */
  @Test
  @DisplayName("Test countNumCommitsByTableId(Set); when HashSet(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.countNumCommitsByTableId(Set)"})
  void testCountNumCommitsByTableId_whenHashSet_thenReturnEmpty() {
    // Arrange
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(new ArrayList<>());

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.countNumCommitsByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId2() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("42", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId3() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunStarted("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId4() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId5() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunStarted("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId6() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunStarted("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunStarted("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId7() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunStarted("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitSummary("Log Stream", timestamp2, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(new HashSet<>()).isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set); given '42'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId_given42_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(logs);

    HashSet<String> tableIds = new HashSet<>();
    tableIds.add("42");

    // Act
    Map<String, Double> actualComputeOverallCommitsPerSecondByTableIdResult = stateStoreCommitterLogEntries
        .computeOverallCommitsPerSecondByTableId(tableIds);

    // Assert
    assertEquals(1, actualComputeOverallCommitsPerSecondByTableIdResult.size());
    assertEquals(0.0d, actualComputeOverallCommitsPerSecondByTableIdResult.get("42").doubleValue());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set); given '42'; then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId_given42_thenReturnSizeIsTwo() {
    // Arrange
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(new ArrayList<>());

    HashSet<String> tableIds = new HashSet<>();
    tableIds.add("42");
    tableIds.add("foo");

    // Act
    Map<String, Double> actualComputeOverallCommitsPerSecondByTableIdResult = stateStoreCommitterLogEntries
        .computeOverallCommitsPerSecondByTableId(tableIds);

    // Assert
    assertEquals(2, actualComputeOverallCommitsPerSecondByTableIdResult.size());
    assertEquals(0.0d, actualComputeOverallCommitsPerSecondByTableIdResult.get("42").doubleValue());
    assertEquals(0.0d, actualComputeOverallCommitsPerSecondByTableIdResult.get("foo").doubleValue());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set); given 'foo'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId_givenFoo_thenReturnSizeIsOne() {
    // Arrange
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(new ArrayList<>());

    HashSet<String> tableIds = new HashSet<>();
    tableIds.add("foo");

    // Act
    Map<String, Double> actualComputeOverallCommitsPerSecondByTableIdResult = stateStoreCommitterLogEntries
        .computeOverallCommitsPerSecondByTableId(tableIds);

    // Assert
    assertEquals(1, actualComputeOverallCommitsPerSecondByTableIdResult.size());
    assertEquals(0.0d, actualComputeOverallCommitsPerSecondByTableIdResult.get("foo").doubleValue());
  }

  /**
   * Test {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}.
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterLogEntries#computeOverallCommitsPerSecondByTableId(Set)}
   */
  @Test
  @DisplayName("Test computeOverallCommitsPerSecondByTableId(Set); when HashSet(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(Set)"})
  void testComputeOverallCommitsPerSecondByTableId_whenHashSet_thenReturnEmpty() {
    // Arrange
    StateStoreCommitterLogEntries stateStoreCommitterLogEntries = new StateStoreCommitterLogEntries(new ArrayList<>());

    // Act and Assert
    assertTrue(stateStoreCommitterLogEntries.computeOverallCommitsPerSecondByTableId(new HashSet<>()).isEmpty());
  }
}
