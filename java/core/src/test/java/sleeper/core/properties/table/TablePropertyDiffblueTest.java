package sleeper.core.properties.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TablePropertyDiffblueTest {
  /**
   * Test {@link TableProperty#getAll()}.
   * <p>
   * Method under test: {@link TableProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List TableProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<TableProperty> actualAll = TableProperty.getAll();

    // Assert
    assertEquals(74, actualAll.size());
    assertTrue(actualAll.get(0) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(1) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(2) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(3) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(4) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(5) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(68) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(69) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(70) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(71) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(72) instanceof TablePropertyImpl);
    assertTrue(actualAll.get(73) instanceof TablePropertyImpl);
  }
}
