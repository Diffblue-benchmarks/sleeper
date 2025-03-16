package sleeper.systemtest.drivers.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.UnaryOperator;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.systemtest.drivers.instance.AwsScheduleRulesDriver;
import sleeper.systemtest.drivers.instance.AwsSleeperInstanceDriver;
import sleeper.systemtest.drivers.instance.AwsSleeperTablesDriver;
import sleeper.systemtest.drivers.instance.AwsSystemTestDeploymentDriver;
import sleeper.systemtest.drivers.sourcedata.AwsGeneratedIngestSourceFilesDriver;
import sleeper.systemtest.drivers.statestore.AwsSnapshotsDriver;
import sleeper.systemtest.drivers.util.SystemTestClients.Builder;
import sleeper.systemtest.dsl.SystemTestDrivers;
import sleeper.systemtest.dsl.instance.AssumeAdminRoleDriver;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SleeperTablesDriver;
import sleeper.systemtest.dsl.instance.SystemTestDeploymentDriver;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import software.amazon.awssdk.regions.providers.AwsRegionProvider;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
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

class AwsSystemTestDriversDiffblueTest {
  /**
   * Test {@link AwsSystemTestDrivers#systemTestDeployment(SystemTestParameters)}.
   * <ul>
   *   <li>Then return {@link AwsSystemTestDeploymentDriver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSystemTestDrivers#systemTestDeployment(SystemTestParameters)}
   */
  @Test
  @DisplayName("Test systemTestDeployment(SystemTestParameters); then return AwsSystemTestDeploymentDriver")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestDeploymentDriver AwsSystemTestDrivers.systemTestDeployment(SystemTestParameters)"})
  void testSystemTestDeployment_thenReturnAwsSystemTestDeploymentDriver() {
    // Arrange
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
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

    // Act and Assert
    assertTrue((new AwsSystemTestDrivers(clients))
        .systemTestDeployment(mock(SystemTestParameters.class)) instanceof AwsSystemTestDeploymentDriver);
  }

  /**
   * Test {@link AwsSystemTestDrivers#instance(SystemTestParameters)}.
   * <ul>
   *   <li>Then return {@link AwsSleeperInstanceDriver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSystemTestDrivers#instance(SystemTestParameters)}
   */
  @Test
  @DisplayName("Test instance(SystemTestParameters); then return AwsSleeperInstanceDriver")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.systemtest.dsl.instance.SleeperInstanceDriver AwsSystemTestDrivers.instance(SystemTestParameters)"})
  void testInstance_thenReturnAwsSleeperInstanceDriver() {
    // Arrange
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
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

    // Act and Assert
    assertTrue((new AwsSystemTestDrivers(clients))
        .instance(mock(SystemTestParameters.class)) instanceof AwsSleeperInstanceDriver);
  }

  /**
   * Test {@link AwsSystemTestDrivers#assumeAdminRole()}.
   * <p>
   * Method under test: {@link AwsSystemTestDrivers#assumeAdminRole()}
   */
  @Test
  @DisplayName("Test assumeAdminRole()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssumeAdminRoleDriver AwsSystemTestDrivers.assumeAdminRole()"})
  void testAssumeAdminRole() {
    // Arrange
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
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

    // Act
    AssumeAdminRoleDriver actualAssumeAdminRoleResult = (new AwsSystemTestDrivers(clients)).assumeAdminRole();
    SystemTestDrivers actualAssumeAdminRoleResult2 = actualAssumeAdminRoleResult
        .assumeAdminRole(new InstanceProperties());

    // Assert
    assertTrue(actualAssumeAdminRoleResult2.schedules() instanceof AwsScheduleRulesDriver);
    assertTrue(actualAssumeAdminRoleResult2.snapshots() instanceof AwsSnapshotsDriver);
    assertTrue(actualAssumeAdminRoleResult2 instanceof AwsSystemTestDrivers);
  }

  /**
   * Test {@link AwsSystemTestDrivers#assumeAdminRole()}.
   * <p>
   * Method under test: {@link AwsSystemTestDrivers#assumeAdminRole()}
   */
  @Test
  @DisplayName("Test assumeAdminRole()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssumeAdminRoleDriver AwsSystemTestDrivers.assumeAdminRole()"})
  void testAssumeAdminRole2() {
    // Arrange
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
        .cloudWatch(mock(CloudWatchClient.class))
        .cloudWatchEvents(mock(CloudWatchEventsClient.class))
        .cloudWatchLogs(mock(CloudWatchLogsClient.class))
        .configureHadoop(mock(UnaryOperator.class));
    Builder regionProviderResult = configureHadoopResult
        .dynamoDB(new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig()))
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
    AssumeAdminRoleDriver actualAssumeAdminRoleResult = (new AwsSystemTestDrivers(clients)).assumeAdminRole();
    SystemTestDrivers actualAssumeAdminRoleResult2 = actualAssumeAdminRoleResult
        .assumeAdminRole(new InstanceProperties());

    // Assert
    assertTrue(actualAssumeAdminRoleResult2.schedules() instanceof AwsScheduleRulesDriver);
    assertTrue(actualAssumeAdminRoleResult2.snapshots() instanceof AwsSnapshotsDriver);
    assertTrue(actualAssumeAdminRoleResult2 instanceof AwsSystemTestDrivers);
  }

  /**
   * Test {@link AwsSystemTestDrivers#tables(SystemTestParameters)}.
   * <ul>
   *   <li>Then return {@link AwsSleeperTablesDriver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSystemTestDrivers#tables(SystemTestParameters)}
   */
  @Test
  @DisplayName("Test tables(SystemTestParameters); then return AwsSleeperTablesDriver")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SleeperTablesDriver AwsSystemTestDrivers.tables(SystemTestParameters)"})
  void testTables_thenReturnAwsSleeperTablesDriver() {
    // Arrange
    UnaryOperator<Configuration> configureHadoop = mock(UnaryOperator.class);
    when(configureHadoop.apply(Mockito.<Configuration>any())).thenReturn(new Configuration());
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
        .cloudWatch(mock(CloudWatchClient.class))
        .cloudWatchEvents(mock(CloudWatchEventsClient.class))
        .cloudWatchLogs(mock(CloudWatchLogsClient.class))
        .configureHadoop(configureHadoop);
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
    SleeperTablesDriver actualTablesResult = (new AwsSystemTestDrivers(clients))
        .tables(mock(SystemTestParameters.class));

    // Assert
    verify(configureHadoop).apply(isA(Configuration.class));
    assertTrue(actualTablesResult instanceof AwsSleeperTablesDriver);
  }

  /**
   * Test {@link AwsSystemTestDrivers#generatedSourceFiles(SystemTestParameters, DeployedSystemTestResources)}.
   * <ul>
   *   <li>Then return {@link AwsGeneratedIngestSourceFilesDriver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSystemTestDrivers#generatedSourceFiles(SystemTestParameters, DeployedSystemTestResources)}
   */
  @Test
  @DisplayName("Test generatedSourceFiles(SystemTestParameters, DeployedSystemTestResources); then return AwsGeneratedIngestSourceFilesDriver")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.systemtest.dsl.sourcedata.GeneratedIngestSourceFilesDriver AwsSystemTestDrivers.generatedSourceFiles(SystemTestParameters, DeployedSystemTestResources)"})
  void testGeneratedSourceFiles_thenReturnAwsGeneratedIngestSourceFilesDriver() {
    // Arrange
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
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
    AwsSystemTestDrivers awsSystemTestDrivers = new AwsSystemTestDrivers(clients);
    SystemTestParameters parameters = mock(SystemTestParameters.class);

    // Act and Assert
    assertTrue(awsSystemTestDrivers.generatedSourceFiles(parameters,
        new DeployedSystemTestResources(mock(SystemTestParameters.class),
            mock(SystemTestDeploymentDriver.class))) instanceof AwsGeneratedIngestSourceFilesDriver);
  }

  /**
   * Test {@link AwsSystemTestDrivers#snapshots()}.
   * <ul>
   *   <li>Then return {@link AwsSnapshotsDriver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSystemTestDrivers#snapshots()}
   */
  @Test
  @DisplayName("Test snapshots(); then return AwsSnapshotsDriver")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.systemtest.dsl.snapshot.SnapshotsDriver AwsSystemTestDrivers.snapshots()"})
  void testSnapshots_thenReturnAwsSnapshotsDriver() {
    // Arrange
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
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

    // Act and Assert
    assertTrue((new AwsSystemTestDrivers(clients)).snapshots() instanceof AwsSnapshotsDriver);
  }

  /**
   * Test {@link AwsSystemTestDrivers#schedules()}.
   * <ul>
   *   <li>Then return {@link AwsScheduleRulesDriver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSystemTestDrivers#schedules()}
   */
  @Test
  @DisplayName("Test schedules(); then return AwsScheduleRulesDriver")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.systemtest.dsl.instance.ScheduleRulesDriver AwsSystemTestDrivers.schedules()"})
  void testSchedules_thenReturnAwsScheduleRulesDriver() {
    // Arrange
    Builder configureHadoopResult = SystemTestClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
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

    // Act and Assert
    assertTrue((new AwsSystemTestDrivers(clients)).schedules() instanceof AwsScheduleRulesDriver);
  }
}
