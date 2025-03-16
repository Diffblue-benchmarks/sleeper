package sleeper.core.table;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TableStatusProviderDiffblueTest {
  /**
   * Test {@link TableStatusProvider#getById(String)}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStatusProvider#getById(String)}
   */
  @Test
  @DisplayName("Test getById(String); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional TableStatusProvider.getById(String)"})
  void testGetById_thenReturnNotPresent() {
    // Arrange, Act and Assert
    assertFalse((new TableStatusProvider(new InMemoryTableIndex())).getById("42").isPresent());
  }
}
