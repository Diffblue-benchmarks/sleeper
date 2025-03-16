package sleeper.statestore.lambda.committer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.core.job.commit.CompactionCommitBatcher;
import sleeper.core.properties.instance.InstanceProperties;

class CompactionCommitBatcherLambdaDiffblueTest {
  /**
   * Test {@link CompactionCommitBatcherLambda#CompactionCommitBatcherLambda(CompactionCommitBatcher)}.
   * <p>
   * Method under test: {@link CompactionCommitBatcherLambda#CompactionCommitBatcherLambda(CompactionCommitBatcher)}
   */
  @Test
  @DisplayName("Test new CompactionCommitBatcherLambda(CompactionCommitBatcher)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionCommitBatcherLambda.<init>(CompactionCommitBatcher)"})
  void testNewCompactionCommitBatcherLambda() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();

    // Act
    CompactionCommitBatcherLambda actualCompactionCommitBatcherLambda = new CompactionCommitBatcherLambda(
        CompactionCommitBatcherLambda.createBatcher(instanceProperties, sqsClient, new AmazonS3Client()));
    SQSEvent event = new SQSEvent();
    event.setRecords(new ArrayList<>());

    // Assert
    assertTrue(
        actualCompactionCommitBatcherLambda.handleRequest(event, mock(Context.class)).getBatchItemFailures().isEmpty());
  }

  /**
   * Test {@link CompactionCommitBatcherLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Then return BatchItemFailures Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionCommitBatcherLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; then return BatchItemFailures Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "com.amazonaws.services.lambda.runtime.events.SQSBatchResponse CompactionCommitBatcherLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_thenReturnBatchItemFailuresEmpty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    CompactionCommitBatcherLambda compactionCommitBatcherLambda = new CompactionCommitBatcherLambda(
        CompactionCommitBatcherLambda.createBatcher(instanceProperties, sqsClient, new AmazonS3Client()));

    SQSEvent event = new SQSEvent();
    event.setRecords(new ArrayList<>());

    // Act and Assert
    assertTrue(
        compactionCommitBatcherLambda.handleRequest(event, mock(Context.class)).getBatchItemFailures().isEmpty());
  }
}
