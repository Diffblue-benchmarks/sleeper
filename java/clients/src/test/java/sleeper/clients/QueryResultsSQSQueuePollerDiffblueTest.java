package sleeper.clients;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class QueryResultsSQSQueuePollerDiffblueTest {
  /**
   * Test {@link QueryResultsSQSQueuePoller#run()}.
   * <ul>
   *   <li>Then calls {@link AmazonSQSClient#receiveMessage(ReceiveMessageRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryResultsSQSQueuePoller#run()}
   */
  @Test
  @DisplayName("Test run(); then calls receiveMessage(ReceiveMessageRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryResultsSQSQueuePoller.run()"})
  void testRun_thenCallsReceiveMessage() {
    // Arrange
    AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
    when(sqsClient.receiveMessage(Mockito.<ReceiveMessageRequest>any())).thenReturn(new ReceiveMessageResult());

    // Act
    (new QueryResultsSQSQueuePoller(sqsClient, "https://example.org/example")).run();

    // Assert
    verify(sqsClient, atLeast(1)).receiveMessage(isA(ReceiveMessageRequest.class));
  }
}
