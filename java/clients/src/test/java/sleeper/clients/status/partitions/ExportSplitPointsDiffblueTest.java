package sleeper.clients.status.partitions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;

class ExportSplitPointsDiffblueTest {
  /**
   * Test {@link ExportSplitPoints#getSplitPoints()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportSplitPoints#getSplitPoints()}
   */
  @Test
  @DisplayName("Test getSplitPoints(); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportSplitPoints.getSplitPoints()"})
  void testGetSplitPoints_givenArrayListAddFieldWithNameAndTypeIsByteArrayType() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<Object> actualSplitPoints = (new ExportSplitPoints(stateStore, schema)).getSplitPoints();

    // Assert
    verify(stateStore).getLeafPartitions();
    assertTrue(actualSplitPoints.isEmpty());
  }

  /**
   * Test {@link ExportSplitPoints#getSplitPoints()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportSplitPoints#getSplitPoints()}
   */
  @Test
  @DisplayName("Test getSplitPoints(); given ArrayList() add Field(String, Type) with 'Name' and type is IntType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportSplitPoints.getSplitPoints()"})
  void testGetSplitPoints_givenArrayListAddFieldWithNameAndTypeIsIntType() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<Object> actualSplitPoints = (new ExportSplitPoints(stateStore, schema)).getSplitPoints();

    // Assert
    verify(stateStore).getLeafPartitions();
    assertTrue(actualSplitPoints.isEmpty());
  }

  /**
   * Test {@link ExportSplitPoints#getSplitPoints()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportSplitPoints#getSplitPoints()}
   */
  @Test
  @DisplayName("Test getSplitPoints(); given ArrayList() add Field(String, Type) with 'Name' and type is LongType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportSplitPoints.getSplitPoints()"})
  void testGetSplitPoints_givenArrayListAddFieldWithNameAndTypeIsLongType() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<Object> actualSplitPoints = (new ExportSplitPoints(stateStore, schema)).getSplitPoints();

    // Assert
    verify(stateStore).getLeafPartitions();
    assertTrue(actualSplitPoints.isEmpty());
  }

  /**
   * Test {@link ExportSplitPoints#getSplitPoints()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportSplitPoints#getSplitPoints()}
   */
  @Test
  @DisplayName("Test getSplitPoints(); given ArrayList() add Field(String, Type) with 'Name' and type is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportSplitPoints.getSplitPoints()"})
  void testGetSplitPoints_givenArrayListAddFieldWithNameAndTypeIsPrimitiveType() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new PrimitiveType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<Object> actualSplitPoints = (new ExportSplitPoints(stateStore, schema)).getSplitPoints();

    // Assert
    verify(stateStore).getLeafPartitions();
    assertTrue(actualSplitPoints.isEmpty());
  }

  /**
   * Test {@link ExportSplitPoints#getSplitPoints()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportSplitPoints#getSplitPoints()}
   */
  @Test
  @DisplayName("Test getSplitPoints(); given ArrayList() add Field(String, Type) with 'Name' and type is StringType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportSplitPoints.getSplitPoints()"})
  void testGetSplitPoints_givenArrayListAddFieldWithNameAndTypeIsStringType() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<Object> actualSplitPoints = (new ExportSplitPoints(stateStore, schema)).getSplitPoints();

    // Assert
    verify(stateStore).getLeafPartitions();
    assertTrue(actualSplitPoints.isEmpty());
  }

  /**
   * Test {@link ExportSplitPoints#getSplitPoints()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportSplitPoints#getSplitPoints()}
   */
  @Test
  @DisplayName("Test getSplitPoints(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportSplitPoints.getSplitPoints()"})
  void testGetSplitPoints_thenThrowIllegalArgumentException() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenThrow(new IllegalArgumentException("foo"));

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new ExportSplitPoints(stateStore, schema)).getSplitPoints());
    verify(stateStore).getLeafPartitions();
  }
}
