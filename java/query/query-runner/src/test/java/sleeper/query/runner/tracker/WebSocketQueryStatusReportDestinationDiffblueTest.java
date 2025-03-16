package sleeper.query.runner.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import sleeper.query.core.model.QueryProcessingConfig;
import sleeper.query.core.model.QueryProcessingConfig.Builder;
import sleeper.query.core.output.ResultsOutputInfo;

class WebSocketQueryStatusReportDestinationDiffblueTest {
  /**
   * Test {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(Map)}.
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketQueryStatusReportDestination(Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketQueryStatusReportDestination.<init>(Map)"})
  void testNewWebSocketQueryStatusReportDestination() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put("webSocketManagementApiRegion", "Config");
    config.put("webSocketManagementApiEndpoint", "Config");
    config.put("webSocketConnectionId", "Config");
    config.put("awsAccessKey", "");
    config.put("awsSecretKey", null);

    // Act
    WebSocketQueryStatusReportDestination actualWebSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
        config);

    // Assert
    assertEquals("Config", actualWebSocketQueryStatusReportDestination.getConnectionId());
    assertEquals("Config", actualWebSocketQueryStatusReportDestination.getEndpoint());
    assertFalse(actualWebSocketQueryStatusReportDestination.isClientGone());
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(Map)}.
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketQueryStatusReportDestination(Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketQueryStatusReportDestination.<init>(Map)"})
  void testNewWebSocketQueryStatusReportDestination2() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put("webSocketManagementApiRegion", "Config");
    config.put("webSocketManagementApiEndpoint", "Config");
    config.put("webSocketConnectionId", "Config");
    config.put("awsAccessKey", "");
    config.put("awsSecretKey", "");

    // Act
    WebSocketQueryStatusReportDestination actualWebSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
        config);

    // Assert
    assertEquals("Config", actualWebSocketQueryStatusReportDestination.getConnectionId());
    assertEquals("Config", actualWebSocketQueryStatusReportDestination.getEndpoint());
    assertFalse(actualWebSocketQueryStatusReportDestination.isClientGone());
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(String, String, String)}.
   * <ul>
   *   <li>Then return ConnectionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(String, String, String)}
   */
  @Test
  @DisplayName("Test new WebSocketQueryStatusReportDestination(String, String, String); then return ConnectionId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketQueryStatusReportDestination.<init>(String, String, String)"})
  void testNewWebSocketQueryStatusReportDestination_thenReturnConnectionIdIs42() {
    // Arrange and Act
    WebSocketQueryStatusReportDestination actualWebSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
        "us-east-2", "https://config.us-east-2.amazonaws.com", "42");

    // Assert
    assertEquals("42", actualWebSocketQueryStatusReportDestination.getConnectionId());
    assertEquals("https://config.us-east-2.amazonaws.com", actualWebSocketQueryStatusReportDestination.getEndpoint());
    assertFalse(actualWebSocketQueryStatusReportDestination.isClientGone());
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(String, String, String, AWSCredentials)}.
   * <ul>
   *   <li>Then return ConnectionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(String, String, String, AWSCredentials)}
   */
  @Test
  @DisplayName("Test new WebSocketQueryStatusReportDestination(String, String, String, AWSCredentials); then return ConnectionId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketQueryStatusReportDestination.<init>(String, String, String, AWSCredentials)"})
  void testNewWebSocketQueryStatusReportDestination_thenReturnConnectionIdIs422() {
    // Arrange and Act
    WebSocketQueryStatusReportDestination actualWebSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
        "us-east-2", "https://config.us-east-2.amazonaws.com", "42", new AnonymousAWSCredentials());

    // Assert
    assertEquals("42", actualWebSocketQueryStatusReportDestination.getConnectionId());
    assertEquals("https://config.us-east-2.amazonaws.com", actualWebSocketQueryStatusReportDestination.getEndpoint());
    assertFalse(actualWebSocketQueryStatusReportDestination.isClientGone());
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code awsAccessKey} is {@code Config}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketQueryStatusReportDestination(Map); when HashMap() 'awsAccessKey' is 'Config'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketQueryStatusReportDestination.<init>(Map)"})
  void testNewWebSocketQueryStatusReportDestination_whenHashMapAwsAccessKeyIsConfig() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put("webSocketManagementApiRegion", "Config");
    config.put("webSocketManagementApiEndpoint", "Config");
    config.put("webSocketConnectionId", "Config");
    config.put("awsAccessKey", "Config");
    config.put("awsSecretKey", "");

    // Act
    WebSocketQueryStatusReportDestination actualWebSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
        config);

    // Assert
    assertEquals("Config", actualWebSocketQueryStatusReportDestination.getConnectionId());
    assertEquals("Config", actualWebSocketQueryStatusReportDestination.getEndpoint());
    assertFalse(actualWebSocketQueryStatusReportDestination.isClientGone());
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code awsAccessKey} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketQueryStatusReportDestination(Map); when HashMap() 'awsAccessKey' is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketQueryStatusReportDestination.<init>(Map)"})
  void testNewWebSocketQueryStatusReportDestination_whenHashMapAwsAccessKeyIsNull() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put("webSocketManagementApiRegion", "Config");
    config.put("webSocketManagementApiEndpoint", "Config");
    config.put("webSocketConnectionId", "Config");
    config.put("awsAccessKey", null);
    config.put("awsSecretKey", null);

    // Act
    WebSocketQueryStatusReportDestination actualWebSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
        config);

    // Assert
    assertEquals("Config", actualWebSocketQueryStatusReportDestination.getConnectionId());
    assertEquals("Config", actualWebSocketQueryStatusReportDestination.getEndpoint());
    assertFalse(actualWebSocketQueryStatusReportDestination.isClientGone());
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code awsSecretKey} is {@code Config}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(Map)}
   */
  @Test
  @DisplayName("Test new WebSocketQueryStatusReportDestination(Map); when HashMap() 'awsSecretKey' is 'Config'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketQueryStatusReportDestination.<init>(Map)"})
  void testNewWebSocketQueryStatusReportDestination_whenHashMapAwsSecretKeyIsConfig() {
    // Arrange
    HashMap<String, String> config = new HashMap<>();
    config.put("webSocketManagementApiRegion", "Config");
    config.put("webSocketManagementApiEndpoint", "Config");
    config.put("webSocketConnectionId", "Config");
    config.put("awsAccessKey", "Config");
    config.put("awsSecretKey", "Config");

    // Act
    WebSocketQueryStatusReportDestination actualWebSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
        config);

    // Assert
    assertEquals("Config", actualWebSocketQueryStatusReportDestination.getConnectionId());
    assertEquals("Config", actualWebSocketQueryStatusReportDestination.getEndpoint());
    assertFalse(actualWebSocketQueryStatusReportDestination.isClientGone());
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(String, String, String, AWSCredentials)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return ConnectionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#WebSocketQueryStatusReportDestination(String, String, String, AWSCredentials)}
   */
  @Test
  @DisplayName("Test new WebSocketQueryStatusReportDestination(String, String, String, AWSCredentials); when 'null'; then return ConnectionId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketQueryStatusReportDestination.<init>(String, String, String, AWSCredentials)"})
  void testNewWebSocketQueryStatusReportDestination_whenNull_thenReturnConnectionIdIs42() {
    // Arrange and Act
    WebSocketQueryStatusReportDestination actualWebSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
        "us-east-2", "https://config.us-east-2.amazonaws.com", "42", null);

    // Assert
    assertEquals("42", actualWebSocketQueryStatusReportDestination.getConnectionId());
    assertEquals("https://config.us-east-2.amazonaws.com", actualWebSocketQueryStatusReportDestination.getEndpoint());
    assertFalse(actualWebSocketQueryStatusReportDestination.isClientGone());
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#queryCompleted(LeafPartitionQuery, ResultsOutputInfo)} with {@code leafQuery}, {@code outputInfo}.
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#queryCompleted(LeafPartitionQuery, ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test queryCompleted(LeafPartitionQuery, ResultsOutputInfo) with 'leafQuery', 'outputInfo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void WebSocketQueryStatusReportDestination.queryCompleted(LeafPartitionQuery, ResultsOutputInfo)"})
  void testQueryCompletedWithLeafQueryOutputInfo() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      BasicAWSCredentials awsCredentials = mock(BasicAWSCredentials.class);
      when(awsCredentials.getAWSAccessKeyId()).thenReturn(null);
      when(awsCredentials.getAWSSecretKey()).thenReturn("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
      WebSocketQueryStatusReportDestination webSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
          "us-east-2", "https://config.us-east-2.amazonaws.com", "42", awsCredentials);
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
      LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("").tableId("42").build();

      // Act and Assert
      assertThrows(IllegalArgumentException.class, () -> webSocketQueryStatusReportDestination.queryCompleted(leafQuery,
          new ResultsOutputInfo(3L, new ArrayList<>())));
      verify(awsCredentials).getAWSAccessKeyId();
      verify(awsCredentials).getAWSSecretKey();
    }
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#queryCompleted(LeafPartitionQuery, ResultsOutputInfo)} with {@code leafQuery}, {@code outputInfo}.
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#queryCompleted(LeafPartitionQuery, ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test queryCompleted(LeafPartitionQuery, ResultsOutputInfo) with 'leafQuery', 'outputInfo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void WebSocketQueryStatusReportDestination.queryCompleted(LeafPartitionQuery, ResultsOutputInfo)"})
  void testQueryCompletedWithLeafQueryOutputInfo2() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      BasicAWSCredentials awsCredentials = mock(BasicAWSCredentials.class);
      when(awsCredentials.getAWSAccessKeyId()).thenReturn("EXAMPLEakiAIOSFODNN7");
      when(awsCredentials.getAWSSecretKey()).thenReturn(null);
      WebSocketQueryStatusReportDestination webSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
          "us-east-2", "https://config.us-east-2.amazonaws.com", "42", awsCredentials);
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
      LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("").tableId("42").build();

      // Act and Assert
      assertThrows(IllegalArgumentException.class, () -> webSocketQueryStatusReportDestination.queryCompleted(leafQuery,
          new ResultsOutputInfo(3L, new ArrayList<>())));
      verify(awsCredentials).getAWSAccessKeyId();
      verify(awsCredentials).getAWSSecretKey();
    }
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#queryCompleted(Query, ResultsOutputInfo)} with {@code query}, {@code outputInfo}.
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#queryCompleted(Query, ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test queryCompleted(Query, ResultsOutputInfo) with 'query', 'outputInfo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketQueryStatusReportDestination.queryCompleted(Query, ResultsOutputInfo)"})
  void testQueryCompletedWithQueryOutputInfo() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      BasicAWSCredentials awsCredentials = mock(BasicAWSCredentials.class);
      when(awsCredentials.getAWSAccessKeyId()).thenReturn(null);
      when(awsCredentials.getAWSSecretKey()).thenReturn("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
      WebSocketQueryStatusReportDestination webSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
          "us-east-2", "https://config.us-east-2.amazonaws.com", "42", awsCredentials);
      Query.Builder builderResult = Query.builder();
      Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
          .queryTimeIteratorClassName("Query Time Iterator Class Name")
          .queryTimeIteratorConfig("Query Time Iterator Config");
      Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
      Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
      QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
          .build();
      Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("");
      Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

      // Act and Assert
      assertThrows(IllegalArgumentException.class, () -> webSocketQueryStatusReportDestination.queryCompleted(query,
          new ResultsOutputInfo(3L, new ArrayList<>())));
      verify(awsCredentials).getAWSAccessKeyId();
      verify(awsCredentials).getAWSSecretKey();
    }
  }

  /**
   * Test {@link WebSocketQueryStatusReportDestination#queryCompleted(Query, ResultsOutputInfo)} with {@code query}, {@code outputInfo}.
   * <p>
   * Method under test: {@link WebSocketQueryStatusReportDestination#queryCompleted(Query, ResultsOutputInfo)}
   */
  @Test
  @DisplayName("Test queryCompleted(Query, ResultsOutputInfo) with 'query', 'outputInfo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketQueryStatusReportDestination.queryCompleted(Query, ResultsOutputInfo)"})
  void testQueryCompletedWithQueryOutputInfo2() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      BasicAWSCredentials awsCredentials = mock(BasicAWSCredentials.class);
      when(awsCredentials.getAWSAccessKeyId()).thenReturn("EXAMPLEakiAIOSFODNN7");
      when(awsCredentials.getAWSSecretKey()).thenReturn(null);
      WebSocketQueryStatusReportDestination webSocketQueryStatusReportDestination = new WebSocketQueryStatusReportDestination(
          "us-east-2", "https://config.us-east-2.amazonaws.com", "42", awsCredentials);
      Query.Builder builderResult = Query.builder();
      Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
          .queryTimeIteratorClassName("Query Time Iterator Class Name")
          .queryTimeIteratorConfig("Query Time Iterator Config");
      Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
      Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
      QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
          .build();
      Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("");
      Query query = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

      // Act and Assert
      assertThrows(IllegalArgumentException.class, () -> webSocketQueryStatusReportDestination.queryCompleted(query,
          new ResultsOutputInfo(3L, new ArrayList<>())));
      verify(awsCredentials).getAWSAccessKeyId();
      verify(awsCredentials).getAWSSecretKey();
    }
  }
}
