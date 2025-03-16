package sleeper.clients.status.update;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.cloudwatchevents.CloudWatchEventsClient;
import software.amazon.awssdk.services.cloudwatchevents.model.DisableRuleRequest;
import software.amazon.awssdk.services.cloudwatchevents.model.DisableRuleRequest.Builder;

class PauseSystemDiffblueTest {
  /**
   * Test {@link PauseSystem#pause(CloudWatchEventsClient, InstanceProperties)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PauseSystem#pause(CloudWatchEventsClient, InstanceProperties)}
   */
  @Test
  @DisplayName("Test pause(CloudWatchEventsClient, InstanceProperties); given IllegalArgumentException(String) with 'foo'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PauseSystem.pause(CloudWatchEventsClient, InstanceProperties)"})
  void testPause_givenIllegalArgumentExceptionWithFoo_thenThrowIllegalArgumentException()
      throws AwsServiceException, SdkClientException {
    // Arrange
    CloudWatchEventsClient cwClient = mock(CloudWatchEventsClient.class);
    when(cwClient.disableRule(Mockito.<Consumer<Builder>>any())).thenThrow(new IllegalArgumentException("foo"));
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> PauseSystem.pause(cwClient, instanceProperties));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(cwClient).disableRule(isA(Consumer.class));
  }

  /**
   * Test {@link PauseSystem#pause(CloudWatchEventsClient, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link CloudWatchEventsClient} {@link CloudWatchEventsClient#disableRule(Consumer)} return {@code null}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PauseSystem#pause(CloudWatchEventsClient, InstanceProperties)}
   */
  @Test
  @DisplayName("Test pause(CloudWatchEventsClient, InstanceProperties); given 'null'; when CloudWatchEventsClient disableRule(Consumer) return 'null'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PauseSystem.pause(CloudWatchEventsClient, InstanceProperties)"})
  void testPause_givenNull_whenCloudWatchEventsClientDisableRuleReturnNull_thenCallsGet()
      throws AwsServiceException, SdkClientException {
    // Arrange
    CloudWatchEventsClient cwClient = mock(CloudWatchEventsClient.class);
    when(cwClient.disableRule(Mockito.<Consumer<Builder>>any())).thenReturn(null);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    PauseSystem.pause(cwClient, instanceProperties);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(cwClient, atLeast(1)).disableRule(Mockito.<Consumer<Builder>>any());
  }
}
