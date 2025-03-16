package sleeper.systemtest.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SystemTestPropertyDiffblueTest {
  /**
   * Test {@link SystemTestProperty#getAll()}.
   * <p>
   * Method under test: {@link SystemTestProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SystemTestProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<SystemTestProperty> actualAll = SystemTestProperty.getAll();

    // Assert
    assertEquals(27, actualAll.size());
    assertTrue(actualAll.get(0) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(1) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(2) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(21) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(22) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(23) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(24) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(25) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(26) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(3) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(4) instanceof SystemTestPropertyImpl);
    assertTrue(actualAll.get(5) instanceof SystemTestPropertyImpl);
  }
}
