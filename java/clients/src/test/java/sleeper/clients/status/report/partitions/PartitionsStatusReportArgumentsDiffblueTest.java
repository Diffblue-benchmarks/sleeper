package sleeper.clients.status.report.partitions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PartitionsStatusReportArgumentsDiffblueTest {
  /**
   * Test {@link PartitionsStatusReportArguments#fromArgs(String[])}.
   * <ul>
   *   <li>When {@code Args}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsStatusReportArguments#fromArgs(String[])}
   */
  @Test
  @DisplayName("Test fromArgs(String[]); when 'Args'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionsStatusReportArguments PartitionsStatusReportArguments.fromArgs(String[])"})
  void testFromArgs_whenArgs_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PartitionsStatusReportArguments.fromArgs("Args"));
  }
}
