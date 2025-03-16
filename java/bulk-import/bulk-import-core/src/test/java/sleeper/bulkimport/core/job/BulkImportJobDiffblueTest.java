package sleeper.bulkimport.core.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import sleeper.bulkimport.core.job.BulkImportJob.Builder;
import sleeper.ingest.core.job.IngestJob;

class BulkImportJobDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#className(String)}
   *   <li>{@link Builder#files(List)}
   *   <li>{@link Builder#id(String)}
   *   <li>{@link Builder#platformSpec(Map)}
   *   <li>{@link Builder#sparkConf(Map)}
   *   <li>{@link Builder#tableId(String)}
   *   <li>{@link Builder#tableName(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Builder.<init>()", "BulkImportJob Builder.build()", "Builder Builder.className(String)",
      "Builder Builder.files(List)", "Builder Builder.id(String)", "Builder Builder.platformSpec(Map)",
      "Builder Builder.sparkConf(Map)", "Builder Builder.tableId(String)", "Builder Builder.tableName(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    ArrayList<String> files = new ArrayList<>();
    Builder idResult = classNameResult.files(files).id("42");
    HashMap<String, String> platformSpec = new HashMap<>();
    Builder sparkConfResult = idResult.platformSpec(platformSpec).sparkConf("Key", "42");
    HashMap<String, String> sparkConf = new HashMap<>();

    // Act
    BulkImportJob actualBuildResult = sparkConfResult.sparkConf(sparkConf)
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("Class Name", actualBuildResult.getClassName());
    assertEquals("Table Name", actualBuildResult.getTableName());
    List<String> files2 = actualBuildResult.getFiles();
    assertTrue(files2.isEmpty());
    Map<String, String> platformSpec2 = actualBuildResult.getPlatformSpec();
    assertTrue(platformSpec2.isEmpty());
    Map<String, String> sparkConf2 = actualBuildResult.getSparkConf();
    assertTrue(sparkConf2.isEmpty());
    assertSame(files, files2);
    assertSame(platformSpec, platformSpec2);
    assertSame(sparkConf, sparkConf2);
  }

  /**
   * Test Builder {@link Builder#sparkConf(String, String)} with {@code key}, {@code value}.
   * <ul>
   *   <li>Given builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#sparkConf(String, String)}
   */
  @Test
  @DisplayName("Test Builder sparkConf(String, String) with 'key', 'value'; given builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.sparkConf(String, String)"})
  void testBuilderSparkConfWithKeyValue_givenBuilder() {
    // Arrange
    Builder builderResult = BulkImportJob.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.sparkConf("Key", "42"));
  }

  /**
   * Test Builder {@link Builder#sparkConf(String, String)} with {@code key}, {@code value}.
   * <ul>
   *   <li>Given builder sparkConf {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#sparkConf(String, String)}
   */
  @Test
  @DisplayName("Test Builder sparkConf(String, String) with 'key', 'value'; given builder sparkConf HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.sparkConf(String, String)"})
  void testBuilderSparkConfWithKeyValue_givenBuilderSparkConfHashMap() {
    // Arrange
    Builder builderResult = BulkImportJob.builder();
    builderResult.sparkConf(new HashMap<>());

    // Act and Assert
    assertSame(builderResult, builderResult.sparkConf("Key", "42"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BulkImportJob#toString()}
   *   <li>{@link BulkImportJob#getClassName()}
   *   <li>{@link BulkImportJob#getFiles()}
   *   <li>{@link BulkImportJob#getId()}
   *   <li>{@link BulkImportJob#getPlatformSpec()}
   *   <li>{@link BulkImportJob#getSparkConf()}
   *   <li>{@link BulkImportJob#getTableId()}
   *   <li>{@link BulkImportJob#getTableName()}
   *   <li>{@link BulkImportJob#toBuilder()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportJob.getClassName()", "List BulkImportJob.getFiles()",
      "String BulkImportJob.getId()", "Map BulkImportJob.getPlatformSpec()", "Map BulkImportJob.getSparkConf()",
      "String BulkImportJob.getTableId()", "String BulkImportJob.getTableName()", "Builder BulkImportJob.toBuilder()",
      "String BulkImportJob.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    ArrayList<String> files = new ArrayList<>();
    Builder idResult = classNameResult.files(files).id("42");
    HashMap<String, String> platformSpec = new HashMap<>();
    BulkImportJob buildResult = idResult.platformSpec(platformSpec)
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualClassName = buildResult.getClassName();
    List<String> actualFiles = buildResult.getFiles();
    String actualId = buildResult.getId();
    Map<String, String> actualPlatformSpec = buildResult.getPlatformSpec();
    Map<String, String> actualSparkConf = buildResult.getSparkConf();
    String actualTableId = buildResult.getTableId();
    String actualTableName = buildResult.getTableName();
    buildResult.toBuilder();

    // Assert
    assertEquals(1, actualSparkConf.size());
    assertEquals("42", actualSparkConf.get("Key"));
    assertEquals("42", actualId);
    assertEquals("42", actualTableId);
    assertEquals("BulkImportJob{id='42', tableName='Table Name', tableId='42', files=[], className='Class Name',"
        + " platformSpec={}, sparkConf={Key=42}}", actualToStringResult);
    assertEquals("Class Name", actualClassName);
    assertEquals("Table Name", actualTableName);
    assertTrue(actualFiles.isEmpty());
    assertTrue(actualPlatformSpec.isEmpty());
    assertSame(files, actualFiles);
    assertSame(platformSpec, actualPlatformSpec);
  }

  /**
   * Test {@link BulkImportJob#toIngestJob()}.
   * <p>
   * Method under test: {@link BulkImportJob#toIngestJob()}
   */
  @Test
  @DisplayName("Test toIngestJob()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJob BulkImportJob.toIngestJob()"})
  void testToIngestJob() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    IngestJob actualToIngestJobResult = buildResult.toIngestJob();

    // Assert
    assertEquals("42", actualToIngestJobResult.getId());
    assertEquals("42", actualToIngestJobResult.getTableId());
    assertEquals("Table Name", actualToIngestJobResult.getTableName());
    assertEquals(0, actualToIngestJobResult.getFileCount());
    assertTrue(actualToIngestJobResult.getFiles().isEmpty());
  }

  /**
   * Test {@link BulkImportJob#applyIngestJobChanges(IngestJob)}.
   * <p>
   * Method under test: {@link BulkImportJob#applyIngestJobChanges(IngestJob)}
   */
  @Test
  @DisplayName("Test applyIngestJobChanges(IngestJob)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BulkImportJob BulkImportJob.applyIngestJobChanges(IngestJob)"})
  void testApplyIngestJobChanges() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    IngestJob.Builder builderResult = IngestJob.builder();
    IngestJob job = builderResult.files(new ArrayList<>()).id("42").tableId("42").tableName("Table Name").build();

    // Act and Assert
    assertEquals(buildResult, buildResult.applyIngestJobChanges(job));
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}, and {@link BulkImportJob#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BulkImportJob#equals(Object)}
   *   <li>{@link BulkImportJob#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Builder classNameResult2 = BulkImportJob.builder().className("Class Name");
    Builder idResult2 = classNameResult2.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult2 = idResult2.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}, and {@link BulkImportJob#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BulkImportJob#equals(Object)}
   *   <li>{@link BulkImportJob#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className(null);
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Builder classNameResult2 = BulkImportJob.builder().className("Class Name");
    Builder idResult2 = classNameResult2.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult2 = idResult2.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");
    Builder idResult = BulkImportJob.builder().className("Class Name").files(files).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult2 = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult2 = idResult2.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("Id");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Builder classNameResult2 = BulkImportJob.builder().className("Class Name");
    Builder idResult2 = classNameResult2.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult2 = idResult2.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    HashMap<String, String> platformSpec = new HashMap<>();
    platformSpec.put("foo", "foo");
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    BulkImportJob buildResult = classNameResult.files(new ArrayList<>())
        .id("42")
        .platformSpec(platformSpec)
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Builder classNameResult2 = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult2.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult2 = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf(null, "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    Builder classNameResult2 = BulkImportJob.builder().className("Class Name");
    Builder idResult2 = classNameResult2.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult2 = idResult2.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("Table Id")
        .tableName("Table Name")
        .build();
    Builder classNameResult2 = BulkImportJob.builder().className("Class Name");
    Builder idResult2 = classNameResult2.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult2 = idResult2.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName(null)
        .build();
    Builder classNameResult2 = BulkImportJob.builder().className("Class Name");
    Builder idResult2 = classNameResult2.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult2 = idResult2.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link BulkImportJob#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJob#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportJob.equals(Object)", "int BulkImportJob.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to BulkImportJob");
  }
}
