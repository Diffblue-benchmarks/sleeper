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
import sleeper.core.properties.SleeperProperties;
import sleeper.core.properties.SleeperProperty;
import sleeper.core.properties.testutils.DummySleeperProperty;
import software.amazon.awssdk.services.cloudwatchevents.CloudWatchEventsClient;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.emr.EmrClient;
import software.amazon.awssdk.services.emrserverless.EmrServerlessClient;

class ShutdownSystemProcessesDiffblueTest {
  /**
   * Test {@link ShutdownSystemProcesses#ShutdownSystemProcesses(TearDownClients)}.
   * <ul>
   *   <li>Then calls {@link TearDownClients#getCloudWatch()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShutdownSystemProcesses#ShutdownSystemProcesses(TearDownClients)}
   */
  @Test
  @DisplayName("Test new ShutdownSystemProcesses(TearDownClients); then calls getCloudWatch()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShutdownSystemProcesses.<init>(TearDownClients)"})
  void testNewShutdownSystemProcesses_thenCallsGetCloudWatch() {
    // Arrange
    TearDownClients clients = mock(TearDownClients.class);
    when(clients.getCloudWatch()).thenReturn(mock(CloudWatchEventsClient.class));
    when(clients.getEcs()).thenReturn(mock(EcsClient.class));
    when(clients.getEmr()).thenReturn(mock(EmrClient.class));
    when(clients.getEmrServerless()).thenReturn(mock(EmrServerlessClient.class));

    // Act
    new ShutdownSystemProcesses(clients);

    // Assert
    verify(clients).getCloudWatch();
    verify(clients).getEcs();
    verify(clients).getEmr();
    verify(clients).getEmrServerless();
  }

  /**
   * Test {@link ShutdownSystemProcesses#stopTasks(EcsClient, SleeperProperties, SleeperProperty)} with {@code ecs}, {@code properties}, {@code property}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link EcsClient}.</li>
   *   <li>Then calls {@link SleeperProperties#isSet(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShutdownSystemProcesses#stopTasks(EcsClient, SleeperProperties, SleeperProperty)}
   */
  @Test
  @DisplayName("Test stopTasks(EcsClient, SleeperProperties, SleeperProperty) with 'ecs', 'properties', 'property'; given 'false'; when EcsClient; then calls isSet(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShutdownSystemProcesses.stopTasks(EcsClient, SleeperProperties, SleeperProperty)"})
  void testStopTasksWithEcsPropertiesProperty_givenFalse_whenEcsClient_thenCallsIsSet() {
    // Arrange
    EcsClient ecs = mock(EcsClient.class);
    SleeperProperties<SleeperProperty> properties = mock(SleeperProperties.class);
    when(properties.isSet(Mockito.<SleeperProperty>any())).thenReturn(false);

    // Act
    ShutdownSystemProcesses.stopTasks(ecs, properties, new DummySleeperProperty());

    // Assert
    verify(properties).isSet(isA(SleeperProperty.class));
  }
}
