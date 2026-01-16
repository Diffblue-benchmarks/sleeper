package sleeper.compaction.core.job.commit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.ReplaceFileReferencesRequest;
import sleeper.core.statestore.ReplaceFileReferencesRequest.Builder;

class CompactionCommitMessageSerDeDiffblueTest {
  /**
   * Test new {@link CompactionCommitMessageSerDe} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * CompactionCommitMessageSerDe}
   */
  @Test
  @DisplayName("Test new CompactionCommitMessageSerDe (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionCommitMessageSerDe.<init>()"})
  void testNewCompactionCommitMessageSerDe() {
    // Arrange and Act
    CompactionCommitMessageSerDe actualCompactionCommitMessageSerDe =
        new CompactionCommitMessageSerDe();

    // Assert
    assertEquals("null", actualCompactionCommitMessageSerDe.toJson(null));
    assertEquals("null", actualCompactionCommitMessageSerDe.toJsonPrettyPrint(null));
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}.
   *
   * <p>Method under test: {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}
   */
  @Test
  @DisplayName("Test toJson(CompactionCommitMessage)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionCommitMessageSerDe.toJson(CompactionCommitMessage)"})
  void testToJson() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    Builder builderResult = ReplaceFileReferencesRequest.builder();

    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(true)
                    .filename("foo.txt")
                    .jobId("42")
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("42", request);

    // Act
    String actualToJsonResult = compactionCommitMessageSerDe.toJson(message);

    // Assert
    assertEquals(
        "{\"tableId\":\"42\",\"request\":{\"jobId\":\"42\",\"taskId\":\"42\",\"jobRunId\":\"42\",\"inputFiles\":[],\"newReference\""
            + ":{\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate\":true,"
            + "\"onlyContainsDataForThisPartition\":true}}}",
        actualToJsonResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}.
   *
   * <p>Method under test: {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}
   */
  @Test
  @DisplayName("Test toJson(CompactionCommitMessage)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionCommitMessageSerDe.toJson(CompactionCommitMessage)"})
  void testToJson2() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    Builder builderResult = ReplaceFileReferencesRequest.builder();

    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(true)
                    .filename("foo.txt")
                    .jobId("42")
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("", request);

    // Act
    String actualToJsonResult = compactionCommitMessageSerDe.toJson(message);

    // Assert
    assertEquals(
        "{\"tableId\":\"\",\"request\":{\"jobId\":\"42\",\"taskId\":\"42\",\"jobRunId\":\"42\",\"inputFiles\":[],\"newReference\":{"
            + "\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate\":true,"
            + "\"onlyContainsDataForThisPartition\":true}}}",
        actualToJsonResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}.
   *
   * <p>Method under test: {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}
   */
  @Test
  @DisplayName("Test toJson(CompactionCommitMessage)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionCommitMessageSerDe.toJson(CompactionCommitMessage)"})
  void testToJson3() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    Builder builderResult = ReplaceFileReferencesRequest.builder();

    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(false)
                    .filename("foo.txt")
                    .jobId("42")
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("42", request);

    // Act
    String actualToJsonResult = compactionCommitMessageSerDe.toJson(message);

    // Assert
    assertEquals(
        "{\"tableId\":\"42\",\"request\":{\"jobId\":\"42\",\"taskId\":\"42\",\"jobRunId\":\"42\",\"inputFiles\":[],\"newReference\""
            + ":{\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate\":false,"
            + "\"onlyContainsDataForThisPartition\":true}}}",
        actualToJsonResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}.
   *
   * <p>Method under test: {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}
   */
  @Test
  @DisplayName("Test toJson(CompactionCommitMessage)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionCommitMessageSerDe.toJson(CompactionCommitMessage)"})
  void testToJson4() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    Builder builderResult = ReplaceFileReferencesRequest.builder();

    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(true)
                    .filename("foo.txt")
                    .jobId(null)
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("42", request);

    // Act
    String actualToJsonResult = compactionCommitMessageSerDe.toJson(message);

    // Assert
    assertEquals(
        "{\"tableId\":\"42\",\"request\":{\"jobId\":\"42\",\"taskId\":\"42\",\"jobRunId\":\"42\",\"inputFiles\":[],\"newReference\""
            + ":{\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"countApproximate\":true,\"onlyContainsD"
            + "ataForThisPartition\":true}}}",
        actualToJsonResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}
   */
  @Test
  @DisplayName(
      "Test toJson(CompactionCommitMessage); given '42'; when ArrayList() add '42'; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionCommitMessageSerDe.toJson(CompactionCommitMessage)"})
  void testToJson_given42_whenArrayListAdd42_thenReturnAString() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("42");
    inputFiles.add("foo");

    Builder jobRunIdResult =
        ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(true)
                    .filename("foo.txt")
                    .jobId("42")
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("42", request);

    // Act
    String actualToJsonResult = compactionCommitMessageSerDe.toJson(message);

    // Assert
    assertEquals(
        "{\"tableId\":\"42\",\"request\":{\"jobId\":\"42\",\"taskId\":\"42\",\"jobRunId\":\"42\",\"inputFiles\":[\"42\",\"foo\"],"
            + "\"newReference\":{\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate"
            + "\":true,\"onlyContainsDataForThisPartition\":true}}}",
        actualToJsonResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}
   */
  @Test
  @DisplayName(
      "Test toJson(CompactionCommitMessage); given 'foo'; when ArrayList() add 'foo'; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionCommitMessageSerDe.toJson(CompactionCommitMessage)"})
  void testToJson_givenFoo_whenArrayListAddFoo_thenReturnAString() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("foo");

    Builder jobRunIdResult =
        ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(true)
                    .filename("foo.txt")
                    .jobId("42")
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("42", request);

    // Act
    String actualToJsonResult = compactionCommitMessageSerDe.toJson(message);

    // Assert
    assertEquals(
        "{\"tableId\":\"42\",\"request\":{\"jobId\":\"42\",\"taskId\":\"42\",\"jobRunId\":\"42\",\"inputFiles\":[\"foo\"],\"newReference"
            + "\":{\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate\":true,"
            + "\"onlyContainsDataForThisPartition\":true}}}",
        actualToJsonResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionCommitMessageSerDe#toJson(CompactionCommitMessage)}
   */
  @Test
  @DisplayName("Test toJson(CompactionCommitMessage); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CompactionCommitMessageSerDe.toJson(CompactionCommitMessage)"})
  void testToJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", new CompactionCommitMessageSerDe().toJson(null));
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}.
   *
   * <p>Method under test: {@link
   * CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionCommitMessage)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CompactionCommitMessageSerDe.toJsonPrettyPrint(CompactionCommitMessage)"
  })
  void testToJsonPrettyPrint() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    Builder builderResult = ReplaceFileReferencesRequest.builder();

    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(true)
                    .filename("foo.txt")
                    .jobId("42")
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("42", request);

    // Act
    String actualToJsonPrettyPrintResult = compactionCommitMessageSerDe.toJsonPrettyPrint(message);

    // Assert
    assertEquals(
        "{\n"
            + "  \"tableId\": \"42\",\n"
            + "  \"request\": {\n"
            + "    \"jobId\": \"42\",\n"
            + "    \"taskId\": \"42\",\n"
            + "    \"jobRunId\": \"42\",\n"
            + "    \"inputFiles\": [],\n"
            + "    \"newReference\": {\n"
            + "      \"filename\": \"foo.txt\",\n"
            + "      \"partitionId\": \"42\",\n"
            + "      \"numberOfRecords\": 1,\n"
            + "      \"jobId\": \"42\",\n"
            + "      \"countApproximate\": true,\n"
            + "      \"onlyContainsDataForThisPartition\": true\n"
            + "    }\n"
            + "  }\n"
            + "}",
        actualToJsonPrettyPrintResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}.
   *
   * <p>Method under test: {@link
   * CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionCommitMessage)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CompactionCommitMessageSerDe.toJsonPrettyPrint(CompactionCommitMessage)"
  })
  void testToJsonPrettyPrint2() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    Builder builderResult = ReplaceFileReferencesRequest.builder();

    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(true)
                    .filename("foo.txt")
                    .jobId("42")
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("", request);

    // Act
    String actualToJsonPrettyPrintResult = compactionCommitMessageSerDe.toJsonPrettyPrint(message);

    // Assert
    assertEquals(
        "{\n"
            + "  \"tableId\": \"\",\n"
            + "  \"request\": {\n"
            + "    \"jobId\": \"42\",\n"
            + "    \"taskId\": \"42\",\n"
            + "    \"jobRunId\": \"42\",\n"
            + "    \"inputFiles\": [],\n"
            + "    \"newReference\": {\n"
            + "      \"filename\": \"foo.txt\",\n"
            + "      \"partitionId\": \"42\",\n"
            + "      \"numberOfRecords\": 1,\n"
            + "      \"jobId\": \"42\",\n"
            + "      \"countApproximate\": true,\n"
            + "      \"onlyContainsDataForThisPartition\": true\n"
            + "    }\n"
            + "  }\n"
            + "}",
        actualToJsonPrettyPrintResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}.
   *
   * <p>Method under test: {@link
   * CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionCommitMessage)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CompactionCommitMessageSerDe.toJsonPrettyPrint(CompactionCommitMessage)"
  })
  void testToJsonPrettyPrint3() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    Builder builderResult = ReplaceFileReferencesRequest.builder();

    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(false)
                    .filename("foo.txt")
                    .jobId("42")
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("42", request);

    // Act
    String actualToJsonPrettyPrintResult = compactionCommitMessageSerDe.toJsonPrettyPrint(message);

    // Assert
    assertEquals(
        "{\n"
            + "  \"tableId\": \"42\",\n"
            + "  \"request\": {\n"
            + "    \"jobId\": \"42\",\n"
            + "    \"taskId\": \"42\",\n"
            + "    \"jobRunId\": \"42\",\n"
            + "    \"inputFiles\": [],\n"
            + "    \"newReference\": {\n"
            + "      \"filename\": \"foo.txt\",\n"
            + "      \"partitionId\": \"42\",\n"
            + "      \"numberOfRecords\": 1,\n"
            + "      \"jobId\": \"42\",\n"
            + "      \"countApproximate\": false,\n"
            + "      \"onlyContainsDataForThisPartition\": true\n"
            + "    }\n"
            + "  }\n"
            + "}",
        actualToJsonPrettyPrintResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}.
   *
   * <p>Method under test: {@link
   * CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionCommitMessage)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CompactionCommitMessageSerDe.toJsonPrettyPrint(CompactionCommitMessage)"
  })
  void testToJsonPrettyPrint4() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    Builder builderResult = ReplaceFileReferencesRequest.builder();

    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(true)
                    .filename("foo.txt")
                    .jobId(null)
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("42", request);

    // Act
    String actualToJsonPrettyPrintResult = compactionCommitMessageSerDe.toJsonPrettyPrint(message);

    // Assert
    assertEquals(
        "{\n"
            + "  \"tableId\": \"42\",\n"
            + "  \"request\": {\n"
            + "    \"jobId\": \"42\",\n"
            + "    \"taskId\": \"42\",\n"
            + "    \"jobRunId\": \"42\",\n"
            + "    \"inputFiles\": [],\n"
            + "    \"newReference\": {\n"
            + "      \"filename\": \"foo.txt\",\n"
            + "      \"partitionId\": \"42\",\n"
            + "      \"numberOfRecords\": 1,\n"
            + "      \"countApproximate\": true,\n"
            + "      \"onlyContainsDataForThisPartition\": true\n"
            + "    }\n"
            + "  }\n"
            + "}",
        actualToJsonPrettyPrintResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link
   * CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}
   */
  @Test
  @DisplayName(
      "Test toJsonPrettyPrint(CompactionCommitMessage); given '42'; when ArrayList() add '42'; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CompactionCommitMessageSerDe.toJsonPrettyPrint(CompactionCommitMessage)"
  })
  void testToJsonPrettyPrint_given42_whenArrayListAdd42_thenReturnAString() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("42");
    inputFiles.add("foo");

    Builder jobRunIdResult =
        ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(true)
                    .filename("foo.txt")
                    .jobId("42")
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("42", request);

    // Act
    String actualToJsonPrettyPrintResult = compactionCommitMessageSerDe.toJsonPrettyPrint(message);

    // Assert
    assertEquals(
        "{\n"
            + "  \"tableId\": \"42\",\n"
            + "  \"request\": {\n"
            + "    \"jobId\": \"42\",\n"
            + "    \"taskId\": \"42\",\n"
            + "    \"jobRunId\": \"42\",\n"
            + "    \"inputFiles\": [\n"
            + "      \"42\",\n"
            + "      \"foo\"\n"
            + "    ],\n"
            + "    \"newReference\": {\n"
            + "      \"filename\": \"foo.txt\",\n"
            + "      \"partitionId\": \"42\",\n"
            + "      \"numberOfRecords\": 1,\n"
            + "      \"jobId\": \"42\",\n"
            + "      \"countApproximate\": true,\n"
            + "      \"onlyContainsDataForThisPartition\": true\n"
            + "    }\n"
            + "  }\n"
            + "}",
        actualToJsonPrettyPrintResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link
   * CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}
   */
  @Test
  @DisplayName(
      "Test toJsonPrettyPrint(CompactionCommitMessage); given 'foo'; when ArrayList() add 'foo'; then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CompactionCommitMessageSerDe.toJsonPrettyPrint(CompactionCommitMessage)"
  })
  void testToJsonPrettyPrint_givenFoo_whenArrayListAddFoo_thenReturnAString() {
    // Arrange
    CompactionCommitMessageSerDe compactionCommitMessageSerDe = new CompactionCommitMessageSerDe();

    ArrayList<String> inputFiles = new ArrayList<>();
    inputFiles.add("foo");

    Builder jobRunIdResult =
        ReplaceFileReferencesRequest.builder().inputFiles(inputFiles).jobId("42").jobRunId("42");
    ReplaceFileReferencesRequest request =
        jobRunIdResult
            .newReference(
                FileReference.builder()
                    .countApproximate(true)
                    .filename("foo.txt")
                    .jobId("42")
                    .lastStateStoreUpdateTime(
                        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                    .numberOfRecords(1L)
                    .onlyContainsDataForThisPartition(true)
                    .partitionId("42")
                    .build())
            .taskId("42")
            .build();
    CompactionCommitMessage message = new CompactionCommitMessage("42", request);

    // Act
    String actualToJsonPrettyPrintResult = compactionCommitMessageSerDe.toJsonPrettyPrint(message);

    // Assert
    assertEquals(
        "{\n"
            + "  \"tableId\": \"42\",\n"
            + "  \"request\": {\n"
            + "    \"jobId\": \"42\",\n"
            + "    \"taskId\": \"42\",\n"
            + "    \"jobRunId\": \"42\",\n"
            + "    \"inputFiles\": [\n"
            + "      \"foo\"\n"
            + "    ],\n"
            + "    \"newReference\": {\n"
            + "      \"filename\": \"foo.txt\",\n"
            + "      \"partitionId\": \"42\",\n"
            + "      \"numberOfRecords\": 1,\n"
            + "      \"jobId\": \"42\",\n"
            + "      \"countApproximate\": true,\n"
            + "      \"onlyContainsDataForThisPartition\": true\n"
            + "    }\n"
            + "  }\n"
            + "}",
        actualToJsonPrettyPrintResult);
  }

  /**
   * Test {@link CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CompactionCommitMessageSerDe#toJsonPrettyPrint(CompactionCommitMessage)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionCommitMessage); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CompactionCommitMessageSerDe.toJsonPrettyPrint(CompactionCommitMessage)"
  })
  void testToJsonPrettyPrint_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", new CompactionCommitMessageSerDe().toJsonPrettyPrint(null));
  }
}
