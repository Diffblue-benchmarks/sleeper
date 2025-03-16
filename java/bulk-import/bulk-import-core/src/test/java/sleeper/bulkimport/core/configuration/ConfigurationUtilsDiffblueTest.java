package sleeper.bulkimport.core.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.validation.EmrInstanceArchitecture;

class ConfigurationUtilsDiffblueTest {
  /**
   * Test {@link ConfigurationUtils#getSparkConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)}.
   * <ul>
   *   <li>Then return size is twenty-six.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationUtils#getSparkConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test getSparkConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture); then return size is twenty-six")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map ConfigurationUtils.getSparkConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)"})
  void testGetSparkConfigurationFromInstanceProperties_thenReturnSizeIsTwentySix() {
    // Arrange and Act
    Map<String, String> actualSparkConfigurationFromInstanceProperties = ConfigurationUtils
        .getSparkConfigurationFromInstanceProperties(new InstanceProperties(), EmrInstanceArchitecture.X86_64);

    // Assert
    assertEquals(26, actualSparkConfigurationFromInstanceProperties.size());
    assertEquals("16g", actualSparkConfigurationFromInstanceProperties.get("spark.driver.memory"));
    assertEquals("16g", actualSparkConfigurationFromInstanceProperties.get("spark.executor.memory"));
    assertEquals("1706m", actualSparkConfigurationFromInstanceProperties.get("spark.executor.memoryOverhead"));
    assertEquals("29", actualSparkConfigurationFromInstanceProperties.get("spark.executor.instances"));
    assertEquals("290", actualSparkConfigurationFromInstanceProperties.get("spark.default.parallelism"));
    assertEquals("290", actualSparkConfigurationFromInstanceProperties.get("spark.sql.shuffle.partitions"));
    assertEquals("5",
        actualSparkConfigurationFromInstanceProperties.get("spark.yarn.scheduler.reporterThread.maxFailures"));
    assertEquals("800s", actualSparkConfigurationFromInstanceProperties.get("spark.network.timeout"));
    String expectedGetResult = Boolean.TRUE.toString();
    assertEquals(expectedGetResult, actualSparkConfigurationFromInstanceProperties.get("spark.rdd.compress"));
    String expectedGetResult2 = Boolean.TRUE.toString();
    assertEquals(expectedGetResult2, actualSparkConfigurationFromInstanceProperties.get("spark.shuffle.compress"));
    String expectedGetResult3 = Boolean.TRUE.toString();
    assertEquals(expectedGetResult3,
        actualSparkConfigurationFromInstanceProperties.get("spark.shuffle.spill.compress"));
  }

  /**
   * Test {@link ConfigurationUtils#getSparkConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)}.
   * <ul>
   *   <li>When {@link EmrInstanceArchitecture#ARM64}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationUtils#getSparkConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test getSparkConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture); when ARM64")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map ConfigurationUtils.getSparkConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)"})
  void testGetSparkConfigurationFromInstanceProperties_whenArm64() {
    // Arrange and Act
    Map<String, String> actualSparkConfigurationFromInstanceProperties = ConfigurationUtils
        .getSparkConfigurationFromInstanceProperties(new InstanceProperties(), EmrInstanceArchitecture.ARM64);

    // Assert
    assertEquals(26, actualSparkConfigurationFromInstanceProperties.size());
    assertEquals("16g", actualSparkConfigurationFromInstanceProperties.get("spark.driver.memory"));
    assertEquals("16g", actualSparkConfigurationFromInstanceProperties.get("spark.executor.memory"));
    assertEquals("1706m", actualSparkConfigurationFromInstanceProperties.get("spark.executor.memoryOverhead"));
    assertEquals("29", actualSparkConfigurationFromInstanceProperties.get("spark.executor.instances"));
    assertEquals("290", actualSparkConfigurationFromInstanceProperties.get("spark.default.parallelism"));
    assertEquals("290", actualSparkConfigurationFromInstanceProperties.get("spark.sql.shuffle.partitions"));
    assertEquals("5",
        actualSparkConfigurationFromInstanceProperties.get("spark.yarn.scheduler.reporterThread.maxFailures"));
    assertEquals("800s", actualSparkConfigurationFromInstanceProperties.get("spark.network.timeout"));
    String expectedGetResult = Boolean.TRUE.toString();
    assertEquals(expectedGetResult, actualSparkConfigurationFromInstanceProperties.get("spark.rdd.compress"));
    String expectedGetResult2 = Boolean.TRUE.toString();
    assertEquals(expectedGetResult2, actualSparkConfigurationFromInstanceProperties.get("spark.shuffle.compress"));
    String expectedGetResult3 = Boolean.TRUE.toString();
    assertEquals(expectedGetResult3,
        actualSparkConfigurationFromInstanceProperties.get("spark.shuffle.spill.compress"));
  }

  /**
   * Test {@link ConfigurationUtils#getSparkServerlessConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)}.
   * <p>
   * Method under test: {@link ConfigurationUtils#getSparkServerlessConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test getSparkServerlessConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map ConfigurationUtils.getSparkServerlessConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)"})
  void testGetSparkServerlessConfigurationFromInstanceProperties() {
    // Arrange and Act
    Map<String, String> actualSparkServerlessConfigurationFromInstanceProperties = ConfigurationUtils
        .getSparkServerlessConfigurationFromInstanceProperties(new InstanceProperties(),
            EmrInstanceArchitecture.X86_64);

    // Assert
    assertEquals(21, actualSparkServerlessConfigurationFromInstanceProperties.size());
    assertEquals("/usr/lib/jvm/java-17-amazon-corretto.x86_64",
        actualSparkServerlessConfigurationFromInstanceProperties.get("spark.executorEnv.JAVA_HOME"));
    assertEquals("0.30", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.memory.storageFraction"));
    assertEquals("0.75", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.speculation.quantile"));
    assertEquals("16G", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.driver.memory"));
    assertEquals("16G", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.executor.memory"));
    assertEquals("4", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.driver.cores"));
    assertEquals("4", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.executor.cores"));
    assertEquals("60s",
        actualSparkServerlessConfigurationFromInstanceProperties.get("spark.executor.heartbeatInterval"));
    assertEquals("800s", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.network.timeout"));
    String expectedGetResult = Boolean.FALSE.toString();
    assertEquals(expectedGetResult, actualSparkServerlessConfigurationFromInstanceProperties.get("spark.speculation"));
    String expectedGetResult2 = Boolean.TRUE.toString();
    assertEquals(expectedGetResult2,
        actualSparkServerlessConfigurationFromInstanceProperties.get("spark.rdd.compress"));
  }

  /**
   * Test {@link ConfigurationUtils#getSparkServerlessConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)}.
   * <p>
   * Method under test: {@link ConfigurationUtils#getSparkServerlessConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test getSparkServerlessConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map ConfigurationUtils.getSparkServerlessConfigurationFromInstanceProperties(InstanceProperties, EmrInstanceArchitecture)"})
  void testGetSparkServerlessConfigurationFromInstanceProperties2() {
    // Arrange and Act
    Map<String, String> actualSparkServerlessConfigurationFromInstanceProperties = ConfigurationUtils
        .getSparkServerlessConfigurationFromInstanceProperties(new InstanceProperties(), EmrInstanceArchitecture.ARM64);

    // Assert
    assertEquals(21, actualSparkServerlessConfigurationFromInstanceProperties.size());
    assertEquals("/usr/lib/jvm/java-17-amazon-corretto.aarch64",
        actualSparkServerlessConfigurationFromInstanceProperties.get("spark.executorEnv.JAVA_HOME"));
    assertEquals("0.30", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.memory.storageFraction"));
    assertEquals("0.75", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.speculation.quantile"));
    assertEquals("16G", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.driver.memory"));
    assertEquals("16G", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.executor.memory"));
    assertEquals("4", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.driver.cores"));
    assertEquals("4", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.executor.cores"));
    assertEquals("60s",
        actualSparkServerlessConfigurationFromInstanceProperties.get("spark.executor.heartbeatInterval"));
    assertEquals("800s", actualSparkServerlessConfigurationFromInstanceProperties.get("spark.network.timeout"));
    String expectedGetResult = Boolean.FALSE.toString();
    assertEquals(expectedGetResult, actualSparkServerlessConfigurationFromInstanceProperties.get("spark.speculation"));
    String expectedGetResult2 = Boolean.TRUE.toString();
    assertEquals(expectedGetResult2,
        actualSparkServerlessConfigurationFromInstanceProperties.get("spark.rdd.compress"));
  }

  /**
   * Test {@link ConfigurationUtils#getSparkEMRConfiguration()}.
   * <p>
   * Method under test: {@link ConfigurationUtils#getSparkEMRConfiguration()}
   */
  @Test
  @DisplayName("Test getSparkEMRConfiguration()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map ConfigurationUtils.getSparkEMRConfiguration()"})
  void testGetSparkEMRConfiguration() {
    // Arrange and Act
    Map<String, String> actualSparkEMRConfiguration = ConfigurationUtils.getSparkEMRConfiguration();

    // Assert
    assertEquals(1, actualSparkEMRConfiguration.size());
    String expectedGetResult = Boolean.FALSE.toString();
    assertEquals(expectedGetResult, actualSparkEMRConfiguration.get("maximizeResourceAllocation"));
  }

  /**
   * Test {@link ConfigurationUtils#getYarnConfiguration()}.
   * <p>
   * Method under test: {@link ConfigurationUtils#getYarnConfiguration()}
   */
  @Test
  @DisplayName("Test getYarnConfiguration()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map ConfigurationUtils.getYarnConfiguration()"})
  void testGetYarnConfiguration() {
    // Arrange and Act
    Map<String, String> actualYarnConfiguration = ConfigurationUtils.getYarnConfiguration();

    // Assert
    assertEquals(2, actualYarnConfiguration.size());
    String expectedGetResult = Boolean.FALSE.toString();
    assertEquals(expectedGetResult, actualYarnConfiguration.get("yarn.nodemanager.pmem-check-enabled"));
    String expectedGetResult2 = Boolean.FALSE.toString();
    assertEquals(expectedGetResult2, actualYarnConfiguration.get("yarn.nodemanager.vmem-check-enabled"));
  }

  /**
   * Test {@link ConfigurationUtils#getMapRedSiteConfiguration()}.
   * <p>
   * Method under test: {@link ConfigurationUtils#getMapRedSiteConfiguration()}
   */
  @Test
  @DisplayName("Test getMapRedSiteConfiguration()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map ConfigurationUtils.getMapRedSiteConfiguration()"})
  void testGetMapRedSiteConfiguration() {
    // Arrange and Act
    Map<String, String> actualMapRedSiteConfiguration = ConfigurationUtils.getMapRedSiteConfiguration();

    // Assert
    assertEquals(1, actualMapRedSiteConfiguration.size());
    String expectedGetResult = Boolean.TRUE.toString();
    assertEquals(expectedGetResult, actualMapRedSiteConfiguration.get("mapreduce.map.output.compress"));
  }

  /**
   * Test {@link ConfigurationUtils#getJavaHomeConfiguration(EmrInstanceArchitecture)}.
   * <p>
   * Method under test: {@link ConfigurationUtils#getJavaHomeConfiguration(EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test getJavaHomeConfiguration(EmrInstanceArchitecture)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map ConfigurationUtils.getJavaHomeConfiguration(EmrInstanceArchitecture)"})
  void testGetJavaHomeConfiguration() {
    // Arrange and Act
    Map<String, String> actualJavaHomeConfiguration = ConfigurationUtils
        .getJavaHomeConfiguration(EmrInstanceArchitecture.X86_64);

    // Assert
    assertEquals(1, actualJavaHomeConfiguration.size());
    assertEquals("/usr/lib/jvm/java-17-amazon-corretto.x86_64", actualJavaHomeConfiguration.get("JAVA_HOME"));
  }

  /**
   * Test {@link ConfigurationUtils#getJavaHomeConfiguration(EmrInstanceArchitecture)}.
   * <p>
   * Method under test: {@link ConfigurationUtils#getJavaHomeConfiguration(EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test getJavaHomeConfiguration(EmrInstanceArchitecture)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map ConfigurationUtils.getJavaHomeConfiguration(EmrInstanceArchitecture)"})
  void testGetJavaHomeConfiguration2() {
    // Arrange and Act
    Map<String, String> actualJavaHomeConfiguration = ConfigurationUtils
        .getJavaHomeConfiguration(EmrInstanceArchitecture.ARM64);

    // Assert
    assertEquals(1, actualJavaHomeConfiguration.size());
    assertEquals("/usr/lib/jvm/java-17-amazon-corretto.aarch64", actualJavaHomeConfiguration.get("JAVA_HOME"));
  }

  /**
   * Test {@link ConfigurationUtils#getJavaHome(EmrInstanceArchitecture)}.
   * <ul>
   *   <li>When {@link EmrInstanceArchitecture#ARM64}.</li>
   *   <li>Then return {@code /usr/lib/jvm/java-17-amazon-corretto.aarch64}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationUtils#getJavaHome(EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test getJavaHome(EmrInstanceArchitecture); when ARM64; then return '/usr/lib/jvm/java-17-amazon-corretto.aarch64'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ConfigurationUtils.getJavaHome(EmrInstanceArchitecture)"})
  void testGetJavaHome_whenArm64_thenReturnUsrLibJvmJava17AmazonCorrettoAarch64() {
    // Arrange, Act and Assert
    assertEquals("/usr/lib/jvm/java-17-amazon-corretto.aarch64",
        ConfigurationUtils.getJavaHome(EmrInstanceArchitecture.ARM64));
  }

  /**
   * Test {@link ConfigurationUtils#getJavaHome(EmrInstanceArchitecture)}.
   * <ul>
   *   <li>When {@code X86_64}.</li>
   *   <li>Then return {@code /usr/lib/jvm/java-17-amazon-corretto.x86_64}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationUtils#getJavaHome(EmrInstanceArchitecture)}
   */
  @Test
  @DisplayName("Test getJavaHome(EmrInstanceArchitecture); when 'X86_64'; then return '/usr/lib/jvm/java-17-amazon-corretto.x86_64'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ConfigurationUtils.getJavaHome(EmrInstanceArchitecture)"})
  void testGetJavaHome_whenX8664_thenReturnUsrLibJvmJava17AmazonCorrettoX8664() {
    // Arrange, Act and Assert
    assertEquals("/usr/lib/jvm/java-17-amazon-corretto.x86_64",
        ConfigurationUtils.getJavaHome(EmrInstanceArchitecture.X86_64));
  }
}
