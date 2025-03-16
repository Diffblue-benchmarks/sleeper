package sleeper.job.common.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MessageReferenceDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MessageReference#MessageReference(AmazonSQS, String, String, String)}
   *   <li>{@link MessageReference#getJobDescription()}
   *   <li>{@link MessageReference#getReceiptHandle()}
   *   <li>{@link MessageReference#getSqsClient()}
   *   <li>{@link MessageReference#getSqsJobQueueUrl()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MessageReference.<init>(AmazonSQS, String, String, String)",
      "String MessageReference.getJobDescription()", "String MessageReference.getReceiptHandle()",
      "AmazonSQS MessageReference.getSqsClient()", "String MessageReference.getSqsJobQueueUrl()"})
  void testGettersAndSetters() {
    // Arrange
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();

    // Act
    MessageReference actualMessageReference = new MessageReference(sqsClient, "https://example.org/example",
        "Job Description", "Receipt Handle");
    String actualJobDescription = actualMessageReference.getJobDescription();
    String actualReceiptHandle = actualMessageReference.getReceiptHandle();
    AmazonSQS actualSqsClient = actualMessageReference.getSqsClient();

    // Assert
    assertEquals("Job Description", actualJobDescription);
    assertEquals("Receipt Handle", actualReceiptHandle);
    assertEquals("https://example.org/example", actualMessageReference.getSqsJobQueueUrl());
    assertSame(sqsClient, actualSqsClient);
  }

  /**
   * Test {@link MessageReference#deleteAction()}.
   * <ul>
   *   <li>Then calls {@link AmazonSQSClient#deleteMessage(DeleteMessageRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageReference#deleteAction()}
   */
  @Test
  @DisplayName("Test deleteAction(); then calls deleteMessage(DeleteMessageRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.job.common.action.DeleteMessageAction MessageReference.deleteAction()"})
  void testDeleteAction_thenCallsDeleteMessage() throws ActionException {
    // Arrange
    AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
    when(sqsClient.deleteMessage(Mockito.<DeleteMessageRequest>any())).thenReturn(new DeleteMessageResult());

    // Act
    (new MessageReference(sqsClient, "https://example.org/example", "Job Description", "Receipt Handle")).deleteAction()
        .call();

    // Assert
    verify(sqsClient).deleteMessage(isA(DeleteMessageRequest.class));
  }

  /**
   * Test {@link MessageReference#changeVisibilityTimeoutAction(int)}.
   * <ul>
   *   <li>Then calls {@link AmazonSQSClient#changeMessageVisibility(ChangeMessageVisibilityRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageReference#changeVisibilityTimeoutAction(int)}
   */
  @Test
  @DisplayName("Test changeVisibilityTimeoutAction(int); then calls changeMessageVisibility(ChangeMessageVisibilityRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.job.common.action.ChangeMessageVisibilityTimeoutAction MessageReference.changeVisibilityTimeoutAction(int)"})
  void testChangeVisibilityTimeoutAction_thenCallsChangeMessageVisibility() throws ActionException {
    // Arrange
    AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
    when(sqsClient.changeMessageVisibility(Mockito.<ChangeMessageVisibilityRequest>any()))
        .thenReturn(new ChangeMessageVisibilityResult());

    // Act
    (new MessageReference(sqsClient, "https://example.org/example", "Job Description", "Receipt Handle"))
        .changeVisibilityTimeoutAction(10)
        .call();

    // Assert
    verify(sqsClient).changeMessageVisibility(isA(ChangeMessageVisibilityRequest.class));
  }
}
