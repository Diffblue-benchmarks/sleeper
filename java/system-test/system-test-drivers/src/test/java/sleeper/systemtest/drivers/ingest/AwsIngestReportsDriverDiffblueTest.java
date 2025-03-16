package sleeper.systemtest.drivers.ingest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
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
import software.amazon.awssdk.services.emr.EmrClient;

class AwsIngestReportsDriverDiffblueTest {
  /**
   * Test {@link AwsIngestReportsDriver#AwsIngestReportsDriver(SystemTestInstanceContext, SystemTestClients)}.
   * <p>
   * Method under test: {@link AwsIngestReportsDriver#AwsIngestReportsDriver(SystemTestInstanceContext, SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsIngestReportsDriver(SystemTestInstanceContext, SystemTestClients)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsIngestReportsDriver.<init>(SystemTestInstanceContext, SystemTestClients)"})
  void testNewAwsIngestReportsDriver() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(new AmazonDynamoDBAsyncClient());
    when(clients.getSqs()).thenReturn(new AmazonSQSAsyncClient());
    when(clients.getEmr()).thenReturn(mock(EmrClient.class));

    // Act
    new AwsIngestReportsDriver(instance, clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getEmr();
    verify(clients).getSqs();
  }
}
