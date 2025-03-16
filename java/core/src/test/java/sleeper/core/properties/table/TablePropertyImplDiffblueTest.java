package sleeper.core.properties.table;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperProperty;
import sleeper.core.properties.table.TablePropertyImpl.Builder;
import sleeper.core.properties.testutils.DummySleeperProperty;
import sleeper.core.properties.testutils.DummyTableProperty;

class TablePropertyImplDiffblueTest {
  /**
   * Test Builder {@link Builder#computeValue(TablePropertyComputeValue)}.
   * <ul>
   *   <li>Given builder.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#computeValue(TablePropertyComputeValue)}
   */
  @Test
  @DisplayName("Test Builder computeValue(TablePropertyComputeValue); given builder; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.computeValue(TablePropertyComputeValue)"})
  void testBuilderComputeValue_givenBuilder_thenReturnBuilder() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.computeValue(mock(TablePropertyComputeValue.class)));
  }

  /**
   * Test Builder {@link Builder#computeValue(TablePropertyComputeValue)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#computeValue(TablePropertyComputeValue)}
   */
  @Test
  @DisplayName("Test Builder computeValue(TablePropertyComputeValue); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.computeValue(TablePropertyComputeValue)"})
  void testBuilderComputeValue_thenThrowIllegalArgumentException() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    builderResult.defaultValue("42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> builderResult.computeValue(mock(TablePropertyComputeValue.class)));
  }

  /**
   * Test Builder {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}.
   * <p>
   * Method under test: {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}
   */
  @Test
  @DisplayName("Test Builder defaultPropertyWithBehaviour(SleeperProperty, Function)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultPropertyWithBehaviour(SleeperProperty, Function)"})
  void testBuilderDefaultPropertyWithBehaviour() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    Function<SleeperProperty, TablePropertyComputeValue> behaviour = mock(Function.class);
    when(behaviour.apply(Mockito.<SleeperProperty>any())).thenReturn(mock(TablePropertyComputeValue.class));

    // Act
    Builder actualDefaultPropertyWithBehaviourResult = builderResult
        .defaultPropertyWithBehaviour(TableProperty.ADD_TRANSACTION_FIRST_RETRY_WAIT_CEILING_MS, behaviour);

    // Assert
    verify(behaviour).apply(isA(SleeperProperty.class));
    assertSame(builderResult, actualDefaultPropertyWithBehaviourResult);
  }

  /**
   * Test Builder {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}.
   * <p>
   * Method under test: {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}
   */
  @Test
  @DisplayName("Test Builder defaultPropertyWithBehaviour(SleeperProperty, Function)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultPropertyWithBehaviour(SleeperProperty, Function)"})
  void testBuilderDefaultPropertyWithBehaviour2() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    builderResult.computeValue(mock(TablePropertyComputeValue.class));
    DummyTableProperty dummyTableProperty = mock(DummyTableProperty.class);
    when(dummyTableProperty.getDefaultValue()).thenReturn("42");
    Function<SleeperProperty, TablePropertyComputeValue> behaviour = mock(Function.class);
    when(behaviour.apply(Mockito.<SleeperProperty>any())).thenReturn(mock(TablePropertyComputeValue.class));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> builderResult.defaultPropertyWithBehaviour(dummyTableProperty, behaviour));
    verify(behaviour).apply(isA(SleeperProperty.class));
    verify(dummyTableProperty).getDefaultValue();
  }

  /**
   * Test Builder {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}.
   * <p>
   * Method under test: {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}
   */
  @Test
  @DisplayName("Test Builder defaultPropertyWithBehaviour(SleeperProperty, Function)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultPropertyWithBehaviour(SleeperProperty, Function)"})
  void testBuilderDefaultPropertyWithBehaviour3() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    DummyTableProperty dummyTableProperty = mock(DummyTableProperty.class);
    when(dummyTableProperty.getDefaultValue()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> builderResult.defaultPropertyWithBehaviour(dummyTableProperty, mock(Function.class)));
    verify(dummyTableProperty).getDefaultValue();
  }

  /**
   * Test Builder {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}.
   * <ul>
   *   <li>Given builder computeValue {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}
   */
  @Test
  @DisplayName("Test Builder defaultPropertyWithBehaviour(SleeperProperty, Function); given builder computeValue 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultPropertyWithBehaviour(SleeperProperty, Function)"})
  void testBuilderDefaultPropertyWithBehaviour_givenBuilderComputeValueNull() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    builderResult.computeValue(null);
    Function<SleeperProperty, TablePropertyComputeValue> behaviour = mock(Function.class);
    when(behaviour.apply(Mockito.<SleeperProperty>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> builderResult
        .defaultPropertyWithBehaviour(TableProperty.ADD_TRANSACTION_FIRST_RETRY_WAIT_CEILING_MS, behaviour));
    verify(behaviour).apply(isA(SleeperProperty.class));
  }

  /**
   * Test Builder {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}.
   * <ul>
   *   <li>Then calls {@link SleeperProperty#getValidationPredicate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}
   */
  @Test
  @DisplayName("Test Builder defaultPropertyWithBehaviour(SleeperProperty, Function); then calls getValidationPredicate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultPropertyWithBehaviour(SleeperProperty, Function)"})
  void testBuilderDefaultPropertyWithBehaviour_thenCallsGetValidationPredicate() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    DummyTableProperty dummyTableProperty = mock(DummyTableProperty.class);
    when(dummyTableProperty.getValidationPredicate()).thenReturn(mock(Predicate.class));
    when(dummyTableProperty.getDefaultValue()).thenReturn("42");
    Function<SleeperProperty, TablePropertyComputeValue> behaviour = mock(Function.class);
    when(behaviour.apply(Mockito.<SleeperProperty>any())).thenReturn(mock(TablePropertyComputeValue.class));

    // Act
    Builder actualDefaultPropertyWithBehaviourResult = builderResult.defaultPropertyWithBehaviour(dummyTableProperty,
        behaviour);

    // Assert
    verify(behaviour).apply(isA(SleeperProperty.class));
    verify(dummyTableProperty).getValidationPredicate();
    verify(dummyTableProperty).getDefaultValue();
    assertSame(builderResult, actualDefaultPropertyWithBehaviourResult);
  }

  /**
   * Test Builder {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}.
   * <ul>
   *   <li>When {@link DummySleeperProperty} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultPropertyWithBehaviour(SleeperProperty, Function)}
   */
  @Test
  @DisplayName("Test Builder defaultPropertyWithBehaviour(SleeperProperty, Function); when DummySleeperProperty (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultPropertyWithBehaviour(SleeperProperty, Function)"})
  void testBuilderDefaultPropertyWithBehaviour_whenDummySleeperProperty() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    DummySleeperProperty dummySleeperProperty = new DummySleeperProperty();
    Function<SleeperProperty, TablePropertyComputeValue> behaviour = mock(Function.class);
    when(behaviour.apply(Mockito.<SleeperProperty>any())).thenReturn(mock(TablePropertyComputeValue.class));

    // Act
    Builder actualDefaultPropertyWithBehaviourResult = builderResult.defaultPropertyWithBehaviour(dummySleeperProperty,
        behaviour);

    // Assert
    verify(behaviour).apply(isA(SleeperProperty.class));
    assertSame(builderResult, actualDefaultPropertyWithBehaviourResult);
  }

  /**
   * Test Builder {@link Builder#defaultProperty(SleeperProperty)}.
   * <ul>
   *   <li>Given builder computeValue {@link TablePropertyComputeValue}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultProperty(SleeperProperty)}
   */
  @Test
  @DisplayName("Test Builder defaultProperty(SleeperProperty); given builder computeValue TablePropertyComputeValue")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultProperty(SleeperProperty)"})
  void testBuilderDefaultProperty_givenBuilderComputeValueTablePropertyComputeValue() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    builderResult.computeValue(mock(TablePropertyComputeValue.class));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> builderResult.defaultProperty(TableProperty.ADD_TRANSACTION_FIRST_RETRY_WAIT_CEILING_MS));
  }

  /**
   * Test Builder {@link Builder#defaultProperty(SleeperProperty)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultProperty(SleeperProperty)}
   */
  @Test
  @DisplayName("Test Builder defaultProperty(SleeperProperty); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultProperty(SleeperProperty)"})
  void testBuilderDefaultProperty_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    DummyTableProperty defaultProperty = mock(DummyTableProperty.class);
    when(defaultProperty.getDefaultValue()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> builderResult.defaultProperty(defaultProperty));
    verify(defaultProperty).getDefaultValue();
  }

  /**
   * Test Builder {@link Builder#defaultProperty(SleeperProperty)}.
   * <ul>
   *   <li>Given {@link Predicate}.</li>
   *   <li>Then calls {@link SleeperProperty#getValidationPredicate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultProperty(SleeperProperty)}
   */
  @Test
  @DisplayName("Test Builder defaultProperty(SleeperProperty); given Predicate; then calls getValidationPredicate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultProperty(SleeperProperty)"})
  void testBuilderDefaultProperty_givenPredicate_thenCallsGetValidationPredicate() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    DummyTableProperty defaultProperty = mock(DummyTableProperty.class);
    when(defaultProperty.getValidationPredicate()).thenReturn(mock(Predicate.class));
    when(defaultProperty.getDefaultValue()).thenReturn("42");

    // Act
    Builder actualDefaultPropertyResult = builderResult.defaultProperty(defaultProperty);

    // Assert
    verify(defaultProperty).getValidationPredicate();
    verify(defaultProperty).getDefaultValue();
    assertSame(builderResult, actualDefaultPropertyResult);
  }

  /**
   * Test Builder {@link Builder#defaultProperty(SleeperProperty)}.
   * <ul>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultProperty(SleeperProperty)}
   */
  @Test
  @DisplayName("Test Builder defaultProperty(SleeperProperty); then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultProperty(SleeperProperty)"})
  void testBuilderDefaultProperty_thenReturnBuilder() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.defaultProperty(TableProperty.ADD_TRANSACTION_FIRST_RETRY_WAIT_CEILING_MS));
  }

  /**
   * Test Builder {@link Builder#defaultValue(String)}.
   * <ul>
   *   <li>Given builder.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultValue(String)}
   */
  @Test
  @DisplayName("Test Builder defaultValue(String); given builder; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultValue(String)"})
  void testBuilderDefaultValue_givenBuilder_thenReturnBuilder() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.defaultValue("42"));
  }

  /**
   * Test Builder {@link Builder#defaultValue(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultValue(String)}
   */
  @Test
  @DisplayName("Test Builder defaultValue(String); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultValue(String)"})
  void testBuilderDefaultValue_thenThrowIllegalArgumentException() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    builderResult.computeValue(mock(TablePropertyComputeValue.class));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> builderResult.defaultValue("42"));
  }

  /**
   * Test Builder {@link Builder#getDefaultValue(BiFunction)}.
   * <ul>
   *   <li>Given builder.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#getDefaultValue(BiFunction)}
   */
  @Test
  @DisplayName("Test Builder getDefaultValue(BiFunction); given builder; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.getDefaultValue(BiFunction)"})
  void testBuilderGetDefaultValue_givenBuilder_thenReturnBuilder() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.getDefaultValue(mock(BiFunction.class)));
  }

  /**
   * Test Builder {@link Builder#getDefaultValue(BiFunction)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#getDefaultValue(BiFunction)}
   */
  @Test
  @DisplayName("Test Builder getDefaultValue(BiFunction); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.getDefaultValue(BiFunction)"})
  void testBuilderGetDefaultValue_thenThrowIllegalArgumentException() {
    // Arrange
    Builder builderResult = TablePropertyImpl.builder();
    builderResult.computeValue(mock(TablePropertyComputeValue.class));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> builderResult.getDefaultValue(mock(BiFunction.class)));
  }
}
