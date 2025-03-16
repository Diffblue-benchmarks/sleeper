package sleeper.core.tracker.ingest.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class IngestTaskStatusesBuilderDiffblueTest {
  /**
   * Test {@link IngestTaskStatusesBuilder#taskStarted(String, Instant, Instant)}.
   * <p>
   * Method under test: {@link IngestTaskStatusesBuilder#taskStarted(String, Instant, Instant)}
   */
  @Test
  @DisplayName("Test taskStarted(String, Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestTaskStatusesBuilder IngestTaskStatusesBuilder.taskStarted(String, Instant, Instant)"})
  void testTaskStarted() {
    // Arrange
    IngestTaskStatusesBuilder ingestTaskStatusesBuilder = new IngestTaskStatusesBuilder();
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    IngestTaskStatusesBuilder actualTaskStartedResult = ingestTaskStatusesBuilder.taskStarted("42", startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    Stream<IngestTaskStatus> streamResult = ingestTaskStatusesBuilder.stream();
    assertEquals(1, streamResult.limit(5).collect(Collectors.toList()).size());
    assertEquals(1, ingestTaskStatusesBuilder.build().size());
    assertSame(ingestTaskStatusesBuilder, actualTaskStartedResult);
  }

  /**
   * Test {@link IngestTaskStatusesBuilder#taskFinished(String, IngestTaskFinishedStatus)}.
   * <p>
   * Method under test: {@link IngestTaskStatusesBuilder#taskFinished(String, IngestTaskFinishedStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(String, IngestTaskFinishedStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "IngestTaskStatusesBuilder IngestTaskStatusesBuilder.taskFinished(String, IngestTaskFinishedStatus)"})
  void testTaskFinished() {
    // Arrange
    IngestTaskStatusesBuilder ingestTaskStatusesBuilder = new IngestTaskStatusesBuilder();

    // Act and Assert
    assertSame(ingestTaskStatusesBuilder,
        ingestTaskStatusesBuilder.taskFinished("42", mock(IngestTaskFinishedStatus.class)));
  }

  /**
   * Test {@link IngestTaskStatusesBuilder#stream()}.
   * <p>
   * Method under test: {@link IngestTaskStatusesBuilder#stream()}
   */
  @Test
  @DisplayName("Test stream()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream IngestTaskStatusesBuilder.stream()"})
  void testStream() {
    // Arrange and Act
    Stream<IngestTaskStatus> actualStreamResult = (new IngestTaskStatusesBuilder()).stream();

    // Assert
    assertTrue(actualStreamResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link IngestTaskStatusesBuilder#build()}.
   * <p>
   * Method under test: {@link IngestTaskStatusesBuilder#build()}
   */
  @Test
  @DisplayName("Test build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List IngestTaskStatusesBuilder.build()"})
  void testBuild() {
    // Arrange, Act and Assert
    assertTrue((new IngestTaskStatusesBuilder()).build().isEmpty());
  }
}
