package sleeper.query.runner.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.iterator.ConcatenatingIterator;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.range.Region;
import sleeper.core.schema.Schema;
import sleeper.query.core.model.LeafPartitionQuery;
import sleeper.query.core.model.LeafPartitionQuery.Builder;
import sleeper.query.core.model.QueryOrLeafPartitionQuery;
import sleeper.query.core.model.QueryProcessingConfig;
import sleeper.query.core.output.ResultsOutputInfo;
import sleeper.query.core.output.ResultsOutputLocation;

class SQSResultsOutputDiffblueTest {
  /**
   * Test {@link SQSResultsOutput#SQSResultsOutput(InstanceProperties, AmazonSQS, Schema, Map)}.
   * <p>
   * Method under test: {@link SQSResultsOutput#SQSResultsOutput(InstanceProperties, AmazonSQS, Schema, Map)}
   */
  @Test
  @DisplayName("Test new SQSResultsOutput(InstanceProperties, AmazonSQS, Schema, Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SQSResultsOutput.<init>(InstanceProperties, AmazonSQS, Schema, Map)"})
  void testNewSQSResultsOutput() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    AmazonSQSAsyncClient amazonSQS = new AmazonSQSAsyncClient();
    Schema schema = mock(Schema.class);

    HashMap<String, String> config = new HashMap<>();
    config.put(SQSResultsOutput.SQS_RESULTS_URL, "Config");
    config.put(SQSResultsOutput.BATCH_SIZE, null);

    // Act
    SQSResultsOutput actualSqsResultsOutput = new SQSResultsOutput(instanceProperties, amazonSQS, schema, config);
    Builder builderResult = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult = queryTimeIteratorConfigResult
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult = requestedValueFieldsResult
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery query = new QueryOrLeafPartitionQuery(leafQuery);
    ResultsOutputInfo actualPublishResult = actualSqsResultsOutput.publish(query,
        new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    Exception error = actualPublishResult.getError();
    assertTrue(error instanceof SdkClientException);
    List<ResultsOutputLocation> locations = actualPublishResult.getLocations();
    assertEquals(1, locations.size());
    ResultsOutputLocation getResult = locations.get(0);
    assertEquals("Config", getResult.getLocation());
    assertEquals("sqs", getResult.getType());
    assertNull(error.getCause());
    assertEquals(0, error.getSuppressed().length);
    assertEquals(0L, actualPublishResult.getRecordCount());
    assertTrue(((SdkClientException) error).isRetryable());
    assertTrue(instanceProperties.getProperties().isEmpty());
    assertTrue(instanceProperties.getTagsProperties().isEmpty());
  }

  /**
   * Test {@link SQSResultsOutput#SQSResultsOutput(InstanceProperties, AmazonSQS, Schema, Map)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SQSResultsOutput#SQSResultsOutput(InstanceProperties, AmazonSQS, Schema, Map)}
   */
  @Test
  @DisplayName("Test new SQSResultsOutput(InstanceProperties, AmazonSQS, Schema, Map); when 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SQSResultsOutput.<init>(InstanceProperties, AmazonSQS, Schema, Map)"})
  void testNewSQSResultsOutput_whenNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    AmazonSQSAsyncClient amazonSQS = new AmazonSQSAsyncClient();

    HashMap<String, String> config = new HashMap<>();
    config.put(SQSResultsOutput.SQS_RESULTS_URL, "Config");
    config.put(SQSResultsOutput.BATCH_SIZE, null);

    // Act
    SQSResultsOutput actualSqsResultsOutput = new SQSResultsOutput(instanceProperties, amazonSQS, null, config);
    Builder builderResult = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult = queryTimeIteratorConfigResult
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult = requestedValueFieldsResult
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery query = new QueryOrLeafPartitionQuery(leafQuery);
    ResultsOutputInfo actualPublishResult = actualSqsResultsOutput.publish(query,
        new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    Exception error = actualPublishResult.getError();
    assertTrue(error instanceof SdkClientException);
    assertNull(error.getCause());
    assertEquals(0, error.getSuppressed().length);
    assertEquals(0L, actualPublishResult.getRecordCount());
    assertEquals(1, actualPublishResult.getLocations().size());
    assertTrue(((SdkClientException) error).isRetryable());
    assertTrue(instanceProperties.getProperties().isEmpty());
    assertTrue(instanceProperties.getTagsProperties().isEmpty());
  }
}
