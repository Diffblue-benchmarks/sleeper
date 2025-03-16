package sleeper.core.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.ArrayListIngestProperty;
import sleeper.core.properties.instance.AthenaProperty;
import sleeper.core.properties.instance.InstancePropertyGroup;
import sleeper.core.properties.table.TablePropertyGroup;

class PropertyGroupDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PropertyGroup#group(String)}
   *   <li>{@link PropertyGroup#getDescription()}
   *   <li>{@link PropertyGroup#getDetails()}
   *   <li>{@link PropertyGroup#getName()}
   *   <li>{@link PropertyGroup#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String PropertyGroup.getDescription()", "String PropertyGroup.getDetails()",
      "String PropertyGroup.getName()", "PropertyGroup.Builder PropertyGroup.group(String)",
      "String PropertyGroup.toString()"})
  void testGettersAndSetters() {
    // Arrange
    PropertyGroup propertyGroup = InstancePropertyGroup.ATHENA;

    // Act
    propertyGroup.group("Name");
    String actualDescription = propertyGroup.getDescription();
    String actualDetails = propertyGroup.getDetails();
    String actualName = propertyGroup.getName();

    // Assert
    assertEquals("Athena", actualName);
    assertEquals("The following properties relate to the integration with Athena.", actualDescription);
    assertEquals("The following properties relate to the integration with Athena.", propertyGroup.toString());
    assertNull(actualDetails);
  }

  /**
   * Test {@link PropertyGroup#equals(Object)}, and {@link PropertyGroup#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PropertyGroup#equals(Object)}
   *   <li>{@link PropertyGroup#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyGroup.equals(Object)", "int PropertyGroup.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    PropertyGroup propertyGroup = InstancePropertyGroup.ATHENA;
    PropertyGroup propertyGroup2 = InstancePropertyGroup.ATHENA;

    // Act and Assert
    assertEquals(propertyGroup, propertyGroup2);
    int expectedHashCodeResult = propertyGroup.hashCode();
    assertEquals(expectedHashCodeResult, propertyGroup2.hashCode());
  }

  /**
   * Test {@link PropertyGroup#equals(Object)}, and {@link PropertyGroup#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PropertyGroup#equals(Object)}
   *   <li>{@link PropertyGroup#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyGroup.equals(Object)", "int PropertyGroup.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    PropertyGroup propertyGroup = InstancePropertyGroup.ATHENA;

    // Act and Assert
    assertEquals(propertyGroup, propertyGroup);
    int expectedHashCodeResult = propertyGroup.hashCode();
    assertEquals(expectedHashCodeResult, propertyGroup.hashCode());
  }

  /**
   * Test {@link PropertyGroup#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyGroup.equals(Object)", "int PropertyGroup.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(InstancePropertyGroup.BULK_IMPORT, InstancePropertyGroup.ATHENA);
  }

  /**
   * Test {@link PropertyGroup#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyGroup.equals(Object)", "int PropertyGroup.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(InstancePropertyGroup.ATHENA, null);
  }

  /**
   * Test {@link PropertyGroup#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertyGroup.equals(Object)", "int PropertyGroup.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(InstancePropertyGroup.ATHENA, "Different type to PropertyGroup");
  }

  /**
   * Test {@link PropertyGroup#sortPropertiesByGroup(List, List)}.
   * <ul>
   *   <li>Given {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>Then return {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#sortPropertiesByGroup(List, List)}
   */
  @Test
  @DisplayName("Test sortPropertiesByGroup(List, List); given ATHENA; when ArrayList() add ATHENA; then return ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertyGroup.sortPropertiesByGroup(List, List)"})
  void testSortPropertiesByGroup_givenAthena_whenArrayListAddAthena_thenReturnArrayList() {
    // Arrange
    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(InstancePropertyGroup.ATHENA);

    // Act
    List<SleeperProperty> actualSortPropertiesByGroupResult = PropertyGroup.sortPropertiesByGroup(properties, groups);

    // Assert
    assertEquals(properties, actualSortPropertiesByGroupResult);
  }

  /**
   * Test {@link PropertyGroup#sortPropertiesByGroup(List, List)}.
   * <ul>
   *   <li>Given {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>Then return {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#sortPropertiesByGroup(List, List)}
   */
  @Test
  @DisplayName("Test sortPropertiesByGroup(List, List); given ATHENA; when ArrayList() add ATHENA; then return ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertyGroup.sortPropertiesByGroup(List, List)"})
  void testSortPropertiesByGroup_givenAthena_whenArrayListAddAthena_thenReturnArrayList2() {
    // Arrange
    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(InstancePropertyGroup.ATHENA);
    groups.add(InstancePropertyGroup.ATHENA);

    // Act
    List<SleeperProperty> actualSortPropertiesByGroupResult = PropertyGroup.sortPropertiesByGroup(properties, groups);

    // Assert
    assertEquals(properties, actualSortPropertiesByGroupResult);
  }

  /**
   * Test {@link PropertyGroup#sortPropertiesByGroup(List, List)}.
   * <ul>
   *   <li>Given {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#sortPropertiesByGroup(List, List)}
   */
  @Test
  @DisplayName("Test sortPropertiesByGroup(List, List); given ATHENA; when ArrayList() add ATHENA; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertyGroup.sortPropertiesByGroup(List, List)"})
  void testSortPropertiesByGroup_givenAthena_whenArrayListAddAthena_thenReturnEmpty() {
    // Arrange
    ArrayList<SleeperProperty> properties = new ArrayList<>();

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(InstancePropertyGroup.ATHENA);

    // Act
    List<SleeperProperty> actualSortPropertiesByGroupResult = PropertyGroup.sortPropertiesByGroup(properties, groups);

    // Assert
    assertTrue(actualSortPropertiesByGroupResult.isEmpty());
  }

  /**
   * Test {@link PropertyGroup#sortPropertiesByGroup(List, List)}.
   * <ul>
   *   <li>Given {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#sortPropertiesByGroup(List, List)}
   */
  @Test
  @DisplayName("Test sortPropertiesByGroup(List, List); given ATHENA; when ArrayList() add ATHENA; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertyGroup.sortPropertiesByGroup(List, List)"})
  void testSortPropertiesByGroup_givenAthena_whenArrayListAddAthena_thenReturnEmpty2() {
    // Arrange
    ArrayList<SleeperProperty> properties = new ArrayList<>();

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(InstancePropertyGroup.ATHENA);
    groups.add(InstancePropertyGroup.ATHENA);

    // Act
    List<SleeperProperty> actualSortPropertiesByGroupResult = PropertyGroup.sortPropertiesByGroup(properties, groups);

    // Assert
    assertTrue(actualSortPropertiesByGroupResult.isEmpty());
  }

  /**
   * Test {@link PropertyGroup#sortPropertiesByGroup(List, List)}.
   * <ul>
   *   <li>Given {@link TablePropertyGroup#INGEST}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link TablePropertyGroup#INGEST}.</li>
   *   <li>Then return {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#sortPropertiesByGroup(List, List)}
   */
  @Test
  @DisplayName("Test sortPropertiesByGroup(List, List); given INGEST; when ArrayList() add INGEST; then return ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertyGroup.sortPropertiesByGroup(List, List)"})
  void testSortPropertiesByGroup_givenIngest_whenArrayListAddIngest_thenReturnArrayList() {
    // Arrange
    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(TablePropertyGroup.INGEST);

    // Act
    List<SleeperProperty> actualSortPropertiesByGroupResult = PropertyGroup.sortPropertiesByGroup(properties, groups);

    // Assert
    assertEquals(properties, actualSortPropertiesByGroupResult);
  }

  /**
   * Test {@link PropertyGroup#sortPropertiesByGroup(List, List)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code null}.</li>
   *   <li>Then return {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#sortPropertiesByGroup(List, List)}
   */
  @Test
  @DisplayName("Test sortPropertiesByGroup(List, List); given 'null'; when ArrayList() add 'null'; then return ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertyGroup.sortPropertiesByGroup(List, List)"})
  void testSortPropertiesByGroup_givenNull_whenArrayListAddNull_thenReturnArrayList() {
    // Arrange
    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(null);

    // Act
    List<SleeperProperty> actualSortPropertiesByGroupResult = PropertyGroup.sortPropertiesByGroup(properties, groups);

    // Assert
    assertEquals(properties, actualSortPropertiesByGroupResult);
  }

  /**
   * Test {@link PropertyGroup#sortPropertiesByGroup(List, List)}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#sortPropertiesByGroup(List, List)}
   */
  @Test
  @DisplayName("Test sortPropertiesByGroup(List, List); then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertyGroup.sortPropertiesByGroup(List, List)"})
  void testSortPropertiesByGroup_thenReturnSizeIsTwo() {
    // Arrange
    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(AthenaProperty.ATHENA_COMPOSITE_HANDLER_CLASSES);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(InstancePropertyGroup.ATHENA);

    // Act
    List<SleeperProperty> actualSortPropertiesByGroupResult = PropertyGroup.sortPropertiesByGroup(properties, groups);

    // Assert
    assertEquals(2, actualSortPropertiesByGroupResult.size());
    SleeperProperty getResult = actualSortPropertiesByGroupResult.get(1);
    assertFalse(getResult.isIncludedInBasicTemplate());
    assertFalse(getResult.isSetByCdk());
    assertTrue(getResult.isEditable());
    assertTrue(getResult.isIgnoreEmptyValue());
    assertTrue(getResult.isIncludedInTemplate());
    assertTrue(getResult.isUserDefined());
  }

  /**
   * Test {@link PropertyGroup#sortPropertiesByGroup(List, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#sortPropertiesByGroup(List, List)}
   */
  @Test
  @DisplayName("Test sortPropertiesByGroup(List, List); when ArrayList(); then return ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertyGroup.sortPropertiesByGroup(List, List)"})
  void testSortPropertiesByGroup_whenArrayList_thenReturnArrayList() {
    // Arrange
    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    // Act
    List<SleeperProperty> actualSortPropertiesByGroupResult = PropertyGroup.sortPropertiesByGroup(properties,
        new ArrayList<>());

    // Assert
    assertEquals(properties, actualSortPropertiesByGroupResult);
  }

  /**
   * Test {@link PropertyGroup#sortPropertiesByGroup(List, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#sortPropertiesByGroup(List, List)}
   */
  @Test
  @DisplayName("Test sortPropertiesByGroup(List, List); when ArrayList(); then return ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertyGroup.sortPropertiesByGroup(List, List)"})
  void testSortPropertiesByGroup_whenArrayList_thenReturnArrayList2() {
    // Arrange
    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    // Act
    List<SleeperProperty> actualSortPropertiesByGroupResult = PropertyGroup.sortPropertiesByGroup(properties,
        new ArrayList<>());

    // Assert
    assertEquals(properties, actualSortPropertiesByGroupResult);
  }

  /**
   * Test {@link PropertyGroup#sortPropertiesByGroup(List, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertyGroup#sortPropertiesByGroup(List, List)}
   */
  @Test
  @DisplayName("Test sortPropertiesByGroup(List, List); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertyGroup.sortPropertiesByGroup(List, List)"})
  void testSortPropertiesByGroup_whenArrayList_thenReturnEmpty() {
    // Arrange
    ArrayList<SleeperProperty> properties = new ArrayList<>();

    // Act
    List<SleeperProperty> actualSortPropertiesByGroupResult = PropertyGroup.sortPropertiesByGroup(properties,
        new ArrayList<>());

    // Assert
    assertTrue(actualSortPropertiesByGroupResult.isEmpty());
  }
}
