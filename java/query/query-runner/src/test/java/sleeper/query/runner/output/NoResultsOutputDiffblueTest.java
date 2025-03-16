package sleeper.query.runner.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.ConcatenatingIterator;
import sleeper.core.range.Region;
import sleeper.query.core.model.LeafPartitionQuery;
import sleeper.query.core.model.LeafPartitionQuery.Builder;
import sleeper.query.core.model.QueryOrLeafPartitionQuery;
import sleeper.query.core.model.QueryProcessingConfig;
import sleeper.query.core.output.ResultsOutputInfo;
import sleeper.query.core.output.ResultsOutputLocation;

class NoResultsOutputDiffblueTest {
  /**
   * Test new {@link NoResultsOutput} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NoResultsOutput}
   */
  @Test
  @DisplayName("Test new NoResultsOutput (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NoResultsOutput.<init>()"})
  void testNewNoResultsOutput() {
    // Arrange and Act
    NoResultsOutput actualNoResultsOutput = new NoResultsOutput();
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
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery query = new QueryOrLeafPartitionQuery(leafQuery);
    ResultsOutputInfo actualPublishResult = actualNoResultsOutput.publish(query,
        new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    List<ResultsOutputLocation> locations = actualPublishResult.getLocations();
    assertEquals(1, locations.size());
    ResultsOutputLocation getResult = locations.get(0);
    assertEquals("destination", getResult.getType());
    assertNull(actualPublishResult.getError());
    assertEquals(0L, actualPublishResult.getRecordCount());
    assertEquals(NoResultsOutput.NO_RESULTS_OUTPUT, getResult.getLocation());
  }

  /**
   * Test {@link NoResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}.
   * <p>
   * Method under test: {@link NoResultsOutput#publish(QueryOrLeafPartitionQuery, CloseableIterator)}
   */
  @Test
  @DisplayName("Test publish(QueryOrLeafPartitionQuery, CloseableIterator)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ResultsOutputInfo NoResultsOutput.publish(QueryOrLeafPartitionQuery, CloseableIterator)"})
  void testPublish() {
    // Arrange
    NoResultsOutput noResultsOutput = new NoResultsOutput();
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
    LeafPartitionQuery leafQuery = queryIdResult.regions(new ArrayList<>()).subQueryId("42").tableId("42").build();
    QueryOrLeafPartitionQuery query = new QueryOrLeafPartitionQuery(leafQuery);

    // Act
    ResultsOutputInfo actualPublishResult = noResultsOutput.publish(query,
        new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    List<ResultsOutputLocation> locations = actualPublishResult.getLocations();
    assertEquals(1, locations.size());
    ResultsOutputLocation getResult = locations.get(0);
    assertEquals("destination", getResult.getType());
    assertNull(actualPublishResult.getError());
    assertEquals(0L, actualPublishResult.getRecordCount());
    assertEquals(NoResultsOutput.NO_RESULTS_OUTPUT, getResult.getLocation());
  }
}
