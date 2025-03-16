package sleeper.core.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;

class SchemaDiffblueTest {
  /**
   * Test {@link Schema#builder()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Schema#builder()}
   *   <li>{@link Schema#rowKeyFields(List)}
   *   <li>{@link Schema#sortKeyFields(List)}
   *   <li>{@link Schema#valueFields(List)}
   * </ul>
   */
  @Test
  @DisplayName("Test builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Schema Builder.build()", "Builder Builder.rowKeyFields(List)",
      "Builder Builder.sortKeyFields(List)", "Builder Builder.valueFields(List)"})
  void testBuilder() {
    // Arrange and Act
    Builder actualBuilderResult = Schema.builder();
    Builder actualRowKeyFieldsResult = actualBuilderResult.rowKeyFields(new ArrayList<>());
    Builder actualSortKeyFieldsResult = actualRowKeyFieldsResult.sortKeyFields(new ArrayList<>());

    // Assert
    assertSame(actualSortKeyFieldsResult, actualSortKeyFieldsResult.valueFields(new ArrayList<>()));
  }

  /**
   * Test Builder {@link Builder#rowKeyFields(Field[])} with {@code Field[]}.
   * <p>
   * Method under test: {@link Builder#rowKeyFields(Field[])}
   */
  @Test
  @DisplayName("Test Builder rowKeyFields(Field[]) with 'Field[]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.rowKeyFields(Field[])"})
  void testBuilderRowKeyFieldsWithField() {
    // Arrange
    Builder builderResult = Schema.builder();

    // Act
    Builder actualRowKeyFieldsResult = builderResult.rowKeyFields(new Field("Name", new ByteArrayType()));

    // Assert
    Schema buildResult = builderResult.build();
    assertEquals(1, buildResult.getAllFieldNames().size());
    assertEquals(1, buildResult.getAllFields().size());
    assertEquals(1, buildResult.getRowKeyFieldNames().size());
    assertEquals(1, buildResult.getRowKeyFields().size());
    assertEquals(1, buildResult.getRowKeyTypes().size());
    assertSame(builderResult, actualRowKeyFieldsResult);
  }

  /**
   * Test Builder {@link Builder#sortKeyFields(Field[])} with {@code Field[]}.
   * <p>
   * Method under test: {@link Builder#sortKeyFields(Field[])}
   */
  @Test
  @DisplayName("Test Builder sortKeyFields(Field[]) with 'Field[]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.sortKeyFields(Field[])"})
  void testBuilderSortKeyFieldsWithField() {
    // Arrange
    Builder builderResult = Schema.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.sortKeyFields(new Field("Name", new ByteArrayType())));
  }

  /**
   * Test Builder {@link Builder#valueFields(Field[])} with {@code Field[]}.
   * <p>
   * Method under test: {@link Builder#valueFields(Field[])}
   */
  @Test
  @DisplayName("Test Builder valueFields(Field[]) with 'Field[]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.valueFields(Field[])"})
  void testBuilderValueFieldsWithField() {
    // Arrange
    Builder builderResult = Schema.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.valueFields(new Field("Name", new ByteArrayType())));
  }
}
