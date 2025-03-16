package sleeper.clients.status.report.statestore;

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
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.StartQueryRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.StartQueryRequest.Builder;

class QueryStateStoreCommitterLogsDiffblueTest {
  /**
   * Test {@link QueryStateStoreCommitterLogs#getLogsInPeriod(Instant, Instant)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStateStoreCommitterLogs#getLogsInPeriod(Instant, Instant)}
   */
  @Test
  @DisplayName("Test getLogsInPeriod(Instant, Instant); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List QueryStateStoreCommitterLogs.getLogsInPeriod(Instant, Instant)"})
  void testGetLogsInPeriod_thenThrowRuntimeException()
      throws InterruptedException, AwsServiceException, SdkClientException {
    // Arrange
    CloudWatchLogsClient cloudWatch = mock(CloudWatchLogsClient.class);
    when(cloudWatch.startQuery(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new RuntimeException("Submitting logs query for log group {} starting at time {}"));
    QueryStateStoreCommitterLogs queryStateStoreCommitterLogs = new QueryStateStoreCommitterLogs(
        new InstanceProperties(), cloudWatch);
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertThrows(RuntimeException.class, () -> queryStateStoreCommitterLogs.getLogsInPeriod(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    verify(cloudWatch).startQuery(isA(Consumer.class));
  }
}
