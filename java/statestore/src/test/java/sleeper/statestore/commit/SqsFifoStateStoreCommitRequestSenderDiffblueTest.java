package sleeper.statestore.commit;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.AddPermissionRequest;
import com.amazonaws.services.sqs.model.AddPermissionResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.transactionlog.transaction.TransactionSerDeProvider;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;

class SqsFifoStateStoreCommitRequestSenderDiffblueTest {
  /**
   * Test {@link SqsFifoStateStoreCommitRequestSender#SqsFifoStateStoreCommitRequestSender(InstanceProperties, AmazonSQS, AmazonS3, TransactionSerDeProvider)}.
   * <ul>
   *   <li>Then calls {@link AmazonSQSAsyncClient#addPermissionAsync(AddPermissionRequest, AsyncHandler)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SqsFifoStateStoreCommitRequestSender#SqsFifoStateStoreCommitRequestSender(InstanceProperties, AmazonSQS, AmazonS3, TransactionSerDeProvider)}
   */
  @Test
  @DisplayName("Test new SqsFifoStateStoreCommitRequestSender(InstanceProperties, AmazonSQS, AmazonS3, TransactionSerDeProvider); then calls addPermissionAsync(AddPermissionRequest, AsyncHandler)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SqsFifoStateStoreCommitRequestSender.<init>(InstanceProperties, AmazonSQS, AmazonS3, TransactionSerDeProvider)"})
  void testNewSqsFifoStateStoreCommitRequestSender_thenCallsAddPermissionAsync() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.sendMessage(Mockito.<SendMessageRequest>any())).thenReturn(new SendMessageResult());
      when(sqsClient.addPermissionAsync(Mockito.<AddPermissionRequest>any(),
          Mockito.<AsyncHandler<AddPermissionRequest, AddPermissionResult>>any()))
          .thenReturn(new CompletableFuture<>());
      sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));

      // Act
      SqsFifoStateStoreCommitRequestSender actualSqsFifoStateStoreCommitRequestSender = new SqsFifoStateStoreCommitRequestSender(
          instanceProperties, sqsClient, new AmazonS3Client(), mock(TransactionSerDeProvider.class));
      actualSqsFifoStateStoreCommitRequestSender
          .send(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES));

      // Assert
      verify(sqsClient).addPermissionAsync(isA(AddPermissionRequest.class), isA(AsyncHandler.class));
      verify(sqsClient).sendMessage(isA(SendMessageRequest.class));
      verify(instanceProperties).get(isA(InstanceProperty.class));
    }
  }

  /**
   * Test {@link SqsFifoStateStoreCommitRequestSender#send(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Then calls {@link AmazonSQSAsyncClient#addPermissionAsync(AddPermissionRequest, AsyncHandler)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SqsFifoStateStoreCommitRequestSender#send(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test send(StateStoreCommitRequest); then calls addPermissionAsync(AddPermissionRequest, AsyncHandler)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SqsFifoStateStoreCommitRequestSender.send(StateStoreCommitRequest)"})
  void testSend_thenCallsAddPermissionAsync() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.sendMessage(Mockito.<SendMessageRequest>any())).thenReturn(new SendMessageResult());
      when(sqsClient.addPermissionAsync(Mockito.<AddPermissionRequest>any(),
          Mockito.<AsyncHandler<AddPermissionRequest, AddPermissionResult>>any()))
          .thenReturn(new CompletableFuture<>());
      sqsClient.addPermissionAsync(new AddPermissionRequest(), mock(AsyncHandler.class));
      SqsFifoStateStoreCommitRequestSender sqsFifoStateStoreCommitRequestSender = new SqsFifoStateStoreCommitRequestSender(
          instanceProperties, sqsClient, new AmazonS3Client(), mock(TransactionSerDeProvider.class));

      // Act
      sqsFifoStateStoreCommitRequestSender
          .send(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES));

      // Assert
      verify(sqsClient).addPermissionAsync(isA(AddPermissionRequest.class), isA(AsyncHandler.class));
      verify(sqsClient).sendMessage(isA(SendMessageRequest.class));
      verify(instanceProperties).get(isA(InstanceProperty.class));
    }
  }
}
