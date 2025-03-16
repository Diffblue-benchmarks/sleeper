package sleeper.clients.status.report.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.query.JobQuery.Type;
import sleeper.clients.util.console.ConsoleInput;
import sleeper.core.table.TableStatus;

class JobQueryDiffblueTest {
  /**
   * Test {@link JobQuery#from(TableStatus, Type, String, Clock)}.
   * <ul>
   *   <li>When {@code ALL}.</li>
   *   <li>Then return {@link AllJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQuery#from(TableStatus, Type, String, Clock)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Type, String, Clock); when 'ALL'; then return AllJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.from(TableStatus, Type, String, Clock)"})
  void testFrom_whenAll_thenReturnAllJobsQuery() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act
    JobQuery actualFromResult = JobQuery.from(table, Type.ALL, null,
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC));

    // Assert
    assertTrue(actualFromResult instanceof AllJobsQuery);
    assertEquals(Type.ALL, actualFromResult.getType());
  }

  /**
   * Test {@link JobQuery#from(TableStatus, Type, String, Clock)}.
   * <ul>
   *   <li>When {@code DETAILED}.</li>
   *   <li>Then return {@link DetailedJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQuery#from(TableStatus, Type, String, Clock)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Type, String, Clock); when 'DETAILED'; then return DetailedJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.from(TableStatus, Type, String, Clock)"})
  void testFrom_whenDetailed_thenReturnDetailedJobsQuery() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act
    JobQuery actualFromResult = JobQuery.from(table, Type.DETAILED, "Query Parameters",
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC));

    // Assert
    assertTrue(actualFromResult instanceof DetailedJobsQuery);
    assertEquals(Type.DETAILED, actualFromResult.getType());
  }

  /**
   * Test {@link JobQuery#from(TableStatus, Type, String, Clock)}.
   * <ul>
   *   <li>When {@code DETAILED}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQuery#from(TableStatus, Type, String, Clock)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Type, String, Clock); when 'DETAILED'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.from(TableStatus, Type, String, Clock)"})
  void testFrom_whenDetailed_thenThrowIllegalArgumentException() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> JobQuery.from(table, Type.DETAILED, null,
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC)));
  }

  /**
   * Test {@link JobQuery#from(TableStatus, Type, String, Clock)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQuery#from(TableStatus, Type, String, Clock)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Type, String, Clock); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.from(TableStatus, Type, String, Clock)"})
  void testFrom_whenEmptyString_thenReturnNull() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act and Assert
    assertNull(JobQuery.from(table, Type.DETAILED, "",
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC)));
  }

  /**
   * Test {@link JobQuery#from(TableStatus, Type, String, Clock)}.
   * <ul>
   *   <li>When {@code PROMPT}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQuery#from(TableStatus, Type, String, Clock)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Type, String, Clock); when 'PROMPT'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.from(TableStatus, Type, String, Clock)"})
  void testFrom_whenPrompt_thenThrowIllegalArgumentException() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> JobQuery.from(table, Type.PROMPT, "Query Parameters",
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC)));
  }

  /**
   * Test {@link JobQuery#from(TableStatus, Type, String, Clock)}.
   * <ul>
   *   <li>When {@code RANGE}.</li>
   *   <li>Then return {@link RangeJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQuery#from(TableStatus, Type, String, Clock)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Type, String, Clock); when 'RANGE'; then return RangeJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.from(TableStatus, Type, String, Clock)"})
  void testFrom_whenRange_thenReturnRangeJobsQuery() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act
    JobQuery actualFromResult = JobQuery.from(table, Type.RANGE, null,
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC));

    // Assert
    assertTrue(actualFromResult instanceof RangeJobsQuery);
    assertEquals(Type.RANGE, actualFromResult.getType());
  }

  /**
   * Test {@link JobQuery#from(TableStatus, Type, String, Clock)}.
   * <ul>
   *   <li>When {@code REJECTED}.</li>
   *   <li>Then return {@link RejectedJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQuery#from(TableStatus, Type, String, Clock)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Type, String, Clock); when 'REJECTED'; then return RejectedJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.from(TableStatus, Type, String, Clock)"})
  void testFrom_whenRejected_thenReturnRejectedJobsQuery() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act
    JobQuery actualFromResult = JobQuery.from(table, Type.REJECTED, null,
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC));

    // Assert
    assertTrue(actualFromResult instanceof RejectedJobsQuery);
    assertEquals(Type.REJECTED, actualFromResult.getType());
  }

  /**
   * Test {@link JobQuery#from(TableStatus, Type, String, Clock)}.
   * <ul>
   *   <li>When {@code UNFINISHED}.</li>
   *   <li>Then return {@link UnfinishedJobsQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQuery#from(TableStatus, Type, String, Clock)}
   */
  @Test
  @DisplayName("Test from(TableStatus, Type, String, Clock); when 'UNFINISHED'; then return UnfinishedJobsQuery")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.from(TableStatus, Type, String, Clock)"})
  void testFrom_whenUnfinished_thenReturnUnfinishedJobsQuery() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act
    JobQuery actualFromResult = JobQuery.from(table, Type.UNFINISHED, null,
        Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC));

    // Assert
    assertTrue(actualFromResult instanceof UnfinishedJobsQuery);
    assertEquals(Type.UNFINISHED, actualFromResult.getType());
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput) with 'table', 'queryType', 'queryParameters', 'clock', 'input'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInput() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> JobQuery.fromParametersOrPrompt(table, Type.DETAILED, null, clock, new ConsoleInput(null)));
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput) with 'table', 'queryType', 'queryParameters', 'clock', 'input'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInput2() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);

    // Act and Assert
    assertNull(JobQuery.fromParametersOrPrompt(table, Type.DETAILED, "", clock, new ConsoleInput(null)));
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput) with 'table', 'queryType', 'queryParameters', 'clock', 'input'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInput3() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);

    // Act
    JobQuery actualFromParametersOrPromptResult = JobQuery.fromParametersOrPrompt(table, Type.DETAILED,
        "Query Parameters", clock, new ConsoleInput(null));

    // Assert
    assertTrue(actualFromParametersOrPromptResult instanceof DetailedJobsQuery);
    assertEquals(Type.DETAILED, actualFromParametersOrPromptResult.getType());
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput) with 'table', 'queryType', 'queryParameters', 'clock', 'input'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInput4() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);

    // Act
    JobQuery actualFromParametersOrPromptResult = JobQuery.fromParametersOrPrompt(table, Type.ALL, null, clock,
        new ConsoleInput(null));

    // Assert
    assertTrue(actualFromParametersOrPromptResult instanceof AllJobsQuery);
    assertEquals(Type.ALL, actualFromParametersOrPromptResult.getType());
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput) with 'table', 'queryType', 'queryParameters', 'clock', 'input'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInput5() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);

    // Act
    JobQuery actualFromParametersOrPromptResult = JobQuery.fromParametersOrPrompt(table, Type.UNFINISHED, null, clock,
        new ConsoleInput(null));

    // Assert
    assertTrue(actualFromParametersOrPromptResult instanceof UnfinishedJobsQuery);
    assertEquals(Type.UNFINISHED, actualFromParametersOrPromptResult.getType());
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput) with 'table', 'queryType', 'queryParameters', 'clock', 'input'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInput6() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);

    // Act
    JobQuery actualFromParametersOrPromptResult = JobQuery.fromParametersOrPrompt(table, Type.RANGE, null, clock,
        new ConsoleInput(null));

    // Assert
    assertTrue(actualFromParametersOrPromptResult instanceof RangeJobsQuery);
    assertEquals(Type.RANGE, actualFromParametersOrPromptResult.getType());
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput) with 'table', 'queryType', 'queryParameters', 'clock', 'input'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInput7() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);

    // Act
    JobQuery actualFromParametersOrPromptResult = JobQuery.fromParametersOrPrompt(table, Type.REJECTED, null, clock,
        new ConsoleInput(null));

    // Assert
    assertTrue(actualFromParametersOrPromptResult instanceof RejectedJobsQuery);
    assertEquals(Type.REJECTED, actualFromParametersOrPromptResult.getType());
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}, {@code extraQueryTypes}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map) with 'table', 'queryType', 'queryParameters', 'clock', 'input', 'extraQueryTypes'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInputExtraQueryTypes() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput input = new ConsoleInput(null);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> JobQuery.fromParametersOrPrompt(table, Type.DETAILED, null, clock, input, new HashMap<>()));
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}, {@code extraQueryTypes}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map) with 'table', 'queryType', 'queryParameters', 'clock', 'input', 'extraQueryTypes'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInputExtraQueryTypes2() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput input = new ConsoleInput(null);

    // Act and Assert
    assertNull(JobQuery.fromParametersOrPrompt(table, Type.DETAILED, "", clock, input, new HashMap<>()));
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}, {@code extraQueryTypes}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map) with 'table', 'queryType', 'queryParameters', 'clock', 'input', 'extraQueryTypes'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInputExtraQueryTypes3() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput input = new ConsoleInput(null);

    // Act
    JobQuery actualFromParametersOrPromptResult = JobQuery.fromParametersOrPrompt(table, Type.DETAILED,
        "Query Parameters", clock, input, new HashMap<>());

    // Assert
    assertTrue(actualFromParametersOrPromptResult instanceof DetailedJobsQuery);
    assertEquals(Type.DETAILED, actualFromParametersOrPromptResult.getType());
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}, {@code extraQueryTypes}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map) with 'table', 'queryType', 'queryParameters', 'clock', 'input', 'extraQueryTypes'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInputExtraQueryTypes4() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput input = new ConsoleInput(null);

    // Act
    JobQuery actualFromParametersOrPromptResult = JobQuery.fromParametersOrPrompt(table, Type.ALL, null, clock, input,
        new HashMap<>());

    // Assert
    assertTrue(actualFromParametersOrPromptResult instanceof AllJobsQuery);
    assertEquals(Type.ALL, actualFromParametersOrPromptResult.getType());
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}, {@code extraQueryTypes}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map) with 'table', 'queryType', 'queryParameters', 'clock', 'input', 'extraQueryTypes'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInputExtraQueryTypes5() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput input = new ConsoleInput(null);

    // Act
    JobQuery actualFromParametersOrPromptResult = JobQuery.fromParametersOrPrompt(table, Type.UNFINISHED, null, clock,
        input, new HashMap<>());

    // Assert
    assertTrue(actualFromParametersOrPromptResult instanceof UnfinishedJobsQuery);
    assertEquals(Type.UNFINISHED, actualFromParametersOrPromptResult.getType());
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}, {@code extraQueryTypes}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map) with 'table', 'queryType', 'queryParameters', 'clock', 'input', 'extraQueryTypes'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInputExtraQueryTypes6() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput input = new ConsoleInput(null);

    // Act
    JobQuery actualFromParametersOrPromptResult = JobQuery.fromParametersOrPrompt(table, Type.RANGE, null, clock, input,
        new HashMap<>());

    // Assert
    assertTrue(actualFromParametersOrPromptResult instanceof RangeJobsQuery);
    assertEquals(Type.RANGE, actualFromParametersOrPromptResult.getType());
  }

  /**
   * Test {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)} with {@code table}, {@code queryType}, {@code queryParameters}, {@code clock}, {@code input}, {@code extraQueryTypes}.
   * <p>
   * Method under test: {@link JobQuery#fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)}
   */
  @Test
  @DisplayName("Test fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map) with 'table', 'queryType', 'queryParameters', 'clock', 'input', 'extraQueryTypes'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobQuery JobQuery.fromParametersOrPrompt(TableStatus, Type, String, Clock, ConsoleInput, Map)"})
  void testFromParametersOrPromptWithTableQueryTypeQueryParametersClockInputExtraQueryTypes7() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Clock clock = Clock.fixed(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
        ZoneOffset.UTC);
    ConsoleInput input = new ConsoleInput(null);

    // Act
    JobQuery actualFromParametersOrPromptResult = JobQuery.fromParametersOrPrompt(table, Type.REJECTED, null, clock,
        input, new HashMap<>());

    // Assert
    assertTrue(actualFromParametersOrPromptResult instanceof RejectedJobsQuery);
    assertEquals(Type.REJECTED, actualFromParametersOrPromptResult.getType());
  }

  /**
   * Test Type {@link Type#isParametersRequired()}.
   * <ul>
   *   <li>Given {@link Type#DETAILED}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Type#isParametersRequired()}
   */
  @Test
  @DisplayName("Test Type isParametersRequired(); given DETAILED; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Type.isParametersRequired()"})
  void testTypeIsParametersRequired_givenDetailed_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(Type.DETAILED.isParametersRequired());
  }

  /**
   * Test Type {@link Type#isParametersRequired()}.
   * <ul>
   *   <li>Given {@code PROMPT}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Type#isParametersRequired()}
   */
  @Test
  @DisplayName("Test Type isParametersRequired(); given 'PROMPT'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Type.isParametersRequired()"})
  void testTypeIsParametersRequired_givenPrompt_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(Type.PROMPT.isParametersRequired());
  }
}
