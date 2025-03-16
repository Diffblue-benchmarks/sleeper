package sleeper.clients.admin.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.SleeperPropertyIndex;

class PropertyDiffDiffblueTest {
  /**
   * Test {@link PropertyDiff#PropertyDiff(String, String, String)}.
   * <p>
   * Method under test: {@link PropertyDiff#PropertyDiff(String, String, String)}
   */
  @Test
  @DisplayName("Test new PropertyDiff(String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PropertyDiff.<init>(String, String, String)"})
  void testNewPropertyDiff() {
    // Arrange and Act
    PropertyDiff actualPropertyDiff = new PropertyDiff("Property Name", "42", "42");

    // Assert
    assertEquals("42", actualPropertyDiff.getNewValue());
    assertEquals("42", actualPropertyDiff.getOldValue());
    assertEquals("Property Name", actualPropertyDiff.getPropertyName());
  }

  /**
   * Test {@link PropertyDiff#forProperty(String, Map, Map)}.
   * <p>
   * Method under test: {@link PropertyDiff#forProperty(String, Map, Map)}
   */
  @Test
  @DisplayName("Test forProperty(String, Map, Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional PropertyDiff.forProperty(String, Map, Map)"})
  void testForProperty() {
    // Arrange
    HashMap<String, String> beforeMap = new HashMap<>();

    // Act
    Optional<PropertyDiff> actualForPropertyResult = PropertyDiff.forProperty("Property Name", beforeMap,
        new HashMap<>());

    // Assert
    assertFalse(actualForPropertyResult.isPresent());
  }

  /**
   * Test {@link PropertyDiff#andThen(PropertyDiff)}.
   * <ul>
   *   <li>Given newValue {@code Property} and {@code 42}.</li>
   *   <li>Then return Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyDiff#andThen(PropertyDiff)}
   */
  @Test
  @DisplayName("Test andThen(PropertyDiff); given newValue 'Property' and '42'; then return Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional PropertyDiff.andThen(PropertyDiff)"})
  void testAndThen_givenNewValuePropertyAnd42_thenReturnPresent() {
    // Arrange
    PropertyDiff newValueResult = PropertiesDiffTestHelper.newValue("Property", "42");

    // Act
    Optional<PropertyDiff> actualAndThenResult = newValueResult
        .andThen(PropertiesDiffTestHelper.newValue("Property", "42"));

    // Assert
    assertTrue(actualAndThenResult.isPresent());
    assertEquals(newValueResult, actualAndThenResult.get());
  }

  /**
   * Test {@link PropertyDiff#andThen(PropertyDiff)}.
   * <ul>
   *   <li>Given valueDeleted {@code propertyName must not be null} and {@code 42}.</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyDiff#andThen(PropertyDiff)}
   */
  @Test
  @DisplayName("Test andThen(PropertyDiff); given valueDeleted 'propertyName must not be null' and '42'; then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional PropertyDiff.andThen(PropertyDiff)"})
  void testAndThen_givenValueDeletedPropertyNameMustNotBeNullAnd42_thenReturnNotPresent() {
    // Arrange
    PropertyDiff valueDeletedResult = PropertiesDiffTestHelper.valueDeleted("propertyName must not be null", "42");

    // Act and Assert
    assertFalse(valueDeletedResult.andThen(PropertiesDiffTestHelper.newValue("Property", "42")).isPresent());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PropertyDiff#toString()}
   *   <li>{@link PropertyDiff#getNewValue()}
   *   <li>{@link PropertyDiff#getOldValue()}
   *   <li>{@link PropertyDiff#getPropertyName()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PropertyDiff.getNewValue()", "String PropertyDiff.getOldValue()",
      "String PropertyDiff.getPropertyName()", "String PropertyDiff.toString()"})
  void testGettersAndSetters() {
    // Arrange
    PropertyDiff propertyDiff = new PropertyDiff("Property Name", "42", "42");

    // Act
    String actualToStringResult = propertyDiff.toString();
    String actualNewValue = propertyDiff.getNewValue();
    String actualOldValue = propertyDiff.getOldValue();

    // Assert
    assertEquals("42", actualNewValue);
    assertEquals("42", actualOldValue);
    assertEquals("Property Name", propertyDiff.getPropertyName());
    assertEquals("PropertyDiff{propertyName='Property Name', oldValue='42', newValue='42'}", actualToStringResult);
  }

  /**
   * Test {@link PropertyDiff#getProperty(SleeperPropertyIndex)}.
   * <ul>
   *   <li>When {@link SleeperPropertyIndex} (default constructor).</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyDiff#getProperty(SleeperPropertyIndex)}
   */
  @Test
  @DisplayName("Test getProperty(SleeperPropertyIndex); when SleeperPropertyIndex (default constructor); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional PropertyDiff.getProperty(SleeperPropertyIndex)"})
  void testGetProperty_whenSleeperPropertyIndex_thenReturnNotPresent() {
    // Arrange
    PropertyDiff newValueResult = PropertiesDiffTestHelper.newValue("Property", "42");

    // Act and Assert
    assertFalse(newValueResult.getProperty(new SleeperPropertyIndex<>()).isPresent());
  }

  /**
   * Test {@link PropertyDiff#equals(Object)}, and {@link PropertyDiff#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PropertyDiff#equals(Object)}
   *   <li>{@link PropertyDiff#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyDiff.equals(Object)", "int PropertyDiff.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    PropertyDiff newValueResult = PropertiesDiffTestHelper.newValue("Property", "42");
    PropertyDiff newValueResult2 = PropertiesDiffTestHelper.newValue("Property", "42");

    // Act and Assert
    assertEquals(newValueResult, newValueResult2);
    int expectedHashCodeResult = newValueResult.hashCode();
    assertEquals(expectedHashCodeResult, newValueResult2.hashCode());
  }

  /**
   * Test {@link PropertyDiff#equals(Object)}, and {@link PropertyDiff#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PropertyDiff#equals(Object)}
   *   <li>{@link PropertyDiff#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyDiff.equals(Object)", "int PropertyDiff.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    PropertyDiff newValueResult = PropertiesDiffTestHelper.newValue("Property", "42");

    // Act and Assert
    assertEquals(newValueResult, newValueResult);
    int expectedHashCodeResult = newValueResult.hashCode();
    assertEquals(expectedHashCodeResult, newValueResult.hashCode());
  }

  /**
   * Test {@link PropertyDiff#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyDiff.equals(Object)", "int PropertyDiff.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    PropertyDiff valueDeletedResult = PropertiesDiffTestHelper.valueDeleted("Property", "42");

    // Act and Assert
    assertNotEquals(valueDeletedResult, PropertiesDiffTestHelper.newValue("Property", "42"));
  }

  /**
   * Test {@link PropertyDiff#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyDiff.equals(Object)", "int PropertyDiff.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    PropertyDiff newValueResult = PropertiesDiffTestHelper.newValue("Property", "Value");

    // Act and Assert
    assertNotEquals(newValueResult, PropertiesDiffTestHelper.newValue("Property", "42"));
  }

  /**
   * Test {@link PropertyDiff#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyDiff.equals(Object)", "int PropertyDiff.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    PropertyDiff newValueResult = PropertiesDiffTestHelper.newValue("42", "Value");

    // Act and Assert
    assertNotEquals(newValueResult, PropertiesDiffTestHelper.newValue("Property", "42"));
  }

  /**
   * Test {@link PropertyDiff#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyDiff.equals(Object)", "int PropertyDiff.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(PropertiesDiffTestHelper.newValue("Property", "42"), null);
  }

  /**
   * Test {@link PropertyDiff#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyDiff.equals(Object)", "int PropertyDiff.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(PropertiesDiffTestHelper.newValue("Property", "42"), "Different type to PropertyDiff");
  }
}
