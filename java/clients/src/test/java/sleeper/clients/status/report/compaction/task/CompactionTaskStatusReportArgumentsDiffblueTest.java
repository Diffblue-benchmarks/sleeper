package sleeper.clients.status.report.compaction.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CompactionTaskStatusReportArgumentsDiffblueTest {
  /**
   * Test {@link CompactionTaskStatusReportArguments#fromArgs(String[])}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatusReportArguments#fromArgs(String[])}
   */
  @Test
  @DisplayName("Test fromArgs(String[]); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionTaskStatusReportArguments CompactionTaskStatusReportArguments.fromArgs(String[])"})
  void testFromArgs_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> CompactionTaskStatusReportArguments.fromArgs());
  }

  /**
   * Test {@link CompactionTaskStatusReportArguments#fromArgs(String[])}.
   * <ul>
   *   <li>When {@code Args}.</li>
   *   <li>Then Reporter return {@link StandardCompactionTaskStatusReporter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskStatusReportArguments#fromArgs(String[])}
   */
  @Test
  @DisplayName("Test fromArgs(String[]); when 'Args'; then Reporter return StandardCompactionTaskStatusReporter")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionTaskStatusReportArguments CompactionTaskStatusReportArguments.fromArgs(String[])"})
  void testFromArgs_whenArgs_thenReporterReturnStandardCompactionTaskStatusReporter() {
    // Arrange and Act
    CompactionTaskStatusReportArguments actualFromArgsResult = CompactionTaskStatusReportArguments.fromArgs("Args");

    // Assert
    assertTrue(actualFromArgsResult.getReporter() instanceof StandardCompactionTaskStatusReporter);
    assertEquals("Args", actualFromArgsResult.getInstanceId());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionTaskStatusReportArguments#getInstanceId()}
   *   <li>{@link CompactionTaskStatusReportArguments#getQuery()}
   *   <li>{@link CompactionTaskStatusReportArguments#getReporter()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionTaskStatusReportArguments.getInstanceId()",
      "sleeper.clients.status.report.compaction.task.CompactionTaskQuery CompactionTaskStatusReportArguments.getQuery()",
      "sleeper.clients.status.report.compaction.task.CompactionTaskStatusReporter CompactionTaskStatusReportArguments.getReporter()"})
  void testGettersAndSetters() {
    // Arrange
    CompactionTaskStatusReportArguments fromArgsResult = CompactionTaskStatusReportArguments.fromArgs("Args");

    // Act
    String actualInstanceId = fromArgsResult.getInstanceId();
    fromArgsResult.getQuery();

    // Assert
    assertTrue(fromArgsResult.getReporter() instanceof StandardCompactionTaskStatusReporter);
    assertEquals("Args", actualInstanceId);
  }
}
