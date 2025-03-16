package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UserDefinedInstancePropertyDiffblueTest {
  /**
   * Test {@link UserDefinedInstanceProperty#getAll()}.
   * <p>
   * Method under test: {@link UserDefinedInstanceProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List UserDefinedInstanceProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<UserDefinedInstanceProperty> actualAll = UserDefinedInstanceProperty.getAll();

    // Assert
    assertEquals(325, actualAll.size());
    assertTrue(actualAll.get(0) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(1) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(2) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(3) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(319) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(320) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(321) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(322) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(323) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(324) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(4) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(5) instanceof UserDefinedInstancePropertyImpl);
  }
}
