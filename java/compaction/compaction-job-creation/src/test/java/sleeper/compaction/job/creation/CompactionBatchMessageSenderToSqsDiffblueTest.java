package sleeper.compaction.job.creation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class CompactionBatchMessageSenderToSqsDiffblueTest {
  /**
   * Test {@link
   * CompactionBatchMessageSenderToSqs#CompactionBatchMessageSenderToSqs(InstanceProperties,
   * AmazonSQS)}.
   *
   * <p>Method under test: {@link
   * CompactionBatchMessageSenderToSqs#CompactionBatchMessageSenderToSqs(InstanceProperties,
   * AmazonSQS)}
   */
  @Test
  @DisplayName("Test new CompactionBatchMessageSenderToSqs(InstanceProperties, AmazonSQS)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CompactionBatchMessageSenderToSqs.<init>(InstanceProperties, AmazonSQS)"
  })
  void testNewCompactionBatchMessageSenderToSqs() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();

    // Act
    new CompactionBatchMessageSenderToSqs(instanceProperties, sqsClient);

    // Assert that nothing has changed
    assertTrue(sqsClient.getExecutorService() instanceof ThreadPoolExecutor);
    assertEquals("sqs", sqsClient.getEndpointPrefix());
    assertEquals("sqs", sqsClient.getServiceName());
    assertEquals(0, sqsClient.getTimeOffset());
    assertTrue(sqsClient.getMonitoringListeners().isEmpty());
    Stream<Entry<String, String>> unknownProperties = instanceProperties.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
    assertTrue(instanceProperties.toMap().isEmpty());
    assertTrue(instanceProperties.getTags().isEmpty());
    Properties properties = instanceProperties.getProperties();
    assertTrue(properties.isEmpty());
    assertEquals(properties, instanceProperties.getTagsProperties());
  }

  /**
   * Test {@link
   * CompactionBatchMessageSenderToSqs#CompactionBatchMessageSenderToSqs(InstanceProperties,
   * AmazonSQS)}.
   *
   * <ul>
   *   <li>Then calls {@link AmazonSQSAsyncClient#sendMessage(String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CompactionBatchMessageSenderToSqs#CompactionBatchMessageSenderToSqs(InstanceProperties,
   * AmazonSQS)}
   */
  @Test
  @DisplayName(
      "Test new CompactionBatchMessageSenderToSqs(InstanceProperties, AmazonSQS); then calls sendMessage(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CompactionBatchMessageSenderToSqs.<init>(InstanceProperties, AmazonSQS)"
  })
  void testNewCompactionBatchMessageSenderToSqs_thenCallsSendMessage() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonSQSAsyncClient sqsClient = mock(AmazonSQSAsyncClient.class);
    when(sqsClient.sendMessage(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new SendMessageResult());

    // Act
    CompactionBatchMessageSenderToSqs actualCompactionBatchMessageSenderToSqs =
        new CompactionBatchMessageSenderToSqs(instanceProperties, sqsClient);
    actualCompactionBatchMessageSenderToSqs.sendMessage(mock(CompactionJobDispatchRequest.class));

    // Assert
    verify(sqsClient).sendMessage("Get", "{}");
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }
}
