package sleeper.systemtest.drivers.metrics;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.systemtest.drivers.util.SystemTestClients;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.reporting.ReportingContext;
import sleeper.systemtest.dsl.util.TestContext;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;

class AwsTableMetricsDriverDiffblueTest {
  /**
   * Test {@link AwsTableMetricsDriver#AwsTableMetricsDriver(SystemTestInstanceContext, ReportingContext, SystemTestClients)}.
   * <ul>
   *   <li>Given {@link AmazonSQSAsyncClient#AmazonSQSAsyncClient()}.</li>
   *   <li>Then calls {@link SystemTestClients#getCloudWatch()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsTableMetricsDriver#AwsTableMetricsDriver(SystemTestInstanceContext, ReportingContext, SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsTableMetricsDriver(SystemTestInstanceContext, ReportingContext, SystemTestClients); given AmazonSQSAsyncClient(); then calls getCloudWatch()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AwsTableMetricsDriver.<init>(SystemTestInstanceContext, ReportingContext, SystemTestClients)"})
  void testNewAwsTableMetricsDriver_givenAmazonSQSAsyncClient_thenCallsGetCloudWatch() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    ReportingContext reporting = new ReportingContext(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getSqs()).thenReturn(new AmazonSQSAsyncClient());
    when(clients.getCloudWatch()).thenReturn(mock(CloudWatchClient.class));

    // Act
    new AwsTableMetricsDriver(instance, reporting, clients);

    // Assert
    verify(clients).getCloudWatch();
    verify(clients).getSqs();
  }

  /**
   * Test {@link AwsTableMetricsDriver#AwsTableMetricsDriver(SystemTestInstanceContext, ReportingContext, SystemTestClients)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsTableMetricsDriver#AwsTableMetricsDriver(SystemTestInstanceContext, ReportingContext, SystemTestClients)}
   */
  @Test
  @DisplayName("Test new AwsTableMetricsDriver(SystemTestInstanceContext, ReportingContext, SystemTestClients); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AwsTableMetricsDriver.<init>(SystemTestInstanceContext, ReportingContext, SystemTestClients)"})
  void testNewAwsTableMetricsDriver_thenThrowRuntimeException() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    ReportingContext reporting = new ReportingContext(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getSqs()).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new AwsTableMetricsDriver(instance, reporting, clients));

    verify(clients).getSqs();
  }
}
