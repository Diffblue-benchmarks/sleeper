package sleeper.dynamodb.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DynamoDBRecordBuilderDiffblueTest {
  /**
   * Test {@link DynamoDBRecordBuilder#string(String, String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return build size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#string(String, String)}
   */
  @Test
  @DisplayName("Test string(String, String); when '42'; then return build size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.string(String, String)"})
  void testString_when42_thenReturnBuildSizeIsOne() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    // Act and Assert
    Map<String, AttributeValue> buildResult = dynamoDBRecordBuilder.string("Key", "42").build();
    assertEquals(1, buildResult.size());
    AttributeValue getResult = buildResult.get("Key");
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
    Map<String, AttributeValue> buildResult2 = dynamoDBRecordBuilder.build();
    assertEquals(1, buildResult2.size());
    assertTrue(buildResult2.containsKey("Key"));
  }

  /**
   * Test {@link DynamoDBRecordBuilder#string(String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#string(String, String)}
   */
  @Test
  @DisplayName("Test string(String, String); when 'null'; then DynamoDBRecordBuilder (default constructor) build Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.string(String, String)"})
  void testString_whenNull_thenDynamoDBRecordBuilderBuildEmpty() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    // Act
    DynamoDBRecordBuilder actualStringResult = dynamoDBRecordBuilder.string("Key", null);

    // Assert
    assertTrue(dynamoDBRecordBuilder.build().isEmpty());
    assertSame(dynamoDBRecordBuilder, actualStringResult);
  }

  /**
   * Test {@link DynamoDBRecordBuilder#number(String, Number)}.
   * <ul>
   *   <li>When {@link Double#NaN}.</li>
   *   <li>Then return build {@code Key} N is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#number(String, Number)}
   */
  @Test
  @DisplayName("Test number(String, Number); when NaN; then return build 'Key' N is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.number(String, Number)"})
  void testNumber_whenNaN_thenReturnBuildKeyNIsNull() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    // Act and Assert
    Map<String, AttributeValue> buildResult = dynamoDBRecordBuilder.number("Key", Double.NaN).build();
    assertEquals(1, buildResult.size());
    AttributeValue getResult = buildResult.get("Key");
    assertNull(getResult.getBOOL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.getN());
    assertNull(getResult.getS());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    Map<String, AttributeValue> buildResult2 = dynamoDBRecordBuilder.build();
    assertEquals(1, buildResult2.size());
    assertTrue(getResult.getNULL());
    assertTrue(getResult.isNULL());
    assertTrue(buildResult2.containsKey("Key"));
  }

  /**
   * Test {@link DynamoDBRecordBuilder#number(String, Number)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#number(String, Number)}
   */
  @Test
  @DisplayName("Test number(String, Number); when 'null'; then DynamoDBRecordBuilder (default constructor) build Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.number(String, Number)"})
  void testNumber_whenNull_thenDynamoDBRecordBuilderBuildEmpty() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    // Act
    DynamoDBRecordBuilder actualNumberResult = dynamoDBRecordBuilder.number("Key", null);

    // Assert
    assertTrue(dynamoDBRecordBuilder.build().isEmpty());
    assertSame(dynamoDBRecordBuilder, actualNumberResult);
  }

  /**
   * Test {@link DynamoDBRecordBuilder#number(String, Number)}.
   * <ul>
   *   <li>When valueOf one.</li>
   *   <li>Then return build {@code Key} N is {@code 1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#number(String, Number)}
   */
  @Test
  @DisplayName("Test number(String, Number); when valueOf one; then return build 'Key' N is '1'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.number(String, Number)"})
  void testNumber_whenValueOfOne_thenReturnBuildKeyNIs1() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    // Act and Assert
    Map<String, AttributeValue> buildResult = dynamoDBRecordBuilder.number("Key", Integer.valueOf(1)).build();
    assertEquals(1, buildResult.size());
    AttributeValue getResult = buildResult.get("Key");
    assertEquals("1", getResult.getN());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getS());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    Map<String, AttributeValue> buildResult2 = dynamoDBRecordBuilder.build();
    assertEquals(1, buildResult2.size());
    assertTrue(buildResult2.containsKey("Key"));
  }

  /**
   * Test {@link DynamoDBRecordBuilder#bool(String, Boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#bool(String, Boolean)}
   */
  @Test
  @DisplayName("Test bool(String, Boolean); when 'true'; then DynamoDBRecordBuilder (default constructor) build size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.bool(String, Boolean)"})
  void testBool_whenTrue_thenDynamoDBRecordBuilderBuildSizeIsOne() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    // Act
    DynamoDBRecordBuilder actualBoolResult = dynamoDBRecordBuilder.bool("Key", true);

    // Assert
    Map<String, AttributeValue> buildResult = dynamoDBRecordBuilder.build();
    assertEquals(1, buildResult.size());
    assertTrue(buildResult.containsKey("Key"));
    assertSame(dynamoDBRecordBuilder, actualBoolResult);
  }

  /**
   * Test {@link DynamoDBRecordBuilder#list(String, List)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@code foo}.</li>
   *   <li>Then return build {@code Key} L is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#list(String, List)}
   */
  @Test
  @DisplayName("Test list(String, List); given AttributeValue(String) with s is 'foo'; then return build 'Key' L is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.list(String, List)"})
  void testList_givenAttributeValueWithSIsFoo_thenReturnBuildKeyLIsArrayList() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    ArrayList<AttributeValue> values = new ArrayList<>();
    values.add(new AttributeValue("foo"));

    // Act and Assert
    Map<String, AttributeValue> buildResult = dynamoDBRecordBuilder.list("Key", values).build();
    assertEquals(1, buildResult.size());
    assertEquals(values, buildResult.get("Key").getL());
  }

  /**
   * Test {@link DynamoDBRecordBuilder#list(String, List)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@code foo}.</li>
   *   <li>Then return build {@code Key} L size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#list(String, List)}
   */
  @Test
  @DisplayName("Test list(String, List); given AttributeValue(String) with s is 'foo'; then return build 'Key' L size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.list(String, List)"})
  void testList_givenAttributeValueWithSIsFoo_thenReturnBuildKeyLSizeIsTwo() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    ArrayList<AttributeValue> values = new ArrayList<>();
    values.add(new AttributeValue("foo"));
    AttributeValue attributeValue = new AttributeValue("foo");
    values.add(attributeValue);

    // Act and Assert
    Map<String, AttributeValue> buildResult = dynamoDBRecordBuilder.list("Key", values).build();
    assertEquals(1, buildResult.size());
    List<AttributeValue> l = buildResult.get("Key").getL();
    assertEquals(2, l.size());
    assertSame(attributeValue, l.get(1));
  }

  /**
   * Test {@link DynamoDBRecordBuilder#list(String, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return build {@code Key} BOOL is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#list(String, List)}
   */
  @Test
  @DisplayName("Test list(String, List); when ArrayList(); then return build 'Key' BOOL is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.list(String, List)"})
  void testList_whenArrayList_thenReturnBuildKeyBoolIsNull() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    // Act and Assert
    Map<String, AttributeValue> buildResult = dynamoDBRecordBuilder.list("Key", new ArrayList<>()).build();
    assertEquals(1, buildResult.size());
    AttributeValue getResult = buildResult.get("Key");
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getS());
    assertNull(getResult.getB());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    Map<String, AttributeValue> buildResult2 = dynamoDBRecordBuilder.build();
    assertEquals(1, buildResult2.size());
    assertTrue(getResult.getL().isEmpty());
    assertTrue(buildResult2.containsKey("Key"));
  }

  /**
   * Test {@link DynamoDBRecordBuilder#list(String, List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#list(String, List)}
   */
  @Test
  @DisplayName("Test list(String, List); when 'null'; then DynamoDBRecordBuilder (default constructor) build Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.list(String, List)"})
  void testList_whenNull_thenDynamoDBRecordBuilderBuildEmpty() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    // Act
    DynamoDBRecordBuilder actualListResult = dynamoDBRecordBuilder.list("Key", null);

    // Assert
    assertTrue(dynamoDBRecordBuilder.build().isEmpty());
    assertSame(dynamoDBRecordBuilder, actualListResult);
  }

  /**
   * Test {@link DynamoDBRecordBuilder#bytes(String, byte[])}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#bytes(String, byte[])}
   */
  @Test
  @DisplayName("Test bytes(String, byte[]); when 'AXAXAXAX' Bytes is 'UTF-8'; then DynamoDBRecordBuilder (default constructor) build size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.bytes(String, byte[])"})
  void testBytes_whenAxaxaxaxBytesIsUtf8_thenDynamoDBRecordBuilderBuildSizeIsOne() throws UnsupportedEncodingException {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();

    // Act
    DynamoDBRecordBuilder actualBytesResult = dynamoDBRecordBuilder.bytes("Key", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    Map<String, AttributeValue> buildResult = dynamoDBRecordBuilder.build();
    assertEquals(1, buildResult.size());
    assertTrue(buildResult.containsKey("Key"));
    assertSame(dynamoDBRecordBuilder, actualBytesResult);
  }

  /**
   * Test {@link DynamoDBRecordBuilder#apply(Consumer)}.
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#apply(Consumer)}
   */
  @Test
  @DisplayName("Test apply(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBRecordBuilder DynamoDBRecordBuilder.apply(Consumer)"})
  void testApply() {
    // Arrange
    DynamoDBRecordBuilder dynamoDBRecordBuilder = new DynamoDBRecordBuilder();
    Consumer<DynamoDBRecordBuilder> config = mock(Consumer.class);
    doNothing().when(config).accept(Mockito.<DynamoDBRecordBuilder>any());

    // Act
    DynamoDBRecordBuilder actualApplyResult = dynamoDBRecordBuilder.apply(config);

    // Assert
    verify(config).accept(isA(DynamoDBRecordBuilder.class));
    assertSame(dynamoDBRecordBuilder, actualApplyResult);
  }

  /**
   * Test {@link DynamoDBRecordBuilder#build()}.
   * <p>
   * Method under test: {@link DynamoDBRecordBuilder#build()}
   */
  @Test
  @DisplayName("Test build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBRecordBuilder.build()"})
  void testBuild() {
    // Arrange, Act and Assert
    assertTrue((new DynamoDBRecordBuilder()).build().isEmpty());
  }
}
