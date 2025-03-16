package sleeper.compaction.tracker.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.monitoring.internal.ClientSideMonitoringRequestHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;

class CompactionTaskTrackerFactoryDiffblueTest {
  /**
   * Test {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.task.CompactionTaskTracker CompactionTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());

    // Act and Assert
    assertTrue(CompactionTaskTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionTaskTracker);
  }

  /**
   * Test {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.task.CompactionTaskTracker CompactionTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamoDB.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));
    dynamoDB.putItemAsync("", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionTaskTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionTaskTracker);
  }

  /**
   * Test {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.task.CompactionTaskTracker CompactionTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker3() {
    // Arrange
    ClientConfiguration clientConfiguration = PredefinedClientConfigurations.defaultConfig();
    clientConfiguration.setProxyPassword("iloveyou");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(clientConfiguration);
    dynamoDB.addRequestHandler(new ClientSideMonitoringRequestHandler("", new ArrayList<>()));
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act and Assert
    assertTrue(CompactionTaskTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionTaskTracker);
  }

  /**
   * Test {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.task.CompactionTaskTracker CompactionTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_given42() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamoDB.putItemAsync("42", new HashMap<>(), "42");

    // Act and Assert
    assertTrue(CompactionTaskTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionTaskTracker);
  }

  /**
   * Test {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.task.CompactionTaskTracker CompactionTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenEmptyString() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamoDB.putItemAsync("", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionTaskTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionTaskTracker);
  }

  /**
   * Test {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code iloveyou}.</li>
   *   <li>When defaultConfig ProxyPassword is {@code iloveyou}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given 'iloveyou'; when defaultConfig ProxyPassword is 'iloveyou'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.task.CompactionTaskTracker CompactionTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenIloveyou_whenDefaultConfigProxyPasswordIsIloveyou() {
    // Arrange
    ClientConfiguration clientConfiguration = PredefinedClientConfigurations.defaultConfig();
    clientConfiguration.setProxyPassword("iloveyou");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(clientConfiguration);
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act and Assert
    assertTrue(CompactionTaskTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionTaskTracker);
  }

  /**
   * Test {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.task.CompactionTaskTracker CompactionTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenPutItemRequest() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act and Assert
    assertTrue(CompactionTaskTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionTaskTracker);
  }

  /**
   * Test {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} ReturnValues is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given PutItemRequest() ReturnValues is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.task.CompactionTaskTracker CompactionTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenPutItemRequestReturnValuesIs42() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.setReturnValues("42");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(request);

    // Act and Assert
    assertTrue(CompactionTaskTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionTaskTracker);
  }

  /**
   * Test {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code Table Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given 'Table Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.task.CompactionTaskTracker CompactionTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenTableName() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act and Assert
    assertTrue(CompactionTaskTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionTaskTracker);
  }

  /**
   * Test {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.task.CompactionTaskTracker CompactionTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();

    // Act and Assert
    assertTrue(CompactionTaskTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionTaskTracker);
  }
}
