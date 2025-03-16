package sleeper.query.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.range.Region;
import sleeper.query.core.model.QueryProcessingConfig.Builder;

class QueryDiffblueTest {
  /**
   * Test Builder {@link Query.Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Query.Builder#build()}
   *   <li>{@link Query.Builder#processingConfig(QueryProcessingConfig)}
   *   <li>{@link Query.Builder#queryId(String)}
   *   <li>{@link Query.Builder#regions(List)}
   *   <li>{@link Query.Builder#tableName(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Query Query.Builder.build()",
      "Query.Builder Query.Builder.processingConfig(QueryProcessingConfig)",
      "Query.Builder Query.Builder.queryId(String)", "Query.Builder Query.Builder.regions(List)",
      "Query.Builder Query.Builder.tableName(String)"})
  void testBuilderBuild() {
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
    ArrayList<Region> regions = new ArrayList<>();

    // Act
    Query actualBuildResult = queryIdResult.regions(regions).tableName("Table Name").build();

    // Assert
    assertEquals("42", actualBuildResult.getQueryId());
    assertEquals("Query Time Iterator Class Name", actualBuildResult.getQueryTimeIteratorClassName());
    QueryProcessingConfig processingConfig2 = actualBuildResult.getProcessingConfig();
    assertEquals("Query Time Iterator Class Name", processingConfig2.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", actualBuildResult.getQueryTimeIteratorConfig());
    assertEquals("Query Time Iterator Config", processingConfig2.getQueryTimeIteratorConfig());
    assertEquals("Table Name", actualBuildResult.getTableName());
    List<Region> regions2 = actualBuildResult.getRegions();
    assertTrue(regions2.isEmpty());
    List<String> requestedValueFields2 = actualBuildResult.getRequestedValueFields();
    assertTrue(requestedValueFields2.isEmpty());
    List<Map<String, String>> statusReportDestinations2 = actualBuildResult.getStatusReportDestinations();
    assertTrue(statusReportDestinations2.isEmpty());
    Map<String, String> resultsPublisherConfig2 = actualBuildResult.getResultsPublisherConfig();
    assertTrue(resultsPublisherConfig2.isEmpty());
    assertSame(regions, regions2);
    assertSame(requestedValueFields, requestedValueFields2);
    assertSame(statusReportDestinations, statusReportDestinations2);
    assertSame(requestedValueFields, processingConfig2.getRequestedValueFields());
    assertSame(statusReportDestinations, processingConfig2.getStatusReportDestinations());
    assertSame(resultsPublisherConfig, resultsPublisherConfig2);
    assertSame(resultsPublisherConfig, processingConfig2.getResultsPublisherConfig());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Query#toString()}
   *   <li>{@link Query#getProcessingConfig()}
   *   <li>{@link Query#getQueryId()}
   *   <li>{@link Query#getRegions()}
   *   <li>{@link Query#getTableName()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryProcessingConfig Query.getProcessingConfig()", "String Query.getQueryId()",
      "List Query.getRegions()", "String Query.getTableName()", "String Query.toString()"})
  void testGettersAndSetters() {
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
    ArrayList<Region> regions = new ArrayList<>();
    Query buildResult = queryIdResult.regions(regions).tableName("Table Name").build();

    // Act
    String actualToStringResult = buildResult.toString();
    QueryProcessingConfig actualProcessingConfig = buildResult.getProcessingConfig();
    String actualQueryId = buildResult.getQueryId();
    List<Region> actualRegions = buildResult.getRegions();
    String actualTableName = buildResult.getTableName();

    // Assert
    assertEquals("42", actualQueryId);
    assertEquals("Query Time Iterator Class Name", actualProcessingConfig.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", actualProcessingConfig.getQueryTimeIteratorConfig());
    assertEquals(
        "Query{tableName='Table Name', queryId='42', regions=[], processingConfig=QueryProcessingConfig"
            + "{queryTimeIteratorClassName='Query Time Iterator Class Name', queryTimeIteratorConfig='Query Time"
            + " Iterator Config', resultsPublisherConfig={}, statusReportDestinations=[], requestedValueFields=[]}}",
        actualToStringResult);
    assertEquals("Table Name", actualTableName);
    assertTrue(actualRegions.isEmpty());
    List<String> requestedValueFields2 = actualProcessingConfig.getRequestedValueFields();
    assertTrue(requestedValueFields2.isEmpty());
    List<Map<String, String>> statusReportDestinations2 = actualProcessingConfig.getStatusReportDestinations();
    assertTrue(statusReportDestinations2.isEmpty());
    Map<String, String> resultsPublisherConfig2 = actualProcessingConfig.getResultsPublisherConfig();
    assertTrue(resultsPublisherConfig2.isEmpty());
    assertSame(regions, actualRegions);
    assertSame(requestedValueFields, requestedValueFields2);
    assertSame(statusReportDestinations, statusReportDestinations2);
    assertSame(resultsPublisherConfig, resultsPublisherConfig2);
  }

  /**
   * Test {@link Query#getQueryTimeIteratorClassName()}.
   * <p>
   * Method under test: {@link Query#getQueryTimeIteratorClassName()}
   */
  @Test
  @DisplayName("Test getQueryTimeIteratorClassName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Query.getQueryTimeIteratorClassName()"})
  void testGetQueryTimeIteratorClassName() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertEquals("Query Time Iterator Class Name", buildResult.getQueryTimeIteratorClassName());
  }

  /**
   * Test {@link Query#getQueryTimeIteratorConfig()}.
   * <p>
   * Method under test: {@link Query#getQueryTimeIteratorConfig()}
   */
  @Test
  @DisplayName("Test getQueryTimeIteratorConfig()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Query.getQueryTimeIteratorConfig()"})
  void testGetQueryTimeIteratorConfig() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertEquals("Query Time Iterator Config", buildResult.getQueryTimeIteratorConfig());
  }

  /**
   * Test {@link Query#getResultsPublisherConfig()}.
   * <p>
   * Method under test: {@link Query#getResultsPublisherConfig()}
   */
  @Test
  @DisplayName("Test getResultsPublisherConfig()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map Query.getResultsPublisherConfig()"})
  void testGetResultsPublisherConfig() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertTrue(buildResult.getResultsPublisherConfig().isEmpty());
  }

  /**
   * Test {@link Query#getRequestedValueFields()}.
   * <p>
   * Method under test: {@link Query#getRequestedValueFields()}
   */
  @Test
  @DisplayName("Test getRequestedValueFields()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List Query.getRequestedValueFields()"})
  void testGetRequestedValueFields() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertTrue(buildResult.getRequestedValueFields().isEmpty());
  }

  /**
   * Test {@link Query#getStatusReportDestinations()}.
   * <p>
   * Method under test: {@link Query#getStatusReportDestinations()}
   */
  @Test
  @DisplayName("Test getStatusReportDestinations()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List Query.getStatusReportDestinations()"})
  void testGetStatusReportDestinations() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertTrue(buildResult.getStatusReportDestinations().isEmpty());
  }

  /**
   * Test {@link Query#withRequestedValueFields(List)}.
   * <p>
   * Method under test: {@link Query#withRequestedValueFields(List)}
   */
  @Test
  @DisplayName("Test withRequestedValueFields(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Query Query.withRequestedValueFields(List)"})
  void testWithRequestedValueFields() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertEquals(buildResult, buildResult.withRequestedValueFields(new ArrayList<>()));
  }

  /**
   * Test {@link Query#withResultsPublisherConfig(Map)}.
   * <p>
   * Method under test: {@link Query#withResultsPublisherConfig(Map)}
   */
  @Test
  @DisplayName("Test withResultsPublisherConfig(Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Query Query.withResultsPublisherConfig(Map)"})
  void testWithResultsPublisherConfig() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertEquals(buildResult, buildResult.withResultsPublisherConfig(new HashMap<>()));
  }

  /**
   * Test {@link Query#withStatusReportDestination(Map)}.
   * <p>
   * Method under test: {@link Query#withStatusReportDestination(Map)}
   */
  @Test
  @DisplayName("Test withStatusReportDestination(Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Query Query.withStatusReportDestination(Map)"})
  void testWithStatusReportDestination() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    ArrayList<String> requestedValueFields = new ArrayList<>();
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(requestedValueFields);
    HashMap<String, String> resultsPublisherConfig = new HashMap<>();
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(resultsPublisherConfig);
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act
    Query actualWithStatusReportDestinationResult = buildResult.withStatusReportDestination(new HashMap<>());

    // Assert
    assertEquals("42", actualWithStatusReportDestinationResult.getQueryId());
    assertEquals("Query Time Iterator Class Name",
        actualWithStatusReportDestinationResult.getQueryTimeIteratorClassName());
    QueryProcessingConfig processingConfig2 = actualWithStatusReportDestinationResult.getProcessingConfig();
    assertEquals("Query Time Iterator Class Name", processingConfig2.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", actualWithStatusReportDestinationResult.getQueryTimeIteratorConfig());
    assertEquals("Query Time Iterator Config", processingConfig2.getQueryTimeIteratorConfig());
    assertEquals("Table Name", actualWithStatusReportDestinationResult.getTableName());
    List<Map<String, String>> statusReportDestinations = actualWithStatusReportDestinationResult
        .getStatusReportDestinations();
    assertEquals(1, statusReportDestinations.size());
    assertTrue(actualWithStatusReportDestinationResult.getRegions().isEmpty());
    assertTrue(actualWithStatusReportDestinationResult.getRequestedValueFields().isEmpty());
    assertTrue(statusReportDestinations.get(0).isEmpty());
    assertTrue(actualWithStatusReportDestinationResult.getResultsPublisherConfig().isEmpty());
    assertSame(requestedValueFields, processingConfig2.getRequestedValueFields());
    assertSame(resultsPublisherConfig, processingConfig2.getResultsPublisherConfig());
    assertSame(statusReportDestinations, processingConfig2.getStatusReportDestinations());
  }

  /**
   * Test {@link Query#equals(Object)}, and {@link Query#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Query#equals(Object)}
   *   <li>{@link Query#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Query.equals(Object)", "int Query.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    Query.Builder builderResult2 = Query.builder();
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult2 = builderResult2.processingConfig(processingConfig2).queryId("42");
    Query buildResult2 = queryIdResult2.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link Query#equals(Object)}, and {@link Query#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Query#equals(Object)}
   *   <li>{@link Query#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Query.equals(Object)", "int Query.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link Query#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Query#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Query.equals(Object)", "int Query.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName(null)
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("42");
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    Query.Builder builderResult2 = Query.builder();
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult2 = builderResult2.processingConfig(processingConfig2).queryId("42");
    Query buildResult2 = queryIdResult2.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link Query#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Query#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Query.equals(Object)", "int Query.hashCode()"})
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
    Query.Builder queryIdResult = builderResult.processingConfig(processingConfig).queryId("Query Id");
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();
    Query.Builder builderResult2 = Query.builder();
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult2 = builderResult2.processingConfig(processingConfig2).queryId("42");
    Query buildResult2 = queryIdResult2.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link Query#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Query#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Query.equals(Object)", "int Query.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<Region> regions = new ArrayList<>();
    regions.add(new Region(new ArrayList<>()));
    Query.Builder builderResult = Query.builder();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query buildResult = builderResult.processingConfig(processingConfig)
        .queryId("42")
        .regions(regions)
        .tableName("Table Name")
        .build();
    Query.Builder builderResult2 = Query.builder();
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult2.processingConfig(processingConfig2).queryId("42");
    Query buildResult2 = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link Query#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Query#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Query.equals(Object)", "int Query.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("42").build();
    Query.Builder builderResult2 = Query.builder();
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult2 = builderResult2.processingConfig(processingConfig2).queryId("42");
    Query buildResult2 = queryIdResult2.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link Query#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Query#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Query.equals(Object)", "int Query.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link Query#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Query#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Query.equals(Object)", "int Query.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
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
    Query buildResult = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to Query");
  }
}
