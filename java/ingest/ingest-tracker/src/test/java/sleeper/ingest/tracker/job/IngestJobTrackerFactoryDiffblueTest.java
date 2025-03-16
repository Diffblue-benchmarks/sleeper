package sleeper.ingest.tracker.job;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.sqs.MessageMD5ChecksumHandler;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.testutils.DummyInstanceProperty;

class IngestJobTrackerFactoryDiffblueTest {
  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-job-lookup", new HashMap<>(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("ingest-job-updates", new HashMap<>());

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties(),
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("42", new HashMap<>(), "42", mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties(),
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow3() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("", new HashMap<>(), "ingest-job-updates");
    dynamoDB.putItemAsync("ingest-job-updates", new HashMap<>());

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties(),
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow4() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-job-lookup", new HashMap<>(), "", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("ingest-job-updates", new HashMap<>());

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties(),
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow5() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-job-updates", new HashMap<>(), "42");
    dynamoDB.addRequestHandler(new MessageMD5ChecksumHandler());
    dynamoDB.putItemAsync("ingest-job-updates", new HashMap<>());

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties(),
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow6() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-job-lookup", new HashMap<>(), "", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("ingest-job-updates", new HashMap<>());

    InstanceProperties properties = new InstanceProperties();
    properties.set(new DummyInstanceProperty("ingest-job-updates"), "42");

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, properties,
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <ul>
   *   <li>Given {@code ingest-job-updates}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'; given 'ingest-job-updates'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow_givenIngestJobUpdates() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-job-updates", new HashMap<>());

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties(),
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <ul>
   *   <li>Given {@link MessageMD5ChecksumHandler} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'; given MessageMD5ChecksumHandler (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow_givenMessageMD5ChecksumHandler() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.addRequestHandler(new MessageMD5ChecksumHandler());
    dynamoDB.putItemAsync("ingest-job-updates", new HashMap<>());

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties(),
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'; given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow_givenPutItemRequest() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("ingest-job-lookup", new HashMap<>(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("ingest-job-updates", new HashMap<>());

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties(),
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <ul>
   *   <li>Given {@code Table Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'; given 'Table Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow_givenTableName() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties(),
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)} with {@code dynamoDB}, {@code properties}, {@code getTimeNow}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties, Supplier) with 'dynamoDB', 'properties', 'getTimeNow'; when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties, Supplier)"})
  void testGetTrackerWithDynamoDBPropertiesGetTimeNow_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();

    // Act and Assert
    assertTrue(IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties(),
        mock(Supplier.class)) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)} with {@code dynamoDB}, {@code properties}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties) with 'dynamoDB', 'properties'; given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithDynamoDBProperties_givenPutItemRequest() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act and Assert
    assertTrue(
        IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)} with {@code dynamoDB}, {@code properties}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties) with 'dynamoDB', 'properties'; given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithDynamoDBProperties_givenPutItemRequest2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act and Assert
    assertTrue(
        IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)} with {@code dynamoDB}, {@code properties}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties) with 'dynamoDB', 'properties'; given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithDynamoDBProperties_givenPutItemRequest3() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-job-lookup", new HashMap<>(), "ingest-job-updates");
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act and Assert
    assertTrue(
        IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)} with {@code dynamoDB}, {@code properties}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} withReturnValues {@code NONE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties) with 'dynamoDB', 'properties'; given PutItemRequest() withReturnValues 'NONE'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithDynamoDBProperties_givenPutItemRequestWithReturnValuesNone() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.withReturnValues(ReturnValue.NONE);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("ingest-job-updates", new HashMap<>(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("ingest-job-lookup", new HashMap<>(), "ingest-job-updates");
    dynamoDB.putItemAsync(request);

    // Act and Assert
    assertTrue(
        IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestJobTracker);
  }

  /**
   * Test {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)} with {@code dynamoDB}, {@code properties}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobTrackerFactory#getTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getTracker(AmazonDynamoDB, InstanceProperties) with 'dynamoDB', 'properties'; when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.tracker.ingest.job.IngestJobTracker IngestJobTrackerFactory.getTracker(AmazonDynamoDB, InstanceProperties)"})
  void testGetTrackerWithDynamoDBProperties_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();

    // Act and Assert
    assertTrue(
        IngestJobTrackerFactory.getTracker(dynamoDB, new InstanceProperties()) instanceof DynamoDBIngestJobTracker);
  }
}
