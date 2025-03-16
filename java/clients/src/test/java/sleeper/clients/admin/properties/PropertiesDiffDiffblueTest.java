package sleeper.clients.admin.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperProperties;
import sleeper.core.properties.SleeperProperty;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.testutils.DummyInstanceProperty;
import sleeper.core.properties.testutils.DummySleeperProperty;

class PropertiesDiffDiffblueTest {
  /**
   * Test {@link PropertiesDiff#PropertiesDiff(SleeperProperty, String, String)}.
   * <p>
   * Method under test: {@link PropertiesDiff#PropertiesDiff(SleeperProperty, String, String)}
   */
  @Test
  @DisplayName("Test new PropertiesDiff(SleeperProperty, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PropertiesDiff.<init>(SleeperProperty, String, String)"})
  void testNewPropertiesDiff() {
    // Arrange and Act
    PropertiesDiff actualPropertiesDiff = new PropertiesDiff(new DummySleeperProperty(), "Before", "After");

    // Assert
    List<PropertyDiff> changes = actualPropertiesDiff.getChanges();
    assertEquals(1, changes.size());
    PropertyDiff getResult = changes.get(0);
    assertEquals("After", getResult.getNewValue());
    assertEquals("Before", getResult.getOldValue());
    assertEquals("made.up", getResult.getPropertyName());
    assertTrue(actualPropertiesDiff.isChanged());
  }

  /**
   * Test {@link PropertiesDiff#PropertiesDiff(Map, Map)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@code foo}.</li>
   *   <li>Then return Changes size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#PropertiesDiff(Map, Map)}
   */
  @Test
  @DisplayName("Test new PropertiesDiff(Map, Map); given '42'; when HashMap() '42' is 'foo'; then return Changes size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PropertiesDiff.<init>(Map, Map)"})
  void testNewPropertiesDiff_given42_whenHashMap42IsFoo_thenReturnChangesSizeIsTwo() {
    // Arrange
    HashMap<String, String> before = new HashMap<>();
    before.put("42", "foo");
    before.put("foo", "foo");

    // Act and Assert
    List<PropertyDiff> changes = (new PropertiesDiff(before, new HashMap<>())).getChanges();
    assertEquals(2, changes.size());
    PropertyDiff getResult = changes.get(1);
    assertEquals("42", getResult.getPropertyName());
    assertEquals("foo", getResult.getOldValue());
    assertNull(getResult.getNewValue());
  }

  /**
   * Test {@link PropertiesDiff#PropertiesDiff(Map, Map)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code foo}.</li>
   *   <li>Then return Changes size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#PropertiesDiff(Map, Map)}
   */
  @Test
  @DisplayName("Test new PropertiesDiff(Map, Map); given 'foo'; when HashMap() 'foo' is 'foo'; then return Changes size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PropertiesDiff.<init>(Map, Map)"})
  void testNewPropertiesDiff_givenFoo_whenHashMapFooIsFoo_thenReturnChangesSizeIsOne() {
    // Arrange
    HashMap<String, String> before = new HashMap<>();
    before.put("foo", "foo");

    // Act
    PropertiesDiff actualPropertiesDiff = new PropertiesDiff(before, new HashMap<>());

    // Assert
    List<PropertyDiff> changes = actualPropertiesDiff.getChanges();
    assertEquals(1, changes.size());
    PropertyDiff getResult = changes.get(0);
    assertEquals("foo", getResult.getOldValue());
    assertEquals("foo", getResult.getPropertyName());
    assertNull(getResult.getNewValue());
    assertTrue(actualPropertiesDiff.isChanged());
  }

  /**
   * Test {@link PropertiesDiff#PropertiesDiff(Map, Map)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code foo}.</li>
   *   <li>Then return not Changed.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#PropertiesDiff(Map, Map)}
   */
  @Test
  @DisplayName("Test new PropertiesDiff(Map, Map); given 'foo'; when HashMap() 'foo' is 'foo'; then return not Changed")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PropertiesDiff.<init>(Map, Map)"})
  void testNewPropertiesDiff_givenFoo_whenHashMapFooIsFoo_thenReturnNotChanged() {
    // Arrange
    HashMap<String, String> before = new HashMap<>();
    before.put("foo", "foo");

    HashMap<String, String> after = new HashMap<>();
    after.put("foo", "foo");

    // Act
    PropertiesDiff actualPropertiesDiff = new PropertiesDiff(before, after);

    // Assert
    assertFalse(actualPropertiesDiff.isChanged());
    assertTrue(actualPropertiesDiff.getChanges().isEmpty());
  }

  /**
   * Test {@link PropertiesDiff#PropertiesDiff(SleeperProperties, SleeperProperties)}.
   * <ul>
   *   <li>Then return Changes size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#PropertiesDiff(SleeperProperties, SleeperProperties)}
   */
  @Test
  @DisplayName("Test new PropertiesDiff(SleeperProperties, SleeperProperties); then return Changes size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PropertiesDiff.<init>(SleeperProperties, SleeperProperties)"})
  void testNewPropertiesDiff_thenReturnChangesSizeIsOne() {
    // Arrange
    InstanceProperties before = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty("Property Name");
    before.addToListIfMissing(dummyInstanceProperty, new ArrayList<>());

    // Act
    PropertiesDiff actualPropertiesDiff = new PropertiesDiff(before, new InstanceProperties());

    // Assert
    List<PropertyDiff> changes = actualPropertiesDiff.getChanges();
    assertEquals(1, changes.size());
    PropertyDiff getResult = changes.get(0);
    assertEquals("", getResult.getOldValue());
    assertEquals("Property Name", getResult.getPropertyName());
    assertNull(getResult.getNewValue());
    assertTrue(actualPropertiesDiff.isChanged());
  }

  /**
   * Test {@link PropertiesDiff#PropertiesDiff(SleeperProperties, SleeperProperties)}.
   * <ul>
   *   <li>Then return Changes size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#PropertiesDiff(SleeperProperties, SleeperProperties)}
   */
  @Test
  @DisplayName("Test new PropertiesDiff(SleeperProperties, SleeperProperties); then return Changes size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PropertiesDiff.<init>(SleeperProperties, SleeperProperties)"})
  void testNewPropertiesDiff_thenReturnChangesSizeIsTwo() {
    // Arrange
    InstanceProperties before = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty("propertyName must not be null");
    before.addToListIfMissing(dummyInstanceProperty, new ArrayList<>());
    DummyInstanceProperty dummyInstanceProperty2 = new DummyInstanceProperty("Property Name");
    before.addToListIfMissing(dummyInstanceProperty2, new ArrayList<>());

    // Act and Assert
    List<PropertyDiff> changes = (new PropertiesDiff(before, new InstanceProperties())).getChanges();
    assertEquals(2, changes.size());
    PropertyDiff getResult = changes.get(1);
    assertEquals("", getResult.getOldValue());
    assertEquals("Property Name", getResult.getPropertyName());
    assertEquals("propertyName must not be null", changes.get(0).getPropertyName());
    assertNull(getResult.getNewValue());
  }

  /**
   * Test {@link PropertiesDiff#PropertiesDiff(Map, Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return not Changed.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#PropertiesDiff(Map, Map)}
   */
  @Test
  @DisplayName("Test new PropertiesDiff(Map, Map); when HashMap(); then return not Changed")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PropertiesDiff.<init>(Map, Map)"})
  void testNewPropertiesDiff_whenHashMap_thenReturnNotChanged() {
    // Arrange
    HashMap<String, String> before = new HashMap<>();

    // Act
    PropertiesDiff actualPropertiesDiff = new PropertiesDiff(before, new HashMap<>());

    // Assert
    assertFalse(actualPropertiesDiff.isChanged());
    assertTrue(actualPropertiesDiff.getChanges().isEmpty());
  }

  /**
   * Test {@link PropertiesDiff#PropertiesDiff(SleeperProperties, SleeperProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return not Changed.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#PropertiesDiff(SleeperProperties, SleeperProperties)}
   */
  @Test
  @DisplayName("Test new PropertiesDiff(SleeperProperties, SleeperProperties); when InstanceProperties(); then return not Changed")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PropertiesDiff.<init>(SleeperProperties, SleeperProperties)"})
  void testNewPropertiesDiff_whenInstanceProperties_thenReturnNotChanged() {
    // Arrange
    InstanceProperties before = new InstanceProperties();

    // Act
    PropertiesDiff actualPropertiesDiff = new PropertiesDiff(before, new InstanceProperties());

    // Assert
    assertFalse(actualPropertiesDiff.isChanged());
    assertTrue(actualPropertiesDiff.getChanges().isEmpty());
  }

  /**
   * Test {@link PropertiesDiff#noChanges()}.
   * <p>
   * Method under test: {@link PropertiesDiff#noChanges()}
   */
  @Test
  @DisplayName("Test noChanges()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PropertiesDiff PropertiesDiff.noChanges()"})
  void testNoChanges() {
    // Arrange and Act
    PropertiesDiff actualNoChangesResult = PropertiesDiff.noChanges();

    // Assert
    assertFalse(actualNoChangesResult.isChanged());
    assertTrue(actualNoChangesResult.getChanges().isEmpty());
  }

  /**
   * Test {@link PropertiesDiff#getChangedPropertiesDeployedByCDK(SleeperPropertyIndex)}.
   * <p>
   * Method under test: {@link PropertiesDiff#getChangedPropertiesDeployedByCDK(SleeperPropertyIndex)}
   */
  @Test
  @DisplayName("Test getChangedPropertiesDeployedByCDK(SleeperPropertyIndex)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertiesDiff.getChangedPropertiesDeployedByCDK(SleeperPropertyIndex)"})
  void testGetChangedPropertiesDeployedByCDK() {
    // Arrange
    PropertiesDiff noChangesResult = PropertiesDiff.noChanges();

    // Act and Assert
    assertTrue(noChangesResult.getChangedPropertiesDeployedByCDK(new SleeperPropertyIndex<>()).isEmpty());
  }

  /**
   * Test {@link PropertiesDiff#andThen(PropertiesDiff)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code 42} is {@code 42}.</li>
   *   <li>When noChanges.</li>
   *   <li>Then return Changes size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#andThen(PropertiesDiff)}
   */
  @Test
  @DisplayName("Test andThen(PropertiesDiff); given HashMap() '42' is '42'; when noChanges; then return Changes size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PropertiesDiff PropertiesDiff.andThen(PropertiesDiff)"})
  void testAndThen_givenHashMap42Is42_whenNoChanges_thenReturnChangesSizeIsTwo() {
    // Arrange
    HashMap<String, String> before = new HashMap<>();
    before.put("42", "42");
    before.put("foo", "foo");
    PropertiesDiff propertiesDiff = new PropertiesDiff(before, new HashMap<>());

    // Act and Assert
    List<PropertyDiff> changes = propertiesDiff.andThen(PropertiesDiff.noChanges()).getChanges();
    assertEquals(2, changes.size());
    PropertyDiff getResult = changes.get(1);
    assertEquals("42", getResult.getOldValue());
    assertEquals("42", getResult.getPropertyName());
    assertNull(getResult.getNewValue());
  }

  /**
   * Test {@link PropertiesDiff#andThen(PropertiesDiff)}.
   * <ul>
   *   <li>Given noChanges.</li>
   *   <li>When noChanges.</li>
   *   <li>Then return noChanges.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#andThen(PropertiesDiff)}
   */
  @Test
  @DisplayName("Test andThen(PropertiesDiff); given noChanges; when noChanges; then return noChanges")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PropertiesDiff PropertiesDiff.andThen(PropertiesDiff)"})
  void testAndThen_givenNoChanges_whenNoChanges_thenReturnNoChanges() {
    // Arrange
    PropertiesDiff noChangesResult = PropertiesDiff.noChanges();

    // Act and Assert
    assertEquals(noChangesResult, noChangesResult.andThen(PropertiesDiff.noChanges()));
  }

  /**
   * Test {@link PropertiesDiff#andThen(PropertiesDiff)}.
   * <ul>
   *   <li>Then return {@link PropertiesDiff#PropertiesDiff(Map, Map)} with before is {@link HashMap#HashMap()} and after is {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#andThen(PropertiesDiff)}
   */
  @Test
  @DisplayName("Test andThen(PropertiesDiff); then return PropertiesDiff(Map, Map) with before is HashMap() and after is HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PropertiesDiff PropertiesDiff.andThen(PropertiesDiff)"})
  void testAndThen_thenReturnPropertiesDiffWithBeforeIsHashMapAndAfterIsHashMap() {
    // Arrange
    HashMap<String, String> before = new HashMap<>();
    before.put("foo", "foo");
    PropertiesDiff propertiesDiff = new PropertiesDiff(before, new HashMap<>());

    // Act and Assert
    assertEquals(propertiesDiff, propertiesDiff.andThen(PropertiesDiff.noChanges()));
  }

  /**
   * Test {@link PropertiesDiff#getChanges()}.
   * <p>
   * Method under test: {@link PropertiesDiff#getChanges()}
   */
  @Test
  @DisplayName("Test getChanges()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PropertiesDiff.getChanges()"})
  void testGetChanges() {
    // Arrange, Act and Assert
    assertTrue(PropertiesDiff.noChanges().getChanges().isEmpty());
  }

  /**
   * Test {@link PropertiesDiff#getDiff(SleeperProperty)}.
   * <ul>
   *   <li>When {@link DummySleeperProperty} (default constructor).</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#getDiff(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getDiff(SleeperProperty); when DummySleeperProperty (default constructor); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional PropertiesDiff.getDiff(SleeperProperty)"})
  void testGetDiff_whenDummySleeperProperty_thenReturnNotPresent() {
    // Arrange
    PropertiesDiff noChangesResult = PropertiesDiff.noChanges();

    // Act and Assert
    assertFalse(noChangesResult.getDiff(new DummySleeperProperty()).isPresent());
  }

  /**
   * Test {@link PropertiesDiff#getValuesBefore(SleeperPropertyValues)}.
   * <ul>
   *   <li>Then return {@link DummySleeperProperty} (default constructor) is {@code Get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#getValuesBefore(SleeperPropertyValues)}
   */
  @Test
  @DisplayName("Test getValuesBefore(SleeperPropertyValues); then return DummySleeperProperty (default constructor) is 'Get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SleeperPropertyValues PropertiesDiff.getValuesBefore(SleeperPropertyValues)"})
  void testGetValuesBefore_thenReturnDummySleeperPropertyIsGet() {
    // Arrange
    PropertiesDiff noChangesResult = PropertiesDiff.noChanges();
    SleeperPropertyValues<SleeperProperty> valuesAfter = mock(SleeperPropertyValues.class);
    when(valuesAfter.get(Mockito.<SleeperProperty>any())).thenReturn("Get");

    // Act
    SleeperPropertyValues<SleeperProperty> actualValuesBefore = noChangesResult.getValuesBefore(valuesAfter);
    String actualGetResult = actualValuesBefore.get(new DummySleeperProperty());

    // Assert
    verify(valuesAfter).get(isA(SleeperProperty.class));
    assertEquals("Get", actualGetResult);
  }

  /**
   * Test {@link PropertiesDiff#isChanged()}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is {@code foo}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#isChanged()}
   */
  @Test
  @DisplayName("Test isChanged(); given HashMap() 'foo' is 'foo'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertiesDiff.isChanged()"})
  void testIsChanged_givenHashMapFooIsFoo_thenReturnTrue() {
    // Arrange
    HashMap<String, String> before = new HashMap<>();
    before.put("foo", "foo");

    // Act and Assert
    assertTrue((new PropertiesDiff(before, new HashMap<>())).isChanged());
  }

  /**
   * Test {@link PropertiesDiff#isChanged()}.
   * <ul>
   *   <li>Given noChanges.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#isChanged()}
   */
  @Test
  @DisplayName("Test isChanged(); given noChanges; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertiesDiff.isChanged()"})
  void testIsChanged_givenNoChanges_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(PropertiesDiff.noChanges().isChanged());
  }

  /**
   * Test {@link PropertiesDiff#equals(Object)}, and {@link PropertiesDiff#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PropertiesDiff#equals(Object)}
   *   <li>{@link PropertiesDiff#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertiesDiff.equals(Object)", "int PropertiesDiff.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    PropertiesDiff noChangesResult = PropertiesDiff.noChanges();
    PropertiesDiff noChangesResult2 = PropertiesDiff.noChanges();

    // Act and Assert
    assertEquals(noChangesResult, noChangesResult2);
    int expectedHashCodeResult = noChangesResult.hashCode();
    assertEquals(expectedHashCodeResult, noChangesResult2.hashCode());
  }

  /**
   * Test {@link PropertiesDiff#equals(Object)}, and {@link PropertiesDiff#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PropertiesDiff#equals(Object)}
   *   <li>{@link PropertiesDiff#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertiesDiff.equals(Object)", "int PropertiesDiff.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    PropertiesDiff noChangesResult = PropertiesDiff.noChanges();

    // Act and Assert
    assertEquals(noChangesResult, noChangesResult);
    int expectedHashCodeResult = noChangesResult.hashCode();
    assertEquals(expectedHashCodeResult, noChangesResult.hashCode());
  }

  /**
   * Test {@link PropertiesDiff#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertiesDiff.equals(Object)", "int PropertiesDiff.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(PropertiesDiff.noChanges(), null);
  }

  /**
   * Test {@link PropertiesDiff#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertiesDiff.equals(Object)", "int PropertiesDiff.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(PropertiesDiff.noChanges(), null);
  }

  /**
   * Test {@link PropertiesDiff#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PropertiesDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PropertiesDiff.equals(Object)", "int PropertiesDiff.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(PropertiesDiff.noChanges(), "Different type to PropertiesDiff");
  }
}
