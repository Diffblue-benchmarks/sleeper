package sleeper.clients.deploy;

import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.deploy.SyncJars.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.testutils.DummyInstanceProperty;

class SyncJarsDiffblueTest {
  /**
   * Test Builder {@link Builder#instanceProperties(InstanceProperties)}.
   * <ul>
   *   <li>Given {@link DummyInstanceProperty#DummyInstanceProperty(String)} with {@code Property Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#instanceProperties(InstanceProperties)}
   */
  @Test
  @DisplayName("Test Builder instanceProperties(InstanceProperties); given DummyInstanceProperty(String) with 'Property Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.instanceProperties(InstanceProperties)"})
  void testBuilderInstanceProperties_givenDummyInstancePropertyWithPropertyName() {
    // Arrange
    Builder builderResult = SyncJars.builder();

    InstanceProperties instanceProperties = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty("Property Name");
    instanceProperties.addToListIfMissing(dummyInstanceProperty, new ArrayList<>());

    // Act and Assert
    assertSame(builderResult, builderResult.instanceProperties(instanceProperties));
  }

  /**
   * Test Builder {@link Builder#instanceProperties(InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#instanceProperties(InstanceProperties)}
   */
  @Test
  @DisplayName("Test Builder instanceProperties(InstanceProperties); when InstanceProperties(); then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.instanceProperties(InstanceProperties)"})
  void testBuilderInstanceProperties_whenInstanceProperties_thenReturnBuilder() {
    // Arrange
    Builder builderResult = SyncJars.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.instanceProperties(new InstanceProperties()));
  }
}
