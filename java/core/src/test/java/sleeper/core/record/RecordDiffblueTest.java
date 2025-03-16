package sleeper.core.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.key.Key;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;

class RecordDiffblueTest {
  /**
   * Test {@link Record#Record()}.
   * <p>
   * Method under test: {@link Record#Record()}
   */
  @Test
  @DisplayName("Test new Record()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Record.<init>()"})
  void testNewRecord() {
    // Arrange, Act and Assert
    assertTrue((new Record()).getKeys().isEmpty());
  }

  /**
   * Test {@link Record#Record(Map)}.
   * <p>
   * Method under test: {@link Record#Record(Map)}
   */
  @Test
  @DisplayName("Test new Record(Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Record.<init>(Map)"})
  void testNewRecord2() {
    // Arrange, Act and Assert
    assertTrue((new Record(new HashMap<>())).getKeys().isEmpty());
  }

  /**
   * Test {@link Record#Record(Record)}.
   * <ul>
   *   <li>When {@link Record#Record()}.</li>
   *   <li>Then return {@link Record#Record()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#Record(Record)}
   */
  @Test
  @DisplayName("Test new Record(Record); when Record(); then return Record()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Record.<init>(Record)"})
  void testNewRecord_whenRecord_thenReturnRecord() {
    // Arrange
    Record resultRecord = new Record();

    // Act and Assert
    assertEquals(resultRecord, new Record(resultRecord));
  }

  /**
   * Test {@link Record#get(String)}.
   * <p>
   * Method under test: {@link Record#get(String)}
   */
  @Test
  @DisplayName("Test get(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object Record.get(String)"})
  void testGet() {
    // Arrange, Act and Assert
    assertNull((new Record()).get("Field Name"));
  }

  /**
   * Test {@link Record#getRowKeys(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return Keys size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#getRowKeys(Schema)}
   */
  @Test
  @DisplayName("Test getRowKeys(Schema); given ArrayList() add 'foo'; then return Keys size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Key Record.getRowKeys(Schema)"})
  void testGetRowKeys_givenArrayListAddFoo_thenReturnKeysSizeIsOne() {
    // Arrange
    Record resultRecord = new Record();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);

    // Act
    Key actualRowKeys = resultRecord.getRowKeys(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    List<Object> keys = actualRowKeys.getKeys();
    assertEquals(1, keys.size());
    assertNull(keys.get(0));
    assertEquals(1, actualRowKeys.size());
    assertFalse(actualRowKeys.isEmpty());
  }

  /**
   * Test {@link Record#getRowKeys(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#getRowKeys(Schema)}
   */
  @Test
  @DisplayName("Test getRowKeys(Schema); given ArrayList(); then return size is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Key Record.getRowKeys(Schema)"})
  void testGetRowKeys_givenArrayList_thenReturnSizeIsZero() {
    // Arrange
    Record resultRecord = new Record();
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());

    // Act
    Key actualRowKeys = resultRecord.getRowKeys(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    assertEquals(0, actualRowKeys.size());
    assertTrue(actualRowKeys.getKeys().isEmpty());
    assertTrue(actualRowKeys.isEmpty());
  }

  /**
   * Test {@link Record#put(String, Object)}.
   * <p>
   * Method under test: {@link Record#put(String, Object)}
   */
  @Test
  @DisplayName("Test put(String, Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Record.put(String, Object)"})
  void testPut() {
    // Arrange
    Record resultRecord = new Record();

    // Act
    resultRecord.put("Field Name", "Value");

    // Assert
    Set<String> keys = resultRecord.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Field Name"));
  }

  /**
   * Test {@link Record#getKeys()}.
   * <p>
   * Method under test: {@link Record#getKeys()}
   */
  @Test
  @DisplayName("Test getKeys()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Set Record.getKeys()"})
  void testGetKeys() {
    // Arrange, Act and Assert
    assertTrue((new Record()).getKeys().isEmpty());
  }

  /**
   * Test {@link Record#getValues(List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#getValues(List)}
   */
  @Test
  @DisplayName("Test getValues(List); given '42'; when ArrayList() add '42'; then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List Record.getValues(List)"})
  void testGetValues_given42_whenArrayListAdd42_thenReturnSizeIsTwo() {
    // Arrange
    Record resultRecord = new Record();

    ArrayList<String> fieldNames = new ArrayList<>();
    fieldNames.add("42");
    fieldNames.add("foo");

    // Act
    List<Object> actualValues = resultRecord.getValues(fieldNames);

    // Assert
    assertEquals(2, actualValues.size());
    assertNull(actualValues.get(0));
    assertNull(actualValues.get(1));
  }

  /**
   * Test {@link Record#getValues(List)}.
   * <ul>
   *   <li>Given {@code Field Names}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code Field Names}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#getValues(List)}
   */
  @Test
  @DisplayName("Test getValues(List); given 'Field Names'; when ArrayList() add 'Field Names'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List Record.getValues(List)"})
  void testGetValues_givenFieldNames_whenArrayListAddFieldNames_thenReturnSizeIsOne() {
    // Arrange
    Record resultRecord = new Record();

    ArrayList<String> fieldNames = new ArrayList<>();
    fieldNames.add("Field Names");

    // Act
    List<Object> actualValues = resultRecord.getValues(fieldNames);

    // Assert
    assertEquals(1, actualValues.size());
    assertNull(actualValues.get(0));
  }

  /**
   * Test {@link Record#getValues(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#getValues(List)}
   */
  @Test
  @DisplayName("Test getValues(List); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List Record.getValues(List)"})
  void testGetValues_whenArrayList_thenReturnEmpty() {
    // Arrange
    Record resultRecord = new Record();

    // Act and Assert
    assertTrue(resultRecord.getValues(new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link Record#equals(Object)}, and {@link Record#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Record#equals(Object)}
   *   <li>{@link Record#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Record.equals(Object)", "int Record.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Record resultRecord = new Record();
    Record resultRecord2 = new Record();

    // Act and Assert
    assertEquals(resultRecord, resultRecord2);
    int expectedHashCodeResult = resultRecord.hashCode();
    assertEquals(expectedHashCodeResult, resultRecord2.hashCode());
  }

  /**
   * Test {@link Record#equals(Object)}, and {@link Record#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Record#equals(Object)}
   *   <li>{@link Record#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Record.equals(Object)", "int Record.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Record resultRecord = new Record();

    // Act and Assert
    assertEquals(resultRecord, resultRecord);
    int expectedHashCodeResult = resultRecord.hashCode();
    assertEquals(expectedHashCodeResult, resultRecord.hashCode());
  }

  /**
   * Test {@link Record#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Record.equals(Object)", "int Record.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Record resultRecord = new Record();
    resultRecord.put("Field Name", "Value");

    // Act and Assert
    assertNotEquals(resultRecord, new Record());
  }

  /**
   * Test {@link Record#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Record.equals(Object)", "int Record.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Record resultRecord = new Record();

    Record resultRecord2 = new Record();
    resultRecord2.put("Field Name", "Value");

    // Act and Assert
    assertNotEquals(resultRecord, resultRecord2);
  }

  /**
   * Test {@link Record#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Record.equals(Object)", "int Record.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Record(), null);
  }

  /**
   * Test {@link Record#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Record.equals(Object)", "int Record.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Record(), "Different type to Record");
  }

  /**
   * Test {@link Record#toString(Schema)} with {@code Schema}.
   * <ul>
   *   <li>Then return {@code Record{}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#toString(Schema)}
   */
  @Test
  @DisplayName("Test toString(Schema) with 'Schema'; then return 'Record{}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Record.toString(Schema)"})
  void testToStringWithSchema_thenReturnRecord() {
    // Arrange
    Record resultRecord = new Record();
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyFields()).thenReturn(new ArrayList<>());
    when(schema.getValueFields()).thenReturn(new ArrayList<>());

    // Act
    String actualToStringResult = resultRecord.toString(schema);

    // Assert
    verify(schema).getRowKeyFields();
    verify(schema).getSortKeyFields();
    verify(schema).getValueFields();
    assertEquals("Record{}", actualToStringResult);
  }

  /**
   * Test {@link Record#toString(Schema)} with {@code Schema}.
   * <ul>
   *   <li>Then return {@code Record{Record{=null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#toString(Schema)}
   */
  @Test
  @DisplayName("Test toString(Schema) with 'Schema'; then return 'Record{Record{=null}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Record.toString(Schema)"})
  void testToStringWithSchema_thenReturnRecordRecordNull() {
    // Arrange
    Record resultRecord = new Record();

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Record{", new IntType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(fieldList);
    when(schema.getSortKeyFields()).thenReturn(new ArrayList<>());
    when(schema.getValueFields()).thenReturn(new ArrayList<>());

    // Act
    String actualToStringResult = resultRecord.toString(schema);

    // Assert
    verify(schema).getRowKeyFields();
    verify(schema).getSortKeyFields();
    verify(schema).getValueFields();
    assertEquals("Record{Record{=null}", actualToStringResult);
  }

  /**
   * Test {@link Record#toString(Schema)} with {@code Schema}.
   * <ul>
   *   <li>Then return {@code Record{Record{=PureByteArray{array=null}}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#toString(Schema)}
   */
  @Test
  @DisplayName("Test toString(Schema) with 'Schema'; then return 'Record{Record{=PureByteArray{array=null}}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Record.toString(Schema)"})
  void testToStringWithSchema_thenReturnRecordRecordPureByteArrayArrayNull() {
    // Arrange
    Record resultRecord = new Record();

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Record{", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(fieldList);
    when(schema.getSortKeyFields()).thenReturn(new ArrayList<>());
    when(schema.getValueFields()).thenReturn(new ArrayList<>());

    // Act
    String actualToStringResult = resultRecord.toString(schema);

    // Assert
    verify(schema).getRowKeyFields();
    verify(schema).getSortKeyFields();
    verify(schema).getValueFields();
    assertEquals("Record{Record{=PureByteArray{array=null}}", actualToStringResult);
  }

  /**
   * Test {@link Record#toString()}.
   * <ul>
   *   <li>Given {@link Record#Record()} {@code Field Name} is {@code Value}.</li>
   *   <li>Then return {@code Record{values={Field Name=Value}}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#toString()}
   */
  @Test
  @DisplayName("Test toString(); given Record() 'Field Name' is 'Value'; then return 'Record{values={Field Name=Value}}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Record.toString()"})
  void testToString_givenRecordFieldNameIsValue_thenReturnRecordValuesFieldNameValue() {
    // Arrange
    Record resultRecord = new Record();
    resultRecord.put("Field Name", "Value");

    // Act and Assert
    assertEquals("Record{values={Field Name=Value}}", resultRecord.toString());
  }

  /**
   * Test {@link Record#toString()}.
   * <ul>
   *   <li>Given {@link Record#Record()}.</li>
   *   <li>Then return {@code Record{values={}}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Record#toString()}
   */
  @Test
  @DisplayName("Test toString(); given Record(); then return 'Record{values={}}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Record.toString()"})
  void testToString_givenRecord_thenReturnRecordValues() {
    // Arrange, Act and Assert
    assertEquals("Record{values={}}", (new Record()).toString());
  }
}
