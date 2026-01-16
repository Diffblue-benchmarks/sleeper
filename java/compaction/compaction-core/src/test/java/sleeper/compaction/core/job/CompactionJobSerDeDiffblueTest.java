package sleeper.compaction.core.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
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
   *
   * <p>Method under test: default or parameterless constructor of {@link CompactionJobSerDe}
   */
  @Test
  @DisplayName("Test new CompactionJobSerDe (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(List)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(List) with 'batch'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(List)"})
  void testToJsonPrettyPrintWithBatch() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();

    Builder builderResult = CompactionJob.builder();
    batch.add(
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"jobs\": [\n"
            + "    {\n"
            + "      \"tableId\": \"42\",\n"
            + "      \"jobId\": \"42\",\n"
            + "      \"inputFiles\": [],\n"
            + "      \"outputFile\": \"Output File\",\n"
            + "      \"partitionId\": \"42\"\n"
            + "    }\n"
            + "  ]\n"
            + "}",
        compactionJobSerDe.toJsonPrettyPrint(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(List)} with {@code batch}.
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(List)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(List) with 'batch'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(List)"})
  void testToJsonPrettyPrintWithBatch2() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();

    Builder builderResult = CompactionJob.builder();
    batch.add(
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    Builder builderResult2 = CompactionJob.builder();
    batch.add(
        builderResult2
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"jobs\": [\n"
            + "    {\n"
            + "      \"tableId\": \"42\",\n"
            + "      \"jobId\": \"42\",\n"
            + "      \"inputFiles\": [],\n"
            + "      \"outputFile\": \"Output File\",\n"
            + "      \"partitionId\": \"42\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"tableId\": \"42\",\n"
            + "      \"jobId\": \"42\",\n"
            + "      \"inputFiles\": [],\n"
            + "      \"outputFile\": \"Output File\",\n"
            + "      \"partitionId\": \"42\"\n"
            + "    }\n"
            + "  ]\n"
            + "}",
        compactionJobSerDe.toJsonPrettyPrint(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(List)} with {@code batch}.
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(List)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(List) with 'batch'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(List)"})
  void testToJsonPrettyPrintWithBatch3() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();

    Builder builderResult = CompactionJob.builder();
    batch.add(
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"jobs\": [\n"
            + "    {\n"
            + "      \"tableId\": \"42\",\n"
            + "      \"jobId\": \"\",\n"
            + "      \"inputFiles\": [],\n"
            + "      \"outputFile\": \"Output File\",\n"
            + "      \"partitionId\": \"42\"\n"
            + "    }\n"
            + "  ]\n"
            + "}",
        compactionJobSerDe.toJsonPrettyPrint(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(List)} with {@code batch}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code { "jobs": [] }}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(List)}
   */
  @Test
  @DisplayName(
      "Test toJsonPrettyPrint(List) with 'batch'; when ArrayList(); then return '{ \"jobs\": [] }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(List)"})
  void testToJsonPrettyPrintWithBatch_whenArrayList_thenReturnJobs() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    // Act and Assert
    assertEquals("{\n  \"jobs\": []\n}", compactionJobSerDe.toJsonPrettyPrint(new ArrayList<>()));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)} with {@code job}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)}
   */
  @Test
  @DisplayName(
      "Test toJsonPrettyPrint(CompactionJob) with 'job'; given '42'; when ArrayList() add '42'; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(CompactionJob)"})
  void testToJsonPrettyPrintWithJob_given42_whenArrayListAdd42_thenReturnAString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("42");
    inputFiles.add("foo");

    // Act
    String actualToJsonPrettyPrintResult =
        compactionJobSerDe.toJsonPrettyPrint(
            CompactionJob.builder()
                .inputFiles(inputFiles)
                .jobId("42")
                .outputFile("Output File")
                .partitionId("42")
                .tableId("42")
                .build());

    // Assert
    assertEquals(
        "{\n"
            + "  \"tableId\": \"42\",\n"
            + "  \"jobId\": \"42\",\n"
            + "  \"inputFiles\": [\n"
            + "    \"42\",\n"
            + "    \"foo\"\n"
            + "  ],\n"
            + "  \"outputFile\": \"Output File\",\n"
            + "  \"partitionId\": \"42\"\n"
            + "}",
        actualToJsonPrettyPrintResult);
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)} with {@code job}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link ArrayList#ArrayList()} add empty string.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)}
   */
  @Test
  @DisplayName(
      "Test toJsonPrettyPrint(CompactionJob) with 'job'; given empty string; when ArrayList() add empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(CompactionJob)"})
  void testToJsonPrettyPrintWithJob_givenEmptyString_whenArrayListAddEmptyString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("");
    inputFiles.add("42");
    inputFiles.add("foo");

    // Act
    String actualToJsonPrettyPrintResult =
        compactionJobSerDe.toJsonPrettyPrint(
            CompactionJob.builder()
                .inputFiles(inputFiles)
                .jobId("42")
                .outputFile("Output File")
                .partitionId("42")
                .tableId("42")
                .build());

    // Assert
    assertEquals(
        "{\n"
            + "  \"tableId\": \"42\",\n"
            + "  \"jobId\": \"42\",\n"
            + "  \"inputFiles\": [\n"
            + "    \"\",\n"
            + "    \"42\",\n"
            + "    \"foo\"\n"
            + "  ],\n"
            + "  \"outputFile\": \"Output File\",\n"
            + "  \"partitionId\": \"42\"\n"
            + "}",
        actualToJsonPrettyPrintResult);
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)} with {@code job}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)}
   */
  @Test
  @DisplayName(
      "Test toJsonPrettyPrint(CompactionJob) with 'job'; given 'foo'; when ArrayList() add 'foo'; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(CompactionJob)"})
  void testToJsonPrettyPrintWithJob_givenFoo_whenArrayListAddFoo_thenReturnAString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("foo");

    // Act
    String actualToJsonPrettyPrintResult =
        compactionJobSerDe.toJsonPrettyPrint(
            CompactionJob.builder()
                .inputFiles(inputFiles)
                .jobId("42")
                .outputFile("Output File")
                .partitionId("42")
                .tableId("42")
                .build());

    // Assert
    assertEquals(
        "{\n"
            + "  \"tableId\": \"42\",\n"
            + "  \"jobId\": \"42\",\n"
            + "  \"inputFiles\": [\n"
            + "    \"foo\"\n"
            + "  ],\n"
            + "  \"outputFile\": \"Output File\",\n"
            + "  \"partitionId\": \"42\"\n"
            + "}",
        actualToJsonPrettyPrintResult);
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)} with {@code job}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionJob) with 'job'; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(CompactionJob)"})
  void testToJsonPrettyPrintWithJob_thenReturnAString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    Builder builderResult = CompactionJob.builder();

    // Act
    String actualToJsonPrettyPrintResult =
        compactionJobSerDe.toJsonPrettyPrint(
            builderResult
                .inputFiles(new ArrayList<>())
                .jobId("42")
                .outputFile("Output File")
                .partitionId("42")
                .tableId("42")
                .build());

    // Assert
    assertEquals(
        "{\n"
            + "  \"tableId\": \"42\",\n"
            + "  \"jobId\": \"42\",\n"
            + "  \"inputFiles\": [],\n"
            + "  \"outputFile\": \"Output File\",\n"
            + "  \"partitionId\": \"42\"\n"
            + "}",
        actualToJsonPrettyPrintResult);
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)} with {@code job}.
   *
   * <ul>
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionJob) with 'job'; then return '{}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(CompactionJob)"})
  void testToJsonPrettyPrintWithJob_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", new CompactionJobSerDe().toJsonPrettyPrint(mock(CompactionJob.class)));
  }

  /**
   * Test {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)} with {@code job}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJsonPrettyPrint(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionJob) with 'job'; when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJsonPrettyPrint(CompactionJob)"})
  void testToJsonPrettyPrintWithJob_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", new CompactionJobSerDe().toJsonPrettyPrint((CompactionJob) null));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(List)} with {@code batch}.
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJson(List)}
   */
  @Test
  @DisplayName("Test toJson(List) with 'batch'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(List)"})
  void testToJsonWithBatch() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();

    Builder builderResult = CompactionJob.builder();
    batch.add(
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    // Act and Assert
    assertEquals(
        "{\"jobs\":[{\"tableId\":\"42\",\"jobId\":\"\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}]}",
        compactionJobSerDe.toJson(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(List)} with {@code batch}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJson(List)}
   */
  @Test
  @DisplayName("Test toJson(List) with 'batch'; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(List)"})
  void testToJsonWithBatch_thenReturnAString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();

    Builder builderResult = CompactionJob.builder();
    batch.add(
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    // Act and Assert
    assertEquals(
        "{\"jobs\":[{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId"
            + "\":\"42\"}]}",
        compactionJobSerDe.toJson(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(List)} with {@code batch}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJson(List)}
   */
  @Test
  @DisplayName("Test toJson(List) with 'batch'; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(List)"})
  void testToJsonWithBatch_thenReturnAString2() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<CompactionJob> batch = new ArrayList<>();

    Builder builderResult = CompactionJob.builder();
    batch.add(
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    Builder builderResult2 = CompactionJob.builder();
    batch.add(
        builderResult2
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build());

    // Act and Assert
    assertEquals(
        "{\"jobs\":[{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}"
            + ",{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}]}",
        compactionJobSerDe.toJson(batch));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(List)} with {@code batch}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code {"jobs":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJson(List)}
   */
  @Test
  @DisplayName("Test toJson(List) with 'batch'; when ArrayList(); then return '{\"jobs\":[]}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(List)"})
  void testToJsonWithBatch_whenArrayList_thenReturnJobs() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    // Act and Assert
    assertEquals("{\"jobs\":[]}", compactionJobSerDe.toJson(new ArrayList<>()));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(CompactionJob)} with {@code job}.
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJson(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJson(CompactionJob) with 'job'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(CompactionJob)"})
  void testToJsonWithJob() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    Builder builderResult = CompactionJob.builder();

    // Act
    String actualToJsonResult =
        compactionJobSerDe.toJson(
            builderResult
                .inputFiles(new ArrayList<>())
                .jobId("42")
                .outputFile("Output File")
                .partitionId("42")
                .tableId("42")
                .build());

    // Assert
    assertEquals(
        "{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}",
        actualToJsonResult);
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(CompactionJob)} with {@code job}.
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJson(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJson(CompactionJob) with 'job'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(CompactionJob)"})
  void testToJsonWithJob2() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("foo");

    // Act
    String actualToJsonResult =
        compactionJobSerDe.toJson(
            CompactionJob.builder()
                .inputFiles(inputFiles)
                .jobId("42")
                .outputFile("Output File")
                .partitionId("42")
                .tableId("42")
                .build());

    // Assert
    assertEquals(
        "{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[\"foo\"],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}",
        actualToJsonResult);
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(CompactionJob)} with {@code job}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJson(CompactionJob)}
   */
  @Test
  @DisplayName(
      "Test toJson(CompactionJob) with 'job'; given '42'; when ArrayList() add '42'; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(CompactionJob)"})
  void testToJsonWithJob_given42_whenArrayListAdd42_thenReturnAString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("42");
    inputFiles.add("foo");

    // Act
    String actualToJsonResult =
        compactionJobSerDe.toJson(
            CompactionJob.builder()
                .inputFiles(inputFiles)
                .jobId("42")
                .outputFile("Output File")
                .partitionId("42")
                .tableId("42")
                .build());

    // Assert
    assertEquals(
        "{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[\"42\",\"foo\"],\"outputFile\":\"Output File\",\"partitionId"
            + "\":\"42\"}",
        actualToJsonResult);
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(CompactionJob)} with {@code job}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link ArrayList#ArrayList()} add empty string.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJson(CompactionJob)}
   */
  @Test
  @DisplayName(
      "Test toJson(CompactionJob) with 'job'; given empty string; when ArrayList() add empty string; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(CompactionJob)"})
  void testToJsonWithJob_givenEmptyString_whenArrayListAddEmptyString_thenReturnAString() {
    // Arrange
    CompactionJobSerDe compactionJobSerDe = new CompactionJobSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("");
    inputFiles.add("42");
    inputFiles.add("foo");

    // Act
    String actualToJsonResult =
        compactionJobSerDe.toJson(
            CompactionJob.builder()
                .inputFiles(inputFiles)
                .jobId("42")
                .outputFile("Output File")
                .partitionId("42")
                .tableId("42")
                .build());

    // Assert
    assertEquals(
        "{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[\"\",\"42\",\"foo\"],\"outputFile\":\"Output File\",\"partitionId"
            + "\":\"42\"}",
        actualToJsonResult);
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(CompactionJob)} with {@code job}.
   *
   * <ul>
   *   <li>When {@link CompactionJob}.
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJson(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJson(CompactionJob) with 'job'; when CompactionJob; then return '{}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(CompactionJob)"})
  void testToJsonWithJob_whenCompactionJob_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("{}", new CompactionJobSerDe().toJson(mock(CompactionJob.class)));
  }

  /**
   * Test {@link CompactionJobSerDe#toJson(CompactionJob)} with {@code job}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#toJson(CompactionJob)}
   */
  @Test
  @DisplayName("Test toJson(CompactionJob) with 'job'; when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionJobSerDe.toJson(CompactionJob)"})
  void testToJsonWithJob_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", new CompactionJobSerDe().toJson((CompactionJob) null));
  }

  /**
   * Test {@link CompactionJobSerDe#fromJson(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"CompactionJob CompactionJobSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new CompactionJobSerDe().fromJson(""));
  }

  /**
   * Test {@link CompactionJobSerDe#fromJson(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"CompactionJob CompactionJobSerDe.fromJson(String)"})
  void testFromJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new CompactionJobSerDe().fromJson(null));
  }
}
