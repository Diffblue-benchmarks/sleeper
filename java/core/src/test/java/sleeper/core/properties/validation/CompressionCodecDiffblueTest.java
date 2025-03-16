package sleeper.core.properties.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CompressionCodecDiffblueTest {
  /**
   * Test {@link CompressionCodec#isValid(String)}.
   * <ul>
   *   <li>When {@code BROTLI}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompressionCodec#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'BROTLI'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompressionCodec.isValid(String)"})
  void testIsValid_whenBrotli_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(CompressionCodec.isValid("BROTLI"));
  }

  /**
   * Test {@link CompressionCodec#isValid(String)}.
   * <ul>
   *   <li>When {@code Codec}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompressionCodec#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'Codec'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompressionCodec.isValid(String)"})
  void testIsValid_whenCodec_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(CompressionCodec.isValid("Codec"));
  }

  /**
   * Test {@link CompressionCodec#isValid(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompressionCodec#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompressionCodec.isValid(String)"})
  void testIsValid_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(CompressionCodec.isValid(null));
  }
}
