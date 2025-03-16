package sleeper.statestore.transactionlog.snapshots;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Optional;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.properties.testutils.DummyInstanceProperty;
import sleeper.core.statestore.transactionlog.log.TransactionLogRange;
import sleeper.core.statestore.transactionlog.snapshot.TransactionLogSnapshot;
import sleeper.statestore.StateStoreArrowFileStore;

class DynamoDBTransactionLogSnapshotLoaderDiffblueTest {
  /**
   * Test {@link DynamoDBTransactionLogSnapshotLoader#loadLatestSnapshotInRange(TransactionLogRange)}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotLoader#loadLatestSnapshotInRange(TransactionLogRange)}
   */
  @Test
  @DisplayName("Test loadLatestSnapshotInRange(TransactionLogRange)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DynamoDBTransactionLogSnapshotLoader.loadLatestSnapshotInRange(TransactionLogRange)"})
  void testLoadLatestSnapshotInRange() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty("#TableId = :table_id");
    instanceProperties.addToListIfMissing(dummyInstanceProperty, new ArrayList<>());
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    QueryResult queryResult = new QueryResult();
    queryResult.setCount(0);
    AmazonDynamoDBAsyncClient dynamo = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamo.query(Mockito.<QueryRequest>any())).thenReturn(queryResult);
    DynamoDBTransactionLogSnapshotMetadataStore metadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        instanceProperties, tableProperties, dynamo);

    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());
    DynamoDBTransactionLogSnapshotLoader dynamoDBTransactionLogSnapshotLoader = new DynamoDBTransactionLogSnapshotLoader(
        metadataStore, new StateStoreArrowFileStore(tableProperties2, new Configuration()), SnapshotType.FILES);

    // Act
    Optional<TransactionLogSnapshot> actualLoadLatestSnapshotInRangeResult = dynamoDBTransactionLogSnapshotLoader
        .loadLatestSnapshotInRange(TransactionLogRange.fromMinimum(1L));

    // Assert
    verify(dynamo).query(isA(QueryRequest.class));
    verify(tableProperties).get(isA(TableProperty.class));
    assertFalse(actualLoadLatestSnapshotInRangeResult.isPresent());
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotLoader#loadLatestSnapshotInRange(TransactionLogRange)}.
   * <ul>
   *   <li>Given {@link QueryResult} (default constructor) Count is zero.</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotLoader#loadLatestSnapshotInRange(TransactionLogRange)}
   */
  @Test
  @DisplayName("Test loadLatestSnapshotInRange(TransactionLogRange); given QueryResult (default constructor) Count is zero; then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DynamoDBTransactionLogSnapshotLoader.loadLatestSnapshotInRange(TransactionLogRange)"})
  void testLoadLatestSnapshotInRange_givenQueryResultCountIsZero_thenReturnNotPresent() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    QueryResult queryResult = new QueryResult();
    queryResult.setCount(0);
    AmazonDynamoDBAsyncClient dynamo = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamo.query(Mockito.<QueryRequest>any())).thenReturn(queryResult);
    DynamoDBTransactionLogSnapshotMetadataStore metadataStore = new DynamoDBTransactionLogSnapshotMetadataStore(
        new InstanceProperties(), tableProperties, dynamo);

    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());
    DynamoDBTransactionLogSnapshotLoader dynamoDBTransactionLogSnapshotLoader = new DynamoDBTransactionLogSnapshotLoader(
        metadataStore, new StateStoreArrowFileStore(tableProperties2, new Configuration()), SnapshotType.FILES);

    // Act
    Optional<TransactionLogSnapshot> actualLoadLatestSnapshotInRangeResult = dynamoDBTransactionLogSnapshotLoader
        .loadLatestSnapshotInRange(TransactionLogRange.fromMinimum(1L));

    // Assert
    verify(dynamo).query(isA(QueryRequest.class));
    verify(tableProperties).get(isA(TableProperty.class));
    assertFalse(actualLoadLatestSnapshotInRangeResult.isPresent());
  }
}
