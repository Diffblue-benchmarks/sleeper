package sleeper.systemtest.drivers.util;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.systemtest.drivers.util.SystemTestClients.Builder;

class SystemTestClientsDiffblueTest {
  /**
   * Test Builder {@link Builder#configureHadoopSetter(Consumer)}.
   * <p>
   * Method under test: {@link Builder#configureHadoopSetter(Consumer)}
   */
  @Test
  @DisplayName("Test Builder configureHadoopSetter(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.configureHadoopSetter(Consumer)"})
  void testBuilderConfigureHadoopSetter() {
    // Arrange
    Builder builderResult = SystemTestClients.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.configureHadoopSetter(mock(Consumer.class)));
  }
}
