package sleeper.statestore;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
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
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore.Builder;

class StateStoreFactoryDiffblueTest {
  /**
   * Test {@link StateStoreFactory#forCommitterProcess(boolean, TableProperties, Builder)} with {@code committerProcess}, {@code tableProperties}, {@code builder}.
   * <p>
   * Method under test: {@link StateStoreFactory#forCommitterProcess(boolean, TableProperties, TransactionLogStateStore.Builder)}
   */
  @Test
  @DisplayName("Test forCommitterProcess(boolean, TableProperties, Builder) with 'committerProcess', 'tableProperties', 'builder'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "TransactionLogStateStore.Builder StateStoreFactory.forCommitterProcess(boolean, TableProperties, TransactionLogStateStore.Builder)"})
  void testForCommitterProcessWithCommitterProcessTablePropertiesBuilder() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);

    // Act
    StateStoreFactory.forCommitterProcess(true, tableProperties, TransactionLogStateStore.builder());

    // Assert
    verify(tableProperties).getBoolean(isA(TableProperty.class));
  }

  /**
   * Test {@link StateStoreFactory#forCommitterProcess(boolean, TableProperties, Builder)} with {@code committerProcess}, {@code tableProperties}, {@code builder}.
   * <p>
   * Method under test: {@link StateStoreFactory#forCommitterProcess(boolean, TableProperties, TransactionLogStateStore.Builder)}
   */
  @Test
  @DisplayName("Test forCommitterProcess(boolean, TableProperties, Builder) with 'committerProcess', 'tableProperties', 'builder'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "TransactionLogStateStore.Builder StateStoreFactory.forCommitterProcess(boolean, TableProperties, TransactionLogStateStore.Builder)"})
  void testForCommitterProcessWithCommitterProcessTablePropertiesBuilder2() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    Builder builder = mock(Builder.class);
    when(builder.updateLogBeforeAddTransaction(anyBoolean())).thenReturn(TransactionLogStateStore.builder());

    // Act
    StateStoreFactory.forCommitterProcess(true, tableProperties, builder);

    // Assert
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(builder).updateLogBeforeAddTransaction(eq(true));
  }

  /**
   * Test {@link StateStoreFactory#forCommitterProcess(boolean, TableProperties, Builder)} with {@code committerProcess}, {@code tableProperties}, {@code builder}.
   * <ul>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFactory#forCommitterProcess(boolean, TableProperties, TransactionLogStateStore.Builder)}
   */
  @Test
  @DisplayName("Test forCommitterProcess(boolean, TableProperties, Builder) with 'committerProcess', 'tableProperties', 'builder'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "TransactionLogStateStore.Builder StateStoreFactory.forCommitterProcess(boolean, TableProperties, TransactionLogStateStore.Builder)"})
  void testForCommitterProcessWithCommitterProcessTablePropertiesBuilder_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = new TableProperties(instanceProperties);

    // Act
    StateStoreFactory.forCommitterProcess(true, tableProperties, TransactionLogStateStore.builder());

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link StateStoreFactory#forCommitterProcess(InstanceProperties, AmazonS3, AmazonDynamoDB, Configuration)} with {@code instanceProperties}, {@code s3}, {@code dynamoDB}, {@code configuration}.
   * <p>
   * Method under test: {@link StateStoreFactory#forCommitterProcess(InstanceProperties, AmazonS3, AmazonDynamoDB, Configuration)}
   */
  @Test
  @DisplayName("Test forCommitterProcess(InstanceProperties, AmazonS3, AmazonDynamoDB, Configuration) with 'instanceProperties', 's3', 'dynamoDB', 'configuration'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreFactory StateStoreFactory.forCommitterProcess(InstanceProperties, AmazonS3, AmazonDynamoDB, Configuration)"})
  void testForCommitterProcessWithInstancePropertiesS3DynamoDBConfiguration() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    AmazonS3Client s3 = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();

    // Act
    StateStoreFactory actualForCommitterProcessResult = StateStoreFactory.forCommitterProcess(instanceProperties, s3,
        dynamoDB, new Configuration());
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    // Assert
    assertThrows(RuntimeException.class, () -> actualForCommitterProcessResult.getStateStore(tableProperties));
    verify(tableProperties).get(isA(TableProperty.class));
  }

  /**
   * Test {@link StateStoreFactory#createProvider(InstanceProperties, AmazonS3, AmazonDynamoDB, Configuration)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFactory#createProvider(InstanceProperties, AmazonS3, AmazonDynamoDB, Configuration)}
   */
  @Test
  @DisplayName("Test createProvider(InstanceProperties, AmazonS3, AmazonDynamoDB, Configuration); given one; then calls getInt(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.statestore.StateStoreProvider StateStoreFactory.createProvider(InstanceProperties, AmazonS3, AmazonDynamoDB, Configuration)"})
  void testCreateProvider_givenOne_thenCallsGetInt() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();

    // Act
    StateStoreFactory.createProvider(instanceProperties, s3Client, dynamoDBClient, new Configuration());

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
  }

  /**
   * Test {@link StateStoreFactory#getStateStore(TableProperties)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFactory#getStateStore(TableProperties)}
   */
  @Test
  @DisplayName("Test getStateStore(TableProperties); given 'Get'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.core.statestore.StateStore StateStoreFactory.getStateStore(TableProperties)"})
  void testGetStateStore_givenGet_thenThrowRuntimeException() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    AmazonS3Client s3 = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    StateStoreFactory forCommitterProcessResult = StateStoreFactory.forCommitterProcess(instanceProperties, s3,
        dynamoDB, new Configuration());
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> forCommitterProcessResult.getStateStore(tableProperties));
    verify(tableProperties).get(isA(TableProperty.class));
  }
}
