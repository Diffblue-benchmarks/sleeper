package sleeper.statestore.transactionlog.snapshots;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.STSSessionCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalOperator;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.TransactionCanceledException;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.amazonaws.services.sqs.MessageMD5ChecksumHandler;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.util.HashMap;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.statestore.transactionlog.DuplicateSnapshotException;

class DynamoDBTransactionLogSnapshotMetadataStoreDiffblueTest {
  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties,
        new AmazonDynamoDBAsyncClient());

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("Table Name", new HashMap<>(), "42");

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore3() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamo.putItemAsync(new PutItemRequest());
    dynamo.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore4() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore5() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeNamesEntry("Key", "42");
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore6() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.withReturnValues(ReturnValue.NONE);
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore7() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("42", new AttributeValue("foo"));
    request.addExpressionAttributeNamesEntry("Key", "42");
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore8() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.addItemEntry("", new AttributeValue("foo"));
    request.addExpressionAttributeValuesEntry("42", new AttributeValue("foo"));
    request.addExpressionAttributeNamesEntry("Key", "42");
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore9() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.setReturnConsumedCapacity("Return Consumed Capacity");
    request.addExpressionAttributeValuesEntry("42", new AttributeValue("foo"));
    request.addExpressionAttributeNamesEntry("Key", "42");
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore10() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamo.putItemAsync(new PutItemRequest());
    dynamo.putItemAsync(new PutItemRequest("Table Name", new HashMap<>(), ReturnValue.NONE));

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore11() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.setRequestCredentials(new AnonymousAWSCredentials());

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore12() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.setRequestCredentials(new AnonymousAWSCredentials());

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync(new PutItemRequest());
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore13() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.setRequestCredentials(new AnonymousAWSCredentials());

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore14() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.addRequestHandler(new MessageMD5ChecksumHandler());
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore15() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.withReturnValues(ReturnValue.NONE);
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.put("foo", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", item, "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore16() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.withReturnValues(ReturnValue.NONE);
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putIfAbsent("42", new AttributeValue("foo"));
    item.put("foo", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", item, "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore17() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("42", new AttributeValue("foo"));
    request.addExpressionAttributeNamesEntry("Key", "42");
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link AsyncHandler}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB); given AsyncHandler")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore_givenAsyncHandler() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.addItemEntry("", new AttributeValue("foo"));
    request.addExpressionAttributeValuesEntry("42", new AttributeValue("foo"));
    request.addExpressionAttributeNamesEntry("Key", "42");
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB); given 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore_givenFoo() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("foo");

    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(request);

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB); given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore_givenPutItemRequest() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB); given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore_givenPutItemRequest2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync(new PutItemRequest());
    dynamo.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB); given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore_givenPutItemRequest3() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();
    dynamo.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamo.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties, dynamo);

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotMetadataStore(InstanceProperties, TableProperties, AmazonDynamoDB); when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotMetadataStore.<init>(InstanceProperties, TableProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTransactionLogSnapshotMetadataStore_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    // Act
    new DynamoDBTransactionLogSnapshotMetadataStore(instanceProperties, tableProperties,
        new AmazonDynamoDBAsyncClient());

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot() throws DuplicateSnapshotException {
    // Arrange
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, tableProperties, new AmazonDynamoDBAsyncClient(), timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot2() throws DuplicateSnapshotException {
    // Arrange
    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamo.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(new InstanceProperties()), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot3() throws DuplicateSnapshotException {
    // Arrange
    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient((AWSCredentials) null);
    dynamo.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(new InstanceProperties()), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot4() throws DuplicateSnapshotException {
    // Arrange
    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AWSSecurityTokenServiceAsyncClient()));
    dynamo.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(new InstanceProperties()), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot5() throws DuplicateSnapshotException {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("Key",
        new AttributeValue(DynamoDBTransactionLogSnapshotMetadataStore.TABLE_ID_AND_SNAPSHOT_TYPE));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamo.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(new InstanceProperties()), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot6() throws DuplicateSnapshotException {
    // Arrange
    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AWSSecurityTokenServiceAsyncClient()));
    dynamo.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamo.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(new InstanceProperties()), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot7() throws DuplicateSnapshotException {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("Key",
        new AttributeValue(DynamoDBTransactionLogSnapshotMetadataStore.TABLE_ID_AND_SNAPSHOT_TYPE));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamo.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(new InstanceProperties()), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(new TransactionLogSnapshotMetadata("Path", SnapshotType.FILES, 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} addExpressionAttributeNamesEntry {@code Key} and {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata); given PutItemRequest() addExpressionAttributeNamesEntry 'Key' and '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot_givenPutItemRequestAddExpressionAttributeNamesEntryKeyAnd42()
      throws DuplicateSnapshotException {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeNamesEntry("Key", "42");

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient((AWSCredentials) null);
    dynamo.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(new InstanceProperties()), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} CustomQueryParameter {@code Name} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata); given PutItemRequest() CustomQueryParameter 'Name' is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot_givenPutItemRequestCustomQueryParameterNameIs42() throws DuplicateSnapshotException {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.putCustomQueryParameter("Name", "42");

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient((AWSCredentials) null);
    dynamo.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(new InstanceProperties()), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} CustomRequestHeader {@code 42} is {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata); given PutItemRequest() CustomRequestHeader '42' is 'Value'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot_givenPutItemRequestCustomRequestHeader42IsValue() throws DuplicateSnapshotException {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.putCustomRequestHeader("42", "Value");
    request.addExpressionAttributeValuesEntry("Key",
        new AttributeValue(DynamoDBTransactionLogSnapshotMetadataStore.TABLE_ID_AND_SNAPSHOT_TYPE));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamo.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(new InstanceProperties()), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} withConditionalOperator {@code AND}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata); given PutItemRequest() withConditionalOperator 'AND'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot_givenPutItemRequestWithConditionalOperatorAnd() throws DuplicateSnapshotException {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.withConditionalOperator(ConditionalOperator.AND);
    request.addExpressionAttributeValuesEntry("Key",
        new AttributeValue(DynamoDBTransactionLogSnapshotMetadataStore.TABLE_ID_AND_SNAPSHOT_TYPE));

    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamo.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(new InstanceProperties()), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}.
   * <ul>
   *   <li>Given {@link TableProperties#TableProperties(InstanceProperties)} with instanceProperties is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotMetadataStore#saveSnapshot(TransactionLogSnapshotMetadata)}
   */
  @Test
  @DisplayName("Test saveSnapshot(TransactionLogSnapshotMetadata); given TableProperties(InstanceProperties) with instanceProperties is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotMetadataStore.saveSnapshot(TransactionLogSnapshotMetadata)"})
  void testSaveSnapshot_givenTablePropertiesWithInstancePropertiesIsNull() throws DuplicateSnapshotException {
    // Arrange
    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AWSSecurityTokenServiceAsyncClient()));
    dynamo.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new TransactionCanceledException("An error occurred"));
    InstanceProperties instanceProperties = new InstanceProperties();
    DynamoDBTransactionLogSnapshotMetadataStore dynamoDBTransactionLogSnapshotMetadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, new TableProperties(null), dynamo, timeSupplier);

    // Act and Assert
    assertThrows(TransactionCanceledException.class, () -> dynamoDBTransactionLogSnapshotMetadataStore
        .saveSnapshot(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L)));
    verify(timeSupplier).get();
  }
}
