package sleeper.core.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Field;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.StringType;
import sleeper.core.schema.type.Type;

class RegionCanonicaliserDiffblueTest {
  /**
   * Test {@link RegionCanonicaliser#canonicaliseRegion(Region)}.
   * <ul>
   *   <li>Then Ranges first FieldType return {@link ByteArrayType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionCanonicaliser#canonicaliseRegion(Region)}
   */
  @Test
  @DisplayName("Test canonicaliseRegion(Region); then Ranges first FieldType return ByteArrayType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionCanonicaliser.canonicaliseRegion(Region)"})
  void testCanonicaliseRegion_thenRangesFirstFieldTypeReturnByteArrayType() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    ByteArrayType type = new ByteArrayType();
    Field field = new Field("Name", type);

    ranges.add(new Range(field, "Min", true, null, true));

    // Act and Assert
    List<Range> ranges2 = RegionCanonicaliser.canonicaliseRegion(new Region(ranges)).getRanges();
    assertEquals(1, ranges2.size());
    Range getResult = ranges2.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof ByteArrayType);
    assertNull(getResult.getMax());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link RegionCanonicaliser#canonicaliseRegion(Region)}.
   * <ul>
   *   <li>Then Ranges first FieldType return {@link StringType}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionCanonicaliser#canonicaliseRegion(Region)}
   */
  @Test
  @DisplayName("Test canonicaliseRegion(Region); then Ranges first FieldType return StringType")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionCanonicaliser.canonicaliseRegion(Region)"})
  void testCanonicaliseRegion_thenRangesFirstFieldTypeReturnStringType() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    StringType type = new StringType();
    Field field = new Field("Name", type);

    ranges.add(new Range(field, "Min", true, "Max", true));

    // Act and Assert
    List<Range> ranges2 = RegionCanonicaliser.canonicaliseRegion(new Region(ranges)).getRanges();
    assertEquals(1, ranges2.size());
    Range getResult = ranges2.get(0);
    Type fieldType = getResult.getFieldType();
    assertTrue(fieldType instanceof StringType);
    assertEquals("Max\u0000", getResult.getMax());
    Field field2 = getResult.getField();
    assertSame(field, field2);
    assertSame(type, fieldType);
    assertSame(type, field2.getType());
  }

  /**
   * Test {@link RegionCanonicaliser#canonicaliseRegion(Region)}.
   * <ul>
   *   <li>Then return Ranges is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionCanonicaliser#canonicaliseRegion(Region)}
   */
  @Test
  @DisplayName("Test canonicaliseRegion(Region); then return Ranges is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionCanonicaliser.canonicaliseRegion(Region)"})
  void testCanonicaliseRegion_thenReturnRangesIsArrayList() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));

    // Act and Assert
    assertEquals(ranges, RegionCanonicaliser.canonicaliseRegion(new Region(ranges)).getRanges());
  }

  /**
   * Test {@link RegionCanonicaliser#canonicaliseRegion(Region)}.
   * <ul>
   *   <li>When {@link Region#Region(List)} with ranges is {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Ranges Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionCanonicaliser#canonicaliseRegion(Region)}
   */
  @Test
  @DisplayName("Test canonicaliseRegion(Region); when Region(List) with ranges is ArrayList(); then return Ranges Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region RegionCanonicaliser.canonicaliseRegion(Region)"})
  void testCanonicaliseRegion_whenRegionWithRangesIsArrayList_thenReturnRangesEmpty() {
    // Arrange, Act and Assert
    assertTrue(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>())).getRanges().isEmpty());
  }

  /**
   * Test {@link RegionCanonicaliser#isRegionInCanonicalForm(Region)}.
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionCanonicaliser#isRegionInCanonicalForm(Region)}
   */
  @Test
  @DisplayName("Test isRegionInCanonicalForm(Region); given Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean RegionCanonicaliser.isRegionInCanonicalForm(Region)"})
  void testIsRegionInCanonicalForm_givenFieldWithNameAndTypeIsByteArrayType() {
    // Arrange
    ArrayList<Range> ranges = new ArrayList<>();
    ranges.add(RangeCanonicaliser.canonicaliseRange(new Range(new Field("Name", new ByteArrayType()), "Min", "Max")));

    // Act and Assert
    assertTrue(RegionCanonicaliser.isRegionInCanonicalForm(RegionCanonicaliser.canonicaliseRegion(new Region(ranges))));
  }

  /**
   * Test {@link RegionCanonicaliser#isRegionInCanonicalForm(Region)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegionCanonicaliser#isRegionInCanonicalForm(Region)}
   */
  @Test
  @DisplayName("Test isRegionInCanonicalForm(Region); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean RegionCanonicaliser.isRegionInCanonicalForm(Region)"})
  void testIsRegionInCanonicalForm_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(RegionCanonicaliser
        .isRegionInCanonicalForm(RegionCanonicaliser.canonicaliseRegion(new Region(new ArrayList<>()))));
  }
}
