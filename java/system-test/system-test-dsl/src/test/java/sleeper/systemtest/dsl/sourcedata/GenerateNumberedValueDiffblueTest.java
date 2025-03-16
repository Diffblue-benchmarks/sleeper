package sleeper.systemtest.dsl.sourcedata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Field;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.StringType;

class GenerateNumberedValueDiffblueTest {
  /**
   * Test {@link GenerateNumberedValue#forField(KeyType, Field)}.
   * <ul>
   *   <li>Then return generateValue {@link Long#MAX_VALUE} is {@code row-9223372036854775807}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedValue#forField(KeyType, Field)}
   */
  @Test
  @DisplayName("Test forField(KeyType, Field); then return generateValue MAX_VALUE is 'row-9223372036854775807'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GenerateNumberedValue GenerateNumberedValue.forField(KeyType, Field)"})
  void testForField_thenReturnGenerateValueMax_valueIsRow9223372036854775807() {
    // Arrange, Act and Assert
    assertEquals("row-9223372036854775807",
        GenerateNumberedValue.forField(KeyType.ROW, new Field("Name", new StringType())).generateValue(Long.MAX_VALUE));
  }

  /**
   * Test {@link GenerateNumberedValue#forField(KeyType, Field)}.
   * <ul>
   *   <li>Then return generateValue one intValue is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedValue#forField(KeyType, Field)}
   */
  @Test
  @DisplayName("Test forField(KeyType, Field); then return generateValue one intValue is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GenerateNumberedValue GenerateNumberedValue.forField(KeyType, Field)"})
  void testForField_thenReturnGenerateValueOneIntValueIsOne() {
    // Arrange, Act and Assert
    assertEquals(1,
        ((Integer) GenerateNumberedValue.forField(KeyType.ROW, new Field("Name", new IntType())).generateValue(1L))
            .intValue());
  }

  /**
   * Test {@link GenerateNumberedValue#forField(KeyType, Field)}.
   * <ul>
   *   <li>Then return generateValue one is {@code row-0000000000000000001}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedValue#forField(KeyType, Field)}
   */
  @Test
  @DisplayName("Test forField(KeyType, Field); then return generateValue one is 'row-0000000000000000001'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GenerateNumberedValue GenerateNumberedValue.forField(KeyType, Field)"})
  void testForField_thenReturnGenerateValueOneIsRow0000000000000000001() {
    // Arrange, Act and Assert
    assertEquals("row-0000000000000000001",
        GenerateNumberedValue.forField(KeyType.ROW, new Field("Name", new StringType())).generateValue(1L));
  }

  /**
   * Test {@link GenerateNumberedValue#forField(KeyType, Field)}.
   * <ul>
   *   <li>Then return generateValue one longValue is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedValue#forField(KeyType, Field)}
   */
  @Test
  @DisplayName("Test forField(KeyType, Field); then return generateValue one longValue is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GenerateNumberedValue GenerateNumberedValue.forField(KeyType, Field)"})
  void testForField_thenReturnGenerateValueOneLongValueIsOne() {
    // Arrange, Act and Assert
    assertEquals(1L,
        ((Long) GenerateNumberedValue.forField(KeyType.ROW, new Field("Name", new LongType())).generateValue(1L))
            .longValue());
  }

  /**
   * Test {@link GenerateNumberedValue#numberStringAndZeroPadTo(int, long)} with {@code size}, {@code number}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code 1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedValue#numberStringAndZeroPadTo(int, long)}
   */
  @Test
  @DisplayName("Test numberStringAndZeroPadTo(int, long) with 'size', 'number'; when one; then return '1'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String GenerateNumberedValue.numberStringAndZeroPadTo(int, long)"})
  void testNumberStringAndZeroPadToWithSizeNumber_whenOne_thenReturn1() {
    // Arrange, Act and Assert
    assertEquals("1", GenerateNumberedValue.numberStringAndZeroPadTo(1, 1L));
  }

  /**
   * Test {@link GenerateNumberedValue#numberStringAndZeroPadTo(int, long)} with {@code size}, {@code number}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code 001}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedValue#numberStringAndZeroPadTo(int, long)}
   */
  @Test
  @DisplayName("Test numberStringAndZeroPadTo(int, long) with 'size', 'number'; when three; then return '001'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String GenerateNumberedValue.numberStringAndZeroPadTo(int, long)"})
  void testNumberStringAndZeroPadToWithSizeNumber_whenThree_thenReturn001() {
    // Arrange, Act and Assert
    assertEquals("001", GenerateNumberedValue.numberStringAndZeroPadTo(3, 1L));
  }

  /**
   * Test {@link GenerateNumberedValue#numberStringAndZeroPadTo(int)} with {@code size}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return generateValue one is {@code 1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedValue#numberStringAndZeroPadTo(int)}
   */
  @Test
  @DisplayName("Test numberStringAndZeroPadTo(int) with 'size'; when one; then return generateValue one is '1'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GenerateNumberedValue GenerateNumberedValue.numberStringAndZeroPadTo(int)"})
  void testNumberStringAndZeroPadToWithSize_whenOne_thenReturnGenerateValueOneIs1() {
    // Arrange, Act and Assert
    assertEquals("1", GenerateNumberedValue.numberStringAndZeroPadTo(1).generateValue(1L));
  }

  /**
   * Test {@link GenerateNumberedValue#numberStringAndZeroPadTo(int)} with {@code size}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return generateValue one is {@code 001}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedValue#numberStringAndZeroPadTo(int)}
   */
  @Test
  @DisplayName("Test numberStringAndZeroPadTo(int) with 'size'; when three; then return generateValue one is '001'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GenerateNumberedValue GenerateNumberedValue.numberStringAndZeroPadTo(int)"})
  void testNumberStringAndZeroPadToWithSize_whenThree_thenReturnGenerateValueOneIs001() {
    // Arrange, Act and Assert
    assertEquals("001", GenerateNumberedValue.numberStringAndZeroPadTo(3).generateValue(1L));
  }

  /**
   * Test {@link GenerateNumberedValue#addPrefix(String)}.
   * <p>
   * Method under test: {@link GenerateNumberedValue#addPrefix(String)}
   */
  @Test
  @DisplayName("Test addPrefix(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UnaryOperator GenerateNumberedValue.addPrefix(String)"})
  void testAddPrefix() {
    // Arrange and Act
    UnaryOperator<Object> actualAddPrefixResult = GenerateNumberedValue.addPrefix("Prefix");

    // Assert
    assertEquals("Prefix42", actualAddPrefixResult.apply("42"));
  }

  /**
   * Test {@link GenerateNumberedValue#applyFormat(String)}.
   * <p>
   * Method under test: {@link GenerateNumberedValue#applyFormat(String)}
   */
  @Test
  @DisplayName("Test applyFormat(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UnaryOperator GenerateNumberedValue.applyFormat(String)"})
  void testApplyFormat() {
    // Arrange and Act
    UnaryOperator<Object> actualApplyFormatResult = GenerateNumberedValue.applyFormat("Format");

    // Assert
    assertEquals("Format", actualApplyFormatResult.apply("42"));
  }
}
