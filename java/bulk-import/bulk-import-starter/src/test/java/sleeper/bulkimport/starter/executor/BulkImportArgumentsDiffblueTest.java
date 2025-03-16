package sleeper.bulkimport.starter.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.bulkimport.starter.executor.BulkImportArguments.Builder;
import sleeper.core.properties.instance.InstanceProperties;

class BulkImportArgumentsDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#bulkImportJob(BulkImportJob)}
   *   <li>{@link Builder#instanceProperties(InstanceProperties)}
   *   <li>{@link Builder#jobRunId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BulkImportArguments Builder.build()", "Builder Builder.bulkImportJob(BulkImportJob)",
      "Builder Builder.instanceProperties(InstanceProperties)", "Builder Builder.jobRunId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = BulkImportArguments.builder();
    BulkImportJob.Builder classNameResult = BulkImportJob.builder().className("Class Name");
    ArrayList<String> files = new ArrayList<>();
    BulkImportJob.Builder idResult = classNameResult.files(files).id("42");
    HashMap<String, String> platformSpec = new HashMap<>();
    BulkImportJob bulkImportJob = idResult.platformSpec(platformSpec)
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Builder bulkImportJobResult = builderResult.bulkImportJob(bulkImportJob);
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    BulkImportArguments actualBuildResult = bulkImportJobResult.instanceProperties(instanceProperties)
        .jobRunId("42")
        .build();

    // Assert
    BulkImportJob bulkImportJob2 = actualBuildResult.getBulkImportJob();
    Map<String, String> sparkConf = bulkImportJob2.getSparkConf();
    assertEquals(1, sparkConf.size());
    assertEquals("42", sparkConf.get("Key"));
    assertEquals("42", bulkImportJob2.getId());
    assertEquals("42", bulkImportJob2.getTableId());
    assertEquals("42", actualBuildResult.getJobRunId());
    assertEquals("Class Name", bulkImportJob2.getClassName());
    assertEquals("Table Name", bulkImportJob2.getTableName());
    List<String> files2 = bulkImportJob2.getFiles();
    assertTrue(files2.isEmpty());
    Map<String, String> platformSpec2 = bulkImportJob2.getPlatformSpec();
    assertTrue(platformSpec2.isEmpty());
    assertSame(files, files2);
    assertSame(platformSpec, platformSpec2);
    assertSame(instanceProperties, actualBuildResult.getInstanceProperties());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BulkImportArguments#getBulkImportJob()}
   *   <li>{@link BulkImportArguments#getInstanceProperties()}
   *   <li>{@link BulkImportArguments#getJobRunId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BulkImportJob BulkImportArguments.getBulkImportJob()",
      "InstanceProperties BulkImportArguments.getInstanceProperties()", "String BulkImportArguments.getJobRunId()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = BulkImportArguments.builder();
    BulkImportJob.Builder classNameResult = BulkImportJob.builder().className("Class Name");
    ArrayList<String> files = new ArrayList<>();
    BulkImportJob.Builder idResult = classNameResult.files(files).id("42");
    HashMap<String, String> platformSpec = new HashMap<>();
    BulkImportJob bulkImportJob = idResult.platformSpec(platformSpec)
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Builder bulkImportJobResult = builderResult.bulkImportJob(bulkImportJob);
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportArguments buildResult = bulkImportJobResult.instanceProperties(instanceProperties).jobRunId("42").build();

    // Act
    BulkImportJob actualBulkImportJob = buildResult.getBulkImportJob();
    InstanceProperties actualInstanceProperties = buildResult.getInstanceProperties();
    String actualJobRunId = buildResult.getJobRunId();

    // Assert
    Map<String, String> sparkConf = actualBulkImportJob.getSparkConf();
    assertEquals(1, sparkConf.size());
    assertEquals("42", sparkConf.get("Key"));
    assertEquals("42", actualBulkImportJob.getId());
    assertEquals("42", actualBulkImportJob.getTableId());
    assertEquals("42", actualJobRunId);
    assertEquals("Class Name", actualBulkImportJob.getClassName());
    assertEquals("Table Name", actualBulkImportJob.getTableName());
    List<String> files2 = actualBulkImportJob.getFiles();
    assertTrue(files2.isEmpty());
    Map<String, String> platformSpec2 = actualBulkImportJob.getPlatformSpec();
    assertTrue(platformSpec2.isEmpty());
    assertSame(files, files2);
    assertSame(platformSpec, platformSpec2);
    assertSame(instanceProperties, actualInstanceProperties);
  }
}
