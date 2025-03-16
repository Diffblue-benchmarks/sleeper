package sleeper.systemtest.configuration;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.systemtest.configuration.SystemTestPropertyImpl.Builder;

class SystemTestPropertyImplDiffblueTest {
  /**
   * Test {@link SystemTestPropertyImpl#builder()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SystemTestPropertyImpl#builder()}
   *   <li>{@link SystemTestPropertyImpl#defaultValue(String)}
   *   <li>{@link SystemTestPropertyImpl#description(String)}
   *   <li>{@link SystemTestPropertyImpl#editable(boolean)}
   *   <li>{@link SystemTestPropertyImpl#propertyName(String)}
   *   <li>{@link SystemTestPropertyImpl#runCdkDeployWhenChanged(boolean)}
   *   <li>{@link SystemTestPropertyImpl#setByCdk(boolean)}
   *   <li>{@link SystemTestPropertyImpl#validationPredicate(Predicate)}
   * </ul>
   */
  @Test
  @DisplayName("Test builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addToIndex(java.util.function.Consumer)",
      "sleeper.systemtest.configuration.SystemTestProperty Builder.build()", "Builder Builder.defaultValue(String)",
      "Builder Builder.description(String)", "Builder Builder.editable(boolean)",
      "Builder Builder.propertyName(String)", "Builder Builder.runCdkDeployWhenChanged(boolean)",
      "Builder Builder.setByCdk(boolean)", "Builder Builder.validationPredicate(Predicate)"})
  void testBuilder() {
    // Arrange and Act
    Builder actualSetByCdkResult = SystemTestPropertyImpl.builder()
        .defaultValue("42")
        .description("The characteristics of someone or something")
        .editable(true)
        .propertyName("Property Name")
        .runCdkDeployWhenChanged(true)
        .setByCdk(true);

    // Assert
    assertSame(actualSetByCdkResult, actualSetByCdkResult.validationPredicate(mock(Predicate.class)));
  }
}
