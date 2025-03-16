package sleeper.environment.cdk.buildec2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.environment.cdk.buildec2.BuildEC2Parameters.Builder;
import sleeper.environment.cdk.config.AppContext;
import sleeper.environment.cdk.config.BooleanParameter;
import sleeper.environment.cdk.config.IntParameter;
import sleeper.environment.cdk.config.OptionalStringParameter;
import sleeper.environment.cdk.config.StringListParameter;
import sleeper.environment.cdk.config.StringParameter;

class BuildEC2ParametersDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#context(AppContext)}
   *   <li>{@link Builder#testBucket(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildEC2Parameters Builder.build()", "Builder Builder.context(AppContext)",
      "Builder Builder.testBucket(String)"})
  void testBuilderBuild() {
    // Arrange and Act
    BuildEC2Parameters actualBuildResult = BuildEC2Parameters.builder()
        .context(mock(AppContext.class))
        .testBucket("s3://bucket-name/object-key")
        .build();

    // Assert
    assertNull(actualBuildResult.image().loginUser());
    assertFalse(actualBuildResult.isNightlyTestEnabled());
  }

  /**
   * Test Builder {@link Builder#inheritVpc(String, List)} with {@code vpc}, {@code subnetIds}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#inheritVpc(String, List)}
   */
  @Test
  @DisplayName("Test Builder inheritVpc(String, List) with 'vpc', 'subnetIds'; given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.inheritVpc(String, List)"})
  void testBuilderInheritVpcWithVpcSubnetIds_given42_whenArrayListAdd42() {
    // Arrange
    Builder builderResult = BuildEC2Parameters.builder();

    ArrayList<String> subnetIds = new ArrayList<>();
    subnetIds.add("42");
    subnetIds.add("foo");

    // Act and Assert
    assertSame(builderResult, builderResult.inheritVpc("Vpc", subnetIds));
  }

  /**
   * Test Builder {@link Builder#inheritVpc(String, List)} with {@code vpc}, {@code subnetIds}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#inheritVpc(String, List)}
   */
  @Test
  @DisplayName("Test Builder inheritVpc(String, List) with 'vpc', 'subnetIds'; given 'foo'; when ArrayList() add 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.inheritVpc(String, List)"})
  void testBuilderInheritVpcWithVpcSubnetIds_givenFoo_whenArrayListAddFoo() {
    // Arrange
    Builder builderResult = BuildEC2Parameters.builder();

    ArrayList<String> subnetIds = new ArrayList<>();
    subnetIds.add("foo");

    // Act and Assert
    assertSame(builderResult, builderResult.inheritVpc("Vpc", subnetIds));
  }

  /**
   * Test Builder {@link Builder#inheritVpc(String, List)} with {@code vpc}, {@code subnetIds}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#inheritVpc(String, List)}
   */
  @Test
  @DisplayName("Test Builder inheritVpc(String, List) with 'vpc', 'subnetIds'; when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.inheritVpc(String, List)"})
  void testBuilderInheritVpcWithVpcSubnetIds_whenArrayList() {
    // Arrange
    Builder builderResult = BuildEC2Parameters.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.inheritVpc("Vpc", new ArrayList<>()));
  }

  /**
   * Test {@link BuildEC2Parameters#from(AppContext)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code context must not be null}.</li>
   *   <li>Then return NightlyTestEnabled.</li>
   * </ul>
   * <p>
   * Method under test: {@link BuildEC2Parameters#from(AppContext)}
   */
  @Test
  @DisplayName("Test from(AppContext); given ArrayList() add 'context must not be null'; then return NightlyTestEnabled")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildEC2Parameters BuildEC2Parameters.from(AppContext)"})
  void testFrom_givenArrayListAddContextMustNotBeNull_thenReturnNightlyTestEnabled() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("context must not be null");
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<StringListParameter>any())).thenReturn(stringList);
    Optional<String> ofResult = Optional.of("42");
    when(context.get(Mockito.<OptionalStringParameter>any())).thenReturn(ofResult);
    when(context.get(Mockito.<BooleanParameter>any())).thenReturn(true);
    when(context.get(Mockito.<IntParameter>any())).thenReturn(1);
    when(context.get(Mockito.<StringParameter>any())).thenReturn("Get");

    // Act
    BuildEC2Parameters actualFromResult = BuildEC2Parameters.from(context);

    // Assert
    verify(context).get(isA(BooleanParameter.class));
    verify(context, atLeast(1)).get(Mockito.<IntParameter>any());
    verify(context, atLeast(1)).get(Mockito.<OptionalStringParameter>any());
    verify(context).get(isA(StringListParameter.class));
    verify(context, atLeast(1)).get(Mockito.<StringParameter>any());
    assertEquals("Get", actualFromResult.image().loginUser());
    assertTrue(actualFromResult.isNightlyTestEnabled());
  }

  /**
   * Test {@link BuildEC2Parameters#from(AppContext)}.
   * <ul>
   *   <li>Given empty.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(OptionalStringParameter)} return empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link BuildEC2Parameters#from(AppContext)}
   */
  @Test
  @DisplayName("Test from(AppContext); given empty; when AppContext get(OptionalStringParameter) return empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildEC2Parameters BuildEC2Parameters.from(AppContext)"})
  void testFrom_givenEmpty_whenAppContextGetReturnEmpty() {
    // Arrange
    AppContext context = mock(AppContext.class);
    Optional<String> emptyResult = Optional.empty();
    when(context.get(Mockito.<OptionalStringParameter>any())).thenReturn(emptyResult);
    when(context.get(Mockito.<BooleanParameter>any())).thenReturn(true);
    when(context.get(Mockito.<IntParameter>any())).thenReturn(1);
    when(context.get(Mockito.<StringParameter>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> BuildEC2Parameters.from(context));
    verify(context).get(isA(BooleanParameter.class));
    verify(context).get(isA(IntParameter.class));
    verify(context).get(isA(OptionalStringParameter.class));
    verify(context, atLeast(1)).get(Mockito.<StringParameter>any());
  }

  /**
   * Test {@link BuildEC2Parameters#from(AppContext)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(BooleanParameter)} return {@code false}.</li>
   *   <li>Then return not NightlyTestEnabled.</li>
   * </ul>
   * <p>
   * Method under test: {@link BuildEC2Parameters#from(AppContext)}
   */
  @Test
  @DisplayName("Test from(AppContext); given 'false'; when AppContext get(BooleanParameter) return 'false'; then return not NightlyTestEnabled")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildEC2Parameters BuildEC2Parameters.from(AppContext)"})
  void testFrom_givenFalse_whenAppContextGetReturnFalse_thenReturnNotNightlyTestEnabled() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<BooleanParameter>any())).thenReturn(false);
    when(context.get(Mockito.<IntParameter>any())).thenReturn(1);
    when(context.get(Mockito.<StringParameter>any())).thenReturn("Get");

    // Act
    BuildEC2Parameters actualFromResult = BuildEC2Parameters.from(context);

    // Assert
    verify(context).get(isA(BooleanParameter.class));
    verify(context).get(isA(IntParameter.class));
    verify(context, atLeast(1)).get(Mockito.<StringParameter>any());
    assertEquals("Get", actualFromResult.image().loginUser());
    assertFalse(actualFromResult.isNightlyTestEnabled());
  }

  /**
   * Test {@link BuildEC2Parameters#from(AppContext)}.
   * <ul>
   *   <li>Given {@link Optional} with {@code foo}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(OptionalStringParameter)} return {@link Optional} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BuildEC2Parameters#from(AppContext)}
   */
  @Test
  @DisplayName("Test from(AppContext); given Optional with 'foo'; when AppContext get(OptionalStringParameter) return Optional with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildEC2Parameters BuildEC2Parameters.from(AppContext)"})
  void testFrom_givenOptionalWithFoo_whenAppContextGetReturnOptionalWithFoo() {
    // Arrange
    AppContext context = mock(AppContext.class);
    Optional<String> ofResult = Optional.of("foo");
    when(context.get(Mockito.<OptionalStringParameter>any())).thenReturn(ofResult);
    when(context.get(Mockito.<BooleanParameter>any())).thenReturn(true);
    when(context.get(Mockito.<IntParameter>any())).thenReturn(1);
    when(context.get(Mockito.<StringParameter>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> BuildEC2Parameters.from(context));
    verify(context).get(isA(BooleanParameter.class));
    verify(context).get(isA(IntParameter.class));
    verify(context).get(isA(OptionalStringParameter.class));
    verify(context, atLeast(1)).get(Mockito.<StringParameter>any());
  }

  /**
   * Test {@link BuildEC2Parameters#from(AppContext)}.
   * <ul>
   *   <li>When {@link AppContext} {@link AppContext#get(OptionalStringParameter)} throw {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code context must not be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BuildEC2Parameters#from(AppContext)}
   */
  @Test
  @DisplayName("Test from(AppContext); when AppContext get(OptionalStringParameter) throw IllegalArgumentException(String) with 'context must not be null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildEC2Parameters BuildEC2Parameters.from(AppContext)"})
  void testFrom_whenAppContextGetThrowIllegalArgumentExceptionWithContextMustNotBeNull() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<OptionalStringParameter>any()))
        .thenThrow(new IllegalArgumentException("context must not be null"));
    when(context.get(Mockito.<BooleanParameter>any())).thenReturn(true);
    when(context.get(Mockito.<IntParameter>any())).thenReturn(1);
    when(context.get(Mockito.<StringParameter>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> BuildEC2Parameters.from(context));
    verify(context).get(isA(BooleanParameter.class));
    verify(context).get(isA(IntParameter.class));
    verify(context).get(isA(OptionalStringParameter.class));
    verify(context, atLeast(1)).get(Mockito.<StringParameter>any());
  }

  /**
   * Test {@link BuildEC2Parameters#from(AppContext)}.
   * <ul>
   *   <li>When {@link AppContext} {@link AppContext#get(StringListParameter)} throw {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code context must not be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BuildEC2Parameters#from(AppContext)}
   */
  @Test
  @DisplayName("Test from(AppContext); when AppContext get(StringListParameter) throw IllegalArgumentException(String) with 'context must not be null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildEC2Parameters BuildEC2Parameters.from(AppContext)"})
  void testFrom_whenAppContextGetThrowIllegalArgumentExceptionWithContextMustNotBeNull2() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<StringListParameter>any()))
        .thenThrow(new IllegalArgumentException("context must not be null"));
    Optional<String> ofResult = Optional.of("42");
    when(context.get(Mockito.<OptionalStringParameter>any())).thenReturn(ofResult);
    when(context.get(Mockito.<BooleanParameter>any())).thenReturn(true);
    when(context.get(Mockito.<IntParameter>any())).thenReturn(1);
    when(context.get(Mockito.<StringParameter>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> BuildEC2Parameters.from(context));
    verify(context).get(isA(BooleanParameter.class));
    verify(context, atLeast(1)).get(Mockito.<IntParameter>any());
    verify(context, atLeast(1)).get(Mockito.<OptionalStringParameter>any());
    verify(context).get(isA(StringListParameter.class));
    verify(context, atLeast(1)).get(Mockito.<StringParameter>any());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BuildEC2Parameters#image()}
   *   <li>{@link BuildEC2Parameters#isNightlyTestEnabled()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildEC2Image BuildEC2Parameters.image()", "boolean BuildEC2Parameters.isNightlyTestEnabled()"})
  void testGettersAndSetters() {
    // Arrange
    BuildEC2Parameters buildResult = BuildEC2Parameters.builder()
        .context(mock(AppContext.class))
        .testBucket("s3://bucket-name/object-key")
        .build();

    // Act
    BuildEC2Image actualImageResult = buildResult.image();
    boolean actualIsNightlyTestEnabledResult = buildResult.isNightlyTestEnabled();

    // Assert
    assertNull(actualImageResult.loginUser());
    assertFalse(actualIsNightlyTestEnabledResult);
  }
}
