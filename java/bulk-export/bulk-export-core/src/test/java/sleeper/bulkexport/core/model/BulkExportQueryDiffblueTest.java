package sleeper.bulkexport.core.model;

import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.bulkexport.core.model.BulkExportQuery.Builder;

class BulkExportQueryDiffblueTest {
  /**
   * Test {@link BulkExportQuery#builder()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BulkExportQuery#builder()}
   *   <li>{@link BulkExportQuery#exportId(String)}
   *   <li>{@link BulkExportQuery#tableId(String)}
   *   <li>{@link BulkExportQuery#tableName(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BulkExportQuery Builder.build()", "Builder Builder.exportId(String)",
      "Builder Builder.tableId(String)", "Builder Builder.tableName(String)"})
  void testBuilder() {
    // Arrange and Act
    Builder actualTableIdResult = BulkExportQuery.builder().exportId("42").tableId("42");

    // Assert
    assertSame(actualTableIdResult, actualTableIdResult.tableName("Table Name"));
  }
}
