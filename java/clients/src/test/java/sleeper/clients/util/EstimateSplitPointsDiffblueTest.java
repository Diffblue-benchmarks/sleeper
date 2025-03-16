package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.LongType;

class EstimateSplitPointsDiffblueTest {
  /**
   * Test {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int)}
   */
  @Test
  @DisplayName("Test new EstimateSplitPoints(Schema, Iterable, int); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void EstimateSplitPoints.<init>(Schema, Iterable, int)"})
  void testNewEstimateSplitPoints_givenArrayListAddFieldWithNameAndTypeIsByteArrayType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    new EstimateSplitPoints(schema, new ArrayList<>(), 10);

    // Assert
    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int, int)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int, int)}
   */
  @Test
  @DisplayName("Test new EstimateSplitPoints(Schema, Iterable, int, int); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void EstimateSplitPoints.<init>(Schema, Iterable, int, int)"})
  void testNewEstimateSplitPoints_givenArrayListAddFieldWithNameAndTypeIsByteArrayType2() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    new EstimateSplitPoints(schema, new ArrayList<>(), 10, 3);

    // Assert
    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int)}
   */
  @Test
  @DisplayName("Test new EstimateSplitPoints(Schema, Iterable, int); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void EstimateSplitPoints.<init>(Schema, Iterable, int)"})
  void testNewEstimateSplitPoints_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new EstimateSplitPoints(schema, new ArrayList<>(), 10));

    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int, int)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int, int)}
   */
  @Test
  @DisplayName("Test new EstimateSplitPoints(Schema, Iterable, int, int); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void EstimateSplitPoints.<init>(Schema, Iterable, int, int)"})
  void testNewEstimateSplitPoints_givenIllegalArgumentExceptionWithFoo2() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new EstimateSplitPoints(schema, new ArrayList<>(), 10, 3));

    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int)}.
   * <ul>
   *   <li>When {@link Schema}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int)}
   */
  @Test
  @DisplayName("Test new EstimateSplitPoints(Schema, Iterable, int); when Schema; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void EstimateSplitPoints.<init>(Schema, Iterable, int)"})
  void testNewEstimateSplitPoints_whenSchema_thenThrowIllegalArgumentException() {
    // Arrange
    Schema schema = mock(Schema.class);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new EstimateSplitPoints(schema, new ArrayList<>(), 1));

  }

  /**
   * Test {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int, int)}.
   * <ul>
   *   <li>When {@link Schema}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EstimateSplitPoints#EstimateSplitPoints(Schema, Iterable, int, int)}
   */
  @Test
  @DisplayName("Test new EstimateSplitPoints(Schema, Iterable, int, int); when Schema; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void EstimateSplitPoints.<init>(Schema, Iterable, int, int)"})
  void testNewEstimateSplitPoints_whenSchema_thenThrowIllegalArgumentException2() {
    // Arrange
    Schema schema = mock(Schema.class);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new EstimateSplitPoints(schema, new ArrayList<>(), 1, 3));

  }

  /**
   * Test {@link EstimateSplitPoints#estimate()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link EstimateSplitPoints#estimate()}
   */
  @Test
  @DisplayName("Test estimate(); given ArrayList() add Field(String, Type) with 'Name' and type is LongType (default constructor); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List EstimateSplitPoints.estimate()"})
  void testEstimate_givenArrayListAddFieldWithNameAndTypeIsLongType_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    Record resultRecord = mock(Record.class);
    when(resultRecord.get(Mockito.<String>any())).thenReturn("Get");

    ArrayList<Record> records = new ArrayList<>();
    records.add(resultRecord);

    // Act
    List<Object> actualEstimateResult = (new EstimateSplitPoints(schema, records, 10)).estimate();

    // Assert
    verify(resultRecord).get(eq("Name"));
    assertEquals(1, actualEstimateResult.size());
    assertEquals("Get", actualEstimateResult.get(0));
  }
}
