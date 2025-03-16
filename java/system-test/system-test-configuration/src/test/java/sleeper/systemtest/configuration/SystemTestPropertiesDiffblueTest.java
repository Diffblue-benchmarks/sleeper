package sleeper.systemtest.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.instance.InstanceProperty;

class SystemTestPropertiesDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SystemTestProperties#SystemTestProperties()}
   *   <li>{@link SystemTestProperties#getPropertiesIndex()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestProperties.<init>()",
      "SleeperPropertyIndex SystemTestProperties.getPropertiesIndex()"})
  void testGettersAndSetters() {
    // Arrange and Act
    SystemTestProperties actualSystemTestProperties = new SystemTestProperties();
    SleeperPropertyIndex<InstanceProperty> actualPropertiesIndex = actualSystemTestProperties.getPropertiesIndex();

    // Assert
    assertTrue(actualSystemTestProperties.getTags().isEmpty());
    assertSame(actualSystemTestProperties.PROPERTY_INDEX, actualPropertiesIndex);
  }

  /**
   * Test {@link SystemTestProperties#SystemTestProperties(Properties)}.
   * <p>
   * Method under test: {@link SystemTestProperties#SystemTestProperties(Properties)}
   */
  @Test
  @DisplayName("Test new SystemTestProperties(Properties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestProperties.<init>(Properties)"})
  void testNewSystemTestProperties() {
    // Arrange
    Properties properties = new Properties();

    // Act
    SystemTestProperties actualSystemTestProperties = new SystemTestProperties(properties);

    // Assert
    Stream<Entry<String, String>> unknownProperties = actualSystemTestProperties.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
    assertTrue(actualSystemTestProperties.toMap().isEmpty());
    assertTrue(actualSystemTestProperties.getTags().isEmpty());
    assertSame(properties, actualSystemTestProperties.getProperties());
    SleeperPropertyIndex<InstanceProperty> expectedPropertiesIndex = actualSystemTestProperties.PROPERTY_INDEX;
    assertSame(expectedPropertiesIndex, actualSystemTestProperties.getPropertiesIndex());
  }

  /**
   * Test {@link SystemTestProperties#testPropertiesOnly()}.
   * <ul>
   *   <li>Then {@link SystemTestProperty#INGEST_MODE} {@link SystemTestPropertyImpl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestProperties#testPropertiesOnly()}
   */
  @Test
  @DisplayName("Test testPropertiesOnly(); then INGEST_MODE SystemTestPropertyImpl")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestPropertyValues SystemTestProperties.testPropertiesOnly()"})
  void testTestPropertiesOnly_thenIngest_modeSystemTestPropertyImpl() {
    // Arrange
    SystemTestProperty systemTestProperty = SystemTestProperty.INGEST_MODE;

    // Act and Assert
    assertTrue(systemTestProperty instanceof SystemTestPropertyImpl);
    assertEquals("direct", (new SystemTestProperties()).testPropertiesOnly().get(systemTestProperty));
  }
}
