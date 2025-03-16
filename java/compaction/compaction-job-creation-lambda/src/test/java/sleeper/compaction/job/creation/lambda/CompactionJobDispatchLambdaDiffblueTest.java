package sleeper.compaction.job.creation.lambda;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.AddPermissionRequest;
import com.amazonaws.services.sqs.model.AddPermissionResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;

class CompactionJobDispatchLambdaDiffblueTest {
  /**
   * Test {@link CompactionJobDispatchLambda#dispatcher(AmazonS3, AmazonDynamoDB, AmazonSQS, Configuration, InstanceProperties, Supplier)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatchLambda#dispatcher(AmazonS3, AmazonDynamoDB, AmazonSQS, Configuration, InstanceProperties, Supplier)}
   */
  @Test
  @DisplayName("Test dispatcher(AmazonS3, AmazonDynamoDB, AmazonSQS, Configuration, InstanceProperties, Supplier); given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.compaction.core.job.dispatch.CompactionJobDispatcher CompactionJobDispatchLambda.dispatcher(AmazonS3, AmazonDynamoDB, AmazonSQS, Configuration, InstanceProperties, Supplier)"})
  void testDispatcher_givenPutItemRequest() {
    // Arrange
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler);
    AsyncHandler<AddPermissionRequest, AddPermissionResult> asyncHandler2 = mock(AsyncHandler.class);
    doNothing().when(asyncHandler2).onError(Mockito.<Exception>any());

    AmazonSQSAsyncClient sqs = new AmazonSQSAsyncClient();
    ArrayList<String> aWSAccountIds = new ArrayList<>();
    sqs.addPermissionAsync("https://example.org/example", "compaction-job-updates", aWSAccountIds, new ArrayList<>(),
        asyncHandler2);
    Configuration conf = new Configuration();

    // Act
    CompactionJobDispatchLambda.dispatcher(null, dynamoDB, sqs, conf, new InstanceProperties(), mock(Supplier.class));

    // Assert
    verify(asyncHandler).onError(Mockito.<Exception>any());
  }
}
