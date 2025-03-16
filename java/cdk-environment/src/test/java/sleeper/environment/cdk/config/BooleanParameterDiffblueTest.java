package sleeper.environment.cdk.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BooleanParameterDiffblueTest {
  /**
   * Test {@link BooleanParameter#get(AppContext)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return {@code Get}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BooleanParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given 'Get'; when AppContext get(String) return 'Get'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BooleanParameter.get(AppContext)"})
  void testGet_givenGet_whenAppContextGetReturnGet_thenReturnFalse() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    boolean actualGetResult = AppParameters.DEPLOY_EC2.get(context);

    // Assert
    verify(context).get(eq("deployEc2"));
    assertFalse(actualGetResult);
  }

  /**
   * Test {@link BooleanParameter#get(AppContext)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BooleanParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given 'true'; when AppContext get(String) return 'true'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BooleanParameter.get(AppContext)"})
  void testGet_givenTrue_whenAppContextGetReturnTrue_thenReturnTrue() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn(true);

    // Act
    boolean actualGetResult = AppParameters.DEPLOY_EC2.get(context);

    // Assert
    verify(context).get(eq("deployEc2"));
    assertTrue(actualGetResult);
  }
}
