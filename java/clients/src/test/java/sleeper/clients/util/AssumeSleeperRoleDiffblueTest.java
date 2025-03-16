package sleeper.clients.util;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class AssumeSleeperRoleDiffblueTest {
  /**
   * Test {@link AssumeSleeperRole#ingestByQueue(InstanceProperties)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssumeSleeperRole#ingestByQueue(InstanceProperties)}
   */
  @Test
  @DisplayName("Test ingestByQueue(InstanceProperties); given 'Get'; when InstanceProperties get(InstanceProperty) return 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssumeSleeperRole AssumeSleeperRole.ingestByQueue(InstanceProperties)"})
  void testIngestByQueue_givenGet_whenInstancePropertiesGetReturnGet_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    AssumeSleeperRole.ingestByQueue(instanceProperties);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link AssumeSleeperRole#directIngest(InstanceProperties)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssumeSleeperRole#directIngest(InstanceProperties)}
   */
  @Test
  @DisplayName("Test directIngest(InstanceProperties); given 'Get'; when InstanceProperties get(InstanceProperty) return 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssumeSleeperRole AssumeSleeperRole.directIngest(InstanceProperties)"})
  void testDirectIngest_givenGet_whenInstancePropertiesGetReturnGet_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    AssumeSleeperRole.directIngest(instanceProperties);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link AssumeSleeperRole#instanceAdmin(InstanceProperties)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssumeSleeperRole#instanceAdmin(InstanceProperties)}
   */
  @Test
  @DisplayName("Test instanceAdmin(InstanceProperties); given 'Get'; when InstanceProperties get(InstanceProperty) return 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssumeSleeperRole AssumeSleeperRole.instanceAdmin(InstanceProperties)"})
  void testInstanceAdmin_givenGet_whenInstancePropertiesGetReturnGet_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    AssumeSleeperRole.instanceAdmin(instanceProperties);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }
}
