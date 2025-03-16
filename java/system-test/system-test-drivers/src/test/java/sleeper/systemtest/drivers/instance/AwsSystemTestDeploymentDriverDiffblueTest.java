package sleeper.systemtest.drivers.instance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.drivers.util.SystemTestClients;
import sleeper.systemtest.drivers.util.SystemTestClients.Builder;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.providers.AwsRegionProvider;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.DescribeStacksRequest;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatchevents.CloudWatchEventsClient;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ecr.EcrClient;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.emr.EmrClient;
import software.amazon.awssdk.services.emrserverless.EmrServerlessClient;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.internal.crossregion.S3CrossRegionSyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sts.StsClient;

class AwsSystemTestDeploymentDriverDiffblueTest {
  /**
   * Test {@link AwsSystemTestDeploymentDriver#AwsSystemTestDeploymentDriver(SystemTestParameters, SystemTestClients)}.
   * <ul>
   *   <li>Then calls {@link SystemTestClients#getCloudFormation()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSystemTestDeploymentDriver#AwsSystemTestDeploymentDriver(SystemTestParameters, SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsSystemTestDeploymentDriver(SystemTestParameters, SystemTestClients); then calls getCloudFormation()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsSystemTestDeploymentDriver.<init>(SystemTestParameters, SystemTestClients)"})
  void testNewAwsSystemTestDeploymentDriver_thenCallsGetCloudFormation() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getS3()).thenReturn(new AmazonS3Client());
    when(clients.getCloudFormation()).thenReturn(mock(CloudFormationClient.class));
    when(clients.getEcr()).thenReturn(mock(EcrClient.class));
    when(clients.getS3V2()).thenReturn(new S3CrossRegionSyncClient(mock(S3Client.class)));

    // Act
    new AwsSystemTestDeploymentDriver(parameters, clients);

    // Assert
    verify(clients).getCloudFormation();
    verify(clients).getEcr();
    verify(clients).getS3();
    verify(clients).getS3V2();
  }

  /**
   * Test {@link AwsSystemTestDeploymentDriver#AwsSystemTestDeploymentDriver(SystemTestParameters, SystemTestClients)}.
   * <ul>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSystemTestDeploymentDriver#AwsSystemTestDeploymentDriver(SystemTestParameters, SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsSystemTestDeploymentDriver(SystemTestParameters, SystemTestClients); then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsSystemTestDeploymentDriver.<init>(SystemTestParameters, SystemTestClients)"})
  void testNewAwsSystemTestDeploymentDriver_thenThrowUncheckedIOException() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getS3()).thenThrow(new UncheckedIOException(new IOException("foo")));

    // Act and Assert
    assertThrows(UncheckedIOException.class, () -> new AwsSystemTestDeploymentDriver(parameters, clients));

    verify(clients).getS3();
  }

  /**
   * Test {@link AwsSystemTestDeploymentDriver#deployIfNotPresent(SystemTestStandaloneProperties)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSystemTestDeploymentDriver#deployIfNotPresent(SystemTestStandaloneProperties)}
   */
  @Test
  @DisplayName("Test deployIfNotPresent(SystemTestStandaloneProperties); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AwsSystemTestDeploymentDriver.deployIfNotPresent(SystemTestStandaloneProperties)"})
  void testDeployIfNotPresent_thenReturnFalse() throws AwsServiceException, SdkClientException {
    // Arrange
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.describeStacks(Mockito.<Consumer<DescribeStacksRequest.Builder>>any())).thenReturn(null);
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(cloudFormation)
        .cloudWatch(mock(CloudWatchClient.class))
        .cloudWatchEvents(mock(CloudWatchEventsClient.class))
        .cloudWatchLogs(mock(CloudWatchLogsClient.class))
        .configureHadoop(mock(UnaryOperator.class));
    Builder regionProviderResult = configureHadoopResult.dynamoDB(new AmazonDynamoDBAsyncClient())
        .ec2(mock(Ec2Client.class))
        .ecr(mock(EcrClient.class))
        .ecs(mock(EcsClient.class))
        .emr(mock(EmrClient.class))
        .emrServerless(mock(EmrServerlessClient.class))
        .lambda(mock(LambdaClient.class))
        .regionProvider(mock(AwsRegionProvider.class));
    Builder s3AsyncResult = regionProviderResult.s3(new AmazonS3Client()).s3Async(null);
    Builder skipAssumeRoleResult = s3AsyncResult.s3V2(new S3CrossRegionSyncClient(mock(S3Client.class)))
        .skipAssumeRole(true);
    Builder sqsV2Result = skipAssumeRoleResult.sqs(new AmazonSQSAsyncClient()).sqsV2(mock(SqsClient.class));
    SystemTestClients clients = sqsV2Result.sts(new AWSSecurityTokenServiceAsyncClient())
        .stsV2(mock(StsClient.class))
        .build();
    SystemTestParameters.Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    SystemTestParameters.Builder shortTestIdResult = forceStateStoreClassnameResult
        .instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters parameters = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();
    AwsSystemTestDeploymentDriver awsSystemTestDeploymentDriver = new AwsSystemTestDeploymentDriver(parameters,
        clients);

    // Act
    boolean actualDeployIfNotPresentResult = awsSystemTestDeploymentDriver
        .deployIfNotPresent(new SystemTestStandaloneProperties());

    // Assert
    verify(cloudFormation).describeStacks(isA(Consumer.class));
    assertFalse(actualDeployIfNotPresentResult);
  }
}
