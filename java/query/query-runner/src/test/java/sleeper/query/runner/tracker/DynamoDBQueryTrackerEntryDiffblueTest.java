package sleeper.query.runner.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.range.Region;
import sleeper.query.core.model.LeafPartitionQuery;
import sleeper.query.core.model.Query;
import sleeper.query.core.model.QueryProcessingConfig;
import sleeper.query.core.output.ResultsOutputInfo;
import sleeper.query.core.output.ResultsOutputLocation;
import sleeper.query.core.tracker.QueryState;
import sleeper.query.runner.tracker.DynamoDBQueryTrackerEntry.Builder;

class DynamoDBQueryTrackerEntryDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#errorMessage(String)}
   *   <li>{@link Builder#queryId(String)}
   *   <li>{@link Builder#recordCount(long)}
   *   <li>{@link Builder#state(QueryState)}
   *   <li>{@link Builder#subQueryId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBQueryTrackerEntry Builder.build()", "Builder Builder.errorMessage(String)",
      "Builder Builder.queryId(String)", "Builder Builder.recordCount(long)", "Builder Builder.state(QueryState)",
      "Builder Builder.subQueryId(String)"})
  void testBuilderBuild() {
    // Arrange and Act
    DynamoDBQueryTrackerEntry actualBuildResult = DynamoDBQueryTrackerEntry.builder()
        .errorMessage("An error occurred")
        .queryId("42")
        .recordCount(3L)
        .state(QueryState.COMPLETED)
        .subQueryId("42")
        .build();

    // Assert
    Map<String, AttributeValue> key = actualBuildResult.getKey();
    assertEquals(2, key.size());
    AttributeValue getResult = key.get(DynamoDBQueryTracker.QUERY_ID);
    assertEquals("42", getResult.getS());
    assertEquals("42", actualBuildResult.getQueryId());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    assertTrue(actualBuildResult.isUpdateParent());
  }

  /**
   * Test Builder {@link Builder#completed(ResultsOutputInfo)}.
   * <p>
   * Method under test: {@link Builder#completed(ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test Builder completed(ResultsOutputInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.completed(ResultsOutputInfo)"})
  void testBuilderCompleted() {
    // Arrange
    Builder builderResult = DynamoDBQueryTrackerEntry.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.completed(new ResultsOutputInfo(3L, new ArrayList<>())));
  }

  /**
   * Test Builder {@link Builder#completed(ResultsOutputInfo)}.
   * <p>
   * Method under test: {@link Builder#completed(ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test Builder completed(ResultsOutputInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.completed(ResultsOutputInfo)"})
  void testBuilderCompleted2() {
    // Arrange
    Builder builderResult = DynamoDBQueryTrackerEntry.builder();
    ArrayList<ResultsOutputLocation> locations = new ArrayList<>();

    // Act and Assert
    assertSame(builderResult, builderResult.completed(new ResultsOutputInfo(3L, locations, new Exception("foo"))));
  }

  /**
   * Test Builder {@link Builder#completed(ResultsOutputInfo)}.
   * <p>
   * Method under test: {@link Builder#completed(ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test Builder completed(ResultsOutputInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.completed(ResultsOutputInfo)"})
  void testBuilderCompleted3() {
    // Arrange
    Builder builderResult = DynamoDBQueryTrackerEntry.builder();
    ArrayList<ResultsOutputLocation> locations = new ArrayList<>();

    // Act and Assert
    assertSame(builderResult, builderResult.completed(new ResultsOutputInfo(0L, locations, new Exception("foo"))));
  }

  /**
   * Test Builder {@link Builder#failed(Exception)}.
   * <ul>
   *   <li>When {@link Exception#Exception(String)} with {@code foo}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#failed(Exception)}
   */
  @Test
  @DisplayName("Test Builder failed(Exception); when Exception(String) with 'foo'; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.failed(Exception)"})
  void testBuilderFailed_whenExceptionWithFoo_thenReturnBuilder() {
    // Arrange
    Builder builderResult = DynamoDBQueryTrackerEntry.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.failed(new Exception("foo")));
  }

  /**
   * Test {@link DynamoDBQueryTrackerEntry#getKey()}.
   * <p>
   * Method under test: {@link DynamoDBQueryTrackerEntry#getKey()}
   */
  @Test
  @DisplayName("Test getKey()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBQueryTrackerEntry.getKey()"})
  void testGetKey() {
    // Arrange
    DynamoDBQueryTrackerEntry buildResult = DynamoDBQueryTrackerEntry.builder()
        .errorMessage("An error occurred")
        .queryId("42")
        .recordCount(3L)
        .state(QueryState.COMPLETED)
        .subQueryId("42")
        .build();

    // Act
    Map<String, AttributeValue> actualKey = buildResult.getKey();

    // Assert
    assertEquals(2, actualKey.size());
    AttributeValue getResult = actualKey.get(DynamoDBQueryTracker.QUERY_ID);
    assertEquals("42", getResult.getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    assertEquals(getResult, actualKey.get(DynamoDBQueryTracker.SUB_QUERY_ID));
  }

  /**
   * Test {@link DynamoDBQueryTrackerEntry#getValueUpdate(long)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTrackerEntry#getValueUpdate(long)}
   */
  @Test
  @DisplayName("Test getValueUpdate(long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBQueryTrackerEntry.getValueUpdate(long)"})
  void testGetValueUpdate() {
    // Arrange
    DynamoDBQueryTrackerEntry buildResult = DynamoDBQueryTrackerEntry.builder()
        .errorMessage("An error occurred")
        .queryId("42")
        .recordCount(3L)
        .state(QueryState.COMPLETED)
        .subQueryId("42")
        .build();

    // Act
    Map<String, AttributeValueUpdate> actualValueUpdate = buildResult.getValueUpdate(1L);

    // Assert
    assertEquals(5, actualValueUpdate.size());
    assertEquals("PUT", actualValueUpdate.get("errors").getAction());
    assertEquals("PUT", actualValueUpdate.get("expiryDate").getAction());
    assertEquals("PUT", actualValueUpdate.get("lastKnownState").getAction());
    assertEquals("PUT", actualValueUpdate.get("lastUpdateTime").getAction());
    assertEquals("PUT", actualValueUpdate.get("recordCount").getAction());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DynamoDBQueryTrackerEntry#withLeafQuery(LeafPartitionQuery)}
   *   <li>{@link DynamoDBQueryTrackerEntry#withQuery(Query)}
   *   <li>{@link DynamoDBQueryTrackerEntry#getQueryId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBQueryTrackerEntry.getQueryId()",
      "Builder DynamoDBQueryTrackerEntry.withLeafQuery(LeafPartitionQuery)",
      "Builder DynamoDBQueryTrackerEntry.withQuery(Query)"})
  void testGettersAndSetters() {
    // Arrange
    DynamoDBQueryTrackerEntry buildResult = DynamoDBQueryTrackerEntry.builder()
        .errorMessage("An error occurred")
        .queryId("42")
        .recordCount(3L)
        .state(QueryState.COMPLETED)
        .subQueryId("42")
        .build();
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult = queryTimeIteratorConfigResult
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult = requestedValueFieldsResult
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery query = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act
    buildResult.withLeafQuery(query);
    Query.Builder builderResult2 = Query.builder();
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult2 = builderResult2.processingConfig(processingConfig2).queryId("42");
    Query query2 = queryIdResult2.regions(new ArrayList<>()).tableName("Table Name").build();
    buildResult.withQuery(query2);

    // Assert
    assertEquals("42", buildResult.getQueryId());
  }

  /**
   * Test {@link DynamoDBQueryTrackerEntry#updateParent(QueryState, long)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTrackerEntry#updateParent(QueryState, long)}
   */
  @Test
  @DisplayName("Test updateParent(QueryState, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DynamoDBQueryTrackerEntry DynamoDBQueryTrackerEntry.updateParent(QueryState, long)"})
  void testUpdateParent() {
    // Arrange
    DynamoDBQueryTrackerEntry buildResult = DynamoDBQueryTrackerEntry.builder()
        .errorMessage("An error occurred")
        .queryId("42")
        .recordCount(3L)
        .state(QueryState.COMPLETED)
        .subQueryId("42")
        .build();

    // Act
    DynamoDBQueryTrackerEntry actualUpdateParentResult = buildResult.updateParent(QueryState.COMPLETED, 3L);

    // Assert
    assertEquals("42", actualUpdateParentResult.getQueryId());
    Map<String, AttributeValue> key = actualUpdateParentResult.getKey();
    assertEquals(2, key.size());
    assertFalse(actualUpdateParentResult.isUpdateParent());
    assertTrue(key.containsKey(DynamoDBQueryTracker.QUERY_ID));
    assertTrue(key.containsKey(DynamoDBQueryTracker.SUB_QUERY_ID));
  }
}
