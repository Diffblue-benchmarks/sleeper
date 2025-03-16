package sleeper.core.properties.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperProperties;
import sleeper.core.properties.SleeperProperty;
import sleeper.core.properties.instance.ArrayListIngestProperty;
import sleeper.core.properties.validation.EmrInstanceTypeConfig.Builder;

class EmrInstanceTypeConfigDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#architecture(EmrInstanceArchitecture)}
   *   <li>{@link Builder#instanceType(String)}
   *   <li>{@link Builder#weightedCapacity(Integer)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.architecture(EmrInstanceArchitecture)", "EmrInstanceTypeConfig Builder.build()",
      "Builder Builder.instanceType(String)", "Builder Builder.weightedCapacity(Integer)"})
  void testBuilderBuild() {
    // Arrange and Act
    EmrInstanceTypeConfig actualBuildResult = EmrInstanceTypeConfig.builder()
        .architecture(EmrInstanceArchitecture.X86_64)
        .instanceType("Instance Type")
        .weightedCapacity(3)
        .build();

    // Assert
    assertEquals("Instance Type", actualBuildResult.getInstanceType());
    assertEquals(3, actualBuildResult.getWeightedCapacity().intValue());
    assertEquals(EmrInstanceArchitecture.X86_64, actualBuildResult.getArchitecture());
  }

  /**
   * Test {@link EmrInstanceTypeConfig#readInstanceTypes(SleeperProperties, SleeperProperty, SleeperProperty, SleeperProperty)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#readInstanceTypes(SleeperProperties, SleeperProperty, SleeperProperty, SleeperProperty)}
   */
  @Test
  @DisplayName("Test readInstanceTypes(SleeperProperties, SleeperProperty, SleeperProperty, SleeperProperty); given ArrayList() stream; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Stream EmrInstanceTypeConfig.readInstanceTypes(SleeperProperties, SleeperProperty, SleeperProperty, SleeperProperty)"})
  void testReadInstanceTypes_givenArrayListStream_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    SleeperProperties<SleeperProperty> properties = mock(SleeperProperties.class);

    ArrayList<EmrInstanceArchitecture> emrInstanceArchitectureList = new ArrayList<>();
    Stream<EmrInstanceArchitecture> streamResult = emrInstanceArchitectureList.stream();
    when(properties.streamEnumList(Mockito.<SleeperProperty>any(), Mockito.<Class<EmrInstanceArchitecture>>any()))
        .thenReturn(streamResult);

    // Act
    Stream<EmrInstanceTypeConfig> actualReadInstanceTypesResult = EmrInstanceTypeConfig.readInstanceTypes(properties,
        ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE,
        ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    // Assert
    verify(properties).streamEnumList(isA(SleeperProperty.class), isA(Class.class));
    assertTrue(actualReadInstanceTypesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link EmrInstanceTypeConfig#readInstanceTypesProperty(List, EmrInstanceArchitecture)}.
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#readInstanceTypesProperty(List, EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test readInstanceTypesProperty(List, EmrInstanceArchitecture)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream EmrInstanceTypeConfig.readInstanceTypesProperty(List, EmrInstanceArchitecture)"})
  void testReadInstanceTypesProperty() {
    // Arrange
    ArrayList<String> instanceTypeEntries = new ArrayList<>();
    instanceTypeEntries.add("Instance Type Entries");

    // Act
    Stream<EmrInstanceTypeConfig> actualReadInstanceTypesPropertyResult = EmrInstanceTypeConfig
        .readInstanceTypesProperty(instanceTypeEntries, EmrInstanceArchitecture.X86_64);

    // Assert
    List<EmrInstanceTypeConfig> collectResult = actualReadInstanceTypesPropertyResult.limit(5)
        .collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    EmrInstanceTypeConfig getResult = collectResult.get(0);
    assertEquals("Instance Type Entries", getResult.getInstanceType());
    assertNull(getResult.getWeightedCapacity());
    assertEquals(EmrInstanceArchitecture.X86_64, getResult.getArchitecture());
  }

  /**
   * Test {@link EmrInstanceTypeConfig#readInstanceTypesProperty(List, EmrInstanceArchitecture)}.
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#readInstanceTypesProperty(List, EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test readInstanceTypesProperty(List, EmrInstanceArchitecture)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream EmrInstanceTypeConfig.readInstanceTypesProperty(List, EmrInstanceArchitecture)"})
  void testReadInstanceTypesProperty2() {
    // Arrange
    ArrayList<String> instanceTypeEntries = new ArrayList<>();
    instanceTypeEntries.add("");
    instanceTypeEntries.add("42");
    instanceTypeEntries.add("42");

    // Act
    Stream<EmrInstanceTypeConfig> actualReadInstanceTypesPropertyResult = EmrInstanceTypeConfig
        .readInstanceTypesProperty(instanceTypeEntries, EmrInstanceArchitecture.X86_64);

    // Assert
    List<EmrInstanceTypeConfig> collectResult = actualReadInstanceTypesPropertyResult.limit(5)
        .collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    EmrInstanceTypeConfig getResult = collectResult.get(0);
    assertEquals("", getResult.getInstanceType());
    assertEquals(42, getResult.getWeightedCapacity().intValue());
    assertEquals(EmrInstanceArchitecture.X86_64, getResult.getArchitecture());
  }

  /**
   * Test {@link EmrInstanceTypeConfig#readInstanceTypesProperty(List, EmrInstanceArchitecture)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#readInstanceTypesProperty(List, EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test readInstanceTypesProperty(List, EmrInstanceArchitecture); given '42'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream EmrInstanceTypeConfig.readInstanceTypesProperty(List, EmrInstanceArchitecture)"})
  void testReadInstanceTypesProperty_given42_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<String> instanceTypeEntries = new ArrayList<>();
    instanceTypeEntries.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> EmrInstanceTypeConfig.readInstanceTypesProperty(instanceTypeEntries, EmrInstanceArchitecture.X86_64));
  }

  /**
   * Test {@link EmrInstanceTypeConfig#readInstanceTypesProperty(List, EmrInstanceArchitecture)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#readInstanceTypesProperty(List, EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test readInstanceTypesProperty(List, EmrInstanceArchitecture); given '42'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream EmrInstanceTypeConfig.readInstanceTypesProperty(List, EmrInstanceArchitecture)"})
  void testReadInstanceTypesProperty_given42_thenThrowIllegalArgumentException2() {
    // Arrange
    ArrayList<String> instanceTypeEntries = new ArrayList<>();
    instanceTypeEntries.add("42");
    instanceTypeEntries.add("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> EmrInstanceTypeConfig.readInstanceTypesProperty(instanceTypeEntries, EmrInstanceArchitecture.X86_64));
  }

  /**
   * Test {@link EmrInstanceTypeConfig#readInstanceTypesProperty(List, EmrInstanceArchitecture)}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#readInstanceTypesProperty(List, EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test readInstanceTypesProperty(List, EmrInstanceArchitecture); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream EmrInstanceTypeConfig.readInstanceTypesProperty(List, EmrInstanceArchitecture)"})
  void testReadInstanceTypesProperty_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<EmrInstanceTypeConfig> actualReadInstanceTypesPropertyResult = EmrInstanceTypeConfig
        .readInstanceTypesProperty(new ArrayList<>(), EmrInstanceArchitecture.X86_64);

    // Assert
    assertTrue(actualReadInstanceTypesPropertyResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}
   */
  @Test
  @DisplayName("Test isValidInstanceTypes(String); when '42'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.isValidInstanceTypes(String)"})
  void testIsValidInstanceTypes_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(EmrInstanceTypeConfig.isValidInstanceTypes("42"));
  }

  /**
   * Test {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}.
   * <ul>
   *   <li>When {@code ,42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}
   */
  @Test
  @DisplayName("Test isValidInstanceTypes(String); when ',42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.isValidInstanceTypes(String)"})
  void testIsValidInstanceTypes_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(EmrInstanceTypeConfig.isValidInstanceTypes(",42"));
  }

  /**
   * Test {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}.
   * <ul>
   *   <li>When {@code ,}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}
   */
  @Test
  @DisplayName("Test isValidInstanceTypes(String); when ','; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.isValidInstanceTypes(String)"})
  void testIsValidInstanceTypes_whenComma_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(EmrInstanceTypeConfig.isValidInstanceTypes(","));
  }

  /**
   * Test {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}
   */
  @Test
  @DisplayName("Test isValidInstanceTypes(String); when empty string; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.isValidInstanceTypes(String)"})
  void testIsValidInstanceTypes_whenEmptyString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(EmrInstanceTypeConfig.isValidInstanceTypes(""));
  }

  /**
   * Test {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}
   */
  @Test
  @DisplayName("Test isValidInstanceTypes(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.isValidInstanceTypes(String)"})
  void testIsValidInstanceTypes_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(EmrInstanceTypeConfig.isValidInstanceTypes(null));
  }

  /**
   * Test {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}.
   * <ul>
   *   <li>When {@code Value}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}
   */
  @Test
  @DisplayName("Test isValidInstanceTypes(String); when 'Value'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.isValidInstanceTypes(String)"})
  void testIsValidInstanceTypes_whenValue_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(EmrInstanceTypeConfig.isValidInstanceTypes("Value"));
  }

  /**
   * Test {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}.
   * <ul>
   *   <li>When {@code ,Value}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#isValidInstanceTypes(String)}
   */
  @Test
  @DisplayName("Test isValidInstanceTypes(String); when ',Value'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.isValidInstanceTypes(String)"})
  void testIsValidInstanceTypes_whenValue_thenReturnTrue2() {
    // Arrange, Act and Assert
    assertTrue(EmrInstanceTypeConfig.isValidInstanceTypes(",Value"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EmrInstanceTypeConfig#toString()}
   *   <li>{@link EmrInstanceTypeConfig#getArchitecture()}
   *   <li>{@link EmrInstanceTypeConfig#getInstanceType()}
   *   <li>{@link EmrInstanceTypeConfig#getWeightedCapacity()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"EmrInstanceArchitecture EmrInstanceTypeConfig.getArchitecture()",
      "String EmrInstanceTypeConfig.getInstanceType()", "Integer EmrInstanceTypeConfig.getWeightedCapacity()",
      "String EmrInstanceTypeConfig.toString()"})
  void testGettersAndSetters() {
    // Arrange
    EmrInstanceTypeConfig buildResult = EmrInstanceTypeConfig.builder()
        .architecture(EmrInstanceArchitecture.X86_64)
        .instanceType("Instance Type")
        .weightedCapacity(3)
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    EmrInstanceArchitecture actualArchitecture = buildResult.getArchitecture();
    String actualInstanceType = buildResult.getInstanceType();

    // Assert
    assertEquals("EmrInstanceTypeConfig{instanceType='Instance Type', weightedCapacity=3}", actualToStringResult);
    assertEquals("Instance Type", actualInstanceType);
    assertEquals(3, buildResult.getWeightedCapacity().intValue());
    assertEquals(EmrInstanceArchitecture.X86_64, actualArchitecture);
  }

  /**
   * Test {@link EmrInstanceTypeConfig#equals(Object)}, and {@link EmrInstanceTypeConfig#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EmrInstanceTypeConfig#equals(Object)}
   *   <li>{@link EmrInstanceTypeConfig#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.equals(Object)", "int EmrInstanceTypeConfig.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    EmrInstanceTypeConfig buildResult = EmrInstanceTypeConfig.builder()
        .architecture(EmrInstanceArchitecture.X86_64)
        .instanceType("Instance Type")
        .weightedCapacity(3)
        .build();
    EmrInstanceTypeConfig buildResult2 = EmrInstanceTypeConfig.builder()
        .architecture(EmrInstanceArchitecture.X86_64)
        .instanceType("Instance Type")
        .weightedCapacity(3)
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link EmrInstanceTypeConfig#equals(Object)}, and {@link EmrInstanceTypeConfig#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EmrInstanceTypeConfig#equals(Object)}
   *   <li>{@link EmrInstanceTypeConfig#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.equals(Object)", "int EmrInstanceTypeConfig.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    EmrInstanceTypeConfig buildResult = EmrInstanceTypeConfig.builder()
        .architecture(EmrInstanceArchitecture.X86_64)
        .instanceType("Instance Type")
        .weightedCapacity(3)
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link EmrInstanceTypeConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.equals(Object)", "int EmrInstanceTypeConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    EmrInstanceTypeConfig buildResult = EmrInstanceTypeConfig.builder()
        .architecture(EmrInstanceArchitecture.X86_64)
        .instanceType("42")
        .weightedCapacity(3)
        .build();
    EmrInstanceTypeConfig buildResult2 = EmrInstanceTypeConfig.builder()
        .architecture(EmrInstanceArchitecture.X86_64)
        .instanceType("Instance Type")
        .weightedCapacity(3)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link EmrInstanceTypeConfig#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.equals(Object)", "int EmrInstanceTypeConfig.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    EmrInstanceTypeConfig buildResult = EmrInstanceTypeConfig.builder()
        .architecture(EmrInstanceArchitecture.X86_64)
        .instanceType("Instance Type")
        .weightedCapacity(3)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link EmrInstanceTypeConfig#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceTypeConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EmrInstanceTypeConfig.equals(Object)", "int EmrInstanceTypeConfig.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    EmrInstanceTypeConfig buildResult = EmrInstanceTypeConfig.builder()
        .architecture(EmrInstanceArchitecture.X86_64)
        .instanceType("Instance Type")
        .weightedCapacity(3)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to EmrInstanceTypeConfig");
  }
}
