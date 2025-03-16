package sleeper.core.properties.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.awt.Component;
import java.awt.Component.BaselineResizeBehavior;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SleeperPropertyValueUtilsDiffblueTest {
  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveInteger(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveInteger(String)}
   */
  @Test
  @DisplayName("Test isPositiveInteger(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveInteger(String)"})
  void testIsPositiveInteger_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isPositiveInteger("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveInteger(String)}.
   * <ul>
   *   <li>When {@code Integer}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveInteger(String)}
   */
  @Test
  @DisplayName("Test isPositiveInteger(String); when 'Integer'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveInteger(String)"})
  void testIsPositiveInteger_whenInteger_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isPositiveInteger("Integer"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveIntegerOrNull(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveIntegerOrNull(String)}
   */
  @Test
  @DisplayName("Test isPositiveIntegerOrNull(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveIntegerOrNull(String)"})
  void testIsPositiveIntegerOrNull_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isPositiveIntegerOrNull("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveIntegerOrNull(String)}.
   * <ul>
   *   <li>When {@code Integer}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveIntegerOrNull(String)}
   */
  @Test
  @DisplayName("Test isPositiveIntegerOrNull(String); when 'Integer'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveIntegerOrNull(String)"})
  void testIsPositiveIntegerOrNull_whenInteger_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isPositiveIntegerOrNull("Integer"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveIntegerOrNull(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveIntegerOrNull(String)}
   */
  @Test
  @DisplayName("Test isPositiveIntegerOrNull(String); when 'null'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveIntegerOrNull(String)"})
  void testIsPositiveIntegerOrNull_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isPositiveIntegerOrNull(null));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveIntegerLtEq10(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveIntegerLtEq10(String)}
   */
  @Test
  @DisplayName("Test isPositiveIntegerLtEq10(String); when '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveIntegerLtEq10(String)"})
  void testIsPositiveIntegerLtEq10_when42() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isPositiveIntegerLtEq10("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveIntegerLtEq10(String)}.
   * <ul>
   *   <li>When {@code Integer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveIntegerLtEq10(String)}
   */
  @Test
  @DisplayName("Test isPositiveIntegerLtEq10(String); when 'Integer'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveIntegerLtEq10(String)"})
  void testIsPositiveIntegerLtEq10_whenInteger() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isPositiveIntegerLtEq10("Integer"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNegativeInteger(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNegativeInteger(String)}
   */
  @Test
  @DisplayName("Test isNonNegativeInteger(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNegativeInteger(String)"})
  void testIsNonNegativeInteger_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isNonNegativeInteger("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNegativeInteger(String)}.
   * <ul>
   *   <li>When {@code Integer}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNegativeInteger(String)}
   */
  @Test
  @DisplayName("Test isNonNegativeInteger(String); when 'Integer'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNegativeInteger(String)"})
  void testIsNonNegativeInteger_whenInteger_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNegativeInteger("Integer"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNegativeIntegerOrNull(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNegativeIntegerOrNull(String)}
   */
  @Test
  @DisplayName("Test isNonNegativeIntegerOrNull(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNegativeIntegerOrNull(String)"})
  void testIsNonNegativeIntegerOrNull_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isNonNegativeIntegerOrNull("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNegativeIntegerOrNull(String)}.
   * <ul>
   *   <li>When {@code Integer}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNegativeIntegerOrNull(String)}
   */
  @Test
  @DisplayName("Test isNonNegativeIntegerOrNull(String); when 'Integer'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNegativeIntegerOrNull(String)"})
  void testIsNonNegativeIntegerOrNull_whenInteger_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNegativeIntegerOrNull("Integer"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNegativeIntegerOrNull(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNegativeIntegerOrNull(String)}
   */
  @Test
  @DisplayName("Test isNonNegativeIntegerOrNull(String); when 'null'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNegativeIntegerOrNull(String)"})
  void testIsNonNegativeIntegerOrNull_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isNonNegativeIntegerOrNull(null));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isInteger(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isInteger(String)}
   */
  @Test
  @DisplayName("Test isInteger(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isInteger(String)"})
  void testIsInteger_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isInteger("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isInteger(String)}.
   * <ul>
   *   <li>When {@code Integer}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isInteger(String)}
   */
  @Test
  @DisplayName("Test isInteger(String); when 'Integer'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isInteger(String)"})
  void testIsInteger_whenInteger_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isInteger("Integer"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveLong(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveLong(String)}
   */
  @Test
  @DisplayName("Test isPositiveLong(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveLong(String)"})
  void testIsPositiveLong_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isPositiveLong("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveLong(String)}.
   * <ul>
   *   <li>When {@code Value}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveLong(String)}
   */
  @Test
  @DisplayName("Test isPositiveLong(String); when 'Value'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveLong(String)"})
  void testIsPositiveLong_whenValue_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isPositiveLong("Value"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isLong(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isLong(String)}
   */
  @Test
  @DisplayName("Test isLong(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isLong(String)"})
  void testIsLong_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isLong("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isLong(String)}.
   * <ul>
   *   <li>When {@code Value}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isLong(String)}
   */
  @Test
  @DisplayName("Test isLong(String); when 'Value'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isLong(String)"})
  void testIsLong_whenValue_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isLong("Value"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveDouble(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveDouble(String)}
   */
  @Test
  @DisplayName("Test isPositiveDouble(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveDouble(String)"})
  void testIsPositiveDouble_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isPositiveDouble("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveDouble(String)}.
   * <ul>
   *   <li>When {@code Value}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveDouble(String)}
   */
  @Test
  @DisplayName("Test isPositiveDouble(String); when 'Value'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveDouble(String)"})
  void testIsPositiveDouble_whenValue_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isPositiveDouble("Value"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNullNonEmptyString(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNullNonEmptyString(String)}
   */
  @Test
  @DisplayName("Test isNonNullNonEmptyString(String); when empty string; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNullNonEmptyString(String)"})
  void testIsNonNullNonEmptyString_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNullNonEmptyString(""));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNullNonEmptyString(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNullNonEmptyString(String)}
   */
  @Test
  @DisplayName("Test isNonNullNonEmptyString(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNullNonEmptyString(String)"})
  void testIsNonNullNonEmptyString_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNullNonEmptyString(null));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNullNonEmptyString(String)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNullNonEmptyString(String)}
   */
  @Test
  @DisplayName("Test isNonNullNonEmptyString(String); when 'String'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNullNonEmptyString(String)"})
  void testIsNonNullNonEmptyString_whenString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isNonNullNonEmptyString("String"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNullNonEmptyStringWithMaxLength(String, int)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNullNonEmptyStringWithMaxLength(String, int)}
   */
  @Test
  @DisplayName("Test isNonNullNonEmptyStringWithMaxLength(String, int); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNullNonEmptyStringWithMaxLength(String, int)"})
  void testIsNonNullNonEmptyStringWithMaxLength_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isNonNullNonEmptyStringWithMaxLength("42", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNullNonEmptyStringWithMaxLength(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNullNonEmptyStringWithMaxLength(String, int)}
   */
  @Test
  @DisplayName("Test isNonNullNonEmptyStringWithMaxLength(String, int); when empty string; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNullNonEmptyStringWithMaxLength(String, int)"})
  void testIsNonNullNonEmptyStringWithMaxLength_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNullNonEmptyStringWithMaxLength("", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNullNonEmptyStringWithMaxLength(String, int)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNullNonEmptyStringWithMaxLength(String, int)}
   */
  @Test
  @DisplayName("Test isNonNullNonEmptyStringWithMaxLength(String, int); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNullNonEmptyStringWithMaxLength(String, int)"})
  void testIsNonNullNonEmptyStringWithMaxLength_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNullNonEmptyStringWithMaxLength(null, 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNullNonEmptyStringWithMaxLength(String, int)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNullNonEmptyStringWithMaxLength(String, int)}
   */
  @Test
  @DisplayName("Test isNonNullNonEmptyStringWithMaxLength(String, int); when 'String'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNullNonEmptyStringWithMaxLength(String, int)"})
  void testIsNonNullNonEmptyStringWithMaxLength_whenString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNullNonEmptyStringWithMaxLength("String", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isTrueOrFalse(String)}.
   * <ul>
   *   <li>When {@link Boolean#FALSE} toString.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isTrueOrFalse(String)}
   */
  @Test
  @DisplayName("Test isTrueOrFalse(String); when FALSE toString; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isTrueOrFalse(String)"})
  void testIsTrueOrFalse_whenFalseToString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isTrueOrFalse(Boolean.FALSE.toString()));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isTrueOrFalse(String)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isTrueOrFalse(String)}
   */
  @Test
  @DisplayName("Test isTrueOrFalse(String); when 'String'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isTrueOrFalse(String)"})
  void testIsTrueOrFalse_whenString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isTrueOrFalse("String"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isTrueOrFalse(String)}.
   * <ul>
   *   <li>When {@link Boolean#TRUE} toString.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isTrueOrFalse(String)}
   */
  @Test
  @DisplayName("Test isTrueOrFalse(String); when TRUE toString; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isTrueOrFalse(String)"})
  void testIsTrueOrFalse_whenTrueToString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isTrueOrFalse(Boolean.TRUE.toString()));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidLambdaTimeout(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidLambdaTimeout(String)}
   */
  @Test
  @DisplayName("Test isValidLambdaTimeout(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidLambdaTimeout(String)"})
  void testIsValidLambdaTimeout_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidLambdaTimeout("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidLambdaTimeout(String)}.
   * <ul>
   *   <li>When {@code 4242}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidLambdaTimeout(String)}
   */
  @Test
  @DisplayName("Test isValidLambdaTimeout(String); when '4242'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidLambdaTimeout(String)"})
  void testIsValidLambdaTimeout_when4242_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidLambdaTimeout("4242"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidLambdaTimeout(String)}.
   * <ul>
   *   <li>When {@code Timeout}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidLambdaTimeout(String)}
   */
  @Test
  @DisplayName("Test isValidLambdaTimeout(String); when 'Timeout'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidLambdaTimeout(String)"})
  void testIsValidLambdaTimeout_whenTimeout_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidLambdaTimeout("Timeout"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidSqsLambdaMaximumConcurrency(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidSqsLambdaMaximumConcurrency(String)}
   */
  @Test
  @DisplayName("Test isValidSqsLambdaMaximumConcurrency(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidSqsLambdaMaximumConcurrency(String)"})
  void testIsValidSqsLambdaMaximumConcurrency_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidSqsLambdaMaximumConcurrency("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidSqsLambdaMaximumConcurrency(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidSqsLambdaMaximumConcurrency(String)}
   */
  @Test
  @DisplayName("Test isValidSqsLambdaMaximumConcurrency(String); when 'null'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidSqsLambdaMaximumConcurrency(String)"})
  void testIsValidSqsLambdaMaximumConcurrency_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidSqsLambdaMaximumConcurrency(null));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidSqsLambdaMaximumConcurrency(String)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidSqsLambdaMaximumConcurrency(String)}
   */
  @Test
  @DisplayName("Test isValidSqsLambdaMaximumConcurrency(String); when 'String'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidSqsLambdaMaximumConcurrency(String)"})
  void testIsValidSqsLambdaMaximumConcurrency_whenString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidSqsLambdaMaximumConcurrency("String"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidFadvise(String)}.
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidFadvise(String)}
   */
  @Test
  @DisplayName("Test isValidFadvise(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidFadvise(String)"})
  void testIsValidFadvise() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidFadvise("Fadvise"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidLogRetention(String)}.
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidLogRetention(String)}
   */
  @Test
  @DisplayName("Test isValidLogRetention(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidLogRetention(String)"})
  void testIsValidLogRetention() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidLogRetention("Log Retention"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 9}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '9'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when9_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("9"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 9E}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '9E'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when9e_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("9E"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 9G}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '9G'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when9g_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("9G"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 9K}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '9K'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when9k_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("9K"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 9M}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '9M'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when9m_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("9M"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 9P}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '9P'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when9p_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("9P"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 9T}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '9T'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when9t_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("9T"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 42E}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '42E'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when42e_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("42E"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 42G}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '42G'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when42g_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("42G"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 42K}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '42K'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when42k_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("42K"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 42M}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '42M'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when42m_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("42M"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 42P}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '42P'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when42p_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("42P"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 42T}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '42T'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when42t_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("42T"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 99}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '99'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when99_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("99"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 429}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '429'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when429_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("429"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 429E}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '429E'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when429e_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("429E"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 429G}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '429G'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when429g_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("429G"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 429K}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '429K'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when429k_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("429K"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 429M}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '429M'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when429m_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("429M"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 429P}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '429P'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when429p_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("429P"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 429T}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '429T'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when429t_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("429T"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 942}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '942'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when942_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("942"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 4242}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '4242'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when4242_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("4242"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 4242E}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '4242E'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when4242e_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("4242E"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 4242G}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '4242G'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when4242g_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("4242G"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 4242K}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '4242K'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when4242k_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("4242K"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 4242M}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '4242M'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when4242m_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("4242M"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 4242P}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '4242P'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when4242p_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("4242P"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 4242T}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '4242T'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when4242t_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("4242T"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 4299}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '4299'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when4299_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("4299"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 42429}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '42429'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when42429_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("42429"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 42942}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '42942'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when42942_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("42942"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code 424242}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '424242'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_when424242_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidHadoopLongBytes("424242"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code \d+[KMGTPE]?}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when '\\d+[KMGTPE]?'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_whenDKmgtpe_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidHadoopLongBytes("\\d+[KMGTPE]?"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when empty string; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidHadoopLongBytes(""));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidHadoopLongBytes(String)}
   */
  @Test
  @DisplayName("Test isValidHadoopLongBytes(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidHadoopLongBytes(String)"})
  void testIsValidHadoopLongBytes_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidHadoopLongBytes(null));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidNumberOfBytes(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidNumberOfBytes(String)}
   */
  @Test
  @DisplayName("Test isValidNumberOfBytes(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidNumberOfBytes(String)"})
  void testIsValidNumberOfBytes_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidNumberOfBytes("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidNumberOfBytes(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidNumberOfBytes(String)}
   */
  @Test
  @DisplayName("Test isValidNumberOfBytes(String); when empty string; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidNumberOfBytes(String)"})
  void testIsValidNumberOfBytes_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidNumberOfBytes(""));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidNumberOfBytes(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidNumberOfBytes(String)}
   */
  @Test
  @DisplayName("Test isValidNumberOfBytes(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidNumberOfBytes(String)"})
  void testIsValidNumberOfBytes_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidNumberOfBytes(null));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#readBytes(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#readBytes(String)}
   */
  @Test
  @DisplayName("Test readBytes(String); when '42'; then return forty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long SleeperPropertyValueUtils.readBytes(String)"})
  void testReadBytes_when42_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42L, SleeperPropertyValueUtils.readBytes("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#readBytes(String)}.
   * <ul>
   *   <li>When {@code 42E}.</li>
   *   <li>Then return {@code -6917529027641081856}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#readBytes(String)}
   */
  @Test
  @DisplayName("Test readBytes(String); when '42E'; then return '-6917529027641081856'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long SleeperPropertyValueUtils.readBytes(String)"})
  void testReadBytes_when42e_thenReturn6917529027641081856() {
    // Arrange, Act and Assert
    assertEquals(-6917529027641081856L, SleeperPropertyValueUtils.readBytes("42E"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#readBytes(String)}.
   * <ul>
   *   <li>When {@code 42G}.</li>
   *   <li>Then return {@code 45097156608}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#readBytes(String)}
   */
  @Test
  @DisplayName("Test readBytes(String); when '42G'; then return '45097156608'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long SleeperPropertyValueUtils.readBytes(String)"})
  void testReadBytes_when42g_thenReturn45097156608() {
    // Arrange, Act and Assert
    assertEquals(45097156608L, SleeperPropertyValueUtils.readBytes("42G"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#readBytes(String)}.
   * <ul>
   *   <li>When {@code 42K}.</li>
   *   <li>Then return {@code 43008}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#readBytes(String)}
   */
  @Test
  @DisplayName("Test readBytes(String); when '42K'; then return '43008'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long SleeperPropertyValueUtils.readBytes(String)"})
  void testReadBytes_when42k_thenReturn43008() {
    // Arrange, Act and Assert
    assertEquals(43008L, SleeperPropertyValueUtils.readBytes("42K"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#readBytes(String)}.
   * <ul>
   *   <li>When {@code 42M}.</li>
   *   <li>Then return {@code 44040192}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#readBytes(String)}
   */
  @Test
  @DisplayName("Test readBytes(String); when '42M'; then return '44040192'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long SleeperPropertyValueUtils.readBytes(String)"})
  void testReadBytes_when42m_thenReturn44040192() {
    // Arrange, Act and Assert
    assertEquals(44040192L, SleeperPropertyValueUtils.readBytes("42M"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#readBytes(String)}.
   * <ul>
   *   <li>When {@code 42P}.</li>
   *   <li>Then return {@code 47287796087390208}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#readBytes(String)}
   */
  @Test
  @DisplayName("Test readBytes(String); when '42P'; then return '47287796087390208'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long SleeperPropertyValueUtils.readBytes(String)"})
  void testReadBytes_when42p_thenReturn47287796087390208() {
    // Arrange, Act and Assert
    assertEquals(47287796087390208L, SleeperPropertyValueUtils.readBytes("42P"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#readBytes(String)}.
   * <ul>
   *   <li>When {@code 42T}.</li>
   *   <li>Then return {@code 46179488366592}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#readBytes(String)}
   */
  @Test
  @DisplayName("Test readBytes(String); when '42T'; then return '46179488366592'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long SleeperPropertyValueUtils.readBytes(String)"})
  void testReadBytes_when42t_thenReturn46179488366592() {
    // Arrange, Act and Assert
    assertEquals(46179488366592L, SleeperPropertyValueUtils.readBytes("42T"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidEbsSize(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidEbsSize(String)}
   */
  @Test
  @DisplayName("Test isValidEbsSize(String); when '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidEbsSize(String)"})
  void testIsValidEbsSize_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidEbsSize("42"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidEbsSize(String)}.
   * <ul>
   *   <li>When {@code 4242}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidEbsSize(String)}
   */
  @Test
  @DisplayName("Test isValidEbsSize(String); when '4242'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidEbsSize(String)"})
  void testIsValidEbsSize_when4242_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidEbsSize("4242"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidEbsSize(String)}.
   * <ul>
   *   <li>When {@code Ebs Size In Gb}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidEbsSize(String)}
   */
  @Test
  @DisplayName("Test isValidEbsSize(String); when 'Ebs Size In Gb'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidEbsSize(String)"})
  void testIsValidEbsSize_whenEbsSizeInGb_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidEbsSize("Ebs Size In Gb"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidEbsSize(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidEbsSize(String)}
   */
  @Test
  @DisplayName("Test isValidEbsSize(String); when empty string; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidEbsSize(String)"})
  void testIsValidEbsSize_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidEbsSize(""));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidEbsSize(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidEbsSize(String)}
   */
  @Test
  @DisplayName("Test isValidEbsSize(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidEbsSize(String)"})
  void testIsValidEbsSize_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidEbsSize(null));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidEbsVolumeType(String)}.
   * <ul>
   *   <li>When {@code Ebs Volume Type}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidEbsVolumeType(String)}
   */
  @Test
  @DisplayName("Test isValidEbsVolumeType(String); when 'Ebs Volume Type'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidEbsVolumeType(String)"})
  void testIsValidEbsVolumeType_whenEbsVolumeType_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isValidEbsVolumeType("Ebs Volume Type"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isValidEbsVolumeType(String)}.
   * <ul>
   *   <li>When {@code io2}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isValidEbsVolumeType(String)}
   */
  @Test
  @DisplayName("Test isValidEbsVolumeType(String); when 'io2'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isValidEbsVolumeType(String)"})
  void testIsValidEbsVolumeType_whenIo2_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isValidEbsVolumeType("io2"));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveIntLtEqValue(String, int)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveIntLtEqValue(String, int)}
   */
  @Test
  @DisplayName("Test isPositiveIntLtEqValue(String, int); when '42'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveIntLtEqValue(String, int)"})
  void testIsPositiveIntLtEqValue_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isPositiveIntLtEqValue("42", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveIntLtEqValue(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveIntLtEqValue(String, int)}
   */
  @Test
  @DisplayName("Test isPositiveIntLtEqValue(String, int); when empty string; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveIntLtEqValue(String, int)"})
  void testIsPositiveIntLtEqValue_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isPositiveIntLtEqValue("", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveIntLtEqValue(String, int)}.
   * <ul>
   *   <li>When forty-two.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveIntLtEqValue(String, int)}
   */
  @Test
  @DisplayName("Test isPositiveIntLtEqValue(String, int); when forty-two; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveIntLtEqValue(String, int)"})
  void testIsPositiveIntLtEqValue_whenFortyTwo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isPositiveIntLtEqValue("42", 42));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveIntLtEqValue(String, int)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveIntLtEqValue(String, int)}
   */
  @Test
  @DisplayName("Test isPositiveIntLtEqValue(String, int); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveIntLtEqValue(String, int)"})
  void testIsPositiveIntLtEqValue_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isPositiveIntLtEqValue(null, 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isPositiveIntLtEqValue(String, int)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isPositiveIntLtEqValue(String, int)}
   */
  @Test
  @DisplayName("Test isPositiveIntLtEqValue(String, int); when 'String'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isPositiveIntLtEqValue(String, int)"})
  void testIsPositiveIntLtEqValue_whenString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isPositiveIntLtEqValue("String", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNegativeIntLtEqValue(String, int)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNegativeIntLtEqValue(String, int)}
   */
  @Test
  @DisplayName("Test isNonNegativeIntLtEqValue(String, int); when '42'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNegativeIntLtEqValue(String, int)"})
  void testIsNonNegativeIntLtEqValue_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNegativeIntLtEqValue("42", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNegativeIntLtEqValue(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNegativeIntLtEqValue(String, int)}
   */
  @Test
  @DisplayName("Test isNonNegativeIntLtEqValue(String, int); when empty string; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNegativeIntLtEqValue(String, int)"})
  void testIsNonNegativeIntLtEqValue_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNegativeIntLtEqValue("", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNegativeIntLtEqValue(String, int)}.
   * <ul>
   *   <li>When forty-two.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNegativeIntLtEqValue(String, int)}
   */
  @Test
  @DisplayName("Test isNonNegativeIntLtEqValue(String, int); when forty-two; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNegativeIntLtEqValue(String, int)"})
  void testIsNonNegativeIntLtEqValue_whenFortyTwo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isNonNegativeIntLtEqValue("42", 42));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNegativeIntLtEqValue(String, int)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNegativeIntLtEqValue(String, int)}
   */
  @Test
  @DisplayName("Test isNonNegativeIntLtEqValue(String, int); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNegativeIntLtEqValue(String, int)"})
  void testIsNonNegativeIntLtEqValue_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNegativeIntLtEqValue(null, 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isNonNegativeIntLtEqValue(String, int)}.
   * <ul>
   *   <li>When {@code String}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isNonNegativeIntLtEqValue(String, int)}
   */
  @Test
  @DisplayName("Test isNonNegativeIntLtEqValue(String, int); when 'String'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isNonNegativeIntLtEqValue(String, int)"})
  void testIsNonNegativeIntLtEqValue_whenString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isNonNegativeIntLtEqValue("String", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isListWithMaxSize(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isListWithMaxSize(String, int)}
   */
  @Test
  @DisplayName("Test isListWithMaxSize(String, int); when empty string; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isListWithMaxSize(String, int)"})
  void testIsListWithMaxSize_whenEmptyString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isListWithMaxSize("", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isListWithMaxSize(String, int)}.
   * <ul>
   *   <li>When {@code Input}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isListWithMaxSize(String, int)}
   */
  @Test
  @DisplayName("Test isListWithMaxSize(String, int); when 'Input'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isListWithMaxSize(String, int)"})
  void testIsListWithMaxSize_whenInput_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isListWithMaxSize("Input", 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isListWithMaxSize(String, int)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isListWithMaxSize(String, int)}
   */
  @Test
  @DisplayName("Test isListWithMaxSize(String, int); when 'null'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isListWithMaxSize(String, int)"})
  void testIsListWithMaxSize_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SleeperPropertyValueUtils.isListWithMaxSize(null, 3));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#isListWithMaxSize(String, int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#isListWithMaxSize(String, int)}
   */
  @Test
  @DisplayName("Test isListWithMaxSize(String, int); when zero; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValueUtils.isListWithMaxSize(String, int)"})
  void testIsListWithMaxSize_whenZero_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SleeperPropertyValueUtils.isListWithMaxSize("Input", 0));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#describeEnumValuesInLowerCase(Class)}.
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#describeEnumValuesInLowerCase(Class)}
   */
  @Test
  @DisplayName("Test describeEnumValuesInLowerCase(Class)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperPropertyValueUtils.describeEnumValuesInLowerCase(Class)"})
  void testDescribeEnumValuesInLowerCase() {
    // Arrange
    Class<BaselineResizeBehavior> cls = BaselineResizeBehavior.class;

    // Act and Assert
    assertEquals("[constant_ascent, constant_descent, center_offset, other]",
        SleeperPropertyValueUtils.describeEnumValuesInLowerCase(cls));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#describeEnumValues(Class)}.
   * <ul>
   *   <li>Then return {@code [CONSTANT_ASCENT, CONSTANT_DESCENT, CENTER_OFFSET, OTHER]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#describeEnumValues(Class)}
   */
  @Test
  @DisplayName("Test describeEnumValues(Class); then return '[CONSTANT_ASCENT, CONSTANT_DESCENT, CENTER_OFFSET, OTHER]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperPropertyValueUtils.describeEnumValues(Class)"})
  void testDescribeEnumValues_thenReturnConstantAscentConstantDescentCenterOffsetOther() {
    // Arrange
    Class<BaselineResizeBehavior> cls = BaselineResizeBehavior.class;

    // Act and Assert
    assertEquals("[CONSTANT_ASCENT, CONSTANT_DESCENT, CENTER_OFFSET, OTHER]",
        SleeperPropertyValueUtils.describeEnumValues(cls));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#readList(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#readList(String)}
   */
  @Test
  @DisplayName("Test readList(String); when '42'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperPropertyValueUtils.readList(String)"})
  void testReadList_when42_thenReturnSizeIsOne() {
    // Arrange and Act
    List<String> actualReadListResult = SleeperPropertyValueUtils.readList("42");

    // Assert
    assertEquals(1, actualReadListResult.size());
    assertEquals("42", actualReadListResult.get(0));
  }

  /**
   * Test {@link SleeperPropertyValueUtils#readList(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#readList(String)}
   */
  @Test
  @DisplayName("Test readList(String); when empty string; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperPropertyValueUtils.readList(String)"})
  void testReadList_whenEmptyString_thenReturnEmpty() {
    // Arrange and Act
    List<String> actualReadListResult = SleeperPropertyValueUtils.readList("");

    // Assert
    assertTrue(actualReadListResult.isEmpty());
  }

  /**
   * Test {@link SleeperPropertyValueUtils#readList(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValueUtils#readList(String)}
   */
  @Test
  @DisplayName("Test readList(String); when 'null'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperPropertyValueUtils.readList(String)"})
  void testReadList_whenNull_thenReturnEmpty() {
    // Arrange and Act
    List<String> actualReadListResult = SleeperPropertyValueUtils.readList(null);

    // Assert
    assertTrue(actualReadListResult.isEmpty());
  }
}
