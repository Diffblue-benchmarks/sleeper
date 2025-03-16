package sleeper.build.github.api.containers;

import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Properties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.github.api.containers.DeleteGHCRVersions.Builder;

class DeleteGHCRVersionsDiffblueTest {
  /**
   * Test Builder {@link Builder#ignoreTagsPattern(String)}.
   * <ul>
   *   <li>When {@code Ignore Tags Pattern}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#ignoreTagsPattern(String)}
   */
  @Test
  @DisplayName("Test Builder ignoreTagsPattern(String); when 'Ignore Tags Pattern'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.ignoreTagsPattern(String)"})
  void testBuilderIgnoreTagsPattern_whenIgnoreTagsPattern() {
    // Arrange
    Builder builderResult = Builder.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.ignoreTagsPattern("Ignore Tags Pattern"));
  }

  /**
   * Test Builder {@link Builder#ignoreTagsPattern(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#ignoreTagsPattern(String)}
   */
  @Test
  @DisplayName("Test Builder ignoreTagsPattern(String); when 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.ignoreTagsPattern(String)"})
  void testBuilderIgnoreTagsPattern_whenNull() {
    // Arrange
    Builder builderResult = Builder.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.ignoreTagsPattern(null));
  }

  /**
   * Test Builder {@link Builder#keepMostRecent(String)} with {@code String}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#keepMostRecent(String)}
   */
  @Test
  @DisplayName("Test Builder keepMostRecent(String) with 'String'; when '42'; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.keepMostRecent(String)"})
  void testBuilderKeepMostRecentWithString_when42_thenReturnBuilder() {
    // Arrange
    Builder builderResult = Builder.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.keepMostRecent("42"));
  }

  /**
   * Test Builder {@link Builder#keepMostRecent(String)} with {@code String}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#keepMostRecent(String)}
   */
  @Test
  @DisplayName("Test Builder keepMostRecent(String) with 'String'; when 'null'; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.keepMostRecent(String)"})
  void testBuilderKeepMostRecentWithString_whenNull_thenReturnBuilder() {
    // Arrange
    Builder builderResult = Builder.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.keepMostRecent(null));
  }

  /**
   * Test Builder {@link Builder#properties(Properties)}.
   * <p>
   * Method under test: {@link Builder#properties(Properties)}
   */
  @Test
  @DisplayName("Test Builder properties(Properties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(Properties)"})
  void testBuilderProperties() {
    // Arrange
    Builder builderResult = Builder.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.properties(new Properties()));
  }
}
