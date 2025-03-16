package sleeper.query.core.recordretrieval;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.partition.Partition;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.range.Region;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.util.ObjectFactory;
import sleeper.query.core.model.LeafPartitionQuery;
import sleeper.query.core.model.LeafPartitionQuery.Builder;
import sleeper.query.core.model.QueryException;
import sleeper.query.core.model.QueryProcessingConfig;

class QueryExecutorDiffblueTest {
  /**
   * Test {@link QueryExecutor#init(Instant)} with {@code now}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryExecutor#init(Instant)}
   */
  @Test
  @DisplayName("Test init(Instant) with 'now'; then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryExecutor.init(Instant)"})
  void testInitWithNow_thenThrowStateStoreException() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenThrow(new StateStoreException("An error occurred"));
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    LeafPartitionRecordRetriever recordRetriever = mock(LeafPartitionRecordRetriever.class);
    QueryExecutor queryExecutor = new QueryExecutor(objectFactory, stateStore, tableProperties, recordRetriever,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(StateStoreException.class,
        () -> queryExecutor.init(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    verify(stateStore).getAllPartitions();
  }

  /**
   * Test {@link QueryExecutor#initIfNeeded(Instant)}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryExecutor#initIfNeeded(Instant)}
   */
  @Test
  @DisplayName("Test initIfNeeded(Instant); then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryExecutor.initIfNeeded(Instant)"})
  void testInitIfNeeded_thenThrowStateStoreException() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenThrow(new StateStoreException("An error occurred"));
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    LeafPartitionRecordRetriever recordRetriever = mock(LeafPartitionRecordRetriever.class);
    QueryExecutor queryExecutor = new QueryExecutor(objectFactory, stateStore, tableProperties, recordRetriever,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(StateStoreException.class,
        () -> queryExecutor.initIfNeeded(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    verify(stateStore).getAllPartitions();
  }

  /**
   * Test {@link QueryExecutor#execute(LeafPartitionQuery)} with {@code LeafPartitionQuery}.
   * <p>
   * Method under test: {@link QueryExecutor#execute(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test execute(LeafPartitionQuery) with 'LeafPartitionQuery'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.core.iterator.CloseableIterator QueryExecutor.execute(LeafPartitionQuery)"})
  void testExecuteWithLeafPartitionQuery() throws QueryException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    QueryExecutor queryExecutor = new QueryExecutor(objectFactory, new TableProperties(new InstanceProperties()),
        mock(StateStore.class), mock(LeafPartitionRecordRetriever.class));
    Builder builderResult = LeafPartitionQuery.builder();
    Builder leafPartitionIdResult = builderResult.files(new ArrayList<>()).leafPartitionId("42");
    Builder partitionRegionResult = leafPartitionIdResult.partitionRegion(new Region(new ArrayList<>()));
    QueryProcessingConfig.Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    QueryProcessingConfig.Builder requestedValueFieldsResult = queryTimeIteratorConfigResult
        .requestedValueFields(new ArrayList<>());
    QueryProcessingConfig.Builder resultsPublisherConfigResult = requestedValueFieldsResult
        .resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig processingConfig = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryIdResult = partitionRegionResult.processingConfig(processingConfig).queryId("42");
    LeafPartitionQuery query = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();

    // Act and Assert
    assertThrows(RuntimeException.class, () -> queryExecutor.execute(query));
  }

  /**
   * Test {@link QueryExecutor#execute(LeafPartitionQuery)} with {@code LeafPartitionQuery}.
   * <ul>
   *   <li>Then calls {@link LeafPartitionQuery#getQueryTimeIteratorConfig()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryExecutor#execute(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test execute(LeafPartitionQuery) with 'LeafPartitionQuery'; then calls getQueryTimeIteratorConfig()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.core.iterator.CloseableIterator QueryExecutor.execute(LeafPartitionQuery)"})
  void testExecuteWithLeafPartitionQuery_thenCallsGetQueryTimeIteratorConfig() throws QueryException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    QueryExecutor queryExecutor = new QueryExecutor(objectFactory, new TableProperties(new InstanceProperties()),
        mock(StateStore.class), mock(LeafPartitionRecordRetriever.class));
    LeafPartitionQuery query = mock(LeafPartitionQuery.class);
    when(query.getQueryTimeIteratorClassName()).thenReturn("Query Time Iterator Class Name");
    when(query.getQueryTimeIteratorConfig()).thenReturn("Query Time Iterator Config");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> queryExecutor.execute(query));
    verify(query).getQueryTimeIteratorClassName();
    verify(query).getQueryTimeIteratorConfig();
  }

  /**
   * Test {@link QueryExecutor#execute(LeafPartitionQuery)} with {@code LeafPartitionQuery}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryExecutor#execute(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test execute(LeafPartitionQuery) with 'LeafPartitionQuery'; then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.core.iterator.CloseableIterator QueryExecutor.execute(LeafPartitionQuery)"})
  void testExecuteWithLeafPartitionQuery_thenThrowStateStoreException() throws QueryException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    QueryExecutor queryExecutor = new QueryExecutor(objectFactory, new TableProperties(new InstanceProperties()),
        mock(StateStore.class), mock(LeafPartitionRecordRetriever.class));
    LeafPartitionQuery query = mock(LeafPartitionQuery.class);
    when(query.getQueryTimeIteratorClassName()).thenThrow(new StateStoreException("An error occurred"));

    // Act and Assert
    assertThrows(StateStoreException.class, () -> queryExecutor.execute(query));
    verify(query).getQueryTimeIteratorClassName();
  }

  /**
   * Test {@link QueryExecutor#getFiles(Partition)}.
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryExecutor#getFiles(Partition)}
   */
  @Test
  @DisplayName("Test getFiles(Partition); given RuntimeException(String) with 'foo'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List QueryExecutor.getFiles(Partition)"})
  void testGetFiles_givenRuntimeExceptionWithFoo_thenThrowRuntimeException() {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    QueryExecutor queryExecutor = new QueryExecutor(objectFactory, new TableProperties(new InstanceProperties()),
        mock(StateStore.class), mock(LeafPartitionRecordRetriever.class));
    Partition partition = mock(Partition.class);
    when(partition.getId()).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> queryExecutor.getFiles(partition));
    verify(partition).getId();
  }
}
