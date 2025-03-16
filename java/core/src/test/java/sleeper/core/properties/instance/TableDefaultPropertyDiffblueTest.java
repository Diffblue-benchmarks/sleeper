package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TableDefaultPropertyDiffblueTest {
  /**
   * Test {@link TableDefaultProperty#getAll()}.
   * <p>
   * Method under test: {@link TableDefaultProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List TableDefaultProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<UserDefinedInstanceProperty> actualAll = TableDefaultProperty.getAll();

    // Assert
    assertEquals(43, actualAll.size());
    assertTrue(actualAll.get(0) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(1) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(2) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(3) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(37) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(38) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(39) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(4) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(40) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(41) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(42) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(5) instanceof UserDefinedInstancePropertyImpl);
  }
}
