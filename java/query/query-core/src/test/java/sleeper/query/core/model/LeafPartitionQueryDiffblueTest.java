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
import sleeper.core.range.Range;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.query.core.model.LeafPartitionQuery.Builder;

class LeafPartitionQueryDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#files(List)}
   *   <li>{@link Builder#leafPartitionId(String)}
   *   <li>{@link Builder#partitionRegion(Region)}
   *   <li>{@link Builder#processingConfig(QueryProcessingConfig)}
   *   <li>{@link Builder#queryId(String)}
   *   <li>{@link Builder#regions(List)}
   *   <li>{@link Builder#subQueryId(String)}
   *   <li>{@link Builder#tableId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"LeafPartitionQuery Builder.build()", "Builder Builder.files(List)",
      "Builder Builder.leafPartitionId(String)", "Builder Builder.partitionRegion(Region)",
      "Builder Builder.processingConfig(QueryProcessingConfig)", "Builder Builder.queryId(String)",
      "Builder Builder.regions(List)", "Builder Builder.subQueryId(String)", "Builder Builder.tableId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = LeafPartitionQuery.builder();
    ArrayList<String> files = new ArrayList<>();
    Builder leafPartitionIdResult = builderResult.files(files).leafPartitionId("42");
    Region partitionRegion = new Region(new ArrayList<>());
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(partitionRegion);
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    ArrayList<String> requestedValueFields = new ArrayList<>();
    QueryProcessingConfig.Builder requestedValueFieldsResult = queryTimeIteratorConfigResult
        .requestedValueFields(requestedValueFields);
    HashMap<String, String> resultsPublisherConfig = new HashMap<>();
    QueryProcessingConfig.Builder resultsPublisherConfigResult = requestedValueFieldsResult
        .resultsPublisherConfig(resultsPublisherConfig);
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult
        .statusReportDestinations(statusReportDestinations)
        .build();
    Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    ArrayList<Region> regions = new ArrayList<>();

    // Act
    LeafPartitionQuery actualBuildResult = queryIdResult.regions(regions).subQueryId("42").tableId("42").build();

    // Assert
    assertEquals("42", actualBuildResult.getLeafPartitionId());
    assertEquals("42", actualBuildResult.getQueryId());
    assertEquals("42", actualBuildResult.getSubQueryId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("Query Time Iterator Class Name", actualBuildResult.getQueryTimeIteratorClassName());
    QueryProcessingConfig processingConfig2 = actualBuildResult.getProcessingConfig();
    assertEquals("Query Time Iterator Class Name", processingConfig2.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", actualBuildResult.getQueryTimeIteratorConfig());
    assertEquals("Query Time Iterator Config", processingConfig2.getQueryTimeIteratorConfig());
    List<String> files2 = actualBuildResult.getFiles();
    assertTrue(files2.isEmpty());
    List<Region> regions2 = actualBuildResult.getRegions();
    assertTrue(regions2.isEmpty());
    List<String> requestedValueFields2 = actualBuildResult.getRequestedValueFields();
    assertTrue(requestedValueFields2.isEmpty());
    List<Map<String, String>> statusReportDestinations2 = actualBuildResult.getStatusReportDestinations();
    assertTrue(statusReportDestinations2.isEmpty());
    Map<String, String> resultsPublisherConfig2 = processingConfig2.getResultsPublisherConfig();
    assertTrue(resultsPublisherConfig2.isEmpty());
    assertSame(files, files2);
    assertSame(regions, regions2);
    assertSame(requestedValueFields, requestedValueFields2);
    assertSame(statusReportDestinations, statusReportDestinations2);
    assertSame(requestedValueFields, processingConfig2.getRequestedValueFields());
    assertSame(statusReportDestinations, processingConfig2.getStatusReportDestinations());
    assertSame(resultsPublisherConfig, resultsPublisherConfig2);
    assertSame(partitionRegion, actualBuildResult.getPartitionRegion());
  }

  /**
   * Test Builder {@link Builder#parentQuery(Query)}.
   * <p>
   * Method under test: {@link Builder#parentQuery(Query)}
   */
  @Test
  @DisplayName("Test Builder parentQuery(Query)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.parentQuery(Query)"})
  void testBuilderParentQuery() {
    // Arrange
    Builder builderResult = LeafPartitionQuery.builder();
    Query.Builder builderResult2 = Query.builder();
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult = queryTimeIteratorConfigResult
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult = requestedValueFieldsResult
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Query.Builder queryIdResult = builderResult2.processingConfig(processingConfig).queryId("42");
    Query parentQuery = queryIdResult.regions(new ArrayList<>()).tableName("Table Name").build();

    // Act and Assert
    assertSame(builderResult, builderResult.parentQuery(parentQuery));
  }

  /**
   * Test {@link LeafPartitionQuery#getStatusReportDestinations()}.
   * <p>
   * Method under test: {@link LeafPartitionQuery#getStatusReportDestinations()}
   */
  @Test
  @DisplayName("Test getStatusReportDestinations()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List LeafPartitionQuery.getStatusReportDestinations()"})
  void testGetStatusReportDestinations() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertTrue(buildResult.getStatusReportDestinations().isEmpty());
  }

  /**
   * Test {@link LeafPartitionQuery#getQueryTimeIteratorClassName()}.
   * <p>
   * Method under test: {@link LeafPartitionQuery#getQueryTimeIteratorClassName()}
   */
  @Test
  @DisplayName("Test getQueryTimeIteratorClassName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LeafPartitionQuery.getQueryTimeIteratorClassName()"})
  void testGetQueryTimeIteratorClassName() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertEquals("Query Time Iterator Class Name", buildResult.getQueryTimeIteratorClassName());
  }

  /**
   * Test {@link LeafPartitionQuery#getQueryTimeIteratorConfig()}.
   * <p>
   * Method under test: {@link LeafPartitionQuery#getQueryTimeIteratorConfig()}
   */
  @Test
  @DisplayName("Test getQueryTimeIteratorConfig()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LeafPartitionQuery.getQueryTimeIteratorConfig()"})
  void testGetQueryTimeIteratorConfig() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertEquals("Query Time Iterator Config", buildResult.getQueryTimeIteratorConfig());
  }

  /**
   * Test {@link LeafPartitionQuery#getRequestedValueFields()}.
   * <p>
   * Method under test: {@link LeafPartitionQuery#getRequestedValueFields()}
   */
  @Test
  @DisplayName("Test getRequestedValueFields()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List LeafPartitionQuery.getRequestedValueFields()"})
  void testGetRequestedValueFields() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertTrue(buildResult.getRequestedValueFields().isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LeafPartitionQuery#toString()}
   *   <li>{@link LeafPartitionQuery#getFiles()}
   *   <li>{@link LeafPartitionQuery#getLeafPartitionId()}
   *   <li>{@link LeafPartitionQuery#getPartitionRegion()}
   *   <li>{@link LeafPartitionQuery#getProcessingConfig()}
   *   <li>{@link LeafPartitionQuery#getQueryId()}
   *   <li>{@link LeafPartitionQuery#getRegions()}
   *   <li>{@link LeafPartitionQuery#getSubQueryId()}
   *   <li>{@link LeafPartitionQuery#getTableId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List LeafPartitionQuery.getFiles()", "String LeafPartitionQuery.getLeafPartitionId()",
      "Region LeafPartitionQuery.getPartitionRegion()",
      "QueryProcessingConfig LeafPartitionQuery.getProcessingConfig()", "String LeafPartitionQuery.getQueryId()",
      "List LeafPartitionQuery.getRegions()", "String LeafPartitionQuery.getSubQueryId()",
      "String LeafPartitionQuery.getTableId()", "String LeafPartitionQuery.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = LeafPartitionQuery.builder();
    ArrayList<String> files = new ArrayList<>();
    Builder leafPartitionIdResult = builderResult.files(files).leafPartitionId("42");
    Region partitionRegion = new Region(new ArrayList<>());
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(partitionRegion);
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    ArrayList<String> requestedValueFields = new ArrayList<>();
    QueryProcessingConfig.Builder requestedValueFieldsResult = queryTimeIteratorConfigResult
        .requestedValueFields(requestedValueFields);
    HashMap<String, String> resultsPublisherConfig = new HashMap<>();
    QueryProcessingConfig.Builder resultsPublisherConfigResult = requestedValueFieldsResult
        .resultsPublisherConfig(resultsPublisherConfig);
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult
        .statusReportDestinations(statusReportDestinations)
        .build();
    Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    ArrayList<Region> regions = new ArrayList<>();
    LeafPartitionQuery buildResult = queryIdResult.regions(regions).subQueryId("42").tableId("42").build();

    // Act
    String actualToStringResult = buildResult.toString();
    List<String> actualFiles = buildResult.getFiles();
    String actualLeafPartitionId = buildResult.getLeafPartitionId();
    Region actualPartitionRegion = buildResult.getPartitionRegion();
    QueryProcessingConfig actualProcessingConfig = buildResult.getProcessingConfig();
    String actualQueryId = buildResult.getQueryId();
    List<Region> actualRegions = buildResult.getRegions();
    String actualSubQueryId = buildResult.getSubQueryId();

    // Assert
    assertEquals("42", actualLeafPartitionId);
    assertEquals("42", actualQueryId);
    assertEquals("42", actualSubQueryId);
    assertEquals("42", buildResult.getTableId());
    assertEquals("LeafPartitionQuery{tableId='42', queryId='42', subQueryId='42', regions=[], processingConfig"
        + "=QueryProcessingConfig{queryTimeIteratorClassName='Query Time Iterator Class Name', queryTimeIteratorConfig"
        + "='Query Time Iterator Config', resultsPublisherConfig={}, statusReportDestinations=[], requestedValueFields"
        + "=[]}, leafPartitionId='42', partitionRegion=Region{rowKeyFieldNameToRange={}}, files=[]}",
        actualToStringResult);
    assertEquals("Query Time Iterator Class Name", actualProcessingConfig.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", actualProcessingConfig.getQueryTimeIteratorConfig());
    assertTrue(actualFiles.isEmpty());
    assertTrue(actualRegions.isEmpty());
    List<String> requestedValueFields2 = actualProcessingConfig.getRequestedValueFields();
    assertTrue(requestedValueFields2.isEmpty());
    List<Map<String, String>> statusReportDestinations2 = actualProcessingConfig.getStatusReportDestinations();
    assertTrue(statusReportDestinations2.isEmpty());
    Map<String, String> resultsPublisherConfig2 = actualProcessingConfig.getResultsPublisherConfig();
    assertTrue(resultsPublisherConfig2.isEmpty());
    assertSame(files, actualFiles);
    assertSame(regions, actualRegions);
    assertSame(requestedValueFields, requestedValueFields2);
    assertSame(statusReportDestinations, statusReportDestinations2);
    assertSame(resultsPublisherConfig, resultsPublisherConfig2);
    assertSame(partitionRegion, actualPartitionRegion);
  }

  /**
   * Test {@link LeafPartitionQuery#withRequestedValueFields(List)}.
   * <p>
   * Method under test: {@link LeafPartitionQuery#withRequestedValueFields(List)}
   */
  @Test
  @DisplayName("Test withRequestedValueFields(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"LeafPartitionQuery LeafPartitionQuery.withRequestedValueFields(List)"})
  void testWithRequestedValueFields() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertEquals(buildResult, buildResult.withRequestedValueFields(new ArrayList<>()));
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}, and {@link LeafPartitionQuery#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LeafPartitionQuery#equals(Object)}
   *   <li>{@link LeafPartitionQuery#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    Builder builderResult2 = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult2 = builderResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult2 = partitionRegionResult2.processingConfig(processingConfig2).queryId("42");
    LeafPartitionQuery buildResult2 = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}, and {@link LeafPartitionQuery#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LeafPartitionQuery#equals(Object)}
   *   <li>{@link LeafPartitionQuery#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");
    Builder leafPartitionIdResult = LeafPartitionQuery.builder().files(files).leafPartitionId("42");
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    Builder builderResult = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult2 = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult2 = partitionRegionResult2.processingConfig(processingConfig2).queryId("42");
    LeafPartitionQuery buildResult2 = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builderResult = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("Leaf Partition Id");
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    Builder builderResult2 = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult2 = builderResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult2 = partitionRegionResult2.processingConfig(processingConfig2).queryId("42");
    LeafPartitionQuery buildResult2 = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"));
    Region partitionRegion = new Region(ranges);
    Builder builderResult = LeafPartitionQuery.builder();
    Builder partitionRegionResult = builderResult.files(new ArrayList<>())
        .leafPartitionId("42")
        .partitionRegion(partitionRegion);
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    Builder builderResult2 = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult = builderResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult2 = partitionRegionResult2.processingConfig(processingConfig2).queryId("42");
    LeafPartitionQuery buildResult2 = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder builderResult = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName(null)
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult = queryTimeIteratorConfigResult
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult = requestedValueFieldsResult
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    Builder builderResult2 = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult2 = builderResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult2 = partitionRegionResult2.processingConfig(processingConfig2).queryId("42");
    LeafPartitionQuery buildResult2 = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
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
    Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("Query Id");
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    Builder builderResult2 = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult2 = builderResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult2 = partitionRegionResult2.processingConfig(processingConfig2).queryId("42");
    LeafPartitionQuery buildResult2 = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    ArrayList<Region> regions = new ArrayList<>();
    regions.add(new Region(new ArrayList<>()));
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
    LeafPartitionQuery buildResult = partitionRegionResult.processingConfig(processingConfig)
        .queryId("42")
        .regions(regions)
        .subQueryId("42")
        .tableId("42")
        .build();
    Builder builderResult2 = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult2 = builderResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult = partitionRegionResult2.processingConfig(processingConfig2).queryId("42");
    LeafPartitionQuery buildResult2 = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>())
        .subQueryId("Sub Query Id")
        .tableId("42")
        .build();
    Builder builderResult2 = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult2 = builderResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult2 = partitionRegionResult2.processingConfig(processingConfig2).queryId("42");
    LeafPartitionQuery buildResult2 = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>())
        .subQueryId("42")
        .tableId("Table Id")
        .build();
    Builder builderResult2 = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult2 = builderResult2.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult2 = leafPartitionIdResult2.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult2 = partitionRegionResult2.processingConfig(processingConfig2).queryId("42");
    LeafPartitionQuery buildResult2 = queryIdResult2.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link LeafPartitionQuery#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LeafPartitionQuery.equals(Object)", "int LeafPartitionQuery.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
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
    LeafPartitionQuery buildResult = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to LeafPartitionQuery");
  }
}
