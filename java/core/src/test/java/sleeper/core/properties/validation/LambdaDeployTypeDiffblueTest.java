package sleeper.core.properties.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LambdaDeployTypeDiffblueTest {
  /**
   * Test {@link LambdaDeployType#isValid(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaDeployType#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when '42'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaDeployType.isValid(String)"})
  void testIsValid_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(LambdaDeployType.isValid("42"));
  }

  /**
   * Test {@link LambdaDeployType#isValid(String)}.
   * <ul>
   *   <li>When {@code CONTAINER}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaDeployType#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'CONTAINER'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaDeployType.isValid(String)"})
  void testIsValid_whenContainer_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(LambdaDeployType.isValid("CONTAINER"));
  }

  /**
   * Test {@link LambdaDeployType#isValid(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaDeployType#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaDeployType.isValid(String)"})
  void testIsValid_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(LambdaDeployType.isValid(null));
  }
}
