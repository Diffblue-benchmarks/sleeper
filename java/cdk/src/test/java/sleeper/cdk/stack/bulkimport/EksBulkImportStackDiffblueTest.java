package sleeper.cdk.stack.bulkimport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.cdk.stack.bulkimport.EksBulkImportStack.JsonTypeToken;

class EksBulkImportStackDiffblueTest {
  /**
   * Test JsonTypeToken new {@link JsonTypeToken} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link JsonTypeToken}
   */
  @Test
  @DisplayName("Test JsonTypeToken new JsonTypeToken (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonTypeToken.<init>()"})
  void testJsonTypeTokenNewJsonTypeToken() {
    // Arrange and Act
    JsonTypeToken actualJsonTypeToken = new JsonTypeToken();

    // Assert
    assertEquals("java.util.Map<java.lang.String, java.lang.Object>", actualJsonTypeToken.getType().getTypeName());
    Class<Map> expectedRawType = Map.class;
    assertEquals(expectedRawType, actualJsonTypeToken.getRawType());
  }
}
