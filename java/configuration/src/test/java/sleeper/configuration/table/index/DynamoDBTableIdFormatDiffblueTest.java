package sleeper.configuration.table.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.table.TableStatus;

class DynamoDBTableIdFormatDiffblueTest {
  /**
   * Test {@link DynamoDBTableIdFormat#getItem(TableStatus)}.
   * <ul>
   *   <li>Then return {@link DynamoDBTableIndex#TABLE_ONLINE_FIELD} S is {@link Boolean#FALSE} toString.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIdFormat#getItem(TableStatus)}
   */
  @Test
  @DisplayName("Test getItem(TableStatus); then return TABLE_ONLINE_FIELD S is FALSE toString")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBTableIdFormat.getItem(TableStatus)"})
  void testGetItem_thenReturnTable_online_fieldSIsFalseToString() {
    // Arrange and Act
    Map<String, AttributeValue> actualItem = DynamoDBTableIdFormat
        .getItem(TableStatus.uniqueIdAndName("42", "Table Name", false));

    // Assert
    assertEquals(3, actualItem.size());
    AttributeValue getResult = actualItem.get(DynamoDBTableIndex.TABLE_ID_FIELD);
    assertEquals("42", getResult.getS());
    AttributeValue getResult2 = actualItem.get(DynamoDBTableIndex.TABLE_NAME_FIELD);
    assertEquals("Table Name", getResult2.getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult2.getBOOL());
    AttributeValue getResult3 = actualItem.get(DynamoDBTableIndex.TABLE_ONLINE_FIELD);
    assertNull(getResult3.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult2.getNULL());
    assertNull(getResult3.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult2.isBOOL());
    assertNull(getResult3.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult2.isNULL());
    assertNull(getResult3.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult2.getN());
    assertNull(getResult3.getN());
    assertNull(getResult.getB());
    assertNull(getResult2.getB());
    assertNull(getResult3.getB());
    assertNull(getResult.getL());
    assertNull(getResult2.getL());
    assertNull(getResult3.getL());
    assertNull(getResult.getNS());
    assertNull(getResult2.getNS());
    assertNull(getResult3.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult2.getSS());
    assertNull(getResult3.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult2.getBS());
    assertNull(getResult3.getBS());
    assertNull(getResult.getM());
    assertNull(getResult2.getM());
    assertNull(getResult3.getM());
    String expectedS = Boolean.FALSE.toString();
    assertEquals(expectedS, getResult3.getS());
  }

  /**
   * Test {@link DynamoDBTableIdFormat#getItem(TableStatus)}.
   * <ul>
   *   <li>Then return {@link DynamoDBTableIndex#TABLE_ONLINE_FIELD} S is {@link Boolean#TRUE} toString.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIdFormat#getItem(TableStatus)}
   */
  @Test
  @DisplayName("Test getItem(TableStatus); then return TABLE_ONLINE_FIELD S is TRUE toString")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBTableIdFormat.getItem(TableStatus)"})
  void testGetItem_thenReturnTable_online_fieldSIsTrueToString() {
    // Arrange and Act
    Map<String, AttributeValue> actualItem = DynamoDBTableIdFormat
        .getItem(TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    assertEquals(3, actualItem.size());
    AttributeValue getResult = actualItem.get(DynamoDBTableIndex.TABLE_ID_FIELD);
    assertEquals("42", getResult.getS());
    AttributeValue getResult2 = actualItem.get(DynamoDBTableIndex.TABLE_NAME_FIELD);
    assertEquals("Table Name", getResult2.getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult2.getBOOL());
    AttributeValue getResult3 = actualItem.get(DynamoDBTableIndex.TABLE_ONLINE_FIELD);
    assertNull(getResult3.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult2.getNULL());
    assertNull(getResult3.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult2.isBOOL());
    assertNull(getResult3.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult2.isNULL());
    assertNull(getResult3.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult2.getN());
    assertNull(getResult3.getN());
    assertNull(getResult.getB());
    assertNull(getResult2.getB());
    assertNull(getResult3.getB());
    assertNull(getResult.getL());
    assertNull(getResult2.getL());
    assertNull(getResult3.getL());
    assertNull(getResult.getNS());
    assertNull(getResult2.getNS());
    assertNull(getResult3.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult2.getSS());
    assertNull(getResult3.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult2.getBS());
    assertNull(getResult3.getBS());
    assertNull(getResult.getM());
    assertNull(getResult2.getM());
    assertNull(getResult3.getM());
    String expectedS = Boolean.TRUE.toString();
    assertEquals(expectedS, getResult3.getS());
  }

  /**
   * Test {@link DynamoDBTableIdFormat#getIdKey(TableStatus)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIdFormat#getIdKey(TableStatus)}
   */
  @Test
  @DisplayName("Test getIdKey(TableStatus); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBTableIdFormat.getIdKey(TableStatus)"})
  void testGetIdKey_thenReturnSizeIsOne() {
    // Arrange and Act
    Map<String, AttributeValue> actualIdKey = DynamoDBTableIdFormat
        .getIdKey(TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    assertEquals(1, actualIdKey.size());
    AttributeValue getResult = actualIdKey.get(DynamoDBTableIndex.TABLE_ID_FIELD);
    assertEquals("42", getResult.getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
  }

  /**
   * Test {@link DynamoDBTableIdFormat#getNameKey(TableStatus)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIdFormat#getNameKey(TableStatus)}
   */
  @Test
  @DisplayName("Test getNameKey(TableStatus); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBTableIdFormat.getNameKey(TableStatus)"})
  void testGetNameKey_thenReturnSizeIsOne() {
    // Arrange and Act
    Map<String, AttributeValue> actualNameKey = DynamoDBTableIdFormat
        .getNameKey(TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    assertEquals(1, actualNameKey.size());
    AttributeValue getResult = actualNameKey.get(DynamoDBTableIndex.TABLE_NAME_FIELD);
    assertEquals("Table Name", getResult.getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
  }

  /**
   * Test {@link DynamoDBTableIdFormat#getOnlineKey(TableStatus)}.
   * <ul>
   *   <li>Then return {@link DynamoDBTableIndex#TABLE_ONLINE_FIELD} S is {@link Boolean#FALSE} toString.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIdFormat#getOnlineKey(TableStatus)}
   */
  @Test
  @DisplayName("Test getOnlineKey(TableStatus); then return TABLE_ONLINE_FIELD S is FALSE toString")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBTableIdFormat.getOnlineKey(TableStatus)"})
  void testGetOnlineKey_thenReturnTable_online_fieldSIsFalseToString() {
    // Arrange and Act
    Map<String, AttributeValue> actualOnlineKey = DynamoDBTableIdFormat
        .getOnlineKey(TableStatus.uniqueIdAndName("42", "Table Name", false));

    // Assert
    assertEquals(2, actualOnlineKey.size());
    AttributeValue getResult = actualOnlineKey.get(DynamoDBTableIndex.TABLE_NAME_FIELD);
    assertEquals("Table Name", getResult.getS());
    assertNull(getResult.getBOOL());
    AttributeValue getResult2 = actualOnlineKey.get(DynamoDBTableIndex.TABLE_ONLINE_FIELD);
    assertNull(getResult2.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult2.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult2.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult2.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult2.getN());
    assertNull(getResult.getB());
    assertNull(getResult2.getB());
    assertNull(getResult.getL());
    assertNull(getResult2.getL());
    assertNull(getResult.getNS());
    assertNull(getResult2.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult2.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult2.getBS());
    assertNull(getResult.getM());
    assertNull(getResult2.getM());
    String expectedS = Boolean.FALSE.toString();
    assertEquals(expectedS, getResult2.getS());
  }

  /**
   * Test {@link DynamoDBTableIdFormat#getOnlineKey(TableStatus)}.
   * <ul>
   *   <li>Then return {@link DynamoDBTableIndex#TABLE_ONLINE_FIELD} S is {@link Boolean#TRUE} toString.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIdFormat#getOnlineKey(TableStatus)}
   */
  @Test
  @DisplayName("Test getOnlineKey(TableStatus); then return TABLE_ONLINE_FIELD S is TRUE toString")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBTableIdFormat.getOnlineKey(TableStatus)"})
  void testGetOnlineKey_thenReturnTable_online_fieldSIsTrueToString() {
    // Arrange and Act
    Map<String, AttributeValue> actualOnlineKey = DynamoDBTableIdFormat
        .getOnlineKey(TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    assertEquals(2, actualOnlineKey.size());
    AttributeValue getResult = actualOnlineKey.get(DynamoDBTableIndex.TABLE_NAME_FIELD);
    assertEquals("Table Name", getResult.getS());
    assertNull(getResult.getBOOL());
    AttributeValue getResult2 = actualOnlineKey.get(DynamoDBTableIndex.TABLE_ONLINE_FIELD);
    assertNull(getResult2.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult2.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult2.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult2.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult2.getN());
    assertNull(getResult.getB());
    assertNull(getResult2.getB());
    assertNull(getResult.getL());
    assertNull(getResult2.getL());
    assertNull(getResult.getNS());
    assertNull(getResult2.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult2.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult2.getBS());
    assertNull(getResult.getM());
    assertNull(getResult2.getM());
    String expectedS = Boolean.TRUE.toString();
    assertEquals(expectedS, getResult2.getS());
  }

  /**
   * Test {@link DynamoDBTableIdFormat#readItem(Map)}.
   * <ul>
   *   <li>Given {@link DynamoDBTableIndex#TABLE_NAME_FIELD}.</li>
   *   <li>Then return TableName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIdFormat#readItem(Map)}
   */
  @Test
  @DisplayName("Test readItem(Map); given TABLE_NAME_FIELD; then return TableName is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableStatus DynamoDBTableIdFormat.readItem(Map)"})
  void testReadItem_givenTable_name_field_thenReturnTableNameIsNull() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.put(DynamoDBTableIndex.TABLE_NAME_FIELD, null);
    item.put(DynamoDBTableIndex.TABLE_ID_FIELD, null);
    item.put(DynamoDBTableIndex.TABLE_ONLINE_FIELD, new AttributeValue("foo"));

    // Act
    TableStatus actualReadItemResult = DynamoDBTableIdFormat.readItem(item);

    // Assert
    assertNull(actualReadItemResult.getTableName());
    assertNull(actualReadItemResult.getTableUniqueId());
    assertFalse(actualReadItemResult.isOnline());
  }
}
