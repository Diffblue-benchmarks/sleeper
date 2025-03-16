package sleeper.dynamodb.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongFunction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DynamoDBAttributesDiffblueTest {
  /**
   * Test {@link DynamoDBAttributes#createStringAttribute(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#createStringAttribute(String)}
   */
  @Test
  @DisplayName("Test createStringAttribute(String); when 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createStringAttribute(String)"})
  void testCreateStringAttribute_whenNull() {
    // Arrange, Act and Assert
    assertNull(DynamoDBAttributes.createStringAttribute(null));
  }

  /**
   * Test {@link DynamoDBAttributes#createStringAttribute(String)}.
   * <ul>
   *   <li>When {@code Str}.</li>
   *   <li>Then return S is {@code Str}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#createStringAttribute(String)}
   */
  @Test
  @DisplayName("Test createStringAttribute(String); when 'Str'; then return S is 'Str'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createStringAttribute(String)"})
  void testCreateStringAttribute_whenStr_thenReturnSIsStr() {
    // Arrange and Act
    AttributeValue actualCreateStringAttributeResult = DynamoDBAttributes.createStringAttribute("Str");

    // Assert
    assertEquals("Str", actualCreateStringAttributeResult.getS());
    assertNull(actualCreateStringAttributeResult.getBOOL());
    assertNull(actualCreateStringAttributeResult.getNULL());
    assertNull(actualCreateStringAttributeResult.isBOOL());
    assertNull(actualCreateStringAttributeResult.isNULL());
    assertNull(actualCreateStringAttributeResult.getN());
    assertNull(actualCreateStringAttributeResult.getB());
    assertNull(actualCreateStringAttributeResult.getL());
    assertNull(actualCreateStringAttributeResult.getNS());
    assertNull(actualCreateStringAttributeResult.getSS());
    assertNull(actualCreateStringAttributeResult.getBS());
    assertNull(actualCreateStringAttributeResult.getM());
  }

  /**
   * Test {@link DynamoDBAttributes#createNumberAttribute(Number)}.
   * <ul>
   *   <li>When {@link Double#NaN}.</li>
   *   <li>Then return N is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#createNumberAttribute(Number)}
   */
  @Test
  @DisplayName("Test createNumberAttribute(Number); when NaN; then return N is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createNumberAttribute(Number)"})
  void testCreateNumberAttribute_whenNaN_thenReturnNIsNull() {
    // Arrange and Act
    AttributeValue actualCreateNumberAttributeResult = DynamoDBAttributes.createNumberAttribute(Double.NaN);

    // Assert
    assertNull(actualCreateNumberAttributeResult.getBOOL());
    assertNull(actualCreateNumberAttributeResult.isBOOL());
    assertNull(actualCreateNumberAttributeResult.getN());
    assertNull(actualCreateNumberAttributeResult.getS());
    assertNull(actualCreateNumberAttributeResult.getB());
    assertNull(actualCreateNumberAttributeResult.getL());
    assertNull(actualCreateNumberAttributeResult.getNS());
    assertNull(actualCreateNumberAttributeResult.getSS());
    assertNull(actualCreateNumberAttributeResult.getBS());
    assertNull(actualCreateNumberAttributeResult.getM());
    assertTrue(actualCreateNumberAttributeResult.getNULL());
    assertTrue(actualCreateNumberAttributeResult.isNULL());
  }

  /**
   * Test {@link DynamoDBAttributes#createNumberAttribute(Number)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#createNumberAttribute(Number)}
   */
  @Test
  @DisplayName("Test createNumberAttribute(Number); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createNumberAttribute(Number)"})
  void testCreateNumberAttribute_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DynamoDBAttributes.createNumberAttribute(null));
  }

  /**
   * Test {@link DynamoDBAttributes#createNumberAttribute(Number)}.
   * <ul>
   *   <li>When valueOf one.</li>
   *   <li>Then return N is {@code 1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#createNumberAttribute(Number)}
   */
  @Test
  @DisplayName("Test createNumberAttribute(Number); when valueOf one; then return N is '1'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createNumberAttribute(Number)"})
  void testCreateNumberAttribute_whenValueOfOne_thenReturnNIs1() {
    // Arrange and Act
    AttributeValue actualCreateNumberAttributeResult = DynamoDBAttributes.createNumberAttribute(Integer.valueOf(1));

    // Assert
    assertEquals("1", actualCreateNumberAttributeResult.getN());
    assertNull(actualCreateNumberAttributeResult.getBOOL());
    assertNull(actualCreateNumberAttributeResult.getNULL());
    assertNull(actualCreateNumberAttributeResult.isBOOL());
    assertNull(actualCreateNumberAttributeResult.isNULL());
    assertNull(actualCreateNumberAttributeResult.getS());
    assertNull(actualCreateNumberAttributeResult.getB());
    assertNull(actualCreateNumberAttributeResult.getL());
    assertNull(actualCreateNumberAttributeResult.getNS());
    assertNull(actualCreateNumberAttributeResult.getSS());
    assertNull(actualCreateNumberAttributeResult.getBS());
    assertNull(actualCreateNumberAttributeResult.getM());
  }

  /**
   * Test {@link DynamoDBAttributes#createInstantAttribute(Instant)}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#createInstantAttribute(Instant)}
   */
  @Test
  @DisplayName("Test createInstantAttribute(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createInstantAttribute(Instant)"})
  void testCreateInstantAttribute() {
    // Arrange and Act
    AttributeValue actualCreateInstantAttributeResult = DynamoDBAttributes
        .createInstantAttribute(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals("0", actualCreateInstantAttributeResult.getN());
    assertNull(actualCreateInstantAttributeResult.getBOOL());
    assertNull(actualCreateInstantAttributeResult.getNULL());
    assertNull(actualCreateInstantAttributeResult.isBOOL());
    assertNull(actualCreateInstantAttributeResult.isNULL());
    assertNull(actualCreateInstantAttributeResult.getS());
    assertNull(actualCreateInstantAttributeResult.getB());
    assertNull(actualCreateInstantAttributeResult.getL());
    assertNull(actualCreateInstantAttributeResult.getNS());
    assertNull(actualCreateInstantAttributeResult.getSS());
    assertNull(actualCreateInstantAttributeResult.getBS());
    assertNull(actualCreateInstantAttributeResult.getM());
  }

  /**
   * Test {@link DynamoDBAttributes#createBooleanAttribute(boolean)}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#createBooleanAttribute(boolean)}
   */
  @Test
  @DisplayName("Test createBooleanAttribute(boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createBooleanAttribute(boolean)"})
  void testCreateBooleanAttribute() {
    // Arrange and Act
    AttributeValue actualCreateBooleanAttributeResult = DynamoDBAttributes.createBooleanAttribute(true);

    // Assert
    assertNull(actualCreateBooleanAttributeResult.getNULL());
    assertNull(actualCreateBooleanAttributeResult.isNULL());
    assertNull(actualCreateBooleanAttributeResult.getN());
    assertNull(actualCreateBooleanAttributeResult.getS());
    assertNull(actualCreateBooleanAttributeResult.getB());
    assertNull(actualCreateBooleanAttributeResult.getL());
    assertNull(actualCreateBooleanAttributeResult.getNS());
    assertNull(actualCreateBooleanAttributeResult.getSS());
    assertNull(actualCreateBooleanAttributeResult.getBS());
    assertNull(actualCreateBooleanAttributeResult.getM());
    assertTrue(actualCreateBooleanAttributeResult.getBOOL());
    assertTrue(actualCreateBooleanAttributeResult.isBOOL());
  }

  /**
   * Test {@link DynamoDBAttributes#createBinaryAttribute(byte[])}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#createBinaryAttribute(byte[])}
   */
  @Test
  @DisplayName("Test createBinaryAttribute(byte[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createBinaryAttribute(byte[])"})
  void testCreateBinaryAttribute() throws UnsupportedEncodingException {
    // Arrange and Act
    AttributeValue actualCreateBinaryAttributeResult = DynamoDBAttributes
        .createBinaryAttribute("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateBinaryAttributeResult.getBOOL());
    assertNull(actualCreateBinaryAttributeResult.getNULL());
    assertNull(actualCreateBinaryAttributeResult.isBOOL());
    assertNull(actualCreateBinaryAttributeResult.isNULL());
    assertNull(actualCreateBinaryAttributeResult.getN());
    assertNull(actualCreateBinaryAttributeResult.getS());
    assertNull(actualCreateBinaryAttributeResult.getL());
    assertNull(actualCreateBinaryAttributeResult.getNS());
    assertNull(actualCreateBinaryAttributeResult.getSS());
    assertNull(actualCreateBinaryAttributeResult.getBS());
    assertNull(actualCreateBinaryAttributeResult.getM());
  }

  /**
   * Test {@link DynamoDBAttributes#createListAttribute(List)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@code foo}.</li>
   *   <li>Then return L is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#createListAttribute(List)}
   */
  @Test
  @DisplayName("Test createListAttribute(List); given AttributeValue(String) with s is 'foo'; then return L is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createListAttribute(List)"})
  void testCreateListAttribute_givenAttributeValueWithSIsFoo_thenReturnLIsArrayList() {
    // Arrange
    ArrayList<AttributeValue> values = new ArrayList<>();
    values.add(new AttributeValue("foo"));

    // Act and Assert
    assertEquals(values, DynamoDBAttributes.createListAttribute(values).getL());
  }

  /**
   * Test {@link DynamoDBAttributes#createListAttribute(List)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@code foo}.</li>
   *   <li>Then return L size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#createListAttribute(List)}
   */
  @Test
  @DisplayName("Test createListAttribute(List); given AttributeValue(String) with s is 'foo'; then return L size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createListAttribute(List)"})
  void testCreateListAttribute_givenAttributeValueWithSIsFoo_thenReturnLSizeIsTwo() {
    // Arrange
    ArrayList<AttributeValue> values = new ArrayList<>();
    values.add(new AttributeValue("foo"));
    AttributeValue attributeValue = new AttributeValue("foo");
    values.add(attributeValue);

    // Act and Assert
    List<AttributeValue> l = DynamoDBAttributes.createListAttribute(values).getL();
    assertEquals(2, l.size());
    assertSame(attributeValue, l.get(1));
  }

  /**
   * Test {@link DynamoDBAttributes#createListAttribute(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return BOOL is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#createListAttribute(List)}
   */
  @Test
  @DisplayName("Test createListAttribute(List); when ArrayList(); then return BOOL is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createListAttribute(List)"})
  void testCreateListAttribute_whenArrayList_thenReturnBoolIsNull() {
    // Arrange and Act
    AttributeValue actualCreateListAttributeResult = DynamoDBAttributes.createListAttribute(new ArrayList<>());

    // Assert
    assertNull(actualCreateListAttributeResult.getBOOL());
    assertNull(actualCreateListAttributeResult.getNULL());
    assertNull(actualCreateListAttributeResult.isBOOL());
    assertNull(actualCreateListAttributeResult.isNULL());
    assertNull(actualCreateListAttributeResult.getN());
    assertNull(actualCreateListAttributeResult.getS());
    assertNull(actualCreateListAttributeResult.getB());
    assertNull(actualCreateListAttributeResult.getNS());
    assertNull(actualCreateListAttributeResult.getSS());
    assertNull(actualCreateListAttributeResult.getBS());
    assertNull(actualCreateListAttributeResult.getM());
    assertTrue(actualCreateListAttributeResult.getL().isEmpty());
  }

  /**
   * Test {@link DynamoDBAttributes#createListAttribute(List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#createListAttribute(List)}
   */
  @Test
  @DisplayName("Test createListAttribute(List); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AttributeValue DynamoDBAttributes.createListAttribute(List)"})
  void testCreateListAttribute_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DynamoDBAttributes.createListAttribute(null));
  }

  /**
   * Test {@link DynamoDBAttributes#getStringAttribute(Map, String)}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#getStringAttribute(Map, String)}
   */
  @Test
  @DisplayName("Test getStringAttribute(Map, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBAttributes.getStringAttribute(Map, String)"})
  void testGetStringAttribute() {
    // Arrange, Act and Assert
    assertNull(DynamoDBAttributes.getStringAttribute(new HashMap<>(), "Name"));
  }

  /**
   * Test {@link DynamoDBAttributes#getNumberAttribute(Map, String)}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#getNumberAttribute(Map, String)}
   */
  @Test
  @DisplayName("Test getNumberAttribute(Map, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBAttributes.getNumberAttribute(Map, String)"})
  void testGetNumberAttribute() {
    // Arrange, Act and Assert
    assertNull(DynamoDBAttributes.getNumberAttribute(new HashMap<>(), "Name"));
  }

  /**
   * Test {@link DynamoDBAttributes#getBooleanAttribute(Map, String)}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#getBooleanAttribute(Map, String)}
   */
  @Test
  @DisplayName("Test getBooleanAttribute(Map, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DynamoDBAttributes.getBooleanAttribute(Map, String)"})
  void testGetBooleanAttribute() {
    // Arrange, Act and Assert
    assertFalse(DynamoDBAttributes.getBooleanAttribute(new HashMap<>(), "Name"));
  }

  /**
   * Test {@link DynamoDBAttributes#getIntAttribute(Map, String, int)}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#getIntAttribute(Map, String, int)}
   */
  @Test
  @DisplayName("Test getIntAttribute(Map, String, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int DynamoDBAttributes.getIntAttribute(Map, String, int)"})
  void testGetIntAttribute() {
    // Arrange, Act and Assert
    assertEquals(42, DynamoDBAttributes.getIntAttribute(new HashMap<>(), "Name", 42));
  }

  /**
   * Test {@link DynamoDBAttributes#getNullableIntAttribute(Map, String)}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#getNullableIntAttribute(Map, String)}
   */
  @Test
  @DisplayName("Test getNullableIntAttribute(Map, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Integer DynamoDBAttributes.getNullableIntAttribute(Map, String)"})
  void testGetNullableIntAttribute() {
    // Arrange, Act and Assert
    assertNull(DynamoDBAttributes.getNullableIntAttribute(new HashMap<>(), "Name"));
  }

  /**
   * Test {@link DynamoDBAttributes#getLongAttribute(Map, String, long)}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#getLongAttribute(Map, String, long)}
   */
  @Test
  @DisplayName("Test getLongAttribute(Map, String, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long DynamoDBAttributes.getLongAttribute(Map, String, long)"})
  void testGetLongAttribute() {
    // Arrange, Act and Assert
    assertEquals(42L, DynamoDBAttributes.getLongAttribute(new HashMap<>(), "Name", 42L));
  }

  /**
   * Test {@link DynamoDBAttributes#getInstantAttribute(Map, String)} with {@code item}, {@code name}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#getInstantAttribute(Map, String)}
   */
  @Test
  @DisplayName("Test getInstantAttribute(Map, String) with 'item', 'name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant DynamoDBAttributes.getInstantAttribute(Map, String)"})
  void testGetInstantAttributeWithItemName() {
    // Arrange, Act and Assert
    assertNull(DynamoDBAttributes.getInstantAttribute(new HashMap<>(), "Name"));
  }

  /**
   * Test {@link DynamoDBAttributes#getInstantAttribute(Map, String, LongFunction)} with {@code item}, {@code name}, {@code buildInstant}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#getInstantAttribute(Map, String, LongFunction)}
   */
  @Test
  @DisplayName("Test getInstantAttribute(Map, String, LongFunction) with 'item', 'name', 'buildInstant'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant DynamoDBAttributes.getInstantAttribute(Map, String, LongFunction)"})
  void testGetInstantAttributeWithItemNameBuildInstant() {
    // Arrange, Act and Assert
    assertNull(DynamoDBAttributes.getInstantAttribute(new HashMap<>(), "Name", mock(LongFunction.class)));
  }

  /**
   * Test {@link DynamoDBAttributes#getDoubleAttribute(Map, String, double)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@code foo} N is {@code 42}.</li>
   *   <li>Then return forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#getDoubleAttribute(Map, String, double)}
   */
  @Test
  @DisplayName("Test getDoubleAttribute(Map, String, double); given AttributeValue(String) with s is 'foo' N is '42'; then return forty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double DynamoDBAttributes.getDoubleAttribute(Map, String, double)"})
  void testGetDoubleAttribute_givenAttributeValueWithSIsFooNIs42_thenReturnFortyTwo() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue("foo");
    attributeValue.setN("42");

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.put("42", attributeValue);

    // Act and Assert
    assertEquals(42.0d, DynamoDBAttributes.getDoubleAttribute(item, "42", 10.0d));
  }

  /**
   * Test {@link DynamoDBAttributes#getDoubleAttribute(Map, String, double)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@code foo}.</li>
   *   <li>Then return {@link Double#NaN}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#getDoubleAttribute(Map, String, double)}
   */
  @Test
  @DisplayName("Test getDoubleAttribute(Map, String, double); given AttributeValue(String) with s is 'foo'; then return NaN")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double DynamoDBAttributes.getDoubleAttribute(Map, String, double)"})
  void testGetDoubleAttribute_givenAttributeValueWithSIsFoo_thenReturnNaN() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.put("42", new AttributeValue("foo"));

    // Act and Assert
    assertEquals(Double.NaN, DynamoDBAttributes.getDoubleAttribute(item, "42", 10.0d));
  }

  /**
   * Test {@link DynamoDBAttributes#getDoubleAttribute(Map, String, double)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@code null}.</li>
   *   <li>Then return {@link Double#NaN}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#getDoubleAttribute(Map, String, double)}
   */
  @Test
  @DisplayName("Test getDoubleAttribute(Map, String, double); given 'null'; when HashMap() '42' is 'null'; then return NaN")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double DynamoDBAttributes.getDoubleAttribute(Map, String, double)"})
  void testGetDoubleAttribute_givenNull_whenHashMap42IsNull_thenReturnNaN() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.put("42", null);

    // Act and Assert
    assertEquals(Double.NaN, DynamoDBAttributes.getDoubleAttribute(item, "42", 10.0d));
  }

  /**
   * Test {@link DynamoDBAttributes#getDoubleAttribute(Map, String, double)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBAttributes#getDoubleAttribute(Map, String, double)}
   */
  @Test
  @DisplayName("Test getDoubleAttribute(Map, String, double); when HashMap(); then return ten")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double DynamoDBAttributes.getDoubleAttribute(Map, String, double)"})
  void testGetDoubleAttribute_whenHashMap_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, DynamoDBAttributes.getDoubleAttribute(new HashMap<>(), "Key", 10.0d));
  }

  /**
   * Test {@link DynamoDBAttributes#getStringListAttribute(Map, String)}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#getStringListAttribute(Map, String)}
   */
  @Test
  @DisplayName("Test getStringListAttribute(Map, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBAttributes.getStringListAttribute(Map, String)"})
  void testGetStringListAttribute() {
    // Arrange and Act
    List<String> actualStringListAttribute = DynamoDBAttributes.getStringListAttribute(new HashMap<>(), "Name");

    // Assert
    assertNull(actualStringListAttribute);
  }

  /**
   * Test {@link DynamoDBAttributes#getListAttribute(Map, String)} with {@code item}, {@code name}.
   * <p>
   * Method under test: {@link DynamoDBAttributes#getListAttribute(Map, String)}
   */
  @Test
  @DisplayName("Test getListAttribute(Map, String) with 'item', 'name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBAttributes.getListAttribute(Map, String)"})
  void testGetListAttributeWithItemName() {
    // Arrange and Act
    List<AttributeValue> actualListAttribute = DynamoDBAttributes.getListAttribute(new HashMap<>(), "Name");

    // Assert
    assertNull(actualListAttribute);
  }
}
