package sleeper.core.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration.PropertiesWriter;
import org.apache.commons.configuration2.convert.DisabledListDelimiterHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PropertiesUtilsDiffblueTest {
  /**
   * Test {@link PropertiesUtils#loadProperties(File)} with {@code File}.
   * <p>
   * Method under test: {@link PropertiesUtils#loadProperties(File)}
   */
  @Test
  @DisplayName("Test loadProperties(File) with 'File'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Properties PropertiesUtils.loadProperties(File)"})
  void testLoadPropertiesWithFile() {
    // Arrange, Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> PropertiesUtils.loadProperties(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Test {@link PropertiesUtils#loadProperties(Path)} with {@code Path}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code foo} and {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesUtils#loadProperties(Path)}
   */
  @Test
  @DisplayName("Test loadProperties(Path) with 'Path'; when Property is 'java.io.tmpdir' is 'foo' and 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Properties PropertiesUtils.loadProperties(Path)"})
  void testLoadPropertiesWithPath_whenPropertyIsJavaIoTmpdirIsFooAndFoo() {
    // Arrange, Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> PropertiesUtils.loadProperties(Paths.get(System.getProperty("java.io.tmpdir"), "foo", "foo")));
  }

  /**
   * Test {@link PropertiesUtils#loadProperties(Path)} with {@code Path}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesUtils#loadProperties(Path)}
   */
  @Test
  @DisplayName("Test loadProperties(Path) with 'Path'; when Property is 'java.io.tmpdir' is 'test.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Properties PropertiesUtils.loadProperties(Path)"})
  void testLoadPropertiesWithPath_whenPropertyIsJavaIoTmpdirIsTestTxt() {
    // Arrange, Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> PropertiesUtils.loadProperties(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }

  /**
   * Test {@link PropertiesUtils#loadProperties(Reader)} with {@code Reader}.
   * <ul>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesUtils#loadProperties(Reader)}
   */
  @Test
  @DisplayName("Test loadProperties(Reader) with 'Reader'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Properties PropertiesUtils.loadProperties(Reader)"})
  void testLoadPropertiesWithReader_thenThrowUncheckedIOException() {
    // Arrange, Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> PropertiesUtils.loadProperties(new FileReader(new FileDescriptor())));
  }

  /**
   * Test {@link PropertiesUtils#loadProperties(Reader)} with {@code Reader}.
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesUtils#loadProperties(Reader)}
   */
  @Test
  @DisplayName("Test loadProperties(Reader) with 'Reader'; when StringReader(String) with 'foo'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Properties PropertiesUtils.loadProperties(Reader)"})
  void testLoadPropertiesWithReader_whenStringReaderWithFoo_thenReturnSizeIsOne() {
    // Arrange and Act
    Properties actualLoadPropertiesResult = PropertiesUtils.loadProperties(new StringReader("foo"));

    // Assert
    assertEquals(1, actualLoadPropertiesResult.size());
    assertEquals("", actualLoadPropertiesResult.get("foo"));
  }

  /**
   * Test {@link PropertiesUtils#loadProperties(String)} with {@code String}.
   * <p>
   * Method under test: {@link PropertiesUtils#loadProperties(String)}
   */
  @Test
  @DisplayName("Test loadProperties(String) with 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Properties PropertiesUtils.loadProperties(String)"})
  void testLoadPropertiesWithString() {
    // Arrange and Act
    Properties actualLoadPropertiesResult = PropertiesUtils.loadProperties("Input");

    // Assert
    assertEquals(1, actualLoadPropertiesResult.size());
    assertEquals("", actualLoadPropertiesResult.get("Input"));
  }

  /**
   * Test {@link PropertiesUtils#buildPropertiesWriter(Writer)}.
   * <ul>
   *   <li>Then DelimiterHandler return {@link DisabledListDelimiterHandler}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesUtils#buildPropertiesWriter(Writer)}
   */
  @Test
  @DisplayName("Test buildPropertiesWriter(Writer); then DelimiterHandler return DisabledListDelimiterHandler")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PropertiesConfiguration.PropertiesWriter PropertiesUtils.buildPropertiesWriter(Writer)"})
  void testBuildPropertiesWriter_thenDelimiterHandlerReturnDisabledListDelimiterHandler() {
    // Arrange and Act
    PropertiesWriter actualBuildPropertiesWriterResult = PropertiesUtils.buildPropertiesWriter(new StringWriter());

    // Assert
    assertTrue(actualBuildPropertiesWriterResult.getDelimiterHandler() instanceof DisabledListDelimiterHandler);
    assertEquals("=", actualBuildPropertiesWriterResult.getGlobalSeparator());
    assertEquals("\n", actualBuildPropertiesWriterResult.getLineSeparator());
    assertNull(actualBuildPropertiesWriterResult.getCurrentSeparator());
  }

  /**
   * Test {@link PropertiesUtils#toMap(Properties)}.
   * <p>
   * Method under test: {@link PropertiesUtils#toMap(Properties)}
   */
  @Test
  @DisplayName("Test toMap(Properties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map PropertiesUtils.toMap(Properties)"})
  void testToMap() {
    // Arrange and Act
    Map<String, String> actualToMapResult = PropertiesUtils.toMap(new Properties());

    // Assert
    assertTrue(actualToMapResult.isEmpty());
  }
}
