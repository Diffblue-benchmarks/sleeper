package sleeper.systemtest.drivers.cdk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;

class TearDownTestInstanceDiffblueTest {
  /**
   * Test {@link TearDownTestInstance#getSystemTestEcsClusters(InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link TearDownTestInstance#getSystemTestEcsClusters(InstanceProperties)}
   */
  @Test
  @DisplayName("Test getSystemTestEcsClusters(InstanceProperties); when InstanceProperties(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List TearDownTestInstance.getSystemTestEcsClusters(InstanceProperties)"})
  void testGetSystemTestEcsClusters_whenInstanceProperties_thenReturnEmpty() {
    // Arrange and Act
    List<String> actualSystemTestEcsClusters = TearDownTestInstance.getSystemTestEcsClusters(new InstanceProperties());

    // Assert
    assertTrue(actualSystemTestEcsClusters.isEmpty());
  }

  /**
   * Test {@link TearDownTestInstance#getSystemTestEcrRepositories(InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link TearDownTestInstance#getSystemTestEcrRepositories(InstanceProperties)}
   */
  @Test
  @DisplayName("Test getSystemTestEcrRepositories(InstanceProperties); when InstanceProperties(); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List TearDownTestInstance.getSystemTestEcrRepositories(InstanceProperties)"})
  void testGetSystemTestEcrRepositories_whenInstanceProperties_thenReturnSizeIsOne() {
    // Arrange and Act
    List<String> actualSystemTestEcrRepositories = TearDownTestInstance
        .getSystemTestEcrRepositories(new InstanceProperties());

    // Assert
    assertEquals(1, actualSystemTestEcrRepositories.size());
    assertEquals("null/system-test", actualSystemTestEcrRepositories.get(0));
  }
}
