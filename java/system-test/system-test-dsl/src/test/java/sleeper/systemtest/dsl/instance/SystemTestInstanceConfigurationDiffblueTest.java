package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.systemtest.dsl.instance.SystemTestInstanceConfiguration.Builder;

class SystemTestInstanceConfigurationDiffblueTest {
  /**
   * Test {@link SystemTestInstanceConfiguration#builder()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SystemTestInstanceConfiguration#builder()}
   *   <li>{@link SystemTestInstanceConfiguration#deployConfig(Supplier)}
   *   <li>{@link SystemTestInstanceConfiguration#disableSchedules(Set)}
   *   <li>{@link SystemTestInstanceConfiguration#shortName(String)}
   *   <li>{@link SystemTestInstanceConfiguration#useSystemTestIngestSourceBucket(boolean)}
   * </ul>
   */
  @Test
  @DisplayName("Test builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestInstanceConfiguration Builder.build()", "Builder Builder.deployConfig(Supplier)",
      "Builder Builder.disableSchedules(Set)", "Builder Builder.shortName(String)",
      "Builder Builder.useSystemTestIngestSourceBucket(boolean)"})
  void testBuilder() {
    // Arrange and Act
    Builder actualDeployConfigResult = SystemTestInstanceConfiguration.builder().deployConfig(mock(Supplier.class));
    Builder actualShortNameResult = actualDeployConfigResult.disableSchedules(new HashSet<>()).shortName("Short Name");

    // Assert
    assertSame(actualShortNameResult, actualShortNameResult.useSystemTestIngestSourceBucket(true));
  }
}
