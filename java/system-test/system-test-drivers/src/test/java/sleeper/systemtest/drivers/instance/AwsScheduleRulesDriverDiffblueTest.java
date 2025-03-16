package sleeper.systemtest.drivers.instance;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.deploy.SleeperScheduleRule;
import sleeper.core.deploy.SleeperScheduleRule.InstanceRule;
import sleeper.systemtest.drivers.util.SystemTestClients;
import sleeper.systemtest.drivers.util.SystemTestClients.Builder;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.providers.AwsRegionProvider;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatchevents.CloudWatchEventsClient;
import software.amazon.awssdk.services.cloudwatchevents.model.DisableRuleRequest;
import software.amazon.awssdk.services.cloudwatchevents.model.EnableRuleRequest;
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

class AwsScheduleRulesDriverDiffblueTest {
  /**
   * Test {@link AwsScheduleRulesDriver#AwsScheduleRulesDriver(SystemTestClients)}.
   * <p>
   * Method under test: {@link AwsScheduleRulesDriver#AwsScheduleRulesDriver(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsScheduleRulesDriver(SystemTestClients)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsScheduleRulesDriver.<init>(SystemTestClients)"})
  void testNewAwsScheduleRulesDriver() {
    // Arrange
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getCloudWatchEvents()).thenReturn(mock(CloudWatchEventsClient.class));

    // Act
    new AwsScheduleRulesDriver(clients);

    // Assert
    verify(clients).getCloudWatchEvents();
  }

  /**
   * Test {@link AwsScheduleRulesDriver#enableRule(InstanceRule)}.
   * <ul>
   *   <li>Then calls {@link CloudWatchEventsClient#enableRule(Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsScheduleRulesDriver#enableRule(InstanceRule)}
   */
  @Test
  @DisplayName("Test enableRule(InstanceRule); then calls enableRule(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsScheduleRulesDriver.enableRule(InstanceRule)"})
  void testEnableRule_thenCallsEnableRule() throws AwsServiceException, SdkClientException {
    // Arrange
    CloudWatchEventsClient cloudWatchEvents = mock(CloudWatchEventsClient.class);
    when(cloudWatchEvents.enableRule(Mockito.<Consumer<EnableRuleRequest.Builder>>any())).thenReturn(null);
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
        .cloudWatch(mock(CloudWatchClient.class))
        .cloudWatchEvents(cloudWatchEvents)
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

    // Act
    (new AwsScheduleRulesDriver(clients)).enableRule(mock(InstanceRule.class));

    // Assert
    verify(cloudWatchEvents).enableRule(isA(Consumer.class));
  }

  /**
   * Test {@link AwsScheduleRulesDriver#disableRule(InstanceRule)}.
   * <ul>
   *   <li>Then calls {@link CloudWatchEventsClient#disableRule(Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsScheduleRulesDriver#disableRule(InstanceRule)}
   */
  @Test
  @DisplayName("Test disableRule(InstanceRule); then calls disableRule(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsScheduleRulesDriver.disableRule(InstanceRule)"})
  void testDisableRule_thenCallsDisableRule() throws AwsServiceException, SdkClientException {
    // Arrange
    CloudWatchEventsClient cloudWatchEvents = mock(CloudWatchEventsClient.class);
    when(cloudWatchEvents.disableRule(Mockito.<Consumer<DisableRuleRequest.Builder>>any())).thenReturn(null);
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
        .cloudWatch(mock(CloudWatchClient.class))
        .cloudWatchEvents(cloudWatchEvents)
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

    // Act
    (new AwsScheduleRulesDriver(clients)).disableRule(mock(InstanceRule.class));

    // Assert
    verify(cloudWatchEvents).disableRule(isA(Consumer.class));
  }
}
