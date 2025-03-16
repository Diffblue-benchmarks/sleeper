package sleeper.query.core.recordretrieval;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RecordRetrievalExceptionDiffblueTest {
  /**
   * Test {@link RecordRetrievalException#RecordRetrievalException(String, Throwable)}.
   * <p>
   * Method under test: {@link RecordRetrievalException#RecordRetrievalException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new RecordRetrievalException(String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordRetrievalException.<init>(String, Throwable)"})
  void testNewRecordRetrievalException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    RecordRetrievalException actualRecordRetrievalException = new RecordRetrievalException("Msg", cause);

    // Assert
    assertEquals("Msg", actualRecordRetrievalException.getMessage());
    assertEquals(0, actualRecordRetrievalException.getSuppressed().length);
    assertSame(cause, actualRecordRetrievalException.getCause());
  }
}
