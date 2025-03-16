package sleeper.bulkimport.starter.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.bulkimport.core.configuration.BulkImportPlatformSpec;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.bulkimport.core.job.BulkImportJob.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.testutils.DummyInstanceProperty;
import software.amazon.awssdk.services.emr.model.ComputeLimits;
import software.amazon.awssdk.services.emr.model.EbsConfiguration;
import software.amazon.awssdk.services.emr.model.JobFlowInstancesConfig;

class EmrInstanceFleetsDiffblueTest {
  /**
   * Test {@link EmrInstanceFleets#createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)}.
   * <p>
   * Method under test: {@link EmrInstanceFleets#createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)}
   */
  @Test
  @DisplayName("Test createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JobFlowInstancesConfig EmrInstanceFleets.createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)"})
  void testCreateJobFlowInstancesConfig() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty("Executors");
    instanceProperties.addToListIfMissing(dummyInstanceProperty, new ArrayList<>());
    EmrInstanceFleets emrInstanceFleets = new EmrInstanceFleets(instanceProperties);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    JobFlowInstancesConfig actualCreateJobFlowInstancesConfigResult = emrInstanceFleets
        .createJobFlowInstancesConfig(null, new BulkImportPlatformSpec(tableProperties, job));

    // Assert
    assertNull(actualCreateJobFlowInstancesConfigResult.keepJobFlowAliveWhenNoSteps());
    assertNull(actualCreateJobFlowInstancesConfigResult.terminationProtected());
    assertNull(actualCreateJobFlowInstancesConfigResult.unhealthyNodeReplacement());
    assertNull(actualCreateJobFlowInstancesConfigResult.instanceCount());
    assertNull(actualCreateJobFlowInstancesConfigResult.ec2KeyName());
    assertNull(actualCreateJobFlowInstancesConfigResult.ec2SubnetId());
    assertNull(actualCreateJobFlowInstancesConfigResult.emrManagedMasterSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.emrManagedSlaveSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.hadoopVersion());
    assertNull(actualCreateJobFlowInstancesConfigResult.masterInstanceType());
    assertNull(actualCreateJobFlowInstancesConfigResult.serviceAccessSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.slaveInstanceType());
    assertNull(actualCreateJobFlowInstancesConfigResult.placement());
    assertEquals(2, actualCreateJobFlowInstancesConfigResult.instanceFleets().size());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasAdditionalMasterSecurityGroups());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasAdditionalSlaveSecurityGroups());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasInstanceGroups());
    List<String> additionalMasterSecurityGroupsResult = actualCreateJobFlowInstancesConfigResult
        .additionalMasterSecurityGroups();
    assertTrue(additionalMasterSecurityGroupsResult.isEmpty());
    assertTrue(actualCreateJobFlowInstancesConfigResult.ec2SubnetIds().isEmpty());
    assertTrue(actualCreateJobFlowInstancesConfigResult.hasEc2SubnetIds());
    assertTrue(actualCreateJobFlowInstancesConfigResult.hasInstanceFleets());
    assertSame(additionalMasterSecurityGroupsResult,
        actualCreateJobFlowInstancesConfigResult.additionalSlaveSecurityGroups());
    assertSame(additionalMasterSecurityGroupsResult, actualCreateJobFlowInstancesConfigResult.instanceGroups());
  }

  /**
   * Test {@link EmrInstanceFleets#createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)}.
   * <ul>
   *   <li>Then return keepJobFlowAliveWhenNoSteps is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceFleets#createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)}
   */
  @Test
  @DisplayName("Test createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec); then return keepJobFlowAliveWhenNoSteps is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JobFlowInstancesConfig EmrInstanceFleets.createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)"})
  void testCreateJobFlowInstancesConfig_thenReturnKeepJobFlowAliveWhenNoStepsIsNull() {
    // Arrange
    EmrInstanceFleets emrInstanceFleets = new EmrInstanceFleets(new InstanceProperties());
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    JobFlowInstancesConfig actualCreateJobFlowInstancesConfigResult = emrInstanceFleets
        .createJobFlowInstancesConfig(null, new BulkImportPlatformSpec(tableProperties, job));

    // Assert
    assertNull(actualCreateJobFlowInstancesConfigResult.keepJobFlowAliveWhenNoSteps());
    assertNull(actualCreateJobFlowInstancesConfigResult.terminationProtected());
    assertNull(actualCreateJobFlowInstancesConfigResult.unhealthyNodeReplacement());
    assertNull(actualCreateJobFlowInstancesConfigResult.instanceCount());
    assertNull(actualCreateJobFlowInstancesConfigResult.ec2KeyName());
    assertNull(actualCreateJobFlowInstancesConfigResult.ec2SubnetId());
    assertNull(actualCreateJobFlowInstancesConfigResult.emrManagedMasterSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.emrManagedSlaveSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.hadoopVersion());
    assertNull(actualCreateJobFlowInstancesConfigResult.masterInstanceType());
    assertNull(actualCreateJobFlowInstancesConfigResult.serviceAccessSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.slaveInstanceType());
    assertNull(actualCreateJobFlowInstancesConfigResult.placement());
    assertEquals(2, actualCreateJobFlowInstancesConfigResult.instanceFleets().size());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasAdditionalMasterSecurityGroups());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasAdditionalSlaveSecurityGroups());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasInstanceGroups());
    List<String> additionalMasterSecurityGroupsResult = actualCreateJobFlowInstancesConfigResult
        .additionalMasterSecurityGroups();
    assertTrue(additionalMasterSecurityGroupsResult.isEmpty());
    assertTrue(actualCreateJobFlowInstancesConfigResult.ec2SubnetIds().isEmpty());
    assertTrue(actualCreateJobFlowInstancesConfigResult.hasEc2SubnetIds());
    assertTrue(actualCreateJobFlowInstancesConfigResult.hasInstanceFleets());
    assertSame(additionalMasterSecurityGroupsResult,
        actualCreateJobFlowInstancesConfigResult.additionalSlaveSecurityGroups());
    assertSame(additionalMasterSecurityGroupsResult, actualCreateJobFlowInstancesConfigResult.instanceGroups());
  }

  /**
   * Test {@link EmrInstanceFleets#createComputeLimits(BulkImportPlatformSpec)}.
   * <ul>
   *   <li>Given {@link EmrInstanceFleets#EmrInstanceFleets(InstanceProperties)} with instanceProperties is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceFleets#createComputeLimits(BulkImportPlatformSpec)}
   */
  @Test
  @DisplayName("Test createComputeLimits(BulkImportPlatformSpec); given EmrInstanceFleets(InstanceProperties) with instanceProperties is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ComputeLimits EmrInstanceFleets.createComputeLimits(BulkImportPlatformSpec)"})
  void testCreateComputeLimits_givenEmrInstanceFleetsWithInstancePropertiesIsNull() {
    // Arrange
    EmrInstanceFleets emrInstanceFleets = new EmrInstanceFleets(null);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    ComputeLimits actualCreateComputeLimitsResult = emrInstanceFleets
        .createComputeLimits(new BulkImportPlatformSpec(tableProperties, job));

    // Assert
    assertNull(actualCreateComputeLimitsResult.maximumCoreCapacityUnits());
    assertNull(actualCreateComputeLimitsResult.maximumOnDemandCapacityUnits());
    assertEquals(10, actualCreateComputeLimitsResult.maximumCapacityUnits().intValue());
    assertEquals(2, actualCreateComputeLimitsResult.minimumCapacityUnits().intValue());
  }

  /**
   * Test {@link EmrInstanceFleets#createComputeLimits(BulkImportPlatformSpec)}.
   * <ul>
   *   <li>Then return maximumCoreCapacityUnits is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceFleets#createComputeLimits(BulkImportPlatformSpec)}
   */
  @Test
  @DisplayName("Test createComputeLimits(BulkImportPlatformSpec); then return maximumCoreCapacityUnits is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ComputeLimits EmrInstanceFleets.createComputeLimits(BulkImportPlatformSpec)"})
  void testCreateComputeLimits_thenReturnMaximumCoreCapacityUnitsIsNull() {
    // Arrange
    EmrInstanceFleets emrInstanceFleets = new EmrInstanceFleets(new InstanceProperties());
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    ComputeLimits actualCreateComputeLimitsResult = emrInstanceFleets
        .createComputeLimits(new BulkImportPlatformSpec(tableProperties, job));

    // Assert
    assertNull(actualCreateComputeLimitsResult.maximumCoreCapacityUnits());
    assertNull(actualCreateComputeLimitsResult.maximumOnDemandCapacityUnits());
    assertEquals(10, actualCreateComputeLimitsResult.maximumCapacityUnits().intValue());
    assertEquals(2, actualCreateComputeLimitsResult.minimumCapacityUnits().intValue());
  }
}
