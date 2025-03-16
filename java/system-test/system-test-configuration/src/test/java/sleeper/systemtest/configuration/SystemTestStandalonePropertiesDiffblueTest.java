package sleeper.systemtest.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class SystemTestStandalonePropertiesDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SystemTestStandaloneProperties#SystemTestStandaloneProperties()}
   *   <li>{@link SystemTestStandaloneProperties#getPropertiesIndex()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestStandaloneProperties.<init>()",
      "void SystemTestStandaloneProperties.<init>(Properties)",
      "SleeperPropertyIndex SystemTestStandaloneProperties.getPropertiesIndex()"})
  void testGettersAndSetters() {
    // Arrange and Act
    SleeperPropertyIndex<SystemTestProperty> actualPropertiesIndex = (new SystemTestStandaloneProperties())
        .getPropertiesIndex();

    // Assert
    List<SystemTestProperty> all = actualPropertiesIndex.getAll();
    assertEquals(27, all.size());
    SystemTestProperty getResult = all.get(0);
    assertTrue(getResult instanceof SystemTestPropertyImpl);
    SystemTestProperty getResult2 = all.get(1);
    assertTrue(getResult2 instanceof SystemTestPropertyImpl);
    SystemTestProperty getResult3 = all.get(2);
    assertTrue(getResult3 instanceof SystemTestPropertyImpl);
    SystemTestProperty getResult4 = all.get(24);
    assertTrue(getResult4 instanceof SystemTestPropertyImpl);
    SystemTestProperty getResult5 = all.get(25);
    assertTrue(getResult5 instanceof SystemTestPropertyImpl);
    SystemTestProperty getResult6 = all.get(26);
    assertTrue(getResult6 instanceof SystemTestPropertyImpl);
    List<SystemTestProperty> cdkDefined = actualPropertiesIndex.getCdkDefined();
    assertEquals(3, cdkDefined.size());
    assertTrue(cdkDefined.get(0) instanceof SystemTestPropertyImpl);
    assertTrue(cdkDefined.get(1) instanceof SystemTestPropertyImpl);
    assertTrue(cdkDefined.get(2) instanceof SystemTestPropertyImpl);
    List<SystemTestProperty> userDefined = actualPropertiesIndex.getUserDefined();
    assertEquals(24, userDefined.size());
    assertSame(getResult, userDefined.get(0));
    assertSame(getResult2, userDefined.get(1));
    assertSame(getResult3, userDefined.get(2));
    assertSame(getResult4, userDefined.get(21));
    assertSame(getResult5, userDefined.get(22));
    assertSame(getResult6, userDefined.get(23));
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When {@link Properties#Properties()}.</li>
   *   <li>Then return Properties is {@link Properties#Properties()}.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SystemTestStandaloneProperties#SystemTestStandaloneProperties(Properties)}
   *   <li>{@link SystemTestStandaloneProperties#getPropertiesIndex()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when Properties(); then return Properties is Properties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestStandaloneProperties.<init>()",
      "void SystemTestStandaloneProperties.<init>(Properties)",
      "SleeperPropertyIndex SystemTestStandaloneProperties.getPropertiesIndex()"})
  void testGettersAndSetters_whenProperties_thenReturnPropertiesIsProperties() {
    // Arrange
    Properties properties = new Properties();

    // Act
    SystemTestStandaloneProperties actualSystemTestStandaloneProperties = new SystemTestStandaloneProperties(
        properties);
    SleeperPropertyIndex<SystemTestProperty> actualPropertiesIndex = actualSystemTestStandaloneProperties
        .getPropertiesIndex();

    // Assert
    List<SystemTestProperty> all = actualPropertiesIndex.getAll();
    assertEquals(27, all.size());
    SystemTestProperty getResult = all.get(0);
    assertTrue(getResult instanceof SystemTestPropertyImpl);
    SystemTestProperty getResult2 = all.get(1);
    assertTrue(getResult2 instanceof SystemTestPropertyImpl);
    SystemTestProperty getResult3 = all.get(2);
    assertTrue(getResult3 instanceof SystemTestPropertyImpl);
    SystemTestProperty getResult4 = all.get(24);
    assertTrue(getResult4 instanceof SystemTestPropertyImpl);
    SystemTestProperty getResult5 = all.get(25);
    assertTrue(getResult5 instanceof SystemTestPropertyImpl);
    SystemTestProperty getResult6 = all.get(26);
    assertTrue(getResult6 instanceof SystemTestPropertyImpl);
    List<SystemTestProperty> cdkDefined = actualPropertiesIndex.getCdkDefined();
    assertEquals(3, cdkDefined.size());
    assertTrue(cdkDefined.get(0) instanceof SystemTestPropertyImpl);
    assertTrue(cdkDefined.get(1) instanceof SystemTestPropertyImpl);
    assertTrue(cdkDefined.get(2) instanceof SystemTestPropertyImpl);
    List<SystemTestProperty> userDefined = actualPropertiesIndex.getUserDefined();
    assertEquals(24, userDefined.size());
    assertSame(properties, actualSystemTestStandaloneProperties.getProperties());
    assertSame(getResult, userDefined.get(0));
    assertSame(getResult2, userDefined.get(1));
    assertSame(getResult3, userDefined.get(2));
    assertSame(getResult4, userDefined.get(21));
    assertSame(getResult5, userDefined.get(22));
    assertSame(getResult6, userDefined.get(23));
  }

  /**
   * Test {@link SystemTestStandaloneProperties#copyOf(SystemTestStandaloneProperties)}.
   * <ul>
   *   <li>Then return {@link SystemTestStandaloneProperties#SystemTestStandaloneProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestStandaloneProperties#copyOf(SystemTestStandaloneProperties)}
   */
  @Test
  @DisplayName("Test copyOf(SystemTestStandaloneProperties); then return SystemTestStandaloneProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SystemTestStandaloneProperties SystemTestStandaloneProperties.copyOf(SystemTestStandaloneProperties)"})
  void testCopyOf_thenReturnSystemTestStandaloneProperties() {
    // Arrange
    SystemTestStandaloneProperties properties = new SystemTestStandaloneProperties();

    // Act and Assert
    assertEquals(properties, SystemTestStandaloneProperties.copyOf(properties));
  }

  /**
   * Test {@link SystemTestStandaloneProperties#get(SystemTestProperty)} with {@code SystemTestProperty}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestStandaloneProperties#get(SystemTestProperty)}
   */
  @Test
  @DisplayName("Test get(SystemTestProperty) with 'SystemTestProperty'; given 'true'; then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SystemTestStandaloneProperties.get(SystemTestProperty)"})
  void testGetWithSystemTestProperty_givenTrue_thenReturn42() {
    // Arrange
    SystemTestStandaloneProperties systemTestStandaloneProperties = new SystemTestStandaloneProperties();
    SystemTestProperty property = mock(SystemTestProperty.class);
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.computeValue(Mockito.<String>any(), Mockito.<SleeperPropertyValues<InstanceProperty>>any()))
        .thenReturn("42");
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    String actualGetResult = systemTestStandaloneProperties.get(property);

    // Assert
    verify(property).getPropertyName();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(isNull(), isA(SleeperPropertyValues.class));
    assertEquals("42", actualGetResult);
  }

  /**
   * Test {@link SystemTestStandaloneProperties#get(SystemTestProperty)} with {@code SystemTestProperty}.
   * <ul>
   *   <li>When {@link SystemTestProperty#INGEST_MODE}.</li>
   *   <li>Then return {@code direct}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestStandaloneProperties#get(SystemTestProperty)}
   */
  @Test
  @DisplayName("Test get(SystemTestProperty) with 'SystemTestProperty'; when INGEST_MODE; then return 'direct'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SystemTestStandaloneProperties.get(SystemTestProperty)"})
  void testGetWithSystemTestProperty_whenIngest_mode_thenReturnDirect() {
    // Arrange, Act and Assert
    assertEquals("direct", (new SystemTestStandaloneProperties()).get(SystemTestProperty.INGEST_MODE));
  }

  /**
   * Test {@link SystemTestStandaloneProperties#toInstancePropertiesForCdkUtils()}.
   * <p>
   * Method under test: {@link SystemTestStandaloneProperties#toInstancePropertiesForCdkUtils()}
   */
  @Test
  @DisplayName("Test toInstancePropertiesForCdkUtils()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties SystemTestStandaloneProperties.toInstancePropertiesForCdkUtils()"})
  void testToInstancePropertiesForCdkUtils() {
    // Arrange and Act
    InstanceProperties actualToInstancePropertiesForCdkUtilsResult = (new SystemTestStandaloneProperties())
        .toInstancePropertiesForCdkUtils();

    // Assert
    Properties properties = actualToInstancePropertiesForCdkUtilsResult.getProperties();
    assertEquals(7, properties.size());
    Stream<Entry<String, String>> unknownProperties = actualToInstancePropertiesForCdkUtilsResult
        .getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
    assertTrue(actualToInstancePropertiesForCdkUtilsResult.getTags().isEmpty());
    assertTrue(properties.containsKey("sleeper.log.retention.days"));
    assertTrue(properties.containsKey("sleeper.logging.apache.level"));
    assertTrue(properties.containsKey("sleeper.logging.aws.level"));
    assertTrue(properties.containsKey("sleeper.logging.level"));
    assertTrue(properties.containsKey("sleeper.logging.parquet.level"));
    assertTrue(properties.containsKey("sleeper.logging.root.level"));
    assertTrue(actualToInstancePropertiesForCdkUtilsResult.getTagsProperties().isEmpty());
    assertEquals(properties, actualToInstancePropertiesForCdkUtilsResult.toMap());
  }

  /**
   * Test {@link SystemTestStandaloneProperties#buildSystemTestBucketName(String)}.
   * <p>
   * Method under test: {@link SystemTestStandaloneProperties#buildSystemTestBucketName(String)}
   */
  @Test
  @DisplayName("Test buildSystemTestBucketName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SystemTestStandaloneProperties.buildSystemTestBucketName(String)"})
  void testBuildSystemTestBucketName() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-system-test", SystemTestStandaloneProperties.buildSystemTestBucketName("42"));
  }
}
