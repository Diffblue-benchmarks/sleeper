package sleeper.clients.util.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.StandardJobRunReporter;
import sleeper.clients.util.table.TableFieldDefinition.Builder;
import sleeper.clients.util.table.TableFieldDefinition.HorizontalAlignment;

class TableFieldDefinitionDiffblueTest {
  /**
   * Test Builder {@link Builder#alignLeft()}.
   * <p>
   * Method under test: {@link Builder#alignLeft()}
   */
  @Test
  @DisplayName("Test Builder alignLeft()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.alignLeft()"})
  void testBuilderAlignLeft() {
    // Arrange
    Builder builderResult = TableFieldDefinition.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.alignLeft());
  }

  /**
   * Test Builder {@link Builder#alignRight()}.
   * <p>
   * Method under test: {@link Builder#alignRight()}
   */
  @Test
  @DisplayName("Test Builder alignRight()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.alignRight()"})
  void testBuilderAlignRight() {
    // Arrange
    Builder builderResult = TableFieldDefinition.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.alignRight());
  }

  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#header(String)}
   *   <li>{@link Builder#horizontalAlignment(HorizontalAlignment)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFieldDefinition Builder.build()", "Builder Builder.header(String)",
      "Builder Builder.horizontalAlignment(HorizontalAlignment)"})
  void testBuilderBuild() {
    // Arrange and Act
    TableFieldDefinition actualBuildResult = TableFieldDefinition.builder()
        .header("Header")
        .horizontalAlignment(HorizontalAlignment.LEFT)
        .build();

    // Assert
    assertEquals("Header", actualBuildResult.getHeader());
    assertEquals(HorizontalAlignment.LEFT, actualBuildResult.getHorizontalAlignment());
  }

  /**
   * Test {@link TableFieldDefinition#numeric(String)}.
   * <p>
   * Method under test: {@link TableFieldDefinition#numeric(String)}
   */
  @Test
  @DisplayName("Test numeric(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFieldDefinition TableFieldDefinition.numeric(String)"})
  void testNumeric() {
    // Arrange and Act
    TableFieldDefinition actualNumericResult = TableFieldDefinition.numeric("Header");

    // Assert
    assertEquals("Header", actualNumericResult.getHeader());
    assertEquals(HorizontalAlignment.RIGHT, actualNumericResult.getHorizontalAlignment());
  }

  /**
   * Test {@link TableFieldDefinition#field(String)}.
   * <p>
   * Method under test: {@link TableFieldDefinition#field(String)}
   */
  @Test
  @DisplayName("Test field(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFieldDefinition TableFieldDefinition.field(String)"})
  void testField() {
    // Arrange and Act
    TableFieldDefinition actualFieldResult = TableFieldDefinition.field("Header");

    // Assert
    assertEquals("Header", actualFieldResult.getHeader());
    assertEquals(HorizontalAlignment.LEFT, actualFieldResult.getHorizontalAlignment());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableFieldDefinition#getHeader()}
   *   <li>{@link TableFieldDefinition#getHorizontalAlignment()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableFieldDefinition.getHeader()",
      "HorizontalAlignment TableFieldDefinition.getHorizontalAlignment()"})
  void testGettersAndSetters() {
    // Arrange
    TableFieldDefinition tableFieldDefinition = StandardJobRunReporter.DURATION;

    // Act
    String actualHeader = tableFieldDefinition.getHeader();

    // Assert
    assertEquals("DURATION", actualHeader);
    assertEquals(HorizontalAlignment.RIGHT, tableFieldDefinition.getHorizontalAlignment());
  }
}
