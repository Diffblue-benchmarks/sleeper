package sleeper.systemtest.dsl.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.PollWithRetries.Builder;
import sleeper.core.util.ThreadSleep;

class WaitForStateStoreCommitLogsDiffblueTest {
  /**
   * Test {@link WaitForStateStoreCommitLogs#waitForCommitLogs(PollWithRetries, Map, Instant)}.
   * <ul>
   *   <li>Given builder.</li>
   *   <li>Then calls {@link Builder#maxRetries(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStateStoreCommitLogs#waitForCommitLogs(PollWithRetries, Map, Instant)}
   */
  @Test
  @DisplayName("Test waitForCommitLogs(PollWithRetries, Map, Instant); given builder; then calls maxRetries(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreCommitterLogs WaitForStateStoreCommitLogs.waitForCommitLogs(PollWithRetries, Map, Instant)"})
  void testWaitForCommitLogs_givenBuilder_thenCallsMaxRetries() throws InterruptedException {
    // Arrange
    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.put("Waiting for commits by table ID: {}", 10);
    StateStoreCommitterLogs stateStoreCommitterLogs = mock(StateStoreCommitterLogs.class);
    when(stateStoreCommitterLogs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(stringIntegerMap);
    StateStoreCommitterLogsDriver driver = mock(StateStoreCommitterLogsDriver.class);
    when(driver.getLogsInPeriod(Mockito.<Instant>any(), Mockito.<Instant>any())).thenReturn(stateStoreCommitterLogs);
    WaitForStateStoreCommitLogs waitForStateStoreCommitLogs = new WaitForStateStoreCommitLogs(driver);
    Builder builder = mock(Builder.class);
    when(builder.maxRetries(anyInt())).thenReturn(PollWithRetries.builder());
    PollWithRetries poll = builder.maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    HashMap<String, Integer> waitForNumCommitsByTableId = new HashMap<>();
    waitForNumCommitsByTableId.put("Waiting for commits by table ID: {}", 10);

    // Act
    waitForStateStoreCommitLogs.waitForCommitLogs(poll, waitForNumCommitsByTableId,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    verify(builder).maxRetries(eq(3));
    verify(stateStoreCommitterLogs).countNumCommitsByTableId(isA(Set.class));
    verify(driver).getLogsInPeriod(isA(Instant.class), isA(Instant.class));
  }

  /**
   * Test {@link WaitForStateStoreCommitLogs#waitForCommitLogs(PollWithRetries, Map, Instant)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code all state store commits are applied} is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStateStoreCommitLogs#waitForCommitLogs(PollWithRetries, Map, Instant)}
   */
  @Test
  @DisplayName("Test waitForCommitLogs(PollWithRetries, Map, Instant); given HashMap() 'all state store commits are applied' is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreCommitterLogs WaitForStateStoreCommitLogs.waitForCommitLogs(PollWithRetries, Map, Instant)"})
  void testWaitForCommitLogs_givenHashMapAllStateStoreCommitsAreAppliedIsTwo() throws InterruptedException {
    // Arrange
    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.put("all state store commits are applied", 2);
    stringIntegerMap.put("Waiting for commits by table ID: {}", 10);
    StateStoreCommitterLogs stateStoreCommitterLogs = mock(StateStoreCommitterLogs.class);
    when(stateStoreCommitterLogs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(stringIntegerMap);
    StateStoreCommitterLogsDriver driver = mock(StateStoreCommitterLogsDriver.class);
    when(driver.getLogsInPeriod(Mockito.<Instant>any(), Mockito.<Instant>any())).thenReturn(stateStoreCommitterLogs);
    WaitForStateStoreCommitLogs waitForStateStoreCommitLogs = new WaitForStateStoreCommitLogs(driver);
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    HashMap<String, Integer> waitForNumCommitsByTableId = new HashMap<>();

    // Act
    waitForStateStoreCommitLogs.waitForCommitLogs(poll, waitForNumCommitsByTableId,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    verify(stateStoreCommitterLogs).countNumCommitsByTableId(isA(Set.class));
    verify(driver).getLogsInPeriod(isA(Instant.class), isA(Instant.class));
  }

  /**
   * Test {@link WaitForStateStoreCommitLogs#waitForCommitLogs(PollWithRetries, Map, Instant)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code Waiting for commits by table ID: {}} is ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStateStoreCommitLogs#waitForCommitLogs(PollWithRetries, Map, Instant)}
   */
  @Test
  @DisplayName("Test waitForCommitLogs(PollWithRetries, Map, Instant); given HashMap() 'Waiting for commits by table ID: {}' is ten")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreCommitterLogs WaitForStateStoreCommitLogs.waitForCommitLogs(PollWithRetries, Map, Instant)"})
  void testWaitForCommitLogs_givenHashMapWaitingForCommitsByTableIdIsTen() throws InterruptedException {
    // Arrange
    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.put("Waiting for commits by table ID: {}", 10);
    StateStoreCommitterLogs stateStoreCommitterLogs = mock(StateStoreCommitterLogs.class);
    when(stateStoreCommitterLogs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(stringIntegerMap);
    StateStoreCommitterLogsDriver driver = mock(StateStoreCommitterLogsDriver.class);
    when(driver.getLogsInPeriod(Mockito.<Instant>any(), Mockito.<Instant>any())).thenReturn(stateStoreCommitterLogs);
    WaitForStateStoreCommitLogs waitForStateStoreCommitLogs = new WaitForStateStoreCommitLogs(driver);
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    HashMap<String, Integer> waitForNumCommitsByTableId = new HashMap<>();

    // Act
    waitForStateStoreCommitLogs.waitForCommitLogs(poll, waitForNumCommitsByTableId,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    verify(stateStoreCommitterLogs).countNumCommitsByTableId(isA(Set.class));
    verify(driver).getLogsInPeriod(isA(Instant.class), isA(Instant.class));
  }

  /**
   * Test {@link WaitForStateStoreCommitLogs#waitForCommitLogs(PollWithRetries, Map, Instant)}.
   * <ul>
   *   <li>Then calls {@link StateStoreCommitterLogs#countNumCommitsByTableId(Set)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStateStoreCommitLogs#waitForCommitLogs(PollWithRetries, Map, Instant)}
   */
  @Test
  @DisplayName("Test waitForCommitLogs(PollWithRetries, Map, Instant); then calls countNumCommitsByTableId(Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreCommitterLogs WaitForStateStoreCommitLogs.waitForCommitLogs(PollWithRetries, Map, Instant)"})
  void testWaitForCommitLogs_thenCallsCountNumCommitsByTableId() throws InterruptedException {
    // Arrange
    StateStoreCommitterLogs stateStoreCommitterLogs = mock(StateStoreCommitterLogs.class);
    when(stateStoreCommitterLogs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(new HashMap<>());
    StateStoreCommitterLogsDriver driver = mock(StateStoreCommitterLogsDriver.class);
    when(driver.getLogsInPeriod(Mockito.<Instant>any(), Mockito.<Instant>any())).thenReturn(stateStoreCommitterLogs);
    WaitForStateStoreCommitLogs waitForStateStoreCommitLogs = new WaitForStateStoreCommitLogs(driver);
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    HashMap<String, Integer> waitForNumCommitsByTableId = new HashMap<>();

    // Act
    waitForStateStoreCommitLogs.waitForCommitLogs(poll, waitForNumCommitsByTableId,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    verify(stateStoreCommitterLogs).countNumCommitsByTableId(isA(Set.class));
    verify(driver).getLogsInPeriod(isA(Instant.class), isA(Instant.class));
  }

  /**
   * Test {@link WaitForStateStoreCommitLogs#getRemainingCommits(Map, StateStoreCommitterLogs)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code 42} is minus one.</li>
   *   <li>Then return {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStateStoreCommitLogs#getRemainingCommits(Map, StateStoreCommitterLogs)}
   */
  @Test
  @DisplayName("Test getRemainingCommits(Map, StateStoreCommitterLogs); given '42'; when HashMap() '42' is minus one; then return HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map WaitForStateStoreCommitLogs.getRemainingCommits(Map, StateStoreCommitterLogs)"})
  void testGetRemainingCommits_given42_whenHashMap42IsMinusOne_thenReturnHashMap() {
    // Arrange
    HashMap<String, Integer> waitForNumCommitsByTableId = new HashMap<>();
    waitForNumCommitsByTableId.put("foo", 1);
    waitForNumCommitsByTableId.put("42", -1);

    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.put("foo", 0);
    stringIntegerMap.put("Remaining unapplied commits by table ID: {}", 1);
    StateStoreCommitterLogs logs = mock(StateStoreCommitterLogs.class);
    when(logs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(stringIntegerMap);

    // Act
    Map<String, Integer> actualRemainingCommits = WaitForStateStoreCommitLogs
        .getRemainingCommits(waitForNumCommitsByTableId, logs);

    // Assert
    verify(logs).countNumCommitsByTableId(isA(Set.class));
    assertEquals(waitForNumCommitsByTableId, actualRemainingCommits);
  }

  /**
   * Test {@link WaitForStateStoreCommitLogs#getRemainingCommits(Map, StateStoreCommitterLogs)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is zero.</li>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStateStoreCommitLogs#getRemainingCommits(Map, StateStoreCommitterLogs)}
   */
  @Test
  @DisplayName("Test getRemainingCommits(Map, StateStoreCommitterLogs); given HashMap() 'foo' is zero; when HashMap(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map WaitForStateStoreCommitLogs.getRemainingCommits(Map, StateStoreCommitterLogs)"})
  void testGetRemainingCommits_givenHashMapFooIsZero_whenHashMap_thenReturnEmpty() {
    // Arrange
    HashMap<String, Integer> waitForNumCommitsByTableId = new HashMap<>();

    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.put("foo", 0);
    stringIntegerMap.put("Remaining unapplied commits by table ID: {}", 1);
    StateStoreCommitterLogs logs = mock(StateStoreCommitterLogs.class);
    when(logs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(stringIntegerMap);

    // Act
    Map<String, Integer> actualRemainingCommits = WaitForStateStoreCommitLogs
        .getRemainingCommits(waitForNumCommitsByTableId, logs);

    // Assert
    verify(logs).countNumCommitsByTableId(isA(Set.class));
    assertTrue(actualRemainingCommits.isEmpty());
  }

  /**
   * Test {@link WaitForStateStoreCommitLogs#getRemainingCommits(Map, StateStoreCommitterLogs)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.</li>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStateStoreCommitLogs#getRemainingCommits(Map, StateStoreCommitterLogs)}
   */
  @Test
  @DisplayName("Test getRemainingCommits(Map, StateStoreCommitterLogs); given HashMap(); when HashMap(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map WaitForStateStoreCommitLogs.getRemainingCommits(Map, StateStoreCommitterLogs)"})
  void testGetRemainingCommits_givenHashMap_whenHashMap_thenReturnEmpty() {
    // Arrange
    HashMap<String, Integer> waitForNumCommitsByTableId = new HashMap<>();
    StateStoreCommitterLogs logs = mock(StateStoreCommitterLogs.class);
    when(logs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(new HashMap<>());

    // Act
    Map<String, Integer> actualRemainingCommits = WaitForStateStoreCommitLogs
        .getRemainingCommits(waitForNumCommitsByTableId, logs);

    // Assert
    verify(logs).countNumCommitsByTableId(isA(Set.class));
    assertTrue(actualRemainingCommits.isEmpty());
  }

  /**
   * Test {@link WaitForStateStoreCommitLogs#getRemainingCommits(Map, StateStoreCommitterLogs)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStateStoreCommitLogs#getRemainingCommits(Map, StateStoreCommitterLogs)}
   */
  @Test
  @DisplayName("Test getRemainingCommits(Map, StateStoreCommitterLogs); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map WaitForStateStoreCommitLogs.getRemainingCommits(Map, StateStoreCommitterLogs)"})
  void testGetRemainingCommits_thenReturnSizeIsOne() {
    // Arrange
    HashMap<String, Integer> waitForNumCommitsByTableId = new HashMap<>();
    waitForNumCommitsByTableId.put("foo", 1);
    waitForNumCommitsByTableId.put("Remaining unapplied commits by table ID: {}", -1);

    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.put("foo", 0);
    stringIntegerMap.put("Remaining unapplied commits by table ID: {}", 1);
    StateStoreCommitterLogs logs = mock(StateStoreCommitterLogs.class);
    when(logs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(stringIntegerMap);

    // Act
    Map<String, Integer> actualRemainingCommits = WaitForStateStoreCommitLogs
        .getRemainingCommits(waitForNumCommitsByTableId, logs);

    // Assert
    verify(logs).countNumCommitsByTableId(isA(Set.class));
    assertEquals(1, actualRemainingCommits.size());
    assertEquals(1, actualRemainingCommits.get("foo").intValue());
  }

  /**
   * Test {@link WaitForStateStoreCommitLogs#getRemainingCommits(Map, StateStoreCommitterLogs)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStateStoreCommitLogs#getRemainingCommits(Map, StateStoreCommitterLogs)}
   */
  @Test
  @DisplayName("Test getRemainingCommits(Map, StateStoreCommitterLogs); when HashMap(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map WaitForStateStoreCommitLogs.getRemainingCommits(Map, StateStoreCommitterLogs)"})
  void testGetRemainingCommits_whenHashMap_thenReturnEmpty() {
    // Arrange
    HashMap<String, Integer> waitForNumCommitsByTableId = new HashMap<>();

    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.put("Remaining unapplied commits by table ID: {}", 1);
    StateStoreCommitterLogs logs = mock(StateStoreCommitterLogs.class);
    when(logs.countNumCommitsByTableId(Mockito.<Set<String>>any())).thenReturn(stringIntegerMap);

    // Act
    Map<String, Integer> actualRemainingCommits = WaitForStateStoreCommitLogs
        .getRemainingCommits(waitForNumCommitsByTableId, logs);

    // Assert
    verify(logs).countNumCommitsByTableId(isA(Set.class));
    assertTrue(actualRemainingCommits.isEmpty());
  }
}
