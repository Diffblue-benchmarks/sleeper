package sleeper.systemtest.dsl.reporting;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintStream;
import java.nio.file.Path;
import java.time.Instant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.systemtest.dsl.util.TestContext;

class ReportingContextDiffblueTest {
  /**
   * Test {@link ReportingContext#print(TestContext, SystemTestReport)}.
   * <ul>
   *   <li>Given Property is {@code java.io.tmpdir}.</li>
   *   <li>Then calls {@link SystemTestReport#print(PrintStream, Instant)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReportingContext#print(TestContext, SystemTestReport)}
   */
  @Test
  @DisplayName("Test print(TestContext, SystemTestReport); given Property is 'java.io.tmpdir'; then calls print(PrintStream, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReportingContext.print(TestContext, SystemTestReport)"})
  void testPrint_givenPropertyIsJavaIoTmpdir_thenCallsPrint() {
    // Arrange
    System.getProperty("java.io.tmpdir");
    ReportingContext reportingContext = new ReportingContext((Path) null);
    TestContext testContext = mock(TestContext.class);
    SystemTestReport report = mock(SystemTestReport.class);
    doNothing().when(report).print(Mockito.<PrintStream>any(), Mockito.<Instant>any());

    // Act
    reportingContext.print(testContext, report);

    // Assert
    verify(report).print(isA(PrintStream.class), isA(Instant.class));
  }
}
