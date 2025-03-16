package sleeper.clients.util.table;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.StandardJobRunReporter;

class TableFieldIndexDiffblueTest {
  /**
   * Test {@link TableFieldIndex#getField(TableFieldReference)}.
   * <ul>
   *   <li>Then return builder builder and one definition {@link StandardJobRunReporter#DURATION} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFieldIndex#getField(TableFieldReference)}
   */
  @Test
  @DisplayName("Test getField(TableFieldReference); then return builder builder and one definition DURATION build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableField TableFieldIndex.getField(TableFieldReference)"})
  void testGetField_thenReturnBuilderBuilderAndOneDefinitionDurationBuild() {
    // Arrange
    TableFieldIndex tableFieldIndex = new TableFieldIndex(new ArrayList<>());
    TableField reference = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();

    // Act and Assert
    assertSame(reference, tableFieldIndex.getField(reference));
  }

  /**
   * Test {@link TableFieldIndex#getField(TableFieldReference)}.
   * <ul>
   *   <li>When {@link StandardJobRunReporter#DURATION}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFieldIndex#getField(TableFieldReference)}
   */
  @Test
  @DisplayName("Test getField(TableFieldReference); when DURATION; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableField TableFieldIndex.getField(TableFieldReference)"})
  void testGetField_whenDuration_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new TableFieldIndex(new ArrayList<>())).getField(StandardJobRunReporter.DURATION));
  }

  /**
   * Test {@link TableFieldIndex#getField(TableFieldReference)}.
   * <ul>
   *   <li>When {@link TableFieldReference}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFieldIndex#getField(TableFieldReference)}
   */
  @Test
  @DisplayName("Test getField(TableFieldReference); when TableFieldReference; then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableField TableFieldIndex.getField(TableFieldReference)"})
  void testGetField_whenTableFieldReference_thenThrowUnsupportedOperationException() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> (new TableFieldIndex(new ArrayList<>())).getField(mock(TableFieldReference.class)));
  }
}
