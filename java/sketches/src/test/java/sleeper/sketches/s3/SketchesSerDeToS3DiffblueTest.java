package sleeper.sketches.s3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SketchesSerDeToS3DiffblueTest {
  /**
   * Test {@link SketchesSerDeToS3#sketchesPathForDataFile(String)}.
   * <ul>
   *   <li>Then return toUri toString is {@code ///.sketches}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SketchesSerDeToS3#sketchesPathForDataFile(String)}
   */
  @Test
  @DisplayName("Test sketchesPathForDataFile(String); then return toUri toString is '///.sketches'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path SketchesSerDeToS3.sketchesPathForDataFile(String)"})
  void testSketchesPathForDataFile_thenReturnToUriToStringIsSketches() {
    // Arrange and Act
    Path actualSketchesPathForDataFileResult = SketchesSerDeToS3.sketchesPathForDataFile("///");

    // Assert
    assertEquals(".sketches", actualSketchesPathForDataFileResult.getName());
    Path parent = actualSketchesPathForDataFileResult.getParent();
    assertEquals("/", parent.toUri().toString());
    assertEquals("///.sketches", actualSketchesPathForDataFileResult.toUri().toString());
    assertTrue(parent.isAbsolute());
    assertTrue(actualSketchesPathForDataFileResult.isAbsolute());
    assertTrue(parent.isAbsoluteAndSchemeAuthorityNull());
    assertTrue(actualSketchesPathForDataFileResult.isAbsoluteAndSchemeAuthorityNull());
  }

  /**
   * Test {@link SketchesSerDeToS3#sketchesPathForDataFile(String)}.
   * <ul>
   *   <li>When {@code foo.txt}.</li>
   *   <li>Then return toUri toString is {@code foo.sketches}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SketchesSerDeToS3#sketchesPathForDataFile(String)}
   */
  @Test
  @DisplayName("Test sketchesPathForDataFile(String); when 'foo.txt'; then return toUri toString is 'foo.sketches'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path SketchesSerDeToS3.sketchesPathForDataFile(String)"})
  void testSketchesPathForDataFile_whenFooTxt_thenReturnToUriToStringIsFooSketches() {
    // Arrange and Act
    Path actualSketchesPathForDataFileResult = SketchesSerDeToS3.sketchesPathForDataFile("foo.txt");

    // Assert
    Path parent = actualSketchesPathForDataFileResult.getParent();
    assertEquals("", parent.toUri().toString());
    assertEquals("foo.sketches", actualSketchesPathForDataFileResult.toUri().toString());
    assertEquals("foo.sketches", actualSketchesPathForDataFileResult.getName());
    assertFalse(parent.isAbsolute());
    assertFalse(parent.isAbsoluteAndSchemeAuthorityNull());
  }

  /**
   * Test {@link SketchesSerDeToS3#sketchesPathForDataFile(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return toUri toString is {@code null.sketches}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SketchesSerDeToS3#sketchesPathForDataFile(String)}
   */
  @Test
  @DisplayName("Test sketchesPathForDataFile(String); when 'null'; then return toUri toString is 'null.sketches'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path SketchesSerDeToS3.sketchesPathForDataFile(String)"})
  void testSketchesPathForDataFile_whenNull_thenReturnToUriToStringIsNullSketches() {
    // Arrange and Act
    Path actualSketchesPathForDataFileResult = SketchesSerDeToS3.sketchesPathForDataFile(null);

    // Assert
    Path parent = actualSketchesPathForDataFileResult.getParent();
    assertEquals("", parent.toUri().toString());
    assertEquals("null.sketches", actualSketchesPathForDataFileResult.toUri().toString());
    assertEquals("null.sketches", actualSketchesPathForDataFileResult.getName());
    assertFalse(parent.isAbsolute());
    assertFalse(parent.isAbsoluteAndSchemeAuthorityNull());
  }

  /**
   * Test {@link SketchesSerDeToS3#sketchesPathForDataFile(String)}.
   * <ul>
   *   <li>When {@code //}.</li>
   *   <li>Then return Name is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SketchesSerDeToS3#sketchesPathForDataFile(String)}
   */
  @Test
  @DisplayName("Test sketchesPathForDataFile(String); when '//'; then return Name is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path SketchesSerDeToS3.sketchesPathForDataFile(String)"})
  void testSketchesPathForDataFile_whenSlashSlash_thenReturnNameIsEmptyString() {
    // Arrange and Act
    Path actualSketchesPathForDataFileResult = SketchesSerDeToS3.sketchesPathForDataFile("//");

    // Assert
    assertEquals("", actualSketchesPathForDataFileResult.getName());
    assertEquals("//.sketches", actualSketchesPathForDataFileResult.toUri().toString());
    assertNull(actualSketchesPathForDataFileResult.getParent());
    assertTrue(actualSketchesPathForDataFileResult.isRoot());
  }

  /**
   * Test {@link SketchesSerDeToS3#sketchesPathForDataFile(String)}.
   * <ul>
   *   <li>When {@code /}.</li>
   *   <li>Then return toUri toString is {@code /.sketches}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SketchesSerDeToS3#sketchesPathForDataFile(String)}
   */
  @Test
  @DisplayName("Test sketchesPathForDataFile(String); when '/'; then return toUri toString is '/.sketches'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path SketchesSerDeToS3.sketchesPathForDataFile(String)"})
  void testSketchesPathForDataFile_whenSlash_thenReturnToUriToStringIsSketches() {
    // Arrange and Act
    Path actualSketchesPathForDataFileResult = SketchesSerDeToS3.sketchesPathForDataFile("/");

    // Assert
    assertEquals(".sketches", actualSketchesPathForDataFileResult.getName());
    Path parent = actualSketchesPathForDataFileResult.getParent();
    assertEquals("/", parent.toUri().toString());
    assertEquals("/.sketches", actualSketchesPathForDataFileResult.toUri().toString());
    assertTrue(parent.isAbsolute());
    assertTrue(actualSketchesPathForDataFileResult.isAbsolute());
    assertTrue(parent.isAbsoluteAndSchemeAuthorityNull());
    assertTrue(actualSketchesPathForDataFileResult.isAbsoluteAndSchemeAuthorityNull());
  }
}
