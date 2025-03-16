package sleeper.splitter.core.split;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.statestore.StateStore;
import sleeper.splitter.core.split.FindPartitionSplitPoint.SketchesLoader;
import sleeper.splitter.core.split.SplitPartition.SendAsyncCommit;

class SplitPartitionDiffblueTest {
  /**
   * Test {@link SplitPartition#SplitPartition(StateStore, TableProperties, SketchesLoader, Supplier, SendAsyncCommit)}.
   * <ul>
   *   <li>Then calls {@link TableProperties#getSchema()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartition#SplitPartition(StateStore, TableProperties, SketchesLoader, Supplier, SendAsyncCommit)}
   */
  @Test
  @DisplayName("Test new SplitPartition(StateStore, TableProperties, SketchesLoader, Supplier, SendAsyncCommit); then calls getSchema()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SplitPartition.<init>(StateStore, TableProperties, SketchesLoader, Supplier, SendAsyncCommit)"})
  void testNewSplitPartition_thenCallsGetSchema() {
    // Arrange
    StateStore stateStore = mock(StateStore.class);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getSchema()).thenReturn(buildResult);

    // Act
    new SplitPartition(stateStore, tableProperties, mock(SketchesLoader.class), mock(Supplier.class),
        mock(SendAsyncCommit.class));

    // Assert
    verify(tableProperties).getSchema();
  }
}
