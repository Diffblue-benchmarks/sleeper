package sleeper.job.common.action;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeleteMessageActionDiffblueTest {
  /**
   * Test {@link DeleteMessageAction#DeleteMessageAction(MessageReference)}.
   * <ul>
   *   <li>Given {@link DeleteMessageResult} (default constructor).</li>
   *   <li>Then calls {@link AmazonSQSClient#deleteMessage(DeleteMessageRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteMessageAction#DeleteMessageAction(MessageReference)}
   */
  @Test
  @DisplayName("Test new DeleteMessageAction(MessageReference); given DeleteMessageResult (default constructor); then calls deleteMessage(DeleteMessageRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeleteMessageAction.<init>(MessageReference)"})
  void testNewDeleteMessageAction_givenDeleteMessageResult_thenCallsDeleteMessage() throws ActionException {
    // Arrange
    AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
    when(sqsClient.deleteMessage(Mockito.<DeleteMessageRequest>any())).thenReturn(new DeleteMessageResult());

    // Act
    (new DeleteMessageAction(
        new MessageReference(sqsClient, "https://example.org/example", "Job Description", "Receipt Handle"))).call();

    // Assert
    verify(sqsClient).deleteMessage(isA(DeleteMessageRequest.class));
  }

  /**
   * Test {@link DeleteMessageAction#call()}.
   * <ul>
   *   <li>Then calls {@link AmazonSQSClient#deleteMessage(DeleteMessageRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteMessageAction#call()}
   */
  @Test
  @DisplayName("Test call(); then calls deleteMessage(DeleteMessageRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeleteMessageAction.call()"})
  void testCall_thenCallsDeleteMessage() throws ActionException {
    // Arrange
    AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
    when(sqsClient.deleteMessage(Mockito.<DeleteMessageRequest>any())).thenReturn(new DeleteMessageResult());

    // Act
    (new DeleteMessageAction(
        new MessageReference(sqsClient, "https://example.org/example", "Job Description", "Receipt Handle"))).call();

    // Assert
    verify(sqsClient).deleteMessage(isA(DeleteMessageRequest.class));
  }
}
