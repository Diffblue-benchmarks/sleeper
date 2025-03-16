package sleeper.clients.status.report.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.clients.util.console.ConsoleInput;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.job.IngestJobTracker;

class RangeJobsQueryDiffblueTest {
  /**
   * Test {@link RangeJobsQuery#RangeJobsQuery(TableStatus, Instant, Instant)}.
   * <ul>
   *   <li>Then return Type is {@code RANGE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeJobsQuery#RangeJobsQuery(TableStatus, Instant, Instant)}
   */
  @Test
  @DisplayName("Test new RangeJobsQuery(TableStatus, Instant, Instant); then return Type is 'RANGE'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RangeJobsQuery.<init>(TableStatus, Instant, Instant)"})
  void testNewRangeJobsQuery_thenReturnTypeIsRange() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Instant start = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertEquals(Type.RANGE,
        (new RangeJobsQuery(table, start, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
            .getType());
  }

  /**
   * Test {@link RangeJobsQuery#run(CompactionJobTracker)} with {@code CompactionJobTracker}.
   * <ul>
   *   <li>When {@link InMemoryCompactionJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeJobsQuery#run(CompactionJobTracker)}
   */
  @Test
  @DisplayName("Test run(CompactionJobTracker) with 'CompactionJobTracker'; when InMemoryCompactionJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List RangeJobsQuery.run(CompactionJobTracker)"})
  void testRunWithCompactionJobTracker_whenInMemoryCompactionJobTracker_thenReturnEmpty() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Instant start = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    RangeJobsQuery rangeJobsQuery = new RangeJobsQuery(table, start,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertTrue(rangeJobsQuery.run(new InMemoryCompactionJobTracker()).isEmpty());
  }

  /**
   * Test {@link RangeJobsQuery#run(IngestJobTracker)} with {@code IngestJobTracker}.
   * <ul>
   *   <li>When {@link InMemoryIngestJobTracker} (default constructor).</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeJobsQuery#run(IngestJobTracker)}
   */
  @Test
  @DisplayName("Test run(IngestJobTracker) with 'IngestJobTracker'; when InMemoryIngestJobTracker (default constructor); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List RangeJobsQuery.run(IngestJobTracker)"})
  void testRunWithIngestJobTracker_whenInMemoryIngestJobTracker_thenReturnEmpty() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Instant start = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    RangeJobsQuery rangeJobsQuery = new RangeJobsQuery(table, start,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertTrue(rangeJobsQuery.run(new InMemoryIngestJobTracker()).isEmpty());
  }

  /**
   * Test {@link RangeJobsQuery#getType()}.
   * <p>
   * Method under test: {@link RangeJobsQuery#getType()}
   */
  @Test
  @DisplayName("Test getType()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type RangeJobsQuery.getType()"})
  void testGetType() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Instant start = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertEquals(Type.RANGE,
        (new RangeJobsQuery(table, start, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
            .getType());
  }

  /**
   * Test {@link RangeJobsQuery#fromParameters(TableStatus, String, Clock)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link RangeJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeJobsQuery#fromParameters(TableStatus, String, Clock)}
   */
  @Test
  @DisplayName("Test fromParameters(TableStatus, String, Clock); when 'null'; then return RangeJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery RangeJobsQuery.fromParameters(TableStatus, String, Clock)"})
  void testFromParameters_whenNull_thenReturnRangeJobsQuery() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act
    JobQuery actualFromParametersResult = RangeJobsQuery.fromParameters(table, null,
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC));

    // Assert
    assertTrue(actualFromParametersResult instanceof RangeJobsQuery);
    assertEquals(Type.RANGE, actualFromParametersResult.getType());
  }

  /**
   * Test {@link RangeJobsQuery#fromParameters(TableStatus, String, Clock)}.
   * <ul>
   *   <li>When {@code Query Parameters}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeJobsQuery#fromParameters(TableStatus, String, Clock)}
   */
  @Test
  @DisplayName("Test fromParameters(TableStatus, String, Clock); when 'Query Parameters'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery RangeJobsQuery.fromParameters(TableStatus, String, Clock)"})
  void testFromParameters_whenQueryParameters_thenThrowIllegalArgumentException() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> RangeJobsQuery.fromParameters(table, "Query Parameters",
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC)));
  }

  /**
   * Test {@link RangeJobsQuery#prompt(TableStatus, ConsoleInput, Clock)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>Then return {@link RangeJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RangeJobsQuery#prompt(TableStatus, ConsoleInput, Clock)}
   */
  @Test
  @DisplayName("Test prompt(TableStatus, ConsoleInput, Clock); given empty string; then return RangeJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery RangeJobsQuery.prompt(TableStatus, ConsoleInput, Clock)"})
  void testPrompt_givenEmptyString_thenReturnRangeJobsQuery() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("");

    // Act
    JobQuery actualPromptResult = RangeJobsQuery.prompt(table, in,
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC));

    // Assert
    verify(in, atLeast(1)).promptLine(Mockito.<String>any());
    assertTrue(actualPromptResult instanceof RangeJobsQuery);
    assertEquals(Type.RANGE, actualPromptResult.getType());
  }
}
