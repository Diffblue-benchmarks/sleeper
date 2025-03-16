package sleeper.systemtest.drivers.statestore;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.StartQueryRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.StartQueryRequest.Builder;

class AwsStateStoreCommitterLogsDriverDiffblueTest {
  /**
   * Test {@link AwsStateStoreCommitterLogsDriver#getLogsInPeriod(Instant, Instant)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsStateStoreCommitterLogsDriver#getLogsInPeriod(Instant, Instant)}
   */
  @Test
  @DisplayName("Test getLogsInPeriod(Instant, Instant); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.systemtest.dsl.statestore.StateStoreCommitterLogs AwsStateStoreCommitterLogsDriver.getLogsInPeriod(Instant, Instant)"})
  void testGetLogsInPeriod_thenThrowRuntimeException() throws AwsServiceException, SdkClientException {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    CloudWatchLogsClient cloudWatch = mock(CloudWatchLogsClient.class);
    when(cloudWatch.startQuery(Mockito.<Consumer<Builder>>any())).thenThrow(new RuntimeException("foo"));
    AwsStateStoreCommitterLogsDriver awsStateStoreCommitterLogsDriver = new AwsStateStoreCommitterLogsDriver(instance,
        cloudWatch);
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertThrows(RuntimeException.class, () -> awsStateStoreCommitterLogsDriver.getLogsInPeriod(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    verify(instance).getInstanceProperties();
    verify(cloudWatch).startQuery(isA(Consumer.class));
  }
}
