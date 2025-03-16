package sleeper.build.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ValidationUtilsDiffblueTest {
  /**
   * Test {@link ValidationUtils#ignoreEmpty(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ValidationUtils#ignoreEmpty(String)}
   */
  @Test
  @DisplayName("Test ignoreEmpty(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ValidationUtils.ignoreEmpty(String)"})
  void testIgnoreEmpty_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(ValidationUtils.ignoreEmpty(""));
  }

  /**
   * Test {@link ValidationUtils#ignoreEmpty(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ValidationUtils#ignoreEmpty(String)}
   */
  @Test
  @DisplayName("Test ignoreEmpty(String); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ValidationUtils.ignoreEmpty(String)"})
  void testIgnoreEmpty_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(ValidationUtils.ignoreEmpty(null));
  }

  /**
   * Test {@link ValidationUtils#ignoreEmpty(String)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then return {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ValidationUtils#ignoreEmpty(String)}
   */
  @Test
  @DisplayName("Test ignoreEmpty(String); when 'String'; then return 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ValidationUtils.ignoreEmpty(String)"})
  void testIgnoreEmpty_whenString_thenReturnString() {
    // Arrange, Act and Assert
    assertEquals("String", ValidationUtils.ignoreEmpty("String"));
  }
}
