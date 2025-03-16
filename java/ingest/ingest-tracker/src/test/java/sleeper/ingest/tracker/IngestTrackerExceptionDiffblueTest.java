package sleeper.ingest.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class IngestTrackerExceptionDiffblueTest {
  /**
   * Test {@link IngestTrackerException#IngestTrackerException(String, Throwable)}.
   * <p>
   * Method under test: {@link IngestTrackerException#IngestTrackerException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new IngestTrackerException(String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestTrackerException.<init>(String, Throwable)"})
  void testNewIngestTrackerException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    IngestTrackerException actualIngestTrackerException = new IngestTrackerException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualIngestTrackerException.getMessage());
    assertEquals(0, actualIngestTrackerException.getSuppressed().length);
    assertSame(cause, actualIngestTrackerException.getCause());
  }
}
