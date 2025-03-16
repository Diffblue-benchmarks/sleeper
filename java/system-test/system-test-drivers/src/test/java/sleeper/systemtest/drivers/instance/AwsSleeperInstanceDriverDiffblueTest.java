package sleeper.systemtest.drivers.instance;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.drivers.util.SystemTestClients;
import sleeper.systemtest.drivers.util.SystemTestClients.Builder;
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

class AwsSleeperInstanceDriverDiffblueTest {
  /**
   * Test {@link AwsSleeperInstanceDriver#AwsSleeperInstanceDriver(SystemTestParameters, SystemTestClients)}.
   * <ul>
   *   <li>Then calls {@link SystemTestClients#getCloudFormation()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSleeperInstanceDriver#AwsSleeperInstanceDriver(SystemTestParameters, SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsSleeperInstanceDriver(SystemTestParameters, SystemTestClients); then calls getCloudFormation()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsSleeperInstanceDriver.<init>(SystemTestParameters, SystemTestClients)"})
  void testNewAwsSleeperInstanceDriver_thenCallsGetCloudFormation() {
    // Arrange
    SystemTestParameters parameters = mock(SystemTestParameters.class);
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(new AmazonDynamoDBAsyncClient());
    when(clients.getS3()).thenReturn(new AmazonS3Client());
    when(clients.getSts()).thenReturn(new AWSSecurityTokenServiceAsyncClient());
    when(clients.getRegionProvider()).thenReturn(mock(AwsRegionProvider.class));
    when(clients.getCloudFormation()).thenReturn(mock(CloudFormationClient.class));
    when(clients.getEcr()).thenReturn(mock(EcrClient.class));
    when(clients.getS3V2()).thenReturn(new S3CrossRegionSyncClient(mock(S3Client.class)));
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsSleeperInstanceDriver(parameters, clients);

    // Assert
    verify(clients).getCloudFormation();
    verify(clients, atLeast(1)).getDynamoDB();
    verify(clients).getEcr();
    verify(clients).getRegionProvider();
    verify(clients).getS3();
    verify(clients, atLeast(1)).getS3V2();
    verify(clients).getSqsV2();
    verify(clients).getSts();
  }

  /**
   * Test {@link AwsSleeperInstanceDriver#redeploy(InstanceProperties, List)}.
   * <p>
   * Method under test: {@link AwsSleeperInstanceDriver#redeploy(InstanceProperties, List)}
   */
  @Test
  @DisplayName("Test redeploy(InstanceProperties, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsSleeperInstanceDriver.redeploy(InstanceProperties, List)"})
  void testRedeploy() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.writeString(Mockito.<Path>any(), Mockito.<CharSequence>any(), isA(OpenOption[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new PipedWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      ArrayList<Path> pathList = new ArrayList<>();
      Stream<Path> streamResult = pathList.stream();
      mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class))).thenReturn(streamResult);
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
      AwsSleeperInstanceDriver awsSleeperInstanceDriver = new AwsSleeperInstanceDriver(parameters, clients);
      InstanceProperties instanceProperties = new InstanceProperties();

      // Act and Assert
      assertThrows(UncheckedIOException.class,
          () -> awsSleeperInstanceDriver.redeploy(instanceProperties, new ArrayList<>()));
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      mockFiles.verify(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)));
    }
  }

  /**
   * Test {@link AwsSleeperInstanceDriver#redeploy(InstanceProperties, List)}.
   * <ul>
   *   <li>Given {@link Files} {@link Files#writeString(Path, CharSequence, OpenOption[])} throw {@link IOException#IOException(String)} with space.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSleeperInstanceDriver#redeploy(InstanceProperties, List)}
   */
  @Test
  @DisplayName("Test redeploy(InstanceProperties, List); given Files writeString(Path, CharSequence, OpenOption[]) throw IOException(String) with space")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsSleeperInstanceDriver.redeploy(InstanceProperties, List)"})
  void testRedeploy_givenFilesWriteStringThrowIOExceptionWithSpace() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.writeString(Mockito.<Path>any(), Mockito.<CharSequence>any(), isA(OpenOption[].class)))
          .thenThrow(new IOException(" "));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      ArrayList<Path> pathList = new ArrayList<>();
      Stream<Path> streamResult = pathList.stream();
      mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class))).thenReturn(streamResult);
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
      AwsSleeperInstanceDriver awsSleeperInstanceDriver = new AwsSleeperInstanceDriver(parameters, clients);
      InstanceProperties instanceProperties = new InstanceProperties();

      // Act and Assert
      assertThrows(UncheckedIOException.class,
          () -> awsSleeperInstanceDriver.redeploy(instanceProperties, new ArrayList<>()));
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      mockFiles.verify(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)));
      mockFiles
          .verify(() -> Files.writeString(Mockito.<Path>any(), Mockito.<CharSequence>any(), isA(OpenOption[].class)));
    }
  }

  /**
   * Test {@link AwsSleeperInstanceDriver#redeploy(InstanceProperties, List)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsSleeperInstanceDriver#redeploy(InstanceProperties, List)}
   */
  @Test
  @DisplayName("Test redeploy(InstanceProperties, List); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsSleeperInstanceDriver.redeploy(InstanceProperties, List)"})
  void testRedeploy_thenThrowRuntimeException() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.writeString(Mockito.<Path>any(), Mockito.<CharSequence>any(), isA(OpenOption[].class)))
          .thenThrow(new RuntimeException(" "));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      ArrayList<Path> pathList = new ArrayList<>();
      Stream<Path> streamResult = pathList.stream();
      mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class))).thenReturn(streamResult);
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
      AwsSleeperInstanceDriver awsSleeperInstanceDriver = new AwsSleeperInstanceDriver(parameters, clients);
      InstanceProperties instanceProperties = new InstanceProperties();

      // Act and Assert
      assertThrows(RuntimeException.class,
          () -> awsSleeperInstanceDriver.redeploy(instanceProperties, new ArrayList<>()));
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      mockFiles.verify(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)));
      mockFiles
          .verify(() -> Files.writeString(Mockito.<Path>any(), Mockito.<CharSequence>any(), isA(OpenOption[].class)));
    }
  }
}
