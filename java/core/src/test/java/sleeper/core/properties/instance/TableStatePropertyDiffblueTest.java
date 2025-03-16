package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TableStatePropertyDiffblueTest {
  /**
   * Test {@link TableStateProperty#getAll()}.
   * <p>
   * Method under test: {@link TableStateProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List TableStateProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<UserDefinedInstanceProperty> actualAll = TableStateProperty.getAll();

    // Assert
    assertEquals(33, actualAll.size());
    assertTrue(actualAll.get(0) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(1) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(2) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(27) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(28) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(29) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(3) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(30) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(31) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(4) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(5) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(Integer.SIZE) instanceof UserDefinedInstancePropertyImpl);
  }
}
