package sleeper.query.core.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class QueryTrackerExceptionDiffblueTest {
  /**
   * Test {@link QueryTrackerException#QueryTrackerException(String)}.
   * <p>
   * Method under test: {@link QueryTrackerException#QueryTrackerException(String)}
   */
  @Test
  @DisplayName("Test new QueryTrackerException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryTrackerException.<init>(String)"})
  void testNewQueryTrackerException() {
    // Arrange and Act
    QueryTrackerException actualQueryTrackerException = new QueryTrackerException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualQueryTrackerException.getMessage());
    assertNull(actualQueryTrackerException.getCause());
    assertEquals(0, actualQueryTrackerException.getSuppressed().length);
  }
}
