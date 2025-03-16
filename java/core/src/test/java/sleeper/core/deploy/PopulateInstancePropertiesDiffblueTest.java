package sleeper.core.deploy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;

class PopulateInstancePropertiesDiffblueTest {
  /**
   * Test {@link PopulateInstanceProperties#generateTearDownDefaultsFromInstanceId(String)}.
   * <p>
   * Method under test: {@link PopulateInstanceProperties#generateTearDownDefaultsFromInstanceId(String)}
   */
  @Test
  @DisplayName("Test generateTearDownDefaultsFromInstanceId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties PopulateInstanceProperties.generateTearDownDefaultsFromInstanceId(String)"})
  void testGenerateTearDownDefaultsFromInstanceId() {
    // Arrange and Act
    InstanceProperties actualGenerateTearDownDefaultsFromInstanceIdResult = PopulateInstanceProperties
        .generateTearDownDefaultsFromInstanceId("42");

    // Assert
    Properties properties = actualGenerateTearDownDefaultsFromInstanceIdResult.getProperties();
    assertEquals(20, properties.size());
    assertEquals("42-FindPartitionsToSplitPeriodicTrigger", properties.get("sleeper.partition.splitting.rule"));
    assertEquals("42-TransactionLogSnapshotCreationRule",
        properties.get("sleeper.statestore.transactionlog.snapshots.creation.rule"));
    assertEquals("42/bulk-import-runner", properties.get("sleeper.bulk.import.eks.repo"));
    assertEquals("42/compaction-job-execution", properties.get("sleeper.compaction.repo"));
    assertEquals("sleeper-42-config", properties.get("sleeper.config.bucket"));
    assertEquals("sleeper-42-query-results", properties.get("sleeper.query.results.bucket"));
    assertEquals(properties, actualGenerateTearDownDefaultsFromInstanceIdResult.toMap());
  }

  /**
   * Test {@link PopulateInstanceProperties#generateTearDownDefaultsFromInstanceId(String)}.
   * <p>
   * Method under test: {@link PopulateInstanceProperties#generateTearDownDefaultsFromInstanceId(String)}
   */
  @Test
  @DisplayName("Test generateTearDownDefaultsFromInstanceId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties PopulateInstanceProperties.generateTearDownDefaultsFromInstanceId(String)"})
  void testGenerateTearDownDefaultsFromInstanceId2() {
    // Arrange and Act
    InstanceProperties actualGenerateTearDownDefaultsFromInstanceIdResult = PopulateInstanceProperties
        .generateTearDownDefaultsFromInstanceId("sleeper-%s-jars");

    // Assert
    Properties properties = actualGenerateTearDownDefaultsFromInstanceIdResult.getProperties();
    assertEquals(20, properties.size());
    assertEquals("sleeper-%s-jars-FindPartitionsToSplitPeriodicTrigger",
        properties.get("sleeper.partition.splitting.rule"));
    assertEquals("sleeper-%s-jars-TransactionLogSnapshotCreationRule",
        properties.get("sleeper.statestore.transactionlog.snapshots.creation.rule"));
    assertEquals("sleeper-%s-jars/bulk-import-runner", properties.get("sleeper.bulk.import.eks.repo"));
    assertEquals("sleeper-%s-jars/compaction-job-execution", properties.get("sleeper.compaction.repo"));
    assertEquals("sleeper-sleeper-%s-jars-config", properties.get("sleeper.config.bucket"));
    assertEquals("sleeper-sleeper-%s-jars-query-results", properties.get("sleeper.query.results.bucket"));
    assertEquals(properties, actualGenerateTearDownDefaultsFromInstanceIdResult.toMap());
  }

  /**
   * Test {@link PopulateInstanceProperties#generateTearDownDefaultsFromInstanceId(String)}.
   * <p>
   * Method under test: {@link PopulateInstanceProperties#generateTearDownDefaultsFromInstanceId(String)}
   */
  @Test
  @DisplayName("Test generateTearDownDefaultsFromInstanceId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties PopulateInstanceProperties.generateTearDownDefaultsFromInstanceId(String)"})
  void testGenerateTearDownDefaultsFromInstanceId3() {
    // Arrange and Act
    InstanceProperties actualGenerateTearDownDefaultsFromInstanceIdResult = PopulateInstanceProperties
        .generateTearDownDefaultsFromInstanceId("nameFormat must not be null");

    // Assert
    Properties properties = actualGenerateTearDownDefaultsFromInstanceIdResult.getProperties();
    assertEquals(20, properties.size());
    assertEquals("nameFormat must not be null-FindPartitionsToSplitPeriodicTrigger",
        properties.get("sleeper.partition.splitting.rule"));
    assertEquals("nameFormat must not be null-TransactionLogSnapshotCreationRule",
        properties.get("sleeper.statestore.transactionlog.snapshots.creation.rule"));
    assertEquals("nameFormat must not be null/bulk-import-runner", properties.get("sleeper.bulk.import.eks.repo"));
    assertEquals("nameFormat must not be null/compaction-job-execution", properties.get("sleeper.compaction.repo"));
    assertEquals("sleeper-nameFormat must not be null-query-results", properties.get("sleeper.query.results.bucket"));
    assertEquals("sleeper-nameformat must not be null-config", properties.get("sleeper.config.bucket"));
    assertEquals(properties, actualGenerateTearDownDefaultsFromInstanceIdResult.toMap());
  }

  /**
   * Test {@link PopulateInstanceProperties#populateDefaultsFromInstanceId(InstanceProperties, String)}.
   * <ul>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap size is six.</li>
   * </ul>
   * <p>
   * Method under test: {@link PopulateInstanceProperties#populateDefaultsFromInstanceId(InstanceProperties, String)}
   */
  @Test
  @DisplayName("Test populateDefaultsFromInstanceId(InstanceProperties, String); then InstanceProperties() toMap size is six")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "InstanceProperties PopulateInstanceProperties.populateDefaultsFromInstanceId(InstanceProperties, String)"})
  void testPopulateDefaultsFromInstanceId_thenInstancePropertiesToMapSizeIsSix() {
    // Arrange
    InstanceProperties properties = new InstanceProperties();

    // Act
    InstanceProperties actualPopulateDefaultsFromInstanceIdResult = PopulateInstanceProperties
        .populateDefaultsFromInstanceId(properties, "42");

    // Assert
    Map<String, String> toMapResult = properties.toMap();
    assertEquals(6, toMapResult.size());
    Properties properties2 = actualPopulateDefaultsFromInstanceIdResult.getProperties();
    assertEquals(6, properties2.size());
    Stream<Entry<String, String>> unknownProperties = actualPopulateDefaultsFromInstanceIdResult.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
    assertTrue(toMapResult.containsKey("sleeper.bulk.import.eks.repo"));
    assertTrue(toMapResult.containsKey("sleeper.bulk.import.emr.serverless.repo"));
    assertTrue(toMapResult.containsKey("sleeper.compaction.repo"));
    assertTrue(toMapResult.containsKey("sleeper.id"));
    assertTrue(toMapResult.containsKey("sleeper.ingest.repo"));
    assertTrue(toMapResult.containsKey("sleeper.jars.bucket"));
    assertTrue(actualPopulateDefaultsFromInstanceIdResult.getTags().isEmpty());
    assertTrue(properties2.containsKey("sleeper.bulk.import.eks.repo"));
    assertTrue(properties2.containsKey("sleeper.bulk.import.emr.serverless.repo"));
    assertTrue(properties2.containsKey("sleeper.compaction.repo"));
    assertTrue(properties2.containsKey("sleeper.id"));
    assertTrue(properties2.containsKey("sleeper.ingest.repo"));
    assertTrue(properties2.containsKey("sleeper.jars.bucket"));
    assertTrue(actualPopulateDefaultsFromInstanceIdResult.getTagsProperties().isEmpty());
    assertEquals(properties2, actualPopulateDefaultsFromInstanceIdResult.toMap());
  }
}
