package sleeper.systemtest.dsl.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.StateStore;
import sleeper.query.core.model.Query;
import sleeper.query.core.model.QueryProcessingConfig;
import sleeper.query.core.model.QueryProcessingConfig.Builder;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.util.SystemTestSchema;

class QueryCreatorDiffblueTest {
  /**
   * Test {@link QueryCreator#QueryCreator(SystemTestInstanceContext)}.
   * <p>
   * Method under test: {@link QueryCreator#QueryCreator(SystemTestInstanceContext)}
   */
  @Test
  @DisplayName("Test new QueryCreator(SystemTestInstanceContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryCreator.<init>(SystemTestInstanceContext)"})
  void testNewQueryCreator() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(mock(StateStore.class));
    when(instance.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));

    // Act
    new QueryCreator(instance);

    // Assert
    verify(instance).getStateStore(isA(TableProperties.class));
    verify(instance).getTableProperties();
  }

  /**
   * Test {@link QueryCreator#QueryCreator(SystemTestInstanceContext)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link TableProperties#get(TableProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryCreator#QueryCreator(SystemTestInstanceContext)}
   */
  @Test
  @DisplayName("Test new QueryCreator(SystemTestInstanceContext); given TableProperties get(TableProperty) return 'Get'; then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryCreator.<init>(SystemTestInstanceContext)"})
  void testNewQueryCreator_givenTablePropertiesGetReturnGet_thenCallsGet() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(SystemTestSchema.DEFAULT_SCHEMA);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(mock(StateStore.class));
    when(instance.getTableProperties()).thenReturn(tableProperties);

    // Act
    new QueryCreator(instance);

    // Assert
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getSchema();
    verify(instance).getStateStore(isA(TableProperties.class));
    verify(instance).getTableProperties();
  }

  /**
   * Test {@link QueryCreator#forAllTables(SystemTestInstanceContext, Function)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} stream.</li>
   *   <li>When {@link Function}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryCreator#forAllTables(SystemTestInstanceContext, Function)}
   */
  @Test
  @DisplayName("Test forAllTables(SystemTestInstanceContext, Function); given ArrayList() stream; when Function; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List QueryCreator.forAllTables(SystemTestInstanceContext, Function)"})
  void testForAllTables_givenArrayListStream_whenFunction_thenReturnEmpty() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    when(instance.streamTableProperties()).thenReturn(streamResult);

    // Act
    List<Query> actualForAllTablesResult = QueryCreator.forAllTables(instance, mock(Function.class));

    // Assert
    verify(instance).streamTableProperties();
    assertTrue(actualForAllTablesResult.isEmpty());
  }

  /**
   * Test {@link QueryCreator#forAllTables(SystemTestInstanceContext, Function)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link TableProperties#get(TableProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryCreator#forAllTables(SystemTestInstanceContext, Function)}
   */
  @Test
  @DisplayName("Test forAllTables(SystemTestInstanceContext, Function); given TableProperties get(TableProperty) return 'Get'; then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List QueryCreator.forAllTables(SystemTestInstanceContext, Function)"})
  void testForAllTables_givenTablePropertiesGetReturnGet_thenCallsGet() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(SystemTestSchema.DEFAULT_SCHEMA);

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(tableProperties);
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(mock(StateStore.class));
    when(instance.streamTableProperties()).thenReturn(streamResult);
    Function<QueryCreator, Query> queryFactory = mock(Function.class);
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    when(queryFactory.apply(Mockito.<QueryCreator>any())).thenReturn(buildResult);

    // Act
    List<Query> actualForAllTablesResult = QueryCreator.forAllTables(instance, queryFactory);

    // Assert
    verify(queryFactory).apply(isA(QueryCreator.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getSchema();
    verify(instance).getStateStore(isA(TableProperties.class));
    verify(instance).streamTableProperties();
    assertEquals(1, actualForAllTablesResult.size());
    QueryProcessingConfig processingConfig2 = actualForAllTablesResult.get(0).getProcessingConfig();
    assertEquals("Query Time Iterator Class Name", processingConfig2.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", processingConfig2.getQueryTimeIteratorConfig());
    assertSame(requestedValueFields, processingConfig2.getRequestedValueFields());
    assertSame(statusReportDestinations, processingConfig2.getStatusReportDestinations());
    assertSame(resultsPublisherConfig, processingConfig2.getResultsPublisherConfig());
  }

  /**
   * Test {@link QueryCreator#forAllTables(SystemTestInstanceContext, Function)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryCreator#forAllTables(SystemTestInstanceContext, Function)}
   */
  @Test
  @DisplayName("Test forAllTables(SystemTestInstanceContext, Function); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List QueryCreator.forAllTables(SystemTestInstanceContext, Function)"})
  void testForAllTables_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(new TableProperties(new InstanceProperties()));
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(mock(StateStore.class));
    when(instance.streamTableProperties()).thenReturn(streamResult);
    Function<QueryCreator, Query> queryFactory = mock(Function.class);
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    when(queryFactory.apply(Mockito.<QueryCreator>any())).thenReturn(buildResult);

    // Act
    List<Query> actualForAllTablesResult = QueryCreator.forAllTables(instance, queryFactory);

    // Assert
    verify(queryFactory).apply(isA(QueryCreator.class));
    verify(instance).getStateStore(isA(TableProperties.class));
    verify(instance).streamTableProperties();
    assertEquals(1, actualForAllTablesResult.size());
    QueryProcessingConfig processingConfig2 = actualForAllTablesResult.get(0).getProcessingConfig();
    assertEquals("Query Time Iterator Class Name", processingConfig2.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", processingConfig2.getQueryTimeIteratorConfig());
    assertSame(requestedValueFields, processingConfig2.getRequestedValueFields());
    assertSame(statusReportDestinations, processingConfig2.getStatusReportDestinations());
    assertSame(resultsPublisherConfig, processingConfig2.getResultsPublisherConfig());
  }

  /**
   * Test {@link QueryCreator#forAllTables(SystemTestInstanceContext, Function)}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryCreator#forAllTables(SystemTestInstanceContext, Function)}
   */
  @Test
  @DisplayName("Test forAllTables(SystemTestInstanceContext, Function); then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List QueryCreator.forAllTables(SystemTestInstanceContext, Function)"})
  void testForAllTables_thenReturnSizeIsTwo() {
    // Arrange
    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(new TableProperties(new InstanceProperties()));
    tablePropertiesList.add(new TableProperties(new InstanceProperties()));
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(mock(StateStore.class));
    when(instance.streamTableProperties()).thenReturn(streamResult);
    Function<QueryCreator, Query> queryFactory = mock(Function.class);
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    when(queryFactory.apply(Mockito.<QueryCreator>any())).thenReturn(buildResult);

    // Act
    List<Query> actualForAllTablesResult = QueryCreator.forAllTables(instance, queryFactory);

    // Assert
    verify(queryFactory, atLeast(1)).apply(Mockito.<QueryCreator>any());
    verify(instance, atLeast(1)).getStateStore(isA(TableProperties.class));
    verify(instance).streamTableProperties();
    assertEquals(2, actualForAllTablesResult.size());
    Query getResult = actualForAllTablesResult.get(0);
    assertEquals("42", getResult.getQueryId());
    assertEquals("Query Time Iterator Class Name", getResult.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", getResult.getQueryTimeIteratorConfig());
    assertEquals("Table Name", getResult.getTableName());
    assertTrue(getResult.getRegions().isEmpty());
    assertTrue(getResult.getRequestedValueFields().isEmpty());
    assertTrue(getResult.getStatusReportDestinations().isEmpty());
    assertTrue(getResult.getResultsPublisherConfig().isEmpty());
    assertSame(getResult, actualForAllTablesResult.get(1));
  }
}
