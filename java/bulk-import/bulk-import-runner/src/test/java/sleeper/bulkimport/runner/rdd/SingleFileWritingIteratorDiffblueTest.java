package sleeper.bulkimport.runner.rdd;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.sql.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.partition.PartitionTree;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;

class SingleFileWritingIteratorDiffblueTest {
  /**
   * Test {@link SingleFileWritingIterator#SingleFileWritingIterator(Iterator, InstanceProperties, TableProperties, Configuration, PartitionTree, String)}.
   * <ul>
   *   <li>Then return not hasNext.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleFileWritingIterator#SingleFileWritingIterator(Iterator, InstanceProperties, TableProperties, Configuration, PartitionTree, String)}
   */
  @Test
  @DisplayName("Test new SingleFileWritingIterator(Iterator, InstanceProperties, TableProperties, Configuration, PartitionTree, String); then return not hasNext")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SingleFileWritingIterator.<init>(Iterator, InstanceProperties, TableProperties, Configuration, PartitionTree, String)"})
  void testNewSingleFileWritingIterator_thenReturnNotHasNext() {
    // Arrange
    ArrayList<Row> rowList = new ArrayList<>();
    Iterator<Row> input = rowList.iterator();
    InstanceProperties instanceProperties = new InstanceProperties();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getSchema()).thenReturn(buildResult);

    // Act
    SingleFileWritingIterator actualSingleFileWritingIterator = new SingleFileWritingIterator(input, instanceProperties,
        tableProperties, new Configuration(), null, "foo.txt");

    // Assert
    verify(tableProperties).getSchema();
    assertFalse(actualSingleFileWritingIterator.hasNext());
  }
}
