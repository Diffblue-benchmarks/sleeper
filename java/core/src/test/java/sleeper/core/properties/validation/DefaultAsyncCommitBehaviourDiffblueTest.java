package sleeper.core.properties.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.ArrayListIngestProperty;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertyComputeValue;

class DefaultAsyncCommitBehaviourDiffblueTest {
  /**
   * Test {@link DefaultAsyncCommitBehaviour#isValid(String)}.
   * <ul>
   *   <li>When {@code ALL_IMPLEMENTATIONS}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultAsyncCommitBehaviour#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'ALL_IMPLEMENTATIONS'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DefaultAsyncCommitBehaviour.isValid(String)"})
  void testIsValid_whenAllImplementations_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DefaultAsyncCommitBehaviour.isValid("ALL_IMPLEMENTATIONS"));
  }

  /**
   * Test {@link DefaultAsyncCommitBehaviour#isValid(String)}.
   * <ul>
   *   <li>When {@code Behaviour}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultAsyncCommitBehaviour#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'Behaviour'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DefaultAsyncCommitBehaviour.isValid(String)"})
  void testIsValid_whenBehaviour_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DefaultAsyncCommitBehaviour.isValid("Behaviour"));
  }

  /**
   * Test {@link DefaultAsyncCommitBehaviour#isValid(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultAsyncCommitBehaviour#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DefaultAsyncCommitBehaviour.isValid(String)"})
  void testIsValid_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DefaultAsyncCommitBehaviour.isValid(null));
  }

  /**
   * Test {@link DefaultAsyncCommitBehaviour#getDefaultAsyncCommitEnabled(InstanceProperties, TableProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return {@link Boolean#TRUE} toString.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultAsyncCommitBehaviour#getDefaultAsyncCommitEnabled(InstanceProperties, TableProperties)}
   */
  @Test
  @DisplayName("Test getDefaultAsyncCommitEnabled(InstanceProperties, TableProperties); when InstanceProperties(); then return TRUE toString")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "String DefaultAsyncCommitBehaviour.getDefaultAsyncCommitEnabled(InstanceProperties, TableProperties)"})
  void testGetDefaultAsyncCommitEnabled_whenInstanceProperties_thenReturnTrueToString() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    String actualDefaultAsyncCommitEnabled = DefaultAsyncCommitBehaviour
        .getDefaultAsyncCommitEnabled(instanceProperties, new TableProperties(new InstanceProperties()));

    // Assert
    assertEquals(Boolean.TRUE.toString(), actualDefaultAsyncCommitEnabled);
  }

  /**
   * Test {@link DefaultAsyncCommitBehaviour#computeAsyncCommitForUpdate(InstanceProperty)}.
   * <p>
   * Method under test: {@link DefaultAsyncCommitBehaviour#computeAsyncCommitForUpdate(InstanceProperty)}
   */
  @Test
  @DisplayName("Test computeAsyncCommitForUpdate(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "TablePropertyComputeValue DefaultAsyncCommitBehaviour.computeAsyncCommitForUpdate(InstanceProperty)"})
  void testComputeAsyncCommitForUpdate() {
    // Arrange and Act
    TablePropertyComputeValue actualComputeAsyncCommitForUpdateResult = DefaultAsyncCommitBehaviour
        .computeAsyncCommitForUpdate(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    InstanceProperties instanceProperties = new InstanceProperties();

    // Assert
    assertEquals("foo", actualComputeAsyncCommitForUpdateResult.computeValue("foo", instanceProperties,
        new TableProperties(new InstanceProperties())));
  }

  /**
   * Test {@link DefaultAsyncCommitBehaviour#computeAsyncCommitForUpdate(InstanceProperty)}.
   * <p>
   * Method under test: {@link DefaultAsyncCommitBehaviour#computeAsyncCommitForUpdate(InstanceProperty)}
   */
  @Test
  @DisplayName("Test computeAsyncCommitForUpdate(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "TablePropertyComputeValue DefaultAsyncCommitBehaviour.computeAsyncCommitForUpdate(InstanceProperty)"})
  void testComputeAsyncCommitForUpdate2() {
    // Arrange and Act
    TablePropertyComputeValue actualComputeAsyncCommitForUpdateResult = DefaultAsyncCommitBehaviour
        .computeAsyncCommitForUpdate(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    InstanceProperties instanceProperties = new InstanceProperties();
    String actualComputeValueResult = actualComputeAsyncCommitForUpdateResult.computeValue(null, instanceProperties,
        new TableProperties(new InstanceProperties()));

    // Assert
    assertEquals(Boolean.FALSE.toString(), actualComputeValueResult);
  }
}
