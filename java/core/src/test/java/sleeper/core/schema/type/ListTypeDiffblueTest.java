package sleeper.core.schema.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ListTypeDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ListType#ListType(PrimitiveType)}
   *   <li>{@link ListType#toString()}
   *   <li>{@link ListType#getElementType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ListType.<init>(PrimitiveType)", "PrimitiveType ListType.getElementType()",
      "java.lang.String ListType.toString()"})
  void testGettersAndSetters() {
    // Arrange
    PrimitiveType elementType = new PrimitiveType();

    // Act
    ListType actualListType = new ListType(elementType);
    actualListType.toString();

    // Assert
    assertSame(elementType, actualListType.getElementType());
  }

  /**
   * Test {@link ListType#equals(Object)}, and {@link ListType#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ListType#equals(Object)}
   *   <li>{@link ListType#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ListType.equals(Object)", "int ListType.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ListType listType = new ListType(new PrimitiveType());

    // Act and Assert
    assertEquals(listType, listType);
    int expectedHashCodeResult = listType.hashCode();
    assertEquals(expectedHashCodeResult, listType.hashCode());
  }

  /**
   * Test {@link ListType#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListType#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ListType.equals(Object)", "int ListType.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ListType listType = new ListType(new PrimitiveType());

    // Act and Assert
    assertNotEquals(listType, new ListType(new PrimitiveType()));
  }

  /**
   * Test {@link ListType#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListType#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ListType.equals(Object)", "int ListType.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ListType(new PrimitiveType()), null);
  }

  /**
   * Test {@link ListType#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ListType#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ListType.equals(Object)", "int ListType.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ListType(new PrimitiveType()), "Different type to ListType");
  }
}
