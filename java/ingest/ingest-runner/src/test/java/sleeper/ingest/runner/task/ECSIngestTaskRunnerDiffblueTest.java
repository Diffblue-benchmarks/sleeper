package sleeper.ingest.runner.task;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.util.ObjectFactory;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.internal.crossregion.S3CrossRegionAsyncClient;

class ECSIngestTaskRunnerDiffblueTest {
  /**
   * Test {@link ECSIngestTaskRunner#createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link InstanceProperties} {@link SleeperPropertyValues#getBoolean(SleeperProperty)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ECSIngestTaskRunner#createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration)}
   */
  @Test
  @DisplayName("Test createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration); given 'false'; when InstanceProperties getBoolean(SleeperProperty) return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.ingest.core.IngestTask ECSIngestTaskRunner.createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration)"})
  void testCreateIngestTask_givenFalse_whenInstancePropertiesGetBooleanReturnFalse() {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(false);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
    S3CrossRegionAsyncClient s3AsyncClient = new S3CrossRegionAsyncClient(mock(S3AsyncClient.class));

    // Act
    ECSIngestTaskRunner.createIngestTask(objectFactory, instanceProperties, "Local Dir", "42", s3Client, dynamoDBClient,
        sqsClient, cloudWatchClient, s3AsyncClient, new Configuration());

    // Assert
    verify(instanceProperties, atLeast(1)).getBoolean(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link ECSIngestTaskRunner#createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link InstanceProperties} {@link SleeperPropertyValues#getBoolean(SleeperProperty)} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ECSIngestTaskRunner#createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration)}
   */
  @Test
  @DisplayName("Test createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration); given 'true'; when InstanceProperties getBoolean(SleeperProperty) return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.ingest.core.IngestTask ECSIngestTaskRunner.createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration)"})
  void testCreateIngestTask_givenTrue_whenInstancePropertiesGetBooleanReturnTrue() {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();
    S3CrossRegionAsyncClient s3AsyncClient = new S3CrossRegionAsyncClient(mock(S3AsyncClient.class));

    // Act
    ECSIngestTaskRunner.createIngestTask(objectFactory, instanceProperties, "Local Dir", "42", s3Client, dynamoDBClient,
        sqsClient, cloudWatchClient, s3AsyncClient, new Configuration());

    // Assert
    verify(instanceProperties, atLeast(1)).getBoolean(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link ECSIngestTaskRunner#createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link InstanceProperties} {@link SleeperPropertyValues#getBoolean(SleeperProperty)} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ECSIngestTaskRunner#createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration)}
   */
  @Test
  @DisplayName("Test createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration); given 'true'; when InstanceProperties getBoolean(SleeperProperty) return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.ingest.core.IngestTask ECSIngestTaskRunner.createIngestTask(ObjectFactory, InstanceProperties, String, String, AmazonS3, AmazonDynamoDB, AmazonSQS, AmazonCloudWatch, S3AsyncClient, Configuration)"})
  void testCreateIngestTask_givenTrue_whenInstancePropertiesGetBooleanReturnTrue2() {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    AmazonCloudWatchAsyncClient cloudWatchClient = new AmazonCloudWatchAsyncClient();

    // Act
    ECSIngestTaskRunner.createIngestTask(objectFactory, instanceProperties, "Local Dir", "42", s3Client, dynamoDBClient,
        sqsClient, cloudWatchClient, new S3CrossRegionAsyncClient(mock(S3AsyncClient.class)), null);

    // Assert
    verify(instanceProperties, atLeast(1)).getBoolean(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).getInt(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }
}
