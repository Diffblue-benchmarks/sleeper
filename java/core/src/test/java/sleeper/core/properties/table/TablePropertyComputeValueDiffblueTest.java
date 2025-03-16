package sleeper.core.properties.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperProperty;
import sleeper.core.properties.instance.ArrayListIngestProperty;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class TablePropertyComputeValueDiffblueTest {
  /**
   * Test {@link TablePropertyComputeValue#fixedDefault(String)}.
   * <ul>
   *   <li>Then return {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} with {@code foo} and {@link InstanceProperties#InstanceProperties()} and {@code null} is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertyComputeValue#fixedDefault(String)}
   */
  @Test
  @DisplayName("Test fixedDefault(String); then return computeValue(String, InstanceProperties, TableProperties) with 'foo' and InstanceProperties() and 'null' is 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TablePropertyComputeValue TablePropertyComputeValue.fixedDefault(String)"})
  void testFixedDefault_thenReturnComputeValueWithFooAndInstancePropertiesAndNullIsFoo() {
    // Arrange and Act
    TablePropertyComputeValue actualFixedDefaultResult = TablePropertyComputeValue.fixedDefault("42");

    // Assert
    assertEquals("foo", actualFixedDefaultResult.computeValue("foo", new InstanceProperties(), null));
  }

  /**
   * Test {@link TablePropertyComputeValue#fixedDefault(String)}.
   * <ul>
   *   <li>Then return {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} with {@code null} and {@link InstanceProperties#InstanceProperties()} and {@code null} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertyComputeValue#fixedDefault(String)}
   */
  @Test
  @DisplayName("Test fixedDefault(String); then return computeValue(String, InstanceProperties, TableProperties) with 'null' and InstanceProperties() and 'null' is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TablePropertyComputeValue TablePropertyComputeValue.fixedDefault(String)"})
  void testFixedDefault_thenReturnComputeValueWithNullAndInstancePropertiesAndNullIs42() {
    // Arrange and Act
    TablePropertyComputeValue actualFixedDefaultResult = TablePropertyComputeValue.fixedDefault("42");

    // Assert
    assertEquals("42", actualFixedDefaultResult.computeValue(null, new InstanceProperties(), null));
  }

  /**
   * Test {@link TablePropertyComputeValue#none()}.
   * <ul>
   *   <li>Then return {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} with {@code foo} and {@link InstanceProperties#InstanceProperties()} and {@code null} is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertyComputeValue#none()}
   */
  @Test
  @DisplayName("Test none(); then return computeValue(String, InstanceProperties, TableProperties) with 'foo' and InstanceProperties() and 'null' is 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TablePropertyComputeValue TablePropertyComputeValue.none()"})
  void testNone_thenReturnComputeValueWithFooAndInstancePropertiesAndNullIsFoo() {
    // Arrange and Act
    TablePropertyComputeValue actualNoneResult = TablePropertyComputeValue.none();

    // Assert
    assertEquals("foo", actualNoneResult.computeValue("foo", new InstanceProperties(), null));
  }

  /**
   * Test {@link TablePropertyComputeValue#none()}.
   * <ul>
   *   <li>Then return {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} with {@code null} and {@link InstanceProperties#InstanceProperties()} and {@code null} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertyComputeValue#none()}
   */
  @Test
  @DisplayName("Test none(); then return computeValue(String, InstanceProperties, TableProperties) with 'null' and InstanceProperties() and 'null' is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TablePropertyComputeValue TablePropertyComputeValue.none()"})
  void testNone_thenReturnComputeValueWithNullAndInstancePropertiesAndNullIsNull() {
    // Arrange and Act
    TablePropertyComputeValue actualNoneResult = TablePropertyComputeValue.none();

    // Assert
    assertNull(actualNoneResult.computeValue(null, new InstanceProperties(), null));
  }

  /**
   * Test {@link TablePropertyComputeValue#defaultProperty(SleeperProperty)}.
   * <p>
   * Method under test: {@link TablePropertyComputeValue#defaultProperty(SleeperProperty)}
   */
  @Test
  @DisplayName("Test defaultProperty(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TablePropertyComputeValue TablePropertyComputeValue.defaultProperty(SleeperProperty)"})
  void testDefaultProperty() {
    // Arrange and Act
    TablePropertyComputeValue actualDefaultPropertyResult = TablePropertyComputeValue
        .defaultProperty(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    doNothing().when(instanceProperties)
        .addToListIfMissing(Mockito.<InstanceProperty>any(), Mockito.<List<String>>any());
    instanceProperties.addToListIfMissing(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, new ArrayList<>());
    String actualComputeValueResult = actualDefaultPropertyResult.computeValue("foo", instanceProperties, null);

    // Assert
    verify(instanceProperties).addToListIfMissing(isA(InstanceProperty.class), isA(List.class));
    assertEquals("foo", actualComputeValueResult);
  }

  /**
   * Test {@link TablePropertyComputeValue#applyDefaultValue(BiFunction)}.
   * <p>
   * Method under test: {@link TablePropertyComputeValue#applyDefaultValue(BiFunction)}
   */
  @Test
  @DisplayName("Test applyDefaultValue(BiFunction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TablePropertyComputeValue TablePropertyComputeValue.applyDefaultValue(BiFunction)"})
  void testApplyDefaultValue() {
    // Arrange and Act
    TablePropertyComputeValue actualApplyDefaultValueResult = TablePropertyComputeValue
        .applyDefaultValue(mock(BiFunction.class));

    // Assert
    assertEquals("foo", actualApplyDefaultValueResult.computeValue("foo", new InstanceProperties(), null));
  }

  /**
   * Test {@link TablePropertyComputeValue#applyDefaultValue(BiFunction)}.
   * <p>
   * Method under test: {@link TablePropertyComputeValue#applyDefaultValue(BiFunction)}
   */
  @Test
  @DisplayName("Test applyDefaultValue(BiFunction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TablePropertyComputeValue TablePropertyComputeValue.applyDefaultValue(BiFunction)"})
  void testApplyDefaultValue2() {
    // Arrange
    BiFunction<InstanceProperties, TableProperties, String> getDefault = mock(BiFunction.class);
    when(getDefault.apply(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any())).thenReturn("Apply");

    // Act
    TablePropertyComputeValue actualApplyDefaultValueResult = TablePropertyComputeValue.applyDefaultValue(getDefault);
    String actualComputeValueResult = actualApplyDefaultValueResult.computeValue(null, new InstanceProperties(), null);

    // Assert
    verify(getDefault).apply(isA(InstanceProperties.class), isNull());
    assertEquals("Apply", actualComputeValueResult);
  }

  /**
   * Test {@link TablePropertyComputeValue#applyDefaultValue(BiFunction)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertyComputeValue#applyDefaultValue(BiFunction)}
   */
  @Test
  @DisplayName("Test applyDefaultValue(BiFunction); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TablePropertyComputeValue TablePropertyComputeValue.applyDefaultValue(BiFunction)"})
  void testApplyDefaultValue_thenThrowIllegalArgumentException() {
    // Arrange
    BiFunction<InstanceProperties, TableProperties, String> getDefault = mock(BiFunction.class);
    when(getDefault.apply(Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenThrow(new IllegalArgumentException("foo"));

    // Act
    TablePropertyComputeValue actualApplyDefaultValueResult = TablePropertyComputeValue.applyDefaultValue(getDefault);

    // Assert
    assertThrows(IllegalArgumentException.class,
        () -> actualApplyDefaultValueResult.computeValue(null, new InstanceProperties(), null));
    verify(getDefault).apply(isA(InstanceProperties.class), isNull());
  }
}
