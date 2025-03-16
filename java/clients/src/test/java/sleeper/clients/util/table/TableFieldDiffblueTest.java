package sleeper.clients.util.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.StandardJobRunReporter;
import sleeper.clients.util.table.TableField.Builder;
import sleeper.clients.util.table.TableFieldDefinition.HorizontalAlignment;

class TableFieldDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#definition(TableFieldDefinition)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableField Builder.build()", "Builder Builder.definition(TableFieldDefinition)"})
  void testBuilderBuild() {
    // Arrange and Act
    TableField actualBuildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();

    // Assert
    assertEquals("DURATION", actualBuildResult.getHeader());
    TableFieldDefinition definition = actualBuildResult.getDefinition();
    assertEquals("DURATION", definition.getHeader());
    assertEquals(1, actualBuildResult.getIndex());
    assertEquals(HorizontalAlignment.RIGHT, actualBuildResult.getHorizontalAlignment());
    assertEquals(HorizontalAlignment.RIGHT, definition.getHorizontalAlignment());
  }

  /**
   * Test {@link TableField#getHeader()}.
   * <p>
   * Method under test: {@link TableField#getHeader()}
   */
  @Test
  @DisplayName("Test getHeader()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String TableField.getHeader()"})
  void testGetHeader() {
    // Arrange
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();

    // Act and Assert
    assertEquals("DURATION", buildResult.getHeader());
  }

  /**
   * Test {@link TableField#getHorizontalAlignment()}.
   * <p>
   * Method under test: {@link TableField#getHorizontalAlignment()}
   */
  @Test
  @DisplayName("Test getHorizontalAlignment()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"HorizontalAlignment TableField.getHorizontalAlignment()"})
  void testGetHorizontalAlignment() {
    // Arrange
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();

    // Act and Assert
    assertEquals(HorizontalAlignment.RIGHT, buildResult.getHorizontalAlignment());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableField#getDefinition()}
   *   <li>{@link TableField#getIndex()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFieldDefinition TableField.getDefinition()", "int TableField.getIndex()"})
  void testGettersAndSetters() {
    // Arrange
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();

    // Act
    TableFieldDefinition actualDefinition = buildResult.getDefinition();
    int actualIndex = buildResult.getIndex();

    // Assert
    assertEquals("DURATION", actualDefinition.getHeader());
    assertEquals(1, actualIndex);
    assertEquals(HorizontalAlignment.RIGHT, actualDefinition.getHorizontalAlignment());
  }
}
