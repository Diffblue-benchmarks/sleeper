package sleeper.ingest.tracker.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;

class IngestTaskTrackerFactoryDiffblueTest {
  /**
   * Test {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.task.IngestTaskTracker IngestTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-task-status", new HashMap<>(), "42");

    // Act and Assert
    assertTrue(
        IngestTaskTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestTaskTracker);
  }

  /**
   * Test {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.task.IngestTaskTracker IngestTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-task-status", new HashMap<>(), "42", mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(
        IngestTaskTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestTaskTracker);
  }

  /**
   * Test {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.task.IngestTaskTracker IngestTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker3() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-task-status", new HashMap<>(), "Return Values");

    // Act and Assert
    assertTrue(
        IngestTaskTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestTaskTracker);
  }

  /**
   * Test {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.task.IngestTaskTracker IngestTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker4() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-task-status", new HashMap<>(), "Return Values", mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(
        IngestTaskTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestTaskTracker);
  }

  /**
   * Test {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.task.IngestTaskTracker IngestTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenEmptyString() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("", new HashMap<>(), "ingest-task-status");
    dynamoDB.putItemAsync("ingest-task-status", new HashMap<>(), "42");

    // Act and Assert
    assertTrue(
        IngestTaskTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestTaskTracker);
  }

  /**
   * Test {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code Table Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given 'Table Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.task.IngestTaskTracker IngestTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenTableName() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(
        IngestTaskTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestTaskTracker);
  }

  /**
   * Test {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return {@link DynamoDBIngestTaskTracker}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); when InstanceProperties(); then return DynamoDBIngestTaskTracker")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.task.IngestTaskTracker IngestTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_whenInstanceProperties_thenReturnDynamoDBIngestTaskTracker() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();

    // Act and Assert
    assertTrue(
        IngestTaskTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestTaskTracker);
  }

  /**
   * Test {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#putItemAsync(String, Map, String)} with tableName is {@code 42} and item is {@link HashMap#HashMap()} and {@code Return Values}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTaskTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); when putItemAsync(String, Map, String) with tableName is '42' and item is HashMap() and 'Return Values'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.task.IngestTaskTracker IngestTaskTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_whenPutItemAsyncWithTableNameIs42AndItemIsHashMapAndReturnValues() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamoDB.putItemAsync("ingest-task-status", new HashMap<>(), "42", mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(
        IngestTaskTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestTaskTracker);
  }
}
