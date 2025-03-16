package sleeper.clients.status.report.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import sleeper.clients.status.report.statestore.StateStoreCommitterRun.Builder;

class StateStoreCommitterRequestsPerSecondDiffblueTest {
  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}
   */
  @Test
  @DisplayName("Test fromRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitterRequestsPerSecond StateStoreCommitterRequestsPerSecond.fromRuns(List)"})
  void testFromRuns() {
    // Arrange
    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();
    runs.add(buildResult);

    // Act
    StateStoreCommitterRequestsPerSecond actualFromRunsResult = StateStoreCommitterRequestsPerSecond.fromRuns(runs);

    // Assert
    assertEquals(0.0d, actualFromRunsResult.getAverageRequestsPerSecondInRuns());
    assertEquals(0.0d, actualFromRunsResult.getAverageRequestsPerSecondOverall());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}
   */
  @Test
  @DisplayName("Test fromRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitterRequestsPerSecond StateStoreCommitterRequestsPerSecond.fromRuns(List)"})
  void testFromRuns2() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp3, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act
    StateStoreCommitterRequestsPerSecond actualFromRunsResult = StateStoreCommitterRequestsPerSecond.fromRuns(runs);

    // Assert
    assertEquals(0.0d, actualFromRunsResult.getAverageRequestsPerSecondInRuns());
    assertEquals(0.0d, actualFromRunsResult.getAverageRequestsPerSecondOverall());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}
   */
  @Test
  @DisplayName("Test fromRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitterRequestsPerSecond StateStoreCommitterRequestsPerSecond.fromRuns(List)"})
  void testFromRuns3() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp3, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.fromRuns(runs).getAverageRequestsPerSecondInRuns());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}
   */
  @Test
  @DisplayName("Test fromRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitterRequestsPerSecond StateStoreCommitterRequestsPerSecond.fromRuns(List)"})
  void testFromRuns4() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder logStreamResult = StateStoreCommitterRun.builder().commits(commits).finish(null).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act
    StateStoreCommitterRequestsPerSecond actualFromRunsResult = StateStoreCommitterRequestsPerSecond.fromRuns(runs);

    // Assert
    assertEquals(0.0d, actualFromRunsResult.getAverageRequestsPerSecondInRuns());
    assertEquals(0.0d, actualFromRunsResult.getAverageRequestsPerSecondOverall());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}
   */
  @Test
  @DisplayName("Test fromRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitterRequestsPerSecond StateStoreCommitterRequestsPerSecond.fromRuns(List)"})
  void testFromRuns5() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = commitsResult
        .finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .logStream("Log Stream")
        .start(null)
        .build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act
    StateStoreCommitterRequestsPerSecond actualFromRunsResult = StateStoreCommitterRequestsPerSecond.fromRuns(runs);

    // Assert
    assertEquals(0.0d, actualFromRunsResult.getAverageRequestsPerSecondInRuns());
    assertEquals(0.0d, actualFromRunsResult.getAverageRequestsPerSecondOverall());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return AverageRequestsPerSecondOverall is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#fromRuns(List)}
   */
  @Test
  @DisplayName("Test fromRuns(List); when ArrayList(); then return AverageRequestsPerSecondOverall is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitterRequestsPerSecond StateStoreCommitterRequestsPerSecond.fromRuns(List)"})
  void testFromRuns_whenArrayList_thenReturnAverageRequestsPerSecondOverallIsZero() {
    // Arrange and Act
    StateStoreCommitterRequestsPerSecond actualFromRunsResult = StateStoreCommitterRequestsPerSecond
        .fromRuns(new ArrayList<>());

    // Assert
    assertEquals(0.0d, actualFromRunsResult.getAverageRequestsPerSecondInRuns());
    assertEquals(0.0d, actualFromRunsResult.getAverageRequestsPerSecondOverall());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}
   */
  @Test
  @DisplayName("Test byTableIdFromRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRequestsPerSecond.byTableIdFromRuns(List)"})
  void testByTableIdFromRuns() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp3, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act
    Map<String, StateStoreCommitterRequestsPerSecond> actualByTableIdFromRunsResult = StateStoreCommitterRequestsPerSecond
        .byTableIdFromRuns(runs);

    // Assert
    assertEquals(1, actualByTableIdFromRunsResult.size());
    assertEquals(0.0d, actualByTableIdFromRunsResult.get("42").getAverageRequestsPerSecondInRuns());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}
   */
  @Test
  @DisplayName("Test byTableIdFromRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRequestsPerSecond.byTableIdFromRuns(List)"})
  void testByTableIdFromRuns2() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp3, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act
    Map<String, StateStoreCommitterRequestsPerSecond> actualByTableIdFromRunsResult = StateStoreCommitterRequestsPerSecond
        .byTableIdFromRuns(runs);

    // Assert
    assertEquals(1, actualByTableIdFromRunsResult.size());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}
   */
  @Test
  @DisplayName("Test byTableIdFromRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRequestsPerSecond.byTableIdFromRuns(List)"})
  void testByTableIdFromRuns3() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder logStreamResult = StateStoreCommitterRun.builder().commits(commits).finish(null).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act
    Map<String, StateStoreCommitterRequestsPerSecond> actualByTableIdFromRunsResult = StateStoreCommitterRequestsPerSecond
        .byTableIdFromRuns(runs);

    // Assert
    assertEquals(1, actualByTableIdFromRunsResult.size());
    StateStoreCommitterRequestsPerSecond getResult = actualByTableIdFromRunsResult.get("42");
    assertEquals(0.0d, getResult.getAverageRequestsPerSecondInRuns());
    assertEquals(0.0d, getResult.getAverageRequestsPerSecondOverall());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}
   */
  @Test
  @DisplayName("Test byTableIdFromRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRequestsPerSecond.byTableIdFromRuns(List)"})
  void testByTableIdFromRuns4() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = commitsResult
        .finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .logStream("Log Stream")
        .start(null)
        .build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act
    Map<String, StateStoreCommitterRequestsPerSecond> actualByTableIdFromRunsResult = StateStoreCommitterRequestsPerSecond
        .byTableIdFromRuns(runs);

    // Assert
    assertEquals(1, actualByTableIdFromRunsResult.size());
    StateStoreCommitterRequestsPerSecond getResult = actualByTableIdFromRunsResult.get("42");
    assertEquals(0.0d, getResult.getAverageRequestsPerSecondInRuns());
    assertEquals(0.0d, getResult.getAverageRequestsPerSecondOverall());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}.
   * <ul>
   *   <li>Then return {@code 42} AverageRequestsPerSecondOverall is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}
   */
  @Test
  @DisplayName("Test byTableIdFromRuns(List); then return '42' AverageRequestsPerSecondOverall is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRequestsPerSecond.byTableIdFromRuns(List)"})
  void testByTableIdFromRuns_thenReturn42AverageRequestsPerSecondOverallIsZero() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp3, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act
    Map<String, StateStoreCommitterRequestsPerSecond> actualByTableIdFromRunsResult = StateStoreCommitterRequestsPerSecond
        .byTableIdFromRuns(runs);

    // Assert
    assertEquals(1, actualByTableIdFromRunsResult.size());
    StateStoreCommitterRequestsPerSecond getResult = actualByTableIdFromRunsResult.get("42");
    assertEquals(0.0d, getResult.getAverageRequestsPerSecondInRuns());
    assertEquals(0.0d, getResult.getAverageRequestsPerSecondOverall());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}
   */
  @Test
  @DisplayName("Test byTableIdFromRuns(List); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRequestsPerSecond.byTableIdFromRuns(List)"})
  void testByTableIdFromRuns_thenReturnEmpty() {
    // Arrange
    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();
    runs.add(buildResult);

    // Act
    Map<String, StateStoreCommitterRequestsPerSecond> actualByTableIdFromRunsResult = StateStoreCommitterRequestsPerSecond
        .byTableIdFromRuns(runs);

    // Assert
    assertTrue(actualByTableIdFromRunsResult.isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}
   */
  @Test
  @DisplayName("Test byTableIdFromRuns(List); then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRequestsPerSecond.byTableIdFromRuns(List)"})
  void testByTableIdFromRuns_thenReturnSizeIsTwo() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "Table Id", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp2, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp3,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp4 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp4, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act
    Map<String, StateStoreCommitterRequestsPerSecond> actualByTableIdFromRunsResult = StateStoreCommitterRequestsPerSecond
        .byTableIdFromRuns(runs);

    // Assert
    assertEquals(2, actualByTableIdFromRunsResult.size());
    StateStoreCommitterRequestsPerSecond getResult = actualByTableIdFromRunsResult.get("Table Id");
    assertEquals(0.0d, getResult.getAverageRequestsPerSecondInRuns());
    assertEquals(0.0d, getResult.getAverageRequestsPerSecondOverall());
    assertEquals(getResult, actualByTableIdFromRunsResult.get("42"));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#byTableIdFromRuns(List)}
   */
  @Test
  @DisplayName("Test byTableIdFromRuns(List); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRequestsPerSecond.byTableIdFromRuns(List)"})
  void testByTableIdFromRuns_whenArrayList_thenReturnEmpty() {
    // Arrange and Act
    Map<String, StateStoreCommitterRequestsPerSecond> actualByTableIdFromRunsResult = StateStoreCommitterRequestsPerSecond
        .byTableIdFromRuns(new ArrayList<>());

    // Assert
    assertTrue(actualByTableIdFromRunsResult.isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#averageRequestsPerSecondInRunsAndOverall(double, double)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#averageRequestsPerSecondInRunsAndOverall(double, double)}
   */
  @Test
  @DisplayName("Test averageRequestsPerSecondInRunsAndOverall(double, double)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreCommitterRequestsPerSecond StateStoreCommitterRequestsPerSecond.averageRequestsPerSecondInRunsAndOverall(double, double)"})
  void testAverageRequestsPerSecondInRunsAndOverall() {
    // Arrange and Act
    StateStoreCommitterRequestsPerSecond actualAverageRequestsPerSecondInRunsAndOverallResult = StateStoreCommitterRequestsPerSecond
        .averageRequestsPerSecondInRunsAndOverall(10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualAverageRequestsPerSecondInRunsAndOverallResult.getAverageRequestsPerSecondInRuns());
    assertEquals(10.0d, actualAverageRequestsPerSecondInRunsAndOverallResult.getAverageRequestsPerSecondOverall());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRequestsPerSecond#toString()}
   *   <li>{@link StateStoreCommitterRequestsPerSecond#getAverageRequestsPerSecondInRuns()}
   *   <li>{@link StateStoreCommitterRequestsPerSecond#getAverageRequestsPerSecondOverall()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.getAverageRequestsPerSecondInRuns()",
      "double StateStoreCommitterRequestsPerSecond.getAverageRequestsPerSecondOverall()",
      "String StateStoreCommitterRequestsPerSecond.toString()"})
  void testGettersAndSetters() {
    // Arrange
    StateStoreCommitterRequestsPerSecond averageRequestsPerSecondInRunsAndOverallResult = StateStoreCommitterRequestsPerSecond
        .averageRequestsPerSecondInRunsAndOverall(10.0d, 10.0d);

    // Act
    String actualToStringResult = averageRequestsPerSecondInRunsAndOverallResult.toString();
    double actualAverageRequestsPerSecondInRuns = averageRequestsPerSecondInRunsAndOverallResult
        .getAverageRequestsPerSecondInRuns();

    // Assert
    assertEquals("StateStoreCommitterReport{averageRequestsPerSecondInRuns=10.0, averageRequestsPerSecondOverall=10.0}",
        actualToStringResult);
    assertEquals(10.0d, actualAverageRequestsPerSecondInRuns);
    assertEquals(10.0d, averageRequestsPerSecondInRunsAndOverallResult.getAverageRequestsPerSecondOverall());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#equals(Object)}, and {@link StateStoreCommitterRequestsPerSecond#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRequestsPerSecond#equals(Object)}
   *   <li>{@link StateStoreCommitterRequestsPerSecond#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRequestsPerSecond.equals(Object)",
      "int StateStoreCommitterRequestsPerSecond.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    StateStoreCommitterRequestsPerSecond averageRequestsPerSecondInRunsAndOverallResult = StateStoreCommitterRequestsPerSecond
        .averageRequestsPerSecondInRunsAndOverall(10.0d, 10.0d);
    StateStoreCommitterRequestsPerSecond averageRequestsPerSecondInRunsAndOverallResult2 = StateStoreCommitterRequestsPerSecond
        .averageRequestsPerSecondInRunsAndOverall(10.0d, 10.0d);

    // Act and Assert
    assertEquals(averageRequestsPerSecondInRunsAndOverallResult, averageRequestsPerSecondInRunsAndOverallResult2);
    int expectedHashCodeResult = averageRequestsPerSecondInRunsAndOverallResult.hashCode();
    assertEquals(expectedHashCodeResult, averageRequestsPerSecondInRunsAndOverallResult2.hashCode());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#equals(Object)}, and {@link StateStoreCommitterRequestsPerSecond#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRequestsPerSecond#equals(Object)}
   *   <li>{@link StateStoreCommitterRequestsPerSecond#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRequestsPerSecond.equals(Object)",
      "int StateStoreCommitterRequestsPerSecond.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    StateStoreCommitterRequestsPerSecond averageRequestsPerSecondInRunsAndOverallResult = StateStoreCommitterRequestsPerSecond
        .averageRequestsPerSecondInRunsAndOverall(10.0d, 10.0d);

    // Act and Assert
    assertEquals(averageRequestsPerSecondInRunsAndOverallResult, averageRequestsPerSecondInRunsAndOverallResult);
    int expectedHashCodeResult = averageRequestsPerSecondInRunsAndOverallResult.hashCode();
    assertEquals(expectedHashCodeResult, averageRequestsPerSecondInRunsAndOverallResult.hashCode());
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRequestsPerSecond.equals(Object)",
      "int StateStoreCommitterRequestsPerSecond.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    StateStoreCommitterRequestsPerSecond averageRequestsPerSecondInRunsAndOverallResult = StateStoreCommitterRequestsPerSecond
        .averageRequestsPerSecondInRunsAndOverall(1.0d, 10.0d);

    // Act and Assert
    assertNotEquals(averageRequestsPerSecondInRunsAndOverallResult,
        StateStoreCommitterRequestsPerSecond.averageRequestsPerSecondInRunsAndOverall(10.0d, 10.0d));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRequestsPerSecond.equals(Object)",
      "int StateStoreCommitterRequestsPerSecond.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    StateStoreCommitterRequestsPerSecond averageRequestsPerSecondInRunsAndOverallResult = StateStoreCommitterRequestsPerSecond
        .averageRequestsPerSecondInRunsAndOverall(10.0d, 1.0d);

    // Act and Assert
    assertNotEquals(averageRequestsPerSecondInRunsAndOverallResult,
        StateStoreCommitterRequestsPerSecond.averageRequestsPerSecondInRunsAndOverall(10.0d, 10.0d));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRequestsPerSecond.equals(Object)",
      "int StateStoreCommitterRequestsPerSecond.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(StateStoreCommitterRequestsPerSecond.averageRequestsPerSecondInRunsAndOverall(10.0d, 10.0d), null);
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRequestsPerSecond.equals(Object)",
      "int StateStoreCommitterRequestsPerSecond.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(StateStoreCommitterRequestsPerSecond.averageRequestsPerSecondInRunsAndOverall(10.0d, 10.0d),
        "Different type to StateStoreCommitterRequestsPerSecond");
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}
   */
  @Test
  @DisplayName("Test computeAverageRequestsPerSecondInRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(List)"})
  void testComputeAverageRequestsPerSecondInRuns() {
    // Arrange
    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();
    runs.add(buildResult);

    // Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(runs));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}
   */
  @Test
  @DisplayName("Test computeAverageRequestsPerSecondInRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(List)"})
  void testComputeAverageRequestsPerSecondInRuns2() {
    // Arrange
    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();
    runs.add(buildResult);
    Builder builderResult2 = StateStoreCommitterRun.builder();
    Builder commitsResult2 = builderResult2.commits(new ArrayList<>());
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult2 = commitsResult2.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp3,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp4 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult2 = logStreamResult2.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp4, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();
    runs.add(buildResult2);

    // Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(runs));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}
   */
  @Test
  @DisplayName("Test computeAverageRequestsPerSecondInRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(List)"})
  void testComputeAverageRequestsPerSecondInRuns3() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp3, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(runs));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}
   */
  @Test
  @DisplayName("Test computeAverageRequestsPerSecondInRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(List)"})
  void testComputeAverageRequestsPerSecondInRuns4() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder logStreamResult = StateStoreCommitterRun.builder().commits(commits).finish(null).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(runs));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}
   */
  @Test
  @DisplayName("Test computeAverageRequestsPerSecondInRuns(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(List)"})
  void testComputeAverageRequestsPerSecondInRuns5() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = commitsResult
        .finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .logStream("Log Stream")
        .start(null)
        .build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(runs));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondInRuns(List)}
   */
  @Test
  @DisplayName("Test computeAverageRequestsPerSecondInRuns(List); when ArrayList(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(List)"})
  void testComputeAverageRequestsPerSecondInRuns_whenArrayList_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondInRuns(new ArrayList<>()));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondOverall(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondOverall(List)}
   */
  @Test
  @DisplayName("Test computeAverageRequestsPerSecondOverall(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondOverall(List)"})
  void testComputeAverageRequestsPerSecondOverall() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp3, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondOverall(runs));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondOverall(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondOverall(List)}
   */
  @Test
  @DisplayName("Test computeAverageRequestsPerSecondOverall(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondOverall(List)"})
  void testComputeAverageRequestsPerSecondOverall2() {
    // Arrange
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    commits.add(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Builder commitsResult = StateStoreCommitterRun.builder().commits(commits);
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = commitsResult
        .finish(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .logStream("Log Stream")
        .start(null)
        .build();

    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    runs.add(buildResult);

    // Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondOverall(runs));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondOverall(List)}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondOverall(List)}
   */
  @Test
  @DisplayName("Test computeAverageRequestsPerSecondOverall(List); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondOverall(List)"})
  void testComputeAverageRequestsPerSecondOverall_thenReturnZero() {
    // Arrange
    ArrayList<StateStoreCommitterRun> runs = new ArrayList<>();
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();
    runs.add(buildResult);

    // Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondOverall(runs));
  }

  /**
   * Test {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondOverall(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRequestsPerSecond#computeAverageRequestsPerSecondOverall(List)}
   */
  @Test
  @DisplayName("Test computeAverageRequestsPerSecondOverall(List); when ArrayList(); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondOverall(List)"})
  void testComputeAverageRequestsPerSecondOverall_whenArrayList_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, StateStoreCommitterRequestsPerSecond.computeAverageRequestsPerSecondOverall(new ArrayList<>()));
  }
}
