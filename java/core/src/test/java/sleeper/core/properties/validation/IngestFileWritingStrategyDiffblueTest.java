package sleeper.core.properties.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class IngestFileWritingStrategyDiffblueTest {
  /**
   * Test {@link IngestFileWritingStrategy#isValid(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestFileWritingStrategy#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when '42'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestFileWritingStrategy.isValid(String)"})
  void testIsValid_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(IngestFileWritingStrategy.isValid("42"));
  }

  /**
   * Test {@link IngestFileWritingStrategy#isValid(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestFileWritingStrategy#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestFileWritingStrategy.isValid(String)"})
  void testIsValid_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(IngestFileWritingStrategy.isValid(null));
  }

  /**
   * Test {@link IngestFileWritingStrategy#isValid(String)}.
   * <ul>
   *   <li>When {@code ONE_FILE_PER_LEAF}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestFileWritingStrategy#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'ONE_FILE_PER_LEAF'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestFileWritingStrategy.isValid(String)"})
  void testIsValid_whenOneFilePerLeaf_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(IngestFileWritingStrategy.isValid("ONE_FILE_PER_LEAF"));
  }
}
