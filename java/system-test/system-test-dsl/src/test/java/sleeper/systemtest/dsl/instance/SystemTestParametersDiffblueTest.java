package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.testutils.DummyInstanceProperty;
import sleeper.systemtest.configuration.SystemTestProperty;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.instance.SystemTestParameters.Builder;

class SystemTestParametersDiffblueTest {
  /**
   * Test {@link SystemTestParameters#buildInstanceId(String)}.
   * <p>
   * Method under test: {@link SystemTestParameters#buildInstanceId(String)}
   */
  @Test
  @DisplayName("Test buildInstanceId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SystemTestParameters.buildInstanceId(String)"})
  void testBuildInstanceId() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();

    // Act and Assert
    assertEquals("42-42", buildResult.buildInstanceId("42"));
  }

  /**
   * Test {@link SystemTestParameters#buildJarsBucketName()}.
   * <p>
   * Method under test: {@link SystemTestParameters#buildJarsBucketName()}
   */
  @Test
  @DisplayName("Test buildJarsBucketName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SystemTestParameters.buildJarsBucketName()"})
  void testBuildJarsBucketName() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();

    // Act and Assert
    assertEquals("sleeper-42-jars", buildResult.buildJarsBucketName());
  }

  /**
   * Test {@link SystemTestParameters#buildJarsBucketName(String)} with {@code String}.
   * <p>
   * Method under test: {@link SystemTestParameters#buildJarsBucketName(String)}
   */
  @Test
  @DisplayName("Test buildJarsBucketName(String) with 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SystemTestParameters.buildJarsBucketName(String)"})
  void testBuildJarsBucketNameWithString() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-jars", SystemTestParameters.buildJarsBucketName("42"));
  }

  /**
   * Test {@link SystemTestParameters#buildSystemTestECRRepoName()}.
   * <p>
   * Method under test: {@link SystemTestParameters#buildSystemTestECRRepoName()}
   */
  @Test
  @DisplayName("Test buildSystemTestECRRepoName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SystemTestParameters.buildSystemTestECRRepoName()"})
  void testBuildSystemTestECRRepoName() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();

    // Act and Assert
    assertEquals("42/system-test", buildResult.buildSystemTestECRRepoName());
  }

  /**
   * Test {@link SystemTestParameters#buildSystemTestECRRepoName(String)} with {@code String}.
   * <p>
   * Method under test: {@link SystemTestParameters#buildSystemTestECRRepoName(String)}
   */
  @Test
  @DisplayName("Test buildSystemTestECRRepoName(String) with 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SystemTestParameters.buildSystemTestECRRepoName(String)"})
  void testBuildSystemTestECRRepoNameWithString() {
    // Arrange, Act and Assert
    assertEquals("42/system-test", SystemTestParameters.buildSystemTestECRRepoName("42"));
  }

  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#account(String)}
   *   <li>{@link Builder#forceRedeployInstances(boolean)}
   *   <li>{@link Builder#forceRedeploySystemTest(boolean)}
   *   <li>{@link Builder#forceStateStoreClassname(String)}
   *   <li>{@link Builder#instancePropertiesOverrides(InstanceProperties)}
   *   <li>{@link Builder#outputDirectory(Path)}
   *   <li>{@link Builder#pythonDirectory(Path)}
   *   <li>{@link Builder#region(String)}
   *   <li>{@link Builder#scriptsDirectory(Path)}
   *   <li>{@link Builder#shortTestId(String)}
   *   <li>{@link Builder#subnetIds(String)}
   *   <li>{@link Builder#systemTestClusterEnabled(boolean)}
   *   <li>{@link Builder#systemTestStandalonePropertiesTemplate(SystemTestStandaloneProperties)}
   *   <li>{@link Builder#vpcId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.account(String)", "SystemTestParameters Builder.build()",
      "Builder Builder.forceRedeployInstances(boolean)", "Builder Builder.forceRedeploySystemTest(boolean)",
      "Builder Builder.forceStateStoreClassname(String)",
      "Builder Builder.instancePropertiesOverrides(InstanceProperties)", "Builder Builder.outputDirectory(Path)",
      "Builder Builder.pythonDirectory(Path)", "Builder Builder.region(String)",
      "Builder Builder.scriptsDirectory(Path)", "Builder Builder.shortTestId(String)",
      "Builder Builder.subnetIds(String)", "Builder Builder.systemTestClusterEnabled(boolean)",
      "Builder Builder.systemTestStandalonePropertiesTemplate(SystemTestStandaloneProperties)",
      "Builder Builder.vpcId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Path outputDirectory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Path pythonDirectory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Path scriptsDirectory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Builder systemTestClusterEnabledResult = forceStateStoreClassnameResult
        .instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(outputDirectory)
        .pythonDirectory(pythonDirectory)
        .region("us-east-2")
        .scriptsDirectory(scriptsDirectory)
        .shortTestId("42")
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true);

    // Act
    SystemTestParameters actualBuildResult = systemTestClusterEnabledResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .vpcId("42")
        .build();

    // Assert
    assertEquals("3", actualBuildResult.getAccount());
    assertEquals("42", actualBuildResult.getSystemTestShortId());
    assertEquals("42", actualBuildResult.getVpcId());
    assertEquals("Subnet Ids", actualBuildResult.getSubnetIds());
    File toFileResult = actualBuildResult.getDockerDirectory().toFile();
    assertEquals("docker", toFileResult.getName());
    File toFileResult2 = actualBuildResult.getGeneratedDirectory().toFile();
    assertEquals("generated", toFileResult2.getName());
    File toFileResult3 = actualBuildResult.getJarsDirectory().toFile();
    assertEquals("jars", toFileResult3.getName());
    assertEquals("us-east-2", actualBuildResult.getRegion());
    assertTrue(toFileResult.isAbsolute());
    assertTrue(toFileResult2.isAbsolute());
    assertTrue(toFileResult3.isAbsolute());
    assertTrue(actualBuildResult.isForceRedeployInstances());
    assertTrue(actualBuildResult.isForceRedeploySystemTest());
    assertTrue(actualBuildResult.isSystemTestClusterEnabled());
    assertSame(outputDirectory, actualBuildResult.getOutputDirectory());
    assertSame(pythonDirectory, actualBuildResult.getPythonDirectory());
    assertSame(scriptsDirectory, actualBuildResult.getScriptsDirectory());
  }

  /**
   * Test Builder {@link Builder#findDirectories()}.
   * <p>
   * Method under test: {@link Builder#findDirectories()}
   */
  @Test
  @DisplayName("Test Builder findDirectories()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.findDirectories()"})
  void testBuilderFindDirectories() {
    // Arrange
    Builder builderResult = SystemTestParameters.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.findDirectories());
  }

  /**
   * Test Builder {@link Builder#loadFromSystemProperties()}.
   * <p>
   * Method under test: {@link Builder#loadFromSystemProperties()}
   */
  @Test
  @DisplayName("Test Builder loadFromSystemProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.loadFromSystemProperties()"})
  void testBuilderLoadFromSystemProperties() {
    // Arrange
    Builder builderResult = SystemTestParameters.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.loadFromSystemProperties());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SystemTestParameters#getAccount()}
   *   <li>{@link SystemTestParameters#getOutputDirectory()}
   *   <li>{@link SystemTestParameters#getPythonDirectory()}
   *   <li>{@link SystemTestParameters#getRegion()}
   *   <li>{@link SystemTestParameters#getScriptsDirectory()}
   *   <li>{@link SystemTestParameters#getSubnetIds()}
   *   <li>{@link SystemTestParameters#getSystemTestShortId()}
   *   <li>{@link SystemTestParameters#getVpcId()}
   *   <li>{@link SystemTestParameters#isForceRedeployInstances()}
   *   <li>{@link SystemTestParameters#isForceRedeploySystemTest()}
   *   <li>{@link SystemTestParameters#isSystemTestClusterEnabled()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SystemTestParameters.getAccount()", "Path SystemTestParameters.getOutputDirectory()",
      "Path SystemTestParameters.getPythonDirectory()", "String SystemTestParameters.getRegion()",
      "Path SystemTestParameters.getScriptsDirectory()", "String SystemTestParameters.getSubnetIds()",
      "String SystemTestParameters.getSystemTestShortId()", "String SystemTestParameters.getVpcId()",
      "boolean SystemTestParameters.isForceRedeployInstances()",
      "boolean SystemTestParameters.isForceRedeploySystemTest()",
      "boolean SystemTestParameters.isSystemTestClusterEnabled()"})
  void testGettersAndSetters() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Path outputDirectory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Path pythonDirectory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Path scriptsDirectory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(outputDirectory)
        .pythonDirectory(pythonDirectory)
        .region("us-east-2")
        .scriptsDirectory(scriptsDirectory)
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();

    // Act
    String actualAccount = buildResult.getAccount();
    Path actualOutputDirectory = buildResult.getOutputDirectory();
    Path actualPythonDirectory = buildResult.getPythonDirectory();
    String actualRegion = buildResult.getRegion();
    Path actualScriptsDirectory = buildResult.getScriptsDirectory();
    String actualSubnetIds = buildResult.getSubnetIds();
    String actualSystemTestShortId = buildResult.getSystemTestShortId();
    String actualVpcId = buildResult.getVpcId();
    boolean actualIsForceRedeployInstancesResult = buildResult.isForceRedeployInstances();
    boolean actualIsForceRedeploySystemTestResult = buildResult.isForceRedeploySystemTest();

    // Assert
    assertEquals("3", actualAccount);
    assertEquals("42", actualSystemTestShortId);
    assertEquals("42", actualVpcId);
    assertEquals("Subnet Ids", actualSubnetIds);
    assertEquals("us-east-2", actualRegion);
    assertTrue(actualIsForceRedeployInstancesResult);
    assertTrue(actualIsForceRedeploySystemTestResult);
    assertTrue(buildResult.isSystemTestClusterEnabled());
    assertSame(outputDirectory, actualOutputDirectory);
    assertSame(pythonDirectory, actualPythonDirectory);
    assertSame(scriptsDirectory, actualScriptsDirectory);
  }

  /**
   * Test {@link SystemTestParameters#getJarsDirectory()}.
   * <p>
   * Method under test: {@link SystemTestParameters#getJarsDirectory()}
   */
  @Test
  @DisplayName("Test getJarsDirectory()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path SystemTestParameters.getJarsDirectory()"})
  void testGetJarsDirectory() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();

    // Act and Assert
    File toFileResult = buildResult.getJarsDirectory().toFile();
    assertEquals("jars", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link SystemTestParameters#getDockerDirectory()}.
   * <p>
   * Method under test: {@link SystemTestParameters#getDockerDirectory()}
   */
  @Test
  @DisplayName("Test getDockerDirectory()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path SystemTestParameters.getDockerDirectory()"})
  void testGetDockerDirectory() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();

    // Act and Assert
    File toFileResult = buildResult.getDockerDirectory().toFile();
    assertEquals("docker", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link SystemTestParameters#getGeneratedDirectory()}.
   * <p>
   * Method under test: {@link SystemTestParameters#getGeneratedDirectory()}
   */
  @Test
  @DisplayName("Test getGeneratedDirectory()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path SystemTestParameters.getGeneratedDirectory()"})
  void testGetGeneratedDirectory() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();

    // Act and Assert
    File toFileResult = buildResult.getGeneratedDirectory().toFile();
    assertEquals("generated", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link SystemTestParameters#buildSystemTestStandaloneProperties()}.
   * <p>
   * Method under test: {@link SystemTestParameters#buildSystemTestStandaloneProperties()}
   */
  @Test
  @DisplayName("Test buildSystemTestStandaloneProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestStandaloneProperties SystemTestParameters.buildSystemTestStandaloneProperties()"})
  void testBuildSystemTestStandaloneProperties() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();

    // Act
    SystemTestStandaloneProperties actualBuildSystemTestStandalonePropertiesResult = buildResult
        .buildSystemTestStandaloneProperties();

    // Assert
    SleeperPropertyIndex<SystemTestProperty> propertiesIndex = actualBuildSystemTestStandalonePropertiesResult
        .getPropertiesIndex();
    assertEquals(24, propertiesIndex.getUserDefined().size());
    assertEquals(27, propertiesIndex.getAll().size());
    assertEquals(3, propertiesIndex.getCdkDefined().size());
    Properties properties = actualBuildSystemTestStandalonePropertiesResult.getProperties();
    assertEquals(7, properties.size());
    assertTrue(properties.containsKey("sleeper.systemtest.repo"));
    assertTrue(properties.containsKey("sleeper.systemtest.standalone.account"));
    assertTrue(properties.containsKey("sleeper.systemtest.standalone.id"));
    assertTrue(properties.containsKey("sleeper.systemtest.standalone.region"));
    assertTrue(properties.containsKey("sleeper.systemtest.standalone.vpc"));
    String expectedString = Boolean.TRUE.toString();
    assertEquals(expectedString, properties.get("sleeper.systemtest.cluster.enabled"));
    assertEquals(properties, actualBuildSystemTestStandalonePropertiesResult.toMap());
  }

  /**
   * Test {@link SystemTestParameters#buildSystemTestStandaloneProperties()}.
   * <p>
   * Method under test: {@link SystemTestParameters#buildSystemTestStandaloneProperties()}
   */
  @Test
  @DisplayName("Test buildSystemTestStandaloneProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestStandaloneProperties SystemTestParameters.buildSystemTestStandaloneProperties()"})
  void testBuildSystemTestStandaloneProperties2() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(false)
        .vpcId("42")
        .build();

    // Act
    SystemTestStandaloneProperties actualBuildSystemTestStandalonePropertiesResult = buildResult
        .buildSystemTestStandaloneProperties();

    // Assert
    SleeperPropertyIndex<SystemTestProperty> propertiesIndex = actualBuildSystemTestStandalonePropertiesResult
        .getPropertiesIndex();
    assertEquals(24, propertiesIndex.getUserDefined().size());
    assertEquals(27, propertiesIndex.getAll().size());
    assertEquals(3, propertiesIndex.getCdkDefined().size());
    Properties properties = actualBuildSystemTestStandalonePropertiesResult.getProperties();
    assertEquals(7, properties.size());
    assertTrue(properties.containsKey("sleeper.systemtest.repo"));
    assertTrue(properties.containsKey("sleeper.systemtest.standalone.account"));
    assertTrue(properties.containsKey("sleeper.systemtest.standalone.id"));
    assertTrue(properties.containsKey("sleeper.systemtest.standalone.region"));
    assertTrue(properties.containsKey("sleeper.systemtest.standalone.vpc"));
    String expectedString = Boolean.FALSE.toString();
    assertEquals(expectedString, properties.get("sleeper.systemtest.cluster.enabled"));
    assertEquals(properties, actualBuildSystemTestStandalonePropertiesResult.toMap());
  }

  /**
   * Test {@link SystemTestParameters#isInstancePropertyOverridden(InstanceProperty)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestParameters#isInstancePropertyOverridden(InstanceProperty)}
   */
  @Test
  @DisplayName("Test isInstancePropertyOverridden(InstanceProperty); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SystemTestParameters.isInstancePropertyOverridden(InstanceProperty)"})
  void testIsInstancePropertyOverridden_thenReturnFalse() {
    // Arrange
    Builder forceStateStoreClassnameResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname");
    Builder shortTestIdResult = forceStateStoreClassnameResult.instancePropertiesOverrides(new InstanceProperties())
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();

    // Act and Assert
    assertFalse(buildResult.isInstancePropertyOverridden(new DummyInstanceProperty("Property Name")));
  }

  /**
   * Test {@link SystemTestParameters#isInstancePropertyOverridden(InstanceProperty)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestParameters#isInstancePropertyOverridden(InstanceProperty)}
   */
  @Test
  @DisplayName("Test isInstancePropertyOverridden(InstanceProperty); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SystemTestParameters.isInstancePropertyOverridden(InstanceProperty)"})
  void testIsInstancePropertyOverridden_thenReturnTrue() {
    // Arrange
    InstanceProperties instancePropertiesOverrides = new InstanceProperties();
    instancePropertiesOverrides.set(new DummyInstanceProperty("Property Name"), "42");
    Builder shortTestIdResult = SystemTestParameters.builder()
        .account("3")
        .forceRedeployInstances(true)
        .forceRedeploySystemTest(true)
        .forceStateStoreClassname("Force State Store Classname")
        .instancePropertiesOverrides(instancePropertiesOverrides)
        .outputDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .pythonDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .region("us-east-2")
        .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .shortTestId("42");
    SystemTestParameters buildResult = shortTestIdResult
        .systemTestStandalonePropertiesTemplate(new SystemTestStandaloneProperties())
        .subnetIds("Subnet Ids")
        .systemTestClusterEnabled(true)
        .vpcId("42")
        .build();

    // Act and Assert
    assertTrue(buildResult.isInstancePropertyOverridden(new DummyInstanceProperty("Property Name")));
  }
}
