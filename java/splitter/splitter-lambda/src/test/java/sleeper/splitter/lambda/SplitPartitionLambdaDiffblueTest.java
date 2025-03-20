package sleeper.splitter.lambda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.monitoring.internal.ClientSideMonitoringRequestHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSBatchResponse;
import com.amazonaws.services.lambda.runtime.events.SQSBatchResponse.BatchItemFailure;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.AddPermissionRequest;
import com.amazonaws.services.sqs.model.AddPermissionResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class SplitPartitionLambdaDiffblueTest {
  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    sqsClient.addPermissionAsync(new AddPermissionRequest());

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda3() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest());

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda4() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    sqsClient.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));
    sqsClient.addPermissionAsync(new AddPermissionRequest());

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda5() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda6() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));
    sqsClient.addPermissionAsync(new AddPermissionRequest());

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda7() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));
    sqsClient.addPermissionAsync(new AddPermissionRequest());

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda8() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    HdfsConfiguration conf = new HdfsConfiguration();
    conf.addResource("", true);
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient((AWSCredentials) null);
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));
    sqsClient.addPermissionAsync(new AddPermissionRequest());

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier); given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda_givenPutItemRequest() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();

    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    dynamoDBClient.putItemAsync(new PutItemRequest());

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, new AmazonSQSAsyncClient(),
        mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <ul>
   *   <li>When {@link AmazonSQSAsyncClient#AmazonSQSAsyncClient()}.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier); when AmazonSQSAsyncClient(); then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda_whenAmazonSQSAsyncClient_thenCallsGetBoolean() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, new AmazonSQSAsyncClient(),
        mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <ul>
   *   <li>When {@link HdfsConfiguration#HdfsConfiguration()} addResource empty string and {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier); when HdfsConfiguration() addResource empty string and 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda_whenHdfsConfigurationAddResourceEmptyStringAndTrue() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    HdfsConfiguration conf = new HdfsConfiguration();
    conf.addResource("", true);
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));
    sqsClient.addPermissionAsync(new AddPermissionRequest());

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <ul>
   *   <li>When {@link HdfsConfiguration#HdfsConfiguration()} addResource empty string and {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier); when HdfsConfiguration() addResource empty string and 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda_whenHdfsConfigurationAddResourceEmptyStringAndTrue2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    HdfsConfiguration conf = new HdfsConfiguration();
    conf.addResource("", true);
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));
    sqsClient.addPermissionAsync(new AddPermissionRequest());

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}.
   * <ul>
   *   <li>When {@link YarnConfiguration#YarnConfiguration()} addResource empty string and {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionLambda#SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionLambda(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier); when YarnConfiguration() addResource empty string and 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartitionLambda.<init>(InstanceProperties, Configuration, AmazonS3, AmazonDynamoDB, AmazonSQS, Supplier)"})
  void testNewSplitPartitionLambda_whenYarnConfigurationAddResourceEmptyStringAndTrue() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    YarnConfiguration conf = new YarnConfiguration();
    conf.addResource("", true);
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));
    sqsClient.addPermissionAsync(new AddPermissionRequest());

    // Act
    new SplitPartitionLambda(instanceProperties, conf, s3Client, dynamoDBClient, sqsClient, mock(Supplier.class));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Disabled
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext() {
    // Arrange
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addPermissionAsync(new AddPermissionRequest());
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(new SQSMessage());

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act and Assert
    List<BatchItemFailure> batchItemFailures = splitPartitionLambda.handleRequest(event, mock(Context.class))
        .getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext2() {
    // Arrange
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addPermissionAsync(new AddPermissionRequest());
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(new SQSMessage());

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act and Assert
    List<BatchItemFailure> batchItemFailures = splitPartitionLambda.handleRequest(event, mock(Context.class))
        .getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext3() {
    // Arrange
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addPermissionAsync(new AddPermissionRequest());
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody("");

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(sqsMessage);

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act and Assert
    List<BatchItemFailure> batchItemFailures = splitPartitionLambda.handleRequest(event, mock(Context.class))
        .getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext4() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    s3Client.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addPermissionAsync(new AddPermissionRequest());
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody("");

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(sqsMessage);

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act and Assert
    List<BatchItemFailure> batchItemFailures = splitPartitionLambda.handleRequest(event, mock(Context.class))
        .getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext5() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client(PredefinedClientConfigurations.defaultConfig());
    s3Client.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addPermissionAsync(new AddPermissionRequest());
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody("");

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(sqsMessage);

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act and Assert
    List<BatchItemFailure> batchItemFailures = splitPartitionLambda.handleRequest(event, mock(Context.class))
        .getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext6() {
    // Arrange
    AddPermissionRequest request = new AddPermissionRequest();
    request.putCustomRequestHeader("U.s3-fips-us-gov-west-1.amazonaws.com", "U.s3-external-1.amazonaws.com");

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addPermissionAsync(request);
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration(true);
    AmazonS3Client s3Client = new AmazonS3Client();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody("");

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(sqsMessage);

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act and Assert
    List<BatchItemFailure> batchItemFailures = splitPartitionLambda.handleRequest(event, mock(Context.class))
        .getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext7() {
    // Arrange
    AddPermissionRequest request = new AddPermissionRequest();
    request.putCustomQueryParameter("U.s3-fips-us-gov-west-1.amazonaws.com", "U.s3-external-1.amazonaws.com");
    request.putCustomRequestHeader("U.s3-fips-us-gov-west-1.amazonaws.com", "U.s3-external-1.amazonaws.com");

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addPermissionAsync(request);
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration(true);
    AmazonS3Client s3Client = new AmazonS3Client();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody("");

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(sqsMessage);

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act and Assert
    List<BatchItemFailure> batchItemFailures = splitPartitionLambda.handleRequest(event, mock(Context.class))
        .getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Given {@link Configuration#Configuration(boolean)} with loadDefaults is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; given Configuration(boolean) with loadDefaults is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_givenConfigurationWithLoadDefaultsIsTrue() {
    // Arrange
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addPermissionAsync(new AddPermissionRequest());
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration(true);
    AmazonS3Client s3Client = new AmazonS3Client();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody("");

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(sqsMessage);

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act and Assert
    List<BatchItemFailure> batchItemFailures = splitPartitionLambda.handleRequest(event, mock(Context.class))
        .getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Given {@link Configuration#Configuration(Configuration)} with other is {@link Configuration#Configuration()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; given Configuration(Configuration) with other is Configuration()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_givenConfigurationWithOtherIsConfiguration() {
    // Arrange
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addPermissionAsync(new AddPermissionRequest());
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration(new Configuration());
    AmazonS3Client s3Client = new AmazonS3Client();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody("");

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(sqsMessage);

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act and Assert
    List<BatchItemFailure> batchItemFailures = splitPartitionLambda.handleRequest(event, mock(Context.class))
        .getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Given {@link SQSMessage} (default constructor) Body is {@code Not all who wander are lost}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; given SQSMessage (default constructor) Body is 'Not all who wander are lost'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_givenSQSMessageBodyIsNotAllWhoWanderAreLost() {
    // Arrange
    AsyncHandler<AddPermissionRequest, AddPermissionResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException("foo")).when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    sqsClient.addPermissionAsync(new AddPermissionRequest(), asyncHandler);
    sqsClient.addPermissionAsync(new AddPermissionRequest());
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody("Not all who wander are lost");

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(sqsMessage);

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act
    SQSBatchResponse actualHandleRequestResult = splitPartitionLambda.handleRequest(event, mock(Context.class));

    // Assert
    verify(asyncHandler).onError(isA(Exception.class));
    List<BatchItemFailure> batchItemFailures = actualHandleRequestResult.getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Then calls {@link AsyncHandler#onError(Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; then calls onError(Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_thenCallsOnError() {
    // Arrange
    AsyncHandler<AddPermissionRequest, AddPermissionResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException("foo")).when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    sqsClient.addPermissionAsync(new AddPermissionRequest(), asyncHandler);
    sqsClient.addPermissionAsync(new AddPermissionRequest());
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody("");

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(sqsMessage);

    SQSEvent event = new SQSEvent();
    event.setRecords(records);

    // Act
    SQSBatchResponse actualHandleRequestResult = splitPartitionLambda.handleRequest(event, mock(Context.class));

    // Assert
    verify(asyncHandler).onError(isA(Exception.class));
    List<BatchItemFailure> batchItemFailures = actualHandleRequestResult.getBatchItemFailures();
    assertEquals(1, batchItemFailures.size());
    assertNull(batchItemFailures.get(0).getItemIdentifier());
  }

  /**
   * Test {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Then return BatchItemFailures Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; then return BatchItemFailures Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SQSBatchResponse SplitPartitionLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_thenReturnBatchItemFailuresEmpty() {
    // Arrange
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
    sqsClient.addPermissionAsync(new AddPermissionRequest());
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration conf = new Configuration();
    AmazonS3Client s3Client = new AmazonS3Client();
    SplitPartitionLambda splitPartitionLambda = new SplitPartitionLambda(instanceProperties, conf, s3Client,
        new AmazonDynamoDBAsyncClient(), sqsClient, mock(Supplier.class));

    SQSEvent event = new SQSEvent();
    event.setRecords(new ArrayList<>());

    // Act and Assert
    assertTrue(splitPartitionLambda.handleRequest(event, mock(Context.class)).getBatchItemFailures().isEmpty());
  }
}
