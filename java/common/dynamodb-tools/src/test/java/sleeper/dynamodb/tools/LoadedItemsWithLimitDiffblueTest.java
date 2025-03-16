package sleeper.dynamodb.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LoadedItemsWithLimitDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LoadedItemsWithLimit#LoadedItemsWithLimit(List, boolean)}
   *   <li>{@link LoadedItemsWithLimit#toString()}
   *   <li>{@link LoadedItemsWithLimit#getItems()}
   *   <li>{@link LoadedItemsWithLimit#isMoreItems()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void LoadedItemsWithLimit.<init>(List, boolean)", "List LoadedItemsWithLimit.getItems()",
      "boolean LoadedItemsWithLimit.isMoreItems()", "String LoadedItemsWithLimit.toString()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<Map<String, AttributeValue>> items = new ArrayList<>();

    // Act
    LoadedItemsWithLimit actualLoadedItemsWithLimit = new LoadedItemsWithLimit(items, true);
    String actualToStringResult = actualLoadedItemsWithLimit.toString();
    List<Map<String, AttributeValue>> actualItems = actualLoadedItemsWithLimit.getItems();
    boolean actualIsMoreItemsResult = actualLoadedItemsWithLimit.isMoreItems();

    // Assert
    assertEquals("LoadedItemsWithLimit{items=[], moreItems=true}", actualToStringResult);
    assertTrue(actualItems.isEmpty());
    assertTrue(actualIsMoreItemsResult);
    assertSame(items, actualItems);
  }

  /**
   * Test {@link LoadedItemsWithLimit#equals(Object)}, and {@link LoadedItemsWithLimit#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LoadedItemsWithLimit#equals(Object)}
   *   <li>{@link LoadedItemsWithLimit#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LoadedItemsWithLimit.equals(Object)", "int LoadedItemsWithLimit.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    LoadedItemsWithLimit loadedItemsWithLimit = new LoadedItemsWithLimit(new ArrayList<>(), true);
    LoadedItemsWithLimit loadedItemsWithLimit2 = new LoadedItemsWithLimit(new ArrayList<>(), true);

    // Act and Assert
    assertEquals(loadedItemsWithLimit, loadedItemsWithLimit2);
    int expectedHashCodeResult = loadedItemsWithLimit.hashCode();
    assertEquals(expectedHashCodeResult, loadedItemsWithLimit2.hashCode());
  }

  /**
   * Test {@link LoadedItemsWithLimit#equals(Object)}, and {@link LoadedItemsWithLimit#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LoadedItemsWithLimit#equals(Object)}
   *   <li>{@link LoadedItemsWithLimit#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LoadedItemsWithLimit.equals(Object)", "int LoadedItemsWithLimit.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    LoadedItemsWithLimit loadedItemsWithLimit = new LoadedItemsWithLimit(new ArrayList<>(), true);

    // Act and Assert
    assertEquals(loadedItemsWithLimit, loadedItemsWithLimit);
    int expectedHashCodeResult = loadedItemsWithLimit.hashCode();
    assertEquals(expectedHashCodeResult, loadedItemsWithLimit.hashCode());
  }

  /**
   * Test {@link LoadedItemsWithLimit#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadedItemsWithLimit#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LoadedItemsWithLimit.equals(Object)", "int LoadedItemsWithLimit.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<Map<String, AttributeValue>> items = new ArrayList<>();
    items.add(new HashMap<>());
    LoadedItemsWithLimit loadedItemsWithLimit = new LoadedItemsWithLimit(items, true);

    // Act and Assert
    assertNotEquals(loadedItemsWithLimit, new LoadedItemsWithLimit(new ArrayList<>(), true));
  }

  /**
   * Test {@link LoadedItemsWithLimit#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadedItemsWithLimit#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LoadedItemsWithLimit.equals(Object)", "int LoadedItemsWithLimit.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    LoadedItemsWithLimit loadedItemsWithLimit = new LoadedItemsWithLimit(new ArrayList<>(), false);

    // Act and Assert
    assertNotEquals(loadedItemsWithLimit, new LoadedItemsWithLimit(new ArrayList<>(), true));
  }

  /**
   * Test {@link LoadedItemsWithLimit#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadedItemsWithLimit#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LoadedItemsWithLimit.equals(Object)", "int LoadedItemsWithLimit.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new LoadedItemsWithLimit(new ArrayList<>(), true), null);
  }

  /**
   * Test {@link LoadedItemsWithLimit#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadedItemsWithLimit#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LoadedItemsWithLimit.equals(Object)", "int LoadedItemsWithLimit.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new LoadedItemsWithLimit(new ArrayList<>(), true), "Different type to LoadedItemsWithLimit");
  }
}
