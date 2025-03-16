package sleeper.build.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PathUtilsDiffblueTest {
  /**
   * Test {@link PathUtils#commonPath(Path, Path)}.
   * <ul>
   *   <li>Then return toFile Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link PathUtils#commonPath(Path, Path)}
   */
  @Test
  @DisplayName("Test commonPath(Path, Path); then return toFile Name is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path PathUtils.commonPath(Path, Path)"})
  void testCommonPath_thenReturnToFileNameIsEmptyString() {
    // Arrange, Act and Assert
    File toFileResult = PathUtils
        .commonPath(Paths.get(System.getProperty("java.io.tmpdir"), ""),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .toFile();
    assertEquals("", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link PathUtils#commonPath(Path, Path)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt}.</li>
   *   <li>Then return toFile Name is {@code tmp}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PathUtils#commonPath(Path, Path)}
   */
  @Test
  @DisplayName("Test commonPath(Path, Path); when Property is 'java.io.tmpdir' is 'test.txt'; then return toFile Name is 'tmp'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path PathUtils.commonPath(Path, Path)"})
  void testCommonPath_whenPropertyIsJavaIoTmpdirIsTestTxt_thenReturnToFileNameIsTmp() {
    // Arrange, Act and Assert
    File toFileResult = PathUtils
        .commonPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .toFile();
    assertEquals("tmp", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }
}
