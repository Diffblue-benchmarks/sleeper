package sleeper.query.runner.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.STSSessionCredentials;
import com.amazonaws.services.apigatewaymanagementapi.model.GoneException;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.core.range.Region;
import sleeper.query.core.model.LeafPartitionQuery;
import sleeper.query.core.model.Query;
import sleeper.query.core.model.QueryOrLeafPartitionQuery;
import sleeper.query.core.model.QueryProcessingConfig;
import sleeper.query.core.model.QueryProcessingConfig.Builder;

class WebSocketOutputDiffblueTest {
  /**
   * Test {@link WebSocketOutput#WebSocketOutput(Map)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#ACCESS_KEY} is {@code Config}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(Map); given empty string; when HashMap() ACCESS_KEY is 'Config'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(Map)"})
  void testNewWebSocketOutput_givenEmptyString_whenHashMapAccess_keyIsConfig() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, "Config");
    config.put(WebSocketOutput.SECRET_KEY, "");

    // Act
    WebSocketOutput actualWebSocketOutput = new WebSocketOutput(config);

    // Assert
    assertEquals("Config", actualWebSocketOutput.getConnectionId());
    assertEquals("Config", actualWebSocketOutput.getEndpoint());
    assertFalse(actualWebSocketOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketOutput#WebSocketOutput(Map)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#ACCESS_KEY} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(Map); given empty string; when HashMap() ACCESS_KEY is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(Map)"})
  void testNewWebSocketOutput_givenEmptyString_whenHashMapAccess_keyIsEmptyString() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, "");
    config.put(WebSocketOutput.SECRET_KEY, "");

    // Act
    WebSocketOutput actualWebSocketOutput = new WebSocketOutput(config);

    // Assert
    assertEquals("Config", actualWebSocketOutput.getConnectionId());
    assertEquals("Config", actualWebSocketOutput.getEndpoint());
    assertFalse(actualWebSocketOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketOutput#WebSocketOutput(Map)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#ACCESS_KEY} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(Map); given 'null'; when HashMap() ACCESS_KEY is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(Map)"})
  void testNewWebSocketOutput_givenNull_whenHashMapAccess_keyIsEmptyString() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, "");
    config.put(WebSocketOutput.SECRET_KEY, null);

    // Act
    WebSocketOutput actualWebSocketOutput = new WebSocketOutput(config);

    // Assert
    assertEquals("Config", actualWebSocketOutput.getConnectionId());
    assertEquals("Config", actualWebSocketOutput.getEndpoint());
    assertFalse(actualWebSocketOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketOutput#WebSocketOutput(Map)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#ACCESS_KEY} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(Map); given 'null'; when HashMap() ACCESS_KEY is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(Map)"})
  void testNewWebSocketOutput_givenNull_whenHashMapAccess_keyIsNull() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, null);
    config.put(WebSocketOutput.SECRET_KEY, null);

    // Act
    WebSocketOutput actualWebSocketOutput = new WebSocketOutput(config);

    // Assert
    assertEquals("Config", actualWebSocketOutput.getConnectionId());
    assertEquals("Config", actualWebSocketOutput.getEndpoint());
    assertFalse(actualWebSocketOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketOutput#WebSocketOutput(String, String, String)}.
   * <ul>
   *   <li>Then return ConnectionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(String, String, String)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(String, String, String); then return ConnectionId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(String, String, String)"})
  void testNewWebSocketOutput_thenReturnConnectionIdIs42() {
    // Arrange and Act
    WebSocketOutput actualWebSocketOutput = new WebSocketOutput("us-east-2", "https://config.us-east-2.amazonaws.com",
        "42");

    // Assert
    assertEquals("42", actualWebSocketOutput.getConnectionId());
    assertEquals("https://config.us-east-2.amazonaws.com", actualWebSocketOutput.getEndpoint());
    assertFalse(actualWebSocketOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketOutput#WebSocketOutput(String, String, String, AWSCredentials)}.
   * <ul>
   *   <li>Then return ConnectionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(String, String, String, AWSCredentials)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(String, String, String, AWSCredentials); then return ConnectionId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(String, String, String, AWSCredentials)"})
  void testNewWebSocketOutput_thenReturnConnectionIdIs422() {
    // Arrange and Act
    WebSocketOutput actualWebSocketOutput = new WebSocketOutput("us-east-2", "https://config.us-east-2.amazonaws.com",
        "42", new AnonymousAWSCredentials());

    // Assert
    assertEquals("42", actualWebSocketOutput.getConnectionId());
    assertEquals("https://config.us-east-2.amazonaws.com", actualWebSocketOutput.getEndpoint());
    assertFalse(actualWebSocketOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketOutput#WebSocketOutput(String, String, String, AWSCredentials)}.
   * <ul>
   *   <li>Then return ConnectionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(String, String, String, AWSCredentials)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(String, String, String, AWSCredentials); then return ConnectionId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(String, String, String, AWSCredentials)"})
  void testNewWebSocketOutput_thenReturnConnectionIdIs423() {
    // Arrange and Act
    WebSocketOutput actualWebSocketOutput = new WebSocketOutput("us-east-2", "https://config.us-east-2.amazonaws.com",
        "42", null);

    // Assert
    assertEquals("42", actualWebSocketOutput.getConnectionId());
    assertEquals("https://config.us-east-2.amazonaws.com", actualWebSocketOutput.getEndpoint());
    assertFalse(actualWebSocketOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketOutput#WebSocketOutput(Map)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(Map); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(Map)"})
  void testNewWebSocketOutput_thenThrowIllegalArgumentException() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketOutput.REGION, WebSocketOutput.REGION);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new WebSocketOutput(config));
  }

  /**
   * Test {@link WebSocketOutput#WebSocketOutput(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#ENDPOINT} is {@link WebSocketOutput#ENDPOINT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(Map); when HashMap() ENDPOINT is ENDPOINT")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(Map)"})
  void testNewWebSocketOutput_whenHashMapEndpointIsEndpoint() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketOutput.ENDPOINT, WebSocketOutput.ENDPOINT);
    config.put(WebSocketOutput.REGION, WebSocketOutput.REGION);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new WebSocketOutput(config));
  }

  /**
   * Test {@link WebSocketOutput#WebSocketOutput(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#SECRET_KEY} is {@code Config}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(Map); when HashMap() SECRET_KEY is 'Config'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(Map)"})
  void testNewWebSocketOutput_whenHashMapSecret_keyIsConfig() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, "Config");
    config.put(WebSocketOutput.SECRET_KEY, "Config");

    // Act
    WebSocketOutput actualWebSocketOutput = new WebSocketOutput(config);

    // Assert
    assertEquals("Config", actualWebSocketOutput.getConnectionId());
    assertEquals("Config", actualWebSocketOutput.getEndpoint());
    assertFalse(actualWebSocketOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketOutput#WebSocketOutput(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#WebSocketOutput(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketOutput(Map); when HashMap(); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.<init>(Map)"})
  void testNewWebSocketOutput_whenHashMap_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> new WebSocketOutput(new HashMap<>()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link WebSocketOutput#getConnectionId()}
   *   <li>{@link WebSocketOutput#getEndpoint()}
   *   <li>{@link WebSocketOutput#isClientGone()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WebSocketOutput.getConnectionId()", "String WebSocketOutput.getEndpoint()",
      "boolean WebSocketOutput.isClientGone()"})
  void testGettersAndSetters() {
    // Arrange
    WebSocketOutput webSocketOutput = new WebSocketOutput("us-east-2", "https://config.us-east-2.amazonaws.com", "42");

    // Act
    String actualConnectionId = webSocketOutput.getConnectionId();
    String actualEndpoint = webSocketOutput.getEndpoint();

    // Assert
    assertEquals("42", actualConnectionId);
    assertEquals("https://config.us-east-2.amazonaws.com", actualEndpoint);
    assertFalse(webSocketOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketOutput#sendString(String)}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#sendString(String)}
   */
  @Test
  @DisplayName("Test sendString(String); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketOutput.sendString(String)"})
  void testSendString_thenThrowIOException() throws IOException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AWSSecurityTokenServiceAsyncClient stsClient = mock(AWSSecurityTokenServiceAsyncClient.class);
      when(stsClient.getSessionToken(Mockito.<GetSessionTokenRequest>any()))
          .thenThrow(new GoneException("An error occurred"));

      // Act and Assert
      assertThrows(IOException.class, () -> (new WebSocketOutput("us-east-2", "https://config.us-east-2.amazonaws.com",
          "42", new STSSessionCredentials(stsClient))).sendString("Not all who wander are lost"));
      verify(stsClient).getSessionToken(isA(GetSessionTokenRequest.class));
    }
  }

  /**
   * Test {@link WebSocketOutput#getQueryId(LeafPartitionQuery)} with {@code LeafPartitionQuery}.
   * <p>
   * Method under test: {@link WebSocketOutput#getQueryId(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test getQueryId(LeafPartitionQuery) with 'LeafPartitionQuery'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WebSocketOutput.getQueryId(LeafPartitionQuery)"})
  void testGetQueryIdWithLeafPartitionQuery() {
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
    LeafPartitionQuery query = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertEquals("42", WebSocketOutput.getQueryId(query));
  }

  /**
   * Test {@link WebSocketOutput#getQueryId(LeafPartitionQuery)} with {@code LeafPartitionQuery}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#getQueryId(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test getQueryId(LeafPartitionQuery) with 'LeafPartitionQuery'; given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WebSocketOutput.getQueryId(LeafPartitionQuery)"})
  void testGetQueryIdWithLeafPartitionQuery_given42() {
    // Arrange
    LeafPartitionQuery query = mock(LeafPartitionQuery.class);
    when(query.getSubQueryId()).thenReturn("42");

    // Act
    String actualQueryId = WebSocketOutput.getQueryId(query);

    // Assert
    verify(query).getSubQueryId();
    assertEquals("42", actualQueryId);
  }

  /**
   * Test {@link WebSocketOutput#getQueryId(LeafPartitionQuery)} with {@code LeafPartitionQuery}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#getQueryId(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test getQueryId(LeafPartitionQuery) with 'LeafPartitionQuery'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WebSocketOutput.getQueryId(LeafPartitionQuery)"})
  void testGetQueryIdWithLeafPartitionQuery_thenThrowRuntimeException() {
    // Arrange
    LeafPartitionQuery query = mock(LeafPartitionQuery.class);
    when(query.getSubQueryId()).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> WebSocketOutput.getQueryId(query));
    verify(query).getSubQueryId();
  }

  /**
   * Test {@link WebSocketOutput#getQueryId(QueryOrLeafPartitionQuery)} with {@code QueryOrLeafPartitionQuery}.
   * <p>
   * Method under test: {@link WebSocketOutput#getQueryId(QueryOrLeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test getQueryId(QueryOrLeafPartitionQuery) with 'QueryOrLeafPartitionQuery'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WebSocketOutput.getQueryId(QueryOrLeafPartitionQuery)"})
  void testGetQueryIdWithQueryOrLeafPartitionQuery() {
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
    assertEquals("42", WebSocketOutput.getQueryId(new QueryOrLeafPartitionQuery(leafQuery)));
  }

  /**
   * Test {@link WebSocketOutput#getQueryId(QueryOrLeafPartitionQuery)} with {@code QueryOrLeafPartitionQuery}.
   * <p>
   * Method under test: {@link WebSocketOutput#getQueryId(QueryOrLeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test getQueryId(QueryOrLeafPartitionQuery) with 'QueryOrLeafPartitionQuery'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WebSocketOutput.getQueryId(QueryOrLeafPartitionQuery)"})
  void testGetQueryIdWithQueryOrLeafPartitionQuery2() {
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
    assertEquals("42", WebSocketOutput.getQueryId(new QueryOrLeafPartitionQuery(query)));
  }

  /**
   * Test {@link WebSocketOutput#getQueryId(Query)} with {@code Query}.
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#getQueryId(Query)}
   */
  @Test
  @DisplayName("Test getQueryId(Query) with 'Query'; given RuntimeException(String) with 'foo'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WebSocketOutput.getQueryId(Query)"})
  void testGetQueryIdWithQuery_givenRuntimeExceptionWithFoo_thenThrowRuntimeException() {
    // Arrange
    Query query = mock(Query.class);
    when(query.getQueryId()).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> WebSocketOutput.getQueryId(query));
    verify(query).getQueryId();
  }

  /**
   * Test {@link WebSocketOutput#getQueryId(Query)} with {@code Query}.
   * <ul>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketOutput#getQueryId(Query)}
   */
  @Test
  @DisplayName("Test getQueryId(Query) with 'Query'; then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String WebSocketOutput.getQueryId(Query)"})
  void testGetQueryIdWithQuery_thenReturn42() {
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
    assertEquals("42", WebSocketOutput.getQueryId(query));
  }
}
