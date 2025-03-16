package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CompactionPropertyDiffblueTest {
  /**
   * Test {@link CompactionProperty#getAll()}.
   * <p>
   * Method under test: {@link CompactionProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CompactionProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<UserDefinedInstanceProperty> actualAll = CompactionProperty.getAll();

    // Assert
    assertEquals(57, actualAll.size());
    assertTrue(actualAll.get(0) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(1) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(2) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(3) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(4) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(5) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(51) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(52) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(53) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(54) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(55) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(56) instanceof UserDefinedInstancePropertyImpl);
  }
}
