package sleeper.clients.util.table;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.job.StandardJobRunReporter;
import sleeper.clients.util.table.TableWriter.Builder;

class TableWriterDiffblueTest {
  /**
   * Test Builder {@link Builder#itemAndWriter(Object, BiConsumer)}.
   * <p>
   * Method under test: {@link Builder#itemAndWriter(Object, BiConsumer)}
   */
  @Test
  @DisplayName("Test Builder itemAndWriter(Object, BiConsumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.itemAndWriter(Object, BiConsumer)"})
  void testBuilderItemAndWriter() {
    // Arrange
    Builder builder = new Builder(TableStructure.DEFAULT, new ArrayList<>());
    BiConsumer<Object, TableRow.Builder> converter = mock(BiConsumer.class);
    doNothing().when(converter).accept(Mockito.<Object>any(), Mockito.<TableRow.Builder>any());

    // Act
    Builder actualItemAndWriterResult = builder.itemAndWriter("Item", converter);

    // Assert
    verify(converter).accept(isA(Object.class), isA(TableRow.Builder.class));
    assertSame(builder, actualItemAndWriterResult);
  }

  /**
   * Test Builder {@link Builder#itemAndWriter(Object, BiConsumer)}.
   * <p>
   * Method under test: {@link Builder#itemAndWriter(Object, BiConsumer)}
   */
  @Test
  @DisplayName("Test Builder itemAndWriter(Object, BiConsumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.itemAndWriter(Object, BiConsumer)"})
  void testBuilderItemAndWriter2() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    Builder builder = new Builder(TableStructure.DEFAULT, fields);
    BiConsumer<Object, TableRow.Builder> converter = mock(BiConsumer.class);
    doNothing().when(converter).accept(Mockito.<Object>any(), Mockito.<TableRow.Builder>any());

    // Act
    Builder actualItemAndWriterResult = builder.itemAndWriter("Item", converter);

    // Assert
    verify(converter).accept(isA(Object.class), isA(TableRow.Builder.class));
    assertSame(builder, actualItemAndWriterResult);
  }

  /**
   * Test Builder {@link Builder#itemsAndSplittingWriter(List, BiConsumer)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then calls {@link BiConsumer#accept(Object, Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#itemsAndSplittingWriter(List, BiConsumer)}
   */
  @Test
  @DisplayName("Test Builder itemsAndSplittingWriter(List, BiConsumer); given '42'; when ArrayList() add '42'; then calls accept(Object, Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.itemsAndSplittingWriter(List, BiConsumer)"})
  void testBuilderItemsAndSplittingWriter_given42_whenArrayListAdd42_thenCallsAccept() {
    // Arrange
    Builder builder = new Builder(TableStructure.DEFAULT, new ArrayList<>());

    ArrayList<Object> items = new ArrayList<>();
    items.add("42");
    BiConsumer<Object, Builder> writer = mock(BiConsumer.class);
    doNothing().when(writer).accept(Mockito.<Object>any(), Mockito.<Builder>any());

    // Act
    Builder actualItemsAndSplittingWriterResult = builder.itemsAndSplittingWriter(items, writer);

    // Assert
    verify(writer).accept(isA(Object.class), isA(Builder.class));
    assertSame(builder, actualItemsAndSplittingWriterResult);
  }

  /**
   * Test Builder {@link Builder#itemsAndSplittingWriter(List, BiConsumer)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then calls {@link BiConsumer#accept(Object, Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#itemsAndSplittingWriter(List, BiConsumer)}
   */
  @Test
  @DisplayName("Test Builder itemsAndSplittingWriter(List, BiConsumer); given '42'; when ArrayList() add '42'; then calls accept(Object, Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.itemsAndSplittingWriter(List, BiConsumer)"})
  void testBuilderItemsAndSplittingWriter_given42_whenArrayListAdd42_thenCallsAccept2() {
    // Arrange
    Builder builder = new Builder(TableStructure.DEFAULT, new ArrayList<>());

    ArrayList<Object> items = new ArrayList<>();
    items.add("42");
    items.add("42");
    BiConsumer<Object, Builder> writer = mock(BiConsumer.class);
    doNothing().when(writer).accept(Mockito.<Object>any(), Mockito.<Builder>any());

    // Act
    Builder actualItemsAndSplittingWriterResult = builder.itemsAndSplittingWriter(items, writer);

    // Assert
    verify(writer, atLeast(1)).accept(isA(Object.class), isA(Builder.class));
    assertSame(builder, actualItemsAndSplittingWriterResult);
  }

  /**
   * Test Builder {@link Builder#itemsAndSplittingWriter(List, BiConsumer)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#itemsAndSplittingWriter(List, BiConsumer)}
   */
  @Test
  @DisplayName("Test Builder itemsAndSplittingWriter(List, BiConsumer); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.itemsAndSplittingWriter(List, BiConsumer)"})
  void testBuilderItemsAndSplittingWriter_whenArrayList() {
    // Arrange
    Builder builder = new Builder(TableStructure.DEFAULT, new ArrayList<>());

    // Act and Assert
    assertSame(builder, builder.itemsAndSplittingWriter(new ArrayList<>(), mock(BiConsumer.class)));
  }

  /**
   * Test Builder {@link Builder#itemsAndWriter(List, BiConsumer)}.
   * <p>
   * Method under test: {@link Builder#itemsAndWriter(List, BiConsumer)}
   */
  @Test
  @DisplayName("Test Builder itemsAndWriter(List, BiConsumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.itemsAndWriter(List, BiConsumer)"})
  void testBuilderItemsAndWriter() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    Builder builder = new Builder(TableStructure.DEFAULT, fields);

    ArrayList<Object> items = new ArrayList<>();
    items.add("42");
    BiConsumer<Object, TableRow.Builder> writer = mock(BiConsumer.class);
    doNothing().when(writer).accept(Mockito.<Object>any(), Mockito.<TableRow.Builder>any());

    // Act
    Builder actualItemsAndWriterResult = builder.itemsAndWriter(items, writer);

    // Assert
    verify(writer).accept(isA(Object.class), isA(TableRow.Builder.class));
    assertSame(builder, actualItemsAndWriterResult);
  }

  /**
   * Test Builder {@link Builder#itemsAndWriter(List, BiConsumer)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then calls {@link BiConsumer#accept(Object, Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#itemsAndWriter(List, BiConsumer)}
   */
  @Test
  @DisplayName("Test Builder itemsAndWriter(List, BiConsumer); given '42'; when ArrayList() add '42'; then calls accept(Object, Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.itemsAndWriter(List, BiConsumer)"})
  void testBuilderItemsAndWriter_given42_whenArrayListAdd42_thenCallsAccept() {
    // Arrange
    Builder builder = new Builder(TableStructure.DEFAULT, new ArrayList<>());

    ArrayList<Object> items = new ArrayList<>();
    items.add("42");
    BiConsumer<Object, TableRow.Builder> writer = mock(BiConsumer.class);
    doNothing().when(writer).accept(Mockito.<Object>any(), Mockito.<TableRow.Builder>any());

    // Act
    Builder actualItemsAndWriterResult = builder.itemsAndWriter(items, writer);

    // Assert
    verify(writer).accept(isA(Object.class), isA(TableRow.Builder.class));
    assertSame(builder, actualItemsAndWriterResult);
  }

  /**
   * Test Builder {@link Builder#itemsAndWriter(List, BiConsumer)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then calls {@link BiConsumer#accept(Object, Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#itemsAndWriter(List, BiConsumer)}
   */
  @Test
  @DisplayName("Test Builder itemsAndWriter(List, BiConsumer); given '42'; when ArrayList() add '42'; then calls accept(Object, Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.itemsAndWriter(List, BiConsumer)"})
  void testBuilderItemsAndWriter_given42_whenArrayListAdd42_thenCallsAccept2() {
    // Arrange
    Builder builder = new Builder(TableStructure.DEFAULT, new ArrayList<>());

    ArrayList<Object> items = new ArrayList<>();
    items.add("42");
    items.add("42");
    BiConsumer<Object, TableRow.Builder> writer = mock(BiConsumer.class);
    doNothing().when(writer).accept(Mockito.<Object>any(), Mockito.<TableRow.Builder>any());

    // Act
    Builder actualItemsAndWriterResult = builder.itemsAndWriter(items, writer);

    // Assert
    verify(writer, atLeast(1)).accept(isA(Object.class), Mockito.<TableRow.Builder>any());
    assertSame(builder, actualItemsAndWriterResult);
  }

  /**
   * Test Builder {@link Builder#itemsAndWriter(List, BiConsumer)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#itemsAndWriter(List, BiConsumer)}
   */
  @Test
  @DisplayName("Test Builder itemsAndWriter(List, BiConsumer); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.itemsAndWriter(List, BiConsumer)"})
  void testBuilderItemsAndWriter_whenArrayList() {
    // Arrange
    Builder builder = new Builder(TableStructure.DEFAULT, new ArrayList<>());

    // Act and Assert
    assertSame(builder, builder.itemsAndWriter(new ArrayList<>(), mock(BiConsumer.class)));
  }

  /**
   * Test Builder {@link Builder#row(Consumer)}.
   * <p>
   * Method under test: {@link Builder#row(Consumer)}
   */
  @Test
  @DisplayName("Test Builder row(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.row(Consumer)"})
  void testBuilderRow() {
    // Arrange
    Builder builder = new Builder(TableStructure.DEFAULT, new ArrayList<>());
    Consumer<TableRow.Builder> config = mock(Consumer.class);
    doNothing().when(config).accept(Mockito.<TableRow.Builder>any());

    // Act
    Builder actualRowResult = builder.row(config);

    // Assert
    verify(config).accept(isA(TableRow.Builder.class));
    assertSame(builder, actualRowResult);
  }

  /**
   * Test Builder {@link Builder#row(Consumer)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add builder builder and one definition {@link StandardJobRunReporter#DURATION} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#row(Consumer)}
   */
  @Test
  @DisplayName("Test Builder row(Consumer); given ArrayList() add builder builder and one definition DURATION build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.row(Consumer)"})
  void testBuilderRow_givenArrayListAddBuilderBuilderAndOneDefinitionDurationBuild() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    Builder builder = new Builder(TableStructure.DEFAULT, fields);
    Consumer<TableRow.Builder> config = mock(Consumer.class);
    doNothing().when(config).accept(Mockito.<TableRow.Builder>any());

    // Act
    Builder actualRowResult = builder.row(config);

    // Assert
    verify(config).accept(isA(TableRow.Builder.class));
    assertSame(builder, actualRowResult);
  }

  /**
   * Test Builder {@link Builder#showField(boolean, TableFieldReference)}.
   * <p>
   * Method under test: {@link Builder#showField(boolean, TableFieldReference)}
   */
  @Test
  @DisplayName("Test Builder showField(boolean, TableFieldReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.showField(boolean, TableFieldReference)"})
  void testBuilderShowField() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    Builder builder = new Builder(TableStructure.DEFAULT, fields);

    // Act and Assert
    assertSame(builder, builder.showField(true, StandardJobRunReporter.DURATION));
  }

  /**
   * Test Builder {@link Builder#showField(boolean, TableFieldReference)}.
   * <p>
   * Method under test: {@link Builder#showField(boolean, TableFieldReference)}
   */
  @Test
  @DisplayName("Test Builder showField(boolean, TableFieldReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.showField(boolean, TableFieldReference)"})
  void testBuilderShowField2() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    Builder builder = new Builder(TableStructure.DEFAULT, fields);

    // Act and Assert
    assertSame(builder, builder.showField(false, StandardJobRunReporter.DURATION));
  }

  /**
   * Test Builder {@link Builder#showField(boolean, TableFieldReference)}.
   * <ul>
   *   <li>When builder builder and one definition {@link StandardJobRunReporter#DURATION} build.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#showField(boolean, TableFieldReference)}
   */
  @Test
  @DisplayName("Test Builder showField(boolean, TableFieldReference); when builder builder and one definition DURATION build")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.showField(boolean, TableFieldReference)"})
  void testBuilderShowField_whenBuilderBuilderAndOneDefinitionDurationBuild() {
    // Arrange
    Builder builder = new Builder(TableStructure.DEFAULT, new ArrayList<>());
    TableField fieldReference = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();

    // Act and Assert
    assertSame(builder, builder.showField(true, fieldReference));
  }

  /**
   * Test Builder {@link Builder#showFields(boolean, List)} with {@code boolean}, {@code List}.
   * <p>
   * Method under test: {@link Builder#showFields(boolean, List)}
   */
  @Test
  @DisplayName("Test Builder showFields(boolean, List) with 'boolean', 'List'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.showFields(boolean, List)"})
  void testBuilderShowFieldsWithBooleanList() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    Builder builder = new Builder(TableStructure.DEFAULT, fields);

    ArrayList<TableFieldReference> fields2 = new ArrayList<>();
    fields2.add(StandardJobRunReporter.DURATION);

    // Act and Assert
    assertSame(builder, builder.showFields(true, fields2));
  }

  /**
   * Test Builder {@link Builder#showFields(boolean, List)} with {@code boolean}, {@code List}.
   * <p>
   * Method under test: {@link Builder#showFields(boolean, List)}
   */
  @Test
  @DisplayName("Test Builder showFields(boolean, List) with 'boolean', 'List'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.showFields(boolean, List)"})
  void testBuilderShowFieldsWithBooleanList2() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    Builder builder = new Builder(TableStructure.DEFAULT, fields);

    ArrayList<TableFieldReference> fields2 = new ArrayList<>();
    fields2.add(StandardJobRunReporter.DURATION);

    // Act and Assert
    assertSame(builder, builder.showFields(false, fields2));
  }

  /**
   * Test Builder {@link Builder#showFields(boolean, List)} with {@code boolean}, {@code List}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#showFields(boolean, List)}
   */
  @Test
  @DisplayName("Test Builder showFields(boolean, List) with 'boolean', 'List'; when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.showFields(boolean, List)"})
  void testBuilderShowFieldsWithBooleanList_whenArrayList() {
    // Arrange
    Builder builder = new Builder(TableStructure.DEFAULT, new ArrayList<>());

    // Act and Assert
    assertSame(builder, builder.showFields(true, new ArrayList<>()));
  }

  /**
   * Test Builder {@link Builder#showFields(boolean, TableFieldReference[])} with {@code boolean}, {@code TableFieldReference[]}.
   * <p>
   * Method under test: {@link Builder#showFields(boolean, TableFieldReference[])}
   */
  @Test
  @DisplayName("Test Builder showFields(boolean, TableFieldReference[]) with 'boolean', 'TableFieldReference[]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.showFields(boolean, TableFieldReference[])"})
  void testBuilderShowFieldsWithBooleanTableFieldReference() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    Builder builder = new Builder(TableStructure.DEFAULT, fields);

    // Act and Assert
    assertSame(builder, builder.showFields(true, StandardJobRunReporter.DURATION));
  }

  /**
   * Test Builder {@link Builder#showFields(boolean, TableFieldReference[])} with {@code boolean}, {@code TableFieldReference[]}.
   * <p>
   * Method under test: {@link Builder#showFields(boolean, TableFieldReference[])}
   */
  @Test
  @DisplayName("Test Builder showFields(boolean, TableFieldReference[]) with 'boolean', 'TableFieldReference[]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.showFields(boolean, TableFieldReference[])"})
  void testBuilderShowFieldsWithBooleanTableFieldReference2() {
    // Arrange
    ArrayList<TableField> fields = new ArrayList<>();
    TableField buildResult = TableField.builder(TableWriterFactory.builder(), 1)
        .definition(StandardJobRunReporter.DURATION)
        .build();
    fields.add(buildResult);
    Builder builder = new Builder(TableStructure.DEFAULT, fields);

    // Act and Assert
    assertSame(builder, builder.showFields(false, StandardJobRunReporter.DURATION));
  }
}
