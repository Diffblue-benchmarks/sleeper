package sleeper.ingest.runner.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.ingest.runner.impl.IngestCoordinator.Builder;

class IngestCoordinatorDiffblueTest {
  /**
   * Test Builder {@link Builder#instanceProperties(InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return {@link Builder#Builder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#instanceProperties(InstanceProperties)}
   */
  @Test
  @DisplayName("Test Builder instanceProperties(InstanceProperties); when InstanceProperties(); then return Builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.instanceProperties(InstanceProperties)"})
  void testBuilderInstanceProperties_whenInstanceProperties_thenReturnBuilder() {
    // Arrange
    Builder<Object> builder = new Builder<>();

    // Act and Assert
    assertSame(builder, builder.instanceProperties(new InstanceProperties()));
  }

  /**
   * Test Builder {@link Builder#tableProperties(TableProperties)}.
   * <ul>
   *   <li>Then return {@link Builder#Builder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#tableProperties(TableProperties)}
   */
  @Test
  @DisplayName("Test Builder tableProperties(TableProperties); then return Builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.tableProperties(TableProperties)"})
  void testBuilderTableProperties_thenReturnBuilder() {
    // Arrange
    Builder<Object> builder = new Builder<>();

    // Act and Assert
    assertSame(builder, builder.tableProperties(new TableProperties(new InstanceProperties())));
  }
}
