package sleeper.systemtest.dsl.instance;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperProperties;
import sleeper.core.properties.SleeperProperty;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.testutils.DummySleeperProperty;

class ResetPropertiesDiffblueTest {
  /**
   * Test {@link ResetProperties#reset(SleeperProperties, SleeperProperties)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link SleeperProperties} {@link SleeperProperties#unset(SleeperProperty)} does nothing.</li>
   *   <li>Then calls {@link SleeperProperties#unset(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResetProperties#reset(SleeperProperties, SleeperProperties)}
   */
  @Test
  @DisplayName("Test reset(SleeperProperties, SleeperProperties); given 'false'; when SleeperProperties unset(SleeperProperty) does nothing; then calls unset(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResetProperties.reset(SleeperProperties, SleeperProperties)"})
  void testReset_givenFalse_whenSleeperPropertiesUnsetDoesNothing_thenCallsUnset() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();
    sleeperPropertyIndex.add(new DummySleeperProperty());
    SleeperProperties<SleeperProperty> properties = mock(SleeperProperties.class);
    doNothing().when(properties).unset(Mockito.<SleeperProperty>any());
    when(properties.getPropertiesIndex()).thenReturn(sleeperPropertyIndex);
    SleeperProperties<SleeperProperty> resetProperties = mock(SleeperProperties.class);
    when(resetProperties.isSet(Mockito.<SleeperProperty>any())).thenReturn(false);

    // Act
    ResetProperties.reset(properties, resetProperties);

    // Assert
    verify(properties).getPropertiesIndex();
    verify(resetProperties).isSet(isA(SleeperProperty.class));
    verify(properties).unset(isA(SleeperProperty.class));
  }

  /**
   * Test {@link ResetProperties#reset(SleeperProperties, SleeperProperties)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link SleeperProperties} {@link SleeperProperties#set(SleeperProperty, String)} does nothing.</li>
   *   <li>Then calls {@link SleeperProperties#get(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResetProperties#reset(SleeperProperties, SleeperProperties)}
   */
  @Test
  @DisplayName("Test reset(SleeperProperties, SleeperProperties); given 'Get'; when SleeperProperties set(SleeperProperty, String) does nothing; then calls get(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResetProperties.reset(SleeperProperties, SleeperProperties)"})
  void testReset_givenGet_whenSleeperPropertiesSetDoesNothing_thenCallsGet() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();
    sleeperPropertyIndex.add(new DummySleeperProperty());
    SleeperProperties<SleeperProperty> properties = mock(SleeperProperties.class);
    doNothing().when(properties).set(Mockito.<SleeperProperty>any(), Mockito.<String>any());
    when(properties.getPropertiesIndex()).thenReturn(sleeperPropertyIndex);
    SleeperProperties<SleeperProperty> resetProperties = mock(SleeperProperties.class);
    when(resetProperties.get(Mockito.<SleeperProperty>any())).thenReturn("Get");
    when(resetProperties.isSet(Mockito.<SleeperProperty>any())).thenReturn(true);

    // Act
    ResetProperties.reset(properties, resetProperties);

    // Assert
    verify(resetProperties).get(isA(SleeperProperty.class));
    verify(properties).getPropertiesIndex();
    verify(resetProperties).isSet(isA(SleeperProperty.class));
    verify(properties).set(isA(SleeperProperty.class), eq("Get"));
  }

  /**
   * Test {@link ResetProperties#reset(SleeperProperties, SleeperProperties)}.
   * <ul>
   *   <li>Given {@link SleeperPropertyIndex} (default constructor).</li>
   *   <li>When {@link SleeperProperties}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResetProperties#reset(SleeperProperties, SleeperProperties)}
   */
  @Test
  @DisplayName("Test reset(SleeperProperties, SleeperProperties); given SleeperPropertyIndex (default constructor); when SleeperProperties")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResetProperties.reset(SleeperProperties, SleeperProperties)"})
  void testReset_givenSleeperPropertyIndex_whenSleeperProperties() {
    // Arrange
    SleeperProperties<SleeperProperty> properties = mock(SleeperProperties.class);
    when(properties.getPropertiesIndex()).thenReturn(new SleeperPropertyIndex<>());

    // Act
    ResetProperties.reset(properties, mock(SleeperProperties.class));

    // Assert
    verify(properties).getPropertiesIndex();
  }
}
