package sleeper.clients.util.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.StandardJobRunReporter;
import sleeper.clients.util.table.TableFieldDefinition.HorizontalAlignment;

class TableFieldSummaryDiffblueTest {
  /**
   * Test {@link TableFieldSummary#hidden(TableField)}.
   * <p>
   * Method under test: {@link TableFieldSummary#hidden(TableField)}
   */
  @Test
  @DisplayName("Test hidden(TableField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFieldSummary TableFieldSummary.hidden(TableField)"})
  void testHidden() {
    // Arrange and Act
    TableFieldSummary actualHiddenResult = TableFieldSummary.hidden(mock(TableField.class));

    // Assert
    assertNull(actualHiddenResult.getHorizontalAlignment());
    assertEquals(0, actualHiddenResult.getIndex());
    assertEquals(0, actualHiddenResult.getMaxValueLength());
    assertFalse(actualHiddenResult.isVisible());
  }

  /**
   * Test {@link TableFieldSummary#visibleWithMaxValueLength(TableField, int)}.
   * <p>
   * Method under test: {@link TableFieldSummary#visibleWithMaxValueLength(TableField, int)}
   */
  @Test
  @DisplayName("Test visibleWithMaxValueLength(TableField, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFieldSummary TableFieldSummary.visibleWithMaxValueLength(TableField, int)"})
  void testVisibleWithMaxValueLength() {
    // Arrange and Act
    TableFieldSummary actualVisibleWithMaxValueLengthResult = TableFieldSummary
        .visibleWithMaxValueLength(mock(TableField.class), 3);

    // Assert
    assertNull(actualVisibleWithMaxValueLengthResult.getHorizontalAlignment());
    assertEquals(0, actualVisibleWithMaxValueLengthResult.getIndex());
    assertEquals(3, actualVisibleWithMaxValueLengthResult.getMaxValueLength());
    assertTrue(actualVisibleWithMaxValueLengthResult.isVisible());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableFieldSummary#getMaxValueLength()}
   *   <li>{@link TableFieldSummary#isVisible()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int TableFieldSummary.getMaxValueLength()", "boolean TableFieldSummary.isVisible()"})
  void testGettersAndSetters() {
    // Arrange
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    TableFieldSummary hiddenResult = TableFieldSummary.hidden(field);

    // Act
    int actualMaxValueLength = hiddenResult.getMaxValueLength();

    // Assert
    assertEquals(0, actualMaxValueLength);
    assertFalse(hiddenResult.isVisible());
  }

  /**
   * Test {@link TableFieldSummary#getHorizontalAlignment()}.
   * <p>
   * Method under test: {@link TableFieldSummary#getHorizontalAlignment()}
   */
  @Test
  @DisplayName("Test getHorizontalAlignment()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"HorizontalAlignment TableFieldSummary.getHorizontalAlignment()"})
  void testGetHorizontalAlignment() {
    // Arrange
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();

    // Act and Assert
    assertEquals(HorizontalAlignment.RIGHT, TableFieldSummary.hidden(field).getHorizontalAlignment());
  }

  /**
   * Test {@link TableFieldSummary#getIndex()}.
   * <p>
   * Method under test: {@link TableFieldSummary#getIndex()}
   */
  @Test
  @DisplayName("Test getIndex()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int TableFieldSummary.getIndex()"})
  void testGetIndex() {
    // Arrange
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();

    // Act and Assert
    assertEquals(1, TableFieldSummary.hidden(field).getIndex());
  }
}
