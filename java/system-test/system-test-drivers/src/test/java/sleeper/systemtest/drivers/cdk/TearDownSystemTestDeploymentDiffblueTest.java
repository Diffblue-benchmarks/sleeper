package sleeper.systemtest.drivers.cdk;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.teardown.TearDownClients;
import sleeper.clients.teardown.TearDownClients.Builder;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.DeleteStackRequest;
import software.amazon.awssdk.services.cloudformation.model.DescribeStacksRequest;
import software.amazon.awssdk.services.cloudwatchevents.CloudWatchEventsClient;
import software.amazon.awssdk.services.ecr.EcrClient;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.emr.EmrClient;
import software.amazon.awssdk.services.emrserverless.EmrServerlessClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.internal.crossregion.S3CrossRegionSyncClient;

class TearDownSystemTestDeploymentDiffblueTest {
  /**
   * Test {@link TearDownSystemTestDeployment#fromDeploymentId(TearDownClients, String)}.
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@code -}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TearDownSystemTestDeployment#fromDeploymentId(TearDownClients, String)}
   */
  @Test
  @DisplayName("Test fromDeploymentId(TearDownClients, String); given RuntimeException(String) with '-'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "TearDownSystemTestDeployment TearDownSystemTestDeployment.fromDeploymentId(TearDownClients, String)"})
  void testFromDeploymentId_givenRuntimeExceptionWithDash_thenThrowRuntimeException() {
    // Arrange
    TearDownClients clients = mock(TearDownClients.class);
    when(clients.getS3()).thenThrow(new RuntimeException("-"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> TearDownSystemTestDeployment.fromDeploymentId(clients, "42"));
    verify(clients).getS3();
  }

  /**
   * Test {@link TearDownSystemTestDeployment#deleteStack()}.
   * <p>
   * Method under test: {@link TearDownSystemTestDeployment#deleteStack()}
   */
  @Test
  @DisplayName("Test deleteStack()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TearDownSystemTestDeployment.deleteStack()"})
  void testDeleteStack() throws AwsServiceException, SdkClientException {
    // Arrange
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.deleteStack(Mockito.<Consumer<DeleteStackRequest.Builder>>any()))
        .thenThrow(new RuntimeException("Deleting system test CloudFormation stack: {}"));
    Builder emrServerlessResult = TearDownClients.builder()
        .cloudFormation(cloudFormation)
        .cloudWatch(mock(CloudWatchEventsClient.class))
        .ecr(mock(EcrClient.class))
        .ecs(mock(EcsClient.class))
        .emr(mock(EmrClient.class))
        .emrServerless(mock(EmrServerlessClient.class));
    Builder s3Result = emrServerlessResult.s3(new AmazonS3Client());
    TearDownClients clients = s3Result.s3v2(new S3CrossRegionSyncClient(mock(S3Client.class))).build();

    // Act
    TearDownSystemTestDeployment.fromDeploymentId(clients, "42").deleteStack();

    // Assert
    verify(cloudFormation).deleteStack(isA(Consumer.class));
  }

  /**
   * Test {@link TearDownSystemTestDeployment#deleteStack()}.
   * <ul>
   *   <li>Given {@link CloudFormationClient} {@link CloudFormationClient#deleteStack(Consumer)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TearDownSystemTestDeployment#deleteStack()}
   */
  @Test
  @DisplayName("Test deleteStack(); given CloudFormationClient deleteStack(Consumer) return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TearDownSystemTestDeployment.deleteStack()"})
  void testDeleteStack_givenCloudFormationClientDeleteStackReturnNull() throws AwsServiceException, SdkClientException {
    // Arrange
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.deleteStack(Mockito.<Consumer<DeleteStackRequest.Builder>>any())).thenReturn(null);
    Builder emrServerlessResult = TearDownClients.builder()
        .cloudFormation(cloudFormation)
        .cloudWatch(mock(CloudWatchEventsClient.class))
        .ecr(mock(EcrClient.class))
        .ecs(mock(EcsClient.class))
        .emr(mock(EmrClient.class))
        .emrServerless(mock(EmrServerlessClient.class));
    Builder s3Result = emrServerlessResult.s3(new AmazonS3Client());
    TearDownClients clients = s3Result.s3v2(new S3CrossRegionSyncClient(mock(S3Client.class))).build();

    // Act
    TearDownSystemTestDeployment.fromDeploymentId(clients, "42").deleteStack();

    // Assert
    verify(cloudFormation).deleteStack(isA(Consumer.class));
  }

  /**
   * Test {@link TearDownSystemTestDeployment#waitForStackToDelete()}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TearDownSystemTestDeployment#waitForStackToDelete()}
   */
  @Test
  @DisplayName("Test waitForStackToDelete(); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TearDownSystemTestDeployment.waitForStackToDelete()"})
  void testWaitForStackToDelete_thenThrowRuntimeException()
      throws InterruptedException, AwsServiceException, SdkClientException {
    // Arrange
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.describeStacks(Mockito.<Consumer<DescribeStacksRequest.Builder>>any()))
        .thenThrow(new RuntimeException("foo"));
    Builder emrServerlessResult = TearDownClients.builder()
        .cloudFormation(cloudFormation)
        .cloudWatch(mock(CloudWatchEventsClient.class))
        .ecr(mock(EcrClient.class))
        .ecs(mock(EcsClient.class))
        .emr(mock(EmrClient.class))
        .emrServerless(mock(EmrServerlessClient.class));
    Builder s3Result = emrServerlessResult.s3(new AmazonS3Client());
    TearDownClients clients = s3Result.s3v2(new S3CrossRegionSyncClient(mock(S3Client.class))).build();

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> TearDownSystemTestDeployment.fromDeploymentId(clients, "42").waitForStackToDelete());
    verify(cloudFormation).describeStacks(isA(Consumer.class));
  }
}
