package sleeper.systemtest.drivers.statestore;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.GetEventSourceMappingRequest;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequest.Builder;

class AwsStateStoreCommitterDriverDiffblueTest {
  /**
   * Test {@link AwsStateStoreCommitterDriver#sendCommitMessagesInParallelBatches(Stream)}.
   * <ul>
   *   <li>Given {@link SqsClient} {@link SqsClient#sendMessageBatch(Consumer)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsStateStoreCommitterDriver#sendCommitMessagesInParallelBatches(Stream)}
   */
  @Test
  @DisplayName("Test sendCommitMessagesInParallelBatches(Stream); given SqsClient sendMessageBatch(Consumer) return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsStateStoreCommitterDriver.sendCommitMessagesInParallelBatches(Stream)"})
  void testSendCommitMessagesInParallelBatches_givenSqsClientSendMessageBatchReturnNull()
      throws AwsServiceException, SdkClientException {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    InstanceProperties instanceProperties = new InstanceProperties();
    when(instance.getTablePropertiesProvider()).thenReturn(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    SqsClient sqs = mock(SqsClient.class);
    when(sqs.sendMessageBatch(Mockito.<Consumer<Builder>>any())).thenReturn(null);
    AwsStateStoreCommitterDriver awsStateStoreCommitterDriver = new AwsStateStoreCommitterDriver(instance, sqs,
        new AmazonS3Client(), mock(LambdaClient.class));

    ArrayList<StateStoreCommitRequest> stateStoreCommitRequestList = new ArrayList<>();
    stateStoreCommitRequestList
        .add(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES));
    Stream<StateStoreCommitRequest> messages = stateStoreCommitRequestList.stream();

    // Act
    awsStateStoreCommitterDriver.sendCommitMessagesInParallelBatches(messages);

    // Assert
    verify(instance).getInstanceProperties();
    verify(instance).getTablePropertiesProvider();
    verify(sqs).sendMessageBatch(isA(Consumer.class));
  }

  /**
   * Test {@link AwsStateStoreCommitterDriver#sendCommitMessagesInParallelBatches(Stream)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsStateStoreCommitterDriver#sendCommitMessagesInParallelBatches(Stream)}
   */
  @Test
  @DisplayName("Test sendCommitMessagesInParallelBatches(Stream); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsStateStoreCommitterDriver.sendCommitMessagesInParallelBatches(Stream)"})
  void testSendCommitMessagesInParallelBatches_thenThrowRuntimeException()
      throws AwsServiceException, SdkClientException {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    InstanceProperties instanceProperties = new InstanceProperties();
    when(instance.getTablePropertiesProvider()).thenReturn(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    SqsClient sqs = mock(SqsClient.class);
    when(sqs.sendMessageBatch(Mockito.<Consumer<Builder>>any())).thenThrow(new RuntimeException("foo"));
    AwsStateStoreCommitterDriver awsStateStoreCommitterDriver = new AwsStateStoreCommitterDriver(instance, sqs,
        new AmazonS3Client(), mock(LambdaClient.class));

    ArrayList<StateStoreCommitRequest> stateStoreCommitRequestList = new ArrayList<>();
    stateStoreCommitRequestList
        .add(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES));
    Stream<StateStoreCommitRequest> messages = stateStoreCommitRequestList.stream();

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> awsStateStoreCommitterDriver.sendCommitMessagesInParallelBatches(messages));
    verify(instance).getInstanceProperties();
    verify(instance).getTablePropertiesProvider();
    verify(sqs).sendMessageBatch(isA(Consumer.class));
  }

  /**
   * Test {@link AwsStateStoreCommitterDriver#sendCommitMessagesInSequentialBatches(Stream)}.
   * <p>
   * Method under test: {@link AwsStateStoreCommitterDriver#sendCommitMessagesInSequentialBatches(Stream)}
   */
  @Test
  @DisplayName("Test sendCommitMessagesInSequentialBatches(Stream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsStateStoreCommitterDriver.sendCommitMessagesInSequentialBatches(Stream)"})
  void testSendCommitMessagesInSequentialBatches() throws AwsServiceException, SdkClientException {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    InstanceProperties instanceProperties = new InstanceProperties();
    when(instance.getTablePropertiesProvider()).thenReturn(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    SqsClient sqs = mock(SqsClient.class);
    when(sqs.sendMessageBatch(Mockito.<Consumer<Builder>>any())).thenReturn(null);
    AwsStateStoreCommitterDriver awsStateStoreCommitterDriver = new AwsStateStoreCommitterDriver(instance, sqs,
        new AmazonS3Client(), mock(LambdaClient.class));

    ArrayList<StateStoreCommitRequest> stateStoreCommitRequestList = new ArrayList<>();
    stateStoreCommitRequestList
        .add(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES));
    Stream<StateStoreCommitRequest> messages = stateStoreCommitRequestList.stream();

    // Act
    awsStateStoreCommitterDriver.sendCommitMessagesInSequentialBatches(messages);

    // Assert
    verify(instance).getInstanceProperties();
    verify(instance).getTablePropertiesProvider();
    verify(sqs).sendMessageBatch(isA(Consumer.class));
  }

  /**
   * Test {@link AwsStateStoreCommitterDriver#sendCommitMessagesInSequentialBatches(Stream)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsStateStoreCommitterDriver#sendCommitMessagesInSequentialBatches(Stream)}
   */
  @Test
  @DisplayName("Test sendCommitMessagesInSequentialBatches(Stream); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsStateStoreCommitterDriver.sendCommitMessagesInSequentialBatches(Stream)"})
  void testSendCommitMessagesInSequentialBatches_thenThrowRuntimeException()
      throws AwsServiceException, SdkClientException {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    InstanceProperties instanceProperties = new InstanceProperties();
    when(instance.getTablePropertiesProvider()).thenReturn(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));
    SqsClient sqs = mock(SqsClient.class);
    when(sqs.sendMessageBatch(Mockito.<Consumer<Builder>>any())).thenThrow(new RuntimeException("foo"));
    AwsStateStoreCommitterDriver awsStateStoreCommitterDriver = new AwsStateStoreCommitterDriver(instance, sqs,
        new AmazonS3Client(), mock(LambdaClient.class));

    ArrayList<StateStoreCommitRequest> stateStoreCommitRequestList = new ArrayList<>();
    stateStoreCommitRequestList
        .add(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES));
    Stream<StateStoreCommitRequest> messages = stateStoreCommitRequestList.stream();

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> awsStateStoreCommitterDriver.sendCommitMessagesInSequentialBatches(messages));
    verify(instance).getInstanceProperties();
    verify(instance).getTablePropertiesProvider();
    verify(sqs).sendMessageBatch(isA(Consumer.class));
  }

  /**
   * Test {@link AwsStateStoreCommitterDriver#pauseReceivingMessages()}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsStateStoreCommitterDriver#pauseReceivingMessages()}
   */
  @Test
  @DisplayName("Test pauseReceivingMessages(); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsStateStoreCommitterDriver.pauseReceivingMessages()"})
  void testPauseReceivingMessages_thenThrowRuntimeException() throws AwsServiceException, SdkClientException {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    LambdaClient lambda = mock(LambdaClient.class);
    when(lambda.getEventSourceMapping(Mockito.<Consumer<GetEventSourceMappingRequest.Builder>>any()))
        .thenThrow(new RuntimeException("Disabling event source for state store committer: {}"));
    SqsClient sqs = mock(SqsClient.class);

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> (new AwsStateStoreCommitterDriver(instance, sqs, new AmazonS3Client(), lambda)).pauseReceivingMessages());
    verify(instance).getInstanceProperties();
    verify(lambda).getEventSourceMapping(isA(Consumer.class));
  }

  /**
   * Test {@link AwsStateStoreCommitterDriver#resumeReceivingMessages()}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsStateStoreCommitterDriver#resumeReceivingMessages()}
   */
  @Test
  @DisplayName("Test resumeReceivingMessages(); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsStateStoreCommitterDriver.resumeReceivingMessages()"})
  void testResumeReceivingMessages_thenThrowRuntimeException() throws AwsServiceException, SdkClientException {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(new InstanceProperties());
    LambdaClient lambda = mock(LambdaClient.class);
    when(lambda.getEventSourceMapping(Mockito.<Consumer<GetEventSourceMappingRequest.Builder>>any()))
        .thenThrow(new RuntimeException("Enabling event source for state store committer: {}"));
    SqsClient sqs = mock(SqsClient.class);

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> (new AwsStateStoreCommitterDriver(instance, sqs, new AmazonS3Client(), lambda))
            .resumeReceivingMessages());
    verify(instance).getInstanceProperties();
    verify(lambda).getEventSourceMapping(isA(Consumer.class));
  }
}
