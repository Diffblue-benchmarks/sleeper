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
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;

class SplitPartitionResultFactoryDiffblueTest {
  /**
   * Test {@link SplitPartitionResultFactory#SplitPartitionResultFactory(Schema, Supplier)}.
   * <p>
   * Method under test: {@link SplitPartitionResultFactory#SplitPartitionResultFactory(Schema, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionResultFactory(Schema, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionResultFactory.<init>(Schema, Supplier)"})
  void testNewSplitPartitionResultFactory() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    new SplitPartitionResultFactory(schema, mock(Supplier.class));

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link SplitPartitionResultFactory#SplitPartitionResultFactory(Schema, Supplier)}.
   * <p>
   * Method under test: {@link SplitPartitionResultFactory#SplitPartitionResultFactory(Schema, Supplier)}
   */
  @Test
  @DisplayName("Test new SplitPartitionResultFactory(Schema, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionResultFactory.<init>(Schema, Supplier)"})
  void testNewSplitPartitionResultFactory2() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    new SplitPartitionResultFactory(schema, mock(Supplier.class));

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
  }
}
