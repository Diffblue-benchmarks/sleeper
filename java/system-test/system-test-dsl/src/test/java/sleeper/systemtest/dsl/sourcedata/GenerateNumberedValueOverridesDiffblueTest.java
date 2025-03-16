package sleeper.systemtest.dsl.sourcedata;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import java.util.function.BiPredicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.schema.Field;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.Type;

class GenerateNumberedValueOverridesDiffblueTest {
  /**
   * Test {@link GenerateNumberedValueOverrides#none()}.
   * <p>
   * Method under test: {@link GenerateNumberedValueOverrides#none()}
   */
  @Test
  @DisplayName("Test none()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GenerateNumberedValueOverrides GenerateNumberedValueOverrides.none()"})
  void testNone() {
    // Arrange and Act
    GenerateNumberedValueOverrides actualNoneResult = GenerateNumberedValueOverrides.none();

    // Assert
    assertFalse(actualNoneResult.getGenerator(KeyType.ROW, new Field("Name", new ByteArrayType())).isPresent());
  }

  /**
   * Test {@link GenerateNumberedValueOverrides#overrides(GenerateNumberedValueOverrides[])}.
   * <p>
   * Method under test: {@link GenerateNumberedValueOverrides#overrides(GenerateNumberedValueOverrides[])}
   */
  @Test
  @DisplayName("Test overrides(GenerateNumberedValueOverrides[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "GenerateNumberedValueOverrides GenerateNumberedValueOverrides.overrides(GenerateNumberedValueOverrides[])"})
  void testOverrides() {
    // Arrange and Act
    GenerateNumberedValueOverrides actualOverridesResult = GenerateNumberedValueOverrides.overrides();

    // Assert
    assertFalse(actualOverridesResult.getGenerator(KeyType.ROW, new Field("Name", new ByteArrayType())).isPresent());
  }

  /**
   * Test {@link GenerateNumberedValueOverrides#overrideKeyAndFieldType(KeyType, Class, GenerateNumberedValue)}.
   * <p>
   * Method under test: {@link GenerateNumberedValueOverrides#overrideKeyAndFieldType(KeyType, Class, GenerateNumberedValue)}
   */
  @Test
  @DisplayName("Test overrideKeyAndFieldType(KeyType, Class, GenerateNumberedValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "GenerateNumberedValueOverrides GenerateNumberedValueOverrides.overrideKeyAndFieldType(KeyType, Class, GenerateNumberedValue)"})
  void testOverrideKeyAndFieldType() {
    // Arrange
    Class<Type> fieldType = Type.class;
    GenerateNumberedValue generator = mock(GenerateNumberedValue.class);

    // Act
    GenerateNumberedValueOverrides actualOverrideKeyAndFieldTypeResult = GenerateNumberedValueOverrides
        .overrideKeyAndFieldType(KeyType.ROW, fieldType, generator);
    Optional<GenerateNumberedValue> actualGenerator = actualOverrideKeyAndFieldTypeResult.getGenerator(KeyType.ROW,
        new Field("Name", new ByteArrayType()));

    // Assert
    assertTrue(actualGenerator.isPresent());
    assertSame(generator, actualGenerator.get());
  }

  /**
   * Test {@link GenerateNumberedValueOverrides#overrideKeyAndFieldType(KeyType, Class, GenerateNumberedValue)}.
   * <p>
   * Method under test: {@link GenerateNumberedValueOverrides#overrideKeyAndFieldType(KeyType, Class, GenerateNumberedValue)}
   */
  @Test
  @DisplayName("Test overrideKeyAndFieldType(KeyType, Class, GenerateNumberedValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "GenerateNumberedValueOverrides GenerateNumberedValueOverrides.overrideKeyAndFieldType(KeyType, Class, GenerateNumberedValue)"})
  void testOverrideKeyAndFieldType2() {
    // Arrange
    Class<Type> fieldType = Type.class;

    // Act
    GenerateNumberedValueOverrides actualOverrideKeyAndFieldTypeResult = GenerateNumberedValueOverrides
        .overrideKeyAndFieldType(KeyType.SORT, fieldType, mock(GenerateNumberedValue.class));

    // Assert
    assertFalse(actualOverrideKeyAndFieldTypeResult.getGenerator(KeyType.ROW, new Field("Name", new ByteArrayType()))
        .isPresent());
  }

  /**
   * Test {@link GenerateNumberedValueOverrides#overrideField(String, GenerateNumberedValue)}.
   * <p>
   * Method under test: {@link GenerateNumberedValueOverrides#overrideField(String, GenerateNumberedValue)}
   */
  @Test
  @DisplayName("Test overrideField(String, GenerateNumberedValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "GenerateNumberedValueOverrides GenerateNumberedValueOverrides.overrideField(String, GenerateNumberedValue)"})
  void testOverrideField() {
    // Arrange and Act
    GenerateNumberedValueOverrides actualOverrideFieldResult = GenerateNumberedValueOverrides
        .overrideField("Field Name", mock(GenerateNumberedValue.class));

    // Assert
    assertFalse(
        actualOverrideFieldResult.getGenerator(KeyType.ROW, new Field("Name", new ByteArrayType())).isPresent());
  }

  /**
   * Test {@link GenerateNumberedValueOverrides#overrideIf(BiPredicate, GenerateNumberedValue)}.
   * <p>
   * Method under test: {@link GenerateNumberedValueOverrides#overrideIf(BiPredicate, GenerateNumberedValue)}
   */
  @Test
  @DisplayName("Test overrideIf(BiPredicate, GenerateNumberedValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "GenerateNumberedValueOverrides GenerateNumberedValueOverrides.overrideIf(BiPredicate, GenerateNumberedValue)"})
  void testOverrideIf() {
    // Arrange
    BiPredicate<KeyType, Field> condition = mock(BiPredicate.class);
    when(condition.test(Mockito.<KeyType>any(), Mockito.<Field>any())).thenReturn(true);
    GenerateNumberedValue generator = mock(GenerateNumberedValue.class);

    // Act
    GenerateNumberedValueOverrides actualOverrideIfResult = GenerateNumberedValueOverrides.overrideIf(condition,
        generator);
    Optional<GenerateNumberedValue> actualGenerator = actualOverrideIfResult.getGenerator(KeyType.ROW,
        new Field("Name", new ByteArrayType()));

    // Assert
    verify(condition).test(eq(KeyType.ROW), isA(Field.class));
    assertTrue(actualGenerator.isPresent());
    assertSame(generator, actualGenerator.get());
  }

  /**
   * Test {@link GenerateNumberedValueOverrides#overrideIf(BiPredicate, GenerateNumberedValue)}.
   * <p>
   * Method under test: {@link GenerateNumberedValueOverrides#overrideIf(BiPredicate, GenerateNumberedValue)}
   */
  @Test
  @DisplayName("Test overrideIf(BiPredicate, GenerateNumberedValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "GenerateNumberedValueOverrides GenerateNumberedValueOverrides.overrideIf(BiPredicate, GenerateNumberedValue)"})
  void testOverrideIf2() {
    // Arrange
    BiPredicate<KeyType, Field> condition = mock(BiPredicate.class);
    when(condition.test(Mockito.<KeyType>any(), Mockito.<Field>any())).thenReturn(false);

    // Act
    GenerateNumberedValueOverrides actualOverrideIfResult = GenerateNumberedValueOverrides.overrideIf(condition,
        mock(GenerateNumberedValue.class));
    Optional<GenerateNumberedValue> actualGenerator = actualOverrideIfResult.getGenerator(KeyType.ROW,
        new Field("Name", new ByteArrayType()));

    // Assert
    verify(condition).test(eq(KeyType.ROW), isA(Field.class));
    assertFalse(actualGenerator.isPresent());
  }
}
