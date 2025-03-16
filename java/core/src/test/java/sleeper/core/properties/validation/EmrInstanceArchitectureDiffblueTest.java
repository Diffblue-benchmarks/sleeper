package sleeper.core.properties.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class EmrInstanceArchitectureDiffblueTest {
  /**
   * Test {@link EmrInstanceArchitecture#isValid(String)}.
   * <ul>
   *   <li>When {@code ARM64,Input}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceArchitecture#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'ARM64,Input'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceArchitecture.isValid(String)"})
  void testIsValid_whenArm64Input_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(EmrInstanceArchitecture.isValid("ARM64,Input"));
  }

  /**
   * Test {@link EmrInstanceArchitecture#isValid(String)}.
   * <ul>
   *   <li>When {@code ARM64}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceArchitecture#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'ARM64'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceArchitecture.isValid(String)"})
  void testIsValid_whenArm64_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(EmrInstanceArchitecture.isValid("ARM64"));
  }

  /**
   * Test {@link EmrInstanceArchitecture#isValid(String)}.
   * <ul>
   *   <li>When {@code ,}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceArchitecture#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when ','; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceArchitecture.isValid(String)"})
  void testIsValid_whenComma_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(EmrInstanceArchitecture.isValid(","));
  }

  /**
   * Test {@link EmrInstanceArchitecture#isValid(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceArchitecture#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when empty string; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceArchitecture.isValid(String)"})
  void testIsValid_whenEmptyString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(EmrInstanceArchitecture.isValid(""));
  }

  /**
   * Test {@link EmrInstanceArchitecture#isValid(String)}.
   * <ul>
   *   <li>When {@code Input}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceArchitecture#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'Input'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceArchitecture.isValid(String)"})
  void testIsValid_whenInput_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(EmrInstanceArchitecture.isValid("Input"));
  }

  /**
   * Test {@link EmrInstanceArchitecture#isValid(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceArchitecture#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceArchitecture.isValid(String)"})
  void testIsValid_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(EmrInstanceArchitecture.isValid(null));
  }
}
