package sleeper.core.schema.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MapTypeDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MapType#MapType(PrimitiveType, PrimitiveType)}
   *   <li>{@link MapType#toString()}
   *   <li>{@link MapType#getKeyType()}
   *   <li>{@link MapType#getValueType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MapType.<init>(PrimitiveType, PrimitiveType)", "PrimitiveType MapType.getKeyType()",
      "PrimitiveType MapType.getValueType()", "java.lang.String MapType.toString()"})
  void testGettersAndSetters() {
    // Arrange
    PrimitiveType keyType = new PrimitiveType();
    PrimitiveType valueType = new PrimitiveType();

    // Act
    MapType actualMapType = new MapType(keyType, valueType);
    actualMapType.toString();
    PrimitiveType actualKeyType = actualMapType.getKeyType();

    // Assert
    assertSame(keyType, actualKeyType);
    assertSame(valueType, actualMapType.getValueType());
  }

  /**
   * Test {@link MapType#equals(Object)}, and {@link MapType#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MapType#equals(Object)}
   *   <li>{@link MapType#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MapType.equals(Object)", "int MapType.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    PrimitiveType keyType = new PrimitiveType();
    MapType mapType = new MapType(keyType, new PrimitiveType());

    // Act and Assert
    assertEquals(mapType, mapType);
    int expectedHashCodeResult = mapType.hashCode();
    assertEquals(expectedHashCodeResult, mapType.hashCode());
  }

  /**
   * Test {@link MapType#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapType#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MapType.equals(Object)", "int MapType.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    PrimitiveType keyType = new PrimitiveType();
    MapType mapType = new MapType(keyType, new PrimitiveType());
    PrimitiveType keyType2 = new PrimitiveType();

    // Act and Assert
    assertNotEquals(mapType, new MapType(keyType2, new PrimitiveType()));
  }

  /**
   * Test {@link MapType#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapType#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MapType.equals(Object)", "int MapType.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    PrimitiveType keyType = new PrimitiveType();

    // Act and Assert
    assertNotEquals(new MapType(keyType, new PrimitiveType()), null);
  }

  /**
   * Test {@link MapType#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link MapType#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean MapType.equals(Object)", "int MapType.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    PrimitiveType keyType = new PrimitiveType();

    // Act and Assert
    assertNotEquals(new MapType(keyType, new PrimitiveType()), "Different type to MapType");
  }
}
