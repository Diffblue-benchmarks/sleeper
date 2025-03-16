package sleeper.core.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import sleeper.core.key.Key;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;

class RegionDiffblueTest {
  /**
   * Test {@link Region#Region(List)}.
   * <ul>
   *   <li>Then return Ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#Region(List)}
   */
  @Test
  @DisplayName("Test new Region(List); then return Ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Region.<init>(List)"})
  void testNewRegion_thenReturnRangesIsArrayList() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));

    // Act and Assert
    assertEquals(ranges, (new Region(ranges)).getRanges());
  }

  /**
   * Test {@link Region#Region(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Ranges Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#Region(List)}
   */
  @Test
  @DisplayName("Test new Region(List); when ArrayList(); then return Ranges Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Region.<init>(List)"})
  void testNewRegion_whenArrayList_thenReturnRangesEmpty() {
    // Arrange, Act and Assert
    assertTrue((new Region(new ArrayList<>())).getRanges().isEmpty());
  }

  /**
   * Test {@link Region#Region(Range)}.
   * <ul>
   *   <li>When {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   *   <li>Then return Ranges size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#Region(Range)}
   */
  @Test
  @DisplayName("Test new Region(Range); when Field(String, Type) with 'Name' and type is ByteArrayType (default constructor); then return Ranges size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Region.<init>(Range)"})
  void testNewRegion_whenFieldWithNameAndTypeIsByteArrayType_thenReturnRangesSizeIsOne() {
    // Arrange
    Range range = RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"));

    // Act and Assert
    List<Range> ranges = (new Region(range)).getRanges();
    assertEquals(1, ranges.size());
    assertSame(range, ranges.get(0));
  }

  /**
   * Test {@link Region#getRange(String)}.
   * <p>
   * Method under test: {@link Region#getRange(String)}
   */
  @Test
  @DisplayName("Test getRange(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Range Region.getRange(String)"})
  void testGetRange() {
    // Arrange, Act and Assert
    assertNull(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>())).getRange("Field Name"));
  }

  /**
   * Test {@link Region#getRanges()}.
   * <p>
   * Method under test: {@link Region#getRanges()}
   */
  @Test
  @DisplayName("Test getRanges()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List Region.getRanges()"})
  void testGetRanges() {
    // Arrange, Act and Assert
    assertTrue(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>())).getRanges().isEmpty());
  }

  /**
   * Test {@link Region#isKeyInRegion(Schema, Key)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#isKeyInRegion(Schema, Key)}
   */
  @Test
  @DisplayName("Test isKeyInRegion(Schema, Key); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.isKeyInRegion(Schema, Key)"})
  void testIsKeyInRegion_givenArrayListAddFieldWithNameAndTypeIsByteArrayType() {
    // Arrange
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    Key key = mock(Key.class);
    when(key.isEmpty()).thenReturn(false);
    when(key.getKeys()).thenReturn(objectList);

    // Act
    boolean actualIsKeyInRegionResult = canonicaliseRegionResult.isKeyInRegion(schema, key);

    // Assert
    verify(key).getKeys();
    verify(key).isEmpty();
    verify(schema).getRowKeyFields();
    assertTrue(actualIsKeyInRegionResult);
  }

  /**
   * Test {@link Region#isKeyInRegion(Schema, Key)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>When {@link Schema}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#isKeyInRegion(Schema, Key)}
   */
  @Test
  @DisplayName("Test isKeyInRegion(Schema, Key); given ArrayList(); when Schema; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.isKeyInRegion(Schema, Key)"})
  void testIsKeyInRegion_givenArrayList_whenSchema_thenReturnTrue() {
    // Arrange
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));
    Schema schema = mock(Schema.class);
    Key key = mock(Key.class);
    when(key.isEmpty()).thenReturn(false);
    when(key.getKeys()).thenReturn(new ArrayList<>());

    // Act
    boolean actualIsKeyInRegionResult = canonicaliseRegionResult.isKeyInRegion(schema, key);

    // Assert
    verify(key).getKeys();
    verify(key).isEmpty();
    assertTrue(actualIsKeyInRegionResult);
  }

  /**
   * Test {@link Region#isKeyInRegion(Schema, Key)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link Key} {@link Key#isEmpty()} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#isKeyInRegion(Schema, Key)}
   */
  @Test
  @DisplayName("Test isKeyInRegion(Schema, Key); given 'true'; when Key isEmpty() return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.isKeyInRegion(Schema, Key)"})
  void testIsKeyInRegion_givenTrue_whenKeyIsEmptyReturnTrue() {
    // Arrange
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));
    Schema schema = mock(Schema.class);
    Key key = mock(Key.class);
    when(key.isEmpty()).thenReturn(true);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> canonicaliseRegionResult.isKeyInRegion(schema, key));
    verify(key).isEmpty();
  }

  /**
   * Test {@link Region#isKeyInRegion(Schema, Key)}.
   * <ul>
   *   <li>When {@link Key} {@link Key#isEmpty()} throw {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#isKeyInRegion(Schema, Key)}
   */
  @Test
  @DisplayName("Test isKeyInRegion(Schema, Key); when Key isEmpty() throw IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.isKeyInRegion(Schema, Key)"})
  void testIsKeyInRegion_whenKeyIsEmptyThrowIllegalArgumentExceptionWithFoo() {
    // Arrange
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));
    Schema schema = mock(Schema.class);
    Key key = mock(Key.class);
    when(key.isEmpty()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> canonicaliseRegionResult.isKeyInRegion(schema, key));
    verify(key).isEmpty();
  }

  /**
   * Test {@link Region#isKeyInRegion(Schema, Key)}.
   * <ul>
   *   <li>When {@link Schema} {@link Schema#getRowKeyFields()} throw {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#isKeyInRegion(Schema, Key)}
   */
  @Test
  @DisplayName("Test isKeyInRegion(Schema, Key); when Schema getRowKeyFields() throw IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.isKeyInRegion(Schema, Key)"})
  void testIsKeyInRegion_whenSchemaGetRowKeyFieldsThrowIllegalArgumentExceptionWithFoo() {
    // Arrange
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new IllegalArgumentException("foo"));

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    Key key = mock(Key.class);
    when(key.isEmpty()).thenReturn(false);
    when(key.getKeys()).thenReturn(objectList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> canonicaliseRegionResult.isKeyInRegion(schema, key));
    verify(key).getKeys();
    verify(key).isEmpty();
    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link Region#doesRegionOverlap(Region)}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#doesRegionOverlap(Region)}
   */
  @Test
  @DisplayName("Test doesRegionOverlap(Region); given Field(String, Type) with 'Name' and type is ByteArrayType (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.doesRegionOverlap(Region)"})
  void testDoesRegionOverlap_givenFieldWithNameAndTypeIsByteArrayType_thenReturnTrue() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(ranges));

    // Act and Assert
    assertTrue(canonicaliseRegionResult
        .doesRegionOverlap(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()))));
  }

  /**
   * Test {@link Region#doesRegionOverlap(Region)}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#doesRegionOverlap(Region)}
   */
  @Test
  @DisplayName("Test doesRegionOverlap(Region); given Field(String, Type) with 'Name' and type is IntType (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.doesRegionOverlap(Region)"})
  void testDoesRegionOverlap_givenFieldWithNameAndTypeIsIntType_thenReturnFalse() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new IntType()), "Min", "Max")));
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(ranges));

    ArrayList<Range> ranges2 = new ArrayList<>();
    ranges2.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));

    // Act and Assert
    assertFalse(
        canonicaliseRegionResult.doesRegionOverlap(RegionCanonicaliser.canonicaliseRegion(new Region(ranges2))));
  }

  /**
   * Test {@link Region#doesRegionOverlap(Region)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#doesRegionOverlap(Region)}
   */
  @Test
  @DisplayName("Test doesRegionOverlap(Region); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.doesRegionOverlap(Region)"})
  void testDoesRegionOverlap_thenReturnTrue() {
    // Arrange
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));

    // Act and Assert
    assertTrue(canonicaliseRegionResult
        .doesRegionOverlap(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()))));
  }

  /**
   * Test {@link Region#copyWithRange(Range)}.
   * <ul>
   *   <li>Then return Ranges size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#copyWithRange(Range)}
   */
  @Test
  @DisplayName("Test copyWithRange(Range); then return Ranges size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region Region.copyWithRange(Range)"})
  void testCopyWithRange_thenReturnRangesSizeIsOne() {
    // Arrange
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));
    Range range = RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max"));

    // Act and Assert
    List<Range> ranges = canonicaliseRegionResult.copyWithRange(range).getRanges();
    assertEquals(1, ranges.size());
    assertSame(range, ranges.get(0));
  }

  /**
   * Test {@link Region#equals(Object)}, and {@link Region#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Region#equals(Object)}
   *   <li>{@link Region#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.equals(Object)", "int Region.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));
    Region canonicaliseRegionResult2 = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));

    // Act and Assert
    assertEquals(canonicaliseRegionResult, canonicaliseRegionResult2);
    int expectedHashCodeResult = canonicaliseRegionResult.hashCode();
    assertEquals(expectedHashCodeResult, canonicaliseRegionResult2.hashCode());
  }

  /**
   * Test {@link Region#equals(Object)}, and {@link Region#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Region#equals(Object)}
   *   <li>{@link Region#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.equals(Object)", "int Region.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()));

    // Act and Assert
    assertEquals(canonicaliseRegionResult, canonicaliseRegionResult);
    int expectedHashCodeResult = canonicaliseRegionResult.hashCode();
    assertEquals(expectedHashCodeResult, canonicaliseRegionResult.hashCode());
  }

  /**
   * Test {@link Region#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.equals(Object)", "int Region.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));
    Region canonicaliseRegionResult = RegionCanonicaliser.canonicaliseRegion(new Region(ranges));

    // Act and Assert
    assertNotEquals(canonicaliseRegionResult, RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>())));
  }

  /**
   * Test {@link Region#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.equals(Object)", "int Region.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>())), null);
  }

  /**
   * Test {@link Region#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Region#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Region.equals(Object)", "int Region.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>())), "Different type to Region");
  }

  /**
   * Test {@link Region#toString()}.
   * <p>
   * Method under test: {@link Region#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Region.toString()"})
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("Region{rowKeyFieldNameToRange={}}", (new Region(new ArrayList<>())).toString());
  }
}
