package sleeper.clients.teardown;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.teardown.CleanUpLogGroups.DeletionPlan;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.ListStacksRequest;
import software.amazon.awssdk.services.cloudformation.model.ListStacksRequest.Builder;
import software.amazon.awssdk.services.cloudformation.model.StackSummary;
import software.amazon.awssdk.services.cloudformation.paginators.ListStacksIterable;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.LogGroup;
import software.amazon.awssdk.services.cloudwatchlogs.paginators.DescribeLogGroupsIterable;

class CleanUpLogGroupsDiffblueTest {
  /**
   * Test DeletionPlan {@link DeletionPlan#delete(CloudWatchLogsClient, Runnable)}.
   * <ul>
   *   <li>Then calls {@link CloudFormationStacks#getStackNames()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeletionPlan#delete(CloudWatchLogsClient, Runnable)}
   */
  @Test
  @DisplayName("Test DeletionPlan delete(CloudWatchLogsClient, Runnable); then calls getStackNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeletionPlan.delete(CloudWatchLogsClient, Runnable)"})
  void testDeletionPlanDelete_thenCallsGetStackNames() {
    // Arrange
    CloudFormationStacks stacks = mock(CloudFormationStacks.class);
    when(stacks.getStackNames()).thenReturn(new ArrayList<>());

    // Act
    (new DeletionPlan(stacks, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .delete(mock(CloudWatchLogsClient.class), mock(Runnable.class));

    // Assert
    verify(stacks).getStackNames();
  }

  /**
   * Test {@link CleanUpLogGroups#run(CloudWatchLogsClient, CloudFormationClient, Instant, Runnable)} with {@code logsClient}, {@code cloudFormation}, {@code queryTime}, {@code sleepForRateLimit}.
   * <ul>
   *   <li>Then calls {@link Iterable#forEach(Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CleanUpLogGroups#run(CloudWatchLogsClient, CloudFormationClient, Instant, Runnable)}
   */
  @Test
  @DisplayName("Test run(CloudWatchLogsClient, CloudFormationClient, Instant, Runnable) with 'logsClient', 'cloudFormation', 'queryTime', 'sleepForRateLimit'; then calls forEach(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CleanUpLogGroups.run(CloudWatchLogsClient, CloudFormationClient, Instant, Runnable)"})
  void testRunWithLogsClientCloudFormationQueryTimeSleepForRateLimit_thenCallsForEach()
      throws AwsServiceException, SdkClientException {
    // Arrange
    SdkIterable<LogGroup> sdkIterable = mock(SdkIterable.class);
    doNothing().when(sdkIterable).forEach(Mockito.<Consumer<LogGroup>>any());
    DescribeLogGroupsIterable describeLogGroupsIterable = mock(DescribeLogGroupsIterable.class);
    when(describeLogGroupsIterable.logGroups()).thenReturn(sdkIterable);
    CloudWatchLogsClient logsClient = mock(CloudWatchLogsClient.class);
    when(logsClient.describeLogGroupsPaginator()).thenReturn(describeLogGroupsIterable);
    SdkIterable<StackSummary> sdkIterable2 = mock(SdkIterable.class);

    ArrayList<StackSummary> stackSummaryList = new ArrayList<>();
    Stream<StackSummary> streamResult = stackSummaryList.stream();
    when(sdkIterable2.stream()).thenReturn(streamResult);
    ListStacksIterable listStacksIterable = mock(ListStacksIterable.class);
    when(listStacksIterable.stackSummaries()).thenReturn(sdkIterable2);
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.listStacksPaginator(Mockito.<Consumer<Builder>>any())).thenReturn(listStacksIterable);

    // Act
    CleanUpLogGroups.run(logsClient, cloudFormation,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), mock(Runnable.class));

    // Assert
    verify(sdkIterable).forEach(isA(Consumer.class));
    verify(sdkIterable2).stream();
    verify(cloudFormation).listStacksPaginator(isA(Consumer.class));
    verify(listStacksIterable).stackSummaries();
    verify(logsClient).describeLogGroupsPaginator();
    verify(describeLogGroupsIterable).logGroups();
  }

  /**
   * Test {@link CleanUpLogGroups#run(CloudWatchLogsClient, CloudFormationClient)} with {@code logs}, {@code cloudFormation}.
   * <ul>
   *   <li>Then calls {@link Iterable#forEach(Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CleanUpLogGroups#run(CloudWatchLogsClient, CloudFormationClient)}
   */
  @Test
  @DisplayName("Test run(CloudWatchLogsClient, CloudFormationClient) with 'logs', 'cloudFormation'; then calls forEach(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CleanUpLogGroups.run(CloudWatchLogsClient, CloudFormationClient)"})
  void testRunWithLogsCloudFormation_thenCallsForEach() throws AwsServiceException, SdkClientException {
    // Arrange
    SdkIterable<LogGroup> sdkIterable = mock(SdkIterable.class);
    doNothing().when(sdkIterable).forEach(Mockito.<Consumer<LogGroup>>any());
    DescribeLogGroupsIterable describeLogGroupsIterable = mock(DescribeLogGroupsIterable.class);
    when(describeLogGroupsIterable.logGroups()).thenReturn(sdkIterable);
    CloudWatchLogsClient logs = mock(CloudWatchLogsClient.class);
    when(logs.describeLogGroupsPaginator()).thenReturn(describeLogGroupsIterable);
    SdkIterable<StackSummary> sdkIterable2 = mock(SdkIterable.class);

    ArrayList<StackSummary> stackSummaryList = new ArrayList<>();
    Stream<StackSummary> streamResult = stackSummaryList.stream();
    when(sdkIterable2.stream()).thenReturn(streamResult);
    ListStacksIterable listStacksIterable = mock(ListStacksIterable.class);
    when(listStacksIterable.stackSummaries()).thenReturn(sdkIterable2);
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.listStacksPaginator(Mockito.<Consumer<Builder>>any())).thenReturn(listStacksIterable);

    // Act
    CleanUpLogGroups.run(logs, cloudFormation);

    // Assert
    verify(sdkIterable).forEach(isA(Consumer.class));
    verify(sdkIterable2).stream();
    verify(cloudFormation).listStacksPaginator(isA(Consumer.class));
    verify(listStacksIterable).stackSummaries();
    verify(logs).describeLogGroupsPaginator();
    verify(describeLogGroupsIterable).logGroups();
  }
}
