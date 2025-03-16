package sleeper.statestore.transactionlog.snapshots;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.properties.testutils.DummyInstanceProperty;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStore;
import sleeper.core.statestore.transactionlog.log.DuplicateTransactionNumberException;
import sleeper.core.statestore.transactionlog.log.TransactionBodyStore;
import sleeper.core.statestore.transactionlog.log.TransactionLogEntry;
import sleeper.core.statestore.transactionlog.log.TransactionLogStore;
import sleeper.core.statestore.transactionlog.transaction.TransactionSerDeProvider;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.table.TableStatus;
import sleeper.statestore.transactionlog.S3TransactionBodyStore;
import sleeper.statestore.transactionlog.snapshots.DynamoDBTransactionLogSnapshotCreator.LatestSnapshotsMetadataLoader;
import sleeper.statestore.transactionlog.snapshots.DynamoDBTransactionLogSnapshotSaver.SnapshotMetadataSaver;

class DynamoDBTransactionLogSnapshotCreatorDiffblueTest {
  /**
   * Test {@link DynamoDBTransactionLogSnapshotCreator#from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotCreator#from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration)}
   */
  @Test
  @DisplayName("Test from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration); given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBTransactionLogSnapshotCreator DynamoDBTransactionLogSnapshotCreator.from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration)"})
  void testFrom_given42() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getStatus()).thenThrow(new RuntimeException("file"));
    AmazonS3Client s3Client = new AmazonS3Client();

    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    dynamoDBClient.putItemAsync("42", new HashMap<>(), mock(AsyncHandler.class));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> DynamoDBTransactionLogSnapshotCreator.from(instanceProperties,
        tableProperties, s3Client, dynamoDBClient, new Configuration()));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties).getStatus();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotCreator#from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotCreator#from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration)}
   */
  @Test
  @DisplayName("Test from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor); then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBTransactionLogSnapshotCreator DynamoDBTransactionLogSnapshotCreator.from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration)"})
  void testFrom_givenArrayListAddFieldWithNameAndTypeIsByteArrayType_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(buildResult);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    // Act
    DynamoDBTransactionLogSnapshotCreator.from(instanceProperties, tableProperties, s3Client, dynamoDBClient,
        new Configuration());

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties, atLeast(1)).get(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).getSchema();
    verify(tableProperties, atLeast(1)).getStatus();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotCreator#from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotCreator#from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration)}
   */
  @Test
  @DisplayName("Test from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration); given PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBTransactionLogSnapshotCreator DynamoDBTransactionLogSnapshotCreator.from(InstanceProperties, TableProperties, AmazonS3, AmazonDynamoDB, Configuration)"})
  void testFrom_givenPutItemRequest() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getStatus()).thenThrow(new RuntimeException("file"));
    AmazonS3Client s3Client = new AmazonS3Client();

    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    dynamoDBClient.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> DynamoDBTransactionLogSnapshotCreator.from(instanceProperties,
        tableProperties, s3Client, dynamoDBClient, new Configuration()));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties).getStatus();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotCreator#DynamoDBTransactionLogSnapshotCreator(InstanceProperties, TableProperties, TransactionLogStore, TransactionLogStore, TransactionBodyStore, Configuration, LatestSnapshotsMetadataLoader, SnapshotMetadataSaver)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotCreator#DynamoDBTransactionLogSnapshotCreator(InstanceProperties, TableProperties, TransactionLogStore, TransactionLogStore, TransactionBodyStore, Configuration, LatestSnapshotsMetadataLoader, SnapshotMetadataSaver)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotCreator(InstanceProperties, TableProperties, TransactionLogStore, TransactionLogStore, TransactionBodyStore, Configuration, LatestSnapshotsMetadataLoader, SnapshotMetadataSaver); given 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotCreator.<init>(InstanceProperties, TableProperties, TransactionLogStore, TransactionLogStore, TransactionBodyStore, Configuration, LatestSnapshotsMetadataLoader, SnapshotMetadataSaver)"})
  void testNewDynamoDBTransactionLogSnapshotCreator_givenGet_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InMemoryTransactionLogStore filesLogStore = new InMemoryTransactionLogStore();
    InMemoryTransactionLogStore partitionsLogStore = new InMemoryTransactionLogStore();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    S3TransactionBodyStore transactionBodyStore = new S3TransactionBodyStore(instanceProperties2, new AmazonS3Client(),
        mock(TransactionSerDeProvider.class));

    // Act
    new DynamoDBTransactionLogSnapshotCreator(instanceProperties, tableProperties, filesLogStore, partitionsLogStore,
        transactionBodyStore, new Configuration(), mock(LatestSnapshotsMetadataLoader.class),
        mock(SnapshotMetadataSaver.class));

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotCreator#DynamoDBTransactionLogSnapshotCreator(InstanceProperties, TableProperties, TransactionLogStore, TransactionLogStore, TransactionBodyStore, Configuration, LatestSnapshotsMetadataLoader, SnapshotMetadataSaver)}.
   * <ul>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotCreator#DynamoDBTransactionLogSnapshotCreator(InstanceProperties, TableProperties, TransactionLogStore, TransactionLogStore, TransactionBodyStore, Configuration, LatestSnapshotsMetadataLoader, SnapshotMetadataSaver)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotCreator(InstanceProperties, TableProperties, TransactionLogStore, TransactionLogStore, TransactionBodyStore, Configuration, LatestSnapshotsMetadataLoader, SnapshotMetadataSaver); then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotCreator.<init>(InstanceProperties, TableProperties, TransactionLogStore, TransactionLogStore, TransactionBodyStore, Configuration, LatestSnapshotsMetadataLoader, SnapshotMetadataSaver)"})
  void testNewDynamoDBTransactionLogSnapshotCreator_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    InMemoryTransactionLogStore filesLogStore = new InMemoryTransactionLogStore();
    InMemoryTransactionLogStore partitionsLogStore = new InMemoryTransactionLogStore();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    S3TransactionBodyStore transactionBodyStore = new S3TransactionBodyStore(instanceProperties2, new AmazonS3Client(),
        mock(TransactionSerDeProvider.class));

    // Act
    new DynamoDBTransactionLogSnapshotCreator(instanceProperties, tableProperties, filesLogStore, partitionsLogStore,
        transactionBodyStore, new Configuration(), mock(LatestSnapshotsMetadataLoader.class),
        mock(SnapshotMetadataSaver.class));

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getStatus();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotCreator#createSnapshot()}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotCreator#createSnapshot()}
   */
  @Test
  @DisplayName("Test createSnapshot()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotCreator.createSnapshot()"})
  void testCreateSnapshot() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty("Property Name");
    instanceProperties.addToListIfMissing(dummyInstanceProperty, new ArrayList<>());
    LatestSnapshotsMetadataLoader latestMetadataLoader = mock(LatestSnapshotsMetadataLoader.class);
    when(latestMetadataLoader.load()).thenReturn(LatestSnapshots.empty());
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InMemoryTransactionLogStore filesLogStore = new InMemoryTransactionLogStore();
    InMemoryTransactionLogStore partitionsLogStore = new InMemoryTransactionLogStore();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    S3TransactionBodyStore transactionBodyStore = new S3TransactionBodyStore(instanceProperties2, new AmazonS3Client(),
        mock(TransactionSerDeProvider.class));

    // Act
    (new DynamoDBTransactionLogSnapshotCreator(instanceProperties, tableProperties, filesLogStore, partitionsLogStore,
        transactionBodyStore, new Configuration(), latestMetadataLoader, mock(SnapshotMetadataSaver.class)))
        .createSnapshot();

    // Assert
    verify(latestMetadataLoader).load();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotCreator#createSnapshot()}.
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotCreator#createSnapshot()}
   */
  @Test
  @DisplayName("Test createSnapshot()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotCreator.createSnapshot()"})
  void testCreateSnapshot2() throws DuplicateTransactionNumberException {
    // Arrange
    InMemoryTransactionLogStore partitionsLogStore = new InMemoryTransactionLogStore();
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    partitionsLogStore
        .addTransaction(new TransactionLogEntry(1L, updateTime, new AddFilesTransaction(new ArrayList<>())));
    LatestSnapshotsMetadataLoader latestMetadataLoader = mock(LatestSnapshotsMetadataLoader.class);
    when(latestMetadataLoader.load()).thenReturn(LatestSnapshots.empty());
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InMemoryTransactionLogStore filesLogStore = new InMemoryTransactionLogStore();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    S3TransactionBodyStore transactionBodyStore = new S3TransactionBodyStore(instanceProperties2, new AmazonS3Client(),
        mock(TransactionSerDeProvider.class));

    // Act
    (new DynamoDBTransactionLogSnapshotCreator(instanceProperties, tableProperties, filesLogStore, partitionsLogStore,
        transactionBodyStore, new Configuration(), latestMetadataLoader, mock(SnapshotMetadataSaver.class)))
        .createSnapshot();

    // Assert
    verify(latestMetadataLoader).load();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotCreator#createSnapshot()}.
   * <ul>
   *   <li>Then calls {@link LatestSnapshotsMetadataLoader#load()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotCreator#createSnapshot()}
   */
  @Test
  @DisplayName("Test createSnapshot(); then calls load()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotCreator.createSnapshot()"})
  void testCreateSnapshot_thenCallsLoad() {
    // Arrange
    LatestSnapshotsMetadataLoader latestMetadataLoader = mock(LatestSnapshotsMetadataLoader.class);
    when(latestMetadataLoader.load()).thenReturn(LatestSnapshots.empty());
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InMemoryTransactionLogStore filesLogStore = new InMemoryTransactionLogStore();
    InMemoryTransactionLogStore partitionsLogStore = new InMemoryTransactionLogStore();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    S3TransactionBodyStore transactionBodyStore = new S3TransactionBodyStore(instanceProperties2, new AmazonS3Client(),
        mock(TransactionSerDeProvider.class));

    // Act
    (new DynamoDBTransactionLogSnapshotCreator(instanceProperties, tableProperties, filesLogStore, partitionsLogStore,
        transactionBodyStore, new Configuration(), latestMetadataLoader, mock(SnapshotMetadataSaver.class)))
        .createSnapshot();

    // Assert
    verify(latestMetadataLoader).load();
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotCreator#createSnapshot()}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotCreator#createSnapshot()}
   */
  @Test
  @DisplayName("Test createSnapshot(); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTransactionLogSnapshotCreator.createSnapshot()"})
  void testCreateSnapshot_thenThrowRuntimeException() {
    // Arrange
    LatestSnapshotsMetadataLoader latestMetadataLoader = mock(LatestSnapshotsMetadataLoader.class);
    when(latestMetadataLoader.load()).thenThrow(new RuntimeException("Creating snapshot for table {}"));
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    InMemoryTransactionLogStore filesLogStore = new InMemoryTransactionLogStore();
    InMemoryTransactionLogStore partitionsLogStore = new InMemoryTransactionLogStore();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    S3TransactionBodyStore transactionBodyStore = new S3TransactionBodyStore(instanceProperties2, new AmazonS3Client(),
        mock(TransactionSerDeProvider.class));

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> (new DynamoDBTransactionLogSnapshotCreator(instanceProperties, tableProperties, filesLogStore,
            partitionsLogStore, transactionBodyStore, new Configuration(), latestMetadataLoader,
            mock(SnapshotMetadataSaver.class))).createSnapshot());
    verify(latestMetadataLoader).load();
  }
}
