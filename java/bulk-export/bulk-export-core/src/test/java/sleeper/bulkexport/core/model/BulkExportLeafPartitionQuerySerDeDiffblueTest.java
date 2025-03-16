package sleeper.bulkexport.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.bulkexport.core.model.BulkExportLeafPartitionQuerySerDe.SchemaLoader;
import sleeper.core.range.Range;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.StringType;

class BulkExportLeafPartitionQuerySerDeDiffblueTest {
  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)} with {@code query}.
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery) with 'query'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery)"})
  void testToJsonWithQuery() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(null);

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\"tableId\":\"42\",\"exportId\":\"42\",\"subExportId\":\"42\",\"regions\":[],\"leafPartitionId\":\"42\",\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery, boolean) with 'query', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query, true);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals("{\n" + "  \"tableId\": \"42\",\n" + "  \"exportId\": \"42\",\n" + "  \"subExportId\": \"42\",\n"
        + "  \"regions\": [],\n" + "  \"leafPartitionId\": \"42\",\n" + "  \"partitionRegion\": {\n"
        + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": []\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>When {@code true}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery, boolean) with 'query', 'prettyPrint'; given ArrayList() add '42'; when 'true'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint_givenArrayListAdd42_whenTrue_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("42");
    stringList.add("foo");
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(stringList);
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query, true);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals("{\n" + "  \"tableId\": \"42\",\n" + "  \"exportId\": \"42\",\n" + "  \"subExportId\": \"42\",\n"
        + "  \"regions\": [],\n" + "  \"leafPartitionId\": \"42\",\n" + "  \"partitionRegion\": {\n"
        + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": [\n" + "    \"42\",\n" + "    \"foo\"\n"
        + "  ]\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery, boolean) with 'query', 'prettyPrint'; given ArrayList() add Field(String, Type) with 'Name' and type is StringType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint_givenArrayListAddFieldWithNameAndTypeIsStringType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"));
    Region region = new Region(ranges);
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(region);

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query, true);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals("{\n" + "  \"tableId\": \"42\",\n" + "  \"exportId\": \"42\",\n" + "  \"subExportId\": \"42\",\n"
        + "  \"regions\": [],\n" + "  \"leafPartitionId\": \"42\",\n" + "  \"partitionRegion\": {\n"
        + "    \"Name\": {\n" + "      \"min\": \"TWlu\",\n" + "      \"minInclusive\": true,\n"
        + "      \"max\": \"TWF4\",\n" + "      \"maxInclusive\": false\n" + "    },\n"
        + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": []\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>When {@code true}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery, boolean) with 'query', 'prettyPrint'; given ArrayList() add 'foo'; when 'true'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint_givenArrayListAddFoo_whenTrue_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(stringList);
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query, true);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\n" + "  \"tableId\": \"42\",\n" + "  \"exportId\": \"42\",\n" + "  \"subExportId\": \"42\",\n"
            + "  \"regions\": [],\n" + "  \"leafPartitionId\": \"42\",\n" + "  \"partitionRegion\": {\n"
            + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": [\n" + "    \"foo\"\n" + "  ]\n" + "}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery, boolean) with 'query', 'prettyPrint'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint_givenArrayListAddRegionWithRangesIsArrayList() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(regionList);
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query, true);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals("{\n" + "  \"tableId\": \"42\",\n" + "  \"exportId\": \"42\",\n" + "  \"subExportId\": \"42\",\n"
        + "  \"regions\": [\n" + "    {\n" + "      \"stringsBase64Encoded\": true\n" + "    }\n" + "  ],\n"
        + "  \"leafPartitionId\": \"42\",\n" + "  \"partitionRegion\": {\n" + "    \"stringsBase64Encoded\": true\n"
        + "  },\n" + "  \"files\": []\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery, boolean) with 'query', 'prettyPrint'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint_givenArrayListAddRegionWithRangesIsArrayList2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    regionList.add(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(regionList);
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query, true);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals("{\n" + "  \"tableId\": \"42\",\n" + "  \"exportId\": \"42\",\n" + "  \"subExportId\": \"42\",\n"
        + "  \"regions\": [\n" + "    {\n" + "      \"stringsBase64Encoded\": true\n" + "    },\n" + "    {\n"
        + "      \"stringsBase64Encoded\": true\n" + "    }\n" + "  ],\n" + "  \"leafPartitionId\": \"42\",\n"
        + "  \"partitionRegion\": {\n" + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": []\n" + "}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery, boolean) with 'query', 'prettyPrint'; given empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint_givenEmptyString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query, true);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals("{\n" + "  \"tableId\": \"42\",\n" + "  \"exportId\": \"\",\n" + "  \"subExportId\": \"42\",\n"
        + "  \"regions\": [],\n" + "  \"leafPartitionId\": \"42\",\n" + "  \"partitionRegion\": {\n"
        + "    \"stringsBase64Encoded\": true\n" + "  },\n" + "  \"files\": []\n" + "}", actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>Given {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery, boolean) with 'query', 'prettyPrint'; given 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint_givenNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(null);

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query, true);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\n" + "  \"tableId\": \"42\",\n" + "  \"exportId\": \"42\",\n" + "  \"subExportId\": \"42\",\n"
            + "  \"regions\": [],\n" + "  \"leafPartitionId\": \"42\",\n" + "  \"files\": []\n" + "}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery, boolean) with 'query', 'prettyPrint'; when 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint_whenFalse() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query, false);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\"tableId\":\"42\",\"exportId\":\"42\",\"subExportId\":\"42\",\"regions\":[],\"leafPartitionId\":\"42\",\"partitionRegion"
            + "\":{\"stringsBase64Encoded\":true},\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)} with {@code query}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery) with 'query'; given ArrayList() add '42'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery)"})
  void testToJsonWithQuery_givenArrayListAdd42_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("42");
    stringList.add("foo");
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(stringList);
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\"tableId\":\"42\",\"exportId\":\"42\",\"subExportId\":\"42\",\"regions\":[],\"leafPartitionId\":\"42\",\"partitionRegion"
            + "\":{\"stringsBase64Encoded\":true},\"files\":[\"42\",\"foo\"]}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)} with {@code query}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery) with 'query'; given ArrayList() add Field(String, Type) with 'Name' and type is StringType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery)"})
  void testToJsonWithQuery_givenArrayListAddFieldWithNameAndTypeIsStringType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);

    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"));
    Region region = new Region(ranges);
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(region);

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\"tableId\":\"42\",\"exportId\":\"42\",\"subExportId\":\"42\",\"regions\":[],\"leafPartitionId\":\"42\",\"partitionRegion"
            + "\":{\"Name\":{\"min\":\"TWlu\",\"minInclusive\":true,\"max\":\"TWF4\",\"maxInclusive\":false},\"stringsBase64Encoded"
            + "\":true},\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)} with {@code query}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery) with 'query'; given ArrayList() add 'foo'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery)"})
  void testToJsonWithQuery_givenArrayListAddFoo_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(stringList);
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\"tableId\":\"42\",\"exportId\":\"42\",\"subExportId\":\"42\",\"regions\":[],\"leafPartitionId\":\"42\",\"partitionRegion"
            + "\":{\"stringsBase64Encoded\":true},\"files\":[\"foo\"]}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)} with {@code query}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery) with 'query'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery)"})
  void testToJsonWithQuery_givenArrayListAddRegionWithRangesIsArrayList() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(regionList);
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\"tableId\":\"42\",\"exportId\":\"42\",\"subExportId\":\"42\",\"regions\":[{\"stringsBase64Encoded\":true}],"
            + "\"leafPartitionId\":\"42\",\"partitionRegion\":{\"stringsBase64Encoded\":true},\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)} with {@code query}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery) with 'query'; given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery)"})
  void testToJsonWithQuery_givenArrayListAddRegionWithRangesIsArrayList2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);

    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    regionList.add(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(regionList);
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\"tableId\":\"42\",\"exportId\":\"42\",\"subExportId\":\"42\",\"regions\":[{\"stringsBase64Encoded\":true},{"
            + "\"stringsBase64Encoded\":true}],\"leafPartitionId\":\"42\",\"partitionRegion\":{\"stringsBase64Encoded\":true"
            + "},\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)} with {@code query}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery) with 'query'; given empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery)"})
  void testToJsonWithQuery_givenEmptyString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\"tableId\":\"42\",\"exportId\":\"\",\"subExportId\":\"42\",\"regions\":[],\"leafPartitionId\":\"42\",\"partitionRegion"
            + "\":{\"stringsBase64Encoded\":true},\"files\":[]}",
        actualToJsonResult);
  }

  /**
   * Test {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)} with {@code query}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQuerySerDe#toJson(BulkExportLeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportLeafPartitionQuery) with 'query'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkExportLeafPartitionQuerySerDe.toJson(BulkExportLeafPartitionQuery)"})
  void testToJsonWithQuery_thenReturnAString() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);
    BulkExportLeafPartitionQuerySerDe bulkExportLeafPartitionQuerySerDe = new BulkExportLeafPartitionQuerySerDe(
        schemaLoader);
    BulkExportLeafPartitionQuery query = mock(BulkExportLeafPartitionQuery.class);
    when(query.getExportId()).thenReturn("42");
    when(query.getLeafPartitionId()).thenReturn("42");
    when(query.getSubExportId()).thenReturn("42");
    when(query.getTableId()).thenReturn("42");
    when(query.getFiles()).thenReturn(new ArrayList<>());
    when(query.getRegions()).thenReturn(new ArrayList<>());
    when(query.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));

    // Act
    String actualToJsonResult = bulkExportLeafPartitionQuerySerDe.toJson(query);

    // Assert
    verify(query, atLeast(1)).getExportId();
    verify(query).getFiles();
    verify(query).getLeafPartitionId();
    verify(query).getPartitionRegion();
    verify(query).getRegions();
    verify(query).getSubExportId();
    verify(query, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
    assertEquals(
        "{\"tableId\":\"42\",\"exportId\":\"42\",\"subExportId\":\"42\",\"regions\":[],\"leafPartitionId\":\"42\",\"partitionRegion"
            + "\":{\"stringsBase64Encoded\":true},\"files\":[]}",
        actualToJsonResult);
  }
}
