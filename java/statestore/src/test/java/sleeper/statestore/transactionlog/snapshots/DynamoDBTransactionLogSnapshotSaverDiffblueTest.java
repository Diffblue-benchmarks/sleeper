package sleeper.statestore.transactionlog.snapshots;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.statestore.transactionlog.snapshots.DynamoDBTransactionLogSnapshotCreator.LatestSnapshotsMetadataLoader;
import sleeper.statestore.transactionlog.snapshots.DynamoDBTransactionLogSnapshotSaver.SnapshotMetadataSaver;

class DynamoDBTransactionLogSnapshotSaverDiffblueTest {
  /**
   * Test {@link DynamoDBTransactionLogSnapshotSaver#DynamoDBTransactionLogSnapshotSaver(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotSaver#DynamoDBTransactionLogSnapshotSaver(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotSaver(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration); given 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotSaver.<init>(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)"})
  void testNewDynamoDBTransactionLogSnapshotSaver_givenGet_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();

    // Act
    new DynamoDBTransactionLogSnapshotSaver(instanceProperties, tableProperties, dynamo, new Configuration());

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotSaver#DynamoDBTransactionLogSnapshotSaver(LatestSnapshotsMetadataLoader, SnapshotMetadataSaver, InstanceProperties, TableProperties, Configuration)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotSaver#DynamoDBTransactionLogSnapshotSaver(LatestSnapshotsMetadataLoader, SnapshotMetadataSaver, InstanceProperties, TableProperties, Configuration)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotSaver(LatestSnapshotsMetadataLoader, SnapshotMetadataSaver, InstanceProperties, TableProperties, Configuration); given 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotSaver.<init>(LatestSnapshotsMetadataLoader, SnapshotMetadataSaver, InstanceProperties, TableProperties, Configuration)"})
  void testNewDynamoDBTransactionLogSnapshotSaver_givenGet_thenCallsGet2() {
    // Arrange
    LatestSnapshotsMetadataLoader latestMetadataLoader = mock(LatestSnapshotsMetadataLoader.class);
    SnapshotMetadataSaver metadataSaver = mock(SnapshotMetadataSaver.class);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    new DynamoDBTransactionLogSnapshotSaver(latestMetadataLoader, metadataSaver, instanceProperties, tableProperties,
        new Configuration());

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotSaver#DynamoDBTransactionLogSnapshotSaver(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)}.
   * <ul>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotSaver#DynamoDBTransactionLogSnapshotSaver(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotSaver(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration); then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotSaver.<init>(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)"})
  void testNewDynamoDBTransactionLogSnapshotSaver_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    AmazonDynamoDBAsyncClient dynamo = new AmazonDynamoDBAsyncClient();

    // Act
    new DynamoDBTransactionLogSnapshotSaver(instanceProperties, tableProperties, dynamo, new Configuration());

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties, atLeast(1)).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotSaver#DynamoDBTransactionLogSnapshotSaver(LatestSnapshotsMetadataLoader, SnapshotMetadataSaver, InstanceProperties, TableProperties, Configuration)}.
   * <ul>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotSaver#DynamoDBTransactionLogSnapshotSaver(LatestSnapshotsMetadataLoader, SnapshotMetadataSaver, InstanceProperties, TableProperties, Configuration)}
   */
  @Test
  @DisplayName("Test new DynamoDBTransactionLogSnapshotSaver(LatestSnapshotsMetadataLoader, SnapshotMetadataSaver, InstanceProperties, TableProperties, Configuration); then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBTransactionLogSnapshotSaver.<init>(LatestSnapshotsMetadataLoader, SnapshotMetadataSaver, InstanceProperties, TableProperties, Configuration)"})
  void testNewDynamoDBTransactionLogSnapshotSaver_thenCallsGet2() {
    // Arrange
    LatestSnapshotsMetadataLoader latestMetadataLoader = mock(LatestSnapshotsMetadataLoader.class);
    SnapshotMetadataSaver metadataSaver = mock(SnapshotMetadataSaver.class);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    // Act
    new DynamoDBTransactionLogSnapshotSaver(latestMetadataLoader, metadataSaver, instanceProperties, tableProperties,
        new Configuration());

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link DynamoDBTransactionLogSnapshotSaver#getBasePath(InstanceProperties, TableProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return {@code s3a://null/null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogSnapshotSaver#getBasePath(InstanceProperties, TableProperties)}
   */
  @Test
  @DisplayName("Test getBasePath(InstanceProperties, TableProperties); when InstanceProperties(); then return 's3a://null/null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "java.lang.String DynamoDBTransactionLogSnapshotSaver.getBasePath(InstanceProperties, TableProperties)"})
  void testGetBasePath_whenInstanceProperties_thenReturnS3aNullNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertEquals("s3a://null/null", DynamoDBTransactionLogSnapshotSaver.getBasePath(instanceProperties,
        new TableProperties(new InstanceProperties())));
  }
}
