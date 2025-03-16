package sleeper.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;

class ReadSplitPointsDiffblueTest {
  /**
   * Test {@link ReadSplitPoints#readSplitPoints(TableProperties)} with {@code tableProperties}.
   * <p>
   * Method under test: {@link ReadSplitPoints#readSplitPoints(TableProperties)}
   */
  @Test
  @DisplayName("Test readSplitPoints(TableProperties) with 'tableProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReadSplitPoints.readSplitPoints(TableProperties)"})
  void testReadSplitPointsWithTableProperties() throws IOException {
    // Arrange and Act
    List<Object> actualReadSplitPointsResult = ReadSplitPoints
        .readSplitPoints(new TableProperties(new InstanceProperties()));

    // Assert
    assertTrue(actualReadSplitPointsResult.isEmpty());
  }

  /**
   * Test {@link ReadSplitPoints#readSplitPoints(TableProperties)} with {@code tableProperties}.
   * <p>
   * Method under test: {@link ReadSplitPoints#readSplitPoints(TableProperties)}
   */
  @Test
  @DisplayName("Test readSplitPoints(TableProperties) with 'tableProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReadSplitPoints.readSplitPoints(TableProperties)"})
  void testReadSplitPointsWithTableProperties2() throws IOException {
    // Arrange and Act
    List<Object> actualReadSplitPointsResult = ReadSplitPoints.readSplitPoints(new TableProperties(null));

    // Assert
    assertTrue(actualReadSplitPointsResult.isEmpty());
  }

  /**
   * Test {@link ReadSplitPoints#fromString(String, Schema, boolean)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link IntType} (default constructor).</li>
   *   <li>When {@code 42}.</li>
   *   <li>Then return first intValue is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadSplitPoints#fromString(String, Schema, boolean)}
   */
  @Test
  @DisplayName("Test fromString(String, Schema, boolean); given ArrayList() add IntType (default constructor); when '42'; then return first intValue is forty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReadSplitPoints.fromString(String, Schema, boolean)"})
  void testFromString_givenArrayListAddIntType_when42_thenReturnFirstIntValueIsFortyTwo() {
    // Arrange
    ArrayList<PrimitiveType> primitiveTypeList = new ArrayList<>();
    primitiveTypeList.add(new IntType());
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenReturn(primitiveTypeList);

    // Act
    List<Object> actualFromStringResult = ReadSplitPoints.fromString("42", schema, true);

    // Assert
    verify(schema).getRowKeyTypes();
    assertEquals(1, actualFromStringResult.size());
    assertEquals(42, ((Integer) actualFromStringResult.get(0)).intValue());
  }

  /**
   * Test {@link ReadSplitPoints#fromString(String, Schema, boolean)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link LongType} (default constructor).</li>
   *   <li>Then return first longValue is forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadSplitPoints#fromString(String, Schema, boolean)}
   */
  @Test
  @DisplayName("Test fromString(String, Schema, boolean); given ArrayList() add LongType (default constructor); then return first longValue is forty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReadSplitPoints.fromString(String, Schema, boolean)"})
  void testFromString_givenArrayListAddLongType_thenReturnFirstLongValueIsFortyTwo() {
    // Arrange
    ArrayList<PrimitiveType> primitiveTypeList = new ArrayList<>();
    primitiveTypeList.add(new LongType());
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenReturn(primitiveTypeList);

    // Act
    List<Object> actualFromStringResult = ReadSplitPoints.fromString("42", schema, true);

    // Assert
    verify(schema).getRowKeyTypes();
    assertEquals(1, actualFromStringResult.size());
    assertEquals(42L, ((Long) actualFromStringResult.get(0)).longValue());
  }

  /**
   * Test {@link ReadSplitPoints#fromString(String, Schema, boolean)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link PrimitiveType} (default constructor).</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadSplitPoints#fromString(String, Schema, boolean)}
   */
  @Test
  @DisplayName("Test fromString(String, Schema, boolean); given ArrayList() add PrimitiveType (default constructor); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReadSplitPoints.fromString(String, Schema, boolean)"})
  void testFromString_givenArrayListAddPrimitiveType_thenThrowRuntimeException() {
    // Arrange
    ArrayList<PrimitiveType> primitiveTypeList = new ArrayList<>();
    primitiveTypeList.add(new PrimitiveType());
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenReturn(primitiveTypeList);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> ReadSplitPoints.fromString("Split Points", schema, true));
    verify(schema).getRowKeyTypes();
  }

  /**
   * Test {@link ReadSplitPoints#fromString(String, Schema, boolean)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link PrimitiveType} (default constructor).</li>
   *   <li>When empty string.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadSplitPoints#fromString(String, Schema, boolean)}
   */
  @Test
  @DisplayName("Test fromString(String, Schema, boolean); given ArrayList() add PrimitiveType (default constructor); when empty string; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReadSplitPoints.fromString(String, Schema, boolean)"})
  void testFromString_givenArrayListAddPrimitiveType_whenEmptyString_thenReturnEmpty() {
    // Arrange
    ArrayList<PrimitiveType> primitiveTypeList = new ArrayList<>();
    primitiveTypeList.add(new PrimitiveType());
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenReturn(primitiveTypeList);

    // Act
    List<Object> actualFromStringResult = ReadSplitPoints.fromString("", schema, true);

    // Assert
    verify(schema).getRowKeyTypes();
    assertTrue(actualFromStringResult.isEmpty());
  }

  /**
   * Test {@link ReadSplitPoints#fromString(String, Schema, boolean)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link StringType} (default constructor).</li>
   *   <li>Then return first is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadSplitPoints#fromString(String, Schema, boolean)}
   */
  @Test
  @DisplayName("Test fromString(String, Schema, boolean); given ArrayList() add StringType (default constructor); then return first is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReadSplitPoints.fromString(String, Schema, boolean)"})
  void testFromString_givenArrayListAddStringType_thenReturnFirstIsAString() {
    // Arrange
    ArrayList<PrimitiveType> primitiveTypeList = new ArrayList<>();
    primitiveTypeList.add(new StringType());
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenReturn(primitiveTypeList);

    // Act
    List<Object> actualFromStringResult = ReadSplitPoints.fromString("Split Points", schema, true);

    // Assert
    verify(schema).getRowKeyTypes();
    assertEquals(1, actualFromStringResult.size());
    assertEquals("J�b��\"��", actualFromStringResult.get(0));
  }

  /**
   * Test {@link ReadSplitPoints#fromString(String, Schema, boolean)}.
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadSplitPoints#fromString(String, Schema, boolean)}
   */
  @Test
  @DisplayName("Test fromString(String, Schema, boolean); given RuntimeException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReadSplitPoints.fromString(String, Schema, boolean)"})
  void testFromString_givenRuntimeExceptionWithFoo() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> ReadSplitPoints.fromString("Split Points", schema, true));
    verify(schema).getRowKeyTypes();
  }

  /**
   * Test {@link ReadSplitPoints#fromString(String, Schema, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return first is {@code Split Points}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadSplitPoints#fromString(String, Schema, boolean)}
   */
  @Test
  @DisplayName("Test fromString(String, Schema, boolean); when 'false'; then return first is 'Split Points'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReadSplitPoints.fromString(String, Schema, boolean)"})
  void testFromString_whenFalse_thenReturnFirstIsSplitPoints() {
    // Arrange
    ArrayList<PrimitiveType> primitiveTypeList = new ArrayList<>();
    primitiveTypeList.add(new StringType());
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenReturn(primitiveTypeList);

    // Act
    List<Object> actualFromStringResult = ReadSplitPoints.fromString("Split Points", schema, false);

    // Assert
    verify(schema).getRowKeyTypes();
    assertEquals(1, actualFromStringResult.size());
    assertEquals("Split Points", actualFromStringResult.get(0));
  }
}
