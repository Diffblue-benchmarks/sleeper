package sleeper.systemtest.drivers.cdk;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.deploy.DockerImageConfiguration;
import sleeper.clients.teardown.TearDownClients;
import sleeper.clients.teardown.TearDownClients.Builder;
import sleeper.clients.teardown.TearDownInstance;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.ListStacksRequest;
import software.amazon.awssdk.services.cloudformation.model.StackSummary;
import software.amazon.awssdk.services.cloudformation.paginators.ListStacksIterable;
import software.amazon.awssdk.services.cloudwatchevents.CloudWatchEventsClient;
import software.amazon.awssdk.services.ecr.EcrClient;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.emr.EmrClient;
import software.amazon.awssdk.services.emrserverless.EmrServerlessClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.internal.crossregion.S3CrossRegionSyncClient;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;

class CleanUpDeletedSleeperInstancesDiffblueTest {
  /**
   * Test {@link CleanUpDeletedSleeperInstances#run()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CleanUpDeletedSleeperInstances#run()}
   */
  @Test
  @DisplayName("Test run(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CleanUpDeletedSleeperInstances.run()"})
  void testRun_thenThrowIllegalArgumentException()
      throws IOException, InterruptedException, AwsServiceException, SdkClientException {
    // Arrange
    SdkIterable<StackSummary> sdkIterable = mock(SdkIterable.class);

    ArrayList<StackSummary> stackSummaryList = new ArrayList<>();
    Stream<StackSummary> streamResult = stackSummaryList.stream();
    when(sdkIterable.stream()).thenReturn(streamResult);
    ListStacksIterable listStacksIterable = mock(ListStacksIterable.class);
    when(listStacksIterable.stackSummaries()).thenReturn(sdkIterable);
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.listStacksPaginator(Mockito.<Consumer<ListStacksRequest.Builder>>any()))
        .thenReturn(listStacksIterable);
    Builder emrServerlessResult = TearDownClients.builder()
        .cloudFormation(cloudFormation)
        .cloudWatch(mock(CloudWatchEventsClient.class))
        .ecr(mock(EcrClient.class))
        .ecs(mock(EcsClient.class))
        .emr(mock(EmrClient.class))
        .emrServerless(mock(EmrServerlessClient.class));
    Builder s3Result = emrServerlessResult.s3(new AmazonS3Client());
    S3Client s3Client = mock(S3Client.class);
    when(s3Client.listBuckets(Mockito.<ListBucketsRequest>any())).thenThrow(new IllegalArgumentException("foo"));
    TearDownClients clients = s3Result.s3v2(new S3CrossRegionSyncClient(s3Client)).build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new CleanUpDeletedSleeperInstances(clients, TearDownInstance.builder())).run());
    verify(sdkIterable).stream();
    verify(cloudFormation).listStacksPaginator(isA(Consumer.class));
    verify(listStacksIterable).stackSummaries();
    verify(s3Client).listBuckets(isA(ListBucketsRequest.class));
  }

  /**
   * Test {@link CleanUpDeletedSleeperInstances#instanceIdsByJarsBuckets(Stream)} with {@code Stream}.
   * <p>
   * Method under test: {@link CleanUpDeletedSleeperInstances#instanceIdsByJarsBuckets(Stream)}
   */
  @Test
  @DisplayName("Test instanceIdsByJarsBuckets(Stream) with 'Stream'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream CleanUpDeletedSleeperInstances.instanceIdsByJarsBuckets(Stream)"})
  void testInstanceIdsByJarsBucketsWithStream() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> bucketNames = stringList.stream();

    // Act
    Stream<String> actualInstanceIdsByJarsBucketsResult = CleanUpDeletedSleeperInstances
        .instanceIdsByJarsBuckets(bucketNames);

    // Assert
    assertTrue(actualInstanceIdsByJarsBucketsResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link CleanUpDeletedSleeperInstances#instanceIdsByEcrRepositories(DockerImageConfiguration, Stream)} with {@code DockerImageConfiguration}, {@code Stream}.
   * <p>
   * Method under test: {@link CleanUpDeletedSleeperInstances#instanceIdsByEcrRepositories(DockerImageConfiguration, Stream)}
   */
  @Test
  @DisplayName("Test instanceIdsByEcrRepositories(DockerImageConfiguration, Stream) with 'DockerImageConfiguration', 'Stream'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Stream CleanUpDeletedSleeperInstances.instanceIdsByEcrRepositories(DockerImageConfiguration, Stream)"})
  void testInstanceIdsByEcrRepositoriesWithDockerImageConfigurationStream() {
    // Arrange
    DockerImageConfiguration dockerImageConfiguration = DockerImageConfiguration.getDefault();

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> repositoryNames = stringList.stream();

    // Act
    Stream<String> actualInstanceIdsByEcrRepositoriesResult = CleanUpDeletedSleeperInstances
        .instanceIdsByEcrRepositories(dockerImageConfiguration, repositoryNames);

    // Assert
    assertTrue(actualInstanceIdsByEcrRepositoriesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
