package sleeper.clients.teardown;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.s3a.InconsistentAmazonS3Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.clients.teardown.TearDownClients.Builder;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.DeleteStackRequest;
import software.amazon.awssdk.services.cloudwatchevents.CloudWatchEventsClient;
import software.amazon.awssdk.services.ecr.EcrClient;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.emr.EmrClient;
import software.amazon.awssdk.services.emr.model.ListClustersRequest;
import software.amazon.awssdk.services.emrserverless.EmrServerlessClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.internal.crossregion.S3CrossRegionSyncClient;

class TearDownInstanceDiffblueTest {
  /**
   * Test {@link TearDownInstance#tearDown()}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TearDownInstance#tearDown()}
   */
  @Test
  @DisplayName("Test tearDown(); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TearDownInstance.tearDown()"})
  void testTearDown_thenThrowRuntimeException() throws IOException, InterruptedException, AwsServiceException,
      software.amazon.awssdk.core.exception.SdkClientException {
    // Arrange
    EmrClient emr = mock(EmrClient.class);
    when(emr.listClusters(Mockito.<Consumer<ListClustersRequest.Builder>>any()))
        .thenThrow(new RuntimeException("--------------------------------------------------------"));
    Builder emrServerlessResult = TearDownClients.builder()
        .cloudFormation(mock(CloudFormationClient.class))
        .cloudWatch(mock(CloudWatchEventsClient.class))
        .ecr(mock(EcrClient.class))
        .ecs(mock(EcsClient.class))
        .emr(emr)
        .emrServerless(mock(EmrServerlessClient.class));
    ClasspathPropertiesFileCredentialsProvider credentials = new ClasspathPropertiesFileCredentialsProvider();
    ClientConfiguration clientConfiguration = PredefinedClientConfigurations.defaultConfig();
    Builder s3Result = emrServerlessResult
        .s3(new InconsistentAmazonS3Client(credentials, clientConfiguration, new Configuration()));
    TearDownClients clients = s3Result.s3v2(new S3CrossRegionSyncClient(mock(S3Client.class))).build();
    TearDownInstance.Builder instanceIdResult = TearDownInstance.builder().clients(clients).instanceId("42");
    TearDownInstance buildResult = instanceIdResult.instanceProperties(new InstanceProperties())
        .scriptsDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertThrows(RuntimeException.class, () -> buildResult.tearDown());
    verify(emr).listClusters(isA(Consumer.class));
  }

  /**
   * Test {@link TearDownInstance#deleteStack()}.
   * <p>
   * Method under test: {@link TearDownInstance#deleteStack()}
   */
  @Test
  @DisplayName("Test deleteStack()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TearDownInstance.deleteStack()"})
  void testDeleteStack() throws AwsServiceException, software.amazon.awssdk.core.exception.SdkClientException {
    // Arrange
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.deleteStack(Mockito.<Consumer<DeleteStackRequest.Builder>>any()))
        .thenThrow(new IllegalArgumentException("Deleting instance CloudFormation stack: {}"));
    Builder emrServerlessResult = TearDownClients.builder()
        .cloudFormation(cloudFormation)
        .cloudWatch(mock(CloudWatchEventsClient.class))
        .ecr(mock(EcrClient.class))
        .ecs(mock(EcsClient.class))
        .emr(mock(EmrClient.class))
        .emrServerless(mock(EmrServerlessClient.class));
    Builder s3Result = emrServerlessResult.s3(new AmazonS3Client());
    TearDownClients clients = s3Result.s3v2(new S3CrossRegionSyncClient(mock(S3Client.class))).build();
    TearDownInstance.Builder instanceIdResult = TearDownInstance.builder().clients(clients).instanceId("42");
    TearDownInstance buildResult = instanceIdResult.instanceProperties(new InstanceProperties())
        .scriptsDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act
    buildResult.deleteStack();

    // Assert
    verify(cloudFormation).deleteStack(isA(Consumer.class));
  }

  /**
   * Test {@link TearDownInstance#deleteStack()}.
   * <ul>
   *   <li>Given {@link CloudFormationClient} {@link CloudFormationClient#deleteStack(Consumer)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TearDownInstance#deleteStack()}
   */
  @Test
  @DisplayName("Test deleteStack(); given CloudFormationClient deleteStack(Consumer) return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TearDownInstance.deleteStack()"})
  void testDeleteStack_givenCloudFormationClientDeleteStackReturnNull()
      throws AwsServiceException, software.amazon.awssdk.core.exception.SdkClientException {
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
    TearDownInstance.Builder instanceIdResult = TearDownInstance.builder().clients(clients).instanceId("42");
    TearDownInstance buildResult = instanceIdResult.instanceProperties(new InstanceProperties())
        .scriptsDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act
    buildResult.deleteStack();

    // Assert
    verify(cloudFormation).deleteStack(isA(Consumer.class));
  }

  /**
   * Test {@link TearDownInstance#loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)} with {@code s3}, {@code instanceId}.
   * <p>
   * Method under test: {@link TearDownInstance#loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)}
   */
  @Test
  @DisplayName("Test loadInstancePropertiesOrGenerateDefaults(AmazonS3, String) with 's3', 'instanceId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties TearDownInstance.loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)"})
  void testLoadInstancePropertiesOrGenerateDefaultsWithS3InstanceId() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonS3Client s3 = mock(AmazonS3Client.class);
      when(s3.getObjectAsString(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Object As String");

      // Act
      InstanceProperties actualLoadInstancePropertiesOrGenerateDefaultsResult = TearDownInstance
          .loadInstancePropertiesOrGenerateDefaults(s3, "42");

      // Assert
      verify(s3).getObjectAsString(eq("sleeper-42-config"), eq("instance.properties"));
      Map<String, String> toMapResult = actualLoadInstancePropertiesOrGenerateDefaultsResult.toMap();
      assertEquals(1, toMapResult.size());
      assertEquals("As String", toMapResult.get("Object"));
      Properties properties = actualLoadInstancePropertiesOrGenerateDefaultsResult.getProperties();
      assertEquals(1, properties.size());
      assertEquals("As String", properties.get("Object"));
      Stream<Entry<String, String>> unknownProperties = actualLoadInstancePropertiesOrGenerateDefaultsResult
          .getUnknownProperties();
      assertEquals(1, unknownProperties.limit(5).collect(Collectors.toList()).size());
    }
  }

  /**
   * Test {@link TearDownInstance#loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)} with {@code s3}, {@code instanceId}.
   * <p>
   * Method under test: {@link TearDownInstance#loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)}
   */
  @Test
  @DisplayName("Test loadInstancePropertiesOrGenerateDefaults(AmazonS3, String) with 's3', 'instanceId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties TearDownInstance.loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)"})
  void testLoadInstancePropertiesOrGenerateDefaultsWithS3InstanceId2() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonS3Client s3 = mock(AmazonS3Client.class);
      when(s3.getObjectAsString(Mockito.<String>any(), Mockito.<String>any()))
          .thenThrow(new AmazonS3Exception("An error occurred"));

      // Act
      InstanceProperties actualLoadInstancePropertiesOrGenerateDefaultsResult = TearDownInstance
          .loadInstancePropertiesOrGenerateDefaults(s3, "42");

      // Assert
      verify(s3).getObjectAsString(eq("sleeper-42-config"), eq("instance.properties"));
      Map<String, String> toMapResult = actualLoadInstancePropertiesOrGenerateDefaultsResult.toMap();
      assertEquals(20, toMapResult.size());
      assertEquals("42", toMapResult.get("sleeper.id"));
      Properties properties = actualLoadInstancePropertiesOrGenerateDefaultsResult.getProperties();
      assertEquals(20, properties.size());
      assertEquals("42-FindPartitionsToSplitPeriodicTrigger", properties.get("sleeper.partition.splitting.rule"));
      assertEquals("42-IngestTasksCreationRule", toMapResult.get("sleeper.ingest.rule"));
      assertEquals("42-TransactionLogSnapshotCreationRule",
          toMapResult.get("sleeper.statestore.transactionlog.snapshots.creation.rule"));
      assertEquals("42-TransactionLogSnapshotCreationRule",
          properties.get("sleeper.statestore.transactionlog.snapshots.creation.rule"));
      assertEquals("42-TransactionLogSnapshotDeletionRule",
          toMapResult.get("sleeper.statestore.transactionlog.snapshots.deletion.rule"));
      assertEquals("42/bulk-import-runner", properties.get("sleeper.bulk.import.eks.repo"));
      assertEquals("42/compaction-job-execution", properties.get("sleeper.compaction.repo"));
      assertEquals("42/ingest", toMapResult.get("sleeper.ingest.repo"));
      assertEquals("sleeper-42-config", properties.get("sleeper.config.bucket"));
      assertEquals("sleeper-42-query-results", toMapResult.get("sleeper.query.results.bucket"));
      assertEquals("sleeper-42-query-results", properties.get("sleeper.query.results.bucket"));
    }
  }

  /**
   * Test {@link TearDownInstance#loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)} with {@code s3}, {@code instanceId}.
   * <p>
   * Method under test: {@link TearDownInstance#loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)}
   */
  @Test
  @DisplayName("Test loadInstancePropertiesOrGenerateDefaults(AmazonS3, String) with 's3', 'instanceId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties TearDownInstance.loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)"})
  void testLoadInstancePropertiesOrGenerateDefaultsWithS3InstanceId3() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonS3Client s3 = mock(AmazonS3Client.class);
      when(s3.getObjectAsString(Mockito.<String>any(), Mockito.<String>any()))
          .thenThrow(new AmazonS3Exception("An error occurred"));

      // Act
      InstanceProperties actualLoadInstancePropertiesOrGenerateDefaultsResult = TearDownInstance
          .loadInstancePropertiesOrGenerateDefaults(s3, "42");

      // Assert
      verify(s3).getObjectAsString(eq("sleeper-42-config"), eq("instance.properties"));
      Map<String, String> toMapResult = actualLoadInstancePropertiesOrGenerateDefaultsResult.toMap();
      assertEquals(20, toMapResult.size());
      assertEquals("42", toMapResult.get("sleeper.id"));
      Properties properties = actualLoadInstancePropertiesOrGenerateDefaultsResult.getProperties();
      assertEquals(20, properties.size());
      assertEquals("42-FindPartitionsToSplitPeriodicTrigger", properties.get("sleeper.partition.splitting.rule"));
      assertEquals("42-IngestTasksCreationRule", toMapResult.get("sleeper.ingest.rule"));
      assertEquals("42-TransactionLogSnapshotCreationRule",
          toMapResult.get("sleeper.statestore.transactionlog.snapshots.creation.rule"));
      assertEquals("42-TransactionLogSnapshotCreationRule",
          properties.get("sleeper.statestore.transactionlog.snapshots.creation.rule"));
      assertEquals("42-TransactionLogSnapshotDeletionRule",
          toMapResult.get("sleeper.statestore.transactionlog.snapshots.deletion.rule"));
      assertEquals("42/bulk-import-runner", properties.get("sleeper.bulk.import.eks.repo"));
      assertEquals("42/compaction-job-execution", properties.get("sleeper.compaction.repo"));
      assertEquals("42/ingest", toMapResult.get("sleeper.ingest.repo"));
      assertEquals("sleeper-42-config", properties.get("sleeper.config.bucket"));
      assertEquals("sleeper-42-query-results", toMapResult.get("sleeper.query.results.bucket"));
      assertEquals("sleeper-42-query-results", properties.get("sleeper.query.results.bucket"));
    }
  }

  /**
   * Test {@link TearDownInstance#loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)} with {@code s3}, {@code instanceId}.
   * <p>
   * Method under test: {@link TearDownInstance#loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)}
   */
  @Test
  @DisplayName("Test loadInstancePropertiesOrGenerateDefaults(AmazonS3, String) with 's3', 'instanceId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties TearDownInstance.loadInstancePropertiesOrGenerateDefaults(AmazonS3, String)"})
  void testLoadInstancePropertiesOrGenerateDefaultsWithS3InstanceId4() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonS3Client s3 = mock(AmazonS3Client.class);
      when(s3.getObjectAsString(Mockito.<String>any(), Mockito.<String>any()))
          .thenThrow(new AmazonS3Exception("An error occurred"));

      // Act
      InstanceProperties actualLoadInstancePropertiesOrGenerateDefaultsResult = TearDownInstance
          .loadInstancePropertiesOrGenerateDefaults(s3, "Loading configuration for instance {}");

      // Assert
      verify(s3).getObjectAsString(eq("sleeper-loading configuration for instance {}-config"),
          eq("instance.properties"));
      Properties properties = actualLoadInstancePropertiesOrGenerateDefaultsResult.getProperties();
      assertEquals(20, properties.size());
      assertEquals("Loading configuration for instance {}-FindPartitionsToSplitPerio",
          properties.get("sleeper.partition.splitting.rule"));
      assertEquals("Loading configuration for instance {}-TransactionLogSnapshotCrea",
          properties.get("sleeper.statestore.transactionlog.snapshots.creation.rule"));
      assertEquals("Loading configuration for instance {}/bulk-import-runner",
          properties.get("sleeper.bulk.import.eks.repo"));
      assertEquals("Loading configuration for instance {}/compaction-job-execution",
          properties.get("sleeper.compaction.repo"));
      assertEquals("sleeper-Loading configuration for instance {}-query-results",
          properties.get("sleeper.query.results.bucket"));
      assertEquals("sleeper-loading configuration for instance {}-config", properties.get("sleeper.config.bucket"));
      SleeperPropertyIndex<InstanceProperty> propertiesIndex = actualLoadInstancePropertiesOrGenerateDefaultsResult
          .getPropertiesIndex();
      assertEquals(131, propertiesIndex.getCdkDefined().size());
      assertEquals(325, propertiesIndex.getUserDefined().size());
      assertEquals(properties, actualLoadInstancePropertiesOrGenerateDefaultsResult.toMap());
    }
  }
}
