package sleeper.clients.util.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.StandardJobRunReporter;
import sleeper.clients.util.table.TableRow.Builder;

class TableRowDiffblueTest {
  /**
   * Test Builder {@link Builder#value(TableFieldReference, Object)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add builder builder and one definition {@link StandardJobRunReporter#DURATION} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#value(TableFieldReference, Object)}
   */
  @Test
  @DisplayName("Test Builder value(TableFieldReference, Object); given ArrayList() add builder builder and one definition DURATION build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.value(TableFieldReference, Object)"})
  void testBuilderValue_givenArrayListAddBuilderBuilderAndOneDefinitionDurationBuild() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    Builder builder = new Builder(3, new TableFieldIndex(fields));

    // Act and Assert
    assertSame(builder, builder.value(StandardJobRunReporter.DURATION, "Value"));
  }

  /**
   * Test Builder {@link Builder#value(TableFieldReference, Object)}.
   * <ul>
   *   <li>When builder builder and one definition {@link StandardJobRunReporter#DURATION} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#value(TableFieldReference, Object)}
   */
  @Test
  @DisplayName("Test Builder value(TableFieldReference, Object); when builder builder and one definition DURATION build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.value(TableFieldReference, Object)"})
  void testBuilderValue_whenBuilderBuilderAndOneDefinitionDurationBuild() {
    // Arrange
    Builder builder = new Builder(3, new TableFieldIndex(new ArrayList<>()));
    TableField fieldReference = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();

    // Act and Assert
    assertSame(builder, builder.value(fieldReference, "Value"));
  }

  /**
   * Test {@link TableRow#getValueLength(int)}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableRow#getValueLength(int)}
   */
  @Test
  @DisplayName("Test getValueLength(int); then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int TableRow.getValueLength(int)"})
  void testGetValueLength_thenReturnZero() {
    // Arrange
    TableRow buildResult = (new Builder(3, new TableFieldIndex(new ArrayList<>()))).build();

    // Act and Assert
    assertEquals(0, buildResult.getValueLength(1));
  }

  /**
   * Test {@link TableRow#getValue(int)}.
   * <ul>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableRow#getValue(int)}
   */
  @Test
  @DisplayName("Test getValue(int); then return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String TableRow.getValue(int)"})
  void testGetValue_thenReturnEmptyString() {
    // Arrange
    TableRow buildResult = (new Builder(3, new TableFieldIndex(new ArrayList<>()))).build();

    // Act and Assert
    assertEquals("", buildResult.getValue(1));
  }
}
