package sleeper.query.runner.tracker;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.query.core.model.LeafPartitionQuery;
import sleeper.query.core.model.Query;
import sleeper.query.core.output.ResultsOutputInfo;

class QueryStatusReportListenersDiffblueTest {
  /**
   * Test {@link QueryStatusReportListeners#fromConfig(List)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code webSocketManagementApiRegion} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#fromConfig(List)}
   */
  @Test
  @DisplayName("Test fromConfig(List); given HashMap() 'webSocketManagementApiRegion' is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryStatusReportListeners QueryStatusReportListeners.fromConfig(List)"})
  void testFromConfig_givenHashMapWebSocketManagementApiRegionIsNull() {
    // Arrange
    HashMap<String, String> stringStringMap = new HashMap<>();
    stringStringMap.put("webSocketManagementApiRegion", null);
    stringStringMap.put("webSocketManagementApiEndpoint", "Destinations Config");
    stringStringMap.put("webSocketConnectionId", "Destinations Config");
    stringStringMap.put("awsAccessKey", null);
    stringStringMap.put("awsSecretKey", null);
    stringStringMap.put("destination", "WEBSOCKET");
    stringStringMap.put("destination", "Destinations Config");

    ArrayList<Map<String, String>> destinationsConfig = new ArrayList<>();
    destinationsConfig.add(stringStringMap);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QueryStatusReportListeners.fromConfig(destinationsConfig));
  }

  /**
   * Test {@link QueryStatusReportListeners#fromConfig(List)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#fromConfig(List)}
   */
  @Test
  @DisplayName("Test fromConfig(List); given HashMap(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryStatusReportListeners QueryStatusReportListeners.fromConfig(List)"})
  void testFromConfig_givenHashMap_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Map<String, String>> destinationsConfig = new ArrayList<>();
    destinationsConfig.add(new HashMap<>());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QueryStatusReportListeners.fromConfig(destinationsConfig));
  }

  /**
   * Test {@link QueryStatusReportListeners#fromConfig(List)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#fromConfig(List)}
   */
  @Test
  @DisplayName("Test fromConfig(List); given HashMap(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryStatusReportListeners QueryStatusReportListeners.fromConfig(List)"})
  void testFromConfig_givenHashMap_thenThrowIllegalArgumentException2() {
    // Arrange
    ArrayList<Map<String, String>> destinationsConfig = new ArrayList<>();
    destinationsConfig.add(new HashMap<>());
    destinationsConfig.add(new HashMap<>());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> QueryStatusReportListeners.fromConfig(destinationsConfig));
  }

  /**
   * Test {@link QueryStatusReportListeners#queryQueued(Query)}.
   * <ul>
   *   <li>Then calls {@link DynamoDBQueryTracker#queryQueued(Query)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#queryQueued(Query)}
   */
  @Test
  @DisplayName("Test queryQueued(Query); then calls queryQueued(Query)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryStatusReportListeners.queryQueued(Query)"})
  void testQueryQueued_thenCallsQueryQueued() {
    // Arrange
    DynamoDBQueryTracker listener = mock(DynamoDBQueryTracker.class);
    doNothing().when(listener).queryQueued(Mockito.<Query>any());
    QueryStatusReportListeners fromConfigResult = QueryStatusReportListeners.fromConfig(new ArrayList<>());
    fromConfigResult.add(listener);

    // Act
    fromConfigResult.queryQueued(null);

    // Assert
    verify(listener).queryQueued(isNull());
  }

  /**
   * Test {@link QueryStatusReportListeners#queryInProgress(LeafPartitionQuery)} with {@code leafQuery}.
   * <ul>
   *   <li>Then calls {@link DynamoDBQueryTracker#queryInProgress(LeafPartitionQuery)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#queryInProgress(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test queryInProgress(LeafPartitionQuery) with 'leafQuery'; then calls queryInProgress(LeafPartitionQuery)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryStatusReportListeners.queryInProgress(LeafPartitionQuery)"})
  void testQueryInProgressWithLeafQuery_thenCallsQueryInProgress() {
    // Arrange
    DynamoDBQueryTracker listener = mock(DynamoDBQueryTracker.class);
    doNothing().when(listener).queryInProgress(Mockito.<LeafPartitionQuery>any());
    QueryStatusReportListeners fromConfigResult = QueryStatusReportListeners.fromConfig(new ArrayList<>());
    fromConfigResult.add(listener);

    // Act
    fromConfigResult.queryInProgress((LeafPartitionQuery) null);

    // Assert
    verify(listener).queryInProgress((LeafPartitionQuery) isNull());
  }

  /**
   * Test {@link QueryStatusReportListeners#queryInProgress(Query)} with {@code query}.
   * <ul>
   *   <li>Then calls {@link DynamoDBQueryTracker#queryInProgress(Query)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#queryInProgress(Query)}
   */
  @Test
  @DisplayName("Test queryInProgress(Query) with 'query'; then calls queryInProgress(Query)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryStatusReportListeners.queryInProgress(Query)"})
  void testQueryInProgressWithQuery_thenCallsQueryInProgress() {
    // Arrange
    DynamoDBQueryTracker listener = mock(DynamoDBQueryTracker.class);
    doNothing().when(listener).queryInProgress(Mockito.<Query>any());
    QueryStatusReportListeners fromConfigResult = QueryStatusReportListeners.fromConfig(new ArrayList<>());
    fromConfigResult.add(listener);

    // Act
    fromConfigResult.queryInProgress((Query) null);

    // Assert
    verify(listener).queryInProgress((Query) isNull());
  }

  /**
   * Test {@link QueryStatusReportListeners#subQueriesCreated(Query, List)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#subQueriesCreated(Query, List)}
   */
  @Test
  @DisplayName("Test subQueriesCreated(Query, List); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryStatusReportListeners.subQueriesCreated(Query, List)"})
  void testSubQueriesCreated_thenThrowIllegalArgumentException() {
    // Arrange
    QueryStatusReportListeners fromConfigResult = QueryStatusReportListeners.fromConfig(new ArrayList<>());
    fromConfigResult
        .add(new WebSocketQueryStatusReportDestination("us-east-2", "https://config.us-east-2.amazonaws.com", "42"));
    Query query = mock(Query.class);
    when(query.getQueryId()).thenThrow(new IllegalArgumentException("+"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> fromConfigResult.subQueriesCreated(query, new ArrayList<>()));
    verify(query).getQueryId();
  }

  /**
   * Test {@link QueryStatusReportListeners#queryCompleted(LeafPartitionQuery, ResultsOutputInfo)} with {@code leafQuery}, {@code outputInfo}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#queryCompleted(LeafPartitionQuery, ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test queryCompleted(LeafPartitionQuery, ResultsOutputInfo) with 'leafQuery', 'outputInfo'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryStatusReportListeners.queryCompleted(LeafPartitionQuery, ResultsOutputInfo)"})
  void testQueryCompletedWithLeafQueryOutputInfo_thenThrowIllegalArgumentException() {
    // Arrange
    QueryStatusReportListeners fromConfigResult = QueryStatusReportListeners.fromConfig(new ArrayList<>());
    fromConfigResult
        .add(new WebSocketQueryStatusReportDestination("us-east-2", "https://config.us-east-2.amazonaws.com", "42"));
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getSubQueryId()).thenThrow(new IllegalArgumentException("+"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> fromConfigResult.queryCompleted(leafQuery, new ResultsOutputInfo(3L, new ArrayList<>())));
    verify(leafQuery).getSubQueryId();
  }

  /**
   * Test {@link QueryStatusReportListeners#queryCompleted(Query, ResultsOutputInfo)} with {@code query}, {@code outputInfo}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#queryCompleted(Query, ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test queryCompleted(Query, ResultsOutputInfo) with 'query', 'outputInfo'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryStatusReportListeners.queryCompleted(Query, ResultsOutputInfo)"})
  void testQueryCompletedWithQueryOutputInfo_thenThrowIllegalArgumentException() {
    // Arrange
    QueryStatusReportListeners fromConfigResult = QueryStatusReportListeners.fromConfig(new ArrayList<>());
    fromConfigResult
        .add(new WebSocketQueryStatusReportDestination("us-east-2", "https://config.us-east-2.amazonaws.com", "42"));
    Query query = mock(Query.class);
    when(query.getQueryId()).thenThrow(new IllegalArgumentException("+"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> fromConfigResult.queryCompleted(query, new ResultsOutputInfo(3L, new ArrayList<>())));
    verify(query).getQueryId();
  }

  /**
   * Test {@link QueryStatusReportListeners#queryFailed(LeafPartitionQuery, Exception)} with {@code leafQuery}, {@code e}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#queryFailed(LeafPartitionQuery, Exception)}
   */
  @Test
  @DisplayName("Test queryFailed(LeafPartitionQuery, Exception) with 'leafQuery', 'e'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryStatusReportListeners.queryFailed(LeafPartitionQuery, Exception)"})
  void testQueryFailedWithLeafQueryE_thenThrowIllegalArgumentException() {
    // Arrange
    QueryStatusReportListeners fromConfigResult = QueryStatusReportListeners.fromConfig(new ArrayList<>());
    fromConfigResult
        .add(new WebSocketQueryStatusReportDestination("us-east-2", "https://config.us-east-2.amazonaws.com", "42"));
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getSubQueryId()).thenThrow(new IllegalArgumentException("+"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> fromConfigResult.queryFailed(leafQuery, new Exception("foo")));
    verify(leafQuery).getSubQueryId();
  }

  /**
   * Test {@link QueryStatusReportListeners#queryFailed(Query, Exception)} with {@code query}, {@code e}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#queryFailed(Query, Exception)}
   */
  @Test
  @DisplayName("Test queryFailed(Query, Exception) with 'query', 'e'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryStatusReportListeners.queryFailed(Query, Exception)"})
  void testQueryFailedWithQueryE_thenThrowIllegalArgumentException() {
    // Arrange
    QueryStatusReportListeners fromConfigResult = QueryStatusReportListeners.fromConfig(new ArrayList<>());
    fromConfigResult
        .add(new WebSocketQueryStatusReportDestination("us-east-2", "https://config.us-east-2.amazonaws.com", "42"));
    Query query = mock(Query.class);
    when(query.getQueryId()).thenThrow(new IllegalArgumentException("+"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> fromConfigResult.queryFailed(query, new Exception("foo")));
    verify(query).getQueryId();
  }

  /**
   * Test {@link QueryStatusReportListeners#queryFailed(String, Exception)} with {@code queryId}, {@code e}.
   * <ul>
   *   <li>Then calls {@link DynamoDBQueryTracker#queryFailed(String, Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#queryFailed(String, Exception)}
   */
  @Test
  @DisplayName("Test queryFailed(String, Exception) with 'queryId', 'e'; then calls queryFailed(String, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryStatusReportListeners.queryFailed(String, Exception)"})
  void testQueryFailedWithQueryIdE_thenCallsQueryFailed() {
    // Arrange
    DynamoDBQueryTracker listener = mock(DynamoDBQueryTracker.class);
    doNothing().when(listener).queryFailed(Mockito.<String>any(), Mockito.<Exception>any());
    QueryStatusReportListeners fromConfigResult = QueryStatusReportListeners.fromConfig(new ArrayList<>());
    fromConfigResult.add(listener);

    // Act
    fromConfigResult.queryFailed("42", new Exception("foo"));

    // Assert
    verify(listener).queryFailed(eq("42"), isA(Exception.class));
  }

  /**
   * Test {@link QueryStatusReportListeners#queryFailed(String, Exception)} with {@code queryId}, {@code e}.
   * <ul>
   *   <li>Then calls {@link DynamoDBQueryTracker#queryFailed(String, Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryStatusReportListeners#queryFailed(String, Exception)}
   */
  @Test
  @DisplayName("Test queryFailed(String, Exception) with 'queryId', 'e'; then calls queryFailed(String, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryStatusReportListeners.queryFailed(String, Exception)"})
  void testQueryFailedWithQueryIdE_thenCallsQueryFailed2() {
    // Arrange
    DynamoDBQueryTracker listener = mock(DynamoDBQueryTracker.class);
    doNothing().when(listener).queryFailed(Mockito.<String>any(), Mockito.<Exception>any());
    QueryStatusReportListeners fromConfigResult = QueryStatusReportListeners.fromConfig(new ArrayList<>());
    fromConfigResult.add(QueryStatusReportListeners.fromConfig(new ArrayList<>()));
    fromConfigResult.add(listener);

    // Act
    fromConfigResult.queryFailed("42", new Exception("foo"));

    // Assert
    verify(listener).queryFailed(eq("42"), isA(Exception.class));
  }
}
