package sleeper.clients.docker.stack;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.DeleteTableResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.docker.stack.IngestDockerStack.Builder;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest;

class IngestDockerStackDiffblueTest {
  /**
   * Test {@link IngestDockerStack#tearDown()}.
   * <p>
   * Method under test: {@link IngestDockerStack#tearDown()}
   */
  @Test
  @DisplayName("Test tearDown()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestDockerStack.tearDown()"})
  void testTearDown() throws AwsServiceException, SdkClientException {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDB.deleteTable(Mockito.<String>any())).thenReturn(new DeleteTableResult());
    Builder dynamoDBResult = IngestDockerStack.builder().dynamoDB(dynamoDB);
    Builder instancePropertiesResult = dynamoDBResult.instanceProperties(new InstanceProperties());
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.deleteQueue(Mockito.<Consumer<DeleteQueueRequest.Builder>>any())).thenReturn(null);
    IngestDockerStack buildResult = instancePropertiesResult.sqsClient(sqsClient).build();

    // Act
    buildResult.tearDown();

    // Assert
    verify(dynamoDB, atLeast(1)).deleteTable(Mockito.<String>any());
    verify(sqsClient).deleteQueue(isA(Consumer.class));
  }

  /**
   * Test {@link IngestDockerStack#tearDown()}.
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link SleeperPropertyValues#getBoolean(SleeperProperty)} return {@code false}.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestDockerStack#tearDown()}
   */
  @Test
  @DisplayName("Test tearDown(); given InstanceProperties getBoolean(SleeperProperty) return 'false'; then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestDockerStack.tearDown()"})
  void testTearDown_givenInstancePropertiesGetBooleanReturnFalse_thenCallsGetBoolean()
      throws AwsServiceException, SdkClientException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(false);
    Builder instancePropertiesResult = IngestDockerStack.builder()
        .dynamoDB(mock(AmazonDynamoDBAsyncClient.class))
        .instanceProperties(instanceProperties);
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.deleteQueue(Mockito.<Consumer<DeleteQueueRequest.Builder>>any())).thenReturn(null);
    IngestDockerStack buildResult = instancePropertiesResult.sqsClient(sqsClient).build();

    // Act
    buildResult.tearDown();

    // Assert
    verify(instanceProperties, atLeast(1)).getBoolean(isA(InstanceProperty.class));
    verify(sqsClient).deleteQueue(isA(Consumer.class));
  }

  /**
   * Test {@link IngestDockerStack#tearDown()}.
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestDockerStack#tearDown()}
   */
  @Test
  @DisplayName("Test tearDown(); given InstanceProperties get(InstanceProperty) return 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestDockerStack.tearDown()"})
  void testTearDown_givenInstancePropertiesGetReturnGet_thenCallsGet() throws AwsServiceException, SdkClientException {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDB.deleteTable(Mockito.<String>any())).thenReturn(new DeleteTableResult());
    Builder dynamoDBResult = IngestDockerStack.builder().dynamoDB(dynamoDB);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    Builder instancePropertiesResult = dynamoDBResult.instanceProperties(instanceProperties);
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.deleteQueue(Mockito.<Consumer<DeleteQueueRequest.Builder>>any())).thenReturn(null);
    IngestDockerStack buildResult = instancePropertiesResult.sqsClient(sqsClient).build();

    // Act
    buildResult.tearDown();

    // Assert
    verify(dynamoDB, atLeast(1)).deleteTable(Mockito.<String>any());
    verify(instanceProperties, atLeast(1)).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    verify(sqsClient).deleteQueue(isA(Consumer.class));
  }
}
