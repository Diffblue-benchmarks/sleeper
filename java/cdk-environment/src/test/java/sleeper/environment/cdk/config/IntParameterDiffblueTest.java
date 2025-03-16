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

class IntParameterDiffblueTest {
  /**
   * Test {@link IntParameter#get(AppContext)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return {@code 42}.</li>
   *   <li>Then return forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link IntParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given '42'; when AppContext get(String) return '42'; then return forty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IntParameter.get(AppContext)"})
  void testGet_given42_whenAppContextGetReturn42_thenReturnFortyTwo() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("42");

    // Act
    int actualGetResult = AppParameters.AUTO_SHUTDOWN_HOUR_UTC.get(context);

    // Assert
    verify(context).get(eq("autoShutdownHourUtc"));
    assertEquals(42, actualGetResult);
  }

  /**
   * Test {@link IntParameter#get(AppContext)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return {@code Get}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IntParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given 'Get'; when AppContext get(String) return 'Get'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IntParameter.get(AppContext)"})
  void testGet_givenGet_whenAppContextGetReturnGet_thenThrowIllegalArgumentException() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> AppParameters.AUTO_SHUTDOWN_HOUR_UTC.get(context));
    verify(context).get(eq("autoShutdownHourUtc"));
  }

  /**
   * Test {@link IntParameter#get(AppContext)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(String)} return one.</li>
   *   <li>Then return nineteen.</li>
   * </ul>
   * <p>
   * Method under test: {@link IntParameter#get(AppContext)}
   */
  @Test
  @DisplayName("Test get(AppContext); given one; when AppContext get(String) return one; then return nineteen")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IntParameter.get(AppContext)"})
  void testGet_givenOne_whenAppContextGetReturnOne_thenReturnNineteen() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<String>any())).thenReturn(1);

    // Act
    int actualGetResult = AppParameters.AUTO_SHUTDOWN_HOUR_UTC.get(context);

    // Assert
    verify(context).get(eq("autoShutdownHourUtc"));
    assertEquals(19, actualGetResult);
  }
}
