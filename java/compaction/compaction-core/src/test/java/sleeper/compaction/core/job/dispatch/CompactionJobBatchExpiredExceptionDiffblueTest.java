package sleeper.compaction.core.job.dispatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

class CompactionJobBatchExpiredExceptionDiffblueTest {
  /**
   * Test {@link
   * CompactionJobBatchExpiredException#CompactionJobBatchExpiredException(CompactionJobDispatchRequest,
   * Instant)}.
   *
   * <p>Method under test: {@link
   * CompactionJobBatchExpiredException#CompactionJobBatchExpiredException(CompactionJobDispatchRequest,
   * Instant)}
   */
  @Test
  @DisplayName("Test new CompactionJobBatchExpiredException(CompactionJobDispatchRequest, Instant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CompactionJobBatchExpiredException.<init>(CompactionJobDispatchRequest, Instant)"
  })
  void testNewCompactionJobBatchExpiredException() {
    // Arrange
    CompactionJobDispatchRequest request =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    CompactionJobBatchExpiredException actualCompactionJobBatchExpiredException =
        new CompactionJobBatchExpiredException(
            request, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(
        "Dispatch request for table null expired at 1970-01-01T00:00:00Z, batch key: null/compactions/42.json",
        actualCompactionJobBatchExpiredException.getLocalizedMessage());
    assertEquals(
        "Dispatch request for table null expired at 1970-01-01T00:00:00Z, batch key: null/compactions/42.json",
        actualCompactionJobBatchExpiredException.getMessage());
    assertNull(actualCompactionJobBatchExpiredException.getCause());
    assertEquals(0, actualCompactionJobBatchExpiredException.getSuppressed().length);
  }
}
