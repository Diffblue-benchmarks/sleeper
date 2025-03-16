package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.PropertyGroup;
import sleeper.core.properties.instance.CdkDefinedInstancePropertyImpl.Builder;

class CdkDefinedInstancePropertyImplDiffblueTest {
  /**
   * Test {@link CdkDefinedInstancePropertyImpl#builder()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CdkDefinedInstancePropertyImpl#builder()}
   *   <li>{@link CdkDefinedInstancePropertyImpl#description(String)}
   *   <li>{@link CdkDefinedInstancePropertyImpl#propertyGroup(PropertyGroup)}
   *   <li>{@link CdkDefinedInstancePropertyImpl#propertyName(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addToIndex(java.util.function.Consumer)",
      "sleeper.core.properties.instance.CdkDefinedInstanceProperty Builder.build()",
      "Builder Builder.description(String)", "Builder Builder.propertyGroup(PropertyGroup)",
      "Builder Builder.propertyName(String)"})
  void testBuilder() {
    // Arrange and Act
    Builder actualPropertyGroupResult = CdkDefinedInstancePropertyImpl.builder()
        .description("The characteristics of someone or something")
        .propertyGroup(InstancePropertyGroup.ATHENA);

    // Assert
    assertSame(actualPropertyGroupResult, actualPropertyGroupResult.propertyName("Property Name"));
  }
}
