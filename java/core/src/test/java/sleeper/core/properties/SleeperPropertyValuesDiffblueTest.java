package sleeper.core.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.awt.Component;
import java.awt.Component.BaselineResizeBehavior;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.ArrayListIngestProperty;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.testutils.DummyInstanceProperty;

class SleeperPropertyValuesDiffblueTest {
  /**
   * Test {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.
   * <ul>
   *   <li>Given {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getBoolean(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getBoolean(SleeperProperty); given InstanceProperties(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValues.getBoolean(SleeperProperty)"})
  void testGetBoolean_givenInstanceProperties_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new InstanceProperties()).getBoolean(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.
   * <ul>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getBoolean(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getBoolean(SleeperProperty); then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperPropertyValues.getBoolean(SleeperProperty)"})
  void testGetBoolean_thenCallsGetBoolean() {
    // Arrange
    SleeperPropertyValues<InstanceProperty> sleeperPropertyValues = mock(SleeperPropertyValues.class);
    when(sleeperPropertyValues.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);

    // Act
    sleeperPropertyValues.getBoolean(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    // Assert
    verify(sleeperPropertyValues).getBoolean(isA(InstanceProperty.class));
  }

  /**
   * Test {@link SleeperPropertyValues#getInt(SleeperProperty)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return {@code 1000000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getInt(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getInt(SleeperProperty); when MAX_IN_MEMORY_BATCH_SIZE; then return '1000000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int SleeperPropertyValues.getInt(SleeperProperty)"})
  void testGetInt_whenMax_in_memory_batch_size_thenReturn1000000() {
    // Arrange, Act and Assert
    assertEquals(1000000, (new InstanceProperties()).getInt(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test {@link SleeperPropertyValues#getIntOrNull(SleeperProperty)}.
   * <ul>
   *   <li>When {@link DummyInstanceProperty#DummyInstanceProperty(String)} with {@code Property Name}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getIntOrNull(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getIntOrNull(SleeperProperty); when DummyInstanceProperty(String) with 'Property Name'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Integer SleeperPropertyValues.getIntOrNull(SleeperProperty)"})
  void testGetIntOrNull_whenDummyInstancePropertyWithPropertyName_thenReturnNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertNull(instanceProperties.getIntOrNull(new DummyInstanceProperty("Property Name")));
  }

  /**
   * Test {@link SleeperPropertyValues#getIntOrNull(SleeperProperty)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return intValue is {@code 1000000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getIntOrNull(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getIntOrNull(SleeperProperty); when MAX_IN_MEMORY_BATCH_SIZE; then return intValue is '1000000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Integer SleeperPropertyValues.getIntOrNull(SleeperProperty)"})
  void testGetIntOrNull_whenMax_in_memory_batch_size_thenReturnIntValueIs1000000() {
    // Arrange, Act and Assert
    assertEquals(1000000,
        (new InstanceProperties()).getIntOrNull(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE).intValue());
  }

  /**
   * Test {@link SleeperPropertyValues#getLong(SleeperProperty)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return {@code 1000000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getLong(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getLong(SleeperProperty); when MAX_IN_MEMORY_BATCH_SIZE; then return '1000000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long SleeperPropertyValues.getLong(SleeperProperty)"})
  void testGetLong_whenMax_in_memory_batch_size_thenReturn1000000() {
    // Arrange, Act and Assert
    assertEquals(1000000L, (new InstanceProperties()).getLong(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test {@link SleeperPropertyValues#getLongOrNull(SleeperProperty)}.
   * <ul>
   *   <li>When {@link DummyInstanceProperty#DummyInstanceProperty(String)} with {@code Property Name}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getLongOrNull(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getLongOrNull(SleeperProperty); when DummyInstanceProperty(String) with 'Property Name'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Long SleeperPropertyValues.getLongOrNull(SleeperProperty)"})
  void testGetLongOrNull_whenDummyInstancePropertyWithPropertyName_thenReturnNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertNull(instanceProperties.getLongOrNull(new DummyInstanceProperty("Property Name")));
  }

  /**
   * Test {@link SleeperPropertyValues#getLongOrNull(SleeperProperty)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return longValue is {@code 1000000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getLongOrNull(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getLongOrNull(SleeperProperty); when MAX_IN_MEMORY_BATCH_SIZE; then return longValue is '1000000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Long SleeperPropertyValues.getLongOrNull(SleeperProperty)"})
  void testGetLongOrNull_whenMax_in_memory_batch_size_thenReturnLongValueIs1000000() {
    // Arrange, Act and Assert
    assertEquals(1000000L,
        (new InstanceProperties()).getLongOrNull(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE).longValue());
  }

  /**
   * Test {@link SleeperPropertyValues#getDouble(SleeperProperty)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return {@code 1000000.0}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getDouble(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getDouble(SleeperProperty); when MAX_IN_MEMORY_BATCH_SIZE; then return '1000000.0'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double SleeperPropertyValues.getDouble(SleeperProperty)"})
  void testGetDouble_whenMax_in_memory_batch_size_thenReturn10000000() {
    // Arrange, Act and Assert
    assertEquals(1000000.0d, (new InstanceProperties()).getDouble(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test {@link SleeperPropertyValues#getBytes(SleeperProperty)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return {@code 1000000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getBytes(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getBytes(SleeperProperty); when MAX_IN_MEMORY_BATCH_SIZE; then return '1000000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long SleeperPropertyValues.getBytes(SleeperProperty)"})
  void testGetBytes_whenMax_in_memory_batch_size_thenReturn1000000() {
    // Arrange, Act and Assert
    assertEquals(1000000L, (new InstanceProperties()).getBytes(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test {@link SleeperPropertyValues#getList(SleeperProperty)}.
   * <ul>
   *   <li>When {@link DummyInstanceProperty#DummyInstanceProperty(String)} with propertyName is {@code ,}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getList(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getList(SleeperProperty); when DummyInstanceProperty(String) with propertyName is ','; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperPropertyValues.getList(SleeperProperty)"})
  void testGetList_whenDummyInstancePropertyWithPropertyNameIsComma_thenReturnEmpty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertTrue(instanceProperties.getList(new DummyInstanceProperty(",")).isEmpty());
  }

  /**
   * Test {@link SleeperPropertyValues#getList(SleeperProperty)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getList(SleeperProperty)}
   */
  @Test
  @DisplayName("Test getList(SleeperProperty); when MAX_IN_MEMORY_BATCH_SIZE; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperPropertyValues.getList(SleeperProperty)"})
  void testGetList_whenMax_in_memory_batch_size_thenReturnSizeIsOne() {
    // Arrange and Act
    List<String> actualList = (new InstanceProperties()).getList(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    // Assert
    assertEquals(1, actualList.size());
    assertEquals("1000000", actualList.get(0));
  }

  /**
   * Test {@link SleeperPropertyValues#getEnumList(SleeperProperty, Class)}.
   * <ul>
   *   <li>When {@link DummyInstanceProperty#DummyInstanceProperty(String)} with propertyName is {@code ,}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getEnumList(SleeperProperty, Class)}
   */
  @Test
  @DisplayName("Test getEnumList(SleeperProperty, Class); when DummyInstanceProperty(String) with propertyName is ','; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperPropertyValues.getEnumList(SleeperProperty, Class)"})
  void testGetEnumList_whenDummyInstancePropertyWithPropertyNameIsComma_thenReturnEmpty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty(",");
    Class<BaselineResizeBehavior> enumClass = BaselineResizeBehavior.class;

    // Act and Assert
    assertTrue(instanceProperties.getEnumList(dummyInstanceProperty, enumClass).isEmpty());
  }

  /**
   * Test {@link SleeperPropertyValues#getEnumValue(SleeperProperty, Class)}.
   * <ul>
   *   <li>When {@link DummyInstanceProperty#DummyInstanceProperty(String)} with {@code Property Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getEnumValue(SleeperProperty, Class)}
   */
  @Test
  @DisplayName("Test getEnumValue(SleeperProperty, Class); when DummyInstanceProperty(String) with 'Property Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Enum SleeperPropertyValues.getEnumValue(SleeperProperty, Class)"})
  void testGetEnumValue_whenDummyInstancePropertyWithPropertyName() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty("Property Name");
    Class<BaselineResizeBehavior> enumClass = BaselineResizeBehavior.class;

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> instanceProperties.getEnumValue(dummyInstanceProperty, enumClass));
  }

  /**
   * Test {@link SleeperPropertyValues#getEnumValue(SleeperProperty, Class)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyValues#getEnumValue(SleeperProperty, Class)}
   */
  @Test
  @DisplayName("Test getEnumValue(SleeperProperty, Class); when MAX_IN_MEMORY_BATCH_SIZE; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Enum SleeperPropertyValues.getEnumValue(SleeperProperty, Class)"})
  void testGetEnumValue_whenMax_in_memory_batch_size_thenThrowIllegalArgumentException() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    Class<BaselineResizeBehavior> enumClass = BaselineResizeBehavior.class;

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> instanceProperties.getEnumValue(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, enumClass));
  }
}
