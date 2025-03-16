package sleeper.core.key;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class KeyDiffblueTest {
  /**
   * Test {@link Key#create(Object)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Key#create(Object)}
   */
  @Test
  @DisplayName("Test create(Object); when ArrayList(); then return size is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Key Key.create(Object)"})
  void testCreate_whenArrayList_thenReturnSizeIsZero() {
    // Arrange and Act
    Key actualCreateResult = Key.create(new ArrayList<>());

    // Assert
    assertEquals(0, actualCreateResult.size());
    assertTrue(actualCreateResult.getKeys().isEmpty());
    assertTrue(actualCreateResult.isEmpty());
  }

  /**
   * Test {@link Key#create(Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Keys first is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Key#create(Object)}
   */
  @Test
  @DisplayName("Test create(Object); when 'null'; then return Keys first is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Key Key.create(Object)"})
  void testCreate_whenNull_thenReturnKeysFirstIsNull() {
    // Arrange and Act
    Key actualCreateResult = Key.create(null);

    // Assert
    List<Object> keys = actualCreateResult.getKeys();
    assertEquals(1, keys.size());
    assertNull(keys.get(0));
    assertEquals(1, actualCreateResult.size());
    assertFalse(actualCreateResult.isEmpty());
  }

  /**
   * Test {@link Key#create(Object)}.
   * <ul>
   *   <li>When {@code Obj}.</li>
   *   <li>Then return Keys first is {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Key#create(Object)}
   */
  @Test
  @DisplayName("Test create(Object); when 'Obj'; then return Keys first is 'Obj'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Key Key.create(Object)"})
  void testCreate_whenObj_thenReturnKeysFirstIsObj() {
    // Arrange and Act
    Key actualCreateResult = Key.create("Obj");

    // Assert
    List<Object> keys = actualCreateResult.getKeys();
    assertEquals(1, keys.size());
    assertEquals("Obj", keys.get(0));
    assertEquals(1, actualCreateResult.size());
    assertFalse(actualCreateResult.isEmpty());
  }

  /**
   * Test {@link Key#get(int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Key#get(int)}
   */
  @Test
  @DisplayName("Test get(int); when zero; then return 'Obj'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object Key.get(int)"})
  void testGet_whenZero_thenReturnObj() {
    // Arrange, Act and Assert
    assertEquals("Obj", Key.create("Obj").get(0));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Key#toString()}
   *   <li>{@link Key#getKeys()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List Key.getKeys()", "String Key.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Key createResult = Key.create("Obj");

    // Act
    String actualToStringResult = createResult.toString();
    List<Object> actualKeys = createResult.getKeys();

    // Assert
    assertEquals("Key{[Obj]}", actualToStringResult);
    assertEquals(1, actualKeys.size());
    assertEquals("Obj", actualKeys.get(0));
  }

  /**
   * Test {@link Key#size()}.
   * <p>
   * Method under test: {@link Key#size()}
   */
  @Test
  @DisplayName("Test size()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int Key.size()"})
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(1, Key.create("Obj").size());
  }

  /**
   * Test {@link Key#isEmpty()}.
   * <p>
   * Method under test: {@link Key#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Key.isEmpty()"})
  void testIsEmpty() {
    // Arrange, Act and Assert
    assertFalse(Key.create("Obj").isEmpty());
  }

  /**
   * Test {@link Key#equals(Object)}, and {@link Key#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Key#equals(Object)}
   *   <li>{@link Key#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Key.equals(Object)", "int Key.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Key createResult = Key.create("Obj");
    Key createResult2 = Key.create("Obj");

    // Act and Assert
    assertEquals(createResult, createResult2);
    int expectedHashCodeResult = createResult.hashCode();
    assertEquals(expectedHashCodeResult, createResult2.hashCode());
  }

  /**
   * Test {@link Key#equals(Object)}, and {@link Key#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Key#equals(Object)}
   *   <li>{@link Key#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Key.equals(Object)", "int Key.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Key createResult = Key.create("Obj");

    // Act and Assert
    assertEquals(createResult, createResult);
    int expectedHashCodeResult = createResult.hashCode();
    assertEquals(expectedHashCodeResult, createResult.hashCode());
  }

  /**
   * Test {@link Key#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Key#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Key.equals(Object)", "int Key.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Key createResult = Key.create(1);

    // Act and Assert
    assertNotEquals(createResult, Key.create("Obj"));
  }

  /**
   * Test {@link Key#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Key#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Key.equals(Object)", "int Key.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Key createResult = Key.create(Key.create("Obj"));

    // Act and Assert
    assertNotEquals(createResult, Key.create("Obj"));
  }

  /**
   * Test {@link Key#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Key#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Key.equals(Object)", "int Key.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(Key.create("Obj"), null);
  }

  /**
   * Test {@link Key#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Key#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Key.equals(Object)", "int Key.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(Key.create("Obj"), "Different type to Key");
  }
}
