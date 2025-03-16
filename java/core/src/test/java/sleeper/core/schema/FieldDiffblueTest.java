package sleeper.core.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.Type;

class FieldDiffblueTest {
  /**
   * Test {@link Field#Field(String, Type)}.
   * <ul>
   *   <li>When {@link ByteArrayType} (default constructor).</li>
   *   <li>Then Type return {@link ByteArrayType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Field#Field(String, Type)}
   */
  @Test
  @DisplayName("Test new Field(String, Type); when ByteArrayType (default constructor); then Type return ByteArrayType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Field.<init>(String, Type)"})
  void testNewField_whenByteArrayType_thenTypeReturnByteArrayType() {
    // Arrange
    ByteArrayType type = new ByteArrayType();

    // Act
    Field actualField = new Field("Name", type);

    // Assert
    Type type2 = actualField.getType();
    assertTrue(type2 instanceof ByteArrayType);
    assertEquals("Name", actualField.getName());
    assertSame(type, type2);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Field#toString()}
   *   <li>{@link Field#getName()}
   *   <li>{@link Field#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Field.getName()", "Type Field.getType()", "String Field.toString()"})
  void testGettersAndSetters() {
    // Arrange
    ByteArrayType type = new ByteArrayType();
    Field field = new Field("Name", type);

    // Act
    String actualToStringResult = field.toString();
    String actualName = field.getName();
    Type actualType = field.getType();

    // Assert
    assertTrue(actualType instanceof ByteArrayType);
    assertEquals("Field{name=Name, type=ByteArrayType{}}", actualToStringResult);
    assertEquals("Name", actualName);
    assertSame(type, actualType);
  }

  /**
   * Test {@link Field#equals(Object)}, and {@link Field#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Field#equals(Object)}
   *   <li>{@link Field#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Field.equals(Object)", "int Field.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Field field = new Field("Name", new ByteArrayType());
    Field field2 = new Field("Name", new ByteArrayType());

    // Act and Assert
    assertEquals(field, field2);
    int expectedHashCodeResult = field.hashCode();
    assertEquals(expectedHashCodeResult, field2.hashCode());
  }

  /**
   * Test {@link Field#equals(Object)}, and {@link Field#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Field#equals(Object)}
   *   <li>{@link Field#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Field.equals(Object)", "int Field.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Field field = new Field("Name", new ByteArrayType());

    // Act and Assert
    assertEquals(field, field);
    int expectedHashCodeResult = field.hashCode();
    assertEquals(expectedHashCodeResult, field.hashCode());
  }

  /**
   * Test {@link Field#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Field#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Field.equals(Object)", "int Field.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Field field = new Field("java.lang.String", new ByteArrayType());

    // Act and Assert
    assertNotEquals(field, new Field("Name", new ByteArrayType()));
  }

  /**
   * Test {@link Field#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Field#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Field.equals(Object)", "int Field.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Field field = new Field("Name", mock(ByteArrayType.class));

    // Act and Assert
    assertNotEquals(field, new Field("Name", new ByteArrayType()));
  }

  /**
   * Test {@link Field#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Field#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Field.equals(Object)", "int Field.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Field("Name", new ByteArrayType()), null);
  }

  /**
   * Test {@link Field#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Field#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Field.equals(Object)", "int Field.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Field("Name", new ByteArrayType()), "Different type to Field");
  }
}
