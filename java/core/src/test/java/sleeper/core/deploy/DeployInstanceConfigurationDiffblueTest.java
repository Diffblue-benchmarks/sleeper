package sleeper.core.deploy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.deploy.DeployInstanceConfiguration.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

class DeployInstanceConfigurationDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#instanceProperties(InstanceProperties)}
   *   <li>{@link Builder#tableProperties(List)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Builder.<init>()", "DeployInstanceConfiguration Builder.build()",
      "Builder Builder.instanceProperties(InstanceProperties)", "Builder Builder.tableProperties(List)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = DeployInstanceConfiguration.builder();
    InstanceProperties instanceProperties = new InstanceProperties();
    Builder instancePropertiesResult = builderResult.instanceProperties(instanceProperties);
    Builder tablePropertiesResult = instancePropertiesResult.tableProperties(new ArrayList<>());
    TableProperties tableProperties = PopulatePropertiesTestHelper.generateTestTableProperties();

    // Act
    DeployInstanceConfiguration actualBuildResult = tablePropertiesResult.tableProperties(tableProperties).build();

    // Assert
    List<TableProperties> tableProperties2 = actualBuildResult.getTableProperties();
    assertEquals(1, tableProperties2.size());
    assertSame(instanceProperties, actualBuildResult.getInstanceProperties());
    assertSame(tableProperties, tableProperties2.get(0));
  }

  /**
   * Test Builder {@link Builder#tableProperties(TableProperties)} with {@code TableProperties}.
   * <ul>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#tableProperties(TableProperties)}
   */
  @Test
  @DisplayName("Test Builder tableProperties(TableProperties) with 'TableProperties'; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.tableProperties(TableProperties)"})
  void testBuilderTablePropertiesWithTableProperties_thenReturnBuilder() {
    // Arrange
    Builder builderResult = DeployInstanceConfiguration.builder();

    // Act and Assert
    assertSame(builderResult,
        builderResult.tableProperties(PopulatePropertiesTestHelper.generateTestTableProperties()));
  }

  /**
   * Test {@link DeployInstanceConfiguration#DeployInstanceConfiguration(InstanceProperties, List)}.
   * <ul>
   *   <li>Then return TableProperties is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployInstanceConfiguration#DeployInstanceConfiguration(InstanceProperties, List)}
   */
  @Test
  @DisplayName("Test new DeployInstanceConfiguration(InstanceProperties, List); then return TableProperties is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployInstanceConfiguration.<init>(InstanceProperties, List)"})
  void testNewDeployInstanceConfiguration_thenReturnTablePropertiesIsArrayList() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    ArrayList<TableProperties> tableProperties = new ArrayList<>();
    tableProperties.add(PopulatePropertiesTestHelper.generateTestTableProperties());

    // Act and Assert
    assertSame(tableProperties,
        (new DeployInstanceConfiguration(instanceProperties, tableProperties)).getTableProperties());
  }

  /**
   * Test {@link DeployInstanceConfiguration#DeployInstanceConfiguration(InstanceProperties, TableProperties)}.
   * <ul>
   *   <li>Then return TableProperties size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployInstanceConfiguration#DeployInstanceConfiguration(InstanceProperties, TableProperties)}
   */
  @Test
  @DisplayName("Test new DeployInstanceConfiguration(InstanceProperties, TableProperties); then return TableProperties size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployInstanceConfiguration.<init>(InstanceProperties, TableProperties)"})
  void testNewDeployInstanceConfiguration_thenReturnTablePropertiesSizeIsOne() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = PopulatePropertiesTestHelper.generateTestTableProperties();

    // Act
    DeployInstanceConfiguration actualDeployInstanceConfiguration = new DeployInstanceConfiguration(instanceProperties,
        tableProperties);

    // Assert
    List<TableProperties> tableProperties2 = actualDeployInstanceConfiguration.getTableProperties();
    assertEquals(1, tableProperties2.size());
    assertSame(instanceProperties, actualDeployInstanceConfiguration.getInstanceProperties());
    assertSame(tableProperties, tableProperties2.get(0));
  }

  /**
   * Test {@link DeployInstanceConfiguration#DeployInstanceConfiguration(InstanceProperties, List)}.
   * <ul>
   *   <li>Then return TableProperties size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployInstanceConfiguration#DeployInstanceConfiguration(InstanceProperties, List)}
   */
  @Test
  @DisplayName("Test new DeployInstanceConfiguration(InstanceProperties, List); then return TableProperties size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployInstanceConfiguration.<init>(InstanceProperties, List)"})
  void testNewDeployInstanceConfiguration_thenReturnTablePropertiesSizeIsTwo() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    ArrayList<TableProperties> tableProperties = new ArrayList<>();
    tableProperties.add(PopulatePropertiesTestHelper.generateTestTableProperties());
    TableProperties generateTestTablePropertiesResult = PopulatePropertiesTestHelper.generateTestTableProperties();
    tableProperties.add(generateTestTablePropertiesResult);

    // Act and Assert
    List<TableProperties> tableProperties2 = (new DeployInstanceConfiguration(instanceProperties, tableProperties))
        .getTableProperties();
    assertEquals(2, tableProperties2.size());
    assertSame(generateTestTablePropertiesResult, tableProperties2.get(1));
  }

  /**
   * Test {@link DeployInstanceConfiguration#DeployInstanceConfiguration(InstanceProperties, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return TableProperties Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployInstanceConfiguration#DeployInstanceConfiguration(InstanceProperties, List)}
   */
  @Test
  @DisplayName("Test new DeployInstanceConfiguration(InstanceProperties, List); when ArrayList(); then return TableProperties Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployInstanceConfiguration.<init>(InstanceProperties, List)"})
  void testNewDeployInstanceConfiguration_whenArrayList_thenReturnTablePropertiesEmpty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    DeployInstanceConfiguration actualDeployInstanceConfiguration = new DeployInstanceConfiguration(instanceProperties,
        new ArrayList<>());

    // Assert
    assertTrue(actualDeployInstanceConfiguration.getTableProperties().isEmpty());
    assertSame(instanceProperties, actualDeployInstanceConfiguration.getInstanceProperties());
  }

  /**
   * Test {@link DeployInstanceConfiguration#equals(Object)}, and {@link DeployInstanceConfiguration#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DeployInstanceConfiguration#equals(Object)}
   *   <li>{@link DeployInstanceConfiguration#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeployInstanceConfiguration.equals(Object)",
      "int DeployInstanceConfiguration.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = DeployInstanceConfiguration.builder();
    Builder instancePropertiesResult = builderResult.instanceProperties(new InstanceProperties());
    DeployInstanceConfiguration buildResult = instancePropertiesResult.tableProperties(new ArrayList<>()).build();
    Builder builderResult2 = DeployInstanceConfiguration.builder();
    Builder instancePropertiesResult2 = builderResult2.instanceProperties(new InstanceProperties());
    DeployInstanceConfiguration buildResult2 = instancePropertiesResult2.tableProperties(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link DeployInstanceConfiguration#equals(Object)}, and {@link DeployInstanceConfiguration#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DeployInstanceConfiguration#equals(Object)}
   *   <li>{@link DeployInstanceConfiguration#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeployInstanceConfiguration.equals(Object)",
      "int DeployInstanceConfiguration.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = DeployInstanceConfiguration.builder();
    Builder instancePropertiesResult = builderResult.instanceProperties(new InstanceProperties());
    DeployInstanceConfiguration buildResult = instancePropertiesResult.tableProperties(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link DeployInstanceConfiguration#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployInstanceConfiguration#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeployInstanceConfiguration.equals(Object)",
      "int DeployInstanceConfiguration.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder instancePropertiesResult = DeployInstanceConfiguration.builder().instanceProperties(null);
    DeployInstanceConfiguration buildResult = instancePropertiesResult.tableProperties(new ArrayList<>()).build();
    Builder builderResult = DeployInstanceConfiguration.builder();
    Builder instancePropertiesResult2 = builderResult.instanceProperties(new InstanceProperties());
    DeployInstanceConfiguration buildResult2 = instancePropertiesResult2.tableProperties(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link DeployInstanceConfiguration#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployInstanceConfiguration#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeployInstanceConfiguration.equals(Object)",
      "int DeployInstanceConfiguration.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<TableProperties> tableProperties = new ArrayList<>();
    tableProperties.add(PopulatePropertiesTestHelper.generateTestTableProperties());
    Builder builderResult = DeployInstanceConfiguration.builder();
    DeployInstanceConfiguration buildResult = builderResult.instanceProperties(new InstanceProperties())
        .tableProperties(tableProperties)
        .build();
    Builder builderResult2 = DeployInstanceConfiguration.builder();
    Builder instancePropertiesResult = builderResult2.instanceProperties(new InstanceProperties());
    DeployInstanceConfiguration buildResult2 = instancePropertiesResult.tableProperties(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link DeployInstanceConfiguration#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployInstanceConfiguration#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeployInstanceConfiguration.equals(Object)",
      "int DeployInstanceConfiguration.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = DeployInstanceConfiguration.builder();
    Builder instancePropertiesResult = builderResult.instanceProperties(new InstanceProperties());
    DeployInstanceConfiguration buildResult = instancePropertiesResult.tableProperties(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link DeployInstanceConfiguration#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployInstanceConfiguration#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeployInstanceConfiguration.equals(Object)",
      "int DeployInstanceConfiguration.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = DeployInstanceConfiguration.builder();
    Builder instancePropertiesResult = builderResult.instanceProperties(new InstanceProperties());
    DeployInstanceConfiguration buildResult = instancePropertiesResult.tableProperties(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to DeployInstanceConfiguration");
  }
}
