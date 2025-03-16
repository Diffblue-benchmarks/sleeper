package sleeper.compaction.job.creation;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class CompactionBatchMessageSenderToSqsDiffblueTest {
  /**
   * Test {@link CompactionBatchMessageSenderToSqs#sendMessage(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Then calls {@link AmazonSQSClient#sendMessage(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionBatchMessageSenderToSqs#sendMessage(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test sendMessage(CompactionJobDispatchRequest); then calls sendMessage(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionBatchMessageSenderToSqs.sendMessage(CompactionJobDispatchRequest)"})
  void testSendMessage_thenCallsSendMessage() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
    when(sqsClient.sendMessage(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new SendMessageResult());

    // Act
    (new CompactionBatchMessageSenderToSqs(instanceProperties, sqsClient)).sendMessage(null);

    // Assert
    verify(sqsClient).sendMessage(eq("Get"), eq("null"));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }
}
