package sleeper.systemtest.drivers.instance;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.STSSessionCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.systemtest.drivers.util.SystemTestClients;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.internal.crossregion.S3CrossRegionSyncClient;
import software.amazon.awssdk.services.s3.model.PutBucketAccelerateConfigurationRequest;
import software.amazon.awssdk.services.s3.model.PutBucketAccelerateConfigurationRequest.Builder;
import software.amazon.awssdk.services.sqs.SqsClient;

class AwsResetInstanceOnFirstConnectDiffblueTest {
  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect() {
    // Arrange
    AmazonDynamoDBAsyncClient amazonDynamoDBAsyncClient = new AmazonDynamoDBAsyncClient(
        PredefinedClientConfigurations.defaultConfig());
    amazonDynamoDBAsyncClient.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(amazonDynamoDBAsyncClient);
    when(clients.getS3V2()).thenReturn(new S3CrossRegionSyncClient(mock(S3Client.class)));
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
  }

  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect2() {
    // Arrange
    AmazonDynamoDBAsyncClient amazonDynamoDBAsyncClient = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    amazonDynamoDBAsyncClient.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(amazonDynamoDBAsyncClient);
    when(clients.getS3V2()).thenReturn(new S3CrossRegionSyncClient(mock(S3Client.class)));
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
  }

  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect3() {
    // Arrange
    AmazonDynamoDBAsyncClient amazonDynamoDBAsyncClient = new AmazonDynamoDBAsyncClient();
    amazonDynamoDBAsyncClient.putItemAsync("42", new HashMap<>());
    amazonDynamoDBAsyncClient.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(amazonDynamoDBAsyncClient);
    when(clients.getS3V2()).thenReturn(new S3CrossRegionSyncClient(mock(S3Client.class)));
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
  }

  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect4() {
    // Arrange
    AmazonDynamoDBAsyncClient amazonDynamoDBAsyncClient = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    amazonDynamoDBAsyncClient.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(amazonDynamoDBAsyncClient);
    when(clients.getS3V2()).thenReturn(new S3CrossRegionSyncClient(mock(S3Client.class)));
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
  }

  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect5() {
    // Arrange
    AmazonDynamoDBAsyncClient amazonDynamoDBAsyncClient = new AmazonDynamoDBAsyncClient(
        new BasicAWSCredentials("EXAMPLEakiAIOSFODNN7", "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY"));
    amazonDynamoDBAsyncClient.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(amazonDynamoDBAsyncClient);
    when(clients.getS3V2()).thenReturn(new S3CrossRegionSyncClient(mock(S3Client.class)));
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
  }

  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect6() {
    // Arrange
    AmazonDynamoDBAsyncClient amazonDynamoDBAsyncClient = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AWSSecurityTokenServiceAsyncClient()));
    amazonDynamoDBAsyncClient.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(amazonDynamoDBAsyncClient);
    when(clients.getS3V2()).thenReturn(new S3CrossRegionSyncClient(mock(S3Client.class)));
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
  }

  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect7() throws AwsServiceException, SdkClientException {
    // Arrange
    AmazonDynamoDBAsyncClient amazonDynamoDBAsyncClient = new AmazonDynamoDBAsyncClient((AWSCredentials) null);
    amazonDynamoDBAsyncClient.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    S3Client s3Client = mock(S3Client.class);
    when(s3Client.putBucketAccelerateConfiguration(Mockito.<PutBucketAccelerateConfigurationRequest>any()))
        .thenReturn(null);
    Consumer<Builder> putBucketAccelerateConfigurationRequest = mock(Consumer.class);
    doNothing().when(putBucketAccelerateConfigurationRequest).accept(Mockito.<Builder>any());

    S3CrossRegionSyncClient s3CrossRegionSyncClient = new S3CrossRegionSyncClient(s3Client);
    s3CrossRegionSyncClient.putBucketAccelerateConfiguration(putBucketAccelerateConfigurationRequest);
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(amazonDynamoDBAsyncClient);
    when(clients.getS3V2()).thenReturn(s3CrossRegionSyncClient);
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(putBucketAccelerateConfigurationRequest).accept(isA(Builder.class));
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
    verify(s3Client).putBucketAccelerateConfiguration(isA(PutBucketAccelerateConfigurationRequest.class));
  }

  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <ul>
   *   <li>Given {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients); given AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect_givenAmazonDynamoDBAsyncClient() {
    // Arrange
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(new AmazonDynamoDBAsyncClient());
    when(clients.getS3V2()).thenReturn(new S3CrossRegionSyncClient(mock(S3Client.class)));
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
  }

  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} replaceAll {@link BiFunction}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients); given HashMap() replaceAll BiFunction")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect_givenHashMapReplaceAllBiFunction() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.replaceAll(mock(BiFunction.class));

    AmazonDynamoDBAsyncClient amazonDynamoDBAsyncClient = new AmazonDynamoDBAsyncClient();
    amazonDynamoDBAsyncClient.putItemAsync("Table Name", item, "42", mock(AsyncHandler.class));
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(amazonDynamoDBAsyncClient);
    when(clients.getS3V2()).thenReturn(new S3CrossRegionSyncClient(mock(S3Client.class)));
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
  }

  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients); given 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect_givenNull() {
    // Arrange
    AmazonDynamoDBAsyncClient amazonDynamoDBAsyncClient = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    amazonDynamoDBAsyncClient.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(amazonDynamoDBAsyncClient);
    when(clients.getS3V2()).thenReturn(null);
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
  }

  /**
   * Test {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}.
   * <ul>
   *   <li>Then calls {@link Consumer#accept(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsResetInstanceOnFirstConnect#AwsResetInstanceOnFirstConnect(SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsResetInstanceOnFirstConnect(SystemTestClients); then calls accept(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsResetInstanceOnFirstConnect.<init>(SystemTestClients)"})
  void testNewAwsResetInstanceOnFirstConnect_thenCallsAccept() throws AwsServiceException, SdkClientException {
    // Arrange
    AmazonDynamoDBAsyncClient amazonDynamoDBAsyncClient = new AmazonDynamoDBAsyncClient(
        new BasicAWSCredentials("EXAMPLEakiAIOSFODNN7", "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY"));
    amazonDynamoDBAsyncClient.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    S3Client s3Client = mock(S3Client.class);
    when(s3Client.putBucketAccelerateConfiguration(Mockito.<PutBucketAccelerateConfigurationRequest>any()))
        .thenReturn(null);
    Consumer<Builder> putBucketAccelerateConfigurationRequest = mock(Consumer.class);
    doNothing().when(putBucketAccelerateConfigurationRequest).accept(Mockito.<Builder>any());

    S3CrossRegionSyncClient s3CrossRegionSyncClient = new S3CrossRegionSyncClient(s3Client);
    s3CrossRegionSyncClient.putBucketAccelerateConfiguration(putBucketAccelerateConfigurationRequest);
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(amazonDynamoDBAsyncClient);
    when(clients.getS3V2()).thenReturn(s3CrossRegionSyncClient);
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsResetInstanceOnFirstConnect(clients);

    // Assert
    verify(putBucketAccelerateConfigurationRequest).accept(isA(Builder.class));
    verify(clients).getDynamoDB();
    verify(clients).getS3V2();
    verify(clients).getSqsV2();
    verify(s3Client).putBucketAccelerateConfiguration(isA(PutBucketAccelerateConfigurationRequest.class));
  }
}
