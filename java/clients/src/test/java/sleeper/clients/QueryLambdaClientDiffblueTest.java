package sleeper.clients;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.query.core.model.Query;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest.Builder;

class QueryLambdaClientDiffblueTest {
  /**
   * Test {@link QueryLambdaClient#QueryLambdaClient(AmazonS3, AmazonDynamoDB, SqsClient, InstanceProperties)}.
   * <ul>
   *   <li>Then return InstanceProperties is {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryLambdaClient#QueryLambdaClient(AmazonS3, AmazonDynamoDB, SqsClient, InstanceProperties)}
   */
  @Test
  @DisplayName("Test new QueryLambdaClient(AmazonS3, AmazonDynamoDB, SqsClient, InstanceProperties); then return InstanceProperties is InstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryLambdaClient.<init>(AmazonS3, AmazonDynamoDB, SqsClient, InstanceProperties)"})
  void testNewQueryLambdaClient_thenReturnInstancePropertiesIsInstanceProperties() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    SqsClient sqsClient = mock(SqsClient.class);
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertSame(instanceProperties,
        (new QueryLambdaClient(s3Client, dynamoDBClient, sqsClient, instanceProperties)).getInstanceProperties());
  }

  /**
   * Test {@link QueryLambdaClient#submitQuery(Query)} with {@code query}.
   * <p>
   * Method under test: {@link QueryLambdaClient#submitQuery(Query)}
   */
  @Test
  @DisplayName("Test submitQuery(Query) with 'query'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryLambdaClient.submitQuery(Query)"})
  void testSubmitQueryWithQuery() throws AwsServiceException, SdkClientException {
    // Arrange
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.sendMessage(Mockito.<Consumer<Builder>>any())).thenReturn(null);
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    // Act
    (new QueryLambdaClient(s3Client, dynamoDBClient, sqsClient, new InstanceProperties()))
        .submitQuery(mock(Query.class));

    // Assert
    verify(sqsClient).sendMessage(isA(Consumer.class));
  }

  /**
   * Test {@link QueryLambdaClient#submitQuery(TableProperties, Query)} with {@code tableProperties}, {@code query}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryLambdaClient#submitQuery(TableProperties, Query)}
   */
  @Test
  @DisplayName("Test submitQuery(TableProperties, Query) with 'tableProperties', 'query'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryLambdaClient.submitQuery(TableProperties, Query)"})
  void testSubmitQueryWithTablePropertiesQuery_thenThrowIllegalArgumentException() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    SqsClient sqsClient = mock(SqsClient.class);
    QueryLambdaClient queryLambdaClient = new QueryLambdaClient(s3Client, dynamoDBClient, sqsClient,
        new InstanceProperties());
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Query query = mock(Query.class);
    when(query.getQueryId()).thenThrow(new IllegalArgumentException("Polling query tracker"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> queryLambdaClient.submitQuery(tableProperties, query));
    verify(query).getQueryId();
  }
}
