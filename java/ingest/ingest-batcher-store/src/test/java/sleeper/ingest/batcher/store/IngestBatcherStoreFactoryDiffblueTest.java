package sleeper.ingest.batcher.store;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.ingest.batcher.core.IngestBatcherStore;

class IngestBatcherStoreFactoryDiffblueTest {
  /**
   * Test {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <p>
   * Method under test: {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional IngestBatcherStoreFactory.getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testGetStore() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addItemEntry("Key", new AttributeValue("ingest-batcher-store"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    InstanceProperties properties = new InstanceProperties();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Optional<IngestBatcherStore> actualStore = IngestBatcherStoreFactory.getStore(dynamoDB, properties,
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertTrue(actualStore.get() instanceof DynamoDBIngestBatcherStore);
    assertTrue(actualStore.isPresent());
  }

  /**
   * Test {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <p>
   * Method under test: {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional IngestBatcherStoreFactory.getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testGetStore2() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addItemEntry("42", new AttributeValue("ingest-batcher-store"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    InstanceProperties properties = new InstanceProperties();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Optional<IngestBatcherStore> actualStore = IngestBatcherStoreFactory.getStore(dynamoDB, properties,
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertTrue(actualStore.get() instanceof DynamoDBIngestBatcherStore);
    assertTrue(actualStore.isPresent());
  }

  /**
   * Test {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <p>
   * Method under test: {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional IngestBatcherStoreFactory.getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testGetStore3() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addExpectedEntry("", new ExpectedAttributeValue());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    InstanceProperties properties = new InstanceProperties();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Optional<IngestBatcherStore> actualStore = IngestBatcherStoreFactory.getStore(dynamoDB, properties,
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertTrue(actualStore.get() instanceof DynamoDBIngestBatcherStore);
    assertTrue(actualStore.isPresent());
  }

  /**
   * Test {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <p>
   * Method under test: {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional IngestBatcherStoreFactory.getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testGetStore4() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("Key", new AttributeValue("ingest-batcher-store"));
    request.addItemEntry("42", new AttributeValue("ingest-batcher-store"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    InstanceProperties properties = new InstanceProperties();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Optional<IngestBatcherStore> actualStore = IngestBatcherStoreFactory.getStore(dynamoDB, properties,
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertTrue(actualStore.get() instanceof DynamoDBIngestBatcherStore);
    assertTrue(actualStore.isPresent());
  }

  /**
   * Test {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <p>
   * Method under test: {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional IngestBatcherStoreFactory.getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testGetStore5() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addExpectedEntry("", new ExpectedAttributeValue());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new BasicSessionCredentials("EXAMPLEakiAIOSFODNN7", "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY", "ABC123"));
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    InstanceProperties properties = new InstanceProperties();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Optional<IngestBatcherStore> actualStore = IngestBatcherStoreFactory.getStore(dynamoDB, properties,
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertTrue(actualStore.get() instanceof DynamoDBIngestBatcherStore);
    assertTrue(actualStore.isPresent());
  }

  /**
   * Test {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <p>
   * Method under test: {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional IngestBatcherStoreFactory.getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testGetStore6() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("Key", new AttributeValue("ingest-batcher-store"));
    request.addExpectedEntry("", new ExpectedAttributeValue());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    InstanceProperties properties = new InstanceProperties();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Optional<IngestBatcherStore> actualStore = IngestBatcherStoreFactory.getStore(dynamoDB, properties,
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertTrue(actualStore.get() instanceof DynamoDBIngestBatcherStore);
    assertTrue(actualStore.isPresent());
  }

  /**
   * Test {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider); given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional IngestBatcherStoreFactory.getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testGetStore_givenPutItemRequest() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    InstanceProperties properties = new InstanceProperties();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Optional<IngestBatcherStore> actualStore = IngestBatcherStoreFactory.getStore(dynamoDB, properties,
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertTrue(actualStore.get() instanceof DynamoDBIngestBatcherStore);
    assertTrue(actualStore.isPresent());
  }

  /**
   * Test {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} addExpressionAttributeNamesEntry {@code Key} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider); given PutItemRequest() addExpressionAttributeNamesEntry 'Key' and '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional IngestBatcherStoreFactory.getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testGetStore_givenPutItemRequestAddExpressionAttributeNamesEntryKeyAnd42() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeNamesEntry("Key", "42");
    request.addExpectedEntry("", new ExpectedAttributeValue());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    InstanceProperties properties = new InstanceProperties();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Optional<IngestBatcherStore> actualStore = IngestBatcherStoreFactory.getStore(dynamoDB, properties,
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertTrue(actualStore.get() instanceof DynamoDBIngestBatcherStore);
    assertTrue(actualStore.isPresent());
  }

  /**
   * Test {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} CustomQueryParameter empty string is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider); given PutItemRequest() CustomQueryParameter empty string is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional IngestBatcherStoreFactory.getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testGetStore_givenPutItemRequestCustomQueryParameterEmptyStringIs42() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.putCustomQueryParameter("", "42");
    request.addExpectedEntry("", new ExpectedAttributeValue());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    InstanceProperties properties = new InstanceProperties();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Optional<IngestBatcherStore> actualStore = IngestBatcherStoreFactory.getStore(dynamoDB, properties,
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertTrue(actualStore.get() instanceof DynamoDBIngestBatcherStore);
    assertTrue(actualStore.isPresent());
  }

  /**
   * Test {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestBatcherStoreFactory#getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider); when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Optional IngestBatcherStoreFactory.getStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testGetStore_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    InstanceProperties properties = new InstanceProperties();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    Optional<IngestBatcherStore> actualStore = IngestBatcherStoreFactory.getStore(dynamoDB, properties,
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertTrue(actualStore.get() instanceof DynamoDBIngestBatcherStore);
    assertTrue(actualStore.isPresent());
  }
}
