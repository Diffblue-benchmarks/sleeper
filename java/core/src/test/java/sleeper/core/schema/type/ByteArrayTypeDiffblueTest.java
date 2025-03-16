package sleeper.core.schema.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ByteArrayTypeDiffblueTest {
  /**
   * Test {@link ByteArrayType#equals(Object)}, and {@link ByteArrayType#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ByteArrayType#equals(Object)}
   *   <li>{@link ByteArrayType#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ByteArrayType.equals(Object)", "int ByteArrayType.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ByteArrayType byteArrayType = new ByteArrayType();
    ByteArrayType byteArrayType2 = new ByteArrayType();

    // Act and Assert
    assertEquals(byteArrayType, byteArrayType2);
    int expectedHashCodeResult = byteArrayType.hashCode();
    assertEquals(expectedHashCodeResult, byteArrayType2.hashCode());
  }

  /**
   * Test {@link ByteArrayType#equals(Object)}, and {@link ByteArrayType#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ByteArrayType#equals(Object)}
   *   <li>{@link ByteArrayType#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ByteArrayType.equals(Object)", "int ByteArrayType.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ByteArrayType byteArrayType = new ByteArrayType();

    // Act and Assert
    assertEquals(byteArrayType, byteArrayType);
    int expectedHashCodeResult = byteArrayType.hashCode();
    assertEquals(expectedHashCodeResult, byteArrayType.hashCode());
  }

  /**
   * Test {@link ByteArrayType#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ByteArrayType#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ByteArrayType.equals(Object)", "int ByteArrayType.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ByteArrayType(), 1);
  }

  /**
   * Test {@link ByteArrayType#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ByteArrayType#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ByteArrayType.equals(Object)", "int ByteArrayType.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ByteArrayType(), null);
  }

  /**
   * Test {@link ByteArrayType#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ByteArrayType#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ByteArrayType.equals(Object)", "int ByteArrayType.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ByteArrayType(), "Different type to ByteArrayType");
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link ByteArrayType}
   *   <li>{@link ByteArrayType#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ByteArrayType.<init>()", "java.lang.String ByteArrayType.toString()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("ByteArrayType{}", (new ByteArrayType()).toString());
  }
}
