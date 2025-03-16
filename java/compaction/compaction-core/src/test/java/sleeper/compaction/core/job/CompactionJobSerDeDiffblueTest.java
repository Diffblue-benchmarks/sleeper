package sleeper.compaction.core.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.core.job.CompactionJob.Builder;

class CompactionJobSerDeDiffblueTest {
  /**
   * Test new {@link CompactionJobSerDe} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CompactionJobSerDe}
   */
  @Test
  @DisplayName("Test new CompactionJobSerDe (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobSerDe.<init>()"})
  void testNewCompactionJobSerDe() {
    // Arrange and Act
    CompactionJobSerDe actualCompactionJobSerDe = new CompactionJobSerDe();

    // Assert
    assertEquals("null", actualCompactionJobSerDe.toJson((CompactionJob) null));
    assertEquals("{}", actualCompactionJobSerDe.toJson((List<CompactionJob>) null));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(List)} with {@code batch}.
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(List)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(List) with 'batch'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(List)"})
  void testToJsonPrettyPrintWithBatch() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    batch.add(buildResult);

    // Act and Assert
    assertEquals(
        "{\n" + "  \"jobs\": [\n" + "    {\n" + "      \"tableId\": \"42\",\n" + "      \"jobId\": \"42\",\n"
            + "      \"inputFiles\": [],\n" + "      \"outputFile\": \"Output File\",\n"
            + "      \"partitionId\": \"42\"\n" + "    }\n" + "  ]\n" + "}",
        compactionJobSerDe.toJsonPrettyPrint(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(List)} with {@code batch}.
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(List)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(List) with 'batch'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(List)"})
  void testToJsonPrettyPrintWithBatch2() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    batch.add(buildResult);
    Builder builderResult2 = CompactionJob.builder();
    CompactionJob buildResult2 = builderResult2.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    batch.add(buildResult2);

    // Act and Assert
    assertEquals(
        "{\n" + "  \"jobs\": [\n" + "    {\n" + "      \"tableId\": \"42\",\n" + "      \"jobId\": \"42\",\n"
            + "      \"inputFiles\": [],\n" + "      \"outputFile\": \"Output File\",\n"
            + "      \"partitionId\": \"42\"\n" + "    },\n" + "    {\n" + "      \"tableId\": \"42\",\n"
            + "      \"jobId\": \"42\",\n" + "      \"inputFiles\": [],\n" + "      \"outputFile\": \"Output File\",\n"
            + "      \"partitionId\": \"42\"\n" + "    }\n" + "  ]\n" + "}",
        compactionJobSerDe.toJsonPrettyPrint(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(List)} with {@code batch}.
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(List)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(List) with 'batch'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(List)"})
  void testToJsonPrettyPrintWithBatch3() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    batch.add(buildResult);

    // Act and Assert
    assertEquals(
        "{\n" + "  \"jobs\": [\n" + "    {\n" + "      \"tableId\": \"42\",\n" + "      \"jobId\": \"\",\n"
            + "      \"inputFiles\": [],\n" + "      \"outputFile\": \"Output File\",\n"
            + "      \"partitionId\": \"42\"\n" + "    }\n" + "  ]\n" + "}",
        compactionJobSerDe.toJsonPrettyPrint(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(List)} with {@code batch}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code { "jobs": [] }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(List)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(List) with 'batch'; when ArrayList(); then return '{ \"jobs\": [] }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(List)"})
  void testToJsonPrettyPrintWithBatch_whenArrayList_thenReturnJobs() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    // Act and Assert
    assertEquals("{\n  \"jobs\": []\n}", compactionJobSerDe.toJsonPrettyPrint(new ArrayList<>()));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)} with {@code job}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionJob) with 'job'; given '42'; when ArrayList() add '42'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(CompactionJob)"})
  void testToJsonPrettyPrintWithJob_given42_whenArrayListAdd42_thenReturnAString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("42");
    inputFiles.add("");
    CompactionJob job = CompactionJob.builder()
        .inputFiles(inputFiles)
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"tableId\": \"42\",\n" + "  \"jobId\": \"42\",\n" + "  \"inputFiles\": [\n" + "    \"42\",\n"
            + "    \"\"\n" + "  ],\n" + "  \"outputFile\": \"Output File\",\n" + "  \"partitionId\": \"42\"\n" + "}",
        compactionJobSerDe.toJsonPrettyPrint(job));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)} with {@code job}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link ArrayList#ArrayList()} add empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionJob) with 'job'; given empty string; when ArrayList() add empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(CompactionJob)"})
  void testToJsonPrettyPrintWithJob_givenEmptyString_whenArrayListAddEmptyString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("");
    CompactionJob job = CompactionJob.builder()
        .inputFiles(inputFiles)
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"tableId\": \"42\",\n" + "  \"jobId\": \"42\",\n" + "  \"inputFiles\": [\n" + "    \"\"\n"
            + "  ],\n" + "  \"outputFile\": \"Output File\",\n" + "  \"partitionId\": \"42\"\n" + "}",
        compactionJobSerDe.toJsonPrettyPrint(job));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)} with {@code job}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionJob) with 'job'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(CompactionJob)"})
  void testToJsonPrettyPrintWithJob_thenReturnAString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();
    Builder builderResult = CompactionJob.builder();
    CompactionJob job = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"tableId\": \"42\",\n" + "  \"jobId\": \"42\",\n" + "  \"inputFiles\": [],\n"
            + "  \"outputFile\": \"Output File\",\n" + "  \"partitionId\": \"42\"\n" + "}",
        compactionJobSerDe.toJsonPrettyPrint(job));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)} with {@code job}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionJob) with 'job'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(CompactionJob)"})
  void testToJsonPrettyPrintWithJob_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new CompactionJobSerDe()).toJsonPrettyPrint((CompactionJob) null));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(List)} with {@code batch}.
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJson(List)}
   */
  @Test
  @DisplayName("Test toJson(List) with 'batch'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(List)"})
  void testToJsonWithBatch() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    batch.add(buildResult);

    // Act and Assert
    assertEquals(
        "{\"jobs\":[{\"tableId\":\"42\",\"jobId\":\"\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}]}",
        compactionJobSerDe.toJson(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(List)} with {@code batch}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJson(List)}
   */
  @Test
  @DisplayName("Test toJson(List) with 'batch'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(List)"})
  void testToJsonWithBatch_thenReturnAString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    batch.add(buildResult);

    // Act and Assert
    assertEquals(
        "{\"jobs\":[{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId"
            + "\":\"42\"}]}",
        compactionJobSerDe.toJson(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(List)} with {@code batch}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJson(List)}
   */
  @Test
  @DisplayName("Test toJson(List) with 'batch'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(List)"})
  void testToJsonWithBatch_thenReturnAString2() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();
    Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    batch.add(buildResult);
    Builder builderResult2 = CompactionJob.builder();
    CompactionJob buildResult2 = builderResult2.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    batch.add(buildResult2);

    // Act and Assert
    assertEquals(
        "{\"jobs\":[{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}"
            + ",{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}]}",
        compactionJobSerDe.toJson(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(List)} with {@code batch}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code {"jobs":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJson(List)}
   */
  @Test
  @DisplayName("Test toJson(List) with 'batch'; when ArrayList(); then return '{\"jobs\":[]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(List)"})
  void testToJsonWithBatch_whenArrayList_thenReturnJobs() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    // Act and Assert
    assertEquals("{\"jobs\":[]}", compactionJobSerDe.toJson(new ArrayList<>()));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(CompactionJob)} with {@code job}.
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJson(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJson(CompactionJob) with 'job'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(CompactionJob)"})
  void testToJsonWithJob() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();
    Builder builderResult = CompactionJob.builder();
    CompactionJob job = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(
        "{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}",
        compactionJobSerDe.toJson(job));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(CompactionJob)} with {@code job}.
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJson(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJson(CompactionJob) with 'job'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(CompactionJob)"})
  void testToJsonWithJob2() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("");
    CompactionJob job = CompactionJob.builder()
        .inputFiles(inputFiles)
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(
        "{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[\"\"],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}",
        compactionJobSerDe.toJson(job));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(CompactionJob)} with {@code job}.
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJson(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJson(CompactionJob) with 'job'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(CompactionJob)"})
  void testToJsonWithJob3() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("42");
    inputFiles.add("");
    CompactionJob job = CompactionJob.builder()
        .inputFiles(inputFiles)
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(
        "{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[\"42\",\"\"],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}",
        compactionJobSerDe.toJson(job));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(CompactionJob)} with {@code job}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobSerDe#toJson(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJson(CompactionJob) with 'job'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(CompactionJob)"})
  void testToJsonWithJob_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new CompactionJobSerDe()).toJson((CompactionJob) null));
  }

  /**
   * Test {@link CompactionJobSerDe#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJob CompactionJobSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new CompactionJobSerDe()).fromJson(""));
  }
}
