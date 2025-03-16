package sleeper.ingest.batcher.submitter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FileIngestRequestSerDeDiffblueTest {
  /**
   * Test {@link FileIngestRequestSerDe#toJson(String, List, String)} with {@code bucketName}, {@code keys}, {@code tableName}.
   * <p>
   * Method under test: {@link FileIngestRequestSerDe#toJson(String, List, String)}
   */
  @Test
  @DisplayName("Test toJson(String, List, String) with 'bucketName', 'keys', 'tableName'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileIngestRequestSerDe.toJson(String, List, String)"})
  void testToJsonWithBucketNameKeysTableName() {
    // Arrange
    ArrayList<String> keys = new ArrayList<>();
    keys.add("foo");

    // Act and Assert
    assertEquals("{\"files\":[\"s3://bucket-name/object-key/foo\"],\"tableName\":\"Table Name\"}",
        FileIngestRequestSerDe.toJson("s3://bucket-name/object-key", keys, "Table Name"));
  }

  /**
   * Test {@link FileIngestRequestSerDe#toJson(String, List, String)} with {@code bucketName}, {@code keys}, {@code tableName}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequestSerDe#toJson(String, List, String)}
   */
  @Test
  @DisplayName("Test toJson(String, List, String) with 'bucketName', 'keys', 'tableName'; given '42'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileIngestRequestSerDe.toJson(String, List, String)"})
  void testToJsonWithBucketNameKeysTableName_given42_thenReturnAString() {
    // Arrange
    ArrayList<String> keys = new ArrayList<>();
    keys.add("42");
    keys.add("foo");

    // Act and Assert
    assertEquals(
        "{\"files\":[\"s3://bucket-name/object-key/42\",\"s3://bucket-name/object-key/foo\"],\"tableName\":\"Table"
            + " Name\"}",
        FileIngestRequestSerDe.toJson("s3://bucket-name/object-key", keys, "Table Name"));
  }

  /**
   * Test {@link FileIngestRequestSerDe#toJson(String, List, String)} with {@code bucketName}, {@code keys}, {@code tableName}.
   * <ul>
   *   <li>Then return {@code {"files":[],"tableName":"Table Name"}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequestSerDe#toJson(String, List, String)}
   */
  @Test
  @DisplayName("Test toJson(String, List, String) with 'bucketName', 'keys', 'tableName'; then return '{\"files\":[],\"tableName\":\"Table Name\"}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileIngestRequestSerDe.toJson(String, List, String)"})
  void testToJsonWithBucketNameKeysTableName_thenReturnFilesTableNameTableName() {
    // Arrange, Act and Assert
    assertEquals("{\"files\":[],\"tableName\":\"Table Name\"}",
        FileIngestRequestSerDe.toJson("s3://bucket-name/object-key", new ArrayList<>(), "Table Name"));
  }

  /**
   * Test {@link FileIngestRequestSerDe#toJson(String, List, String)} with {@code bucketName}, {@code keys}, {@code tableName}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code {"files":[],"tableName":""}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequestSerDe#toJson(String, List, String)}
   */
  @Test
  @DisplayName("Test toJson(String, List, String) with 'bucketName', 'keys', 'tableName'; when empty string; then return '{\"files\":[],\"tableName\":\"\"}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileIngestRequestSerDe.toJson(String, List, String)"})
  void testToJsonWithBucketNameKeysTableName_whenEmptyString_thenReturnFilesTableName() {
    // Arrange, Act and Assert
    assertEquals("{\"files\":[],\"tableName\":\"\"}",
        FileIngestRequestSerDe.toJson("s3://bucket-name/object-key", new ArrayList<>(), ""));
  }

  /**
   * Test {@link FileIngestRequestSerDe#toJson(List, String)} with {@code files}, {@code tableName}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return {@code {"files":["42","foo"],"tableName":"Table Name"}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequestSerDe#toJson(List, String)}
   */
  @Test
  @DisplayName("Test toJson(List, String) with 'files', 'tableName'; given '42'; then return '{\"files\":[\"42\",\"foo\"],\"tableName\":\"Table Name\"}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileIngestRequestSerDe.toJson(List, String)"})
  void testToJsonWithFilesTableName_given42_thenReturnFiles42FooTableNameTableName() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("42");
    files.add("foo");

    // Act and Assert
    assertEquals("{\"files\":[\"42\",\"foo\"],\"tableName\":\"Table Name\"}",
        FileIngestRequestSerDe.toJson(files, "Table Name"));
  }

  /**
   * Test {@link FileIngestRequestSerDe#toJson(List, String)} with {@code files}, {@code tableName}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>Then return {@code {"files":[""],"tableName":"Table Name"}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequestSerDe#toJson(List, String)}
   */
  @Test
  @DisplayName("Test toJson(List, String) with 'files', 'tableName'; given empty string; then return '{\"files\":[\"\"],\"tableName\":\"Table Name\"}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileIngestRequestSerDe.toJson(List, String)"})
  void testToJsonWithFilesTableName_givenEmptyString_thenReturnFilesTableNameTableName() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("");

    // Act and Assert
    assertEquals("{\"files\":[\"\"],\"tableName\":\"Table Name\"}", FileIngestRequestSerDe.toJson(files, "Table Name"));
  }

  /**
   * Test {@link FileIngestRequestSerDe#toJson(List, String)} with {@code files}, {@code tableName}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>Then return {@code {"files":["foo"],"tableName":"Table Name"}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequestSerDe#toJson(List, String)}
   */
  @Test
  @DisplayName("Test toJson(List, String) with 'files', 'tableName'; given 'foo'; then return '{\"files\":[\"foo\"],\"tableName\":\"Table Name\"}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileIngestRequestSerDe.toJson(List, String)"})
  void testToJsonWithFilesTableName_givenFoo_thenReturnFilesFooTableNameTableName() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");

    // Act and Assert
    assertEquals("{\"files\":[\"foo\"],\"tableName\":\"Table Name\"}",
        FileIngestRequestSerDe.toJson(files, "Table Name"));
  }

  /**
   * Test {@link FileIngestRequestSerDe#toJson(List, String)} with {@code files}, {@code tableName}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code {"files":[],"tableName":"Table Name"}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequestSerDe#toJson(List, String)}
   */
  @Test
  @DisplayName("Test toJson(List, String) with 'files', 'tableName'; when ArrayList(); then return '{\"files\":[],\"tableName\":\"Table Name\"}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileIngestRequestSerDe.toJson(List, String)"})
  void testToJsonWithFilesTableName_whenArrayList_thenReturnFilesTableNameTableName() {
    // Arrange, Act and Assert
    assertEquals("{\"files\":[],\"tableName\":\"Table Name\"}",
        FileIngestRequestSerDe.toJson(new ArrayList<>(), "Table Name"));
  }
}
