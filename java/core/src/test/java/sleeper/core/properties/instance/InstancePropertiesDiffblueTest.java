package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.testutils.DummyInstanceProperty;

class InstancePropertiesDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link InstanceProperties#InstanceProperties()}
   *   <li>{@link InstanceProperties#getPropertiesIndex()}
   *   <li>{@link InstanceProperties#getTags()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InstanceProperties.<init>()", "SleeperPropertyIndex InstanceProperties.getPropertiesIndex()",
      "Map InstanceProperties.getTags()"})
  void testGettersAndSetters() {
    // Arrange and Act
    InstanceProperties actualInstanceProperties = new InstanceProperties();
    SleeperPropertyIndex<InstanceProperty> actualPropertiesIndex = actualInstanceProperties.getPropertiesIndex();
    Map<String, String> actualTags = actualInstanceProperties.getTags();

    // Assert
    List<InstanceProperty> all = actualPropertiesIndex.getAll();
    assertEquals(456, all.size());
    InstanceProperty getResult = all.get(453);
    assertTrue(getResult instanceof CdkDefinedInstancePropertyImpl);
    InstanceProperty getResult2 = all.get(454);
    assertTrue(getResult2 instanceof CdkDefinedInstancePropertyImpl);
    InstanceProperty getResult3 = all.get(455);
    assertTrue(getResult3 instanceof CdkDefinedInstancePropertyImpl);
    List<InstanceProperty> cdkDefined = actualPropertiesIndex.getCdkDefined();
    assertEquals(131, cdkDefined.size());
    assertTrue(cdkDefined.get(0) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(cdkDefined.get(1) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(cdkDefined.get(2) instanceof CdkDefinedInstancePropertyImpl);
    InstanceProperty getResult4 = all.get(0);
    assertTrue(getResult4 instanceof UserDefinedInstancePropertyImpl);
    InstanceProperty getResult5 = all.get(1);
    assertTrue(getResult5 instanceof UserDefinedInstancePropertyImpl);
    InstanceProperty getResult6 = all.get(2);
    assertTrue(getResult6 instanceof UserDefinedInstancePropertyImpl);
    List<InstanceProperty> userDefined = actualPropertiesIndex.getUserDefined();
    assertEquals(325, userDefined.size());
    assertTrue(userDefined.get(322) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(userDefined.get(323) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(userDefined.get(324) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualTags.isEmpty());
    assertSame(getResult, cdkDefined.get(128));
    assertSame(getResult2, cdkDefined.get(129));
    assertSame(getResult3, cdkDefined.get(130));
    assertSame(getResult4, userDefined.get(0));
    assertSame(getResult5, userDefined.get(1));
    assertSame(getResult6, userDefined.get(2));
  }

  /**
   * Test {@link InstanceProperties#InstanceProperties(Properties)}.
   * <p>
   * Method under test: {@link InstanceProperties#InstanceProperties(Properties)}
   */
  @Test
  @DisplayName("Test new InstanceProperties(Properties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InstanceProperties.<init>(Properties)"})
  void testNewInstanceProperties() {
    // Arrange
    Properties properties = new Properties();

    // Act
    InstanceProperties actualInstanceProperties = new InstanceProperties(properties);

    // Assert
    SleeperPropertyIndex<InstanceProperty> propertiesIndex = actualInstanceProperties.getPropertiesIndex();
    assertEquals(131, propertiesIndex.getCdkDefined().size());
    assertEquals(325, propertiesIndex.getUserDefined().size());
    assertEquals(456, propertiesIndex.getAll().size());
    Stream<Entry<String, String>> unknownProperties = actualInstanceProperties.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
    assertTrue(actualInstanceProperties.toMap().isEmpty());
    assertTrue(actualInstanceProperties.getTags().isEmpty());
    assertSame(properties, actualInstanceProperties.getProperties());
  }

  /**
   * Test {@link InstanceProperties#copyOf(InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#copyOf(InstanceProperties)}
   */
  @Test
  @DisplayName("Test copyOf(InstanceProperties); when InstanceProperties(); then return InstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties InstanceProperties.copyOf(InstanceProperties)"})
  void testCopyOf_whenInstanceProperties_thenReturnInstanceProperties() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertEquals(instanceProperties, InstanceProperties.copyOf(instanceProperties));
  }

  /**
   * Test {@link InstanceProperties#createWithoutValidation(Properties)}.
   * <p>
   * Method under test: {@link InstanceProperties#createWithoutValidation(Properties)}
   */
  @Test
  @DisplayName("Test createWithoutValidation(Properties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties InstanceProperties.createWithoutValidation(Properties)"})
  void testCreateWithoutValidation() {
    // Arrange
    Properties properties = new Properties();

    // Act
    InstanceProperties actualCreateWithoutValidationResult = InstanceProperties.createWithoutValidation(properties);

    // Assert
    SleeperPropertyIndex<InstanceProperty> propertiesIndex = actualCreateWithoutValidationResult.getPropertiesIndex();
    assertEquals(131, propertiesIndex.getCdkDefined().size());
    assertEquals(325, propertiesIndex.getUserDefined().size());
    assertEquals(456, propertiesIndex.getAll().size());
    Stream<Entry<String, String>> unknownProperties = actualCreateWithoutValidationResult.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
    assertTrue(actualCreateWithoutValidationResult.toMap().isEmpty());
    assertTrue(actualCreateWithoutValidationResult.getTags().isEmpty());
    assertSame(properties, actualCreateWithoutValidationResult.getProperties());
  }

  /**
   * Test {@link InstanceProperties#setTags(Map)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap {@code sleeper.tags} is {@code 42,foo,foo,foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#setTags(Map)}
   */
  @Test
  @DisplayName("Test setTags(Map); given '42'; then InstanceProperties() toMap 'sleeper.tags' is '42,foo,foo,foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InstanceProperties.setTags(Map)"})
  void testSetTags_given42_thenInstancePropertiesToMapSleeperTagsIs42FooFooFoo() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    HashMap<String, String> tagsMap = new HashMap<>();
    tagsMap.put("42", "foo");
    tagsMap.put("foo", "foo");

    // Act
    instanceProperties.setTags(tagsMap);

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("42,foo,foo,foo", toMapResult.get("sleeper.tags"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("42,foo,foo,foo", properties.get("sleeper.tags"));
    Map<String, String> tags = instanceProperties.getTags();
    assertEquals(2, tags.size());
    assertEquals("foo", tags.get("42"));
    assertEquals("foo", tags.get("foo"));
    Properties tagsProperties = instanceProperties.getTagsProperties();
    assertEquals(2, tagsProperties.size());
    assertEquals("foo", tagsProperties.get("42"));
    assertTrue(tagsProperties.containsKey("foo"));
  }

  /**
   * Test {@link InstanceProperties#setTags(Map)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap {@code sleeper.tags} is {@code foo,foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#setTags(Map)}
   */
  @Test
  @DisplayName("Test setTags(Map); given 'foo'; then InstanceProperties() toMap 'sleeper.tags' is 'foo,foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InstanceProperties.setTags(Map)"})
  void testSetTags_givenFoo_thenInstancePropertiesToMapSleeperTagsIsFooFoo() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    HashMap<String, String> tagsMap = new HashMap<>();
    tagsMap.put("foo", "foo");

    // Act
    instanceProperties.setTags(tagsMap);

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("foo,foo", toMapResult.get("sleeper.tags"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("foo,foo", properties.get("sleeper.tags"));
    Properties tagsProperties = instanceProperties.getTagsProperties();
    assertEquals(1, tagsProperties.size());
    assertTrue(tagsProperties.containsKey("foo"));
  }

  /**
   * Test {@link InstanceProperties#setTags(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap {@code sleeper.tags} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#setTags(Map)}
   */
  @Test
  @DisplayName("Test setTags(Map); when HashMap(); then InstanceProperties() toMap 'sleeper.tags' is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InstanceProperties.setTags(Map)"})
  void testSetTags_whenHashMap_thenInstancePropertiesToMapSleeperTagsIsEmptyString() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    instanceProperties.setTags(new HashMap<>());

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("", toMapResult.get("sleeper.tags"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("", properties.get("sleeper.tags"));
    assertTrue(instanceProperties.getTags().isEmpty());
    assertTrue(instanceProperties.getTagsProperties().isEmpty());
  }

  /**
   * Test {@link InstanceProperties#get(InstanceProperty)} with {@code InstanceProperty}.
   * <p>
   * Method under test: {@link InstanceProperties#get(InstanceProperty)}
   */
  @Test
  @DisplayName("Test get(InstanceProperty) with 'InstanceProperty'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String InstanceProperties.get(InstanceProperty)"})
  void testGetWithInstanceProperty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    instanceProperties.set(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, "");

    // Act and Assert
    assertEquals("1000000", instanceProperties.get(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test {@link InstanceProperties#get(InstanceProperty)} with {@code InstanceProperty}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#get(InstanceProperty)}
   */
  @Test
  @DisplayName("Test get(InstanceProperty) with 'InstanceProperty'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String InstanceProperties.get(InstanceProperty)"})
  void testGetWithInstanceProperty_thenReturnNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertNull(instanceProperties.get(new DummyInstanceProperty("Property Name")));
  }

  /**
   * Test {@link InstanceProperties#get(InstanceProperty)} with {@code InstanceProperty}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return {@code 1000000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#get(InstanceProperty)}
   */
  @Test
  @DisplayName("Test get(InstanceProperty) with 'InstanceProperty'; when MAX_IN_MEMORY_BATCH_SIZE; then return '1000000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String InstanceProperties.get(InstanceProperty)"})
  void testGetWithInstanceProperty_whenMax_in_memory_batch_size_thenReturn1000000() {
    // Arrange, Act and Assert
    assertEquals("1000000", (new InstanceProperties()).get(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test {@link InstanceProperties#getTagsProperties()}.
   * <p>
   * Method under test: {@link InstanceProperties#getTagsProperties()}
   */
  @Test
  @DisplayName("Test getTagsProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Properties InstanceProperties.getTagsProperties()"})
  void testGetTagsProperties() {
    // Arrange, Act and Assert
    assertTrue((new InstanceProperties()).getTagsProperties().isEmpty());
  }

  /**
   * Test {@link InstanceProperties#getConfigBucketFromInstanceId(String)}.
   * <p>
   * Method under test: {@link InstanceProperties#getConfigBucketFromInstanceId(String)}
   */
  @Test
  @DisplayName("Test getConfigBucketFromInstanceId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String InstanceProperties.getConfigBucketFromInstanceId(String)"})
  void testGetConfigBucketFromInstanceId() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-config", InstanceProperties.getConfigBucketFromInstanceId("42"));
  }

  /**
   * Test {@link InstanceProperties#csvTagsToMap(String)}.
   * <ul>
   *   <li>When {@code ,}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#csvTagsToMap(String)}
   */
  @Test
  @DisplayName("Test csvTagsToMap(String); when ','; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map InstanceProperties.csvTagsToMap(String)"})
  void testCsvTagsToMap_whenComma_thenReturnEmpty() {
    // Arrange and Act
    Map<String, String> actualCsvTagsToMapResult = InstanceProperties.csvTagsToMap(",");

    // Assert
    assertTrue(actualCsvTagsToMapResult.isEmpty());
  }

  /**
   * Test {@link InstanceProperties#csvTagsToMap(String)}.
   * <ul>
   *   <li>When {@code ,Csv Tags}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#csvTagsToMap(String)}
   */
  @Test
  @DisplayName("Test csvTagsToMap(String); when ',Csv Tags'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map InstanceProperties.csvTagsToMap(String)"})
  void testCsvTagsToMap_whenCsvTags_thenReturnSizeIsOne() {
    // Arrange and Act
    Map<String, String> actualCsvTagsToMapResult = InstanceProperties.csvTagsToMap(",Csv Tags");

    // Assert
    assertEquals(1, actualCsvTagsToMapResult.size());
    assertEquals("Csv Tags", actualCsvTagsToMapResult.get(""));
  }

  /**
   * Test {@link InstanceProperties#csvTagsToMap(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#csvTagsToMap(String)}
   */
  @Test
  @DisplayName("Test csvTagsToMap(String); when empty string; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map InstanceProperties.csvTagsToMap(String)"})
  void testCsvTagsToMap_whenEmptyString_thenReturnEmpty() {
    // Arrange and Act
    Map<String, String> actualCsvTagsToMapResult = InstanceProperties.csvTagsToMap("");

    // Assert
    assertTrue(actualCsvTagsToMapResult.isEmpty());
  }

  /**
   * Test {@link InstanceProperties#csvTagsToMap(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#csvTagsToMap(String)}
   */
  @Test
  @DisplayName("Test csvTagsToMap(String); when 'null'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map InstanceProperties.csvTagsToMap(String)"})
  void testCsvTagsToMap_whenNull_thenReturnEmpty() {
    // Arrange and Act
    Map<String, String> actualCsvTagsToMapResult = InstanceProperties.csvTagsToMap(null);

    // Assert
    assertTrue(actualCsvTagsToMapResult.isEmpty());
  }

  /**
   * Test {@link InstanceProperties#tagsToString(Map)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@code foo}.</li>
   *   <li>Then return {@code foo,foo,42,foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#tagsToString(Map)}
   */
  @Test
  @DisplayName("Test tagsToString(Map); given '42'; when HashMap() '42' is 'foo'; then return 'foo,foo,42,foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String InstanceProperties.tagsToString(Map)"})
  void testTagsToString_given42_whenHashMap42IsFoo_thenReturnFooFoo42Foo() {
    // Arrange
    HashMap<String, String> tags = new HashMap<>();
    tags.put("42", "foo");
    tags.put("foo", "foo");

    // Act and Assert
    assertEquals("foo,foo,42,foo", InstanceProperties.tagsToString(tags));
  }

  /**
   * Test {@link InstanceProperties#tagsToString(Map)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code foo}.</li>
   *   <li>Then return {@code foo,foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#tagsToString(Map)}
   */
  @Test
  @DisplayName("Test tagsToString(Map); given 'foo'; when HashMap() 'foo' is 'foo'; then return 'foo,foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String InstanceProperties.tagsToString(Map)"})
  void testTagsToString_givenFoo_whenHashMapFooIsFoo_thenReturnFooFoo() {
    // Arrange
    HashMap<String, String> tags = new HashMap<>();
    tags.put("foo", "foo");

    // Act and Assert
    assertEquals("foo,foo", InstanceProperties.tagsToString(tags));
  }

  /**
   * Test {@link InstanceProperties#tagsToString(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstanceProperties#tagsToString(Map)}
   */
  @Test
  @DisplayName("Test tagsToString(Map); when HashMap(); then return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String InstanceProperties.tagsToString(Map)"})
  void testTagsToString_whenHashMap_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", InstanceProperties.tagsToString(new HashMap<>()));
  }
}
