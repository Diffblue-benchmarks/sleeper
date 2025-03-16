package sleeper.environment.cdk.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StringListParameterDiffblueTest {
  /**
   * Test {@link StringListParameter#get(AppContext)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return empty string.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringListParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given empty string; when AppContext get(String) return empty string; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StringListParameter.get(AppContext)"})
  void testGet_givenEmptyString_whenAppContextGetReturnEmptyString_thenReturnEmpty() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("");

    // Act
    List<String> actualGetResult = AppParameters.AUTO_SHUTDOWN_EXISTING_EC2_IDS.get(context);

    // Assert
    verify(context).get(eq("autoShutdownExistingEc2Ids"));
    assertTrue(actualGetResult.isEmpty());
  }

  /**
   * Test {@link StringListParameter#get(AppContext)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return {@code Get}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringListParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given 'Get'; when AppContext get(String) return 'Get'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StringListParameter.get(AppContext)"})
  void testGet_givenGet_whenAppContextGetReturnGet_thenReturnSizeIsOne() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    List<String> actualGetResult = AppParameters.AUTO_SHUTDOWN_EXISTING_EC2_IDS.get(context);

    // Assert
    verify(context).get(eq("autoShutdownExistingEc2Ids"));
    assertEquals(1, actualGetResult.size());
    assertEquals("Get", actualGetResult.get(0));
  }

  /**
   * Test {@link StringListParameter#get(AppContext)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringListParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StringListParameter.get(AppContext)"})
  void testGet_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> AppParameters.AUTO_SHUTDOWN_EXISTING_EC2_IDS.get(context));
    verify(context).get(eq("autoShutdownExistingEc2Ids"));
  }

  /**
   * Test {@link StringListParameter#get(AppContext)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return {@code null}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringListParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given 'null'; when AppContext get(String) return 'null'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StringListParameter.get(AppContext)"})
  void testGet_givenNull_whenAppContextGetReturnNull_thenReturnEmpty() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn(null);

    // Act
    List<String> actualGetResult = AppParameters.AUTO_SHUTDOWN_EXISTING_EC2_IDS.get(context);

    // Assert
    verify(context).get(eq("autoShutdownExistingEc2Ids"));
    assertTrue(actualGetResult.isEmpty());
  }

  /**
   * Test {@link StringListParameter#get(AppContext)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return one.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringListParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given one; when AppContext get(String) return one; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StringListParameter.get(AppContext)"})
  void testGet_givenOne_whenAppContextGetReturnOne_thenThrowIllegalArgumentException() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn(1);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> AppParameters.AUTO_SHUTDOWN_EXISTING_EC2_IDS.get(context));
    verify(context).get(eq("autoShutdownExistingEc2Ids"));
  }
}
