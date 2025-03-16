package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LoggingLevelsPropertyDiffblueTest {
  /**
   * Test {@link LoggingLevelsProperty#getAll()}.
   * <p>
   * Method under test: {@link LoggingLevelsProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List LoggingLevelsProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<UserDefinedInstanceProperty> actualAll = LoggingLevelsProperty.getAll();

    // Assert
    assertEquals(5, actualAll.size());
    assertTrue(actualAll.get(0) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(1) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(2) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(3) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(4) instanceof UserDefinedInstancePropertyImpl);
  }
}
