package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CommonPropertyDiffblueTest {
  /**
   * Test {@link CommonProperty#getAll()}.
   * <p>
   * Method under test: {@link CommonProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CommonProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<UserDefinedInstanceProperty> actualAll = CommonProperty.getAll();

    // Assert
    assertEquals(26, actualAll.size());
    assertTrue(actualAll.get(0) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(1) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(2) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(21) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(22) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(23) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(24) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(25) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(3) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(4) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(5) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(CommonProperty.ID_MAX_LENGTH) instanceof UserDefinedInstancePropertyImpl);
  }
}
