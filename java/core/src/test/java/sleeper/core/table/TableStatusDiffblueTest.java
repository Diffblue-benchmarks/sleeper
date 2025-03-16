package sleeper.core.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TableStatusDiffblueTest {
  /**
   * Test {@link TableStatus#uniqueIdAndName(String, String, boolean)}.
   * <p>
   * Method under test: {@link TableStatus#uniqueIdAndName(String, String, boolean)}
   */
  @Test
  @DisplayName("Test uniqueIdAndName(String, String, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableStatus TableStatus.uniqueIdAndName(String, String, boolean)"})
  void testUniqueIdAndName() {
    // Arrange and Act
    TableStatus actualUniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Assert
    assertEquals("42", actualUniqueIdAndNameResult.getTableUniqueId());
    assertEquals("Table Name", actualUniqueIdAndNameResult.getTableName());
    assertTrue(actualUniqueIdAndNameResult.isOnline());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableStatus#getTableName()}
   *   <li>{@link TableStatus#getTableUniqueId()}
   *   <li>{@link TableStatus#isOnline()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStatus.getTableName()", "String TableStatus.getTableUniqueId()",
      "boolean TableStatus.isOnline()"})
  void testGettersAndSetters() {
    // Arrange
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act
    String actualTableName = uniqueIdAndNameResult.getTableName();
    String actualTableUniqueId = uniqueIdAndNameResult.getTableUniqueId();

    // Assert
    assertEquals("42", actualTableUniqueId);
    assertEquals("Table Name", actualTableName);
    assertTrue(uniqueIdAndNameResult.isOnline());
  }

  /**
   * Test {@link TableStatus#takeOffline()}.
   * <p>
   * Method under test: {@link TableStatus#takeOffline()}
   */
  @Test
  @DisplayName("Test takeOffline()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableStatus TableStatus.takeOffline()"})
  void testTakeOffline() {
    // Arrange and Act
    TableStatus actualTakeOfflineResult = TableStatus.uniqueIdAndName("42", "Table Name", true).takeOffline();

    // Assert
    assertEquals("42", actualTakeOfflineResult.getTableUniqueId());
    assertEquals("Table Name", actualTakeOfflineResult.getTableName());
    assertFalse(actualTakeOfflineResult.isOnline());
  }

  /**
   * Test {@link TableStatus#putOnline()}.
   * <p>
   * Method under test: {@link TableStatus#putOnline()}
   */
  @Test
  @DisplayName("Test putOnline()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableStatus TableStatus.putOnline()"})
  void testPutOnline() {
    // Arrange
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act and Assert
    assertEquals(uniqueIdAndNameResult, uniqueIdAndNameResult.putOnline());
  }

  /**
   * Test {@link TableStatus#equals(Object)}, and {@link TableStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableStatus#equals(Object)}
   *   <li>{@link TableStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableStatus.equals(Object)", "int TableStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);
    TableStatus uniqueIdAndNameResult2 = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act and Assert
    assertEquals(uniqueIdAndNameResult, uniqueIdAndNameResult2);
    int expectedHashCodeResult = uniqueIdAndNameResult.hashCode();
    assertEquals(expectedHashCodeResult, uniqueIdAndNameResult2.hashCode());
  }

  /**
   * Test {@link TableStatus#equals(Object)}, and {@link TableStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableStatus#equals(Object)}
   *   <li>{@link TableStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableStatus.equals(Object)", "int TableStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", true);

    // Act and Assert
    assertEquals(uniqueIdAndNameResult, uniqueIdAndNameResult);
    int expectedHashCodeResult = uniqueIdAndNameResult.hashCode();
    assertEquals(expectedHashCodeResult, uniqueIdAndNameResult.hashCode());
  }

  /**
   * Test {@link TableStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableStatus.equals(Object)", "int TableStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("Table Unique Id", "Table Name", true);

    // Act and Assert
    assertNotEquals(uniqueIdAndNameResult, TableStatus.uniqueIdAndName("42", "Table Name", true));
  }

  /**
   * Test {@link TableStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableStatus.equals(Object)", "int TableStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", null, true);

    // Act and Assert
    assertNotEquals(uniqueIdAndNameResult, TableStatus.uniqueIdAndName("42", "Table Name", true));
  }

  /**
   * Test {@link TableStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableStatus.equals(Object)", "int TableStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    TableStatus uniqueIdAndNameResult = TableStatus.uniqueIdAndName("42", "Table Name", false);

    // Act and Assert
    assertNotEquals(uniqueIdAndNameResult, TableStatus.uniqueIdAndName("42", "Table Name", true));
  }

  /**
   * Test {@link TableStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableStatus.equals(Object)", "int TableStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(TableStatus.uniqueIdAndName("42", "Table Name", true), null);
  }

  /**
   * Test {@link TableStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TableStatus.equals(Object)", "int TableStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(TableStatus.uniqueIdAndName("42", "Table Name", true), "Different type to TableStatus");
  }

  /**
   * Test {@link TableStatus#toString()}.
   * <ul>
   *   <li>Then return {@code Table Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStatus#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return 'Table Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStatus.toString()"})
  void testToString_thenReturnTableName() {
    // Arrange, Act and Assert
    assertEquals("Table Name", TableStatus.uniqueIdAndName(null, "Table Name", true).toString());
  }

  /**
   * Test {@link TableStatus#toString()}.
   * <ul>
   *   <li>Then return {@code Table Name (42)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStatus#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return 'Table Name (42)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStatus.toString()"})
  void testToString_thenReturnTableName42() {
    // Arrange, Act and Assert
    assertEquals("Table Name (42)", TableStatus.uniqueIdAndName("42", "Table Name", true).toString());
  }

  /**
   * Test {@link TableStatus#toString()}.
   * <ul>
   *   <li>Then return {@code Table Name (42) [offline]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStatus#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return 'Table Name (42) [offline]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStatus.toString()"})
  void testToString_thenReturnTableName42Offline() {
    // Arrange, Act and Assert
    assertEquals("Table Name (42) [offline]", TableStatus.uniqueIdAndName("42", "Table Name", false).toString());
  }
}
