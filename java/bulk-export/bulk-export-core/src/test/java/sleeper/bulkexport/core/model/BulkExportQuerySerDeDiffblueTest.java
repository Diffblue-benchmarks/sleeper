package sleeper.bulkexport.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BulkExportQuerySerDeDiffblueTest {
  /**
   * Test new {@link BulkExportQuerySerDe} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link BulkExportQuerySerDe}
   */
  @Test
  @DisplayName("Test new BulkExportQuerySerDe (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkExportQuerySerDe.<init>()"})
  void testNewBulkExportQuerySerDe() {
    // Arrange, Act and Assert
    assertEquals("null", (new BulkExportQuerySerDe()).toJson(null));
  }

  /**
   * Test {@link BulkExportQuerySerDe#toJson(BulkExportQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportQuerySerDe#toJson(BulkExportQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportQuery, boolean) with 'query', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String BulkExportQuerySerDe.toJson(BulkExportQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new BulkExportQuerySerDe()).toJson(null, true));
  }

  /**
   * Test {@link BulkExportQuerySerDe#toJson(BulkExportQuery, boolean)} with {@code query}, {@code prettyPrint}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportQuerySerDe#toJson(BulkExportQuery, boolean)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportQuery, boolean) with 'query', 'prettyPrint'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String BulkExportQuerySerDe.toJson(BulkExportQuery, boolean)"})
  void testToJsonWithQueryPrettyPrint_whenNull_thenReturnNull2() {
    // Arrange, Act and Assert
    assertEquals("null", (new BulkExportQuerySerDe()).toJson(null, false));
  }

  /**
   * Test {@link BulkExportQuerySerDe#toJson(BulkExportQuery)} with {@code query}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportQuerySerDe#toJson(BulkExportQuery)}
   */
  @Test
  @DisplayName("Test toJson(BulkExportQuery) with 'query'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String BulkExportQuerySerDe.toJson(BulkExportQuery)"})
  void testToJsonWithQuery_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new BulkExportQuerySerDe()).toJson(null));
  }
}
