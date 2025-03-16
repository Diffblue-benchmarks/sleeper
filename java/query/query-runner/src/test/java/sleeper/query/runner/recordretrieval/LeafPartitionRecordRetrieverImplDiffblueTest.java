package sleeper.query.runner.recordretrieval;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.DefaultEventLoop;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.filter2.predicate.FilterPredicate.Visitor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.WrappedIterator;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.query.core.model.LeafPartitionQuery;
import sleeper.query.core.recordretrieval.RecordRetrievalException;

class LeafPartitionRecordRetrieverImplDiffblueTest {
  /**
   * Test {@link LeafPartitionRecordRetrieverImpl#getRecords(List, Schema, FilterPredicate)} with {@code files}, {@code dataReadSchema}, {@code filterPredicate}.
   * <p>
   * Method under test: {@link LeafPartitionRecordRetrieverImpl#getRecords(List, Schema, FilterPredicate)}
   */
  @Test
  @DisplayName("Test getRecords(List, Schema, FilterPredicate) with 'files', 'dataReadSchema', 'filterPredicate'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator LeafPartitionRecordRetrieverImpl.getRecords(List, Schema, FilterPredicate)"})
  void testGetRecordsWithFilesDataReadSchemaFilterPredicate() throws RecordRetrievalException {
    // Arrange
    DefaultEventLoop executorService = new DefaultEventLoop();
    Configuration conf = new Configuration();
    LeafPartitionRecordRetrieverImpl leafPartitionRecordRetrieverImpl = new LeafPartitionRecordRetrieverImpl(
        executorService, conf, new TableProperties(new InstanceProperties()));

    ArrayList<String> files = new ArrayList<>();
    files.add("Files");
    Schema dataReadSchema = mock(Schema.class);
    FilterPredicate filterPredicate = mock(FilterPredicate.class);
    when(filterPredicate.accept(Mockito.<Visitor<FilterPredicate>>any())).thenReturn(mock(FilterPredicate.class));
    FilterPredicate filterPredicate2 = mock(FilterPredicate.class);
    when(filterPredicate2.accept(Mockito.<Visitor<FilterPredicate>>any())).thenReturn(filterPredicate);

    // Act and Assert
    assertThrows(RecordRetrievalException.class,
        () -> leafPartitionRecordRetrieverImpl.getRecords(files, dataReadSchema, filterPredicate2));
    verify(filterPredicate2).accept(isA(Visitor.class));
    verify(filterPredicate).accept(isA(Visitor.class));
  }

  /**
   * Test {@link LeafPartitionRecordRetrieverImpl#getRecords(List, Schema, FilterPredicate)} with {@code files}, {@code dataReadSchema}, {@code filterPredicate}.
   * <p>
   * Method under test: {@link LeafPartitionRecordRetrieverImpl#getRecords(List, Schema, FilterPredicate)}
   */
  @Test
  @DisplayName("Test getRecords(List, Schema, FilterPredicate) with 'files', 'dataReadSchema', 'filterPredicate'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator LeafPartitionRecordRetrieverImpl.getRecords(List, Schema, FilterPredicate)"})
  void testGetRecordsWithFilesDataReadSchemaFilterPredicate2() throws RecordRetrievalException {
    // Arrange
    DefaultEventLoop executorService = new DefaultEventLoop(mock(ThreadFactory.class));
    Configuration conf = new Configuration();
    LeafPartitionRecordRetrieverImpl leafPartitionRecordRetrieverImpl = new LeafPartitionRecordRetrieverImpl(
        executorService, conf, new TableProperties(new InstanceProperties()));

    ArrayList<String> files = new ArrayList<>();
    files.add("Files");
    Schema dataReadSchema = mock(Schema.class);
    FilterPredicate filterPredicate = mock(FilterPredicate.class);
    when(filterPredicate.accept(Mockito.<Visitor<FilterPredicate>>any())).thenReturn(mock(FilterPredicate.class));
    FilterPredicate filterPredicate2 = mock(FilterPredicate.class);
    when(filterPredicate2.accept(Mockito.<Visitor<FilterPredicate>>any())).thenReturn(filterPredicate);

    // Act and Assert
    assertThrows(RecordRetrievalException.class,
        () -> leafPartitionRecordRetrieverImpl.getRecords(files, dataReadSchema, filterPredicate2));
    verify(filterPredicate2).accept(isA(Visitor.class));
    verify(filterPredicate).accept(isA(Visitor.class));
  }

  /**
   * Test {@link LeafPartitionRecordRetrieverImpl#getRecords(List, Schema, FilterPredicate)} with {@code files}, {@code dataReadSchema}, {@code filterPredicate}.
   * <p>
   * Method under test: {@link LeafPartitionRecordRetrieverImpl#getRecords(List, Schema, FilterPredicate)}
   */
  @Test
  @DisplayName("Test getRecords(List, Schema, FilterPredicate) with 'files', 'dataReadSchema', 'filterPredicate'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator LeafPartitionRecordRetrieverImpl.getRecords(List, Schema, FilterPredicate)"})
  void testGetRecordsWithFilesDataReadSchemaFilterPredicate3() throws RecordRetrievalException {
    // Arrange
    Configuration conf = new Configuration();
    conf.addResource("42");
    DefaultEventLoop executorService = new DefaultEventLoop();
    LeafPartitionRecordRetrieverImpl leafPartitionRecordRetrieverImpl = new LeafPartitionRecordRetrieverImpl(
        executorService, conf, new TableProperties(new InstanceProperties()));

    ArrayList<String> files = new ArrayList<>();
    files.add("Files");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema dataReadSchema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    FilterPredicate filterPredicate = mock(FilterPredicate.class);
    when(filterPredicate.accept(Mockito.<Visitor<FilterPredicate>>any())).thenReturn(mock(FilterPredicate.class));
    FilterPredicate filterPredicate2 = mock(FilterPredicate.class);
    when(filterPredicate2.accept(Mockito.<Visitor<FilterPredicate>>any())).thenReturn(filterPredicate);

    // Act and Assert
    assertThrows(RecordRetrievalException.class,
        () -> leafPartitionRecordRetrieverImpl.getRecords(files, dataReadSchema, filterPredicate2));
    verify(filterPredicate2).accept(isA(Visitor.class));
    verify(filterPredicate).accept(isA(Visitor.class));
  }

  /**
   * Test {@link LeafPartitionRecordRetrieverImpl#getRecords(List, Schema, FilterPredicate)} with {@code files}, {@code dataReadSchema}, {@code filterPredicate}.
   * <ul>
   *   <li>Then return {@link WrappedIterator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionRecordRetrieverImpl#getRecords(List, Schema, FilterPredicate)}
   */
  @Test
  @DisplayName("Test getRecords(List, Schema, FilterPredicate) with 'files', 'dataReadSchema', 'filterPredicate'; then return WrappedIterator")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator LeafPartitionRecordRetrieverImpl.getRecords(List, Schema, FilterPredicate)"})
  void testGetRecordsWithFilesDataReadSchemaFilterPredicate_thenReturnWrappedIterator()
      throws RecordRetrievalException {
    // Arrange
    DefaultEventLoop executorService = new DefaultEventLoop();
    Configuration conf = new Configuration();
    LeafPartitionRecordRetrieverImpl leafPartitionRecordRetrieverImpl = new LeafPartitionRecordRetrieverImpl(
        executorService, conf, new TableProperties(new InstanceProperties()));

    // Act
    CloseableIterator<Record> actualRecords = leafPartitionRecordRetrieverImpl.getRecords(new ArrayList<>(),
        mock(Schema.class), mock(FilterPredicate.class));

    // Assert
    assertTrue(actualRecords instanceof WrappedIterator);
    assertFalse(actualRecords.hasNext());
  }

  /**
   * Test {@link LeafPartitionRecordRetrieverImpl#getRecords(LeafPartitionQuery, Schema)} with {@code leafPartitionQuery}, {@code dataReadSchema}.
   * <ul>
   *   <li>Then return {@link WrappedIterator}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionRecordRetrieverImpl#getRecords(LeafPartitionQuery, Schema)}
   */
  @Test
  @DisplayName("Test getRecords(LeafPartitionQuery, Schema) with 'leafPartitionQuery', 'dataReadSchema'; then return WrappedIterator")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator LeafPartitionRecordRetrieverImpl.getRecords(LeafPartitionQuery, Schema)"})
  void testGetRecordsWithLeafPartitionQueryDataReadSchema_thenReturnWrappedIterator() throws RecordRetrievalException {
    // Arrange
    DefaultEventLoop executorService = new DefaultEventLoop();
    Configuration conf = new Configuration();
    LeafPartitionRecordRetrieverImpl leafPartitionRecordRetrieverImpl = new LeafPartitionRecordRetrieverImpl(
        executorService, conf, new TableProperties(new InstanceProperties()));
    LeafPartitionQuery leafPartitionQuery = mock(LeafPartitionQuery.class);
    when(leafPartitionQuery.getFiles()).thenReturn(new ArrayList<>());

    // Act
    CloseableIterator<Record> actualRecords = leafPartitionRecordRetrieverImpl.getRecords(leafPartitionQuery,
        mock(Schema.class));

    // Assert
    verify(leafPartitionQuery).getFiles();
    assertTrue(actualRecords instanceof WrappedIterator);
    assertFalse(actualRecords.hasNext());
  }
}
