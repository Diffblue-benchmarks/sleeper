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
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestDeploymentDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.sourcedata.IngestSourceFilesContext;
import sleeper.systemtest.dsl.util.TestContext;

class AwsIngestBatcherDriverDiffblueTest {
  /**
   * Test {@link AwsIngestBatcherDriver#AwsIngestBatcherDriver(SystemTestInstanceContext, IngestSourceFilesContext, SystemTestClients)}.
   * <p>
   * Method under test: {@link AwsIngestBatcherDriver#AwsIngestBatcherDriver(SystemTestInstanceContext, IngestSourceFilesContext, SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsIngestBatcherDriver(SystemTestInstanceContext, IngestSourceFilesContext, SystemTestClients)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AwsIngestBatcherDriver.<init>(SystemTestInstanceContext, IngestSourceFilesContext, SystemTestClients)"})
  void testNewAwsIngestBatcherDriver() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    DeployedSystemTestResources systemTest = new DeployedSystemTestResources(mock(SystemTestParameters.class),
        mock(SystemTestDeploymentDriver.class));

    IngestSourceFilesContext sourceFiles = new IngestSourceFilesContext(systemTest,
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)));

    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(new AmazonDynamoDBAsyncClient());
    when(clients.getSqs()).thenReturn(new AmazonSQSAsyncClient());

    // Act
    new AwsIngestBatcherDriver(instance, sourceFiles, clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getSqs();
  }
}
