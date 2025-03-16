package sleeper.core.deploy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LambdaJarDiffblueTest {
  /**
   * Test {@link LambdaJar#withFormatAndImage(String, String)}.
   * <p>
   * Method under test: {@link LambdaJar#withFormatAndImage(String, String)}
   */
  @Test
  @DisplayName("Test withFormatAndImage(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"LambdaJar LambdaJar.withFormatAndImage(String, String)"})
  void testWithFormatAndImage() {
    // Arrange and Act
    LambdaJar actualWithFormatAndImageResult = LambdaJar.withFormatAndImage("Format", "Image Name");

    // Assert
    assertEquals("Format", actualWithFormatAndImageResult.getFilename());
    assertEquals("Image Name", actualWithFormatAndImageResult.getImageName());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LambdaJar#toString()}
   *   <li>{@link LambdaJar#getFilename()}
   *   <li>{@link LambdaJar#getImageName()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LambdaJar.getFilename()", "String LambdaJar.getImageName()",
      "String LambdaJar.toString()"})
  void testGettersAndSetters() {
    // Arrange
    LambdaJar withFormatAndImageResult = LambdaJar.withFormatAndImage("Format", "Image Name");

    // Act
    String actualToStringResult = withFormatAndImageResult.toString();
    String actualFilename = withFormatAndImageResult.getFilename();

    // Assert
    assertEquals("Format", actualFilename);
    assertEquals("Image Name", withFormatAndImageResult.getImageName());
    assertEquals("LambdaJar{filename=Format, imageName=Image Name}", actualToStringResult);
  }

  /**
   * Test {@link LambdaJar#isFileJar(Path, LambdaJar[])}.
   * <ul>
   *   <li>When {@link LambdaJar#ATHENA} and {@link LambdaJar#ATHENA}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaJar#isFileJar(Path, LambdaJar[])}
   */
  @Test
  @DisplayName("Test isFileJar(Path, LambdaJar[]); when ATHENA and ATHENA; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaJar.isFileJar(Path, LambdaJar[])"})
  void testIsFileJar_whenAthenaAndAthena_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(LambdaJar.isFileJar(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), LambdaJar.ATHENA,
        LambdaJar.ATHENA));
  }

  /**
   * Test {@link LambdaJar#isFileJar(Path, LambdaJar[])}.
   * <ul>
   *   <li>When {@link LambdaJar#ATHENA}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaJar#isFileJar(Path, LambdaJar[])}
   */
  @Test
  @DisplayName("Test isFileJar(Path, LambdaJar[]); when ATHENA; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaJar.isFileJar(Path, LambdaJar[])"})
  void testIsFileJar_whenAthena_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(LambdaJar.isFileJar(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), LambdaJar.ATHENA));
  }

  /**
   * Test {@link LambdaJar#isFileJar(Path, LambdaJar[])}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaJar#isFileJar(Path, LambdaJar[])}
   */
  @Test
  @DisplayName("Test isFileJar(Path, LambdaJar[]); when Property is 'java.io.tmpdir' is 'test.txt'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaJar.isFileJar(Path, LambdaJar[])"})
  void testIsFileJar_whenPropertyIsJavaIoTmpdirIsTestTxt_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(LambdaJar.isFileJar(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }
}
