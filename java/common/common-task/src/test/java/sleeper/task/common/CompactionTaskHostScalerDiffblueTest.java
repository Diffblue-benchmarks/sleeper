package sleeper.task.common;

import static org.mockito.ArgumentMatchers.isA;
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
import software.amazon.awssdk.services.autoscaling.AutoScalingClient;
import software.amazon.awssdk.services.ec2.Ec2Client;

class CompactionTaskHostScalerDiffblueTest {
  /**
   * Test {@link CompactionTaskHostScaler#scaleTo(int)}.
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskHostScaler#scaleTo(int)}
   */
  @Test
  @DisplayName("Test scaleTo(int); given InstanceProperties get(InstanceProperty) return 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionTaskHostScaler.scaleTo(int)"})
  void testScaleTo_givenInstancePropertiesGetReturnGet_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    EC2Scaler.create(instanceProperties, mock(AutoScalingClient.class), mock(Ec2Client.class)).scaleTo(10);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }
}
