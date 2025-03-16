package sleeper.configuration.table.index;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class DynamoDBTableIndexCreatorDiffblueTest {
  /**
   * Test {@link DynamoDBTableIndexCreator#create(AmazonDynamoDB, InstanceProperties)} with {@code AmazonDynamoDB}, {@code InstanceProperties}.
   * <p>
   * Method under test: {@link DynamoDBTableIndexCreator#create(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test create(AmazonDynamoDB, InstanceProperties) with 'AmazonDynamoDB', 'InstanceProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndexCreator.create(AmazonDynamoDB, InstanceProperties)"})
  void testCreateWithAmazonDynamoDBInstanceProperties() {
    // Arrange
    CreateTableResult createTableResult = mock(CreateTableResult.class);
    when(createTableResult.getTableDescription()).thenReturn(new TableDescription());
    AmazonDynamoDBAsyncClient dynamoDBClient = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDBClient.createTable(Mockito.<CreateTableRequest>any())).thenReturn(createTableResult);

    HashMap<String, String> stringStringMap = new HashMap<>();
    stringStringMap.put("instanceProperties must not be null", "instanceProperties must not be null");
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(instanceProperties.getTags()).thenReturn(stringStringMap);

    // Act
    DynamoDBTableIndexCreator.create(dynamoDBClient, instanceProperties);

    // Assert
    verify(dynamoDBClient, atLeast(1)).createTable(Mockito.<CreateTableRequest>any());
    verify(createTableResult, atLeast(1)).getTableDescription();
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).getTags();
  }

  /**
   * Test {@link DynamoDBTableIndexCreator#create(AmazonDynamoDB, InstanceProperties)} with {@code AmazonDynamoDB}, {@code InstanceProperties}.
   * <p>
   * Method under test: {@link DynamoDBTableIndexCreator#create(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test create(AmazonDynamoDB, InstanceProperties) with 'AmazonDynamoDB', 'InstanceProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndexCreator.create(AmazonDynamoDB, InstanceProperties)"})
  void testCreateWithAmazonDynamoDBInstanceProperties2() {
    // Arrange
    CreateTableResult createTableResult = mock(CreateTableResult.class);
    when(createTableResult.getTableDescription()).thenReturn(new TableDescription());
    AmazonDynamoDBAsyncClient dynamoDBClient = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDBClient.createTable(Mockito.<CreateTableRequest>any())).thenReturn(createTableResult);

    HashMap<String, String> stringStringMap = new HashMap<>();
    stringStringMap.put("dynamoDB must not be null", "dynamoDB must not be null");
    stringStringMap.put("instanceProperties must not be null", "instanceProperties must not be null");
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(instanceProperties.getTags()).thenReturn(stringStringMap);

    // Act
    DynamoDBTableIndexCreator.create(dynamoDBClient, instanceProperties);

    // Assert
    verify(dynamoDBClient, atLeast(1)).createTable(Mockito.<CreateTableRequest>any());
    verify(createTableResult, atLeast(1)).getTableDescription();
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).getTags();
  }

  /**
   * Test {@link DynamoDBTableIndexCreator#create(AmazonDynamoDB, InstanceProperties)} with {@code AmazonDynamoDB}, {@code InstanceProperties}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIndexCreator#create(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test create(AmazonDynamoDB, InstanceProperties) with 'AmazonDynamoDB', 'InstanceProperties'; given HashMap(); then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndexCreator.create(AmazonDynamoDB, InstanceProperties)"})
  void testCreateWithAmazonDynamoDBInstanceProperties_givenHashMap_thenCallsGet() {
    // Arrange
    CreateTableResult createTableResult = mock(CreateTableResult.class);
    when(createTableResult.getTableDescription()).thenReturn(new TableDescription());
    AmazonDynamoDBAsyncClient dynamoDBClient = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDBClient.createTable(Mockito.<CreateTableRequest>any())).thenReturn(createTableResult);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(instanceProperties.getTags()).thenReturn(new HashMap<>());

    // Act
    DynamoDBTableIndexCreator.create(dynamoDBClient, instanceProperties);

    // Assert
    verify(dynamoDBClient, atLeast(1)).createTable(Mockito.<CreateTableRequest>any());
    verify(createTableResult, atLeast(1)).getTableDescription();
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).getTags();
  }

  /**
   * Test {@link DynamoDBTableIndexCreator#create(AmazonDynamoDB, InstanceProperties)} with {@code AmazonDynamoDB}, {@code InstanceProperties}.
   * <ul>
   *   <li>Then calls {@link AmazonDynamoDBClient#createTable(CreateTableRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIndexCreator#create(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test create(AmazonDynamoDB, InstanceProperties) with 'AmazonDynamoDB', 'InstanceProperties'; then calls createTable(CreateTableRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndexCreator.create(AmazonDynamoDB, InstanceProperties)"})
  void testCreateWithAmazonDynamoDBInstanceProperties_thenCallsCreateTable() {
    // Arrange
    CreateTableResult createTableResult = mock(CreateTableResult.class);
    when(createTableResult.getTableDescription()).thenReturn(new TableDescription());
    AmazonDynamoDBAsyncClient dynamoDBClient = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDBClient.createTable(Mockito.<CreateTableRequest>any())).thenReturn(createTableResult);

    // Act
    DynamoDBTableIndexCreator.create(dynamoDBClient, new InstanceProperties());

    // Assert
    verify(dynamoDBClient, atLeast(1)).createTable(Mockito.<CreateTableRequest>any());
    verify(createTableResult, atLeast(1)).getTableDescription();
  }
}
