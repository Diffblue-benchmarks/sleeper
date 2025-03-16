package sleeper.clients.admin.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.admin.properties.PropertyGroupWithCategory.Category;

class PropertyGroupWithCategoryDiffblueTest {
  /**
   * Test Category {@link Category#getName()}.
   * <p>
   * Method under test: {@link Category#getName()}
   */
  @Test
  @DisplayName("Test Category getName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String Category.getName()"})
  void testCategoryGetName() {
    // Arrange, Act and Assert
    assertEquals("Instance Properties", Category.valueOf("INSTANCE").getName());
  }
}
