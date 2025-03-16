package sleeper.core.deploy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.deploy.SleeperScheduleRule.InstanceRule;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class SleeperScheduleRuleDiffblueTest {
  /**
   * Test {@link SleeperScheduleRule#all()}.
   * <p>
   * Method under test: {@link SleeperScheduleRule#all()}
   */
  @Test
  @DisplayName("Test all()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperScheduleRule.all()"})
  void testAll() {
    // Arrange and Act
    List<SleeperScheduleRule> actualAllResult = SleeperScheduleRule.all();

    // Assert
    assertEquals(11, actualAllResult.size());
    assertEquals("Triggers creation of compaction jobs for online Sleeper tables",
        actualAllResult.get(0).getDescription());
    assertEquals("Triggers creation of jobs from files submitted to the ingest batcher",
        actualAllResult.get(5).getDescription());
    assertEquals(
        "Triggers creation of snapshots of the current state of online Sleeper tables based on a transaction" + " log",
        actualAllResult.get(8).getDescription());
    assertEquals("Triggers deletion of old snapshots of online Sleeper tables based on a transaction log",
        actualAllResult.get(9).getDescription());
    assertEquals("Triggers deletion of old transactions from the active transaction logs of online Sleeper tables",
        actualAllResult.get(10).getDescription());
    assertEquals("Triggers garbage collection to delete unused files", actualAllResult.get(3).getDescription());
    assertEquals("Triggers looking for partitions to split in online Sleeper tables",
        actualAllResult.get(2).getDescription());
    assertEquals("Triggers publishing metrics based on the current state of Sleeper tables",
        actualAllResult.get(6).getDescription());
    assertEquals("Triggers query requests to prevent the system scaling to zero",
        actualAllResult.get(7).getDescription());
    assertEquals("Triggers scaling compaction tasks to run created jobs", actualAllResult.get(1).getDescription());
    assertEquals("Triggers scaling ingest tasks to run queued jobs", actualAllResult.get(4).getDescription());
  }

  /**
   * Test {@link SleeperScheduleRule#getDeployedRules(InstanceProperties)}.
   * <p>
   * Method under test: {@link SleeperScheduleRule#getDeployedRules(InstanceProperties)}
   */
  @Test
  @DisplayName("Test getDeployedRules(InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream SleeperScheduleRule.getDeployedRules(InstanceProperties)"})
  void testGetDeployedRules() {
    // Arrange and Act
    Stream<InstanceRule> actualDeployedRules = SleeperScheduleRule.getDeployedRules(new InstanceProperties());

    // Assert
    assertTrue(actualDeployedRules.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link SleeperScheduleRule#getDefaultRules(String)}.
   * <p>
   * Method under test: {@link SleeperScheduleRule#getDefaultRules(String)}
   */
  @Test
  @DisplayName("Test getDefaultRules(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream SleeperScheduleRule.getDefaultRules(String)"})
  void testGetDefaultRules() {
    // Arrange and Act
    Stream<InstanceRule> actualDefaultRules = SleeperScheduleRule.getDefaultRules("42");

    // Assert
    List<InstanceRule> collectResult = actualDefaultRules.limit(5).collect(Collectors.toList());
    assertEquals(5, collectResult.size());
    InstanceRule getResult = collectResult.get(0);
    assertEquals("42-CompactionJobCreationRule", getResult.getRuleName());
    InstanceRule getResult2 = collectResult.get(1);
    assertEquals("42-CompactionTasksCreationRule", getResult2.getRuleName());
    InstanceRule getResult3 = collectResult.get(2);
    assertEquals("42-FindPartitionsToSplitPeriodicTrigger", getResult3.getRuleName());
    InstanceRule getResult4 = collectResult.get(3);
    assertEquals("42-GarbageCollectorPeriodicTrigger", getResult4.getRuleName());
    InstanceRule getResult5 = collectResult.get(4);
    assertEquals("42-IngestTasksCreationRule", getResult5.getRuleName());
    assertTrue(getResult.isDeployed());
    assertTrue(getResult2.isDeployed());
    assertTrue(getResult3.isDeployed());
    assertTrue(getResult4.isDeployed());
    assertTrue(getResult5.isDeployed());
  }

  /**
   * Test {@link SleeperScheduleRule#readValue(InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return RuleName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperScheduleRule#readValue(InstanceProperties)}
   */
  @Test
  @DisplayName("Test readValue(InstanceProperties); when InstanceProperties(); then return RuleName is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceRule SleeperScheduleRule.readValue(InstanceProperties)"})
  void testReadValue_whenInstanceProperties_thenReturnRuleNameIsNull() {
    // Arrange
    SleeperScheduleRule sleeperScheduleRule = SleeperScheduleRule.COMPACTION_JOB_CREATION;

    // Act
    InstanceRule actualReadValueResult = sleeperScheduleRule.readValue(new InstanceProperties());

    // Assert
    assertNull(actualReadValueResult.getRuleName());
    assertFalse(actualReadValueResult.isDeployed());
    InstanceProperty property = actualReadValueResult.getProperty();
    assertFalse(property.isEditable());
    assertFalse(property.isIncludedInBasicTemplate());
    assertFalse(property.isIncludedInTemplate());
    assertFalse(property.isUserDefined());
    assertTrue(property.isIgnoreEmptyValue());
    assertTrue(property.isSetByCdk());
    SleeperScheduleRule expectedRule = sleeperScheduleRule.COMPACTION_JOB_CREATION;
    assertSame(expectedRule, actualReadValueResult.getRule());
  }

  /**
   * Test {@link SleeperScheduleRule#buildRuleName(String)} with {@code instanceId}.
   * <p>
   * Method under test: {@link SleeperScheduleRule#buildRuleName(String)}
   */
  @Test
  @DisplayName("Test buildRuleName(String) with 'instanceId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperScheduleRule.buildRuleName(String)"})
  void testBuildRuleNameWithInstanceId() {
    // Arrange, Act and Assert
    assertEquals("%s-TransactionLogTransactionDeletionRule-CompactionJobCreationRu",
        SleeperScheduleRule.COMPACTION_JOB_CREATION.buildRuleName("%s-TransactionLogTransactionDeletionRule"));
  }

  /**
   * Test {@link SleeperScheduleRule#buildRuleName(String)} with {@code instanceId}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code 42-CompactionJobCreationRule}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperScheduleRule#buildRuleName(String)}
   */
  @Test
  @DisplayName("Test buildRuleName(String) with 'instanceId'; when '42'; then return '42-CompactionJobCreationRule'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperScheduleRule.buildRuleName(String)"})
  void testBuildRuleNameWithInstanceId_when42_thenReturn42CompactionJobCreationRule() {
    // Arrange, Act and Assert
    assertEquals("42-CompactionJobCreationRule", SleeperScheduleRule.COMPACTION_JOB_CREATION.buildRuleName("42"));
  }

  /**
   * Test {@link SleeperScheduleRule#buildRuleName(InstanceProperties)} with {@code properties}.
   * <ul>
   *   <li>Then return {@code null-CompactionJobCreationRule}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperScheduleRule#buildRuleName(InstanceProperties)}
   */
  @Test
  @DisplayName("Test buildRuleName(InstanceProperties) with 'properties'; then return 'null-CompactionJobCreationRule'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperScheduleRule.buildRuleName(InstanceProperties)"})
  void testBuildRuleNameWithProperties_thenReturnNullCompactionJobCreationRule() {
    // Arrange, Act and Assert
    assertEquals("null-CompactionJobCreationRule",
        SleeperScheduleRule.COMPACTION_JOB_CREATION.buildRuleName(new InstanceProperties()));
  }

  /**
   * Test {@link SleeperScheduleRule#getDefault(String)}.
   * <p>
   * Method under test: {@link SleeperScheduleRule#getDefault(String)}
   */
  @Test
  @DisplayName("Test getDefault(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceRule SleeperScheduleRule.getDefault(String)"})
  void testGetDefault() {
    // Arrange
    SleeperScheduleRule sleeperScheduleRule = SleeperScheduleRule.COMPACTION_JOB_CREATION;

    // Act
    InstanceRule actualDefault = sleeperScheduleRule.getDefault("%s-TransactionLogTransactionDeletionRule");

    // Assert
    assertEquals("%s-TransactionLogTransactionDeletionRule-CompactionJobCreationRu", actualDefault.getRuleName());
    InstanceProperty property = actualDefault.getProperty();
    assertFalse(property.isEditable());
    assertFalse(property.isIncludedInBasicTemplate());
    assertFalse(property.isIncludedInTemplate());
    assertFalse(property.isUserDefined());
    assertTrue(actualDefault.isDeployed());
    assertTrue(property.isIgnoreEmptyValue());
    assertTrue(property.isSetByCdk());
    SleeperScheduleRule expectedRule = sleeperScheduleRule.COMPACTION_JOB_CREATION;
    assertSame(expectedRule, actualDefault.getRule());
  }

  /**
   * Test {@link SleeperScheduleRule#getDefault(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return RuleName is {@code 42-CompactionJobCreationRule}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperScheduleRule#getDefault(String)}
   */
  @Test
  @DisplayName("Test getDefault(String); when '42'; then return RuleName is '42-CompactionJobCreationRule'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceRule SleeperScheduleRule.getDefault(String)"})
  void testGetDefault_when42_thenReturnRuleNameIs42CompactionJobCreationRule() {
    // Arrange
    SleeperScheduleRule sleeperScheduleRule = SleeperScheduleRule.COMPACTION_JOB_CREATION;

    // Act
    InstanceRule actualDefault = sleeperScheduleRule.getDefault("42");

    // Assert
    assertEquals("42-CompactionJobCreationRule", actualDefault.getRuleName());
    InstanceProperty property = actualDefault.getProperty();
    assertFalse(property.isEditable());
    assertFalse(property.isIncludedInBasicTemplate());
    assertFalse(property.isIncludedInTemplate());
    assertFalse(property.isUserDefined());
    assertTrue(actualDefault.isDeployed());
    assertTrue(property.isIgnoreEmptyValue());
    assertTrue(property.isSetByCdk());
    SleeperScheduleRule expectedRule = sleeperScheduleRule.COMPACTION_JOB_CREATION;
    assertSame(expectedRule, actualDefault.getRule());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SleeperScheduleRule#getDescription()}
   *   <li>{@link SleeperScheduleRule#getProperty()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperScheduleRule.getDescription()",
      "InstanceProperty SleeperScheduleRule.getProperty()"})
  void testGettersAndSetters() {
    // Arrange
    SleeperScheduleRule sleeperScheduleRule = SleeperScheduleRule.COMPACTION_JOB_CREATION;

    // Act
    String actualDescription = sleeperScheduleRule.getDescription();
    InstanceProperty actualProperty = sleeperScheduleRule.getProperty();

    // Assert
    assertEquals("Triggers creation of compaction jobs for online Sleeper tables", actualDescription);
    assertFalse(actualProperty.isEditable());
    assertFalse(actualProperty.isIncludedInBasicTemplate());
    assertFalse(actualProperty.isIncludedInTemplate());
    assertFalse(actualProperty.isUserDefined());
    assertTrue(actualProperty.isIgnoreEmptyValue());
    assertTrue(actualProperty.isSetByCdk());
  }
}
