package sleeper.bulkexport.core.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
import sleeper.core.range.Region;
import sleeper.core.range.RegionSerDe;
import sleeper.core.range.RegionSerDe.KeyDoesNotExistException;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;

class BulkExportLeafPartitionQueryJsonDiffblueTest {
  /**
   * Test {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}.
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(BulkExportLeafPartitionQuery, SchemaLoader)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "BulkExportLeafPartitionQueryJson BulkExportLeafPartitionQueryJson.from(BulkExportLeafPartitionQuery, SchemaLoader)"})
  void testFrom() {
    // Arrange
    BulkExportLeafPartitionQuery leafQuery = mock(BulkExportLeafPartitionQuery.class);
    when(leafQuery.getExportId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any()))
        .thenThrow(new BulkExportQueryValidationException("42", "An error occurred"));

    // Act and Assert
    assertThrows(BulkExportQueryValidationException.class,
        () -> BulkExportLeafPartitionQueryJson.from(leafQuery, schemaLoader));
    verify(leafQuery).getExportId();
    verify(leafQuery).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(BulkExportLeafPartitionQuery, SchemaLoader); given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "BulkExportLeafPartitionQueryJson BulkExportLeafPartitionQueryJson.from(BulkExportLeafPartitionQuery, SchemaLoader)"})
  void testFrom_givenArrayListAddRegionWithRangesIsArrayList() {
    // Arrange
    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery leafQuery = mock(BulkExportLeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getSubExportId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getRegions()).thenReturn(regionList);
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getExportId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    BulkExportLeafPartitionQueryJson.from(leafQuery, schemaLoader);

    // Assert
    verify(leafQuery, atLeast(1)).getExportId();
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getRegions();
    verify(leafQuery).getSubExportId();
    verify(leafQuery, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(BulkExportLeafPartitionQuery, SchemaLoader); given ArrayList() add Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "BulkExportLeafPartitionQueryJson BulkExportLeafPartitionQueryJson.from(BulkExportLeafPartitionQuery, SchemaLoader)"})
  void testFrom_givenArrayListAddRegionWithRangesIsArrayList2() {
    // Arrange
    ArrayList<Region> regionList = new ArrayList<>();
    regionList.add(new Region(new ArrayList<>()));
    regionList.add(new Region(new ArrayList<>()));
    BulkExportLeafPartitionQuery leafQuery = mock(BulkExportLeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getSubExportId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getRegions()).thenReturn(regionList);
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getExportId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    BulkExportLeafPartitionQueryJson.from(leafQuery, schemaLoader);

    // Assert
    verify(leafQuery, atLeast(1)).getExportId();
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getRegions();
    verify(leafQuery).getSubExportId();
    verify(leafQuery, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}.
   * <ul>
   *   <li>Given empty.</li>
   *   <li>When {@link SchemaLoader} {@link SchemaLoader#getSchemaByTableId(String)} return empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(BulkExportLeafPartitionQuery, SchemaLoader); given empty; when SchemaLoader getSchemaByTableId(String) return empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "BulkExportLeafPartitionQueryJson BulkExportLeafPartitionQueryJson.from(BulkExportLeafPartitionQuery, SchemaLoader)"})
  void testFrom_givenEmpty_whenSchemaLoaderGetSchemaByTableIdReturnEmpty() {
    // Arrange
    BulkExportLeafPartitionQuery leafQuery = mock(BulkExportLeafPartitionQuery.class);
    when(leafQuery.getExportId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    Optional<Schema> emptyResult = Optional.empty();
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(BulkExportQueryValidationException.class,
        () -> BulkExportLeafPartitionQueryJson.from(leafQuery, schemaLoader));
    verify(leafQuery).getExportId();
    verify(leafQuery).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link BulkExportLeafPartitionQuery} {@link BulkExportLeafPartitionQuery#getPartitionRegion()} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(BulkExportLeafPartitionQuery, SchemaLoader); given 'null'; when BulkExportLeafPartitionQuery getPartitionRegion() return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "BulkExportLeafPartitionQueryJson BulkExportLeafPartitionQueryJson.from(BulkExportLeafPartitionQuery, SchemaLoader)"})
  void testFrom_givenNull_whenBulkExportLeafPartitionQueryGetPartitionRegionReturnNull() {
    // Arrange
    BulkExportLeafPartitionQuery leafQuery = mock(BulkExportLeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getSubExportId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(null);
    when(leafQuery.getExportId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    BulkExportLeafPartitionQueryJson.from(leafQuery, schemaLoader);

    // Assert
    verify(leafQuery, atLeast(1)).getExportId();
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getRegions();
    verify(leafQuery).getSubExportId();
    verify(leafQuery, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}.
   * <ul>
   *   <li>Given {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(BulkExportLeafPartitionQuery, SchemaLoader); given Region(List) with ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "BulkExportLeafPartitionQueryJson BulkExportLeafPartitionQueryJson.from(BulkExportLeafPartitionQuery, SchemaLoader)"})
  void testFrom_givenRegionWithRangesIsArrayList() {
    // Arrange
    BulkExportLeafPartitionQuery leafQuery = mock(BulkExportLeafPartitionQuery.class);
    when(leafQuery.getLeafPartitionId()).thenReturn("42");
    when(leafQuery.getSubExportId()).thenReturn("42");
    when(leafQuery.getFiles()).thenReturn(new ArrayList<>());
    when(leafQuery.getRegions()).thenReturn(new ArrayList<>());
    when(leafQuery.getPartitionRegion()).thenReturn(new Region(new ArrayList<>()));
    when(leafQuery.getExportId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    BulkExportLeafPartitionQueryJson.from(leafQuery, schemaLoader);

    // Assert
    verify(leafQuery, atLeast(1)).getExportId();
    verify(leafQuery).getFiles();
    verify(leafQuery).getLeafPartitionId();
    verify(leafQuery).getPartitionRegion();
    verify(leafQuery).getRegions();
    verify(leafQuery).getSubExportId();
    verify(leafQuery, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }

  /**
   * Test {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}.
   * <ul>
   *   <li>Then throw {@link RegionSerDe.KeyDoesNotExistException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportLeafPartitionQueryJson#from(BulkExportLeafPartitionQuery, SchemaLoader)}
   */
  @Test
  @DisplayName("Test from(BulkExportLeafPartitionQuery, SchemaLoader); then throw KeyDoesNotExistException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "BulkExportLeafPartitionQueryJson BulkExportLeafPartitionQueryJson.from(BulkExportLeafPartitionQuery, SchemaLoader)"})
  void testFrom_thenThrowKeyDoesNotExistException() {
    // Arrange
    BulkExportLeafPartitionQuery leafQuery = mock(BulkExportLeafPartitionQuery.class);
    when(leafQuery.getSubExportId()).thenThrow(new KeyDoesNotExistException("\n"));
    when(leafQuery.getExportId()).thenReturn("42");
    when(leafQuery.getTableId()).thenReturn("42");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Optional<Schema> ofResult = Optional.of(buildResult);
    SchemaLoader schemaLoader = mock(SchemaLoader.class);
    when(schemaLoader.getSchemaByTableId(Mockito.<String>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(KeyDoesNotExistException.class, () -> BulkExportLeafPartitionQueryJson.from(leafQuery, schemaLoader));
    verify(leafQuery, atLeast(1)).getExportId();
    verify(leafQuery).getSubExportId();
    verify(leafQuery, atLeast(1)).getTableId();
    verify(schemaLoader).getSchemaByTableId(eq("42"));
  }
}
