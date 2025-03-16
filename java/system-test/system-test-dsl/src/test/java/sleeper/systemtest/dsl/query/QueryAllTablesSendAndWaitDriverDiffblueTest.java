package sleeper.systemtest.dsl.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
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
import sleeper.core.record.Record;
import sleeper.core.statestore.StateStore;
import sleeper.query.core.model.Query;
import sleeper.query.core.model.QueryProcessingConfig;
import sleeper.query.core.model.QueryProcessingConfig.Builder;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.util.SystemTestSchema;
import sleeper.systemtest.dsl.util.TestContext;

class QueryAllTablesSendAndWaitDriverDiffblueTest {
  /**
   * Test {@link QueryAllTablesSendAndWaitDriver#runForAllTables(Function)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link TableProperties#get(TableProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryAllTablesSendAndWaitDriver#runForAllTables(Function)}
   */
  @Test
  @DisplayName("Test runForAllTables(Function); given TableProperties get(TableProperty) return 'Get'; then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map QueryAllTablesSendAndWaitDriver.runForAllTables(Function)"})
  void testRunForAllTables_givenTablePropertiesGetReturnGet_thenCallsGet() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(SystemTestSchema.DEFAULT_SCHEMA);

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(tableProperties);
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTestTableName(Mockito.<String>any())).thenReturn("Test Table Name");
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(mock(StateStore.class));
    when(instance.streamTableProperties()).thenReturn(streamResult);
    QuerySendAndWaitDriver driver = mock(QuerySendAndWaitDriver.class);
    when(driver.getResults(Mockito.<Query>any())).thenReturn(new ArrayList<>());
    doNothing().when(driver).send(Mockito.<Query>any());
    doNothing().when(driver).waitFor(Mockito.<Query>any());
    QueryAllTablesSendAndWaitDriver queryAllTablesSendAndWaitDriver = new QueryAllTablesSendAndWaitDriver(instance,
        driver);
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
    Map<String, List<Record>> actualRunForAllTablesResult = queryAllTablesSendAndWaitDriver
        .runForAllTables(queryFactory);

    // Assert
    verify(queryFactory).apply(isA(QueryCreator.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getSchema();
    verify(instance).getStateStore(isA(TableProperties.class));
    verify(instance).getTestTableName(eq("Table Name"));
    verify(instance).streamTableProperties();
    verify(driver).getResults(isA(Query.class));
    verify(driver).send(isA(Query.class));
    verify(driver).waitFor(isA(Query.class));
    assertEquals(1, actualRunForAllTablesResult.size());
    assertTrue(actualRunForAllTablesResult.get("Test Table Name").isEmpty());
  }

  /**
   * Test {@link QueryAllTablesSendAndWaitDriver#runForAllTables(Function)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryAllTablesSendAndWaitDriver#runForAllTables(Function)}
   */
  @Test
  @DisplayName("Test runForAllTables(Function); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map QueryAllTablesSendAndWaitDriver.runForAllTables(Function)"})
  void testRunForAllTables_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(new TableProperties(new InstanceProperties()));
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTestTableName(Mockito.<String>any())).thenReturn("Test Table Name");
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(mock(StateStore.class));
    when(instance.streamTableProperties()).thenReturn(streamResult);
    QuerySendAndWaitDriver driver = mock(QuerySendAndWaitDriver.class);
    when(driver.getResults(Mockito.<Query>any())).thenReturn(new ArrayList<>());
    doNothing().when(driver).send(Mockito.<Query>any());
    doNothing().when(driver).waitFor(Mockito.<Query>any());
    QueryAllTablesSendAndWaitDriver queryAllTablesSendAndWaitDriver = new QueryAllTablesSendAndWaitDriver(instance,
        driver);
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
    Map<String, List<Record>> actualRunForAllTablesResult = queryAllTablesSendAndWaitDriver
        .runForAllTables(queryFactory);

    // Assert
    verify(queryFactory).apply(isA(QueryCreator.class));
    verify(instance).getStateStore(isA(TableProperties.class));
    verify(instance).getTestTableName(eq("Table Name"));
    verify(instance).streamTableProperties();
    verify(driver).getResults(isA(Query.class));
    verify(driver).send(isA(Query.class));
    verify(driver).waitFor(isA(Query.class));
    assertEquals(1, actualRunForAllTablesResult.size());
    assertTrue(actualRunForAllTablesResult.get("Test Table Name").isEmpty());
  }

  /**
   * Test {@link QueryAllTablesSendAndWaitDriver#runForAllTables(Function)}.
   * <ul>
   *   <li>When {@link Function}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryAllTablesSendAndWaitDriver#runForAllTables(Function)}
   */
  @Test
  @DisplayName("Test runForAllTables(Function); when Function; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map QueryAllTablesSendAndWaitDriver.runForAllTables(Function)"})
  void testRunForAllTables_whenFunction_thenReturnEmpty() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    when(instance.streamTableProperties()).thenReturn(streamResult);

    // Act
    Map<String, List<Record>> actualRunForAllTablesResult = (new QueryAllTablesSendAndWaitDriver(instance,
        mock(QuerySendAndWaitDriver.class))).runForAllTables(mock(Function.class));

    // Assert
    verify(instance).streamTableProperties();
    assertTrue(actualRunForAllTablesResult.isEmpty());
  }

  /**
   * Test {@link QueryAllTablesSendAndWaitDriver#run(Query)}.
   * <p>
   * Method under test: {@link QueryAllTablesSendAndWaitDriver#run(Query)}
   */
  @Test
  @DisplayName("Test run(Query)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List QueryAllTablesSendAndWaitDriver.run(Query)"})
  void testRun() {
    // Arrange
    QuerySendAndWaitDriver driver = mock(QuerySendAndWaitDriver.class);
    when(driver.run(Mockito.<Query>any())).thenReturn(new ArrayList<>());

    // Act
    List<Record> actualRunResult = (new QueryAllTablesSendAndWaitDriver(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)),
        driver)).run(mock(Query.class));

    // Assert
    verify(driver).run(isA(Query.class));
    assertTrue(actualRunResult.isEmpty());
  }
}
