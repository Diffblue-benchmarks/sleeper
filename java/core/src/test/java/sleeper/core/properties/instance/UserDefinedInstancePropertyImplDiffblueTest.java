package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.PropertyGroup;
import sleeper.core.properties.instance.UserDefinedInstancePropertyImpl.Builder;
import sleeper.core.properties.testutils.DummyInstanceProperty;

class UserDefinedInstancePropertyImplDiffblueTest {
  /**
   * Test {@link UserDefinedInstancePropertyImpl#builder()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UserDefinedInstancePropertyImpl#builder()}
   *   <li>{@link UserDefinedInstancePropertyImpl#defaultValue(String)}
   *   <li>{@link UserDefinedInstancePropertyImpl#description(String)}
   *   <li>{@link UserDefinedInstancePropertyImpl#editable(boolean)}
   *   <li>{@link UserDefinedInstancePropertyImpl#ignoreEmptyValue(boolean)}
   *   <li>{@link UserDefinedInstancePropertyImpl#includedInTemplate(boolean)}
   *   <li>{@link UserDefinedInstancePropertyImpl#propertyGroup(PropertyGroup)}
   *   <li>{@link UserDefinedInstancePropertyImpl#propertyName(String)}
   *   <li>{@link UserDefinedInstancePropertyImpl#runCdkDeployWhenChanged(boolean)}
   *   <li>{@link UserDefinedInstancePropertyImpl#validationPredicate(Predicate)}
   * </ul>
   */
  @Test
  @DisplayName("Test builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addToIndex(java.util.function.Consumer)",
      "sleeper.core.properties.instance.UserDefinedInstanceProperty Builder.build()",
      "Builder Builder.defaultValue(String)", "Builder Builder.description(String)",
      "Builder Builder.editable(boolean)", "Builder Builder.ignoreEmptyValue(boolean)",
      "Builder Builder.includedInTemplate(boolean)", "Builder Builder.propertyGroup(PropertyGroup)",
      "Builder Builder.propertyName(String)", "Builder Builder.runCdkDeployWhenChanged(boolean)",
      "Builder Builder.validationPredicate(Predicate)"})
  void testBuilder() {
    // Arrange and Act
    Builder actualRunCdkDeployWhenChangedResult = UserDefinedInstancePropertyImpl.builder()
        .defaultValue("42")
        .description("The characteristics of someone or something")
        .editable(true)
        .ignoreEmptyValue(true)
        .includedInBasicTemplate(true)
        .includedInTemplate(true)
        .propertyGroup(InstancePropertyGroup.ATHENA)
        .propertyName("Property Name")
        .runCdkDeployWhenChanged(true);

    // Assert
    assertSame(actualRunCdkDeployWhenChangedResult,
        actualRunCdkDeployWhenChangedResult.validationPredicate(mock(Predicate.class)));
  }

  /**
   * Test Builder {@link Builder#defaultProperty(InstanceProperty)}.
   * <ul>
   *   <li>When {@link DummyInstanceProperty#DummyInstanceProperty(String)} with {@code Property Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultProperty(InstanceProperty)}
   */
  @Test
  @DisplayName("Test Builder defaultProperty(InstanceProperty); when DummyInstanceProperty(String) with 'Property Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultProperty(InstanceProperty)"})
  void testBuilderDefaultProperty_whenDummyInstancePropertyWithPropertyName() {
    // Arrange
    Builder builderResult = UserDefinedInstancePropertyImpl.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.defaultProperty(new DummyInstanceProperty("Property Name")));
  }

  /**
   * Test Builder {@link Builder#defaultProperty(InstanceProperty)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#defaultProperty(InstanceProperty)}
   */
  @Test
  @DisplayName("Test Builder defaultProperty(InstanceProperty); when MAX_IN_MEMORY_BATCH_SIZE; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.defaultProperty(InstanceProperty)"})
  void testBuilderDefaultProperty_whenMax_in_memory_batch_size_thenReturnBuilder() {
    // Arrange
    Builder builderResult = UserDefinedInstancePropertyImpl.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.defaultProperty(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test Builder {@link Builder#includedInBasicTemplate(boolean)}.
   * <p>
   * Method under test: {@link Builder#includedInBasicTemplate(boolean)}
   */
  @Test
  @DisplayName("Test Builder includedInBasicTemplate(boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.includedInBasicTemplate(boolean)"})
  void testBuilderIncludedInBasicTemplate() {
    // Arrange
    Builder builderResult = UserDefinedInstancePropertyImpl.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.includedInBasicTemplate(true));
  }
}
