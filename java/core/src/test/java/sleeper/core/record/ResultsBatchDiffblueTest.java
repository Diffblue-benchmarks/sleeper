package sleeper.core.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;

class ResultsBatchDiffblueTest {
  /**
   * Test {@link ResultsBatch#equals(Object)}, and {@link ResultsBatch#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ResultsBatch#equals(Object)}
   *   <li>{@link ResultsBatch#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ResultsBatch.equals(Object)", "int ResultsBatch.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    ResultsBatch resultsBatch = new ResultsBatch("42", schema, new ArrayList<>());

    ArrayList<Field> rowKeyFields2 = new ArrayList<>();
    rowKeyFields2.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult2 = Schema.builder().rowKeyFields(rowKeyFields2);
    Builder sortKeyFieldsResult2 = rowKeyFieldsResult2.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult2.valueFields(new ArrayList<>()).build();
    ResultsBatch resultsBatch2 = new ResultsBatch("42", schema2, new ArrayList<>());

    // Act and Assert
    assertEquals(resultsBatch, resultsBatch2);
    int expectedHashCodeResult = resultsBatch.hashCode();
    assertEquals(expectedHashCodeResult, resultsBatch2.hashCode());
  }

  /**
   * Test {@link ResultsBatch#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResultsBatch#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ResultsBatch.equals(Object)", "int ResultsBatch.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    ResultsBatch resultsBatch = new ResultsBatch("42", schema, new ArrayList<>());

    ArrayList<Field> rowKeyFields2 = new ArrayList<>();
    rowKeyFields2.add(new Field("java.lang.String", new ByteArrayType()));
    Builder rowKeyFieldsResult2 = Schema.builder().rowKeyFields(rowKeyFields2);
    Builder sortKeyFieldsResult2 = rowKeyFieldsResult2.sortKeyFields(new ArrayList<>());
    Schema schema2 = sortKeyFieldsResult2.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(resultsBatch, new ResultsBatch("42", schema2, new ArrayList<>()));
  }

  /**
   * Test {@link ResultsBatch#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResultsBatch#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ResultsBatch.equals(Object)", "int ResultsBatch.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    ResultsBatch resultsBatch = new ResultsBatch("42", schema, new ArrayList<>());

    // Act and Assert
    assertNotEquals(resultsBatch, new ByteArrayType());
  }

  /**
   * Test {@link ResultsBatch#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResultsBatch#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ResultsBatch.equals(Object)", "int ResultsBatch.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(new ResultsBatch("42", schema, new ArrayList<>()), null);
  }
}
