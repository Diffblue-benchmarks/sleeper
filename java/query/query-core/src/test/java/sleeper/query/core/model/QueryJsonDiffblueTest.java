package sleeper.query.core.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
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
import sleeper.core.range.Region;
import sleeper.core.range.RegionSerDe;
import sleeper.core.range.RegionSerDe.KeyDoesNotExistException;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.query.core.model.QueryProcessingConfig.Builder;
import sleeper.query.core.model.QuerySerDe.SchemaLoader;

class QueryJsonDiffblueTest {
  /**
   * Test {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)} with {@code leafQuery}, {@code schemaLoader}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(LeafPartitionQuery, SchemaLoader) with 'leafQuery', 'schemaLoader'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(LeafPartitionQuery, SchemaLoader)"})
  void testFromWithLeafQuerySchemaLoader_givenArrayListAddRegionWithRangesIsArrayList() {
    // Arrange
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

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult2);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    QueryJson.from(leafQuery, schemaLoader);

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
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)} with {@code leafQuery}, {@code schemaLoader}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(LeafPartitionQuery, SchemaLoader) with 'leafQuery', 'schemaLoader'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(LeafPartitionQuery, SchemaLoader)"})
  void testFromWithLeafQuerySchemaLoader_givenArrayListAddRegionWithRangesIsArrayList2() {
    // Arrange
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

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult2);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    QueryJson.from(leafQuery, schemaLoader);

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
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)} with {@code leafQuery}, {@code schemaLoader}.
   * <ul>
   *   <li>Given empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(LeafPartitionQuery, SchemaLoader) with 'leafQuery', 'schemaLoader'; given empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(LeafPartitionQuery, SchemaLoader)"})
  void testFromWithLeafQuerySchemaLoader_givenEmpty() {
    // Arrange
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    Optional<Schema> emptyResult = Optional.empty();
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(QueryValidationException.class, () -> QueryJson.from(leafQuery, schemaLoader));
    verify(leafQuery).getQueryId();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)} with {@code leafQuery}, {@code schemaLoader}.
   * <ul>
   *   <li>Given {@link Exception#Exception(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(LeafPartitionQuery, SchemaLoader) with 'leafQuery', 'schemaLoader'; given Exception(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(LeafPartitionQuery, SchemaLoader)"})
  void testFromWithLeafQuerySchemaLoader_givenExceptionWithFoo() {
    // Arrange
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getQueryId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    when(leafQuery.getStatusReportDestinations()).thenReturn(new ArrayList<>());
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any()))
        .thenThrow(new QueryValidationException("42", statusReportDestinations, new Exception("foo")));

    // Act and Assert
    assertThrows(QueryValidationException.class, () -> QueryJson.from(leafQuery, schemaLoader));
    verify(leafQuery).getQueryId();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)} with {@code leafQuery}, {@code schemaLoader}.
   * <ul>
   *   <li>Given {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(LeafPartitionQuery, SchemaLoader) with 'leafQuery', 'schemaLoader'; given 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(LeafPartitionQuery, SchemaLoader)"})
  void testFromWithLeafQuerySchemaLoader_givenNull() {
    // Arrange
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

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult2);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    QueryJson.from(leafQuery, schemaLoader);

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
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)} with {@code leafQuery}, {@code schemaLoader}.
   * <ul>
   *   <li>Then calls {@link LeafPartitionQuery#getFiles()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(LeafPartitionQuery, SchemaLoader) with 'leafQuery', 'schemaLoader'; then calls getFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(LeafPartitionQuery, SchemaLoader)"})
  void testFromWithLeafQuerySchemaLoader_thenCallsGetFiles() {
    // Arrange
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

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult2);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    QueryJson.from(leafQuery, schemaLoader);

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
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)} with {@code leafQuery}, {@code schemaLoader}.
   * <ul>
   *   <li>Then throw {@link KeyDoesNotExistException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(LeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(LeafPartitionQuery, SchemaLoader) with 'leafQuery', 'schemaLoader'; then throw KeyDoesNotExistException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(LeafPartitionQuery, SchemaLoader)"})
  void testFromWithLeafQuerySchemaLoader_thenThrowKeyDoesNotExistException() {
    // Arrange
    LeafPartitionQuery leafQuery = mock(LeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenThrow(new KeyDoesNotExistException("LeafPartitionQuery"));
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

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult2);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(KeyDoesNotExistException.class, () -> QueryJson.from(leafQuery, schemaLoader));
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getProcessingConfig();
    verify(leafQuery, atLeast(1)).getQueryId();
    verify(leafQuery).getRegions();
    verify(leafQuery).getStatusReportDestinations();
    verify(leafQuery).getSubQueryId();
    verify(leafQuery, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link QueryJson#from(Query, SchemaLoader)} with {@code query}, {@code schemaLoader}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code null}.</li>
   *   <li>Then calls {@link Query#getProcessingConfig()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(Query, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(Query, SchemaLoader) with 'query', 'schemaLoader'; given ArrayList() add 'null'; then calls getProcessingConfig()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(Query, SchemaLoader)"})
  void testFromWithQuerySchemaLoader_givenArrayListAddNull_thenCallsGetProcessingConfig() {
    // Arrange
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

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult2);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableName(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    QueryJson.from(query, schemaLoader);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    verify(schemaLoader).getSchemaByTableName(eq("Table Name"));
  }

  /**
   * Test {@link QueryJson#from(Query, SchemaLoader)} with {@code query}, {@code schemaLoader}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(Query, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(Query, SchemaLoader) with 'query', 'schemaLoader'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(Query, SchemaLoader)"})
  void testFromWithQuerySchemaLoader_givenArrayListAddRegionWithRangesIsArrayList() {
    // Arrange
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

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult2);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableName(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    QueryJson.from(query, schemaLoader);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    verify(schemaLoader).getSchemaByTableName(eq("Table Name"));
  }

  /**
   * Test {@link QueryJson#from(Query, SchemaLoader)} with {@code query}, {@code schemaLoader}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(Query, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(Query, SchemaLoader) with 'query', 'schemaLoader'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(Query, SchemaLoader)"})
  void testFromWithQuerySchemaLoader_givenArrayListAddRegionWithRangesIsArrayList2() {
    // Arrange
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

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult2);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableName(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    QueryJson.from(query, schemaLoader);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    verify(schemaLoader).getSchemaByTableName(eq("Table Name"));
  }

  /**
   * Test {@link QueryJson#from(Query, SchemaLoader)} with {@code query}, {@code schemaLoader}.
   * <ul>
   *   <li>Given empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(Query, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(Query, SchemaLoader) with 'query', 'schemaLoader'; given empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(Query, SchemaLoader)"})
  void testFromWithQuerySchemaLoader_givenEmpty() {
    // Arrange
    Query query = mock(Query.class);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    Optional<Schema> emptyResult = Optional.empty();
    when(schemaLoader.getSchemaByTableName(Mockito.<String>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(QueryValidationException.class, () -> QueryJson.from(query, schemaLoader));
    verify(query).getQueryId();
    verify(query).getStatusReportDestinations();
    verify(query).getTableName();
    verify(schemaLoader).getSchemaByTableName(eq("Table Name"));
  }

  /**
   * Test {@link QueryJson#from(Query, SchemaLoader)} with {@code query}, {@code schemaLoader}.
   * <ul>
   *   <li>Given {@link Exception#Exception(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(Query, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(Query, SchemaLoader) with 'query', 'schemaLoader'; given Exception(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(Query, SchemaLoader)"})
  void testFromWithQuerySchemaLoader_givenExceptionWithFoo() {
    // Arrange
    Query query = mock(Query.class);
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    when(schemaLoader.getSchemaByTableName(Mockito.<String>any()))
        .thenThrow(new QueryValidationException("42", statusReportDestinations, new Exception("foo")));

    // Act and Assert
    assertThrows(QueryValidationException.class, () -> QueryJson.from(query, schemaLoader));
    verify(query).getQueryId();
    verify(query).getStatusReportDestinations();
    verify(query).getTableName();
    verify(schemaLoader).getSchemaByTableName(eq("Table Name"));
  }

  /**
   * Test {@link QueryJson#from(Query, SchemaLoader)} with {@code query}, {@code schemaLoader}.
   * <ul>
   *   <li>Then calls {@link Query#getProcessingConfig()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(Query, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(Query, SchemaLoader) with 'query', 'schemaLoader'; then calls getProcessingConfig()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(Query, SchemaLoader)"})
  void testFromWithQuerySchemaLoader_thenCallsGetProcessingConfig() {
    // Arrange
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

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult2 = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult2);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableName(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    QueryJson.from(query, schemaLoader);

    // Assert
    verify(query).getProcessingConfig();
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    verify(schemaLoader).getSchemaByTableName(eq("Table Name"));
  }

  /**
   * Test {@link QueryJson#from(Query, SchemaLoader)} with {@code query}, {@code schemaLoader}.
   * <ul>
   *   <li>Then throw {@link KeyDoesNotExistException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryJson#from(Query, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(Query, SchemaLoader) with 'query', 'schemaLoader'; then throw KeyDoesNotExistException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryJson QueryJson.from(Query, SchemaLoader)"})
  void testFromWithQuerySchemaLoader_thenThrowKeyDoesNotExistException() {
    // Arrange
    Query query = mock(Query.class);
    when(query.getRegions()).thenThrow(new KeyDoesNotExistException("Query"));
    when(query.getQueryId()).thenReturn("42");
    when(query.getTableName()).thenReturn("Table Name");
    when(query.getStatusReportDestinations()).thenReturn(new ArrayList<>());

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableName(Mockito.<String>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(KeyDoesNotExistException.class, () -> QueryJson.from(query, schemaLoader));
    verify(query, atLeast(1)).getQueryId();
    verify(query).getRegions();
    verify(query).getStatusReportDestinations();
    verify(query, atLeast(1)).getTableName();
    verify(schemaLoader).getSchemaByTableName(eq("Table Name"));
  }
}
