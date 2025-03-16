package sleeper.core.tracker.job.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.job.status.JobStatusUpdateRecord.Builder;

class JobStatusesBuilderDiffblueTest {
  /**
   * Test {@link JobStatusesBuilder#update(JobStatusUpdateRecord)}.
   * <p>
   * Method under test: {@link JobStatusesBuilder#update(JobStatusUpdateRecord)}
   */
  @Test
  @DisplayName("Test update(JobStatusUpdateRecord)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobStatusesBuilder JobStatusesBuilder.update(JobStatusUpdateRecord)"})
  void testUpdate() {
    // Arrange
    JobStatusesBuilder jobStatusesBuilder = new JobStatusesBuilder();
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord update = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();

    // Act
    JobStatusesBuilder actualUpdateResult = jobStatusesBuilder.update(update);

    // Assert
    Stream<JobStatusUpdates> streamResult = jobStatusesBuilder.stream();
    List<JobStatusUpdates> collectResult = streamResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    JobStatusUpdates getResult = collectResult.get(0);
    assertEquals("42", getResult.getJobId());
    assertSame(jobStatusesBuilder, actualUpdateResult);
    assertSame(update, getResult.getFirstRecord());
    assertSame(update, getResult.getLastRecord());
  }

  /**
   * Test {@link JobStatusesBuilder#stream()}.
   * <p>
   * Method under test: {@link JobStatusesBuilder#stream()}
   */
  @Test
  @DisplayName("Test stream()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream JobStatusesBuilder.stream()"})
  void testStream() {
    // Arrange and Act
    Stream<JobStatusUpdates> actualStreamResult = (new JobStatusesBuilder()).stream();

    // Assert
    assertTrue(actualStreamResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
