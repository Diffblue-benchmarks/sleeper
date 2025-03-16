package sleeper.ingest.runner.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClient;
import com.amazonaws.services.cloudwatch.model.PutAnomalyDetectorRequest;
import com.amazonaws.services.cloudwatch.model.PutCompositeAlarmRequest;
import com.amazonaws.services.cloudwatch.model.PutInsightRuleRequest;
import com.amazonaws.services.cloudwatch.model.PutManagedInsightRulesRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Optional;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.s3a.statistics.impl.AwsStatisticsCollector;
import org.apache.hadoop.fs.s3a.statistics.impl.EmptyS3AStatisticsContext;
import org.apache.hadoop.fs.s3a.statistics.impl.StatisticsFromAwsSdkImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableIndex;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.job.IngestJobTracker;
import sleeper.ingest.core.IngestTask;
import sleeper.ingest.core.IngestTask.MessageHandle;
import sleeper.ingest.core.job.IngestJob;
import sleeper.ingest.core.job.IngestJob.Builder;
import sleeper.ingest.runner.task.IngestJobQueueConsumer.SqsMessageHandle;
import sleeper.job.common.action.Action;
import sleeper.job.common.action.MessageReference;
import sleeper.job.common.action.thread.PeriodicActionRunnable;

class IngestJobQueueConsumerDiffblueTest {
  /**
   * Test {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}
   */
  @Test
  @DisplayName("Test new IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void IngestJobQueueConsumer.<init>(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)"})
  void testNewIngestJobQueueConsumer() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      ReceiveMessageResult receiveMessageResult = mock(ReceiveMessageResult.class);
      when(receiveMessageResult.getMessages()).thenReturn(new ArrayList<>());
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(receiveMessageResult);

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient(
          new BasicAWSCredentials("EXAMPLEakiAIOSFODNN7", "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY"));
      cloudWatchClient.putCompositeAlarmAsync(new PutCompositeAlarmRequest());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(receiveMessageResult).getMessages();
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}
   */
  @Test
  @DisplayName("Test new IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void IngestJobQueueConsumer.<init>(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)"})
  void testNewIngestJobQueueConsumer2() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      ReceiveMessageResult receiveMessageResult = mock(ReceiveMessageResult.class);
      when(receiveMessageResult.getMessages()).thenReturn(new ArrayList<>());
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(receiveMessageResult);

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient(
          new BasicSessionCredentials("EXAMPLEakiAIOSFODNN7", "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY", "ABC123"));
      cloudWatchClient.putCompositeAlarmAsync(new PutCompositeAlarmRequest());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(receiveMessageResult).getMessages();
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}
   */
  @Test
  @DisplayName("Test new IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void IngestJobQueueConsumer.<init>(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)"})
  void testNewIngestJobQueueConsumer3() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      ReceiveMessageResult receiveMessageResult = mock(ReceiveMessageResult.class);
      when(receiveMessageResult.getMessages()).thenReturn(new ArrayList<>());
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(receiveMessageResult);

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient(
          mock(AnonymousAWSCredentials.class));
      cloudWatchClient.putCompositeAlarmAsync(new PutCompositeAlarmRequest());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(receiveMessageResult).getMessages();
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}.
   * <ul>
   *   <li>Given {@link PutManagedInsightRulesRequest} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}
   */
  @Test
  @DisplayName("Test new IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker); given PutManagedInsightRulesRequest (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void IngestJobQueueConsumer.<init>(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)"})
  void testNewIngestJobQueueConsumer_givenPutManagedInsightRulesRequest() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
      cloudWatchClient.putManagedInsightRulesAsync(new PutManagedInsightRulesRequest());
      InstanceProperties instanceProperties = new InstanceProperties();
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}.
   * <ul>
   *   <li>Given {@link ReceiveMessageResult} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}
   */
  @Test
  @DisplayName("Test new IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker); given ReceiveMessageResult (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void IngestJobQueueConsumer.<init>(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)"})
  void testNewIngestJobQueueConsumer_givenReceiveMessageResult() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());
      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
      InstanceProperties instanceProperties = new InstanceProperties();
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}.
   * <ul>
   *   <li>Given {@link ReceiveMessageResult} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}
   */
  @Test
  @DisplayName("Test new IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker); given ReceiveMessageResult (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void IngestJobQueueConsumer.<init>(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)"})
  void testNewIngestJobQueueConsumer_givenReceiveMessageResult2() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());
      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}.
   * <ul>
   *   <li>Given {@link ReceiveMessageResult} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}
   */
  @Test
  @DisplayName("Test new IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker); given ReceiveMessageResult (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void IngestJobQueueConsumer.<init>(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)"})
  void testNewIngestJobQueueConsumer_givenReceiveMessageResult3() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient(new AnonymousAWSCredentials());
      cloudWatchClient.putCompositeAlarmAsync(new PutCompositeAlarmRequest());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}.
   * <ul>
   *   <li>Then calls {@link ReceiveMessageResult#getMessages()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)}
   */
  @Test
  @DisplayName("Test new IngestJobQueueConsumer(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker); then calls getMessages()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void IngestJobQueueConsumer.<init>(AmazonSQS, AmazonCloudWatch, InstanceProperties, Configuration, TableIndex, IngestJobTracker)"})
  void testNewIngestJobQueueConsumer_thenCallsGetMessages() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      ReceiveMessageResult receiveMessageResult = mock(ReceiveMessageResult.class);
      when(receiveMessageResult.getMessages()).thenReturn(new ArrayList<>());
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(receiveMessageResult);

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient(new AnonymousAWSCredentials());
      cloudWatchClient.putCompositeAlarmAsync(new PutCompositeAlarmRequest());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(receiveMessageResult).getMessages();
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#receiveMessage()}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional IngestJobQueueConsumer.receiveMessage()"})
  void testReceiveMessage() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());
      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
      InstanceProperties instanceProperties = new InstanceProperties();
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#receiveMessage()}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional IngestJobQueueConsumer.receiveMessage()"})
  void testReceiveMessage2() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
      cloudWatchClient.putAnomalyDetectorAsync(new PutAnomalyDetectorRequest());
      InstanceProperties instanceProperties = new InstanceProperties();
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#receiveMessage()}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional IngestJobQueueConsumer.receiveMessage()"})
  void testReceiveMessage3() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
      cloudWatchClient.putCompositeAlarmAsync(new PutCompositeAlarmRequest(), mock(AsyncHandler.class));
      InstanceProperties instanceProperties = new InstanceProperties();
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#receiveMessage()}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional IngestJobQueueConsumer.receiveMessage()"})
  void testReceiveMessage4() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
      cloudWatchClient.putInsightRuleAsync(new PutInsightRuleRequest());
      InstanceProperties instanceProperties = new InstanceProperties();
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#receiveMessage()}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional IngestJobQueueConsumer.receiveMessage()"})
  void testReceiveMessage5() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
      cloudWatchClient.putManagedInsightRulesAsync(new PutManagedInsightRulesRequest());
      InstanceProperties instanceProperties = new InstanceProperties();
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#receiveMessage()}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional IngestJobQueueConsumer.receiveMessage()"})
  void testReceiveMessage6() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient(new AnonymousAWSCredentials());
      cloudWatchClient.putAnomalyDetectorAsync(new PutAnomalyDetectorRequest());
      InstanceProperties instanceProperties = new InstanceProperties();
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#receiveMessage()}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional IngestJobQueueConsumer.receiveMessage()"})
  void testReceiveMessage7() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#receiveMessage()}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional IngestJobQueueConsumer.receiveMessage()"})
  void testReceiveMessage8() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient(new AnonymousAWSCredentials());
      cloudWatchClient.putAnomalyDetectorAsync(new PutAnomalyDetectorRequest());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#receiveMessage()}.
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional IngestJobQueueConsumer.receiveMessage()"})
  void testReceiveMessage9() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient((AWSCredentials) null);
      cloudWatchClient.putAnomalyDetectorAsync(new PutAnomalyDetectorRequest());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      Configuration configuration = new Configuration();
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link IngestJobQueueConsumer#receiveMessage()}.
   * <ul>
   *   <li>Then calls {@link AmazonWebServiceRequest#getRequestMetricCollector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobQueueConsumer#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage(); then calls getRequestMetricCollector()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional IngestJobQueueConsumer.receiveMessage()"})
  void testReceiveMessage_thenCallsGetRequestMetricCollector() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      ReceiveMessageResult receiveMessageResult = mock(ReceiveMessageResult.class);
      when(receiveMessageResult.getMessages()).thenReturn(new ArrayList<>());
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(receiveMessageResult);
      PutAnomalyDetectorRequest request = mock(PutAnomalyDetectorRequest.class);
      when(request.getRequestMetricCollector())
          .thenReturn(new AwsStatisticsCollector(new StatisticsFromAwsSdkImpl(new EmptyS3AStatisticsContext())));

      AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient(mock(PropertiesCredentials.class));
      cloudWatchClient.putAnomalyDetectorAsync(request);
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      InMemoryTableIndex tableIndex = new InMemoryTableIndex();

      // Act
      Optional<MessageHandle> actualReceiveMessageResult = (new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
          instanceProperties, null, tableIndex, new InMemoryIngestJobTracker())).receiveMessage();

      // Assert
      verify(request).getRequestMetricCollector();
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(receiveMessageResult).getMessages();
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test SqsMessageHandle getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SqsMessageHandle#SqsMessageHandle(IngestJobQueueConsumer, IngestJob, MessageReference, PeriodicActionRunnable)}
   *   <li>{@link SqsMessageHandle#failed()}
   *   <li>{@link SqsMessageHandle#getJob()}
   * </ul>
   */
  @Test
  @DisplayName("Test SqsMessageHandle getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SqsMessageHandle.<init>(IngestJobQueueConsumer, IngestJob, MessageReference, PeriodicActionRunnable)",
      "void SqsMessageHandle.failed()", "IngestJob SqsMessageHandle.getJob()"})
  void testSqsMessageHandleGettersAndSetters() {
    // Arrange
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
    InstanceProperties instanceProperties = new InstanceProperties();
    Configuration configuration = new Configuration();
    InMemoryTableIndex tableIndex = new InMemoryTableIndex();
    IngestJobQueueConsumer ingestJobQueueConsumer = new IngestJobQueueConsumer(sqsClient, cloudWatchClient,
        instanceProperties, configuration, tableIndex, new InMemoryIngestJobTracker());

    Builder builderResult = IngestJob.builder();
    IngestJob job = builderResult.files(new ArrayList<>()).id("42").tableId("42").tableName("Table Name").build();
    MessageReference message = new MessageReference(new AmazonSQSAsyncClient(), "https://example.org/example",
        "Job Description", "Receipt Handle");

    // Act
    SqsMessageHandle actualSqsMessageHandle = ingestJobQueueConsumer.new SqsMessageHandle(job, message,
        new PeriodicActionRunnable(mock(Action.class), 1));
    actualSqsMessageHandle.failed();

    // Assert
    assertSame(job, actualSqsMessageHandle.getJob());
  }
}
