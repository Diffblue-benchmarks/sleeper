package sleeper.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;

class CompactionTaskRequirementsDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionTaskRequirements#CompactionTaskRequirements(int, int)}
   *   <li>{@link CompactionTaskRequirements#getCpu()}
   *   <li>{@link CompactionTaskRequirements#getMemoryLimitMiB()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionTaskRequirements.<init>(int, int)", "int CompactionTaskRequirements.getCpu()",
      "int CompactionTaskRequirements.getMemoryLimitMiB()"})
  void testGettersAndSetters() {
    // Arrange and Act
    CompactionTaskRequirements actualCompactionTaskRequirements = new CompactionTaskRequirements(1, 1);
    int actualCpu = actualCompactionTaskRequirements.getCpu();

    // Assert
    assertEquals(1, actualCpu);
    assertEquals(1, actualCompactionTaskRequirements.getMemoryLimitMiB());
  }

  /**
   * Test {@link CompactionTaskRequirements#getArchRequirements(String, InstanceProperties)}.
   * <ul>
   *   <li>When {@code Architecture}.</li>
   *   <li>Then return Cpu is {@code 1024}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskRequirements#getArchRequirements(String, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getArchRequirements(String, InstanceProperties); when 'Architecture'; then return Cpu is '1024'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "CompactionTaskRequirements CompactionTaskRequirements.getArchRequirements(String, InstanceProperties)"})
  void testGetArchRequirements_whenArchitecture_thenReturnCpuIs1024() {
    // Arrange and Act
    CompactionTaskRequirements actualArchRequirements = CompactionTaskRequirements.getArchRequirements("Architecture",
        new InstanceProperties());

    // Assert
    assertEquals(1024, actualArchRequirements.getCpu());
    assertEquals(4096, actualArchRequirements.getMemoryLimitMiB());
  }

  /**
   * Test {@link CompactionTaskRequirements#getArchRequirements(String, InstanceProperties)}.
   * <ul>
   *   <li>When {@code ARM}.</li>
   *   <li>Then return Cpu is {@code 1024}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionTaskRequirements#getArchRequirements(String, InstanceProperties)}
   */
  @Test
  @DisplayName("Test getArchRequirements(String, InstanceProperties); when 'ARM'; then return Cpu is '1024'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "CompactionTaskRequirements CompactionTaskRequirements.getArchRequirements(String, InstanceProperties)"})
  void testGetArchRequirements_whenArm_thenReturnCpuIs1024() {
    // Arrange and Act
    CompactionTaskRequirements actualArchRequirements = CompactionTaskRequirements.getArchRequirements("ARM",
        new InstanceProperties());

    // Assert
    assertEquals(1024, actualArchRequirements.getCpu());
    assertEquals(4096, actualArchRequirements.getMemoryLimitMiB());
  }
}
