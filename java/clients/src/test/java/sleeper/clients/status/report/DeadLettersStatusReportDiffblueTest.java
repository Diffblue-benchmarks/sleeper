package sleeper.clients.status.report;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
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
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.task.common.QueueMessageCount;
import sleeper.task.common.QueueMessageCount.Client;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest.Builder;

class DeadLettersStatusReportDiffblueTest {
  /**
   * Test {@link DeadLettersStatusReport#run()}.
   * <ul>
   *   <li>Then calls {@link QueueMessageCount#getApproximateNumberOfMessages()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeadLettersStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); then calls getApproximateNumberOfMessages()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeadLettersStatusReport.run()"})
  void testRun_thenCallsGetApproximateNumberOfMessages() {
    // Arrange
    QueueMessageCount queueMessageCount = mock(QueueMessageCount.class);
    when(queueMessageCount.getApproximateNumberOfMessages()).thenReturn(0);
    Client messageCount = mock(Client.class);
    when(messageCount.getQueueMessageCount(Mockito.<String>any())).thenReturn(queueMessageCount);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SqsClient sqsClient = mock(SqsClient.class);
    InstanceProperties instanceProperties2 = new InstanceProperties();

    // Act
    (new DeadLettersStatusReport(sqsClient, messageCount, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(TablePropertiesStore.Client.class)))))
        .run();

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(queueMessageCount, atLeast(1)).getApproximateNumberOfMessages();
    verify(messageCount, atLeast(1)).getQueueMessageCount(eq("Get"));
  }

  /**
   * Test {@link DeadLettersStatusReport#run()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeadLettersStatusReport#run()}
   */
  @Test
  @DisplayName("Test run(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeadLettersStatusReport.run()"})
  void testRun_thenThrowIllegalArgumentException() throws AwsServiceException, SdkClientException {
    // Arrange
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.receiveMessage(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new IllegalArgumentException("\nDead Letters Status Report:\n--------------------------"));
    Client messageCount = mock(Client.class);
    when(messageCount.getQueueMessageCount(Mockito.<String>any()))
        .thenReturn(QueueMessageCount.approximateNumberVisibleAndNotVisible(1, 1));
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    InstanceProperties instanceProperties2 = new InstanceProperties();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new DeadLettersStatusReport(sqsClient, messageCount, instanceProperties,
            new TablePropertiesProvider(instanceProperties2,
                new TablePropertiesStore(new InMemoryTableIndex(), mock(TablePropertiesStore.Client.class)))))
            .run());
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(messageCount).getQueueMessageCount(eq("Get"));
    verify(sqsClient).receiveMessage(isA(Consumer.class));
  }
}
