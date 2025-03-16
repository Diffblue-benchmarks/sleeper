package sleeper.core.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TableIdGeneratorDiffblueTest {
  /**
   * Test {@link TableIdGenerator#fromRandomSeed(int)}.
   * <p>
   * Method under test: {@link TableIdGenerator#fromRandomSeed(int)}
   */
  @Test
  @DisplayName("Test fromRandomSeed(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableIdGenerator TableIdGenerator.fromRandomSeed(int)"})
  void testFromRandomSeed() {
    // Arrange, Act and Assert
    assertEquals("359d41ba", TableIdGenerator.fromRandomSeed(42).generateString());
  }

  /**
   * Test {@link TableIdGenerator#generateString()}.
   * <p>
   * Method under test: {@link TableIdGenerator#generateString()}
   */
  @Test
  @DisplayName("Test generateString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String TableIdGenerator.generateString()"})
  void testGenerateString() {
    // Arrange, Act and Assert
    assertEquals("359d41ba", TableIdGenerator.fromRandomSeed(42).generateString());
  }
}
