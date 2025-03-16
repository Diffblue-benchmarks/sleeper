package sleeper.clients.teardown;

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
import software.amazon.awssdk.services.emrserverless.EmrServerlessClient;

class TerminateEMRServerlessApplicationsDiffblueTest {
  /**
   * Test {@link TerminateEMRServerlessApplications#TerminateEMRServerlessApplications(EmrServerlessClient, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TerminateEMRServerlessApplications#TerminateEMRServerlessApplications(EmrServerlessClient, InstanceProperties)}
   */
  @Test
  @DisplayName("Test new TerminateEMRServerlessApplications(EmrServerlessClient, InstanceProperties); given 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TerminateEMRServerlessApplications.<init>(EmrServerlessClient, InstanceProperties)"})
  void testNewTerminateEMRServerlessApplications_givenGet_thenCallsGet() {
    // Arrange
    EmrServerlessClient emrServerlessClient = mock(EmrServerlessClient.class);
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new TerminateEMRServerlessApplications(emrServerlessClient, properties);

    // Assert
    verify(properties).get(isA(InstanceProperty.class));
  }
}
