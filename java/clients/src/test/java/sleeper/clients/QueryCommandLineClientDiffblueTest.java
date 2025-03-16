package sleeper.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.range.Range;
import sleeper.core.range.Range.RangeFactory;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.query.core.model.Query;
import sleeper.query.core.model.QueryProcessingConfig;
import software.amazon.awssdk.services.sqs.SqsClient;

class QueryCommandLineClientDiffblueTest {
  /**
   * Test {@link QueryCommandLineClient#getTableProperties()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryCommandLineClient#getTableProperties()}
   */
  @Test
  @DisplayName("Test getTableProperties(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties QueryCommandLineClient.getTableProperties()"})
  void testGetTableProperties_thenReturnNull() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDBClient = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDBClient.scan(Mockito.<ScanRequest>any())).thenReturn(null);
    AmazonS3Client s3Client = new AmazonS3Client();
    SqsClient sqsClient = mock(SqsClient.class);

    // Act
    TableProperties actualTableProperties = (new QueryLambdaClient(s3Client, dynamoDBClient, sqsClient,
        new InstanceProperties())).getTableProperties();

    // Assert
    verify(dynamoDBClient).scan(isA(ScanRequest.class));
    assertNull(actualTableProperties);
  }

  /**
   * Test {@link QueryCommandLineClient#constructExactQuery(String, Schema, RangeFactory)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code Table Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryCommandLineClient#constructExactQuery(String, Schema, RangeFactory)}
   */
  @Test
  @DisplayName("Test constructExactQuery(String, Schema, RangeFactory); given ArrayList(); then return 'Table Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Query QueryCommandLineClient.constructExactQuery(String, Schema, RangeFactory)"})
  void testConstructExactQuery_givenArrayList_thenReturnTableName() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    SqsClient sqsClient = mock(SqsClient.class);
    QueryLambdaClient queryLambdaClient = new QueryLambdaClient(s3Client, dynamoDBClient, sqsClient,
        new InstanceProperties());
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    Query actualConstructExactQueryResult = queryLambdaClient.constructExactQuery("Table Name", schema,
        new RangeFactory(schema2));

    // Assert
    verify(schema).getRowKeyFields();
    assertEquals("Table Name", actualConstructExactQueryResult.getTableName());
    assertNull(actualConstructExactQueryResult.getQueryTimeIteratorClassName());
    assertNull(actualConstructExactQueryResult.getQueryTimeIteratorConfig());
    QueryProcessingConfig processingConfig = actualConstructExactQueryResult.getProcessingConfig();
    assertNull(processingConfig.getQueryTimeIteratorClassName());
    assertNull(processingConfig.getQueryTimeIteratorConfig());
    assertNull(actualConstructExactQueryResult.getRequestedValueFields());
    assertNull(processingConfig.getRequestedValueFields());
    assertEquals(1, actualConstructExactQueryResult.getRegions().size());
    List<Map<String, String>> statusReportDestinations = actualConstructExactQueryResult.getStatusReportDestinations();
    assertTrue(statusReportDestinations.isEmpty());
    Map<String, String> resultsPublisherConfig = actualConstructExactQueryResult.getResultsPublisherConfig();
    assertTrue(resultsPublisherConfig.isEmpty());
    assertSame(resultsPublisherConfig, processingConfig.getResultsPublisherConfig());
    assertSame(statusReportDestinations, processingConfig.getStatusReportDestinations());
  }

  /**
   * Test {@link QueryCommandLineClient#getInstanceProperties()}.
   * <p>
   * Method under test: {@link QueryCommandLineClient#getInstanceProperties()}
   */
  @Test
  @DisplayName("Test getInstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties QueryCommandLineClient.getInstanceProperties()"})
  void testGetInstanceProperties() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    SqsClient sqsClient = mock(SqsClient.class);
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertSame(instanceProperties,
        (new QueryLambdaClient(s3Client, dynamoDBClient, sqsClient, instanceProperties)).getInstanceProperties());
  }
}
