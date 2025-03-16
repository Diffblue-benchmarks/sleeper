package sleeper.job.common.action;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ChangeMessageVisibilityTimeoutActionDiffblueTest {
  /**
   * Test {@link ChangeMessageVisibilityTimeoutAction#ChangeMessageVisibilityTimeoutAction(MessageReference, int)}.
   * <ul>
   *   <li>Then calls {@link AmazonSQSClient#changeMessageVisibility(ChangeMessageVisibilityRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangeMessageVisibilityTimeoutAction#ChangeMessageVisibilityTimeoutAction(MessageReference, int)}
   */
  @Test
  @DisplayName("Test new ChangeMessageVisibilityTimeoutAction(MessageReference, int); then calls changeMessageVisibility(ChangeMessageVisibilityRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ChangeMessageVisibilityTimeoutAction.<init>(MessageReference, int)"})
  void testNewChangeMessageVisibilityTimeoutAction_thenCallsChangeMessageVisibility() throws ActionException {
    // Arrange
    AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
    when(sqsClient.changeMessageVisibility(Mockito.<ChangeMessageVisibilityRequest>any()))
        .thenReturn(new ChangeMessageVisibilityResult());

    // Act
    (new ChangeMessageVisibilityTimeoutAction(
        new MessageReference(sqsClient, "https://example.org/example", "Job Description", "Receipt Handle"), 10))
        .call();

    // Assert
    verify(sqsClient).changeMessageVisibility(isA(ChangeMessageVisibilityRequest.class));
  }

  /**
   * Test {@link ChangeMessageVisibilityTimeoutAction#call()}.
   * <ul>
   *   <li>Then calls {@link AmazonSQSClient#changeMessageVisibility(ChangeMessageVisibilityRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChangeMessageVisibilityTimeoutAction#call()}
   */
  @Test
  @DisplayName("Test call(); then calls changeMessageVisibility(ChangeMessageVisibilityRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ChangeMessageVisibilityTimeoutAction.call()"})
  void testCall_thenCallsChangeMessageVisibility() throws ActionException {
    // Arrange
    AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
    when(sqsClient.changeMessageVisibility(Mockito.<ChangeMessageVisibilityRequest>any()))
        .thenReturn(new ChangeMessageVisibilityResult());

    // Act
    (new ChangeMessageVisibilityTimeoutAction(
        new MessageReference(sqsClient, "https://example.org/example", "Job Description", "Receipt Handle"), 10))
        .call();

    // Assert
    verify(sqsClient).changeMessageVisibility(isA(ChangeMessageVisibilityRequest.class));
  }
}
