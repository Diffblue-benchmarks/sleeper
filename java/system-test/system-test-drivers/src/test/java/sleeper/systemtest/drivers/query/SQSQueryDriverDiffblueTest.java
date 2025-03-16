package sleeper.systemtest.drivers.query;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import sleeper.systemtest.dsl.query.QueryAllTablesDriver;
import sleeper.systemtest.dsl.query.QueryAllTablesSendAndWaitDriver;
import sleeper.systemtest.dsl.util.TestContext;

class SQSQueryDriverDiffblueTest {
  /**
   * Test {@link SQSQueryDriver#SQSQueryDriver(SystemTestInstanceContext, SystemTestClients)}.
   * <ul>
   *   <li>Given {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   *   <li>Then calls {@link SystemTestClients#getDynamoDB()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SQSQueryDriver#SQSQueryDriver(SystemTestInstanceContext, SystemTestClients)}
   */
  @Test
  @DisplayName("Test new SQSQueryDriver(SystemTestInstanceContext, SystemTestClients); given AmazonDynamoDBAsyncClient(); then calls getDynamoDB()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SQSQueryDriver.<init>(SystemTestInstanceContext, SystemTestClients)"})
  void testNewSQSQueryDriver_givenAmazonDynamoDBAsyncClient_thenCallsGetDynamoDB() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(new AmazonDynamoDBAsyncClient());
    when(clients.getS3()).thenReturn(new AmazonS3Client());
    when(clients.getSqs()).thenReturn(new AmazonSQSAsyncClient());

    // Act
    new SQSQueryDriver(instance, clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3();
    verify(clients).getSqs();
  }

  /**
   * Test {@link SQSQueryDriver#SQSQueryDriver(SystemTestInstanceContext, SystemTestClients)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SQSQueryDriver#SQSQueryDriver(SystemTestInstanceContext, SystemTestClients)}
   */
  @Test
  @DisplayName("Test new SQSQueryDriver(SystemTestInstanceContext, SystemTestClients); then throw IllegalStateException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SQSQueryDriver.<init>(SystemTestInstanceContext, SystemTestClients)"})
  void testNewSQSQueryDriver_thenThrowIllegalStateException() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getSqs()).thenThrow(new IllegalStateException("foo"));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> new SQSQueryDriver(instance, clients));

    verify(clients).getSqs();
  }

  /**
   * Test {@link SQSQueryDriver#allTablesDriver(SystemTestInstanceContext, SystemTestClients)}.
   * <ul>
   *   <li>Then return {@link QueryAllTablesSendAndWaitDriver}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SQSQueryDriver#allTablesDriver(SystemTestInstanceContext, SystemTestClients)}
   */
  @Test
  @DisplayName("Test allTablesDriver(SystemTestInstanceContext, SystemTestClients); then return QueryAllTablesSendAndWaitDriver")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "QueryAllTablesDriver SQSQueryDriver.allTablesDriver(SystemTestInstanceContext, SystemTestClients)"})
  void testAllTablesDriver_thenReturnQueryAllTablesSendAndWaitDriver() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getDynamoDB()).thenReturn(new AmazonDynamoDBAsyncClient());
    when(clients.getS3()).thenReturn(new AmazonS3Client());
    when(clients.getSqs()).thenReturn(new AmazonSQSAsyncClient());

    // Act
    QueryAllTablesDriver actualAllTablesDriverResult = SQSQueryDriver.allTablesDriver(instance, clients);

    // Assert
    verify(clients).getDynamoDB();
    verify(clients).getS3();
    verify(clients).getSqs();
    assertTrue(actualAllTablesDriverResult instanceof QueryAllTablesSendAndWaitDriver);
  }

  /**
   * Test {@link SQSQueryDriver#allTablesDriver(SystemTestInstanceContext, SystemTestClients)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SQSQueryDriver#allTablesDriver(SystemTestInstanceContext, SystemTestClients)}
   */
  @Test
  @DisplayName("Test allTablesDriver(SystemTestInstanceContext, SystemTestClients); then throw IllegalStateException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "QueryAllTablesDriver SQSQueryDriver.allTablesDriver(SystemTestInstanceContext, SystemTestClients)"})
  void testAllTablesDriver_thenThrowIllegalStateException() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    SystemTestClients clients = mock(SystemTestClients.class);
    when(clients.getSqs()).thenThrow(new IllegalStateException("foo"));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> SQSQueryDriver.allTablesDriver(instance, clients));
    verify(clients).getSqs();
  }
}
