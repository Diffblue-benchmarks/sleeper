package sleeper.environment.cdk.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StringValueDiffblueTest {
  /**
   * Test {@link StringValue#mapOf(StringValue[])}.
   * <ul>
   *   <li>When {@link StringValue#StringValue(String, String)} with {@code Key} and value is {@code 42}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringValue#mapOf(StringValue[])}
   */
  @Test
  @DisplayName("Test mapOf(StringValue[]); when StringValue(String, String) with 'Key' and value is '42'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StringValue.mapOf(StringValue[])"})
  void testMapOf_whenStringValueWithKeyAndValueIs42_thenReturnSizeIsOne() {
    // Arrange and Act
    Map<String, String> actualMapOfResult = StringValue.mapOf(new StringValue("Key", "42"));

    // Assert
    assertEquals(1, actualMapOfResult.size());
    assertEquals("42", actualMapOfResult.get("Key"));
  }
}
