package sleeper.build.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.chunks.ProjectStructure;

class ReportableExceptionDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReportableException#ReportableException(String)}
   *   <li>{@link ReportableException#report(PrintStream, ProjectStructure)}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReportableException.<init>(String)",
      "void ReportableException.report(PrintStream, ProjectStructure)"})
  void testGettersAndSetters() {
    // Arrange and Act
    ReportableException actualReportableException = new ReportableException("An error occurred");
    PrintStream out = new PrintStream(new ByteArrayOutputStream(1));
    ProjectStructure project = ProjectStructure.builder()
        .chunksYamlPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .mavenProjectPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .workflowsPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    actualReportableException.report(out, project);

    // Assert
    assertEquals("An error occurred", actualReportableException.getMessage());
    assertNull(actualReportableException.getCause());
    assertEquals(0, actualReportableException.getSuppressed().length);
  }
}
