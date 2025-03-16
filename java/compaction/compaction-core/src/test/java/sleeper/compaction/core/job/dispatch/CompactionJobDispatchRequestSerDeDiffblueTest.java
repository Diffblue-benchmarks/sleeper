package sleeper.compaction.core.job.dispatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

class CompactionJobDispatchRequestSerDeDiffblueTest {
  /**
   * Test new {@link CompactionJobDispatchRequestSerDe} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CompactionJobDispatchRequestSerDe}
   */
  @Test
  @DisplayName("Test new CompactionJobDispatchRequestSerDe (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatchRequestSerDe.<init>()"})
  void testNewCompactionJobDispatchRequestSerDe() {
    // Arrange and Act
    CompactionJobDispatchRequestSerDe actualCompactionJobDispatchRequestSerDe = new CompactionJobDispatchRequestSerDe();

    // Assert
    assertEquals("null", actualCompactionJobDispatchRequestSerDe.toJson(null));
    assertEquals("null", actualCompactionJobDispatchRequestSerDe.toJsonPrettyPrint(null));
  }

  /**
   * Test {@link CompactionJobDispatchRequestSerDe#toJsonPrettyPrint(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Then return {@code { "batchKey": "null/compactions/42.json", "createTime": 0 }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatchRequestSerDe#toJsonPrettyPrint(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionJobDispatchRequest); then return '{ \"batchKey\": \"null/compactions/42.json\", \"createTime\": 0 }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobDispatchRequestSerDe.toJsonPrettyPrint(CompactionJobDispatchRequest)"})
  void testToJsonPrettyPrint_thenReturnBatchKeyNullCompactions42JsonCreateTime0() {
    // Arrange
    CompactionJobDispatchRequestSerDe compactionJobDispatchRequestSerDe = new CompactionJobDispatchRequestSerDe();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act and Assert
    assertEquals("{\n  \"batchKey\": \"null/compactions/42.json\",\n  \"createTime\": 0\n}",
        compactionJobDispatchRequestSerDe.toJsonPrettyPrint(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            tableProperties, "42", LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
  }

  /**
   * Test {@link CompactionJobDispatchRequestSerDe#toJsonPrettyPrint(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatchRequestSerDe#toJsonPrettyPrint(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(CompactionJobDispatchRequest); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobDispatchRequestSerDe.toJsonPrettyPrint(CompactionJobDispatchRequest)"})
  void testToJsonPrettyPrint_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new CompactionJobDispatchRequestSerDe()).toJsonPrettyPrint(null));
  }

  /**
   * Test {@link CompactionJobDispatchRequestSerDe#toJson(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Then return {@code {"batchKey":"null/compactions/42.json","createTime":0}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatchRequestSerDe#toJson(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test toJson(CompactionJobDispatchRequest); then return '{\"batchKey\":\"null/compactions/42.json\",\"createTime\":0}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobDispatchRequestSerDe.toJson(CompactionJobDispatchRequest)"})
  void testToJson_thenReturnBatchKeyNullCompactions42JsonCreateTime0() {
    // Arrange
    CompactionJobDispatchRequestSerDe compactionJobDispatchRequestSerDe = new CompactionJobDispatchRequestSerDe();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act and Assert
    assertEquals("{\"batchKey\":\"null/compactions/42.json\",\"createTime\":0}",
        compactionJobDispatchRequestSerDe.toJson(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties,
            "42", LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
  }

  /**
   * Test {@link CompactionJobDispatchRequestSerDe#toJson(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatchRequestSerDe#toJson(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test toJson(CompactionJobDispatchRequest); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobDispatchRequestSerDe.toJson(CompactionJobDispatchRequest)"})
  void testToJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new CompactionJobDispatchRequestSerDe()).toJson(null));
  }

  /**
   * Test {@link CompactionJobDispatchRequestSerDe#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatchRequestSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobDispatchRequest CompactionJobDispatchRequestSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new CompactionJobDispatchRequestSerDe()).fromJson(""));
  }
}
