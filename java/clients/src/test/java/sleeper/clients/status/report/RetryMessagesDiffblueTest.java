package sleeper.clients.status.report;

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
import sleeper.core.properties.instance.InstanceProperties;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest.Builder;

class RetryMessagesDiffblueTest {
  /**
   * Test {@link RetryMessages#run()}.
   * <p>
   * Method under test: {@link RetryMessages#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RetryMessages.run()"})
  void testRun() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new RetryMessages(new InstanceProperties(), mock(SqsClient.class), "Stack", 3)).run());
  }

  /**
   * Test {@link RetryMessages#run()}.
   * <p>
   * Method under test: {@link RetryMessages#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RetryMessages.run()"})
  void testRun2() throws AwsServiceException, SdkClientException {
    // Arrange
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.receiveMessage(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new IllegalArgumentException("compaction"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new RetryMessages(new InstanceProperties(), sqsClient, "compaction", 3)).run());
    verify(sqsClient).receiveMessage(isA(Consumer.class));
  }

  /**
   * Test {@link RetryMessages#run()}.
   * <p>
   * Method under test: {@link RetryMessages#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RetryMessages.run()"})
  void testRun3() throws AwsServiceException, SdkClientException {
    // Arrange
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.receiveMessage(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new IllegalArgumentException("compaction"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new RetryMessages(new InstanceProperties(), sqsClient, "ingest", 3)).run());
    verify(sqsClient).receiveMessage(isA(Consumer.class));
  }

  /**
   * Test {@link RetryMessages#run()}.
   * <p>
   * Method under test: {@link RetryMessages#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RetryMessages.run()"})
  void testRun4() throws AwsServiceException, SdkClientException {
    // Arrange
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.receiveMessage(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new IllegalArgumentException("compaction"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new RetryMessages(new InstanceProperties(), sqsClient, "query", 3)).run());
    verify(sqsClient).receiveMessage(isA(Consumer.class));
  }
}
