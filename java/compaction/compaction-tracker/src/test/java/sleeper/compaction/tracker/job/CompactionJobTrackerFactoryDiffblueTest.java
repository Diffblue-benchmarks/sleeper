package sleeper.compaction.tracker.job;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.monitoring.internal.ClientSideMonitoringRequestHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ReturnConsumedCapacity;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.testutils.DummyInstanceProperty;

class CompactionJobTrackerFactoryDiffblueTest {
  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-job-lookup", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker3() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-job-updates", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker4() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.addRequestHandler(new ClientSideMonitoringRequestHandler("", new ArrayList<>()));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-job-lookup", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker5() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putIfAbsent("compaction-job-updates", new AttributeValue("foo"));
    item.putAll(new HashMap<>());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-job-lookup", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", item, mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker6() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("compaction-job-lookup", new HashMap<>(), "compaction-job-updates", mock(AsyncHandler.class));
    dynamoDB.addRequestHandler(new ClientSideMonitoringRequestHandler("", new ArrayList<>()));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-job-lookup", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker7() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("", new AttributeValue("compaction-job-updates"));

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putAll(stringAttributeValueMap);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", item, "compaction-job-lookup", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker8() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putAll(new HashMap<>());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new BasicSessionCredentials("EXAMPLEakiAIOSFODNN7", "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY", "ABC123"));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-job-lookup", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", item, mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code compaction-job-lookup}.</li>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given 'compaction-job-lookup'; when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenCompactionJobLookup_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-job-lookup", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} All is {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given HashMap() All is HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenHashMapAllIsHashMap() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putAll(new HashMap<>());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", item, "compaction-job-lookup", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} All is {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given HashMap() All is HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenHashMapAllIsHashMap2() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putAll(new HashMap<>());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-job-lookup", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", item, mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code Table Name}.</li>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); given 'Table Name'; when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_givenTableName_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties); when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTracker_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTracker(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithStronglyConsistentReads() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithStronglyConsistentReads2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("compaction-job-updates", new HashMap<>());

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithStronglyConsistentReads3() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithStronglyConsistentReads4() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithStronglyConsistentReads5() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.putCustomQueryParameter("Name", "42");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithStronglyConsistentReads6() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("42", new HashMap<>(), "compaction-job-updates");
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithStronglyConsistentReads7() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("compaction-job-updates", new HashMap<>());

    InstanceProperties properties = new InstanceProperties();
    properties.set(new DummyInstanceProperty("compaction-job-updates"), "42");

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(dynamoDB,
        properties) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithStronglyConsistentReads8() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.withReturnConsumedCapacity(ReturnConsumedCapacity.INDEXES);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties); given empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithStronglyConsistentReads_givenEmptyString() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("", new HashMap<>());

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }

  /**
   * Test {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobTrackerFactory#getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties); when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.compaction.job.CompactionJobTracker CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithStronglyConsistentReads_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();

    // Act and Assert
    assertTrue(CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(dynamoDB,
        new InstanceProperties()) instanceof DynamoDBCompactionJobTracker);
  }
}
