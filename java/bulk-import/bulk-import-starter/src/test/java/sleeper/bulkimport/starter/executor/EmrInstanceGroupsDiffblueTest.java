package sleeper.bulkimport.starter.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.bulkimport.core.configuration.BulkImportPlatformSpec;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.bulkimport.core.job.BulkImportJob.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import software.amazon.awssdk.services.emr.model.ComputeLimits;
import software.amazon.awssdk.services.emr.model.EbsConfiguration;
import software.amazon.awssdk.services.emr.model.JobFlowInstancesConfig;

class EmrInstanceGroupsDiffblueTest {
  /**
   * Test {@link EmrInstanceGroups#createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return ec2SubnetId is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceGroups#createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)}
   */
  @Test
  @DisplayName("Test createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec); given ArrayList() add 'foo'; then return ec2SubnetId is 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JobFlowInstancesConfig EmrInstanceGroups.createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)"})
  void testCreateJobFlowInstancesConfig_givenArrayListAddFoo_thenReturnEc2SubnetIdIsFoo() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getList(Mockito.<InstanceProperty>any())).thenReturn(stringList);
    EmrInstanceGroups emrInstanceGroups = new EmrInstanceGroups(instanceProperties);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    JobFlowInstancesConfig actualCreateJobFlowInstancesConfigResult = emrInstanceGroups
        .createJobFlowInstancesConfig(null, new BulkImportPlatformSpec(tableProperties, job));

    // Assert
    verify(instanceProperties).getList(isA(InstanceProperty.class));
    assertEquals("foo", actualCreateJobFlowInstancesConfigResult.ec2SubnetId());
    assertNull(actualCreateJobFlowInstancesConfigResult.keepJobFlowAliveWhenNoSteps());
    assertNull(actualCreateJobFlowInstancesConfigResult.terminationProtected());
    assertNull(actualCreateJobFlowInstancesConfigResult.unhealthyNodeReplacement());
    assertNull(actualCreateJobFlowInstancesConfigResult.instanceCount());
    assertNull(actualCreateJobFlowInstancesConfigResult.ec2KeyName());
    assertNull(actualCreateJobFlowInstancesConfigResult.emrManagedMasterSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.emrManagedSlaveSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.hadoopVersion());
    assertNull(actualCreateJobFlowInstancesConfigResult.masterInstanceType());
    assertNull(actualCreateJobFlowInstancesConfigResult.serviceAccessSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.slaveInstanceType());
    assertNull(actualCreateJobFlowInstancesConfigResult.placement());
    assertEquals(2, actualCreateJobFlowInstancesConfigResult.instanceGroups().size());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasAdditionalMasterSecurityGroups());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasAdditionalSlaveSecurityGroups());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasEc2SubnetIds());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasInstanceFleets());
    List<String> additionalMasterSecurityGroupsResult = actualCreateJobFlowInstancesConfigResult
        .additionalMasterSecurityGroups();
    assertTrue(additionalMasterSecurityGroupsResult.isEmpty());
    assertTrue(actualCreateJobFlowInstancesConfigResult.hasInstanceGroups());
    assertSame(additionalMasterSecurityGroupsResult,
        actualCreateJobFlowInstancesConfigResult.additionalSlaveSecurityGroups());
    assertSame(additionalMasterSecurityGroupsResult, actualCreateJobFlowInstancesConfigResult.ec2SubnetIds());
    assertSame(additionalMasterSecurityGroupsResult, actualCreateJobFlowInstancesConfigResult.instanceFleets());
  }

  /**
   * Test {@link EmrInstanceGroups#createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code SPOT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceGroups#createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)}
   */
  @Test
  @DisplayName("Test createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec); given ArrayList() add 'SPOT'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JobFlowInstancesConfig EmrInstanceGroups.createJobFlowInstancesConfig(EbsConfiguration, BulkImportPlatformSpec)"})
  void testCreateJobFlowInstancesConfig_givenArrayListAddSpot() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("SPOT");
    stringList.add("foo");
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getList(Mockito.<InstanceProperty>any())).thenReturn(stringList);
    EmrInstanceGroups emrInstanceGroups = new EmrInstanceGroups(instanceProperties);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    JobFlowInstancesConfig actualCreateJobFlowInstancesConfigResult = emrInstanceGroups
        .createJobFlowInstancesConfig(null, new BulkImportPlatformSpec(tableProperties, job));

    // Assert
    verify(instanceProperties).getList(isA(InstanceProperty.class));
    assertNull(actualCreateJobFlowInstancesConfigResult.keepJobFlowAliveWhenNoSteps());
    assertNull(actualCreateJobFlowInstancesConfigResult.terminationProtected());
    assertNull(actualCreateJobFlowInstancesConfigResult.unhealthyNodeReplacement());
    assertNull(actualCreateJobFlowInstancesConfigResult.instanceCount());
    assertNull(actualCreateJobFlowInstancesConfigResult.ec2KeyName());
    assertNull(actualCreateJobFlowInstancesConfigResult.emrManagedMasterSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.emrManagedSlaveSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.hadoopVersion());
    assertNull(actualCreateJobFlowInstancesConfigResult.masterInstanceType());
    assertNull(actualCreateJobFlowInstancesConfigResult.serviceAccessSecurityGroup());
    assertNull(actualCreateJobFlowInstancesConfigResult.slaveInstanceType());
    assertNull(actualCreateJobFlowInstancesConfigResult.placement());
    assertEquals(2, actualCreateJobFlowInstancesConfigResult.instanceGroups().size());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasAdditionalMasterSecurityGroups());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasAdditionalSlaveSecurityGroups());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasEc2SubnetIds());
    assertFalse(actualCreateJobFlowInstancesConfigResult.hasInstanceFleets());
    List<String> additionalMasterSecurityGroupsResult = actualCreateJobFlowInstancesConfigResult
        .additionalMasterSecurityGroups();
    assertTrue(additionalMasterSecurityGroupsResult.isEmpty());
    assertTrue(actualCreateJobFlowInstancesConfigResult.hasInstanceGroups());
    assertSame(additionalMasterSecurityGroupsResult,
        actualCreateJobFlowInstancesConfigResult.additionalSlaveSecurityGroups());
    assertSame(additionalMasterSecurityGroupsResult, actualCreateJobFlowInstancesConfigResult.ec2SubnetIds());
    assertSame(additionalMasterSecurityGroupsResult, actualCreateJobFlowInstancesConfigResult.instanceFleets());
  }

  /**
   * Test {@link EmrInstanceGroups#createComputeLimits(BulkImportPlatformSpec)}.
   * <ul>
   *   <li>Then return maximumCoreCapacityUnits is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrInstanceGroups#createComputeLimits(BulkImportPlatformSpec)}
   */
  @Test
  @DisplayName("Test createComputeLimits(BulkImportPlatformSpec); then return maximumCoreCapacityUnits is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ComputeLimits EmrInstanceGroups.createComputeLimits(BulkImportPlatformSpec)"})
  void testCreateComputeLimits_thenReturnMaximumCoreCapacityUnitsIsNull() {
    // Arrange
    EmrInstanceGroups emrInstanceGroups = new EmrInstanceGroups(new InstanceProperties());
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    ComputeLimits actualCreateComputeLimitsResult = emrInstanceGroups
        .createComputeLimits(new BulkImportPlatformSpec(tableProperties, job));

    // Assert
    assertNull(actualCreateComputeLimitsResult.maximumCoreCapacityUnits());
    assertNull(actualCreateComputeLimitsResult.maximumOnDemandCapacityUnits());
    assertEquals(1, actualCreateComputeLimitsResult.minimumCapacityUnits().intValue());
    assertEquals(10, actualCreateComputeLimitsResult.maximumCapacityUnits().intValue());
  }
}
