package sleeper.environment.cdk.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RequiredStringParameterDiffblueTest {
  /**
   * Test {@link RequiredStringParameter#get(AppContext)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return {@code Get}.</li>
   *   <li>Then return {@code Get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequiredStringParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given 'Get'; when AppContext get(String) return 'Get'; then return 'Get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RequiredStringParameter.get(AppContext)"})
  void testGet_givenGet_whenAppContextGetReturnGet_thenReturnGet() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    String actualGetResult = AppParameters.INSTANCE_ID.get(context);

    // Assert
    verify(context).get(eq("instanceId"));
    assertEquals("Get", actualGetResult);
  }

  /**
   * Test {@link RequiredStringParameter#get(AppContext)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return one.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequiredStringParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given one; when AppContext get(String) return one; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String RequiredStringParameter.get(AppContext)"})
  void testGet_givenOne_whenAppContextGetReturnOne_thenThrowIllegalArgumentException() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn(1);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> AppParameters.INSTANCE_ID.get(context));
    verify(context).get(eq("instanceId"));
  }

  /**
   * Test {@link RequiredStringParameter#getOptionalString(AppContext, String)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return {@code Get}.</li>
   *   <li>Then return {@link Optional#get()} is {@code Get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequiredStringParameter#getOptionalString(AppContext, String)}
   */
  @Test
  @DisplayName("Test getOptionalString(AppContext, String); given 'Get'; when AppContext get(String) return 'Get'; then return get() is 'Get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional RequiredStringParameter.getOptionalString(AppContext, String)"})
  void testGetOptionalString_givenGet_whenAppContextGetReturnGet_thenReturnGetIsGet() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    Optional<String> actualOptionalString = RequiredStringParameter.getOptionalString(context, "Key");

    // Assert
    verify(context).get(eq("Key"));
    assertEquals("Get", actualOptionalString.get());
    assertTrue(actualOptionalString.isPresent());
  }

  /**
   * Test {@link RequiredStringParameter#getOptionalString(AppContext, String)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return one.</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequiredStringParameter#getOptionalString(AppContext, String)}
   */
  @Test
  @DisplayName("Test getOptionalString(AppContext, String); given one; when AppContext get(String) return one; then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional RequiredStringParameter.getOptionalString(AppContext, String)"})
  void testGetOptionalString_givenOne_whenAppContextGetReturnOne_thenReturnNotPresent() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn(1);

    // Act
    Optional<String> actualOptionalString = RequiredStringParameter.getOptionalString(context, "Key");

    // Assert
    verify(context).get(eq("Key"));
    assertFalse(actualOptionalString.isPresent());
  }
}
