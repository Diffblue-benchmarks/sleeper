package sleeper.query.runner.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.ConcatenatingIterator;
import sleeper.core.iterator.EmptyIteratorWithFakeOnClose;
import sleeper.core.iterator.EmptyIteratorWithFakeOnClose.OnClose;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.query.core.model.LeafPartitionQuery;
import sleeper.query.core.model.LeafPartitionQuery.Builder;
import sleeper.query.core.model.QueryOrLeafPartitionQuery;
import sleeper.query.core.model.QueryProcessingConfig;
import sleeper.query.core.output.ResultsOutputInfo;
import sleeper.query.core.output.ResultsOutputLocation;

class S3ResultsOutputDiffblueTest {
  /**
   * Test {@link S3ResultsOutput#S3ResultsOutput(InstanceProperties, TableProperties, Map)}.
   * <p>
   * Method under test: {@link S3ResultsOutput#S3ResultsOutput(InstanceProperties, TableProperties, Map)}
   */
  @Test
  @DisplayName("Test new S3ResultsOutput(InstanceProperties, TableProperties, Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void S3ResultsOutput.<init>(InstanceProperties, TableProperties, Map)"})
  void testNewS3ResultsOutput() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    HashMap<String, String> config = new HashMap<>();
    config.put(S3ResultsOutput.S3_BUCKET, "Config");

    // Act
    S3ResultsOutput actualS3ResultsOutput = new S3ResultsOutput(instanceProperties, tableProperties, config);
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
    ResultsOutputInfo actualPublishResult = actualS3ResultsOutput.publish(query,
        new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    Exception error = actualPublishResult.getError();
    assertEquals("Cannot invoke \"sleeper.core.schema.Schema.getAllFields()\" because \"schema\" is null",
        error.getLocalizedMessage());
    assertEquals("Cannot invoke \"sleeper.core.schema.Schema.getAllFields()\" because \"schema\" is null",
        error.getMessage());
    List<ResultsOutputLocation> locations = actualPublishResult.getLocations();
    assertEquals(1, locations.size());
    assertEquals("s3", locations.get(0).getType());
    assertNull(error.getCause());
    assertEquals(0, error.getSuppressed().length);
    assertEquals(0L, actualPublishResult.getRecordCount());
    assertTrue(instanceProperties.getProperties().isEmpty());
    assertTrue(tableProperties.getProperties().isEmpty());
    assertTrue(instanceProperties.getTagsProperties().isEmpty());
  }

  /**
   * Test {@link S3ResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}.
   * <p>
   * Method under test: {@link S3ResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}
   */
  @Test
  @DisplayName("Test publish(QueryOrLeafPartitionQuery, CloseableIterator)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsOutputInfo S3ResultsOutput.publish(QueryOrLeafPartitionQuery, CloseableIterator)"})
  void testPublish() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    S3ResultsOutput s3ResultsOutput = new S3ResultsOutput(instanceProperties, tableProperties, new HashMap<>());
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

    // Act
    ResultsOutputInfo actualPublishResult = s3ResultsOutput.publish(query,
        new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    Exception error = actualPublishResult.getError();
    assertEquals("Cannot invoke \"sleeper.core.schema.Schema.getAllFields()\" because \"schema\" is null",
        error.getLocalizedMessage());
    assertEquals("Cannot invoke \"sleeper.core.schema.Schema.getAllFields()\" because \"schema\" is null",
        error.getMessage());
    List<ResultsOutputLocation> locations = actualPublishResult.getLocations();
    assertEquals(1, locations.size());
    assertEquals("s3", locations.get(0).getType());
    assertNull(error.getCause());
    assertEquals(0, error.getSuppressed().length);
    assertEquals(0L, actualPublishResult.getRecordCount());
  }

  /**
   * Test {@link S3ResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}.
   * <p>
   * Method under test: {@link S3ResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}
   */
  @Test
  @DisplayName("Test publish(QueryOrLeafPartitionQuery, CloseableIterator)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsOutputInfo S3ResultsOutput.publish(QueryOrLeafPartitionQuery, CloseableIterator)"})
  void testPublish2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(buildResult);
    S3ResultsOutput s3ResultsOutput = new S3ResultsOutput(instanceProperties, tableProperties, new HashMap<>());
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

    // Act
    ResultsOutputInfo actualPublishResult = s3ResultsOutput.publish(query,
        new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getSchema();
    Exception error = actualPublishResult.getError();
    assertEquals("No enum constant org.apache.parquet.hadoop.metadata.CompressionCodecName.GET",
        error.getLocalizedMessage());
    assertEquals("No enum constant org.apache.parquet.hadoop.metadata.CompressionCodecName.GET", error.getMessage());
    List<ResultsOutputLocation> locations = actualPublishResult.getLocations();
    assertEquals(1, locations.size());
    assertEquals("s3", locations.get(0).getType());
    assertNull(error.getCause());
    assertEquals(0, error.getSuppressed().length);
    assertEquals(0L, actualPublishResult.getRecordCount());
  }

  /**
   * Test {@link S3ResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code foo}.</li>
   *   <li>Then calls {@link OnClose#close()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3ResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}
   */
  @Test
  @DisplayName("Test publish(QueryOrLeafPartitionQuery, CloseableIterator); given IOException(String) with 'foo'; then calls close()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsOutputInfo S3ResultsOutput.publish(QueryOrLeafPartitionQuery, CloseableIterator)"})
  void testPublish_givenIOExceptionWithFoo_thenCallsClose() throws IOException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(buildResult);
    S3ResultsOutput s3ResultsOutput = new S3ResultsOutput(instanceProperties, tableProperties, new HashMap<>());
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
    OnClose onClose = mock(OnClose.class);
    doThrow(new IOException("foo")).when(onClose).close();

    // Act
    ResultsOutputInfo actualPublishResult = s3ResultsOutput.publish(query, new EmptyIteratorWithFakeOnClose(onClose));

    // Assert
    verify(onClose).close();
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getSchema();
    Exception error = actualPublishResult.getError();
    assertEquals("No enum constant org.apache.parquet.hadoop.metadata.CompressionCodecName.GET",
        error.getLocalizedMessage());
    assertEquals("No enum constant org.apache.parquet.hadoop.metadata.CompressionCodecName.GET", error.getMessage());
    List<ResultsOutputLocation> locations = actualPublishResult.getLocations();
    assertEquals(1, locations.size());
    assertEquals("s3", locations.get(0).getType());
    assertNull(error.getCause());
    assertEquals(0, error.getSuppressed().length);
    assertEquals(0L, actualPublishResult.getRecordCount());
  }

  /**
   * Test {@link S3ResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}.
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3ResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}
   */
  @Test
  @DisplayName("Test publish(QueryOrLeafPartitionQuery, CloseableIterator); given InstanceProperties get(InstanceProperty) return '/'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsOutputInfo S3ResultsOutput.publish(QueryOrLeafPartitionQuery, CloseableIterator)"})
  void testPublish_givenInstancePropertiesGetReturnSlash() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("/");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(buildResult);
    S3ResultsOutput s3ResultsOutput = new S3ResultsOutput(instanceProperties, tableProperties, new HashMap<>());
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

    // Act
    ResultsOutputInfo actualPublishResult = s3ResultsOutput.publish(query,
        new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getSchema();
    Exception error = actualPublishResult.getError();
    assertEquals("No enum constant org.apache.parquet.hadoop.metadata.CompressionCodecName.GET",
        error.getLocalizedMessage());
    assertEquals("No enum constant org.apache.parquet.hadoop.metadata.CompressionCodecName.GET", error.getMessage());
    List<ResultsOutputLocation> locations = actualPublishResult.getLocations();
    assertEquals(1, locations.size());
    assertEquals("s3", locations.get(0).getType());
    assertNull(error.getCause());
    assertEquals(0, error.getSuppressed().length);
    assertEquals(0L, actualPublishResult.getRecordCount());
  }

  /**
   * Test {@link S3ResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}.
   * <ul>
   *   <li>Then return Error LocalizedMessage is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3ResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}
   */
  @Test
  @DisplayName("Test publish(QueryOrLeafPartitionQuery, CloseableIterator); then return Error LocalizedMessage is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsOutputInfo S3ResultsOutput.publish(QueryOrLeafPartitionQuery, CloseableIterator)"})
  void testPublish_thenReturnErrorLocalizedMessageIsAString() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    S3ResultsOutput s3ResultsOutput = new S3ResultsOutput(instanceProperties, null, new HashMap<>());
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

    // Act
    ResultsOutputInfo actualPublishResult = s3ResultsOutput.publish(query,
        new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    Exception error = actualPublishResult.getError();
    assertEquals(
        "Cannot invoke \"sleeper.core.properties.table.TableProperties.getSchema()\" because \"tableProperties\""
            + " is null",
        error.getLocalizedMessage());
    assertEquals(
        "Cannot invoke \"sleeper.core.properties.table.TableProperties.getSchema()\" because \"tableProperties\""
            + " is null",
        error.getMessage());
    List<ResultsOutputLocation> locations = actualPublishResult.getLocations();
    assertEquals(1, locations.size());
    assertEquals("s3", locations.get(0).getType());
    assertNull(error.getCause());
    assertEquals(0, error.getSuppressed().length);
    assertEquals(0L, actualPublishResult.getRecordCount());
  }
}
