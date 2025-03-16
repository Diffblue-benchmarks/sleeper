package sleeper.environment.cdk.buildec2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.environment.cdk.config.AppContext;
import sleeper.environment.cdk.config.IntParameter;
import sleeper.environment.cdk.config.StringParameter;

class BuildEC2ImageDiffblueTest {
  /**
   * Test {@link BuildEC2Image#from(AppContext)}.
   * <p>
   * Method under test: {@link BuildEC2Image#from(AppContext)}
   */
  @Test
  @DisplayName("Test from(AppContext)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildEC2Image BuildEC2Image.from(AppContext)"})
  void testFrom() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<IntParameter>any())).thenReturn(1);
    when(context.get(Mockito.<StringParameter>any())).thenReturn("Get");

    // Act
    BuildEC2Image actualFromResult = BuildEC2Image.from(context);

    // Assert
    verify(context).get(isA(IntParameter.class));
    verify(context, atLeast(1)).get(Mockito.<StringParameter>any());
    assertEquals("Get", actualFromResult.loginUser());
  }

  /**
   * Test {@link BuildEC2Image#loginUser()}.
   * <p>
   * Method under test: {@link BuildEC2Image#loginUser()}
   */
  @Test
  @DisplayName("Test loginUser()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String BuildEC2Image.loginUser()"})
  void testLoginUser() {
    // Arrange, Act and Assert
    assertNull(BuildEC2Image.from(mock(AppContext.class)).loginUser());
  }
}
