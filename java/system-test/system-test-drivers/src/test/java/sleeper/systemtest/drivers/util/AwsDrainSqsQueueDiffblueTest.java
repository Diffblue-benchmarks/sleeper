package sleeper.systemtest.drivers.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest.Builder;

class AwsDrainSqsQueueDiffblueTest {
  /**
   * Test {@link AwsDrainSqsQueue#emptyQueueForWholeInstance(SqsClient, String)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsDrainSqsQueue#emptyQueueForWholeInstance(SqsClient, String)}
   */
  @Test
  @DisplayName("Test emptyQueueForWholeInstance(SqsClient, String); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsDrainSqsQueue.emptyQueueForWholeInstance(SqsClient, String)"})
  void testEmptyQueueForWholeInstance_thenThrowRuntimeException() throws AwsServiceException, SdkClientException {
    // Arrange
    SqsClient sqs = mock(SqsClient.class);
    when(sqs.receiveMessage(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new RuntimeException("Draining queue until empty: {}"));

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> AwsDrainSqsQueue.emptyQueueForWholeInstance(sqs, "https://example.org/example"));
    verify(sqs).receiveMessage(isA(Consumer.class));
  }

  /**
   * Test {@link AwsDrainSqsQueue#drainQueueForWholeInstance(SqsClient, String)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsDrainSqsQueue#drainQueueForWholeInstance(SqsClient, String)}
   */
  @Test
  @DisplayName("Test drainQueueForWholeInstance(SqsClient, String); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.stream.Stream AwsDrainSqsQueue.drainQueueForWholeInstance(SqsClient, String)"})
  void testDrainQueueForWholeInstance_thenThrowRuntimeException() throws AwsServiceException, SdkClientException {
    // Arrange
    SqsClient sqs = mock(SqsClient.class);
    when(sqs.receiveMessage(Mockito.<Consumer<Builder>>any())).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> AwsDrainSqsQueue.drainQueueForWholeInstance(sqs, "https://example.org/example"));
    verify(sqs).receiveMessage(isA(Consumer.class));
  }
}
