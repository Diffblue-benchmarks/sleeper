package sleeper.environment.cdk.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StringParameterDiffblueTest {
  /**
   * Test {@link StringParameter#get(AppContext)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given empty string; when AppContext get(String) return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StringParameter.get(AppContext)"})
  void testGet_givenEmptyString_whenAppContextGetReturnEmptyString() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> AppParameters.BUILD_BRANCH.get(context));
    verify(context).get(eq("branch"));
  }

  /**
   * Test {@link StringParameter#get(AppContext)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return {@code Get}.</li>
   *   <li>Then return {@code Get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given 'Get'; when AppContext get(String) return 'Get'; then return 'Get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StringParameter.get(AppContext)"})
  void testGet_givenGet_whenAppContextGetReturnGet_thenReturnGet() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    String actualGetResult = AppParameters.BUILD_BRANCH.get(context);

    // Assert
    verify(context).get(eq("branch"));
    assertEquals("Get", actualGetResult);
  }

  /**
   * Test {@link StringParameter#get(AppContext)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StringParameter.get(AppContext)"})
  void testGet_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> AppParameters.BUILD_BRANCH.get(context));
    verify(context).get(eq("branch"));
  }

  /**
   * Test {@link StringParameter#get(AppContext)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return one.</li>
   *   <li>Then return {@code develop}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given one; when AppContext get(String) return one; then return 'develop'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StringParameter.get(AppContext)"})
  void testGet_givenOne_whenAppContextGetReturnOne_thenReturnDevelop() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn(1);

    // Act
    String actualGetResult = AppParameters.BUILD_BRANCH.get(context);

    // Assert
    verify(context).get(eq("branch"));
    assertEquals("develop", actualGetResult);
  }

  /**
   * Test {@link StringParameter#getStringOrDefault(AppContext, String, String)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringParameter#getStringOrDefault(AppContext, String, String)}
   */
  @Test
  @DisplayName("Test getStringOrDefault(AppContext, String, String); given empty string; when AppContext get(String) return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StringParameter.getStringOrDefault(AppContext, String, String)"})
  void testGetStringOrDefault_givenEmptyString_whenAppContextGetReturnEmptyString() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> StringParameter.getStringOrDefault(context, "Key", "42"));
    verify(context).get(eq("Key"));
  }

  /**
   * Test {@link StringParameter#getStringOrDefault(AppContext, String, String)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return {@code Get}.</li>
   *   <li>Then return {@code Get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringParameter#getStringOrDefault(AppContext, String, String)}
   */
  @Test
  @DisplayName("Test getStringOrDefault(AppContext, String, String); given 'Get'; when AppContext get(String) return 'Get'; then return 'Get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StringParameter.getStringOrDefault(AppContext, String, String)"})
  void testGetStringOrDefault_givenGet_whenAppContextGetReturnGet_thenReturnGet() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    String actualStringOrDefault = StringParameter.getStringOrDefault(context, "Key", "42");

    // Assert
    verify(context).get(eq("Key"));
    assertEquals("Get", actualStringOrDefault);
  }

  /**
   * Test {@link StringParameter#getStringOrDefault(AppContext, String, String)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringParameter#getStringOrDefault(AppContext, String, String)}
   */
  @Test
  @DisplayName("Test getStringOrDefault(AppContext, String, String); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StringParameter.getStringOrDefault(AppContext, String, String)"})
  void testGetStringOrDefault_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> StringParameter.getStringOrDefault(context, "Key", "42"));
    verify(context).get(eq("Key"));
  }

  /**
   * Test {@link StringParameter#getStringOrDefault(AppContext, String, String)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return one.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringParameter#getStringOrDefault(AppContext, String, String)}
   */
  @Test
  @DisplayName("Test getStringOrDefault(AppContext, String, String); given one; when AppContext get(String) return one; then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StringParameter.getStringOrDefault(AppContext, String, String)"})
  void testGetStringOrDefault_givenOne_whenAppContextGetReturnOne_thenReturn42() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn(1);

    // Act
    String actualStringOrDefault = StringParameter.getStringOrDefault(context, "Key", "42");

    // Assert
    verify(context).get(eq("Key"));
    assertEquals("42", actualStringOrDefault);
  }
}
