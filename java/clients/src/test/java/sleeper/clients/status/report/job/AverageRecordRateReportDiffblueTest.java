package sleeper.clients.status.report.job;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.job.run.AverageRecordRate;

class AverageRecordRateReportDiffblueTest {
  /**
   * Test {@link AverageRecordRateReport#printf(String, AverageRecordRate, PrintStream)}.
   * <ul>
   *   <li>Given {@link Double#NaN}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AverageRecordRateReport#printf(String, AverageRecordRate, PrintStream)}
   */
  @Test
  @DisplayName("Test printf(String, AverageRecordRate, PrintStream); given NaN")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AverageRecordRateReport.printf(String, AverageRecordRate, PrintStream)"})
  void testPrintf_givenNaN() {
    // Arrange
    AverageRecordRate average = mock(AverageRecordRate.class);
    when(average.getAverageRunRecordsReadPerSecond()).thenReturn(Double.NaN);
    when(average.getAverageRunRecordsWrittenPerSecond()).thenReturn(10.0d);
    when(average.getRunCount()).thenReturn(3);

    // Act
    AverageRecordRateReport.printf("Format", average, new PrintStream(new ByteArrayOutputStream(1)));

    // Assert
    verify(average).getAverageRunRecordsReadPerSecond();
    verify(average).getAverageRunRecordsWrittenPerSecond();
    verify(average).getRunCount();
  }

  /**
   * Test {@link AverageRecordRateReport#printf(String, AverageRecordRate, PrintStream)}.
   * <ul>
   *   <li>Then calls {@link AverageRecordRate#getAverageRunRecordsReadPerSecond()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AverageRecordRateReport#printf(String, AverageRecordRate, PrintStream)}
   */
  @Test
  @DisplayName("Test printf(String, AverageRecordRate, PrintStream); then calls getAverageRunRecordsReadPerSecond()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AverageRecordRateReport.printf(String, AverageRecordRate, PrintStream)"})
  void testPrintf_thenCallsGetAverageRunRecordsReadPerSecond() {
    // Arrange
    AverageRecordRate average = mock(AverageRecordRate.class);
    when(average.getAverageRunRecordsReadPerSecond()).thenReturn(10.0d);
    when(average.getAverageRunRecordsWrittenPerSecond()).thenReturn(10.0d);
    when(average.getRunCount()).thenReturn(3);

    // Act
    AverageRecordRateReport.printf("Format", average, new PrintStream(new ByteArrayOutputStream(1)));

    // Assert
    verify(average).getAverageRunRecordsReadPerSecond();
    verify(average).getAverageRunRecordsWrittenPerSecond();
    verify(average).getRunCount();
  }
}
