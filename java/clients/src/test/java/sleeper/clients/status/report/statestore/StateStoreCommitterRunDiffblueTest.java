package sleeper.clients.status.report.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.statestore.StateStoreCommitterRun.Builder;

class StateStoreCommitterRunDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#commits(List)}
   *   <li>{@link Builder#finish(StateStoreCommitterRunFinished)}
   *   <li>{@link Builder#logStream(String)}
   *   <li>{@link Builder#start(StateStoreCommitterRunStarted)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitterRun Builder.build()", "Builder Builder.commits(List)",
      "Builder Builder.finish(StateStoreCommitterRunFinished)", "Builder Builder.logStream(String)",
      "Builder Builder.start(StateStoreCommitterRunStarted)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Builder commitsResult = builderResult.commits(commits);
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    StateStoreCommitterRun actualBuildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Assert
    assertEquals("Log Stream", actualBuildResult.getLogStream());
    Instant finishTime = actualBuildResult.getFinishTime();
    assertEquals(0, finishTime.getNano());
    assertEquals(0L, finishTime.getEpochSecond());
    List<StateStoreCommitSummary> commits2 = actualBuildResult.getCommits();
    assertTrue(commits2.isEmpty());
    assertSame(commits, commits2);
  }

  /**
   * Test Builder {@link Builder#commit(StateStoreCommitSummary)}.
   * <p>
   * Method under test: {@link Builder#commit(StateStoreCommitSummary)}
   */
  @Test
  @DisplayName("Test Builder commit(StateStoreCommitSummary)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.commit(StateStoreCommitSummary)"})
  void testBuilderCommit() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertSame(builderResult, builderResult.commit(new StateStoreCommitSummary("Log Stream", timestamp, "42", "Type",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
  }

  /**
   * Test {@link StateStoreCommitterRun#StateStoreCommitterRun(Builder)}.
   * <ul>
   *   <li>Then return LogStream is {@code logStream must not be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#StateStoreCommitterRun(Builder)}
   */
  @Test
  @DisplayName("Test new StateStoreCommitterRun(Builder); then return LogStream is 'logStream must not be null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitterRun.<init>(Builder)"})
  void testNewStateStoreCommitterRun_thenReturnLogStreamIsLogStreamMustNotBeNull() {
    // Arrange
    Builder builder = StateStoreCommitterRun.builder();
    builder.logStream("logStream must not be null");

    // Act
    StateStoreCommitterRun actualStateStoreCommitterRun = new StateStoreCommitterRun(builder);

    // Assert
    assertEquals("logStream must not be null", actualStateStoreCommitterRun.getLogStream());
    assertNull(actualStateStoreCommitterRun.getFinishTime());
    assertNull(actualStateStoreCommitterRun.getStartTime());
    assertTrue(actualStateStoreCommitterRun.getCommits().isEmpty());
  }

  /**
   * Test {@link StateStoreCommitterRun#computeRequestsPerSecond()}.
   * <p>
   * Method under test: {@link StateStoreCommitterRun#computeRequestsPerSecond()}
   */
  @Test
  @DisplayName("Test computeRequestsPerSecond()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRun.computeRequestsPerSecond()"})
  void testComputeRequestsPerSecond() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertEquals(0.0d, buildResult.computeRequestsPerSecond());
  }

  /**
   * Test {@link StateStoreCommitterRun#computeRequestsPerSecond()}.
   * <p>
   * Method under test: {@link StateStoreCommitterRun#computeRequestsPerSecond()}
   */
  @Test
  @DisplayName("Test computeRequestsPerSecond()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRun.computeRequestsPerSecond()"})
  void testComputeRequestsPerSecond2() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertEquals(0.0d, buildResult.computeRequestsPerSecond());
  }

  /**
   * Test {@link StateStoreCommitterRun#computeRequestsPerSecond()}.
   * <p>
   * Method under test: {@link StateStoreCommitterRun#computeRequestsPerSecond()}
   */
  @Test
  @DisplayName("Test computeRequestsPerSecond()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRun.computeRequestsPerSecond()"})
  void testComputeRequestsPerSecond3() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder logStreamResult = builderResult.commits(new ArrayList<>()).finish(null).logStream("Log Stream");
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertEquals(0.0d, buildResult.computeRequestsPerSecond());
  }

  /**
   * Test {@link StateStoreCommitterRun#computeRequestsPerSecond()}.
   * <ul>
   *   <li>Given builder logStream {@code Log Stream}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#computeRequestsPerSecond()}
   */
  @Test
  @DisplayName("Test computeRequestsPerSecond(); given builder logStream 'Log Stream'; then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double StateStoreCommitterRun.computeRequestsPerSecond()"})
  void testComputeRequestsPerSecond_givenBuilderLogStreamLogStream_thenReturnZero() {
    // Arrange
    Builder builder = StateStoreCommitterRun.builder();
    builder.logStream("Log Stream");

    // Act and Assert
    assertEquals(0.0d, (new StateStoreCommitterRun(builder)).computeRequestsPerSecond());
  }

  /**
   * Test {@link StateStoreCommitterRun#getStartTime()}.
   * <p>
   * Method under test: {@link StateStoreCommitterRun#getStartTime()}
   */
  @Test
  @DisplayName("Test getStartTime()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant StateStoreCommitterRun.getStartTime()"})
  void testGetStartTime() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act
    Instant actualStartTime = buildResult.getStartTime();

    // Assert
    Instant instant = actualStartTime.EPOCH;
    assertSame(instant, buildResult.getFinishTime());
    assertSame(instant, actualStartTime);
  }

  /**
   * Test {@link StateStoreCommitterRun#getStartTime()}.
   * <ul>
   *   <li>Then {@link StateStoreCommitterRun#StateStoreCommitterRun(Builder)} with builder FinishTime is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#getStartTime()}
   */
  @Test
  @DisplayName("Test getStartTime(); then StateStoreCommitterRun(Builder) with builder FinishTime is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant StateStoreCommitterRun.getStartTime()"})
  void testGetStartTime_thenStateStoreCommitterRunWithBuilderFinishTimeIsNull() {
    // Arrange
    Builder builder = StateStoreCommitterRun.builder();
    builder.logStream("Log Stream");
    StateStoreCommitterRun stateStoreCommitterRun = new StateStoreCommitterRun(builder);

    // Act
    Instant actualStartTime = stateStoreCommitterRun.getStartTime();

    // Assert
    assertNull(stateStoreCommitterRun.getFinishTime());
    assertNull(actualStartTime);
  }

  /**
   * Test {@link StateStoreCommitterRun#getFinishTime()}.
   * <ul>
   *   <li>Given builder logStream {@code Log Stream}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#getFinishTime()}
   */
  @Test
  @DisplayName("Test getFinishTime(); given builder logStream 'Log Stream'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant StateStoreCommitterRun.getFinishTime()"})
  void testGetFinishTime_givenBuilderLogStreamLogStream_thenReturnNull() {
    // Arrange
    Builder builder = StateStoreCommitterRun.builder();
    builder.logStream("Log Stream");
    StateStoreCommitterRun stateStoreCommitterRun = new StateStoreCommitterRun(builder);

    // Act and Assert
    assertNull(stateStoreCommitterRun.getFinishTime());
    assertNull(stateStoreCommitterRun.getStartTime());
  }

  /**
   * Test {@link StateStoreCommitterRun#getFinishTime()}.
   * <ul>
   *   <li>Then return {@link Instant#EPOCH}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#getFinishTime()}
   */
  @Test
  @DisplayName("Test getFinishTime(); then return EPOCH")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant StateStoreCommitterRun.getFinishTime()"})
  void testGetFinishTime_thenReturnEpoch() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act
    Instant actualFinishTime = buildResult.getFinishTime();

    // Assert
    Instant instant = actualFinishTime.EPOCH;
    assertSame(instant, actualFinishTime);
    assertSame(instant, buildResult.getStartTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRun#toString()}
   *   <li>{@link StateStoreCommitterRun#getCommits()}
   *   <li>{@link StateStoreCommitterRun#getLogStream()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StateStoreCommitterRun.getCommits()", "String StateStoreCommitterRun.getLogStream()",
      "String StateStoreCommitterRun.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    ArrayList<StateStoreCommitSummary> commits = new ArrayList<>();
    Builder commitsResult = builderResult.commits(commits);
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act
    String actualToStringResult = buildResult.toString();
    List<StateStoreCommitSummary> actualCommits = buildResult.getCommits();

    // Assert
    assertEquals("Log Stream", buildResult.getLogStream());
    assertEquals(
        "StateStoreCommitterRun{logStream=Log Stream, start=StateStoreCommitterRunStarted{logStream=Log Stream,"
            + " timestamp=1970-01-01T00:00:00Z, startTime=1970-01-01T00:00:00Z}, finish=StateStoreCommitterRunFinished"
            + "{logStream=Log Stream, timestamp=1970-01-01T00:00:00Z, finishTime=1970-01-01T00:00:00Z}, commits=["
            + "]}",
        actualToStringResult);
    assertTrue(actualCommits.isEmpty());
    assertSame(commits, actualCommits);
  }

  /**
   * Test {@link StateStoreCommitterRun#entries()}.
   * <ul>
   *   <li>Then return limit five collect toList size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#entries()}
   */
  @Test
  @DisplayName("Test entries(); then return limit five collect toList size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream StateStoreCommitterRun.entries()"})
  void testEntries_thenReturnLimitFiveCollectToListSizeIsTwo() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunFinished finish = new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    Builder logStreamResult = commitsResult.finish(finish).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRunStarted start = new StateStoreCommitterRunStarted("Log Stream", timestamp2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    StateStoreCommitterRun buildResult = logStreamResult.start(start).build();

    // Act
    Stream<StateStoreCommitterLogEntry> actualEntriesResult = buildResult.entries();

    // Assert
    List<StateStoreCommitterLogEntry> collectResult = actualEntriesResult.limit(5).collect(Collectors.toList());
    assertEquals(2, collectResult.size());
    assertSame(finish, collectResult.get(1));
    assertSame(start, collectResult.get(0));
  }

  /**
   * Test {@link StateStoreCommitterRun#equals(Object)}, and {@link StateStoreCommitterRun#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRun#equals(Object)}
   *   <li>{@link StateStoreCommitterRun#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRun.equals(Object)", "int StateStoreCommitterRun.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();
    Builder builderResult2 = StateStoreCommitterRun.builder();
    Builder commitsResult2 = builderResult2.commits(new ArrayList<>());
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult2 = commitsResult2.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp3,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp4 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult2 = logStreamResult2.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp4, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link StateStoreCommitterRun#equals(Object)}, and {@link StateStoreCommitterRun#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreCommitterRun#equals(Object)}
   *   <li>{@link StateStoreCommitterRun#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRun.equals(Object)", "int StateStoreCommitterRun.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link StateStoreCommitterRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRun.equals(Object)", "int StateStoreCommitterRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
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
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult2 = builderResult.commits(new ArrayList<>());
    Instant timestamp4 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult2 = commitsResult2.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp4,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp5 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult2 = logStreamResult2.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp5, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link StateStoreCommitterRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRun.equals(Object)", "int StateStoreCommitterRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(
        new StateStoreCommitterRunFinished("sleeper.clients.status.report.statestore.StateStoreCommitterRunFinished",
            timestamp, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();
    Builder builderResult2 = StateStoreCommitterRun.builder();
    Builder commitsResult2 = builderResult2.commits(new ArrayList<>());
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult2 = commitsResult2.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp3,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp4 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult2 = logStreamResult2.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp4, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link StateStoreCommitterRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRun.equals(Object)", "int StateStoreCommitterRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("42");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();
    Builder builderResult2 = StateStoreCommitterRun.builder();
    Builder commitsResult2 = builderResult2.commits(new ArrayList<>());
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult2 = commitsResult2.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp3,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp4 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult2 = logStreamResult2.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp4, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link StateStoreCommitterRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRun.equals(Object)", "int StateStoreCommitterRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult
        .start(
            new StateStoreCommitterRunStarted("sleeper.clients.status.report.statestore.StateStoreCommitterRunStarted",
                timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .build();
    Builder builderResult2 = StateStoreCommitterRun.builder();
    Builder commitsResult2 = builderResult2.commits(new ArrayList<>());
    Instant timestamp3 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult2 = commitsResult2.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp3,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp4 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult2 = logStreamResult2.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp4, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link StateStoreCommitterRun#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRun.equals(Object)", "int StateStoreCommitterRun.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link StateStoreCommitterRun#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitterRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreCommitterRun.equals(Object)", "int StateStoreCommitterRun.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = StateStoreCommitterRun.builder();
    Builder commitsResult = builderResult.commits(new ArrayList<>());
    Instant timestamp = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder logStreamResult = commitsResult.finish(new StateStoreCommitterRunFinished("Log Stream", timestamp,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).logStream("Log Stream");
    Instant timestamp2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    StateStoreCommitterRun buildResult = logStreamResult.start(new StateStoreCommitterRunStarted("Log Stream",
        timestamp2, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())).build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to StateStoreCommitterRun");
  }
}
