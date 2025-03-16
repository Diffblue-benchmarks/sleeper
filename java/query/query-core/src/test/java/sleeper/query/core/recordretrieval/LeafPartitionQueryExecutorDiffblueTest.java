package sleeper.query.core.recordretrieval;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.range.Region;
import sleeper.core.util.ObjectFactory;
import sleeper.query.core.model.LeafPartitionQuery;
import sleeper.query.core.model.LeafPartitionQuery.Builder;
import sleeper.query.core.model.QueryException;
import sleeper.query.core.model.QueryProcessingConfig;

class LeafPartitionQueryExecutorDiffblueTest {
  /**
   * Test {@link LeafPartitionQueryExecutor#getRecords(LeafPartitionQuery)}.
   * <ul>
   *   <li>Then throw {@link QueryException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LeafPartitionQueryExecutor#getRecords(LeafPartitionQuery)}
   */
  @Test
  @DisplayName("Test getRecords(LeafPartitionQuery); then throw QueryException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.iterator.CloseableIterator LeafPartitionQueryExecutor.getRecords(LeafPartitionQuery)"})
  void testGetRecords_thenThrowQueryException() throws QueryException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    LeafPartitionQueryExecutor leafPartitionQueryExecutor = new LeafPartitionQueryExecutor(objectFactory,
        new TableProperties(new InstanceProperties()), mock(LeafPartitionRecordRetriever.class));
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
    LeafPartitionQuery leafPartitionQuery = queryIdResult.regions(new ArrayList<>())
        .subQueryId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertThrows(QueryException.class, () -> leafPartitionQueryExecutor.getRecords(leafPartitionQuery));
  }
}
