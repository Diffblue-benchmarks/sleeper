package sleeper.query.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.query.core.model.QueryProcessingConfig.Builder;

class QuerySerDeDiffblueTest {
  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)} with {@code leafQuery}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery, boolean) with 'leafQuery', 'prettyPrint'; given ArrayList() add 'foo'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery, boolean)"})
  void testToJsonWithLeafQueryPrettyPrint_givenArrayListAddFoo_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    stringList.add("LeafPartitionQuery");
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(stringList);
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery, true);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals(
        "{\n" + "  \"tableName\": null,\n" + "  \"tableId\": \"42\",\n" + "  \"queryId\": \"42\",\n"
            + "  \"type\": \"LeafPartitionQuery\",\n" + "  \"regions\": [],\n" + "  \"requestedValueFields\": [],\n"
            + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
            + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
            + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": \"42\",\n"
            + "  \"leafPartitionId\": \"42\",\n" + "  \"partitionRegion\": {\n" + "    \"stringsBase64Encoded\": true\n"
            + "  },\n" + "  \"files\": [\n" + "    \"foo\",\n" + "    \"LeafPartitionQuery\"\n" + "  ]\n" + "}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)} with {@code leafQuery}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code LeafPartitionQuery}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery, boolean) with 'leafQuery', 'prettyPrint'; given ArrayList() add 'LeafPartitionQuery'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery, boolean)"})
  void testToJsonWithLeafQueryPrettyPrint_givenArrayListAddLeafPartitionQuery() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("LeafPartitionQuery");
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(stringList);
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery, true);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals("{\n" + "  \"tableName\": null,\n" + "  \"tableId\": \"42\",\n" + "  \"queryId\": \"42\",\n"
        + "  \"type\": \"LeafPartitionQuery\",\n" + "  \"regions\": [],\n" + "  \"requestedValueFields\": [],\n"
        + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
        + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
        + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": \"42\",\n" + "  \"leafPartitionId\": \"42\",\n"
        + "  \"partitionRegion\": {\n" + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": [\n"
        + "    \"LeafPartitionQuery\"\n" + "  ]\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)} with {@code leafQuery}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery, boolean) with 'leafQuery', 'prettyPrint'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery, boolean)"})
  void testToJsonWithLeafQueryPrettyPrint_givenArrayListAddRegionWithRangesIsArrayList() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(regionList);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery, true);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals("{\n" + "  \"tableName\": null,\n" + "  \"tableId\": \"42\",\n" + "  \"queryId\": \"42\",\n"
        + "  \"type\": \"LeafPartitionQuery\",\n" + "  \"regions\": [\n" + "    {\n"
        + "      \"stringsBase64Encoded\": true\n" + "    }\n" + "  ],\n" + "  \"requestedValueFields\": [],\n"
        + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
        + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
        + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": \"42\",\n" + "  \"leafPartitionId\": \"42\",\n"
        + "  \"partitionRegion\": {\n" + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": []\n" + "}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)} with {@code leafQuery}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery, boolean) with 'leafQuery', 'prettyPrint'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery, boolean)"})
  void testToJsonWithLeafQueryPrettyPrint_givenArrayListAddRegionWithRangesIsArrayList2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    regionList.add(new Region(new ArrayList<>()));
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(regionList);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery, true);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals("{\n" + "  \"tableName\": null,\n" + "  \"tableId\": \"42\",\n" + "  \"queryId\": \"42\",\n"
        + "  \"type\": \"LeafPartitionQuery\",\n" + "  \"regions\": [\n" + "    {\n"
        + "      \"stringsBase64Encoded\": true\n" + "    },\n" + "    {\n" + "      \"stringsBase64Encoded\": true\n"
        + "    }\n" + "  ],\n" + "  \"requestedValueFields\": [],\n"
        + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
        + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
        + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": \"42\",\n" + "  \"leafPartitionId\": \"42\",\n"
        + "  \"partitionRegion\": {\n" + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": []\n" + "}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)} with {@code leafQuery}, {@code prettyPrint}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery, boolean) with 'leafQuery', 'prettyPrint'; given empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery, boolean)"})
  void testToJsonWithLeafQueryPrettyPrint_givenEmptyString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery, true);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals("{\n" + "  \"tableName\": null,\n" + "  \"tableId\": \"42\",\n" + "  \"queryId\": \"42\",\n"
        + "  \"type\": \"LeafPartitionQuery\",\n" + "  \"regions\": [],\n" + "  \"requestedValueFields\": [],\n"
        + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
        + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
        + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": \"42\",\n" + "  \"leafPartitionId\": \"\",\n"
        + "  \"partitionRegion\": {\n" + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": []\n" + "}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)} with {@code leafQuery}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery, boolean) with 'leafQuery', 'prettyPrint'; given 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery, boolean)"})
  void testToJsonWithLeafQueryPrettyPrint_givenNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(null);
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery, true);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals(
        "{\n" + "  \"tableName\": null,\n" + "  \"tableId\": \"42\",\n" + "  \"queryId\": \"42\",\n"
            + "  \"type\": \"LeafPartitionQuery\",\n" + "  \"regions\": [],\n" + "  \"requestedValueFields\": [],\n"
            + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
            + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
            + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": \"42\",\n"
            + "  \"leafPartitionId\": \"42\",\n" + "  \"partitionRegion\": null,\n" + "  \"files\": []\n" + "}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)} with {@code leafQuery}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery, boolean) with 'leafQuery', 'prettyPrint'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery, boolean)"})
  void testToJsonWithLeafQueryPrettyPrint_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery, true);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals("{\n" + "  \"tableName\": null,\n" + "  \"tableId\": \"42\",\n" + "  \"queryId\": \"42\",\n"
        + "  \"type\": \"LeafPartitionQuery\",\n" + "  \"regions\": [],\n" + "  \"requestedValueFields\": [],\n"
        + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
        + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
        + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": \"42\",\n" + "  \"leafPartitionId\": \"42\",\n"
        + "  \"partitionRegion\": {\n" + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": []\n" + "}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)} with {@code leafQuery}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery, boolean) with 'leafQuery', 'prettyPrint'; when 'false'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery, boolean)"})
  void testToJsonWithLeafQueryPrettyPrint_whenFalse_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery, false);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals(
        "{\"tableName\":null,\"tableId\":\"42\",\"queryId\":\"42\",\"type\":\"LeafPartitionQuery\",\"regions\":[],\"requestedV"
            + "alueFields\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query"
            + " Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations\":[],\"subQueryId\":\"42\","
            + "\"leafPartitionId\":\"42\",\"partitionRegion\":{\"stringsBase64Encoded\":true},\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery)} with {@code leafQuery}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery) with 'leafQuery'; given ArrayList() add 'foo'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery)"})
  void testToJsonWithLeafQuery_givenArrayListAddFoo_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    stringList.add("LeafPartitionQuery");
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(stringList);
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals(
        "{\"tableName\":null,\"tableId\":\"42\",\"queryId\":\"42\",\"type\":\"LeafPartitionQuery\",\"regions\":[],\"requestedV"
            + "alueFields\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query"
            + " Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations\":[],\"subQueryId\":\"42\","
            + "\"leafPartitionId\":\"42\",\"partitionRegion\":{\"stringsBase64Encoded\":true},\"files\":[\"foo\",\"LeafPartitionQuery"
            + "\"]}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery)} with {@code leafQuery}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code LeafPartitionQuery}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery) with 'leafQuery'; given ArrayList() add 'LeafPartitionQuery'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery)"})
  void testToJsonWithLeafQuery_givenArrayListAddLeafPartitionQuery_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("LeafPartitionQuery");
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(stringList);
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals(
        "{\"tableName\":null,\"tableId\":\"42\",\"queryId\":\"42\",\"type\":\"LeafPartitionQuery\",\"regions\":[],\"requestedV"
            + "alueFields\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query"
            + " Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations\":[],\"subQueryId\":\"42\","
            + "\"leafPartitionId\":\"42\",\"partitionRegion\":{\"stringsBase64Encoded\":true},\"files\":[\"LeafPartitionQuery\""
            + "]}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery)} with {@code leafQuery}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery) with 'leafQuery'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery)"})
  void testToJsonWithLeafQuery_givenArrayListAddRegionWithRangesIsArrayList() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(regionList);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals(
        "{\"tableName\":null,\"tableId\":\"42\",\"queryId\":\"42\",\"type\":\"LeafPartitionQuery\",\"regions\":[{\"stringsBase64Encoded"
            + "\":true}],\"requestedValueFields\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\","
            + "\"queryTimeIteratorConfig\":\"Query Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations"
            + "\":[],\"subQueryId\":\"42\",\"leafPartitionId\":\"42\",\"partitionRegion\":{\"stringsBase64Encoded\":true},\"files"
            + "\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery)} with {@code leafQuery}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery) with 'leafQuery'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery)"})
  void testToJsonWithLeafQuery_givenArrayListAddRegionWithRangesIsArrayList2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    regionList.add(new Region(new ArrayList<>()));
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(regionList);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals(
        "{\"tableName\":null,\"tableId\":\"42\",\"queryId\":\"42\",\"type\":\"LeafPartitionQuery\",\"regions\":[{\"stringsBase64Encoded"
            + "\":true},{\"stringsBase64Encoded\":true}],\"requestedValueFields\":[],\"queryTimeIteratorClassName\":\"Query"
            + " Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query Time Iterator Config\",\"resultsPublisherConfig"
            + "\":{},\"statusReportDestinations\":[],\"subQueryId\":\"42\",\"leafPartitionId\":\"42\",\"partitionRegion\":{"
            + "\"stringsBase64Encoded\":true},\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery)} with {@code leafQuery}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery) with 'leafQuery'; given empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery)"})
  void testToJsonWithLeafQuery_givenEmptyString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals(
        "{\"tableName\":null,\"tableId\":\"42\",\"queryId\":\"42\",\"type\":\"LeafPartitionQuery\",\"regions\":[],\"requestedV"
            + "alueFields\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query"
            + " Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations\":[],\"subQueryId\":\"42\","
            + "\"leafPartitionId\":\"\",\"partitionRegion\":{\"stringsBase64Encoded\":true},\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery)} with {@code leafQuery}.
   * <ul>
   *   <li>Given {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery) with 'leafQuery'; given 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery)"})
  void testToJsonWithLeafQuery_givenNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(null);
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals(
        "{\"tableName\":null,\"tableId\":\"42\",\"queryId\":\"42\",\"type\":\"LeafPartitionQuery\",\"regions\":[],\"requestedV"
            + "alueFields\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query"
            + " Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations\":[],\"subQueryId\":\"42\","
            + "\"leafPartitionId\":\"42\",\"partitionRegion\":null,\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(LeafPartitionQuery)} with {@code leafQuery}.
   * <ul>
   *   <li>Given {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(LeafPartitionQuery) with 'leafQuery'; given Region(List) with ranges is ArrayList(); then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(LeafPartitionQuery)"})
  void testToJsonWithLeafQuery_givenRegionWithRangesIsArrayList_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getSubQueryId()).thenReturn("42");
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(leafQuery.getProcessingConfig()).thenReturn(buildResult);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(leafQuery);

    // Assert
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    assertEquals(
        "{\"tableName\":null,\"tableId\":\"42\",\"queryId\":\"42\",\"type\":\"LeafPartitionQuery\",\"regions\":[],\"requestedV"
            + "alueFields\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query"
            + " Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations\":[],\"subQueryId\":\"42\","
            + "\"leafPartitionId\":\"42\",\"partitionRegion\":{\"stringsBase64Encoded\":true},\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query)} with {@code query}.
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query)}
   */
  @Test
  @DisplayName("Test toJson(Query) with 'query'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query)"})
  void testToJsonWithQuery() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals(
        "{\"tableName\":\"Table Name\",\"tableId\":null,\"queryId\":\"42\",\"type\":\"Query\",\"regions\":[],\"requestedValueFields"
            + "\":[],\"queryTimeIteratorClassName\":\"\",\"queryTimeIteratorConfig\":\"Query Time Iterator Config\","
            + "\"resultsPublisherConfig\":{},\"statusReportDestinations\":[],\"subQueryId\":null,\"leafPartitionId\":null,"
            + "\"partitionRegion\":null,\"files\":null}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query, boolean)} with {@code query}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Query, boolean) with 'query', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query, boolean)"})
  void testToJsonWithQueryPrettyPrint() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query, true);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals("{\n" + "  \"tableName\": \"Table Name\",\n" + "  \"tableId\": null,\n" + "  \"queryId\": \"42\",\n"
        + "  \"type\": \"Query\",\n" + "  \"regions\": [],\n" + "  \"requestedValueFields\": [],\n"
        + "  \"queryTimeIteratorClassName\": \"\",\n"
        + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
        + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": null,\n" + "  \"leafPartitionId\": null,\n"
        + "  \"partitionRegion\": null,\n" + "  \"files\": null\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code null}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Query, boolean) with 'query', 'prettyPrint'; given ArrayList() add 'null'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query, boolean)"})
  void testToJsonWithQueryPrettyPrint_givenArrayListAddNull_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(null);
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(regionList);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query, true);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals("{\n" + "  \"tableName\": \"Table Name\",\n" + "  \"tableId\": null,\n" + "  \"queryId\": \"42\",\n"
        + "  \"type\": \"Query\",\n" + "  \"regions\": [\n" + "    null\n" + "  ],\n"
        + "  \"requestedValueFields\": [],\n"
        + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
        + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
        + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": null,\n" + "  \"leafPartitionId\": null,\n"
        + "  \"partitionRegion\": null,\n" + "  \"files\": null\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Query, boolean) with 'query', 'prettyPrint'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query, boolean)"})
  void testToJsonWithQueryPrettyPrint_givenArrayListAddRegionWithRangesIsArrayList() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(regionList);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query, true);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals("{\n" + "  \"tableName\": \"Table Name\",\n" + "  \"tableId\": null,\n" + "  \"queryId\": \"42\",\n"
        + "  \"type\": \"Query\",\n" + "  \"regions\": [\n" + "    {\n" + "      \"stringsBase64Encoded\": true\n"
        + "    }\n" + "  ],\n" + "  \"requestedValueFields\": [],\n"
        + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
        + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
        + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": null,\n" + "  \"leafPartitionId\": null,\n"
        + "  \"partitionRegion\": null,\n" + "  \"files\": null\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Query, boolean) with 'query', 'prettyPrint'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query, boolean)"})
  void testToJsonWithQueryPrettyPrint_givenArrayListAddRegionWithRangesIsArrayList2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    regionList.add(new Region(new ArrayList<>()));
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(regionList);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query, true);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals("{\n" + "  \"tableName\": \"Table Name\",\n" + "  \"tableId\": null,\n" + "  \"queryId\": \"42\",\n"
        + "  \"type\": \"Query\",\n" + "  \"regions\": [\n" + "    {\n" + "      \"stringsBase64Encoded\": true\n"
        + "    },\n" + "    {\n" + "      \"stringsBase64Encoded\": true\n" + "    }\n" + "  ],\n"
        + "  \"requestedValueFields\": [],\n"
        + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
        + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
        + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": null,\n" + "  \"leafPartitionId\": null,\n"
        + "  \"partitionRegion\": null,\n" + "  \"files\": null\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Query, boolean) with 'query', 'prettyPrint'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query, boolean)"})
  void testToJsonWithQueryPrettyPrint_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query, true);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals("{\n" + "  \"tableName\": \"Table Name\",\n" + "  \"tableId\": null,\n" + "  \"queryId\": \"42\",\n"
        + "  \"type\": \"Query\",\n" + "  \"regions\": [],\n" + "  \"requestedValueFields\": [],\n"
        + "  \"queryTimeIteratorClassName\": \"Query Time Iterator Class Name\",\n"
        + "  \"queryTimeIteratorConfig\": \"Query Time Iterator Config\",\n" + "  \"resultsPublisherConfig\": {},\n"
        + "  \"statusReportDestinations\": [],\n" + "  \"subQueryId\": null,\n" + "  \"leafPartitionId\": null,\n"
        + "  \"partitionRegion\": null,\n" + "  \"files\": null\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query, boolean)}
   */
  @Test
  @DisplayName("Test toJson(Query, boolean) with 'query', 'prettyPrint'; when 'false'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query, boolean)"})
  void testToJsonWithQueryPrettyPrint_whenFalse_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query, false);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals(
        "{\"tableName\":\"Table Name\",\"tableId\":null,\"queryId\":\"42\",\"type\":\"Query\",\"regions\":[],\"requestedValueFields"
            + "\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query"
            + " Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations\":[],\"subQueryId\":null,"
            + "\"leafPartitionId\":null,\"partitionRegion\":null,\"files\":null}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query)} with {@code query}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code null}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query)}
   */
  @Test
  @DisplayName("Test toJson(Query) with 'query'; given ArrayList() add 'null'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query)"})
  void testToJsonWithQuery_givenArrayListAddNull_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(null);
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(regionList);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals(
        "{\"tableName\":\"Table Name\",\"tableId\":null,\"queryId\":\"42\",\"type\":\"Query\",\"regions\":[null],\"requestedValueFields"
            + "\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query"
            + " Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations\":[],\"subQueryId\":null,"
            + "\"leafPartitionId\":null,\"partitionRegion\":null,\"files\":null}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query)} with {@code query}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query)}
   */
  @Test
  @DisplayName("Test toJson(Query) with 'query'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query)"})
  void testToJsonWithQuery_givenArrayListAddRegionWithRangesIsArrayList() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(regionList);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals(
        "{\"tableName\":\"Table Name\",\"tableId\":null,\"queryId\":\"42\",\"type\":\"Query\",\"regions\":[{\"stringsBase64Encoded"
            + "\":true}],\"requestedValueFields\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\","
            + "\"queryTimeIteratorConfig\":\"Query Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations"
            + "\":[],\"subQueryId\":null,\"leafPartitionId\":null,\"partitionRegion\":null,\"files\":null}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query)} with {@code query}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query)}
   */
  @Test
  @DisplayName("Test toJson(Query) with 'query'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query)"})
  void testToJsonWithQuery_givenArrayListAddRegionWithRangesIsArrayList2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    regionList.add(new Region(new ArrayList<>()));
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(regionList);
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals(
        "{\"tableName\":\"Table Name\",\"tableId\":null,\"queryId\":\"42\",\"type\":\"Query\",\"regions\":[{\"stringsBase64Encoded"
            + "\":true},{\"stringsBase64Encoded\":true}],\"requestedValueFields\":[],\"queryTimeIteratorClassName\":\"Query"
            + " Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query Time Iterator Config\",\"resultsPublisherConfig"
            + "\":{},\"statusReportDestinations\":[],\"subQueryId\":null,\"leafPartitionId\":null,\"partitionRegion\":null,"
            + "\"files\":null}",
        actualToJsonResult);
  }

  /**
   * Test {@link QuerySerDe#toJson(Query)} with {@code query}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link QuerySerDe#toJson(Query)}
   */
  @Test
  @DisplayName("Test toJson(Query) with 'query'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QuerySerDe.toJson(Query)"})
  void testToJsonWithQuery_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    QuerySerDe querySerDe = new QuerySerDe(schema);
    Query query = mock(Query.class);
    when(query.getRegions()).thenReturn(new ArrayList<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    when(query.getProcessingConfig()).thenReturn(buildResult);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    // Act
    String actualToJsonResult = querySerDe.toJson(query);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    assertEquals(
        "{\"tableName\":\"Table Name\",\"tableId\":null,\"queryId\":\"42\",\"type\":\"Query\",\"regions\":[],\"requestedValueFields"
            + "\":[],\"queryTimeIteratorClassName\":\"Query Time Iterator Class Name\",\"queryTimeIteratorConfig\":\"Query"
            + " Time Iterator Config\",\"resultsPublisherConfig\":{},\"statusReportDestinations\":[],\"subQueryId\":null,"
            + "\"leafPartitionId\":null,\"partitionRegion\":null,\"files\":null}",
        actualToJsonResult);
  }
}
