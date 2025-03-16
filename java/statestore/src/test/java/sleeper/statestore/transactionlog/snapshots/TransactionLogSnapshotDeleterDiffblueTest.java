package sleeper.statestore.transactionlog.snapshots;

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
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.statestore.transactionlog.snapshots.TransactionLogSnapshotDeleter.SnapshotFileDeleter;

class TransactionLogSnapshotDeleterDiffblueTest {
  /**
   * Test {@link TransactionLogSnapshotDeleter#TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogSnapshotDeleter#TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)}
   */
  @Test
  @DisplayName("Test new TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration); given one; then calls getInt(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void TransactionLogSnapshotDeleter.<init>(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)"})
  void testNewTransactionLogSnapshotDeleter_givenOne_thenCallsGetInt() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();

    // Act
    new TransactionLogSnapshotDeleter(instanceProperties, tableProperties, dynamoDB, new Configuration());

    // Assert
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link TransactionLogSnapshotDeleter#TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, SnapshotFileDeleter)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogSnapshotDeleter#TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, SnapshotFileDeleter)}
   */
  @Test
  @DisplayName("Test new TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, SnapshotFileDeleter); given one; then calls getInt(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void TransactionLogSnapshotDeleter.<init>(InstanceProperties, TableProperties, AmazonDynamoDB, SnapshotFileDeleter)"})
  void testNewTransactionLogSnapshotDeleter_givenOne_thenCallsGetInt2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    // Act
    new TransactionLogSnapshotDeleter(instanceProperties, tableProperties, new AmazonDynamoDBAsyncClient(),
        mock(SnapshotFileDeleter.class));

    // Assert
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link TransactionLogSnapshotDeleter#TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)}.
   * <ul>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogSnapshotDeleter#TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)}
   */
  @Test
  @DisplayName("Test new TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration); then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void TransactionLogSnapshotDeleter.<init>(InstanceProperties, TableProperties, AmazonDynamoDB, Configuration)"})
  void testNewTransactionLogSnapshotDeleter_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();

    // Act
    new TransactionLogSnapshotDeleter(instanceProperties, tableProperties, dynamoDB, new Configuration());

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link TransactionLogSnapshotDeleter#TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, SnapshotFileDeleter)}.
   * <ul>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogSnapshotDeleter#TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, SnapshotFileDeleter)}
   */
  @Test
  @DisplayName("Test new TransactionLogSnapshotDeleter(InstanceProperties, TableProperties, AmazonDynamoDB, SnapshotFileDeleter); then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void TransactionLogSnapshotDeleter.<init>(InstanceProperties, TableProperties, AmazonDynamoDB, SnapshotFileDeleter)"})
  void testNewTransactionLogSnapshotDeleter_thenCallsGet2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    new TransactionLogSnapshotDeleter(instanceProperties, tableProperties, new AmazonDynamoDBAsyncClient(),
        mock(SnapshotFileDeleter.class));

    // Assert
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }
}
