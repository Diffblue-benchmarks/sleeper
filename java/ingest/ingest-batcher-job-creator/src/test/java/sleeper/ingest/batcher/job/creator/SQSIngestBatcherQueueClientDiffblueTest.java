package sleeper.ingest.batcher.job.creator;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.ingest.core.job.IngestJob;
import sleeper.ingest.core.job.IngestJob.Builder;

class SQSIngestBatcherQueueClientDiffblueTest {
  /**
   * Test {@link SQSIngestBatcherQueueClient#SQSIngestBatcherQueueClient(AmazonSQS)}.
   * <ul>
   *   <li>Given {@link SendMessageResult} (default constructor).</li>
   *   <li>Then calls {@link AmazonSQSClient#sendMessage(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SQSIngestBatcherQueueClient#SQSIngestBatcherQueueClient(AmazonSQS)}
   */
  @Test
  @DisplayName("Test new SQSIngestBatcherQueueClient(AmazonSQS); given SendMessageResult (default constructor); then calls sendMessage(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SQSIngestBatcherQueueClient.<init>(AmazonSQS)"})
  void testNewSQSIngestBatcherQueueClient_givenSendMessageResult_thenCallsSendMessage() {
    // Arrange
    AmazonSQSAsyncClient sqs = mock(AmazonSQSAsyncClient.class);
    when(sqs.sendMessage(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new SendMessageResult());

    // Act
    SQSIngestBatcherQueueClient actualSqsIngestBatcherQueueClient = new SQSIngestBatcherQueueClient(sqs);
    Builder builderResult = IngestJob.builder();
    IngestJob job = builderResult.files(new ArrayList<>()).id("42").tableId("42").tableName("Table Name").build();
    actualSqsIngestBatcherQueueClient.send("https://example.org/example", job);

    // Assert
    verify(sqs).sendMessage(eq("https://example.org/example"),
        eq("{\"id\":\"42\",\"tableName\":\"Table Name\",\"tableId\":\"42\",\"files\":[]}"));
  }

  /**
   * Test {@link SQSIngestBatcherQueueClient#send(String, IngestJob)}.
   * <ul>
   *   <li>Then calls {@link AmazonSQSClient#sendMessage(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SQSIngestBatcherQueueClient#send(String, IngestJob)}
   */
  @Test
  @DisplayName("Test send(String, IngestJob); then calls sendMessage(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SQSIngestBatcherQueueClient.send(String, IngestJob)"})
  void testSend_thenCallsSendMessage() {
    // Arrange
    AmazonSQSAsyncClient sqs = mock(AmazonSQSAsyncClient.class);
    when(sqs.sendMessage(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new SendMessageResult());

    // Act
    (new SQSIngestBatcherQueueClient(sqs)).send("https://example.org/example", null);

    // Assert
    verify(sqs).sendMessage(eq("https://example.org/example"), eq("null"));
  }
}
