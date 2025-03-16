package sleeper.query.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.range.Region;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableStatus;
import sleeper.query.core.model.QueryProcessingConfig.Builder;
import sleeper.query.core.output.ResultsOutputInfo;
import sleeper.query.core.tracker.QueryStatusReportListener;

class QueryOrLeafPartitionQueryDiffblueTest {
  /**
   * Test {@link QueryOrLeafPartitionQuery#QueryOrLeafPartitionQuery(LeafPartitionQuery)}.
   * <ul>
   *   <li>When {@link LeafPartitionQuery}.</li>
   *   <li>Then return QueryId is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#QueryOrLeafPartitionQuery(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test new QueryOrLeafPartitionQuery(LeafPartitionQuery); when LeafPartitionQuery; then return QueryId is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryOrLeafPartitionQuery.<init>(LeafPartitionQuery)"})
  void testNewQueryOrLeafPartitionQuery_whenLeafPartitionQuery_thenReturnQueryIdIsNull() {
    // Arrange and Act
    QueryOrLeafPartitionQuery actualQueryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(
        mock(LeafPartitionQuery.class));

    // Assert
    assertNull(actualQueryOrLeafPartitionQuery.getQueryId());
    assertNull(actualQueryOrLeafPartitionQuery.getProcessingConfig());
    assertTrue(actualQueryOrLeafPartitionQuery.isLeafQuery());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#QueryOrLeafPartitionQuery(Query)}.
   * <ul>
   *   <li>When {@link Query}.</li>
   *   <li>Then return QueryId is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#QueryOrLeafPartitionQuery(Query)}
   */
  @Test
  @DisplayName("Test new QueryOrLeafPartitionQuery(Query); when Query; then return QueryId is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryOrLeafPartitionQuery.<init>(Query)"})
  void testNewQueryOrLeafPartitionQuery_whenQuery_thenReturnQueryIdIsNull() {
    // Arrange and Act
    QueryOrLeafPartitionQuery actualQueryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(mock(Query.class));

    // Assert
    assertNull(actualQueryOrLeafPartitionQuery.getQueryId());
    assertNull(actualQueryOrLeafPartitionQuery.getProcessingConfig());
    assertFalse(actualQueryOrLeafPartitionQuery.isLeafQuery());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#isLeafQuery()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#isLeafQuery()}
   */
  @Test
  @DisplayName("Test isLeafQuery(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryOrLeafPartitionQuery.isLeafQuery()"})
  void testIsLeafQuery_thenReturnFalse() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertFalse((new QueryOrLeafPartitionQuery(query)).isLeafQuery());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#isLeafQuery()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#isLeafQuery()}
   */
  @Test
  @DisplayName("Test isLeafQuery(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryOrLeafPartitionQuery.isLeafQuery()"})
  void testIsLeafQuery_thenReturnTrue() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertTrue((new QueryOrLeafPartitionQuery(leafQuery)).isLeafQuery());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#asParentQuery()}.
   * <ul>
   *   <li>Then return QueryId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#asParentQuery()}
   */
  @Test
  @DisplayName("Test asParentQuery(); then return QueryId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Query QueryOrLeafPartitionQuery.asParentQuery()"})
  void testAsParentQuery_thenReturnQueryIdIs42() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    ArrayList<String> requestedValueFields = new ArrayList<>();
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(requestedValueFields);
    HashMap<String, String> resultsPublisherConfig = new HashMap<>();
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(resultsPublisherConfig);
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult
        .statusReportDestinations(statusReportDestinations)
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act
    Query actualAsParentQueryResult = (new QueryOrLeafPartitionQuery(query)).asParentQuery();

    // Assert
    assertEquals("42", actualAsParentQueryResult.getQueryId());
    assertEquals("Query Time Iterator Class Name", actualAsParentQueryResult.getQueryTimeIteratorClassName());
    QueryProcessingConfig processingConfig2 = actualAsParentQueryResult.getProcessingConfig();
    assertEquals("Query Time Iterator Class Name", processingConfig2.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", actualAsParentQueryResult.getQueryTimeIteratorConfig());
    assertEquals("Query Time Iterator Config", processingConfig2.getQueryTimeIteratorConfig());
    assertEquals("Table Name", actualAsParentQueryResult.getTableName());
    assertTrue(actualAsParentQueryResult.getRegions().isEmpty());
    assertTrue(actualAsParentQueryResult.getRequestedValueFields().isEmpty());
    assertTrue(actualAsParentQueryResult.getStatusReportDestinations().isEmpty());
    assertTrue(actualAsParentQueryResult.getResultsPublisherConfig().isEmpty());
    assertSame(requestedValueFields, processingConfig2.getRequestedValueFields());
    assertSame(statusReportDestinations, processingConfig2.getStatusReportDestinations());
    assertSame(resultsPublisherConfig, processingConfig2.getResultsPublisherConfig());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#asLeafQuery()}.
   * <ul>
   *   <li>Then return LeafPartitionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#asLeafQuery()}
   */
  @Test
  @DisplayName("Test asLeafQuery(); then return LeafPartitionId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"LeafPartitionQuery QueryOrLeafPartitionQuery.asLeafQuery()"})
  void testAsLeafQuery_thenReturnLeafPartitionIdIs42() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    Region partitionRegion = new Region(new ArrayList<>());
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(partitionRegion);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act
    LeafPartitionQuery actualAsLeafQueryResult = (new QueryOrLeafPartitionQuery(leafQuery)).asLeafQuery();

    // Assert
    assertEquals("42", actualAsLeafQueryResult.getLeafPartitionId());
    assertEquals("42", actualAsLeafQueryResult.getQueryId());
    assertEquals("42", actualAsLeafQueryResult.getSubQueryId());
    assertEquals("42", actualAsLeafQueryResult.getTableId());
    assertEquals("Query Time Iterator Class Name", actualAsLeafQueryResult.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", actualAsLeafQueryResult.getQueryTimeIteratorConfig());
    assertTrue(actualAsLeafQueryResult.getFiles().isEmpty());
    assertTrue(actualAsLeafQueryResult.getRegions().isEmpty());
    assertTrue(actualAsLeafQueryResult.getRequestedValueFields().isEmpty());
    assertTrue(actualAsLeafQueryResult.getStatusReportDestinations().isEmpty());
    assertSame(partitionRegion, actualAsLeafQueryResult.getPartitionRegion());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#reportCompleted(QueryStatusReportListener, ResultsOutputInfo)}.
   * <ul>
   *   <li>Then calls {@link QueryStatusReportListener#queryCompleted(LeafPartitionQuery, ResultsOutputInfo)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#reportCompleted(QueryStatusReportListener, ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test reportCompleted(QueryStatusReportListener, ResultsOutputInfo); then calls queryCompleted(LeafPartitionQuery, ResultsOutputInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryOrLeafPartitionQuery.reportCompleted(QueryStatusReportListener, ResultsOutputInfo)"})
  void testReportCompleted_thenCallsQueryCompleted() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(leafQuery);
    QueryStatusReportListener listener = mock(QueryStatusReportListener.class);
    doNothing().when(listener).queryCompleted(Mockito.<LeafPartitionQuery>any(), Mockito.<ResultsOutputInfo>any());

    // Act
    queryOrLeafPartitionQuery.reportCompleted(listener, new ResultsOutputInfo(3L, new ArrayList<>()));

    // Assert
    verify(listener).queryCompleted(isA(LeafPartitionQuery.class), isA(ResultsOutputInfo.class));
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#reportCompleted(QueryStatusReportListener, ResultsOutputInfo)}.
   * <ul>
   *   <li>Then calls {@link QueryStatusReportListener#queryCompleted(Query, ResultsOutputInfo)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#reportCompleted(QueryStatusReportListener, ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test reportCompleted(QueryStatusReportListener, ResultsOutputInfo); then calls queryCompleted(Query, ResultsOutputInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryOrLeafPartitionQuery.reportCompleted(QueryStatusReportListener, ResultsOutputInfo)"})
  void testReportCompleted_thenCallsQueryCompleted2() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(query);
    QueryStatusReportListener listener = mock(QueryStatusReportListener.class);
    doNothing().when(listener).queryCompleted(Mockito.<Query>any(), Mockito.<ResultsOutputInfo>any());

    // Act
    queryOrLeafPartitionQuery.reportCompleted(listener, new ResultsOutputInfo(3L, new ArrayList<>()));

    // Assert
    verify(listener).queryCompleted(isA(Query.class), isA(ResultsOutputInfo.class));
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#reportFailed(QueryStatusReportListener, Exception)}.
   * <ul>
   *   <li>Then calls {@link QueryStatusReportListener#queryFailed(LeafPartitionQuery, Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#reportFailed(QueryStatusReportListener, Exception)}
   */
  @Test
  @DisplayName("Test reportFailed(QueryStatusReportListener, Exception); then calls queryFailed(LeafPartitionQuery, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryOrLeafPartitionQuery.reportFailed(QueryStatusReportListener, Exception)"})
  void testReportFailed_thenCallsQueryFailed() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(leafQuery);
    QueryStatusReportListener listener = mock(QueryStatusReportListener.class);
    doNothing().when(listener).queryFailed(Mockito.<LeafPartitionQuery>any(), Mockito.<Exception>any());

    // Act
    queryOrLeafPartitionQuery.reportFailed(listener, new Exception("foo"));

    // Assert
    verify(listener).queryFailed(isA(LeafPartitionQuery.class), isA(Exception.class));
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#reportFailed(QueryStatusReportListener, Exception)}.
   * <ul>
   *   <li>Then calls {@link QueryStatusReportListener#queryFailed(Query, Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#reportFailed(QueryStatusReportListener, Exception)}
   */
  @Test
  @DisplayName("Test reportFailed(QueryStatusReportListener, Exception); then calls queryFailed(Query, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryOrLeafPartitionQuery.reportFailed(QueryStatusReportListener, Exception)"})
  void testReportFailed_thenCallsQueryFailed2() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(query);
    QueryStatusReportListener listener = mock(QueryStatusReportListener.class);
    doNothing().when(listener).queryFailed(Mockito.<Query>any(), Mockito.<Exception>any());

    // Act
    queryOrLeafPartitionQuery.reportFailed(listener, new Exception("foo"));

    // Assert
    verify(listener).queryFailed(isA(Query.class), isA(Exception.class));
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#getQueryId()}.
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#getQueryId()}
   */
  @Test
  @DisplayName("Test getQueryId()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QueryOrLeafPartitionQuery.getQueryId()"})
  void testGetQueryId() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertEquals("42", (new QueryOrLeafPartitionQuery(leafQuery)).getQueryId());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#getQueryId()}.
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#getQueryId()}
   */
  @Test
  @DisplayName("Test getQueryId()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QueryOrLeafPartitionQuery.getQueryId()"})
  void testGetQueryId2() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertEquals("42", (new QueryOrLeafPartitionQuery(query)).getQueryId());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}.
   * <ul>
   *   <li>Then calls {@link TablePropertiesProvider#getById(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getTableProperties(TablePropertiesProvider); then calls getById(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties QueryOrLeafPartitionQuery.getTableProperties(TablePropertiesProvider)"})
  void testGetTableProperties_thenCallsGetById() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(leafQuery);
    TablePropertiesProvider provider = mock(TablePropertiesProvider.class);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    when(provider.getById(Mockito.<String>any())).thenReturn(tableProperties);

    // Act
    TableProperties actualTableProperties = queryOrLeafPartitionQuery.getTableProperties(provider);

    // Assert
    verify(provider).getById(eq("42"));
    assertSame(tableProperties, actualTableProperties);
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}.
   * <ul>
   *   <li>Then calls {@link TablePropertiesProvider#getByName(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getTableProperties(TablePropertiesProvider); then calls getByName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties QueryOrLeafPartitionQuery.getTableProperties(TablePropertiesProvider)"})
  void testGetTableProperties_thenCallsGetByName() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(query);
    TablePropertiesProvider provider = mock(TablePropertiesProvider.class);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    when(provider.getByName(Mockito.<String>any())).thenReturn(tableProperties);

    // Act
    TableProperties actualTableProperties = queryOrLeafPartitionQuery.getTableProperties(provider);

    // Assert
    verify(provider).getByName(eq("Table Name"));
    assertSame(tableProperties, actualTableProperties);
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}.
   * <ul>
   *   <li>Then calls {@link InMemoryTableIndex#getTableByName(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getTableProperties(TablePropertiesProvider); then calls getTableByName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties QueryOrLeafPartitionQuery.getTableProperties(TablePropertiesProvider)"})
  void testGetTableProperties_thenCallsGetTableByName() throws SleeperPropertiesInvalidException {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(query);
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    queryOrLeafPartitionQuery
        .getTableProperties(new TablePropertiesProvider(new InstanceProperties(), propertiesStore));

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}.
   * <ul>
   *   <li>Then calls {@link InMemoryTableIndex#getTableByUniqueId(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getTableProperties(TablePropertiesProvider); then calls getTableByUniqueId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties QueryOrLeafPartitionQuery.getTableProperties(TablePropertiesProvider)"})
  void testGetTableProperties_thenCallsGetTableByUniqueId() throws SleeperPropertiesInvalidException {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(leafQuery);
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    queryOrLeafPartitionQuery
        .getTableProperties(new TablePropertiesProvider(new InstanceProperties(), propertiesStore));

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(eq("42"));
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}.
   * <ul>
   *   <li>Then calls {@link TablePropertiesStore#loadById(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getTableProperties(TablePropertiesProvider); then calls loadById(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties QueryOrLeafPartitionQuery.getTableProperties(TablePropertiesProvider)"})
  void testGetTableProperties_thenCallsLoadById() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(leafQuery);
    TablePropertiesStore propertiesStore = mock(TablePropertiesStore.class);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    when(propertiesStore.loadById(Mockito.<String>any())).thenReturn(tableProperties);

    // Act
    TableProperties actualTableProperties = queryOrLeafPartitionQuery
        .getTableProperties(new TablePropertiesProvider(new InstanceProperties(), propertiesStore));

    // Assert
    verify(propertiesStore).loadById(eq("42"));
    assertSame(tableProperties, actualTableProperties);
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}.
   * <ul>
   *   <li>Then calls {@link TablePropertiesStore#loadByName(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#getTableProperties(TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test getTableProperties(TablePropertiesProvider); then calls loadByName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties QueryOrLeafPartitionQuery.getTableProperties(TablePropertiesProvider)"})
  void testGetTableProperties_thenCallsLoadByName() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(query);
    TablePropertiesStore propertiesStore = mock(TablePropertiesStore.class);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    when(propertiesStore.loadByName(Mockito.<String>any())).thenReturn(tableProperties);

    // Act
    TableProperties actualTableProperties = queryOrLeafPartitionQuery
        .getTableProperties(new TablePropertiesProvider(new InstanceProperties(), propertiesStore));

    // Assert
    verify(propertiesStore).loadByName(eq("Table Name"));
    assertSame(tableProperties, actualTableProperties);
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#getProcessingConfig()}.
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#getProcessingConfig()}
   */
  @Test
  @DisplayName("Test getProcessingConfig()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryProcessingConfig QueryOrLeafPartitionQuery.getProcessingConfig()"})
  void testGetProcessingConfig() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act
    QueryProcessingConfig actualProcessingConfig = (new QueryOrLeafPartitionQuery(leafQuery)).getProcessingConfig();

    // Assert
    assertEquals("Query Time Iterator Class Name", actualProcessingConfig.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", actualProcessingConfig.getQueryTimeIteratorConfig());
    assertTrue(actualProcessingConfig.getRequestedValueFields().isEmpty());
    assertTrue(actualProcessingConfig.getStatusReportDestinations().isEmpty());
    assertTrue(actualProcessingConfig.getResultsPublisherConfig().isEmpty());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#getProcessingConfig()}.
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#getProcessingConfig()}
   */
  @Test
  @DisplayName("Test getProcessingConfig()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryProcessingConfig QueryOrLeafPartitionQuery.getProcessingConfig()"})
  void testGetProcessingConfig2() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act
    QueryProcessingConfig actualProcessingConfig = (new QueryOrLeafPartitionQuery(query)).getProcessingConfig();

    // Assert
    assertEquals("Query Time Iterator Class Name", actualProcessingConfig.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", actualProcessingConfig.getQueryTimeIteratorConfig());
    assertTrue(actualProcessingConfig.getRequestedValueFields().isEmpty());
    assertTrue(actualProcessingConfig.getStatusReportDestinations().isEmpty());
    assertTrue(actualProcessingConfig.getResultsPublisherConfig().isEmpty());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#equals(Object)}, and {@link QueryOrLeafPartitionQuery#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link QueryOrLeafPartitionQuery#equals(Object)}
   *   <li>{@link QueryOrLeafPartitionQuery#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryOrLeafPartitionQuery.equals(Object)", "int QueryOrLeafPartitionQuery.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(leafQuery);
    LeafPartitionQuery.Builder builderResult2 = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult2 = builderResult2.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult2 = leafPartitionIdResult2
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult2 = partitionRegionResult2.processingConfig(processingConfig2)
        .queryId("42");
    LeafPartitionQuery leafQuery2 = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery2 = new QueryOrLeafPartitionQuery(leafQuery2);

    // Act and Assert
    assertEquals(queryOrLeafPartitionQuery, queryOrLeafPartitionQuery2);
    int expectedHashCodeResult = queryOrLeafPartitionQuery.hashCode();
    assertEquals(expectedHashCodeResult, queryOrLeafPartitionQuery2.hashCode());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#equals(Object)}, and {@link QueryOrLeafPartitionQuery#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link QueryOrLeafPartitionQuery#equals(Object)}
   *   <li>{@link QueryOrLeafPartitionQuery#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryOrLeafPartitionQuery.equals(Object)", "int QueryOrLeafPartitionQuery.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(leafQuery);

    // Act and Assert
    assertEquals(queryOrLeafPartitionQuery, queryOrLeafPartitionQuery);
    int expectedHashCodeResult = queryOrLeafPartitionQuery.hashCode();
    assertEquals(expectedHashCodeResult, queryOrLeafPartitionQuery.hashCode());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryOrLeafPartitionQuery.equals(Object)", "int QueryOrLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");
    LeafPartitionQuery.Builder leafPartitionIdResult = LeafPartitionQuery.builder().files(files).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(leafQuery);
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult2 = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult2 = leafPartitionIdResult2
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult2 = partitionRegionResult2.processingConfig(processingConfig2)
        .queryId("42");
    LeafPartitionQuery leafQuery2 = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(queryOrLeafPartitionQuery, new QueryOrLeafPartitionQuery(leafQuery2));
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryOrLeafPartitionQuery.equals(Object)", "int QueryOrLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    QueryOrLeafPartitionQuery queryOrLeafPartitionQuery = new QueryOrLeafPartitionQuery(query);
    LeafPartitionQuery.Builder builderResult2 = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult2.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult2 = partitionRegionResult.processingConfig(processingConfig2).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(queryOrLeafPartitionQuery, new QueryOrLeafPartitionQuery(leafQuery));
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryOrLeafPartitionQuery.equals(Object)", "int QueryOrLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(new QueryOrLeafPartitionQuery(leafQuery), null);
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryOrLeafPartitionQuery.equals(Object)", "int QueryOrLeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(new QueryOrLeafPartitionQuery(leafQuery), "Different type to QueryOrLeafPartitionQuery");
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#toString()}.
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QueryOrLeafPartitionQuery.toString()"})
  void testToString() {
    // Arrange
    LeafPartitionQuery.Builder builderResult = LeafPartitionQuery.builder();
    LeafPartitionQuery.Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    LeafPartitionQuery.Builder partitionRegionResult = leafPartitionIdResult
        .partitionRegion(new Region(new ArrayList<>()));
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    LeafPartitionQuery.Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertEquals("LeafPartitionQuery{tableId='42', queryId='42', subQueryId='42', regions=[], processingConfig"
        + "=QueryProcessingConfig{queryTimeIteratorClassName='Query Time Iterator Class Name', queryTimeIteratorConfig"
        + "='Query Time Iterator Config', resultsPublisherConfig={}, statusReportDestinations=[], requestedValueFields"
        + "=[]}, leafPartitionId='42', partitionRegion=Region{rowKeyFieldNameToRange={}}, files=[]}",
        (new QueryOrLeafPartitionQuery(leafQuery)).toString());
  }

  /**
   * Test {@link QueryOrLeafPartitionQuery#toString()}.
   * <p>
   * Method under test: {@link QueryOrLeafPartitionQuery#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QueryOrLeafPartitionQuery.toString()"})
  void testToString2() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertEquals(
        "Query{tableName='Table Name', queryId='42', regions=[], processingConfig=QueryProcessingConfig"
            + "{queryTimeIteratorClassName='Query Time Iterator Class Name', queryTimeIteratorConfig='Query Time"
            + " Iterator Config', resultsPublisherConfig={}, statusReportDestinations=[], requestedValueFields=[]}}",
        (new QueryOrLeafPartitionQuery(query)).toString());
  }
}
