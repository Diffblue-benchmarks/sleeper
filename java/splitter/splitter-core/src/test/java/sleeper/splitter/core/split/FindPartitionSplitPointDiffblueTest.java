package sleeper.splitter.core.split;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.splitter.core.split.FindPartitionSplitPoint.SketchesLoader;

class FindPartitionSplitPointDiffblueTest {
  /**
   * Test {@link FindPartitionSplitPoint#FindPartitionSplitPoint(Schema, List, SketchesLoader)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FindPartitionSplitPoint#FindPartitionSplitPoint(Schema, List, SketchesLoader)}
   */
  @Test
  @DisplayName("Test new FindPartitionSplitPoint(Schema, List, SketchesLoader); given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FindPartitionSplitPoint.<init>(Schema, List, SketchesLoader)"})
  void testNewFindPartitionSplitPoint_given42_whenArrayListAdd42() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenReturn(new ArrayList<>());

    ArrayList<String> fileNames = new ArrayList<>();
    fileNames.add("42");
    fileNames.add("foo");

    // Act
    new FindPartitionSplitPoint(schema, fileNames, mock(SketchesLoader.class));

    // Assert
    verify(schema).getRowKeyTypes();
  }

  /**
   * Test {@link FindPartitionSplitPoint#FindPartitionSplitPoint(Schema, List, SketchesLoader)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FindPartitionSplitPoint#FindPartitionSplitPoint(Schema, List, SketchesLoader)}
   */
  @Test
  @DisplayName("Test new FindPartitionSplitPoint(Schema, List, SketchesLoader); given ArrayList(); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FindPartitionSplitPoint.<init>(Schema, List, SketchesLoader)"})
  void testNewFindPartitionSplitPoint_givenArrayList_whenArrayList() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenReturn(new ArrayList<>());

    // Act
    new FindPartitionSplitPoint(schema, new ArrayList<>(), mock(SketchesLoader.class));

    // Assert
    verify(schema).getRowKeyTypes();
  }

  /**
   * Test {@link FindPartitionSplitPoint#FindPartitionSplitPoint(Schema, List, SketchesLoader)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FindPartitionSplitPoint#FindPartitionSplitPoint(Schema, List, SketchesLoader)}
   */
  @Test
  @DisplayName("Test new FindPartitionSplitPoint(Schema, List, SketchesLoader); given 'foo'; when ArrayList() add 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FindPartitionSplitPoint.<init>(Schema, List, SketchesLoader)"})
  void testNewFindPartitionSplitPoint_givenFoo_whenArrayListAddFoo() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenReturn(new ArrayList<>());

    ArrayList<String> fileNames = new ArrayList<>();
    fileNames.add("foo");

    // Act
    new FindPartitionSplitPoint(schema, fileNames, mock(SketchesLoader.class));

    // Assert
    verify(schema).getRowKeyTypes();
  }

  /**
   * Test {@link FindPartitionSplitPoint#FindPartitionSplitPoint(Schema, List, SketchesLoader)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FindPartitionSplitPoint#FindPartitionSplitPoint(Schema, List, SketchesLoader)}
   */
  @Test
  @DisplayName("Test new FindPartitionSplitPoint(Schema, List, SketchesLoader); then throw IllegalStateException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FindPartitionSplitPoint.<init>(Schema, List, SketchesLoader)"})
  void testNewFindPartitionSplitPoint_thenThrowIllegalStateException() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenThrow(new IllegalStateException("foo"));

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> new FindPartitionSplitPoint(schema, new ArrayList<>(), mock(SketchesLoader.class)));

    verify(schema).getRowKeyTypes();
  }

  /**
   * Test {@link FindPartitionSplitPoint#splitPointForDimension(int)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FindPartitionSplitPoint#splitPointForDimension(int)}
   */
  @Test
  @DisplayName("Test splitPointForDimension(int); then throw IllegalStateException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional FindPartitionSplitPoint.splitPointForDimension(int)"})
  void testSplitPointForDimension_thenThrowIllegalStateException() throws IOException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("42", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<String> fileNames = new ArrayList<>();
    fileNames.add("foo");
    SketchesLoader sketchesLoader = mock(SketchesLoader.class);
    when(sketchesLoader.load(Mockito.<String>any()))
        .thenThrow(new IllegalStateException("Testing field {} of type {} (dimension {}) to see if it can be split"));

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> (new FindPartitionSplitPoint(schema, fileNames, sketchesLoader)).splitPointForDimension(1));
    verify(sketchesLoader).load(eq("foo"));
  }

  /**
   * Test {@link FindPartitionSplitPoint#splitPointForDimension(int)}.
   * <ul>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FindPartitionSplitPoint#splitPointForDimension(int)}
   */
  @Test
  @DisplayName("Test splitPointForDimension(int); then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional FindPartitionSplitPoint.splitPointForDimension(int)"})
  void testSplitPointForDimension_thenThrowUncheckedIOException() throws IOException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("42", new ByteArrayType()));
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    ArrayList<String> fileNames = new ArrayList<>();
    fileNames.add("foo");
    SketchesLoader sketchesLoader = mock(SketchesLoader.class);
    when(sketchesLoader.load(Mockito.<String>any()))
        .thenThrow(new IOException("Testing field {} of type {} (dimension {}) to see if it can be split"));

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> (new FindPartitionSplitPoint(schema, fileNames, sketchesLoader)).splitPointForDimension(1));
    verify(sketchesLoader).load(eq("foo"));
  }

  /**
   * Test {@link FindPartitionSplitPoint#loadSketchesFromFile(Schema, Configuration)} with {@code schema}, {@code conf}.
   * <ul>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FindPartitionSplitPoint#loadSketchesFromFile(Schema, Configuration)}
   */
  @Test
  @DisplayName("Test loadSketchesFromFile(Schema, Configuration) with 'schema', 'conf'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SketchesLoader FindPartitionSplitPoint.loadSketchesFromFile(Schema, Configuration)"})
  void testLoadSketchesFromFileWithSchemaConf_thenThrowUncheckedIOException() throws IOException {
    // Arrange
    Configuration conf = mock(Configuration.class);
    when(conf.getBoolean(Mockito.<String>any(), anyBoolean()))
        .thenThrow(new UncheckedIOException(new IOException("foo")));
    when(conf.get(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> FindPartitionSplitPoint.loadSketchesFromFile((Schema) null, conf).load("foo"));
    verify(conf).get(eq("fs.defaultFS"), eq("file:///"));
    verify(conf).getBoolean(eq("fs.hdfs.impl.disable.cache"), eq(false));
  }

  /**
   * Test {@link FindPartitionSplitPoint#loadSketchesFromFile(TableProperties, Configuration)} with {@code tableProperties}, {@code conf}.
   * <ul>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FindPartitionSplitPoint#loadSketchesFromFile(TableProperties, Configuration)}
   */
  @Test
  @DisplayName("Test loadSketchesFromFile(TableProperties, Configuration) with 'tableProperties', 'conf'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SketchesLoader FindPartitionSplitPoint.loadSketchesFromFile(TableProperties, Configuration)"})
  void testLoadSketchesFromFileWithTablePropertiesConf_thenThrowUncheckedIOException() throws IOException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Configuration conf = mock(Configuration.class);
    when(conf.getBoolean(Mockito.<String>any(), anyBoolean()))
        .thenThrow(new UncheckedIOException(new IOException("foo")));
    when(conf.get(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> FindPartitionSplitPoint.loadSketchesFromFile(tableProperties, conf).load("foo"));
    verify(conf).get(eq("fs.defaultFS"), eq("file:///"));
    verify(conf).getBoolean(eq("fs.hdfs.impl.disable.cache"), eq(false));
  }
}
