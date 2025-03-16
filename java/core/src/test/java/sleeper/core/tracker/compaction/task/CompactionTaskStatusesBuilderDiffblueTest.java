package sleeper.core.tracker.compaction.task;

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

class CompactionTaskStatusesBuilderDiffblueTest {
  /**
   * Test {@link CompactionTaskStatusesBuilder#taskStarted(String, Instant, Instant)}.
   * <p>
   * Method under test: {@link CompactionTaskStatusesBuilder#taskStarted(String, Instant, Instant)}
   */
  @Test
  @DisplayName("Test taskStarted(String, Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "CompactionTaskStatusesBuilder CompactionTaskStatusesBuilder.taskStarted(String, Instant, Instant)"})
  void testTaskStarted() {
    // Arrange
    CompactionTaskStatusesBuilder compactionTaskStatusesBuilder = new CompactionTaskStatusesBuilder();
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    CompactionTaskStatusesBuilder actualTaskStartedResult = compactionTaskStatusesBuilder.taskStarted("42", startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    Stream<CompactionTaskStatus> streamResult = compactionTaskStatusesBuilder.stream();
    assertEquals(1, streamResult.limit(5).collect(Collectors.toList()).size());
    assertEquals(1, compactionTaskStatusesBuilder.build().size());
    assertSame(compactionTaskStatusesBuilder, actualTaskStartedResult);
  }

  /**
   * Test {@link CompactionTaskStatusesBuilder#taskFinished(String, CompactionTaskFinishedStatus)}.
   * <p>
   * Method under test: {@link CompactionTaskStatusesBuilder#taskFinished(String, CompactionTaskFinishedStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(String, CompactionTaskFinishedStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "CompactionTaskStatusesBuilder CompactionTaskStatusesBuilder.taskFinished(String, CompactionTaskFinishedStatus)"})
  void testTaskFinished() {
    // Arrange
    CompactionTaskStatusesBuilder compactionTaskStatusesBuilder = new CompactionTaskStatusesBuilder();

    // Act and Assert
    assertSame(compactionTaskStatusesBuilder,
        compactionTaskStatusesBuilder.taskFinished("42", mock(CompactionTaskFinishedStatus.class)));
  }

  /**
   * Test {@link CompactionTaskStatusesBuilder#stream()}.
   * <p>
   * Method under test: {@link CompactionTaskStatusesBuilder#stream()}
   */
  @Test
  @DisplayName("Test stream()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream CompactionTaskStatusesBuilder.stream()"})
  void testStream() {
    // Arrange and Act
    Stream<CompactionTaskStatus> actualStreamResult = (new CompactionTaskStatusesBuilder()).stream();

    // Assert
    assertTrue(actualStreamResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link CompactionTaskStatusesBuilder#build()}.
   * <p>
   * Method under test: {@link CompactionTaskStatusesBuilder#build()}
   */
  @Test
  @DisplayName("Test build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List CompactionTaskStatusesBuilder.build()"})
  void testBuild() {
    // Arrange, Act and Assert
    assertTrue((new CompactionTaskStatusesBuilder()).build().isEmpty());
  }
}
