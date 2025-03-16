package sleeper.ingest.batcher.store;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.UpdateTimeToLiveRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateTimeToLiveResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class DynamoDBIngestBatcherStoreCreatorDiffblueTest {
  /**
   * Test {@link DynamoDBIngestBatcherStoreCreator#create(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Then calls {@link AmazonDynamoDBClient#createTable(CreateTableRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStoreCreator#create(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test create(InstanceProperties, AmazonDynamoDB); then calls createTable(CreateTableRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestBatcherStoreCreator.create(InstanceProperties, AmazonDynamoDB)"})
  void testCreate_thenCallsCreateTable() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    CreateTableResult createTableResult = mock(CreateTableResult.class);
    when(createTableResult.getTableDescription()).thenReturn(new TableDescription());
    AmazonDynamoDBAsyncClient dynamoDB = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDB.updateTimeToLive(Mockito.<UpdateTimeToLiveRequest>any())).thenReturn(new UpdateTimeToLiveResult());
    when(dynamoDB.createTable(Mockito.<CreateTableRequest>any())).thenReturn(createTableResult);

    // Act
    DynamoDBIngestBatcherStoreCreator.create(properties, dynamoDB);

    // Assert
    verify(dynamoDB).createTable(isA(CreateTableRequest.class));
    verify(dynamoDB).updateTimeToLive(isA(UpdateTimeToLiveRequest.class));
    verify(createTableResult).getTableDescription();
    verify(properties).get(isA(InstanceProperty.class));
  }
}
