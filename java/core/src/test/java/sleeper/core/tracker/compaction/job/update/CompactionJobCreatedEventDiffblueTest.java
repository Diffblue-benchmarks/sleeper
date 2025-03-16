package sleeper.core.tracker.compaction.job.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.compaction.job.update.CompactionJobCreatedEvent.Builder;

class CompactionJobCreatedEventDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#inputFilesCount(int)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#partitionId(String)}
   *   <li>{@link Builder#tableId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobCreatedEvent Builder.build()", "Builder Builder.inputFilesCount(int)",
      "Builder Builder.jobId(String)", "Builder Builder.partitionId(String)", "Builder Builder.tableId(String)"})
  void testBuilderBuild() {
    // Arrange and Act
    CompactionJobCreatedEvent actualBuildResult = CompactionJobCreatedEvent.builder()
        .inputFilesCount(3)
        .jobId("42")
        .partitionId("42")
        .tableId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getJobId());
    assertEquals("42", actualBuildResult.getPartitionId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals(3, actualBuildResult.getInputFilesCount());
  }

  /**
   * Test {@link CompactionJobCreatedEvent#CompactionJobCreatedEvent(Builder)}.
   * <ul>
   *   <li>When builder partitionId {@code 42}.</li>
   *   <li>Then return JobId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCreatedEvent#CompactionJobCreatedEvent(Builder)}
   */
  @Test
  @DisplayName("Test new CompactionJobCreatedEvent(Builder); when builder partitionId '42'; then return JobId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobCreatedEvent.<init>(Builder)"})
  void testNewCompactionJobCreatedEvent_whenBuilderPartitionId42_thenReturnJobIdIs42() {
    // Arrange
    Builder builder = CompactionJobCreatedEvent.builder();
    builder.partitionId("42");
    builder.tableId("42");
    builder.jobId("42");

    // Act
    CompactionJobCreatedEvent actualCompactionJobCreatedEvent = new CompactionJobCreatedEvent(builder);

    // Assert
    assertEquals("42", actualCompactionJobCreatedEvent.getJobId());
    assertEquals("42", actualCompactionJobCreatedEvent.getPartitionId());
    assertEquals("42", actualCompactionJobCreatedEvent.getTableId());
    assertEquals(0, actualCompactionJobCreatedEvent.getInputFilesCount());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobCreatedEvent#getInputFilesCount()}
   *   <li>{@link CompactionJobCreatedEvent#getJobId()}
   *   <li>{@link CompactionJobCreatedEvent#getPartitionId()}
   *   <li>{@link CompactionJobCreatedEvent#getTableId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int CompactionJobCreatedEvent.getInputFilesCount()",
      "String CompactionJobCreatedEvent.getJobId()", "String CompactionJobCreatedEvent.getPartitionId()",
      "String CompactionJobCreatedEvent.getTableId()"})
  void testGettersAndSetters() {
    // Arrange
    CompactionJobCreatedEvent buildResult = CompactionJobCreatedEvent.builder()
        .inputFilesCount(3)
        .jobId("42")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act
    int actualInputFilesCount = buildResult.getInputFilesCount();
    String actualJobId = buildResult.getJobId();
    String actualPartitionId = buildResult.getPartitionId();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualPartitionId);
    assertEquals("42", buildResult.getTableId());
    assertEquals(3, actualInputFilesCount);
  }
}
