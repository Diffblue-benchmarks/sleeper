package sleeper.clients.status.report.ingest.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.ingest.task.IngestTaskStatusReportArguments.Builder;

class IngestTaskStatusReportArgumentsDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#instanceId(String)}
   *   <li>{@link Builder#query(IngestTaskQuery)}
   *   <li>{@link Builder#reporter(IngestTaskStatusReporter)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskStatusReportArguments Builder.build()", "Builder Builder.instanceId(String)",
      "Builder Builder.query(IngestTaskQuery)", "Builder Builder.reporter(IngestTaskStatusReporter)"})
  void testBuilderBuild() {
    // Arrange, Act and Assert
    assertEquals("42",
        IngestTaskStatusReportArguments.builder()
            .instanceId("42")
            .query(mock(IngestTaskQuery.class))
            .reporter(mock(IngestTaskStatusReporter.class))
            .build()
            .getInstanceId());
  }

  /**
   * Test {@link IngestTaskStatusReportArguments#fromArgs(String[])}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatusReportArguments#fromArgs(String[])}
   */
  @Test
  @DisplayName("Test fromArgs(String[]); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskStatusReportArguments IngestTaskStatusReportArguments.fromArgs(String[])"})
  void testFromArgs_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> IngestTaskStatusReportArguments.fromArgs());
  }

  /**
   * Test {@link IngestTaskStatusReportArguments#fromArgs(String[])}.
   * <ul>
   *   <li>When {@code Args}.</li>
   *   <li>Then return InstanceId is {@code Args}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatusReportArguments#fromArgs(String[])}
   */
  @Test
  @DisplayName("Test fromArgs(String[]); when 'Args'; then return InstanceId is 'Args'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskStatusReportArguments IngestTaskStatusReportArguments.fromArgs(String[])"})
  void testFromArgs_whenArgs_thenReturnInstanceIdIsArgs() {
    // Arrange and Act
    IngestTaskStatusReportArguments actualFromArgsResult = IngestTaskStatusReportArguments.fromArgs("Args");

    // Assert
    assertTrue(actualFromArgsResult.getReporter() instanceof StandardIngestTaskStatusReporter);
    assertEquals("Args", actualFromArgsResult.getInstanceId());
  }

  /**
   * Test {@link IngestTaskStatusReportArguments#fromArgs(String[])}.
   * <ul>
   *   <li>When {@code header must not be null}.</li>
   *   <li>Then return InstanceId is {@code header must not be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskStatusReportArguments#fromArgs(String[])}
   */
  @Test
  @DisplayName("Test fromArgs(String[]); when 'header must not be null'; then return InstanceId is 'header must not be null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskStatusReportArguments IngestTaskStatusReportArguments.fromArgs(String[])"})
  void testFromArgs_whenHeaderMustNotBeNull_thenReturnInstanceIdIsHeaderMustNotBeNull() {
    // Arrange and Act
    IngestTaskStatusReportArguments actualFromArgsResult = IngestTaskStatusReportArguments
        .fromArgs("header must not be null");

    // Assert
    assertTrue(actualFromArgsResult.getReporter() instanceof StandardIngestTaskStatusReporter);
    assertEquals("header must not be null", actualFromArgsResult.getInstanceId());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestTaskStatusReportArguments#getInstanceId()}
   *   <li>{@link IngestTaskStatusReportArguments#getQuery()}
   *   <li>{@link IngestTaskStatusReportArguments#getReporter()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestTaskStatusReportArguments.getInstanceId()",
      "IngestTaskQuery IngestTaskStatusReportArguments.getQuery()",
      "IngestTaskStatusReporter IngestTaskStatusReportArguments.getReporter()"})
  void testGettersAndSetters() {
    // Arrange
    IngestTaskStatusReportArguments buildResult = IngestTaskStatusReportArguments.builder()
        .instanceId("42")
        .query(mock(IngestTaskQuery.class))
        .reporter(mock(IngestTaskStatusReporter.class))
        .build();

    // Act
    String actualInstanceId = buildResult.getInstanceId();
    buildResult.getQuery();
    buildResult.getReporter();

    // Assert
    assertEquals("42", actualInstanceId);
  }
}
