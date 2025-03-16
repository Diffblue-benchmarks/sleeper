package sleeper.clients.util.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.StandardJobRunReporter;
import sleeper.clients.util.table.TableFieldDefinition.HorizontalAlignment;
import sleeper.clients.util.table.TableWriterFactory.Builder;

class TableWriterFactoryDiffblueTest {
  /**
   * Test Builder {@link Builder#addField(TableFieldDefinition)} with {@code definition}.
   * <ul>
   *   <li>When {@link StandardJobRunReporter#DURATION}.</li>
   *   <li>Then return Header is {@code DURATION}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#addField(TableFieldDefinition)}
   */
  @Test
  @DisplayName("Test Builder addField(TableFieldDefinition) with 'definition'; when DURATION; then return Header is 'DURATION'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableField Builder.addField(TableFieldDefinition)"})
  void testBuilderAddFieldWithDefinition_whenDuration_thenReturnHeaderIsDuration() {
    // Arrange
    TableFieldDefinition definition = StandardJobRunReporter.DURATION;

    // Act
    TableField actualAddFieldResult = TableWriterFactory.builder().addField(definition);

    // Assert
    assertEquals("DURATION", actualAddFieldResult.getHeader());
    assertEquals(0, actualAddFieldResult.getIndex());
    assertEquals(HorizontalAlignment.RIGHT, actualAddFieldResult.getHorizontalAlignment());
    assertSame(definition, actualAddFieldResult.getDefinition());
  }

  /**
   * Test Builder {@link Builder#addField(TableField)} with {@code field}.
   * <p>
   * Method under test: {@link Builder#addField(TableField)}
   */
  @Test
  @DisplayName("Test Builder addField(TableField) with 'field'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableField Builder.addField(TableField)"})
  void testBuilderAddFieldWithField() {
    // Arrange
    TableField field = mock(TableField.class);

    // Act and Assert
    assertSame(field, TableWriterFactory.builder().addField(field));
  }

  /**
   * Test Builder {@link Builder#addField(String)} with {@code header}.
   * <p>
   * Method under test: {@link Builder#addField(String)}
   */
  @Test
  @DisplayName("Test Builder addField(String) with 'header'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableField Builder.addField(String)"})
  void testBuilderAddFieldWithHeader() {
    // Arrange and Act
    TableField actualAddFieldResult = TableWriterFactory.builder().addField("Header");

    // Assert
    assertEquals("Header", actualAddFieldResult.getHeader());
    TableFieldDefinition definition = actualAddFieldResult.getDefinition();
    assertEquals("Header", definition.getHeader());
    assertEquals(0, actualAddFieldResult.getIndex());
    assertEquals(HorizontalAlignment.LEFT, actualAddFieldResult.getHorizontalAlignment());
    assertEquals(HorizontalAlignment.LEFT, definition.getHorizontalAlignment());
  }

  /**
   * Test Builder {@link Builder#addFields(TableFieldDefinition[])}.
   * <ul>
   *   <li>When {@link StandardJobRunReporter#DURATION}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#addFields(TableFieldDefinition[])}
   */
  @Test
  @DisplayName("Test Builder addFields(TableFieldDefinition[]); when DURATION; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addFields(TableFieldDefinition[])"})
  void testBuilderAddFields_whenDuration_thenReturnBuilder() {
    // Arrange
    Builder builderResult = TableWriterFactory.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.addFields(StandardJobRunReporter.DURATION));
  }

  /**
   * Test Builder {@link Builder#addNumericField(String)}.
   * <p>
   * Method under test: {@link Builder#addNumericField(String)}
   */
  @Test
  @DisplayName("Test Builder addNumericField(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableField Builder.addNumericField(String)"})
  void testBuilderAddNumericField() {
    // Arrange and Act
    TableField actualAddNumericFieldResult = TableWriterFactory.builder().addNumericField("Header");

    // Assert
    assertEquals("Header", actualAddNumericFieldResult.getHeader());
    TableFieldDefinition definition = actualAddNumericFieldResult.getDefinition();
    assertEquals("Header", definition.getHeader());
    assertEquals(0, actualAddNumericFieldResult.getIndex());
    assertEquals(HorizontalAlignment.RIGHT, actualAddNumericFieldResult.getHorizontalAlignment());
    assertEquals(HorizontalAlignment.RIGHT, definition.getHorizontalAlignment());
  }
}
