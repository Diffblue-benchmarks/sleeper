package sleeper.systemtest.drivers.compaction;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.systemtest.drivers.util.SystemTestClients;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.util.TestContext;
import software.amazon.awssdk.services.autoscaling.AutoScalingClient;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.sqs.SqsClient;

class AwsCompactionDriverDiffblueTest {
  /**
   * Test {@link AwsCompactionDriver#AwsCompactionDriver(SystemTestInstanceContext, SystemTestClients)}.
   * <ul>
   *   <li>Then calls {@link SystemTestClients#getAutoScaling()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsCompactionDriver#AwsCompactionDriver(SystemTestInstanceContext, SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsCompactionDriver(SystemTestInstanceContext, SystemTestClients); then calls getAutoScaling()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsCompactionDriver.<init>(SystemTestInstanceContext, SystemTestClients)"})
  void testNewAwsCompactionDriver_thenCallsGetAutoScaling() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(new AmazonDynamoDBAsyncClient());
    when(clients.getS3()).thenReturn(new AmazonS3Client());
    when(clients.getSqs()).thenReturn(new AmazonSQSAsyncClient());
    when(clients.getAutoScaling()).thenReturn(mock(AutoScalingClient.class));
    when(clients.getEc2()).thenReturn(mock(Ec2Client.class));
    when(clients.getSqsV2()).thenReturn(mock(SqsClient.class));

    // Act
    new AwsCompactionDriver(instance, clients);

    // Assert
    verify(clients).getAutoScaling();
    verify(clients).getDynamoDB();
    verify(clients).getEc2();
    verify(clients).getS3();
    verify(clients).getSqs();
    verify(clients).getSqsV2();
  }
}
