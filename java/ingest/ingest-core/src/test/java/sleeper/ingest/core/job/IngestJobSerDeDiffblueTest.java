package sleeper.ingest.core.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.ingest.core.job.IngestJob.Builder;

class IngestJobSerDeDiffblueTest {
  /**
   * Test new {@link IngestJobSerDe} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link IngestJobSerDe}
   */
  @Test
  @DisplayName("Test new IngestJobSerDe (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestJobSerDe.<init>()"})
  void testNewIngestJobSerDe() {
    // Arrange, Act and Assert
    assertEquals("null", (new IngestJobSerDe()).toJson(null));
  }

  /**
   * Test {@link IngestJobSerDe#toJson(IngestJob, boolean)} with {@code ingestJob}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link IngestJobSerDe#toJson(IngestJob, boolean)}
   */
  @Test
  @DisplayName("Test toJson(IngestJob, boolean) with 'ingestJob', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobSerDe.toJson(IngestJob, boolean)"})
  void testToJsonWithIngestJobPrettyPrint() {
    // Arrange
    IngestJobSerDe ingestJobSerDe = new IngestJobSerDe();
    Builder builderResult = IngestJob.builder();
    IngestJob ingestJob = builderResult.files(new ArrayList<>()).id("42").tableId("42").tableName("Table Name").build();

    // Act and Assert
    assertEquals("{\n  \"id\": \"42\",\n  \"tableName\": \"Table Name\",\n  \"tableId\": \"42\",\n  \"files\": []\n}",
        ingestJobSerDe.toJson(ingestJob, true));
  }

  /**
   * Test {@link IngestJobSerDe#toJson(IngestJob, boolean)} with {@code ingestJob}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link IngestJobSerDe#toJson(IngestJob, boolean)}
   */
  @Test
  @DisplayName("Test toJson(IngestJob, boolean) with 'ingestJob', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobSerDe.toJson(IngestJob, boolean)"})
  void testToJsonWithIngestJobPrettyPrint2() {
    // Arrange
    IngestJobSerDe ingestJobSerDe = new IngestJobSerDe();

    ArrayList<String> files = new ArrayList<>();
    files.add("");
    IngestJob ingestJob = IngestJob.builder().files(files).id("42").tableId("42").tableName("Table Name").build();

    // Act and Assert
    assertEquals(
        "{\n  \"id\": \"42\",\n  \"tableName\": \"Table Name\",\n  \"tableId\": \"42\",\n  \"files\": [\n    \"\"\n  ]\n}",
        ingestJobSerDe.toJson(ingestJob, true));
  }

  /**
   * Test {@link IngestJobSerDe#toJson(IngestJob, boolean)} with {@code ingestJob}, {@code prettyPrint}.
   * <p>
   * Method under test: {@link IngestJobSerDe#toJson(IngestJob, boolean)}
   */
  @Test
  @DisplayName("Test toJson(IngestJob, boolean) with 'ingestJob', 'prettyPrint'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobSerDe.toJson(IngestJob, boolean)"})
  void testToJsonWithIngestJobPrettyPrint3() {
    // Arrange
    IngestJobSerDe ingestJobSerDe = new IngestJobSerDe();

    ArrayList<String> files = new ArrayList<>();
    files.add("42");
    files.add("");
    IngestJob ingestJob = IngestJob.builder().files(files).id("42").tableId("42").tableName("Table Name").build();

    // Act and Assert
    assertEquals(
        "{\n  \"id\": \"42\",\n  \"tableName\": \"Table Name\",\n  \"tableId\": \"42\",\n  \"files\": [\n    \"42\",\n    \"\"\n  ]\n}",
        ingestJobSerDe.toJson(ingestJob, true));
  }

  /**
   * Test {@link IngestJobSerDe#toJson(IngestJob, boolean)} with {@code ingestJob}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobSerDe#toJson(IngestJob, boolean)}
   */
  @Test
  @DisplayName("Test toJson(IngestJob, boolean) with 'ingestJob', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobSerDe.toJson(IngestJob, boolean)"})
  void testToJsonWithIngestJobPrettyPrint_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new IngestJobSerDe()).toJson(null, true));
  }

  /**
   * Test {@link IngestJobSerDe#toJson(IngestJob, boolean)} with {@code ingestJob}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobSerDe#toJson(IngestJob, boolean)}
   */
  @Test
  @DisplayName("Test toJson(IngestJob, boolean) with 'ingestJob', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobSerDe.toJson(IngestJob, boolean)"})
  void testToJsonWithIngestJobPrettyPrint_whenNull_thenReturnNull2() {
    // Arrange, Act and Assert
    assertEquals("null", (new IngestJobSerDe()).toJson(null, false));
  }

  /**
   * Test {@link IngestJobSerDe#toJson(IngestJob)} with {@code ingestJob}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return {@code {"id":"42","tableName":"Table Name","tableId":"42","files":["42",""]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobSerDe#toJson(IngestJob)}
   */
  @Test
  @DisplayName("Test toJson(IngestJob) with 'ingestJob'; given '42'; then return '{\"id\":\"42\",\"tableName\":\"Table Name\",\"tableId\":\"42\",\"files\":[\"42\",\"\"]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobSerDe.toJson(IngestJob)"})
  void testToJsonWithIngestJob_given42_thenReturnId42TableNameTableNameTableId42Files42() {
    // Arrange
    IngestJobSerDe ingestJobSerDe = new IngestJobSerDe();

    ArrayList<String> files = new ArrayList<>();
    files.add("42");
    files.add("");
    IngestJob ingestJob = IngestJob.builder().files(files).id("42").tableId("42").tableName("Table Name").build();

    // Act and Assert
    assertEquals("{\"id\":\"42\",\"tableName\":\"Table Name\",\"tableId\":\"42\",\"files\":[\"42\",\"\"]}",
        ingestJobSerDe.toJson(ingestJob));
  }

  /**
   * Test {@link IngestJobSerDe#toJson(IngestJob)} with {@code ingestJob}.
   * <ul>
   *   <li>Then return {@code {"id":"42","tableName":"Table Name","tableId":"42","files":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobSerDe#toJson(IngestJob)}
   */
  @Test
  @DisplayName("Test toJson(IngestJob) with 'ingestJob'; then return '{\"id\":\"42\",\"tableName\":\"Table Name\",\"tableId\":\"42\",\"files\":[]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobSerDe.toJson(IngestJob)"})
  void testToJsonWithIngestJob_thenReturnId42TableNameTableNameTableId42Files() {
    // Arrange
    IngestJobSerDe ingestJobSerDe = new IngestJobSerDe();
    Builder builderResult = IngestJob.builder();
    IngestJob ingestJob = builderResult.files(new ArrayList<>()).id("42").tableId("42").tableName("Table Name").build();

    // Act and Assert
    assertEquals("{\"id\":\"42\",\"tableName\":\"Table Name\",\"tableId\":\"42\",\"files\":[]}",
        ingestJobSerDe.toJson(ingestJob));
  }

  /**
   * Test {@link IngestJobSerDe#toJson(IngestJob)} with {@code ingestJob}.
   * <ul>
   *   <li>Then return {@code {"id":"42","tableName":"Table Name","tableId":"42","files":[""]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobSerDe#toJson(IngestJob)}
   */
  @Test
  @DisplayName("Test toJson(IngestJob) with 'ingestJob'; then return '{\"id\":\"42\",\"tableName\":\"Table Name\",\"tableId\":\"42\",\"files\":[\"\"]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobSerDe.toJson(IngestJob)"})
  void testToJsonWithIngestJob_thenReturnId42TableNameTableNameTableId42Files2() {
    // Arrange
    IngestJobSerDe ingestJobSerDe = new IngestJobSerDe();

    ArrayList<String> files = new ArrayList<>();
    files.add("");
    IngestJob ingestJob = IngestJob.builder().files(files).id("42").tableId("42").tableName("Table Name").build();

    // Act and Assert
    assertEquals("{\"id\":\"42\",\"tableName\":\"Table Name\",\"tableId\":\"42\",\"files\":[\"\"]}",
        ingestJobSerDe.toJson(ingestJob));
  }

  /**
   * Test {@link IngestJobSerDe#toJson(IngestJob)} with {@code ingestJob}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobSerDe#toJson(IngestJob)}
   */
  @Test
  @DisplayName("Test toJson(IngestJob) with 'ingestJob'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestJobSerDe.toJson(IngestJob)"})
  void testToJsonWithIngestJob_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new IngestJobSerDe()).toJson(null));
  }

  /**
   * Test {@link IngestJobSerDe#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJob IngestJobSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new IngestJobSerDe()).fromJson(""));
  }
}
