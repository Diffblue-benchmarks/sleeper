package sleeper.core.properties.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.schema.Schema;
import sleeper.core.table.TableStatus;

class TablePropertiesDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableProperties#TableProperties(InstanceProperties)}
   *   <li>{@link TableProperties#getPropertiesIndex()}
   *   <li>{@link TableProperties#getSchema()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TableProperties.<init>(InstanceProperties)",
      "SleeperPropertyIndex TableProperties.getPropertiesIndex()", "Schema TableProperties.getSchema()"})
  void testGettersAndSetters() {
    // Arrange and Act
    TableProperties actualTableProperties = new TableProperties(new InstanceProperties());
    SleeperPropertyIndex<TableProperty> actualPropertiesIndex = actualTableProperties.getPropertiesIndex();
    Schema actualSchema = actualTableProperties.getSchema();

    // Assert
    List<TableProperty> all = actualPropertiesIndex.getAll();
    assertEquals(74, all.size());
    TableProperty getResult = all.get(0);
    assertTrue(getResult instanceof TablePropertyImpl);
    assertTrue(all.get(1) instanceof TablePropertyImpl);
    TableProperty getResult2 = all.get(2);
    assertTrue(getResult2 instanceof TablePropertyImpl);
    TableProperty getResult3 = all.get(71);
    assertTrue(getResult3 instanceof TablePropertyImpl);
    TableProperty getResult4 = all.get(72);
    assertTrue(getResult4 instanceof TablePropertyImpl);
    TableProperty getResult5 = all.get(73);
    assertTrue(getResult5 instanceof TablePropertyImpl);
    List<TableProperty> userDefined = actualPropertiesIndex.getUserDefined();
    assertEquals(73, userDefined.size());
    assertTrue(userDefined.get(2) instanceof TablePropertyImpl);
    assertNull(actualSchema);
    assertTrue(actualPropertiesIndex.getCdkDefined().isEmpty());
    assertSame(getResult, userDefined.get(0));
    assertSame(getResult2, userDefined.get(1));
    assertSame(getResult3, userDefined.get(70));
    assertSame(getResult4, userDefined.get(71));
    assertSame(getResult5, userDefined.get(72));
  }

  /**
   * Test {@link TableProperties#TableProperties(InstanceProperties, Properties)}.
   * <p>
   * Method under test: {@link TableProperties#TableProperties(InstanceProperties, Properties)}
   */
  @Test
  @DisplayName("Test new TableProperties(InstanceProperties, Properties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TableProperties.<init>(InstanceProperties, Properties)"})
  void testNewTableProperties() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    Properties properties = new Properties();

    // Act
    TableProperties actualTableProperties = new TableProperties(instanceProperties, properties);

    // Assert
    TableStatus status = actualTableProperties.getStatus();
    assertNull(status.getTableName());
    assertNull(status.getTableUniqueId());
    assertNull(actualTableProperties.getSchema());
    SleeperPropertyIndex<TableProperty> propertiesIndex = actualTableProperties.getPropertiesIndex();
    assertEquals(73, propertiesIndex.getUserDefined().size());
    assertEquals(74, propertiesIndex.getAll().size());
    Stream<Entry<String, String>> unknownProperties = actualTableProperties.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
    assertTrue(propertiesIndex.getCdkDefined().isEmpty());
    assertTrue(actualTableProperties.toMap().isEmpty());
    assertTrue(status.isOnline());
    assertSame(properties, actualTableProperties.getProperties());
  }
}
