package sleeper.systemtest.drivers.instance;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import java.util.function.Function;
import java.util.function.UnaryOperator;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.configuration.table.index.DynamoDBTableIndex;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.table.TableIndex;
import sleeper.systemtest.drivers.util.SystemTestClients;
import sleeper.systemtest.drivers.util.SystemTestClients.Builder;
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

class AwsSleeperTablesDriverDiffblueTest {
  /**
   * Test {@link AwsSleeperTablesDriver#AwsSleeperTablesDriver(SystemTestClients)}.
   * <ul>
   *   <li>Then calls {@link SystemTestClients#createHadoopConf()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSleeperTablesDriver#AwsSleeperTablesDriver(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsSleeperTablesDriver(SystemTestClients); then calls createHadoopConf()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsSleeperTablesDriver.<init>(SystemTestClients)"})
  void testNewAwsSleeperTablesDriver_thenCallsCreateHadoopConf() {
    // Arrange
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(new AmazonDynamoDBAsyncClient());
    when(clients.getS3()).thenReturn(new AmazonS3Client());
    when(clients.createHadoopConf()).thenReturn(new Configuration());

    // Act
    new AwsSleeperTablesDriver(clients);

    // Assert
    verify(clients).createHadoopConf();
    verify(clients).getDynamoDB();
    verify(clients).getS3();
  }

  /**
   * Test {@link AwsSleeperTablesDriver#AwsSleeperTablesDriver(SystemTestClients)}.
   * <ul>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSleeperTablesDriver#AwsSleeperTablesDriver(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsSleeperTablesDriver(SystemTestClients); then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsSleeperTablesDriver.<init>(SystemTestClients)"})
  void testNewAwsSleeperTablesDriver_thenThrowUncheckedIOException() {
    // Arrange
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getS3()).thenThrow(new UncheckedIOException(new IOException("foo")));

    // Act and Assert
    assertThrows(UncheckedIOException.class, () -> new AwsSleeperTablesDriver(clients));
    verify(clients).getS3();
  }

  /**
   * Test {@link AwsSleeperTablesDriver#createTablePropertiesProvider(InstanceProperties)}.
   * <ul>
   *   <li>Then calls {@link Function#apply(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSleeperTablesDriver#createTablePropertiesProvider(InstanceProperties)}
   */
  @Test
  @DisplayName("Test createTablePropertiesProvider(InstanceProperties); then calls apply(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.properties.table.TablePropertiesProvider AwsSleeperTablesDriver.createTablePropertiesProvider(InstanceProperties)"})
  void testCreateTablePropertiesProvider_thenCallsApply() {
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
    AwsSleeperTablesDriver awsSleeperTablesDriver = new AwsSleeperTablesDriver(clients);

    // Act
    awsSleeperTablesDriver.createTablePropertiesProvider(new InstanceProperties());

    // Assert
    verify(configureHadoop).apply(isA(Configuration.class));
  }

  /**
   * Test {@link AwsSleeperTablesDriver#createStateStoreProvider(InstanceProperties)}.
   * <ul>
   *   <li>Then calls {@link Function#apply(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSleeperTablesDriver#createStateStoreProvider(InstanceProperties)}
   */
  @Test
  @DisplayName("Test createStateStoreProvider(InstanceProperties); then calls apply(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.statestore.StateStoreProvider AwsSleeperTablesDriver.createStateStoreProvider(InstanceProperties)"})
  void testCreateStateStoreProvider_thenCallsApply() {
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
    AwsSleeperTablesDriver awsSleeperTablesDriver = new AwsSleeperTablesDriver(clients);

    // Act
    awsSleeperTablesDriver.createStateStoreProvider(new InstanceProperties());

    // Assert
    verify(configureHadoop).apply(isA(Configuration.class));
  }

  /**
   * Test {@link AwsSleeperTablesDriver#tableIndex(InstanceProperties)}.
   * <ul>
   *   <li>Then return {@link DynamoDBTableIndex}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSleeperTablesDriver#tableIndex(InstanceProperties)}
   */
  @Test
  @DisplayName("Test tableIndex(InstanceProperties); then return DynamoDBTableIndex")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableIndex AwsSleeperTablesDriver.tableIndex(InstanceProperties)"})
  void testTableIndex_thenReturnDynamoDBTableIndex() {
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
    AwsSleeperTablesDriver awsSleeperTablesDriver = new AwsSleeperTablesDriver(clients);

    // Act
    TableIndex actualTableIndexResult = awsSleeperTablesDriver.tableIndex(new InstanceProperties());

    // Assert
    verify(configureHadoop).apply(isA(Configuration.class));
    assertTrue(actualTableIndexResult instanceof DynamoDBTableIndex);
  }
}
