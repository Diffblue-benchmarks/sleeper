package sleeper.compaction.tracker.task;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.DeleteTableResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.UpdateTimeToLiveRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateTimeToLiveResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class DynamoDBCompactionTaskTrackerCreatorDiffblueTest {
  /**
   * Test {@link DynamoDBCompactionTaskTrackerCreator#create(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link InstanceProperties} {@link SleeperPropertyValues#getBoolean(SleeperProperty)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTrackerCreator#create(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test create(InstanceProperties, AmazonDynamoDB); given 'false'; when InstanceProperties getBoolean(SleeperProperty) return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTrackerCreator.create(InstanceProperties, AmazonDynamoDB)"})
  void testCreate_givenFalse_whenInstancePropertiesGetBooleanReturnFalse() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(false);

    // Act
    DynamoDBCompactionTaskTrackerCreator.create(properties, mock(AmazonDynamoDBAsyncClient.class));

    // Assert
    verify(properties).getBoolean(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBCompactionTaskTrackerCreator#create(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Then calls {@link AmazonDynamoDBClient#createTable(CreateTableRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTrackerCreator#create(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test create(InstanceProperties, AmazonDynamoDB); then calls createTable(CreateTableRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTrackerCreator.create(InstanceProperties, AmazonDynamoDB)"})
  void testCreate_thenCallsCreateTable() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(properties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    CreateTableResult createTableResult = mock(CreateTableResult.class);
    when(createTableResult.getTableDescription()).thenReturn(new TableDescription());
    AmazonDynamoDBAsyncClient dynamoDB = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDB.updateTimeToLive(Mockito.<UpdateTimeToLiveRequest>any())).thenReturn(new UpdateTimeToLiveResult());
    when(dynamoDB.createTable(Mockito.<CreateTableRequest>any())).thenReturn(createTableResult);

    // Act
    DynamoDBCompactionTaskTrackerCreator.create(properties, dynamoDB);

    // Assert
    verify(dynamoDB).createTable(isA(CreateTableRequest.class));
    verify(dynamoDB).updateTimeToLive(isA(UpdateTimeToLiveRequest.class));
    verify(createTableResult).getTableDescription();
    verify(properties).getBoolean(isA(InstanceProperty.class));
    verify(properties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBCompactionTaskTrackerCreator#tearDown(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link DeleteTableResult} (default constructor).</li>
   *   <li>Then calls {@link AmazonDynamoDBClient#deleteTable(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTrackerCreator#tearDown(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test tearDown(InstanceProperties, AmazonDynamoDB); given DeleteTableResult (default constructor); then calls deleteTable(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTrackerCreator.tearDown(InstanceProperties, AmazonDynamoDB)"})
  void testTearDown_givenDeleteTableResult_thenCallsDeleteTable() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(properties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    AmazonDynamoDBAsyncClient dynamoDBClient = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDBClient.deleteTable(Mockito.<String>any())).thenReturn(new DeleteTableResult());

    // Act
    DynamoDBCompactionTaskTrackerCreator.tearDown(properties, dynamoDBClient);

    // Assert
    verify(dynamoDBClient).deleteTable(eq("sleeper-Get-compaction-task-status"));
    verify(properties).getBoolean(isA(InstanceProperty.class));
    verify(properties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBCompactionTaskTrackerCreator#tearDown(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link InstanceProperties} {@link SleeperPropertyValues#getBoolean(SleeperProperty)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTrackerCreator#tearDown(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test tearDown(InstanceProperties, AmazonDynamoDB); given 'false'; when InstanceProperties getBoolean(SleeperProperty) return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTrackerCreator.tearDown(InstanceProperties, AmazonDynamoDB)"})
  void testTearDown_givenFalse_whenInstancePropertiesGetBooleanReturnFalse() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(false);

    // Act
    DynamoDBCompactionTaskTrackerCreator.tearDown(properties, mock(AmazonDynamoDBAsyncClient.class));

    // Assert
    verify(properties).getBoolean(isA(InstanceProperty.class));
  }
}
