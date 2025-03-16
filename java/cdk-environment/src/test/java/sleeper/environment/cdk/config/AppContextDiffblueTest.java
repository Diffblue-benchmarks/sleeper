package sleeper.environment.cdk.config;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AppContextDiffblueTest {
  /**
   * Test {@link AppContext#of(StringValue[])} with {@code values}.
   * <p>
   * Method under test: {@link AppContext#of(StringValue[])}
   */
  @Test
  @DisplayName("Test of(StringValue[]) with 'values'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AppContext AppContext.of(StringValue[])"})
  void testOfWithValues() {
    // Arrange, Act and Assert
    assertNull(AppContext.of(new StringValue("Key", "42")).get("foo"));
  }

  /**
   * Test {@link AppContext#empty()}.
   * <p>
   * Method under test: {@link AppContext#empty()}
   */
  @Test
  @DisplayName("Test empty()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AppContext AppContext.empty()"})
  void testEmpty() {
    // Arrange, Act and Assert
    assertNull(AppContext.empty().get("foo"));
  }
}
