package sleeper.clients.util.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.StandardJobRunReporter;

class TableStructureDiffblueTest {
  /**
   * Test {@link TableStructure#paddingLengthForFields(int)}.
   * <p>
   * Method under test: {@link TableStructure#paddingLengthForFields(int)}
   */
  @Test
  @DisplayName("Test paddingLengthForFields(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int TableStructure.paddingLengthForFields(int)"})
  void testPaddingLengthForFields() {
    // Arrange, Act and Assert
    assertEquals(7, TableStructure.DEFAULT.paddingLengthForFields(2));
  }

  /**
   * Test {@link TableStructure#horizontalBorder(int)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code ---}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#horizontalBorder(int)}
   */
  @Test
  @DisplayName("Test horizontalBorder(int); when three; then return '---'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.horizontalBorder(int)"})
  void testHorizontalBorder_whenThree_thenReturnDashDashDash() {
    // Arrange, Act and Assert
    assertEquals("---", TableStructure.DEFAULT.horizontalBorder(3));
  }

  /**
   * Test {@link TableStructure#horizontalBorder(int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#horizontalBorder(int)}
   */
  @Test
  @DisplayName("Test horizontalBorder(int); when zero; then return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.horizontalBorder(int)"})
  void testHorizontalBorder_whenZero_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", TableStructure.DEFAULT.horizontalBorder(0));
  }

  /**
   * Test {@link TableStructure#headerRow(List, List)}.
   * <p>
   * Method under test: {@link TableStructure#headerRow(List, List)}
   */
  @Test
  @DisplayName("Test headerRow(List, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.headerRow(List, List)"})
  void testHeaderRow() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    TableField buildResult2 = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult2);

    ArrayList<TableFieldSummary> fieldSummaries = new ArrayList<>();
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fieldSummaries.add(TableFieldSummary.visibleWithMaxValueLength(field, 3));

    // Act and Assert
    assertEquals("| DURATION |", TableStructure.DEFAULT.headerRow(fields, fieldSummaries));
  }

  /**
   * Test {@link TableStructure#headerRow(List, List)}.
   * <p>
   * Method under test: {@link TableStructure#headerRow(List, List)}
   */
  @Test
  @DisplayName("Test headerRow(List, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.headerRow(List, List)"})
  void testHeaderRow2() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    TableField buildResult2 = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult2);

    ArrayList<TableFieldSummary> fieldSummaries = new ArrayList<>();
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.FINISH_TIME)
        .build();
    fieldSummaries.add(TableFieldSummary.visibleWithMaxValueLength(field, 3));

    // Act and Assert
    assertEquals("| DURATION |", TableStructure.DEFAULT.headerRow(fields, fieldSummaries));
  }

  /**
   * Test {@link TableStructure#headerRow(List, List)}.
   * <p>
   * Method under test: {@link TableStructure#headerRow(List, List)}
   */
  @Test
  @DisplayName("Test headerRow(List, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.headerRow(List, List)"})
  void testHeaderRow3() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    TableField buildResult2 = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult2);

    ArrayList<TableFieldSummary> fieldSummaries = new ArrayList<>();
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fieldSummaries.add(TableFieldSummary.visibleWithMaxValueLength(field, 42));

    // Act and Assert
    assertEquals("|                                   DURATION |",
        TableStructure.DEFAULT.headerRow(fields, fieldSummaries));
  }

  /**
   * Test {@link TableStructure#headerRow(List, List)}.
   * <ul>
   *   <li>Given hidden builder builder and one definition {@link StandardJobRunReporter#DURATION} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#headerRow(List, List)}
   */
  @Test
  @DisplayName("Test headerRow(List, List); given hidden builder builder and one definition DURATION build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.headerRow(List, List)"})
  void testHeaderRow_givenHiddenBuilderBuilderAndOneDefinitionDurationBuild() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();

    ArrayList<TableFieldSummary> fieldSummaries = new ArrayList<>();
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fieldSummaries.add(TableFieldSummary.hidden(field));

    // Act and Assert
    assertEquals("|  |", TableStructure.DEFAULT.headerRow(fields, fieldSummaries));
  }

  /**
   * Test {@link TableStructure#headerRow(List, List)}.
   * <ul>
   *   <li>Given hidden builder builder and one definition {@link StandardJobRunReporter#DURATION} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#headerRow(List, List)}
   */
  @Test
  @DisplayName("Test headerRow(List, List); given hidden builder builder and one definition DURATION build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.headerRow(List, List)"})
  void testHeaderRow_givenHiddenBuilderBuilderAndOneDefinitionDurationBuild2() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();

    ArrayList<TableFieldSummary> fieldSummaries = new ArrayList<>();
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fieldSummaries.add(TableFieldSummary.hidden(field));
    TableField field2 = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fieldSummaries.add(TableFieldSummary.hidden(field2));

    // Act and Assert
    assertEquals("|  |", TableStructure.DEFAULT.headerRow(fields, fieldSummaries));
  }

  /**
   * Test {@link TableStructure#headerRow(List, List)}.
   * <ul>
   *   <li>Then return {@code | |}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#headerRow(List, List)}
   */
  @Test
  @DisplayName("Test headerRow(List, List); then return '| |'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.headerRow(List, List)"})
  void testHeaderRow_thenReturnVerticalLineSpaceVerticalLine() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);

    // Act and Assert
    assertEquals("|  |", TableStructure.DEFAULT.headerRow(fields, new ArrayList<>()));
  }

  /**
   * Test {@link TableStructure#headerRow(List, List)}.
   * <ul>
   *   <li>Then return {@code | |}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#headerRow(List, List)}
   */
  @Test
  @DisplayName("Test headerRow(List, List); then return '| |'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.headerRow(List, List)"})
  void testHeaderRow_thenReturnVerticalLineSpaceVerticalLine2() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    TableField buildResult2 = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult2);

    // Act and Assert
    assertEquals("|  |", TableStructure.DEFAULT.headerRow(fields, new ArrayList<>()));
  }

  /**
   * Test {@link TableStructure#headerRow(List, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code | |}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#headerRow(List, List)}
   */
  @Test
  @DisplayName("Test headerRow(List, List); when ArrayList(); then return '| |'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.headerRow(List, List)"})
  void testHeaderRow_whenArrayList_thenReturnVerticalLineSpaceVerticalLine() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();

    // Act and Assert
    assertEquals("|  |", TableStructure.DEFAULT.headerRow(fields, new ArrayList<>()));
  }

  /**
   * Test {@link TableStructure#row(TableRow, List)}.
   * <p>
   * Method under test: {@link TableStructure#row(TableRow, List)}
   */
  @Test
  @DisplayName("Test row(TableRow, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.row(TableRow, List)"})
  void testRow() {
    // Arrange
    TableRow row = mock(TableRow.class);
    when(row.getValue(anyInt())).thenReturn("42");

    ArrayList<TableFieldSummary> fieldSummaries = new ArrayList<>();
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.FINISH_TIME)
        .build();
    fieldSummaries.add(TableFieldSummary.visibleWithMaxValueLength(field, 3));

    // Act
    String actualRowResult = TableStructure.DEFAULT.row(row, fieldSummaries);

    // Assert
    verify(row).getValue(eq(1));
    assertEquals("| 42  |", actualRowResult);
  }

  /**
   * Test {@link TableStructure#row(TableRow, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link TableRow} {@link TableRow#getValue(int)} return {@code foo}.</li>
   *   <li>Then return {@code | foo |}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#row(TableRow, List)}
   */
  @Test
  @DisplayName("Test row(TableRow, List); given 'foo'; when TableRow getValue(int) return 'foo'; then return '| foo |'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.row(TableRow, List)"})
  void testRow_givenFoo_whenTableRowGetValueReturnFoo_thenReturnFoo() {
    // Arrange
    TableRow row = mock(TableRow.class);
    when(row.getValue(anyInt())).thenReturn("foo");

    ArrayList<TableFieldSummary> fieldSummaries = new ArrayList<>();
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fieldSummaries.add(TableFieldSummary.visibleWithMaxValueLength(field, 3));

    // Act
    String actualRowResult = TableStructure.DEFAULT.row(row, fieldSummaries);

    // Assert
    verify(row).getValue(eq(1));
    assertEquals("| foo |", actualRowResult);
  }

  /**
   * Test {@link TableStructure#row(TableRow, List)}.
   * <ul>
   *   <li>Given hidden builder builder and one definition {@link StandardJobRunReporter#DURATION} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#row(TableRow, List)}
   */
  @Test
  @DisplayName("Test row(TableRow, List); given hidden builder builder and one definition DURATION build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.row(TableRow, List)"})
  void testRow_givenHiddenBuilderBuilderAndOneDefinitionDurationBuild() {
    // Arrange
    TableRow row = mock(TableRow.class);

    ArrayList<TableFieldSummary> fieldSummaries = new ArrayList<>();
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fieldSummaries.add(TableFieldSummary.hidden(field));

    // Act and Assert
    assertEquals("|  |", TableStructure.DEFAULT.row(row, fieldSummaries));
  }

  /**
   * Test {@link TableStructure#row(TableRow, List)}.
   * <ul>
   *   <li>Given hidden builder builder and one definition {@link StandardJobRunReporter#DURATION} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#row(TableRow, List)}
   */
  @Test
  @DisplayName("Test row(TableRow, List); given hidden builder builder and one definition DURATION build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.row(TableRow, List)"})
  void testRow_givenHiddenBuilderBuilderAndOneDefinitionDurationBuild2() {
    // Arrange
    TableRow row = mock(TableRow.class);

    ArrayList<TableFieldSummary> fieldSummaries = new ArrayList<>();
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fieldSummaries.add(TableFieldSummary.hidden(field));
    TableField field2 = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fieldSummaries.add(TableFieldSummary.hidden(field2));

    // Act and Assert
    assertEquals("|  |", TableStructure.DEFAULT.row(row, fieldSummaries));
  }

  /**
   * Test {@link TableStructure#row(TableRow, List)}.
   * <ul>
   *   <li>Then return {@code | 42 |}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#row(TableRow, List)}
   */
  @Test
  @DisplayName("Test row(TableRow, List); then return '| 42 |'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.row(TableRow, List)"})
  void testRow_thenReturn42() {
    // Arrange
    TableRow row = mock(TableRow.class);
    when(row.getValue(anyInt())).thenReturn("42");

    ArrayList<TableFieldSummary> fieldSummaries = new ArrayList<>();
    TableField field = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fieldSummaries.add(TableFieldSummary.visibleWithMaxValueLength(field, 3));

    // Act
    String actualRowResult = TableStructure.DEFAULT.row(row, fieldSummaries);

    // Assert
    verify(row).getValue(eq(1));
    assertEquals("|  42 |", actualRowResult);
  }

  /**
   * Test {@link TableStructure#row(TableRow, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code | |}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableStructure#row(TableRow, List)}
   */
  @Test
  @DisplayName("Test row(TableRow, List); when ArrayList(); then return '| |'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableStructure.row(TableRow, List)"})
  void testRow_whenArrayList_thenReturnVerticalLineSpaceVerticalLine() {
    // Arrange
    TableRow row = mock(TableRow.class);

    // Act and Assert
    assertEquals("|  |", TableStructure.DEFAULT.row(row, new ArrayList<>()));
  }
}
