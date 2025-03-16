package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class IngestPropertyDiffblueTest {
  /**
   * Test {@link IngestProperty#getAll()}.
   * <p>
   * Method under test: {@link IngestProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List IngestProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<UserDefinedInstanceProperty> actualAll = IngestProperty.getAll();

    // Assert
    assertEquals(14, actualAll.size());
    assertTrue(actualAll.get(0) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(1) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(10) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(11) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(12) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(13) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(2) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(3) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(4) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(5) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(8) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(9) instanceof UserDefinedInstancePropertyImpl);
  }
}
