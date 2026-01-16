package sleeper.compaction.job.execution;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.compaction.core.task.CompactionTask;
import sleeper.compaction.core.task.CompactionTask.MessageHandle;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class SqsCompactionQueueHandlerDiffblueTest {
  /**
   * Test {@link SqsCompactionQueueHandler#receiveMessage()}.
   *
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link InstanceProperties#getInt(SleeperProperty)}
   *       return one.
   *   <li>Then calls {@link InstanceProperties#getInt(SleeperProperty)}.
   * </ul>
   *
   * <p>Method under test: {@link SqsCompactionQueueHandler#receiveMessage()}
   */
  @Test
  @DisplayName(
      "Test receiveMessage(); given InstanceProperties getInt(SleeperProperty) return one; then calls getInt(SleeperProperty)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional SqsCompactionQueueHandler.receiveMessage()"})
  void testReceiveMessage_givenInstancePropertiesGetIntReturnOne_thenCallsGetInt()
      throws IOException {
    // Arrange
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {
      mockInetAddress
          .when(() -> InetAddress.getByAddress(Mockito.<byte[]>any()))
          .thenReturn(mock(InetAddress.class));
      mockInetAddress.when(InetAddress::getLocalHost).thenReturn(mock(InetAddress.class));
      mockInetAddress
          .when(() -> InetAddress.getByName(Mockito.<String>any()))
          .thenReturn(mock(InetAddress.class));
      mockInetAddress
          .when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[] {mock(InetAddress.class)});

      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any()))
          .thenReturn(new ReceiveMessageResult());

      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

      SqsCompactionQueueHandler sqsCompactionQueueHandler =
          new SqsCompactionQueueHandler(sqsClient, instanceProperties);

      // Act
      Optional<MessageHandle> actualReceiveMessageResult =
          sqsCompactionQueueHandler.receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
      verify(instanceProperties).get(isA(InstanceProperty.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }

  /**
   * Test {@link SqsCompactionQueueHandler#receiveMessage()}.
   *
   * <ul>
   *   <li>Then return not Present.
   * </ul>
   *
   * <p>Method under test: {@link SqsCompactionQueueHandler#receiveMessage()}
   */
  @Test
  @DisplayName("Test receiveMessage(); then return not Present")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional SqsCompactionQueueHandler.receiveMessage()"})
  void testReceiveMessage_thenReturnNotPresent() throws IOException {
    // Arrange
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {
      mockInetAddress
          .when(() -> InetAddress.getByAddress(Mockito.<byte[]>any()))
          .thenReturn(mock(InetAddress.class));
      mockInetAddress.when(InetAddress::getLocalHost).thenReturn(mock(InetAddress.class));
      mockInetAddress
          .when(() -> InetAddress.getByName(Mockito.<String>any()))
          .thenReturn(mock(InetAddress.class));
      mockInetAddress
          .when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[] {mock(InetAddress.class)});

      AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
      when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any()))
          .thenReturn(new ReceiveMessageResult());
      SqsCompactionQueueHandler sqsCompactionQueueHandler =
          new SqsCompactionQueueHandler(sqsClient, new InstanceProperties());

      // Act
      Optional<MessageHandle> actualReceiveMessageResult =
          sqsCompactionQueueHandler.receiveMessage();

      // Assert
      verify(sqsClient).receiveMessage(isA(ReceiveMessageRequest.class));
      assertFalse(actualReceiveMessageResult.isPresent());
    }
  }
}
