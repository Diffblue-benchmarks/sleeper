package sleeper.bulkimport.core.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.bulkimport.core.job.BulkImportJob.Builder;

class BulkImportJobSerDeDiffblueTest {
  /**
   * Test new {@link BulkImportJobSerDe} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link BulkImportJobSerDe}
   */
  @Test
  @DisplayName("Test new BulkImportJobSerDe (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobSerDe.<init>()"})
  void testNewBulkImportJobSerDe() {
    // Arrange and Act
    BulkImportJobSerDe actualBulkImportJobSerDe = new BulkImportJobSerDe();

    // Assert
    assertEquals("null", actualBulkImportJobSerDe.toJson(null));
    assertEquals("null", actualBulkImportJobSerDe.toPrettyJson(null));
  }

  /**
   * Test {@link BulkImportJobSerDe#toJson(BulkImportJob)}.
   * <p>
   * Method under test: {@link BulkImportJobSerDe#toJson(BulkImportJob)}
   */
  @Test
  @DisplayName("Test toJson(BulkImportJob)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportJobSerDe.toJson(BulkImportJob)"})
  void testToJson() {
    // Arrange
    BulkImportJobSerDe bulkImportJobSerDe = new BulkImportJobSerDe();
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(
        "{\"id\":\"42\",\"tableName\":\"Table Name\",\"tableId\":\"42\",\"files\":[],\"className\":\"Class Name\",\"platformSpec"
            + "\":{},\"sparkConf\":{\"Key\":\"42\"}}",
        bulkImportJobSerDe.toJson(job));
  }

  /**
   * Test {@link BulkImportJobSerDe#toJson(BulkImportJob)}.
   * <p>
   * Method under test: {@link BulkImportJobSerDe#toJson(BulkImportJob)}
   */
  @Test
  @DisplayName("Test toJson(BulkImportJob)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportJobSerDe.toJson(BulkImportJob)"})
  void testToJson2() {
    // Arrange
    BulkImportJobSerDe bulkImportJobSerDe = new BulkImportJobSerDe();
    Builder classNameResult = BulkImportJob.builder().className("");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(
        "{\"id\":\"42\",\"tableName\":\"Table Name\",\"tableId\":\"42\",\"files\":[],\"className\":\"\",\"platformSpec\":{},\"sparkConf"
            + "\":{\"Key\":\"42\"}}",
        bulkImportJobSerDe.toJson(job));
  }

  /**
   * Test {@link BulkImportJobSerDe#toJson(BulkImportJob)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link ArrayList#ArrayList()} add empty string.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobSerDe#toJson(BulkImportJob)}
   */
  @Test
  @DisplayName("Test toJson(BulkImportJob); given empty string; when ArrayList() add empty string; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportJobSerDe.toJson(BulkImportJob)"})
  void testToJson_givenEmptyString_whenArrayListAddEmptyString_thenReturnAString() {
    // Arrange
    BulkImportJobSerDe bulkImportJobSerDe = new BulkImportJobSerDe();

    ArrayList<String> files = new ArrayList<>();
    files.add("");
    files.add("foo");
    Builder idResult = BulkImportJob.builder().className("Class Name").files(files).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(
        "{\"id\":\"42\",\"tableName\":\"Table Name\",\"tableId\":\"42\",\"files\":[\"\",\"foo\"],\"className\":\"Class Name\","
            + "\"platformSpec\":{},\"sparkConf\":{\"Key\":\"42\"}}",
        bulkImportJobSerDe.toJson(job));
  }

  /**
   * Test {@link BulkImportJobSerDe#toJson(BulkImportJob)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobSerDe#toJson(BulkImportJob)}
   */
  @Test
  @DisplayName("Test toJson(BulkImportJob); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportJobSerDe.toJson(BulkImportJob)"})
  void testToJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new BulkImportJobSerDe()).toJson(null));
  }

  /**
   * Test {@link BulkImportJobSerDe#toPrettyJson(BulkImportJob)}.
   * <p>
   * Method under test: {@link BulkImportJobSerDe#toPrettyJson(BulkImportJob)}
   */
  @Test
  @DisplayName("Test toPrettyJson(BulkImportJob)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportJobSerDe.toPrettyJson(BulkImportJob)"})
  void testToPrettyJson() {
    // Arrange
    BulkImportJobSerDe bulkImportJobSerDe = new BulkImportJobSerDe();
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals("{\n" + "  \"id\": \"42\",\n" + "  \"tableName\": \"Table Name\",\n" + "  \"tableId\": \"42\",\n"
        + "  \"files\": [],\n" + "  \"className\": \"Class Name\",\n" + "  \"platformSpec\": {},\n"
        + "  \"sparkConf\": {\n" + "    \"Key\": \"42\"\n" + "  }\n" + "}", bulkImportJobSerDe.toPrettyJson(job));
  }

  /**
   * Test {@link BulkImportJobSerDe#toPrettyJson(BulkImportJob)}.
   * <p>
   * Method under test: {@link BulkImportJobSerDe#toPrettyJson(BulkImportJob)}
   */
  @Test
  @DisplayName("Test toPrettyJson(BulkImportJob)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportJobSerDe.toPrettyJson(BulkImportJob)"})
  void testToPrettyJson2() {
    // Arrange
    BulkImportJobSerDe bulkImportJobSerDe = new BulkImportJobSerDe();
    Builder classNameResult = BulkImportJob.builder().className("");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals("{\n" + "  \"id\": \"42\",\n" + "  \"tableName\": \"Table Name\",\n" + "  \"tableId\": \"42\",\n"
        + "  \"files\": [],\n" + "  \"className\": \"\",\n" + "  \"platformSpec\": {},\n" + "  \"sparkConf\": {\n"
        + "    \"Key\": \"42\"\n" + "  }\n" + "}", bulkImportJobSerDe.toPrettyJson(job));
  }

  /**
   * Test {@link BulkImportJobSerDe#toPrettyJson(BulkImportJob)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link ArrayList#ArrayList()} add empty string.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobSerDe#toPrettyJson(BulkImportJob)}
   */
  @Test
  @DisplayName("Test toPrettyJson(BulkImportJob); given empty string; when ArrayList() add empty string; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportJobSerDe.toPrettyJson(BulkImportJob)"})
  void testToPrettyJson_givenEmptyString_whenArrayListAddEmptyString_thenReturnAString() {
    // Arrange
    BulkImportJobSerDe bulkImportJobSerDe = new BulkImportJobSerDe();

    ArrayList<String> files = new ArrayList<>();
    files.add("");
    files.add("foo");
    Builder idResult = BulkImportJob.builder().className("Class Name").files(files).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertEquals(
        "{\n" + "  \"id\": \"42\",\n" + "  \"tableName\": \"Table Name\",\n" + "  \"tableId\": \"42\",\n"
            + "  \"files\": [\n" + "    \"\",\n" + "    \"foo\"\n" + "  ],\n" + "  \"className\": \"Class Name\",\n"
            + "  \"platformSpec\": {},\n" + "  \"sparkConf\": {\n" + "    \"Key\": \"42\"\n" + "  }\n" + "}",
        bulkImportJobSerDe.toPrettyJson(job));
  }

  /**
   * Test {@link BulkImportJobSerDe#toPrettyJson(BulkImportJob)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobSerDe#toPrettyJson(BulkImportJob)}
   */
  @Test
  @DisplayName("Test toPrettyJson(BulkImportJob); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportJobSerDe.toPrettyJson(BulkImportJob)"})
  void testToPrettyJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new BulkImportJobSerDe()).toPrettyJson(null));
  }

  /**
   * Test {@link BulkImportJobSerDe#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BulkImportJob BulkImportJobSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new BulkImportJobSerDe()).fromJson(""));
  }
}
