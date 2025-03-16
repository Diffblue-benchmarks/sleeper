package sleeper.systemtest.dsl.reporting;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.systemtest.dsl.reporting.SystemTestReports.Builder;

class SystemTestReportsDiffblueTest {
  /**
   * Test Builder {@link Builder#report(SystemTestReport)}.
   * <p>
   * Method under test: {@link Builder#report(SystemTestReport)}
   */
  @Test
  @DisplayName("Test Builder report(SystemTestReport)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.report(SystemTestReport)"})
  void testBuilderReport() {
    // Arrange
    Builder builderResult = SystemTestReports
        .builder(new ReportingContext(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));

    // Act and Assert
    assertSame(builderResult, builderResult.report(mock(SystemTestReport.class)));
  }
}
