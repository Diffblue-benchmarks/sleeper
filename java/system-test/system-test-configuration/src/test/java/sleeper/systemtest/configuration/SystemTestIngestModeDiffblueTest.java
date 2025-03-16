package sleeper.systemtest.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SystemTestIngestModeDiffblueTest {
  /**
   * Test {@link SystemTestIngestMode#toString()}.
   * <p>
   * Method under test: {@link SystemTestIngestMode#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SystemTestIngestMode.toString()"})
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("direct", SystemTestIngestMode.valueOf("DIRECT").toString());
  }
}
