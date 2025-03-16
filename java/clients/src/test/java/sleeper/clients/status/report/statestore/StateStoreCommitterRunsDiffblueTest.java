package sleeper.clients.status.report.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import sleeper.clients.status.report.statestore.StateStoreCommitterRun.Builder;

class StateStoreCommitterRunsDiffblueTest {
  /**
   * Test {@link StateStoreCommitterRuns#findRunsByLogStream(List)}.
   * <ul>
   *   <li>Then return first Commits is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#findRunsByLogStream(List)}
   */
  @Test
  @DisplayName("Test findRunsByLogStream(List); then return first Commits is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StateStoreCommitterRuns.findRunsByLogStream(List)"})
  void testFindRunsByLogStream_thenReturnFirstCommitsIsArrayList() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitSummary("logStream must not be null", timestamp, "42", "logStream must not be null",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    List<StateStoreCommitterRun> actualFindRunsByLogStreamResult = StateStoreCommitterRuns.findRunsByLogStream(logs);

    // Assert
    assertEquals(1, actualFindRunsByLogStreamResult.size());
    assertEquals(logs, actualFindRunsByLogStreamResult.get(0).getCommits());
  }

  /**
   * Test {@link StateStoreCommitterRuns#findRunsByLogStream(List)}.
   * <ul>
   *   <li>Then return first Commits size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#findRunsByLogStream(List)}
   */
  @Test
  @DisplayName("Test findRunsByLogStream(List); then return first Commits size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StateStoreCommitterRuns.findRunsByLogStream(List)"})
  void testFindRunsByLogStream_thenReturnFirstCommitsSizeIsOne() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitSummary("logStream must not be null", timestamp, "42", "logStream must not be null",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("logStream must not be null", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp3,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    List<StateStoreCommitterRun> actualFindRunsByLogStreamResult = StateStoreCommitterRuns.findRunsByLogStream(logs);

    // Assert
    assertEquals(2, actualFindRunsByLogStreamResult.size());
    assertEquals(1, actualFindRunsByLogStreamResult.get(0).getCommits().size());
  }

  /**
   * Test {@link StateStoreCommitterRuns#findRunsByLogStream(List)}.
   * <ul>
   *   <li>Then return first FinishTime is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#findRunsByLogStream(List)}
   */
  @Test
  @DisplayName("Test findRunsByLogStream(List); then return first FinishTime is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StateStoreCommitterRuns.findRunsByLogStream(List)"})
  void testFindRunsByLogStream_thenReturnFirstFinishTimeIsNull() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunStarted("logStream must not be null", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    List<StateStoreCommitterRun> actualFindRunsByLogStreamResult = StateStoreCommitterRuns.findRunsByLogStream(logs);

    // Assert
    assertEquals(1, actualFindRunsByLogStreamResult.size());
    StateStoreCommitterRun getResult = actualFindRunsByLogStreamResult.get(0);
    assertNull(getResult.getFinishTime());
    Instant startTime = getResult.getStartTime();
    assertEquals(0, startTime.getNano());
    assertEquals(0L, startTime.getEpochSecond());
    assertTrue(getResult.getCommits().isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterRuns#findRunsByLogStream(List)}.
   * <ul>
   *   <li>Then return first LogStream is {@code Log Stream}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#findRunsByLogStream(List)}
   */
  @Test
  @DisplayName("Test findRunsByLogStream(List); then return first LogStream is 'Log Stream'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StateStoreCommitterRuns.findRunsByLogStream(List)"})
  void testFindRunsByLogStream_thenReturnFirstLogStreamIsLogStream() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    List<StateStoreCommitterRun> actualFindRunsByLogStreamResult = StateStoreCommitterRuns.findRunsByLogStream(logs);

    // Assert
    assertEquals(1, actualFindRunsByLogStreamResult.size());
    StateStoreCommitterRun getResult = actualFindRunsByLogStreamResult.get(0);
    assertEquals("Log Stream", getResult.getLogStream());
    Instant finishTime = getResult.getFinishTime();
    assertEquals(0, finishTime.getNano());
    assertEquals(0L, finishTime.getEpochSecond());
    assertTrue(getResult.getCommits().isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterRuns#findRunsByLogStream(List)}.
   * <ul>
   *   <li>Then return second FinishTime is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#findRunsByLogStream(List)}
   */
  @Test
  @DisplayName("Test findRunsByLogStream(List); then return second FinishTime is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StateStoreCommitterRuns.findRunsByLogStream(List)"})
  void testFindRunsByLogStream_thenReturnSecondFinishTimeIsNull() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitSummary("logStream must not be null", timestamp, "42", "logStream must not be null",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunStarted("logStream must not be null", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp3,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    List<StateStoreCommitterRun> actualFindRunsByLogStreamResult = StateStoreCommitterRuns.findRunsByLogStream(logs);

    // Assert
    assertEquals(3, actualFindRunsByLogStreamResult.size());
    StateStoreCommitterRun getResult = actualFindRunsByLogStreamResult.get(1);
    assertNull(getResult.getFinishTime());
    Instant startTime = getResult.getStartTime();
    assertEquals(0, startTime.getNano());
    assertEquals(0L, startTime.getEpochSecond());
    List<StateStoreCommitSummary> commits = actualFindRunsByLogStreamResult.get(0).getCommits();
    assertEquals(1, commits.size());
    StateStoreCommitSummary getResult2 = commits.get(0);
    assertSame(startTime, getResult2.getFinishTime());
    assertSame(startTime, getResult2.getTimeInCommitter());
    assertSame(startTime, getResult2.getTimestamp());
    assertSame(startTime, actualFindRunsByLogStreamResult.get(2).getFinishTime());
  }

  /**
   * Test {@link StateStoreCommitterRuns#findRunsByLogStream(List)}.
   * <ul>
   *   <li>Then return second FinishTime Nano is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#findRunsByLogStream(List)}
   */
  @Test
  @DisplayName("Test findRunsByLogStream(List); then return second FinishTime Nano is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StateStoreCommitterRuns.findRunsByLogStream(List)"})
  void testFindRunsByLogStream_thenReturnSecondFinishTimeNanoIsZero() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitSummary("logStream must not be null", timestamp, "42", "logStream must not be null",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitSummary stateStoreCommitSummary = new StateStoreCommitSummary("logStream must not be null",
        timestamp2, "42", "logStream must not be null",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    logs.add(stateStoreCommitSummary);
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp3,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    List<StateStoreCommitterRun> actualFindRunsByLogStreamResult = StateStoreCommitterRuns.findRunsByLogStream(logs);

    // Assert
    assertEquals(2, actualFindRunsByLogStreamResult.size());
    Instant finishTime = actualFindRunsByLogStreamResult.get(1).getFinishTime();
    assertEquals(0, finishTime.getNano());
    assertEquals(0L, finishTime.getEpochSecond());
    List<StateStoreCommitSummary> commits = actualFindRunsByLogStreamResult.get(0).getCommits();
    assertEquals(2, commits.size());
    assertSame(stateStoreCommitSummary, commits.get(1));
    StateStoreCommitSummary getResult = commits.get(0);
    assertSame(finishTime, getResult.getFinishTime());
    assertSame(finishTime, getResult.getTimeInCommitter());
    assertSame(finishTime, getResult.getTimestamp());
  }

  /**
   * Test {@link StateStoreCommitterRuns#findRunsByLogStream(List)}.
   * <ul>
   *   <li>Then return second LogStream is {@code Log Stream}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#findRunsByLogStream(List)}
   */
  @Test
  @DisplayName("Test findRunsByLogStream(List); then return second LogStream is 'Log Stream'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StateStoreCommitterRuns.findRunsByLogStream(List)"})
  void testFindRunsByLogStream_thenReturnSecondLogStreamIsLogStream() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("logStream must not be null", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    List<StateStoreCommitterRun> actualFindRunsByLogStreamResult = StateStoreCommitterRuns.findRunsByLogStream(logs);

    // Assert
    assertEquals(2, actualFindRunsByLogStreamResult.size());
    StateStoreCommitterRun getResult = actualFindRunsByLogStreamResult.get(1);
    assertEquals("Log Stream", getResult.getLogStream());
    assertNull(getResult.getStartTime());
    Instant finishTime = actualFindRunsByLogStreamResult.get(0).getFinishTime();
    assertEquals(0, finishTime.getNano());
    assertEquals(0L, finishTime.getEpochSecond());
    assertSame(finishTime, getResult.getFinishTime());
  }

  /**
   * Test {@link StateStoreCommitterRuns#findRunsByLogStream(List)}.
   * <ul>
   *   <li>Then return third LogStream is {@code Log Stream}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#findRunsByLogStream(List)}
   */
  @Test
  @DisplayName("Test findRunsByLogStream(List); then return third LogStream is 'Log Stream'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StateStoreCommitterRuns.findRunsByLogStream(List)"})
  void testFindRunsByLogStream_thenReturnThirdLogStreamIsLogStream() {
    // Arrange
    ArrayList<StateStoreCommitterLogEntry> logs = new ArrayList<>();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("logStream must not be null", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("logStream must not be null", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    logs.add(new StateStoreCommitterRunFinished("Log Stream", timestamp3,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    List<StateStoreCommitterRun> actualFindRunsByLogStreamResult = StateStoreCommitterRuns.findRunsByLogStream(logs);

    // Assert
    assertEquals(3, actualFindRunsByLogStreamResult.size());
    StateStoreCommitterRun getResult = actualFindRunsByLogStreamResult.get(2);
    assertEquals("Log Stream", getResult.getLogStream());
    assertEquals("logStream must not be null", actualFindRunsByLogStreamResult.get(1).getLogStream());
    assertNull(getResult.getStartTime());
    assertTrue(getResult.getCommits().isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterRuns#findRunsByLogStream(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#findRunsByLogStream(List)}
   */
  @Test
  @DisplayName("Test findRunsByLogStream(List); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StateStoreCommitterRuns.findRunsByLogStream(List)"})
  void testFindRunsByLogStream_whenArrayList_thenReturnEmpty() {
    // Arrange and Act
    List<StateStoreCommitterRun> actualFindRunsByLogStreamResult = StateStoreCommitterRuns
        .findRunsByLogStream(new ArrayList<>());

    // Assert
    assertTrue(actualFindRunsByLogStreamResult.isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterRuns#indexRunsByTableId(List)}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#indexRunsByTableId(List)}
   */
  @Test
  @DisplayName("Test indexRunsByTableId(List); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRuns.indexRunsByTableId(List)"})
  void testIndexRunsByTableId_thenReturnEmpty() {
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
    Map<String, List<StateStoreCommitterRun>> actualIndexRunsByTableIdResult = StateStoreCommitterRuns
        .indexRunsByTableId(runs);

    // Assert
    assertTrue(actualIndexRunsByTableIdResult.isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterRuns#indexRunsByTableId(List)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#indexRunsByTableId(List)}
   */
  @Test
  @DisplayName("Test indexRunsByTableId(List); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRuns.indexRunsByTableId(List)"})
  void testIndexRunsByTableId_thenReturnSizeIsOne() {
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
    Map<String, List<StateStoreCommitterRun>> actualIndexRunsByTableIdResult = StateStoreCommitterRuns
        .indexRunsByTableId(runs);

    // Assert
    assertEquals(1, actualIndexRunsByTableIdResult.size());
    assertEquals(runs, actualIndexRunsByTableIdResult.get("42"));
  }

  /**
   * Test {@link StateStoreCommitterRuns#indexRunsByTableId(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRuns#indexRunsByTableId(List)}
   */
  @Test
  @DisplayName("Test indexRunsByTableId(List); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StateStoreCommitterRuns.indexRunsByTableId(List)"})
  void testIndexRunsByTableId_whenArrayList_thenReturnEmpty() {
    // Arrange and Act
    Map<String, List<StateStoreCommitterRun>> actualIndexRunsByTableIdResult = StateStoreCommitterRuns
        .indexRunsByTableId(new ArrayList<>());

    // Assert
    assertTrue(actualIndexRunsByTableIdResult.isEmpty());
  }
}
