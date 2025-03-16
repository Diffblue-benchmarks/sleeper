package sleeper.systemtest.drivers.nightly;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NightlyTestUploadFileDiffblueTest {
  /**
   * Test {@link NightlyTestUploadFile#fileInUploadDir(Path)}.
   * <ul>
   *   <li>Then return RelativeS3Key is {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestUploadFile#fileInUploadDir(Path)}
   */
  @Test
  @DisplayName("Test fileInUploadDir(Path); then return RelativeS3Key is 'test.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestUploadFile NightlyTestUploadFile.fileInUploadDir(Path)"})
  void testFileInUploadDir_thenReturnRelativeS3KeyIsTestTxt() {
    // Arrange
    Path file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NightlyTestUploadFile actualFileInUploadDirResult = NightlyTestUploadFile.fileInUploadDir(file);

    // Assert
    assertEquals("test.txt", actualFileInUploadDirResult.getRelativeS3Key());
    assertSame(file, actualFileInUploadDirResult.getFile());
  }

  /**
   * Test {@link NightlyTestUploadFile#fileInS3RelativeDir(String, Path)}.
   * <ul>
   *   <li>Then return RelativeS3Key is {@code S3 Relative Dir/test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestUploadFile#fileInS3RelativeDir(String, Path)}
   */
  @Test
  @DisplayName("Test fileInS3RelativeDir(String, Path); then return RelativeS3Key is 'S3 Relative Dir/test.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestUploadFile NightlyTestUploadFile.fileInS3RelativeDir(String, Path)"})
  void testFileInS3RelativeDir_thenReturnRelativeS3KeyIsS3RelativeDirTestTxt() {
    // Arrange
    Path file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NightlyTestUploadFile actualFileInS3RelativeDirResult = NightlyTestUploadFile.fileInS3RelativeDir("S3 Relative Dir",
        file);

    // Assert
    assertEquals("S3 Relative Dir/test.txt", actualFileInS3RelativeDirResult.getRelativeS3Key());
    assertSame(file, actualFileInS3RelativeDirResult.getFile());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NightlyTestUploadFile#toString()}
   *   <li>{@link NightlyTestUploadFile#getFile()}
   *   <li>{@link NightlyTestUploadFile#getRelativeS3Key()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path NightlyTestUploadFile.getFile()", "String NightlyTestUploadFile.getRelativeS3Key()",
      "String NightlyTestUploadFile.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Path file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    NightlyTestUploadFile fileInUploadDirResult = NightlyTestUploadFile.fileInUploadDir(file);

    // Act
    String actualToStringResult = fileInUploadDirResult.toString();
    Path actualFile = fileInUploadDirResult.getFile();

    // Assert
    assertEquals("test.txt", fileInUploadDirResult.getRelativeS3Key());
    assertEquals(
        String.join("", "test.txt from ", Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString()),
        actualToStringResult);
    assertSame(file, actualFile);
  }

  /**
   * Test {@link NightlyTestUploadFile#equals(Object)}, and {@link NightlyTestUploadFile#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NightlyTestUploadFile#equals(Object)}
   *   <li>{@link NightlyTestUploadFile#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestUploadFile.equals(Object)", "int NightlyTestUploadFile.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    NightlyTestUploadFile fileInUploadDirResult = NightlyTestUploadFile
        .fileInUploadDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    NightlyTestUploadFile fileInUploadDirResult2 = NightlyTestUploadFile
        .fileInUploadDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Act and Assert
    assertEquals(fileInUploadDirResult, fileInUploadDirResult2);
    int expectedHashCodeResult = fileInUploadDirResult.hashCode();
    assertEquals(expectedHashCodeResult, fileInUploadDirResult2.hashCode());
  }

  /**
   * Test {@link NightlyTestUploadFile#equals(Object)}, and {@link NightlyTestUploadFile#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NightlyTestUploadFile#equals(Object)}
   *   <li>{@link NightlyTestUploadFile#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestUploadFile.equals(Object)", "int NightlyTestUploadFile.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    NightlyTestUploadFile fileInUploadDirResult = NightlyTestUploadFile
        .fileInUploadDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Act and Assert
    assertEquals(fileInUploadDirResult, fileInUploadDirResult);
    int expectedHashCodeResult = fileInUploadDirResult.hashCode();
    assertEquals(expectedHashCodeResult, fileInUploadDirResult.hashCode());
  }

  /**
   * Test {@link NightlyTestUploadFile#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestUploadFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestUploadFile.equals(Object)", "int NightlyTestUploadFile.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    NightlyTestUploadFile fileInUploadDirResult = NightlyTestUploadFile
        .fileInUploadDir(Paths.get(System.getProperty("java.io.tmpdir"), "foo"));

    // Act and Assert
    assertNotEquals(fileInUploadDirResult,
        NightlyTestUploadFile.fileInUploadDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }

  /**
   * Test {@link NightlyTestUploadFile#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestUploadFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestUploadFile.equals(Object)", "int NightlyTestUploadFile.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    NightlyTestUploadFile fileInS3RelativeDirResult = NightlyTestUploadFile.fileInS3RelativeDir("S3 Relative Dir",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Act and Assert
    assertNotEquals(fileInS3RelativeDirResult,
        NightlyTestUploadFile.fileInUploadDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }

  /**
   * Test {@link NightlyTestUploadFile#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestUploadFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestUploadFile.equals(Object)", "int NightlyTestUploadFile.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(NightlyTestUploadFile.fileInUploadDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")),
        null);
  }

  /**
   * Test {@link NightlyTestUploadFile#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestUploadFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestUploadFile.equals(Object)", "int NightlyTestUploadFile.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(NightlyTestUploadFile.fileInUploadDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")),
        "Different type to NightlyTestUploadFile");
  }
}
