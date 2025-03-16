package sleeper.environment.cdk.buildec2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.security.KeyPair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class KeyPairUtilDiffblueTest {
  /**
   * Test {@link KeyPairUtil#generate()}.
   * <p>
   * Method under test: {@link KeyPairUtil#generate()}
   */
  @Test
  @DisplayName("Test generate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"KeyPair KeyPairUtil.generate()"})
  void testGenerate() {
    // Arrange, Act and Assert
    assertFalse(KeyPairUtil.generate().getPrivate().isDestroyed());
  }

  /**
   * Test {@link KeyPairUtil#writePrivateToFile(KeyPair, String)}.
   * <p>
   * Method under test: {@link KeyPairUtil#writePrivateToFile(KeyPair, String)}
   */
  @Test
  @DisplayName("Test writePrivateToFile(KeyPair, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyPairUtil.writePrivateToFile(KeyPair, String)"})
  void testWritePrivateToFile() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any()))
          .thenThrow(new IOException("Could not write private key"));
      mockFiles.when(() -> Files.createFile(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenThrow(new IOException("Could not write private key"));

      // Act and Assert
      assertThrows(IllegalStateException.class, () -> KeyPairUtil.writePrivateToFile(null, "foo.txt"));
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
    }
  }

  /**
   * Test {@link KeyPairUtil#writePrivateToFile(KeyPair, String)}.
   * <ul>
   *   <li>Given {@link Files} {@link Files#deleteIfExists(Path)} return {@code true}.</li>
   *   <li>Then calls {@link Files#createFile(Path, FileAttribute[])}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyPairUtil#writePrivateToFile(KeyPair, String)}
   */
  @Test
  @DisplayName("Test writePrivateToFile(KeyPair, String); given Files deleteIfExists(Path) return 'true'; then calls createFile(Path, FileAttribute[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyPairUtil.writePrivateToFile(KeyPair, String)"})
  void testWritePrivateToFile_givenFilesDeleteIfExistsReturnTrue_thenCallsCreateFile() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenReturn(true);
      mockFiles.when(() -> Files.createFile(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Act and Assert
      assertThrows(IllegalStateException.class, () -> KeyPairUtil.writePrivateToFile(null, "foo.txt"));
      mockFiles.verify(() -> Files.createFile(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
    }
  }
}
