package sleeper.core.tracker.ingest.job.query;

import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.ingest.job.query.IngestJobStatus.Builder;
import sleeper.core.tracker.job.run.JobRuns;

class IngestJobStatusDiffblueTest {
  /**
   * Test {@link IngestJobStatus#builder()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobStatus#builder()}
   *   <li>{@link IngestJobStatus#expiryDate(Instant)}
   *   <li>{@link IngestJobStatus#jobId(String)}
   *   <li>{@link IngestJobStatus#jobRuns(JobRuns)}
   * </ul>
   */
  @Test
  @DisplayName("Test builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobStatus Builder.build()", "Builder Builder.expiryDate(Instant)",
      "Builder Builder.jobId(String)", "Builder Builder.jobRuns(JobRuns)"})
  void testBuilder() {
    // Arrange and Act
    Builder actualBuilderResult = IngestJobStatus.builder();
    Builder actualJobIdResult = actualBuilderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42");

    // Assert
    assertSame(actualJobIdResult, actualJobIdResult.jobRuns(null));
  }
}
