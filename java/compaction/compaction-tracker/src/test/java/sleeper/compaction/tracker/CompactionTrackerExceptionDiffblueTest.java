package sleeper.compaction.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CompactionTrackerExceptionDiffblueTest {
  /**
   * Test {@link CompactionTrackerException#CompactionTrackerException(String, Throwable)}.
   * <p>
   * Method under test: {@link CompactionTrackerException#CompactionTrackerException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new CompactionTrackerException(String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionTrackerException.<init>(String, Throwable)"})
  void testNewCompactionTrackerException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    CompactionTrackerException actualCompactionTrackerException = new CompactionTrackerException("An error occurred",
        cause);

    // Assert
    assertEquals("An error occurred", actualCompactionTrackerException.getMessage());
    assertEquals(0, actualCompactionTrackerException.getSuppressed().length);
    assertSame(cause, actualCompactionTrackerException.getCause());
  }
}
